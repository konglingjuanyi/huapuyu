package visitor;

public class Ů�� extends Person
{
	@Override
	public void accept(Action visitor)
	{
		visitor.getWomanConclusion(this);
	}
}
