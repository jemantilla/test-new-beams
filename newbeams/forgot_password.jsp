<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />

<%
String sucessURL = request.getParameter("SucessURL");
if (sucessURL != null) {
    profileFormHandler.setMethodName("forgotpassword");
    if (profileFormHandler.processMethod(request, response)){
        response.sendRedirect(sucessURL);
    }else if (profileFormHandler.getErrorFound()) {
        Object [] _errorVec = profileFormHandler.getFormExceptions();
        request.setAttribute("ErrorVector" ,_errorVec);%>
        <jsp:include page="error_page.jsp" flush="true"/>
    <%}
}
%><html>
<head>
<title>Registration</title>
<link rel="stylesheet" href="newbeams.css">
</head>
<FORM METHOD="POST">
<INPUT TYPE="HIDDEN" NAME="SucessURL" VALUE="design.jsp">
<table width="50%" ALIGN="CENTER"  border="0" cellspacing="0" cellpadding="1">
				<tr bgcolor="#CCCCCC"> 
				  <td>
<table width="100%" ALIGN="CENTER" border="0" cellspacing="0" cellpadding="2">
    <th align="left" class="a10red">Password Help</th>
    <tr bgcolor="#FFFFFF">
        <td colspan="2" class="a10black" align="left"> Please submit your e-mail address you provided during registration.<br>
            Your Password will be e-mailed to you.</td>
    </tr>
    <tr bgcolor="#FFFFFF"> 
        <td width="50%" align="right" class="a12skybluebold">email address: </td>
        <td width="50%"> &nbsp;<input type="text" name="email" size="30"></td>
    </tr>
    <tr bgcolor="#FFFFFF" align="right" valign="middle"> 
        <TD><A HREF="log_in.jsp"><FONT class="a10black">log in</FONT></A>
        <td colspan="1"><input type="submit" name="Submit" value="Submit"></td>
    </tr>
</table>

</td></tr></table>
</FORM></HTML>
