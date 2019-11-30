package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CategoriesPOM {
private WebDriver driver; 
	
	public CategoriesPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//For saving categories part
	
	@FindBy(xpath="//*[@id='content']/div[1]/div/div/a[1]/i")
	private WebElement addIcon;
	
	@FindBy(xpath="//*[@id='input-name1']")
	private WebElement nameTextBox;
	
	@FindBy(xpath="//*[@id='language1']/div[2]/div/div/div[3]/div[2]")
	private WebElement descriptionTextBox;
	
	@FindBy(xpath="//*[@id='input-meta-title1']")
	private WebElement metaTagTtl;
	
	@FindBy(xpath="//*[@id='input-meta-description1']")
	private WebElement metaTagDesc;
	
	
	@FindBy(xpath="//*[@id='form-category']/ul/li[2]/a")
	private WebElement dataTab;
	
	@FindBy(xpath="//*[@id=\"form-category\"]/ul/li[3]/a")
	private WebElement designTab;
	
	@FindBy(xpath="//*[@id='content']/div[1]/div/div/button")
	private WebElement saveIcon;
	
	//For editing in categories section
	
	@FindBy(xpath="//*[@id='form-category']/div/table/tbody/tr[23]/td[4]/a")
	private WebElement editBtn;
	
	
	//Actions in save section
	
	public void clickAddIcon() {
	this.addIcon.click();
	}
	public void sendNameText(String nameTextBox) {
		this.nameTextBox.clear();
		this.nameTextBox.sendKeys(nameTextBox);
	}
	public void clickDescText() {
		this.descriptionTextBox.click();
		}
	
	public void sendDescText(String descriptionTextBox) {
		this.descriptionTextBox.clear();
		this.descriptionTextBox.sendKeys(descriptionTextBox);
	}
	
	public void sendMetaTgTtl(String metaTagTtl) {
		this.metaTagTtl.clear();
		this.metaTagTtl.sendKeys(metaTagTtl);
	}
	
	public void sendMetaTgDesc(String metaTagDesc) {
		this.metaTagDesc.clear();
		this.metaTagDesc.sendKeys(metaTagDesc);
	}
	public void clickDataTab() {
		this.dataTab.click();
		}
	public void clickDesignTab() {
		this.designTab.click();
		}
	public void clickSaveIcon() {
		this.saveIcon.click();
		
	}
	//Actions in editing section
	
	public void clickEditBtn() {
		this.editBtn.click();
		}
	public void editMetaTgTtlClr(String metaTagDesc) {
		this.metaTagTtl.clear();
		}
	
	public void editMetaTgDesc(String metaTagDesc) {
		this.metaTagDesc.clear();
		this.metaTagDesc.sendKeys(metaTagDesc);
	}
	
	
}
