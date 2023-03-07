<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="col-12 text-center">
	<a href="/main" class="logo text-dark">MoVie</a>
</div>
<form action="/user/search_user_view" method="get" class="col-12 d-flex justify-content-center mb-3">
		<input type="text" class="form-control col-8" name="nickname" id="nickname" placeholder="닉네임을 입력하세요.">
		<button type="submit" class="btn btn-type1 text-white" id="search-btn">검색</button>
</form>

<div class="movie-box">
	<h1 class="text-center">검색 결과</h1>
	<div class="review-movie">
		<div class="d-flex flex-wrap justify-content-start ml-2">
			<c:forEach var="user" items="${userList}">
				<a href="/mypage/mypage_view?userId=${user.id}"
					class="my-page-number text-dark">
					<div class="card m-2 review-movie"
						style="width: 200px; height: 300px;">
						<c:if test="${user.profileUrl eq null}">
						<img class="card-img-top profileImage" src="https://cdn.pixabay.com/photo/2021/07/25/08/03/account-6491185_1280.png"
							alt="${user.nickname}" width="150" height="210">
						</c:if>
						<c:if test="${user.profileUrl ne null}">
						<img class="card-img-top profileImage" src="${user.profileUrl}"
							alt="${user.nickname}" width="150" height="210">
						</c:if>
						<div class="card-body pt-2">
							<div class="text-center">
								<p class="card-title ml-1 mt-1 h3">${user.nickname}</p>
								<p class="card-title ml-1 mt-1">${user.loginId}</p>
							</div>
						</div>
					</div>
				</a>
			</c:forEach>
		</div>
</div>