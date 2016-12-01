<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%-- <c:set var="ctx" value="${pageContext.request.contextPath}"/> --%>

<!-- Main content starts -->
<div class="content">
	<!-- Main bar -->
	<div class="mainbar">
		<!-- Matter -->
		<div class="matter">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="widget wgreen">
							<div class="widget-head">
								<div class="pull-left">个人信息</div>
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
									<table class="table table-striped  table-hover">
										<tbody>
											<tr>
												<td>报告编号:${resultMap.tCreepersBasic.rptNo}</td>
												<td>查询时间:${resultMap.tCreepersBasic.queryDt}</td>
												<td>报告时间:${resultMap.tCreepersBasic.rptDt}</td>
											</tr>
										</tbody>
									</table>
									<table class="table table-striped  table-hover">
										<tbody>
											<tr>
												<td>姓名:${resultMap.tCreepersBasic.name}</td>
												<td>证件类型:${resultMap.tCreepersBasic.idType}</td>
												<td>证件号码:${resultMap.tCreepersBasic.idNo}</td>
												<td>婚姻状况:${resultMap.tCreepersBasic.maritalStatus}</td>
											</tr>
										</tbody>
									</table>

								</div>
							</div>
							<div class="widget-foot"></div>
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
								<div class="pull-left">信贷记录-资产处置信</div>
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
												<th>资产处置笔数</th>
												<th>说明</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>001</td>
												<td>${resultMap.tCreepersGeneral.assetDisposalNo}</td>
												<td><c:forEach
														items="${resultMap.tCreepersAssetHandle}" var="result">
														<p>${result.memo}</p>
													</c:forEach></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							<div class="widget-foot"></div>
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
								<div class="pull-left">信贷记录-保证人代偿信息</div>
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
												<th>保证人代偿笔数</th>
												<th>说明</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>1</td>
												<td>${resultMap.tCreepersGeneral.guarantorCompensationNo}</td>
												<td><c:forEach
														items="${resultMap.tCreepersCompensatory}" var="result">
														<p>${result.memo}</p>
													</c:forEach></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							<div class="widget-foot"></div>
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
								<div class="pull-left">信贷记录-信用卡</div>
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
												<th>账户数</th>
												<th>未结清/未销户账户数</th>
												<th>发生过逾期的账户数</th>
												<th>发生过90天以上逾期的账户数</th>
												<th>为他人担保笔数</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>${resultMap.tCreepersGeneral.ccNo}</td>
												<td>${resultMap.tCreepersGeneral.outstandingCcNo}</td>
												<td>${resultMap.tCreepersGeneral.overdueCcNo}</td>
												<td>${resultMap.tCreepersGeneral.ninetyCcNo}</td>
												<td>${resultMap.tCreepersGeneral.guaranteeCcNo}</td>
											</tr>
										</tbody>
									</table>
									<c:forEach items="${resultMap.tCreepersCcDetail}" var="result">
										<div class="form-group">
											<div class="col-md-12">${result.memo}</div>
										</div>
									</c:forEach>
								</div>
							</div>
							<div class="widget-foot"></div>
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
								<div class="pull-left">信贷记录-住房贷款</div>
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
												<th>账户数</th>
												<th>未结清/未销户账户数</th>
												<th>发生过逾期的账户数</th>
												<th>发生过90天以上逾期的账户数</th>
												<th>为他人担保笔数</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>${resultMap.tCreepersGeneral.housingLoanNo}</td>
												<td>${resultMap.tCreepersGeneral.outstandingHousingLoanNo}</td>
												<td>${resultMap.tCreepersGeneral.overdueHousingLoanNo}</td>
												<td>${resultMap.tCreepersGeneral.ninetyHousingLoanNo}</td>
												<td>${resultMap.tCreepersGeneral.guaranteeHousingLoanNo}</td>
											</tr>
										</tbody>
									</table>
									<c:forEach items="${resultMap.tCreepersHlDetail}" var="result">
										<div class="form-group">
											<div class="col-md-12">${result.memo }</div>
										</div>
									</c:forEach>
								</div>
							</div>
							<div class="widget-foot"></div>
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
								<div class="pull-left">信贷记录-其他贷款</div>
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
												<th>账户数</th>
												<th>未结清/未销户账户数</th>
												<th>发生过逾期的账户数</th>
												<th>发生过90天以上逾期的账户数</th>
												<th>为他人担保笔数</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>${resultMap.tCreepersGeneral.otherLoanNo}</td>
												<td>${resultMap.tCreepersGeneral.outstandingOtherLoanNo}</td>
												<td>${resultMap.tCreepersGeneral.overdueOtherLoanNo}</td>
												<td>${resultMap.tCreepersGeneral.ninetyOtherLoanNo}</td>
												<td>${resultMap.tCreepersGeneral.guaranteeOtherLoanNo}</td>
											</tr>
										</tbody>
									</table>
									<c:if test="${not empty resultMap.tCreepersOlDetail}">
										<div class="form-group">
											<c:forEach items="${resultMap.tCreepersOlDetail}"
												var="result">
												<div class="col-md-12">${result.memo}</div>
											</c:forEach>
										</div>
									</c:if>
									<c:if test="${not empty resultMap.tCreepersGuarantee}">
										<div class="form-group">
											<div class="col-md-12">为他人担保信息：</div>
										</div>
									</c:if>
									<div class="form-group">
										<c:forEach items="${resultMap.tCreepersGuarantee}"
											var="result">
											<div class="col-md-12">${result.memo}</div>
										</c:forEach>
									</div>
								</div>
							</div>
							<div class="widget-foot"></div>
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
								<div class="pull-left">公共记录-欠税记录</div>
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
												<th>主管税务机关</th>
												<th>欠税统计时间</th>
												<th>欠税总额</th>
												<th>纳税人识别号</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${resultMap.tCreepersPublicTax}"
												var="result">
												<tr>
													<td></td>
													<td>${result.competentTaxAuthority }</td>
													<td>${result.taxStatisticDt }</td>
													<td>${result.taxAmount }</td>
													<td>${result.taxpayerNo }</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
							<div class="widget-foot"></div>
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
								<div class="pull-left">公共记录-强制执行记录</div>
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
												<th>执行法院</th>
												<th>案号</th>
												<th>执行案由</th>
												<th>立案方式</th>
												<th>结案时间</th>
												<th>案件状态</th>
												<th>申请执行标的</th>
												<th>已执行标的</th>
												<th>申请执行标的金额</th>
												<th>已执行标的金额</th>
												<th>结案时间</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${resultMap.tCreepersPublicEnforcement}"
												var="result">
												<tr>
													<td></td>
													<td>${result.enforcementCourt }</td>
													<td>${result.enforcementCase }</td>
													<td>${result.cause }</td>
													<td>${result.closeWay }</td>
													<td>表中无字段，暂时不输出</td>
													<td>${result.caseStatus }</td>
													<td>${result.enforcementSubject }</td>
													<td>${result.executedSubject }</td>
													<td>${result.subjectAmount }</td>
													<td>${result.executedSubjectAmout }</td>
													<td>${result.closingDt }</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
							<div class="widget-foot"></div>
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
								<div class="pull-left">公共记录-行政处罚记录</div>
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
												<th>处罚机构</th>
												<th>文件编号</th>
												<th>处罚内容</th>
												<th>是否行政复议</th>
												<th>处罚金额</th>
												<th>行政复议结果</th>
												<th>处罚生效时间</th>
												<th>处罚截止时间</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${resultMap.tCreepersPublicSanction}"
												var="result">
												<tr>
													<td></td>
													<td>${result.punishmentOrg }</td>
													<td>${result.docNo }</td>
													<td>${result.punishmentContent }</td>
													<td>${result.reconsiderationFlag }</td>
													<td>${result.punishmentAmount }</td>
													<td>${result.reconsiderationRslt }</td>
													<td>${result.punishmentIssueDt }</td>
													<td>${result.punishDt }</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
							<div class="widget-foot"></div>
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
								<div class="pull-left">公共记录-电信欠费信息</div>
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
												<th>电信运营商</th>
												<th>业务类型</th>
												<th>记账年月</th>
												<th>业务开通时间</th>
												<th>欠费金额</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${resultMap.tCreepersPublicIsp}"
												var="result">
												<tr>
													<td>1</td>
													<td>${result.teleOperators }</td>
													<td>${result.serviceType }</td>
													<td>${result.journalEntry }</td>
													<td>${result.bizOperateDt }</td>
													<td>${result.arrearsAmount }</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
							<div class="widget-foot"></div>
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
								<div class="pull-left">查询记录</div>
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
												<th>查询日期</th>
												<th>查询操作员</th>
												<th>查询原油</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${resultMap.tCreepersQueryLog}"
												var="result">
												<tr>
													<td></td>
													<td>${result.queryDt }</td>
													<td>${result.queryBy }</td>
													<td>${result.queryReason }</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
							<div class="widget-foot"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Matter ends -->
	</div>
	<!-- Mainbar ends -->
</div>
<script type="text/javascript">
	
</script>