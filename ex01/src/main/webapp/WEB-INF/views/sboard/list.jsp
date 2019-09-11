<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<%@include file="../include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:forEach items="${list}" var="boardVO">
		<tr>
			<td>${boardVO.bno}</td>
			<td><a
				href='/sboard/readPage${pageMaker.makeSearch(pageMaker.cri.page)}&bno=${boardVO.bno}'>
					${boardVO.title}</a></td>
			<td>${boardVO.writer}></td>
			<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
					value="${boardVO.regdate}" /></td>
			<td><span class="badge bg-red">${boardVO.viewcnt}</span></td>
		</tr>
	</c:forEach>

	<div class="text-center">
		<ul class="pagination">


			<c:if test="${pageMaker.prev}">
				<li><a
					href="listPage${pageMaker.makeQuery(pageMaker.startPage-1)}">&laquo;</a></li>
			</c:if>



			<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}"
				var="idx">
				<li <c:out value="${pageMaker.cri.page == idx?'class=active':''}"/>>
					<a href="listPage${pageMaker.makeQuery(idx)}">${idx}</a>
				</li>
			</c:forEach>



			<c:if test="${pageMaker.next && pageMaker.endPage >0}">
				<li><a
					href="listPage${pageMaker.makeQuery(pageMaker.endPage+1)}">&raquo;</a></li>
			</c:if>
		</ul>

		<ul class="pagination">


			<c:if test="${pageMaker.prev}">
				<li><a
					href="list${pageMaker.makeSearch(pageMaker.startPage-1)}">&laquo;</a></li>
			</c:if>



			<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}"
				var="idx">
				<li <c:out value="${pageMaker.cri.page == idx?'class=active':''}"/>>
					<a href="list${pageMaker.makeSearch(idx)}">${idx}</a>
				</li>
			</c:forEach>



			<c:if test="${pageMaker.next && pageMaker.endPage >0}">
				<li><a href="list${pageMaker.makeSearch(pageMaker.endPage+1)}">&raquo;</a></li>
			</c:if>
		</ul>
	</div>

	<div class='box-body'>

		<select name="searchType">
			<option value="n"
				<c:out value="${cri.searchType == null?'selected':''}"/>>---</option>

			<option value="t"
				<c:out value="${cri.searchType eq 't'?'selected':''}"/>>Title</option>

			<option value="c"
				<c:out value="${cri.searchType eq 'c'?'selected':''}"/>>Content</option>

			<option value="w"
				<c:out value="${cri.searchType eq 'w'?'selected':''}"/>>Writer</option>

			<option value="tc"
				<c:out value="${cri.searchType eq 'tc'?'selected':''}"/>>Title
				OR Content</option>

			<option value="cw"
				<c:out value="${cri.searchType eq 'cw'?'selected':''}"/>>Content
				OR Writer</option>

			<option value="tcw"
				<c:out value="${cri.searchType eq 'tcw'?'selected':''}"/>>Title
				OR Content OR Writer</option>
		</select>
	</div>

	<input type="text" name='keyword' id="keywordInput"
		value='$(cri.keyword)}'>
	<button id='searchBtn'>Search</button>
	<button id='newBtn'>New Board</button>

	<script>
	$(document).ready(
			function(){
				
				$('#SearchBtn').on(
						"click",
						function(event){
							self.location = "list" '${pageMaker.makeQuery(1)}' "&searchType="$("select option:selected").val()
							"&keyword="encodeURIComponent($('#keywordInput'.val());
						});
							$('#newBtn').on("click", function(evt){
								self.location = "register";
							});
			});
	</script>
4069
</body>
</html>
<%@include file="../include/footer.jsp"%>