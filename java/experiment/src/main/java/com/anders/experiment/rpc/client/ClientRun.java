package com.anders.experiment.rpc.client;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.anders.experiment.rpc.api.HelloService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:rpcClient.xml")
public class ClientRun {

	@Autowired
	private RpcProxy rpcProxy;

	@Test
	public void helloTest() {
		// for (int i = 0; i < 3; i++) {
		HelloService helloService = rpcProxy.create(HelloService.class);
		String result = helloService.hello("World");
		Assert.assertEquals("Hello! World", result);
		// }
	}
}
