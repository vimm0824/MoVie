<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach var="time" items="${timeList}" varStatus="status">
	<li class="nav-item font-weight-bold text-center">
		<a href="#" class="nav-link text-dark h4 mt-2 d-flex text-center time-link"
		data-movie-cd="" data-date-string="" data-time-string="${time}">
			${time}
		</a>
	</li>
</c:forEach>

<script>
	$(document).ready(function() {
		$('.time-link').on('click', function(e) {
			e.preventDefault();
			$('#ticketing').empty();
			
			let movieCd = $(this).data('movie-cd');
			let date = $(this).data('date-string');
			let time = $(this).data('time-string');
			let plan = date + " " + time;
			let path = "/ticket/select_seat_view?movieCd=" + movieCd + "&plan=" + plan;
			
			$('#ticketing').append('<a href="#" id="plan-path" class="btn btn-type1 text-white mr-3">좌석선택</a>');
			$('#plan-path').attr('href', path);
		});
	});
</script>