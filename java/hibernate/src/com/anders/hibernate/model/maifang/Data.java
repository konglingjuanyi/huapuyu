package com.anders.hibernate.model.maifang;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ��������
 * 
 * @author Anders
 * 
 */
@Entity
@Table(name = "cfg_data")
public class Data implements Serializable {
	private static final long serialVersionUID = 5409243230923358227L;

	public enum DataType {

		/**
		 * 0�����򣨷��ݣ�
		 */
		ORIENTATION,
		/**
		 * 1����ҵ���ͣ����ݣ�
		 */
		PROPERTY_TYPE,
		/**
		 * 2��������������ݣ�
		 */
		CONSTRUCTION_YEAR,
		/**
		 * 3��װ�޳̶ȣ����ݣ�
		 */
		DECORATION,
		/**
		 * 4��������ʩ�����ݣ�
		 */
		FACILITY,
		/**
		 * 5����Դ��ɫ�����ݣ�
		 */
		FEATURE,
		/**
		 * 6����Ȩ���ʣ����ַ���
		 */
		PROPERTY_RIGHT,
		/**
		 * 7��סլ��𣨶��ַ���
		 */
		RESIDENCE_TYPE,
		/**
		 * 8��������𣨶��ַ���
		 */
		CONSTRUCTION_TYPE,
		/**
		 * 9�����ݽṹ�����ַ���
		 */
		BUILDING_STRUCTURE,
		/**
		 * 10������ʱ�䣨���ַ���
		 */
		VISIT_TIME,
		/**
		 * 11�����ⷽʽ���ⷿ��
		 */
		SHARE_TYPE,
		/**
		 * 12���������Ա�Ҫ���ⷿ��
		 */
		ROOMMATE_GENDER_PREFERENCE,
		/**
		 * 13��֧����ʽ���ⷿ��
		 */
		PAYMENT,
		/**
		 * 14����סʱ�䣨�ⷿ��
		 */
		CHECK_IN_TIME
	}

	/**
	 * ��ţ�������
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/**
	 * ����
	 */
	@Column(nullable = false, length = 50)
	private String title;
	/**
	 * ����
	 */
	@Enumerated
	@Column(nullable = false)
	private DataType type;
	/**
	 * ���÷���1�����ã�0��ͣ�ã�
	 */
	@Column(nullable = false)
	private Boolean enable = true;
}
