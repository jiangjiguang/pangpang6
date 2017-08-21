package com.pangpang6.books.algorithm.randomforest;

public class RandomForestParameters extends Parameters {
	private float[] parameters;
	public RandomForestParameters(float[] parameters){
		this.parameters=parameters;
	}
	public float at(int i){
		return parameters[i];
	}
}
