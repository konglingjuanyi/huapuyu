package com.anders.experiment.多线程.syn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * synchronized如果放在方法的前面，其实也是锁住对象，因此对于同一个对象， 如果调用同样有synchronized标识的两个不同方法method1和method2，后调用的方法会等待先调用的结束后再执行。
 * 
 * @author Anders Zhu
 * 
 */
public class Tester {

	public static void main(String[] args) {
		接口 i = new 非同步类();

		线程1 t1 = new 线程1(i);
		t1.start();

		线程2 t2 = new 线程2(i);
		t2.start();

		i = new 同步类();

		t1 = new 线程1(i);
		t1.start();

		t2 = new 线程2(i);
		t2.start();
	}
}

class 线程1 extends Thread {
	private 接口 i;

	public 线程1(接口 i) {
		this.i = i;
	}

	@Override
	public void run() {
		i.方法1();
		super.run();
	}
}

class 线程2 extends Thread {
	private 接口 i;

	public 线程2(接口 i) {
		this.i = i;
	}

	@Override
	public void run() {
		i.方法2();
		super.run();
	}
}

interface 接口 {
	void 方法1();

	void 方法2();
}

class 同步类 implements 接口 {
	private static final Logger LOG = LoggerFactory.getLogger(同步类.class);

	@Override
	public synchronized void 方法1() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LOG.debug(this.getClass().getSimpleName() + "方法1执行完毕");
	}

	@Override
	public synchronized void 方法2() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LOG.debug(this.getClass().getSimpleName() + "方法2执行完毕");
	}
}

class 非同步类 implements 接口 {
	private static final Logger LOG = LoggerFactory.getLogger(非同步类.class);

	@Override
	public void 方法1() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LOG.debug(this.getClass().getSimpleName() + "方法1执行完毕");
	}

	@Override
	public void 方法2() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LOG.debug(this.getClass().getSimpleName() + "方法2执行完毕");
	}
}
