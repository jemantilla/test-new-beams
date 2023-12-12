<%@page import="com.util.Project, java.util.*"%>

<jsp:useBean id="projectFormHandler" scope="session" class="com.util.ProjectFormHandler" />

<%
Project project = projectFormHandler.getNewProject();
String sucessURL = request.getParameter("SucessURL");
String errorURL = request.getParameter("ErrorURL");
System.out.println("In process_create_project.jsp");

if (!request.getMethod().equalsIgnoreCase("Post")) {
    response.sendRedirect("log_in.jsp");
    return;
}else if (sucessURL != null) {
    projectFormHandler.setIsNewProject(true);
    project.setProjectName(request.getParameter("projectName"));
    project.setStreet(request.getParameter("street"));
    project.setCity(request.getParameter("city"));
    project.setState(request.getParameter("state"));
    project.setZip(request.getParameter("zip"));
    if (projectFormHandler.ProcessInsertNewProject(request, response)) {
        System.out.println("In ProcessInsertNewProject from jsp");
        response.sendRedirect(sucessURL);
        return;
    }else{
        response.sendRedirect(errorURL);
        return;
    }
}
%>
