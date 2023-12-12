<%@ page import="java.util.ArrayList, com.util.TempClass, com.util.Utility, com.util.Design" %>
<%@ page import="com.util.Utility" %>

<jsp:useBean id="newBeamFormHandler" scope="session" class="com.util.NewBeamFormHandler" />
<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />

<HTML>
<HEAD><TITLE> Result </TITLE>
<link rel="stylesheet" href="newbeams.css">
</HEAD>
<BODY style="background-color: #DBEAF5">
<form method="post" action="process_design_beam.jsp">
<input type="hidden" name="SucessURL" VALUE="result_beam.jsp">
<input type="hidden" name="ErrorURL" VALUE="design_beam.jsp">
<CENTER>
<%
String sucessURL = request.getParameter("SucessURL");
Design design = newBeamFormHandler.getDesign();
design.setStatus("New");
if (!profileFormHandler.getProfile().getValidUser()) {
    response.sendRedirect("log_in.jsp");
    return;
}else if (!newBeamFormHandler.getValidResult()) {
    response.sendRedirect("design.jsp");
    return;
}else {
    if (newBeamFormHandler.getErrorFound()) {
        Object [] _errorVec = newBeamFormHandler.getFormExceptions();
        request.setAttribute("ErrorVector" ,_errorVec);%>
        <jsp:include page="error_page.jsp" flush="true"/>
    <%}
}
ArrayList tempList = newBeamFormHandler.getTempList();
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
        <td class="a10blackbold" align="center">Size</td>
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
          <td class="a10black"><input type=radio value=<%=i%> name=R2>
          <%if (design.getMaterial().equalsIgnoreCase("flistd")){%>
            <%=tempClass.getLookUp()%></td>
          <%}else {%>
            <%=tempClass.getVal()%></td>
          <%}%>
            <td class="a10black" align="center"><%=Utility.getTwoDecimal(tempClass.getActsx())%></td>
            <td class="a10black" align="center"><%=Utility.getTwoDecimal(tempClass.getMoi())%></td>
            <td class="a10black" align="center"><%=Utility.getTwoDecimal(tempClass.getWeight())%></td>
            <td class="a10black" align="center"><%=Utility.getTwoDecimal(tempClass.getDeflection())%></TD>
            <td class="a10black" align="center"><%out.print("L /" + tempClass.getDeflectionsp());%></TD>
            <td class="a10black" align="center"><%=Utility.getTwoDecimal(tempClass.getFbx())%></TD>
            <td class="a10black" align="center"><%=Utility.getTwoDecimal(tempClass.getTotalWeight())%></TD>
    <%}%></TR>
<%}%>
</table><BR>
<B>Note:</B> All beams are considered compact sections with lateral support of the compression  flange assumed to be 
continuous.<BR><BR>
<TABLE WIDTH="100%"><TR><td ALIGN="CENTER"><input type=submit value="View Result" name=resultBtn></TD></TR></TABLE>
</form>
<CENTER>
<A HREF="design.jsp">Home</A>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<A HREF="about_us.jsp">Contact Us</A>
</CENTER>
</BODY></HTML>
