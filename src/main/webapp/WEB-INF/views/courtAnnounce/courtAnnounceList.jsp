<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- Main content starts -->

<div class="content">


	<!-- Main bar -->
	<div class="mainbar">

		<!-- Page heading -->
		<div class="page-head">
			<h2 class="pull-left">
				<i class="icon-table"></i> 法院公告信息管理
			</h2>

			<!-- Breadcrumb -->
			<div class="bread-crumb pull-right">
				<a href="${ctx}"><i class="icon-home"></i> Home</a> <span
					class="divider">/</span> <a href="${ctx}" class="bread-current">Dashboard</a>
			</div>

			<div class="clearfix"></div>

		</div>
		<!-- Page heading ends -->
		<div id="addItem"></div>
		<!-- Matter -->
		<div class="matter">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<!-- <div>
							<a href="${ctx}/common/toAddTask" class="btn btn-default">新建爬取</a>
						</div> -->
						<div class="widget wgreen">
							<div class="widget-head">
								<div class="pull-left">法院公告信息爬取进度查询</div>
								<div class="widget-icons pull-right">
									<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
									<a href="#" class="wclose"><i class="icon-remove"></i></a>
								</div>
								<div class="clearfix"></div>
							</div>

							<div class="widget-content">
								<div class="padd">
									<!-- Form starts.  -->
									<form id="queryForm" class="form-horizontal"
										action="${ctx}/courtAnnounce/list" method="get">
										<div class="form-group">
											<div class="col-md-12"></div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">商户名称</label>
											<div class="col-md-3">
												<input class="form-control" type="text" id="usr"
													name="search_EQ_merName" value="${param.search_EQ_merName}">
											</div>

											<label class="col-md-2 control-label">开始日期</label>
											<div class="col-md-3">
												<input type="text" id="startDt" name="search_GT_createdDt"
													class="form-control form_date" value="${param.search_GT_createdDt}"
													data-date-format="yyyy-mm-dd">
											</div>
											<label class="col-md-2 control-label">结束日期</label>
											<div class="col-md-3">
												<input type="text" id="endDt" name="search_LT_createdDt"
													class="form-control form_date" value="${param.search_LT_createdDt}"
													data-date-format="yyyy-mm-dd">
											</div>

										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">查询状态</label>
											<div class="col-md-3">
												<select id="flag" name="search_EQ_flag" class="form-control">
													<option value=''>全部</option>
													<option value='0' <c:if test="${'0' eq param.search_EQ_flag}">selected</c:if> >默认</option>
													<option value='1' <c:if test="${'1' eq param.search_EQ_flag}">selected</c:if> >成功</option>
													<option value='2' <c:if test="${'2' eq param.search_EQ_flag}">selected</c:if> >失败</option>
												</select>
											</div>
											<div class="col-md-3">
												<button type="submit" class="btn btn-default">查询</button>
												<button type="reset" class="btn btn-warning">重置</button>
											</div>
										</div>
									</form>
								</div>
							</div>
							<div class="widget-foot"></div>
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
								<div class="pull-left">法院公告信息爬取进度列表</div>
								<div class="widget-icons pull-right">
									<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
									<a href="#" class="wclose"><i class="icon-remove"></i></a>
								</div>
								<div class="clearfix"></div>
							</div>
							<c:if test="${not empty resultList}">
								<div class="widget-content">
									<table class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th>编号</th>
												<th>商户名称</th>
												<th>备注</th>
												<th>申请时间</th>
												<th>爬取开始时间</th>
												<th>爬取结束时间</th>
												<th>爬取状态</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>

											<c:forEach items="${resultList.content}" var="result"
												varStatus="index">
												<tr>
													<td>${index.count}</td>
													<td>${result.merName}</td>
													<td>${result.memo}</td>
													<td><fmt:formatDate value="${result.createdDt}"
															pattern="yyyy-MM-dd HH:mm:ss" /></td>
													<td><fmt:formatDate value="${result.createdDt}"
															pattern="yyyy-MM-dd HH:mm:ss" /></td>
													<td>
														<c:choose>
															<c:when test="${result.flag == '0' }"></c:when>
															<c:otherwise>
																<fmt:formatDate value="${result.updatedDt}"
															pattern="yyyy-MM-dd HH:mm:ss" />
															</c:otherwise>
														</c:choose>
													</td>
													<c:if test="${result.flag == '0'}">
														<td>爬取中</td>
														<td>
															<button class="btn btn-xs btn-primary" title="重新爬取"
																onclick="doRecycle(this,'${result.merName}')">
																<i class="icon-play"></i>
															</button>
														</td>
													</c:if>
													<c:if test="${result.flag == '1'}">
														<td>成功</td>
														<td>
															<button class="btn btn-xs btn-success" title="详情"
																onclick="javascript:doShowDetail('${result.merName}')">
																<i class="icon-list"></i>
															</button>
															<button class="btn btn-xs btn-primary" title="重新爬取"
																onclick="doRecycle(this,'${result.merName}')">
																<i class="icon-play"></i>
															</button>
														</td>
													</c:if>
													<c:if test="${result.flag == '2'}">
														<td>失败</td>
														<td>
															<button class="btn btn-xs btn-warning" title="错误日志"
																onclick="javascript:doShowLog('${result.merName}')">
																<i class="icon-list"></i>
															</button>
															<button class="btn btn-xs btn-primary" title="重新爬取"
																onclick="doRecycle(this,'${result.merName}')">
																<i class="icon-play"></i>
															</button>
														</td>
													</c:if>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<div class="widget-foot">
										<!-- paginationSize:前台页面最多能显示的页数 -->
										<tags:pagination page="${resultList}" paginationSize="5" />
										<div class="clearfix"></div>
									</div>
								</div>
							</c:if>
						</div>
					</div>
				</div>

				<div class="clearfix"></div>
			</div>
			<!-- Content ends -->


		</div>
	</div>
</div>

<script type="text/javascript">
	function doShowDetail(merName) {
		window.location.href='${ctx}/courtAnnounce/queryInfo?merName='+merName;
// 		$.ajax({
// 			cache : true,
// 			type : 'get',
// 			url : '${ctx}/courtAnnounce/queryInfo',
// 			data: 'merName=' + merName,
// 			success : function(data) {
// 				$("body").html(data);
// 			},
// 			error : function(data) {
// 			},
// 		});
	}
	function doShowLog(merName) {
		window.location.href='${ctx}/courtAnnounce/queryLog?merName='+merName;
// 		$.ajax({
// 			cache : true,
// 			type : 'POST',
// 			url : '${ctx}/courtAnnounce/queryLog',
// 			data: 'merName=' + merName,
// 			success : function(data) {
// 				$("body").html(data);
// 			},
// 			error : function(data) {
// 			},
// 		});
	}
	
	function doRecycle(obj,merName) {
		$.ajax({
			cache : true,
			type : 'POST',
			url : '${ctx}/courtAnnounce/doRecycle',
			data: 'merName=' + merName,
			success : function(data) {
				if(data.message=="success"){
					$(obj).attr("class","btn btn-xs btn-info disabled");
					$(obj).attr("title","已重爬");
					$(obj).unbind("click");
					$(obj).find('i').attr("class","icon-ok");
				}
			},
			error : function(data) {
				$(obj).attr("class","btn btn-xs btn-warning");
				$(obj).attr("title","异常");
				$(obj).find('i').attr("class","icon-pause");
			},
		});
		
	}
</script> 