package com.qa.autopractice.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.autopractice.factory.DriverFactory;
import com.qa.autopractice.pages.AccountPage;
import com.qa.autopractice.pages.LoginPage;
import com.qa.autopractice.pages.ProductInfoPage;
import com.qa.autopractice.pages.RegistrationAccountPage;
import com.qa.autopractice.pages.ShoppingPage;


public class BaseTest {
	
	
	DriverFactory df ;
	WebDriver driver;
	LoginPage lp;
	AccountPage ap;
	Properties prop;
	ShoppingPage sp;
	ProductInfoPage pi;
	SoftAssert softAssert;
	RegistrationAccountPage rap;
	
	
	
	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.init_properties();
		driver = df.init_drivers(prop);
		lp = new LoginPage(driver);
		pi = new ProductInfoPage(driver);
		sp = new ShoppingPage(driver);
		softAssert = new SoftAssert();
		rap = new RegistrationAccountPage(driver);
	
		
	
	}
	
	@AfterTest
	public void tearDown() {
		
	}

}
