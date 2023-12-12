<%@page import="com.util.Design, com.util.Project"%>

<jsp:useBean id="alternateBeamFormHandler" scope="session" class="com.util.AlternateBeamFormHandler" />
<jsp:useBean id="projectFormHandler" scope="session" class="com.util.ProjectFormHandler" />

<%
String material = request.getParameter("mater");
String number = request.getParameter("number");
String aMaterial = request.getParameter("aMater");
String aNumber = request.getParameter("aNumber");
System.out.println(material + "-" + number+"-"+aMaterial + "-" + aNumber);
Design design = alternateBeamFormHandler.getDesign();
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
    design.setABeamSize(request.getParameter("aBeamsize"));
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
        }
        alternateBeamFormHandler.setProjectName(project.getProjectName());
        if(alternateBeamFormHandler.processAlternate(request , response)){
            System.out.println("getAMaterial:"+design.getAMaterial());
            System.out.println(" Alternate sucess");
            projectFormHandler.setBeamInserted(false);
            if (alternateBeamFormHandler.getBeamIsGood()) {
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
