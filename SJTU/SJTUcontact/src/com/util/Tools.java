package com.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.sjtu.ContactColumn;

import android.database.Cursor;

public class Tools {
	private static CodeUtils codeUtils;
	/**
	 * 
	 * @return 2001-11-11 11:11:11
	 */
	public static String getTime(){
		Date date = new Date();
		return String.format("%tF", date) + " " + String.format("%tT", date);
	}
	
	public static List<User> cursor2User(Cursor cursor,Map<Integer, String> groupMap,boolean codeFlag){
		int count = cursor.getCount();
		List<User> userList = new ArrayList<User>();
		cursor.moveToFirst();
		for (int i = 0; i < count; i++) {
			User user = new User();
			
			user.setId(cursor.getInt(cursor.getColumnIndex(ContactColumn._ID)));
			user.setMobileNumber(cursor.getString(cursor.getColumnIndex(ContactColumn.MOBILE)));
			user.setName(cursor.getString(cursor.getColumnIndex(ContactColumn.NAME)));
			user.setCreatedDate(cursor.getString(cursor.getColumnIndex(ContactColumn.CREATED)));
			user.setModifiedDate(cursor.getString(cursor.getColumnIndex(ContactColumn.MODIFIED)));
			user.setGroupnum(cursor.getInt(cursor.getColumnIndex(ContactColumn.GROUP)));
			user.setGroupName(groupMap.get(user.getGroupnum()));
			user.setIsCode(codeFlag);
			
			userList.add(user);
			cursor.moveToNext();
		}
		return userList;
	}
	
	public static Map<Integer, String> getIdColumnMap(Cursor cursor){
		Map<Integer, String> map = new HashMap<Integer, String>();
		String[] strs = cursor.getColumnNames();
		String id = "_id";
		String others = "";
		for (int i = 0; i < strs.length; i++) {
			if(!strs[i].equals("_id")) others = strs[i];
		}
		int count = cursor.getCount();
		cursor.moveToFirst();
		for (int i = 0; i < count; i++) {
			map.put(cursor.getInt(cursor.getColumnIndex(id)), cursor.getString(cursor.getColumnIndex(others)));
			cursor.moveToNext();
		}
		return map;
	}
	
	public static String enCode (String message){
		if(codeUtils == null ){
			try {
				codeUtils = new CodeUtils();
			} catch (Exception e) {
				e.printStackTrace();
				return "���ܳ���"+e.getMessage();
			}
		}
		try {
			return codeUtils.encrypt(message);
		} catch (Exception e) {
			e.printStackTrace();
			return "���ܳ���"+e.getMessage();
		}
		
	}
	
	public static String deCode (String message){
		if(codeUtils == null ){
			try {
				codeUtils = new CodeUtils();
			} catch (Exception e) {
				e.printStackTrace();
				return "���ܳ���"+e.getMessage();
			}
		}
		try {
			return codeUtils.decrypt(message);
		} catch (Exception e) {
			e.printStackTrace();
			return "���ܳ���"+e.getMessage();
		}
		
	}
}