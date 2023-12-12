<%@ page import="java.util.ArrayList, com.util.TempClass, com.util.Utility, com.util.Design" %>
<%@ page import="com.util.Utility" %>

<jsp:useBean id="timberAFrameFormHandler" scope="session" class="com.util.TimberAFrameFormHandler" />
<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />

<HTML>
<HEAD><TITLE> Result </TITLE>
<link rel="stylesheet" href="newbeams.css">
</HEAD>
<BODY style="background-color: #DBEAF5">
<form method="post" action="process_design_timberaframe.jsp">
<input type="hidden" name="SucessURL" VALUE="result_aframe.jsp">
<input type="hidden" name="ErrorURL" VALUE="design_timberaframe.jsp">
<CENTER>
<%
ArrayList tempList = null;
ArrayList resultArray = null;
Design design = null;
String sucessURL;

sucessURL = request.getParameter("SucessURL");
design = timberAFrameFormHandler.getDesign();
design.setStatus("New");
if (!profileFormHandler.getProfile().getValidUser()) {
    response.sendRedirect("log_in.jsp");
    return;
}/*else if (!timberAFrameFormHandler.getValidResult()) {
    response.sendRedirect("design_aframe.jsp");
    return;
}*/else {
    if (timberAFrameFormHandler.getErrorFound()) {
        Object [] _errorVec = timberAFrameFormHandler.getFormExceptions();
        request.setAttribute("ErrorVector" ,_errorVec);%>
        <jsp:include page="error_page.jsp" flush="true"/>
    <%}
}
resultArray = design.getResultArray();
tempList = design.getTempArray();
//deflection = design.getDeflection();
//deflectionsp = design.getDeflectionsp();
//fbx = design.getFbx();
//totalWeight = design.getTotalWeight();%>
<IMG SRC="graphics/logo.gif" BORDER="0"><br><br>
<B>Required Sx = </B><%=Utility.getTwoDecimal(design.getSx())%></br>
<B>Tension in Collar Tie (kips) = </b><%=Utility.getTwoDecimal(design.getT())%></br>
<B>Min. C.S. Area of Collar Tie (in<SUP>2</SUP>) = </B><%=Utility.getTwoDecimal(design.getArea())%>
</CENTER><BR>
<font color="#FF0000">Choose One Radio Button</font><BR><BR>
<table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#999999">
    <tr bgcolor="#CCCCCC">
        <td class="a10blackbold" align="center">Rafter Size</td>
        <td class="a10blackbold" align="center">S (in<SUP>3</SUP>)</td>
        <td class="a10blackbold" align="center">Bending<br>Stress (ksi)</td>
        <!--<td class="a10blackbold" align="center">Moment of<br>Inertia</td>
        <td class="a10blackbold" align="center">Weight (lbf)</td>
        <td class="a10blackbold" align="center">Deflection (in)</td>
        <td class="a10blackbold" align="center">Deflection (span)</td>
        <td class="a10blackbold" align="center">Total<br>Weight (lbs)</td> -->
    </tr>
    <tr>
									  
<%if (tempList != null) {
    for (int i=0; i<tempList.size(); i++){
        TempClass tempClass = (TempClass)tempList.get(i);%>
        <tr bgcolor="#FFFFFF">
          <td class="a10black"><input type=radio value=<%=i%> name=R2>
            <%=tempClass.getVal()%></td>
            <td class="a10black" align="center"><%=Utility.getTwoDecimal(tempClass.getActsx())%></td>
            <td class="a10black" align="center"><%=Utility.getTwoDecimal(tempClass.getFbx())%></td>
    <%}%></TR>
<%}%>
</table><BR>
<B>Note:</B> All beams are considered compact sections with lateral support of the compression  flange assumed to be 
continuous.<BR><BR>
<TABLE WIDTH="100%"><TR><td ALIGN="CENTER"><input type=submit value="View Result" name=resultBtn></TD></TR></TABLE>
</form>
<CENTER>
<A HREF="design_aframe.jsp">Home</A>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<A HREF="about_us.jsp">Contact Us</A>
</CENTER>
</BODY></HTML>
