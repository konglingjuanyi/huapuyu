package ԭʼģ��ģʽ.�Ǽ���ʽ;

public class ConcretePrototype implements Prototype
{
	public synchronized Object clone()
	{
		Prototype tmp = null;

		try
		{
			tmp = (Prototype) super.clone();
			return tmp;
		}
		catch (CloneNotSupportedException e)
		{
			e.printStackTrace();
		}

		return tmp;
	}
}
