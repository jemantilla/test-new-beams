<%@page import="com.util.Design, com.util.Project, java.util.*"%>

<jsp:useBean id="projectFormHandler" scope="session" class="com.util.ProjectFormHandler" />

<%
Project project = projectFormHandler.getProject();
String sucessURL = request.getParameter("SucessURL");
String errorURL = request.getParameter("ErrorURL");
if (!request.getMethod().equalsIgnoreCase("Post")) {
    response.sendRedirect("log_in.jsp");
    return;
}else if (sucessURL != null) {
    project.setProjectName(request.getParameter("projectName"));
    project.setStreet(request.getParameter("street"));
    project.setCity(request.getParameter("city"));
    project.setState(request.getParameter("state"));
    project.setZip(request.getParameter("zip"));
    if (projectFormHandler.processUpdateProject(request, response)) {
        response.sendRedirect(sucessURL);
        return;
    }else{
        response.sendRedirect(errorURL);
        return;
    }
}
%>
