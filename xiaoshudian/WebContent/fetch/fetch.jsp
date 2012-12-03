<%@page import="com.xiaoshudian.update.main.Main"%>
<%@page import="com.xiaoshudian.util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>抓取数据</title>
</head>
<%
	String key = request.getParameter("key");
	if( key == null || key.isEmpty() || !"fetch".equals(key)){
		response.sendRedirect("../news/it.jsp");
		return ;
	}
	String magic = request.getParameter("magic");
	if( magic == null || magic.isEmpty() ){
		response.sendRedirect("../news/it.jsp");
		return ;
	}
	int magicNum = StringUtils.String2Int(magic);
	if( magicNum % 121 == 1 ){
		Main.fetchSummary();
		Main.fetchArticle();
	}
%>
</html>