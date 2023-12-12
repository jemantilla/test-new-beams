package com.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class AlternateBeamFormHandler extends DesignFormHandler{

    double sxa, ma; 
    String query;
    ArrayList propList = new ArrayList();
    boolean beamIsGood = false;

    public AlternateBeamFormHandler(){
        //loggingDebug = false;
    }

    public boolean getBeamIsGood(){ return beamIsGood; }

    public boolean processMethod(HttpServletRequest pReq , HttpServletResponse pRes) throws Exception {
        return true;
    }

    public boolean processAlternate(HttpServletRequest pReq , HttpServletResponse pRes) throws Exception {
        beamIsGood = false;
        setLogDebug(" in processAlternate");
        design.setValidSx(false);
        validResult = false;
        try{
            validateParams();
            if (errorFound) return false;
            fb =  Double.parseDouble((String)h.get(design.getMaterial()));
            fba = Double.parseDouble((String)h.get(design.getAMaterial()));
            setLogDebug("Material : " +design.getMaterial());

            propList.clear();
            String _desc = design.getBeamSize();
            query = createQueryString(_desc);
            propList = sqlHandler.executeQuery(query,4);
            String _array1[] = (String[]) propList.get(0);
            sx = Double.parseDouble(_array1[1]);
            m=sx*fb/12;
            wMax = (8*m)/(span*span);
            load = wMax;
            design.setLoad(""+wMax);
            setLogDebug("m:"+m+"-sx:"+sx+"load:"+design.getLoad() + "-localload:"+load+ "-sx:"+sx+"-fb:"+fb);
            ma = (span*span*wMax)/8;
            sxa=ma*12/fba; //sxa = ma/fba;

            _desc = design.getABeamSize();
            query = createQueryString(_desc);
            propList = sqlHandler.executeQuery(query,4);
            _array1 = (String[]) propList.get(0);
            double _sx = Double.parseDouble(_array1[1]);
            double _i = Double.parseDouble(_array1[2]);
            if (sxa <= _sx) {
                double _fbx = Utility.getTwoDecimal((ma*12)/sxa);
                moe = new Integer((String)h2.get(design.getAMaterial())).intValue();
                double _deflection = Utility.getTwoDecimal((5*load*(span*span*span*span)*1728)/(384*moe*_i));
                int _deflectionsp = new Double(1/(_deflection/(span*12))).intValue();
                System.out.println("_desc:"+_desc);
                tempClass = new TempClass(_desc,sxa,_i,0,0,_deflection,_deflectionsp,_fbx,null,0);
                beamIsGood = true;
                validResult = true;
                System.out.println("before return beamIsGood sxa:"+sxa+"-_sx:"+_sx);
                return true;
            }
            
            createSQLStatement(design.getAMaterial(), sxa); // create SQL Statments depends on Material type
            setLogDebug("before execute");
            executeSQLStatements(design.getAMaterial()); // this execute SQL statements and create ArrayList with String array object used below
            setBeamValues(design.getAMaterial());
            if ((resultArray == null) || (resultArray.size() < 1)) {
                addFormException(Messages.HIGH_LOAD);
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
            addFormException(e.toString());
        }
        if (this.getErrorFound()) { return false;}

        design.setSx(sx);
        design.setM(m);
        validResult = true;
        return true;
    }

    private String createQueryString(String pBeamsize){
            if (design.getMaterial().equalsIgnoreCase("steel")) {
                return "select * from check_exist where Description='"+ design.getBeamSize()+"'";
            }else if (design.getMaterial().equalsIgnoreCase("std")) {
                return "select * from Checkstd"+design.getNumber()+" where Description='"+ design.getBeamSize()+"'";
            }else if (design.getMaterial().equalsIgnoreCase("lvl")) {
                return "select * from LVLCheck"+design.getNumber()+" where Description='"+ design.getBeamSize()+"'";
            }else if (design.getMaterial().equalsIgnoreCase("flistd")) {
                if (design.getNumber().equals("1")) {
                    return "select * from CheckS1 where Description='"+ design.getBeamSize()+"'";
                }else if (design.getNumber().equals("2")) {
                    return "select * from CheckS2 where Description='"+ design.getBeamSize()+"'";
                }else if (design.getNumber().equals("3")) {
                    return "select * from CheckS3 where Description='"+ design.getBeamSize()+"'";
                }
            }else if (design.getMaterial().equalsIgnoreCase("flilvl")) {
                if (design.getNumber().equals("1")) {
                    return "select * from CheckL1 where Description='"+ design.getBeamSize()+"'";
                }else if (design.getNumber().equals("2")) {
                    return "select * from CheckL2 where Description='"+ design.getBeamSize()+"'";
                }else if (design.getNumber().equals("3")) {
                    return "select * from CheckL3 where Description='"+ design.getBeamSize()+"'";
                }
            }
            return null;
    }

}
