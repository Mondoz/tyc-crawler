package com.hiekn.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * 配置信息
 * 
 * @author pzn
 * @version 1.0
 * @since 1.7
 */
public class ConstResource {

    static Properties props = new Properties();

    static {
    	 FileInputStream in = null;
         try {
         	String path = System.getProperty("user.dir")+"\\crawler.properties"; //windows
//         	String path = System.getProperty("user.dir")+"/crawler.properties";  //linux
             in = new FileInputStream(path);
             props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    // 操作系统换行符
    public static final String FIREFOX_PATH = get("firefox_path");
    public static final String MONGO_URL = "192.168.1.31";
    public static final int MONGO_PORT = 27017;
    
    // get property
    public static String get(String key) { return props.getProperty(key); }

}
