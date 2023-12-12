<%@ page import="java.util.ArrayList, com.util.TempClass, com.util.Utility, com.util.Design" %>
<%@ page import="com.util.Utility" %>

<jsp:useBean id="alternateJoistFormHandler" scope="session" class="com.util.AlternateJoistFormHandler" />
<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />

<HTML>
<HEAD><TITLE> Result </TITLE></HEAD>
<BODY style="background-color: #DBEAF5">
<form method="post" action="process_alternate_timberjoist.jsp">
<input type="hidden" name="SucessURL" VALUE="result_alternate_joist.jsp">
<input type="hidden" name="ErrorURL" VALUE="alternate_timberjoist.jsp">
<CENTER>
<%
String sucessURL = request.getParameter("SucessURL");
Design design = alternateJoistFormHandler.getDesign();
design.setStatus("New");
if (!profileFormHandler.getProfile().getValidUser()) {
    response.sendRedirect("log_in.jsp");
    return;
}else if (!alternateJoistFormHandler.getValidResult()) {
    response.sendRedirect("design.jsp");
    return;
}else {
    if (alternateJoistFormHandler.getErrorFound()) {
        Object [] _errorVec = alternateJoistFormHandler.getFormExceptions();
        request.setAttribute("ErrorVector" ,_errorVec);%>
        <jsp:include page="error_page.jsp" flush="true"/>
    <%}
}
ArrayList resultArray = design.getResultArray();
double [] deflection = design.getDeflection();
int [] deflectionsp = design.getDeflectionsp();
double [] fbx = design.getFbx();
double [] totalWeight = design.getTotalWeight();%>
<IMG SRC="graphics/logo.gif" BORDER="0"><br><br>
<B>Moment = </B><%=Utility.getTwoDecimal(design.getM())%><br>
<B>Required Sx = </B><%=Utility.getTwoDecimal(design.getSx())%>
</CENTER><BR>
<table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#999999">
    <tr bgcolor="#CCCCCC">
        <td class="a10blackbold" align="center">Size</td>
        <td class="a10blackbold" align="center">S (in<SUP>3</SUP>)</td>
        <td class="a10blackbold" align="center">Material</td>
        <td class="a10blackbold" align="center">Spacing (in)</td>
        <td class="a10blackbold" align="center">Maximum<br>Depth (in)</td>
        <td class="a10blackbold" align="center">Moment of<br>Inertia</td>
        <td class="a10blackbold" align="center">Weight (lbf)</td>
        <td class="a10blackbold" align="center">Deflection (in)</td>
        <td class="a10blackbold" align="center">Deflection (span)</td>
        <td class="a10blackbold" align="center">Bending<br>Stress (ksi)</td>
        <td class="a10blackbold" align="center">Total<br>Weight (lbs)</td>
        <td class="a10blackbold" align="center">Manufacturer</td>
    </tr>
									  
<%if (resultArray != null) {
    for (int i=0; i<resultArray.size(); i++){
        String _array[] = (String[])resultArray.get(i);%>
            <td class="a10black"><%=_array[0]%></td>
            <td class="a10black"><%=Utility.getTwoDecimal(_array[1])%></td>
            <td class="a10black"><%=Utility.getTwoDecimal(_array[2])%></td>
            <td class="a10black"><%=Utility.getTwoDecimal(_array[3])%></td>
            <td class="a10black"><%=deflection[i]%></TD>
            <td class="a10black"><%out.print("L /" + deflectionsp[i]);%></TD>
            <td class="a10black"><%=Utility.getTwoDecimal(fbx[i])%></TD>
            <td class="a10black"><%=totalWeight[i]%></TD>
    <%}%></TR>
<%}%>
</table><BR>
<TABLE WIDTH="100%"><TR><td ALIGN="CENTER"><input type=submit value="View Result" name=resultBtn></TD></TR></TABLE>
</form></BODY></HTML>
