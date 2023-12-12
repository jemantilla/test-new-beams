<%@page import="com.util.Profile, com.util.Design, com.util.DesignHelp, com.util.Project, com.util.HashValues"%>
<%@ page import="com.util.TJILVLFlange, com.util.TJIWoodFlange" %>
<%@page import="java.util.Iterator"%>

<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />
<jsp:useBean id="alternateJoistFormHandler" scope="session" class="com.util.AlternateJoistFormHandler" />

<%
String first="checked", upper="unchecked", attic="unchecked", roof="unchecked";


Profile profile = profileFormHandler.getProfile();
if (!profile.getValidUser()) {
    response.sendRedirect("log_in.jsp");                               
    return;
}
%>
<html>
<head><title>Provide and Alternate Joist System</title>
<script language='JavaScript' src='newbeams.js'></script>
<link rel="stylesheet" href="newbeams.css">
</head>

<body topmargin=0>
<form method="POST" ACTION="process_alternate_joist.jsp"  NAME="DesignForm">
<input type="hidden" name="SucessURL" VALUE="alternate_timberjoist.jsp">
<input type="hidden" name="BeamOkURL" VALUE="result_alternate_joist.jsp">
<input type="hidden" name="ErrorURL" VALUE="alternate_joist.jsp">
<table width="100%">
  <tr>
    <td width="22%" valign="TOP">
        <jsp:include page="slide.jsp" flush="true"/>
    </TD>
    <TD WIDTH="78%" valign="TOP">
<%
Design design = alternateJoistFormHandler.getDesign();
DesignHelp designHelp = design.getDesignHelp();

if ( (design.getMaterial().length() <= 0) || (design.getMaterial().equalsIgnoreCase("steel")) ) 
    design.setMaterial("std");
if ( (design.getAMaterial().length() <= 0) || (design.getAMaterial().equalsIgnoreCase("steel")) ) 
    design.setAMaterial("std");

//project = projectFormHandler.getProject();
if (alternateJoistFormHandler.getErrorFound()) {
    Object [] _errorVec = alternateJoistFormHandler.getFormExceptions();
    request.setAttribute("ErrorVector" ,_errorVec);%>
    <jsp:include page="error_page.jsp" flush="true"/>
<%
}
if (design.getMaterial().equalsIgnoreCase("steel")) {
    design.setMaterial("std");
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
%>
<table border="0" cellpadding=2 cellspacing=2 width="100%" valign="Top" ALIGN="CENTER">
      <tr>
        <td colspan=4 bgcolor="#DBEAF5" class="bigtitle">Provide the Detail for the Present Joist</td>
      </tr>
      <tr>
        <td class="a10black" colspan=1 ALIGN="RIGHT">Select the Material for the Present Joist</td>
        <td class="a10black" colspan=3 ALIGN="LEFT">
            <select size="1" name="mater" onChange="this.form.submit()">
                <option value="std" <%=designHelp.getStd()%>>Wood/Standard Lumber</option>
                <option value="woodIShapeWood" <%=designHelp.getWoodIShapeWood()%>>Wooden I-Shaped Joists with Wood Flange</option>
                <option value="woodIShapeLVL" <%=designHelp.getWoodIShapeLVL()%>>Wooden I-Shaped Joists with LVL Flange</option>   
                <option value="lvl" <%=designHelp.getLvl()%>>Laminated Veneer Lumber</option>
                <option value="psl" <%=designHelp.getPsl()%>>Parallel Strand Lumber</option>  
            </select>
             
          </td>
       </tr>
        <tr> 
        <td class="a10black" ALIGN="RIGHT">Select the Beam Size</td>
        <td colspan="3" class="a10black">
            <select name="beamsize" size="1">
            <%if (design.getBeamSize().length() > 0){%>
            
              <% if (design.getMaterial().equalsIgnoreCase("woodIShapeLVL")) {%>
                <OPTION VALUE="<%=design.getBeamSize()%>"><%=TJILVLFlange.getTJILVLFlangeLookup(design.getBeamSize())%></OPTION>
              <%} else if (design.getMaterial().equalsIgnoreCase("woodIShapeWood")) {%>
                <OPTION VALUE="<%=design.getBeamSize()%>"><%=TJIWoodFlange.getTJIWoodFlangeLookup(design.getBeamSize())%></OPTION>
              <%} else {%>
                <OPTION VALUE="<%=design.getBeamSize()%>"><%=design.getBeamSize()%></OPTION>
              <%}%>
              
            <%}%> 
                               
             <%if (design.getMaterial().equals("std")){%>
                <jsp:include page="std1.jsp" flush="true"/>
            <%}else if (design.getMaterial().equals("woodIShapeWood")) {%>
                <jsp:include page="TJIWoodflange.jsp" flush="true"/>
            <%}else if (design.getMaterial().equals("woodIShapeLVL")){%>
                <jsp:include page="TJILVLflange.jsp" flush="true"/>
            <%}else if (design.getMaterial().equals("lvl")){%>
                <jsp:include page="lvlsize.jsp" flush="true"/>
            <%}else if (design.getMaterial().equals("psl")){%>
                <jsp:include page="pslsize.jsp" flush="true"/>
             <%}%>
                </select>
            </td>
      </tr>
       <tr>
         <td class="a10black" ALIGN="RIGHT">Spacing Between Joists</td>
         <td class="a10black" colspan=3 ALIGN="LEFT"> 
            <input type="text" name="spaceIn" size="5" maxLength=3 VALUE=<%=design.getSpaceIn()%>>inches O.C.
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
    
    
    <tr> 
        <td colspan="2" bgcolor="#DBEAF5" class="bigtitle"><font class="bigtitle">Provide detail for Alternate Joist</td>
      </tr>              
      <tr>
        <td class="a10black" colspan=1 ALIGN="RIGHT">Select the Material for the Alternate Joist</td>
        <td class="a10black" colspan=3 ALIGN="LEFT">
            <select size="1" name="aMater" onChange="this.form.submit()">
                <option value="std" <%=designHelp.getAStd()%>>Wood/Standard Lumber</option>
                <option value="woodIShapeWood" <%=designHelp.getAWoodIShapeWood()%>>Wooden I-Shaped Joists with Wood Flange</option>
                <option value="woodIShapeLVL" <%=designHelp.getAWoodIShapeLVL()%>>Wooden I-Shaped Joists with LVL Flange</option>   
                <option value="lvl" <%=designHelp.getALvl()%>>Laminated Veneer Lumber</option>
                <option value="psl" <%=designHelp.getAPsl()%>>Parallel Strand Lumber</option>  
            </select>
             
          </td>
       </tr>
       <tr>
           <TD class="a10black" ALIGN="RIGHT">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Provide the Maximum Depth</TD>
           <td class="a10black" colspan=3 ALIGN="LEFT"> 
           <input type="text" name="depthIn" size="5" maxLength=3 VALUE=<%=design.getDepthIn()%>>inches
         </TD>
           </TR>
       <tr>
         <td class="a10black" ALIGN="RIGHT">&nbsp;&nbsp;&nbsp;&nbsp;Spacing Between Joists</td>
         <td class="a10black" colspan=3 ALIGN="LEFT"> 
            <input type="text" name="aspaceIn" size="5" maxLength=3 VALUE="<%=design.getASpaceIn()%>">inches
         </TD>
       </TR>
           </TABLE>
    <jsp:include page="include_project.jsp" flush="true"/>
    <TABLE WIDTH="100%">
      <tr>
        <td width="30%" ALIGN="RIGHT"><input type="submit" name="btn1" value="Alternate Joist"></TD>
        <td width="70%" ALIGN="LEFT"><input type="reset" value="Reset" name="reset"></td>
      </tr>
    </TABLE>
</TD></TR>
</table>
</form></body>
</html>
