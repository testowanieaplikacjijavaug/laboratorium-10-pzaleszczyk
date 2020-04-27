
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginTest {

	private static WebDriver driver;


	@BeforeAll
	public static void setUpDriver() {
		String os = System.getProperty("os.name");
		if(os.contentEquals("Windows 10")) {
			System.out.println("Windows");
			System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
		}
		else
			System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setHeadless(true);
		driver = new ChromeDriver(chromeOptions);
		// Implicity wait -> max czas na znalezienie elementu na stronie
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@BeforeEach
	public void setUp() throws Exception {
		driver.get("https://www.reddit.com/login/?dest=https%3A%2F%2Fwww.reddit.com%2F");
	}
	
	@AfterEach
	public void ref() {
		driver.get("https://www.reddit.com/login/?dest=https%3A%2F%2Fwww.reddit.com%2F");
	}

	@AfterAll
	public static void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void noPasswordNoLogin() throws InterruptedException {
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/form/div/fieldset[5]/button")).click();
		TimeUnit.SECONDS.sleep(1);
		String text = driver.findElement(By.xpath("//*[@id=\"default-register\"]/div")).getText();
		assertEquals("Username must be between 3 and 20 characters", text);

	}

	@Test
	public void onlyPassword() throws InterruptedException {
		driver.findElement(By.name("password")).sendKeys("testpassword");
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/form/div/fieldset[5]/button")).click();
		TimeUnit.SECONDS.sleep(1);
		String text = driver.findElement(By.xpath("//*[@id=\"default-register\"]/div")).getText();
		assertEquals("Username must be between 3 and 20 characters", text);
	}

	@Test
	public void onlyLogin() throws InterruptedException {
		driver.findElement(By.name("username")).sendKeys("testlogin");
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/form/div/fieldset[5]/button")).click();
		TimeUnit.SECONDS.sleep(1);
		String text = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/form/div/fieldset[2]/div")).getText();
		assertEquals("Incorrect password", text);
	}

	@Test
	public void withLoginAndPassword() throws InterruptedException {
		driver.findElement(By.name("username")).sendKeys("testloing");
		driver.findElement(By.name("password")).sendKeys("testpasword");
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/form/div/fieldset[5]/button")).click();
		TimeUnit.SECONDS.sleep(1);

		String text = driver.findElement(By.className("AnimatedForm__errorMessage")).getText();
		assertEquals("Incorrect username or password", text);
	}
	//  //*[@id="default-register"]/div
	//  /html/body/div/div/div[2]/div/form/div/fieldset[2]/div
	

	
}