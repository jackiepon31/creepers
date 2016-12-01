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
				<i class="icon-table"></i> 偷税漏税黑名单明细信息管理
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
								<div class="pull-left">偷税漏税黑名单明细信息</div>
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
												<th>数据来源</th>
												<th>数据类别</th>
												<th>省份</th>
												<th>检查机关</th>
												<th>纳税人名称</th>
												<th>纳税人识别码</th>
												<th>组织机构代码</th>
												<th>注册地址</th>
												<th>法定代表人或者负责人姓名</th>
												<th>性别</th>
												<th>法定代表人或者负责人证件名称</th>
												<th>负有直接责任的财务负责人姓名</th>
												<th>负有直接责任的财务负责人性别</th>
												<th>负有直接责任的财务负责人证件名称</th>
												<th>负有直接责任的中介机构信息</th>
												<th>案件性质</th>
												<th>主要违法事实</th>
												<th>相关法律依据及处理处罚情况</th>
												<th>发布级别</th>
												<th>备注</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${not empty resultList}">
												<c:forEach items="${resultList.content }" var="item"
													varStatus="index">
													<tr>
														<td>${index.count}</td>
														<td>${item.source}</td>
														<td>${item.type}</td>
														<td>${item.province}</td>
														<td>${item.court}</td>
														<td>${item.name}</td>
														<td>${item.taxNo}</td>
														<td>${item.code}</td>
														<td>${item.addr}</td>
														<td>${item.legalRep}</td>
														<td>${item.legalSex}</td>
														<td>${item.certName}</td>
														<td>${item.financialName}</td>
														<td>${item.financialSex}</td>
														<td>${item.financialCertName}</td>
														<td>${item.interInfo}</td>
														<td>${item.caseNature}</td>
														<td>
															<c:choose>
																<c:when test="${fn:length(item.illegalFacts)>20}">
																	${fn:substring(item.illegalFacts, 0, 20)}
																	<span>......</span>
																	<span style="display:none">
																	${fn:substring(item.illegalFacts, 20, -1)}
																	</span>
																	<a href="javascript:void(0)" onclick="showDetail(this)">更多</a>
																</c:when>
																<c:otherwise>
																	${item.illegalFacts}
																</c:otherwise>
															</c:choose>
														</td>
														<td>${item.punishInfo}</td>
														<td>${item.publishLevel}</td>
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