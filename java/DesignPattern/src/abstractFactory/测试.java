package abstractFactory;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ���� {
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
		I���ֲ� club = new ����ͼ˹();
		Iǰ�� forward = club.factoryǰ��();
		I�� field = club.factory��();
		forward.����();
		forward.����();
		field.����();
		field.�ص�();

		club = new �ʼ������();
		forward = club.factoryǰ��();
		field = club.factory��();
		forward.����();
		forward.����();
		field.����();
		field.�ص�();

		// BeanFactory�ǳ��󹤳�ģʽ��������̫���ͣ���Ҫ��һ�����͵ĳ��󹤳�ģʽ
		// XmlBeanFactory
	}
}
