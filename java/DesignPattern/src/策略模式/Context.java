package ����ģʽ;

public class Context
{
	private Strategy strategy;

	public Context(Strategy strategy)
	{
		this.strategy = strategy;
	}

	public void doSomething()
	{
		this.strategy.doSomething();
	}
}
