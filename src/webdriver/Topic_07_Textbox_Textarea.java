package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_07_Textbox_Textarea {
	WebDriver driver;
	Actions action;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emloyeeId = String.valueOf(rand.nextInt(99999));
	String firstName, middleName, lastName, password;
	String passportNumber, comment;
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		firstName = "Geni";
		middleName = "Quoc";
		lastName = "Nguyen";
		password = "Test1234!";
		passportNumber = "ABC1234567";
		comment = "Loream\\n\\n123123";
	}

	@Test
	public void TC_01_Create_New_Employee() {
		// login
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.cssSelector(".orangehrm-login-button")).click();
		sleepInSecond(3);
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		
		// add employee
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		sleepInSecond(3);
		driver.findElement(By.name("firstName")).sendKeys(firstName);
		driver.findElement(By.name("middleName")).sendKeys(middleName);
		driver.findElement(By.name("lastName")).sendKeys(lastName);
		WebElement employeeIdTextbox = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"));
		employeeIdTextbox.sendKeys(Keys.CONTROL + "a");
		employeeIdTextbox.sendKeys(Keys.DELETE);
		employeeIdTextbox.sendKeys(emloyeeId);
		// click login details
		driver.findElement(By.xpath("//p[text()='Create Login Details']/following-sibling::div")).click();
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div//input")).sendKeys(firstName + emloyeeId);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div//input")).sendKeys(password);
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div//input")).sendKeys(password);
		

		driver.findElement(By.xpath("//button[contains(., 'Save')]")).click();
		sleepInSecond(10);
		
		// verify employee
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.name("middleName")).getAttribute("value"), middleName);
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), lastName);
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"))
				.getAttribute("value"), emloyeeId);

		// click immigration
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(4);
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();
		sleepInSecond(2);
		driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div//input")).sendKeys(passportNumber);
		driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(comment);
		driver.findElement(By.xpath("//button[contains(., 'Save')]")).click();
		sleepInSecond(5);
		// verify immigration
		driver.findElement(By.cssSelector(".bi-pencil-fill")).click();
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div//input"))
				.getAttribute("value"), passportNumber);
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea"))
				.getAttribute("value"), comment);
	
		// verify login
		driver.findElement(By.cssSelector(".oxd-userdropdown-name")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		sleepInSecond(3);
		
		driver.findElement(By.name("username")).sendKeys(firstName + emloyeeId);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.cssSelector(".orangehrm-login-button")).click();
		sleepInSecond(3);
		driver.findElement(By.xpath("//span[text()='My Info']")).click();
		sleepInSecond(3);
		
		// verify employee
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.name("middleName")).getAttribute("value"), middleName);
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), lastName);
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"))
				.getAttribute("value"), emloyeeId);
		
		//verify immigration
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(3);
		driver.findElement(By.cssSelector(".bi-pencil-fill")).click();
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div//input"))
				.getAttribute("value"), passportNumber);
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea"))
				.getAttribute("value"), comment);

	}

	@Test
	public void TC_02_Verify_Employee() {
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