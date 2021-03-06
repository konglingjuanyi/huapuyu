package com.anders.ssh.dao.jpa;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

import com.anders.ssh.bo.test.Account;

@RunWith(UnitilsJUnit4TestClassRunner.class)
@SpringApplicationContext("classpath:spring-jpa-test.xml")
public class AccountDaoUnitilsTest extends UnitilsJUnit4 {
	// TODO Anders Zhu : 如果dao类上有事务注解(@Transactional)，会报如下错误（原因可能是没有用接口注入）：
	// [2011-09-06 23:06:03] [org.springframework.test.context.TestContextManager] ERROR : Caught
	// exception while allowing TestExecutionListener
	// [org.springframework.test.context.support.DependencyInjectionTestExecutionListener@8fa0d1] to
	// prepare test instance [com.anders.ssh.dao.hibernate.DataDaoTest@18706f6]
	// (TestContextManager.java:324)
	// org.springframework.beans.factory.BeanCreationException: Error creating bean with name
	// 'com.anders.ssh.dao.hibernate.DataDaoTest': Injection of resource dependencies failed; nested
	// exception is org.springframework.beans.factory.BeanNotOfRequiredTypeException: Bean named
	// 'jpaDataDao' must be of type [com.anders.ssh.dao.jpa.DataDao], but was actually of type
	// [$Proxy22]
	// @Autowired
	// @Qualifier("jdbcAccountDao")
	@SpringBean("jpaAccountDao")
	private AccountDao accountDao;

	// 奇了怪了，必须使用unitils进行测试，如果使用spring测试框架进行测试，写操作无法提交，可能是没有找到事务管理器的原因
	@Test
	@Transactional(transactionManagerName = "jpaTxManager")
	public void testSave() {
		Account account = new Account();
		account.setName("zhuzhen");
		account.setEnable(true);
		accountDao.save(account);

		List<Account> list = accountDao.getAll();
		Assert.assertEquals(1, list.size());
		Assert.assertEquals("zhuzhen", list.get(0).getName());
	}

	@Test
	@DataSet("AccountDaoTest.xml")
	@Transactional(transactionManagerName = "jpaTxManager")
	public void test二级缓存() {
		List<Account> list = accountDao.getAll();
		System.out.println(list.size());
		list = accountDao.getAll();
	}
}
