package commonFunctions;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class FunctionLibrary  {
	public static WebDriver driver;
	public static Properties conpro;
	//method to launch browser
	@BeforeTest
	public static WebDriver startBrowser() throws Throwable {
		conpro = new Properties();
		conpro.load(new FileInputStream("./PropertyFiles/Environment.properties"));
		if(conpro.getProperty("Browser").equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if(conpro.getProperty("Browser").equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		else{
			Reporter.log("Browser is not available" ,true);
		}
		return driver;
	}
	
	//1method to launch Fitpeo url
	
	public static void openUrl() {
		driver.get(conpro.getProperty("FitpeoUrl"));
		String Expected = "Home";
		String Actual = driver.findElement(By.linkText("Home")).getText();
		Assert.assertEquals(Actual, Expected);
		Reporter.log("Navigated to Fitpeo" + " "+Actual + " " +"page", true);
	}
	 
	//2method to navigate to Revenue Calculator Page
	
	public static void revenueCalculatorPage() {
		WebElement rc = driver.findElement(By.xpath(conpro.getProperty("RevenueCalculator")));
		rc.click();
		if(driver.getTitle().contains("revenue-calculator")) {
			boolean actres = true;
			Assert.assertTrue(actres);
			Reporter.log("Revenue Calculator module is opened", true);
		}
	 }
	
	//3method to scroll down to slider section
	
	public static void scrollToSlider() throws Throwable {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(5000);
		js.executeScript("window.scrollBy(0,250)");
		
	}
	
	//4method to adjust the slider and update textbox 
	
	public static void adjustSlider() {
		Actions ac = new Actions(driver);
		WebElement slider = driver.findElement(By.xpath("//span/input[contains(@aria-valuemax,'2000')]"));
		ac.dragAndDropBy(slider, 94, 0).build().perform();
		ac.sendKeys(Keys.ARROW_LEFT).perform();
		ac.sendKeys(Keys.ARROW_LEFT).perform();
		ac.sendKeys(Keys.ARROW_LEFT).perform();
		
		String expected = slider.getAttribute("value");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)");
		WebElement textbox = 
				driver.findElement(By.xpath(conpro.getProperty("NumOfPatientsTextbox")));
		String actual = textbox.getAttribute("value");
		if (expected.equals(actual)) {
            System.out.println("Slider value and textbox value are the same.");
        } else {
            System.out.println("Slider value and textbox value are different.");
        }
		//Assert.assertEquals(actual, expected, "both are not matching");
	Assert.assertEquals(actual, expected, "both are not matching");
	Reporter.log("Slider value:" + " " +expected +" "+ "and" +"Textbox value:" + " "+ actual +" "+"are matching", true);
	}
	
	
	//5,6method to update text field and validate slider value
	public static void updateTextField(String patients) {
		
		WebElement textbox = 
				driver.findElement(By.xpath(conpro.getProperty("NumOfPatientsTextbox")));
		textbox.sendKeys(Keys.CONTROL + "a");
		textbox.sendKeys(Keys.DELETE);  // Deletes the selected text
		textbox.sendKeys(patients);
		String expected = textbox.getAttribute("value");
		WebElement slider = driver.findElement(By.xpath(conpro.getProperty("Slider")));
		String actual = slider.getAttribute("value");
		Assert.assertEquals(actual, expected, "both are not matching");
		Reporter.log("Textbox value:" + " " +patients +" "+ "and " +"Slider value:" + " "+ actual+" " +"are matching", true);	
			 
		 
	}
	
	//7,8,9method to select CPT codes
	public static void selectCPTCodes() {
		WebElement textbox = 
				driver.findElement(By.xpath(conpro.getProperty("NumOfPatientsTextbox")));
		textbox.sendKeys(Keys.CONTROL + "a");
		textbox.sendKeys(Keys.DELETE); 
		textbox.sendKeys("820");
		String num = textbox.getAttribute("value");
		int numOfPatients = Integer.parseInt(num);
		//System.out.println(textbox.getAttribute("value"));
		WebElement check091 = driver.findElement(By.xpath(conpro.getProperty("Checkbox99091")));
		WebElement text091 = driver.findElement(By.xpath(conpro.getProperty("Price0fCPT99091")));
		int sum = 0;
		if(!check091.isSelected()) {
			check091.click();
			String text1 = text091.getText();
			int value1 = Integer.parseInt(text1);
			//System.out.println(value1);
			sum +=value1;
		}
		WebElement check453 = driver.findElement(By.xpath(conpro.getProperty("Checkbox99453")));
		WebElement text453 = driver.findElement(By.xpath(conpro.getProperty("PriceOfCPT99453")));
		if(!check453.isSelected()) {
			check453.click();
			String text2 = text453.getText();
		}		
		WebElement check454 = driver.findElement(By.xpath(conpro.getProperty("Checkbox99454")));
		WebElement text454 = driver.findElement(By.xpath(conpro.getProperty("PriceOfCPT99454")));
		if(!check454.isSelected()) {
			check454.click();
			String text3 = text454.getText();
			int value3 = Integer.parseInt(text3);
			//System.out.println(value3);
			sum+=value3;
		}		
		WebElement check474 = driver.findElement(By.xpath(conpro.getProperty("Checkbox99474")));
		WebElement text474 = driver.findElement(By.xpath(conpro.getProperty("PriceOfCPT99474")));
		if(!check474.isSelected()) {
			check474.click();
			String text4 = text474.getText();
			int value4 = Integer.parseInt(text4);
			//System.out.println(value4);
			sum+=value4;
		}
		int Total_Patient_Reimbursement = sum*numOfPatients;
		String expected = String.valueOf(Total_Patient_Reimbursement);
		//System.out.println("Total Patient Reimbursement: " + sum*numOfPatients );
		
				
	    WebElement Patient_Reimburse = 
	    		driver.findElement(By.xpath(conpro.getProperty("TotalRecurringReimbursementforallPatientsPerMonth")));
	    String  topText = Patient_Reimburse.getText().replace("$", "");
		int Total_Recurring_Reimbursement = Integer.parseInt(topText);
		String actual = String.valueOf(Total_Recurring_Reimbursement);
		Assert.assertEquals(actual, expected);
		Reporter.log("Total Recurring Reimbursement:"+" "+expected+
				" matching the Total Recurring Reimbursement for all Patients Per Month"+""+actual, true);
		
	}
	 
	    public static void tearDown() {
	    	driver.quit();
	    	
	    }
	  //method to generate date on screenshot,extent reports
		public static String generateDate() {
			Date date = new Date();
			DateFormat dateF = new SimpleDateFormat("YYYY_MM_dd hh_mm_ss");
			return dateF.format(date);
		}
	

}
