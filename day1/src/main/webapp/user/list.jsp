<%@page contentType="text/html; charset=euc-kr" %>
<%@page import="java.util.*" %>
<%@page import="net.slipp.user.*" %>
<%
UserService service = new UserService();
List userList = service.findUserList();
%>
<html>
<head>
<title>사용자 관리</title>
<%@  %>
</head>
<body>
<form name="f" method="post" action="user_write.jsp">
<table width=780 border=0 cellpadding=0 cellspacing=0>
<tr>
	<td width="20"></td>
	<td>
	  	<!--contents-->
	  	<table width=590 border=0 cellpadding=0 cellspacing=0>
		  	<tr>
				<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>사용자 관리 - 리스트</b></td>
		  	</tr>
	  	</table>  
	  	<br>
	  
	  	<!-- list -->
	  	<table border="0" cellpadding="0" cellspacing="1" width="590" bgcolor="BBBBBB">
		  	<tr>
				<td width=190 align=center bgcolor="E6ECDE" height="22">사용자 아이디</td>
				<td width=200 align=center bgcolor="E6ECDE">이름</td>
				<td width=200 align=center bgcolor="E6ECDE">이메일</td>
		  	</tr>
<%
	Iterator userIter = userList.iterator();
	
	//사용자 리스트를 클라이언트에게 보여주기 위하여 출력.
	while ( userIter.hasNext() ) {
		User user = (User)userIter.next();
%>		  	
		  	<tr>
				<td width=190 align=center bgcolor="ffffff" height="20">
					<%= user.getUserId() %>
				</td>
				<td width=200 bgcolor="ffffff" style="padding-left:10">
					<a href="user_view.jsp?userId=<%= user.getUserId() %>" class="user">
						<%= user.getName() %>
					</a>
				</td>
				<td width=200 align=center bgcolor="ffffff">
					<%= user.getEmail() %>
				</td>
		  	</tr>
<%
	}
%>		  	
	  	</table>
	  	<!-- /list -->	 

		<br>
		<!-- button -->
	  	<table border="0" cellpadding="0" cellspacing="1" width="590">
			<tr>
				<td align="right">
					<input type="submit" value="사용자 추가"/>
				</td>
			</tr>
		</table>		
	</td>
</tr>
</table>  
</form>
</body>
</html>