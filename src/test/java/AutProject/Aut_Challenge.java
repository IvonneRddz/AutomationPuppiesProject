package AutProject;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.NoSuchElementException;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pages.HomePage;
import pages.OrderPage;
import pages.PuppyDetailsPage;
import pages.ShoppingCartPage;


public class Aut_Challenge {

	WebDriver driver;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;
	String path, fileName, reportFilename, testcaseid, title, description, url, name, address, email, paytype, message,
			adoptamount, puppydescription, pupyname1, puppybreen1, puppysex1, pupyname2, puppybreen2, puppysex2,
			pupyname3, puppybreen3, puppysex3, pupyname4, puppybreen4, puppysex4, pupyname5, puppybreen5, puppysex5,
			pupyname6, puppybreen6, puppysex6, pupyname7, puppybreen7, puppysex7, pupyname8, puppybreen8, puppysex8,
			pupyname9, puppybreen9, puppysex9, menuoptions, dtFileName, dtFilePath, dtHoja;

	

	@BeforeClass
	public void setClass() throws IOException {
		reportFilename = String.format("target/report_%s.html", getDateTime());
		htmlReporter = new ExtentHtmlReporter(reportFilename);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

	public void ReadExcel(String fileName, String filePath, String hoja) {
		this.dtFileName = fileName;
		this.dtFilePath = filePath + fileName;
		this.dtHoja = hoja;
	}

	public void Read(String reference) {
		try (FileInputStream file = new FileInputStream(new File(dtFilePath))) {
			XSSFWorkbook worbook = new XSSFWorkbook(file);
			XSSFSheet sheet = worbook.getSheetAt(0);
			CellReference ref = new CellReference(reference);
			Row fila = sheet.getRow(ref.getRow());

			if (fila != null) {
				testcaseid = fila.getCell(0).toString();
				title = fila.getCell(1).toString();
				description = fila.getCell(2).toString();
				url = fila.getCell(3).toString();
				name = fila.getCell(4).toString();
				address = fila.getCell(5).toString();
				email = fila.getCell(6).toString();
				paytype = fila.getCell(7).toString();
				message = fila.getCell(8).toString();
				adoptamount = fila.getCell(9).toString();
				puppydescription = fila.getCell(10).toString();
				pupyname1 = fila.getCell(11).toString();
				puppybreen1 = fila.getCell(12).toString();
				puppysex1 = fila.getCell(13).toString();
				pupyname2 = fila.getCell(14).toString();
				puppybreen2 = fila.getCell(15).toString();
				puppysex2 = fila.getCell(16).toString();
				pupyname3 = fila.getCell(17).toString();
				puppybreen3 = fila.getCell(18).toString();
				puppysex3 = fila.getCell(19).toString();
				pupyname4 = fila.getCell(20).toString();
				puppybreen4 = fila.getCell(21).toString();
				puppysex4 = fila.getCell(22).toString();
				pupyname5 = fila.getCell(23).toString();
				puppybreen5 = fila.getCell(24).toString();
				puppysex5 = fila.getCell(25).toString();
				pupyname6 = fila.getCell(26).toString();
				puppybreen6 = fila.getCell(27).toString();
				puppysex6 = fila.getCell(28).toString();
				pupyname7 = fila.getCell(29).toString();
				puppybreen7 = fila.getCell(30).toString();
				puppysex7 = fila.getCell(31).toString();
				pupyname8 = fila.getCell(32).toString();
				puppybreen8 = fila.getCell(33).toString();
				puppysex8 = fila.getCell(34).toString();
				pupyname9 = fila.getCell(35).toString();
				puppybreen9 = fila.getCell(36).toString();
				puppysex9 = fila.getCell(37).toString();
				menuoptions = fila.getCell(38).toString();

				file.close();
				FileOutputStream outputStream = new FileOutputStream(dtFilePath);
				worbook.write(outputStream);
				worbook.close();
				outputStream.close();
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	private void initWebDriver() {
		try {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	private String getDateTime() {
		Date date = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH_mm_ss");
		String today = formatter.format(date);
		return today;
	}

	
	
	 @Test 
	  public void TestCase1() throws IOException { 
	  
	  try {
		  ReadExcel("DTAutomationProjectChallenge.xlsx","data/","Global");
		  Read("A2"); 
		  test = extent.createTest(title,description);
		  
		  initWebDriver(); 
		  HomePage HomePage = new HomePage(driver); 
		  PuppyDetailsPage DetailsPage = new PuppyDetailsPage(driver); 
		  ShoppingCartPage CartPage = new ShoppingCartPage(driver); 
		  OrderPage OrderPage = new OrderPage(driver);
		  System.out.println("\nTest Case: " + testcaseid + "\nName: " +title + "\nDescription: " +description + "\n");
		  
		  HomePage.navigate(url); 
		  HomePage.selectPuppy(1); 
		  DetailsPage.adoptMe();
		  CartPage.completeAdoption(); 
		  OrderPage.registerName(name);
		  OrderPage.registerAddress(address); 
		  OrderPage.registerEmail(email);
		  OrderPage.selectPayType(paytype); 
		  OrderPage.placeOrder();
		  HomePage.verifySuccessfulAdoption(message);
		  
			test.pass("Test Case "+ testcaseid + " Executed Successfully");
		} catch (Exception | AssertionError e) {
			test.fail("Test Case "+testcaseid + " Failed - " + " " + e.getMessage() );
	  } 
	  driver.quit(); 
	}
	

	
 	@Test 
	 public void TestCase2() throws IOException { 
	  
	  try {
		  ReadExcel("DTAutomationProjectChallenge.xlsx","data/","Global");
		  Read("A4"); 
		  test = extent.createTest(title,description);
		  
		  initWebDriver(); 
		  HomePage HomePage = new HomePage(driver); 
		  PuppyDetailsPage DetailsPage = new PuppyDetailsPage(driver); 
		  ShoppingCartPage CartPage = new ShoppingCartPage(driver); 
		  OrderPage OrderPage = new OrderPage(driver);
		  System.out.println("\nTest Case: " + testcaseid + "\nName: " +title + "\nDescription: " +description + "\n");
		  
		  HomePage.navigate(url);
		  
		  for (int i=1; i<3; ++i) { 
			  HomePage.selectPuppy(i); 
			  DetailsPage.adoptMe();
			  if(i==2) { 
			  break; 
			  } 
			  CartPage.adoptAnother(); 
		  }
		  
		  CartPage.completeAdoption(); 
		  OrderPage.registerName(name);
		  OrderPage.registerAddress(address); 
		  OrderPage.registerEmail(email);
		  OrderPage.selectPayType(paytype); 
		  OrderPage.placeOrder();
		  HomePage.verifySuccessfulAdoption(message);
		  
			test.pass("Test Case "+ testcaseid + " Executed Successfully");
		} catch (Exception | AssertionError e) {
			test.fail("Test Case "+testcaseid + " Failed - " + " " + e.getMessage() );
	  } 
	  driver.quit(); 
	}
	
	
 	
	 @Test 
	 public void TestCase3() throws IOException { 
	  
	  try {
		  ReadExcel("DTAutomationProjectChallenge.xlsx","data/","Global");
		  Read("A6"); 
		  test = extent.createTest(title, description);
		  
		  initWebDriver(); 
		  HomePage HomePage = new HomePage(driver); 
		  PuppyDetailsPage DetailsPage = new PuppyDetailsPage(driver); 
		  ShoppingCartPage CartPage = new ShoppingCartPage(driver); 
		  System.out.println("\nTest Case: " + testcaseid + "\nName: " +title + "\nDescription: " +description + "\n");
		  
		  HomePage.navigate(url);
		  for (int i = 1; i < 4; ++i) { 
			  HomePage.selectPuppy(i); 
			  DetailsPage.adoptMe();
			  if (i == 3) { 
			  break; 
			  } 
			  CartPage.adoptAnother(); 
		  }
		  List<String> peets = Arrays.asList(pupyname1,pupyname2,pupyname3);
		  CartPage.verifyPuppyName(peets);
		  
		 	test.pass("Test Case "+ testcaseid + " Executed Successfully");
		} catch (Exception | AssertionError e) {
			test.fail("Test Case "+testcaseid + " Failed - " + " " + e.getMessage() );
		  } 
		  driver.quit(); 
	 }
	

	
	@Test
	public void TestCase4() throws IOException {
		
		try {
			ReadExcel("DTAutomationProjectChallenge.xlsx","data/","Global");
			Read("A8");
			test = extent.createTest(title, description);

			initWebDriver();
			HomePage HomePage = new HomePage(driver);
			PuppyDetailsPage DetailsPage = new PuppyDetailsPage(driver);
			ShoppingCartPage CartPage = new ShoppingCartPage(driver);
			System.out.println("\nTest Case: " + testcaseid + "\nName: " +title + "\nDescription: " +description + "\n");
			
			HomePage.navigate(url);
			for (int i = 1; i < 3; ++i) {
				HomePage.selectPuppy(i);
				DetailsPage.adoptMe();
				if (i == 2) {
				break;
				}
				CartPage.adoptAnother();
			}
			CartPage.verifyTotalAmount(3);
			
			test.pass("Test Case "+ testcaseid + " Executed Successfully");
		} catch (Exception | AssertionError e) {
			test.fail("Test Case "+testcaseid + " Failed - " + " " + e.getMessage() );
		}
		driver.quit();
	}
	 
	
	 
	@Test
	public void TestCase5() throws IOException {
		
		try {
			ReadExcel("DTAutomationProjectChallenge.xlsx","data/","Global");
			Read("A10");
			test = extent.createTest(title, description);

			initWebDriver();
			HomePage HomePage = new HomePage(driver);
			PuppyDetailsPage DetailsPage = new PuppyDetailsPage(driver);
			ShoppingCartPage CartPage = new ShoppingCartPage(driver);
			System.out.println("\nTest Case: " + testcaseid + "\nName: " +title + "\nDescription: " +description + "\n");

			HomePage.navigate(url);
			for (int i = 1; i < 3; ++i) {
				HomePage.selectPuppy(i);
				DetailsPage.adoptMe();
				if (i == 2) {
				break;
				}
				CartPage.adoptAnother();
			}
			CartPage.selectAdditionalProducts(3);
			CartPage.verifyTotalAmountAdditionalProducts(3);
			
			test.pass("Test Case "+ testcaseid + " Executed Successfully");
		} catch (Exception | AssertionError e) {
			test.fail("Test Case "+testcaseid + " Failed - " + " " + e.getMessage() );
		}
		driver.quit();
	}
	 
	
	 
	@Test
	public void TestCase6() throws IOException {
		
		try {
			ReadExcel("DTAutomationProjectChallenge.xlsx","data/","Global");
			Read("A12");
			test = extent.createTest(title, description);

			initWebDriver();
			HomePage HomePage = new HomePage(driver);
			System.out.println("\nTest Case: " + testcaseid + "\nName: " +title + "\nDescription: " +description + "\n"); 

			HomePage.navigate(url);
			HomePage.verifyNumberOfPeetsperPage();
			HomePage.paginationNext();
			HomePage.verifyNumberOfPeetsperPage();
			HomePage.paginationNext();
			HomePage.verifyNumberOfPeetsperPage();
			
			test.pass("Test Case "+ testcaseid + " Executed Successfully");
		} catch (Exception | AssertionError e) {
			test.fail("Test Case "+testcaseid + " Failed - " + " " + e.getMessage() );
		}
		driver.quit();
	}
	
	
	
	@Test
	public void TestCase7() throws IOException {
		
		try {
			ReadExcel("DTAutomationProjectChallenge.xlsx","data/","Global");
			Read("A14");
			test = extent.createTest(title, description);

			initWebDriver();
			HomePage HomePage = new HomePage(driver);
			System.out.println("\nTest Case: " + testcaseid + "\nName: " +title + "\nDescription: " +description + "\n"); 

			HomePage.navigate(url);
			HomePage.verifyPuppiesDetails(pupyname1,puppybreen1,puppysex1,1);
			HomePage.verifyPuppiesDetails(pupyname2,puppybreen2,puppysex2,2);
			HomePage.verifyPuppiesDetails(pupyname3,puppybreen3,puppysex3,3);
			HomePage.verifyPuppiesDetails(pupyname4,puppybreen4,puppysex4,4);
			HomePage.paginationNext();
			HomePage.verifyPuppiesDetails(pupyname5,puppybreen5,puppysex5,1);
			HomePage.verifyPuppiesDetails(pupyname6,puppybreen6,puppysex6,2);
			HomePage.verifyPuppiesDetails(pupyname7,puppybreen7,puppysex7,1);
			HomePage.verifyPuppiesDetails(pupyname8,puppybreen8,puppysex8,2);
			HomePage.paginationNext();
			HomePage.verifyPuppiesDetails(pupyname9,puppybreen9,puppysex9,1);
			
			test.pass("Test Case "+ testcaseid + " Executed Successfully");
		} catch (Exception | AssertionError e) {
			test.fail("Test Case "+testcaseid + " Failed - " + " " + e.getMessage() );
		}
		driver.quit();
	}
	
	
	
	@Test
	public void TestCase8() throws IOException {
		
		try {
			ReadExcel("DTAutomationProjectChallenge.xlsx","data/","Global");
			Read("A16");
			test = extent.createTest(title, description);

			initWebDriver();
			HomePage HomePage = new HomePage(driver); 
			PuppyDetailsPage DetailsPage = new PuppyDetailsPage(driver); 
			ShoppingCartPage CartPage = new ShoppingCartPage(driver); 
			System.out.println("\nTest Case: " + testcaseid + "\nName: " +title + "\nDescription: " +description + "\n");

			HomePage.navigate(url);
			HomePage.selectPuppy(1);
			DetailsPage.adoptMe();
			CartPage.verifyPuppyDetails(pupyname1,puppydescription,adoptamount);
						
			test.pass("Test Case "+ testcaseid + " Executed Successfully");
		} catch (Exception | AssertionError e) {
			test.fail("Test Case "+testcaseid + " Failed - " + " " + e.getMessage() );
		}
		driver.quit();
	}
	
	
	
	@Test
	public void TestCase9() throws IOException {
		
		try {
			ReadExcel("DTAutomationProjectChallenge.xlsx","data/","Global");
			Read("A18");
			test = extent.createTest(title, description);

			initWebDriver();
			HomePage HomePage = new HomePage(driver); 
			PuppyDetailsPage DetailsPage = new PuppyDetailsPage(driver); 
			ShoppingCartPage CartPage = new ShoppingCartPage(driver);
			System.out.println("\nTest Case: " + testcaseid + "\nName: " +title + "\nDescription: " +description + "\n"); 

			HomePage.navigate(url);
			for (int i = 1; i < 3; ++i) {
				HomePage.selectPuppy(i);
				DetailsPage.adoptMe();
				if (i == 2) {
				break;
				}
				CartPage.adoptAnother();
			}
			
			CartPage.changeYourMind();
			HomePage.verifyCarisEmpty(message);
						
			test.pass("Test Case "+ testcaseid + " Executed Successfully");
		} catch (Exception | AssertionError e) {
			test.fail("Test Case "+testcaseid + " Failed - " + " " + e.getMessage() );
		}
		driver.quit();
	}
	 
	
	
	@Test
	public void TestCase10() throws IOException {
		
		try {
			ReadExcel("DTAutomationProjectChallenge.xlsx","data/","Global");
			Read("A20");
			test = extent.createTest(title, description);

			initWebDriver();
			HomePage HomePage = new HomePage(driver);
			System.out.println("\nTest Case: " + testcaseid + "\nName: " +title + "\nDescription: " +description + "\n");

			HomePage.navigate(url);
			HomePage.menuOptions(menuoptions);
			
			test.pass("Test Case "+ testcaseid + " Executed Successfully");
		} catch (Exception | AssertionError e) {
			test.fail("Test Case "+testcaseid + " Failed - " + " " + e.getMessage() );
		}
		driver.quit();
	}

	
	@AfterSuite
	public void tearDown() {
		extent.flush();
		driver.quit();
		try {
			Desktop.getDesktop().open(new File(reportFilename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
