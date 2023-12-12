package com.util;

import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class GenericFormHandler extends GenericService{
    Vector errorVector = new Vector();
    boolean errorFound = false;
    protected String sucessURL;
    protected String errorURL;
    protected String cancelURL;
    protected String methodName;

    public abstract boolean processMethod(HttpServletRequest pReq , HttpServletResponse pRes) throws Exception;

    public Object [] getFormExceptions(){
        errorFound = false;
        if (errorVector.size() >= 1) {
            Object [] _errArray = new Object[errorVector.size()];
            _errArray = errorVector.toArray();
            errorVector.clear();
            return _errArray;
        }
        return null;
    }

    public void addFormException(Object pException){
        errorVector.addElement(pException);
        errorFound = true;
    }

    public void resetFormExceptions(){ errorVector.clear(); }
    public boolean getErrorFound() { return errorFound; }

    public void setSucessURL(String pSucessURL) { sucessURL = pSucessURL; }
    public String getSucessURL() { return sucessURL; }

    public void setErrorURL(String pErrorURL) { errorURL = pErrorURL; }
    public String getErrorURL() { return errorURL; }

    public void setCancelURL(String pCancelURL) { cancelURL = pCancelURL; }
    public String getCancelURL() { return cancelURL; }

    public void setMethodName(String pMethodName){ methodName = pMethodName; }
    public String getMethodName() { return methodName; }
}

