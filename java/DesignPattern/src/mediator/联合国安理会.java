package mediator;

public class ���Ϲ������ extends ���Ϲ�����
{
	private ���� usa;
	private ������ iraq;

	@Override
	public void Declare(String message, ���� colleague)
	{
		if (colleague.equals(usa))
		{
			iraq.GetMessage(message);
		}
		else
		{
			usa.GetMessage(message);
		}
	}

	public ���� getUsa()
	{
		return usa;
	}

	public void setUsa(���� usa)
	{
		this.usa = usa;
	}

	public ������ getIraq()
	{
		return iraq;
	}

	public void setIraq(������ iraq)
	{
		this.iraq = iraq;
	}

}
