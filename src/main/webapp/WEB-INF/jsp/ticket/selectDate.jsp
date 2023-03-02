<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach var="date" items="${week}" varStatus="status">
	<li class="nav-item font-weight-bold">
		<a href="#" class="nav-link text-dark h4 mt-2 d-flex text-center date-link"
		data-movie-cd="" data-date-string="${date}">
			<c:choose>
				<c:when test="${date.contains('토요일')}">
				<span class="text-primary">${date}</span>
				</c:when>
				<c:when test="${date.contains('일요일')}">
				<span class="text-danger">${date}</span>
				</c:when>
				<c:otherwise>
				<span class="text-secondary">${date}</span>
				</c:otherwise>
			</c:choose>
		</a>
	</li>
</c:forEach>

<script>
	$(document).ready(function() {
		$('.date-link').on('click', function(e) {
			e.preventDefault();
			let movieCd = $(this).data('movie-cd');
			let date = $(this).data('date-string');
			$.ajax({
				url:"/ticket/select_plan_time"
				, data:{"movieCd":movieCd,"date":date}
				, success : function(data) {
					$('#time-nav').html(data);
					$('.time-link').attr('data-movie-cd', movieCd);
					$('.time-link').attr('data-date-string', date);
				}
			});
		});
	});
</script>