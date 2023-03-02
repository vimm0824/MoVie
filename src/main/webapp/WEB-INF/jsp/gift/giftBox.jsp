<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../include/mypageBox.jsp" />

<div class="mt-2">
	<h1>선물 받은 티켓</h1>
	<c:choose>
		<c:when test="${empty giftCardList}">
			<div class="my-tickets text-white d-flex align-items-center justify-content-center h1">티켓없다~~~</div>
		</c:when>
		<c:otherwise>
		<c:forEach var="giftCard" items="${giftCardList}">
		<div class="my-tickets mt-3 d-flex">
			<div class="p-3"> 
				<img alt="${giftCard.ticketCard.movieNm}" src="${giftCard.ticketCard.image}"
				width="180" height="234">
			</div>
			<div class="d-flex align-items-end justify-content-between col-9 ml-2 mb-3">
				<div class="mb-3">
					<div class="display-4 font-weight-bold text-white">${giftCard.ticketCard.movieNm}</div>
					<div class="text-white h4">${giftCard.ticketCard.ticket.plan}</div>
					<div class="text-white h4">좌석 : ${giftCard.ticketCard.ticket.seat}</div>
				</div>
				<div class="mb-3 ml-3 d-flex">
					<button type="button" class="btn btn-type2 text-dark delete-btn" 
					data-gift-id="${giftCard.gift.id}">
						거절
					</button>
				</div>
			</div>
		</div>
		<div class="my-ticket-message p-1">
			<h1 class="text-white">메세지</h1>
			<div class="text-center text-white">
				<c:choose>
					<c:when test="${empty giftCard.gift.message}">
					<h2>
						메세지가 없어요...
					</h2>
					</c:when>
					<c:otherwise>
					<h2>
						${giftCard.gift.message}
					</h2>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		</c:forEach>
		</c:otherwise>
	</c:choose>
</div>

<script>
	$(document).ready(function() {
		$('.delete-btn').on('click', function() {
			let id = $(this).data('gift-id');
			
			$.ajax({
				type:"get"
				, url:"/gift/delete_gift"
				, data:{"id":id}
			
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