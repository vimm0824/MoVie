<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="my-tickets mt-3 d-flex">
	<div class="p-3"> 
		<img alt="영화 제목" src="${ticketCard.image}"
		width="180" height="234">
	</div>
	<div class="d-flex align-items-end justify-content-between col-9 ml-2 mb-3">
		<div class="mb-3">
			<input type="hidden" id="ticketId" value="${ticketCard.ticket.id}">
			<div class="display-4 font-weight-bold text-white">${ticketCard.movieNm}</div>
			<div id="plan" class="text-white h4">${ticketCard.ticket.plan}</div>
			<div class="text-white h4">좌석 : <span id="seat">${ticketCard.ticket.seat}</span></div>
		</div>
	</div>
</div>
    
<div class="my-info-movie mt-2">
	<span class="h1">선물하기</span>
	<div class="d-flex justify-content-center">
		<div class="h4 mt-1"><span>받는사람 아이디 : </span></div>
		<input type="text" id="checkId" class="form-control col-5">
		<button type="button" id="idCheckBtn" class="btn btn-type1 text-white">아이디 확인</button>
		<input type="hidden" id="receiverId" value="">
	</div>
	<div class="text-center p-0">
		<small id="idCheckNo" class="text-danger d-none">존재하지 않는 아이디입니다.</small>
		<small id="idCheckOk" class="text-info d-none">전송 가능한 아이디입니다.</small>
	</div>
	<div class="d-flex justify-content-center">
		<div class="ml-2">
			<div class="h4 mt-1">보낼 메세지 : </div>
			<textarea id="message" rows="7" cols="70"></textarea>
		</div>
	</div>
	<div class="d-flex justify-content-end">
		<button type="button" id="send-btn" class="btn btn-type1 text-white mr-5 mb-2" value="">전송</button>
	</div>
</div>

<script>
	$(document).ready(function() {
		let userId = ${userId};
		// 전송
		$('#send-btn').on('click', function() {
			let ticketId = $('#ticketId').val();
			let receiverId = $('#receiverId').val().trim();
			let message = $('#message').val();
			
			//alert(ticketId);
			if (receiverId == 0) {
				alert("전송 가능한 아이디가 없습니다.");
				return false;
			}
			
			if ($('#idCheckOk').hasClass('d-none')) {
				alert("전송 가능한 아이디가 없습니다.");
				return false;
			}
			
			$.ajax({
				type:"post"
				, url:"/gift/ticket"
				, data:{"receiverId":receiverId,"ticketId":ticketId,"message":message}
			
				, success:function(data) {
					if (data.code == 1) {
						location.href = "/ticket/ticket_box_view?userId=" + userId;
					} else {
						alert(data.result);
					}
				}
				, error:function(e) {
					alert("ajax error!!!");
				}
			});
		});
		
		// 아이디 확인
		$('#idCheckBtn').on('click', function() {
			
			$('#idCheckNo').addClass('d-none');
			$('#idCheckOk').addClass('d-none');
			
			let checkId = $('#checkId').val().trim();
			//alert(checkId);
			
			$.ajax({
				type:"get"
				, url:"/user/is_duplicated_id"
				, data:{"loginId":checkId}
			
				, success:function(data) {
					if (data.userId == 0) {
						$('#receiverId').val(0);
						$('#idCheckNo').removeClass('d-none');
					} else {
						$('#receiverId').val(data.userId);
						$('#idCheckOk').removeClass('d-none');
					} 
				}
				, error:function(e) {
					alert("ajax error!!!!");
				}
			});
		});
	});
</script>