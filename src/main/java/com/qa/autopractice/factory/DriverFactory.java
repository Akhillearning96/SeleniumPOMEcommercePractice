package com.qa.autopractice.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	WebDriver driver;
	Properties prop;
	public static String highlight;
	OptionManager optionManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/*
	 * This method is used to initialize the webdriver
	 * 
	 * @param browser name
	 * 
	 * @return this will return the driver
	 */

	public WebDriver init_drivers(Properties prop) {

		String browserName = prop.getProperty("browser");
		String url = prop.getProperty("url");
		highlight = prop.getProperty("highlight");
		optionManager = new OptionManager(prop);

		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver(optionManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionManager.getChromeOptions()));

		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver(optionManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionManager.getFirefoxOptions()));

		} else if (browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		} else {
			System.out.println("Please pass the correct browser");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(url);

		return getDriver();

	}

	/*
	 * getDriver(); it will return a thread local copy of the web driver
	 * 
	 * 
	 */

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties init_properties() {
		prop = new Properties();
		FileInputStream ip = null;
		String envName = System.getProperty("env");// qa/dev/stage/uat

		if (envName == null) {
			try {
				ip = new FileInputStream("./src/test/resources/confiration/config.prop");
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
		} else {
			System.out.println("running on environment:" + envName);

			try {

				if (envName.equals("qa")) {
					ip = new FileInputStream("./src/test/resources/confiration/qa.config.properties");

				} else if (envName.equals("dev")) {
					ip = new FileInputStream("./src/test/resources/confiration/dev.config.properties");

				} else if (envName.equals("stage")) {
					ip = new FileInputStream("./src/test/resources/confiration/stage.config.properties");

				} else if (envName.equals("uat")) {
					ip = new FileInputStream("./src/test/resources/confiration/uat.config.properties");

				} else {
					System.out.println("please pass the right environment.....");

				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();

			}

		}
		try {
			prop.load(ip);
		} catch (IOException e) {

			e.printStackTrace();
		}

		return prop;

	}
	public String getScreenshot() {
		File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"screenshots/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src,destination);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return path;
	}
}
