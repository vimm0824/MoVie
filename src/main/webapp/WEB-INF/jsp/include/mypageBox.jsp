<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="my-info mt-4 d-flex">
		<div class="d-flex align-items-center h-100">
			<div class="ml-4">
				<c:if test="${result.user.profileUrl eq null}">
				<img alt="" src="https://cdn.pixabay.com/photo/2021/07/25/08/03/account-6491185_1280.png"
				height="200" width="200" class="">
				</c:if>
				<c:if test="${result.user.profileUrl ne null}">
				<img alt="" src="${result.user.profileUrl}"
				height="200" width="200" class="profileImage">
				</c:if>
				<!-- d-none -->
				<div class="update-group d-none">
					<input type="file" id="file" class="d-none" accept=".gif, .jpg, .png, .jpeg">
					<button type="button" id="fileUploadBtn" class="btn btn-type1 text-white ml-5 ">+</button>
				</div>
				<!--  -->
			</div>
		</div>
		<div class="p-4 col-9 ml-3 mt-3">
			<div class="d-flex justify-content-between col-12">
				<div class="ml-4">
					<h1 class="userNickName">${result.user.nickname}</h1>
					<!-- d-none -->
					<div class="d-none update-group">
						<small id="fileName"></small>
						<div class="d-flex">
							<input type="text" class="form-control" id="nickname" placeholder="수정할 닉네임을 입력하세요.">
							<button type="button" id="updateSubmitBtn" class="btn btn-type1 text-white" data-update-id="${result.user.id}">수정</button>
						</div>
					</div>
					<!--  -->
				</div>
				<div>
					<c:if test="${result.user.id eq userId}">
					<button type="button" id="update-btn" class="border-0 bg-transparent mr-3">
						<img alt="수정버튼" src="https://cdn.pixabay.com/photo/2021/07/25/08/05/add-6491195_1280.png"
						width="50" height="50">
					</button>
					</c:if>
				</div>
			</div>
			<div class="col-12 h-75 d-flex align-items-center justify-content-around">
				<a href="#" class="my-page-number text-dark">
					<div class="text-center mb-2">${result.reviewCount}</div>
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
					<div class="text-center mb-2">${result.followCount}</div>
					<div>팔로우</div>
				</a>
				<a href="#" class="my-page-number text-dark">
					<div class="text-center mb-2">${result.followingCount}</div>
					<div>팔로잉</div>
				</a>
				<div>
				<c:choose>
					<c:when test="${result.filedFollow eq false }">
					<button type="button" id="follow-btn" data-follow-id="${result.user.id}" class="btn btn-type1 text-white">팔로우</button>
					</c:when>
					<c:otherwise>
					<button type="button" id="follow-btn" data-follow-id="${result.user.id}" class="btn btn-type3 text-dark">팔로우 취소</button>
					</c:otherwise>
				</c:choose>
				</div>
			</div>
	</div>
</div>
	
<script>
	$(document).ready(function() {
		// +이미지 누르면 화면 바뀌기
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
		
		// +버튼 누르면 file눌리기
		$('#fileUploadBtn').on('click', function() {
			$('#file').click();
		});
		
		// 사진 유효성검사 및 업로드된 파일 이름 노출
		$('#file').on('change', function(e) {
			let fileName = e.target.files[0].name;
			
			// 확장자 유효성 검사
			let ext = fileName.split(".").pop().toLowerCase();
			if (ext != "png" && ext != "jpeg" && ext != "jpg" && ext != "png") {
				alert("이미지 파일만 업로드 할수 있습니다.");
				$('#file').val('');
				$('#fileName').text('');
				return;
			}
			
			$('#fileName').text(fileName);
		});
		// 사진 유효성검사 및 업로드된 파일 이름 노출 끝
		
		// 수정
		$('#updateSubmitBtn').on('click', function() {
			let userId = $(this).data('update-id');
			let nickname = $('#nickname').val().trim();
			let file = $('#file')[0].files[0];
			if (nickname.length < 1 && file == null) {
				alert("수정할 항목이 없습니다.");
				return false;
			}
			
			let formData = new FormData();
			formData.append("userId", userId);
			formData.append("nickname", nickname);
			formData.append("file", $('#file')[0].files[0]);
			
			$.ajax({
				type:"post"
				, url:"/user/update_user"
				, data:formData
				
				, enctype:"multipart/form-data" 
				, processData:false 
				, contentType:false 
				
				, success:function(data) {
					if (data.code == 1) {
						alert(data.result);
						location.reload();
					} else {
						alert(data.result);
						location.href = "/user/sign_in_view";
					}
				}
			
				, error:function(e) {
					alert("ajax error!!");
				}
				
			});
		});
		// 수정 끝
		
		
		// 팔로우 버튼
		$('#follow-btn').on('click', function() {
			let userId = $(this).data('follow-id');
			
			$.ajax({
				type:"get"
				, url:"/follow/" + userId
				
				, success:function(data) {
					if (data.code == 1) {
						location.reload();
					} else {
						alert(data.result);
					}
				} 
				, error:function(e) {
					alert("ajax error" + e);
				}
			});
			
		});
	});
</script>