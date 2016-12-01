<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="content">


	<!-- Main bar -->
	<div class="mainbar">

		<!-- Page heading -->
		<div class="page-head">
			<h2 class="pull-left">
				<i class="icon-table"></i> 公积金信息管理
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
								<div class="pull-left">公积金账户信息表</div>
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
<!-- 												<th>序号</th> -->
												<th>姓名</th>
												<th>开户日期</th>
												<th>公积金账号</th>
												<th>所属单位</th>
												<th>末次缴存年月</th>
												<th>账户余额</th>
												<th>月缴存额</th>
												<th>当前账户状态</th>
												<th>绑定手机号</th>
												<th>实名认证状态</th>
												<th>备注</th>
											</tr>
										</thead>
										<tbody>
<%-- 											<c:if test="${not empty basicResult}"> --%>
<%-- 												<c:forEach items="${basicResultList}" var="item" --%>
<%-- 													varStatus="index"> --%>
													<tr>
<%-- 														<td>${index.count}</td> --%>
														<%-- 														<td>${item.merNo}</td> --%>
														<td>${basicResult.name}</td>
														<td>${basicResult.accountDt}</td>
														<td>${basicResult.accountNo}</td>
														<td>${basicResult.unit }</td>
														<td>${basicResult.endDt }</td>
														<td>${basicResult.sumAmount }</td>
														<td>${basicResult.monthlyAmount }</td>
														<td>${basicResult.accountStatus }</td>
														<td>${basicResult.mobile }</td>
														<td>${basicResult.certificateStatus }</td>
														<td>${basicResult.memo }</td>
													</tr>
<%-- 												</c:forEach> --%>
<%-- 											</c:if> --%>
										</tbody>
									</table>
								</div>
							</div>
<!-- 							<div class="widget-foot"> -->
<!-- 																paginationSize:前台页面最多能显示的页数 -->
<%-- 								<tags:pagination page="${basicResultList}" paginationSize="5" /> --%>
<!-- 								<div class="clearfix"></div> -->
<!-- 							</div> -->
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Matter ends -->
		<!-- Matter -->
		<div class="matter">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="widget wgreen">
							<div class="widget-head">
								<div class="pull-left">公积金账户本年度明细记录信息</div>
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
												<!-- 												<th>工商注册号</th> -->
												<th>日期</th>
												<th>单位名称</th>
												<th>金额（元）</th>
												<th>业务描述</th>
												<th>业务原因</th>
												<th>备注</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${not empty basicDetailResultList}">
												<c:forEach items="${basicDetailResultList }" var="item"
													varStatus="index">
													<tr>
														<td>${index.count}</td>
														<%-- 														<td>${item.merNo}</td> --%>
														<td>${item.operationDt}</td>
														<td>${item.unit}</td>
														<td>${item.amount}</td>
														<td>${item.operationDesc}</td>
														<td>${item.operationReason}</td>
														<td>${item.memo }</td>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</div>
							</div>
<!-- 							<div class="widget-foot"> -->
<!-- 																paginationSize:前台页面最多能显示的页数 -->
<%-- 								<tags:pagination page="${basicDetailResultList}" --%>
<%-- 									paginationSize="5" /> --%>
<!-- 								<div class="clearfix"></div> -->
<!-- 							</div> -->
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Matter ends -->
		<!-- Matter -->
		<div class="matter">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="widget wgreen">
							<div class="widget-head">
								<div class="pull-left">补充公积金账户信息</div>
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
<!-- 												<th>序号</th> -->
												<!-- 												<th>工商注册号</th> -->
												<th>姓名</th>
												<th>开户日期</th>
												<th>公积金账号</th>
												<th>所属单位</th>
												<th>末次缴存年月</th>
												<th>账户余额</th>
												<th>月缴存额</th>
												<th>当前账户状态</th>
												<th>备注</th>
											</tr>
										</thead>
										<tbody>
<%-- 											<c:if test="${not empty extraResult}"> --%>
<%-- 												<c:forEach items="${extraResultList }" var="item" --%>
<%-- 													varStatus="index"> --%>
													<tr>
<%-- 														<td>${index.count}</td> --%>
														<%-- 														<td>${item.merNo}</td> --%>
														<td>${extraResult.name}</td>
														<td>${extraResult.accountDt}</td>
														<td>${extraResult.accountNo}</td>
														<td>${extraResult.unit }</td>
														<td>${extraResult.endDt }</td>
														<td>${extraResult.sumAmount }</td>
														<td>${extraResult.monthlyAmount }</td>
														<td>${extraResult.accountStatus }</td>
														<td>${extraResult.memo }</td>
													</tr>
<%-- 												</c:forEach> --%>
<%-- 											</c:if> --%>
										</tbody>
									</table>
								</div>
							</div>
<!-- 							<div class="widget-foot"> -->
<!-- 																paginationSize:前台页面最多能显示的页数 -->
<%-- 								<tags:pagination page="${extraResultList}" paginationSize="5" /> --%>
<!-- 								<div class="clearfix"></div> -->
<!-- 							</div> -->
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Matter ends -->
		<!-- Matter -->
		<div class="matter">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="widget wgreen">
							<div class="widget-head">
								<div class="pull-left">补充公积金账户本年度明细信息</div>
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
												<!-- 												<th>工商注册号</th> -->
												<th>日期</th>
												<th>单位名称</th>
												<th>金额（元）</th>
												<th>业务描述</th>
												<th>业务原因</th>
												<th>备注</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${not empty extraDetailResultList}">
												<c:forEach items="${extraDetailResultList }" var="item"
													varStatus="index">
													<tr>
														<td>${index.count}</td>
														<%-- 														<td>${item.merNo}</td> --%>
														<td>${item.operationDt}</td>
														<td>${item.unit}</td>
														<td>${item.amount}</td>
														<td>${item.operationDesc}</td>
														<td>${item.operationReason}</td>
														<td>${item.memo }</td>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</div>
							</div>
<!-- 							<div class="widget-foot"> -->
<!-- 															paginationSize:前台页面最多能显示的页数 -->
<%-- 								<tags:pagination page="${extraDetailResultList}" --%>
<%-- 									paginationSize="5" /> --%>
<!-- 								<div class="clearfix"></div> -->
<!-- 							</div> -->
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Matter ends -->
	</div>
</div>