<%@ page import="java.text.*, java.util.*, com.util.TempClass, com.util.Design, com.util.Project" %>

<jsp:useBean id="alternateBeamFormHandler" scope="session" class="com.util.AlternateBeamFormHandler" />
<jsp:useBean id="projectFormHandler" scope="session" class="com.util.ProjectFormHandler" />
<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />

<%
if (!profileFormHandler.getProfile().getValidUser()) {
    response.sendRedirect("log_in.jsp");
    return;
}
if (request.getMethod().equalsIgnoreCase("Post")) {
    TempClass tempClass0 = alternateBeamFormHandler.getTempClass();
    projectFormHandler.insertDesignBeam(alternateBeamFormHandler.getDesign(), tempClass0.getVal(),tempClass0.getDeflection());
    response.sendRedirect(request.getParameter("SucessURL"));
}else { response.sendRedirect(request.getParameter("ErrorURL"));
}
%>
