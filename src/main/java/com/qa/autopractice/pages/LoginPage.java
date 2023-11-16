package com.qa.autopractice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.autopractice.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	ElementUtil eleUtil ;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		
	}
	
	private By appLogo = By.xpath("//header[@id='header']//img");
	private By loginHeader =By.xpath("//h2[text()='Login']");
	private By lostPwdLink = By.linkText("Lost your password?");
	private By username = By.xpath("//input[@id='username']");
	private By password = By.xpath("//input[@id='password']");
	private By loginBtn = By.xpath("//input[@name='login']");
	
	@Step("getting login page title.....")
	public String loginPageTitle() {
		return driver.getTitle();

	}
	@Step("getting login page url.....")
	public String loginPageUrl() {
		return driver.getCurrentUrl();
	}
	
	@Step("checking forgot password link exist or not .....")
	public boolean loginPageLogoExist() {
		 return eleUtil.doIsDisplayed(appLogo);	
		 }
	public String loginHeaderExist() {
		String text = eleUtil.getElement(loginHeader).getText();
		System.out.println("login header is :"+ text);
		return text;
	}
	public boolean forgotPwdLinkExist() {
		return eleUtil.doIsDisplayed(lostPwdLink);
	}
	@Step("do login with username:{0} and password : {1}")
	public AccountPage doLogin(String un,String pwd) {
		eleUtil.doSendKeys(username, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
		return new AccountPage(driver);
		
	}
	
	
	
	
	
	
	

}
