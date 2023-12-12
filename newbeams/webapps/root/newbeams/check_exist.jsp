<%@page import="com.util.Profile, com.util.Design, com.util.DesignHelp, com.util.Project, com.util.HashValues"%>
<%@page import="java.util.Iterator"%>
<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />
<jsp:useBean id="existBeamFormHandler" scope="session" class="com.util.ExistBeamFormHandler" />

<%
String beamload="checked", tributaryWidth="unchecked";
Profile profile = profileFormHandler.getProfile();
if (!profile.getValidUser()) {
    response.sendRedirect("log_in.jsp");
    return;
}
Design design = existBeamFormHandler.getDesign();
%>
<html>
<head>
<title>Check an existing beam</title>
<script language='JavaScript' src='newbeams.js'></script>
<link rel="stylesheet" href="newbeams.css">
</head>

<body ONLOAD="setCheckExist(<% out.print("'"+design.getMaterial()+"','"+design.getNumber()+"'");%>)" topmargin=0>
<form method="POST" ACTION="process_check_exist.jsp"  NAME="DesignForm">
<input type="hidden" name="SucessURL" VALUE="check_exist_beam.jsp">
<input type="hidden" name="ValidSxURL" VALUE="result_check_exist.jsp">
<input type="hidden" name="InValidSxURL" VALUE="check_exist_beam.jsp">
<input type="hidden" name="ErrorURL" VALUE="check_exist.jsp">
<table width="100%">
  <tr>
    <td width="22%" valign="TOP">
        <jsp:include page="slide.jsp" flush="true"/>
    </TD>
    <TD WIDTH="78%" VALIGN="TOP">
<%
DesignHelp designHelp = design.getDesignHelp();
if (design.getMaterial().length() <= 0) design.setMaterial("steel");
if (design.getMethodName().equalsIgnoreCase("TributaryWidth")){
    tributaryWidth = "checked";
    beamload = "unchecked";
}else {
    beamload = "checked";
    tributaryWidth = "unchecked";
}
if (existBeamFormHandler.getErrorFound()) {
    Object [] _errorVec = existBeamFormHandler.getFormExceptions();
    request.setAttribute("ErrorVector" ,_errorVec);%>
    <jsp:include page="error_page.jsp" flush="true"/>
    <%
}
%>

<table border=0 cellpadding=2 cellspacing=2 width="100%" ALIGN="CENTER">
      <tr> 
        <td colspan=4 bgcolor="#DBEAF5"> <a name="profiletop"><font class="bigtitle">Check an Existing Beam</font></a> </td>
      </tr>
      <tr>
        <td class="a10black" ALIGN="RIGHT">Select the Beam Material</td>
        <td class="a10black" colspan=3 ALIGN="LEFT">
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
       </tr>
       <tr>
         <td class="a10black" ALIGN="RIGHT">Provide the Beam Span</td>
         <td class="a10black" ALIGN="LEFT">
            <input type="text" name="spanf" size="5" maxLength=3 VALUE=<%=design.getSpanf()%>>feet&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="text" name="spani" size="5" maxLength=3 VALUE=<%=design.getSpani()%>>inches
         </TD>
          <TD></TD>
       </TR>
       <TR> 
        <td class="a10black" ALIGN="RIGHT">Select the Beam Size</td>
        <td colspan="3" class="a10black">
            <select name="beamsize" size="1">
            <%if (design.getBeamSize().length() > 0){%>
                <OPTION VALUE="<%=design.getBeamSize()%>"><%=design.getBeamSize()%></OPTION>
            <%}%>
            <%if (design.getMaterial().length() <= 0){%>
                <jsp:include page="steel1.jsp" flush="true"/>
            <%}else{%>
                <jsp:include page="<%=existBeamFormHandler.getFileName()%>" flush="true"/>
            <%}%>
            </select>
            </td>
      </tr>

              <tr>
         <td class="a10black" ALIGN="RIGHT">
            <INPUT TYPE="RADIO" NAME="loadType" VALUE="Beamload" <%=beamload%> ONCLICK="setEnable()">Provide the Beam load:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         </td>
         <td class="a10black" ALIGN="LEFT">
            <input type="text" name="load" size="5" maxLength=3  VALUE=<%=design.getLoad()%>>kips/ft
         </TD>
       </TR>
       <TR>
         <td class="a10black" ALIGN="CENTER" COLSPAN="1"><b>OR</b></TD>
       </TR>
       <TR>
         <TD class="a10black" ALIGN="RIGHT">
            <INPUT TYPE="RADIO" NAME="loadType" VALUE="TributaryWidth" <%=tributaryWidth%> ONCLICK="setEnable()">
            Provide the <A HREF="javascript:popUpWindow('tributary.jsp', 'Win1')">Tributary width:</A>
         </TD>
         
         <td class="a10black" ALIGN="LEFT" COLSPAN="3">
            <input type="text" name="trifeet" maxLength=3 size="5"  VALUE=<%=design.getTriFeet()%>>feet&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="text" name="triinch" maxLength=3 size="5"  VALUE=<%=design.getTriInch()%>>inch&nbsp;&nbsp;&nbsp;&nbsp;
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
        <td width="30%" ALIGN="RIGHT"><input type="submit" name="btn1" value="Check Beam"></TD>
        <td width="70%" ALIGN="LEFT"><input type="reset" value="Reset" name="reset"></td>
      </tr>
    </TABLE>
</TD></TR>
</table>
</form>

</body>
</html>