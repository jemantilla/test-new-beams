package com.util;

public class TempClass{
    private String val, lookUp, flangemat;
    private double actsx, moi, weight, totalWeight, deflection, fbx;
    private float depth,tWeb,a,b, descId;
    private int deflectionsp;

    public TempClass(){}

    public TempClass(String pVal,double pActsx,double pMoi,double pWeight,double pTotalWeight,double pDeflection,int pDeflectionsp,double pFbx,String pLookUp, float pDescId){
        val = pVal;
        actsx = pActsx;
        moi = pMoi;
        weight = pWeight;
        totalWeight = pTotalWeight;
        deflection = pDeflection;
        deflectionsp = pDeflectionsp;
        fbx = pFbx;
        lookUp = pLookUp;
        descId = pDescId;
    }
    public TempClass(String pVal,double pActsx ,double pMoi ,double pWeight, double pTotalWeight ,double pDeflection, int pDeflectionsp,double pFbx,float pDepth,float pTWeb,float pA,float pB,String pFlangemat){
        this(pVal,pActsx,pMoi,pWeight,pTotalWeight,pDeflection,pDeflectionsp,pFbx,null,0);
        depth = pDepth;
        tWeb = pTWeb;
        a = pA;
        b = pB;
        flangemat = pFlangemat;
    }

    public void setDescId(float pDescId){ descId = pDescId; }
    public float getDescId(){ return descId; }

    public void setDepth(float pDepth) { depth = pDepth; }
    public float getDepth() { return depth; }

    public void setTWeb(float pTWeb) { tWeb = pTWeb; }
    public float getTWeb() { return tWeb; }

    public void setA(float pA) { a = pA; }
    public float getA() { return a; }

    public void setB(float pB) { b = pB; }
    public float getB() { return b; }

    public void setFlangemat(String pFlangemat) { flangemat = pFlangemat; }
    public String getFlangemat() { return flangemat; }

    public void setVal(String pVal) { val = pVal; }
    public String getVal() { return val; }

    public void setActsx(double pActsx) { actsx = pActsx; }
    public double getActsx() { return actsx; }

    public void setMoi(double pMoi) { moi = pMoi; }
    public double getMoi() { return moi; }

    public void setWeight(double pWeight) { weight = pWeight; }
    public double getWeight() { return weight; }

    public void setTotalWeight(double pTotalWeight) { totalWeight = pTotalWeight; }
    public double getTotalWeight() { return totalWeight; }

    public void setDeflection(double pDeflection) { deflection = pDeflection; }
    public double getDeflection() { return deflection; }

    public void setDeflectionsp(int pDeflectionsp) { deflectionsp = pDeflectionsp; }
    public int getDeflectionsp() { return deflectionsp; }

    public void setFbx(double pFbx) { fbx = pFbx; }
    public double getFbx() { return fbx; }

    public void setLookUp(String pLookUp){ lookUp = pLookUp; }
    public String getLookUp(){ return lookUp; }
}