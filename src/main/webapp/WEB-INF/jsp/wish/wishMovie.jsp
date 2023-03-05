<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../include/mypageBox.jsp" />

<div class="my-info-movie mt-4">
	<div class="d-flex justify-content-start p-3">
		<h1 class="">보고싶은 영화</h1>
	</div>
	<div class="review-movie">
		<div class="d-flex flex-wrap justify-content-start ml-2">
			<c:forEach var="movie" items="${wishMovieList}">
				<a href="/search/detail_movie_view?movieCd=${movie.movieCd}"
					class="my-page-number text-dark">
					<div class="card m-2 review-movie"
						style="width: 200px; height: 300px;">
						<img class="card-img-top" src="${movie.image}"
							alt="${movie.movieNm}" width="150" height="210">
						<div class="card-body pt-2">
							<div class="d-flex justify-content-around align-items-center">
								<p class="card-title ml-1 mt-1">${movie.movieNm}</p>
							</div>
						</div>
					</div>
				</a>
			</c:forEach>
		</div>
	</div>
</div>