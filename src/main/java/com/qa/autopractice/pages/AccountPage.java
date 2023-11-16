package com.qa.autopractice.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.autopractice.utils.ElementUtil;

public class AccountPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	

	public AccountPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	
	}
	private By linksPresnt = By.cssSelector("div.woocommerce ul li");
	
	private By confirmationMessage = By.cssSelector("div.woocommerce-MyAccount-content p:nth-of-type(1)");
	private By shopLink = By.xpath("//a[text()='Shop']");
	
	
	public String accountPageConfirmMsg() {
		return eleUtil.doGetText(confirmationMessage);
		
	}
	public int linksPresentOnPage() {
		return eleUtil.getElementsCount(linksPresnt);
	}
	public List<String> linksNameMatching() {
		List<WebElement> linksList = eleUtil.getElements(linksPresnt);
		List<String> linksData = new ArrayList<String>();
		for( WebElement e : linksList) {
			String text = e.getText();
			linksData.add(text);
		
		}
		return linksData;
	}
	public ShoppingPage clickOnShop() {
		eleUtil.doClick(shopLink);
		return new ShoppingPage(driver);
	}
	

}
