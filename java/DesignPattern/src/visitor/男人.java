package visitor;

public class ���� extends Person
{
	@Override
	public void accept(Action visitor)
	{
		visitor.getManConclusion(this);
	}
}
