package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_WebElement_Exercise_2 {
	WebDriver driver;
	Random rand;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	By myAccountButtonBy = By.xpath("//div[@class='footer']//a[@title='My Account']");
	By buttonLoginBy = By.cssSelector("button[title='Login']");
	String emailAddress, firstName, lastName, password, fullName;
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new ChromeDriver();
		rand = new Random();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		emailAddress = "automation" + rand.nextInt(999)+ "@gmail.com";
		firstName = "Geni";
		lastName ="Nguyen";
		fullName = firstName + " " +  lastName;
		password = "12345678";
		
	}

	@Test
	public void TC_01_Empty_Email_Password() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(myAccountButtonBy).click();
		sleepInSecond(1);
		driver.findElement(buttonLoginBy).click();
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
	}

	@Test
	public void TC_02_Invalid_Email() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(myAccountButtonBy).click();
		sleepInSecond(1);
		driver.findElement(By.cssSelector("#email")).sendKeys("cbhc@gasd");
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(buttonLoginBy).click();
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), 
				"Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void TC_03_Password_Less_than_6_Chars() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(myAccountButtonBy).click();
		sleepInSecond(1);
		driver.findElement(By.cssSelector("#email")).sendKeys("vu@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("12345");
		driver.findElement(buttonLoginBy).click();
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), 
				"Please enter 6 or more characters without leading or trailing spaces.");
	}	
	
	@Test
	public void TC_04_Register_Incorrect_Email_Password() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(myAccountButtonBy).click();
		sleepInSecond(1);
		driver.findElement(By.cssSelector("#email")).sendKeys(emailAddress);
		driver.findElement(By.id("pass")).sendKeys("123465");
		driver.findElement(buttonLoginBy).click();
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), 
				"Invalid login or password.");
	}
	
	
	@Test
	public void TC_05_Create_New_Account() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(myAccountButtonBy).click();
		sleepInSecond(1);

		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");
		String contactInformationText = driver.findElement(By.xpath
				("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p")).getText();
		Assert.assertTrue(contactInformationText.contains(fullName));
		Assert.assertTrue(contactInformationText.contains(emailAddress));
		
		
		driver.findElement(By.xpath("//div[@class='page-header-container']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		
		// verify đã về trang home
		Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src, 'logo.png')]")).isDisplayed());
	}

	@Test 
	public void TC_06_Login_Valid_Info() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(myAccountButtonBy).click();
		sleepInSecond(1);
		driver.findElement(By.cssSelector("#email")).sendKeys(emailAddress);
		driver.findElement(By.id("pass")).sendKeys(password);
		driver.findElement(buttonLoginBy).click();
		
		Assert.assertFalse(driver.getPageSource().contains("Thank you for registering with Main Website Store."));
		String contactInformationText = driver.findElement(By.xpath
				("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p")).getText();
		Assert.assertTrue(contactInformationText.contains(fullName));
		Assert.assertTrue(contactInformationText.contains(emailAddress));
	}
	

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}