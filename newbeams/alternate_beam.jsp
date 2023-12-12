<%@ page import="java.util.ArrayList, com.util.TempClass, com.util.Utility, com.util.Design" %>
<%@ page import="com.util.Utility" %>

<jsp:useBean id="alternateBeamFormHandler" scope="session" class="com.util.AlternateBeamFormHandler" />
<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />

<HTML>
<HEAD><TITLE> Result </TITLE></HEAD>
<BODY style="background-color: #DBEAF5">
<form method="post" action="process_alternate_beam.jsp">
<input type="hidden" name="SucessURL" VALUE="result_alternate_beam.jsp">
<input type="hidden" name="ErrorURL" VALUE="alternate_beam.jsp">
<CENTER>
<%
String sucessURL = request.getParameter("SucessURL");
Design design = alternateBeamFormHandler.getDesign();
design.setStatus("New");
if (!profileFormHandler.getProfile().getValidUser()) {
    response.sendRedirect("log_in.jsp");
    return;
}else if (!alternateBeamFormHandler.getValidResult()) {
    response.sendRedirect("design.jsp");
    return;
}else {
    if (alternateBeamFormHandler.getErrorFound()) {
        Object [] _errorVec = alternateBeamFormHandler.getFormExceptions();
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
        <td class="a10blackbold" align="center">Moment of<br>Inertia</td>
        <td class="a10blackbold" align="center">Weight (lbf)</td>
        <td class="a10blackbold" align="center">Deflection (in)</td>
        <td class="a10blackbold" align="center">Deflection (span)</td>
        <td class="a10blackbold" align="center">Bending<br>Stress (ksi)</td>
        <td class="a10blackbold" align="center">Total<br>Weight (lbs)</td>
    </tr>
									  
<%if (resultArray != null) {
    for (int i=0; i<resultArray.size(); i++){
        String _array[] = (String[])resultArray.get(i);%>
        <tr align="center" bgcolor="#FFFFFF"> 
          <%if (design.getAMaterial().equalsIgnoreCase("flistd")){%>
            <td class="a10black" align="center"><%=_array[4]%></td>
          <%}else {%>
            <td class="a10black"><%=_array[0]%></td>
          <%}%>
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
<center>
<%ArrayList tempList = alternateBeamFormHandler.getTempList();
System.out.println("from alternate_beam tempList size:"+ tempList.size());
for (int i=0; i<tempList.size(); i++) {
    TempClass _tempClass = (TempClass) tempList.get(i);
    System.out.println(_tempClass.getTotalWeight());
}
if (tempList.size() == 1) {
    TempClass _tempClass0 = (TempClass) tempList.get(0);%>
    <input type=radio value=x name=R2><B>Size = </B>
    <%if (design.getAMaterial().equalsIgnoreCase("flistd")){
        out.print(_tempClass0.getLookUp());
    }else{
        out.print(_tempClass0.getVal());}%>&nbsp;&nbsp;&nbsp;&nbsp;<B>Total Weight = </B><%=_tempClass0.getTotalWeight()%><B> lbs</B>
<%}else if (tempList.size() > 1) {
    TempClass _tempClass0 = (TempClass) tempList.get(0);
    TempClass _tempClass1 = (TempClass) tempList.get(1);%>
    <table WIDTH="100%">
    <TR><td width="30%"></td><td width="70%" ALIGN="LEFT">
    <input type=radio value=y name=R2><B>Size = </B>
    <%if (design.getAMaterial().equalsIgnoreCase("flistd")){
        out.print(_tempClass1.getLookUp());
    }else{
        out.print(_tempClass1.getVal());}%>&nbsp;&nbsp;&nbsp;&nbsp;<B>Total Weight = </B><%=_tempClass1.getTotalWeight()%><B> lbs</B></TD></TR>
    <TR><td width="30%"></td><td width="70%" ALIGN="LEFT">
    <input type=radio value=x name=R2><B>Size = </B>
    <%if (design.getAMaterial().equalsIgnoreCase("flistd")){
        out.print(_tempClass0.getLookUp());
    }else{
        out.print(_tempClass0.getVal());}%>&nbsp;&nbsp;&nbsp;&nbsp;<B>Total Weight = </B><%=_tempClass0.getTotalWeight()%><B> lbs</B></TD></TR></TABLE>
    
<%}
%><BR></CENTER>
<TABLE WIDTH="100%"><TR><td ALIGN="CENTER"><input type=submit value="View Result" name=resultBtn></TD></TR></TABLE>
</form></BODY></HTML>
