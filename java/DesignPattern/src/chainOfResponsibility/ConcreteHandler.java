package chainOfResponsibility;

public class ConcreteHandler extends Handler
{
	@Override
	public void handleRequest(String request)
	{
		if (getNextHandler() != null)
		{
			System.out.println("�޷�����������һ������");
			nextHandler.handleRequest(request);
		}
		else
		{
			System.out.println("����");
		}
	}
}
