package com.util;

import java.sql.Date;

public class ProjectDetail extends GenericService{
    private String projectName, status, beamSize, material;
    private double mSpan, load, deflection;
    private float mTension, mCSArea;
    private String mDate, mRafterSize;

    public void setProjectName(String pProjectName){ projectName = pProjectName; }
    public String getProjectName(){ if (projectName == null) projectName=""; return projectName; }

    public void setStatus(String pStatus){ status = pStatus; }
    public String getStatus(){ if (status == null) status=""; return status; }

    public void setBeamSize(String pBeamSize){ beamSize = pBeamSize; }
    public String getBeamSize(){ if (beamSize == null) beamSize=""; return beamSize; }

    public void setSpan(double pSpan){ mSpan = pSpan; }
    public double getSpan(){ return mSpan; }

    public void setLoad(double pLoad){ load = pLoad; }
    public double getLoad(){ return load; }

    public void setDate(String pDate){ mDate = pDate; }
    public String getDate(){ return mDate; }

    public void setDeflection(double pDeflection){ deflection = pDeflection; }
    public double getDeflection(){ return deflection; }

    public void setMaterial(String pMaterial){ material = pMaterial; }
    public String getMaterial(){ return material; }

    public void setTension(float pTension){ mTension = pTension; }
    public float getTension(){ return mTension; }

    public void setCSArea(float pCSArea){ mCSArea = pCSArea; }
    public float getCSArea(){ return mCSArea; }

    public void setRafterSize(String pRafterSize){ mRafterSize = pRafterSize; }
    public String getRafterSize(){ return mRafterSize; }
}
