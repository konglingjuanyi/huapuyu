package ��������ģʽ;

public class OrangeGardener implements IFruitGardener
{
	public IFruit factory()
	{
		return new Orange();
	}
}
