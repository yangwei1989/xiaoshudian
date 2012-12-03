<%@page import="com.sina.sae.kvdb.SaeKVUtil"%>
<%@page import="com.xiaoshudian.data.cache.KVCacheProvider"%>
<%@page import="com.xiaoshudian.data.cache.Cache"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>小数点 点击量统计</title>
</head>
<body>
	<%
		KVCacheProvider cache = KVCacheProvider.getInstance();
		Map<String,Object> map = cache.getByPrefix("click", 100,false, "click_");
		for( String key : map.keySet() ){
			out.println("key=" + key +  ",ser=" + (cache.get(key)));
		}
		out.flush();
	%>
</body>
</html>