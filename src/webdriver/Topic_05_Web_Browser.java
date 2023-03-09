package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser {
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
	public void TC_01_() {
		driver.close();
		driver.quit();
		WebElement email =  driver.findElement(By.id("123"));
		email.clear();
		email.sendKeys("123");
		driver.getPageSource();
		// dùng 1 lần không cần tạo biến
		driver.findElements(By.xpath("//input[@id='name']"));
		List<WebElement> checkboxes = driver.findElements(By.className("checkbox"));
		
		// mở url
		driver.get("https://www.facebook.com/");
		String vietnamePageUrl = driver.getCurrentUrl();
		
		//verify tương đối
		Assert.assertTrue(driver.getPageSource().contains("Facebook giúp bạn kết nối"));
		String id = driver.getWindowHandle();
		Set<String> ids = driver.getWindowHandles();
		
		//cookie và cache
		Options opt = driver.manage();
		opt.getCookies();
		Timeouts timeout = opt.timeouts();
		timeout.implicitlyWait(5, TimeUnit.SECONDS);
		timeout.implicitlyWait(5000, TimeUnit.MILLISECONDS);
		timeout.implicitlyWait(5000000, TimeUnit.MICROSECONDS);
		
		timeout.pageLoadTimeout(5, TimeUnit.SECONDS);
		timeout.setScriptTimeout(5, TimeUnit.NANOSECONDS);
		
		Window window = opt.window();
		window.fullscreen();// giống f11 màn hình
		window.maximize();// mở full màn hình tab
		
		// test giao diện
		window.getPosition(); // lấy vị trí màn hình vd (0 0)
		window.getSize();
		Point aPoint = new Point(1, 2);
		window.setPosition(aPoint);
		
		Navigation nav = driver.navigate();
		nav.back();
		nav.forward();
		nav.refresh();
		nav.to("https://facebook.com");
		TargetLocator tar =  driver.switchTo();
		tar.alert();
		tar.frame(0);
		tar.window("1");
		
		
	}

	@Test
	public void TC_02_() {
	}

	@Test
	public void TC_03_() {
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}