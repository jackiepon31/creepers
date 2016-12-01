<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- Sidebar starts-->
<div class="sidebar">
	<div class="sidebar-dropdown">
		<a href="#">导航信息</a>
	</div>
	<!--- Sidebar navigation -->
	<!-- If the main navigation has sub navigation, then add the class "has_sub" to "li" of main navigation. -->
	<ul id="nav">
		<!-- Main menu with font awesome icon -->
		<li><a href="${ctx}/" class="open"><i class="icon-home"></i>首页</a>
		</li>
		<li class="has_sub"><a href="#"> <i class="icon-list-alt"></i>爬取任务管理
				<span class="pull-right"> <iclass="icon-chevron-right">
					</i></span>
		</a>
			<ul>
				<li><a href="${ctx}/creeperTask/toAddTask">新增爬取任务</a></li>
				<li><a href="${ctx}/job/init">爬虫批处理</a></li>
			</ul></li>
		<!-- 		<li class="has_sub"><a href="#"><i class="icon-list-alt"></i> -->
		<!-- 				个人征信报告管理 <span class="pull-right"><i -->
		<!-- 					class="icon-chevron-right"></i></span></a> -->
		<!-- 			<ul> -->
		<%-- 				<li><a href="${ctx}/reference/init">个人征信报告管理</a></li> --%>
		<!-- 			</ul> -->
		<!-- 		</li> -->
		<li class="has_sub"><a href="#"><i class="icon-list-alt"></i>
				法院公告管理 <span class="pull-right"><i class="icon-chevron-right"></i></span></a>
			<ul>
				<li><a href="${ctx}/courtAnnounce/init">法院公告管理</a></li>
			</ul></li>
		<li class="has_sub"><a href="#"><i class="icon-list-alt"></i>
				法院判决书管理 <span class="pull-right"><i
					class="icon-chevron-right"></i></span></a>
			<ul>
				<li><a href="${ctx}/judgement/init">法院判决书管理</a></li>
			</ul></li>
		<li class="has_sub"><a href="#"><i class="icon-list-alt"></i>
				专利管理 <span class="pull-right"><i class="icon-chevron-right"></i></span></a>
			<ul>
				<li><a href="${ctx}/patent/init">专利管理</a></li>
			</ul></li>
		<li class="has_sub"><a href="#"><i class="icon-list-alt"></i>
				商标管理 <span class="pull-right"><i class="icon-chevron-right"></i></span></a>
			<ul>
				<li><a href="${ctx}/tradeMark/init">商标管理</a></li>
			</ul></li>
		<li class="has_sub"><a href="#"><i class="icon-list-alt"></i>
				失信人管理 <span class="pull-right"><i class="icon-chevron-right"></i></span></a>
			<ul>
				<li><a href="${ctx}/shixin/init">失信人管理</a></li>
			</ul></li>
		<li class="has_sub"><a href="#"><i class="icon-list-alt"></i>
				社保管理 <span class="pull-right"><i class="icon-chevron-right"></i></span></a>
			<ul>
				<li><a href="${ctx}/insurance/init">社保管理</a></li>
			</ul></li>
		<li class="has_sub"><a href="#"><i class="icon-list-alt"></i>
				公积金管理 <span class="pull-right"><i class="icon-chevron-right"></i></span></a>
			<ul>
				<li><a href="${ctx}/fund/init">公积金管理</a></li>
			</ul></li>
		<li class="has_sub"><a href="#"><i class="icon-list-alt"></i>
				法院黑名单管理 <span class="pull-right"><i
					class="icon-chevron-right"></i></span></a>
			<ul>
				<li><a href="${ctx}/courtDishonesty/init">法院黑名单管理</a></li>
			</ul></li>
		<li class="has_sub"><a href="#"><i class="icon-list-alt"></i>
				限制发行企业债黑名单管理 <span class="pull-right"><i
					class="icon-chevron-right"></i></span></a>
			<ul>
				<li><a href="${ctx}/courtCorpBonds/init">限制发行企业债黑名单管理</a></li>
			</ul></li>
		<li class="has_sub"><a href="#"><i class="icon-list-alt"></i>
				导游证管理<span class="pull-right"><i class="icon-chevron-right"></i></span></a>
			<ul>
				<li><a href="${ctx}/touristGuide/init">导游证管理</a></li>
			</ul></li>
		<li class="has_sub"><a href="#"><i class="icon-list-alt"></i>
				导游旅行社黑名单管理<span class="pull-right"><i class="icon-chevron-right"></i></span></a>
			<ul>
				<li><a href="${ctx}/touristBlackList/init">导游旅行社黑名单管理</a></li>
			</ul></li>
		<li class="has_sub"><a href="#"><i class="icon-list-alt"></i>
				爬虫配置中心<span class="pull-right"><i class="icon-chevron-right"></i></span></a>
			<ul>
				<li><a href="${ctx}/configCenter/init">配置管理</a></li>
				<li><a href="${ctx}/configCenter/doModify">配置新增</a></li>
			</ul></li>
		<!--
          <li class="has_sub"><a href="#"><i class="icon-list-alt"></i> 插件页面  <span class="pull-right"><i class="icon-chevron-right"></i></span></a>
            <ul>
              <li><a href="widgets1.jsp">插件页面 #1</a></li>
              <li><a href="widgets2.jsp">插件页面 #2</a></li>
              <li><a href="widgets3.jsp">插件页面 #3</a></li>
            </ul>
          </li>  
          <li class="has_sub"><a href="#"><i class="icon-list-alt"></i> Mongo测试  <span class="pull-right"><i class="icon-chevron-right"></i></span></a>
            <ul>
              <li><a href="${ctx}/mongo/insert">Mongo-Insert</a></li>
              <li><a href="${ctx}/mongo/delete">Mongo-Delete</a></li>
              <li><a href="${ctx}/mongo/query">Mongo-Query</a></li>
              <li><a href="${ctx}/mongo/update">Mongo-Update</a></li>
            </ul>
          </li> 
          <li class="has_sub"><a href="#"><i class="icon-file-alt"></i> 页面模块1 <span class="pull-right"><i class="icon-chevron-right"></i></span></a>
            <ul>
              <li><a href="post.jsp">表单Post</a></li>
              <li><a href="login.jsp">登录页</a></li>
              <li><a href="register.jsp">注册页面</a></li>
              <li><a href="support.jsp">帮助页</a></li>
              <li><a href="invoice.jsp">购物清单</a></li>
              <li><a href="profile.jsp">个人资料</a></li>
              <li><a href="gallery.jsp">相册页面</a></li>
            </ul>
          </li> 
          <li class="has_sub"><a href="#"><i class="icon-file-alt"></i> 页面模块2  <span class="pull-right"><i class="icon-chevron-right"></i></span></a>
            <ul>
              <li><a href="media.jsp">媒体</a></li>
              <li><a href="statement.jsp">描述</a></li>
              <li><a href="error.jsp">错误</a></li>
              <li><a href="error-log.jsp">错误日志</a></li>
              <li><a href="calendar.jsp">日历</a></li>
              <li><a href="grid.jsp">网格</a></li>
            </ul>
          </li>                             
          <li><a href="charts.jsp"><i class="icon-bar-chart"></i>图表</a></li> 
          <li><a href="tables.jsp"><i class="icon-table"></i>表格</a></li>
          <li><a href="forms.jsp"><i class="icon-tasks"></i>表单</a></li>
          <li><a href="ui.jsp"><i class="icon-magic"></i>UI图标</a></li>
          <li><a href="calendar.jsp"><i class="icon-calendar"></i>日历</a></li>
        </ul>
         -->
</div>

<!-- Sidebar ends -->