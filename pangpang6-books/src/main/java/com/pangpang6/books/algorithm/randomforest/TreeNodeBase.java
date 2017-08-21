package com.pangpang6.books.algorithm.randomforest;

public abstract class TreeNodeBase {
	public final static byte INNER_NODE=0x0;
	public final static byte LEAF_NODE=0x1;
	double gini;
	int good;
	int bad;
	short id;
	byte t=LEAF_NODE;
	
	
	public TreeNodeBase(short id, double gini, int good, int bad){
		this.id=id;
		this.gini=gini;
		this.good=good;
		this.bad=bad;
	}
	public TreeNodeBase(short id, double gini, int good, int bad, byte type){
		this.id=id;
		this.gini=gini;
		this.good=good;
		this.bad=bad;
		this.t=type;
	}
	public short getId() {
		return id;
	}
	public double getGini() {
		return gini;
	}
	public int getGood() {
		return good;
	}
	public int getBad() {
		return bad;
	}
	public boolean isLeaf(){
		return t==LEAF_NODE;
	}
}
