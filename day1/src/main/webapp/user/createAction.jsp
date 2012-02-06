<%@page contentType="text/html; charset=euc-kr" %>
<%@page import="net.javajigi.user.*" %>
<%
	String userId = request.getParameter("userId");
	String password = request.getParameter("password");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	
	User user = new User();
	user.setUserId(userId);
	user.setPassword(password);
	user.setName(name);
	user.setEmail(email);
	
	UserManager manager = UserManager.instance();
	manager.create(user);

	response.sendRedirect("user_list.jsp");
%>