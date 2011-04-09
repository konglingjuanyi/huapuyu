package prototype.simple;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Tester
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
		// ���cloneʱ����������
		// x.clone != x
		// x.clone.getClass() == x.getClass()
		// x.clone().equals(x)
		Prototype p1 = new ConcretePrototype();
		System.out.println(p1.getClass().getName() + "\t" + p1.hashCode());

		Prototype p2 = (Prototype) p1.clone();
		System.out.println(p2.getClass().getName() + "\t" + p2.hashCode());

		if (p1.equals(p2))
		{
			System.out.println("true");
		}
		else
		{
			System.out.println("false");
		}
	}
}
