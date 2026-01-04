package rahulshattyacadmy;

import java.awt.Window;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshattyacademy.pageobjectmodel.Landingpage;

public class StandAloneTest {
	@Test

	public  void test2() throws InterruptedException {
		// TODO Auto-generated method stub
		String product_name = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		
		Landingpage landingpage = new Landingpage(driver);
		
		
		
		
		
		driver.findElement(By.id("userEmail")).sendKeys("testdiksha@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("sR123456");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst()
				.orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		Thread.sleep(2000);

		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[routerlink*='cart']"))).click();

		List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match = cartproducts.stream()
				.anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(product_name));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();

		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action__submit"))).click();

		//driver.findElement(By.className(".action__submit")).click();
		String confrimMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();

		Assert.assertTrue(confrimMessage.equalsIgnoreCase("Thankyou for the order."));

	}

}
