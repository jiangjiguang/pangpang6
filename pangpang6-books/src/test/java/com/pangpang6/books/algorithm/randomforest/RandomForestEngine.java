package com.pangpang6.books.algorithm.randomforest;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class RandomForestEngine {
	
	public static BigDecimal execute(RandomForest forest,RandomForestParameters parameters) {
		RandomForestParameters p=RandomForestParameters.class.cast(parameters);
		float sum=0.0f;
		double tmp=0.0;
		for(int i=0;i<forest.treeCount();i++){
			TreeNodeBase tree=forest.tree(i);
			TreeNodeBase node=tree;
			if(i==49){
				node.getBad();
			}
			while(!node.isLeaf()){
				TreeNodeInner parent=TreeNodeInner.class.cast(node);
				if(parent.selected(parameters.at(parent.getpIndex()))){
					node=parent.getLeft();
				}else{
					node=parent.getRight();
				}
				if(node==null){
					node=parent;
					break;
				}
			}
			double delta=((double)node.getGood()/(node.getGood()+node.getBad()));
			tmp+=delta;
		}
		double r=tmp/forest.treeCount();
		BigDecimal bd=new BigDecimal(r).setScale(8, RoundingMode.FLOOR);
		
		return bd;
		//return (float)(tmp/forest.treeCount());
		//BigDecimal bd=new BigDecimal(tmp/forest.treeCount());
		//return bd.floatValue();
		//Double d=tmp/forest.treeCount();
		//return d.floatValue();
		//return sum/forest.treeCount();
	}

}
