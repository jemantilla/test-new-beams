<%@ page import="java.util.ArrayList, com.util.TempClass, com.util.Project, com.util.Utility, com.util.Design, com.util.Profile" %>
<%@ page import="com.util.HashValues" %>

<jsp:useBean id="existTimberFormHandler" scope="session" class="com.util.ExistTimberFormHandler" />
<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />
<jsp:useBean id="projectFormHandler" scope="session" class="com.util.ProjectFormHandler" />

<%
Profile profile = profileFormHandler.getProfile();
if (!profile.getValidUser()) {
    response.sendRedirect("log_in.jsp");
    return;
}
%>
<HTML>
<HEAD><TITLE>Check Exist Beam</TITLE>
<link rel="stylesheet" href="newbeams.css">
<script language='JavaScript' src='newbeams.js'></script>
</HEAD>                                                      
<body TOPMARGIN="0" bgcolor="#C0C0C0">
<%
Design design  = existTimberFormHandler.getDesign();
String backURL = request.getParameter("backURL");
projectFormHandler.insertProject();
Project project = projectFormHandler.getProject();
request.setAttribute("design" , design);
request.setAttribute("project" , project);
if (design.getValidSx()) {%>
    <FORM ACTION="process_result_check_exist.jsp" METHOD="POST" NAME="DesignForm">
    <input type="hidden" name="SucessURL" VALUE="result_check_exist_timber.jsp">
    <input type="hidden" name="ErrorURL" VALUE="check_exist_timber.jsp">
    <INPUT TYPE="hidden" name="backURL" value="<%=backURL%>">
    <table width="100%" BORDER="0">
    <tr>
  
    <TD WIDTH="25%" VALIGN="TOP">
        <jsp:include page="include_result_left.jsp" flush="true"/>
    </TD>
    
    <TD WIDTH="75%" VALIGN="TOP">
    <center><b><font size=5 color="blue"><u>Calculations</u></font></b></center><br>
            <%if (existTimberFormHandler.getAlternateBeam()){ 
                design.setStatus("Exist_Alter");%>
                <CENTER><FONT class="a12bluebold">
                    Your existing Joist is no good. An alternate Joist is listed below:</FONT></CENTER>
            <%}else { design.setStatus("Exist_OK");%>
                <CENTER><FONT class="a12bluebold">
                    Your existing Joist is ok</FONT></CENTER>
            <%}%>
<table width="100%" border="1" cellspacing="2" cellpadding="0" bgcolor="#DBEAF5">
    <tr>
        <td class="a10blackbold" align="center">Sx</td>
        <td class="a10black" align="center"><%=Utility.getTwoDecimal(design.getSx())%></td>
    </tr>
    <tr>
        <td class="a10blackbold" align="center">Material</td>
        <td class="a10black" align="center"><%=HashValues.getMaterialValue(design.getMaterial())%></td>
    </tr>
    <tr>
        <td class="a10blackbold" align="center">Size</td>
        <td class="a10black" align="center"><%=design.getShape()%></td>
    </tr>
    <tr>
        <td class="a10blackbold" align="center">I</td>
        <td class="a10black" align="center"><%=design.getICheck()%></td>
    </tr>
    <tr>
        <td class="a10blackbold" align="center">Deflection (in)</td>
        <td class="a10black" align="center"><%=design.getDeflectCheck()%></td>
    </tr>
    <tr>
        <td class="a10blackbold" align="center">Deflection (span) </td>
        <td class="a10black" align="center">L / <%=design.getDeflectSpCheck()%></td>
    </tr>
</TABLE><BR>
    
    <CENTER>
    <%if (projectFormHandler.getBeamInserted()) { 
        out.print("<FONT CLASS=\"a12greenbold\">Beam Saved</FONT>");
    }else {%>
        <input TYPE="SUBMIT" name="btn" VALUE="Save Result" ONCLICK="saveBeamClicked()">
    <%}%>    
    <BR><BR>
    <A HREF="check_exist_timber.jsp">Home</A>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <A HREF="javascript:window.print();">Print Results</A><br><BR>
	<A HREF="<%=backURL%>">Go Back</A>
    </CENTER><BR>
    <table width="100%" align="CENTER">
      <TR>
        <TD WIDTH="10%">&nbsp;</TD>
        <TD>
          <jsp:include page="include_result_bottom.html" flush="true"/>
        </TD>
      </TR>
    </TABLE>

  </TD>
  </TR>
</TABLE>
</FORM>
<%}else{%>
<table width="100%" BORDER="0">
  <tr>
    <td width="22%">
        <jsp:include page="slide.jsp" flush="true"/>
    </TD>
    <TD WIDTH="78%" valign="TOP" ALIGN="CENTER">
      <FONT class="a12bluebold">Your beamsize can't work with this load</FONT><BR><BR>
      <A HREF="<%=backURL%>">Go Back</A>
    </TD>
  </TR>
</table>  
<%}%>
</BODY>
</HTML>
