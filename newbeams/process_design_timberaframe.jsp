<%@ page import="java.text.*, java.util.*, com.util.TempClass, com.util.Design, com.util.Project" %>

<jsp:useBean id="timberAFrameFormHandler" scope="session" class="com.util.TimberAFrameFormHandler" />
<jsp:useBean id="projectFormHandler" scope="session" class="com.util.ProjectFormHandler" />
<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />

<%
if (!profileFormHandler.getProfile().getValidUser()) {
    response.sendRedirect("log_in.jsp");
    return;
}
if (request.getMethod().equalsIgnoreCase("Post")) {
    if (request.getParameter("R2") == null) {
        response.sendRedirect("design_timberaframe.jsp");
        return;
    }
    int R2 = Integer.parseInt(request.getParameter("R2"));
    //newBeamFormHandler.setBeamSize(R2);
    if (timberAFrameFormHandler.getErrorFound()) {
        response.sendRedirect(request.getParameter("ErrorURL"));
    }else{
        ArrayList tempList = timberAFrameFormHandler.getTempList();
        TempClass tempClass0 = (TempClass) tempList.get(R2);
        timberAFrameFormHandler.setTempClass(tempClass0);
        timberAFrameFormHandler.getDesign().setStatus("New");
        projectFormHandler.setBeamInserted(false);
        response.sendRedirect(request.getParameter("SucessURL"));
    }
}else { response.sendRedirect("design_aframe.jsp");
}
%>
