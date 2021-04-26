package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage {

	WebDriver driver;
	By name = By.xpath("//input[contains(@name,'name')]");
	By address = By.xpath("//textarea[contains(@name,'address')]");
	By email = By.xpath("//input[contains(@name,'email')]");
	By payType = By.xpath("//select[contains(@name,'pay')]");
	By placeOrder = By.xpath("//input[contains(@value,'Place')]");

	
	public OrderPage(WebDriver driver) {
		this.driver = driver;
	}

	
	public void registerName(String value) {
		driver.findElement(name).sendKeys(value);
		System.out.println("Step Description - "+ value +" was entered in the Name field");
	}

	
	public void registerAddress(String value) {
		driver.findElement(address).sendKeys(value);
		System.out.println("Step Description - "+ value +" was entered in the Address field");
	}

	
	public void registerEmail(String value) {
		driver.findElement(email).sendKeys(value);
		System.out.println("Step Description - "+ value +" was entered in the Email field");
	}

	
	public void selectPayType(String value) {
		driver.findElement(payType).click();
		System.out.println("Step Description - Pay Type was clicked");
		By payTypeOption = By.xpath("//*[@id='order_pay_type']/option[contains (text(),'" + value + "')]");
		driver.findElement(payTypeOption).click();
		System.out.println("Step Description - "+ value +" was selected from Pay Type");
	}

	
	public void placeOrder() {
		driver.findElement(placeOrder).click();
		System.out.println("Step Description - Place Order button was clicked");
	}
}


