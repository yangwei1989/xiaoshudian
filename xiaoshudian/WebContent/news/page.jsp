<%@page import="com.xiaoshudian.util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String url = request.getParameter("url");
	String minPage = request.getParameter("minPage");
	int minP = StringUtils.String2Int(minPage);
	String maxPage = request.getParameter("maxPage");
	int maxP = StringUtils.String2Int(maxPage);
	String curPage = request.getParameter("curPage");
	int curP = StringUtils.String2Int(curPage);
	if( curP > maxP ){
		curP = maxP;
	}
	if( curP < minP ){
		curP = minP;
	}
	int begin = (curP - 4);
	int end = (curP + 4);
	if( begin < minP ){
		begin = minP;
		end = begin + 8;
	}
	if( end > maxP ){
		end = maxP;
		begin = maxP - 4;
	}
	end = (end > maxP) ? maxP : end;
	begin = (begin < minP) ? minP : begin;
	System.out.printf("begin=%d,end=%d\n",begin,end);
%>
	<style type="text/css">
		.pageContainer{width:1000px;margin:0px auto 0px auto;}
		.spage{ width:750px;margin-top:10px;text-align:center; }
		.spage a{ text-decoration: none;color:RGB(153,153,153); }
		.spage span{ display:inline-block;margin-left:5px;margin-right:5px;background-color:RGB(255,255,255);width:45px;border:1px solid RGB(153,153,153); }
		.spage .on{background-color:RGB(88,89,91);}
	</style>
	<div class="pageContainer">
		<div class="spage">
			<%if( curP > minP ){ %>
				<a href="<%=url%>?p=<%=curP-1%>"><span>&lt;</span></a>
			<%}%>
			<%for( int i = begin ; i <= end;i++ ){ 
				if( i == curP ){
				%>
					<span class="on"><%=i%></span>
				<%
				}else{
				%>
					<a href="<%=url%>?p=<%=i%>"><span><%=i%></span></a>
				<%}
			%>
				
			<%}%>
			<%if( curP < maxP ){ %>
				<a href="<%=url%>?p=<%=curP + 1%>"><span>&gt;</span></a>
			<%}%>
		</div>
	</div>