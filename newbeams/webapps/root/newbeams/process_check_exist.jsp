<%@page import="com.util.Design, com.util.Project"%>

<jsp:useBean id="existBeamFormHandler" scope="session" class="com.util.ExistBeamFormHandler" />
<jsp:useBean id="projectFormHandler" scope="session" class="com.util.ProjectFormHandler" />

<%
String material = request.getParameter("mater");
String number = request.getParameter("number");
System.out.println(material + "-" + number);
Design design = existBeamFormHandler.getDesign();
Project project = projectFormHandler.getProject();

String projectName = request.getParameter("PreviousProjects");
String sucessURL = request.getParameter("SucessURL");
String errorURL = request.getParameter("ErrorURL");
String loadType = request.getParameter("loadType");
System.out.println(loadType +"loadType");
String btn1 = request.getParameter("btn1");
if (!request.getMethod().equalsIgnoreCase("Post")) {
    response.sendRedirect("log_in.jsp");
    return;
}else if (sucessURL != null && sucessURL.length() > 1) {
    design.setBeamSize(request.getParameter("beamsize"));
    System.out.println("from jsp:"+request.getParameter("spanf") +"-"+ request.getParameter("spani"));
    design.setSpanf(request.getParameter("spanf"));
    design.setSpani(request.getParameter("spani"));
    design.setLoadType(request.getParameter("loadType"));
    design.setMaterb(request.getParameter("materb"));
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
    if (!(design.getMaterial().equalsIgnoreCase(material))) {
        design.setMaterial(material);
        design.setNumber("1");
        design.setBeamSize("");
        response.sendRedirect(errorURL);
        return;
    }else if (!(design.getNumber().equals(number))) {
        design.setNumber(number);
        design.setBeamSize("");
        response.sendRedirect(errorURL);
        return;
    }else if (btn1 == null) {
        if (( projectName != null) && (projectName.length() > 0)){
            projectFormHandler.setProject(projectFormHandler.getProjectFromTable(projectName));
        }
        response.sendRedirect(errorURL);
        return;
    }else {
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
        out.write(loadType + request.getParameter("load"));
        existBeamFormHandler.setProjectName(project.getProjectName());
        if(existBeamFormHandler.processCheckExist(request , response)){
            if (design.getValidSx()) {
                projectFormHandler.insertProject();
                projectFormHandler.setBeamInserted(false);
                response.sendRedirect(request.getParameter("ValidSxURL") + "?backURL=" + errorURL);
            }else {
                response.sendRedirect(request.getParameter("InValidSxURL") + "?backURL=" + errorURL);
            }
            return;
        }else{
            response.sendRedirect(errorURL);
            return;
        }
    }
}
%>
