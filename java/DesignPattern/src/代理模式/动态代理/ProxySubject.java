package ����ģʽ.��̬����;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxySubject implements InvocationHandler
{
	private RealSubject realSubject; // ����ʵ��ɫ��Ϊ�����ɫ������

	public ProxySubject()
	{
	}

	private void preRequest()
	{
		System.out.println("PreRequest");
	}

	private void postRequest()
	{
		System.out.println("PostRequest");
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{
		this.preRequest();

		if (null == realSubject)
			realSubject = new RealSubject();

		Object object = method.invoke(realSubject, args);

		this.postRequest();

		return object;
	}
}
