package com.anders.hibernate.model.maifang;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * ��������
 * 
 * @author Anders
 * 
 */
@Entity
@Table(name = "cfg_area")
public class Area implements Serializable {
	private static final long serialVersionUID = -3838557037798985561L;

	public enum AreaType {
		/**
		 * 0��ʡ����������ֱϽ��
		 */
		PROVINCE,
		/**
		 * 1������
		 */
		CITY,
		/**
		 * 2�������ء���
		 */
		DISTRICT
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
	 * ���ͣ�0��ʡ����������ֱϽ�У�1�����У�2�������ء��У�
	 */
	@Enumerated
	@Column(nullable = false)
	private AreaType type;
	/**
	 * �ϼ�����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Area parentArea;
	/**
	 * �¼�����
	 */
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "parentArea")
	@OrderBy("id")
	private List<Area> sonAreas = new ArrayList<Area>(0);
	/**
	 * ���÷���1�����ã�0��ͣ�ã�
	 */
	@Column(nullable = false)
	private Boolean enable = true;
}
