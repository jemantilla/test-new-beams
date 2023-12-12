<%@ page import="com.util.TempClass, com.util.Design" %>

<jsp:useBean id="designTimberFormHandler" scope="session" class="com.util.DesignTimberFormHandler" />
<jsp:useBean id="projectFormHandler" scope="session" class="com.util.ProjectFormHandler" />
<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />

<%
if (!profileFormHandler.getProfile().getValidUser()) {
    response.sendRedirect("log_in.jsp");
    return;
}
if (request.getMethod().equalsIgnoreCase("Post")) {
    TempClass tempClass0 = designTimberFormHandler.getTempClass();
    System.out.println("in process result beam.jsp");
    projectFormHandler.insertDesignBeam(designTimberFormHandler.getDesign(), tempClass0.getVal(),tempClass0.getDeflection());
    response.sendRedirect(request.getParameter("SucessURL"));
}else { response.sendRedirect(request.getParameter("ErrorURL"));
}
%>
