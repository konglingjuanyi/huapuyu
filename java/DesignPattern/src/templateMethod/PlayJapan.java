package templateMethod;

public class PlayJapan extends PlayPES
{
	@Override
	protected void chooseShirt()
	{
		System.out.println("�ͳ�");
	}

	@Override
	protected void chooseTeam()
	{
		System.out.println("�ձ�");
	}
}
