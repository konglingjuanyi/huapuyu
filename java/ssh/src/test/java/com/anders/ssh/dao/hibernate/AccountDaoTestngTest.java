package com.anders.ssh.dao.hibernate;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.anders.ssh.bo.test.Account;

@ContextConfiguration(locations = { "classpath:spring-test.xml" })
// public class AccountDaoTestngTest extends AbstractTestNGSpringContextTests {
public class AccountDaoTestngTest extends AbstractTransactionalTestNGSpringContextTests {
	@Resource(name = "hibernateAccountDao")
	private AccountDao accountDao;

	@Test
	@Rollback(true)
	public void testSave() {
		Account account = new Account();
		account.setName("zhuzhen");
		account.setEnable(true);
		accountDao.save(account);

		List<Account> list = accountDao.getAll();
		Assert.assertEquals(1, list.size());
		Assert.assertEquals("zhuzhen", list.get(0).getName());
	}
}
