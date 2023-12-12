package com.util;

import java.util.ArrayList;

public class Design extends GenericService{
    private Project project = new Project();
    private DesignHelp designHelp;
    private float span;
    private String aMaterial = "steel", aBeamSize, aNumber = "1",  spaceIn="0", spaceFt,depthIn="0" ,aspaceIn="0";
    private String l1F,l1I="0",l2F,l2I="0",l3F,l3I="0",h1F,h1I="0",h2F,h2I="0",pe;
    private String material = "steel", materialDes, lookUp, beamSize, load, loadType, methodName = "BeamLoad", shape, i, number = "1", status;
    private String triFeet, triInch = "0", triWidth, materb, loadt,tl, spanf, spani = "0", storey, beamsize;
    private String roofPitch = "0",floorType;
    private double ll=30, m, sx,mRafter, mFb, moe, inertia, w, deflectCheck, deflectSpCheck, iCheck, reactionLeft, reactionRight, fbxCheck;
    private double fbx[], moi[], weight[], totalWeight[], deflection[], actSx[];
    private double mArea, mT;
    private int[] deflectionsp;
    private ArrayList resultArray = new ArrayList();
    private ArrayList tempArray = new ArrayList();
    private boolean validSx = false, roofIncluded = false;
    private ArrayList sxArray = new ArrayList();
    private ArrayList materialVector, materialCount;

    public Design(){ designHelp = new DesignHelp(this); }

    public void setDesignHelp(DesignHelp pDesignHelp){ designHelp = pDesignHelp; }
    public DesignHelp getDesignHelp() { return designHelp;}

    public void setValidSx(boolean pValidSx) { validSx = pValidSx; }
    public boolean getValidSx() { return validSx; }
    
    public void setRoofIncluded(boolean pRoofIncluded) { roofIncluded = pRoofIncluded; }
    public boolean getRoofIncluded() { return roofIncluded; }

    public void setMethodName(String pMethodName){ methodName = pMethodName; }
    public String getMethodName(){ if (methodName == null) methodName=""; return methodName; }

    public void setStatus(String pStatus){ status = pStatus; }
    public String getStatus(){ if (status == null) status=""; return status; }

    public void setSpaceIn(String pSpaceIn){ spaceIn = pSpaceIn; }
    public String getSpaceIn(){ if (spaceIn == null) spaceIn=""; return spaceIn; }

    public void setSpaceFt(String pSpaceFt){ spaceFt = pSpaceFt; }
    public String getSpaceFt(){ if (spaceFt == null) spaceFt=""; return spaceFt; }   //by sejal

    public void setDepthIn(String pDepthIn){ depthIn = pDepthIn; }
    public String getDepthIn(){ if (depthIn == null) depthIn=""; return depthIn; }

    public void setASpaceIn(String pASpaceIn){ aspaceIn = pASpaceIn; }
    public String getASpaceIn(){ if (aspaceIn == null) aspaceIn=""; return aspaceIn; }  

    public void setL1F(String pL1F){ l1F = pL1F; }
    public String getL1F(){ if (l1F == null) l1F=""; return l1F; }         //by sejal

    public void setL1I(String pL1I){ l1I = pL1I; }
    public String getL1I(){ if (l1I == null) l1I=""; return l1I; }         //by sejal       
    
    public void setL2F(String pL2F){ l2F = pL2F; }     
    public String getL2F(){ if (l2F == null) l2F=""; return l2F; }  //by sejal

    public void setL2I(String pL2I){ l2I = pL2I; }     
    public String getL2I(){ if (l2I == null) l2I=""; return l2I; }  //by sejal

    public void setL3F(String pL3F){ l3F = pL3F; }     
    public String getL3F(){ if (l3F == null) l3F=""; return l3F; }  //by sejal

    public void setL3I(String pL3I){ l3I = pL3I; }     
    public String getL3I(){ if (l3I == null) l3I=""; return l3I; }  //by sejal

    public void setH1F(String pH1F){ h1F = pH1F; }
    public String getH1F(){ if (h1F == null) h1F=""; return h1F; }         //by sejal

    public void setH1I(String pH1I){ h1I = pH1I; }
    public String getH1I(){ if (h1I == null) h1I=""; return h1I; }         //by sejal       
    
    public void setH2F(String pH2F){ h2F = pH2F; }     
    public String getH2F(){ if (h2F == null) h2F=""; return h2F; }  //by sejal

    public void setH2I(String pH2I){ h2I = pH2I; }     
    public String getH2I(){ if (h2I == null) h2I=""; return h2I; }  //by sejal   

    public void setFloorType(String pFloorType){ floorType = pFloorType; }
    public String getFloorType(){ if (floorType == null) floorType=""; return floorType; }

    public void setNumber(String pNumber){ number = pNumber; designHelp.setNumberValues(); }
    public String getNumber(){ if (number == null) number=""; return number; }

    public void setANumber(String pANumber){ aNumber = pANumber;  designHelp.setANumberValues();  }
    public String getANumber(){ if (aNumber == null) aNumber=""; return aNumber; }

    public void setMaterial(String pMaterial){ material = pMaterial; designHelp.setMaterialValues(); }
    public String getMaterial(){ if (material == null) material=""; return material; }

    public void setAMaterial(String pAMaterial){ aMaterial = pAMaterial; designHelp.setAMaterialValues(); }
    public String getAMaterial(){ if (aMaterial == null) aMaterial=""; return aMaterial; }

    public void setMaterialCount(ArrayList pMaterialCount){ materialCount = pMaterialCount; }
    public ArrayList getMaterialCount(){ return materialCount; }

    public void setMaterialVector(ArrayList pMaterialVector){ materialVector = pMaterialVector; }
    public ArrayList getMaterialVector(){ return materialVector; }

    public void setBeamSize(String pBeamSize){ beamsize = pBeamSize; }
    public String getBeamSize(){ if (beamsize == null) beamsize=""; return beamsize; }

    public void setABeamSize(String pABeamSize){ aBeamSize = pABeamSize; }
    public String getABeamSize(){ if (aBeamSize == null) aBeamSize=""; return aBeamSize; }

    public void setMaterialDes(String pMaterialDes){ materialDes = pMaterialDes; }
    public String getMaterialDes(){ if (materialDes == null) materialDes=""; return materialDes; }

    public void setLookUp(String pLookUp){ lookUp = pLookUp; }
    public String getLookUp(){ return lookUp; }

    public void setMaterb(String pMaterb){ materb = pMaterb; designHelp.setMaterBValues();}
    public String getMaterb(){ if (materb == null) materb=""; return materb; }

    public void setLoadType(String pLoadType){ loadType = pLoadType; }
    public String getLoadType(){ if (loadType == null) loadType=""; return loadType; }

    public void setTriFeet(String pTriFeet){ triFeet = pTriFeet; }
    public String getTriFeet(){ if (triFeet == null) triFeet=""; return triFeet; }

    public void setTriInch(String pTriInch){ triInch = pTriInch; }
    public String getTriInch(){ if (triInch == null) triInch=""; return triInch; }

    public void setStorey(String pStorey){ storey = pStorey; }
    public String getStorey(){ if (storey == null) storey=""; return storey; }

    public void setSpan(float pSpan){ span = pSpan; }
    public float getSpan(){ return span; }

    public void setSpanf(String pSpanf){ spanf = pSpanf; }
    public String getSpanf(){ if (spanf == null) spanf=""; return spanf; }

    public void setSpani(String pSpani){ spani = pSpani; }
    public String getSpani(){ if (spani == null) spani=""; return spani; }
    
    public void setLoad(String pLoad){ load = pLoad; }
    public String getLoad(){ if (load == null) load=""; return load; }

    public void setShape(String pShape){ shape = pShape.trim(); }
    public String getShape(){ if (shape == null) shape=""; return shape; }

    public void setI(String pI){ i = pI.trim(); }
    public String getI(){ if (i == null) i=""; return i; }

    public void setRoofPitch(String pRoofPitch){ roofPitch = pRoofPitch.trim(); }
    public String getRoofPitch(){ if (roofPitch == null) roofPitch=""; return roofPitch; }

    public void setM(double pM) { m = pM; }
    public double getM() { return m; }

    public void setSx(double pSx) { sx = pSx; }
    public double getSx() { return sx; }

    public void setRafter(double pRafter) { mRafter = pRafter; }
    public double getRafter() { return mRafter; }            //sejal              
    
    public void setW(double pW) { w = pW; }
    public double getW() { return w; }

    public void setDeflectCheck(double pDeflectCheck) { deflectCheck = Math.floor((pDeflectCheck*100)+0.5)/100; }
    public double getDeflectCheck() { return deflectCheck; }

    public void setICheck(double pICheck) { iCheck = Math.floor((pICheck*100)+0.5)/100; }
    public double getICheck() { return iCheck; }

    public void setDeflectSpCheck(double pDeflectSpCheck) { deflectSpCheck = Math.floor((pDeflectSpCheck*100)+0.5)/100; }
    public double getDeflectSpCheck() { return deflectSpCheck; }

    public int getDeflectSpCheckInt() { return new Double(deflectSpCheck).intValue(); }

    public void setInertia(double pInertia) { inertia = pInertia; }
    public double getInertia() { return inertia; }

    public void setFbxCheck(double pFbxCheck) { fbxCheck = pFbxCheck; }
    public double getFbxCheck() { return fbxCheck; }

    public void setReactionLeft(double pReactionLeft) { reactionLeft = pReactionLeft; }
    public double getReactionLeft() { return Utility.getTwoDecimal((Double.parseDouble(load)*span)/2); }

    public void setReactionRight(double pReactionRight) { reactionRight = pReactionRight; }
    public double getReactionRight() { return Utility.getTwoDecimal((Double.parseDouble(load)*span)/2); }

    public void setFbx(double[] pFbx) { fbx = pFbx; }
    public double[] getFbx() { return fbx; }

    public void setMoi(double[] pMoi) { moi = pMoi; }
    public double[] getMoi() { return moi; }

    public void setWeight(double[] pWeight) { weight = pWeight; }
    public double[] getWeight() { return weight; }

    public void setTotalWeight(double[] pTotalWeight) { totalWeight = pTotalWeight; }
    public double[] getTotalWeight() { return totalWeight; }

    public void setDeflection(double[] pDeflection) { deflection = pDeflection; }
    public double[] getDeflection() { return deflection; }

    public void setDeflectionsp(int[] pDeflectionsp) { deflectionsp = pDeflectionsp; }
    public int[] getDeflectionsp() { return deflectionsp; }

    public void setResultArray(ArrayList pResultArray) { resultArray = pResultArray; }
    public ArrayList getResultArray() { return resultArray; }

    public void setTempArray(ArrayList pTempArray) { tempArray = pTempArray; }
    public ArrayList getTempArray() { return tempArray; }

    public void setSxArray(ArrayList pSxArray) { sxArray = pSxArray; }
    public ArrayList getSxArray() { return sxArray; }

    public void setArea(double pArea){ mArea = pArea; }
    public double getArea(){ return mArea; }

    public void setFb(double pFb) { mFb = pFb; }
    public double getFb(){ return mFb; }

    public void setT(double pT) { mT = pT; }
    public double getT(){ return mT; }
}
