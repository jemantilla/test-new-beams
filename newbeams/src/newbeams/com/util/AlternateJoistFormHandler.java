package com.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.sql.SQLException;

public class AlternateJoistFormHandler extends DesignFormHandler{

    double sxa, ma, aspaceIn, depthIn, fbxx,wa; 
    String query;
    ArrayList propList = new ArrayList();
    boolean beamIsGood = false;


    public AlternateJoistFormHandler(){
        //loggingDebug = false;
    }

     public boolean getBeamIsGood(){ return beamIsGood; }    
     
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
  protected void validateASpaceIn(){
      try{
          aspaceIn = Double.parseDouble(design.getASpaceIn());
      }catch (Exception e){
          errMsg[0] = "SpaceIn";
          addFormException(Messages.getMsgStr(Messages.INVALID_NUMBER, errMsg));
      }
  }
  protected void validateDepthIn(){
      try{
          depthIn = Double.parseDouble(design.getDepthIn());
      }catch (Exception e){
          errMsg[0] = "DepthIn";
          addFormException(Messages.getMsgStr(Messages.INVALID_NUMBER, errMsg));
      }
  }       

    public boolean processAlternateJoist(HttpServletRequest pReq , HttpServletResponse pRes) throws Exception {
        beamIsGood = false;
        setLogDebug(" in processAlternateJoist");
        design.setValidSx(false);
        validResult = false;
      try{
            validateParams();
            if (errorFound) return false;
            fb =  Double.parseDouble((String)h.get(design.getMaterial()));
            fba = Double.parseDouble((String)h.get(design.getAMaterial()));
            setLogDebug("Material : " +design.getMaterial());
            validateSpaceIn();
            validateASpaceIn();
            validateDepthIn();
          if (this.errorFound) return false;
          // totalLoad = dlFirst + llfirst or totalLoad = dlUpper + llUpper
          if (design.getFloorType().equalsIgnoreCase("first")) tl = .055;
          else if (design.getFloorType().equalsIgnoreCase("upper")) tl = .040;
          else if (design.getFloorType().equalsIgnoreCase("attic")) tl = .030;
          else if (design.getFloorType().equalsIgnoreCase("roof")) tl=.055; 
              //setDesignTimberLoad();
          w = tl * spaceIn / 12;
          System.out.println("tl:"+tl+"-spaceIn:"+spaceIn+"-W:"+w);
          load = w;
          design.setLoad(""+ w);
          System.out.println("design.getLoad():"+design.getLoad());
          m = w * span * span / 8;
          System.out.println("span:"+span +"-m:"+m);
          validResult = false;
          setLogDebug("Material : " +design.getMaterial());

            propList.clear();
            String _desc = design.getBeamSize();
            query = createQueryString(_desc);
            propList = sqlHandler.executeQuery(query,4);
            String _array1[] = (String[]) propList.get(0);
            sx = Double.parseDouble(_array1[1]);
            double _i = Double.parseDouble(_array1[2]);
            fbxx = m*12/sx;
            if (fbxx <= fb) {
                double _fbx = Utility.getTwoDecimal(fbxx);
                System.out.println("5");
                moe = new Integer((String)h2.get(design.getMaterial())).intValue();
                double _deflection = Utility.getTwoDecimal((5*load*(span*span*span*span)*1728)/(384*moe*_i));
                int _deflectionsp = new Double(1/(_deflection/(span*12))).intValue();
                tempClass = new TempClass(_desc,sx,_i,0,0,_deflection,_deflectionsp,_fbx,null,0);
                beamIsGood = true;
                validResult = true;
                System.out.println("before return beamIsGood sx:"+sx);
                return true;
            }

            //calculations for alternate joist
            wa=tl * aspaceIn / 12;
            ma = (span*span*wa)/8;
            sxa=ma*12/fba; //sxa = ma/fba;
            creaSQLStatement(design.getMaterial(), sxa); // create SQL Statments depends on Material type
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

    private String createQueryString(String pBeamsize){
        try{
            if (design.getMaterial().equalsIgnoreCase("psl")) {
                return "select * from psl where Description='"+ design.getBeamSize()+"'";
            }else if (design.getMaterial().equalsIgnoreCase("std")) {
                return "select * from Checkstd1 where Description='"+ design.getBeamSize()+"'";
            }else if (design.getMaterial().equalsIgnoreCase("lvl")) {
                return "select * from lvl where Description='"+ design.getBeamSize()+"'";
            }else if (design.getMaterial().equalsIgnoreCase("woodIShapeWood")) {  
                return "select * from TJIWoodflange where ID ="+ Integer.parseInt(design.getBeamSize()); 
            }else if (design.getMaterial().equalsIgnoreCase("woodIShapeLVL")) {  
                return "select * from TJILVLflange where ID ="+ Integer.parseInt(design.getBeamSize());
            }
        }catch(Exception e){
            setLogDebug("from AlternateJoistFormHandler design.getBeamSize()"+design.getBeamSize());
            e.printStackTrace();
        }
            return "";
    }

    private void creaSQLStatement(String pMaterial, double pSx){
        double _depth = depthIn;
        sql = new String[1];
        if(pMaterial.equalsIgnoreCase("std")){
            moe = 1500;
            sql[0] = "select * from std where s=( s>="+pSx+" and depth <= "+_depth+" order by depth)";    
        }else if(pMaterial.equalsIgnoreCase("lvl")){
            moe = 2000;
            sql[0]= "select * from lvl where s=( s>="+pSx+" and depth <= "+_depth+" order by depth)";      
        }else if (pMaterial.equalsIgnoreCase("psl")){
            moe = 2000;
            sql[0] = "select * from psl where s=( s>="+pSx+" and depth <= "+_depth+" order by depth)";
        }else if(pMaterial.equalsIgnoreCase("woodIShapeWood")){
            moe = 1330;
            sql[0] ="select Description,S,I,W,depth,tWeb,a,b,flangemat,manufacturer from TJIWoodflange "+
                "where s>="+pSx+"  and depth <= "+_depth+" order by depth";
        }else if(pMaterial.equalsIgnoreCase("woodIShapeLVL")){
            moe = 1330;
            sql[0] = "select Description,S,I,W,depth,tWeb,a,b,flangemat,manufacturer from TJILVLflange "+
                "where s>="+pSx+"  and depth <= "+_depth+" order by depth";
        }
    }
}

