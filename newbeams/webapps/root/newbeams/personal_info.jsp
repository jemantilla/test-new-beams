<%@page import="com.util.Profile, com.util.HashValues"%>
<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />

<%
Profile profile = profileFormHandler.getProfile();
if (!(profile.getAgree())) { response.sendRedirect("agreement.jsp"); return;}
if (profileFormHandler.getErrorFound()) {
    Object [] _errorVec = profileFormHandler.getFormExceptions();
    request.setAttribute("ErrorVector" ,_errorVec);%>
    <jsp:include page="error_page.jsp" flush="true"/>
<%}
%>

<html>
<head>
<title>Registration</title>
<script language='JavaScript' src='newbeams.js'></script>
<link rel="stylesheet" href="newbeams.css">
</head>
<body bgcolor="#ffffff" topmargin=0>
<center>
    <form method="POST" ACTION="process_profile.jsp">
    <INPUT TYPE="HIDDEN" NAME="SucessURL" VALUE="design.jsp">
    <INPUT TYPE="HIDDEN" NAME="ErrorURL" VALUE="personal_info.jsp">
    <INPUT TYPE="HIDDEN" NAME="btn" VALUE="signup">

    <table border=0 cellpadding=1 cellspacing=2 width="80%">
      <TR>
        <TD colspan="3" ALIGN="center"><IMG SRC="graphics/logo.gif" BORDER="0"><BR></TD>
      </TR>
      <tr> 
        <td colspan=3 bgcolor="#DBEAF5"><a name="profiletop"><font class="title">Profile Information</font></a></td>
      </tr>
      <tr> 
        <td class="a10black"><font color="#FF0000">First Name</font></td>
        <td><input type="text" name="firstName" size="20" maxlength="20" value=<%=profile.getFirstName()%>></td>
      </tr>
      <tr>
        <td class="a10black"><font color="#FF0000">Last Name</font></td>
        <td><input type="text" name="lastName" size="20" value=<%=profile.getLastName()%>></td>
      </tr>
      <tr>
        <td class="a10black"><font color="#FF0000">E-mail</font></td>
        <td class="a10black" COLSPAN="2"><input type="text" name="email" size="30" value=<%=profile.getEmail()%>></td>
      </tr>
      <tr>
        <td class="a10black"><font color="#FF0000"></font>Address1</td>
        <td class="a10black" colspan="2"><input type="text" name="address1" size="20" value=<%=profile.getAddress1()%>></td>
      </tr>
      <tr>
        <td class="a10black"><font color="#FF0000"></font>City </td>
        <td><input type="text" name="city" size="20" value=<%=profile.getCity()%>></td>
      </tr>
      <tr>
        <td class="a10black"><font color="#FF0000"></font>State</td>
        <td colspan="2">
          <select name="state" size="1">
            <%if ((profile.getState().length() >= 1) && (!(profile.getState().equals("NY"))))
                out.print("<OPTION VALUE=" + profile.getState()+">"+ HashValues.stateTable.get(profile.getState()));%>
           <jsp:include page="include_state.jsp" flush="true"/>
          </select>in <b><i>United States</i></b>
        </td>
      </tr>
      <tr>
        <td class="a10black"><font color="#FF0000"></font>Zip</td>
        <td><input type="text" name="zip" size="5" maxlength="5" value=<%=profile.getZip()%>></td>
      </tr>
      <tr>
        <td class="a10black">Home Phone</td>
        <td class="a10black" colspan=2>
          <input type="text" name="cellPhoneNum" size="15" maxlength="15" value=<%=profile.getCellPhoneNum()%>>
          <SPAN class="commentbg">(number)</SPAN> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
      </tr>
      <tr>
        <td class="a10black">Cell Phone</td>
        <td class="a10black" colspan=2>
          <input type="text" name="workPhoneNum" size="15" maxlength="15" value=<%=profile.getWorkPhoneNum()%>>
          <SPAN class="commentbg">(number)</SPAN>
        </td>
      </tr>
      <tr> 
        <td colspan=3 bgcolor="#DBEAF5"> <font class="title">Account Information</font> 
        </td>
      </tr>
      <tr> 
        <td> <font class="a10black" color="#FF0000">UserName</font> </td>
        <td> 
          <input type="text" class="a10black" name="userName" size="12" maxlength="8" value=<%=profile.getUserName()%>>
          </td>
        <td bgcolor="#EFF7FF"> <font class="s"> Begin with a letter, and use only 
          letters (A-Z, a-z), numbers (0-9), the underscore (_), and <b>no spaces</b>. 
          </font> </td>
      </tr>
      <tr> 
        <td> <font class="a10black" color="#FF0000">Password</font> </td>
        <td> 
          <input type="password" class="a10black" name="password" size="10" maxlength="8">
        </td>
        <td rowspan=2 bgcolor="#EFF7FF"> <font class="s"> Must be between <b> four to 
          eight (4-8) characters long</b>,Begin with a letter, and use only letters (A-Z, a-z),
          numbers (0-9),the underscore (_), but <b>no spaces</b>.</font> </td>
      </tr>
      <tr> 
        <td> <font class="a10black" color="#FF0000">Re-enter Password</font> </td>
        <td> 
          <input type="password" class="a10black" name="password2" size="10" maxlength="8">
        </td>
      </tr>
	  <!--<tr> 
        <td align="right"> 
          <input type="checkbox" name="agree" value="yes" <%if (profile.getAgree()) out.print("checked");%>>
        </td>
        <td> I agree <a href="javascript:agreementWindow('agreement.jsp', 'Agreement')">Terms and Conditions</a></td>
      </tr> -->
      <tr>
      		<td colspan="2" align="center"><input type="submit" name="submit" value="Sign Up"></td>
            <td colspan="1" align="left"><A HREF="log_in.jsp">log in</A></td>
      </tr></table>
      
  </form>
</center>
</body></html>
