package templateMethod;

public class MainClass
{
	public static void main(String[] args)
	{
		PlayPES p1 = new PlayChina();
		PlayPES p2 = new PlayJapan();
		p1.play();
		p2.play();

		// HttpServlet���е�service��������ģ�巽��������ֻ��Ҫ�̳�HttpServlet��doGet, doPost�ȷ���
	}
}
