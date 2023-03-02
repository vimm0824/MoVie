<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="text-center mb-4">
	<span class="h3 text-dark">SCREEN</span>
</div>
<div class="pl-3">
	<c:forEach var="h" items="${charList}">
	<div class="d-flex">
		<c:forEach var="w" begin="1" end="12">
		<c:set var="num" value="${h}${w}"/>
		<c:choose>
			<c:when test="${w % 4 == 0}">
				<c:choose>
					<c:when test="${seatList.contains(num)}">
					<a href="#" class="text-dark selected" data-seat-num="${num}">
						<div class="seat-box border border-dark bg-secondary text-center mr-4">
							${num}
						</div>
					</a>
					</c:when>
					
					<c:otherwise>
					<a href="#" class="seat-num text-dark" data-seat-num="${num}">
						<div class="seat-box border border-dark text-center mr-4">
							${num}
						</div>
					</a>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${seatList.contains(num)}">
					<a href="#" class="text-dark selected" data-seat-num="${num}">
						<div class="seat-box border border-dark bg-secondary text-center">
							${num}
						</div>
					</a>
					</c:when>
					<c:otherwise>
					<a href="#" class="seat-num text-dark" data-seat-num="${num}">
						<div class="seat-box border border-dark text-center">
							${num}
						</div>
					</a>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
		</c:forEach>
	</div>
	</c:forEach>
</div>

<script>
	$(document).ready(function() {
		let headcount = ${headcount};
		let seatArr = [];
		$('.selected').on('click', function(e) {
			e.preventDefault();
			alert("이미 선택된 자리입니다.");
		});
		
		$('.seat-num').on('click', function(e) {
			e.preventDefault();
			let seat = $(this).data('seat-num');
			//alert(seat);
			if ($(this).children().hasClass('bg-dark')){
				$(this).children().removeClass('bg-dark');
				headcount++;
			} else {
				if (headcount == 0) {
					alert("인원수 선택 혹은 자리선택을 잘못하였습니다.");
					return false;
				}
				$(this).children().addClass('bg-dark');
				headcount--;
				seatArr.push(seat);
			}
		});
		
		$('#ticketing').on('click',function(e) {
			e.preventDefault();
			let movieCd = $(this).data('movie-cd');
			let plan = $(this).data('plan-str');
			if (headcount != 0) {
				alert("선택이 완료되지 않았습니다.");
				return false;
			}
			
			$.ajax({
				type:"post"
				, url:"/ticket/ticketing"
				, data:{"movieCd":movieCd,"plan":plan,"seatArr":seatArr}
			
				, success:function(data) {
					if (data.code == 1) {
						location.href = "/ticket/ticket_box_view?userId=" + data.userId;
					} else {
						alert(data.result);
					}
				}
				
				, error:function(e) {
					alert("ajax error");
				}
			});
		});
	});
</script>