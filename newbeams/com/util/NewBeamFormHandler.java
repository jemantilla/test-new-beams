package com.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewBeamFormHandler extends DesignFormHandler{

    public boolean processMethod(HttpServletRequest pReq , HttpServletResponse pRes) throws Exception {
        setLogDebug(" in Process Method");
        validResult = false;
        try{
            validateParams();
            setLoadType();
            if (this.errorFound) return false;
            fb =  Double.parseDouble((String)h.get(design.getMaterial()));
            m=((load*span*span)/8);
            sx=((m*12)/fb);
            m = Utility.getTwoDecimal(m);
            sx= Utility.getTwoDecimal(sx);
            setLogDebug("triWidth :"+triWidth + "sx" + sx +"-" +"m" + m +"-"+"Material : " +design.getMaterial() +
                        "-span:"+ span);
            createSQLStatement(design.getMaterial(), sx); // create SQL Statments depends on Material type
            setLogDebug("before execute");
            executeSQLStatements(design.getMaterial()); // this execute SQL statements and create ArrayList with String array object used below
            if ((resultArray == null) || (resultArray.size() < 1)) {
                addFormException(Messages.HIGH_LOAD);
                return false;
            }else {
                setBeamValues(design.getMaterial());
            }
        }catch(Exception e){
            e.printStackTrace();
            addFormException(e.toString());
        }

        if (this.getErrorFound()) {
            return false;
        }
        validResult = true;
        design.setSx(sx);
        design.setM(m);
        setLogDebug(" End Process Method");
        return true;
    }
}
