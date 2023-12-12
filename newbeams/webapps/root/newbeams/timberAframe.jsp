<%@page import="com.util.Profile, com.util.Design, com.util.DesignHelp, com.util.HashValues"%>
<%@page import="java.util.Iterator"%>

<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />
<jsp:useBean id="timberAframeFormHandler" scope="session" class="com.util.TimberAFrameFormHandler" />

<%
String beamload="checked", tributaryWidth="unchecked";
String asphalt = "selected", builtUp = "", slate = "", wood = "", tile = "";
Profile profile = profileFormHandler.getProfile();
if (!profile.getValidUser()) {
    response.sendRedirect("log_in.jsp");
    return;
}
%>
<html>
<head><title>Design A Timber A-Frame</title>
<script language='JavaScript' src='newbeams.js'></script>
<link rel="stylesheet" href="newbeams.css">
</head>

<body ONLOAD="setEnable()" topmargin=0>
<form method="POST" ACTION="process_design.jsp"  NAME="DesignForm">
<input type="hidden" name="SucessURL" VALUE="design_timberAframe.jsp">
<input type="hidden" name="ErrorURL" VALUE="timberAframe.jsp">
<table width="100%">
  <tr>
    <td width="22%" valign="TOP">
        <jsp:include page="slide.jsp" flush="true"/>
    </TD>
    <TD WIDTH="78%" valign="TOP">
<%
Design design = timberAframeFormHandler.getDesign();
DesignHelp designHelp = design.getDesignHelp();
if (design.getMethodName().equalsIgnoreCase("TributaryWidth")){
    tributaryWidth = "checked";
    beamload = "unchecked";
}else {
    beamload = "checked";
    tributaryWidth = "unchecked";
}
if (newBeamFormHandler.getErrorFound()) {
    Object [] _errorVec = newBeamFormHandler.getFormExceptions();
    request.setAttribute("ErrorVector" ,_errorVec);%>
    <jsp:include page="error_page.jsp" flush="true"/>
<%
}
%>

<table border="0" cellpadding=2 cellspacing=2 width="100%" ALIGN="CENTER">
      <tr> 
        <td colspan=4 bgcolor="#DBEAF5"> <a name="profiletop"><font class="bigtitle">Design a New Beam</font></a> </td>
      </tr>
      <tr>
        <td class="a10black" ALIGN="RIGHT">Select the Beam Material</td>
        <td class="a10black" colspan=3 ALIGN="LEFT">
            <select size="1" name="mater">
                <option value="steel" <%=designHelp.getSteel()%>>Steel Type A36</option>
                <option value="std" <%=designHelp.getStd()%>>Wood/Standard Lumber</option>
                <option value="lvl" <%=designHelp.getLvl()%>>Laminated Veneer Lumber</option>
                <option value="psl" <%=designHelp.getPsl()%>>Parallel Strand Lumber</option>
                <option value="flistd" <%=designHelp.getFlistd()%>>Steel flitch plate + Std.Lumber</option>
                <option value="flilvl" <%=designHelp.getFlilvl()%>>Steel flitch plate + LVL</option>
            </select>
          </td>
       </tr>
       <tr>
         <td class="a10black" ALIGN="RIGHT">Provide the Beam Span</td>
         <td class="a10black" ALIGN="LEFT">
            <input type="text" name="spanf" size="5" maxLength=3 VALUE=<%=design.getSpanf()%>>feet&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="text" name="spani" size="5" maxLength=3 VALUE=<%=design.getSpani()%>>inches
         </TD>
       </TR>
       <tr>
         <td class="a10black" ALIGN="RIGHT">
            <INPUT TYPE="RADIO" NAME="loadType" VALUE="Beamload" <%=beamload%> ONCLICK="setEnable()">Provide the Beam load:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         </td>
         <td class="a10black" ALIGN="LEFT">
            <input type="text" name="load" size="7" maxLength=5 VALUE=<%=design.getLoad()%>>kips/ft
         </TD>
       </TR>
       <TR>
         <td class="a10black" ALIGN="CENTER" COLSPAN="1"><b>OR</b></TD>
       </TR>
       <TR>
         <TD class="a10black" ALIGN="RIGHT">
            <INPUT TYPE="RADIO" NAME="loadType" VALUE="TributaryWidth" <%=tributaryWidth%> ONCLICK="setEnable()">
            Provide the <A HREF="javascript:popUpWindow('tributary.jsp', 'Tributary')">Tributary width:</A>
         </TD>
         <td class="a10black" ALIGN="LEFT" COLSPAN="3">
            <input type="text" name="trifeet" maxLength=2 size="5"  VALUE=<%=design.getTriFeet()%>>feet&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="text" name="triinch" maxLength=3 size="5"  VALUE=<%=design.getTriInch()%>>inches&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="text" name="storey" maxLength=3 size="5"  VALUE=<%=design.getStorey()%>>
                <a href="javaScript:openWindow('roof_image.html','Roof',375,375);">No. of Stories </a>Carried
         </td>
       </TR>
       <TR>
         <td class="a10black" ALIGN="RIGHT">Is there roof included in the no. of stories.</td>
         <td class="a10black" colspan=3 ALIGN="LEFT">
            <select size="1" name="roofIncluded">
                <option value="N">No</OPTION>
                <option value="Y" <%if (design.getRoofIncluded()) out.print("selected");%>>Yes </OPTION>
            </select>
        </TD>
      </TR>
       <TR>
         <td class="a10black" ALIGN="RIGHT">Roof Pitch.</td>
         <td class="a10black" colspan=3 ALIGN="LEFT">
            <input type="text" name="roofPitch" maxLength=5 size="5"  VALUE=<%=design.getRoofPitch()%>>
            &nbsp;&nbsp;Inch per 12" <font color="#FF0000">[between 0.0 to 14.0]</FONT>
        </TD>
      </TR>
       <tr>
        <td class="a10black" ALIGN="RIGHT">Select the material for the Roof</td>
        <td class="a10black" colspan=3 ALIGN="LEFT">
            <select size="1" name="materb">
                <option value="Asphalt" <%=designHelp.getAsphalt()%>>Asphalt</OPTION>
                <option value="BuiltUp" <%=designHelp.getBuildUp()%>>BuiltUp</OPTION>
                <option value="MetalStandingSeam" <%=designHelp.getMetalStandingSeam()%>>Metal Standing Seam</OPTION>
                <option value="Slate" <%=designHelp.getSlate()%>>Slate</OPTION>
                <option value="Wood" <%=designHelp.getWood()%>>Wood</OPTION>
                <option value="Tile" <%=designHelp.getTile()%>>Tile</OPTION>
            </select>
        </TD>
      </TR>
    </TABLE>
    <jsp:include page="include_project.jsp" flush="true"/>
    <TABLE WIDTH="100%">
      <tr>
        <td width="30%" ALIGN="RIGHT"><input type="submit" name="btn1" value="Design Beam"></TD>
        <td width="70%" ALIGN="LEFT"><input type="reset" value="Reset" name="reset"></td>
      </tr>
    </TABLE>
</TD></TR>
</table>
</form></body>
</html>