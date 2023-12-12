package com.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class DesignTimberFormHandler extends DesignFormHandler{

    public DesignTimberFormHandler(){
        //loggingDebug = false;
    }

    public boolean processMethod(HttpServletRequest pReq , HttpServletResponse pRes) throws Exception {
        return true;
    }

    protected void refreshValues(int pArraySize){
        if (design.getMaterial().equalsIgnoreCase("woodIShape")){
            depth = new float[pArraySize];
            tWeb = new float[pArraySize];
            a = new float[pArraySize];
            b = new float[pArraySize];
            flangemat = new String[pArraySize];
        }
        super.refreshValues(pArraySize);
    }
    protected void executeSQLStatements(String pMaterial) throws SQLException{
        if(pMaterial.equalsIgnoreCase("woodIShape")){
            tempList.clear();
            resultArray = sqlHandler.executeQuery(sql[0],9,3);
            refreshValues(resultArray.size());
        }else{
            super.executeSQLStatements(pMaterial);
        }
    }
    protected void validateSpaceIn(){
        try{
            spaceIn = Double.parseDouble(design.getSpaceIn());
        }catch (Exception e){
            errMsg[0] = "SpaceIn";
            addFormException(Messages.getMsgStr(Messages.INVALID_NUMBER, errMsg));
        }
    }
    
    protected void setDesignTimberLoad(){
        if (design.getRoofIncluded()) {
            validateMaterb();
            validateRoofPitch();
            setLL();
        }
    }
    public boolean processDesignTimber(HttpServletRequest pReq , HttpServletResponse pRes) throws Exception {
        try{
            setLogDebug("Start processDesignTimber");
            validateSpaceIn();
            validateParams();
            if (this.errorFound) return false;
            // totalLoad = dlFirst + llfirst or totalLoad = dlUpper + llUpper
            if (design.getFloorType().equalsIgnoreCase("first")) tl = .055;
            else if (design.getFloorType().equalsIgnoreCase("upper")) tl = .040;
            else if (design.getFloorType().equalsIgnoreCase("attic")) tl = .030;
            else if (design.getFloorType().equalsIgnoreCase("roof")) tl=.055; 
                //setDesignTimberLoad();
            w = tl * spaceIn / 12;
            load = w;
            design.setLoad(""+ w);
            System.out.println("design.getLoad():"+design.getLoad());
            m = w * span * span / 8;
            System.out.println("span:"+span +"-m:"+m);
            validResult = false;
            fb =  Double.parseDouble((String)h.get(design.getMaterial()));
            sx = m * 12 /fb;
            createSQLStatement(design.getMaterial(), sx); // create SQL Statments depends on Material type
            setLogDebug("before execute");
            // this execute SQL statements and create ArrayList with String array object used below
            executeSQLStatements(design.getMaterial());
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
        design.setSx(sx);
        design.setM(m);
        validResult = true;
        setLogDebug(" End processDesignTimber");
        return true;
    }
}
