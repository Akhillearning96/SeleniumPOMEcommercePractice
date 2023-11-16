package com.qa.autopractice.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.autopractice.utils.ExcelUtil;

public class RegistrationTest extends BaseTest {
	
	public String getRandomEmail() {
		Random randomGenerator = new Random();
		String email = "noveautomation" +randomGenerator.nextInt(1000) + "@gmail.com";
		
		return email;
	}
	
	@DataProvider 
	public Object[][] registerCredentials() {
		return ExcelUtil.getTestData("Registration");
			
	}
	@Test(dataProvider = "registerCredentials")
	public void registerTest(String pwd) {
		
			Assert.assertTrue(rap.registration(getRandomEmail(),pwd));	
		}
	}
