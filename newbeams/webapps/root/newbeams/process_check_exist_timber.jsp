<%@page import="com.util.Design, com.util.Project"%>

<jsp:useBean id="existTimberFormHandler" scope="session" class="com.util.ExistTimberFormHandler" />
<jsp:useBean id="projectFormHandler" scope="session" class="com.util.ProjectFormHandler" />

<%
String material = request.getParameter("mater");
String number = request.getParameter("number");
System.out.println("beamsize:"+ request.getParameter("beamsize")+ "-" +material + "-" + number);
Design design = existTimberFormHandler.getDesign();
Project project = projectFormHandler.getProject();

String projectName = request.getParameter("PreviousProjects");
String sucessURL = request.getParameter("SucessURL");
String errorURL = request.getParameter("ErrorURL");
String loadType = request.getParameter("loadType");
String btn1 = request.getParameter("btn1");
if (!request.getMethod().equalsIgnoreCase("Post")) {
    response.sendRedirect("log_in.jsp");
    return;
}else if (sucessURL != null && sucessURL.length() > 1) {
    design = existTimberFormHandler.getDesign();
    project = projectFormHandler.getProject();
    design.setBeamSize(request.getParameter("beamsize"));
    design.setSpanf(request.getParameter("spanf"));
    design.setSpani(request.getParameter("spani"));
    design.setFloorType(request.getParameter("floorType"));
    design.setSpaceIn(request.getParameter("spaceIn"));
    System.out.println("   before     beam size is null");

    if (!(design.getMaterial().equalsIgnoreCase(material))) {
        design.setMaterial(material);
        design.setNumber("1");
        design.setBeamSize("");
        response.sendRedirect(errorURL);
        System.out.println("        beam size is null");
        return;
    }else if (!(design.getNumber().equals(number))) {
        design.setNumber(number);
        design.setBeamSize("");
        response.sendRedirect(errorURL);
        System.out.println("        beam size is null");
        return;
    }else if (btn1 == null) {
        if (( projectName != null) && (projectName.length() > 0)){
            projectFormHandler.setProject(projectFormHandler.getProjectFromTable(projectName));
        }
        response.sendRedirect(errorURL);
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
        design.setMaterial(material);
        design.setNumber(number);
        existTimberFormHandler.setProjectName(project.getProjectName());
        if(existTimberFormHandler.processCheckExistTimber(request , response)){
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
