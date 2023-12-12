<%@page import="com.util.Design, com.util.Project, java.util.*"%>

<jsp:useBean id="newBeamFormHandler" scope="session" class="com.util.NewBeamFormHandler" />
<jsp:useBean id="projectFormHandler" scope="session" class="com.util.ProjectFormHandler" />

<%
String sucessURL = request.getParameter("SucessURL");
String errorURL = request.getParameter("ErrorURL");
String loadType = request.getParameter("loadType");
String projectName = request.getParameter("PreviousProjects");
String btn1 = request.getParameter("btn1");
if (!request.getMethod().equalsIgnoreCase("Post")) {
    response.sendRedirect("log_in.jsp");
    return;
}else if (sucessURL != null) {
    Design design = newBeamFormHandler.getDesign();
    Project project = projectFormHandler.getProject();
    design.setSpanf(request.getParameter("spanf"));
    design.setSpani(request.getParameter("spani"));
    design.setLoadType(request.getParameter("loadType"));
    design.setMaterial(request.getParameter("mater"));
    if (( projectName != null) && (projectName.length() > 0)) {
        projectFormHandler.setProject(projectFormHandler.getProjectFromTable(projectName));
    }else {
        projectFormHandler.setIsNewProject(true);
        project.setProjectName(request.getParameter("projectName"));
        project.setStreet(request.getParameter("street"));
        project.setCity(request.getParameter("city"));
        project.setState(request.getParameter("state"));
        project.setZip(request.getParameter("zip"));
    }
    if (loadType.equalsIgnoreCase("Beamload")) {
        design.setMethodName("BeamLoad");
        design.setLoad(request.getParameter("load"));
    }else if (loadType.equalsIgnoreCase("TributaryWidth")) {
        design.setTriFeet(request.getParameter("trifeet"));
        design.setTriInch(request.getParameter("triinch"));
        design.setStorey(request.getParameter("storey"));
        design.setMethodName("TributaryWidth");
        design.setMaterb(request.getParameter("materb"));
        design.setRoofPitch(request.getParameter("roofPitch"));
        if (request.getParameter("roofIncluded").equals("Y")) {
            design.setRoofIncluded(true);
        }else design.setRoofIncluded(false);
    }
    if (btn1 == null) {
        if (( projectName != null) && (projectName.length() > 0)){
            projectFormHandler.setProject(projectFormHandler.getProjectFromTable(projectName));
        }
        response.sendRedirect(errorURL);
        return;
    }
    newBeamFormHandler.setProjectName(projectName);
    if(newBeamFormHandler.processMethod(request , response)){
        response.sendRedirect(sucessURL);
        return;
    }else{
        response.sendRedirect(errorURL);
        return;
    }
}
%>
