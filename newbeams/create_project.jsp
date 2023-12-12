<%@page import="com.util.Project, com.util.HashValues, com.util.Profile"%>

<jsp:useBean id="projectFormHandler" scope="session" class="com.util.ProjectFormHandler" />
<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />

<%
Profile profile = profileFormHandler.getProfile();
if (!profile.getValidUser()) {
    response.sendRedirect("log_in.jsp");
    return;
}
String isNew = request.getParameter("isNew");
if ((isNew != null) && (isNew.equals("true"))) {
    projectFormHandler.createNewProject();
}
Project project = projectFormHandler.getNewProject();
String projectName = project.getProjectName();
%>
<html>
<head><title>Create a new beam</title>
<script language='JavaScript' src='newbeams.js'></script>
<link rel="stylesheet" href="newbeams.css">
</head>

<body topmargin=0><!--ONLOAD="setEnable()" -->
<form method="POST" ACTION="process_create_project.jsp"  NAME="DesignForm">
<input type="hidden" name="SucessURL" VALUE="design_beam.jsp">
<input type="hidden" name="ErrorURL" VALUE="create_project.jsp">
<table width="100%">
  <tr>
    <td width="22%" valign="TOP">
        <jsp:include page="slide.jsp" flush="true"/>
    </TD>
    <TD WIDTH="78%" valign="TOP">
<%    
if (projectFormHandler.getErrorFound()) {
    Object [] _errorVec = projectFormHandler.getFormExceptions();
    request.setAttribute("ErrorVector" ,_errorVec);%>
    <jsp:include page="error_page.jsp" flush="true"/>
<%
}
%>
<table border="0" cellpadding=2 cellspacing=2 width="100%" ALIGN="CENTER">
      <tr> 
        <td colspan="3" bgcolor="#DBEAF5" class="bigtitle"><font class="bigtitle">Project Detail</td>
      </tr>
      <tr>
        <td class="a10black" ALIGN="RIGHT">Project Name</td>
        <td colspan="2" class="a10black"ALIGN="LEFT">
            <input type="text" name="projectName" size="35" VALUE="<%=projectName%>">
        </TD>
      </TR>
      <TR>
        <TD class="a10black" ALIGN="RIGHT">Address</TD>
        <TD colspan="2" class="a10black" ALIGN="LEFT">
            <input type="text" name="street" size="35" VALUE="<%=project.getStreet()%>">
        </TD>
      </TR>
      <TR>
        <TD class="a10black" ALIGN="RIGHT">Town/City</TD>
        <TD colspan="2" class="a10black" ALIGN="LEFT">
            <input type="text" name="city" size="20" VALUE="<%=project.getCity()%>">
        </TD>
      </TR>
      <TR> 
        <td class="a10black" ALIGN="RIGHT">State</td>
        <td colspan="2" class="a10black">
        <select name="state"  size=1>
              <%if (project.getState().length() >= 1)
                  out.print("<OPTION VALUE=" + project.getState()+">"+ HashValues.stateTable.get(project.getState()));
                else {%><OPTION VALUE="">&lt;Select&gt;<%}%>
                <OPTION VALUE="AL"> Alabama
                <OPTION VALUE="AK"> Alaska
                <OPTION VALUE="AZ"> Arizona
                <OPTION VALUE="AR"> Arkansas
                <OPTION VALUE="CA"> California
                <OPTION VALUE="CO"> Colorado
                <OPTION VALUE="CT"> Connecticut
                <OPTION VALUE="DE"> Delaware
                <OPTION VALUE="DC"> District of Columbia
                <OPTION VALUE="FL"> Florida
                <OPTION VALUE="GA"> Georgia
                <OPTION VALUE="HI"> Hawaii
                <OPTION VALUE="ID"> Idaho
                <OPTION VALUE="IL"> Illinois
                <OPTION VALUE="IN"> Indiana
                <OPTION VALUE="IA"> Iowa
                <OPTION VALUE="KS"> Kansas
                <OPTION VALUE="KY"> Kentucky
                <OPTION VALUE="LA"> Louisiana
                <OPTION VALUE="ME"> Maine
                <OPTION VALUE="MD"> Maryland
                <OPTION VALUE="MA"> Massachusetts
                <OPTION VALUE="MI"> Michigan
                <OPTION VALUE="MN"> Minnesota
                <OPTION VALUE="MS"> Mississippi
                <OPTION VALUE="MO"> Missouri
                <OPTION VALUE="MT"> Montana
                <OPTION VALUE="NE"> Nebraska
                <OPTION VALUE="NV"> Nevada
                <OPTION VALUE="NH"> New Hampshire
                <OPTION VALUE="NJ"> New Jersey
                <OPTION VALUE="NM"> New Mexico
                <OPTION VALUE="NY"> New York
                <OPTION VALUE="NC"> North Carolina
                <OPTION VALUE="ND"> North Dakota
                <OPTION VALUE="OH"> Ohio
                <OPTION VALUE="OK"> Oklahoma
                <OPTION VALUE="OR"> Oregon
                <OPTION VALUE="PA"> Pennsylvania
                <OPTION VALUE="RI"> Rhode Island
                <OPTION VALUE="SC"> South Carolina
                <OPTION VALUE="SD"> South Dakota
                <OPTION VALUE="TN"> Tennessee
                <OPTION VALUE="TX"> Texas
                <OPTION VALUE="UT"> Utah
                <OPTION VALUE="VT"> Vermont
                <OPTION VALUE="VA"> Virginia
                <OPTION VALUE="WA"> Washington
                <OPTION VALUE="WV"> West Virginia
                <OPTION VALUE="WI"> Wisconsin
                <OPTION VALUE="WY"> Wyoming
                <OPTION VALUE="GU"> Guam
                <OPTION VALUE="PR"> Puerto Rico
                <OPTION VALUE="NB"> New Brunswick
                <OPTION VALUE="NF"> Newfoundland
                <OPTION VALUE="AB"> Alberta
                <OPTION VALUE="NT"> Northwest Territories
                <OPTION VALUE="NS"> Nova Scotia
                <OPTION VALUE="PE"> Prince Edward Island
                <OPTION VALUE="QC"> Quebec
                <OPTION VALUE="SK"> Saskatchewan
                <OPTION VALUE="ON"> Ontario
                <OPTION VALUE="YK"> Yukon Territory
                <OPTION VALUE="MB"> Manitoba
                <OPTION VALUE="BC"> British Columbia
              </select>in <b><i>United States</i></b>
        </td>
      </tr>
      <TR>
        <TD class="a10black" ALIGN="RIGHT">ZipCode</TD>
        <TD colspan="2" class="a10black" ALIGN="LEFT">
            <input type="text" name="zip" size="5" MAXLENGTH="5" VALUE="<%=project.getZip()%>">
        </TD>
      </TR>
      <TR>
        <TD colspan="3" class="a10black" ALIGN="CENTER">
            <input type="submit" name="Create" VALUE="Create Project">
        </TD>
      </TR>
</TABLE>

    </TD></TR>
</table>
</form></body>
</html>     
