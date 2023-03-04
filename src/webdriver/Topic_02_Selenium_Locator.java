package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
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

		// mở trang register
		driver.get("https://www.nopcommerce.com/en/login?returnUrl=%2Fen%2Fdemo");
	}

	//	html của the first name
	// <input class="username" autofocus="" type="text" id="Username" name="Username">
	// Tên thẻ
	// Attribute name
	// Attribute value

	@Test
	public void TC_01_ID() {
		// phải tìm được element đó: findElement theo id/class/css/xpath
		// sau đó action lên element đó
		driver.findElement(By.id("Username")).sendKeys("Automation");;
	}

	@Test
	public void TC_02_Class() {
		driver.findElement(By.className("password")).sendKeys("QuocVu");
	}

	@Test
	public void TC_03_Name() {
		
		// mở trang register
		driver.get("https://www.nopcommerce.com/en/register?returnUrl=%2Fen%2Fdemo");
		
		driver.findElement(By.name("Details.CompanySizeId")).click();
		
	}

	@Test
	public void TC_04_TagName() {
		// tìm ra các thẻ input trên màn hình
		System.out.println(driver.findElements(By.tagName("input")).size());
	}

	@Test
	public void TC_05_LinkText() {
		// click vào đường link (chuỗi tuyệt đối)
		driver.findElement(By.linkText("Store demo")).click();
	}

	@Test
	public void TC_06_PartialLinkText() {
		// click vào đường link Premium support services (chuỗi tương đối --> chỉ cần bao gồm chuỗi)
		driver.findElement(By.partialLinkText("Premium")).click();
	}

	@Test
	public void TC_07_Css() {
		driver.get("https://www.nopcommerce.com/en/login?returnUrl=%2Fen%2Fdemo");
		driver.findElement(By.cssSelector("input#Username")).sendKeys("Selenium");
		driver.findElement(By.cssSelector("input[id='Password']")).sendKeys("Locator");
		driver.findElement(By.cssSelector("input[name='Username']")).sendKeys("Sử dụng name");
		// có thể sử dụng, #abcd, .asdasd, 
	}

	@Test
	public void TC_08_Xpath() {
		driver.findElement(By.xpath("//input[@id='Username']")).sendKeys("Selenium");
		driver.findElement(By.xpath("//input[@name='Password']")).sendKeys("Locator");
//		driver.findElement(By.xpath("//input[@name='Username']")).sendKeys("Sử dụng name");
//		driver.findElement(By.xpath("//label[text() = 'Password:']\\following-sibling::input")).sendKeys("Sử dụng name");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}