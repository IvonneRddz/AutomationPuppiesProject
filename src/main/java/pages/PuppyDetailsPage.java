package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PuppyDetailsPage {

	WebDriver driver;
	By btnAdoptMe = By.xpath("//input[contains(@value,'Adopt')]");

	
	public PuppyDetailsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void highLighterMethod(By LocatorValue){
		WebElement element= driver.findElement(LocatorValue);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", element);
	}
	
	
	public void adoptMe() {
		highLighterMethod(btnAdoptMe);
		driver.findElement(btnAdoptMe).click();
		System.out.println("Step Description - Adopt Me button was clicked");
	}
}
