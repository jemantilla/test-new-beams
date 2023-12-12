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
<title>Update Profile</title>
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
<form method="POST" ACTION="process_profile.jsp">
    <INPUT TYPE="HIDDEN" NAME="SucessURL" VALUE="sucess_update_profile.jsp">
    <INPUT TYPE="HIDDEN" NAME="ErrorURL" VALUE="update_info.jsp">
    <INPUT TYPE="HIDDEN" NAME="btn" VALUE="update">
        
    <table border=0 cellpadding=1 cellspacing=2 width="80%">
      <tr> 
        <td colspan=2 bgcolor="#DBEAF5"> <a name="profiletop"><font class="title">Edit Information for <%=profile.getUserName()%></font></a> </td>
      </tr>
      <tr>
        <td class="a10black"><font color="#FF0000">First Name</font></td>
        <td><input type="text" name="firstName" size="20" maxlength="20" value="<%=profile.getFirstName()%>"></td>
      </tr>
      <tr>
        <td class="a10black"><font color="#FF0000">Last Name</font></td>
        <td><input type="text" name="lastName" size="20" value="<%=profile.getLastName()%>"></td>
      </tr>
      <tr>
        <td class="a10black"><font color="#FF0000">E-mail</font></td>
        <td class="a10black"><input type="text" name="email" size="30" value=<%=profile.getEmail()%>></td>
      </tr>
      <tr>
        <td class="a10black"><font color="#FF0000"></font>Address1</td>
        <td class="a10black"><input type="text" name="address1" size="20" value="<%=profile.getAddress1()%>"></td>
      </tr>
      <tr>
        <td class="a10black"><font color="#FF0000"></font>City</td>
        <td><input type="text" name="city" size="20" value="<%=profile.getCity()%>"></td>
      </tr>
      <tr>
        <td class="a10black"><font color="#FF0000"></font>State</td>
        <td>
          <select name="state" size="1">
            <%if (profile.getState().length() >= 1)
                out.print("<OPTION VALUE=" + profile.getState()+">"+ HashValues.stateTable.get(profile.getState()));%>
            <jsp:include page="include_state.jsp" flush="true"/>
          </select><font class="s">in <b><i>United States</i></b></font>
        </td>
      </tr>
      <tr> 
        <td class="a10black"><font color="#FF0000"></font>Zip</td>
        <td><input type="text" name="zip" size="5" maxlength="5" value=<%=profile.getZip()%>></td>
      </tr>
      <tr>
        <td class="a10black">Home Phone</td>
        <td class="a10black">
          <input type="text" name="cellPhoneNum" size="15" maxlength="15" value="<%=profile.getCellPhoneNum()%>">
          <SPAN class="commentbg">(area-number)</SPAN> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
      </tr>
      <tr> 
        <td class="a10black">Cell Phone</td>
        <td class="a10black">
          <input type="text" name="workPhoneNum" size="15" maxlength="15" value="<%=profile.getWorkPhoneNum()%>">
          <SPAN class="commentbg">(area-number)</SPAN>
        </td>
      </tr>
      <tr>
        <TD>&nbsp;</TD>
        <td align="Left"><input type="submit" name="submit" value="Update"></td>
      </tr>
    </table>
    </td>
  </tr>
</table>      
</form>
</body></html>
