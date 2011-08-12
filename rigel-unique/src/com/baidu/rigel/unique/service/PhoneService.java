package com.baidu.rigel.unique.service;

import java.util.List;
import java.util.Map;

import com.baidu.rigel.platform.service.GenericService;
import com.baidu.rigel.unique.bo.Phone;

public interface PhoneService extends GenericService<Phone, Long> {
	List<Long> selectDisCustIdByFullPhone(String fullPhone);

	List<Map<String, Object>> selectDisCustIdFullNameByPhoneNumAreaCode(String phoneAreaCode, String phoneNumber);
}