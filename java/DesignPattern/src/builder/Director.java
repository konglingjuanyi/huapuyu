package builder;

public class Director
{
	private Builder builder;

	public Director(Builder builder)
	{
		this.builder = builder;
	}

	public void construct(String from, String to)
	{
		builder.������(from);
		builder.�ռ���(to);
		builder.����();
		builder.����();
	}
}
