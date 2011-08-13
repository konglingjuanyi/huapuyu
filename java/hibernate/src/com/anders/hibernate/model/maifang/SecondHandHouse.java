package com.anders.hibernate.model.maifang;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ���ַ�
 * 
 * @author Anders
 * 
 */
@Entity
@Table(name = "tb_second_hand_house")
public class SecondHandHouse implements Serializable {
	private static final long serialVersionUID = 4779933743319140372L;

	/**
	 * ��ţ�������
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/**
	 * �ۼ�
	 */
	@Column(nullable = false, scale = 2)
	private BigDecimal price;
	/**
	 * �������
	 */
	@Column(nullable = false, scale = 2)
	private BigDecimal buildingArea;
	/**
	 * ʹ�����
	 */
	@Column(nullable = false, scale = 2)
	private BigDecimal usableArea;
	/**
	 * ��Ȩ����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "property_right_id")
	private Data propertyRight;
	/**
	 * סլ���
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "residence_type_id")
	private Data residenceType;
	/**
	 * �������
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "construction_type_id")
	private Data constructionType;
	/**
	 * ���ݽṹ
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "building_structure_id")
	private Data buildingStructure;
	/**
	 * ����ʱ��
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "visit_time_id")
	private Data visitTime;
	/**
	 * ����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "house_id")
	// @OneToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "house_id")
	private House house;
}
