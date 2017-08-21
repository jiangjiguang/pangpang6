package com.pangpang6.books.algorithm.randomforest;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class RandomForestMain {
	static String[] parameters=new String[]{
			"diff_scrndt_vlhght",
			"abs_diff_scrndt_vlhght",
			"diff_hdr_hstrylngth",
			"diff_scrndt_rlwdth",
			"nvgtr_plgns_sim",
			"abs_diff_pt_nldvntstrt",
			"diff_pt_dmntrctv",
			"diff_pt_rspnsnd",
			"idf_scrndt_vlhght",
			"diff_scrndt_dvcpxlrt",
			"abs_diff_hdr_hstrylngth",
			"abs_diff_pt_rspnsnd",
			"abs_diff_pt_rspnsstrt",
			"abs_diff_pt_rqststrt",
			"abs_diff_pt_ldvntnd",
			"idf_bsc_brwsr",
			"idf_f_vrsn",
			"diff_pt_dmcmplt",
			"idf_wbgl_nmskdrndrr",
			"idf_hdr_hstrylngth",
			"idf_wbgl_mxvrtxnfrmvctrs",
			"abs_diff_scrndt_rlhght",
			"idf_brwsrtyp_schrm",
			"abs_diff_pt_dmcntntlddvntstrt",
			"idf_wbgl_mxfrgmntnfrmvctrs",
			"idf_scrndt_dvcpxlrt",
			"pt_dmcnt_sediff",
			"idf_scrndt_vlwdth",
			"abs_diff_scrndt_dvcpxlrt",
			"idf_brwsrtyp_swbkt",
			"idf_nvgtr_mmtyps",
			"idf_wbrtc_dvccnt",
			"idf_wbrtc_dvcnm",
			"idf_o_hsllan",
			"pt_rdrct_sediff",
			"abs_diff_pt_rdrctnd",
			"idf_wbgl_mxcmbndtxtrmgnts",
			"idf_wbgl_mxvrtxtxtrmgnts",
			"diff_scrndt_pxldpth",
			"idf_nvgtr_lngg",
			"idf_wbrtc_cspprt"
	};
	static int[] pIndex=null;
	public static void main(String[] args) throws IOException {
		String model="D:\\did\\m\\trees";
		String testdata="D:\\did\\m\\output_test.csv";
		
		RandomForest forest=ModelLanguageParser.parserForest(model);
		test(forest,testdata,",");
		
		//double d1=1.4810035e+12;
		//double d2=1.48100349952e+12;
		//BigDecimal bd=new BigDecimal(d2);
		//bd.setScale(2, RoundingMode.HALF_UP);
		
		//System.out.println("d1="+d1+",d2="+d2+", bd2="+bd.floatValue());
		
		String s1="0.42025603354";
//		String s2="1.48100349952e+12";
		double d=Double.parseDouble(s1);
		float f1=Float.parseFloat(s1);
		float f2=(float)d;
		Double dd=d;
//		float f2=Float.parseFloat(s2);
		System.out.println("f1="+f1+",f2="+f2+",ddf="+dd.floatValue()+", dd="+dd.doubleValue());
		String lds=Long.toBinaryString(Double.doubleToLongBits(d));
		String lrds=Long.toBinaryString(Double.doubleToRawLongBits(d));
		String fds=Integer.toBinaryString(Float.floatToIntBits(f1));
		String frds=Integer.toBinaryString(Float.floatToRawIntBits(f1));
		System.out.println("ld1="+lds);
		System.out.println("ld2="+lrds);
		System.out.println("fd1="+fds);
		System.out.println("fd2="+frds);
		System.out.println("fd3="+Integer.toBinaryString(Float.floatToIntBits(f2)));
		System.out.println("fd4="+Integer.toBinaryString(Float.floatToRawIntBits(f2)));
		
	}

	public static int[] sort(String[] header){
		int[] parameterIndex=new int[parameters.length];
		for(int i=0;i<parameters.length;i++){
			for(int j=3;j<header.length;j++){
				if(header[j].equals(parameters[i])){
					parameterIndex[i]=j;
					break;
				}
			}
		}
		return parameterIndex;
	}
	
	public static void test(RandomForest forest,String file, String seperator) throws NumberFormatException, IOException{
		File filename = new File(file);  
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename),"UTF-8");  
        BufferedReader br = new BufferedReader(reader);  
        String line=br.readLine();
        String[] header=line.split(seperator);
        pIndex=sort(header);
        long spend=0L;
        int i=0;
        while((line=br.readLine())!=null){
        	i++;
        	line=line.trim();
        	if(line.isEmpty()) continue;
        	String[] parts=line.split(seperator);
        	float[] p=new float[parameters.length];
        	for(int m=0;m<parameters.length;m++){
        		if(parts[pIndex[m]]=="") p[m]=0.0f;
        		else{
        			//p[m]=Double.parseDouble(parts[pIndex[m]].trim());
        			p[m]=Float.parseFloat(parts[pIndex[m]].trim());
        		}
        	}
        	long start=System.nanoTime();
        	BigDecimal sum=RandomForestEngine.execute(forest, new RandomForestParameters(p));
        	spend+=(System.nanoTime()-start);
        	
        	double expected=Double.parseDouble(parts[parts.length-1].trim());
        	BigDecimal bdexpected=new BigDecimal(parts[parts.length-1].trim());
        	sum=sum.setScale(7, RoundingMode.HALF_UP);
        	bdexpected=bdexpected.setScale(7, RoundingMode.HALF_UP);
        	if(sum.compareTo(bdexpected)!=0){
        		System.out.println(i+":sum="+sum+",expected="+bdexpected+",raw="+parts[parts.length-1]);
        	}
        	
        	
//        	float expected=Float.parseFloat(parts[parts.length-1].trim());
//        	if(Float.compare(sum, expected)!=0){
//        		System.out.println(i+":sum="+sum+",expected="+expected+",raw="+parts[parts.length-1]);
//        	}
        	
//            if(i==69704){
//            	System.out.println("69704:sum1="+Integer.toBinaryString(Float.floatToIntBits(sum)));
//        		System.out.println("69704:sum2="+Integer.toBinaryString(Float.floatToRawIntBits(sum)));
//        		System.out.println("69704:exp1="+Integer.toBinaryString(Float.floatToIntBits(expected)));
//        		System.out.println("69704:exp2="+Integer.toBinaryString(Float.floatToRawIntBits(expected)));
//            }
        }
        System.out.println("total:"+spend+", avg="+(spend/i));
        br.close();
	}

}
