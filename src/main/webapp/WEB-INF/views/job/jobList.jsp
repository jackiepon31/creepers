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
				<i class="icon-table"></i> 任务调度管理
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
						<div class="widget wgreen">
							<div class="widget-head">
								<div class="pull-left">爬虫批处理任务查询</div>
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
										action="${ctx}/job/list" method="post">
										<div class="form-group">
											<div class="col-md-12"></div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">任务名称</label>
											<div class="col-md-3">
												<input class="form-control" type="text" id="usr"
													name="search_EQ_jobName" value="${param.search_EQ_jobName}">
											</div>

											<label class="col-md-2 control-label">任务类型</label>
											<div class="col-md-3">
												<select id="search_EQ_jobGroup" name="search_EQ_jobGroup"
													class="form-control">
													<option value='' >全部</option>
													<c:forEach items="${taskTypeList }" var="item">
														<option value="${item.value }">${item.name }</option>
													</c:forEach>
												</select>
											</div>
											<label class="col-md-2 control-label">任务状态</label>
											<div class="col-md-3">
												<select id="status" name="search_EQ_status" class="form-control">
													<option value='' >全部</option>
													<option value='NORMAL' <c:if test="${'0' eq param.search_EQ_flag}">selected</c:if> >正常运行</option>
													<option value='NONE' <c:if test="${'NONE' eq param.search_EQ_flag}">selected</c:if> >未知</option>
													<option value='PAUSED' <c:if test="${'PAUSED' eq param.search_EQ_flag}">selected</c:if> >暂停状态</option>
													<option value='COMPLETE' <c:if test="${'COMPLETE' eq param.search_EQ_flag}">selected</c:if> >完成</option>
													<option value='ERROR' <c:if test="${'ERROR' eq param.search_EQ_flag}">selected</c:if> >错误状态</option>
													<option value='BLOCKED' <c:if test="${'BLOCKED' eq param.search_EQ_flag}">selected</c:if> >锁定状态</option>
												</select>
											</div>

										</div>
										<div class="form-group">
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
								<div class="pull-left">爬虫批处理任务列表
								<button class="btn btn-primary" title="新增" onclick="javascript:doAdd()">新增</button>
								</div>
								<div class="widget-icons pull-right">
									<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
									<a href="#" class="wclose"><i class="icon-remove"></i></a>
								</div>
								<div class="clearfix"></div>
							</div>
							<c:if test="${not empty resultList}">
								<div class="widget-content" style="overflow:scroll;">
									<table class="table table-striped table-bordered table-hover"  style="min-width:1650px;">
										<thead>
											<tr>
												<th>任务名称</th>
												<th>任务类型</th>
												<th>并发执行</th>
												<th>任务运行时间表达式</th>
												<th>任务类名</th>
												<th>任务方法名</th>
												<th>启动时间</th>
												<th>前次运行时间</th>
												<th>下次运行时间</th>
												<th>任务描述</th>
												<th>任务状态</th>
												<th align="middle">操作</th>
											</tr>
										</thead>
										<tbody>

											<c:forEach items="${resultList.content}" var="result"
												varStatus="index">
												<tr>
													<td>${result.jobName}</td>
													<td>${result.jobGroup}</td>
													<td><c:choose>
															<c:when test="${result.isConcurrent == '0' }">否</c:when>
															<c:otherwise>是</c:otherwise>
														</c:choose></td>
													<td>${result.cronExpression}</td>
													<td>${result.jobClass}</td>
													<td>${result.methodName}</td>
													<td><fmt:formatDate value="${result.startTime}"
															pattern="yyyy-MM-dd HH:mm:ss" /></td>
													<td><fmt:formatDate value="${result.previousTime}"
															pattern="yyyy-MM-dd HH:mm:ss" /></td>
													<td><fmt:formatDate value="${result.nextTime}"
															pattern="yyyy-MM-dd HH:mm:ss" />
													</td>
													<td>${result.description}</td>
													<td>
														<c:choose>
															<c:when test="${result.status == 'NONE' }">未知</c:when>
															<c:when test="${result.status == 'NORMAL' }">正常运行</c:when>
															<c:when test="${result.status == 'PAUSED' }">已暂停</c:when>
															<c:when test="${result.status == 'COMPLETE' }">成功完成</c:when>
															<c:when test="${result.status == 'ERROR' }">发生异常</c:when>
															<c:when test="${result.status == 'BLOCKED' }">已锁定</c:when>
															<c:otherwise>默认状态</c:otherwise>
														</c:choose>
													</td>
													<td>
														<!-- <button  class="btn btn-default" title="添加"
															onclick="javascript:doAdd()">添加
														</button> -->
														<c:if test="${! empty result.memo}">
															<button  class="btn btn-xs btn-primary" title="断点续传"
															onclick="javascript:resumeFromBreakPoint(this,'${result.jobName}','${result.jobGroup}')">断点续传
															</button>
														</c:if>
														<button   class="btn btn-xs btn-primary" title="更新"
															onclick="javascript:doUpdate(this,'${result.jobName}','${result.jobGroup}')">更新
														</button>
														<c:if test="${result.status == 'NORMAL' }">
															<button  class="btn btn-xs btn-warning"
																onclick="javascript:doPause(this,'${result.jobName}','${result.jobGroup}')">暂停
															</button>
														</c:if>
														<c:if test="${result.status == 'PAUSED' }">
															<button  class="btn btn-xs btn-primary" title="恢复"
																onclick="javascript:doResume(this,'${result.jobName}','${result.jobGroup}')">恢复
															</button>
														</c:if>
														<button  class="btn btn-xs btn-danger" title="删除"
															onclick="javascript:doDelete(this,'${result.jobName}','${result.jobGroup}')">删除
														</button>
														<button  class="btn btn-xs btn-info" title="日志"
															onclick="javascript:doDelete(this,'${result.jobName}','${result.jobGroup}')">日志
														</button>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<div class="widget-foot">
									<!-- paginationSize:前台页面最多能显示的页数 -->
									<tags:pagination page="${resultList}" paginationSize="10" />
									<div class="clearfix"></div>
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
	function doAdd() {
		window.location.href='${ctx}/job/toAdd';
	}
	function doUpdate(obj,jobName,jobGroup) {
		window.location.href='${ctx}/job/queryJob?jobName='+jobName+"&jobGroup="+jobGroup;
	}
	function doPause(obj,jobName,jobGroup) {
		$.ajax({
			cache : true,
			type : 'POST',
			url : '${ctx}/job/doPause',
			data : {jobName:jobName,jobGroup:jobGroup},
			success : function(data) {
				if (data.message == "success") {
					$(obj).attr("class", "btn btn-xs btn-primary");
					$(obj).text("恢复");
					$(obj).attr("title", "恢复");
					$(obj).attr("onclick", "javascript:doResume(this,'${result.jobName}','${result.jobGroup}')");
					$(obj).parent().prev().text("已暂停");
				}
			},
			error : function(data) {
				$(obj).attr("class", "btn btn-xs btn-warning");
				$(obj).attr("title", "发生异常，请再次点击");
			},
		});
	}
	function doResume(obj,jobName,jobGroup) {
		$.ajax({
			cache : true,
			type : 'POST',
			url : '${ctx}/job/doResume',
			data : {jobName:jobName,jobGroup:jobGroup},
			success : function(data) {
				if (data.message == "success") {
					$(obj).attr("class", "btn btn-xs btn-warning");
					$(obj).text("暂停");
					$(obj).attr("title", "暂停");
					$(obj).attr("onclick", "javascript:doPause(this,'${result.jobName}','${result.jobGroup}')");
					$(obj).parent().prev().text("正常运行");
				}
			},
			error : function(data) {
				$(obj).attr("class", "btn btn-xs btn-warning");
				$(obj).attr("title", "发生异常，请再次点击");
			},
		});
	}
	function resumeFromBreakPoint(obj,jobName,jobGroup) {
		$.ajax({
			cache : true,
			type : 'POST',
			url : '${ctx}/job/resumeFromBreakPoint',
			data : {jobName:jobName,jobGroup:jobGroup},
			success : function(data) {
				if (data.message == "success") {
					$(obj).attr("class", "btn btn-xs btn-info disabled");
					$(obj).text("已续爬");
					$(obj).attr("title", "已续爬");
				}
			},
			error : function(data) {
				$(obj).attr("class", "btn btn-xs btn-warning");
				$(obj).attr("title", "发生异常，请再次点击");
			},
		});
	}
	function doDelete(obj,jobName,jobGroup) {
		$.ajax({
			cache : true,
			type : 'POST',
			url : '${ctx}/job/doResume',
			data : {jobName:jobName,jobGroup:jobGroup},
			success : function(data) {
				if (data.message == "success") {
					doReSet();
				}
			},
			error : function(data) {
				$(obj).attr("class", "btn btn-xs btn-warning");
				$(obj).attr("title", "发生异常，请再次点击");
			},
		});
	}	
	function doReSet(){
		form.action='${ctx}/job/list';
		form.submit();
	}
</script>