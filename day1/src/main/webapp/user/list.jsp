<%@page contentType="text/html; charset=euc-kr" %>
<%@page import="java.util.*" %>
<%@page import="net.slipp.user.*" %>
<%
UserService service = new UserService();
List userList = service.findUserList();
%>
<html>
<head>
<title>����� ����</title>
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
				<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>����� ���� - ����Ʈ</b></td>
		  	</tr>
	  	</table>  
	  	<br>
	  
	  	<!-- list -->
	  	<table border="0" cellpadding="0" cellspacing="1" width="590" bgcolor="BBBBBB">
		  	<tr>
				<td width=190 align=center bgcolor="E6ECDE" height="22">����� ���̵�</td>
				<td width=200 align=center bgcolor="E6ECDE">�̸�</td>
				<td width=200 align=center bgcolor="E6ECDE">�̸���</td>
		  	</tr>
<%
	Iterator userIter = userList.iterator();
	
	//����� ����Ʈ�� Ŭ���̾�Ʈ���� �����ֱ� ���Ͽ� ���.
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
					<input type="submit" value="����� �߰�"/>
				</td>
			</tr>
		</table>		
	</td>
</tr>
</table>  
</form>
</body>
</html>