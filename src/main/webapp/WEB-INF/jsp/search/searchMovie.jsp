<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="position-center">
	<div class="col-12 text-center">
		<a href="/main" class="logo text-dark">MoVie</a>
	</div>
	<div class="col-12 pt-2 d-flex justify-content-center mb-3">
		<input type="text" class="form-control col-8" placeholder="영화를 입력하세요.">
		<button type="button" class="btn btn-type1 text-white">검색</button>
	</div>
	<c:forEach var="movie" items="${result}">
	<div class="d-flex my-movie-box mb-4">
			<div class="movie-poster p-4 ml-3 d-flex align-items-center">
				<img alt="영화 이름" src="${movie.image}" 
				width="250" height="350">
			</div>
			<div class="col-10">
				<div class="d-flex align-items-end h-75">
					<div>
						<div class="display-3 font-weight-bold">${movie.movieNm}</div>
						<h5 class="ml-2">${movie.movieNmEn}</h5>
						<div class="ml-2">${movie.genreAlt} | 상영시간 | 평점</div>
						<h4 class="ml-2 mt-3">${movie.director}</h4>
						<h5 class="ml-2 mt-3">${movie.actor}</h4>
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
					</div>
					<div>
						<button type="button" class="btn btn-type1 text-white">평가</button>
						<button type="button" class="btn btn-type3 text-dark">보고싶어요</button>
					</div>
				</div>
			</div>
	</div>
	</c:forEach>
</div>