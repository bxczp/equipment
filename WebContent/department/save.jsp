<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	
	function checkForm(){
		var deptName=$("#deptName").val();
		if(deptName==null || deptName==""){
			$("#error").html("部门名称不能为空！");
			return false;
		}
		return true;
	}
	
	
	function resetValue(){
		$("#deptName").val("");
		$("#remark").val("");
	}
</script>
</head>
<body>
<!-- bootstrap的面板组件 -->
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">${actionName }</h3>
  </div>
  <div class="panel-body">
<!--  bootstrap的水平排列的表单 -->
    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/department/save.do" onsubmit="return checkForm()">
	  <div class="form-group">
	    <label class="col-sm-2 control-label">部门名称：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="deptName" name="deptName" style="width: 300px" value="${department.deptName }">
	    </div>
	  </div>
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">部门备注：</label>
	    <div class="col-sm-10">
	    	<input type="hidden"  id="id" name="id" value="${department.id }"/>
			<!--自动控制 文本域的大小 -->
	      <textarea class="form-control" rows="4" id="remark" name="remark" >${department.remark }</textarea>
	    </div>
	  </div>
	  <div class="form-group">
		<!--offset 偏移 -->
	    <div class="col-sm-offset-2 col-sm-10">
	      <button type="submit" class="btn btn-primary">保存</button>&nbsp;&nbsp;
	      <button type="button" class="btn btn-primary" onclick="resetValue()">重置</button>&nbsp;&nbsp;
	      <font color="red" id="error"></font>
	    </div>
	  </div>
  </div>
</div>
</body>
</html>