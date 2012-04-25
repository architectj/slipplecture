<%@page contentType="text/html; charset=euc-kr"%>
<%@page import="net.slipp.user.*"%>
<%@ include file="loginCheck.jsp"%>
<%
    String userId = request.getParameter("userId");

    UserService service = UserServiceHelper.getUserService(application);
    service.remove(userId);

    response.sendRedirect("index.jsp");
%>