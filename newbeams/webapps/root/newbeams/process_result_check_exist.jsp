<%@ page import="com.util.Design" %>

<jsp:useBean id="existBeamFormHandler" scope="session" class="com.util.ExistBeamFormHandler" />
<jsp:useBean id="projectFormHandler" scope="session" class="com.util.ProjectFormHandler" />
<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />

<%
Design design = existBeamFormHandler.getDesign();
System.out.print("Span:"+design.getSpan());
if (!profileFormHandler.getProfile().getValidUser()) {
    response.sendRedirect("log_in.jsp");
    return;
}
if (request.getMethod().equalsIgnoreCase("Post")) {
    projectFormHandler.insertDesignBeam(design, design.getBeamSize(), design.getDeflectCheck());
    response.sendRedirect(request.getParameter("SucessURL")+"?backURL="+request.getParameter("backURL"));
}else { response.sendRedirect(request.getParameter("ErrorURL"));
}
%>
