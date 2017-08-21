package com.pangpang6.books.algorithm.randomforest;

public class RandomForest {
	TreeNodeBase[] roots;
	
	public RandomForest(TreeNodeBase[] roots){
		this.roots=roots;
	}
	
	public TreeNodeBase tree(int index){
		return roots[index];
	}
	public int treeCount(){
		return roots.length;
	}
}
