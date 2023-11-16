package com.qa.autopractice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.autopractice.utils.Constants;
import com.qa.autopractice.utils.ElementUtil;

public class RegistrationAccountPage {
	

	ElementUtil eleUtil;
	
	public RegistrationAccountPage(WebDriver driver) {
	
		eleUtil = new ElementUtil(driver);
		
	}
	private By email = By.id("reg_email");
	private By password = By.id("reg_password");
	private By registerBtn = By.xpath("(//input[@class='woocommerce-Button button'])[2]");
	private By newPageText = By.xpath("(//div[@class='woocommerce-MyAccount-content']/p)[1]");
	private By signout = By.xpath("//a[text()='Sign out']");
	
	
	
	public boolean registration(String username , String password) {
		eleUtil.doSendKeys(email, username);
		eleUtil.doSendKeys(this.password,password );
		eleUtil.doClick(registerBtn);
	
		
		String text = eleUtil.WaitForTheElementToBeVisible(newPageText, 5).getText();
		
		if(text.contains(Constants.REGISTATION_CONFIRMATION)) {
			eleUtil.doClick(signout);
			return true;
		}
		return false;
		
		
	}
	

}
