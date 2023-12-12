package com.newbeams.bean;

public class WorkOrder{
	
    private String mRespondedBy;
    private String mResponseDate;
  //  private String mQuote;
    private String mEstimatedServiceFee;
    private String mRetainer;
    private String mDateReceived;
    private String mAmountType;
  //  private String mPayment;
    private String mCardNumber;
    private String mExpirationDate;
    private String mBillingHouseNumber;
    private String mBillingStreet;
    private String mBillingCity;
    private String mBillingState;
    private String mBillingZip;
    private String mClientProceedDate;
    private String mOtherPromises;
    private String mFeeAcceptedFlag;
    
    private LeadForm mLeadForm;    
      
    public void setLeadForm(LeadForm pLeadForm) { mLeadForm = pLeadForm; }
    public LeadForm getLeadForm() { if (mLeadForm == null) mLeadForm = new LeadForm(); return mLeadForm; }
    
        
    public void setRespondedBy(String pRespondedBy) { mRespondedBy = pRespondedBy; }
    public String getRespondedBy() { if (mRespondedBy == null) mRespondedBy = ""; return mRespondedBy.trim(); } 
    
    public void setResponseDate(String pResponseDate) { mResponseDate = pResponseDate; }
    public String getResponseDate() { if (mResponseDate == null) mResponseDate = ""; return mResponseDate.trim(); } 

  //  public void setQuote(String pQuote) { mQuote = pQuote; }
  //  public String getQuote() { if (mQuote == null) mQuote = ""; return mQuote; } 
    
    public void setEstimatedServiceFee(String pEstimatedServiceFee) { mEstimatedServiceFee = pEstimatedServiceFee; }
    public String getEstimatedServiceFee() { if (mEstimatedServiceFee == null) mEstimatedServiceFee = ""; return mEstimatedServiceFee.trim(); } 

    public void setRetainer(String pRetainer) { mRetainer = pRetainer; }
    public String getRetainer() { if (mRetainer == null) mRetainer = ""; return mRetainer.trim(); } 
    
    public void setDateReceived(String pDateReceived) { mDateReceived = pDateReceived; }
    public String getDateReceived() { if (mDateReceived == null) mDateReceived = ""; return mDateReceived.trim(); }

    public void setAmountType(String pAmountType) { mAmountType = pAmountType; }
    public String getAmountType() { if (mAmountType == null) mAmountType = ""; return mAmountType.trim(); } 

  //  public void setPayment(String pPayment) { mPayment = pPayment; }
  //  public String getPayment() { if (mPayment == null) mPayment = ""; return mPayment; } 

    public void setCardNumber(String pCardNumber) { mCardNumber = pCardNumber; }
    public String getCardNumber() { if (mCardNumber == null) mCardNumber = ""; return mCardNumber.trim(); } 

    public void setExpirationDate(String pExpirationDate) { mExpirationDate = pExpirationDate; }
    public String getExpirationDate() { if (mExpirationDate == null) mExpirationDate = ""; return mExpirationDate.trim(); } 

    public void setBillingHouseNumber(String pBillingHouseNumber) { mBillingHouseNumber = pBillingHouseNumber; }
    public String getBillingHouseNumber() { if (mBillingHouseNumber == null) mBillingHouseNumber = ""; return mBillingHouseNumber.trim(); } 

    public void setBillingStreet(String pBillingStreet) { mBillingStreet = pBillingStreet; }
    public String getBillingStreet() { if (mBillingStreet == null) mBillingStreet = ""; return mBillingStreet.trim(); } 
    
    public void setBillingCity(String pBillingCity) { mBillingCity = pBillingCity; }
    public String getBillingCity() { if (mBillingCity == null) mBillingCity = ""; return mBillingCity.trim(); } 

    public void setBillingState(String pBillingState) { mBillingState = pBillingState; }
    public String getBillingState() { if (mBillingState == null) mBillingState = ""; return mBillingState.trim(); } 

    public void setBillingZip(String pBillingZip) { mBillingZip = pBillingZip; }
    public String getBillingZip() { if (mBillingZip == null) mBillingZip = ""; return mBillingZip.trim(); } 

    public void setClientProceedDate(String pClientProceedDate) { mClientProceedDate = pClientProceedDate; }
    public String getClientProceedDate() { if (mClientProceedDate == null) mClientProceedDate = ""; return mClientProceedDate.trim(); } 

    public void setOtherPromises(String pOtherPromises) { mOtherPromises = pOtherPromises; }
    public String getOtherPromises() { if (mOtherPromises == null) mOtherPromises = ""; return mOtherPromises.trim(); } 

    public void setFeeAcceptedFlag(String pFeeAcceptedFlag) { mFeeAcceptedFlag = pFeeAcceptedFlag; }
    public String getFeeAcceptedFlag() { if (mFeeAcceptedFlag == null) mFeeAcceptedFlag = ""; return mFeeAcceptedFlag.trim(); }    
}