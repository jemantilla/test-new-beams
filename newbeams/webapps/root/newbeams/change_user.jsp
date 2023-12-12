<%@page import="javax.servlet.http.Cookie"%>
<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />

<%
profileFormHandler.setMethodName("logout");
boolean NoError = profileFormHandler.processMethod(request, response);
Cookie loginCookie = new Cookie("www.newbeams.com", "delete");
loginCookie.setMaxAge(0);
response.addCookie(loginCookie);
response.sendRedirect("log_in.jsp");
//return;%>
