<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工列表</title>
<!-- 引入jQueery -->
<script type="text/javascript"
	src="${APP_PATH }/static/js/jquery-1.9.1.min.js"></script>
<!-- 引入bootstrap文件 -->
<!-- bootstrap引入文件顺序问题 -->
<link
	href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

</head>
<body>
	<!-- 编辑按钮的模态框 -->
	<div class="modal fade" tabindex="-1" role="dialog" id="empUpdateModal"
		data-backdrop="static">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">修改员工</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-2 control-label">empName</label>
							<div class="col-sm-10">
								<p class="form-control-static" id="empName_update_static">232</p>
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">email</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="email_update_input"
									placeholder="xxx@gmail.com" name="email"> <span
									class="help-block"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">gender</label>
							<div class="col-sm-10">
								<label class="radio-inline"> <input type="radio"
									name="gender" id="gender1_update_input" value="M"
									checked="checked"> 男
								</label> <label class="radio-inline"> <input type="radio"
									name="gender" id="gender2_update_input" value="F"> 女
								</label>
							</div>
						</div>

						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">department</label>
							<div class="col-sm-4">
								<select class="form-control" name="dId" id="dept_update_select">
								</select>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="emp_update_btn">更新</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->


	<!-- 新增按钮的弹出框 -->
	<div class="modal fade" tabindex="-1" role="dialog" id="empAddModal"
		data-backdrop="static">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">添加员工</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">empName</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="empName_input"
									placeholder="员工姓名" name="empName"> <span
									class="help-block"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">email</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="email_add_input"
									placeholder="xxx@gmail.com" name="email"> <span
									class="help-block"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">gender</label>
							<div class="col-sm-10">
								<label class="radio-inline"> <input type="radio"
									name="gender" id="gender_add_input" value="M" checked="checked">
									男
								</label> <label class="radio-inline"> <input type="radio"
									name="gender" id="gender_add_input" value="F"> 女
								</label>
							</div>
						</div>

						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">department</label>
							<div class="col-sm-4">
								<select class="form-control" name="dId" id="dept_add_select">
								</select>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="emp_save_btn">保存</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h1>SSM-CRUD</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-primary" id="emp_add_modal_btn">新增</button>
				<button class="btn btn-danger" id="emp_del_all_btn">删除</button>
			</div>
		</div>
		<div class="row">
			<table class="table table-hover" id="emp_table">
				<thead>
					<tr>
						<th><input type="checkbox" id="check_all"></input></th>
						<th>#</th>
						<th>lastName</th>
						<th>email</th>
						<th>gender</th>
						<th>deptName</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>

				</tbody>

			</table>
		</div>
		<div class="row">
			<div class="col-md-6" id="page_info_area"></div>
			<div class="col-md-6" id="page_nav_area"></div>
		</div>
	</div>
</body>
<script type="text/javascript">
	//总记录数和当前页数
	var totalRecord, currentPage;
	//当页面家在加载完成以后
	$(function() {
		//加载完成之后首先去第一页
		to_page(1);
	});

	function to_page(pn) {
		//发送一个ajax请求
		$.ajax({
			type : "GET",
			url : "${APP_PATH}/emp",
			data : "pn=" + pn,
			success : function(result) {
				//console.log(result);
				//1、解析并展示员工信息
				build_emp_table(result);
				//2、解析并显示分页信息
				build_page_info(result);
				//3、解析并显示分页条数据展示
				build_page_nav(result);
			}
		});
	}

	//新增按钮触发弹出框出现
	function modal_emp_add() {
		$("emp_add").click(function() {
			$("#modal_add").modal(options);
		});
	}
	/*
	<button class="btn btn-primary btn-sm">
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
								编辑
							</button>
		<button class="btn btn-danger btn-sm">
		<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
		删除
	</button>
	 */
	function build_emp_table(result) {
		$("#emp_table tbody").empty();
		var emp = result.extend.pageInfo.list;
		$
				.each(
						emp,
						function(index, item) {
							var checkBoxTd = $("<td><input type='checkbox' class='check-item'></input></td>");
							var empIdTd = $("<td></td>").append(item.empId);
							var empNameTd = $("<td></td>").append(item.empName);
							var gender = item.gender == "M" ? "男" : "女";
							var genderTd = $("<td></td>").append(gender);
							var emailTd = $("<td></td>").append(item.email);
							var deptNameTd = $("<td></td>").append(
									item.dept.deptName);
							//修改按钮
							var editBtn = $("<button></button>")
									.append("修改")
									.addClass("btn btn-primary btn-sm edit-btn")
									.append("<span></span>").addClass(
											"glyphicon glyphicon-pencil").attr(
											"aria-hidden", "true");
							//为edit-btn添加自定义属性edit-id,添加属性值
							editBtn.attr("edit-id", item.empId);
							//删除按钮
							var delBtn = $("<button></button>").append("删除")
									.addClass(
											"btn btn-danger btn-sm delete-btn")
									.append("<span></span>").addClass(
											"glyphicon glyphicon-trash").attr(
											"aria-hidden", "true").attr(
											"delete-id", item.empId);
							delBtn.attr("delete-id", item.empId);
							//添加按钮到<tr></tr>标签中
							var btn = $("<td></td>").append(editBtn).append(
									"&nbsp;").append(delBtn);
							$("<tr></tr>").append(checkBoxTd).append(empIdTd)
									.append(empNameTd).append(genderTd).append(
											emailTd).append(deptNameTd).append(
											btn).appendTo("#emp_table tbody");

						});
	}

	function build_page_info(result) {
		$("#page_info_area").empty();
		$("#page_info_area").append(
				"当前" + result.extend.pageInfo.pageNum + "页," + "总"
						+ result.extend.pageInfo.pages + "页," + "总共有"
						+ result.extend.pageInfo.total + "条记录");
		totalRecord = result.extend.pageInfo.total;
		currentPage = result.extend.pageInfo.pageNum;
	}
	//显示分页条
	function build_page_nav(result) {
		$("#page_nav_area").empty();
		//显示当前页和总页数
		//构建ul实现
		var page_Num = result.extend.pageInfo.pageNum;
		var pages = result.extend.pageInfo.pages;
		var ul = $("<ul></ul>").addClass("pagination");
		//首页
		var firstPageLi = $("<li></li>").append(
				$("<a></a>").append("首页").attr("href", "#"));
		//上一页
		var prePageLi = $("<li></li>").append(
				$("<a></a>").append(
						$("<span></span>").attr("aria-hidden", "true").append(
								"&laquo;")).attr({
					"href" : "#",
					"aria-label" : "Previous"
				}));

		if (result.extend.pageInfo.hasPreviousPage == false) {
			firstPageLi.addClass("disabled");
			prePageLi.addClass("disabled");
		} else {
			firstPageLi.click(function() {
				to_page(1);
			});

			prePageLi.click(function() {
				to_page(page_Num - 1);
			});
		}
		//下一页
		var nextPageLi = $("<li></li>").append(
				$("<a></a>").append(
						$("<span></span>").append("&raquo;").attr(
								"aria-hidden", "true")).attr({
					"href" : "#",
					"aria-label" : "Next"
				}));

		//末页
		var lastPageLi = $("<li></li>").append(
				$("<a></a>").append("尾页").attr("href", "#"));
		var navigatepageNums = result.extend.pageInfo.navigatepageNums;

		if (result.extend.pageInfo.hasNextPage == false) {
			nextPageLi.addClass("disabled");
			lastPageLi.addClass("disabled");
		} else {
			nextPageLi.click(function() {
				to_page(page_Num + 1);
			});
			lastPageLi.click(function() {
				to_page(pages);
			});

		}
		//构造分页
		ul.append(firstPageLi).append(prePageLi);
		$.each(navigatepageNums, function(index, item) {
			var numLi = $("<li></li>").append(
					$("<a></a>").attr("href", "#").append(item));
			if (result.extend.pageInfo.pageNum == item) {
				numLi.addClass("active");
			}
			numLi.click(function() {
				to_page(item);
			});
			ul.append(numLi);
		});
		ul.append(nextPageLi);
		ul.append(lastPageLi);
		var navEle = $("<ul></ul>").append(ul).attr("aria-label",
				"Page navigation");
		navEle.appendTo("#page_nav_area");
	}

	//点击新增按钮，弹出对话框
	$("#emp_add_modal_btn").click(function() {
		//reset是DOM对象的方法，不是jQuery的方法，所以要先取出DOM对象
		$("#empAddModal form")[0].reset();
		//发送ajax请求，查出部门信息，显示在下拉列表中
		getDepts();
		//设置点击弹窗以外的地方时，弹窗不会消失
		$("#empAddModal").modal({
			backdrop : "static"
		});
	});

	//
	function getDepts(ele) {
		$(ele).empty();
		$.ajax({
			type : "POST",
			url : "${APP_PATH}/getDepts",
			data : "",
			success : function(result) {
				var deptJson = result.extend.depts;
				//console.log(deptJson);
				$.each(deptJson, function(index, item) {
					$(ele).append(
							$("<option></option>").append(item.deptName).attr(
									"value", item.deptId));
				});

			}
		});
	}

	//根据后台传过来的数据，构建selection下的option数据
	function build_dept_select(result) {
		$("#dept_add_input").empty();
		var deptJson = result.extend.depts;
		//console.log(deptJson);
		$.each(deptJson, function(index, item) {
			$("#empAddModal select").append(
					$("<option></option>").append(item.deptName).attr("value",
							item.deptId));
		});

	}

	/*
	前端校验
	对用户名的长度和格式进行限制
	以及判断邮箱格式是否合格
	 */
	function validate_add_form() {
		var empName = $("#empName_input").val();
		var regEmpName = /(^[a-zA-Z0-9_-]{4,10}$)|(^[\u2E80-\u9FFF]{2,5}$)/;
		if (!regEmpName.test(empName)) {
			//alert("用户名可以是2-5个中文组合或者4-10个英文数字组合");
			show_validate_msg("#empName_input", "error",
					"用户名可以是2-5个中文组合或者4-10个英文数字组合");
			return false;
		} else {
			show_validate_msg("#empName_input", "success", "");
		}

		var email = $("#email_add_input").val();
		var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
		if (!regEmail.test(email)) {
			//alert("邮箱格式不正确例如xxxx@gmail.com");
			show_validate_msg("#email_add_input", "error",
					"邮箱格式不正确例如xxxx@gmail.com");
			return false;
		} else {
			show_validate_msg("#email_add_input", "success", "");
		}
		return true;
	}

	//抽取方法，对例如这些基本操作进行
	function show_validate_msg(ele, status, msg) {
		//在每次调用该方法时，清除原来父类的样式,以及span标签中的数据
		$(ele).parent().removeClass("has-success has-error");
		$(ele).next("span").text("");
		if ("success" == status) {
			$(ele).parent().addClass("has-success");
			$(ele).next("span").text(msg);
		} else if ("error" == status) {
			$(ele).parent().addClass("has-error");
			$(ele).next("span").text(msg);
		}
	}

	//发送ajax请求，判断用户名是否重复
	$("#empName_input").change(function() {
		var empName = this.value;
		$.ajax({
			type : "POST",
			url : "${APP_PATH}/checkUser",
			data : "empName=" + empName,
			success : function(result) {
				if (200 == result.code) {
					show_validate_msg("#empName_input", "error", "用户名不可用！");
					//设置emp_save_btn的ajax属性
					$("#emp_save_btn").attr("ajax-va", "error");
				} else if (100 == result.code) {
					show_validate_msg("#empName_input", "success", "用户名可用！");
					$("#emp_save_btn").attr("ajax-va", "success");

				}

			}
		});
	});
	//发送ajax请求进行保存
	$("#emp_save_btn")
			.click(
					function() {
						//在进行保存前，先进行校验
						if (!validate_add_form()) {
							return false;
						}

						//在检验了数据是否符合规则以后
						//根据emp_save_btn的ajax属性来判断ajaxs是否满足生效
						if ($(this).attr("ajax-va") == "error") {

							return false;
						}

						$
								.ajax({
									type : "POST",
									url : "${APP_PATH}/emp",
									data : $("#empAddModal form").serialize(),
									success : function(result) {
										//首先根据状态码来判断是否处理成功
										if (result.code == 100) {
											//1、处理完成以后，关闭模态弹出框
											$("#empAddModal").modal("hide");
											//2、并显示最后一页的数据
											to_page(totalRecord);
										} else {

											console.log(result);
											//判断员工信息是否正确
											//有哪个错误信息就显示哪个的
											if (result.extend.fieldErrors.empName != "undefined") {
												show_validate_msg(
														"#email_add_input",
														"error",
														result.extend.fieldErrors.email);
											} else {
												show_validate_msg(
														"#empName_input",
														"error",
														result.extend.fieldErrors.empName);

											}
										}

									}
								});
					});

	//jQuery按钮事件绑定
	//因为时间是在数据加载完成之前就已经绑定了的，所以绑定事件无效
	//1）、在创建按钮的时候就绑定事件，
	//2)、使用jQuery中的live()方法，只在jQuery低版本中使用，新版本中使用on替代
	$(document).on("click", ".edit-btn", function() {
		//加载部门信息数据
		getDepts("#empUpdateModal select");
		getEmp($(this).attr("edit-id"));
		//将edit-btn的值传递给更新按钮
		$("#emp_update_btn").attr("edit-id", $(this).attr("edit-id"));
		$("#empUpdateModal").modal({
			backdrop : "static"
		});
	});

	//根据员工id得到员工信息
	function getEmp(id) {
		$
				.ajax({
					type : "GET",
					url : "${APP_PATH}/emp/" + id,
					success : function(result) {
						var empData = result.extend.emp;
						$("#empName_update_static").text(empData.empName);
						$("#email_update_input").val(empData.email);
						$("#empUpdateModal input[name=gender]").val(
								[ empData.gender ]);
						$("#empUpdateModal select").val([ empData.dId ]);
					}
				});
	}

	//更新员工信息
	$("#emp_update_btn").click(function() {
		$.ajax({
			type : "PUT",
			url : "${APP_PATH}/emp/" + $(this).attr("edit-id"),
			data : $("#empUpdateModal form").serialize(),
			success : function(result) {
				if (result.code == 100) {
					//1、修改完成之后关闭模态框
					$("#empUpdateModal").modal("hide");
					//2、同时显示到修改的页面
					to_page(currentPage);
				}
			}
		});
	});

	//给删除按钮添加事件
	$(document).on("click", ".delete-btn", function() {
		//1、弹出确认框是否删除员工
		var empName = $(this).parents("tr").find("td:eq(2)").text();
		if (confirm("你确定要删除【" + empName + "】吗？")) {
			$.ajax({
				type : "DELETE",
				url : "${APP_PATH}/emp/" + $(this).attr("delete-id"),
				success : function(result) {
					alert(result.msg);
					//返回当前页
					to_page(currentPage);
				}
			});
		}

	});

	//设置全选和全不选
	/*
	注意原生DOM和jQuery自定义的属性区别，
	原生DOM主要使用jQuery()的prop()方法，
	自定义的属性主要使用attr()方法。
	使用prop方法修改原生DOM属性
	 */
	$("#check_all").click(function() {
		//alert($(this).prop("checked"));

		$(".check-item").prop("checked", $(this).prop("checked"));
	});

	$(document).on("click", ".check-item", function() {
		//首先判断选中的个数是否为当前页显示的记录数
		var flag = $(".check-item:checked").length == $(".check-item").length;
		$("#check_all").prop("checked", flag);
	});

	/*
		删除事件
	 */
	$("#emp_del_all_btn").click(function() {
		//弹出确认框,使用$.each()循环
		var empIds = "";
		var empNames = "";
		$.each($(".check-item:checked"), function() {
			empIds += $(this).parents("tr").find("td:eq(1)").text() + "-";
			empNames += $(this).parents("tr").find("td:eq(2)").text() + ",";
		});
		//去除empNames多余的,
		empNames = empNames.substring(0, empNames.length - 1);
		//去除empIds中多余的-
		empIds = empIds.substring(0, empIds.length - 1);
		if (confirm("确定要删除【" + empNames + "】吗？")) {
			$.ajax({
				type : "DELETE",
				url : "${APP_PATH}/emp/" + empIds,
				success : function(result) {
					alert(result.msg);
					//删除完以后再次返回当前页面
					to_page(currentPage);
				}
			});
		}

	});
</script>
</html>