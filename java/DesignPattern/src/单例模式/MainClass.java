package ����ģʽ;

import java.io.IOException;

public class MainClass
{
	public static void main(String[] args)
	{
		System.out.println(SingletonClass.getInstance().toString());
		System.out.println(SingletonClass.getInstance().toString());
		System.out.println(SingletonClass.getInstance().toString());
		System.out.println(SingletonClass2.getInstance().toString());
		System.out.println(SingletonClass2.getInstance().toString());
		System.out.println(SingletonClass2.getInstance().toString());

		// java�еķǳ����͵ĵ���ģʽ
		try
		{
			Runtime.getRuntime().exec("notepad.exe");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
