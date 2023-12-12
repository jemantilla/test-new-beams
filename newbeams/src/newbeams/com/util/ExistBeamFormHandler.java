package com.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class ExistBeamFormHandler extends DesignFormHandler{

    boolean alternateBeam = false;
    int _i;
    double id, fbxCheck;
    String query, _desc, _param;
    ArrayList propList = new ArrayList();

    public ExistBeamFormHandler(){
        //loggingDebug = false;
    }
    
    public boolean getAlternateBeam(){ return alternateBeam; }

    public boolean processMethod(HttpServletRequest pReq , HttpServletResponse pRes) throws Exception {
        return true;
    }

    public boolean processCheckExist(HttpServletRequest pReq , HttpServletResponse pRes) throws Exception {
        setLogDebug("   in processCheckExist");
        design.setValidSx(false);
        tempList.clear();
        resultArray.clear();
        propList.clear();
        validResult = false;
        try{
            validateParams();
            setLoadType();
            if (errorFound) return false;
            fb =  Double.parseDouble((String)h.get(design.getMaterial()));
            m=((load*span*span)/8);
            setLogDebug("triWidth :"+triWidth + "-sx" + sx +"-" +"m" + m +"-"+"Material : " +design.getMaterial() +
                        "-span:"+ span);
            design.setValidSx(false);
            alternateBeam = false;
            _desc = design.getBeamSize();
            if (design.getMaterial().equalsIgnoreCase("steel")) {
                moe = 29000;
                query = "select * from check_exist where Description='"+ design.getBeamSize()+"'";
                propList = sqlHandler.getValuesByDis(query);
                String _array[] = (String[]) propList.get(0);
                sx = Double.parseDouble(_array[1]);
                fbxCheck = (m*12)/sx;
                setLogDebug(_array[1]+"-fbxCheck:" + fbxCheck +"- fb:" + fb);
                if (!(fbxCheck <= fb)) {
                    sx = (m*12) / fb;
                    setLogDebug("invalid sx");
                    query = "select s.Description, s.sx, s.I, s.groupid from check_exist s where s.sx>="+sx+" and s.groupid= "+
                        "(select groupid from check_exist where sx =(select min(sx) from check_exist where sx>="+sx+"))";
                    propList = sqlHandler.executeQuery(query, 4);
                    int propListSize = propList.size();
                    while (propListSize < 4) {
                        String[] _allColumns = (String[])propList.get(propListSize-1);
                        query = "select Description, sx, I, groupid from check_exist s where groupid= "+
                            "(select max(groupid) from check_exist where groupid <"+ _allColumns[3]+")";
                        ArrayList _temp = sqlHandler.executeQuery(query, 4);
                        int _size =_temp.size();
                        if (_size < 1) {
                            break;
                        }
                        for (int i10=0; i10<_size; i10++) {
                            propList.add(propListSize,_temp.get(i10));
                            propListSize++;
                            if (propList.size() >= 4) {
                                break;
                            }
                        }
                    }
                    design.setSxArray(propList);
                }else{
                    setDesignValues(_array);
                }
            }else if(design.getMaterial().equalsIgnoreCase("lvl")){
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
            }else if(design.getMaterial().equalsIgnoreCase("flilvl")){
                moe = 1700;
                _i = Integer.parseInt(design.getNumber());
                query = "select * from CheckL"+_i+" where Description='"+_desc+"'";
                propList = sqlHandler.executeQuery(query, 4);
                String _array[] = (String[]) propList.get(0);
                setLogDebug("rows:"+ propList.size() +"-sx:"+ _array[1]+"-");
                sx = Double.parseDouble(_array[1]);
                fbxCheck = (m*12)/sx;
                setLogDebug("fbxCheck:" + fbxCheck +"- fb:" + fb);
                if (!(fbxCheck <= fb)) {
                    sx = (m*12) / fb;
                    int _index1 = _desc.indexOf("LVL");
                    int _index2 = _desc.indexOf("X");
                    int _index3 = _desc.indexOf("+");
                    int _index4 = _desc.indexOf("X",(_index2+1));
                    double _id3 = Double.parseDouble((String)HashValues.keyDouble.get( _desc.substring((_index1+4),(_index2-1)) ));
                    double _id1 = Double.parseDouble((String)HashValues.keyDouble.get( _desc.substring((_index2+2),(_index3-1)) ));
                    double _id2 = 0;
                    if (_i == 3) {
                        _id2 = Double.parseDouble((String)HashValues.keyDouble.get( _desc.substring((_index3+6),(_index4-1)) ));
                    }else {
                        _id2 = Double.parseDouble((String)HashValues.keyDouble.get( _desc.substring((_index3+2),(_index4-1)) ));
                    }
                    setLogDebug(_id1 +"-"+_id2 +"-"+_id3 +"-");
                    for (int i1=_i; i1<=3; i1++) {
                        query = "select Description, s, I, W from CheckL"+i1+
                            " where ID1 >= "+_id1+" and ID2 >= "+_id2+" and ID3 >= "+_id3+" and s >= "+sx+ 
                            " order by ID1, ID2, ID3";
                        propList = sqlHandler.executeQuery(query, 4, 1);
                        if (alternateBeamFound()) break;
                    }
                }else{
                    setDesignValues(_array);
                }
            }else if(design.getMaterial().equalsIgnoreCase("flistd")){
                moe = 1500;
                _i = Integer.parseInt(design.getNumber());
                query = "select * from CheckS"+_i+" where Description='"+_desc+"'";
                propList = sqlHandler.executeQuery(query, 4);
                String _array[] = (String[]) propList.get(0);
                sx = Double.parseDouble(_array[1]);
                fbxCheck = (m*12)/sx;
                setLogDebug("rows:"+ propList.size() +"-sx:"+ _array[2]+"-"+"fbxCheck:" + fbxCheck +"- fb:" + fb);
                if (!(fbxCheck <= fb)) {
                    sx = (m*12) / fb;
                    int _index1 = _desc.indexOf("STD");
                    int _index2 = _desc.indexOf("X");
                    int _index3 = _desc.indexOf("+");
                    int _index4 = _desc.indexOf("X",(_index2+1));
                    double _id3 = Double.parseDouble((String)HashValues.keyDouble.get( _desc.substring((_index1+4),(_index2-1)) ));
                    double _id1 = Double.parseDouble((String)HashValues.keyDouble.get( _desc.substring((_index2+2),(_index3-1)) ));
                    double _id2 = 0;
                    if (_i == 3) {
                        _id2 = Double.parseDouble((String)HashValues.keyDouble.get( _desc.substring((_index3+6),(_index4-1)) ));
                    }else {
                        _id2 = Double.parseDouble((String)HashValues.keyDouble.get( _desc.substring((_index3+2),(_index4-1)) ));
                    }
                    setLogDebug(_id1 +"-"+_id2 +"-"+_id3 +"-");
                    for (int i1=_i; i1<=3; i1++) {
                        query = "select Description, s, I, W from CheckS"+i1+
                            " where ID1 >= "+_id1+" and ID2 >= "+_id2+" and ID3 >= "+_id3+" and s >= "+sx+ 
                            " order by ID1, ID2, ID3";
                        propList = sqlHandler.executeQuery(query, 4, 1);
                        if (alternateBeamFound()) break;
                    }
                }else{
                    setDesignValues(_array);
                }
            }
            design.setFbxCheck(Utility.getTwoDecimal(fbxCheck));
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
