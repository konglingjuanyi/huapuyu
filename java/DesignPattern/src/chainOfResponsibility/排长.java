package chainOfResponsibility;

public class �ų� extends Handler
{
	public �ų�(String name)
	{
		this.name = name;
	}

	@Override
	public void handleRequest(String request)
	{
		if ("����ȫ��ʿ��".equals(request))
		{
			System.out.println(this.name + "����" + request);
		}
		else
		{
			System.out.println(this.name + "������" + request + "�����ϼ�" + nextHandler.getName() + "��������");
			nextHandler.handleRequest(request);
		}
	}
}
