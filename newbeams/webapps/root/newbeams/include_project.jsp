<%@page import="com.util.Project, com.util.HashValues"%>
<%@page import="java.util.Iterator"%>

<jsp:useBean id="projectFormHandler" scope="session" class="com.util.ProjectFormHandler" />

<%
projectFormHandler.setProjects();
Project project = projectFormHandler.getProject();
String projectName = project.getProjectName();
%>
<table border="0" cellpadding=2 cellspacing=2 width="100%" ALIGN="CENTER">

         <input type="hidden" name="projectName" VALUE="<%=projectName%>">
         <input type="hidden" name="street" VALUE="<%=project.getStreet()%>">
         <input type="hidden" name="city" VALUE="<%=project.getCity()%>">
         <INPUT TYPE="hidden" NAME="state" VALUE="<%=project.getState()%>">
         <input type="hidden" name="zip" VALUE="<%=project.getZip()%>">
         
      <tr> 
        <td colspan="3" bgcolor="#DBEAF5" class="bigtitle"><font class="bigtitle">Project Detail</td>
      </tr>
      <tr>
        <td colspan="1" class="a10black" ALIGN="RIGHT"><B>Project Name:</B></td>
        <td colspan="2" class="a10black" ALIGN="LEFT">
            <%if ((projectName == null) || (projectName.length() <1)){
                out.print("<FONT COLOR=\"RED\">Please Create Project for this Beam</FONT>");
            }else{
                out.print(projectName);
            }%>&nbsp;
            <%if (projectFormHandler.hasProjects()){
                Iterator projects = projectFormHandler.getProjects();%>
                &nbsp;&nbsp;<SELECT NAME="PreviousProjects" onChange="projectSelected();">
                    <OPTION VALUE="">(select Project)</OPTION>
                    <%while (projects.hasNext()){
                        String _name = (String)projects.next();%>
                        <OPTION VALUE="<%=_name%>"
                          <%if (_name.equals(projectName)) out.print(" selected");
                          out.print(">"+_name+"</OPTION>");%>
                    <%}%>    
                </SELECT>&nbsp;&nbsp;<a href="update_project.jsp">Update Project</a>
            <%}%>
        </TD>
      </TR>
      <TR>
        <TD colspan="1" class="a10black" ALIGN="RIGHT"><B>Address:</B></TD>
        <TD colspan="2" class="a10black" ALIGN="LEFT">
            <%=project.getStreet()%>&nbsp;
        </TD>
      </TR>
      <TR>
        <TD colspan="1" class="a10black" ALIGN="RIGHT"><B>Town/City:</B></TD>
        <TD colspan="2" class="a10black" ALIGN="LEFT">
            <%=project.getCity()%>&nbsp;
        </TD>
      </TR>
      <TR> 
        <td colspan="1" class="a10black" ALIGN="RIGHT"><B>State:</B></td>
        <td colspan="2" class="a10black">
         <%=project.getState()%>&nbsp;
        </td>
      </tr>
      <TR>
        <TD colspan="1" class="a10black" ALIGN="RIGHT"><B>ZipCode:</B></TD>
        <TD colspan="2" class="a10black" ALIGN="LEFT">
            <%=project.getZip()%>&nbsp;
        </TD>
      </TR>
</TABLE>      
