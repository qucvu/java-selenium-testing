package webdriver;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element {
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
	public void TC_01_WebElement() {
		WebElement 	webElement= driver.findElement(By.id("email"));
		webElement.clear();
		webElement.sendKeys("?");
		webElement.click();
		
		webElement.getAttribute("placeholder");
		webElement.getAttribute("value");
		
		// test giao diện: font size / font weight / font color
		webElement.getCssValue("color");
		
		webElement.getLocation();
		webElement.getSize();
		Rectangle rec =  webElement.getRect();

		// chụp hình khi test case fail
		webElement.getScreenshotAs(OutputType.BASE64);
		webElement.getScreenshotAs(OutputType.FILE);
		webElement.getScreenshotAs(OutputType.BYTES);
		
		webElement.getTagName(); // tên thể
		webElement.getClass(); //lấy class
		
		webElement.getText();
		
		// verify hiển thị hay k
		// phạm vi: tất cả element
		assertTrue(webElement.isDisplayed());
		
		assertTrue(webElement.isEnabled());
		assertTrue(webElement.isSelected());
		
		webElement.submit();
		

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