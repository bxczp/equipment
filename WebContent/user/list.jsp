<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	function deleteuser(id) {
		if(confirm("确认删除？")){
			$.post("${pageContext.request.contextPath}/user/delete.do",{id:id},function(result){
				var result=eval("("+result+")");
				if(result.success){
					alert("删除成功");
					window.location.href="${pageContext.request.contextPath}/user/list.do";
				}
			});
		}
	}
</script>

<div class="row search" >
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/user/list.do" method="post">
	    <div class="input-group" style="width: 300px">
	    <!-- springMVC中的参数自动封装 只要 name值 与 属性名 相同即可，不需要对象名.属性名-->
		      <input type="text" class="form-control" name="userName"  value="${s_user.userName }" placeholder="请输入要查询的用户名...">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
		      </span>
	    </div>
    </form>
  </div>
  <div class="col-md-6" >
  		<button class="btn btn-primary" style="float: right;" onclick="javascript:window.location.href='${pageContext.request.contextPath}/user/preSave.do'">添加</button>
  </div>
</div>
<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
		<tr>
		  	<th>编号</th>
		  	<th>用户名</th>
		  	<th>密码</th>
		  	<th>真实姓名</th>
		  	<th>角色名</th>
		  	<th>所在部门</th>
		  	<th>操作</th>
		  </tr>
		  <c:forEach var="user" items="${userList}" varStatus="status">
		  	<tr>	
				<!-- 是 index+1 不是status+1 -->
		  		<td>${status.index+1 }</td>
		  		<td>${user.userName }</td>
		  		<td>${user.password }</td>
		  		<td>${user.trueName }</td>
		  		<td>${user.roleName }</td>
		  		<td>${user.deptName }</td>
		  		<td>	
		  			<button class="btn-primary btn btn-xs" 
		  			onclick="javascript:window.location.href='${pageContext.request.contextPath}/user/preSave.do?id=${user.id }'">修改</button>&nbsp;&nbsp;
		  			<button class="btn btn-danger btn-xs" 
		  			onclick="javascript:deleteuser(${user.id})">删除</button>
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



