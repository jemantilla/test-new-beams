<%@page import="com.util.Design, java.util.*"%>

<jsp:useBean id="timberAFrameFormHandler" scope="session" class="com.util.TimberAFrameFormHandler" />
<jsp:useBean id="projectFormHandler" scope="session" class="com.util.ProjectFormHandler" />

<%
String sucessURL = request.getParameter("SucessURL");
String errorURL = request.getParameter("ErrorURL");
String projectName = request.getParameter("PreviousProjects");
if (!request.getMethod().equalsIgnoreCase("Post")) {
    response.sendRedirect("log_in.jsp");
    return;
}else if (sucessURL != null) {
    Design design = timberAFrameFormHandler.getDesign();
    design.setSpaceFt(request.getParameter("spaceft"));
    design.setSpaceIn(request.getParameter("spacein"));
    design.setL1F(request.getParameter("l1f"));
    design.setL1I(request.getParameter("l1i"));
    design.setL2F(request.getParameter("l2f"));
    design.setL2I(request.getParameter("l2i"));
    design.setL3F(request.getParameter("l3f"));
    design.setL3I(request.getParameter("l3i"));
    design.setH1F(request.getParameter("h1f"));
    design.setH1I(request.getParameter("h1i"));
    design.setH2F(request.getParameter("h2f"));
    design.setH2I(request.getParameter("h2i"));
   // design.setLoadType(request.getParameter("loadType"));
    design.setMaterial(request.getParameter("mater"));
    if (( projectName != null) && (projectName.length() > 0)) {
        projectFormHandler.setProject(projectFormHandler.getProjectFromTable(projectName));
    }
   /* if (loadType.equalsIgnoreCase("Beamload")) {
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
    }        */
    timberAFrameFormHandler.setProjectName(projectName);
    if(timberAFrameFormHandler.processDesignTimberAFrame(request , response)){
        response.sendRedirect(sucessURL);
        return;
    }else{
        response.sendRedirect(errorURL);
        return;
    }
}
%>
