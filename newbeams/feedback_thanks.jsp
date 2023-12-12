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
<title>Newbeams.com Thanks for feedback</title>
<script language='JavaScript' src='newbeams.js'></script>
<link rel="stylesheet" href="newbeams.css">
</head>
<body bgcolor="#ffffff" topmargin=0>
<table width="100%">
  <tr>
    <td width="22%" valign="TOP">
        <jsp:include page="slide.jsp" flush="true"/>
    </td>
    <td WIDTH="78%" valign="TOP" ALIGN="LEFT">
      <IMG SRC="graphics/thank_you_title.gif"><BR>
      At Newbeams, we value what our customers have to say. Your suggestions allow us to serve you better. 
      A customer service representative will read your form and Newbeams will strive to have your ideas and suggestions 
      implemented in the near future.<BR><BR>
      Thanks again for your time.
    </td>
  </tr>
</table>
</body></html>

