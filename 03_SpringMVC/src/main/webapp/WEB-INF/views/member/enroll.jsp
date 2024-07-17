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
<title>회원 가입</title>
</head>
<body>
	<h2>회원 가입</h2>
	
	<form action="${ path }/member/enroll" method="post">
		<table>
			<tr>
				<th><label for="userId">아이디</label></th>
				<td><input type="text" id="userId" name="id" placeholder="아이디(4글자 이상)" required/></td>
			</tr>
			<tr>
				<th><label for="userPwd">패스워드</label></th>
				<td><input type="password" id="userPwd" name="password" placeholder="패스워드" required/></td>
			</tr>
			<tr>
				<th><label for="userPwd2">패스워드 확인</label></th>
				<td><input type="password" id="userPwd2" placeholder="패스워드 확인" /></td>
			</tr>
			<tr>
				<th><label for="userName">이름</label></th>
				<td><input type="text" id="userName" name="name" required></td>
			</tr>
			<tr>
				<th><label for="phone">휴대전화</label></th>
				<td><input type="text" id="phone" name="phone" placeholder="(-없이)01011112222" maxlength="11"></td>
			</tr>
			<tr>
				<th><label for="email">이메일</label></th>
				<td><input type="text" id="email" name="email" placeholder="abc@abc.com"></td>
			</tr>
			<tr>
				<th><label for="address">주소</label></th>
				<td><input type="text" id="address" name="address"></td>
			</tr>
			<tr>
				<th>취미</th>
				<td>
					<label><input type="checkbox" name="hobby" value="운동">운동</label>
					<label><input type="checkbox" name="hobby" value="등산">등산</label>
					<label><input type="checkbox" name="hobby" value="독서">독서</label>
					<br>
					<label><input type="checkbox" name="hobby" value="게임">게임</label>
					<label><input type="checkbox" name="hobby" value="여행">여행</label>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="가입">
					<input type="reset" value="초기화">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>