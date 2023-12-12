<%@page import="com.util.Profile, com.util.Design, com.util.DesignHelp, com.util.HashValues"%>
<%@page import="java.util.Iterator"%>

<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />
<jsp:useBean id="designTimberFormHandler" scope="session" class="com.util.DesignTimberFormHandler" />

<%
String first="checked", upper="unchecked", attic="unchecked", roof="unchecked";
Profile profile = profileFormHandler.getProfile();
if (!profile.getValidUser()) {
    response.sendRedirect("log_in.jsp");
    return;
}
%>
<html>
<head><title>Design Timber Floor Joist</title>
<script language='JavaScript' src='newbeams.js'></script>
<link rel="stylesheet" href="newbeams.css">
</head>

<body topmargin=0>
<form method="POST" ACTION="process_design_timber.jsp"  NAME="DesignForm">
<input type="hidden" name="SucessURL" VALUE="design_timber_beam.jsp">
<input type="hidden" name="ErrorURL" VALUE="design_timber.jsp">
<table width="100%">
  <tr>
    <td width="22%" valign="TOP">
        <jsp:include page="slide.jsp" flush="true"/>
    </TD>
    <TD WIDTH="78%" valign="TOP">
<%
Design design = designTimberFormHandler.getDesign();
DesignHelp designHelp = design.getDesignHelp();
if (designTimberFormHandler.getErrorFound()) {
    Object [] _errorVec = designTimberFormHandler.getFormExceptions();
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
%>

<table border="0" cellpadding=2 cellspacing=2 width="100%" valign="Top" ALIGN="CENTER">
      <tr> 
        <td colspan=4 bgcolor="#DBEAF5"> <a name="profiletop"><font class="bigtitle">Design a Timber Floor Joist</font></a> </td>
      </tr>
      <tr>
        <td class="a10black" colspan=1 ALIGN="RIGHT">Select the Joist Material</td>
        <td class="a10black" colspan=3 ALIGN="LEFT">
            <select size="1" name="mater">
                <option value="std" <%=designHelp.getStd()%>>Wood/Standard Lumber</option>
                <option value="woodIShape" <%=designHelp.getWoodIShape()%>>Wooden I-Shaped Joists</option>
                <option value="lvl" <%=designHelp.getLvl()%>>Laminated Veneer Lumber</option>
                <option value="psl" <%=designHelp.getPsl()%>>Parallel Strand Lumber</option>  
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
        <td width="30%" ALIGN="RIGHT"><input type="submit" name="btn1" value="Design Joist"></TD>
        <td width="70%" ALIGN="LEFT"><input type="reset" value="Reset" name="reset"></td>
      </tr>
    </TABLE>
</TD></TR>
</table>
</form></body>
</html>