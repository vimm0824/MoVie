<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tr>
	<th class="h4 text-center">성인</th>
	<td>
		<select class="form-control select-headcount" id="adult">
			<option selected value="0">인원을 선택해주세요</option>
			<c:forEach begin="1" end="${headcount}" varStatus="status">
				<option value="${status.count}">${status.count}</option>
			</c:forEach>
		</select>
	</td>
	<th class="h4 text-center">어린이</th>
	<td>
		<select class="form-control select-headcount" id="child">
			<option selected value="0">인원을 선택해주세요</option>
			<c:forEach begin="1" end="${headcount}" varStatus="status">
				<option value="${status.count}">${status.count}</option>
			</c:forEach>
		</select>
	</td>
	<th class="h4 text-center">우대</th>
	<td>
		<select class="form-control select-headcount" id="old">
			<option selected value="0">인원을 선택해주세요</option>
			<c:forEach begin="1" end="${headcount}" varStatus="status">
				<option value="${status.count}">${status.count}</option>
			</c:forEach>
		</select>
	</td>
	<td>
		<button type="button" class="btn btn-type1 text-white total-btn" data-head-count="">적용</button>
	</td>
</tr>

<script>
	$(document).ready(function() {
		$('.total-btn').on('click', function() {
			let headcount = Number($(this).data('head-count'));
			let adult = Number($('#adult').val());
			let child = Number($('#child').val());
			let old = Number($('#old').val());
			let total = adult + child + old;
			let price = adult * 15000 + child * 8000 + old * 5000;
			let movieCd = $('#ticketing').data('movie-cd');
			let plan = $('#ticketing').data('plan-str');
			
			if (headcount != total) {
				alert("인원수를 잘못선택하셨습니다.");
				return false;
			}
			$('.price').text(price);
			
			$.ajax({
				url:"/ticket/select_seat_num"
				, data:{"headcount":headcount,"movieCd":movieCd,"plan":plan}
			
				, success:function(data) {
					$('.seat-num-view').html(data);
				}
			});
		});
	});
</script>