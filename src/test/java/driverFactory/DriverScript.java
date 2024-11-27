package driverFactory;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;


public class DriverScript {
	ExtentReports reports;
	ExtentTest logger;
	public static WebDriver driver;
	public void startTest() throws Throwable {
		reports = new ExtentReports("./target/Reports.html");
		try {
			
			driver = FunctionLibrary.startBrowser();
			logger=reports.startTest("Revenue Calculator");
			logger.assignAuthor("Surya Deepthi");
			FunctionLibrary.openUrl();
			logger.log(LogStatus.INFO, "Home page is opened");
			File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen, new File("./target/Screenshot/"+"---"+FunctionLibrary.generateDate()+".png"));
			FunctionLibrary.revenueCalculatorPage();
			logger.log(LogStatus.INFO, "Navigated to Revenue Calculator page");
			File screen1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen1, new File("./target/Screenshot/"+"---"+FunctionLibrary.generateDate()+".png"));
			Thread.sleep(5000);
			FunctionLibrary.scrollToSlider();
			File screen2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen2, new File("./target/Screenshot/"+"---"+FunctionLibrary.generateDate()+".png"));
			Thread.sleep(5000);
			FunctionLibrary.adjustSlider();
			logger.log(LogStatus.INFO, "Slider and texbox value is matching");
			File screen3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen3, new File("./target/Screenshot/"+"---"+FunctionLibrary.generateDate()+".png"));
			Thread.sleep(5000);
			FunctionLibrary.updateTextField("540");
			logger.log(LogStatus.INFO, "slider is updated");
			File screen4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen4, new File("./target/Screenshot/"+"---"+FunctionLibrary.generateDate()+".png"));
			Thread.sleep(5000);
			FunctionLibrary.selectCPTCodes();
			logger.log(LogStatus.INFO, "Total recurring reimbursement is matching");
			File screen5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen5, new File("./target/Screenshot/"+"---"+FunctionLibrary.generateDate()+".png"));
			Thread.sleep(5000);
			FunctionLibrary.tearDown();
			
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			
		}
		reports.endTest(logger);
		reports.flush();
	}
	
	
	
}
