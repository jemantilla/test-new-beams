package com.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
  
public class TimberAFrameFormHandler extends DesignFormHandler{

   public TimberAFrameFormHandler(){
        //loggingDebug = false;
    }   
           
   float l1,l2,l3,h1,h2,l,ht,mrafter,l1F,l1I,l2F,l2I,l3F,l3I,h1F,h1I,h2F,h2I,space,spaceFt,spaceIn,rl,rr,T, area;

    public boolean processMethod(HttpServletRequest pReq , HttpServletResponse pRes) throws Exception {
        return true;
    }  

   /* protected void refreshValues(int pArraySize){
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
            resultArray = sqlHandler.executeQuery(sql[0],9,3);
        }else{
            super.executeSQLStatements(pMaterial);
        }
    }      */
    protected void validateSpaceFt(){
        try{
            spaceFt = Float.parseFloat(design.getSpaceFt());
        }catch (Exception e){
            errMsg[0] = "SpaceFt";
            addFormException(Messages.getMsgStr(Messages.INVALID_NUMBER, errMsg));
        }
    }
    protected void validateSpaceIn(){
        try{
            System.out.println("design.getSpaceIn()"+design.getSpaceIn());
            spaceIn = Float.parseFloat(design.getSpaceIn());
        }catch (Exception e){
            errMsg[0] = "SpaceIn";
            addFormException(Messages.getMsgStr(Messages.INVALID_NUMBER, errMsg));
        }
    }

    protected void validateL1F(){
        try{
            l1F = Float.parseFloat(design.getL1F());
        }catch (Exception e){
            errMsg[0] = "L1f";
            addFormException(Messages.getMsgStr(Messages.INVALID_NUMBER, errMsg));
        }
    }
    protected void validateL1I(){
        try{
            l1I = Float.parseFloat(design.getL1I());
        }catch (Exception e){
            errMsg[0] = "L1I";
            addFormException(Messages.getMsgStr(Messages.INVALID_NUMBER, errMsg));
        }
    } 
    protected void validateL2F(){
        try{
            l2F = Float.parseFloat(design.getL2F());
        }catch (Exception e){
            errMsg[0] = "L2F";
            addFormException(Messages.getMsgStr(Messages.INVALID_NUMBER, errMsg));
        }
    }
   protected void validateL2I(){
        try{
            l2I = Float.parseFloat(design.getL2I());
        }catch (Exception e){
            errMsg[0] = "L2I";
            addFormException(Messages.getMsgStr(Messages.INVALID_NUMBER, errMsg));
        }
    }
   protected void validateL3F(){
        try{
            l3F = Float.parseFloat(design.getL3F());
        }catch (Exception e){
            errMsg[0] = "L3F";
            addFormException(Messages.getMsgStr(Messages.INVALID_NUMBER, errMsg));
        }
    }
   protected void validateL3I(){
        try{
            l3I = Float.parseFloat(design.getL3I());
        }catch (Exception e){
            errMsg[0] = "L3I";
            addFormException(Messages.getMsgStr(Messages.INVALID_NUMBER, errMsg));
        }
    }
   protected void validateH1F(){
        try{
            h1F = Float.parseFloat(design.getH1F());
        }catch (Exception e){
            errMsg[0] = "H1f";
            addFormException(Messages.getMsgStr(Messages.INVALID_NUMBER, errMsg));
        }
    }
    protected void validateH1I(){
        try{
            h1I = Float.parseFloat(design.getH1I());
        }catch (Exception e){
            errMsg[0] = "H1I";
            addFormException(Messages.getMsgStr(Messages.INVALID_NUMBER, errMsg));
        }
    } 
    protected void validateH2F(){
        try{
            h2F = Float.parseFloat(design.getH2F());
        }catch (Exception e){
            errMsg[0] = "H2F";
            addFormException(Messages.getMsgStr(Messages.INVALID_NUMBER, errMsg));
        }
    }
   protected void validateH2I(){
        try{
            h2I = Float.parseFloat(design.getH2I());
        }catch (Exception e){
            errMsg[0] = "H2I";
            addFormException(Messages.getMsgStr(Messages.INVALID_NUMBER, errMsg));
        }
    }

   /*  protected void setDesignTimberLoad(){
        if (design.getRoofIncluded()) {
            validateMaterb();
            validateRoofPitch();
            setLL();
        }
    }        */
       public boolean processDesignTimberAFrame(HttpServletRequest pReq , HttpServletResponse pRes) throws Exception {
        try{
            setLogDebug("Start processDesignTimberAFrame");
            validateSpaceFt();
            validateSpaceIn();
            validateL1F();
            validateL1I();
            validateL2F();
            validateL2I();
            validateL3F();
            validateL3I();
            validateH1F();
            validateH1I();
            validateH2F();
            validateH2I(); 
            if (this.errorFound) return false;
       /*     // totalLoad = dlFirst + llfirst or totalLoad = dlUpper + llUpper
            if (design.getFloorType().equalsIgnoreCase("first")) tl = .055;
            else if (design.getFloorType().equalsIgnoreCase("upper")) tl = .040;
            else if (design.getFloorType().equalsIgnoreCase("attic")) tl = .030;
            else if (design.getFloorType().equalsIgnoreCase("roof")) tl=.055; 
                //setDesignTimberLoad();  
            w = tl * spaceFt / 12; 
                   */
            space= spaceFt + (spaceIn/12) ;
            w= 0.045 * space;
            System.out.println("w"+w +"-spacein:"+spaceIn);

            load = w;
            design.setLoad(""+ w);
            System.out.println("design.getLoad():"+design.getLoad());
            l1= l1F + (l1I/12);
            l2= l2F + (l2I/12);
            l3= l3F + (l3I/12);
            h1= h1F + (h1I/12);
            h2= h2F + (h2I/12);
            ht= h1 + h2;
            l=l1+l2+l3;
            design.setSpan(l);
            System.out.println("l1"+l1 +"-l2:"+l2+"-l3:"+l3+"-l:"+l+"-w:"+w);

            rl=new Float(w*l/2).floatValue();
            System.out.println("rl"+rl +"-spacein:"+spaceIn);

            rr=rl;
            System.out.println("rl:"+rl +"-rr:"+rr);
            T=(rl * l)/(h2*2);
            area = new Float(T/.65).floatValue();
            mrafter = rl * l1;
            System.out.println("rl:"+rl +"-l1:"+l1+"-mrafter:"+mrafter);
            /*m = w * span * span / 8;
            System.out.println("span:"+span +"-m:"+m);   */
            validResult = false;
            fb =  Float.parseFloat((String)h.get(design.getMaterial()));
            sx = mrafter*12/fb;
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
        design.setRafter(mrafter);
        design.setT(T);
        design.setFb(fb);
        design.setArea(area);
        validResult = true;
        setLogDebug(" End processDesignTimberAFrame");
        return true;
    }

    protected void createSQLStatement(String pMaterial, double pSx){
        if(pMaterial.equalsIgnoreCase("std")){
            sqlCount=5;
            sql = new String[sqlCount];
            System.out.println("In createSQLStatement");
            sql[0]="select * from Checkstd1 where s=(select min(s) from Checkstd1 where s>="+pSx+")";
            sql[1]="select * from Checkstd2 where s=(select min(s) from Checkstd2 where s>="+pSx+")";
            sql[2]="select * from Checkstd3 where s=(select min(s) from Checkstd3 where s>="+pSx+")";
            sql[3]="select * from Checkstd4 where s=(select min(s) from Checkstd4 where s>="+pSx+")";
            sql[4]="select * from Checkstd5 where s=(select min(s) from Checkstd5 where s>="+pSx+")";
            System.out.println("In createSQLStatement 2");
            tableName = new String[sqlCount];
            tableName[0] = "Checkstd1";
            tableName[1] = "Checkstd2";
            tableName[2] = "Checkstd3";
            tableName[3] = "Checkstd4";
            tableName[4] = "Checkstd5";
         }else if(pMaterial.equalsIgnoreCase("lvl")){
            sqlCount=5;
            sql = new String[sqlCount];
            sql[0]= "select * from LVLCheck1 where s=(select min(s) from LVLCheck1 where s>="+pSx+" )";
            sql[1]= "select * from LVLCheck2 where s=(select min(s) from LVLCheck2 where s>="+pSx+" )";
            sql[2]= "select * from LVLCheck3 where s=(select min(s) from LVLCheck3 where s>="+pSx+" )";
            sql[3]= "select * from LVLCheck4 where s=(select min(s) from LVLCheck4 where s>="+pSx+" )";
            sql[4]= "select * from LVLCheck5 where s=(select min(s) from LVLCheck5 where s>="+pSx+" )";
            tableName = new String[sqlCount];
            tableName[0] = "LVLCheck1";
            tableName[1] = "LVLCheck2";
            tableName[2] = "LVLCheck3";
            tableName[3] = "LVLCheck4";
            tableName[4] = "LVLCheck5";
        }else if (pMaterial.equalsIgnoreCase("flistd")){
            sqlCount=3;
            sql = new String[sqlCount];
            sql[0] = "select * from CheckS1 where s=(select min(s) from CheckS1 where s>="+pSx+")";
            sql[1] = "select * from CheckS2 where s=(select min(s) from CheckS2 where s>="+pSx+")";
            sql[2] = "select * from CheckS3 where s=(select min(s) from CheckS3 where s>="+pSx+")";
            tableName = new String[sqlCount];
            tableName[0] = "CheckS1";
            tableName[1] = "CheckS2";
            tableName[2] = "CheckS3";
        }else if(pMaterial.equalsIgnoreCase("flilvl")){
            sqlCount=3;
            sql = new String[sqlCount];
            sql[0] = "select * from CheckL1 where s=(select min(s) from CheckL1 where s>="+pSx+" )";
            sql[1] = "select * from CheckL2 where s=(select min(s) from CheckL2 where s>="+pSx+" )";
            sql[2] = "select * from CheckL3 where s=(select min(s) from CheckL3 where s>="+pSx+" )";
            tableName = new String[sqlCount];
            tableName[0] = "CheckL1";
            tableName[1] = "CheckL2";
            tableName[2] = "CheckL3";
        }
    }
    protected void setBeamValues(String pMaterial){
        for (int i=0; i<resultArray.size(); i++) {
            String _array[] = (String[]) resultArray.get(i);
            String _description = _array[0];
            actSx[i] = Float.parseFloat(_array[1]);
            fbx[i] = Utility.getTwoDecimal(mrafter*12/actSx[i]);
            // add elements to Arraylist
            TempClass _tempclass = new TempClass();
            _tempclass.setVal(_description);
            _tempclass.setActsx(actSx[i]);
            _tempclass.setFbx(fbx[i]);
            System.out.println("_tempClass:"+_tempclass.getVal());
            tempList.add(_tempclass);
        }
        design.setResultArray(resultArray);
        design.setTempArray(tempList);
        System.out.println("tempList size:"+design.getTempArray().size());
    }
}
