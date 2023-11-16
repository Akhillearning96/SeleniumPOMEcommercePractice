package com.qa.autopractice.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.autopractice.factory.DriverFactory;

public class ElementUtil {
	private WebDriver driver;
	JavaScriptUtil jsUtil;
	
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
	}
	
	//***************************By locator  **********************************

	public  By getBy(String locatorType,  String locatorValue) {
		
		
		By locator = null;
		
		if(locatorType.toLowerCase().equals("id") ){
			locator = By.id(locatorValue);
		}else if(locatorType.toLowerCase().equals("name")) {
			locator = By.name(locatorValue);
		}else if(locatorType.toLowerCase().equals("classname")) {
			locator = By.className(locatorValue);
		}else if(locatorType.toLowerCase().equals("xpath")) {
			locator = By.xpath(locatorValue);
		}else if(locatorType.toLowerCase().equals("cssselector")) {
			locator = By.cssSelector(locatorValue);
		}else if(locatorType.toLowerCase().equals("linktext")) {
			locator = By.linkText(locatorValue);
		}else if(locatorType.toLowerCase().equals("partiallinktext")) {
			locator = By.partialLinkText(locatorValue);
		}else if(locatorType.toLowerCase().equals("tagname")) {
			locator = By.tagName(locatorValue);
		}else {
			System.out.println("please pass the correct browser.........");
		}
		return locator;
	}
	//***************************findElement**********************************
	public  WebElement getElement(By locator) {
		
				WebElement element = driver.findElement(locator);
				if(Boolean.parseBoolean(DriverFactory.highlight)){
						jsUtil.flash(element);
				}
				
		return element;
	}
	public  WebElement getElement(String locatorType, String locatorValue) {
		return driver.findElement(getBy(locatorType,locatorValue));
	}
	
	//***************************findElements**********************************
	public  List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	
	//*************************** sendKeys **********************************
	
	public  void doSendKeys(By locator,String value) {
		getElement(locator).sendKeys(value);
	}
	public  void doSendKeys(String locatorType,String locatorValue,String Value) {
		getElement(locatorType, locatorValue).sendKeys(Value);
	}
	public void doSendKeys(By locator,int timeOut,String value) {
		doPresenceOfElementLocated(locator,timeOut).sendKeys(value);
		
	}
	//*************************** Click **********************************
	public  void doClick(By locator) {
		getElement(locator).click();
	}
	public  void doClick(String locatorType,String locatorValue) {
		getElement(locatorType,locatorValue);
	}
	public  void doClick(By locator,int timeOut) {
		doPresenceOfElementLocated(locator,timeOut).click();
	}
	//*************************** getText **********************************
	public String doGetText(By locator) {
		return getElement(locator).getText();
	}
	public String doGetText(String locatorType,String locatorValue) {
		return getElement(getBy(locatorType,locatorValue)).getText();
	}
	//*************************** isDisplayed **********************************
	public  boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	
	//***************************Elements other Utils **********************************	
	public  int getElementsCount(By locator) {
		return getElements(locator).size();
		
	}
	public  List<String> getElementsTextList(By locator) {
		List<WebElement> list = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();
		for(WebElement e : list) {
			String eleList = e.getText();
			if(!eleList.isEmpty()) {
			eleTextList.add(eleList);
			}
		}
		return eleTextList ;
		
	}
	
	public  List<String> getAttributeList(By locator,String attributeName) {
		List<WebElement> al = getElements(locator);
		List<String> attrList = new ArrayList<String>();
		for(WebElement e : al) {
		String attrValue =	e.getAttribute(attributeName);
		attrList.add(attrValue);
		
		}
		return attrList;
	}
	//*************************** DropDownWithSelect **********************************	
	
	public  void doDropDownSelectByIndex(By locator,int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);

}
	public  void doDropDownSelectByVisibleText(By locator,String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}
	public  void doDropDownSelectByValue(By locator,String Value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(Value);
	}
	public  void doSelectDropDownValue(By locator,String value) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		for(WebElement e : optionsList) {
			String text = e.getText();
			if(text.equals(value)) {
				e.click();			
				}
		}
	
	}
	//****************************LinksList*********************************/
	public  List<String> getLinksTextList(By locator) {
		List<WebElement> linksList =driver.findElements(locator);
		List<String> list = new ArrayList<String>();
		
		for(WebElement e : linksList) {
			String text = e.getText().trim();
			
			list.add(text);
			
	}
		return list;
	

	}
	/******************************Action Util***********************************/
	
	public  void doMoveToElement(By locator) {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(locator)).build().perform();
		
	}
	public  void doClickOnChildMenu(By parentMenuLocator,By childMenuLocator) throws InterruptedException {
		doMoveToElement(parentMenuLocator);
		Thread.sleep(3000);
		getElement(childMenuLocator).click();
		
	}
	public  void doActionsSendKeys(By locator,String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).build().perform();
	}
	public  void doActionSendKeysOnActiveElement(By locator,String value) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).sendKeys(value).build().perform();
	}
	public  void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).build().perform();
	}

/******************************Wait Utils****************************************/	
	public  WebElement doPresenceOfElementLocated(By locator,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	public  WebElement doPresenceOfElementLocated(By locator,int timeOut,long intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut), Duration.ofMillis(intervalTime));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	public  WebElement WaitForTheElementToBeVisible(By locator,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	public  WebElement WaitForTheElementToBeVisible(By locator,int timeOut,long intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut),Duration.ofMillis(intervalTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}public  List<WebElement> WaitForTheAllElementToBeVisible(By locator,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	public  boolean waitForURLToContain(String urlFraction , int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.urlContains(urlFraction));
	}
	public  boolean waitForURLToBe(String url , int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.urlToBe(url));
	}
	public  String doGetTitle(String urlFraction , int timeOut) {
		if( waitForURLToContain(urlFraction , timeOut) ) {
			return driver.getCurrentUrl();
		}
		return null;
	}
	public  Alert waitForAlert(int timeOut) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	public  String getAlertText(int timeOut) {
		return waitForAlert(timeOut).getText();
	}
	public void doAlertAccept(int timeOut) {
		
		waitForAlert(timeOut).accept();
	}
	public  void doAlertDismiss(int timeOut) {
		
		waitForAlert(timeOut).dismiss();
	}
	public  void waitForFrameByLocator(By locator,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
		
	}
	public  void waitForFrameByNameorId(String nameOrId,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId));
	}
	public  void clickElementWhenReady(By locator,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver ,Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	public  void clickElementWhenReady(By locator,int timeOut,long intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver ,Duration.ofSeconds(timeOut),Duration.ofMillis(intervalTime));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	public  WebElement WaitForElementPresentUsingFluentWait(By locator,int timeOut,long intervalTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
					.pollingEvery(Duration.ofMillis(intervalTime))
						.withMessage("Element is not found........");
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	public  WebElement WaitForElementPresentUsingWebDriverWait(By locator,int timeOut,long intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
				wait.pollingEvery(Duration.ofMillis(intervalTime))
						.withMessage("Element is not found........");
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	public  WebElement retryElement(By locator, int timeOut) {
		WebElement element  = null;
		int attempts = 0;
		while (attempts < timeOut){
			try {
			element = getElement(locator);
			}catch(Exception e){
				System.out.println("Element is not found in attempt  : " + attempts);
			}
			attempts++;
		}
		if(element == null) {
			try {
				throw new Exception("ELEMENTNOTFOUNDEXCEPTION");
			}catch(Exception e) {
				System.out.println("Element is not found exception");
			}
		}
		return element;
	}
}
