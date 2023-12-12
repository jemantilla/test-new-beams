<%@ page import="com.util.Profile"%>

<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />

<%
Profile profile = profileFormHandler.getProfile();
%>
<IMG SRC="graphics/logo.gif" BORDER="0">
<TABLE WIDTH="100%" CELLPADDING="5" bgcolor="#DBEAF5">
    <TR><TD class="a12blackbold">Welcome <%out.print(profile.getFirstName() +" "+ profile.getLastName());%></TD></TR>
    <TR><TD><a href="project_detail.jsp">-Beam List by Projects</a></TD></TR>
    <TR><TD><a href="create_project.jsp?isNew=true">-Create New Project</a></TD></TR>
    <TR><TD><a href="design.jsp?val=new">-Design a New Beam</a></TD></TR>
    <TR><TD><A HREF="check_exist.jsp?val=new">-Check an Existing Beam</A></TD></TR>
    <TR><TD><A HREF="alternate.jsp?val=new">-Provide an Alternate Beam</A></TD></TR>
    <TR><TD><A HREF="design_timber.jsp?val=new">-Design Timber Floor Joists</A></TD></TR>
    <TR><TD><A HREF="check_exist_timber.jsp?val=new">-Check an Existing Floor Joists</A></TD></TR>
    <TR><TD>-Provide an alternate Joist System</TD></TR>
    <TR><TD><A HREF="design_aframe.jsp?val=new">-Design a Timber A-Frame</TD></TR>
    <TR><TD>-Check an Existing A-Frame</TD></TR>
    <TR><TD>-Provide an alternate Collar Tie System</TD></TR>
    <TR><TD><a href="update_info.jsp">-Update profile</a></TD></TR>
    <TR><TD><a href="change_password.jsp">-Change Password</a></TD></TR>
    <TR><TD><A HREF="change_user.jsp">  -Change User</A></TD></TR>
    <TR><TD><A HREF="about_us.jsp"> -About Us</A></TD></TR>
    <TR><TD><A HREF="javaScript:openWindowWithScroll('agreement_read.jsp','Agreement',800,600,'yes',5,5);"> -License Agreement</A></TD></TR>
</TABLE>

