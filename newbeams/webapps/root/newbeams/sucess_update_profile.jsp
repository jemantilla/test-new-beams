<%@page import="com.util.Profile, com.util.HashValues"%>
<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />

<%
Profile profile = profileFormHandler.getProfile();
if (!profile.getValidUser()) {
    response.sendRedirect("log_in.jsp");
    return;
}%>
<html>
<head>
<title>Password Change Sucessfull</title>
<script language='JavaScript' src='newbeams.js'></script>
<link rel="stylesheet" href="newbeams.css">
</head>
<body bgcolor="#ffffff" topmargin=0>
<table width="100%">
  <tr>
    <td width="22%">
        <jsp:include page="slide.jsp" flush="true"/>
    </TD>
    <TD WIDTH="78%" ALIGN="CENTER" VALIGN="TOP">

    <table border=0 width="90%">
      <tr> 
        <td>&nbsp;</Td>
      </tr>
      <tr>
        <td><a name="profiletop"><font class="title">Profile Changed successfully</font></a></td>
      </tr>
    </table>
</td>
      </tr></table>
</body></html>
