<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="position-center">
	<div class="col-12 text-center">
		<a href="/main" class="logo text-dark">MoVie</a>
	</div>
	<div class="col-12 pt-2 d-flex justify-content-center mb-3">
		<input type="text" class="form-control col-8" id="search" placeholder="영화를 입력하세요.">
		<button type="button" class="btn btn-type1 text-white" id="search-btn">검색</button>
	</div>
	
	<div class="d-flex my-movie-box mb-4"><!-- 영화 박스 -->
			<div class="movie-poster p-4 ml-3 d-flex align-items-center">
				<img alt="영화 이름" src="${result.image}" 
				width="250" height="350">
			</div>
			<div class="col-10">
				<div class="d-flex align-items-end h-75">
					<div>
						<div class="display-4 font-weight-bold">${result.movieNm}</div>
						<h5 class="ml-2">${result.movieNmEn}</h5>
						<div class="ml-2">${result.genreNm} | <fmt:formatNumber value="${result.showTm / 60}" pattern="0" />시간 ${result.showTm % 60}분 | ${result.nationNm}</div>
						<div class="ml-2">개봉일:${result.openDt}</div>
						<h4 class="ml-2 mt-3">${result.director}</h4>
						<h5 class="ml-2 mt-3">${result.actor}</h4>
					</div>
				</div>
				<div class="col-10 d-flex align-items-center justify-content-between mt-3">
					<div class="d-flex justify-content-around col-6 mr-4">
						<a href="#" class="my-page-number text-dark">
							<div class="text-center mb-2">3000</div>
							<div class="ml-1">추천</div>
						</a>
						<a href="#" class="my-page-number text-dark">
							<div class="text-center mb-2">1000</div>
							<div>보고싶어요</div>
						</a>
						<a href="#" class="my-page-number text-dark">
							<div class="text-center mb-2">4.3</div>
							<div>평균평</div>
						</a>
					</div>
					<div>
						<button type="button" id="review-btn" class="btn btn-type1 text-white">리뷰작성</button>
						<button type="button" class="btn btn-type3 text-dark">보고싶어요</button>
					</div>
				</div>
			</div>
	</div>
	
	<div class="write-box d-none">
	<h2 class="pl-3">리뷰작성</h2>
	<div class="d-flex justify-content-around align-items-center border border-secondary border-3 mb-3 ml-4
	">
		<div class="h3 ml-3"><span class="reviewUser">userId</span></div>
		
		<!-- <form class="" name="myform" id="myform" method="post"> -->
		<div class="myform">
			<fieldset>
				<input type="radio" name="reviewStar" value="5" id="rate1" class="starValue">
				<label for="rate1">★</label>
				<input type="radio" name="reviewStar" value="4" id="rate2" class="starValue">
				<label for="rate2">★</label>
				<input type="radio" name="reviewStar" value="3" id="rate3" class="starValue">
				<label for="rate3">★</label>
				<input type="radio" name="reviewStar" value="2" id="rate4" class="starValue">
				<label for="rate4">★</label>
				<input type="radio" name="reviewStar" value="1" id="rate5" class="starValue">
				<label for="rate5">★</label>
			</fieldset>
		</div>
		<!-- </form>		 -->
		<textarea rows="5" class="form-control col-6" id="review"></textarea>
		<button type="button" id="reviewWriteBtn" class="btn btn-type1 text-white">작성</button>
	</div>
	</div>
	<div class="update-box d-none">
	<h2 class="pl-3">리뷰수정</h2>
	<div class="d-flex justify-content-around align-items-center border border-secondary border-3 mb-3 ml-4">
		<div class="h3 ml-3">userId</div>
		
		
		<input type="text" class="form-control col-5" placeholder="리뷰를 입력해주세요.">
		<button type="button" class="btn btn-type1 text-white">작성</button>
	</div>
	</div>
	
	<div class="review-box">
		<div class="d-flex justify-content-start p-4">
			<h1>리뷰</h1>
		</div>
		<div class="col-12">
			<c:forEach var="review" items="${reviewList}">
			<table class="table text-center ml-4">
				<tr class="border border-1">
					<td>vimm0824</td>
					<td>${review.point}</td>
					<td>
						${review.review}
					</td>
					<td>
						<button type="button" class="btn btn-type1 text-white update-btn">
							<img alt="수정" src="https://cdn.pixabay.com/photo/2014/04/02/11/17/pencil-305800_1280.png"
								width="20" height="30">
						</button>
						<button type="button" class="btn btn-type3 text-dark">
							<img alt="삭제" src="https://cdn.pixabay.com/photo/2014/03/25/16/59/cancel-297784_1280.png"
								width="30" height="30">
						</button>
					</td>
				</tr>
			</table>
			</c:forEach>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
			
		let point = 0;
		$('input[name=reviewStar]').on('change', function() {
			point = $('input[name=reviewStar]:checked').val();
		});
		$('#reviewWriteBtn').on('click', function() {
			if (point == 0) {
				alert("평점 부탁드립니다.");
				return false;
			}
			let review = $('#review').val();
			let movieCd = ${result.movieCd};
			
			$.ajax({
				type:"post"
				, url:"/review/add_review"
				, data:{"movieCd":movieCd,"point":point,"review":review}
			
				, success:function(data) {
					if (data.code == 1) {
						location.reload();
					} else {
						alert(data.result);
					}
				}
				, error:function(e) {
					alert("ajax error!!!" + e);
				}
			});
		});
		
		// 리뷰작성 버튼
		$('#review-btn').on('click', function() {
			if($('.update-box').hasClass("d-none") == true) {
				if ($('.write-box').hasClass("d-none") == true) {
					$('.write-box').removeClass("d-none")
				} else {
					$('.write-box').addClass("d-none")
				}
			} else { 
				alert("리뷰 수정중에는 작성할수 없습니다.");
			}
		});
		
		// 리뷰수정 버틎
		$('.update-btn').on('click', function() {
			if($('.write-box').hasClass("d-none") == true) {
				if ($('.update-box').hasClass("d-none") == true) {
					$('.update-box').removeClass("d-none")
				} else {
					$('.update-box').addClass("d-none")
				}
			} else {
				alert("리뷰 작성중에는 수정할수 없습니다.");
			}
		})
	});
</script>