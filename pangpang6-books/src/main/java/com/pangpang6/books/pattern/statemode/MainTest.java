package com.pangpang6.books.pattern.statemode;


/**
 * 状态模式：能根据内部状态的变化，改变对象的行为，看起来好像修改了类
 * 每个状态包装成一个行为对象，状态整数封装成对象
 * 每个行为抽象成一个接口的一个方法
 *
 *
 * 与策略模式、模板模式的区别：
 * 策略模式：不同的行为有不同的表现
 * 模板表示：步骤化
 * 状态模式：在某个状态下行为不一样
 */
public class MainTest {
	public static void main(String[] args) {
		CandyMachine mCandyMachine=new CandyMachine(1);
		
		mCandyMachine.printstate();
		
		mCandyMachine.insertCoin();
		mCandyMachine.printstate();
		
		mCandyMachine.turnCrank();
		
		mCandyMachine.printstate();
		
		mCandyMachine.insertCoin();
		mCandyMachine.printstate();
		
		mCandyMachine.turnCrank();
		
		mCandyMachine.printstate();
	}
}
