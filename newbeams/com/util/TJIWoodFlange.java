package com.util;

import java.util.Hashtable;

public class TJIWoodFlange extends GenericService{

    private static Hashtable TJIWoodFlangeLookup = new Hashtable(80);

    public static String getTJIWoodFlangeLookup(String pValue){ return (String) TJIWoodFlangeLookup.get(pValue); }

    static{
        TJIWoodFlangeLookup.put("1" , "3/8\" Web with 1 3/4\" X 11/2\" Wood Flange & 9  1/2\" depth");
        TJIWoodFlangeLookup.put("2" , "3/8\" Web with 1 3/4\" X 1 1/2\" Wood Flange & 12\" depth");
        TJIWoodFlangeLookup.put("3" , "3/8\" Web with 2 1/2\" X 1 1/2\" Wood Flange & 9 1/2\" depth");
        TJIWoodFlangeLookup.put("4" , "3/8\" Web with 2 1/2\" X 1 1/2\" Wood Flange & 12\" depth");
        TJIWoodFlangeLookup.put("5" , "3/8\" Web with 2 1/2\" X 1 1/2\" Wood Flange & 9 1/2\" depth");
        TJIWoodFlangeLookup.put("6" , "3/8\" Web with 2 1/2\" X 1 1/2\" Wood Flange & 12\" depth");
        TJIWoodFlangeLookup.put("7" , "3/8\" Web with 2 1/2\" X 1 1/2\" Wood Flange & 14\" depth");
        TJIWoodFlangeLookup.put("8" , "3/8\" Web with 2 1/2\" X 1 1/2\" Wood Flange & 16\" depth");
        TJIWoodFlangeLookup.put("9" , "3/8\" OSB Web with 2 X 3 MSR Flange & 9 1/2\" depth");
        TJIWoodFlangeLookup.put("10" , "3/8\" OSB Web with 2 X 3 MSR Flange & 12\" depth");
        TJIWoodFlangeLookup.put("11" , "3/8\" OSB  Web with 2 X 3 MSR Flange & 14\" depth");
        TJIWoodFlangeLookup.put("12" , "3/8\" OSB  Web with 2 X 3 MSR Flange & 16\" depth");
        TJIWoodFlangeLookup.put("13" , "3/8\" OSB  Web with 2 X 4 MSR Flange & 9 1/2\" depth");
        TJIWoodFlangeLookup.put("14" , "3/8\" OSB  Web with 2 X 4 MSR Flange & 12\" depth");
        TJIWoodFlangeLookup.put("15" , "3/8\" OSB  Web with 2 X 4 MSR Flange & 14\" depth");
        TJIWoodFlangeLookup.put("16" , "3/8\" OSB Web with 2 X 4 MSR Flange & 16\" depth");
    }
}
