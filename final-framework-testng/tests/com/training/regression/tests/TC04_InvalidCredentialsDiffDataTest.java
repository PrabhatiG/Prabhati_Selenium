package com.training.regression.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.training.bean.LoginBean;
import com.training.dao.ELearningDAO;
import com.training.dataproviders.LoginDataProviders;
import com.training.generics.GenericMethods;
import com.training.generics.ScreenShot;
import com.training.pom.AdminLoginPOM;
import com.training.pom.LoginPOM;
import com.training.readexcel.ReadExcel;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TC04_InvalidCredentialsDiffDataTest {
  
	private WebDriver driver;
	private String adminUrl;
	private AdminLoginPOM adminLoginPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private GenericMethods gc;

	
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		adminLoginPOM = new AdminLoginPOM(driver); 
		adminUrl = properties.getProperty("adminURL");
		screenShot = new ScreenShot(driver); 
		gc = new GenericMethods(driver);
		// Navigate to URL
		driver.get(adminUrl);
		gc.assertURL("http://retailm1.upskills.in/admin/");
	}

	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

	@Test(dataProvider = "xls-inputs", dataProviderClass = LoginDataProviders.class)
	public void invalidCredentialsTest(String adminUserName, String password) {
		adminLoginPOM.sendUserName(adminUserName);
		adminLoginPOM.sendPassword(password);
		adminLoginPOM.clickLoginBtn();
		String str=driver.findElement(By.xpath("//*[@id='content']/div/div/div/div/div[2]/div")).getText();
		System.out.println(str);
		//gc.assertText("No match for Username and/or Password.","//*[@id='content']/div/div/div/div/div[2]/div", "xpath", "The correct message is not displayed");
		screenShot.captureScreenShot("mismatch message");          
	}

}