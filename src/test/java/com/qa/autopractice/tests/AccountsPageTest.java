package com.qa.autopractice.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

import com.qa.autopractice.utils.Constants;

public class AccountsPageTest extends BaseTest {
	
	
	@BeforeClass
	public void accPageSetup() {
		ap = lp.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		
	}

	@Test(priority = 1)
	public void confirmationCheckTest() {
		String confirmText = ap.accountPageConfirmMsg();
		Assert.assertEquals(confirmText, Constants.CONFIRMATION_ACCOUNT_PAGE);
	}
	@Test(priority = 2)
	public void linksOnPageExistTest() {
		int count = ap.linksPresentOnPage();
		Assert.assertTrue(count>0);
	}
	@Test(priority =3)
	public void linksDataMatchTest() {
		List<String> linksData = ap.linksNameMatching();
		
		Assert.assertEquals(linksData, Constants.ACCOUNT_PAGE_LINKS_LIST());
	}
	
	@Test(priority =4)
	public void clickOnShopLinkTest() {
		sp = ap.clickOnShop();
		String url = sp.getShopPageUrl();
		Assert.assertTrue(url.equals(Constants.SHOPPING_PAGE_URL));
	}
	@Test(priority =5)
	public void prodCategoryListTest() {
		List<String> prodListCa = sp.prodCategoryList();
		Assert.assertEquals(prodListCa, Constants.SEARCH_PAGE_PRODUCT_CATEGORIES());
	}
	@Test(priority =6)
	public void DefaultSortingExistTest() {
		
		Assert.assertTrue(sp.DefaultSortingExist());
	}

	
	@Test(priority =7)
	public void selecProductTest(String productName) {
		sp.selectProduct();
	}
	
	
}
