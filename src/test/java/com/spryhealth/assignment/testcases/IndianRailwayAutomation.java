package com.spryhealth.assignment.testcases;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.spryhealth.assignment.utility.ExcelUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class IndianRailwayAutomation {
	public WebDriver driver;

	@BeforeMethod
	public void setup() throws IOException {
		ExcelUtility.setup();
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test(priority = 1)
	public void validatePNRTest() throws IOException {
		driver.get("https://www.indianrail.gov.in/enquiry/PNR/PnrEnquiry.html?locale=en");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		int rowCount=ExcelUtility.getRowCount("ValidatePNRTest");
		
		
		for(int i=1; i<=rowCount;i++)
		{
			
			System.out.println(ExcelUtility.getData("ValidatePNRTest", i, 0)); 
			//Enter PNR number
			driver.findElement(By.id("inputPnrNo")).sendKeys(ExcelUtility.getData("ValidatePNRTest", i, 0));

			
			//click on submit button
			driver.findElement(By.id("modal1")).click();
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			//User has entered valid input
			if(driver.findElement(By.xpath("//div[@class='modal-content']")).isDisplayed())
			{
				System.out.println("Valid Input  ");
				driver.findElement(By.xpath("//button[@class='close']")).click();
				
			}//If it is invalid input
			else if(driver.findElement(By.xpath("//p[@id='errorMessage']")).isDisplayed())
			{
				System.out.println("Invalid Input");
			}
			
			driver.navigate().to("https://www.indianrail.gov.in/enquiry/PNR/PnrEnquiry.html?locale=en");
		}//End for loop
		
	
	}

	@Test(priority = 0)
	public void scheduleTrainTest() throws IOException
	{
		driver.get("https://www.indianrail.gov.in/enquiry/SCHEDULE/TrainSchedule.html?locale=en");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		int rowCount=ExcelUtility.getRowCount("ScheduleTrainTest");
		int columnCount=ExcelUtility.getColumnCount("ScheduleTrainTest",0);
		
		
		for(int i=1; i<=rowCount;i++)
		{
			WebElement autoOptions=driver.findElement(By.id("trainNo"));
			autoOptions.sendKeys(ExcelUtility.getData("ScheduleTrainTest", i, 0));
			
			driver.findElement(By.xpath("//li[@class='ui-menu-item']//a[text()='14524 - HARIHAR EXPRESS']")).click();
			List<WebElement> options= driver.findElements(By.xpath("//li[@class='ui-menu-item']"));
			
			/*for(WebElement element: options)
			{
				System.out.println("In for loop " + element.getText());
				if(element.getText().equals("14501 - BTI JAT EXP"))
				{
					
					
					element.click();
					driver.findElement(By.id("modal1")).click();
					break;
				}
			}*/
		}
		
	}
	
	@Test
	public void verifyValidFaretest()
	{
		driver.get("https://www.indianrail.gov.in/enquiry/FARE/FareEnquiry.html?locale=en");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		
		driver.findElement(By.id("trainNo")).sendKeys("145");
		driver.findElement(By.xpath("//li[@class='ui-menu-item']//a[text()='14523 - HARIHAR EXP']")).click();
		driver.findElement(By.id("dt")).sendKeys("17-11-2022");
		driver.findElement(By.id("sourceStation")).sendKeys("Pun");
		driver.findElement(By.xpath("//li[@class='ui-menu-item']//a[text()='PUNE JN - PUNE']")).click();
		
		
		driver.findElement(By.id("destinationStation")).sendKeys("Mum");
		driver.findElement(By.xpath("//li[@class='ui-menu-item']//a[text()='MUMBAI CENTRAL - BCT']")).click();
		
		driver.findElement(By.id("modal1")).click();
		
	}
	 

	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
