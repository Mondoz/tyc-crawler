package com.hiekn.tianyancha;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebTest {
	static String firefoxPath = "D:\\Mozilla Firefox\\firefox.exe";
//	static String wdPath = "D:\\Download\\geckodriver-v0.15.0-win64\\geckodriver.exe";
	static String wdPath = "chromedriver.exe";
	public static void main(String[] args) throws InterruptedException {
		while (true) {
			Random random = new Random();
//			File pathToBinary = new File(firefoxPath);
//			FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
//			FirefoxProfile firefoxProfile = new FirefoxProfile();  		
//			System.setProperty("webdriver.gecko.driver", firefoxPath); 
//			System.setProperty("webdriver.chrome.driver", wdPath); 
			System.setProperty("webdriver.firefox.bin", firefoxPath); 
			WebDriver driver = new FirefoxDriver();
//			WebDriver driver = new ChromeDriver();
			Thread.sleep(5000);
//			driver.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS); 
			// Sets the amount of time to wait for a page load to complete before throwing an error. 
			// If the timeout is negative, page loads can be indefinite.
//			driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
			// Specifies the amount of time the driver should wait 
			// when searching for an element if it is not immediately present. 
//			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			driver.get("https://www.baidu.com");
			Thread.sleep(5000);
//			WebElement element = driver.findElement(By.cssSelector("ul.container > li > div.info-block > h4 > a"));
//			System.out.println(element.getText());
//	        // 输入关键字
//	        element.sendKeys("响应式系统");
//	        WebElement clickEle = driver.findElement(By.id("btnSearch"));
//	        clickEle.click();
//	        System.out.println("click finish");
//	        Thread.sleep(10000);
//	        driver.get("http://kns.cnki.net/kns/brief/brief.aspx?pagename=ASP.brief_default_result_aspx&dbPrefix=SCDB&dbCatalog=%E4%B8%AD%E5%9B%BD%E5%AD%A6%E6%9C%AF%E6%96%87%E7%8C%AE%E7%BD%91%E7%BB%9C%E5%87%BA%E7%89%88%E6%80%BB%E5%BA%93");
////	        System.out.println(driver.getPageSource());
//	        List<WebElement> infoList = driver.findElements(By.tagName("tr"));
//	        for (WebElement webElement : infoList) {
//				System.out.println(webElement.getAttribute("innerHTML"));
//			}
//			WebElement element = driver.findElement(By.cssSelector("div#WAF_NC_WRAPPER"));
//			WebElement from = driver.findElement(By.cssSelector("span#nc_1_n1z"));
//			WebElement to = driver.findElement(By.cssSelector("div.clickCaptcha_img"));
//			WebElement to = driver.findElement(By.cssSelector("i#nc_1__btn_2"));
//			String s = element.getAttribute("innerHTML");
//			while (s.contains("加载")) {
//				s = element.getAttribute("innerHTML");
//				System.out.println("加载中");
//			}
//			System.out.println("加载完成");
//			Actions builder = new Actions(driver); 
//			Action drag = builder.clickAndHold(from).moveToElement(to).release(to).build();
//			Actions drag = new Actions(driver).dragAndDropBy(from, 1000, 0);
//			drag.perform();
//			Actions drag = new Actions(driver).clickAndHold(from).moveByOffset(200, -random.nextInt(10));
//			drag.clickAndHold(from).moveByOffset(100, -random.nextInt(10));
//			drag.moveToElement(from).release();
//			drag.perform();
//			Actions click = new Actions(driver).moveToElement(to, 200, 100).click();
//			click.perform();
//			String flagStr = driver.findElement(By.cssSelector("body")).getAttribute("innerHTML");
//			System.out.println(flagStr);
//			while (flagStr.contains("安全验证")) {
//				click = new Actions(driver).moveToElement(to, 200, 100).click();
//				click.perform();
//				flagStr = driver.findElement(By.cssSelector("body")).getAttribute("innerHTML");
//			}
//			System.out.println("验证成功");
//			driver.findElement(By.className("search_button")).click();
			TimeUnit.MINUTES.sleep(10);
			driver.close();
			driver.quit();
			TimeUnit.SECONDS.sleep(5);
		}
	}
	
}
