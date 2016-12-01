<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div class="content">


	<!-- Main bar -->
	<div class="mainbar">

		<!-- Page heading -->
		<div class="page-head">
			<h2 class="pull-left">
				<i class="icon-table"></i> 法院黑名单明细信息管理
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
								<div class="pull-left">法院黑名单明细信息</div>
								<div class="widget-icons pull-right">
									<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
									<a href="#" class="wclose"><i class="icon-remove"></i></a>
								</div>
								<div class="clearfix"></div>
							</div>

							<div class="widget-content">
								<div class="padd" style="overflow:scroll;">
									<div class="form-group">
										<div class="col-md-12"></div>
									</div>
									<table class="table table-striped table-bordered table-hover" style="min-width:1500px;">
										<thead>
											<tr>
												<th>序号</th>
												<th>个人/企业名称</th>
												<th>数据类别</th>
												<th>身份证号码/组织机构代码</th>
												<th>地域名称</th>
												<th>来源</th>
												<th>案号</th>
												<th>企业法人姓名</th>
												<th>执行法院</th>
												<th>法律生效文书确定的义务</th>
												<th>被执行人的履行情况</th>
												<th>失信被执行人具体情况</th>
												<th>执行依据文号</th>
												<th>做出执行依据单位</th>
												<th>已履行部分</th>
												<th>未履行部分</th>
												<th>立案时间</th>
												<th>发布时间</th>
												<th>备注</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${not empty resultList}">
												<c:forEach items="${resultList.content }" var="item"
													varStatus="index">
													<tr>
														<td>${index.count}</td>
														<td>${item.name}</td>
														<td>${item.type}</td>
														<td>${item.code}</td>
														<td>${item.province}</td>
														<td>${item.source}</td>
														<td>${item.caseNo}</td>
														<td>${item.legalRep}</td>
														<td>${item.court}</td>
														<td>
															<c:choose>
																<c:when test="${fn:length(item.duty)>20}">
																	${fn:substring(item.duty, 0, 20)}
																	<span>......</span>
																	<span style="display:none">
																	${fn:substring(item.duty, 20, -1)}
																	</span>
																	<a href="javascript:void(0)" onclick="showDetail(this)">更多</a>
																</c:when>
																<c:otherwise>
																	${item.duty}
																</c:otherwise>
															</c:choose>
														</td>
														<td>${item.performance}</td>
														<td>${item.specific}</td>
														<td>${item.implementNo}</td>
														<td>${item.implementUnit}</td>
														<td>${item.performY}</td>
														<td>${item.performN}</td>
														<td>${item.caseDt}</td>
														<td>${item.publishDt}</td>
														<td>${item.memo}</td>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</div>
							</div>
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
		<!-- Matter ends -->
	</div>
</div>
<script type="text/javascript">
	function showDetail(obj){
		$(obj).prev().show();
		$(obj).prev().prev().hide();
		$(obj).attr("onclick","hideDetail(this)");
		$(obj).html("隐藏");
	}
	function hideDetail(obj,id){
		$(obj).prev().hide();
		$(obj).prev().prev().show();
		$(obj).attr("onclick","showDetail(this)");
		$(obj).html("更多");
	}
</script>