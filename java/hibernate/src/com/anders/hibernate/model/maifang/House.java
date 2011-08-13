package com.anders.hibernate.model.maifang;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ����
 * 
 * @author Anders
 * 
 */
@Entity
@Table(name = "tb_house")
public class House implements Serializable {
	private static final long serialVersionUID = -3838557037798985561L;

	/**
	 * ��ţ�������
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/**
	 * ¥������
	 */
	@Column(nullable = false, length = 50)
	private String title;
	/**
	 * ʡ����������ֱϽ�б�ţ���Ӧ�������ñ�����0��
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "province_id")
	private Area province;
	/**
	 * ���б�ţ���Ӧ�������ñ�����1��
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id")
	private Area city;
	/**
	 * �����ء��б�ţ���Ӧ�������ñ�����2��
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id")
	private Area district;
	/**
	 * ��ϸ��ַ
	 */
	@Column(nullable = false, length = 100)
	private String address;
	/**
	 * ��
	 */
	@Column(nullable = false)
	private Byte bedroomCount;
	/**
	 * ��
	 */
	@Column(nullable = false)
	private Byte livingRoomCount;
	/**
	 * ��
	 */
	@Column(nullable = false)
	private Byte kitchenCount;
	/**
	 * ��
	 */
	@Column(nullable = false)
	private Byte washroomCount;
	/**
	 * ��̨
	 */
	@Column(nullable = false)
	private Byte balconyCount;
	/**
	 * ����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orientation_id")
	private Data orientation;
	/**
	 * ��ҵ����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "property_type_id")
	private Data propertyType;
	/**
	 * �������
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "construction_year_id")
	private Data constructionYear;
	/**
	 * װ�޳̶�
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "decoration_id")
	private Data decoration;
	/**
	 * ��¥��
	 */
	@Column(nullable = false)
	private Byte totalFloor;
	/**
	 * ����¥��
	 */
	@Column(nullable = false)
	private Byte floor;
	/**
	 * ��ͨ״��
	 */
	@Column(length = 500)
	private String transport;
	/**
	 * �ܱ�����
	 */
	@Column(length = 500)
	private String environment;
	/**
	 * ��Դ����
	 */
	@Column(length = 500)
	private String remark;
	/**
	 * ������ʩ
	 */
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "house")
	private Set<Facility> facilities = new HashSet<Facility>(0);
	/**
	 * ��Դ��ɫ
	 */
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "house")
	private Set<Feature> features = new HashSet<Feature>(0);
	/**
	 * ���ַ�
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "second_hand_house_id")
	// @OneToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "second_hand_house_id")
	private SecondHandHouse secondHandHouse;
}
