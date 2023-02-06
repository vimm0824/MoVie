<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container">
	<div class="col-12 text-center">
		<a href="/main" class="domain-logo text-dark">MoVie</a>
	</div>
	<div class="d-flex justify-content-center">
		<input type="text" id="loginId" class="form-control col-6"
			placeholder="아이디를 입력해주세요.">
	</div>
	<div class="mt-3 d-flex justify-content-center">
		<input type="password" id="password" class="form-control col-6"
			placeholder="비밀번호를 입력해주세요.">
	</div>
	<div class="mt-3 d-flex justify-content-center">
		<button type="submit" id="signIn" class="btn btn-type1 text-white">로그인</button>
		<a href="/user/sign_up_view" type="button" id="signUp" class="btn btn-type2 text-dark">회원가입</a>
	</div>
</div>

<script>
	$(document).ready(function() {
		$('#signIn').on('click', function() {
			let loginId = $('#loginId').val().trim();
			let password = $('#password').val().trim();
			
			$.ajax({
				type:"post"
				, url:"/user/sign_in"
			});
		});
	});
	
</script>