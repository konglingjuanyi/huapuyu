package dao.hibernate;

import model.Data;

import org.springframework.stereotype.Component;

import dao.hibernate.impl.BaseDao;

@Component
public class DataDao extends BaseDao<Integer, Data>
{
}
