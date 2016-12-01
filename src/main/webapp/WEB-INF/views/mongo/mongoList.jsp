<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!-- Main content starts -->

<div class="content">

  
  	<!-- Main bar -->
  	<div class="mainbar">

      <!-- Page heading -->
      <div class="page-head">
        <h2 class="pull-left"><i class="icon-table"></i> Mong List</h2>

        <!-- Breadcrumb -->
        <!-- 
        <div class="bread-crumb pull-right">
          <a href="/index"><i class="icon-home"></i></a> 
          <span class="divider">/</span> 
          <a href="#" class="bread-current"></a>
        </div>
        -->

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
                  <div class="pull-left">用户查询</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>
                  <div class="clearfix"></div>
                </div>

                <div class="widget-content">
                  <div class="padd">
                    <!-- Form starts.  -->
                     <form class="form-horizontal form-search" id="creditCustomerForm" action="${ctx}/mongo/${action}"  method="get" role="form">
                         <div class="form-group">
                           <div class="col-md-12">
                           </div>
                         </div>
                         <div class="form-group">
                           <label class="col-md-2 control-label">客户名称</label>
                           <div class="col-md-3">
                             <input type="text" name="userName" class="form-control" value="${param.userName}"> 
                           </div>
                           <label class="col-md-2 control-label">性别</label>
                           <div class="col-md-3">
                             <input type="text" name="sex" class="form-control" value="${param.sex}"> 
                           </div>
                         </div>
                         <div class="form-group">
                           <div class="col-md-offset-2 col-md-8">
                             <button type="submit" class="btn btn-primary"  id="search_btn">查询</button>
                             <button type="reset" class="btn btn-default">重置</button>
                           </div>
                         </div>
           			</form>
                  </div>
                </div>
                <div class="widget-foot">
			    </div>
              </div>  
            </div>
           
           
      </div>
		<!-- Matter ends -->
    </div>
   <!-- Mainbar ends -->
   <div class="container">

          <!-- Table -->
		            <div class="row">
              <div class="col-md-12">
                <div class="widget">
                <div class="widget-head">
                  <div class="pull-left">用户列表</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>
                  <div class="widget-content">
                    <table class="table table-striped table-bordered table-hover">
                      <thead>
                        <tr>
                          <th>名称</th>
                          <th>性别</th>
                          <th>电子邮箱</th>
                          <th>生日</th>
                          <th>操作</th>
                        </tr>
                      </thead>
                      <tbody>
					<c:forEach items="${users}" var="user">	
					<tr>
                          <td>${user.userName}</td>
                          <td>${user.sex}</td>
                          <td>${user.email}</td>
                          <td><fmt:formatDate  value="${user.birthday}"  pattern="yyyy-MM-dd" /></td>
                          <td>
                             <%--  <button class="btn btn-xs btn-warning" onclick="javascript:window.location.href='${ctx}/creditCustomer/update/${user.Id}'"><i class="icon-pencil"></i></button> --%>
                          </td>
                        </tr>
					</c:forEach>
                      </tbody>
                    </table>
                     <%--    <div class="widget-foot">
                        <tags:pagination page="${customers}" paginationSize="5"/>
                      <div class="clearfix"></div>  --%>
                    </div>
                     <div class="widget-foot">
			    	 </div> 
                      <div class="clearfix"></div> 
                  </div>
                </div>
              </div>
            </div>
           
   <div class="clearfix"></div>
</div>
</script>