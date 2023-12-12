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
<title>Change Password</title>
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
<%
if (profileFormHandler.getErrorFound()) {
        Object [] _errorVec = profileFormHandler.getFormExceptions();
        request.setAttribute("ErrorVector" ,_errorVec);%>
        <jsp:include page="error_page.jsp" flush="true"/>
    <%}
%>
<form method="POST" ACTION="process_change_password.jsp">
    <INPUT TYPE="HIDDEN" NAME="SucessURL" VALUE="sucess_change_password.jsp">
    <INPUT TYPE="HIDDEN" NAME="ErrorURL" VALUE="change_password.jsp">
    <INPUT TYPE="HIDDEN" NAME="btn" VALUE="update">
        
    <table border=0 cellpadding=1 cellspacing=2 width="90%">
      <tr> 
        <td>&nbsp;</Td>
      </tr>
      <tr>
        <td colspan=3 bgcolor="#DBEAF5"> <a name="profiletop"><font class="title">Change Password for <%=profile.getUserName()%></font></a></td>
      </tr>
      <tr> 
        <td>&nbsp;</Td>
      </tr>
      <tr>
        <td class="a10black"><font color="#FF0000">Old Password</font></td>
        <td> <input type="text" name="oldPassword" size="10" maxlength="8"></td>
      </tr>
      <tr> 
        <td class="a10black"><font color="#FF0000">New Password</font></td>
        <td><input type="text" name="newPassword" size="10" maxlength="8"></td>
        <td rowspan=2 bgcolor="#EFF7FF"> <font class="s"> Must between <b> four to 
          eight (4-8) characters long</b>,Begin with a letter, and use only letters (A-Z, a-z),
          numbers (0-9),the underscore (_), but <b>no spaces</b>.</font> </td>
      </tr>
      <tr> 
        <td class="a10black"><font color="#FF0000">Re-enter New Password</font></td>
        <td class="a10black"><input type="text" name="newPassword2" size="10" maxlength="8"></td>
      </tr>
      <tr>
        <td colspan="2" align="center"><input type="submit" name="submit" value="Change Password"></td>
      </tr>
    </table>
</td>
      </tr></table>      
  </form>
</center>
</body></html>
