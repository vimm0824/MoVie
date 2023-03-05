<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../include/mypageBox.jsp" />

<div class="d-flex justify-content-around mt-3">
	<div class="my-follow-box">
		<h1 class="p-2">팔로우</h1>
		<c:forEach var="follow" items="${followList}">
			<div class="d-flex justify-content-around m-2 border border-white">
				<c:if test="${follow.user.profileUrl eq null}">
				<img alt="${follow.user.nickname}" src="https://cdn.pixabay.com/photo/2021/07/25/08/03/account-6491185_1280.png"
				height="100" width="100" class="profileImage">
				</c:if>
				<c:if test="${follow.user.profileUrl ne null}">
				<img alt="${follow.user.nickname}" src="${follow.user.profileUrl}"
				width="100" height="100" class="profileImage">
				</c:if>
				<div class="mr-3">
					<h1>${follow.user.nickname}</h1>
					<h4>${follow.user.loginId}</h4>
				</div>
				<div class="d-flex align-items-center">
					<a href="/mypage/mypage_view?userId=${follow.user.id}" class="btn btn-type1 text-white">
						마이페이지
					</a>
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="my-follow-box">
		<h1 class="p-2">팔로잉</h1>
		<c:forEach var="following" items="${followingList}">
			<div class="d-flex justify-content-around m-2 border border-white">
				<c:if test="${following.user.profileUrl eq null}">
				<img alt="${following.user.nickname}" src="https://cdn.pixabay.com/photo/2021/07/25/08/03/account-6491185_1280.png"
				height="100" width="100" class="profileImage">
				</c:if>
				<c:if test="${following.user.profileUrl ne null}">
				<img alt="${following.user.nickname}" src="${following.user.profileUrl}"
				width="100" height="100" class="profileImage">
				</c:if>
				<div class="mr-3">
					<h1>${following.user.nickname}</h1>
					<h4>${following.user.loginId}</h4>
				</div>
				<div class="d-flex align-items-center">
					<a href="/mypage/mypage_view?userId=${following.user.id}" class="btn btn-type1 text-white">
						마이페이지
					</a>
				</div>
			</div>
		</c:forEach>
	</div>
</div>