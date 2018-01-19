package com.hiekn.tianyancha;

import java.io.BufferedReader;
import java.io.FileWriter;

import com.alibaba.fastjson.JSONObject;
import com.hiekn.util.BufferedReaderUtil;

public class ExtractCom {
	public static void main(String[] args) {
		try {
			extract();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void extract() throws Exception {
		String input = "";
		BufferedReader br = BufferedReaderUtil.getBuffer("data/kc/test.txt");
		while ((input = br.readLine()) != null) {
			try {
				String[] split = input.split("\t");
				String resultJson = split[1];
				String originName = split[0];
				if (!resultJson.equals("无数据")) {
					JSONObject resultObj = JSONObject.parseObject(resultJson);
					String comName = JSONObject.parseObject(
							JSONObject.parseObject(resultObj.getString("data"))
									.getString("baseInfo")).getString("name");
					String originCompareName = originName.replace("（", "(").replace("）", ")").trim();
					String comCompareName = comName.replace("（", "(").replace("）", ")").trim();
					if (originCompareName.equals(comCompareName)) {
						System.out.println("safsa");
					} 
				} 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		br.close();
	}
}
