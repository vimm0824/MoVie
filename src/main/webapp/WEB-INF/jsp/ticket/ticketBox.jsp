<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../include/mypageBox.jsp" />

<div class="mt-2">
	<h1>내 티켓</h1>
	<c:choose>
		<c:when test="${empty ticketList}">
			<div class="my-tickets text-white d-flex align-items-center justify-content-center h1">티켓없다~~~</div>
		</c:when>
		<c:otherwise>
		<c:forEach var="ticket" items="${ticketList}">
		<c:if test="${ticket.ticket.gift eq false}">
		<div class="my-tickets mt-3 d-flex">
			<div class="p-3"> 
				<img alt="영화 제목" src="${ticket.image}"
				width="180" height="234">
			</div>
			<div class="d-flex align-items-end justify-content-between col-9 ml-2 mb-3">
				<div class="mb-3">
					<div class="display-4 font-weight-bold text-white">${ticket.movieNm}</div>
					<div class="text-white h4">${ticket.ticket.plan}</div>
					<div class="text-white h4">좌석 : ${ticket.ticket.seat}</div>
				</div>
				<div class="mb-3 ml-3 d-flex">
					<a href="/gift/gift_ticket_view?id=${ticket.ticket.id}&userId=${ticket.ticket.userId}" class="btn btn-type3 text-dark gift-btn" 
					data-ticket-id="${ticket.ticket.id}">
						선물하기
					</a>
					<button type="button" class="btn btn-type2 text-dark delete-btn" 
					data-ticket-id="${ticket.ticket.id}">
						삭제
					</button>
				</div>
			</div>
		</div>
		</c:if>
		</c:forEach>
		</c:otherwise>
	</c:choose>
	
</div>
<script>
	$(document).ready(function() {
		
		
		// 삭제 버튼
		$('.delete-btn').on('click', function() {
			let ticketId = $(this).data('ticket-id');
			
			$.ajax({
				type:"delete"
				, url:"/ticket/delete_ticket"
				, data:{"ticketId":ticketId}
			
				, success:function(data) {
					if (data.code == 1) {
						location.reload();
					} else {
						alert(data.result);
					}
				}
				, error:function(e) {
					alert("ajax error!!!");
				}
			});
		});
	});
</script>