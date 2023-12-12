package com.newbeams.utility;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DateUtility{
    static SimpleDateFormat FORMATER = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
  //  static SimpleDateFormat FORMATER = new SimpleDateFormat("MM/dd/yyyy");

    synchronized public static String format(Date pDate){ return FORMATER.format(pDate); }
}
