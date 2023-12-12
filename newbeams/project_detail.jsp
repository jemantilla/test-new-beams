<%@ page import="java.util.Vector, com.util.Utility, com.util.ProjectDetail, com.util.Project, java.util.Iterator" %>
<%@ page import="com.util.Profile" %>

<jsp:useBean id="projectFormHandler" scope="session" class="com.util.ProjectFormHandler" />
<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />

<%
Profile profile = profileFormHandler.getProfile();
Project project = null;
if (!profile.getValidUser()) {
    response.sendRedirect("log_in.jsp");
    return;
}
String projectName = request.getParameter("ProjectName");
System.out.println("projectName:"+projectName);
if ((projectName == null) || (projectName.length() < 1)) {
    project = projectFormHandler.getProject();
    projectName = project.getProjectName();
}else {
    project = projectFormHandler.getProjectFromTable(projectName);
}
projectFormHandler.setProjectDetail(projectName);
Vector detailVector = projectFormHandler.getProjectDetail();

/*if (request.getMethod().equalsIgnoreCase("Post")) {
    if ((projectName != null) && (projectName.length() > 0)){
        projectFormHandler.setProjectDetail(projectName);
    project = projectFormHandler.getProjectFromTable(projectName);
    detailVector = projectFormHandler.getProjectDetail();
}*/
%>
<html>
<head><title>Create a new beam</title>
<link rel="stylesheet" href="newbeams.css">
</head>
<BODY topmargin=0>

<FORM ACTION="project_detail.jsp" METHOD="POST">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<%if (projectFormHandler.hasProjects()){
                Iterator projects = projectFormHandler.getProjects();%>
     <TR>
        <td class="a10blackbold" ALIGN="RIGHT"><B>All Projects:</B>&nbsp;&nbsp;</td>
        <td ALIGN="LEFT">
                <SELECT NAME="ProjectName" onChange="this.form.submit()">
                    <OPTION VALUE="">(Select project)</OPTION>
                    <%while (projects.hasNext()){
                        String _name = (String)projects.next();%>
                        <OPTION VALUE="<%=_name%>"
                          <%if (_name.equals(projectName)) out.print(" selected"); out.print(">"+_name+"</OPTION>");%>
                    <%}%>    
                </SELECT>&nbsp;&nbsp;<input type="submit" name="btn1" value="Detail">
        </td>
     </TR>
     <TR>
        <td class="a10blackbold">&nbsp;</td>
        <td class="a10blackbold">&nbsp;</td>           
<%}%>
<%if (project != null){%>
         <tr>
            <td width="15%" class="a10blackbold" align="right"><B>Project Detail:&nbsp;&nbsp;</B></td>
            <td width="15%" class="a10blackbold" align="left"><%=project.getProjectName()%></td>
         </tr>
         <tr>
            <td width="15%">&nbsp;</td>
            <td width="15%" class="a10blackbold" align="left"><%if (project.getStreet().length()>0) out.print(project.getStreet()+",");%></td>
         </tr>
         <tr>
            <td width="15%">&nbsp;</td>
            <td width="15%" class="a10blackbold" align="left"><%if (project.getCity().length()>0) out.print(project.getCity()+",");%></td>
         </tr>
         <tr>
            <td width="15%">&nbsp;</td>
            <td width="15%" class="a10blackbold" align="left">
                <%if (project.getState().length()>0) out.print(project.getState()+"-"); out.print(project.getZip());%></td>
         </tr>
</TABLE>
    </FORM>
<BR>
<%}%>
<%if (detailVector != null && detailVector.size() > 0){%>
    <table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#999999">
        <tr bgcolor="#CCCCCC">
            <td class="a10blackbold" align="center">Status</td>
            <td class="a10blackbold" align="center">BeamSize</td>
            <td class="a10blackbold" align="center">Material</td>
            <td class="a10blackbold" align="center">Span</td>
            <td class="a10blackbold" align="center">Date  Time</td>
            <td class="a10blackbold" align="center">Load</td>
            <td class="a10blackbold" align="center">Deflection (in)</td>
            <td class="a10blackbold" align="center">Tension</td>
            <td class="a10blackbold" align="center">CSArea</td>
            <td class="a10blackbold" align="center">RafterSize</td>
        </tr>
    <%for (int i=0; i<detailVector.size(); i++){
        ProjectDetail _projectDetail = (ProjectDetail)detailVector.get(i);%>
        <tr align="center" bgcolor="#FFFFFF"> 
            <td class="a10black"><%=_projectDetail.getStatus()%></td>
            <td class="a10black">
              <% if ( (_projectDetail.getBeamSize() != null) && (_projectDetail.getBeamSize().length() > 0) ) {%>
                    <%=_projectDetail.getBeamSize()%>
              <%} else {%>
                    N/A              
              <%}%>
            </td>
            <td class="a10black"><%=_projectDetail.getMaterial()%></TD>
            <td class="a10black"><%=_projectDetail.getSpan()%></TD>
            <td class="a10black"><%=_projectDetail.getDate()%></td>
            <td class="a10black"><%=Utility.getTwoDecimal(_projectDetail.getLoad())%></td>
            <td class="a10black"><%=_projectDetail.getDeflection()%></TD>
            <td class="a10black">
              <% if (_projectDetail.getTension() > 0) {%>
                    <%=Utility.getTwoDecimal(_projectDetail.getTension())%>
              <%} else {%>
                    N/A              
              <%}%>
            </td>
            <td class="a10black">
              <% if (_projectDetail.getCSArea() > 0) {%>
                    <%=Utility.getTwoDecimal(_projectDetail.getCSArea())%>
              <%} else {%>
                    N/A              
              <%}%>
            </td>
            <td class="a10black">
              <% if (_projectDetail.getRafterSize() == null) {%>
                    N/A
              <%} else {%>
                    <%=_projectDetail.getRafterSize()%>
              <%}%>
            </td>
        </tr>
    <%}%>
    </table>
<%}%>
<br><DIV ALIGN="CENTER"><A HREF="design.jsp">Home</A></DIV>
</BODY>
</HTML>
