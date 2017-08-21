package com.pangpang6.books.algorithm.randomforest;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TreeNodeInner extends TreeNodeBase {

	private int pIndex;
	private float val;
	@JsonIgnore
	private Function fun;
	private TreeNodeBase left;
	private TreeNodeBase right;
	
	public TreeNodeInner(short id, float gini, int good, int bad, int pIndex, float val, Function fun){
		super(id, gini, good, bad,TreeNodeBase.INNER_NODE);
		this.pIndex=pIndex;
		this.val=val;
		this.fun=fun;
		
	}

	public int getpIndex() {
		return pIndex;
	}

	public double getVal() {
		return val;
	}

	public Function getFun() {
		return fun;
	}
	public TreeNodeBase getLeft() {
		return left;
	}
	public void setLeft(TreeNodeBase left) {
		this.left = left;
	}
	public TreeNodeBase getRight() {
		return right;
	}
	public void setRight(TreeNodeBase right) {
		this.right = right;
	}
	
	public boolean selected(float left){
		return fun.apply(left,val);
	}
}
