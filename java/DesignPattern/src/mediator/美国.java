package mediator;

public class ���� extends ����
{
	public ����(���Ϲ����� mediator)
	{
		super(mediator);
	}

	public void Declare(String message)
	{
		mediator.Declare(message, this);
	}

	public void GetMessage(String message)
	{
		System.out.println("������öԷ���Ϣ��" + message);
	}
}
