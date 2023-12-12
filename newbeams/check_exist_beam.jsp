<%@ page import="java.util.ArrayList, com.util.TempClass, com.util.Utility, com.util.Design, com.util.Profile" %>

<jsp:useBean id="existBeamFormHandler" scope="session" class="com.util.ExistBeamFormHandler" />
<jsp:useBean id="projectFormHandler" scope="session" class="com.util.ProjectFormHandler" />
<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />

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
</HEAD>
<table width="100%">
  <tr>
    <td width="22%">
        <jsp:include page="slide.jsp" flush="true"/>
    </TD>
    <TD WIDTH="78%" VALIGN="TOP">
<%
Design design  = existBeamFormHandler.getDesign();
if (design.getMaterial().equalsIgnoreCase("steel")) {
    ArrayList sxArray = design.getSxArray();
    if (sxArray.size() > 0){%>

<FORM METHOD="POST" action="process_check_exist_beam.jsp">
<INPUT TYPE="HIDDEN" NAME="SucessURL" VALUE="result_check_exist.jsp">
<CENTER><FONT class="a12blackbold">Your selected size is no good.<BR>
            Below are the alternate Beam Sizes</CENTER><BR>
<table width="100%" border="1" cellspacing="0" cellpadding="0" bgcolor="#DBEAF5">
    <TR>
        <td class="a10blackbold" align="center">Size</TD>
        <td class="a10blackbold" align="center">Sx</TD>
        <td class="a10blackbold" align="center">I</TD>
    </TR>
    <%
    for (int i=0; i<sxArray.size(); i++){
        String [] _result = (String[]) sxArray.get(i);%>
    <TR>
        <td class="a10black" align="center"><INPUT TYPE="RADIO" name="index" checked VALUE="<%=i%>"><%=_result[0]%></TD>
        <td class="a10black" align="center"><%=Utility.getTwoDecimal(_result[1])%></TD>
        <td class="a10black" align="center"><%=Utility.getTwoDecimal(_result[2])%></TD>
    </TR>
    <%}%>
</TABLE><BR>
<center><INPUT TYPE="SUBMIT" NAME="btn1" VALUE="View Result"></CENTER>
</FORM>
    <%}else{%>
        <CENTER><FONT class="a12blackbold">Your Existing Beam is no good.</FONT></CENTER>
    <%}%>
<%}else if ((design.getMaterial().equalsIgnoreCase("flistd")) || (design.getMaterial().equalsIgnoreCase("flilvl"))) {%>
    <CENTER><FONT class="a12blackbold">Your Existing Beam is no good.</FONT></CENTER>
<%}else if ((design.getMaterial().equalsIgnoreCase("std"))  || (design.getMaterial().equalsIgnoreCase("LVL"))){%>
<FORM METHOD="POST" action="process_check_exist_beam.jsp">
<INPUT TYPE="HIDDEN" NAME="SucessURL" VALUE="check_exist.jsp">
<CENTER><FONT class="a12blackbold">Your existing size is no good</FONT></CENTER><BR>
<table width="100%" border="0" cellspacing="0" cellpadding="2" bgcolor="#DBEAF5">
    <tr>
        <TD WIDTH="30%">&nbsp;</TD>
        <td class="a10blackbold" align="left">
            <INPUT TYPE="RADIO" name="index" checked VALUE="Next Size">Choose to get Next available Size
        </TD>
    </TR>
    <tr>
        <TD WIDTH="30%">&nbsp;</TD>
        <td class="a10blackbold" align="left">
            <INPUT TYPE="RADIO" name="index" VALUE="Insert Plate">Choose to insert steel plate
        </TD>
    </TR>
</TABLE><BR>
<center><INPUT TYPE="SUBMIT" NAME="btn1" VALUE="Next"></CENTER>
</FORM>
<%}%>
<TABLE ALIGN="CENTER">
    <TR><TD>&nbsp;</TD></TR>
    <TR VALIGN="TOP" class="a12blackbold"><TD><A HREF="check_exist.jsp">Go Back</A></TD></TR>
</TABLE>
        </TD>
    </TD>
</TABLE>

</HTML>
