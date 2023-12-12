package com.util;

import java.util.Hashtable;

public class TJILVLFlange extends GenericService{

    private static Hashtable TJILVLFlangeLookup = new Hashtable(80);

    public static String getTJILVLFlangeLookup(String pValue){ return (String) TJILVLFlangeLookup.get(pValue); }

    static{
        TJILVLFlangeLookup.put("1", "7/16\" Web with 2 9/16\" X  1 1/2\" LVL Flange & 12\" Depth");
        TJILVLFlangeLookup.put("2" , "7/16\" Web with 2 9/16\" X  1 1/2\" LVL Flange & 14\" Depth");
        TJILVLFlangeLookup.put("3" , "7/16\" Web with 2 9/16\" X  1 1/2\" LVL Flange & 16\" Depth");
        TJILVLFlangeLookup.put("4" , "7/16\" Web with 2 9/16\" X  1 1/2\" LVL Flange & 18\" Depth");
        TJILVLFlangeLookup.put("5" , "7/16\" Web with 2 9/16\" X  1 1/2\" LVL Flange & 20\" Depth");
        TJILVLFlangeLookup.put("6" , "7/16\" Web with 2 9/16\" X  1 1/2\" LVL Flange & 22\" Depth");
        TJILVLFlangeLookup.put("7" , "7/16\" Web with 2 9/16\" X  1 1/2\" LVL Flange & 24\" Depth");
        TJILVLFlangeLookup.put("8" , "7/16\" Web with 2 9/16\" X  1 1/2\" LVL Flange & 26\" Depth");
        TJILVLFlangeLookup.put("9" , "7/16\" Web with 2 9/16\" X  1 1/2\" LVL Flange & 28\" Depth");
        TJILVLFlangeLookup.put("10" , "7/16\" Web with 2 9/16\" X  1 1/2\" LVL Flange & 30\" Depth");
        TJILVLFlangeLookup.put("11" , "7/16\" Web with 3 1/2\" X 1 1/2\" LVL Flange & 16\" Depth");
        TJILVLFlangeLookup.put("12" , "7/16\" Web with 3 1/2\" X 1 1/2\" LVL Flange & 18\" Depth");
        TJILVLFlangeLookup.put("13" , "7/16\" Web with 3 1/2\" X 1 1/2\" LVL Flange & 20\" Depth");
        TJILVLFlangeLookup.put("14" , "7/16\" Web with 3 1/2\" X 1 1/2\" LVL Flange & 22\" Depth");
        TJILVLFlangeLookup.put("15" , "7/16\" Web with 3 1/2\" X 1 1/2\" LVL Flange & 24\" Depth");
        TJILVLFlangeLookup.put("16" , "7/16\" Web with 3 1/2\" X 1 1/2\" LVL Flange & 26\" Depth");
        TJILVLFlangeLookup.put("17" , "7/16\" Web with 3 1/2\" X 1 1/2\" LVL Flange & 28\" Depth");
        TJILVLFlangeLookup.put("18" , "7/16\" Web with 3 1/2\" X 1 1/2\" LVL Flange & 30\" Depth");
        TJILVLFlangeLookup.put("19" , "7/16\" Web with 3 1/2\" X1 3/4\" LVL Flange & 12\" Depth");
        TJILVLFlangeLookup.put("20" , "7/16\" Web with 3 1/2\" X 1 1/2\" LVL Flange & 14\" Depth");
        TJILVLFlangeLookup.put("21" , "7/16\" Web with 3 1/2\" X 1 1/2\" LVL Flange & 16\" Depth");
        TJILVLFlangeLookup.put("22" , "7/16\" Web with 3 1/2\" X 1 1/2\" LVL Flange & 18\" Depth");
        TJILVLFlangeLookup.put("23" , "7/16\" Web with 3 1/2\" X 1 1/2\" LVL Flange & 20\" Depth");
        TJILVLFlangeLookup.put("24" , "7/16\" Web with 3 1/2\" X 1 1/2\" LVL Flange & 22\" Depth");
        TJILVLFlangeLookup.put("25" , "7/16\" Web with 3 1/2\" X 1 1/2\" LVL Flange & 24\" Depth");
        TJILVLFlangeLookup.put("26" , "7/16\" Web with 3 1/2\" X 1 1/2\" LVL Flange & 26\" Depth");
        TJILVLFlangeLookup.put("27" , "7/16\" Web with 3 1/2\" X 1 1/2\" LVL Flange & 28\" Depth");
        TJILVLFlangeLookup.put("28" , "7/16\" Web with 3 1/2\" X 1 1/2\" LVL Flange & 30\" Depth");
        TJILVLFlangeLookup.put("29" , "3/8\" Web with 1 1/2\" X 1 1/2\" LVL Flange  & 9 1/2\" Depth");
        TJILVLFlangeLookup.put("30" , "3/8\" Web with 1 1/2\" X 1 1/2\" LVL Flange & 12\" Depth");
        TJILVLFlangeLookup.put("31" , "3/8\" Web with 1 3/4\" X 1 1/2\" LVL Flange & 9 1/2\" Depth");
        TJILVLFlangeLookup.put("32" , "3/8\" Web with 1 3/4\" X 1 1/2\" LVL Flange  & 12\" Depth");
        TJILVLFlangeLookup.put("33" , "3/8\" Web with 1 3/4\" X 1 1/2\" LVL Flange  & 14\" Depth");
        TJILVLFlangeLookup.put("34" , "3/8\" Web with  1 3/4\" X 1 1/2\" LVL Flange & 16\" Depth");
        TJILVLFlangeLookup.put("35" , "3/8\" Web with 2 5/16\" X 1 1/2\" LVL Flange & 12\" Depth");
        TJILVLFlangeLookup.put("36" , "3/8\" Web with 2 5/16\" X 1 1/2\" LVL Flange & 14\" Depth");
        TJILVLFlangeLookup.put("37" , "3/8\" Web with 2 5/16\" X 1 1/2\" LVL Flange & 16\" Depth");
        TJILVLFlangeLookup.put("38" , "7/16\" Web with 3 1/2\" X 1 1/2\" LVL Flange & 12\" Depth");
        TJILVLFlangeLookup.put("39" , "7/16\" Web with 3 1/2\" X 1 1/2\" LVL Flange & 14\" Depth");
        TJILVLFlangeLookup.put("40" , "7/16\" Web with 3 1/2\" X 1 1/2\" LVL Flange  & 16\" Depth");
        TJILVLFlangeLookup.put("41" , "3/8\" Web with 2 1/4\" X 1 1/2\"  LVL Flange  & 12\" Depth");
        TJILVLFlangeLookup.put("42" , "3/8\" Web with 2 1/4\" X 1 1/2\" LVL Flange  & 14\" Depth");
        TJILVLFlangeLookup.put("43" , "3/8\" Web with 2 1/4\" X 1 1/2\" LVL Flange  & 16\" Depth");
        TJILVLFlangeLookup.put("44" , "3/8\" Web with 2 1/4\" X 1 1/2\" LVL Flange  & 18\" Depth");
        TJILVLFlangeLookup.put("45" , "3/8\" Web with 2 1/4\" X 1 1/2\" LVL Flange  & 20\" Depth");
        TJILVLFlangeLookup.put("46" , "3/8\" Web with 2 1/4\" X 1 1/2\" LVL Flange  & 22\" Depth");
        TJILVLFlangeLookup.put("47" , "3/8\" Web with 2 1/4\" X 1 1/2\" LVL Flange  & 24\" Depth");
        TJILVLFlangeLookup.put("48" , "7/16\" Web with 3 1/2\" X 1 1/2\" LVL Flange & 12\" Depth");
        TJILVLFlangeLookup.put("49" , "7/16\" Web with 3 1/2\" X 1 1/2\" LVL Flange  & 14\" Depth");
        TJILVLFlangeLookup.put("50" , "7/16\" Web with 3 1/2\" X 1 1/2\" LVL Flange  & 16\" Depth");
        TJILVLFlangeLookup.put("51" , "7/16\" Web with 3 1/2\" X 1 1/2\" LVL Flange  & 18\" Depth");
        TJILVLFlangeLookup.put("52" , "7/16\" Web with 3 1/2\" X 1 1/2\" LVL Flange  & 20\" Depth");
        TJILVLFlangeLookup.put("53" , "7/16\" Web with 3 1/2\" X 1 1/2\" LVL Flange  & 22\" Depth");
        TJILVLFlangeLookup.put("54" , "7/16\" Web with 3 1/2\" X 1 1/2\" LVL Flange  & 24\" Depth");
        TJILVLFlangeLookup.put("55" , "3/8\" Web with 2.3\" X 1 1/2\" LVL Flange  & 14\" Depth");
        TJILVLFlangeLookup.put("56" , "3/8\" Web with 2.3\" X 1 1/2\" LVL Flange  & 16\" Depth");
        TJILVLFlangeLookup.put("57" , "3/8\" Web with 1 1/2\" X 1 1/2\" LVL Flange  & 9 1/2\" Depth");
        TJILVLFlangeLookup.put("58" , "3/8\" Web with 1 1/2\" X 1 1/2\" LVL Flange  & 12\" Depth");
        TJILVLFlangeLookup.put("59" , "3/8\" Web with 1 3/4\" X 1 1/2\" LVL Flange & 9 1/2\" Depth");
        TJILVLFlangeLookup.put("60" , "3/8\" Web with 1 3/4\" X 1 1/2\" LVL Flange  & 12\" Depth");
        TJILVLFlangeLookup.put("61" , "3/8\" Web with 1 3/4\" X 1 1/2\" LVL Flange  & 14\" Depth");
        TJILVLFlangeLookup.put("62" , "3/8\" Web with 1 3/4\" X 1 1/2\" LVL Flange  & 16\" Depth");
        TJILVLFlangeLookup.put("63" , "3/8\" Web with 2 1/4\"  x 1 1/2\" LVL Flange  & 12\" Depth");
        TJILVLFlangeLookup.put("64" , "3/8\" Web with 2 1/4\"  x 1 1/2\" LVL Flange  & 14\" Depth");
        TJILVLFlangeLookup.put("65" , "3/8\" Web with 2 1/4\"  x 1 1/2\" LVL Flange  & 16\" Depth");
        TJILVLFlangeLookup.put("66" , "7/16\" Web with 3 1/2\" X 1 1/2\" LVL Flange  & 12\" Depth");
        TJILVLFlangeLookup.put("67" , "7/16\" Web with 3 1/2\" X 1 1/2\" LVL Flange  & 14\" Depth");
        TJILVLFlangeLookup.put("68" , "7/16\" Web with 3 1/2\" X 1 1/2\" LVL Flange & 16\" Depth");
        TJILVLFlangeLookup.put("69" , "3/8\" Web with 1 1/2\" X 1 1/2\" LVL Flange  & 9 1/2\" Depth");
        TJILVLFlangeLookup.put("70" , "3/8\" Web with 1 1/2\" X 1 1/2\" LVL Flange  & 12\" Depth");
        TJILVLFlangeLookup.put("71" , "3/8\" Web with 1 3/4\" X 1 1/2\" LVL Flange  & 9 1/2\" Depth");
        TJILVLFlangeLookup.put("72" , "3/8\" Web with 1 3/4\" X 1 1/2\" LVL Flange  & 12\" Depth");
        TJILVLFlangeLookup.put("73" , "3/8\" Web with 1 3/4\" X 1 1/2\" LVL Flange  & 14\" Depth");
        TJILVLFlangeLookup.put("74" , "3/8\" Web with 1 3/4\" X 1 1/2\" LVL Flange  & 16\" Depth");
        TJILVLFlangeLookup.put("75" , "3/8\" Web with  2 5/16\" X 1 1/2\"  LVL Flange  & 12\" Depth");
        TJILVLFlangeLookup.put("76" , "3/8\" Web with  2 5/16\" X 1 1/2\"  LVL Flange & 14\" Depth");
        TJILVLFlangeLookup.put("77" , "3/8\" Web with  2 5/16\" X 1 1/2\"  LVL Flange  & 16\" Depth");
        TJILVLFlangeLookup.put("78" , "1/2\" OSB Web with 2 X 4 LVL Flange & 12\" Depth");
        TJILVLFlangeLookup.put("79" , "1/2\" OSB Web with 2 X 4 LVL Flange & 14\" Depth");
        TJILVLFlangeLookup.put("80" , "1/2\" OSB Web with 2 X 4 LVL Flange & 16\" Depth");
    }
}
