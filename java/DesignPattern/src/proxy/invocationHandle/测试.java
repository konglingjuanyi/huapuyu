package proxy.invocationHandle;

import java.lang.reflect.Proxy;

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

	// ��̬������ʵ����java.lang.reflect.Proxy�ද̬�ĸ�����ָ�������нӿ�����һ��class byte��
	// ��class��̳�Proxy�࣬��ʵ��������ָ���Ľӿڣ����ڲ����д���Ľӿ����飩��
	// Ȼ����������ָ����classloader��class byte���ؽ�ϵͳ��
	// �����������һ����Ķ��󣬲���ʼ���ö����һЩֵ����invocationHandler��
	// �Լ����еĽӿڶ�Ӧ��Method��Ա�� ��ʼ��֮�󽫶��󷵻ظ����õĿͻ��ˡ������ͻ����õ��ľ���һ��ʵ�������еĽӿڵ�Proxy����
	@Test
	public void test()
	{
		Subject subject = (Subject) Proxy.newProxyInstance(RealSubject.class.getClassLoader(), RealSubject.class.getInterfaces(), new ProxySubject());
		subject.request();
	}
}