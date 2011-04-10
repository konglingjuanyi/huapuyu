package annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Parser
{
	public void parser() throws SecurityException, NoSuchMethodException, NoSuchFieldException
	{
		TestClass testClass = new TestClass();

		// ���ע��
		Annotation4Class annotation4Class = testClass.getClass().getAnnotation(Annotation4Class.class);
		System.out.println(annotation4Class.myClass());

		// ������ע��
		Method method = testClass.getClass().getMethod("myMethod", new Class[0]);
		Annotation4Method annotation4Method = method.getAnnotation(Annotation4Method.class);
		System.out.println(annotation4Method.myMethod());

		// �ֶε�ע��
		Field field = testClass.getClass().getDeclaredField("myField");
		Annotation4Field annotation4Field = field.getAnnotation(Annotation4Field.class);
		System.out.println(annotation4Field.myField());
		System.out.println(annotation4Field.isTrue());

		testClass.myMethod();
	}
}
