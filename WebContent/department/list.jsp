<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	function deleteDepartment(id) {
		if(confirm("确认删除？")){
			$.post("${pageContext.request.contextPath}/department/delete.do",{id:id},function(result){
				var result=eval("("+result+")");
				if(result.success){
					alert("删除成功");
					window.location.href="${pageContext.request.contextPath}/department/list.do";
				}else if(result.error){
					alert(result.error);
					window.location.href="${pageContext.request.contextPath}/department/list.do";
					return ;
				}
			});
		}
	}
</script>

<div class="row search" >
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/department/list.do" method="post">
	    <div class="input-group" style="width: 300px">
	    <!-- springMVC中的参数自动封装 只要 name值 与 属性名 相同即可，不需要对象名.属性名-->
		      <input type="text" class="form-control" name="deptName"  value="${s_department.deptName }" placeholder="请输入要查询的部门...">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
		      </span>
	    </div>
    </form>
  </div>
  <div class="col-md-6" >
  		<button class="btn btn-primary" style="float: right;" onclick="javascript:window.location.href='${pageContext.request.contextPath}/department/preSave.do'">添加</button>
  </div>
</div>
<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
		<tr>
		  	<th>编号</th>
		  	<th>部门名称</th>
		  	<th>部门备注</th>
		  	<th>操作</th>
		  </tr>
		  <c:forEach var="department" items="${departmentList}" varStatus="status">
		  	<tr>	
		  		<td>${status.index+1 }</td>
		  		<td>${department.deptName }</td>
		  		<td>${department.remark }</td>
		  		<td>	
		  			<button class="btn-primary btn btn-xs" 
		  			onclick="javascript:window.location.href='${pageContext.request.contextPath}/department/preSave.do?id=${department.id }'">修改</button>&nbsp;&nbsp;
		  			<button class="btn btn-danger btn-xs" 
		  			onclick="javascript:deleteDepartment(${department.id})">删除</button>
		  		</td>
		  	</tr>
		  </c:forEach>
	</table>
	<nav >
		<ul class="pagination">
			${pageCode }
		</ul>
	</nav>
</div>



