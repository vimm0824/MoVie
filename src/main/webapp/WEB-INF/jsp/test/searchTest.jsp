<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="col-md-12">
		<h1>영화 검색</h1>
		<div class="col-md-4">
			<table class="table">
				<thead>
					<tr>
						<th>NO</th>
						<th>title</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="movie" items="${result}" varStatus="status">
					<tr>
						<th>${movie.title}</th>
						<th>
							<img alt="poster" src="${movie.image}">
						</th>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>