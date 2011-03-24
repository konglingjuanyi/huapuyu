package ��������ģʽ;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class MainTest
{
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{
		IFruitGardener fg = new AppleGardener();
		IFruit fruit = fg.factory();
		fruit.grow();
		fruit.harvest();
		fruit.plant();

		fg = new OrangeGardener();
		fruit = fg.factory();
		fruit.grow();
		fruit.harvest();
		fruit.plant();

		// Java�еĹ�������ģʽ
		// Collection���������IFruitGardener
		// ArrayList��HashSet���൱��AppleGardener��OrangeGardener
		// Iterator���������IFruit
		// collection.iterator()�������صľ���Apple��Orange
		Collection collection = new ArrayList();
		collection.add(1);
		collection.add(2);
		// ����java.util.AbstractList$Itr
		System.out.println(collection.iterator().getClass().getName());
		for (Iterator iterator = collection.iterator(); iterator.hasNext();)
		{
			Integer object = (Integer) iterator.next();
			System.out.println(object);
		}

		collection = new HashSet();
		collection.add("zhuzhen");
		collection.add("guolili");
		// ����java.util.HashMap$KeyIterator
		System.out.println(collection.iterator().getClass().getName());
		for (Iterator iterator = collection.iterator(); iterator.hasNext();)
		{
			String object = (String) iterator.next();
			System.out.println(object);
		}
	}
}
