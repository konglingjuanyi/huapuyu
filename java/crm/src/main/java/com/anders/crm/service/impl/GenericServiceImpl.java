package com.anders.crm.service.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.anders.crm.dao.GenericDao;
import com.anders.crm.service.GenericService;

/**
 * Generic Service Implement
 * 
 * @author Anders Zhu
 * 
 */
@Transactional
public abstract class GenericServiceImpl<PK extends Serializable, T> implements GenericService<PK, T> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected abstract GenericDao<PK, T> getDao();

	public void save(T entity) {
		getDao().save(entity);
	}
}
