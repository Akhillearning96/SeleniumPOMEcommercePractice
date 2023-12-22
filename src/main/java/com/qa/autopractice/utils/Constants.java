package com.qa.autopractice.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	
	public static final String CONFIRMATION_ACCOUNT_PAGE = "Hello akhil.chennuru95 (not akhil.chennuru95? Sign out)";
	
	public static final String SHOPPING_PAGE_URL = "https://practice.automationtesting.in/shop/";
	public static final String PRODUCT_PAGE_HEADER= "Android Quick Start Guide";
	public static final String REGISTATION_CONFIRMATION ="Hello";
	public static final String SHIPPING_PAGE_TITLE = "Checkout";
	
	public static final List<String> ACCOUNT_PAGE_LINKS_LIST() {
		
		List<String> linksList = new ArrayList<String>();
		linksList.add("Dashboard");
		linksList.add("Orders");
		linksList.add("Downloads");
		linksList.add("Addresses");
		linksList.add("Account Details");
		linksList.add("Logout");
		
		return linksList;
	}
public static final List<String> SEARCH_PAGE_PRODUCT_CATEGORIES() {
		
		List<String> prodList = new ArrayList<String>();
		prodList.add("Android (1)");
		prodList.add("HTML (3)");
		prodList.add("JavaScript (3)");
		prodList.add("selenium (1)");
		
		return prodList;
	}
public static final List<String> PRODUCT_PAGE_PRODUCT_SPEC() {
	
	List<String> prodSpec = new ArrayList<String>();
	prodSpec.add("600");
	prodSpec.add("600");
	
	
	return prodSpec;
}

}
