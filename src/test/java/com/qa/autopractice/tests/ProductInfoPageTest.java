package com.qa.autopractice.tests;



import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

import com.qa.autopractice.utils.Constants;

public class ProductInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void prodPageSetUp() {
		ap = lp.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		ap.clickOnShop();
		sp.selectProduct();
	}
	
	@Test(priority = 1)
	public void productNameTest() {
		String prodName = pi.checkProductName();
		Assert.assertEquals(prodName, Constants.PRODUCT_PAGE_HEADER);
		
	}
	@Test(priority = 2)
	public void prodPriceCheckTest() {
		String price = pi.productPriceCheck();
		
		Assert.assertTrue(price.equalsIgnoreCase("₹450.00"));
	}
	@Test(priority = 3)
	public void prodImageExistTest() {
		Assert.assertTrue(pi.prodImageExist());
	}
//	@DataProvider
//	public Object[][]attrDetails() {
//		return new Object[][] {
//			{"width"},
//			{"height"}
//		};
//		
//	}
//	@Test(priority = 4, dataProvider = "attrDetails")
//	public void prodSpecTest(String attrName) {
//		List<String> specDetails = pi.productSpecCheck(attrName);
//		
//		Assert.assertEquals(specDetails,Constants.PRODUCT_PAGE_PRODUCT_SPEC());
//		
//	}

}
