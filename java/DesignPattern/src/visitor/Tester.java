package visitor;

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
		ObjectStructure objectStructure = new ObjectStructure();
		objectStructure.attach(new ����());
		objectStructure.attach(new Ů��());

		�ɹ� success = new �ɹ�();
		objectStructure.display(success);

		ʧ�� failure = new ʧ��();
		objectStructure.display(failure);

		���� love = new ����();
		objectStructure.display(love);

	}
}