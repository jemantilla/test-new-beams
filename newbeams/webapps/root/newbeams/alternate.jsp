<%@page import="com.util.Profile, com.util.Design, com.util.DesignHelp, com.util.Project, com.util.HashValues"%>
<%@page import="java.util.Iterator"%>

<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />
<jsp:useBean id="alternateBeamFormHandler" scope="session" class="com.util.AlternateBeamFormHandler" />

<%
String beamload="checked", tributaryWidth="unchecked";
Profile profile = profileFormHandler.getProfile();
if (!profile.getValidUser()) {
    response.sendRedirect("log_in.jsp");
    return;
}
%>
<html>
<head><title>Provide an Alternate beam</title>
<script language='JavaScript' src='newbeams.js'></script>
<link rel="stylesheet" href="newbeams.css">
</head>

<body topmargin=0>
<form method="POST" ACTION="process_alternate.jsp"  NAME="DesignForm">
<input type="hidden" name="SucessURL" VALUE="alternate_beam.jsp">
<input type="hidden" name="BeamOkURL" VALUE="result_alternate_beam.jsp">
<input type="hidden" name="ErrorURL" VALUE="alternate.jsp">
<table width="100%">
  <tr>
    <td width="22%" valign="TOP">
        <jsp:include page="slide.jsp" flush="true"/>
    </TD>
    <TD WIDTH="78%" VALIGN="TOP">
<%
Design design = alternateBeamFormHandler.getDesign();
DesignHelp designHelp = design.getDesignHelp();
if (design.getMaterial().length() <= 0) design.setMaterial("steel");
if (design.getAMaterial().length() <= 0) design.setAMaterial("steel");
if (alternateBeamFormHandler.getErrorFound()) {
    Object [] _errorVec = alternateBeamFormHandler.getFormExceptions();
    request.setAttribute("ErrorVector" ,_errorVec);%>
    <jsp:include page="error_page.jsp" flush="true"/>
    <%
}
%>
  	
<table border="0" cellpadding=2 cellspacing=2 width="100%" ALIGN="CENTER">
      <tr> 
        <td colspan="2" bgcolor="#DBEAF5"> <a name="profiletop"><font class="bigtitle">Provide Detail for Present Beam</font></a> </td>
      </tr>
      <tr>
        <td class="a10black" ALIGN="RIGHT">Select the Material of the Present Beam</td>
        <td class="a10black" ALIGN="LEFT">
            <select size="1" name="mater" onChange="this.form.submit()">
                <option value="steel" <%=designHelp.getSteel()%>>Steel Type A36</option>
                <option value="std" <%=designHelp.getStd()%>>Wood/Standard Lumber</option>
                <option value="lvl" <%=designHelp.getLvl()%>>Laminated Veneer Lumber</option>
                <!--<option value="psl" <%=designHelp.getPsl()%>>Parallel Strand Lumber</option>-->
                <option value="flistd" <%=designHelp.getFlistd()%>>Steel flitch plate + Std.Lumber</option>
                <option value="flilvl" <%=designHelp.getFlilvl()%>>Steel flitch plate + LVL</option>
            </select>&nbsp;&nbsp;
            <select size="1" name="number" onChange="this.form.submit()">
                <%if (design.getMaterial().equalsIgnoreCase("steel")){%>
                    <option value="1">Single I Beam</option>
                <%}else if (design.getMaterial().equals("std")){%>
                    <option value="1" <%=designHelp.getOne()%>>Single Std.Lumber</option>
                    <option value="2" <%=designHelp.getTwo()%>>Double Std.Lumber</option>
                    <option value="3" <%=designHelp.getThree()%>>Triple Std.Lumber</option>
                    <option value="4" <%=designHelp.getFour()%>>Quad Std.Lumber</option>
                    <option value="5" <%=designHelp.getFive()%>>Five Std.Lumber</option>
                <%}else if (design.getMaterial().equals("lvl")){%>
                    <option value="1" <%=designHelp.getOne()%>>Single LVL</option>
                    <option value="2" <%=designHelp.getTwo()%>>Double LVL</option>
                    <option value="3" <%=designHelp.getThree()%>>Triple LVL</option>
                    <option value="4" <%=designHelp.getFour()%>>Quad LVL</option>
                    <option value="5" <%=designHelp.getFive()%>>Five LVL</option>
                <%}else if (design.getMaterial().equals("flistd")) {%>
                    <option value="1" <%=designHelp.getOne()%>>Double Std.Lumber + Single Plate</option>
                    <option value="2" <%=designHelp.getTwo()%>>Four Std.Lumber + Single Plate</option>
                    <option value="3" <%=designHelp.getThree()%>>Triple Std.Lumber + Double Plate</option>
                <%}else if (design.getMaterial().equals("flilvl")){%>
                    <option value="1" <%=designHelp.getOne()%>>Double LVL + Single Plate</option>
                    <option value="2" <%=designHelp.getTwo()%>>Four LVL + Single Plate</option>
                    <option value="3" <%=designHelp.getThree()%>>Triple LVL + Double Plate</option>
                <%}%>
            </select>&nbsp;
            <%if (!design.getMaterial().equals("steel")){%>
                &nbsp;<A HREF="javaScript:openWindow('<%=design.getMaterial()%>.jsp','Images',675,625);">View Image</A>
            <%}%>
        </td>
        <!--<TD>&nbsp;</TD>
        <TD>&nbsp;</TD>-->
      </tr>
      <tr>
        <td class="a10black" ALIGN="RIGHT">Provide the Beam Span</td>
        <td class="a10black" ALIGN="LEFT">
            <input type="text" name="spanf" size="5" maxLength=3 VALUE=<%=design.getSpanf()%>>feet&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="text" name="spani" size="5" maxLength=3 VALUE=<%=design.getSpani()%>>inches
        </TD>
      </TR>
      <TR> 
        <td class="a10black" ALIGN="RIGHT">Select the Beam Size</td>
        <td class="a10black"> 
            <select name="beamsize" size="1">
            <%if (design.getBeamSize().length() > 0){%>
                <OPTION VALUE="<%=design.getBeamSize()%>"><%=design.getBeamSize()%></OPTION>
            <%}%>
            <%if (design.getMaterial().length() <= 0){%>
                <jsp:include page="steel1.jsp" flush="true"/>
            <%}else{%>
                <jsp:include page="<%=alternateBeamFormHandler.getFileName()%>" flush="true"/>
            <%}%>
            </select>
            </td>
      </tr>
      <tr> 
        <td colspan="2" bgcolor="#DBEAF5" class="bigtitle"><font class="bigtitle">Provide detail for Alternate Beam</td>
      </tr>
      <tr>
        <td class="a10black" ALIGN="RIGHT">Select the Material of the Alternate Beam</td>
        <td class="a10black" ALIGN="LEFT">
            <select size="1" name="aMater" onChange="this.form.submit()">
                <option value="steel" <%=designHelp.getASteel()%>>Steel Type A36</option>
                <option value="std" <%=designHelp.getAStd()%>>Wood/Standard Lumber</option>
                <option value="lvl" <%=designHelp.getALvl()%>>Laminated Veneer Lumber</option>
                <option value="flistd" <%=designHelp.getAFlistd()%>>Steel flitch plate + Std.Lumber</option>
                <option value="flilvl" <%=designHelp.getAFlilvl()%>>Steel flitch plate + LVL</option>
            </select>&nbsp;&nbsp;
            <select size="1" name="aNumber" onChange="this.form.submit()">
            <%if (design.getAMaterial().equalsIgnoreCase("steel")){%>
                    <option value="1">Single I Beam</option>
                <%}else if (design.getAMaterial().equals("std")){%>
                    <option value="1" <%=designHelp.getAOne()%>>Single Std.Lumber</option>
                    <option value="2" <%=designHelp.getATwo()%>>Double Std.Lumber</option>
                    <option value="3" <%=designHelp.getAThree()%>>Triple Std.Lumber</option>
                    <option value="4" <%=designHelp.getAFour()%>>Quad Std.Lumber</option>
                    <option value="5" <%=designHelp.getAFive()%>>Five Std.Lumber</option>
                <%}else if (design.getAMaterial().equals("lvl")){%>
                    <option value="1" <%=designHelp.getAOne()%>>Single LVL</option>
                    <option value="2" <%=designHelp.getATwo()%>>Double LVL</option>
                    <option value="3" <%=designHelp.getAThree()%>>Triple LVL</option>
                    <option value="4" <%=designHelp.getAFour()%>>Quad LVL</option>
                    <option value="5" <%=designHelp.getAFive()%>>Five LVL</option>
                <%}else if (design.getAMaterial().equals("flistd")) {%>
                    <option value="1" <%=designHelp.getAOne()%>>Double Std.Lumber + Single Plate</option>
                    <option value="2" <%=designHelp.getATwo()%>>Four Std.Lumber + Single Plate</option>
                    <option value="3" <%=designHelp.getAThree()%>>Triple Std.Lumber + Double Plate</option>
                <%}else if (design.getAMaterial().equals("flilvl")){%>
                    <option value="1" <%=designHelp.getAOne()%>>Double LVL + Single Plate</option>
                    <option value="2" <%=designHelp.getATwo()%>>Four LVL + Single Plate</option>
                    <option value="3" <%=designHelp.getAThree()%>>Triple LVL + Double Plate</option>
                <%}%>
            </select>&nbsp;
            <%if (!design.getAMaterial().equals("steel")){%>
                &nbsp;<A HREF="javaScript:openWindow('<%=design.getAMaterial()%>.jsp','Images',675,625);">View Image</A>
            <%}%>
          </td>
      </tr>
      <TR> 
        <td class="a10black" ALIGN="RIGHT">Select the Beam Size</td>
        <td class="a10black"> 
            <select name="aBeamsize" size="1">
            <%if (design.getABeamSize().length() > 0){%>
                <OPTION VALUE="<%=design.getABeamSize()%>"><%=design.getABeamSize()%></OPTION>
            <%}%>
            <%if (design.getAMaterial().length() <= 0){%>
                <jsp:include page="steel1.jsp" flush="true"/>
            <%}else{%>
                <jsp:include page="<%=alternateBeamFormHandler.getAFileName()%>" flush="true"/>
            <%}%>
            </select>
            </td>
      </tr>
    </TABLE>
    <jsp:include page="include_project.jsp" flush="true"/>
    <TABLE WIDTH="100%">
      <tr>
        <td width="30%" ALIGN="RIGHT"><input type="submit" name="btn1" value="Alternate Beam"></TD>
        <td width="70%" ALIGN="LEFT"><input type="reset" value="Reset" name="reset"></td>
      </tr>
    </TABLE>
</TD></TR>
</table>
</form></body>
</html>
