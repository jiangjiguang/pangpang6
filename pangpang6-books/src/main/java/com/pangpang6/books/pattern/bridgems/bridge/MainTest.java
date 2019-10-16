package com.pangpang6.books.pattern.bridgems.bridge;


import com.pangpang6.books.pattern.bridgems.control.LGControl;
import com.pangpang6.books.pattern.bridgems.control.SharpControl;
import com.pangpang6.books.pattern.bridgems.control.SonyControl;

/**
 * 桥接模式：将实现与抽象放在两个不同的类层次中，使两个层次可以独立改变
 *  目的：分离抽象与实现，使抽象和实现可以独立变化
 *  使用：系统有多维度分类时，每一种分类又有可能变化
 * 抽象继承抽象
 * 实现实现接口（行为）
 *
 * 与策略模式的差异：
 * 策略的目的：将复杂的算法封装起来，从而便于替换不同的算法
 * 桥接模式往往是为了利用已有的方法或类
 * 策略模式是为了扩展和修改，并提供动态配置
 *
 * 桥接模式强调接口对象仅提供基本操作
 * 策略模式强调接口对象提供一种算法
 */
public class MainTest {
    public static void main(String[] args) {
        TvControl mLGTvControl;
        TvControl mSonyTvControl;
        mSonyTvControl = new TvControl(new SonyControl());
        mLGTvControl = new TvControl(new LGControl());
        mLGTvControl.Onoff();
        mLGTvControl.nextChannel();
        mLGTvControl.nextChannel();
        mLGTvControl.preChannel();
        mLGTvControl.Onoff();

        mSonyTvControl.Onoff();
        mSonyTvControl.preChannel();
        mSonyTvControl.preChannel();
        mSonyTvControl.preChannel();
        mSonyTvControl.Onoff();

        newTvControl mSharpTvControl;
        mSharpTvControl = new newTvControl(new SharpControl());
        mSharpTvControl.Onoff();
        mSharpTvControl.nextChannel();
        mSharpTvControl.setChannel(9);
        mSharpTvControl.setChannel(28);
        mSharpTvControl.Back();
        mSharpTvControl.Onoff();

    }


}
