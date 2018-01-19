package com.hiekn.tianyancha;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class T1 {
	
	@Test
	public void t8() {
	    String str1 = "a";
	    String str2 = "b";
	    String str3 = "ab";
	    String str4 = str1 + str2;
	    String str6 = str3;
	    String str7 = "a"+ str2;
	    String str5 = new String("ab");

	    System.out.println(str3 == str7);
	    System.out.println(str5.intern() == str3);
	    System.out.println(str5.intern() == str4);
	    System.out.println(str5.intern() == str6);
	    System.out.println(str5.intern() == str7);
	}
	
	@Test
	public void t7() {
		String s = "33,102,117,110,99,116,105,111,110,40,110,41,123,100,111,99,117,109,101,110,116,46,99,111,111,107,105,101,61,39,116,111,107,101,110,61,54,53,54,54,50,51,49,56,51,101,56,54,52,54,100,101,97,97,49,54,51,55,97,57,99,51,56,99,102,99,98,55,59,112,97,116,104,61,47,59,39,59,110,46,119,116,102,61,102,117,110,99,116,105,111,110,40,41,123,114,101,116,117,114,110,39,51,49,44,50,54,44,49,57,44,48,44,49,57,44,50,53,44,50,53,44,49,51,44,50,53,44,53,44,52,44,50,57,44,50,56,44,51,50,44,51,54,44,50,54,44,50,50,44,50,50,44,50,54,44,52,44,48,44,51,54,44,51,51,44,48,44,50,53,44,49,51,44,52,44,50,56,44,51,49,44,50,57,44,48,44,51,50,39,125,125,40,119,105,110,100,111,119,41,59";
		String[] arr = s.split(",");
		char[] charr = new char[arr.length];
		int j = 0;
		for (String string : arr) {
			int i = Integer.valueOf(string);
			char c = (char) i;
			charr[j] = c;
			j++;
		}
		String result = String.valueOf(charr);
		System.out.println(result);
	}
	
	@Test
	public void t6() {
		JSONObject obj = new JSONObject();
		obj.put("1","sdfgs");
		obj.put("2","sdfg3");
		System.out.println(obj.toJSONString());
		obj.put("1", "we3");
		obj.put("2","wer");
		System.out.println(obj.toJSONString());
	}
	
	@Test
	public void t5() {
		String s = "久钰车料（昆山）有(限)公司";
		String com = s.replace("（", "(").replace("）", ")");
		System.out.println(s);
		System.out.println(com);
	}
	
	@Test
	public void t4() {
		Date date = new Date();
		System.out.println(date.getTime());
		SimpleDateFormat DAY_FORMATE = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(DAY_FORMATE.format(new Date()));
	}
	
	@Test 
	public void t3() {
		String md5 = DigestUtils.md5Hex(new Date() + "");
		System.out.println(md5);
	}
	
	@Test
	public void t2() {
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			System.out.print(random.nextInt(255) +"\t");
		}
	}
	
	@Test
	public void t1() {
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		try {
			client = HttpClientBuilder.create().build();
			HttpClientContext context = HttpClientContext.create();
			HttpGet get = new HttpGet("http://www.tianyancha.com/");
			response = client.execute(get,context);
			CookieStore cookieStore = (CookieStore) context.getCookieStore();
			List<Cookie> cookies = cookieStore.getCookies();
			for (Cookie cookie : cookies) {
				System.out.println(cookie.getName() + "\t" + cookie.getValue());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			try {
				client.close();
				response.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
