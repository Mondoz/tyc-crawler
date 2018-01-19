package com.hiekn.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class BufferedReaderUtil {
	public static BufferedReader getBuffer(String filePath) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return br;
	}
	
	
	public static BufferedReader getBuffer(String filePath,String charSet) {
		BufferedReader br = null;
		try {
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),charSet));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return br;
	}
}
