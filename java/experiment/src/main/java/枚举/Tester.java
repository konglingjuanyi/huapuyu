package ö��;

import java.util.HashMap;
import java.util.Map;

public class Tester {
	public static void main(String[] args) throws Exception {
		Type t1 = Type.����;
		Type t2 = Type.������;
		System.out.println(t1);
		System.out.println(t2);
		System.out.println(t1.getValue());
		System.out.println(t2.getValue());
		System.out.println(Type.����.getValue());
		System.out.println(Type.������.getValue());

		System.out.println("----------------------");

		for (Period period : Period.values()) {
			System.out.println(period.name());
			System.out.println(period.ordinal());
			System.out.println(period.getLabel());
			System.out.println(period.getValue());
			System.out.println(Period.LONG);
			System.out.println(Period.SHORT);
			System.out.println("----------------------");
		}

		Map<String, String> map = new HashMap<String, String>();
		// map.put("123", SourceType.CUST_SOURCE_BLACKLIST);
	}
}

enum SourceType {
	/**
	 * ����
	 */
	CUST_SOURCE_SALE {
		@Override
		public String toString() {
			return "custSourceSale";
		}
	},
	/**
	 * ʮ��
	 */
	CUST_SOURCE_SHIFEN {
		@Override
		public String toString() {
			return "custSourceShifen";
		}
	},
	/**
	 * ������
	 */
	CUST_SOURCE_BLACKLIST {
		@Override
		public String toString() {
			return "custSourceBlacklist";
		}
	}
}
