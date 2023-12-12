<%@ page import="java.text.*, java.util.*, com.util.TempClass, com.util.Design, com.util.Project, com.util.HashValues" %>
<%@ page import="com.util.Utility" %>

<jsp:useBean id="timberAFrameFormHandler" scope="session" class="com.util.TimberAFrameFormHandler" />
<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />
<jsp:useBean id="projectFormHandler" scope="session" class="com.util.ProjectFormHandler" />

<%
if (!profileFormHandler.getProfile().getValidUser()) {
    response.sendRedirect("log_in.jsp");
    return;
}
Design design = timberAFrameFormHandler.getDesign();
TempClass _tempclass = timberAFrameFormHandler.getTempClass();
if(_tempclass == null) System.out.println("_tempclass is null");
Project project = projectFormHandler.getProject();
request.setAttribute("design" , design);
request.setAttribute("project" , project);
%>
<html>
<head>
    <title> Result </title>
    <link rel="stylesheet" href="newbeams.css">
</head>
<body TOPMARGIN="0" bgcolor="#CFCFCF">
<FORM ACTION="process_result_aframe.jsp" METHOD="POST">
<input type="hidden" name="SucessURL" VALUE="result_aframe.jsp">
<input type="hidden" name="ErrorURL" VALUE="design_aframe.jsp">
<table width="100%" BORDER="0">
  <tr>
  
    <TD WIDTH="25%" VALIGN="TOP">
        <jsp:include page="include_resultaframe_left.jsp" flush="true"/>
    </TD>
    
    <TD WIDTH="75%" VALIGN="TOP">
    <center><b><font size=5 color="blue"><u>Calculations</u></font></b></center><br>
    <!--<img width=200 height=80 align=left src=currentimage>-->    
    <table border=1 cellpadding=2 cellspacing=2 width="80%" align="CENTER" bgcolor="#DBEAF5">
        <tr>
            <td class="a10blackbold" align="center">Required Sx </td>
            <td class="a10black" align="center"><%=Utility.getTwoDecimal(design.getSx())%> in<sup>3</sup></td>
        </tr>
        <tr>
            <td class="a10blackbold" align="center">Tension in Collar Tie</td>
            <td class="a10black" align="center"><%=Utility.getTwoDecimal(design.getT())%> Kips</td>
        </tr>
        <tr>
            <td class="a10blackbold" align="center">Min. C.S. Area of Collar Tie</td>
            <td class="a10black" align="center"><%=Utility.getTwoDecimal(design.getArea())%>in<SUP>2</SUP></td>
        </tr>
        <tr>
            <td class="a10blackbold" align="center">Rafter Size </td>
            <td class="a10black" align="center"><%=_tempclass.getVal()%></td>
                              
        </tr>
        <tr>
            <td class="a10blackbold" align="center">Actual Sx </td>
            <td class="a10black" align="center"><%=Utility.getTwoDecimal(_tempclass.getActsx())%> in<sup>3</sup></td>
        </tr>
        <tr>
            <td class="a10blackbold" align="center">Bending Stress </td>
            <td class="a10black" align="center"><%=Utility.getTwoDecimal(_tempclass.getFbx())%> Ksi</td>
        </tr>
        
    </TABLE><BR>
    
    <CENTER>
    <%if (projectFormHandler.getBeamInserted()) { 
        out.print("<FONT CLASS=\"a12greenbold\">AFrame Saved</FONT>");
    }else {%>
        <input TYPE="SUBMIT" VALUE="Save Result">
    <%}%>    
    <BR><BR>
    <A HREF="design_aframe.jsp">Home</A>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <A HREF="javascript:window.print();">Print Results</A>
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
</BODY></HTML>

