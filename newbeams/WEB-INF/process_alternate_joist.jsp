<%@page import="com.util.Design, com.util.Project"%>

<jsp:useBean id="alternateJoistFormHandler" scope="session" class="com.util.AlternateJoistFormHandler" />
<jsp:useBean id="projectFormHandler" scope="session" class="com.util.ProjectFormHandler" />

<%! String redirectStr ="true", sucessURL, errorURL, loadType, material, number, aMaterial, aNumber, projectName, btn1;
    Design design = null;
    Project project = null;
%>
<%
material = request.getParameter("mater");
number = request.getParameter("number");
aMaterial = request.getParameter("aMater");
aNumber = request.getParameter("aNumber");
System.out.println(material + "-" + number+"-"+aMaterial + "-" + aNumber);
design = alternateJoistFormHandler.getDesign();
project = projectFormHandler.getProject();

projectName = request.getParameter("PreviousProjects");
sucessURL = request.getParameter("SucessURL");
errorURL = request.getParameter("ErrorURL");
//loadType = request.getParameter("loadType");
//System.out.println(loadType +"loadType");
//btn1 = request.getParameter("btn1");
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
    if (!(design.getMaterial().equalsIgnoreCase(material))) {
        design.setMaterial(material);
        design.setNumber("1");
        design.setBeamSize("");
        response.sendRedirect(errorURL);
    }else if (!(design.getNumber().equals(number))) {
        design.setNumber(number);
        design.setBeamSize("");
        response.sendRedirect(errorURL);
    }else if (!(design.getAMaterial().equalsIgnoreCase(aMaterial))) {
        design.setAMaterial(aMaterial);
        design.setANumber("1");
        design.setABeamSize("");
        response.sendRedirect(errorURL);
    }else if (!(design.getANumber().equals(aNumber))) {
        design.setANumber(aNumber);
        design.setABeamSize("");
        response.sendRedirect(errorURL);
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
        alternateJoistFormHandler.setProjectName(project.getProjectName());
        if(alternateJoistFormHandler.processAlternate(request , response)){
            System.out.println("getAMaterial:"+design.getAMaterial());
            System.out.println(" Alternate sucess");
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
