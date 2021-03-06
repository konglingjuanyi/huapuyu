package com.anders.ssh.ioc;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * Pojo对象
 * 
 * @author Anders Zhu
 * 
 */
public class Pojo {
	private int id;
	private String name;
	private float score;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
