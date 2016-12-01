<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="sitemesh"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<!-- Title and other stuffs -->
<title><sitemesh:title default="[网络爬虫管理系统]-量富证信管理有限公司" /></title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="">
<!-- JS -->

<script src="${ctx}/static/js/jquery-1.9.1.min.js"></script>
<script src="${ctx}/static/js/bootstrap.js"></script>
<!-- Bootstrap -->
<script src="${ctx}/static/js/jquery-ui-1.9.2.custom.min.js"></script>
<!-- jQuery UI -->
<script src="${ctx}/static/js/fullcalendar.min.js"></script>
<!-- Full Google Calendar - Calendar -->
<script src="${ctx}/static/js/jquery.rateit.min.js"></script>
<!-- RateIt - Star rating -->
<script src="${ctx}/static/js/jquery.prettyPhoto.js"></script>
<!-- prettyPhoto -->

<!-- jQuery Notification - Noty -->
<script src="${ctx}/static/js/jquery.noty.js"></script>
<!-- jQuery Notify -->
<script src="${ctx}/static/js/themes/default.js"></script>
<!-- jQuery Notify -->
<script src="${ctx}/static/js/layouts/bottom.js"></script>
<!-- jQuery Notify -->
<script src="${ctx}/static/js/layouts/topRight.js"></script>
<!-- jQuery Notify -->
<script src="${ctx}/static/js/layouts/top.js"></script>
<!-- jQuery Notify -->
<!-- jQuery Notification ends -->

<script src="${ctx}/static/js/jquery.cleditor.min.js"></script>
<!-- CLEditor -->
<script src="${ctx}/static/js/bootstrap-datetimepicker.js"></script>
<!-- Date picker -->
<script src="${ctx}/static/js/bootstrap-switch.min.js"></script>
<!-- Bootstrap Toggle -->
<script src="${ctx}/static/js/filter.js"></script>
<!-- Filter for support page -->
<script src="${ctx}/static/js/custom.js"></script>
<!-- Custom codes -->
<script src="${ctx}/static/js/process.js"></script>

<!-- echart start-->
<script src="${ctx}/static/js/echarts/src/esl.js"></script>
<script src="${ctx}/static/js/echarts/src/echarts.min.js"></script>
<!-- echart end-->

<script
	src="${ctx}/static/js/jquery-validation/1.11.1/jquery.validate.min.js"
	type="text/javascript"></script>
<script src="${ctx}/static/js/jquery-validation-customer.js"
	type="text/javascript"></script>
<script
	src="${ctx}/static/js/jquery-validation/1.11.1/messages_bs_zh.js"
	type="text/javascript"></script>
<!-- Stylesheets -->
<link rel="stylesheet" href="${ctx}/static/style/bootstrap.css">
<!-- Font awesome icon -->
<link rel="stylesheet" href="${ctx}/static/style/font-awesome.css">
<!-- jQuery UI -->
<link rel="stylesheet" href="${ctx}/static/style/jquery-ui.css">
<!-- Calendar -->
<link rel="stylesheet" href="${ctx}/static/style/fullcalendar.css">
<!-- prettyPhoto -->
<link rel="stylesheet" href="${ctx}/static/style/prettyPhoto.css">
<!-- Star rating -->
<link rel="stylesheet" href="${ctx}/static/style/rateit.css">
<!-- Date picker -->
<link rel="stylesheet"
	href="${ctx}/static/style/bootstrap-datetimepicker.min.css">
<!-- CLEditor -->
<link rel="stylesheet" href="${ctx}/static/style/jquery.cleditor.css">
<!-- Bootstrap toggle -->
<link rel="stylesheet" href="${ctx}/static/style/bootstrap-switch.css">
<!-- Main stylesheet -->
<link rel="stylesheet" href="${ctx}/static/style/style.css">
<!-- Widgets stylesheet -->
<link rel="stylesheet" href="${ctx}/static/style/widgets.css">
<!-- Validate stylesheet -->
<link rel="stylesheet"
	href="${ctx}/static/js/jquery-validation/1.11.1/validate.css" />

<!-- HTML5 Support for IE -->
<!--[if lt IE 9]>
  <script src="${ctx}/static/js/html5shim.js"></script>
  <![endif]-->
<sitemesh:head />
</head>
<body>
	<div>
		<%@ include file="/WEB-INF/layouts/header.jsp"%>
		<div class="content">
			<%@ include file="/WEB-INF/layouts/left.jsp"%>
			<div id="main" class="span10">
				<sitemesh:body />
			</div>
		</div>
		<%@ include file="/WEB-INF/layouts/footer.jsp"%>
	</div>
</body>

<script type="text/javascript">
	//更新进度条
	function updatePercent() {
		var value = $('#progressBar').progressbar('option', 'value');
		if (value == 100) {
			value = 0;
		}
		$('#progressBar').progressbar('option', 'value', value + 1);
	}

	var progressBarTimer;

	//启动进度条
	function startProgressBar() {
		$("#progressBar").progressbar({
			value : 0
		});
		window.clearInterval(window.thread);
		window.thread = window.setInterval("updatePercent()", 10);
	}
	//终止进度条
	function stopProgressBar() {
		window.clearInterval(window.thread);
		$('#progressBar').progressbar('option', 'value', 0);
	}

	//清除消息显示区域
	clearGlobalMessage = function() {
		$("#messageBar").html("&nbsp;");
	}

	var timer;
	/**
	 * JS客户端方式在公共消息区域显示提示消息
	 *
	 * @params msg 显示信息
	 * @returns 
	 */
	function publishMessage(msg) {
		if (undefined != timer && timer != null) {
			clearTimeout(timer);
		}
		$("#messageBar").html(msg);
		timer = setTimeout(clearGlobalMessage, 30000);
	}

	/**
	 * JS客户端方式在公共消息区域以红色显示错误信息
	 *
	 * @params msg 显示的错误信息
	 * @returns 
	 */
	function publishError(msg, devMode) {
		if (undefined != timer && timer != null) {
			clearTimeout(timer);
		}
		if (devMode != undefined && devMode == true) {
			$("#messageBar").html(
					"<a href='javascript:showDetail()'>查看异常明细:</a><b><font color=red>"
							+ msg + "</font></b>");
		} else {
			$("#messageBar").html("<b><font color=red>" + msg + "</font></b>");
		}
		timer = setTimeout(clearGlobalMessage, 60000);
	}

	function publishErrorContentPage(html) {
		$("#submitDiv").html(html);
	}

	//点击错误提示信息，弹出窗口显示错误细节
// 	function showDetail() {
// 		var option = {
// 			width : 1024,
// 			height : 500
// 		};
// 		$("#errorDetailDiv").dialog(option);
// 		$("#errorDetailDiv").dialog("open");
// 	}

	//点击错误提示信息，弹出窗口显示错误细节
	function setErrorDetailContent(content) {
		$("#errorDetailDiv").html(content);
	}

	function addPanelTabImplement(menuCode, title, link) {
		var node = $("#menu_" + menuCode);
		if (node.attr("id") == undefined) {
			node = $("body").append(
					"<div id='"+menuCode+"' style='display:none'></div>");
		}
		link = getRandedURL(link);
		$.fn.jerichoTab.loadOnce = false;
		$.fn.jerichoTab.addTab({
			tabFirer : $(node),
			title : title,
			closeable : true,
			iconImg : '',
			loadOnce : false,
			data : {
				dataType : 'iframe',
				dataLink : link
			}
		}).showLoader().loadData();
		$.fn.jerichoTab.loadOnce = true;
	}

	$('.form_date').datetimepicker({
		language : 'cn',
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 2,
		forceParse : 1,
	});

	function removeOptions(idNext) {
		var objSelect = document.getElementById(idNext);
		objSelect.options.length = 1;
	}

	function addOptions(idNext, data) {
		$.each(data,
				function(i, item) {
					$("#" + idNext).append(
							"<option value="+i+">" + item + "</option>");
				});
	}

	function changeCategory(idCurrent, idNext) {
		if (idCurrent == "Category0") {
			removeOptions("Category2");
			removeOptions("Category3");
		}
		var selectValue = $('#' + idCurrent + ' option:selected').val();
		$.ajax({
			cache : true,
			type : "GET",
			url : '${ctx}/creditDict/query',
			data : {
				category : selectValue
			},
			async : false,
			success : function(data) {
				removeOptions(idNext);
				addOptions(idNext, data);
			}
		});

	}
</script>
<div id="submitDiv" style="display: none"></div>

<!-- submit result message -->
<c:if test="${not empty message}">
	<script>
		$(function() {
			$('#messageModal').modal({
				keyboard : true
			})
		});
		setTimeout(function() {
			$("#messageModal").modal("hide")
		}, 2000);
	</script>
	<!--
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
		 -->
</c:if>

<!-- Modal Message -->
<div class="modal fade" id="messageModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">提示信息</h4>
			</div>
			<div class="modal-body">${message}</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="exampleModalLabel">中国SIC码</h4>
			</div>

			<div class="modal-body">

				<form name="form" id="tempForm" action="${ctx}/creditDict/queryIsic"
					method="GET" enctype="multipart/form-data">

					<div class="form-group">
						<label for="recipient-name" class="control-label">SIC码分类:</label>

						<select id="Category0" name="Category0"
							onchange="changeCategory('Category0','Category1')"
							class="form-control">
							<option>请选择</option>
							<c:forEach var="mp" items="${map}">
								<option value='${mp.key}'>${mp.value}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="recipient-name" class="control-label">级别一:</label> <select
							id="Category1" name="Category1"
							onchange="changeCategory('Category1','Category2')"
							class="form-control">
							<option>请选择</option>
						</select>
					</div>

					<div class="form-group">
						<label for="recipient-name" class="control-label">级别二:</label> <select
							id="Category2" name="Category2"
							onchange="changeCategory('Category2','Category3')"
							class="form-control">
							<option>请选择</option>
						</select>
					</div>

					<div class="form-group">
						<label for="recipient-name" class="control-label">级别三:</label> <select
							id="Category3" name="Category3" class="form-control">
							<option>请选择</option>
						</select>
					</div>
				</form>
			</div>

			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<input type="submit" data-dismiss="modal" onclick="returnParam()"
					class="btn btn-primary" value="确定" />
			</div>

		</div>
	</div>
</div>

</html>