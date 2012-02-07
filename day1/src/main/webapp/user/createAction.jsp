<%@page contentType="text/html; charset=euc-kr" %>
<%@page import="net.slipp.user.*" %>
<%
	String userId = request.getParameter("userId");
	String password = request.getParameter("password");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	
	User user = new User(userId, password, name, email);
	UserService service = new UserService();
	service.create(user);

	response.sendRedirect("index.jsp");
%>