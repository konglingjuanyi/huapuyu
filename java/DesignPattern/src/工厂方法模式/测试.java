package ��������ģʽ;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

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
		I���󹤳��� factory = new �µϹ�����();
		ICar car = factory.factory();
		car.����();
		car.ֹͣ();

		factory = new ��������();
		car = factory.factory();
		car.����();
		car.ֹͣ();

		// Java�еĹ�������ģʽ
		// Collection���൱�ڳ��󹤳���
		// ArrayList��HashSet���൱�ڰµϹ�����ͱ�������
		// Iterator���൱��ICar
		// collection.iterator()���൱��factory.factory()���ذµϻ���
		Collection<String> collection = new ArrayList<String>();
		collection.add("�µ�");
		collection.add("����");
		// ����java.util.AbstractList$Itr
		// private class Itr implements Iterator<E>
		System.out.println(collection.iterator().getClass().getName());
		for (Iterator<String> iterator = collection.iterator(); iterator.hasNext();)
		{
			String object = (String) iterator.next();
			System.out.println(object);
		}

		collection = new HashSet<String>();
		collection.add("����");
		collection.add("����");
		// ����java.util.HashMap$KeyIterator
		// private final class KeyIterator extends HashIterator<K>
		// private abstract class HashIterator<E> implements Iterator<E> {
		System.out.println(collection.iterator().getClass().getName());
		for (Iterator<String> iterator = collection.iterator(); iterator.hasNext();)
		{
			String object = (String) iterator.next();
			System.out.println(object);
		}
	}
}
