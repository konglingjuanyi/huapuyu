package com.anders.ssh.service;

import java.io.Serializable;
import java.util.List;

public interface GenericService<PK extends Serializable, T> {
	T getById(PK id);

	void save(T entity);

	void update(T entity);

	void delete(T entity);

	void deleteById(PK id);

	List<T> getAll();

	void saveOrUpdate(T entity);
}
