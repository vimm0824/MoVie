<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="m-4">
	<button type="button" id="navbar-toggler" class="border-0 bg-transparent" > 
	<img alt=""src="https://cdn.pixabay.com/photo/2016/03/31/19/14/list-1294835_1280.png"
		width="30" height="30">
	</button> 
</div>

<div class="m-4 d-flex">
	<c:if test="${empty userId}">
	<a href="/user/sign_in_view" class="btn btn-type1 text-white ">로그인</a> 
	<a href="/user/sign_up_view"class="btn btn-type2 text-dark ">회원가입</a>
	</c:if>
	<c:if test="${not empty userId}">
	<a href="/main"class="mr-2">
		<img alt="" src="https://cdn.pixabay.com/photo/2014/04/03/00/38/house-308936_1280.png"
		width="40" height="40">
	</a> 
	<a href="/mypage/mypage_view?userId=${userId}" class="btn btn-type1 text-white">${nickname}</a>
	<a href="/user/sign_out" id="signOutBtn" class="btn btn-type2 text-dark">로그아웃</a>
	</c:if>
</div>

<script>
	$(document).ready(function() {
		$('#navbar-toggler').on('click', function() {
			if ($('#nav-bar').hasClass('d-none')) {
				$('#nav-bar').removeClass('d-none');
			} else {
				$('#nav-bar').addClass('d-none');
			}
		});
	});
</script>
