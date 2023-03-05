package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_XPath_Part_1 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_EmptyData() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

		// Assert.assertTrue() --> ktra 1 dki trả về đúng
		// Assert.assertFalse() --> ktra 1 dki trả về sai
		// Assert.assertEqual() --> ktra thực tế và mong đợi như nhau

		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");
	}

	@Test
	public void TC_02_Invalid_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.id("txtFirstname")).sendKeys("Nguyễn Quốc Vũ");
		driver.findElement(By.id("txtEmail")).sendKeys("vu1@vsđsfá123@123");
		driver.findElement(By.id("txtCEmail")).sendKeys("vu1@vsđsfá123@123");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0326156464");

		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
	}

	@Test
	public void TC_03_Incorrect_Confirm_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.id("txtFirstname")).sendKeys("Nguyễn Quốc Vũ");
		driver.findElement(By.id("txtEmail")).sendKeys("vu@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("vu1@vsđsf");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0326156464");

		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");

	}

	@Test
	public void TC_04_Invalid_Password() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.id("txtFirstname")).sendKeys("Nguyễn Quốc Vũ");
		driver.findElement(By.id("txtEmail")).sendKeys("vu@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("vu@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123");
		driver.findElement(By.id("txtCPassword")).sendKeys("1234");
		driver.findElement(By.id("txtPhone")).sendKeys("0326156464");

		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),
				"Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),
				"Mật khẩu phải có ít nhất 6 ký tự");

	}

	@Test
	public void TC_06_Incorrect_Confirm_Passowrd() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.id("txtFirstname")).sendKeys("Nguyễn Quốc Vũ");
		driver.findElement(By.id("txtEmail")).sendKeys("vu@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("vu@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("1234567");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0326156464");

		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");

	}

	@Test
	public void TC_06_Invalid_Phone() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.id("txtFirstname")).sendKeys("Nguyễn Quốc Vũ");
		driver.findElement(By.id("txtEmail")).sendKeys("vu@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("vu@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("1234567");
		driver.findElement(By.id("txtCPassword")).sendKeys("1234567");
		driver.findElement(By.id("txtPhone")).sendKeys("01234567");

		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		// verify 9 số
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");

		// verify 11 số
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("01234567891025");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");

		// verify nhà mạng
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("123456");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),
				"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}