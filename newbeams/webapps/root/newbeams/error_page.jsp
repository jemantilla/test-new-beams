<%
Object [] _ErrorArray = (Object[]) request.getAttribute("ErrorVector");
out.write("<TABLE WIDTH=\"100%\" >");
for (int i=0; i<_ErrorArray.length; i++) {
    out.write("<TR ALIGN = CENTER><TD><FONT COLOR=\"RED\">");
    out.print(_ErrorArray[i]);
    out.write("</FONT></TD></TR>");
}
out.write("</TABLE>");
%>

