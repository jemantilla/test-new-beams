package com.util;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Utility{
    static SimpleDateFormat FORMATER = new SimpleDateFormat("MM/dd/yyyy hh:mm a");

    synchronized public static String format(Date pDate){ return FORMATER.format(pDate); }

    synchronized public static double getTwoDecimal(String pNumString){
        double _twoDigit = 0;
        try{
            _twoDigit = Double.parseDouble(pNumString);
            _twoDigit = Math.floor((_twoDigit*100)+0.5)/100;
        }catch(Exception e){}
        return _twoDigit;
    }

    synchronized public static double getTwoDecimal(double pNum){ return Math.floor((pNum*100)+0.5)/100; }

    synchronized public static boolean checkValidChar(String pString){
        try{
            char _ch = pString.charAt(0);
            if ((_ch>=65 && _ch<=90) || (_ch>=97 && _ch<=122)){
                // valid char start
            }else return false;
            for (int i=1; i<pString.length(); i++) {
                if ((_ch>=65 && _ch<=90) || (_ch>=97 && _ch<=122) || (_ch == 95)){
                    // valid char start
                }else return false;
            }
            return true;
        }catch (Exception e){ return false; }
    }
}
