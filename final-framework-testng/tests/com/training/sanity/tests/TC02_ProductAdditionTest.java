package com.training.sanity.tests;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.GenericMethods;
import com.training.generics.ScreenShot;
import com.training.pom.CartPOM;
import com.training.pom.PreLoginPOM;
import com.training.pom.ProductPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;
import com.trianing.waits.WaitTypes;
public class TC02_ProductAdditionTest {
	private WebDriver driver;
	private String userUrl;
	private PreLoginPOM preLoginPage;
	private ProductPOM productPage;
	private CartPOM cartPage;
	private GenericMethods gc;
	private static Properties properties;
	private ScreenShot screenShot;
	private WaitTypes waitType;
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		userUrl = properties.getProperty("userURL");
		preLoginPage=new PreLoginPOM(driver);
		productPage=new ProductPOM(driver);
		cartPage=new CartPOM(driver);
		screenShot = new ScreenShot(driver); 
		gc=new GenericMethods(driver);
		waitType= new WaitTypes(driver);
		// open the browser 
		driver.get(userUrl);
		Thread.sleep(5000L);

}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
	
		
	@Test
	public void  productAddition() throws InterruptedException {
		
	
		Thread.sleep(5000L);
		preLoginPage.clickSearchBtn();
		Thread.sleep(5000L);
		screenShot.captureScreenShot("Your Prelogin Page");
		preLoginPage.clickTextBox();
		preLoginPage.sendTextBox(" Integer Vitae Iaculis Massa");
		Thread.sleep(5000L);
		preLoginPage.sendNext(Keys.ENTER);
		Thread.sleep(5000L);
		gc.assertText("Products meeting the search criteria", "//*[@id='ProductsSystem_YD9pMDOx']/nav/h2", "xpath", "We are not in product page");
		productPage.clickVitaeProductLink();
		Thread.sleep(5000L);
		cartPage.AddtoCartBtn();
		waitType.waitForElement(By.xpath("//*[starts-with(@id,'noty_alert')]/div/div[1]/h3"), 20);
		gc.assertText("Shopping Cart updated!", "//*[starts-with(@id,'noty_alert')]/div/div[1]/h3", "xpath","the alert is not displayed" );
		//Thread.sleep(2000L);
	    screenShot.captureScreenShot("success message page1");
		Thread.sleep(7000L);
		cartPage.CartIconDetect();
		Thread.sleep(5000L);
		screenShot.captureScreenShot("View cart page");
		cartPage.clickViewCartBtn();
		gc.assertText("Image", "//*[@id='cart_form']/div/table/thead/tr/td[1]", "xpath", "We are not in final product page");
		screenShot.captureScreenShot("Final Product Page");
	}


}
