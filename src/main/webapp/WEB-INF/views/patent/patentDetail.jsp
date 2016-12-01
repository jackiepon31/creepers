<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div class="content">


	<!-- Main bar -->
	<div class="mainbar">

		<!-- Page heading -->
		<div class="page-head">
			<h2 class="pull-left">
				<i class="icon-table"></i> 专利信息管理
			</h2>

			<!-- Breadcrumb -->
			<div class="bread-crumb pull-right">
				<a href="${ctx}"><i class="icon-home"></i> Home</a> <span
					class="divider">/</span> <a href="${ctx}" class="bread-current">Dashboard</a>
			</div>

			<div class="clearfix"></div>

		</div>
		<!-- Matter -->
		<div class="matter">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="widget wgreen">
							<div class="widget-head">
								<div class="pull-left">专利信息</div>
								<div class="widget-icons pull-right">
									<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
									<a href="#" class="wclose"><i class="icon-remove"></i></a>
								</div>
								<div class="clearfix"></div>
							</div>

							<div class="widget-content">
								<div class="padd">
									<div class="form-group">
										<div class="col-md-12"></div>
									</div>
									<table class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th>序号</th>
												<th>商户名称</th>
												<th>主分类号</th>
												<th>专利类型</th>
												<th>专利号</th>
												<th>发明名称</th>
												<th>申请人</th>
												<th>申请日期</th>
												<th>备注</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${not empty resultList}">
												<c:forEach items="${resultList.content }" var="item"
													varStatus="index">
													<tr>
														<td>${index.count}</td>
														<td>${item.merName}</td>
														<td>${item.categoryNo}</td>
														<td>${item.patentType}</td>
														<td>${item.patentNo}</td>
														<td>${item.patentName}</td>
														<td>${item.applicant}</td>
														<td><fmt:formatDate value="${item.applyDt}"
																pattern="yyyy-MM-dd" /></td>
														<td>${item.memo}</td>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
									<div class="widget-foot">
										<!-- paginationSize:前台页面最多能显示的页数 -->
										<tags:pagination page="${resultList}" paginationSize="5" />
										<div class="clearfix"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Matter ends -->
	</div>
</div>