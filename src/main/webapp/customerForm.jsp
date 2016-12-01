<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>任务管理</title>
</head>

<body>
<!-- Main content starts -->

<div class="content">

  
  	<!-- Main bar -->
  	<div class="mainbar">

      <!-- Page heading -->
      <div class="page-head">
        <h2 class="pull-left"><i class="icon-table"></i> 客户管理</h2>

        <!-- Breadcrumb -->
        <div class="bread-crumb pull-right">
          <a href="/index"><i class="icon-home"></i> Home</a> 
          <!-- Divider -->
          <span class="divider">/</span> 
          <a href="#" class="bread-current">Dashboard</a>
        </div>

        <div class="clearfix"></div>

      </div>
      <!-- Page heading ends -->

	  	<!-- Matter -->
	    <div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left">客户录入</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>
                  <div class="clearfix"></div>
                </div>

                <div class="widget-content">
                  <div class="padd">
                    <!-- Form starts.  -->
                     <form  id="customerForm" action="${ctx}/customer/${action}" method="post" class="form-horizontal" >
                     <input type="hidden" name="id" value="${customer.id}"/>
                     <input type="text" name="id" value="${ctx}/customer/${action}"/>
                                <div class="form-group">
                                  <label class="col-lg-4 control-label">客户名称</label>
                                  <div class="col-lg-8">
                                    <input type="text"  name="customerName"  value="${customer.customerName}"  class="form-control" placeholder="" check-type="required">
                                  </div>
                                </div>
                                <div class="form-group">
                                  <label class="col-lg-4 control-label">电话号码</label>
                                  <div class="col-lg-8">
                                    <input type="text"  name="telephone"  value="${customer.telphone}"  class="form-control" placeholder="">
                                  </div>
                                </div>
                                <div class="form-group">
                                  <label class="col-lg-4 control-label">手机号码</label>
                                  <div class="col-lg-8">
                                    <input type="text"  name="mobile"  value="${customer.mobile}"  class="form-control" placeholder="">
                                  </div>
                                </div>
                                <div class="form-group">
                                  <label class="col-lg-4 control-label">客户地址</label>
                                  <div class="col-lg-8">
                                    <input type="text"  name="address"  value="${customer.address}"  class="form-control" placeholder="">
                                  </div>
                                </div>
                                <div  class="form-group">
                                  <label class="col-lg-4 control-label">生效日期</label>
                                  <div class="col-lg-8">
                                     <input type="text"  name="start_dt"  value="${customer.startDt}"  class="form-control" placeholder=""  check-type="required">
                                  </div>
                               </div>
                               <div  class="form-group">
                                  <label class="col-lg-4 control-label">结束日期</label>
                                  <div class="col-lg-8">
                                     <input type="text"  name="end_dt"  value="${customer.endDt}"  class="form-control" placeholder=""  check-type="required">
                                  </div>
                               </div>
                                <div class="form-group">
                                  <label class="col-lg-4 control-label">备注信息</label>
                                  <div class="col-lg-8">
                                    <textarea class="form-control"  name="memo"  value="${customer.memo}"  rows="3" placeholder=""></textarea>
                                  </div>
                                </div>    
                                <div class="form-group">
                                  <div class="col-lg-offset-1 col-lg-9">
                                    <input id="submit_btn" class="btn btn-primary  btn-lg" type="submit" value="提交"/>&nbsp;	
									<input id="cancel_btn" class="btn btn-default btn-lg " type="button" value="返回" onclick="history.back()"/>
                                  </div>
                                </div>
                              </form>
                  </div>
                </div>
              </div>  
            </div>
      </div>
		<!-- Matter ends -->
    </div>
   <!-- Mainbar ends -->	    	
   <div class="clearfix"></div>
</div>
<!-- Content ends -->
<script>
<script type="text/javascript">
$(function(){
   $("#customerForm").validation({icon:true});
})
</script>
</body>
</html>