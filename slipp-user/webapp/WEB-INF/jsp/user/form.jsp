<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ include file="/WEB-INF/jsp/include/tags.jspf"%><!DOCTYPE html>
<html>
<head>
<title>사용자 관리</title>
</head>
<body>
	<div id="main">
		<c:set var="method" value="post" />
		<c:if test="${not empty user.userId}">
		<c:set var="method" value="put" />
		</c:if>
		<form:form modelAttribute="user" method="${method}" action="/user">
			<table>
				<tr>
					<td>사용자 아이디</td>
					<td>
						<c:choose>
						<c:when test="${empty user.userId}">
						<form:input path="userId"/>	
						</c:when>
						<c:otherwise>
						${user.userId}
						<form:hidden path="userId"/>
						</c:otherwise>
						</c:choose>
						
					</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><form:password path="password"/></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><form:input path="name"/></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><form:input path="email"/></td>
				</tr>
                <tr>
                    <td>관리자 유무</td>
                    <td><form:checkbox path="admin" value="true"/></td>
                </tr>				
			</table>
			<input type="submit" value="사용자추가" />		
		</form:form>
	</div>
</body>
</html>