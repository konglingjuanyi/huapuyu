package com.anders.dp.结构模式.装饰;

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
		齐天大圣 monkey = new 大圣本尊();
		齐天大圣 fish = new 鱼(monkey);
		fish.move();

		// InputStream
		// FileInputStream extends InputStream
		// BufferedInputStream extends FilterInputStream
		// BufferedInputStream是一个半透明的装饰类
	}
}
