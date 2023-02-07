<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="my-info mt-4 d-flex">
		<div class="d-flex align-items-center h-100">
			<div class="ml-4">
				<img alt="" src="https://cdn.pixabay.com/photo/2021/07/25/08/03/account-6491185_1280.png"
				height="200" width="200" class="">
				<div class="update-group d-none">
					<button type="button" class="btn btn-type1 text-white ml-5 ">+</button>
				</div>
			</div>
		</div>
		<div class="p-4 col-9 ml-3 mt-3">
			<div class="d-flex justify-content-between col-12">
				<div class="ml-4">
					<h1 class="userNickName">유저 닉네임</h1>
					<div class="d-none update-group">
						<div class="d-flex">
							<input type="text" class="form-control" placeholder="수정할 닉네임을 입력하세요.">
							<button type="button" class="btn btn-type1 text-white">수정</button>
						</div>
					</div>
				</div>
				<div>
					<button type="button" id="update-btn" class="border-0 bg-transparent mr-3">
						<img alt="수정버튼" src="https://cdn.pixabay.com/photo/2021/07/25/08/05/add-6491195_1280.png"
						width="50" height="50">
					</button>
				</div>
			</div>
			<div class="col-12 h-75 d-flex align-items-center justify-content-around">
				<a href="#" class="my-page-number text-dark">
					<div class="text-center mb-2">100</div>
					<div>내가 본 영화</div>
				</a>
				<a href="#" class="my-page-number text-dark">
					<div class="text-center mb-2">100</div>
					<div>보고싶은 영화</div>
				</a>
				<a href="#" class="my-page-number text-dark">
					<div class="text-center mb-2">3</div>
					<div>보유티켓</div>
				</a>
				<a href="#" class="my-page-number text-dark">
					<div class="text-center mb-2">100</div>
					<div>팔로우</div>
				</a>
				<a href="#" class="my-page-number text-dark">
					<div class="text-center mb-2">100</div>
					<div>팔로잉</div>
				</a>
				<div>
					<button type="button" class="btn btn-type1 text-white">팔로우</button>
				</div>
			</div>
	</div>
</div>
	
<script>
	$(document).ready(function() {
		$('#update-btn').on('click', function() {
			//alert(1111);
			if ($('.update-group').hasClass('d-none')) {
				$('.update-group').removeClass('d-none');
				$('.userNickName').addClass('d-none');
			} else {
				$('.update-group').addClass('d-none');
				$('.userNickName').removeClass('d-none');
			}
		});
	});
</script>