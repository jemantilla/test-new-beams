package com.newbeams.bean;

import java.util.Date;

public class LeadForm{
	private int mID;
    private Date mCallDate;
    private String mIntakeBy;
    private String mForWhichCompany;
    private String mCallerFirstName;
    private String mCallerLastName;
    private String mCallerCompanyName;
    private String mPhone;
    private String mFacsimile;
    private String mMobile;
    private String mEmail;
    private String mPropertyAddress;
    private String mHouse;
    private String mStreet;
    private String mCity;
    private String mState;
    private String mZip;
    private String mTaxId;
    private String mNotes;
    private String mNeed;
    private String mService;
    private String mScope;
    private String mPropertySize;
    private String mShape;
    private String mFormerSurvey;
    private String mFromWhom;
    private String mSurveyDate;
    private String mAttention;
    
    
    public void setID(int pID) { mID = pID; }
    public int getID(){ return mID; }

    public void setCallDate(Date pCallDate) { mCallDate = pCallDate; }
    public Date getCallDate() { return mCallDate; }
    
    public void setIntakeBy(String pIntakeBy) { mIntakeBy = pIntakeBy; }
    public String getIntakeBy() { if (mIntakeBy == null) mIntakeBy = ""; return mIntakeBy.trim();}
    
    public void setForWhichCompany(String pForWhichCompany) { mForWhichCompany = pForWhichCompany; }
    public String getForWhichCompany() { if (mForWhichCompany == null) mForWhichCompany = ""; return mForWhichCompany.trim();}

    public void setCallerFirstName(String pCallerFirstName) { mCallerFirstName = pCallerFirstName; }
    public String getCallerFirstName() { if (mCallerFirstName == null) mCallerFirstName = ""; return mCallerFirstName.trim(); }

    public void setCallerLastName(String pCallerLastName) { mCallerLastName = pCallerLastName; }
    public String getCallerLastName() { if (mCallerLastName == null) mCallerLastName = ""; return mCallerLastName.trim(); }

    public void setCallerCompanyName(String pCallerCompanyName) { mCallerCompanyName = pCallerCompanyName; }
    public String getCallerCompanyName() { if (mCallerCompanyName == null) mCallerCompanyName = ""; return mCallerCompanyName.trim(); }

    public void setPhone(String pPhone) { mPhone = pPhone; }
    public String getPhone() { if (mPhone == null) mPhone = ""; return mPhone.trim(); }

    public void setFacsimile(String pFacsimile) { mFacsimile = pFacsimile; }
    public String getFacsimile() { if (mFacsimile == null) mFacsimile = ""; return mFacsimile.trim(); }

    public void setMobile(String pMobile) { mMobile = pMobile; }
    public String getMobile() { if (mMobile == null) mMobile = ""; return mMobile.trim(); }

    public void setEmail(String pEmail) { mEmail = pEmail; }
    public String getEmail() { if (mEmail == null) mEmail = ""; return mEmail.trim(); }

  //  public void setPropertyAddress(String pPropertyAddress) { mPropertyAddress = pPropertyAddress.trim(); }
  //  public String getPropertyAddress() { if (mPropertyAddress == null) mPropertyAddress = ""; return mPropertyAddress.trim(); }

    public void setHouse(String pHouse) { mHouse = pHouse; }
    public String getHouse() { if (mHouse == null) mHouse = ""; return mHouse.trim(); }

    public void setStreet(String pStreet) { mStreet = pStreet; }
    public String getStreet() { if (mStreet == null) mStreet = ""; return mStreet.trim(); }
 
    public void setCity(String pCity) { mCity = pCity; }
    public String getCity() { if (mCity == null) mCity = ""; return mCity.trim(); }

    public void setState(String pState) { mState = pState; }
    public String getState() { if (mState == null) mState = ""; return mState.trim(); }

    public void setZip(String pZip) { mZip = pZip; }
    public String getZip() { if (mZip == null) mZip = ""; return mZip.trim(); }

    public void setTaxId(String pTaxId) { mTaxId = pTaxId; }
    public String getTaxId() { if (mTaxId == null) mTaxId = ""; return mTaxId.trim(); }

  //  public void setNotes(String pNotes) { mNotes = pNotes; }
  //  public String getNotes() { if (mNotes == null) mNotes = ""; return mNotes.trim(); }
 
    public void setNeed(String pNeed) { mNeed = pNeed; }
    public String getNeed() { if (mNeed == null) mNeed = ""; return mNeed.trim(); }
    
    public void setService(String pService) { mService = pService; }
    public String getService() { if (mService == null) mService = ""; return mService.trim(); }

    public void setScope(String pScope) { mScope = pScope; }
    public String getScope() { if (mScope == null) mScope = ""; return mScope.trim(); }

    public void setPropertySize(String pPropertySize) { mPropertySize = pPropertySize; }
    public String getPropertySize() { if (mPropertySize == null) mPropertySize = ""; return mPropertySize.trim(); }

    public void setShape(String pShape) { mShape = pShape; }
    public String getShape() { if (mShape == null) mShape = ""; return mShape.trim(); } 

    public void setFormerSurvey(String pFormerSurvey) { mFormerSurvey = pFormerSurvey; }
    public String getFormerSurvey() { if (mFormerSurvey == null) mFormerSurvey = ""; return mFormerSurvey.trim(); } 

    public void setFromWhom(String pFromWhom) { mFromWhom = pFromWhom; }
    public String getFromWhom() { if (mFromWhom == null) mFromWhom = ""; return mFromWhom.trim(); } 

    public void setSurveyDate(String pSurveyDate) { mSurveyDate = pSurveyDate; }
    public String getSurveyDate() { if (mSurveyDate == null) mSurveyDate = ""; return mSurveyDate.trim(); }
    
    public void setAttention(String pAttention) { mAttention = pAttention; }
    public String getAttention() { if (mAttention == null) mAttention = ""; return mAttention.trim();}
    
    

}
