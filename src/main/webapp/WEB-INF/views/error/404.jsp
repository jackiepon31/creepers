<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="Cache-Control" content="no-store" />
  <meta http-equiv="Pragma" content="no-cache" />
  <meta http-equiv="Expires" content="0" />
  <!-- Title and other stuffs -->
  <title>404错误-量富证信管理有限公司</title> 
  <meta name="keywords" content="" />
  <meta name="description" content="" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="author" content="">
  <script src="${ctx}/static/js/jquery-1.9.1.min.js"></script> <!-- jQuery -->
  <!-- Stylesheets -->
  <link rel="stylesheet" href="${ctx}/static/style/bootstrap.css" >
  <!-- Font awesome icon -->
  <link rel="stylesheet" href="${ctx}/static/style/font-awesome.css"> 
  <!-- jQuery UI -->
  <link rel="stylesheet" href="${ctx}/static/style/jquery-ui.css"> 
  <!-- Calendar -->
  <link rel="stylesheet" href="${ctx}/static/style/fullcalendar.css">
  <!-- prettyPhoto -->
  <link rel="stylesheet" href="${ctx}/static/style/prettyPhoto.css">  
  <!-- Star rating -->
  <link rel="stylesheet" href="${ctx}/static/style/rateit.css">
  <!-- Date picker -->
  <link rel="stylesheet" href="${ctx}/static/style/bootstrap-datetimepicker.min.css">
  <!-- CLEditor -->
  <link rel="stylesheet" href="${ctx}/static/style/jquery.cleditor.css"> 
  <!-- Bootstrap toggle -->
  <link rel="stylesheet" href="${ctx}/static/style/bootstrap-switch.css">
  <!-- Main stylesheet -->
  <link rel="stylesheet" href="${ctx}/static/style/style.css" >
  <!-- Widgets stylesheet -->
  <link rel="stylesheet" href="${ctx}/static/style/widgets.css" >
  <!-- Validate stylesheet -->
  <link rel="stylesheet" href="${ctx}/static/js/jquery-validation/1.11.1/validate.css" />
  
  <!-- HTML5 Support for IE -->
  <!--[if lt IE 9]>
  <script src="${ctx}/static/js/html5shim.js"></script>
  <![endif]-->
</head>

<body>

<!-- Form area -->
<div class="error-page">
  <div class="container">

    <div class="row">
      <div class="col-md-12">
        <!-- Widget starts -->
            <div class="widget">
              <!-- Widget head -->
              <div class="widget-head">
                <i class="icon-question-sign"></i> Error-404 
              </div>

              <div class="widget-content">
                <div class="padd error">
                  
                  <h1>Opps!!! It's 404</h1>
                  <p>404 - 页面不存在！</p>
                  <br />
                  <form class="form-inline">
				      <div class="form-group">
				      </div>
                      <input id="cancel_btn" class="btn btn-primary btn-lg"  type="button"  value="返回" onclick="history.back()"/>
                  </form>
                 <br />
                </div>
                <div class="widget-foot">
                  <!-- Footer goes here -->
                </div>
              </div>
            </div>  
      </div>
    </div>
  </div> 
</div>
	
		

<!-- JS -->
<script src="${ctx}/static/js/jquery.js"></script>
<script src="${ctx}/static/js/bootstrap.js"></script>
</body>
</html>