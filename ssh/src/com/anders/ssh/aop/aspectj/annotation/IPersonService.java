package com.anders.ssh.aop.aspectj.annotation;

public interface IPersonService {
	public String save(String name, int i);

	public void update(String name);

	public void get(String name, long i);
}
