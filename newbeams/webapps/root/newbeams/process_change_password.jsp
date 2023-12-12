<%@page import="com.util.Profile"%>

<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />

<%
Profile profile = profileFormHandler.getProfile();
String sucessURL = request.getParameter("SucessURL");
String errorURL = request.getParameter("ErrorURL");
if (!request.getMethod().equalsIgnoreCase("Post")) {
    response.sendRedirect("log_in.jsp");
    return;
}else {
    profile.setOldPassWord(request.getParameter("oldPassword"));
    profileFormHandler.setMethodName("changePassword");
    profile.setNewPassWord(request.getParameter("newPassword"));
    profile.setNewPassWord2(request.getParameter("newPassword2"));
    if (profileFormHandler.processMethod(request, response)){
        response.sendRedirect(sucessURL);
        return;
    }else {
        response.sendRedirect(errorURL);
        //return;
    }
}
%>
