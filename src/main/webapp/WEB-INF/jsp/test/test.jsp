<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test</title>

<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>  

<!-- bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<!-- 내가 만든 style -->
<link rel="stylesheet" type="text/css" href="/static/css/style.css">
</head>
<body>
	<div class="container">
		<h1>박스오피스</h1>
		
		<div>
			<h3>날짜</h3>
			<input type="text" id="date" class="form-control">
			<button type="button" id="date-box-office" class="btn btn-info">검색</button>
		</div>	
		<div>
			<table class="table">
				<thead>
					<tr>
						<th>NO</th>
						<th>title</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="movie" items="${result.dailyBoxOfficeList}" varStatus="status">
					<tr>
						<th>${movie.rank}</th>
						<th>${movie.movieNm}</th>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<script>
		/* $(document).ready(function() {
			$('#date-box-office').on('click', function() {
				let date = $('#date').val();
				//alert(date);
				
				$.ajax({
					type:"get"
					, url:"/get"
					, data:{"date":date}
				
					,success:function(data) {
						
					}
				});
			})
		}); */
	</script>

</body>
</html>