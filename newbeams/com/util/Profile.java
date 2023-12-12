package com.util;

public class Profile{
    private boolean validUser = false;
    private String userName = "";
    private String passWord = "" , newPassWord = "", oldPassWord = "";
    private String passWord2 = "" , newPassWord2 = "";
    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private String address1 = "";
    private String city = "";
    private String state = "";
    private String zip = "";
    private String workPhoneArea = "";
    private String workPhoneNum = "";
    private String cellPhoneArea = "";
    private String cellPhoneNum = "";
    private boolean keepCookie = false;
    private boolean agree = false;

    public void setValidUser(boolean pValidUser) {validUser = pValidUser; System.out.println("  At Profile- setValidUser :"+ validUser); }
    public boolean getValidUser() { return validUser; }

    public void setAgree(boolean pAgree) {agree = pAgree; }
    public boolean getAgree() { return agree; }

    public void setKeepCookie(boolean pKeepCookie) {keepCookie = pKeepCookie; }
    public boolean getKeepCookie() { return keepCookie; }

    public void setUserName(String pUserName) {userName = pUserName.trim().toLowerCase(); }
    public String getUserName() { if (userName == null)  userName = ""; return userName; }

    public void setPassWord(String pPassWord) { passWord = pPassWord.trim(); }
    public String getPassWord() { return passWord; }

    public void setPassWord2(String pPassWord2) { passWord2 = pPassWord2.trim(); }
    public String getPassWord2() { return passWord2; }

    public void setOldPassWord(String pOldPassWord) { oldPassWord = pOldPassWord.trim(); }
    public String getOldPassWord() { return oldPassWord; }

    public void setNewPassWord(String pNewPassWord) { newPassWord = pNewPassWord.trim(); }
    public String getNewPassWord() { return newPassWord; }

    public void setNewPassWord2(String pNewPassWord2) { newPassWord2 = pNewPassWord2.trim(); }
    public String getNewPassWord2() { return newPassWord2; }

    public void setFirstName(String pFirstName) { firstName = pFirstName.trim(); }
    public String getFirstName() { if (firstName == null)  firstName = ""; return firstName; }

    public void setLastName(String pLastName) { lastName = pLastName.trim(); }
    public String getLastName() { if (lastName == null)  lastName = ""; return lastName; }

    public void setEmail(String pEmail) { email = pEmail.trim(); }
    public String getEmail() { if (email == null)  email = ""; return email; }

    public void setAddress1(String pAddress1) { address1 = pAddress1.trim(); }
    public String getAddress1() { if (address1 == null)  address1 = ""; return address1; }

    public void setCity(String pCity) { city = pCity.trim(); }
    public String getCity() { if (city == null)  city = ""; return city; }

    public void setState(String pState) { state = pState.trim(); }
    public String getState() { if (state == null)  state = ""; return state; }

    public void setZip(String pZip) { zip = pZip.trim(); }
    public String getZip() { if (zip == null)  zip = ""; return zip; }

    public void setWorkPhoneArea(String pWorkPhoneArea) { workPhoneArea = pWorkPhoneArea.trim(); }
    public String getWorkPhoneArea() { if (workPhoneArea == null)  workPhoneArea = ""; return workPhoneArea; }

    public void setWorkPhoneNum(String pWorkPhoneNum) { workPhoneNum = pWorkPhoneNum.trim(); }
    public String getWorkPhoneNum() { if (workPhoneArea == null)  workPhoneArea = ""; return workPhoneNum; }

    public void setCellPhoneArea(String pCellPhoneArea) { cellPhoneArea = pCellPhoneArea.trim(); }
    public String getCellPhoneArea() { if (cellPhoneArea == null)  cellPhoneArea = ""; return cellPhoneArea; }

    public void setCellPhoneNum(String pCellPhoneNum) { cellPhoneNum = pCellPhoneNum.trim(); }
    public String getCellPhoneNum() { if (cellPhoneNum == null)  cellPhoneNum = ""; return cellPhoneNum; }
}
