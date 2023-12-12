<%@ page import="java.text.*, java.util.*, com.util.TempClass, com.util.Design, com.util.Project" %>

<jsp:useBean id="newBeamFormHandler" scope="session" class="com.util.NewBeamFormHandler" />
<jsp:useBean id="projectFormHandler" scope="session" class="com.util.ProjectFormHandler" />
<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />

<%
if (!profileFormHandler.getProfile().getValidUser()) {
    response.sendRedirect("log_in.jsp");
    return;
}
if (request.getMethod().equals("POST")) {
    TempClass tempClass0 = newBeamFormHandler.getTempClass();
    System.out.println("in process result beam.jsp");
    projectFormHandler.insertDesignBeam(newBeamFormHandler.getDesign(), tempClass0.getVal(),tempClass0.getDeflection());
    response.sendRedirect(request.getParameter("SucessURL"));
}else { response.sendRedirect(request.getParameter("ErrorURL"));
}
%>
