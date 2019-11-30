   package com.training.sanity.tests;

	import java.io.FileInputStream;
	import java.io.IOException;
	import java.util.Properties;

	import org.openqa.selenium.WebDriver;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Test;

	import com.training.generics.GenericMethods;
	import com.training.generics.ScreenShot;
    import com.training.pom.AdminLoginPOM;
    import com.training.pom.LoginPOM;
	import com.training.utility.DriverFactory;
	import com.training.utility.DriverNames;

	public class TC04_InvalidCredentialTest {

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
			//Opening the browser
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
		@Test
		public void InvalidCredentialTest() {
			
			adminLoginPOM.sendUserName("admin");
			adminLoginPOM.sendPassword("admin@1234");
			adminLoginPOM.clickLoginBtn(); 
			screenShot.captureScreenShot("mismatch message");
		}
	}



