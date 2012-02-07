<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><%@ include file="/include/tags.jspf" 
%><!DOCTYPE html>
<html>
  <head>
    <title>사용자 관리</title>
    <%@ include file="/include/header.jspf" %>
  </head>
  <body>
        <%@ include file="/include/top.jspf" %>
        
        <div id="main">
		  <form name="userForm" method="post" action="createAction.jsp">
		  <table>
			  <tr>
				<td>사용자 아이디</td>
				<td>
					<input type="text" name="userId">
				</td>
			  </tr>
			  <tr>
				<td>비밀번호</td>
				<td>
					<input type="password" name="password">
				</td>
			  </tr>
			  <tr>
				<td>이름</td>
				<td>
					<input type="text" name="name">
				</td>
			  </tr>
			  <tr>
				<td>이메일</td>
				<td>
					<input type="text" name="email">
				</td>
			  </tr>			  
		  </table>
		  <input type="submit" value="사용자추가" />
		  </form>
        </div>
        
        <%@ include file="/include/footer.jspf" %> 
  </body>
</html>