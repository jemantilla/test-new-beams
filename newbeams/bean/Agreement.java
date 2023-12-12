package com.newbeams.bean;

import java.util.Date;

public class Agreement{
    private String mProjectNo;
    private String mProjectName;
    private String mSpecialConditions;
    private Date mAgreementDate;
    private String mFeeArrangement;
    
    private WorkOrder mWorkOrder;
    
    public void setWorkOrder(WorkOrder pWorkOrder) { mWorkOrder = pWorkOrder; }
    public WorkOrder getWorkOrder() {if (mWorkOrder == null) mWorkOrder = new WorkOrder(); return mWorkOrder; } 
    
    public void setAgreementDate(Date pAgreementDate) { mAgreementDate = pAgreementDate; }
    public Date getAgreementDate() { return mAgreementDate; }
  
    public void setProjectNo(String pProjectNo) { mProjectNo = pProjectNo; }
    public String getProjectNo() { if (mProjectNo == null) mProjectNo = ""; return mProjectNo.trim(); }
    
    public void setProjectName(String pProjectName) { mProjectName = pProjectName; }
    public String getProjectName() { if (mProjectName == null) mProjectName = ""; return mProjectName.trim(); } 
    
    public void setFeeArrangement(String pFeeArrangement) { mFeeArrangement = pFeeArrangement;}
    public String getFeeArrangement() { if (mFeeArrangement == null) mFeeArrangement = ""; return mFeeArrangement.trim(); }
           
    public void setSpecialConditions(String pSpecialConditions) { mSpecialConditions = pSpecialConditions; }
    public String getSpecialConditions() { if (mSpecialConditions == null) mSpecialConditions = ""; return mSpecialConditions.trim(); } 
    
}
