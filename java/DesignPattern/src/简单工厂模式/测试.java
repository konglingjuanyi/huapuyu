package �򵥹���ģʽ;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ��������ģʽ.CreateCarException;

public class ����
{
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}

	@Before
	public void setUp() throws Exception
	{
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void ��ͨ������() throws CreateCarException
	{
		ICar ���� = ��ͨ������.factory(����Ʒ��.����);
		����.����();
		����.ֹͣ();

		ICar �µ� = ��ͨ������.factory(����Ʒ��.�µ�);
		�µ�.����();
		�µ�.ֹͣ();

		// Java�еļ򵥹���ģʽ��SimpleDateFormat��DateFormat
		Date date = new Date();
		System.out.println(SimpleDateFormat.getDateInstance(DateFormat.DEFAULT).format(date));
		System.out.println(SimpleDateFormat.getDateInstance(DateFormat.FULL).format(date));
	}

	@Test
	public void ���乤����() throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		ICar ���� = ���乤����.getInstance(����Ʒ��.����);
		����.����();
		����.ֹͣ();

		ICar �µ� = ���乤����.getInstance(����Ʒ��.�µ�);
		�µ�.����();
		�µ�.ֹͣ();
	}
}
