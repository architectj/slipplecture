<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ include
	file="/WEB-INF/jsp/include/tags.jspf"%><!DOCTYPE html>
<html>
<head>
<title>사용자 관리</title>
</head>
<body>
	<div id="main">
		<form name="userForm" method="post" action="/user/login">
			<table>
				<tr>
					<td>사용자 아이디</td>
					<td><input type="text" name="userId"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="password"></td>
				</tr>
				<c:if test="${not empty error}">
				<tr>
					<td>에러메시지</td>
					<td>${error}</td>
				</tr>				
				</c:if>
			</table>
			<input type="submit" value="로그인" />
		</form>
	</div>
</body>
</html>