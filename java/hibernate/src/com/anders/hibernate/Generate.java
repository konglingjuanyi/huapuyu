package com.anders.hibernate;

import org.apache.commons.lang.SystemUtils;
import org.hibernate.Session;
import org.junit.Test;

public class Generate {
	@Test
	public void generate() {
		// TODO Anders Zhu :
		// ע��lib���е�jaybird21.dll��libjaybird21.so���������Firebird���ݿ���Ҫ���������ļ�����java.library.path������JAVA_HOME/bin�£�������ᱨ��no
		// jaybird21 in java.library.path��
		System.out.println(System.getProperty("java.library.path"));
		System.out.println(SystemUtils.JAVA_LIBRARY_PATH);
		Session session = HibernateUtil.getSession();
		// Transaction tx = session.beginTransaction();
		// Tb_user user = new Tb_user();
		// user.setName("zhuzhen");
		// user.setPwd("123");
		// session.save(user);
		//
		// tx.commit();

		// House house = (House) session.get(House.class, 1L);
		//
		// StringBuilder sBuilder = new StringBuilder();
		// sBuilder.append(house.getName());
		// sBuilder.append(house.getCity().getName());
		// sBuilder.append(house.getConstructYear().getName());
		// sBuilder.append(house.getDistrict().getName());
		// sBuilder.append(house.getRentHouse().getRoommateGender().getName());
		// sBuilder.append(house.getSecondHandHouse().getPropType().getName());
		// System.out.println(sBuilder.toString());

		// System.out.println(user.getId());
	}

}
