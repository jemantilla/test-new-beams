package com.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class ExistTimberFormHandler extends DesignFormHandler{

    boolean alternateBeam = false;
    int _i;
    double id, fbxCheck;
    String query, _desc, _param;
    ArrayList propList = new ArrayList();

    public ExistTimberFormHandler(){
        //loggingDebug = false;
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
    
    public boolean getAlternateBeam(){ return alternateBeam; }

    public boolean processMethod(HttpServletRequest pReq , HttpServletResponse pRes) throws Exception {
        return true;
    }

    public boolean processCheckExistTimber(HttpServletRequest pReq , HttpServletResponse pRes) throws Exception {
        setLogDebug("   in processCheckExist");
        design.setValidSx(false);
        tempList.clear();
        resultArray.clear();
        propList.clear();
        validResult = false;
        alternateBeam = false;
        try{
            validateSpaceIn();
            validateParams();
            if (this.errorFound) return false;
            // totalLoad = dlFirst + llfirst or totalLoad = dlUpper + llUpper
            if (design.getFloorType().equalsIgnoreCase("first")) tl = .055;
            else if (design.getFloorType().equalsIgnoreCase("upper")) tl = .040;
            else if (design.getFloorType().equalsIgnoreCase("attic")) tl = .030;
            else if (design.getFloorType().equalsIgnoreCase("roof")) setDesignTimberLoad();
            w = tl * spaceIn / 12;
            load = w;
            design.setLoad(""+ w);
            System.out.println("design.getLoad():"+design.getLoad());
            m = w * span * span / 8;
            System.out.println("span:"+span +"-m:"+m);
            fb =  Double.parseDouble((String)h.get(design.getMaterial()));
            setLogDebug("m" + m +"-"+"Material : " +design.getMaterial() + "-span:"+ span);
            _desc = design.getBeamSize();
            if(design.getMaterial().equalsIgnoreCase("lvl")){
                moe = 1900;
                _param = _desc.substring((_desc.indexOf("X")+2),_desc.length());
                id = Double.parseDouble((String)HashValues.keyDouble.get(_param));
                _i = Integer.parseInt(design.getNumber());
                query = "select * from LVLCheck"+_i+" where Description='"+_desc+"'";
                propList = sqlHandler.executeQuery(query, 4);
                String _array[] = (String[]) propList.get(0);
                sx = Double.parseDouble(_array[1]);
                fbxCheck = (m*12)/sx;
                setLogDebug(_array[1]+"-fbxCheck:" + fbxCheck +"- fb:" + fb);
                if (!(fbxCheck <= fb)) {
                    sx = (m*12) / fb;
                    for (int i1=_i; i1<=5; i1++) {
                        query = "select * from LVLCheck"+i1+" where s=(select min(s) from LVLCheck"+i1+" where "+
                            "ID = "+id+" and s>="+sx+") order by s";
                        propList = sqlHandler.executeQuery(query, 5);
                        if (alternateBeamFound()) break;
                    }
                }else{
                    setDesignValues(_array);
                }
            }else if(design.getMaterial().equalsIgnoreCase("std")){
                moe = 1500;
                _i = Integer.parseInt(design.getNumber());
                int _index1 = design.getBeamSize().indexOf("X");
                id = Integer.parseInt( design.getBeamSize().substring((_index1-1),_index1) );
                query = "select * from Checkstd"+_i+" where Description='"+_desc+"'";
                propList = sqlHandler.executeQuery(query, 5);
                String _array[] = (String[]) propList.get(0);
                sx = Double.parseDouble(_array[1]);
                fbxCheck = (m*12)/sx;
                setLogDebug(_array[1]+"-fbxCheck:" + fbxCheck +"- fb:" + fb);
                if (!(fbxCheck <= fb)) {
                    sx = (m*12) / fb;
                    for (int i1=_i; i1<=5; i1++) {
                        query = "select * from Checkstd"+i1+" where s=(select min(s) from Checkstd"+i1+" where "+
                            "ID = "+id+" and s>="+sx+") order by s";
                        propList = sqlHandler.executeQuery(query, 4);
                        if (alternateBeamFound()) break;
                    }
                }else{
                    setDesignValues(_array);
                }
            }else if(design.getMaterial().equalsIgnoreCase("psl")){
                moe = 1500;
                _i = Integer.parseInt(design.getNumber());
                query = "select * from psl"+_i+" where Description='"+_desc+"'";
                propList = sqlHandler.executeQuery(query, 4);
                String _array[] = (String[]) propList.get(0);
                sx = Double.parseDouble(_array[1]);
                fbxCheck = (m*12)/sx;
                setLogDebug("rows:"+ propList.size() +"-sx:"+ _array[2]+"-"+"fbxCheck:" + fbxCheck +"- fb:" + fb);
                if (!(fbxCheck <= fb)) {
                    sx = (m*12) / fb;
                    for (int i1=_i; i1<=2; i1++) {
                        query = "select Description, s, I, W from psl"+i1+" where s >= "+sx;
                        propList = sqlHandler.executeQuery(query, 4, 1);
                        if (alternateBeamFound()) break;
                    }
                }else{
                    setDesignValues(_array);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            addFormException(e.toString());
        }
        if (this.getErrorFound()) {
            return false;
        }
        //insertProject();
        validResult = true;
        setLogDebug("   End processCheckExist");
        return true;
    }
    // this method is called from process_check_exist_beam.jsp
    public void setDesignValuesForCheckExistTable(int pIndex){
        String _str [] = (String[])design.getSxArray().get(pIndex);
        design.setSx(Double.parseDouble(_str[1]));
        setValues(_str);
    }

    private void setDesignValues(String[] pArray){
        setLogDebug("valid sx");
        design.setSx(sx);
        setValues(pArray);
    }
    private void setValues(String[] pArray){
        design.setShape(pArray[0]);
        design.setICheck(Double.parseDouble(pArray[2]));
        design.setValidSx(true);
        design.setDeflectCheck(calcDefelection(design.getICheck()));
        design.setDeflectSpCheck(calcDefelectionSp(design.getDeflectCheck()));
    }

    private boolean alternateBeamFound(){
        if ((propList != null) && (propList.size() >=1)) {
            String _str [] = (String[])propList.get(0);
            design.setSx(Double.parseDouble(_str[1]));
            design.setW(Double.parseDouble(_str[3]));
            setValues(_str);
            alternateBeam = true;
            return true;
        }else return false;
    }

    public boolean processNextSizeLVL(HttpServletRequest pReq , HttpServletResponse pRes) throws Exception {
        setLogDebug("   In processNextSizeLVL");
        try{
            propList.clear();
            setLogDebug("moe:"+moe);
            _i = Integer.parseInt(design.getNumber());
            
                for (int i1=_i; i1<=5; i1++) {
                    query = "select * from LVLCheck"+i1+" where s=(select min(s) from LVLCheck"+i1+" where "+
                        "ID >= "+ id +" and s>="+sx+")";
                    propList = sqlHandler.executeQuery(query, 5);
                    if (alternateBeamFound()) break;
                }
        }catch(Exception e){
            e.printStackTrace();
        }
        if (this.getErrorFound()) {
            return false;
        }
        validResult = true;
        setLogDebug("   End processNextSizeLVL");
        return true;
    }

    public boolean processNextSizeSTD(HttpServletRequest pReq , HttpServletResponse pRes) throws Exception {
        setLogDebug("   In processNextSizeSTD");
        try{
            propList.clear();
            setLogDebug("moe:"+moe);
            _i = Integer.parseInt(design.getNumber());
            
                for (int i1=_i; i1<=5; i1++) {
                    query = "select * from Checkstd"+i1+" where s=(select min(s) from Checkstd"+i1+" where "+
                        "ID >= "+ id +" and s>="+sx+")";
                    propList = sqlHandler.executeQuery(query, 4);
                    if (alternateBeamFound()) break;
                }
        }catch(Exception e){
            e.printStackTrace();
        }
        if (this.getErrorFound()) {
            return false;
        }
        validResult = true;
        setLogDebug("   End processNextSizeSTD");
        return true;
    }

    public boolean processInsertPlateLVL(HttpServletRequest pReq , HttpServletResponse pRes) throws Exception {
        setLogDebug("   In processInsertPlateLVL");
        try{
            propList.clear();
            int _index1 = _desc.indexOf("LVL");
            int _index2 = _desc.indexOf("X");
            int _index3= _desc.length();
            double _id3 = Double.parseDouble((String)HashValues.keyDouble.get( _desc.substring((_index1+4),(_index2-1)) ));
            double _id1 = Double.parseDouble((String)HashValues.keyDouble.get( _desc.substring((_index2+2),(_index3)) ));
            setLogDebug("In processInsertPlateLVL:"+_desc + "_id1:"+_id1 +"_id3"+_id3 +"-");
                for (int i1=1; i1<=3; i1++) {
                        query = "select Description, s, I, W, ID1, ID3 from CheckL"+i1+
                            " where ID1 >= "+_id1+" and ID3 >= "+_id3+" and s >= "+sx+ 
                            " order by ID1, ID3";
                        propList = sqlHandler.executeQuery(query, 4, 1);
                        if (alternateBeamFound()) break;
                }
        }catch(Exception e){ 
            e.printStackTrace();
            return false;
        }
        setLogDebug("   End processInsertPlateLVL");
        return true;
    }

    public boolean processInsertPlateSTD(HttpServletRequest pReq , HttpServletResponse pRes) throws Exception {
        try{
            propList.clear();
            this.setLogDebug("In processInsertPlateSTD:"+_desc);
                for (int i1=1; i1<=3; i1++) {
                        query = "select Description, s, I, W from CheckS"+i1+
                            " where s >= "+sx+" order by ID1, ID3";
                        propList = sqlHandler.executeQuery(query, 4, 1);
                        if (alternateBeamFound()) break;
                }
        }catch(Exception e){ 
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
