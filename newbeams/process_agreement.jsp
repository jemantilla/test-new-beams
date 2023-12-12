<%@page import="com.util.Profile"%>

<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />

<%
Profile profile = profileFormHandler.getProfile();
String sucessURL = request.getParameter("SucessURL");
String errorURL = request.getParameter("ErrorURL");
if ((request.getMethod().equalsIgnoreCase("Post")) && (sucessURL != null)) {
    if (request.getParameter("agree").equals("I  Accept")) {
        profileFormHandler.setMethodName("agree");
        profile.setAgree(true);
        profileFormHandler.processMethod(request,response);
        response.sendRedirect(sucessURL);
        return;
    }else {
        profile.setAgree(false);
        response.sendRedirect(errorURL);
        return;
    }
}else{
    profile.setAgree(false);
    response.sendRedirect(errorURL);
    //return;
}
%>
