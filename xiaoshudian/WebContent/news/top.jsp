<%@page import="com.xiaoshudian.util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style type="text/css">
	*{font-family: 微软雅黑,Verdana,sans-serif,宋体;}
	body{ background-color:RGB(249,249,249); }
	#left{ width:750px;float:left; border:1px solid #999;background-color:RGB(255,255,255);}
	#right{ width:220px;float:right; border:1px solid #999;background-color:RGB(255,255,255);}
	.clear{clear:both;}
	#content{width:1000px;margin:20px auto 0 auto;}
	#banner{ width:100%;height:100px;margin:0 auto 0 auto; }
	.toolbar{ height:25px;font-size:10px;color:#999;line-height:25px;margin-top:3px;padding:10px 15px; }
	.left{ float:left; }
	.right{ float:right; }
	ul,ol{ list-style:none; }
	.blog{margin:0px 0px 30px 0px;border-bottom:1px solid #EEE;padding-bottom:5px;position:relative;}
	h2{font-size:15px;margin:0px 0px 5px 0px;display:block;font-weight:bold;}
	.outline{ color:#666;font-size:10px; }
	.blog_content{ margin:15px 0px 0px 0px;line-height:22px;overflow:hidden;color:#666;font-size:12px; }
	.blog_bottom{ font-size:10px;margin:20px 0px 0px 0px;color:#666;}
	.top_arcitle{}
	.top_arcitle ul{margin:10px 0px 5px 20px;padding:0px;}
	.top_arcitle ul li{ padding-top:5px;padding-bottom:2px; }
	.f12{ font-size:12px; }
	.ritem{ margin:10px 0px 10px 10px; }
	.split{ height:2px;width:90%;background-color:#666;margin:10px auto 10px auto; }
	.mr10{ margin-right:10px; }
	#bottom{width:1000px;height:70px;margin:40px auto 0px auto;font-size:12px;}
	.btmtips{ text-align:center; }
	.btmtips span{ margin-left:10px; }
	.logo{width:1000px;margin:0px auto 0px auto;height:60px;}
	 .logo .t1{ color:#FC911E;line-height:60px;font-size:32px; }
	 .a_btn{ color:#FC911E;width:60px;height:30px;font-size:14px; }
	 .search_text{ width:220px;height:20px; }
	 .func{ font-size:12px;margin-left:10px; }
	 .blog_content img {width: expression(this.width > 600 ? 600: true); max-width: 600px;} 
</style>
<%
	String count = request.getParameter("totalCount");
	long articleCount = StringUtils.String2Long(count);
%>
<div id="banner">
	<div class="logo">
		<div class="left">
			<span class="t1">小数点 </span>
			<span class="t2">行业信息交流</span>
		</div>
		<div class="right">
			<span class="func">登陆</span>
			<span class="func">注册</span>
			<span class="func">新闻投递</span><br/><br/>
			<input type="text" placeholder="在<%=articleCount%>篇文章中搜索" class="search_text"/>
			<input type="submit" value="搜索" class="a_btn"/>
		</div>
		<div class="clear"></div>
	</div>
</div>