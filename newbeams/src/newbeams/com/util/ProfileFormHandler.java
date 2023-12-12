package com.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProfileFormHandler extends GenericFormHandler {
    Profile profile = new Profile();
    SQLHandler sqlHandler = new SQLHandler();
    EmailSender emailSender = new EmailSender();

    public Profile getProfile(){ return profile; }
    public void setProfile(Profile pProfile){ profile = pProfile; }

    public boolean processMethod(HttpServletRequest pReq , HttpServletResponse pRes) throws Exception {
        if (methodName.equalsIgnoreCase("signup")) {
            return signUp(pReq, pRes);
        }else if (methodName.equalsIgnoreCase("update")) {
            return updateProfile(pReq, pRes);
        }else if (methodName.equalsIgnoreCase("changePassword")) {
            return changePassword(pReq, pRes);
        }else if (methodName.equalsIgnoreCase("logout")) {
            return logOut(pReq, pRes);
        }else if (methodName.equalsIgnoreCase("login")) {
            return logIn(pReq, pRes);
        }else if (methodName.equalsIgnoreCase("agree")) {
            return agree(pReq, pRes);
        }else if (methodName.equalsIgnoreCase("forgotpassword")) {
            return forgotPassword(pReq, pRes);
        }else {
            return false;
        }
    }
    boolean changePassword(HttpServletRequest pReq , HttpServletResponse pRes) throws Exception {
        if (!(profile.getOldPassWord().equals(profile.getPassWord()))) {
            addFormException(Messages.INVALID_OLDPASSWORD);
            return false;
        }
        validateChangePassword();
        if (!errorFound) validateChangePassword2();
        if (errorFound) { return false; }
        else{
            if (profile.getPassWord().equals(profile.getNewPassWord())) {
                return true;
            }
            try{
                String _qryStr = "update profile set password = '" + profile.getNewPassWord() +
                    "' where UserName = '" + profile.getUserName() +"'";
                sqlHandler.executeUpdate(_qryStr);
            }catch (Exception e){
                addFormException("unknwon error");
                setLogError(e.getMessage());
            }
        }
        if (errorFound){
            return false;
        }else{
            profile.setPassWord(profile.getNewPassWord());
            return true;
        }
    }

    boolean agree(HttpServletRequest pReq , HttpServletResponse pRes) throws Exception {
        if (profile.getAgree()) {
            this.clearProfile();
            return true;
        }else return false;
    }

    boolean forgotPassword(HttpServletRequest pReq , HttpServletResponse pRes) throws Exception {
        String _email = pReq.getParameter("email");
        if (emailSender.validateEmailAdddress(_email)){
            try{
                Object [] _args = new Object[1];
                _args[0] = "'"+ _email +"'";
                ArrayList _array = sqlHandler.executeQuery(Messages.getMsgStr(Configuration.FORGOT_PASSWORD_SQL,_args),2);
                if (_array != null && _array.size() > 0) {
                    String[] _rs = (String[]) _array.get(0);
                    String _msgStr = "your UserName is "+_rs[0] + " and password is "+_rs[1];
                    setLogDebug("msg:"+_msgStr);
                    String [] _emails = {_email};
                    emailSender.sendEmail(_emails , _msgStr);
                }else {
                    addFormException("Problem");
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        if (_email != null && _email.length() > 6) {
            // validate from database
            // send email to user with username , password
            return true;
        }else {
            addFormException(Messages.INVALID_EMAIL);
            return false;
        }
    }

    boolean signUp(HttpServletRequest pReq , HttpServletResponse pRes) throws Exception {
        validateFormFields();
        validateAgree();
        validateLogInFields();
        if (!errorFound) validateConfirmPassword();
        if (errorFound) { return false; }
        else{
            Object [] _args = new Object[1];
            _args[0] = "'"+ profile.getUserName() + "','"+ profile.getPassWord() + "','"+ profile.getFirstName() + "'," +
                "'"+ profile.getLastName() + "','"+ profile.getEmail() + "','"+ profile.getAddress1() + "'," +
                "'"+ profile.getCity() + "','"+ profile.getState() + "','"+ profile.getZip() + "'," +
                "'"+ profile.getWorkPhoneNum() + "','"+ profile.getCellPhoneNum() + "'";
            try{
                String _qryStr = Messages.getMsgStr(Configuration.SIGN_UP_SQL , _args);
                sqlHandler.executeUpdate(_qryStr);
                profile.setValidUser(true);
            }catch (Exception e){
                addFormException("unknwon error");
                setLogError(e.getMessage());
            }
        }
        if (errorFound){
            return false;
        }else{
            profile.setAgree(false);
            return true;
        }
    }

    boolean updateProfile(HttpServletRequest pReq , HttpServletResponse pRes) throws Exception {
        validateFormFields();
        if (errorFound) { return false; }
        else {
            String _qryStr = "update profile set FirstName= '"+ profile.getFirstName() + 
                "', LastName= '"+ profile.getLastName() +"', Email= '"+ profile.getEmail() +
                "', Address1= '"+ profile.getAddress1() +"', City= '"+ profile.getCity() +
                "', State= '"+ profile.getState() +"', Zip= '"+ profile.getZip() +
                "', WorkPhoneNum= '"+ profile.getWorkPhoneNum() + "', CellPhoneNum= '"+ profile.getCellPhoneNum() +
                "' where UserName = '" + profile.getUserName() +"'";
            try{
                sqlHandler.executeUpdate(_qryStr);
            }catch (Exception e){
                addFormException("unknwon error");
                setLogError(e.getMessage());
            }
        }
        if (errorFound) return false;
        else return true;
    }

    boolean logOut(HttpServletRequest pReq , HttpServletResponse pRes) throws Exception {
        clearProfile();
        if (errorFound) { return false; }
        else return true;
    }

    boolean logIn(HttpServletRequest pReq , HttpServletResponse pRes) throws Exception {
        validateLogInFields();
        if (!errorFound) {
            try{
                /*
                Object [] _args = new Object[2];
                _args[0] = "'"+ profile.getUserName() + "'";
                _args[1] = "'"+ profile.getPassWord() + "'";String _qryStr = Messages.getMsgStr(Configuration.LOG_IN_SQL , _args);
                ArrayList _array = sqlHandler.executeQuery(_qryStr , 11);
                if (_array == null || _array.size() < 1) {
                    addFormException(Messages.INVALID_PASSWORD);
                    return false;
                }
                setProfileValues(_array);
                profile.setValidUser(true);*/
                if (sqlHandler.login(profile)) {
                    if (profile.getKeepCookie()) {
                        System.out.println("  In Login Method keepCookie");
                        pRes.addCookie(setCookie());
                    }else {
                        Cookie loginCookie = new Cookie("www.newbeams.com", "delete");
                        loginCookie.setMaxAge(0);
                        pRes.addCookie(loginCookie);
                    }
                    return true;
                }else {
                    addFormException("Invalid UserName or Password");
                    return false;
                }
            }catch (Exception e){
                e.printStackTrace();
                setLogError(e.getMessage());
                addFormException("Unknown Error");
                return false;
            }
        }else return false;
    }

    private Cookie setCookie(){
        Cookie loginCookie = new Cookie("www.newbeams.com", profile.getUserName()+","+profile.getPassWord());
        loginCookie.setMaxAge(43200);
        return loginCookie;
    }

    private void setProfileValues(ArrayList pArrayResult) throws Exception{
        String [] _array = (String[])pArrayResult.get(0);
        profile.setFirstName(_array[2]);
        profile.setLastName(_array[3]);
        profile.setEmail(_array[4]);
        profile.setAddress1(_array[5]);
        profile.setCity(_array[6]);
        profile.setState(_array[7]);
        profile.setZip(_array[8]);
        profile.setWorkPhoneNum(_array[9]);
        profile.setCellPhoneNum(_array[10]);
    }

    protected void validateFormFields(){
        if ((profile.getFirstName() == null) || (profile.getFirstName().length() < 1)){
            addFormException(Messages.INVALID_FIRSTNAME);
        }
        if ((profile.getLastName() == null) || (profile.getLastName().length() < 1)){
            addFormException(Messages.INVALID_LASTNAME);
        }
        if ((profile.getEmail() == null) || (profile.getEmail().length() < 6)){
            addFormException(Messages.INVALID_EMAIL);
        }else{
            if(!emailSender.validateEmailAdddress(profile.getEmail())) {
                addFormException(Messages.INVALID_EMAIL);
            }
        }
    }
    protected void validateAgree(){
        if (!(profile.getAgree())){
            addFormException(Messages.INVALID_AGREE);
        }
    }

    protected void validateLogInFields(){
        if ((profile.getUserName() == null) || (profile.getUserName().length() < 1)){
            addFormException(Messages.INVALID_USERNAME);
        }else if (!Utility.checkValidChar(profile.getUserName())) {
            addFormException(Messages.INVALID_USERNAME);
        }
        if ((profile.getPassWord()==null) || (profile.getPassWord().length()<4) || (profile.getPassWord().length()>8)){
            addFormException(Messages.INVALID_PASSWORD);
        }else if (!Utility.checkValidChar(profile.getPassWord())) {
            addFormException(Messages.INVALID_PASSWORD);
        }
    }

    protected void validateConfirmPassword(){
        if ((profile.getPassWord2()==null) || (profile.getPassWord2().length()<4) || (profile.getPassWord2().length()>8)){
            addFormException(Messages.INVALID_REENTER_PASSWORD);
        }else if (!Utility.checkValidChar(profile.getPassWord2())) {
            addFormException(Messages.INVALID_PASSWORD);
        }else if (!(profile.getPassWord().equals(profile.getPassWord()))) {
            addFormException(Messages.BOTH_PASSWORD_MISMATCH);
        }
    }

    protected void validateChangePassword(){
        if ((profile.getNewPassWord()==null) || (profile.getNewPassWord().length()<4) || (profile.getNewPassWord().length()>8)){
            addFormException(Messages.INVALID_NEWPASSWORD);
        }else if (!Utility.checkValidChar(profile.getNewPassWord())) {
            addFormException(Messages.INVALID_NEWPASSWORD);
        }
    }

    protected void validateChangePassword2(){
        if ((profile.getNewPassWord2()==null) || (profile.getNewPassWord2().length()<4) || (profile.getNewPassWord2().length()>8)){
            addFormException(Messages.INVALID_REENTER_PASSWORD);
        }else if (!Utility.checkValidChar(profile.getNewPassWord2())) {
            addFormException(Messages.INVALID_NEWPASSWORD);
        }else if (!(profile.getNewPassWord2().equals(profile.getNewPassWord()))) {
            addFormException(Messages.BOTH_NEWPASSWORD_MISMATCH);
        }
    }

    protected void clearProfile(){
        profile.setValidUser(false);
        profile.setUserName("");
        profile.setPassWord("");
        profile.setPassWord2("");
        profile.setFirstName("");
        profile.setLastName("");
        profile.setEmail("");
        profile.setAddress1("");
        profile.setCity("");
        profile.setState("");
        profile.setZip("");
        profile.setWorkPhoneArea("");
        profile.setWorkPhoneNum("");
        profile.setCellPhoneArea("");
        profile.setCellPhoneNum("");
    }
}
