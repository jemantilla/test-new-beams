<%@page import="com.util.Profile"%>

<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />

<%
Profile profile = profileFormHandler.getProfile();
String sucessURL = request.getParameter("SucessURL");
String errorURL = request.getParameter("ErrorURL");
String agree = request.getParameter("agree");
if (sucessURL != null) {
    profileFormHandler.setMethodName(request.getParameter("btn"));
    if (profileFormHandler.getMethodName().equalsIgnoreCase("signup")) {
        profile.setUserName(request.getParameter("userName"));
        profile.setPassWord(request.getParameter("password"));
        profile.setPassWord2(request.getParameter("password2"));
    }
    profile.setFirstName(request.getParameter("firstName"));
    profile.setLastName(request.getParameter("lastName"));
    profile.setEmail(request.getParameter("email"));
    profile.setAddress1(request.getParameter("address1"));
    profile.setCity(request.getParameter("city"));
    profile.setState(request.getParameter("state"));
    profile.setZip(request.getParameter("zip"));
/*    if ((agree != null) && (agree.equalsIgnoreCase("yes"))) {
        profile.setAgree(true);
    }else profile.setAgree(false);*/
//    profile.setWorkPhoneArea(request.getParameter("workPhoneArea"));
    profile.setWorkPhoneNum(request.getParameter("workPhoneNum"));
//    profile.setCellPhoneArea(request.getParameter("cellPhoneArea"));
    profile.setCellPhoneNum(request.getParameter("cellPhoneNum"));
    if (profileFormHandler.processMethod(request, response)){
        response.sendRedirect(sucessURL);
        return;
    }else {
        response.sendRedirect(errorURL);
        return;
    }
}
%>
