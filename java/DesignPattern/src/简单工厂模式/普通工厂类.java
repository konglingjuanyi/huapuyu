package �򵥹���ģʽ;

public class ��ͨ������
{
	public static ICar factory(����Ʒ�� carBrand) throws CreateCarException
	{
		switch (carBrand)
		{
		case ����:
			return new ����();
		case �µ�:
			return new �µ�();
		case ����:
			return new ����();
		default:
			throw new CreateCarException("���������쳣");
		}
	}
}