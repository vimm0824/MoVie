<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="my-page">
	<!-- 마이페이지 박스  -->
	<jsp:include page="../include/mypageBox.jsp" />

	<div class="my-info-movie mt-4">
		<div class="d-flex justify-content-between p-3">
			<h1 class="">전체영화</h1>
			<div>
				<button type="button" class="btn btn-type1 text-white desc-btn">최신순</button>
				<button type="button" class="btn btn-type3 text-dark point-btn">평점순</button>
			</div>
		</div>
		<div class="review-movie">
			<div class="d-flex flex-wrap justify-content-start ml-2">
				<c:forEach var="review" items="${reviewList}">
					<a href="/search/detail_movie_view?movieCd=${review.movieCd}"
						class="my-page-number text-dark">
						<div class="card m-2 review-movie"
							style="width: 200px; height: 300px;">
							<img class="card-img-top" src="${review.image}"
								alt="${review.movieNm}" width="150" height="210">
							<div class="card-body pt-2">
								<div class="d-flex justify-content-around align-items-center">
									<p class="card-title ml-1 mt-1">${review.movieNm}</p>
								</div>
								<div class="text-center">
									<c:set var="point" value="${review.point}" />
									<c:forEach begin="1" end="5">
										<c:choose>
											<c:when test="${point > 0.5}">
												<img src="/static/img/star_fill.png" width="20" alt="star">
												<c:set var="point" value="${point - 1}" />
											</c:when>
											<c:when test="${point == 0}">
												<img src="/static/img/star_empty.png" width="20">
											</c:when>
										</c:choose>
									</c:forEach>
								</div>
							</div>
						</div>
					</a>
				</c:forEach>
			</div>
		</div>
	</div>

<script>
		$(document).ready(function() {
			$('.point-btn').on('click', function() {
				let userId = ${result.user.id};
				$.ajax({
					url : "/mypage/point",
					data : {
						"userId" : userId
					},
					success : function(data) {
						$('.review-movie').html(data);
					}
				});
			});

			$('.desc-btn').on('click', function() {
				let userId = ${result.user.id};
				$.ajax({
					url : "/mypage/desc",
					data : {
						"userId" : userId
					},
					success : function(data) {
						$('.review-movie').html(data);
					}
				});
			});

		})
	</script>