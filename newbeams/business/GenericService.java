package com.newbeams.business;

public class GenericService{
    protected  boolean loggingError = true;
    protected  boolean loggingDebug = true;
    protected  boolean loggingInfo = true;
    protected  boolean loggingWarning = true;
    ThreadImpl threadImpl = ThreadImpl.getInstance();

    public boolean isLoggingError() { return loggingError; }
    public void setLoggingError (boolean pLoggingError)  { loggingError = pLoggingError; }
    public void setLogError (String pMessage){
        if (isLoggingError()) { writeMessage("Error: " , pMessage); }
    }

    public boolean isLoggingDebug() { return loggingDebug; }
    public void setLoggingDebug (boolean pLoggingDebug)  { loggingDebug = pLoggingDebug; }
    public void setLogDebug (String pMessage){
        if (isLoggingDebug()) { writeMessage("Debug: " , pMessage); }
    }

    public boolean isLoggingInfo() { return loggingInfo; }
    public void setLoggingInfo (boolean pLoggingInfo)  { loggingInfo = pLoggingInfo; }
    public void setLogInfo (String pMessage){
        if (isLoggingInfo()) { writeMessage("Info: " , pMessage); }
    }

    public boolean isLoggingWarning() { return loggingWarning; }
    public void setLoggingWarning (boolean pLoggingWarning)  { loggingWarning = pLoggingWarning; }
    public void setLogWarning (String pMessage){
        if (isLoggingWarning()) { writeMessage("Warning: " , pMessage); }
    }

    private void writeMessage(String pMessageType , String pMessage){
        System.out.println(pMessageType + threadImpl.getNow() + pMessage);
    }
}


