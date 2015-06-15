package com.anders.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.retry.RetryOneTime;
import org.apache.curator.retry.RetryUntilElapsed;
import org.junit.Test;

public class CuratorTester {
	// 查看路径状态
	@Test
	public void test1() {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		retryPolicy = new RetryNTimes(3, 1000);
		retryPolicy = new RetryOneTime(1000);
		retryPolicy = new RetryUntilElapsed(1000, 1000);

		CuratorFramework client = CuratorFrameworkFactory.builder()
				.connectString("anders1:2181,anders2:2181,anders3:2181")
				.namespace("anders_test").retryPolicy(retryPolicy)
				.connectionTimeoutMs(5000).sessionTimeoutMs(5000).build();
		client.start();

		System.out.println(client.getState());
	}

	// 创建路径，指定namespace
	@Test
	public void test2() throws Exception {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework client = CuratorFrameworkFactory.builder()
				.connectString("anders1:2181,anders2:2181,anders3:2181")
				.namespace("anders_test").retryPolicy(retryPolicy)
				.connectionTimeoutMs(5000).sessionTimeoutMs(5000).build();
		client.start();
		client.create().creatingParentsIfNeeded()
				.forPath("/test1", "helloworld".getBytes());
	}

	// 创建路径，不指定namespace
	@Test
	public void test3() throws Exception {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework client = CuratorFrameworkFactory.builder()
				.connectString("anders1:2181,anders2:2181,anders3:2181")
				.retryPolicy(retryPolicy).connectionTimeoutMs(5000)
				.sessionTimeoutMs(5000).build();
		client.start();
		client.create().creatingParentsIfNeeded()
				.forPath("/anders_test/test2", "sayhello".getBytes());
	}
}
