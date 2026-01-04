package rahushatty.abstarctcomponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshattyacademy.pageobjectmodel.Cartpage;

public class Abstarctcompenets {
	
	
	WebDriver driver;

	public Abstarctcompenets(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;

	
	public Cartpage goToCartPage() {
		
		cartHeader.click();
		Cartpage cartpage = new Cartpage(driver);
		return cartpage;

		
	}

	public void waitForElementToAppear(By findBy ) {
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}

}
