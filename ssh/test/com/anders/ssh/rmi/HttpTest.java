package com.anders.ssh.rmi;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-remote-test.xml" })
public class HttpTest {
	@Autowired
	private AndersHttp andersHttp;

	@Test
	public void testGetUserInfo() {
		Map<Long, String> map = andersHttp.getHttp();
		Assert.assertEquals(2, map.size());
		Assert.assertEquals("zhuzhen", map.get(1L));
		Assert.assertEquals("guolili", map.get(2L));
	}
}
