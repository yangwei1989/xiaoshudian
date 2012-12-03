<%@page import="java.util.List"%>
<%@page import="com.xiaoshudian.util.StringUtils"%>
<%@page import="com.xiaoshudian.data.domain.ITArticle"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String totalCount = request.getParameter("totalCount");
	long totalNum = 0L;
	ITArticle article = new ITArticle();
	if( totalCount == null || totalCount.isEmpty() ){
		totalNum = article.totalCount();
	}else{
		totalNum = StringUtils.String2Long(totalCount);
	}
	List<ITArticle> articles1 = (List<ITArticle>)article.specifyByPage("id,title", " where flag = 0 order by posttime desc ", 1, 5);
	List<ITArticle> articles2 = (List<ITArticle>)article.specifyByPage("id,title", " where flag = 0 order by posttime desc ", 2, 5);
	List<ITArticle> articles3 = (List<ITArticle>)article.specifyByPage("id,title", " where flag = 0 order by posttime desc ", 3, 5);
%>
	<div id="right">
			<div class="top_arcitle">
				<div class="ritem">
					<h2>最新文章:</h2>
				</div>
				<ul class="f12">
					<% for( ITArticle thisArticle : articles1 ){ %>
						<li><a href="itdetail.jsp?id=<%=thisArticle.getId()%>"><%=thisArticle.getTitle() %></a></li>
					<%}%>
				</ul>
				<div class="split"></div>
			</div>	
			<div class="top_arcitle">
				<div class="ritem">
					<h2>人气文章:</h2>
				</div>
				<ul class="f12">
					<% for( ITArticle thisArticle : articles2 ){ %>
						<li><a href="itdetail.jsp?id=<%=thisArticle.getId()%>"><%=thisArticle.getTitle() %></a></li>
					<%}%>
				</ul>
				<div class="split"></div>
			</div>
			<div class="top_arcitle">
				<div class="ritem">
					<h2>随便看看:</h2>
				</div>
				<ul class="f12">
					<% for( ITArticle thisArticle : articles3 ){ %>
						<li><a href="itdetail.jsp?id=<%=thisArticle.getId()%>"><%=thisArticle.getTitle() %></a></li>
					<%}%>
				</ul>
				<div class="split"></div>
			</div>							
		</div>