package com.qa.autopractice.listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.autopractice.factory.DriverFactory;

import io.qameta.allure.Attachment;

public class TestAllureListener extends DriverFactory implements ITestListener {

	
	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}
	//Text attachments for allure
	@Attachment(value="Page screenshot", type="image/png")
	public byte[] screenshotPNG(WebDriver driver) {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	}
	//Text attachments for allure
	@Attachment(value="{0}", type="text/plain")
	public static String saveTextLog(String message) {
		return message;
	}
	//HTML attachments for allure
	@Attachment(value="{0}", type="text/html")
	public static String attachHtml(String html) {
		return html;
	}
	@Override
	public void onStart(ITestContext iTestContext) {
		System.out.println("I am in onStart method " + iTestContext.getName());
		
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		System.out.println("I am in onFinish method " + iTestContext.getName());
		
	}
	
	
	@Override
	public void onTestStart(ITestResult iTestResult) {
		System.out.println("I am in onTestStart method" + getTestMethodName(iTestResult)+"start");
		
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println("I am in onTestSuccess method" + getTestMethodName(iTestResult)+"success");
		
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		System.out.println("I am in onTestFailure method" + getTestMethodName(iTestResult)+"fail");
		//Object testclass = iTestResult.getInstance();
		if(getDriver()instanceof WebDriver) {
			System.out.println("Screenshot captured for test case"+getTestMethodName(iTestResult));
			screenshotPNG(getDriver());
		}
		saveTextLog(getTestMethodName(iTestResult)+"failed and screenshot taken!");
		
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		System.out.println("I am in onTestSkipped method" + getTestMethodName(iTestResult)+"skip");
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		System.out.println("onTestFailedButWithinSuccessPercentage" + getTestMethodName(iTestResult));
		
	}

	

}
