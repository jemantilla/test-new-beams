<%@ page import="java.text.*, java.util.*, com.util.TempClass, com.util.Design, com.util.Project, com.util.HashValues" %>
<%@ page import="com.util.Utility" %>

<jsp:useBean id="newBeamFormHandler" scope="session" class="com.util.NewBeamFormHandler" />
<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />
<jsp:useBean id="projectFormHandler" scope="session" class="com.util.ProjectFormHandler" />

<%
if (!profileFormHandler.getProfile().getValidUser()) {
    response.sendRedirect("log_in.jsp");
    return;
}
Design design = newBeamFormHandler.getDesign();
TempClass tempClass0 = newBeamFormHandler.getTempClass();
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
<FORM ACTION="process_result_beam.jsp" METHOD="POST">
<input type="hidden" name="SucessURL" VALUE="result_beam.jsp">
<input type="hidden" name="ErrorURL" VALUE="design.jsp">
<table width="100%" BORDER="0">
  <tr>
  
    <TD WIDTH="25%" VALIGN="TOP">
        <jsp:include page="include_result_left.jsp" flush="true"/>
    </TD>
    
    <TD WIDTH="75%" VALIGN="TOP">
    <center><b><font size=5 color="blue"><u>Calculations</u></font></b></center><br>
    <!--<img width=200 height=80 align=left src=currentimage>-->    
    <table border=1 cellpadding=2 cellspacing=2 width="80%" align="CENTER" bgcolor="#DBEAF5">
        <tr>
            <td class="a10blackbold" align="center">Span</td>
            <td class="a10black" align="center"><%=design.getSpan()%> feet</td>
        </tr>
        <tr>
            <td class="a10blackbold" align="center">Uniform Load</td>
            <td class="a10black" align="center"><%=Utility.getTwoDecimal(design.getLoad())%> Kips/ft</td>
        </tr>
        <tr>
            <td class="a10blackbold" align="center">Material</td>
            <td class="a10black" align="center"><%=HashValues.getMaterialValue(design.getMaterial())%></td>
        </tr>
        <tr>
            <td class="a10blackbold" align="center">Moment </td>
            <td class="a10black" align="center"><%=Utility.getTwoDecimal(design.getM())%> Kip-Ft</td>
        </tr>
        <tr>
            <td class="a10blackbold" align="center">Required Sx </td>
            <td class="a10black" align="center"><%=Utility.getTwoDecimal(design.getSx())%> in<sup>3</sup></td>
        </tr>
        <tr>
            <td class="a10blackbold" align="center">Size </td>
            <td class="a10black" align="center">
                <%if (design.getMaterial().equalsIgnoreCase("flistd")){
                    out.print(tempClass0.getLookUp());
                }else out.print(tempClass0.getVal());%></td>
        </tr>
        <tr>
            <td class="a10blackbold" align="center">Actual Sx </td>
            <td class="a10black" align="center"><%=Utility.getTwoDecimal(tempClass0.getActsx())%> in<sup>3</sup></td>
        </tr>
        <tr>
            <td class="a10blackbold" align="center">Bending Stress </td>
            <td class="a10black" align="center"><%=Utility.getTwoDecimal(tempClass0.getFbx())%> Ksi</td>
        </tr>
        <tr>
            <td class="a10blackbold" align="center">Total Weight </td>
            <td class="a10black" align="center"><%=tempClass0.getTotalWeight()%> lbs</td>
        </tr>
        <tr>
            <td class="a10blackbold" align="center">Ix </td>
            <td class="a10black" align="center"><%=Utility.getTwoDecimal(tempClass0.getMoi())%> in<sup>4</sup></td>
        </tr>
        <tr>
            <td class="a10blackbold" align="center">Deflection (in)</td>
            <td class="a10black" align="center"><%=tempClass0.getDeflection()%> in</td>
        </tr>
        <tr>
            <td class="a10blackbold" align="center">Deflection (span)</td>
            <td class="a10black" align="center">L /<%=tempClass0.getDeflectionsp()%></td>
        </tr>
    </TABLE><BR>
    
    <CENTER>
    <%if (projectFormHandler.getBeamInserted()) { 
        out.print("<FONT CLASS=\"a12greenbold\">Beam Saved</FONT>");
    }else {%>
        <input TYPE="SUBMIT" VALUE="Save Result">
    <%}%>    
    <BR><BR>
    <A HREF="design.jsp">Home</A>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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

