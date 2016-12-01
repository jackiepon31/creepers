<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="navbar navbar-fixed-top bs-docs-nav" role="banner">
    <div class="conjtainer">
      <nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">         
        <ul class="nav navbar-nav">  
          <li class="dropdown dropdown-big">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="label label-success"><i class="icon-cloud-upload"></i></span> 银行集团旗下信息管理系统</a>
          </li>
        </ul>
		<shiro:authenticated>
        <ul class="nav navbar-nav pull-right">
          <li class="dropdown pull-right">            
            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
              <i class="icon-user"></i> <shiro:principal/> <b class="caret"></b>              
            </a>
            <ul class="dropdown-menu">
              <li><a href="${ctx}/logout"><i class="icon-off"></i> 退出</a></li>
            </ul>
          </li>
        </ul>
        </shiro:authenticated>
      </nav>
    </div>
  </div>
   <header> <div></div></header>
  
<!-- Header starts -->
  <header>
    <div class="container">
      <div class="row">
        <div class="col-md-6">
          <div class="logo">
            <h1><a href="#">量富征信网络爬虫管理系统<span class="bold"></span></a></h1>
          </div>
        </div>
      </div>
    </div>
  </header>
<!-- Header ends -->