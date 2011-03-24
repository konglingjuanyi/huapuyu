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
		System.out.println("preRequest.");
	}

	private void postRequest()
	{
		System.out.println("postRequest.");
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{
		if (null == realSubject)
			realSubject = new RealSubject();

		this.preRequest();

		method.invoke(realSubject, args);

		this.postRequest();

		return null;
	}
}
