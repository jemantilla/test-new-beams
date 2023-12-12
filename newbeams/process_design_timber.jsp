<%@page import="com.util.Design, com.util.Project"%>

<jsp:useBean id="designTimberFormHandler" scope="session" class="com.util.DesignTimberFormHandler" />
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
    if (btn1 == null) {
        if (( projectName != null) && (projectName.length() > 0)){
            projectFormHandler.setProject(projectFormHandler.getProjectFromTable(projectName));
        }
        response.sendRedirect(errorURL);
        return;
    }
    Design design = designTimberFormHandler.getDesign();
    design.setSpanf(request.getParameter("spanf"));
    design.setSpani(request.getParameter("spani"));
    design.setMaterial(request.getParameter("mater"));
    design.setFloorType(request.getParameter("floorType"));
    design.setSpaceIn(request.getParameter("spaceIn"));
    if (( projectName != null) && (projectName.length() > 0)) {
        projectFormHandler.setProject(projectFormHandler.getProjectFromTable(projectName));
    }
	if (design.getFloorType().equals("Roof")){
        design.setMaterb(request.getParameter("materb"));
        /*design.setRoofPitch(request.getParameter("roofPitch"));
        if (request.getParameter("roofIncluded").equals("Y")) {
            design.setRoofIncluded(true);
        }else design.setRoofIncluded(false);*/
    }else //design.setRoofIncluded(false);
    designTimberFormHandler.setProjectName(projectName);
    if(designTimberFormHandler.processDesignTimber(request , response)){
        response.sendRedirect(sucessURL);
        return;
    }else{
        response.sendRedirect(errorURL);
        return;
    }
}
%>
