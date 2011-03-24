package ����ģʽ.��̬����;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MainTest
{
	public static void main(String[] args) throws IllegalArgumentException, InstantiationException, IllegalAccessException
	{
		Subject subject = (Subject) Proxy.newProxyInstance(RealSubject.class.getClassLoader(), RealSubject.class.getInterfaces(), new ProxySubject());
		subject.request();

		MyInterface1 proxy1 = (MyInterface1) MyHandle.getObject(MyClass1.class);
		System.out.println(proxy1.getClass().getName());
		proxy1.sayHello();
		MyInterface2 proxy2 = (MyInterface2) MyHandle.getObject(MyClass2.class);
		System.out.println(proxy2.getClass().getName());
		proxy2.sayHello();
	}
}

interface MyInterface1
{
	public void sayHello();
}

class MyClass1 implements MyInterface1
{
	@Override
	public void sayHello()
	{
		System.out.println("fuck xwc");
	}
}

interface MyInterface2
{
	public void sayHello();
}

class MyClass2 implements MyInterface2
{
	@Override
	public void sayHello()
	{
		System.out.println("fuck wl");
	}
}

class MyProxy implements InvocationHandler
{
	private Object target;

	public MyProxy(Object target)
	{
		this.target = target;
	}

	public void before()
	{
		System.out.println("before");
	}

	public void after()
	{
		System.out.println("after");
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{
		this.before();
		method.invoke(this.target, args);
		this.after();
		return null;
	}
}

// ��̬������ʵ����java.lang.reflect.Proxy�ද̬�ĸ�����ָ�������нӿ�����һ��class byte��
// ��class��̳�Proxy�࣬��ʵ��������ָ���Ľӿڣ����ڲ����д���Ľӿ����飩��
// Ȼ����������ָ����classloader��class byte���ؽ�ϵͳ��
// �����������һ����Ķ��󣬲���ʼ���ö����һЩֵ����invocationHandler��
// �Լ����еĽӿڶ�Ӧ��Method��Ա�� ��ʼ��֮�󽫶��󷵻ظ����õĿͻ��ˡ������ͻ����õ��ľ���һ��ʵ�������еĽӿڵ�Proxy����
class MyHandle
{
	@SuppressWarnings("unchecked")
	public static Object getObject(Class c) throws IllegalArgumentException, InstantiationException, IllegalAccessException
	{
		return Proxy.newProxyInstance(c.getClassLoader(), c.getInterfaces(), new MyProxy(c.newInstance()));
	}
}