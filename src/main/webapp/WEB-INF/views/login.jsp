<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <!-- Title and other stuffs -->
 <title>[网络爬虫管理系统]-量富证信管理有限公司</title> 
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="author" content="">
  <script src="${ctx}/static/js/jquery-1.9.1.min.js"></script> <!-- jQuery -->
  <link href="${ctx}/static/style/bootstrap.css" rel="stylesheet">
  <link href="${ctx}/static/style/font-awesome.css" rel="stylesheet" >
  <link href="${ctx}/static/style/style.css" rel="stylesheet">
  
  <!-- HTML5 Support for IE -->
  <!--[if lt IE 9]>
  <script src="${ctx}/static/js/html5shim.js"></script>
  <![endif]-->

  <!-- Favicon -->
  <link rel="shortcut icon" href="${ctx}/static/img/favicon/favicon.png">
</head>
<body>
<!-- Form area -->
<div class="admin-form">
  <div class="container">

    <div class="row">
      <div class="col-md-12">
        <!-- Widget starts -->
            <div class="widget worange">
              <!-- Widget head -->
              <div class="widget-head">
                <i class="icon-lock"></i>量富征信统一网络爬虫管理系统登录
              </div>

              <div class="widget-content">
                <div class="padd">
                  <!-- Login form -->
                  <form id="loginForm" action="${ctx}/login" method="post" class="form-horizontal">
				    <%
				    String error = (String)request.getAttribute(org.apache.shiro.web.filter.authc.FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		            boolean flag = false;
		            String msg = "";                   
		            if( error != null ){
		                if( "org.apache.shiro.authc.UnknownAccountException".equals(error)){
		                    msg = "未知帐号错误！";
		                }else if("org.apache.shiro.authc.IncorrectCredentialsException".equals(error)){
		                    msg = "密码错误！";                   
		                }else if("org.apache.shiro.authc.LockedAccountException".equals(error)){  
		                    msg = "账号锁定！";
		                }else if( "org.apache.shiro.authc.AuthenticationException".equals(error)){
		                    msg = "认证失败！";
		                }else{
		                    msg = "登录失败，请重试!";
		                }
		            }           
					if(error != null){
					%>
					<!--
					<div id="messageModal" class="alert alert-warning" >
						<button class="close" data-dismiss="alert">×</button>登录失败，请重试!
					</div>
					-->
					 <script>
			         $.alert({
							title: '提示信息',
						    content:  '<%=msg %>',
						    confirmButton: '确认',
						    autoClose: 'confirm|10000'
				     });
			         </script>
					
					<%
					}
					%>
                    <!-- Username -->
                    <div class="form-group">
                      <label class="control-label col-lg-3" for="inputEmail">用户</label>
                      <div class="col-lg-9">
                        <input type="text" id="username" name="username" class="form-control required"/>
                      </div>
                    </div>
                    <!-- Password -->
                    <div class="form-group">
                      <label class="control-label col-lg-3" for="inputPassword">密码</label>
                      <div class="col-lg-9">
                        <input type="password" id="password" name="password" class="form-control required"/>
                      </div>
                    </div>
                    <!-- Remember Me-->
                     <div class="form-group">
						<div class="col-md-8 col-md-offset-2">
	                      <div class="checkbox">
	                          <label for="rememberMe"/><input type="checkbox" id="rememberMe" name="rememberMe"/>记住我</label>
						  </div>
						</div>
					</div>
	                <div class="col-lg-9 col-lg-offset-2">
						<input id="submit_btn" class="btn btn-primary btn-lg" type="submit" value="登录"/>
						<input id="reset_btn" class="btn btn-default btn-lg" type="reset" value="重置"/>
					</div>
                    <br />
                  </form>
				</div>
                </div>
                <div class="widget-foot">
                  
                </div>
            </div>  
      </div>
    </div>
  </div> 
</div>

<!-- Modal Message -->
<div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">提示信息</h4>
      </div>
      <div class="modal-body">
        <%=msg %>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
</div>	
		

<!-- JS -->
<script src="${ctx}/static/js/bootstrap.js"></script>
</script>
</body>
</html>