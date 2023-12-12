<%@ page import="java.text.*, java.util.*, com.util.TempClass, com.util.Design, com.util.Project" %>

<jsp:useBean id="designTimberFormHandler" scope="session" class="com.util.DesignTimberFormHandler" />
<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />
<jsp:useBean id="projectFormHandler" scope="session" class="com.util.ProjectFormHandler" />

<%
if (!profileFormHandler.getProfile().getValidUser()) {
    response.sendRedirect("log_in.jsp");
    return;
}
if (request.getMethod().equalsIgnoreCase("Post")) {
    String sucessURL = request.getParameter("SucessURL");
    String errorURL = request.getParameter("ErrorURL");
    if (request.getParameter("R2") == null) {
        response.sendRedirect(errorURL);
        return;
    }
    int R2 = Integer.parseInt(request.getParameter("R2"));
    //designTimberFormHandler.setBeamSize(R2);
    if (designTimberFormHandler.getErrorFound()) {
        response.sendRedirect(errorURL);
    }else{
        ArrayList tempList = designTimberFormHandler.getTempList();
        TempClass tempClass0 = (TempClass) tempList.get(R2);
        designTimberFormHandler.setTempClass(tempClass0);
        designTimberFormHandler.getDesign().setStatus("TJI");
        projectFormHandler.insertProject();
        projectFormHandler.setBeamInserted(false);
        response.sendRedirect(sucessURL);
    }
}else { response.sendRedirect("design.jsp");
}
%>
