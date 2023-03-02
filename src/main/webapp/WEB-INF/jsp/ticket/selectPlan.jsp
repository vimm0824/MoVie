<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="col-12 text-center">
	<a href="/main" class="logo text-dark">MoVie</a>
</div>
<div class="position-center plan-box">
	<div class="d-flex">
		<h1 class="p-4 col-4">예매하기</h1>
		<div id="ticketing" class="d-flex justify-content-end align-items-center col-8">
			
		</div>
	</div>
	<div class="d-flex justify-content-between align-items-center">
		<!-- 영화 -->
		<div class="plan-nav ml-4 movie">
			<div class="p-3"><h1>영화</h1></div>
			<ul class="nav flex-column">
				<c:forEach var="movie" items="${result}" varStatus="status">
				<li class="nav-item font-weight-bold">
					<a href="#" class="nav-link text-dark h3 mt-2 d-flex movie-link"
					data-movie-cd="${movie.movieCd}">
						<div>${status.count}.</div><div>${movie.movieNm}</div>
					</a>
				</li>
				</c:forEach>
			</ul>
		</div>
		<!-- 날짜 -->
		<div class="plan-nav">
			<div class="p-3"><h1>날짜</h1></div>
			<ul id="date-nav" class="nav flex-column"></ul>
		</div>
		<!-- 시간 -->
		<div class="plan-nav mr-4">
			<div class="p-3"><h1>시간</h1></div>
			<ul id="time-nav" class="nav flex-column h-100"></ul>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		$('.movie-link').on('click', function(e) {
			e.preventDefault();
			let movieCd = $(this).data('movie-cd');
			$.ajax({
				url:"/ticket/select_plan_date"
				, success : function(data) {
					$('#date-nav').html(data);
					$('.date-link').attr('data-movie-cd', movieCd);
				}
			});
		})
	});
</script>