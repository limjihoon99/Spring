<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="path" value="${ pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
</head>
<body>
	<h2>회원 정보</h2>
	
	<form action="${ path }/member/update" method="post">
		<input type="hidden" name="no" value="${ loginMember.no }" />
		
		<table>
			<tr>
				<th><label for="userId">아이디</label></th>
				<td><input type="text" id="userId" name="userId" value="${ loginMember.id }"
						readonly required/></td>
			</tr>
			<tr>
				<th><label for="userName">이름</label></th>
				<td><input type="text" id="userName" name="userName" value="${ loginMember.name }" 
						required></td>
			</tr>
			<tr>
				<th><label for="phone">휴대전화</label></th>
				<td><input type="text" id="phone" name="phone" value="${ loginMember.phone }"
						 maxlength="11"></td>
			</tr>
			<tr>
				<th><label for="email">이메일</label></th>
				<td><input type="text" id="email" name="email" value="${ loginMember.email }"></td>
			</tr>
			<tr>
				<th><label for="address">주소</label></th>
				<td><input type="text" id="address" name="address" value="${ loginMember.address }"></td>
			</tr>
			<tr>
				<th>취미</th>
				<td>
					<label><input type="checkbox" name="hobby" value="운동"
								${ fn:contains(loginMember.hobby, '운동') ? 'checked' : '' }>운동</label>
					<label><input type="checkbox" name="hobby" value="등산"
								${ fn:contains(loginMember.hobby, '등산') ? 'checked' : '' }>등산</label>
					<label><input type="checkbox" name="hobby" value="독서"
								${ fn:contains(loginMember.hobby, '독서') ? 'checked' : '' }>독서</label>
					<br>
					<label><input type="checkbox" name="hobby" value="게임"
								${ fn:contains(loginMember.hobby, '게임') ? 'checked' : '' }>게임</label>
					<label><input type="checkbox" name="hobby" value="여행"
								${ fn:contains(loginMember.hobby, '여행') ? 'checked' : '' }>여행</label>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="정보수정">
					<input type="button" onclick="memberDelete();" value="탈퇴">
				</td>
			</tr>
		</table>
	</form>
	<script>
		function memberDelete() {
			if (confirm("정말로 탈퇴하시겠습니까?")) {
				location.replace('${ path }/member/delete');
			}
		}
	</script>
</body>
</html>