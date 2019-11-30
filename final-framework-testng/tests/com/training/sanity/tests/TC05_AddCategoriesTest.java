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
import com.training.pom.CatalogPOM;
import com.training.pom.CategoriesPOM;
import com.training.pom.LoginPOM;
	import com.training.utility.DriverFactory;
	import com.training.utility.DriverNames;

	public class TC05_AddCategoriesTest {

		private WebDriver driver;
		private String adminUrl;
		private AdminLoginPOM adminLoginPOM;
		private static Properties properties;
		private ScreenShot screenShot;
		private GenericMethods gc;
		private CatalogPOM catalogPOM;
		private CategoriesPOM categoriesPOM;

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
			catalogPOM= new CatalogPOM(driver);
			categoriesPOM= new CategoriesPOM(driver);
			// Navigate to URL
			driver.get(adminUrl);
			gc.assertURL("http://retailm1.upskills.in/admin/");
			adminLoginPOM.sendUserName("admin");
			adminLoginPOM.sendPassword("admin@123");
			adminLoginPOM.clickLoginBtn(); 
		}
		
		@AfterMethod
		public void tearDown() throws Exception {
			Thread.sleep(1000);
			driver.quit();
		}
		@Test
		public void CategoriesAdditionTest() throws InterruptedException {
		Thread.sleep(5000L);
		catalogPOM.mouseCatalogIcon();
		catalogPOM.clickCatalogIcon();
		screenShot.captureScreenShot("different links");
		Thread.sleep(5000L);
		catalogPOM.clickCategoriesLink();
		Thread.sleep(5000L);
		gc.assertText("Categories", "//*[@id=\"content\"]/div[1]/div/h1", "xpath", "We are not in required page");
		categoriesPOM.clickAddIcon();
		Thread.sleep(2000L);
		screenShot.captureScreenShot("Add icon clicked");
		Thread.sleep(2000L);
		gc.assertText("Category Name", "//*[@id=\"language1\"]/div[1]/label", "xpath", "Field not present");
		categoriesPOM.sendNameText("ORNAMENTS");
		Thread.sleep(2000L);
		categoriesPOM.clickDescText();
		Thread.sleep(5000L);
		categoriesPOM.sendDescText("ornaments for ladies");
		Thread.sleep(2000L);
		//gc.scrollToElement("input-meta-title1", "id");
		Thread.sleep(2000L);
		categoriesPOM.sendMetaTgTtl("ORNAMENTS");
		Thread.sleep(2000L);
		categoriesPOM.sendMetaTgDesc("ornaments for ladies");
		Thread.sleep(2000L);
		screenShot.captureScreenShot("Values entered");
		categoriesPOM.clickDataTab();
		Thread.sleep(2000L);
		gc.assertText("Parent", "//*[@id=\"tab-data\"]/div[1]/label", "xpath", "data tab not opened");
		screenShot.captureScreenShot("Data tab values");
		categoriesPOM.clickDesignTab();
		Thread.sleep(2000L);
		gc.assertText("Default", "//*[@id=\"tab-design\"]/div/table/tbody/tr/td[1]", "xpath", "design tab not opened");
		screenShot.captureScreenShot("Design tab values");
		categoriesPOM.clickSaveIcon();
		Thread.sleep(2000L);
		screenShot.captureScreenShot("Category Saved");
		//gc.assertText("Success: You have modified categories!", "//*[@id=\"content\"]/div[2]/div[1]", "xpath", "Correct message not displayed");
	}
		}



