

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserTest {
	
	//Przykﾃ�窶啾dy znajdowania elementﾃδｳw na stronie www bez elementﾃδｳw xpath
	
	private static WebDriver driver;

	@BeforeAll
	public static void setUpDriver(){
		System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setHeadless(true);
        driver = new FirefoxDriver(firefoxOptions);
        // Implicity wait -> max czas na znalezienie elementu na stronie
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@BeforeEach
	public void setUp() throws Exception {
    	driver.get("https://duckduckgo.com/");
	}

	@AfterAll
	public static void tearDown() throws Exception {
		driver.quit();
	}
	
	@Test
	public void findFirst() {
		driver.findElement(By.id("search_form_input_homepage")).sendKeys("mfi.ug.edu.pl");
        driver.findElement(By.id("search_button_homepage")).click();
        driver.findElement(By.id("r1-0")).click();
        assertEquals("https://mfi.ug.edu.pl/", driver.getCurrentUrl());
	}
	
	@Test
	public void findThird() {
		driver.findElement(By.id("search_form_input_homepage")).sendKeys("mfi.ug.edu.pl");
        driver.findElement(By.id("search_button_homepage")).click();
        driver.findElement(By.id("r1-2")).sendKeys(Keys.ENTER);
        assertEquals("https://mat.ug.edu.pl/pracownicy/dr-maciej-mroczkowski/", driver.getCurrentUrl());
	}

	@Test
	public void findById() {
		WebElement element = driver.findElement(By.id("searchform"));
		assertNotNull(element);
	}
	
	@Test
	public void findByName() {
		WebElement element = driver.findElement(By.name("q"));
		assertNotNull(element);
	}
	
	@Test
	public void findByClass() {
		WebElement element = driver.findElement(By.className("gsfi"));
		assertNotNull(element);
	}
	
	@Test
	public void findBylinkText() {
		WebElement element = driver.findElement(By.linkText("Gmail"));
		assertNotNull(element);
	}
	
	@Test
	public void findByPartiallinkText() {
		WebElement element = driver.findElement(By.partialLinkText("ma"));
		assertNotNull(element);
	}
	
	@Test
	public void findByTagName() {
		WebElement element = driver.findElement(By.tagName("div"));
		assertNotNull(element);
	}
	
	@Test
	public void findByCssSelector() {
		WebElement element = driver.findElement(By.cssSelector("input.gsfi"));
		assertNotNull(element);
	}


}
