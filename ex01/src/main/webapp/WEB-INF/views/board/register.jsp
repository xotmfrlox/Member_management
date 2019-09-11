<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@include file="../include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form role="form" method="post">
		<div class="boax-body">
			<label for="exampleInputEmail"></label> <input type="text"
				name='title' class="form-control" placeholder="Enter Title">
		</div>
		<div class="form-group">
			<label for="exampleInputPassword1">Content</label>
			<textarea class="form-control" name="content" rows="3"
				placeholder="Enter ..."></textarea>
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Writer</label> <input type="text"
				name="writer" class="form-control" placeholder="Enter Writer">
		</div>
		<!-- /.box-body -->

		<div class="box-footer">
			<button type="submet" class="btn btn-primary">Submit</button>
		</div>
	</form>

</body>
</html>
<%@include file="../include/footer.jsp"%>