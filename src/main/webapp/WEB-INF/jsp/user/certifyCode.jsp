<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container">
	<div class="col-12 text-center">
		<a href="/main" class="logo text-dark">MoVie</a>
	</div>
	<div class="col-7 position-center">
		<h1 class="text-center">인증번호</h1>
		<input type="text" id="code" class="form-control" placeholder="인증번호를 입력하세요.">
		<div class="d-flex justify-content-center mt-3">
			<button type="button" id="certify-btn" class="btn btn-type1 text-white">인증</button>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		$('#certify-btn').on('click', function() {
			let userId = ${id};
			let code = $('#code').val().trim();
			
			$.ajax({
				type:"get"
				, url:""
			});
			
		})
	});
</script>