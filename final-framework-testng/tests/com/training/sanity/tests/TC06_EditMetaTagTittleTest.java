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

	public class TC06_EditMetaTagTittleTest {

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
		public void MetaTagEditTest() throws InterruptedException {
		Thread.sleep(5000L);
		catalogPOM.mouseCatalogIcon();
		catalogPOM.clickCatalogIcon();
		screenShot.captureScreenShot("different links");
		Thread.sleep(5000L);
		catalogPOM.clickCategoriesLink();
		gc.assertText("ORNAMENTS", "//*[@id=\"form-category\"]/div/table/tbody/tr[24]/td[2]", "xpath", "Category not found");
		Thread.sleep(5000L);
		categoriesPOM.clickEditBtn();
		Thread.sleep(5000L);
		gc.assertText("Meta Tag Title", "//*[@id=\"language1\"]/div[3]/label", "xpath", "Meta tag Title box not visible");
	    categoriesPOM.editMetaTgTtlClr("ornaments");
	    Thread.sleep(2000L);
	    screenShot.captureScreenShot("Meta Tag Title box cleared");
		categoriesPOM.sendMetaTgTtl("ORNAMENTS");
		Thread.sleep(2000L);
		categoriesPOM.sendMetaTgDesc("ornaments for ladies");
		Thread.sleep(2000L);
		gc.assertText("Meta Tag Description", "//*[@id=\"language1\"]/div[4]/label", "xpath", "Meta Tag description box is not displayed");
		categoriesPOM.clickDataTab();
		Thread.sleep(2000L);
		gc.assertText("Parent", "//*[@id=\"tab-data\"]/div[1]/label", "xpath", "data tab not opened");
		categoriesPOM.clickDesignTab();
		Thread.sleep(2000L);
		gc.assertText("Default", "//*[@id=\"tab-design\"]/div/table/tbody/tr/td[1]", "xpath", "design tab not opened");
		categoriesPOM.clickSaveIcon();
		Thread.sleep(2000L);
		screenShot.captureScreenShot("TC06_Category Saved");
		Thread.sleep(5000L);
		//gc.assertText("Success: You have modified categories!", "//*[@id=\"content\"]/div[2]/div[1]", "xpath", "Correct message not displayed");
		}
		}



