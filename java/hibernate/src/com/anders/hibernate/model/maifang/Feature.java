package com.anders.hibernate.model.maifang;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ��Դ��ɫ
 * 
 * @author Anders
 * 
 */
@Entity
@Table(name = "rlt_feature")
public class Feature implements Serializable {
	private static final long serialVersionUID = -3838557037798985561L;

	/**
	 * ��ţ�������
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/**
	 * ���ݱ��
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "house_id")
	private House house;
	/**
	 * �������ñ��
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "data_id")
	private Data data;
}
