package com.util;

public class Project{
    String projectName, previousName, street, city, state, zip;

    public void setProjectName(String pProjectName){ projectName = pProjectName.trim(); }
    public String getProjectName(){ if (projectName == null) projectName=""; return projectName; }

    public void setStreet(String pStreet){ street = pStreet.trim(); }
    public String getStreet(){ if (street == null) street=""; return street; }

    public void setCity(String pCity){ city = pCity.trim(); }
    public String getCity(){ if (city == null) city=""; return city; }

    public void setState(String pState){ state = pState.trim(); }
    public String getState(){ if (state == null) state=""; return state; }

    public void setZip(String pZip){ zip = pZip.trim(); }
    public String getZip(){ if (zip == null) zip=""; return zip; }

    public void setPreviousName(String pPreviousName){ previousName = pPreviousName.trim(); }
    public String getPreviousName(){ return previousName; }
}
