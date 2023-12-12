<%@ page import="com.util.Design, com.util.Profile" %>

<jsp:useBean id="existTimberFormHandler" scope="session" class="com.util.ExistTimberFormHandler" />
<jsp:useBean id="profileFormHandler" scope="session" class="com.util.ProfileFormHandler" />
<jsp:useBean id="projectFormHandler" scope="session" class="com.util.ProjectFormHandler" />

<%
int index = 0;
String sucessURL = request.getParameter("SucessURL");
String btn1 = request.getParameter("btn1");
Profile profile = profileFormHandler.getProfile();
if ( (!profile.getValidUser()) || (!request.getMethod().equalsIgnoreCase("Post"))) {
    response.sendRedirect("log_in.jsp");
    return;
}else if (sucessURL != null) {
    System.out.println("index" + request.getParameter("index"));
    Design design  = existTimberFormHandler.getDesign();
    if ((btn1 != null) && (btn1.equalsIgnoreCase("Next"))) {
        String _process = request.getParameter("index");
        if (_process.equals("Next Size")) {
            if (design.getMaterial().equals("lvl")) {
                existTimberFormHandler.processNextSizeLVL(request,response);
            }else if (design.getMaterial().equals("std")) {
                existTimberFormHandler.processNextSizeSTD(request,response);
            }
        }else if (_process.equals("Insert Plate")) {
            if (design.getMaterial().equals("lvl")) {
                existTimberFormHandler.processInsertPlateLVL(request,response);
            }else if (design.getMaterial().equals("std")) {
                existTimberFormHandler.processInsertPlateSTD(request,response);
            }
        }
        response.sendRedirect("result_check_exist.jsp?backURL=check_exist_timber_beam.jsp");
        return;
    }else if (request.getParameter("index") != null){
        try{
            index = Integer.parseInt(request.getParameter("index"));
            existTimberFormHandler.setDesignValuesForCheckExistTable(index);
            
            design.setStatus("Exist_Alter");
            projectFormHandler.insertProject();
            projectFormHandler.setBeamInserted(false);
            // get value from design.getSxArray().get[index] values
        }catch(Exception e){ e.printStackTrace();}
        response.sendRedirect(sucessURL + "?backURL=check_exist_timber_beam.jsp");
        return;
    }else{
        response.sendRedirect("log_in.jsp");
        return;
    }
}
%>
