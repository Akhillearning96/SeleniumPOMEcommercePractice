package com.qa.autopractice.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.autopractice.utils.ElementUtil;

public class ProductInfoPage {
	
	ElementUtil eleUtil;
	
	public ProductInfoPage(WebDriver driver) {
		
		eleUtil = new ElementUtil(driver);
		
	}

	private By productName = By.cssSelector("h1.product_title.entry-title");
	private By productPrice = By.xpath("//div[@itemprop='offers']//ins");
	private By prodImage = By.xpath("//div[@class='images']");
	
	public String checkProductName() {
		return eleUtil.WaitForTheElementToBeVisible(productName, 5).getText();
	}
	public String productPriceCheck() {
		return eleUtil.getElement(productPrice).getText();
		
		
	}
	public boolean prodImageExist() {
		return eleUtil.doIsDisplayed(prodImage);
		
	}
	public List<String> productSpecCheck(String attr) {
		return eleUtil.getAttributeList(prodImage, attr);
		
	}
	
}
