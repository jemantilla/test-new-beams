<%@ page import="java.util.*, com.util.TempClass, com.util.Design" %>

<jsp:useBean id="alternateBeamFormHandler" scope="session" class="com.util.AlternateBeamFormHandler" />
<jsp:useBean id="projectFormHandler" scope="session" class="com.util.ProjectFormHandler" />
<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />

<%
String errorURL = request.getParameter("ErrorURL");
TempClass tempClass0 = null;
if (!profileFormHandler.getProfile().getValidUser()) {
    response.sendRedirect("log_in.jsp");
    return;
}
if (request.getMethod().equalsIgnoreCase("POST")) {
    String R2 = request.getParameter("R2");
    alternateBeamFormHandler.setBeamSize(R2);
    if (alternateBeamFormHandler.getErrorFound()) {
        response.sendRedirect(errorURL);
    }else{
        ArrayList tempList = alternateBeamFormHandler.getTempList();
        if (R2.equals("x")){
            tempClass0 = (TempClass) tempList.get(0);
        }else if (R2.equals("y")) {
            tempClass0 = (TempClass) tempList.get(1);
        }else{
            response.sendRedirect(errorURL);
            return;
        }
        alternateBeamFormHandler.setTempClass(tempClass0);
        alternateBeamFormHandler.getDesign().setStatus("Alternate");
        System.out.println("load:"+alternateBeamFormHandler.getDesign().getLoad()+"END");
        projectFormHandler.setBeamInserted(false);
        response.sendRedirect(request.getParameter("SucessURL"));
    }
}else { response.sendRedirect(errorURL);
}
%>
