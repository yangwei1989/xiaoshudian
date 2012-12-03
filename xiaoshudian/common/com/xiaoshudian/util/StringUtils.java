package com.xiaoshudian.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public final class StringUtils {
	
	public static boolean isBlank(String str){
		return str == null || str.isEmpty() ;
	}
	
	public static boolean isNotBlank(String str){
		return !isBlank(str);
	}
	
	public static int String2Int(String str){
		return String2Int(str,0);
	}
	
	public static int String2Int(String str,int def){
		try{
			return Integer.parseInt(str);
		}catch (Exception e) {
			//e.printStackTrace();
			return def;
		}
	}
	
	public static long String2Long(String str){
		return String2Long(str,0L);
	}
	
	public static long String2Long(String str,long def){
		try{
			return Long.parseLong(str);
		}catch (Exception e) {
			e.printStackTrace();
			return def;
		}
	}
	
	public static double String2Double(String str){
		return String2Double(str,0.0d);
	}
	
	public static double String2Double(String str,double def){
		try{
			return Double.parseDouble(str);
		}catch (Exception e) {
			e.printStackTrace();
			return def;
		}
	}
	
	public static String extraHtmlTag(String htmlSegment){
		Document doc = Jsoup.parse(htmlSegment);
		return doc.text();
	}
	
	public static String chopString(String str,int len){
		if( str.length() <= len ){
			return str;
		}
		return str.substring(len) + "...";
	}
	
}
