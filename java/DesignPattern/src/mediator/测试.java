package mediator;

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
		���Ϲ������ unsc = new ���Ϲ������();
		���� usa = new ����(unsc);
		������ iraq = new ������(unsc);
		unsc.setUsa(usa);
		unsc.setIraq(iraq);

		usa.Declare("����ķ��̨���������ǽ���������");
		iraq.Declare("���Ǿ�����Ͷ��");
	}
}