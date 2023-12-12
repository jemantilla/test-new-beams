<%@ page import="com.util.Project, com.util.Design, com.util.Utility" %>
<%@ page import="com.util.Profile"%>

<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />

<%
Profile profile = profileFormHandler.getProfile();
Project project =(Project) request.getAttribute("project");
Design design =(Design) request.getAttribute("design");
%>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <TR><TD ALIGN="Left"><IMG SRC="graphics/logo.gif" BORDER="0"></TD></TR>
      <TR><TD>&nbsp;</TD></TR>
      <TR><TD class="a12blackbold">Welcome <%out.print(profile.getFirstName() +" "+ profile.getLastName());%></TD></TR>
      <TR><TD>&nbsp;</TD></TR>
      <TR>
        <TD WIDTH="70%">
        <FONT class="a10bluebold"><B>Project Detail:</B></FONT><br>
        <font class="a10black">
            &nbsp;&nbsp;<%=project.getProjectName()%><BR>
            <%if (project.getStreet().length()>0) out.print("&nbsp;&nbsp;"+project.getStreet()+"<BR>");%>
            <%if (project.getCity().length()>0) out.print("&nbsp;&nbsp;"+project.getCity()+",&nbsp;");%>
            <%if (project.getState().length()>0) out.print("&nbsp;&nbsp;"+project.getState()+"-"); out.print(project.getZip());%>
        </font>        
        </TD>
      </TR>
      <TR><TD>&nbsp;</TD></TR>
      <TR>
            <TD class="a10bluebold" align="center"><u>Beam Image</u></TD>
          </TR>
      <TR><TD>&nbsp;</TD></TR>
      <TR>  
        <TD WIDTH="30%" ALIGN="CENTER" VALIGN="TOP" CLASS="a10black">
          <%=("<b>W=</b>"+Utility.getTwoDecimal(design.getLoad())+"  Kips-ft")%><BR>
          <IMG alt="load" src="graphics/aframe2.gif" BORDER="0"><BR>
          <%out.print("<b>RL=</b>"+Utility.getTwoDecimal(design.getReactionLeft())+
              "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>L=</b>"+
              Utility.getTwoDecimal(design.getSpan())+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>RR=</b>"+
              Utility.getTwoDecimal(design.getReactionRight()));
            out.print("<br>  Kips-ft &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;feet&nbsp;&nbsp;"+
                        "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  Kips-ft");
          %>
        </TD>
     </TR>
    </table>
