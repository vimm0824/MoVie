<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="container">
	<div class="col-12 text-center">
		<a href="/main" class="domain-logo text-dark">MoVie</a>
	</div>
	<form action="search/movie_view" method="get">
		<div class="d-flex justify-content-center">
			<input type="text" class="form-control col-7"
				placeholder="영화를 입력해주세요." name="search">
		</div>
		<div class="d-flex justify-content-center mt-3">
			<button type="submit" class="btn btn-type1 text-white">검색</button>
		</div>
	</form>
</div>