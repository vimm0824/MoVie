<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<ul class="nav flex-column pt-4 text-center collapse">
	<li class="nav-item font-weight-bold"><a href="/main"
		class="nav-link text-dark h4 lead">Search-MoVie</a></li>
	<li class="nav-item font-weight-bold"><a href="/info/boxoffice_view"
		class="nav-link text-dark h4 lead">BoxOffice</a></li>
	<li class="nav-item font-weight-bold"><a href="/mypage/mypage_view?userId=${userId}"
		class="nav-link text-dark h4 lead">My-Page</a></li>
	<li class="nav-item font-weight-bold"><a href="/wish/wish_movie_view?userId=${userId}"
		class="nav-link text-dark h4 lead">Wish-MoVie</a></li>
	<li class="nav-item font-weight-bold"><a href="/ticket/select_plan_view"
		class="nav-link text-dark h4 lead">Ticketing</a></li>
	<li class="nav-item font-weight-bold"><a href="/ticket/ticket_box_view?userId=${userId}"
		class="nav-link text-dark h4 lead">Ticket-Box</a></li>
	<li class="nav-item font-weight-bold"><a href="/gift/gift_box_view?userId=${userId}"
		class="nav-link text-dark h4 lead">Gift-Box</a></li>
</ul>