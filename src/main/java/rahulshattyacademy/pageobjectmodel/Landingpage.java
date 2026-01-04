package rahulshattyacademy.pageobjectmodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahushatty.abstarctcomponent.Abstarctcompenets;

public class Landingpage extends Abstarctcompenets {
	
	WebDriver driver;
	
	public Landingpage(WebDriver driver) {
		
		super(driver);
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
		
	
	@FindBy(id= "userEmail")
	WebElement userEmail ;
	
	@FindBy(id = "userPassword")
	WebElement userPassword;
	
	@FindBy(id ="login")
	WebElement login;
	
	public ProductCatalog loginapplication(String email , String password) {
		
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		login.click();
		ProductCatalog productcatalog = new ProductCatalog(driver);
		return productcatalog;

		
		
	}
	
	public void goTo() {
		
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		
	}
	
	
	
	
	

}
