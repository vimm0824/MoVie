<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="position-center mr-3">
	<div class="col-12 text-center">
		<a href="/main" class="logo text-dark">MoVie</a>
	</div>
	<div class="movie-box mb-2">
		<div class="p-3">
			<h1>일간 박스오피스</h1>
		</div>
		<div class="d-flex flex-wrap justify-content-between ml-3 mr-3">
			<c:forEach var="movie" items="${result}">
			<div class="card m-2" style="width: 200px;" >
				<img class="card-img-top" src="${movie.image}"
					alt="${movie.movieNm}" width="150" height="210">
				<div class="card-body">
					<div class="d-flex justify-content-around align-items-center">
						<h2>${movie.rank}</h2>
						<p class="card-title ml-1 mt-1">${movie.movieNm}</p>
					</div>
					<div class="col-12 d-flex justify-content-center">
					</div>
					<div class="d-flex justify-content-center align-items-end">
						<a href="#" class="btn btn-type1 text-white">예매하기</a>
						<a href="#" class="btn btn-type2 text-dark" data-movie-id="${movie.movieCd}">상세보기</a>
					</div>
				</div>
			</div>
			</c:forEach>
		</div>
	</div>
</div>