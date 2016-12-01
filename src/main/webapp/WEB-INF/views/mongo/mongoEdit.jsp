<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!-- Main content starts -->

<div class="content">
  	<!-- Main bar -->
  	<div class="mainbar">

      <!-- Page heading -->
      <div class="page-head">
        <h2 class="pull-left"><i class="icon-table"></i>MongoInsert</h2>
        <!-- Breadcrumb -->
       <!--
        <div class="bread-crumb pull-right">
          <a href="/index"><i class="icon-home"></i> Home</a> 
          <span class="divider">/</span> 
          <a href="#" class="bread-current">Dashboard</a>
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
                  <div class="pull-left">Mongo Insert</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>
                  <div class="clearfix"></div>
                </div>

                <div class="widget-content">
                  <div class="padd">
                    <!-- Form starts.  -->
                     <form id="modelForm" action="${ctx}/mongo/${action}" method="post" class="form-horizontal">
                     <input type="hidden" name="id" value="${user.id}"/>
                     <input type="hidden" name="respondentCode" value="${user.respondentCode}"/>
                     <input type="hidden" name="orderCode" value="${user.orderCode}"/>
                     <input type="hidden" name="mainCode" value="${user.mainCode}"/>
                           <div class="form-group">
                                  <div class="col-md-12">
                                  </div>
                            </div>
                            <div class="form-group">
                               <label class="col-md-2 control-label">Username:</label>
                                  <div class="col-md-3">
                                    <input type="text"  id="userName"  name="userName"  value="${user.userName}"  class="form-control" placeholder="" >
                                  </div>
                              <label class="col-md-2 control-label">email:</label>
                                 <div class="col-md-3">
                                    <input type="text"  id="email"  name="email"  value="${user.email}"  class="form-control" placeholder="" >
                                 </div>
                            </div>
                            
                            
                            <div class="form-group">
                                <label class="col-md-2 control-label">sex:</label>
                                  <div class="col-md-3">
                                    <input type="text"  id="sex"  name="sex"  value="${user.sex}"  class="form-control" placeholder="">
                                  </div>
                                <label class="col-md-2 control-label">birthday:</label>
                                  <div class="col-md-3">
 									<input type="text" id="bithday" name="bithday" 
														value="<fmt:formatDate value='${user.birthday}' type='date' pattern="yyyy-MM-dd"/>"
														class="form-control dateTimePicker" placeholder=""/>	                                
                                  </div> 
                             </div>
                          <div class="form-group">
                              <div class="col-md-offset-2 col-md-8">
                               <input id="submit_btn" class="btn btn-primary  btn-md" type="submit"  value="提交"/>&nbsp;	
						       <input id="cancel_btn" class="btn btn-default btn-md"   type="reset" value="重置"/>
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
      </div>
      </div>
		<!-- Matter ends -->
    </div>
   <!-- Mainbar ends -->	  
   <div class="clearfix"></div>
</div>
<!-- Content ends -->
<script type="text/javascript">
$().ready(function() {
	$("#modelForm").validate({
		rules: {
			suppleyName:{
				required:true,
				stringMaxLength:50
			},
			suppleyThing:{
				required:true,
				stringMaxLength:50
			},
			intervieweeTitle:{
				required:true,
				stringMaxLength:50
			},
			intervieweeTel:{
				required:true,
				number:true
			}, 
			
			intervieweeEvaluate:{
				required:true,
				stringMaxLength:50
			},
			reviewDt:{
				required:true,
				dateISO:true
			}
		},
		submitHandler : function(form) {  
            form.submit();  
        }  
	});
});
</script>
