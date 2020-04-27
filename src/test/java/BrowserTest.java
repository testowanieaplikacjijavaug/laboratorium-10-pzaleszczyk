

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.nio.charset.Charset;
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
        driver.findElement(By.id("r1-2")).click();
        assertEquals("https://mat.ug.edu.pl/pracownicy/dr-maciej-mroczkowski/", driver.getCurrentUrl());
	}



}
