package simpleFactory;

public class ���乤����
{
	public static ICar getInstance(����Ʒ�� carBrand) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		// ʹ�÷��䴴�����������ַ�ʽ��Ȼ�Ǽ򵥹���ģʽ
		return (ICar) Class.forName("�򵥹���ģʽ." + carBrand).newInstance();
	}
}