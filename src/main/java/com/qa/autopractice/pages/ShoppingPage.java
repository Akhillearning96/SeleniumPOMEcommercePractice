package com.qa.autopractice.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.autopractice.utils.ElementUtil;

public class ShoppingPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public ShoppingPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		
	}
	
	private By prodCategory = By.cssSelector("#woocommerce_product_categories-2 li");
	private By dropDown = By.xpath("//select[@name='orderby']");
	private By prodDisplay = By.xpath("//ul[@class='products masonry-done']//li");
	//private By prodDetails = By.xpath("//ul[@class='products masonry-done']//li//h3");
	private By prodAddToCart = By.xpath("//ul[@class='products masonry-done']//li//a[text()='Add to basket']");
	private By clickOnImage = By.xpath("//*[@id=\"content\"]/ul/li[1]/a[1]/img");
	
	
	public List<String> prodCategoryList() {
		List<WebElement> prodList = eleUtil.getElements(prodCategory);
		System.out.println(prodList.size());
		List<String> prodCategoryList = new ArrayList<String>();
		for(WebElement e : prodList) {
			String text = e.getText();
			prodCategoryList.add(text);
		}
		return prodCategoryList;
		
	}

	public boolean DefaultSortingExist() {
		return eleUtil.doIsDisplayed(dropDown);
		
	}
	public int prodDisplayCheck() {
		return eleUtil.getElements(prodDisplay).size();
	}
	public void selectProduct() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		eleUtil.doClick(clickOnImage);
		
		
	}
	public void selectProductCheck() {
		eleUtil.doClick(prodAddToCart);
	}
	public String getShopPageUrl() {
		return driver.getCurrentUrl();
	}
	
}
