<%@ page import="java.util.ArrayList, com.util.TempClass, com.util.Utility, com.util.Design" %>
<%@ page import="com.util.Utility" %>

<jsp:useBean id="designTimberFormHandler" scope="session" class="com.util.DesignTimberFormHandler" />
<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />

<HTML>
<HEAD><TITLE> Result </TITLE>
<link rel="stylesheet" href="newbeams.css">
</HEAD>
<BODY style="background-color: #DBEAF5">
<form method="post" action="process_design_timber_beam.jsp">
<input type="hidden" name="SucessURL" VALUE="result_timber_beam.jsp">
<input type="hidden" name="ErrorURL" VALUE="design_timber_beam.jsp">
<CENTER>
<%
String sucessURL = request.getParameter("SucessURL");
Design design = designTimberFormHandler.getDesign();
design.setStatus("New");
if (!profileFormHandler.getProfile().getValidUser()) {
    response.sendRedirect("log_in.jsp");
    return;
}else if (!designTimberFormHandler.getValidResult()) {
    response.sendRedirect("design.jsp");
    return;
}else {
    if (designTimberFormHandler.getErrorFound()) {
        Object [] _errorVec = designTimberFormHandler.getFormExceptions();
        request.setAttribute("ErrorVector" ,_errorVec);%>
        <jsp:include page="error_page.jsp" flush="true"/>
    <%}
}
ArrayList tempList = designTimberFormHandler.getTempList();
double [] deflection = design.getDeflection();
int [] deflectionsp = design.getDeflectionsp();
double [] fbx = design.getFbx();
double [] totalWeight = design.getTotalWeight();%>
<IMG SRC="graphics/logo.gif" BORDER="0"><br><br>
<B>Moment = </B><%=Utility.getTwoDecimal(design.getM())%><br>
<B>Required Sx = </B><%=Utility.getTwoDecimal(design.getSx())%>
</CENTER><BR>
<font color="#FF0000">Choose One Radio Button</font><BR><BR>
<table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#999999">
    <tr bgcolor="#CCCCCC">
        <td class="a10blackbold" align="center">Size
        <%if (design.getMaterial().equalsIgnoreCase("woodIShape")){%>
			<table WIDTH="100%" BORDER="1" cellspacing="0">
              <tr bgcolor="#CCCCCC">
                <TD>&nbsp;</TD>
                <td class="a10blackbold" align="center" WIDTH="20%">d</td>
                <td class="a10blackbold" align="center" WIDTH="20%">bf</td>
                <td class="a10blackbold" align="center" WIDTH="20%">tf</td>
                <td class="a10blackbold" align="center" WIDTH="20%">tw</td>
              </tr>
            </TABLE>
         <%}%>     
         </td>
        <td class="a10blackbold" align="center">S (in<SUP>3</SUP>)</td>
        <td class="a10blackbold" align="center">Moment of<br>Inertia</td>
        <td class="a10blackbold" align="center">Weight (lbf)</td>
        <td class="a10blackbold" align="center">Deflection (in)</td>
        <td class="a10blackbold" align="center">Deflection (span)</td>
        <td class="a10blackbold" align="center">Bending<br>Stress (ksi)</td>
        <td class="a10blackbold" align="center">Total<br>Weight (lbs)</td>
    </tr>
									  
<%if (tempList != null) {
    for (int i=0; i<tempList.size(); i++){
        TempClass tempClass = (TempClass)tempList.get(i);%>
        <tr bgcolor="#FFFFFF">
          <%if (design.getMaterial().equalsIgnoreCase("woodIShape")){%>
			<TD><table WIDTH="100%" BORDER="1" cellspacing="0">
              <TR>
                <td class="a10black" WIDTH="20%"><input type=radio value=<%=i%> name=R2></TD>
                <TD class="a10black" WIDTH="20%"><%=Utility.getTwoDecimal(tempClass.getDepth())%></TD>
                <TD class="a10black" WIDTH="20%"><%=Utility.getTwoDecimal(tempClass.getB())%></TD>
                <TD class="a10black" WIDTH="20%"><%=Utility.getTwoDecimal(tempClass.getA())%></TD>
                <TD class="a10black" WIDTH="20%"><%=Utility.getTwoDecimal(tempClass.getTWeb())%></TD>
                
              </TR>
			</table></TD>
          <%}else {%>
            <td class="a10black"><input type=radio value=<%=i%> name=R2><%=tempClass.getVal()%></td>
          <%}%>
            <td class="a10black" align="center"><%=Utility.getTwoDecimal(tempClass.getActsx())%></td>
            <td class="a10black" align="center"><%=Utility.getTwoDecimal(tempClass.getMoi())%></td>
            <td class="a10black" align="center"><%=Utility.getTwoDecimal(tempClass.getWeight())%></td>
            <td class="a10black" align="center"><%=deflection[i]%></TD>
            <td class="a10black" align="center"><%out.print("L /" + deflectionsp[i]);%></TD>
            <td class="a10black" align="center"><%=Utility.getTwoDecimal(fbx[i])%></TD>
            <td class="a10black" align="center"><%=tempClass.getTotalWeight()%></TD>
    <%}%></TR>
<%}%>
</table><BR>
<CENTER>
<TABLE WIDTH="100%">
  <TR>
    <%if (design.getMaterial().equalsIgnoreCase("woodIShape")){%>
      <TD WIDTH="50%">
        <IMG SRC="graphics/woodIShape.gif">
      </TD>
    <%}%>
    <TD WIDTH="50%" VALIGN="TOP">
      <TABLE WIDTH="100%" BORDER="0">
        <TR>
          <TD colspan="2"><B>Note:</B> All beams are considered compact sections with lateral support of the compression flange assumed to be 
            continuous.
          </TD>
        </TR>
        <TR><TD>&nbsp;</TD></tr>
        <TR>
          <td colspan="2" ALIGN="CENTER"><input type=submit value="View Result" name=resultBtn></TD>
        </TR>
        <TR><TD COLSPAN="2">&nbsp;</TD></TR>
        <tr>
          <TD ALIGN="RIGHT" WIDTH="50%">
            <A HREF="design.jsp">Home</A>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<!--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-->
          </TD>
          <TD ALIGN="LEFT" WIDTH="50%">
            <A HREF="about_us.jsp">Contact Us</A>
          </TD>
        </TR>
      </TABLE>
    </TD>
  </TR>
</table>
</CENTER>
</form>
</BODY></HTML>
