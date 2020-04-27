
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class XPath3Test {

	private static WebDriver driver;

	@BeforeAll
	public static void setUpDriver(){
		String os = System.getProperty("os.name");
		if(os.contentEquals("Windows 10")) {
			System.out.println("Windows");
			System.setProperty("webdriver.gecko.driver", "resources/geckodriver.exe");
		}
		else
			System.setProperty("webdriver.gecko.driver", "resources/geckodriver");
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		firefoxOptions.setHeadless(true);
		driver = new FirefoxDriver(firefoxOptions);
		// Implicity wait -> max czas na znalezienie elementu na stronie
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@BeforeEach
	public void setUp() throws Exception {
		driver.get("https://inf.ug.edu.pl/studinfo");
	}

	@AfterAll
	public static void tearDown() throws Exception {
		driver.quit();
	}



	@Test
	public void countImg() {
		List<WebElement> elements = driver.findElements(By.xpath("//img"));
		int amount = 0;
		for ( WebElement element : elements ) {
			amount++;
		}
		assertEquals(23, amount);
	}

	@Test
	public void countHrefs() {
		List<WebElement> elements = driver.findElements(By.xpath("//a"));
		int amount = 0;
		for ( WebElement element : elements ) {
			if(element.getAttribute("href") != null)
				amount++;
		}
		assertEquals(82, amount);
	}
	
	@Test
	public void enterHrefs() {
		//*[@id="content"]/div[3]/div[1]/a
		List<WebElement> elements = driver.findElements(By.xpath("//a"));
		int visited = 0;
		for ( WebElement element : elements ) {
			String href = element.getAttribute("href");
			if(href != null) {
				if(!href.startsWith("/")) {
					element.getLocation();
					driver.navigate().back();
				}
				else {
					driver.navigate().to(driver.getCurrentUrl()+href);
					driver.navigate().back();
				}
			}
		}
		assertEquals("https://inf.ug.edu.pl/studinfo",driver.getCurrentUrl());
	}
	
	@Test
	public void enterInputs() {
		driver.navigate().to("https://inf.ug.edu.pl/sq/src/login.php");
		
		List<WebElement> elements = driver.findElements(By.xpath("//input")); 
		int count = 0;
		for ( WebElement element : elements ) {
			if(element.getAttribute("value").length() > 0)
				count++;
		}
		assertEquals(3, count);
	}
}