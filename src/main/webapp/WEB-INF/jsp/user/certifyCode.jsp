<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container">
	<div class="col-12 text-center">
		<a href="/main" class="logo text-dark">MoVie</a>
	</div>
	<div class="col-7 position-center">
		<h1 class="text-center">인증번호</h1>
		<input type="text" id="code" class="form-control" placeholder="인증번호를 입력하세요.">
		<small class="text-center ml-2">3분안에 인증하셔야 합니다.</small>
		<div class="d-flex justify-content-center mt-3">
			<button type="button" id="certify-btn" class="btn btn-type1 text-white">인증</button>
		</div>
	</div>
	
	<div id="change-box" class="col-7 position-center mt-1 pt-4 pb-4 border d-none">
		<h1 class="text-center">새로운 비밀번호</h1>
		<input type="password" id="password" class="form-control mb-3" placeholder="비밀번호를 입력하세요.">
		<input type="password" id="passwordCol" class="form-control" placeholder="비밀번호 확인을 입력하세요.">
		<div class="d-flex justify-content-center mt-3">
			<button type="button" id="change-btn" class="btn btn-type1 text-white">변경</button>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		let plag = false;
		let userId = ${id};
		$('#certify-btn').on('click', function() {
			let code = $('#code').val().trim();
			
			$.ajax({
				type:"post"
				, url:"/user/certify_code"
				, data:{"userId":userId,"code":code}
			
				,success:function(data) {
					if (data.code == 1) {
						alert(data.result);
						flag = true;
						$('#change-box').removeClass('d-none');
					} else {
						alert(data.result);
						location.href = "/user/find_password_view"
					}
				}
				, error:function(e) {
					alert("ajax error!!!");
				}
			});
			
		})
		
		$('#change-btn').on('click', function() {
			let password = $('#password').val().trim();
			let passwordCol = $('#passwordCol').val().trim();
			let passwordCheck = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
			
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
			
			$.ajax({
				type:"post"
				, url:"/user/change_password"
				, data:{"userId":userId, "password":password}
			
				,success:function(data) {
					if (flag == true) {
						if (data.code == 1) {
							alert(data.result);
							location.href = "/user/sign_in_view";
						} else {
							alert(data.result);
							location.href = "/user/find_password_view";
						}
					} else {
						alert("다시 시도 하세요.");
						location.href = "/user/find_password_view";
					}
				}
			});
		});
		
	});
</script>