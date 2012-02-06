<%@page contentType="text/html; charset=euc-kr" %>
<%@page import="net.javajigi.user.*" %>
<%@ include file="loginCheck.jsp" %>
<%
	String userId = request.getParameter("userId");

	UserManager manager = UserManager.instance();
	manager.remove(userId);

	response.sendRedirect("user_list.jsp");
%>