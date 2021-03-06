package com.anders.ssh.dao.mongo;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.anders.ssh.bo.test.Account;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-test.xml" })
public class AccountDaoTest {
	@Resource(name = "mongoAccountDao")
	private AccountDao accountDao;

	@Test
	public void testCRUD() {
		Account account = new Account();
		account.setId(1L);
		account.setName("zhuzhen");
		account.setEnable(true);
		accountDao.save(account);
		List<Account> list = accountDao.getAll();
		Assert.assertEquals(1, list.size());
		Assert.assertEquals("zhuzhen", list.get(0).getName());

		// account.setName("guolili");
		// accountDao.update(account);
		// list = accountDao.getAll();
		// Assert.assertEquals(1, list.size());
		// Assert.assertEquals("guolili", list.get(0).getName());

		accountDao.deleteById(1L);
		list = accountDao.getAll();
		Assert.assertEquals(0, list.size());
	}
}
