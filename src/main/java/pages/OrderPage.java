package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPage{

	WebDriver driver;
	By name = By.xpath("//input[contains(@name,'name')]");
	By address = By.xpath("//textarea[contains(@name,'address')]");
	By email = By.xpath("//input[contains(@name,'email')]");
	By payType = By.xpath("//select[contains(@name,'pay')]");
	By placeOrder = By.xpath("//input[contains(@value,'Place')]");

	
	public OrderPage(WebDriver driver) {
		this.driver = driver;
	}
	
	
	public void highLighterMethod(By LocatorValue){
		WebElement element= driver.findElement(LocatorValue);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", element);
	}
	
	
	public void registerName(String value) {
		highLighterMethod(name);
		driver.findElement(name).sendKeys(value);
		System.out.println("Step Description - "+ value +" was entered in the Name field");
	}

	
	public void registerAddress(String value) {
		highLighterMethod(address);
		driver.findElement(address).sendKeys(value);
		System.out.println("Step Description - "+ value +" was entered in the Address field");
	}

	
	public void registerEmail(String value) {
		highLighterMethod(email);
		driver.findElement(email).sendKeys(value);
		System.out.println("Step Description - "+ value +" was entered in the Email field");
	}

	
	public void selectPayType(String value) {
		highLighterMethod(payType);
		driver.findElement(payType).click();
		System.out.println("Step Description - Pay Type was clicked");
		
		By payTypeOption = By.xpath("//*[@id='order_pay_type']/option[contains (text(),'" + value + "')]");
		highLighterMethod(payTypeOption);
		driver.findElement(payTypeOption).click();
		System.out.println("Step Description - "+ value +" was selected from Pay Type");
	}

	
	public void placeOrder() {
		highLighterMethod(placeOrder);
		driver.findElement(placeOrder).click();
		System.out.println("Step Description - Place Order button was clicked");
	}
}

