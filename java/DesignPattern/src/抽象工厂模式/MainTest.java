package ���󹤳�ģʽ;

public class MainTest
{
	public static void main(String[] args)
	{
		Iʡ�� prov = new ����();
		Iˮ�� fruit = prov.factoryFruit();
		I�߲� vege = prov.factoryVegetable();
		fruit.grow();
		fruit.harvest();
		fruit.plant();
		vege.grow();
		vege.harvest();
		vege.plant();

		System.out.println("-------------------------------");

		prov = new ɽ��();
		fruit = prov.factoryFruit();
		vege = prov.factoryVegetable();
		fruit.grow();
		fruit.harvest();
		fruit.plant();
		vege.grow();
		vege.harvest();
		vege.plant();

		// BeanFactory�ǳ��󹤳�ģʽ��������̫���ͣ���Ҫ��һ�����͵ĳ��󹤳�ģʽ
		// XmlBeanFactory
	}
}
