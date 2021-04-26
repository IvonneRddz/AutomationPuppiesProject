package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PuppyDetailsPage {

	WebDriver driver;
	By btnAdoptMe = By.xpath("//input[contains(@value,'Adopt')]");

	public PuppyDetailsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void adoptMe() {
		driver.findElement(btnAdoptMe).click();
		System.out.println("Step Description - Adopt Me button was clicked");
	}
}
