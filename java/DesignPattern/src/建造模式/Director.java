package ����ģʽ;

public class Director
{
	private Builder builder;

	public Director(Builder builder)
	{
		this.builder = builder;
	}

	public void construct(String from, String to)
	{
		builder.buildSubject();
		builder.buildBody();
		builder.buildFrom(from);
		builder.buildTo(to);
	}
}
