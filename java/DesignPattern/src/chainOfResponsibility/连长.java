package chainOfResponsibility;

public class ���� extends Handler
{
	public ����(String name)
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
