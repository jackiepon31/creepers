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
				<i class="icon-table"></i> 社保信息管理
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
								<div class="pull-left">社保基础信息</div>
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
												<th>姓名</th>
												<th>证件号码</th>
												<th>92年底前连续工龄</th>
												<th>备注</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${not empty basicResultList}">
												<c:forEach items="${basicResultList}" var="item"
													varStatus="index">
													<tr>
														<td>${index.count}</td>
														<%-- 														<td>${item.merNo}</td> --%>
														<td>${item.name}</td>
														<td>${item.certNo}</td>
														<td>${item.workTime}</td>
														<td>${item.memo }</td>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</div>
							</div>
							<!-- 							<div class="widget-foot"> -->
							<!-- 								paginationSize:前台页面最多能显示的页数 -->
							<%-- 								<tags:pagination page="${resultList}" paginationSize="5" /> --%>
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
								<div class="pull-left">社保应缴记录信息</div>
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
												<th>年月</th>
												<th>缴费基数</th>
												<th>养保个人缴费额</th>
												<th>医保个人缴费额</th>
												<th>失保个人缴费额</th>
												<th>备注</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${not empty paymentResultList}">
												<c:forEach items="${paymentResultList }" var="item"
													varStatus="index">
													<tr>
														<td>${index.count}</td>
														<%-- 														<td>${item.merNo}</td> --%>
														<td>${item.paymentDt}</td>
														<td>${item.paymentBase}</td>
														<td>${item.endowment}</td>
														<td>${item.medical}</td>
														<td>${item.unemployment}</td>
														<td>${item.memo }</td>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</div>
							</div>
							<!-- 							<div class="widget-foot"> -->
							<!-- 								paginationSize:前台页面最多能显示的页数 -->
							<%-- 								<tags:pagination page="${resultList}" paginationSize="5" /> --%>
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
								<div class="pull-left">社保账户信息</div>
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
												<th>所属单位</th>
												<th>起始日期</th>
												<th>备注</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${not empty unitResultList}">
												<c:forEach items="${unitResultList }" var="item"
													varStatus="index">
													<tr>
														<td>${index.count}</td>
														<%-- 														<td>${item.merNo}</td> --%>
														<td>${item.unit}</td>
														<td>${item.period}</td>
														<td>${item.memo }</td>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</div>
							</div>
							<!-- 							<div class="widget-foot"> -->
							<!-- 								paginationSize:前台页面最多能显示的页数 -->
							<%-- 								<tags:pagination page="${resultList}" paginationSize="5" /> --%>
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
								<div class="pull-left">社保累计缴费信息</div>
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
												<th>截止年月</th>
												<th>累计缴费月数</th>
												<th>养老金本息总额</th>
												<th>养老金总额个人部分</th>
												<th>备注</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${not empty sumResultList}">
												<c:forEach items="${sumResultList }" var="item"
													varStatus="index">
													<tr>
														<td>${index.count}</td>
														<%-- 														<td>${item.merNo}</td> --%>
														<td>${item.endDt}</td>
														<td>${item.months}</td>
														<td>${item.endowmentSum}</td>
														<td>${item.endowmentSumPrivate}</td>
														<td>${item.memo }</td>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</div>
							</div>
							<!-- 							<div class="widget-foot"> -->
							<!-- 								paginationSize:前台页面最多能显示的页数 -->
							<%-- 								<tags:pagination page="${resultList}" paginationSize="5" /> --%>
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