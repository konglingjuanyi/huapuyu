package com.anders.dp.结构模式.缺省适配;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Tester {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		和尚 h = new 鲁智深();
		h.习武();
		System.out.println(h.getName());

		// 参考下面的类
		// WindowAdapter
		//
		// class Closer extends WindowAdapter implements Serializable{
		// public void windowClosing(WindowEvent e) {
		// cancelButton.doClick(0);
		// Window w = e.getWindow();
		// w.hide();
		// }
		// }
	}
}
