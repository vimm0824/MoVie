<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="col-12 text-center">
	<a href="/main" class="logo text-dark">MoVie</a>
</div>
<div class="position-center plan-seat-box">
	<div class="d-flex">
		<h1 class="p-4 col-4">예매하기</h1>
	</div>
	<div class="col-8">
		<table class="table">
			<tr>
				<th class="h4">총 인원</th>
				<td><input type="text" class="form-control headcount" placeholder="최대 4인까지 가능"></td>
				<td><button type="button" class="btn btn-type1 text-white headcountBtn">적용</button></td>
			</tr>
		</table>
	</div>
	<table class="table headcount-table col-12"></table>
	<div class="d-flex justify-content-around">
		<div class="seat-view">
			<h2 class="text-white p-2">좌석 정보</h2>
			<div class="seat-num-view position-center">
				
			</div>
		</div>
		<div>
			<div class="movie-view">
				<h2 class="text-white p-2">영화 정보</h2>
				<div class="d-flex justify-content-center align-items-end">
					<div class="text-center mt-3">
						<img alt="${movie.movieNm}" src="${movie.image}" width="200" height="260">
						<div class="h2 text-white mt-2">${movie.movieNm}</div>
						<div class="text-white">${plan}</div>
						<div class="h4 text-white">최종금액 : <span class="price"></span> </div>
					</div>
				</div>
			</div>
			<div class="d-flex justify-content-center mt-4">
				<a href="#" id="ticketing" class="btn btn-type1 text-white mr-3 col-11" 
				data-movie-cd="${movie.movieCd}" data-plan-str="${plan}">예매하기</a>
			</div>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		$('.headcountBtn').on('click', function() {
			let headcount = $('.headcount').val().trim();
			
			if (headcount == "") {
				alert("인원수를 입력해주세요.");
				return false;
			}
			if (isNaN(headcount) == true) {
				alert("숫자만 입력 가능합니다.");
				return false;
			}
			if (headcount > 4 || headcount < 1) {
				alert("최대 4인 최소 1인만 가능합니다.");
				return false;
			}
			
			$.ajax({
				url:"/ticket/select_headcount"
				, data:{"headcount":headcount}
			
				,success:function(data) {
					$('.headcount-table').html(data);
					$('.total-btn').attr('data-head-count', headcount);
				}
				,error:function(e) {
					alert("ajax error!!!");
				}
			});
		});
		
		
	});
</script>