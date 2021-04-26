package pages;

import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ShoppingCartPage {

	WebDriver driver;
	By btnCompleteAdoption = By.xpath("//input[contains(@value,'Complete')]");
	By btnAdoptAnother = By.xpath("//input[starts-with(@value,'Adopt')]");
	By btnChangeYourMind = By.xpath("//input[contains(@value,'Change')]");
	By totalPrice = By.xpath("//*[@class='total_cell']");
	

	private static void delaySegundo(){
        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
        	e.getMessage();
        }
    }
	
	
	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
	}

	
	public void completeAdoption() {
		driver.findElement(btnCompleteAdoption).click();
		System.out.println("Step Description - Complete Adoption button was clicked");
	}

	
	public void adoptAnother() {
		driver.findElement(btnAdoptAnother).click();
		System.out.println("Step Description - Adopt Another button was clicked");
	}

	
	public void changeYourMind() {
		driver.findElement(btnChangeYourMind).click();
		System.out.println("Step Description - Change Your Mind button was clicked");
		aceptAlert();
		aceptAlert();
	}
	
	
	public void aceptAlert(){
		delaySegundo();
		Alert alert = driver.switchTo().alert();	
		alert.accept();
		delaySegundo();
		System.out.println("Step Description - Alert  was accepted");
		}
	

	public String getTotalPrice() {
		return driver.findElement(totalPrice).getText();
	}

	
	public void verifyPuppyDescription(String value) {
			By puppyDescription = By.xpath("//*[contains(text(),'"+value+"')]");
			String description = driver.findElement(puppyDescription).getText();
			Assert.assertEquals(value,description);
			System.out.println("Step Description - Verified Puppy description");
			System.out.println("Expected result: " + value + ", Actual result: " + description);
	}

	
	public void verifyPuppyName(List<String> list) {
			String summary = "";
			System.out.println("Step Description - Verified purchase summary Puppies");
		
			for (int i = 0; i < list.size(); i++) {
				String value = list.get(i);
				By puppyName = By.xpath("//*[contains(text(),'" + value + "')]");
				Assert.assertEquals(list.get(i),driver.findElement(puppyName).getText()); 
				summary += list.get(i) + ",";
			}
				System.out.println("Summary of my 3 purchases: " + summary);
	}
	
	
	public void verifyPuppyName(String value) {
			By puppyName = By.xpath("//*[contains(text(),'" + value + "')]");
			Assert.assertEquals(value,driver.findElement(puppyName).getText()); 
			System.out.println("Step Description - Verified Puppy Name");
			System.out.println("Expected result: " + value + ", Actual result: " + driver.findElement(puppyName).getText() );
	}

	
	public void selectAdditionalProducts(int numberOfPeets) {
		
		for (int i = 1; i < numberOfPeets; i++) {
			By chkbCollarAddicionalProduct = By.xpath("(//*[@id='collar'])["+i+"]");
			By chkbToyAddicionalProduct = By.xpath("(//*[@id='toy'])["+i+"]");
			By chkbCarrierAddicionalProduct = By.xpath("(//*[@id='carrier'])["+i+"]");
			By chkbVetAddicionalProduct = By.xpath("(//*[@id='vet'])["+i+"]");

			driver.findElement(chkbCollarAddicionalProduct).click();
			System.out.println("Step Description - Collar from Aditional products was selected, Puppy "+i);
			driver.findElement(chkbToyAddicionalProduct).click();
			System.out.println("Step Description - Toy from Aditional products was selected, Puppy "+i);
			driver.findElement(chkbCarrierAddicionalProduct).click();
			System.out.println("Step Description - Carrier from Aditional products was selected, Puppy "+i);
			driver.findElement(chkbVetAddicionalProduct).click();
			System.out.println("Step Description - Vet from Aditional products was selected, Puppy "+i);
			}
	}
		
	
	public String sumaAdditionalProducts(int numberOfPeets) {
		double suma = 0;
		
		for (int i = 1; i < numberOfPeets; i++) {	
			By puppyPrice = By.xpath("(//*[@class='item_price'])[" + i + "]");
			By collarPrice = By.xpath("(//*[@class='collar-amount'])["+i+"]");
			By toyPrice = By.xpath("(//*[@class='toy-amount'])["+i+"]");
			By carrierPrice = By.xpath("(//*[@class='carrier-amount'])["+i+"]");
			By vetPrice = By.xpath("(//*[@class='vet-amount'])["+i+"]");
			
			String puppy = driver.findElement(puppyPrice).getText();
			String collar = driver.findElement(collarPrice).getText();
			String toy = driver.findElement(toyPrice).getText();
			String carrier = driver.findElement(carrierPrice).getText();
			String vet = driver.findElement(vetPrice).getText();
			
			double pricePuppy = Double.parseDouble(puppy.replace("$", ""));
			double priceCollar = Double.parseDouble(collar.replace("$", ""));
			double priceToy = Double.parseDouble(toy.replace("$", ""));
			double priceCarrier = Double.parseDouble(carrier.replace("$", ""));
			double priceVet = Double.parseDouble(vet.replace("$", ""));
			
			double price = pricePuppy+priceCollar+priceToy+priceCarrier+priceVet;
			suma += Math.round(price*100.0)/100.0;
		}
		String sum = String.valueOf("$" + suma);
		return sum;
	}
	
	
	public void verifyTotalAmountAdditionalProducts(int numberOfPeets){
		String value =sumaAdditionalProducts(numberOfPeets);
		String expectedValue = getTotalPrice();
		System.out.println("Step Description - Verified Correct Total Amount including Additional Products");
		System.out.println("Expected result: " + value + ", Actual result: " + expectedValue);
		Assert.assertEquals(value,expectedValue); 
	}
	
	
	public void verifyTotalAmount(int peetsNum) {
			Assert.assertEquals(sumaItemPrice(peetsNum),getTotalPrice()); 
			System.out.println("Step Description - Verified Correct Total Amount");
			System.out.println("Expected result: " + sumaItemPrice(peetsNum)+ ", Actual result: " + getTotalPrice() );;
	}

	
	public String sumaItemPrice(int numberOfPeets) {
		double suma = 0;
		for (int i = 1; i < numberOfPeets; i++) {
			By itemPrice = By.xpath("(//*[@class='item_price'])[" + i + "]");
			String item = driver.findElement(itemPrice).getText();
			String itemNew = item.replace("$", "");
			double price = Double.parseDouble(itemNew);
			suma += price;
		}
		String sum = String.valueOf("$" + suma);
		return sum;
	}
	
	
	public void verifyAdoptAmount(String value) {
			By itemPrice = By.xpath("(//*[@class='item_price'])[1]");
			Assert.assertEquals(value,driver.findElement(itemPrice).getText());
			System.out.println("Step Description: Verified Adopt Amount Puppy");
			System.out.println("Expected result: " + value + ", Actual result: " + driver.findElement(itemPrice).getText());
	}
	
	
	public void verifyPuppyDetails(String value1, String value2, String value3) {
		try {
			verifyPuppyName(value1);
			verifyPuppyDescription(value2);
			verifyAdoptAmount(value3);
		} catch (Exception ex) {
			ex.getMessage();
		}
	}
}
