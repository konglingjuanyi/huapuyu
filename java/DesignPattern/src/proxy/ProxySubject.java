package proxy;

public class ProxySubject extends Subject
{
	private RealSubject realSubject; // ����ʵ��ɫ��Ϊ�����ɫ������

	public ProxySubject()
	{
	}

	// �÷�����װ����ʵ�����request����
	@Override
	public void request()
	{
		preRequest();

		if (null == realSubject)
			realSubject = new RealSubject();

		realSubject.request(); // �˴�ִ����ʵ�����request����

		postRequest();
	}

	private void preRequest()
	{
		System.out.println("preRequest.");
	}

	private void postRequest()
	{
		System.out.println("postRequest.");
	}
}
