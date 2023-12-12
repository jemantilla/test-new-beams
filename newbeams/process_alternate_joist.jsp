<%@page import="com.util.Design, com.util.Project"%>

<jsp:useBean id="alternateJoistFormHandler" scope="session" class="com.util.AlternateJoistFormHandler" />
<jsp:useBean id="projectFormHandler" scope="session" class="com.util.ProjectFormHandler" />

<%
String material = request.getParameter("mater");
String aMaterial = request.getParameter("aMater");
System.out.println(material + "-" +aMaterial );
Design design = alternateJoistFormHandler.getDesign();
Project project = projectFormHandler.getProject();

String projectName = request.getParameter("PreviousProjects");
String sucessURL = request.getParameter("SucessURL");
String errorURL = request.getParameter("ErrorURL");

String btn1 = request.getParameter("btn1");
if (!request.getMethod().equalsIgnoreCase("Post")) {
    response.sendRedirect("log_in.jsp");
    return;
}else if (sucessURL != null && sucessURL.length() > 1) {
    design.setBeamSize(request.getParameter("beamsize"));
    System.out.println("from jsp:"+request.getParameter("spanf") +"-"+ request.getParameter("spani"));
    design.setSpanf(request.getParameter("spanf"));
    design.setSpani(request.getParameter("spani"));
    design.setDepthIn(request.getParameter("depthIn"));
    design.setASpaceIn(request.getParameter("aspaceIn"));
    design.setSpaceIn(request.getParameter("spaceIn"));
    design.setFloorType(request.getParameter("floorType"));
    if (!(design.getMaterial().equalsIgnoreCase(material))) {
        System.out.println("material is different");
        design.setMaterial(material);
        design.setBeamSize("");
        response.sendRedirect(errorURL);
    }else if (!(design.getAMaterial().equalsIgnoreCase(aMaterial))) {
        System.out.println("aMaterial is different");
        design.setAMaterial(aMaterial);
        response.sendRedirect(errorURL);
    }else if (btn1 == null) {
        System.out.println("btn is null");
        if (( projectName != null) && (projectName.length() > 0)){
            projectFormHandler.setProject(projectFormHandler.getProjectFromTable(projectName));
        }
        response.sendRedirect(errorURL);
    }else {
        if (( projectName != null) && (projectName.length() > 0)) {
            projectFormHandler.setProject(projectFormHandler.getProjectFromTable(projectName));
        }
        alternateJoistFormHandler.setProjectName(project.getProjectName());
        if(alternateJoistFormHandler.processAlternateJoist(request , response)){
            System.out.println("getAMaterial:"+design.getAMaterial());
            System.out.println(" Alternate Joist sucess");
            projectFormHandler.setBeamInserted(false);
            if (alternateJoistFormHandler.getBeamIsGood()) {
                response.sendRedirect(request.getParameter("BeamOkURL"));
                return;
            }
            response.sendRedirect(sucessURL);
            return;
        }else{
            System.out.println("getAMaterial F:"+design.getAMaterial());
            System.out.println(" Alternate fail");
            response.sendRedirect(errorURL);
            return;
        }
    }
}
%>
%>
