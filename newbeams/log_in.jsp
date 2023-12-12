<%@page import="com.util.Profile , javax.servlet.http.Cookie"%>
<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />
<jsp:useBean id="projectFormHandler" scope="session" class="com.util.ProjectFormHandler" />

<%
Profile profile = profileFormHandler.getProfile();
String sucessURL = request.getParameter("SucessURL");
if ((sucessURL != null) && (request.getMethod().equalsIgnoreCase("Post"))) {
    profileFormHandler.setMethodName("login");
    profile.setUserName(request.getParameter("userName"));
    profile.setPassWord(request.getParameter("password"));
    if ((request.getParameter("keepCookie") != null) && (request.getParameter("keepCookie").equals("Y"))) {
        profile.setKeepCookie(true);
    }else profile.setKeepCookie(false);
    if (profileFormHandler.processMethod(request, response)){
        projectFormHandler.setUserName(profile.getUserName());
        projectFormHandler.setLastProject(profile.getUserName());
        response.sendRedirect(sucessURL);
        return;
    }else if (profileFormHandler.getErrorFound()) {
        Object [] _errorVec = profileFormHandler.getFormExceptions();
        request.setAttribute("ErrorVector" ,_errorVec);%>
        <jsp:include page="error_page.jsp" flush="true"/>
    <%}
}else {
    Cookie[] cookies = request.getCookies();
    for(int i=0; i < cookies.length; i++) {
        Cookie loginCookie = cookies[i];
        //System.out.println(loginCookie.getName());
        if (loginCookie.getName().equals("www.newbeams.com")){
            try{
                String _val = loginCookie.getValue();
                String userName = _val.substring(0,_val.indexOf(","));
                String passWord = _val.substring((_val.indexOf(",")+1),_val.length());
                //System.out.println("userName:"+ userName +"-passWord:"+passWord);
                profileFormHandler.setMethodName("login");
                profile.setUserName(userName);
                profile.setPassWord(passWord);
                profile.setKeepCookie(true);
                if (profileFormHandler.processMethod(request, response)){
                    projectFormHandler.setUserName(profile.getUserName());
                    projectFormHandler.setLastProject(profile.getUserName());
                    response.sendRedirect("design.jsp");
                    return;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
%>

<html>
<head>
<title>Log In NewBeams.com</title>
<link rel="stylesheet" href="newbeams.css">
</head>
<!--<H1><CENTER><font color="#008080">Welcome To Newbeams.com</font></CENTER></H1>-->
<body topmargin=0><!--bgcolor="#ffffff" -->
<FORM METHOD="POST">
<INPUT TYPE="HIDDEN" NAME="SucessURL" VALUE="design.jsp">
<table width="100%" border="0" cellspacing="0" cellpadding="5">
<TBODY>
<TR>
<TD WIDTH="20%" VALIGN="TOP">
<TABLE WIDTH="100%">
    <tr>
        <td COLSPAN="2" ALIGN="LEFT"><IMG SRC="graphics/logo.gif" BORDER="0"></TD>
    </tr>
    <tr>
        <td>&nbsp;</td>
    </TR>
    <tr>
        <td class="a10blackbold" width="50%" align="right"> UserName (ID)</td>
        <td width="50%"><input type="text" name="userName" size="10" maxlength="10" class="courier10"  value=<%=profile.getUserName()%>></td>
    </tr>
    <tr>
        <td  width="50%"class="a10blackbold" align="right"> Password</td>
        <td width="50%"><input type="password" name="password" size="10" maxlength="8" class="courier10"></td>
    </tr>
    <tr bgcolor="#FFFFFF">
        <td colspan="2" align="center" class="a10black" valign="top">
          <INPUT TYPE="CHECKBOX" NAME="keepCookie" VALUE="Y">Remember ID and Password
        </td>
    </TR>
    <tr>
        <td colspan="2" align="center" class="a10black" valign="top">
          <input type="submit" name="Submit" value="Log In">
        </td>
    </tr>
    <tr>
        <td class="a10black"  colspan="2" align="center"><a href="agreement.jsp">New User</a></td>
    </tr>
    <tr>
        <td class="a10black"  colspan="2" align="center"><a href="forgot_password.jsp">Forgot Password?</a></td>
    </tr>
    <tr>
        <td>&nbsp;</td>
    </TR>
    <TR>
        <TD CLASS="a10blackbold" colspan="2">
        NewBeams.com<BR>
        11 Riverdale Avenue,<BR>
        Port Chester, NY - 10573<BR>
        1-888-NEW-BEAM <br>
        (1-888-639-2326)<BR>
        <a href="mailto:feedback@newbeams.com">feedback@newbeams.com</a>
        </TD>
    </TR>
</TABLE>    
</TD>
<TD WIDTH="80%" VALIGN="TOP">
    <TABLE width=351 align=left border=0>
      <TBODY>
        <TR>
          <TD><IMG SRC="graphics/beam_animate.gif" ALT="Newbeams.com" BORDER="0"></td>
        </TR>
      </TBODY>
    </TABLE>
    
<P><B>NewBeams.com,LLC</B> was founded in 1999 by <B>Thomas G.Ahneman</B>, a licensed professional structural engineer. 
        during <B>Tom's 20- year</B> practice of engineering he recognized that many clients and colleagues could benefit from 
        a beam design spreadsheet that determines simple beam sizes for small construction projects. Such structural 
        design software is available in the pages that follow: just log in, enter your project's information, select/provide
        parameters, and get your design choices.NewBeams.com provides structural alternatives for you to choose from so 
        you can meet your architectural design needs. The web page also stores your designs under your login name for
        future reference.<BR>
        
        <B>Mr.Ahneman is a licensed professional engineer in New York and Connecticut</B> where he practices, but has previously 
        worked on structural designs in Virginia, Colorado, Washington D.C., Texas, Massachusetts, Ohio, Maryland, and 
        Singapore.<BR>
        
        NewBeams.com provides new beam designs in wood, timber, standard lumber, engineered timber products, steel and 
        composite flitch plate construction. Whatever your structural needs are, they can be accomplished here.Beam design 
        is made simple with these intuitive worksheets. Should you have specific needs, please call us toll free at 
        <B>888-New-Beam(639-2326)</B>, or fax us at <B>888-639-2300</B>. The web page is free.Check it out. Please e-mail us 
        feedback on your experience to <a href="mailto:feedback@newbeams.com">feedback@newbeams.com</a><BR>
        
        If you need to contact a licensed professional engineer regarding signed and sealed calculations for your project,
        please go to our e-consultant section and type in your e-mail address and telephone area code. You will be given 
        the name and phone number of professional contacts by e-mail.<BR>
        Category:  Business<BR>
        
        This web page contains design software that can do beam designs for wood beams, lumber beams, engineered 
        timber beams, flitch plate beams, steel beams, timber A-frames, conventional timber framing and much more.<BR>
        select engineered design of beams with ease. Beam design is made simple with these intuitive worksheets. 
        Structural design the way a liensed structural engineer would do it,without the hassie or expense.<BR>
        Analyze a beam, existing or new, like a professional engineer. Check a beam size. Newbeams.com is beam selection 
        made simple.</P>
</TD>
</TR>
<TBODY>
</table>

</FORM>
</BODY></HTML>


	
