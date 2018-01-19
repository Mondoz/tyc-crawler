package com.hiekn.service;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.hiekn.util.ConstResource;

public class FlushService implements Runnable {
	
	private static final String firefoxPath = ConstResource.FIREFOX_PATH;
	private static WebDriver driver = null;
	
	public FlushService() {
		System.setProperty("webdriver.firefox.bin", firefoxPath); 
		FlushService.driver = new FirefoxDriver();
	}
	public void run() {
		
		while (true) {
//			File pathToBinary = new File(firefoxPath);
//			FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
//			FirefoxProfile firefoxProfile = new FirefoxProfile();  		
			
			driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
			// Sets the amount of time to wait for a page load to complete before throwing an error. 
			// If the timeout is negative, page loads can be indefinite.
			driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
			// Specifies the amount of time the driver should wait 
			// when searching for an element if it is not immediately present. 
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.get("http://www.tianyancha.com/");
			driver.findElement(By.id("live-search")).sendKeys("华为");
			driver.findElement(By.className("search_button")).click();
			try {
				TimeUnit.SECONDS.sleep(20);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//			driver.close();
//			driver.quit();
//			try {
//				TimeUnit.SECONDS.sleep(5);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
	}
}
