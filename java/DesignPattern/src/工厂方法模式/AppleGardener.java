package ��������ģʽ;

public class AppleGardener implements IFruitGardener
{
	public IFruit factory()
	{
		return new Apple();
	}
}
