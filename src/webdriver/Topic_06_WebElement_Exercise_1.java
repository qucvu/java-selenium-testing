package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_WebElement_Exercise_1 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	By emailTextBoxBy = By.id("mail");
	By isUnder18Radio = By.id("under_18");
	By educationTextBoxBy = By.id("edu");
	By nameUser5TextBy = By.xpath("//h5[text()='Name: User5']");
	By javaCheckboxBy = By.id("java");
	By biographyTextAreaBy = By.cssSelector("#bio");
	By passowrdTextBox = By.id("disable_password");
	By developmentCheckbox = By.id("development");
	
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Textbox/Textarea nếu có hiển thị thì nhập text vào console
		if (driver.findElement(emailTextBoxBy).isDisplayed()) {
			driver.findElement(emailTextBoxBy).sendKeys("Selenium Webdriver");
			System.out.println("Email textbox is displayed");
		} else {
			System.out.println("Email textbox is not displayed");
		}

		// Textarea
		if (driver.findElement(educationTextBoxBy).isDisplayed()) {
			driver.findElement(educationTextBoxBy).sendKeys("Selenium Grid");
			System.out.println("Education Textarea is displayed");
		} else {
			System.out.println("Education Textarea is not displayed");

		}

		if (driver.findElement(isUnder18Radio).isDisplayed()) {
			driver.findElement(isUnder18Radio).click();
			System.out.println("Age under 18 is displayed");
		} else {
			System.out.println("Age under 18 is not displayed");
		}

		if (driver.findElement(nameUser5TextBy).isDisplayed()) {
			System.out.println("Name user 5 is displayed");
		} else {
			System.out.println("Name user 5 is not displayed");
		}
		
		
		
	}

	@Test
	public void TC_02_Enable() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		if(driver.findElement(passowrdTextBox).isEnabled()) {
			System.out.println("Password textbox is enabled");
		}else {
			System.out.println("Password textbox is disabled");
		}


		if(driver.findElement(biographyTextAreaBy).isEnabled()) {
			System.out.println("Biography Textarea is enabled");
		}else {
			System.out.println("Biography Textarea is disabled");
		}

		if(driver.findElement(developmentCheckbox).isEnabled()) {
			System.out.println("Development checkbox is enabled");
		}else {
			System.out.println("Development checkbox is disabled");
		}

	}

	@Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Assert.assertFalse(driver.findElement(isUnder18Radio).isSelected());
		Assert.assertFalse(driver.findElement(developmentCheckbox).isSelected());

		//click
		driver.findElement(isUnder18Radio).click();
		driver.findElement(developmentCheckbox).click();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(isUnder18Radio).isSelected());
		Assert.assertTrue(driver.findElement(developmentCheckbox).isSelected());

	}	
	
	@Test
	public void TC_04_Register_MailChimp() {
		driver.get("https://login.mailchimp.com/signup/");
		sleepInSecond(5);
		driver.findElement(By.id("email")).sendKeys("abc@gmail.com");
		By passwordText = By.id("new_password");
		By signupButtonBy = By.id("create-account-enabled");
		
		
		// Băt đầu nhập
		// nhập chữ cái thường
		driver.findElement(passwordText).sendKeys("abcd");
		driver.findElement(signupButtonBy).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		// nhập chữ hoa
		driver.findElement(passwordText).clear();
		driver.findElement(passwordText).sendKeys("ABCD");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

		// nhập số
		driver.findElement(passwordText).clear();
		driver.findElement(passwordText).sendKeys("1234");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

		// nhập kí tự đặc biệt
		driver.findElement(passwordText).clear();
		driver.findElement(passwordText).sendKeys("@.;',");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

		// nhập 8 kí tự và chữ cái thường
		driver.findElement(passwordText).clear();
		driver.findElement(passwordText).sendKeys("nguyenvu");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());

		// nhập đầy đủ thông tin
		driver.findElement(passwordText).clear();
		driver.findElement(passwordText).sendKeys("Nguyenvu123!");
		sleepInSecond(1);
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
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