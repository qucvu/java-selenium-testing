package javaTester;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;


public class Topic_02_Data_Type {
	public static void main(String[] args) {
		// Kiểu dữ liệu nguyên thủy (Primitive)
		// Số nguyên: byte, short, int, long (k có phần thập phân) --> tùy thuộc độ lưu
		// trữ/ độ rộng
		byte bNumber = 123;
		short sNumber = 12123;
		int iNumber = 123123123;
		long lNumber = 123123123;
		// Số thực: float, double (có phần thập phân)
		float fNumber = 9.5f;
		double dNumber = 2.5354534d;
		// Logic: boolean
		boolean status = true;
		status = false;
		// Kí tự: char
		char a = '1';

		// Kiểu dữ liệu tham chiếu (reference)
		// class
		FirefoxDriver firefoxDriver = new FirefoxDriver();
		
		// interface
		WebElement firstNameTextBox;
		
		// string
		String name = "Vũ";
		// object
		Object people;
		// array
		String[] names = {"Vũ", "Trang"};
		
		// collections
		List<WebElement> checkboxes = firefoxDriver.findElements(By.cssSelector(""));		
		// Map
		Map<String, Integer> student = new HashMap<>();

	}
}
