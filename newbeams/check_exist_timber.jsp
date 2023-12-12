<%@page import="com.util.Profile, com.util.Design, com.util.DesignHelp"%>

<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />
<jsp:useBean id="existTimberFormHandler" scope="session" class="com.util.ExistTimberFormHandler" />

<%
String beamload="checked", tributaryWidth="unchecked";
String first="checked", upper="unchecked", attic="unchecked", roof="unchecked";
Profile profile = profileFormHandler.getProfile();
if (!profile.getValidUser()) {
    response.sendRedirect("log_in.jsp");
    return;
}
Design design = existTimberFormHandler.getDesign();
if (design.getMaterial().equals("steel")) {
    design.setMaterial("std");
}
%>
<html>
<head><title>Check an Existing Timber Floor Joist</title>
<!--<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="-1">-->
<script language='JavaScript' src='newbeams.js'></script>
<link rel="stylesheet" href="newbeams.css">
</head>

<body ONLOAD="setEnableTimber(<% out.print("'"+design.getMaterial()+"','"+design.getNumber()+"'");%>)" topmargin=0>
<form method="POST" ACTION="process_check_exist_timber.jsp"  NAME="DesignForm">
<input type="hidden" name="ValidSxURL" VALUE="result_check_exist_timber.jsp">
<input type="hidden" name="InValidSxURL" VALUE="check_exist_timber_beam.jsp">
<input type="hidden" name="SucessURL" VALUE="check_exist_timber_beam.jsp">
<input type="hidden" name="ErrorURL" VALUE="check_exist_timber.jsp">

<INPUT TYPE="HIDDEN" name="test" value="javaScript:RefreshPage();">
<table width="100%">
  <tr>
    <td width="22%" valign="TOP">
        <jsp:include page="slide.jsp" flush="true"/>
    </TD>
    <TD WIDTH="78%" valign="TOP">
<%
DesignHelp designHelp = design.getDesignHelp();
if (existTimberFormHandler.getErrorFound()) {
    Object [] _errorVec = existTimberFormHandler.getFormExceptions();
    request.setAttribute("ErrorVector" ,_errorVec);%>
    <jsp:include page="error_page.jsp" flush="true"/>
<%
}
if (design.getFloorType().equals("Upper")) {
    first = "unchecked";
    upper = "checked";
    attic = "unchecked";
    roof = "unchecked";
}else if (design.getFloorType().equals("Attic")) {
    first = "unchecked";
    upper = "unchecked";
    attic = "checked";
    roof = "unchecked";
}else if (design.getFloorType().equals("Roof")) {
    first = "unchecked";
    upper = "unchecked";
    attic = "unchecked";
    roof = "checked";
}else {
    first = "checked";
    upper = "unchecked";
    attic = "unchecked";
    roof = "unchecked";
}
//design.setMaterial(design.getMaterial());
//design.setNumber(design.getNumber());
//out.print("design.getBeamsize():"+design.getBeamSize()+"-mater" + design.getMaterial()+"-Number:"+design.getNumber());
//out.print("file:"+ existTimberFormHandler.getFileName());
%>

<table border="0" cellpadding=2 cellspacing=2 width="100%" ALIGN="CENTER">
      <tr> 
        <td colspan=4 bgcolor="#DBEAF5"> <a name="profiletop"><font class="bigtitle">Check an Existing Timber Floor Joist</font></a> </td>
      </tr>
      <tr>
        <td class="a10black" colspan=1 ALIGN="RIGHT">Select the Joist Material</td>
        <td class="a10black" colspan=3 ALIGN="LEFT">
            <select size="1" name="mater" onChange="this.form.submit()">
                <option <%=designHelp.getStd()%> value="std">Wood/Standard Lumber</option>
                <!--<option value="woodIShapeWood" <%=designHelp.getWoodIShapeWood()%>>Wooden I shaped joists with standard wood flange</option>
                <option value="woodIShapeLVL" <%=designHelp.getWoodIShapeLVL()%>>Wooden I shaped joists with LVL flange</option>-->
                <option <%=designHelp.getLvl()%> value="lvl">Laminated Veneer Lumber</option>
                <option <%=designHelp.getPsl()%> value="psl">Parallel Strand Lumber</option>
            </select>&nbsp;
            <select size="1" name="number" onChange="this.form.submit()">
            <%if (design.getMaterial().equals("lvl")){%>
                <option <%=designHelp.getOne()%> value="1">Single LVL</option>
                <option <%=designHelp.getTwo()%> value="2">Double LVL</option>
                <option <%=designHelp.getThree()%> value="3">Triple LVL</option>
                <option <%=designHelp.getFour()%> value="4">Quart LVL</option>
                <option <%=designHelp.getFive()%> value="5">Five LVL</option>
            <%}else if (design.getMaterial().equals("woodIShapeWood")) {%>
                <option value="1">Wood Flange</option>
            <%}else if (design.getMaterial().equals("woodIShapeLVL")){%>
                <option value="1">LVL Flange</option>
            <%}else if (design.getMaterial().equals("psl")){%>
                <option <%=designHelp.getOne()%> value="1">Single PSL</option>
                <option <%=designHelp.getTwo()%> value="2">Double PSL</option>
            <%}else {%>
                <option value="1" <%=designHelp.getOne()%>>Single Std.Lumber</option>
                <option value="2" <%=designHelp.getTwo()%>>Double Std.Lumber</option>
                <option value="3" <%=designHelp.getThree()%>>Triple Std.Lumber</option>
                <option value="4" <%=designHelp.getFour()%>>Quart Std.Lumber</option>
                <option value="5" <%=designHelp.getFive()%>>Five Std.Lumber</option>
            <%}%>
            </select>
        </td>
       </tr>
       <tr> 
        <td class="a10black" ALIGN="RIGHT">Select the Joist Size</td>
        <td colspan="3" class="a10black">
            <select name="beamsize" size="1">
            <%if (design.getBeamSize().length() > 0){%>
                <OPTION VALUE="<%=design.getBeamSize()%>"><%=design.getBeamSize()%></OPTION>
            <%}%>
            <%if (design.getMaterial().equals("steel")){%>
                <jsp:include page="steel1.jsp" flush="true"/>
            <%}else{%>
                <jsp:include page="<%=existTimberFormHandler.getFileName()%>" flush="true"/>
            <%}%>
            </select>
            </td>
      </tr>
       <tr>
         <td class="a10black" ALIGN="RIGHT">Spacing Between Joists</td>
         <td class="a10black" colspan=3 ALIGN="LEFT"> 
            <input type="text" name="spaceIn" size="5" maxLength=3 VALUE=<%=design.getSpaceIn()%>>inches
         </TD>
       </TR>

       <tr>
         <td class="a10black" ALIGN="RIGHT">Provide the Joist Span</td>
         <td class="a10black" colspan=3 ALIGN="LEFT">
            <input type="text" name="spanf" size="5" maxLength=3 VALUE=<%=design.getSpanf()%>>feet&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="text" name="spani" size="5" maxLength=3 VALUE=<%=design.getSpani()%>>inches
         </TD>
       </TR>
       <tr>
         <td class="a10black" ALIGN="RIGHT"><B>Select The Joist Location:</B></td>
         <td class="a10black" ALIGN="LEFT">
           <INPUT TYPE="RADIO" NAME="floorType" VALUE="First" <%=first%> >First Floor
         </td>
       </TR>
       <TR>
         <TD>&nbsp;</TD>
         <TD class="a10black" ALIGN="LEFT">
            <INPUT TYPE="RADIO" NAME="floorType" VALUE="Upper" <%=upper%> >Upper Floor
         </TD>
       </TR>
         <TD>&nbsp;</TD>
         <TD class="a10black" ALIGN="LEFT">
            <INPUT TYPE="RADIO" NAME="floorType" VALUE="Attic" <%=attic%> >Attic
         </TD>
       </TR>
         <TD>&nbsp;</TD>
         <TD class="a10black" ALIGN="LEFT">
            <INPUT TYPE="RADIO" NAME="floorType" VALUE="Roof" <%=roof%> >Roof
         </TD>
       </TR>
    </TABLE>
    <jsp:include page="include_project.jsp" flush="true"/>
    <TABLE WIDTH="100%">
      <tr>
        <td width="30%" ALIGN="RIGHT"><input type="submit" name="btn1" value="Check Joist"></TD>
        <td width="70%" ALIGN="LEFT"><input type="reset" value="Reset" name="reset"></td>
      </tr>
    </TABLE>
</TD></TR>
</table>
</form>
</body>
</html>