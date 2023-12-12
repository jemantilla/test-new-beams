<%@ page import="java.text.*, java.util.*, com.util.TempClass, com.util.Design, com.util.Project" %>
<%@ page import="com.util.TJILVLFlange, com.util.TJIWoodFlange" %>

<jsp:useBean id="alternateJoistFormHandler" scope="session" class="com.util.AlternateJoistFormHandler" />
<jsp:useBean id="projectFormHandler" scope="session" class="com.util.ProjectFormHandler" />
<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />

<%
if (!profileFormHandler.getProfile().getValidUser()) {
    response.sendRedirect("log_in.jsp");
    return;
}
if (request.getMethod().equalsIgnoreCase("Post")) {
    TempClass tempClass0 = alternateJoistFormHandler.getTempClass();
    Design design = alternateJoistFormHandler.getDesign();
    design.setStatus("Alt_Joist_OK");

    String _description = "";
    if (design.getMaterial().equalsIgnoreCase("woodIShapeLVL")) {
       _description = TJILVLFlange.getTJILVLFlangeLookup(tempClass0.getVal());
    }else if (design.getMaterial().equalsIgnoreCase("woodIShapeWood")) {
       _description = TJIWoodFlange.getTJIWoodFlangeLookup(tempClass0.getVal());
    } else {
       _description = tempClass0.getVal();
    }

    projectFormHandler.insertDesignBeam(design, _description ,tempClass0.getDeflection());
    response.sendRedirect(request.getParameter("SucessURL"));
}else { response.sendRedirect(request.getParameter("ErrorURL"));
}
%>
