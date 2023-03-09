package testng;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;

public class Topic_01_Assert {
	public static void main(String[] args) {
		Assert.assertTrue("Automation Testing".contains("manual")); // mong đợi true
		Assert.assertFalse(3<5);
		
		// expected result vs actual result
		Assert.assertEquals(false, false); // mong đợi giống nhau
	}
}
