<%@page import="com.util.Profile, com.util.Design, com.util.DesignHelp, com.util.Project, com.util.HashValues"%>
<%@page import="java.util.Iterator"%>

<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />
<jsp:useBean id="projectFormHandler" scope="session" class="com.util.ProjectFormHandler" />
<jsp:useBean id="timberAFrameFormHandler" scope="session" class="com.util.TimberAFrameFormHandler" />

<%  Design design = null;
    DesignHelp designHelp = null;
    Project project = null;
    Iterator projects = null;
Profile profile = profileFormHandler.getProfile();
if (!profile.getValidUser()) {
    response.sendRedirect("log_in.jsp");
    return;
}
%>
<html>
<head><title>Design Timber A-Frame</title>
<script language='JavaScript' src='newbeams.js'></script>
<link rel="stylesheet" href="newbeams.css">
</head>

<body topmargin=0>
<form method="POST" ACTION="process_designaframe.jsp"  NAME="DesignForm">
<input type="hidden" name="SucessURL" VALUE="design_timberaframe.jsp">
<input type="hidden" name="ErrorURL" VALUE="design_aframe.jsp">
<table width="100%">
  <tr>
    <td width="22%" valign="TOP">
        <jsp:include page="slide.jsp" flush="true"/>
    </TD>
    <TD WIDTH="78%" valign="TOP">
<%
design = timberAFrameFormHandler.getDesign();
designHelp = design.getDesignHelp();
project = projectFormHandler.getProject();
if (timberAFrameFormHandler.getErrorFound()) {
    Object [] _errorVec = timberAFrameFormHandler.getFormExceptions();
    request.setAttribute("ErrorVector" ,_errorVec);%>
    <jsp:include page="error_page.jsp" flush="true"/>
<%
}
%>   


<table border="0" cellpadding=2 cellspacing=2 width="100%" ALIGN="CENTER">
      <tr> 
        <td colspan=4 bgcolor="#DBEAF5"> <a name="profiletop"><font class="bigtitle">Design Timber A-Frame</font></a> </td>
      </tr>
      <tr>
        <td class="a10black" ALIGN="RIGHT">Select the Beam Material</td>
        <td class="a10black" colspan=3 ALIGN="LEFT">
            <select size="1" name="mater">
                <option value="std" <%=designHelp.getStd()%>>Wood/Standard Lumber</option>
                <option value="lvl" <%=designHelp.getLvl()%>>Laminated Veneer Lumber</option>
                <option value="flistd" <%=designHelp.getFlistd()%>>Steel flitch plate + Std.Lumber</option>
                <option value="flilvl" <%=designHelp.getFlilvl()%>>Steel flitch plate + LVL</option>
            </select>
          </td>
       </tr>
       <tr>
         <td class="a10black" ALIGN="RIGHT">Frame Spacing </td>
         <td class="a10black" colspan=3 ALIGN="LEFT"> 
            <input type="text" name="spaceft" size="5" maxLength=3 VALUE=<%=design.getSpaceFt()%>>feet&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="text" name="spacein" size="5" maxLength=3 VALUE=<%=design.getSpaceIn()%>>inches    
          </TD>
       </TR>
       
       <tr>
         <TD ROWSPAN="5"><IMG SRC="graphics/aframe.gif"></TD>
         <td class="a10black" ALIGN="RIGHT">L1 = </td>
         <td class="a10black" ALIGN="LEFT">
            <input type="text" name="l1f" size="5" maxLength=3 VALUE=<%=design.getL1F()%>>feet&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="text" name="l1i" size="5" maxLength=3 VALUE=<%=design.getL1I()%>>inches
         </TD>
       </TR>
       <tr>
         <td class="a10black" ALIGN="RIGHT">L2 = </td>
         <td class="a10black" ALIGN="LEFT">
            <input type="text" name="l2f" size="5" maxLength=3 VALUE=<%=design.getL2F()%>>feet&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="text" name="l2i" size="5" maxLength=3 VALUE=<%=design.getL2I()%>>inches
         </TD>
       </TR>
       <tr>
         <td class="a10black" ALIGN="RIGHT">L3 = </td>
         <td class="a10black" ALIGN="LEFT">
            <input type="text" name="l3f" size="5" maxLength=3 VALUE=<%=design.getL3F()%>>feet&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="text" name="l3i" size="5" maxLength=3 VALUE=<%=design.getL3I()%>>inches
         </TD>
       </TR>
       <tr>
         <td class="a10black" ALIGN="RIGHT">H1 = </td>
         <td class="a10black" ALIGN="LEFT">
            <input type="text" name="h1f" size="5" maxLength=3 VALUE=<%=design.getH1F()%>>feet&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="text" name="h1i" size="5" maxLength=3 VALUE=<%=design.getH1I()%>>inches
         </TD>
       </TR>
       <tr>
         <td class="a10black" ALIGN="RIGHT">H2 = </td>
         <td class="a10black" ALIGN="LEFT">
            <input type="text" name="h2f" size="5" maxLength=3 VALUE=<%=design.getH2F()%>>feet&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="text" name="h2i" size="5" maxLength=3 VALUE=<%=design.getH2I()%>>inches
         </TD>
       </TR>   
           </TABLE>
    <jsp:include page="include_project.jsp" flush="true"/>
    <TABLE WIDTH="100%">
      <tr>
        <td width="30%" ALIGN="RIGHT"><input type="submit" name="btn1" value="Design Timber A-Frame"></TD>
        <td width="70%" ALIGN="LEFT"><input type="reset" value="Reset" name="reset"></td>
      </tr>
    </TABLE>
</TD></TR>
</table>
</form></body>
</html>