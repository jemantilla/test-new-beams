<%@ page import="java.text.*, java.util.*, com.util.TempClass, com.util.Design, com.util.Project, com.util.HashValues" %>
<%@ page import="com.util.Utility" %> 

<jsp:useBean id="alternateBeamFormHandler" scope="session" class="com.util.AlternateBeamFormHandler" />
<jsp:useBean id="projectFormHandler" scope="session" class="com.util.ProjectFormHandler" />
<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />

<%
if (!profileFormHandler.getProfile().getValidUser()) {
    response.sendRedirect("log_in.jsp");
    return;
}
Design design = alternateBeamFormHandler.getDesign();
Project project = projectFormHandler.getProject();
TempClass tempClass0 = alternateBeamFormHandler.getTempClass();
%>
<html>
<head>
    <title> Result </title>
    <link rel="stylesheet" href="newbeams.css">
</head>
<body>
<CENTER><IMG SRC="graphics/logo.gif" BORDER="0"></CENTER>
<table width="100%">
  <tr>
    <TD WIDTH="78%" VALIGN="TOP">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <TR><TD WIDTH="70%">
        <FONT class="a10blackbold">
        <B>Project Detail:</B>&nbsp;&nbsp;</FONT><font class="a10black"><%=project.getProjectName()%><BR>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <%if (project.getStreet().length()>0) out.print(project.getStreet()+",<BR>");%>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <%if (project.getCity().length()>0) out.print(project.getCity()+",<BR>");%>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <%if (project.getState().length()>0) out.print(project.getState()+"-"); out.print(project.getZip());%></font>
        </FONT>
      </TD>
      <TD WIDTH="30%"><img align=left alt="load" src="graphics/load.gif"></TD></TR>
    </TABLE>
    <br><center><b><font size=5>Calculations</font></b><br></center><br>
    <!--<img width=200 height=80 align=left src=currentimage>-->
    <center>
    <table border=1 cellpadding=2 cellspacing=2 width="65%" align="CENTER" bgcolor="#DBEAF5">
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
            <td class="a10black" align="center"><%=HashValues.getMaterialValue(design.getAMaterial())%></td>
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
                <%if (design.getAMaterial().equalsIgnoreCase("flistd")){
                    out.print(tempClass0.getLookUp());
                }else out.print(tempClass0.getVal());%></B></center></td>
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
            <td class="a10blackbold" align="center">Deflection (in)</td>
            <td class="a10black" align="center"><%=tempClass0.getDeflection()%> in</td>
        </tr>
        <tr>
            <td class="a10blackbold" align="center">Deflection (span)</td>
            <td class="a10black" align="center">L /<%=tempClass0.getDeflectionsp()%></td>
        </tr>
        
    </TABLE>
  </TD></TR>
</TABLE>  
<BR><BR><BR>
<CENTER>
<A HREF="design.jsp">Home</A>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<A HREF="javascript:window.print();">Print Results</A>
</CENTER>
</BODY></HTML>

