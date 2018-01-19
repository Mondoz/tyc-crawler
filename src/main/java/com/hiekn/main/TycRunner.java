package com.hiekn.main;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hiekn.service.FlushService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.bson.Document;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hiekn.util.BufferedReaderUtil;
import com.hiekn.util.ConstResource;
import com.hiekn.util.HttpReader;
import com.hiekn.util.StaticHttpReader;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class TycRunner {
	
	static final Logger LOGGER = Logger.getLogger(TycRunner.class);
	private final static long interval = 3000;
	private final static long deadInterval = 50000;
	static String firefoxPath = "D:\\Mozilla Firefox\\firefox.exe";
	private final static String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0";
//	private final static String userAgent = "Mozilla/5.0 (X11; Linux x86_64; rv:31.0) Gecko/20100101 Firefox/31.0"; //linux editon
//	private static String staticCookie = "TYCID=ff1b192795334ce09d27431d099fbbfc; tnet=43.250.8.19; token=";
	private static String staticCookie = "";
	private static String comName = "";
	private static String cookie = "ssuid=8887511418; aliyungf_tc=AQAAACSc/U/7qAYAxgdRZaE+qnEpYAna; _pk_id.1.e431=f73cc63fd7ec9a85.1494838855.4.1498637679.1498637605.; bannerStorageV2=%22true%22; token=dc1df0e108764d4e8fa8cc6540d093d2; _utm=ab4b7951207549d094c1052997f5f9a6; paaptp=d161e6812e050cfa915c3d92c9769a51aec13afa1e01ac670715cedc43c69; csrfToken=fLlN23wdljQvbOTrcbSc0-9F; TYCID=89a84640607b11e7afba09deff63d2be; uccid=d45505d9cfbdf4874624a3fd0234a79d; bannerFlag=true; Qs_lvt_117460=1498634799%2C1499146868%2C1499218773%2C1499754807; Qs_pv_117460=4080857822864851500%2C1359333255647943400%2C3720102349947999700%2C3875274748719039500%2C2280929900027250000; RTYCID=9a0a7d9d0fc2456dabdb25ac90f84b68; tyc-user-info=%257B%2522token%2522%253A%2522eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxODk2NDYyNjk2NyIsImlhdCI6MTUwMDYyMjkyOSwiZXhwIjoxNTE2MTc0OTI5fQ.x_ZUncg6OXDpDtYZ2_GAEsAkZcw04R0dusRfYlT2mORnLOvgZS3tGnfQK6AZb2D2pxr8iNkLUDVlPSBCAenpLg%2522%252C%2522integrity%2522%253A%25220%2525%2522%252C%2522state%2522%253A%25220%2522%252C%2522vnum%2522%253A%25220%2522%252C%2522onum%2522%253A%25220%2522%252C%2522mobile%2522%253A%252218964626967%2522%257D; auth_token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxODk2NDYyNjk2NyIsImlhdCI6MTUwMDYyMjkyOSwiZXhwIjoxNTE2MTc0OTI5fQ.x_ZUncg6OXDpDtYZ2_GAEsAkZcw04R0dusRfYlT2mORnLOvgZS3tGnfQK6AZb2D2pxr8iNkLUDVlPSBCAenpLg; OA=+1sUA6SDzJCpmk/Dtht3PRB4/lj85MQzadj0fMjk2lZBVGEy0pxGHkXIl+1Szl36; _csrf=etDUhj3mydjTMWi+bR33/g==; _csrf_bk=97406dde41fe202bbf8d1434417bda95; Hm_lvt_e92c8d65d92d534b0fc290df538b4758=1499146868,1499754807,1500348317,1500604729; Hm_lpvt_e92c8d65d92d534b0fc290df538b4758=1500631964";
	private static String comId = "";
	private static HttpReader reader = null;
	public static void main(String[] args) {
		
		try {
			PropertyConfigurator.configure("log4j.properties");  
			if(args.length<1){
				System.out.println("请输入路径"); 
			}
			LOGGER.info("start");
			LOGGER.info(ConstResource.FIREFOX_PATH);
			FlushService service = new FlushService();
			Thread thread = new Thread(service);
			thread.start();
			while (true) {
//				crawler(args[0]);
				crawler("location");
				System.out.println("sleep 15");
				TimeUnit.MINUTES.sleep(15);
			}
		} catch (Exception e) {
			LOGGER.error(e);
			e.printStackTrace();
		}
	}
	
	public static void getStaticCookie() {
		boolean getCookie = false;
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		while (!getCookie) {
			try {
				Map<String,String> cookieMap = new HashMap<String,String>();
				client = HttpClientBuilder.create().build();
				HttpClientContext context = HttpClientContext.create();
				HttpGet get = new HttpGet("http://www.tianyancha.com/");
				get.setHeader("Accept", "application/json, text/plain, */*");
				get.setHeader("User-Agent",userAgent);
				get.setHeader("Referer","http://www.tianyancha.com/");
				get.setHeader("Host","www.tianyancha.com");
				get.setHeader("Cache-Control","max-age=0");
				get.setHeader("Connection","keep-alive");
				get.setHeader("Accept-Encoding","gzip, deflate");
				get.setHeader("Accept-Language","en-US,en;q=0.5");
				RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();//设置请求和传输超时时间
				get.setConfig(requestConfig);
				response = client.execute(get,context);
				CookieStore cookieStore = (CookieStore) context.getCookieStore();
				List<Cookie> cookies = cookieStore.getCookies();
				if (cookies.size() > 0) getCookie = true;
				for (Cookie cookie : cookies) {
					cookieMap.put(cookie.getName(), cookie.getValue());
				}
				for (Entry<String,String> entry : cookieMap.entrySet()) {
					staticCookie = staticCookie + entry.getKey() + "=" + entry.getValue() + "; ";
				}
				staticCookie = staticCookie + "token=";
			} catch (Exception e) {
				try {
					client.close();
					response.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	public static void crawler(String path) throws Exception {
		MongoClient client = new MongoClient("127.0.0.1",27017);
		MongoDatabase db = client.getDatabase("tyc");
		MongoCollection<Document> collection = db.getCollection("com_info");
//		System.setProperty("webdriver.firefox.bin", firefoxPath); 
//		WebDriver driver = new FirefoxDriver();
//		driver.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);
		// Sets the amount of time to wait for a page load to complete before throwing an error. 
		// If the timeout is negative, page loads can be indefinite.
//		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		// Specifies the amount of time the driver should wait 
		// when searching for an element if it is not immediately present. 
//		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		Set<String> crawlerSet = getCrawlerSet(path);
		if (crawlerSet.size()==0) {
			System.out.println("extraction has finished");
			System.exit(0);
		}
		LOGGER.info("剩余公司为"+crawlerSet.size()+"家");
		FileWriter fw = new FileWriter(path+"/result.txt",true);
		for (String com : crawlerSet) {
			comName = com.trim();
			System.out.println(comName);
			getSearchToken(comName);
			String comUrl = getComUrl(comName);
			if (!comUrl.equals("") && !comUrl.equals("error")) {
				fw.write(comName + "\t" + comUrl + "\r\n");
				LOGGER.info(comName + "\t" + comUrl + "\r\n");
				fw.flush();
				System.out.println(comUrl + comName);
				try {
					String source = getComInfo(comUrl);
					System.out.println(source);
					Document doc = new Document();
					doc.append("name", comName).append("url", comUrl).append("source", source);
					collection.insertOne(doc);
					System.out.println("insert success " + comName);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		fw.close();
		client.close();
	}
	
	/**
	 * 获得剩余公司信息
	 * @return
	 * @throws Exception
	 */
	public static Set<String> getCrawlerSet(String path) throws Exception {
		Set<String> crawlerSet = new HashSet<String>();
		Set<String> crawleredSet = new HashSet<String>();
		BufferedReader br = BufferedReaderUtil.getBuffer(path+"/result.txt");
		String input = "";
		while ((input = br.readLine())!=null) {
			String com = input.split("\t")[0];
			crawleredSet.add(com);
		}
		br = BufferedReaderUtil.getBuffer(path+"/ent_all.txt","gbk");
		while ((input = br.readLine())!=null) {
			String com = input;
			crawlerSet.add(com);
		}
		crawlerSet.removeAll(crawleredSet);
		br.close();
		return crawlerSet;
	}

	/**
	 * 获得公司信息
	 * @param comUrl
	 * @return
	 * @throws IOException
	 */
	public static String getComInfo(String comUrl) {
		try {
			String result = "";
			CloseableHttpClient client = HttpClientBuilder.create().build();
			HttpGet get = new HttpGet(comUrl);
//			getInfoToken(comId);
			get.setHeader("Accept", "application/json, text/plain, */*");
			get.setHeader("Cookie",cookie);
			get.setHeader("User-Agent",userAgent);
			get.setHeader("Referer",comUrl);
			get.setHeader("Host","www.tianyancha.com");
			get.setHeader("Cache-Control","max-age=0");
			get.setHeader("Accept-Encoding","gzip, deflate, sdch");
			get.setHeader("Accept-Language","zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4");
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();//设置请求和传输超时时间
			get.setConfig(requestConfig);
			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity);
			client.close();
			Thread.sleep(interval);
			return result;
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return "error";
	}
	
	/**
	 * 获得公司url
	 * @param comName
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String getComUrl(String comName) {
		try {
			String prefix = "http://www.tianyancha.com/company/";
			String url = "";
			String absInfoUrl = "http://www.tianyancha.com/search?key="+URLEncoder.encode(comName, "utf-8")+"&checkFrom=searchBox";
			CloseableHttpClient client = HttpClientBuilder.create().build();
			HttpGet get = new HttpGet(absInfoUrl);
			get.setHeader("Accept", "application/json, text/plain, */*");
			get.setHeader("Cookie",cookie);
			get.setHeader("User-Agent",userAgent);
			get.setHeader("Referer","http://www.tianyancha.com/search?key="+URLEncoder.encode(comName,"utf-8")+"?checkFrom=searchBox");
			get.setHeader("Host","www.tianyancha.com");
			get.setHeader("Tyc-From","normal");
			get.setHeader("Connection","keep-alive");
			get.setHeader("CheckError","check");
			get.setHeader("Cache-Control","max-age=0");
			get.setHeader("Accept-Encoding","gzip, deflate, sdch");
			get.setHeader("Accept-Language","zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4");
			CloseableHttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			String res = EntityUtils.toString(entity,"utf-8");
			if (res.equals("")) {
				getStaticCookie();
				return "";
			}
			Elements comEle = Jsoup.parse(res).select("div.search_result_container div.search_repadding2 > a");
			String comUrl = comEle == null ? "" : comEle.get(0).attr("href");
			client.close();
			Thread.sleep(interval);
			return comUrl;
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return "error";
	}
	
	/**
	 * 获得token
	 * @param comName
	 * @throws UnsupportedEncodingException
	 */
	public static void getSearchToken(String comName) {
		try {
			reader = new StaticHttpReader();
			String tokenUrl = "http://www.tianyancha.com/tongji/"+URLEncoder.encode(comName, "utf-8")+".json?random=?"+System.currentTimeMillis();
			String searchSource = reader.readSource(tokenUrl, "utf-8", staticCookie);
			JSONObject obj = JSONObject.parseObject(searchSource);
			List<String> arr = JSONArray.parseArray(obj.getString("data"), String.class);
			char[] charr = new char[arr.size()];
			int j = 0;
			for (String string : arr) {
				int i = Integer.valueOf(string);
				char c = (char) i;
				charr[j] = c;
				j++;
			}
			String result = String.valueOf(charr);
			String pattern = "(?<=token=).*?(?=;)";
			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(result);
			String token = "";
			while (m.find()) {
				token = m.group();
			}
			cookie = staticCookie + token;
			Thread.sleep(interval);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		
	}
	/**
	 * 获得token
	 * @param comName
	 * @throws UnsupportedEncodingException
	 */
	public static void getInfoToken(String comId) {
		try {
			reader = new StaticHttpReader();
			String tokenUrl = "http://www.tianyancha.com/tongji/"+ comId +".json?random=?"+System.currentTimeMillis();
			String searchSource = reader.readSource(tokenUrl, "utf-8", staticCookie);
			JSONObject obj = JSONObject.parseObject(searchSource);
			String attStr = JSONObject.parseObject(obj.getString("data")).getString("v");
			String[] arr = attStr.split(",");
			char[] charr = new char[arr.length];
			int j = 0;
			for (String string : arr) {
				int i = Integer.valueOf(string);
				char c = (char) i;
				charr[j] = c;
				j++;
			}
			String result = String.valueOf(charr);
			String pattern = "(?<=token=).*?(?=;)";
			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(result);
			String token = "";
			while (m.find()) {
				token = m.group();
			}
			cookie = staticCookie + token;
			Thread.sleep(interval);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		
	}
	
}
