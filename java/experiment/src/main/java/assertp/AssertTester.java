package assertp;

import junit.framework.Assert;

public class AssertTester {
	public static void main(String[] args) {
		String name = null;
		Assert.assertNotNull(name);
		System.out.println("hello zhuzhen");
		org.springframework.util.Assert.notNull(name, "name is null");
		System.out.println("hello zhuzhen");
	}
}
