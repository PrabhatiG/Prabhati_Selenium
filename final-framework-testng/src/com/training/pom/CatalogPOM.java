package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CatalogPOM {
	
private WebDriver driver; 
	
	public CatalogPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	
     @FindBy(xpath="//*[@id='menu-catalog']/a/i")
     private WebElement catalogIcon;
     
     @FindBy(xpath="//*[@id='menu-catalog']/ul/li[1]/a")
     private WebElement categoriesLink;

     
     public void mouseCatalogIcon() {
	Actions act=new Actions(driver);
			act.moveToElement(this.catalogIcon);
			act.build().perform();
      }
     public void clickCatalogIcon() {
    	 this.catalogIcon.click();
     }
     
     public void clickCategoriesLink() {
    	 this.categoriesLink.click();
     }
}
