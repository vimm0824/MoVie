<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="container">
	<div class="col-12 text-center">
		<a href="/main" class="logo text-dark">MoVie</a>
	</div>
	<div class="position-center d-flex justify-content-center col-6">
		<input type="text" id="loginId" class="form-control" placeholder="아이디">
	</div>
	<div class="position-center d-flex justify-content-center col-6">
		<input type="text" id="name" class="form-control mt-2" placeholder="이름">
	</div>
	<div class="position-center d-flex justify-content-center col-6">
		<input type="text" id="email" class="form-control mt-2"placeholder="이메일">
	</div>
	<div class="mt-4 d-flex justify-content-center">
		<button type="button" id="send-btn" class="btn btn-type1 text-white col-5 mb-3">이메일로 인증 번호 보내기</button>
	</div>
</div>

<script>
	$(document).ready(function() {
		$('#send-btn').on('click', function() {
			let loginId = $('#loginId').val().trim();
			let name = $('#name').val().trim();
			let email = $('#email').val().trim();
			
			if (loginId == "" || email == "" || name == "" ) {
				alert("빈칸없이 입력해주세요.");
			}
			
			$.ajax({
				type:"post"
				, url:"/user/find_password"
				, data:{"loginId":loginId,"email":email,"name":name}
				
				,success:function(data) {
					if (data.code == 1) {
						alert(data.result);
						location.href = "/user/certify_view?userId=" + data.userId;
					} else {
						alert(data.result);
					}
				}
			});
			
		});
	});
</script>
