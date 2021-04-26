package pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class HomePage {

	WebDriver driver;
	By mnsThxForAddopt = By.xpath("//p[contains(text(),'Thank you for adopting a puppy!')]");
	By puppyList = By.xpath("//*[@class='puppy_list']/div");
	By paginationNext = By.xpath(" //a[contains(text(),'Next')]");
	By mnsEmptyCar = By.xpath("//p[contains(text(),'Your car is currently empty')]");
		
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	
	public void navigate(String value) {
		driver.navigate().to(value);
		System.out.println("Step Description - Go to Puppy site");
	}
	
	
	public void paginationNext() {
			driver.findElement(paginationNext).click();
			System.out.println("Step Description - Next button was clicked");
	}

	
	public void selectPuppy(int indice) {
			By btnViewDetails = By.xpath("(//input[contains(@value,'View')])[" + indice + "]");
			driver.findElement(btnViewDetails).click();
			System.out.println("Step Description - One Puppy was selected");
	}
	
	
	public String getMnsThxForAddopt() {
		return driver.findElement(mnsThxForAddopt).getText();
	}
	
	
	public String getMnsCarisEmpty() {
		return driver.findElement(mnsEmptyCar).getText();
	}

	
	public void verifySuccessfulAdoption(String value) {
			Assert.assertEquals(value,getMnsThxForAddopt());
			System.out.println("Step Description - Verified Message");
			System.out.println("Expected result: " + value + ", Actual result: " + getMnsThxForAddopt());
	}
	
	
	public void verifyCarisEmpty(String value) {
			Assert.assertEquals(value,getMnsCarisEmpty());
			System.out.println("Step Description - Verified Message");
			System.out.println("Expected result: " + value + ", Actual result: " + getMnsCarisEmpty());
	}


	
	public void verifyPuppiesDetails(String val1, String val2, String val3, int indice) {
			By puppyName = By.xpath("//*[contains(text(),'"+ val1 +"')]");
			By puppyBreed = By.xpath("//*[contains(text(),'"+ val2 +"')]");
			By puppySex = By.xpath(" (//*[contains(text(),'"+ val3 +"')])[" + indice + "]");
			
			Assert.assertEquals(val1,driver.findElement(puppyName).getText());
			Assert.assertEquals(val2,driver.findElement(puppyBreed).getText());
			Assert.assertEquals(val3,driver.findElement(puppySex).getText());
			
			System.out.println("Step Description - Verified Puppy Details");
			System.out.println("Puppy name: "+ val1 + ", Puppy breed: "+ val2+ ", Puppy Sex: " + val3);
	}

	
	public void verifyNumberOfPeetsperPage() {
	            List<WebElement> elements = driver.findElements(puppyList);
	            System.out.println("Step Description - Verified Puppies views per page");
	            
	            for (WebElement element : elements) { 
	            	System.out.println("Puppy: " + element.getText());
	            }
	           
	            if(elements.size()<5){
	            	System.out.println("This page content: " + elements.size() +" records");
	            }else
	            	System.out.println("More views than are required, maximum 4 Puppies per page" );
	}

	
	public void menuOptions(String value) {
		String[ ]split=value.split(",");
		ArrayList<String> pal = new ArrayList<>();
		for(int i = 0; i < split.length; i++){ 
		    pal.add(split[i]);
		    }
		verifyMenuOptions(pal);
	}
	
	
	public void verifyMenuOptions(ArrayList<String> list) {
			String options = "";
			System.out.println("Step Description - Verified Menu Options");
			
			for (int i = 0; i < list.size(); i++) {
				String value = list.get(i);
				By menuOption = By.xpath("//a[contains(text(),'"+value+"')]");
				Assert.assertEquals(list.get(i),driver.findElement(menuOption).getText());
				options += list.get(i) + ",";
			}
			System.out.println("Expected result: " + options +" Actual result: " + list);
	}
}