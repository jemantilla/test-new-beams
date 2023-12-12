package com.util;

import java.util.Hashtable;

public final class HashValues{

    public final static Hashtable stateTable = new Hashtable();
    public final static Hashtable materialTable = new Hashtable();
    public final static Hashtable steelTable = new Hashtable();
    public final static Hashtable stdTable = new Hashtable();
    public final static Hashtable lvlTable = new Hashtable();
    public final static Hashtable flistdTable = new Hashtable();
    public final static Hashtable flilvlTable = new Hashtable();
    public final static Hashtable keyDouble = new Hashtable();

    static{
        steelTable.put("1" , "Single Steel");

        stdTable.put("1" , "Single Std.Lumber");
        stdTable.put("2" , "Double Std.Lumber");
        stdTable.put("3" , "Triple Std.Lumber");
        stdTable.put("4" , "Quart Std.Lumber");
        stdTable.put("5" , "Five Std.Lumber");

        lvlTable.put("1" , "Single LVL");
        lvlTable.put("2" , "Double LVL");
        lvlTable.put("3" , "Triple LVL");
        lvlTable.put("4" , "Quart LVL");
        lvlTable.put("5" , "Five LVL");

        flistdTable.put("1" , "Single Std.Lumber + Single Plate");
        flistdTable.put("2" , "Four Std.Lumber + Single Plate");
        flistdTable.put("3" , "Triple Std.Lumber + Double Plate");

        flilvlTable.put("1" , "Double LVL + Single Plate");
        flilvlTable.put("2" , "Four LVL + Single Plate");
        flilvlTable.put("3" , "Triple LVL + Double Plate");

        materialTable.put("steel" , "Steel Type A36");
        materialTable.put("std" , "Wood/Standard Lumber");
        materialTable.put("lvl" , "Laminated Veneer Lumber");
        materialTable.put("flistd" , "Steel flitch plate + Std.Lumber");
        materialTable.put("flilvl" , "Steel flitch plate + LVL");
        materialTable.put("woodIShape", "Wooden I-Shaped Joists");
        materialTable.put("woodIShapeWood", "Wooden I-Shaped Joist with Wood Flange");
        materialTable.put("woodIShapeLVL", "Wooden I-Shaped Joist with LVL Flange");
        materialTable.put("psl", "Parallel Strand Lumber");

        keyDouble.put("1 1/2" , "1.5");
        keyDouble.put("1 3/4" , "1.75");
        keyDouble.put("3 1/2" , "3.5");
        keyDouble.put("5 1/2" , "5.5");
        keyDouble.put("7 1/2" , "7.5");
        keyDouble.put("9 1/4" , "9.25");
        keyDouble.put("9 1/2" , "9.5");
        keyDouble.put("11 1/4" , "11.25");
        keyDouble.put("11 7/8" , "11.875");
        keyDouble.put("14" , "14");
        keyDouble.put("16" , "16");
        keyDouble.put("18" , "18");
        keyDouble.put("1/4" , "0.25");
        keyDouble.put("3/8" , "0.375");
        keyDouble.put("1/2" , "0.5");
        keyDouble.put("5/8" , "0.625");
        keyDouble.put("3/4" , "0.75");
        keyDouble.put("7/8" , "0.875");
        keyDouble.put("1" , "1");
        keyDouble.put("11 1/4" , "11.25");
        keyDouble.put("11 7/8" , "11.875");

        stateTable.put("NY" , "New York");
        stateTable.put("AL" , "Alabama");
        stateTable.put("AK" , "Alaska");
        stateTable.put("AZ" , "Arizona");
        stateTable.put("AR" , "Arkansas");
        stateTable.put("CA" , "California");
        stateTable.put("CO" , "Colorado");
        stateTable.put("CT" , "Connecticut");
        stateTable.put("DE" , "Delaware");
        stateTable.put("DC" , "District of Columbia");
        stateTable.put("FL" , "Florida");
        stateTable.put("GA" , "Georgia");
        stateTable.put("GU" , "Guam");
        stateTable.put("HI" , "Hawaii");
        stateTable.put("ID" , "Idaho");
        stateTable.put("IL" , "Illinois");
        stateTable.put("IN" , "Indiana");
        stateTable.put("IA" , "Iowa");
        stateTable.put("KS" , "Kansas");
        stateTable.put("KY" , "Kentucky");
        stateTable.put("LA" , "Louisiana");
        stateTable.put("ME" , "Maine");
        stateTable.put("MD" , "Maryland");
        stateTable.put("MA" , "Massachusetts");
        stateTable.put("MI" , "Michigan");
        stateTable.put("MN" , "Minnesota");
        stateTable.put("MS" , "Mississippi");
        stateTable.put("MO" , "Missouri");
        stateTable.put("MT" , "Montana");
        stateTable.put("NE" , "Nebraska");
        stateTable.put("NV" , "Nevada");
        stateTable.put("NH" , "New Hampshire");
        stateTable.put("NJ" , "New Jersey");
        stateTable.put("NM" , "New Mexico");
        stateTable.put("NC" , "North Carolina");
        stateTable.put("ND" , "North Dakota");
        stateTable.put("OH" , "Ohio");
        stateTable.put("OK" , "Oklahoma");
        stateTable.put("OR" , "Oregon");
        stateTable.put("PA" , "Pennsylvania");
        stateTable.put("PR" , "Puerto Rico");
        stateTable.put("RI" , "Rhode Island");
        stateTable.put("SC" , "South Carolina");
        stateTable.put("SD" , "South Dakota");
        stateTable.put("TN" , "Tennessee");
        stateTable.put("TX" , "Texas");
        stateTable.put("UT" , "Utah");
        stateTable.put("VT" , "Vermont");
        stateTable.put("VA" , "Virginia");
        stateTable.put("WA" , "Washington");
        stateTable.put("WV" , "West Virginia");
        stateTable.put("WI" , "Wisconsin");
        stateTable.put("WY" , "Wyoming");
    }

    public static String getSteelValue(String pKey){ return (String)steelTable.get(pKey); }
    public static String getStdValue(String pKey){ return (String)stdTable.get(pKey); }
    public static String getLVLValue(String pKey){ return (String)lvlTable.get(pKey); }
    public static String getFlistdValue(String pKey){ return (String)flistdTable.get(pKey); }
    public static String getFlilvlValue(String pKey){ return (String)flilvlTable.get(pKey); }
    public static String getStateValue(String pKey){ return (String)stateTable.get(pKey); }
    public static String getMaterialValue(String pKey){ return (String)materialTable.get(pKey); }
}
