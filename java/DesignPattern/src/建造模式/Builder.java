package ����ģʽ;

public abstract class Builder
{
	public Builder()
	{
	}

	public abstract void buildSubject();

	public abstract void buildBody();

	public void buildFrom(String from)
	{
		System.out.println("buildFrom");
	}

	public void buildTo(String to)
	{
		System.out.println("buildTo");
	}
}
