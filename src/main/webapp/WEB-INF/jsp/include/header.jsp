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
	<a href="/user/sign_in_view" class="btn btn-type1 text-white ">로그인</a> 
	<a href="/user/sign_up_view"class="btn btn-type2 text-dark ">회원가입</a> 
	<a href="#"class="mr-2 d-none">
		<img alt="" src="https://cdn.pixabay.com/photo/2021/07/25/08/03/account-6491185_1280.png"
		width="45" height="45">
	</a> 
	<a href="/mypage/mypage_view" class="btn btn-type1 text-white mr-4 d-none">loginId</a>
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
