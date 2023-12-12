package com.util;                                                                                     

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.ArrayList;

public abstract class DesignFormHandler extends GenericFormHandler{

    boolean validResult = false;
    byte ll = 30;
    int storey, sqlCount;//  dlfirst = 15, llFirst = 40, dlUpper = 10, llUpper = 20, tlAttic = 30;
    int deflectionsp[] = null;
    float roofPitch, span, spanf, spani;
    double w;
    float [] depth,tWeb,a,b;
    double  triFeet, triInch, triWidth, materb, load, loadt, tl, m, sx, moe, wMax; 
    double fb, fba, spaceIn;
    double  fbx[], moi[], weight[], totalweight[], deflection[], actSx[];
    String beamSize, loadType, mProjectName;
    String sql[], flangemat[], tableName[];
    Object [] errMsg = new Object[1];
    ArrayList resultArray = new ArrayList();
    ArrayList tempList = new ArrayList();
    SQLHandler sqlHandler = new SQLHandler();
    Design design = new Design();
    //DesignHelp designHelp = new DesignHelp(design);
    TempClass tempClass;
    static Hashtable h = new Hashtable();
    static Hashtable h1 = new Hashtable();
    static Hashtable h2 = new Hashtable();

    static {
        h.put("std","1.45f");
        h.put("steel","24.0f");
        h.put("lvl","2.3f");
        h.put("flistd","1.40f");
        h.put("flilvl","1.77f");
        h.put("woodIShape", "2.0f");
        h.put("woodIShapeWood", "2.0f");
        h.put("woodIShapeLVL", "2.0f");
        h.put("psl", "2.6f");
        
        h1.put("Asphalt","15f");
        h1.put("BuiltUp","15f");
        h1.put("MetalStandingSeam","20f");
        h1.put("Slate","25f");
        h1.put("Wood","20f");
        h1.put("Tile","20f");

        h2.put("std","1500");
        h2.put("steel","29000");
        h2.put("lvl","1900");
        h2.put("flistd","1500");
        h2.put("flilvl","1700");
        h2.put("woodIShape", "1330");
        h2.put("woodIShapeWood", "1330");
        h2.put("woodIShapeLVL", "1330");
        h2.put("psl", "2000");
    }

    public DesignFormHandler(){
        //loggingDebug = false;
    }

    public double getFb(){ return fb; }

    public boolean getValidResult(){ return validResult; }

    public void setTempClass(TempClass pTempClass){ tempClass = pTempClass; }
    public TempClass getTempClass(){ return tempClass; }

    public void setDesign(Design pDesign){ design = pDesign; }
    public Design getDesign() { return design; }

    //public void setDesignHelp(DesignHelp pDesignHelp){ designHelp = pDesignHelp; }
    //public DesignHelp getDesignHelp() { return designHelp; }

    public String getFileName(){ return (design.getMaterial()+design.getNumber() + ".jsp"); }

    public String getAFileName(){ return (design.getAMaterial()+design.getANumber() + ".jsp"); }

    public ArrayList getTempList(){ return tempList; }

    public void setBeamSize(String pBeamSize){
        if ((pBeamSize != null) && (pBeamSize.length() > 0)) {
            beamSize = pBeamSize;
        }else{
            addFormException(Messages.UNSELECTED_REDIO);
        }
    }
    public String getBeamSize(){ return beamSize; }

    public void setProjectName(String pProjectName){ mProjectName = pProjectName; }
    public String getProjectName(){ return mProjectName; }

    protected void validateProject(){
        if ((mProjectName == null) || ( mProjectName.length() < 1)) {
            addFormException(Messages.INVALID_PROJECTNAME);
        }
    }

    public void validateParams(){
        //designHelp.setMaterialValues();
        //designHelp.setMaterBValues();
        validateSpan();
        validateProject();
    }

    public void validateSpan(){
        if ((design.getSpanf().length() < 1) && (design.getSpani().length() < 1)) {
            errMsg[0] = "Span values";
            addFormException(Messages.getMsgStr(Messages.INVALID_NUMBER, errMsg));
            return;
        }
        if (design.getSpanf().length() > 0){
            try{
                spanf = Integer.parseInt(design.getSpanf());
            }catch (Exception e){
                errMsg[0] = "Span Feet";
                addFormException(Messages.getMsgStr(Messages.INVALID_NUMBER, errMsg));
            }
        }else spanf = 0;
        if (design.getSpani().length() > 0){
            try{
                spani = Integer.parseInt(design.getSpani());
                if ((spani < 1) && (spanf < 1)) {
                    errMsg[0] = "Span Inch";
                    addFormException(Messages.getMsgStr(Messages.INVALID_NUMBER, errMsg));
                }
            }catch (Exception e){
                errMsg[0] = "Span Inch";
                addFormException(Messages.getMsgStr(Messages.INVALID_NUMBER, errMsg));
            }
        }else {
            spani = 0;
        }
        span = (float)Utility.getTwoDecimal(spanf+(spani/12));
        design.setSpan(span);
    }
    public void validateTributaryWidth(){
        try{
            triInch = Double.parseDouble(design.getTriInch());
        }catch (Exception e){
            errMsg[0] = "TriInch";
            addFormException(Messages.getMsgStr(Messages.INVALID_NUMBER, errMsg));
        }
        try{
            storey = Integer.parseInt(design.getStorey());
        }catch (Exception e){
            errMsg[0] = "Storey";
            addFormException(Messages.getMsgStr(Messages.INVALID_NUMBER, errMsg));
        }
        try{
            triFeet = Double.parseDouble(design.getTriFeet());
        }catch (Exception e){
            errMsg[0] = "TriFeet";
            addFormException(Messages.getMsgStr(Messages.INVALID_NUMBER, errMsg));
        }
    }
    public void validateLoad(){
        try{
            int _decimalIndex = design.getLoad().indexOf(".");
            if (_decimalIndex >= 0) {
                int _totalLength = design.getLoad().length();
                if ((_totalLength-_decimalIndex) > 3) {
                    addFormException(Messages.INVALID_LOAD_DECIMAL);
                }
            }
            load = Double.parseDouble(design.getLoad());
            if (load >=100) {
                addFormException(Messages.HIGH_LOAD);
            }
        }catch (Exception e){
            errMsg[0] = "Load";
            addFormException(Messages.getMsgStr(Messages.INVALID_NUMBER, errMsg));
        }
    }
    protected void validateMaterb(){
        try{
            materb = Double.parseDouble((String)h1.get(design.getMaterb()));
        }catch (Exception e){
            errMsg[0] = "Material Roof";
            addFormException(Messages.getMsgStr(Messages.INVALID_NUMBER, errMsg));
        }
    }
    protected void validateRoofPitch(){
        try{
            roofPitch = Float.parseFloat(design.getRoofPitch());
            if ( (roofPitch < 0) || (roofPitch > 14)) {
                errMsg[0] = "Roof Pitch";
                addFormException(Messages.getMsgStr(Messages.INVALID_NUMBER, errMsg));
            }
        }catch (Exception e){
            errMsg[0] = "Roof Pitch";
            addFormException(Messages.getMsgStr(Messages.INVALID_NUMBER, errMsg));
        }
    }
    protected void setLL(){
        if (roofPitch <= 2) {
            ll = 40;
        }else if ((roofPitch > 2) && (roofPitch <= 6)) {
            ll = 30;
        }else if ((roofPitch > 6) && (roofPitch <= 12)) {
            ll = 25;
        }else if ((roofPitch > 12) && (roofPitch <= 14)) {
            ll = 20;
        }
        tl = ll + materb;
    }
    
    void setLoadType(){
        if (design.getMethodName().equalsIgnoreCase("TributaryWidth")) {
            validateTributaryWidth();
            if (this.errorFound) return;
            triWidth=triFeet+(triInch/12);
            if (design.getRoofIncluded()) {
                validateMaterb();
                validateRoofPitch();
                setLL();
                loadt = (triWidth/2)*(storey-1)*(0.055) + (triWidth)*(tl/1000) ;
            }else {
                loadt=(triWidth/2)*.055*storey;
            }
            load=loadt;   // replace with load from front end
            design.setLoad(""+load);
        }else validateLoad();
    }
    private double calcIReq(){
        double _iReq = load*span*span*span*450/moe;   //  1728*240*5/384 = 5400
        return _iReq;
    }

    protected void createSQLStatement(String pMaterial, double pSx){
        if(pMaterial.equalsIgnoreCase("std")){
            moe = 1500;
            double _iReq = calcIReq();
            sqlCount=5;
            sql = new String[sqlCount];
            sql[0]="select * from Checkstd1 where s=(select min(s) from Checkstd1 where s>="+pSx+" and i >= "+_iReq+")";
            sql[1]="select * from Checkstd2 where s=(select min(s) from Checkstd2 where s>="+pSx+" and i >= "+_iReq+")";
            sql[2]="select * from Checkstd3 where s=(select min(s) from Checkstd3 where s>="+pSx+" and i >= "+_iReq+")";
            sql[3]="select * from Checkstd4 where s=(select min(s) from Checkstd4 where s>="+pSx+" and i >= "+_iReq+")";
            sql[4]="select * from Checkstd5 where s=(select min(s) from Checkstd5 where s>="+pSx+" and i >= "+_iReq+")";
            tableName = new String[sqlCount];
            tableName[0] = "Checkstd1";
            tableName[1] = "Checkstd2";
            tableName[2] = "Checkstd3";
            tableName[3] = "Checkstd4";
            tableName[4] = "Checkstd5";
        }else if(pMaterial.equalsIgnoreCase("steel")){
            moe = 29000;
            double _iReq = calcIReq();
            sqlCount=1;
            sql = new String[sqlCount];
            sql[0]= "select * from steelt where s>="+pSx+" and  i >= "+_iReq+" and groupid= (select groupid from steelt where s =("+
                "select min(s) from steelt where s>="+pSx+" and i >= "+_iReq+"))";
            //sql[0]="select * from steelt where s=(select min(s) from steelt where s>="+pSx+" and i >= "+_iReq+")";
            tableName = new String[sqlCount];
            tableName[0] = "steelt";
        }else if(pMaterial.equalsIgnoreCase("lvl")){
            moe = 2000;
            double _iReq = calcIReq();
            sqlCount=5;
            sql = new String[sqlCount];
            sql[0]= "select * from LVLCheck1 where s=(select min(s) from LVLCheck1 where s>="+pSx+" and i >= "+_iReq+")";
            sql[1]= "select * from LVLCheck2 where s=(select min(s) from LVLCheck2 where s>="+pSx+" and i >= "+_iReq+")";
            sql[2]= "select * from LVLCheck3 where s=(select min(s) from LVLCheck3 where s>="+pSx+" and i >= "+_iReq+")";
            sql[3]= "select * from LVLCheck4 where s=(select min(s) from LVLCheck4 where s>="+pSx+" and i >= "+_iReq+")";
            sql[4]= "select * from LVLCheck5 where s=(select min(s) from LVLCheck5 where s>="+pSx+" and i >= "+_iReq+")";
            tableName = new String[sqlCount];
            tableName[0] = "LVLCheck1";
            tableName[1] = "LVLCheck2";
            tableName[2] = "LVLCheck3";
            tableName[3] = "LVLCheck4";
            tableName[4] = "LVLCheck5";
        }else if (pMaterial.equalsIgnoreCase("flistd")){
            moe = 1500;
            double _iReq = calcIReq();
            sqlCount=3;
            sql = new String[sqlCount];
            sql[0] = "select * from CheckS1 where s=(select min(s) from CheckS1 where s>="+pSx+" and i >= "+_iReq+")";
            sql[1] = "select * from CheckS2 where s=(select min(s) from CheckS2 where s>="+pSx+" and i >= "+_iReq+")";
            sql[2] = "select * from CheckS3 where s=(select min(s) from CheckS3 where s>="+pSx+" and i >= "+_iReq+")";
            tableName = new String[sqlCount];
            tableName[0] = "CheckS1";
            tableName[1] = "CheckS2";
            tableName[2] = "CheckS3";
        }else if (pMaterial.equalsIgnoreCase("psl")){
            moe = 2000;
            double _iReq = calcIReq();
            sqlCount=2;
            sql = new String[sqlCount];
            sql[0] = "select * from psl1 where s=(select min(s) from psl1 where s>="+pSx+" and i >= "+_iReq+")";
            sql[1] = "select * from psl2 where s=(select min(s) from psl2 where s>="+pSx+" and i >= "+_iReq+")";
            tableName = new String[sqlCount];
            tableName[0] = "psl1";
            tableName[1] = "psl2";
        }else if(pMaterial.equalsIgnoreCase("woodIShape")){
            moe = 1330;
            double _iReq = calcIReq();
            sqlCount= 1;
            sql = new String[sqlCount];
            sql[0]="select Description,S,I,W,depth,tWeb,a,b,flangemat from woodIShape where s>="+pSx+"  and i >= "+_iReq+" order by s";
            tableName = new String[sqlCount];
            tableName[0] = "woodIShape";
        }else if(pMaterial.equalsIgnoreCase("flilvl")){
            moe = 1700;
            double _iReq = calcIReq();
            sqlCount=3;
            sql = new String[sqlCount];
            sql[0] = "select * from CheckL1 where s=(select min(s) from CheckL1 where s>="+pSx+" and i >= "+_iReq+")";
            sql[1] = "select * from CheckL2 where s=(select min(s) from CheckL2 where s>="+pSx+" and i >= "+_iReq+")";
            sql[2] = "select * from CheckL3 where s=(select min(s) from CheckL3 where s>="+pSx+" and i >= "+_iReq+")";
            tableName = new String[sqlCount];
            tableName[0] = "CheckL1";
            tableName[1] = "CheckL2";
            tableName[2] = "CheckL3";
        }
    }

    protected void refreshValues(int pArraySize){
        System.out.println("in designFormHandler.refreshValues() parraySize:"+ pArraySize);
        moi=new double[pArraySize];
        fbx = new double[pArraySize];
        weight = new double[pArraySize];
        totalweight = new double[pArraySize];
        deflection = new double[pArraySize];
        deflectionsp = new int[pArraySize];
        actSx = new double[pArraySize];
    }

    protected void executeSQLStatements(String pMaterial) throws SQLException{
        //tempList.clear();
        resultArray.clear();
        if (pMaterial.equalsIgnoreCase("steel")){
            resultArray = sqlHandler.executeQuery(sql[0], 5);
            int resultArraySize = resultArray.size();
            while (resultArraySize < 4) {
                String[] _allColumns = (String[])resultArray.get(resultArraySize-1);
                String _query = "select * from steelt where groupid= "+
                    "(select max(groupid) from steelt where groupid <"+ _allColumns[4]+") order by s";
                ArrayList _temp = sqlHandler.executeQuery(_query, 5);
                int _size =_temp.size();
                if (_size < 1) {
                    break;
                }
                for (int i10=0; i10<_size; i10++) {
                    resultArray.add(resultArraySize,_temp.get(i10));
                    resultArraySize++;
                    if (resultArray.size() >= 4) {
                        break;
                    }
                }
            }
            sqlCount = resultArray.size();
        }else{
            for(int i=0; i<sqlCount; ++i){
                int columnCount;
                if (pMaterial.equalsIgnoreCase("flistd")||pMaterial.equalsIgnoreCase("lvl")||pMaterial.equalsIgnoreCase("std")||pMaterial.equalsIgnoreCase("psl")){
                    columnCount = 5;
                }else if (pMaterial.equalsIgnoreCase("woodIShapeWood")||pMaterial.equalsIgnoreCase("woodIShapeLVL")){
                    columnCount = 6;
                }else{
                    columnCount = 4;
                }
                String _array [] = sqlHandler.executeQueryRow(sql[i],columnCount);
                if (_array != null) { resultArray.add(_array); }
            }
        }
        System.out.println("resultArray size:"+ resultArray.size());
        refreshValues(sqlCount);
    }

    protected String getStatement(String pTableName , double pI){
        if (pTableName.equals("woodIShape")) {
            return "select Description,S,I,W,depth,tWeb,a,b,flangemat from woodIShape where I>="+pI+" order by s";
        }else{
            return "select * from "+pTableName+" where I=(select min(I) from "+pTableName+" where I>="+pI+")";
        }
    }

    protected void setWoodIShapeValues(String[] pArray, int pIndex){
        depth[pIndex] = Float.parseFloat(pArray[4]);
        tWeb[pIndex] = Float.parseFloat(pArray[5]);
        a[pIndex] = Float.parseFloat(pArray[6]);
        b[pIndex] = Float.parseFloat(pArray[7]);
        flangemat[pIndex] =  pArray[8];
    }
    protected void setBeamValues(String pMaterial){
        tempList.clear();
        for (int i=0; i<resultArray.size(); i++) {
            String _array[] = (String[]) resultArray.get(i);
            String _description = "";
            moi[i] = Double.parseDouble(_array[2]);
            deflection[i] = calcDefelection(moi[i]);
            System.out.println("Deflection"+deflection[i]);
            deflectionsp[i] = calcDefelectionSp(deflection[i]);
            System.out.println("Deflectionsp"+deflectionsp[i]);
            //if defelectionSP < 240 than modify defelection, defelectionSp and moi
            if (deflectionsp[i] < 240) {
                setLogDebug("before pDefelection:"+ deflection[i]+"-pDefelectionsp:"+deflectionsp[i]+"-moi[i]"+moi[i]);
                double _tempDeflection = (240.0/deflectionsp[i])*deflection[i];
                deflection[i] = Utility.getTwoDecimal(_tempDeflection);
                double _tempMoi = moi[i]*(240.0/deflectionsp[i]);
                moi[i] = _tempMoi; 
                deflectionsp[i] = 240;
                // get tablename and fetch row from that table
                int columnCount;
                if (pMaterial.equalsIgnoreCase("flistd") || pMaterial.equalsIgnoreCase("lvl")||pMaterial.equalsIgnoreCase("std")||pMaterial.equalsIgnoreCase("psl")){
                    columnCount = 5;
                }else{
                    columnCount = 4;
                }
                String query;
                if (pMaterial.equalsIgnoreCase("steel")) {
                    query = getStatement("steelt",moi[i]);
                }else {
                    query = getStatement(tableName[i], moi[i]);
                }
                try{
                    String _array1 [] = sqlHandler.executeQueryRow(query,columnCount);
                    _description = _array1[0];
                    actSx[i] = Double.parseDouble(_array1[1]);
                    moi[i] = Double.parseDouble(_array1[2]);
                    weight[i] = Utility.getTwoDecimal(Double.parseDouble(_array1[3]));
                    if (pMaterial.equalsIgnoreCase("woodIShape")) {
                        setWoodIShapeValues(_array1, i);
                    }
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }else{
                if (pMaterial.equalsIgnoreCase("woodIShape")) {
                    setWoodIShapeValues(_array, i);
                }
                _description = _array[0];
                actSx[i] = Double.parseDouble(_array[1]);
                weight[i] = Utility.getTwoDecimal(Double.parseDouble(_array[3]));
            }
            fbx[i] = Utility.getTwoDecimal((m*12)/actSx[i]);
            totalweight[i] = Utility.getTwoDecimal(weight[i]*span);
            setLogDebug("_description:"+_description+"-fbx:"+ fbx[i]+ "-i"+i +"-deflectionsp:"+deflectionsp[i]+"-deflection[i]:"+deflection[i]+"-moe:"+moe+"-moi[i]:"+moi[i]+"-load:"+load+"-span:"+span);
            // add elements to Arraylist
            if (pMaterial.equalsIgnoreCase("flistd")){
                tempList.add(new TempClass(_description,actSx[i],moi[i],weight[i],totalweight[i],deflection[i],deflectionsp[i],fbx[i],_array[4],0));
            }else if (pMaterial.equalsIgnoreCase("woodIShape")) {
                tempList.add(new TempClass(_description,actSx[i],moi[i],weight[i],totalweight[i],deflection[i],deflectionsp[i],fbx[i],depth[i],tWeb[i],a[i],b[i],flangemat[i]));
            }else if (pMaterial.equalsIgnoreCase("lvl")||pMaterial.equalsIgnoreCase("std")||pMaterial.equalsIgnoreCase("psl")){
                float _descId = new Float(_array[4]).floatValue();
                tempList.add(new TempClass(_description,actSx[i],moi[i],weight[i],totalweight[i],deflection[i],deflectionsp[i],fbx[i],null,_descId));
            }else tempList.add(new TempClass(_description,actSx[i],moi[i],weight[i],totalweight[i],deflection[i],deflectionsp[i],fbx[i],null, 0));
        }
        if (tempList.size() > 1) {
            // sort ArrayList order by TotalWeight low to high
            Object _tempClassj , _tempClassj1;
            for (int first=0; first<tempList.size(); first++) {
                for (int j1=0; j1<tempList.size()-1; j1++) {
                    if ( ((TempClass)tempList.get(j1)).getTotalWeight() > ((TempClass)tempList.get(j1+1)).getTotalWeight() ) {
                        _tempClassj = tempList.get(j1);
                        _tempClassj1 = tempList.get(j1+1);
                        tempList.set(j1 ,_tempClassj1);
                        tempList.set(j1+1 , _tempClassj);
                    }
                }
            }
            for (int first=0; first<tempList.size(); first++) {
                TempClass _tempClass = (TempClass)tempList.get(first);
                System.out.println("desc:"+ _tempClass.getVal()+"-def:"+_tempClass.getDeflection());
            }
            if (pMaterial.equalsIgnoreCase("lvl")||pMaterial.equalsIgnoreCase("std")||pMaterial.equalsIgnoreCase("psl")){
                //if two rows has same DescId than remove row with more weight
                for (int first=0; first<tempList.size(); first++) {
                    for (int j1=0; j1<tempList.size(); j1++) {
                        if ((j1!= first) && (((TempClass)tempList.get(first)).getDescId() == ((TempClass)tempList.get(j1)).getDescId())) {
                            setLogDebug("removed for weight :"+((TempClass)tempList.get(j1)).getTotalWeight());
                            setLogDebug("removed for descId :"+((TempClass)tempList.get(j1)).getDescId());
                            tempList.remove(j1);
                            j1 -=1;
                        }
                    }
                }
            }
        }
            for (int first=0; first<tempList.size(); first++) {
                TempClass _tempClass = (TempClass)tempList.get(first);
                System.out.println("sec desc:"+ _tempClass.getVal()+"-def:"+_tempClass.getDeflection());
            }
        design.setMoi(moi);
        design.setWeight(weight);
        design.setDeflection(deflection);
        design.setDeflectionsp(deflectionsp);
        design.setFbx(fbx);
        design.setTotalWeight(totalweight);
        design.setResultArray(resultArray);
        design.setTempArray(tempList);
    }

    public double calcDefelection(double pI){
        return Utility.getTwoDecimal((5*load*(span*span*span*span)*1728)/(384*moe*pI));
    }

    public int calcDefelectionSp(double pDefelection){
        return new Double(1/(pDefelection/(span*12))).intValue();
    }

    public abstract boolean processMethod(HttpServletRequest pReq , HttpServletResponse pRes) throws Exception;
}
