package templateMethod;

public abstract class PlayPES
{
	/**
	 * ����
	 */
	public void play()
	{
		chooseTeam();
		chooseShirt();
		chooseFormation();
	}

	/**
	 * ѡ�����
	 */
	protected abstract void chooseTeam();

	/**
	 * ѡ������
	 */
	protected abstract void chooseShirt();

	/**
	 * ѡ������
	 */
	private final void chooseFormation()
	{
		System.out.println("442");
	}
}
