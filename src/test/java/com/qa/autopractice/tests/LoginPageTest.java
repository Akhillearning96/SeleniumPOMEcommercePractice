package com.qa.autopractice.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.autopractice.listeners.TestAllureListener;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
@Listeners(TestAllureListener.class)
public class LoginPageTest extends BaseTest {
	
	@Description("login page url Test")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void loginPageUrlTest() {
		String text =lp.loginPageUrl();
		Assert.assertTrue(text.contains("https://practice.automationtesting.in/my-account/"));
	}
	
	@Description("login Header Exist Test")
	@Severity(SeverityLevel.MINOR)
	@Test
	
	public void loginHeaderExistTest() {
		String header = lp.loginHeaderExist();
		Assert.assertEquals(header, "Login");
	}

	@Description("login Header Exist Test")
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void loginPageTest() {
	ap = lp.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	
	int count = ap.linksPresentOnPage();
	softAssert.assertTrue(count>0);
	softAssert.assertAll();
		
	}

}
