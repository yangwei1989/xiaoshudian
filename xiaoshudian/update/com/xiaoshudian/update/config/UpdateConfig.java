package com.xiaoshudian.update.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * 配置文件
 * @author zhangya
 */
public class UpdateConfig {
	
	private static String config_file;
	private final List<String> FILES = new ArrayList<String>();
	private static Map<String, String> params = new ConcurrentHashMap<String,String>();
	
	public UpdateConfig(String filePath){
		config_file = filePath;
		//读取配置文件
		InputStream ins = this.getClass().getResourceAsStream(config_file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
		try{
			String line = "";
			while( (line = reader.readLine()) != null ){
				if( line.startsWith("#") ){
					continue;
				}
				FILES.add(line);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if( reader != null ){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void next(String filePath){
		params.clear();
		InputStream ins = UpdateConfig.class.getResourceAsStream(filePath);
		BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
		try{
			String line = "";
			while( (line = reader.readLine()) != null ){
				if( line.startsWith("#") ){
					continue;
				}
				String[] pair = line.split("@");
				if( pair.length == 2){
					params.put(pair[0], pair[1]);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if( reader != null ){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public String get(String key){
		return params.get(key);
	}
	
	public boolean contains(String key){
		return params.containsKey(key);
	}

	public List<String> getFiles() {
		return FILES;
	}
	
}
