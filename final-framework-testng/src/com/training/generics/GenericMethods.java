package com.training.generics;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;

/**
 * 
 * @author Naveen
 * @see this class will help when you want to do custom business logic, since  in POM we dont do 
 * 			dynamic elements available, when you want to iterate the table/accordion etc 
 * @since 17-Dec-2018 
 */
public class GenericMethods {
	WebDriver driver ; 
	
	public GenericMethods(WebDriver driver){
		this.driver = driver;
	}
	
	/**
	 * 
	 * @param locator 
	 * @param type
	 * @see type is id, name, xpath, text, partialtext
	 * @see locator will be the element to be found on DOM 
	 * @return  WebElement
	 * this method shall give provided it has single enty in the DOM
	 */
	public WebElement getElement(String locator, String type){
		WebElement element  = null;
		type = type.toLowerCase();
		
		if(type.equals("id")){
			element  =  driver.findElement(By.id(locator));
		} else if(type.equals("css")){
			element = driver.findElement(By.cssSelector(locator));
		}else if (type.equals("name")){
			element  = driver.findElement(By.name(locator));
		}else if(type.equals("xpath")){
			element = driver.findElement(By.xpath(locator));
		}
		if(checkSingleEntry(locator, type)){
			System.out.println("Element Found and Returned");
			return element;
		}	
		System.out.println("Sorry Element not found, so not returned...");
		return null;


	}
	
	
	// shall give if it has multiple entries as a list in DOM 
	
	public List<WebElement> getElementsAsList(String locator, String type){
		type = type.toLowerCase();
		if(type.equals("id")){
			return driver.findElements(By.id(locator));
		}else if(type.equals("name")){
			return driver.findElements(By.name(locator));
		}else if(type.equals("xpath")){
			return driver.findElements(By.xpath(locator));
		}else if(type.equals("class")){
			return driver.findElements(By.className(locator));
		}// other TODO 
		return null;
	}
	
	// return true if element exists 
	// this method works for us when we have more than 1 element 
	// to be found for 
	public boolean isElementFound(String locator, String type){
		return getElementsAsList(locator, type).size()>0;
	}
	
	// this method gives true only where there is an single entry 
	// in the DOM 
	public boolean checkSingleEntry(String locator, String type){
		return getElementsAsList(locator, type).size() ==1;
	}
	public void assertURL(String expectedURL) {
		String actualURL = driver.getCurrentUrl();
		System.out.println("The URL at runtime is "+actualURL);
		Assert.assertTrue(actualURL.equals(expectedURL), "The assertion failed as the expected URL did not match with actual URL");
	}
	
	public void assertText(String expectedText, String locator, String type, String msg) {
		type = type.toLowerCase();
		
		if(type.equals("id")){
			String actualText = driver.findElement(By.id(locator)).getText();
			Assert.assertTrue(actualText.contains(expectedText),msg );
		}else if(type.equals("class")){
			String actualText = driver.findElement(By.className(locator)).getText();
			Assert.assertTrue(actualText.contains(expectedText),msg );
		}else if(type.equals("name")){
			String actualText = driver.findElement(By.name(locator)).getText();
			Assert.assertTrue(actualText.contains(expectedText),msg );
		}else if(type.equals("xpath")){
			String actualText = driver.findElement(By.xpath(locator)).getText();
			System.out.println(actualText);
			Assert.assertTrue(actualText.contains(expectedText),msg );
			
		}else if(type.equals("css")){
			String actualText = driver.findElement(By.cssSelector(locator)).getText();
			Assert.assertTrue(actualText.contains(expectedText),msg );
		}else if(type.equals("tagname")){
			String actualText = driver.findElement(By.tagName(locator)).getText();
			Assert.assertTrue(actualText.contains(expectedText),msg );
		}else if(type.equals("linktext")){
			String actualText = driver.findElement(By.linkText(locator)).getText();
			Assert.assertTrue(actualText.contains(expectedText),msg );
		}else if(type.equals("partiallinktext")){
			String actualText = driver.findElement(By.partialLinkText(locator)).getText();
			Assert.assertTrue(actualText.contains(expectedText),msg );
		}
		
	}
	
	public void scrollToElement(String locator, String type) {
		Point p;
		JavascriptExecutor js;
		int xcords;
		String xcoordinates ;
		int ycords;
		String ycoordinates;
	
		
		type = type.toLowerCase();
		
		if(type.equals("id")){
			p = driver.findElement(By.id(locator)).getLocation();
			xcords = p.x;
			xcoordinates = String.valueOf(xcords);
			ycords =p.y;
			ycoordinates = String.valueOf(ycords);
			js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollTo(xcoordinates, ycoordinates)", "");
		}else if(type.equals("name")){
			p = driver.findElement(By.name(locator)).getLocation();
			xcords = p.x;
			xcoordinates = String.valueOf(xcords);
			ycords =p.y;
			ycoordinates = String.valueOf(ycords);
			js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollTo(xcoordinates, ycoordinates)", "");
		}else if(type.equals("xpath")){
			p = driver.findElement(By.xpath(locator)).getLocation();
			xcords = p.x;
			xcoordinates = String.valueOf(xcords);
			ycords =p.y;
			ycoordinates = String.valueOf(ycords);
			js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollTo(xcoordinates, ycoordinates)", "");
		}else if(type.equals("class")){
			p = driver.findElement(By.className(locator)).getLocation();
			xcords = p.x;
			xcoordinates = String.valueOf(xcords);
			ycords =p.y;
			ycoordinates = String.valueOf(ycords);
			js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollTo(xcoordinates, ycoordinates)", "");
		}
    
	}
	}

	


