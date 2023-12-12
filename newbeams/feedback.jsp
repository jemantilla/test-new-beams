<%@page import="com.util.Profile, com.util.HashValues"%>
<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />

<%
Profile profile = profileFormHandler.getProfile();

if (profileFormHandler.getErrorFound()) {
    Object [] _errorVec = profileFormHandler.getFormExceptions();
    request.setAttribute("ErrorVector" ,_errorVec);%>
    <jsp:include page="error_page.jsp" flush="true"/>
<%}
%>

<html>
<head>
<title>Newbeams.com Give us your feedback</title>
<script language='JavaScript' src='newbeams.js'></script>
<link rel="stylesheet" href="newbeams.css">
</head>
<body bgcolor="#ffffff" topmargin=0>
<center>
<form method="POST" ACTION="process_feedback.jsp">
<INPUT TYPE="HIDDEN" NAME="SucessURL" VALUE="thanks.jsp">
<INPUT TYPE="HIDDEN" NAME="ErrorURL" VALUE="feedback.jsp">
  <table width="100%">
   <tr>
    <td width="22%" valign="TOP">
        <jsp:include page="slide.jsp" flush="true"/>
    </td>
    <td WIDTH="78%" valign="TOP">
    <table border=0 cellpadding=1 cellspacing=2 width="80%" ALIGN="CENTER">
      <tr> 
        <td colspan=3 bgcolor="#DBEAF5"><a name="profiletop"><font class="title">Give us your feedback</font></a></td>
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
      <tr>
        <td colspan="2" align="left" class="a10black">Questions/Comments:</td>
      </tr>
      <tr>
        <td colspan="2" align="left">
          <textarea class="a10black" rows="5" cols="80" name="msgComments"></textarea>
        </td>
      </tr>
      <tr>
        <td colspan="2" align="center"><input type="button" name="submit" value="Feedback"></td>
        <td colspan="1" align="left">&nbsp;</td>
      </tr>
    </table>
    </td>
    </tr>
  </table>
</form>
</center>
</body></html>
