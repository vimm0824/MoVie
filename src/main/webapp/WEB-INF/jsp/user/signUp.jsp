<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<div class="container">
	<div class="col-12 text-center">
		<a href="/main" class="domain-logo text-dark">MoVie</a>
	</div>
	<div class="position-center input-group d-flex justify-content-center col-6">
		<input type="text" id="loginId" class="form-control"
			placeholder="아이디를 입력해주세요.">
		<button type="button" id="idCheckBtn" class="btn btn-type1 text-white">중복확인</button>
	</div>
	<div class="text-center p-0">
		<small id="idCheckExpression" class="text-warning d-none">아이디는 영문자+숫자 조합으로 6~15자리 사용하세요.</small>
		<small id="idCheckDuplicated" class="text-danger d-none">중복된 아이디입니다.</small>
		<small id="idCheckOk" class="text-info d-none">사용 가능한 아이디입니다.</small>
	</div>
	<div class="position-center mt-3 mb-3 d-flex justify-content-center col-6">
		<input type="password" id="password" class="form-control mt-2"
			placeholder="비밀번호를 입력해주세요.">
	</div>
	<div class="position-center mb-3 d-flex justify-content-center col-6">
		<input type="password" id="passwordCol" class="form-control mt-2"
			placeholder="비밀번호 확인">
	</div>
	<div class="position-center mb-3 mb-2 d-flex justify-content-center col-6">
		<input type="text" id="name" class="form-control mt-2"
			placeholder="이름을 입력해주세요.">
	</div>
	<div class="position-center mb-3 d-flex justify-content-center col-6">
		<input type="text" id="nickname" class="form-control mt-2"
			placeholder="닉네임을 입력해주세요.">
	</div>
	<div class="mt-4 d-flex justify-content-center">
		<button type="button" id="signUp" class="btn btn-type1 text-white col-5">회원가입</button>
	</div>
</div>

<script>
	$(document).ready(function() {
		
		$('#idCheckBtn').on('click', function() {
			//alert(111);
			$('#idCheckExpression').addClass('d-none');
			$('#idCheckDuplicated').addClass('d-none');
			$('#idCheckOk').addClass('d-none');
		
			let loginId = $('#loginId').val().trim();
			let loginIdCheck = /^[a-zA-Z0-9]{6,15}$/;
			
			if (loginIdCheck.test(loginId) == false) {
				$('#idCheckExpression').removeClass('d-none');
				return false;
			} 
			
			$.ajax({
				type:"get"
				, url:"/user/is_duplicated_id"
				, data:{"loginId":loginId}
			
				, success:function(data) {
					if (data.result == false) {
						$('#idCheckDuplicated').removeClass('d-none');
					} else if (data.result == true) {
						$('#idCheckOk').removeClass('d-none');
					} else {
						alert("문의 부탁드립니다.");
					}
				}
				, error:function(e) {
					alert("ajax error, 문의 부탁드립니다." + e);
				}
			});
		});
		
		$('#signUp').on('click', function() {
			let loginId = $('#loginId').val().trim();
			let password = $('#password').val().trim();
			let passwordCol = $('#passwordCol').val().trim();
			let passwordCheck = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
			let name = $('#name').val().trim();
			let nickname = $('#nickname').val().trim();
			
			if ($('#idCheckOk').hasClass('d-none')) {
				alert("아이디를 확인해주세요.");
				return false;
			}
			if (password.length < 1) {
				alert("비밀번호를 입력해주세요.");
				return false;
			}
			if (passwordCol.length < 1) {
				alert("비밀번호 확인을 입력해주세요.");
				return false;
			}
			if (password != passwordCol) {
				alert("비밀번호 확인이 맞지 않습니다.");
				return false;
			}
			if (passwordCheck.test(password) == false) {
				alert("비밀번호는 영문자+숫자+특수기호 조합으로 8~25자리 사용하세요");
				return false;
			}
			if (name.length < 1) {
				alert("이름을 입력하세요.");
				return false;
			}
			if (nickname.length < 1) {
				alert("닉네임을 입력하세요.");
				return false;
			}
			
			$.ajax({
				type:"post"
				, url:"/user/sign_up"
				, data:{"loginId":loginId,"password":password,"name":name,"nickname":nickname}
			
				, success:function(data) {
					if (data.code == 1) {
						alert("회원가입을 환영합니다." + nickname + "님");
						location.href = "/user/sign_in_view";
					} else {
						alert(data.errorMessage);
					}
				}
				, error:function(e) {
					alert("ajax error");
				}
			});
		});
		
	});
</script>