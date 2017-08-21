package com.pangpang6.books.algorithm.randomforest;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pangpang6.utils.MyJSONMapper;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ModelLanguageParser {
	public static RandomForest parserForest(String dir) throws NumberFormatException, IOException{
		File d=new File(ModelLanguageParser.class.getClassLoader().getResource(dir).getPath());
		File[] files=d.listFiles(new FilenameFilter(){
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".dot");
			}
			
		});
		List<File> alltrees=Lists.newArrayList(files);
		Collections.sort(alltrees, new Comparator<File>(){

			@Override
			public int compare(File o1, File o2) {
				String n1=o1.getName().substring(5, o1.getName().indexOf("."));
				String n2=o2.getName().substring(5, o2.getName().indexOf("."));
				int i1=Integer.parseInt(n1);
				int i2=Integer.parseInt(n2);
				return i1-i2;
			}
			
		});
		TreeNodeBase[] roots=new TreeNodeBase[files.length];
		for(int i=0;i<alltrees.size();i++){
			Tree t=parser(alltrees.get(i));
			roots[i]=t.getRoot();
		}
		return new RandomForest(roots);
	}
	public static Tree parser(File file) throws NumberFormatException, IOException{
		 InputStreamReader reader = new InputStreamReader(new FileInputStream(file),"UTF-8");  
	        BufferedReader br = new BufferedReader(reader);
	        String line=null; 
	        Tree tree=new Tree();
	        
	        Map<Short, TreeNodeBase> nodes=Maps.newHashMap(); 
	        while((line=br.readLine())!=null){
	        	StringBuffer sb=new StringBuffer(line);
	        	if(!Character.isDigit(sb.charAt(0))){
	        		continue;
	        	}
	        	int pre=0;
	        	int pos=0;
	        	while(sb.charAt(pos)==' ') pos++;
	        	pre=pos;
	        	short nid=0;
	        	while(sb.charAt(pos)!=' '){
	        		nid=(short)(nid*10+sb.charAt(pos)-'0');
	        		pos++;
	        	}
	        	while(sb.charAt(pos)==' ') pos++;
	        	pre=pos;
	        	int parameterIndex=0;
	        	float conditionValue=0.0f; 
	        	Function fun=null;
	        	boolean isLeaf=false;
	        	if(sb.charAt(pos)=='[' &&
	        	   sb.charAt(pos+1)=='l' &&
	        	   sb.charAt(pos+2)=='a' &&
	        	   sb.charAt(pos+3)=='b' &&
	        	   sb.charAt(pos+4)=='e' &&
	        	   sb.charAt(pos+5)=='l' &&
	        	   sb.charAt(pos+6)=='=' &&
	        	   sb.charAt(pos+7)=='"'
	        			){
	        		pos+=8;
	        		if(sb.charAt(pos)=='X'){//inner
	        			pos++;//skip 'X'
	        			pos++;//skip '[
	        			while(sb.charAt(pos)!=']'){
	        				parameterIndex=parameterIndex*10+(sb.charAt(pos)-'0');
	        				pos++;
	        			}
	        			pos++;//skip ']'
	        			while(sb.charAt(pos)==' ') pos++;
	        			if(sb.charAt(pos)=='<' && sb.charAt(pos+1)=='='){
	        				fun=new LessEqual();
	        			}else if(sb.charAt(pos)=='>' && sb.charAt(pos+1)=='='){
	        				fun=new GreaterEqual();
	        			}
	        			pos+=2;
	        			while(sb.charAt(pos)==' ') pos++;
	        			pre=pos;
	        			while(sb.charAt(pos)!='\\' && sb.charAt(pos+1)!='n') pos++;
	        			conditionValue=Float.parseFloat(sb.substring(pre, pos));
	        			//conditionValue=Double.parseDouble(sb.substring(pre, pos));
	        			pos+=2;
	        		}else{//leaf
	        			isLeaf=true;
	        		}
	        		
	        		//gini
	            	float gini=0.0f;
	            	if(sb.charAt(pos++)=='g' &&
	                 	   sb.charAt(pos++)=='i' &&
	                	   sb.charAt(pos++)=='n' &&
	                	   sb.charAt(pos++)=='i' ){
	            		while(sb.charAt(pos)==' ' || sb.charAt(pos)=='=') pos++;
	            		pre=pos;
	            		while(sb.charAt(pos)!='\\' && sb.charAt(pos+1)!='n') pos++;
	            		//gini=Double.parseDouble(sb.substring(pre, pos));
	            		gini=Float.parseFloat(sb.substring(pre, pos));
	            		pos+=2;
	            	}
	            	//skip sampe
	            	while(sb.charAt(pos)!='\\' && sb.charAt(pos+1)!='n') pos++;
	            	pos+=2;
	            	//good,bad
	            	int good=0,bad=0;
	            	if(sb.charAt(pos++)=='v' &&
	                  	   sb.charAt(pos++)=='a' &&
	                 	   sb.charAt(pos++)=='l' &&
	                 	   sb.charAt(pos++)=='u' &&
	                 	   sb.charAt(pos++)=='e' ){
	            		while(sb.charAt(pos)==' ' || sb.charAt(pos)=='=') pos++;
	            		//assert sb.charAt(pos++)=='[';
	            		pos++;//skip '[';
	            		pre=pos;
	            		while(sb.charAt(pos)!=','){
	            			bad=bad*10+sb.charAt(pos)-'0';
	            			pos++;
	            		}
	            		pos++;//skip ','
	            		while(sb.charAt(pos)==' ') pos++;
	            		while(sb.charAt(pos)!=']'){
	            			good=good*10+sb.charAt(pos)-'0';
	            			pos++;
	            		}
	            	}
	            	TreeNodeBase node=null;
	            	if(isLeaf){
	            		node=new TreeNodeLeaf(nid,gini,good,bad);
	            	}else{
	            		node=new TreeNodeInner(nid,gini,good,bad,parameterIndex,conditionValue,fun);
	            	}
	        		nodes.put(nid, node);
	        		if(nid==0) tree.setRoot(node);
	        	}else if(sb.charAt(pos)=='-' &&
	             	   sb.charAt(pos+1)=='>' ){
	        		pos+=2;
	        		short childId=0;
	        		while(sb.charAt(pos)==' ') pos++;
	        		while(sb.charAt(pos)!=' ' && sb.charAt(pos)!=';'){
	        			childId=(short)(childId*10+sb.charAt(pos)-'0');
	        			pos++;
	        		}
	        		TreeNodeInner parent=(TreeNodeInner)nodes.get(nid);
	        		TreeNodeBase child=nodes.get(childId);
	        		if(parent.getLeft()==null) parent.setLeft(child);
	        		else if(parent.getRight()==null) parent.setRight(child);
	        		else{
	        			throw new RuntimeException("Invalid relation:"+sb);
	        		}
	        	}
	        }
	        tree.setSize(nodes.size());
			return tree;
	}
	public static Tree parser(String fileName) throws IOException{
		File f = new File(fileName);  
       return parser(f);
	}
	public static void main(String[] args) throws IOException{
		String filename="D:\\did\\tree1.dot";
		Tree t=parser(filename);
		System.out.println(MyJSONMapper.nonDefaultMapper().toJSONString(t));
		
		System.out.println("Finished.");
	}
	@SuppressWarnings("deprecation")
	public static boolean verify(Tree tree){
		TreeNodeInner inner;
		TreeNodeLeaf leaf;
		inner=(TreeNodeInner)tree.getRoot();		
		return true;
	}
}
