package ����ģʽ;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ����
{
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}

	@Before
	public void setUp() throws Exception
	{
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void test()
	{
		System.out.println(SingletonClass.getInstance().toString());
		System.out.println(SingletonClass.getInstance().toString());
		System.out.println(SingletonClass.getInstance().toString());
		System.out.println(SingletonClass2.getInstance().toString());
		System.out.println(SingletonClass2.getInstance().toString());
		System.out.println(SingletonClass2.getInstance().toString());

		// Java�еķǳ����͵ĵ���ģʽ
		try
		{
			Runtime.getRuntime().exec("notepad.exe");
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}