package ����;

public class ���ݶ����
{
	public static void main(String[] args)
	{
		new ���ݶ����().test(1, 2, 3, 4, 5);
	}

	public void test(int... params)
	{
		for (int i : params)
		{
			System.out.println(i);
		}
	}
}
