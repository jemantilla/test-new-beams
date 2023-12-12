package com.util;

import java.util.ArrayList;

public class DesignHelp extends GenericService{

    Design design;
    protected String asphalt = "selected", builtUp = "", slate = "", wood = "", tile = "", metalStandingSeam = "";
    protected String steel = "selected", std = "", lvl = "", flistd = "", flilvl = "", psl = "", woodIShape = "";
    protected String woodIShapeLVL = "",  woodIShapeWood = "";
    protected String aSteel = "selected", aStd = "", aLvl = "", aFlistd = "", aFlilvl = "", aPsl = "", aWoodIShape = "";
    protected String aWoodIShapeLVL = "",  aWoodIShapeWood = "";
    protected String one = "", two = "", three = "", four = "", five = "";
    protected String aOne = "", aTwo = "", aThree = "", aFour = "", aFive = "";

    public DesignHelp(Design pDesign){ design =  pDesign; }

    public String getSteel(){ return steel; }
    public String getStd(){ return std; }
    public String getLvl(){ return lvl; }
    public String getFlistd(){ return flistd;}
    public String getFlilvl(){ return flilvl;}
    public String getPsl(){ return psl;}
    public String getWoodIShape(){ return woodIShape;}
    public String getWoodIShapeLVL(){ return woodIShapeLVL;}
    public String getWoodIShapeWood(){ return woodIShapeWood;}

    public void setMaterialValues(){
        if(design.getMaterial().equalsIgnoreCase("std")){
            steel = "";
            std = "selected";
            lvl = "";
            flistd = "";
            flilvl = "";
            psl = "";
            woodIShape = "";
            woodIShapeWood = "";
            woodIShapeLVL = "";
        }else if(design.getMaterial().equalsIgnoreCase("steel")){
            steel = "selected";
            std = "";
            lvl = "";
            flistd = "";
            flilvl = "";
            psl = "";
            woodIShape = "";
            woodIShapeWood = "";
            woodIShapeLVL = "";
        }else if(design.getMaterial().equalsIgnoreCase("lvl")){
            steel = "";
            std = "";
            lvl = "selected";
            flistd = "";
            flilvl = "";
            psl = "";
            woodIShape = "";
            woodIShapeWood = "";
            woodIShapeLVL = "";
        }else if (design.getMaterial().equalsIgnoreCase("flistd")){
            steel = "";
            std = "";
            lvl = "";
            flistd = "selected";
            flilvl = "";
            psl = "";
            woodIShape = "";
            woodIShapeWood = "";
            woodIShapeLVL = "";
        }else if(design.getMaterial().equalsIgnoreCase("flilvl")){
            steel = "";
            std = "";
            lvl = "";
            flistd = "";
            flilvl = "selected";
            psl = "";
            woodIShape = "";
            woodIShapeWood = "";
            woodIShapeLVL = "";
        }else if (design.getMaterial().equalsIgnoreCase("psl")){
            steel = "";
            std = "";
            lvl = "";
            flistd = "";
            flilvl = "";
            psl = "selected";
            woodIShape = "";
            woodIShapeWood = "";
            woodIShapeLVL = "";
        }else if(design.getMaterial().equalsIgnoreCase("woodIShape")){
            steel = "";
            std = "";
            lvl = "";
            flistd = "";
            flilvl = "";
            psl = "";
            woodIShape = "selected";
            woodIShapeWood = "";
            woodIShapeLVL = "";
        }else if(design.getMaterial().equalsIgnoreCase("woodIShapeWood")){
            steel = "";
            std = "";
            lvl = "";
            flistd = "";
            flilvl = "";
            psl = "";
            woodIShape = "";
            woodIShapeWood = "selected";
            woodIShapeLVL = "";
        }else if(design.getMaterial().equalsIgnoreCase("woodIShapeLVL")){
            steel = "";
            std = "";
            lvl = "";
            flistd = "";
            flilvl = "";
            psl = "";
            woodIShape = "";
            woodIShapeWood = "";
            woodIShapeLVL = "selected";
        }
    }
    
    public String getASteel(){ return aSteel; }
    public String getAStd(){ return aStd; }
    public String getALvl(){ return aLvl; }
    public String getAFlistd(){ return aFlistd;}
    public String getAFlilvl(){ return aFlilvl;}
    public String getAPsl(){ return aPsl;}
    public String getAWoodIShape(){ return aWoodIShape;}
    public String getAWoodIShapeLVL(){ return aWoodIShapeLVL;}
    public String getAWoodIShapeWood(){ return aWoodIShapeWood;}

    public void setAMaterialValues(){
        if(design.getAMaterial().equalsIgnoreCase("std")){
            aSteel = "";
            aStd = "selected";
            aLvl = "";
            aFlistd = "";
            aFlilvl = "";
            aPsl = "";
            aWoodIShape = "";
            aWoodIShapeWood = "";
            aWoodIShapeLVL = "";
        }else if(design.getAMaterial().equalsIgnoreCase("steel")){
            aSteel = "selected";
            aStd = "";
            aLvl = "";
            aFlistd = "";
            aFlilvl = "";
            aPsl = "";
            aWoodIShape = "";
            aWoodIShapeWood = "";
            aWoodIShapeLVL = "";
        }else if(design.getAMaterial().equalsIgnoreCase("lvl")){
            aSteel = "";
            aStd = "";
            aLvl = "selected";
            aFlistd = "";
            aFlilvl = "";
            aPsl = "";
            aWoodIShape = "";
            aWoodIShapeWood = "";
            aWoodIShapeLVL = "";
        }else if (design.getAMaterial().equalsIgnoreCase("flistd")){
            aSteel = "";
            aStd = "";
            aLvl = "";
            aFlistd = "selected";
            aFlilvl = "";
            aPsl = "";
            aWoodIShape = "";
            aWoodIShapeWood = "";
            aWoodIShapeLVL = "";
        }else if(design.getAMaterial().equalsIgnoreCase("flilvl")){
            aSteel = "";
            aStd = "";
            aLvl = "";
            aFlistd = "";
            aFlilvl = "selected";
            aPsl = "";
            aWoodIShape = "";
            aWoodIShapeWood = "";
            aWoodIShapeLVL = "";
        }else if (design.getAMaterial().equalsIgnoreCase("psl")){
            aSteel = "";
            aStd = "";
            aLvl = "";
            aFlistd = "";
            aFlilvl = "";
            aPsl = "selected";
            aWoodIShape = "";
            aWoodIShapeWood = "";
            aWoodIShapeLVL = "";
        }else if(design.getAMaterial().equalsIgnoreCase("woodIShape")){
            aSteel = "";
            aStd = "";
            aLvl = "";
            aFlistd = "";
            aFlilvl = "";
            aPsl = "";
            aWoodIShape = "selected";
            aWoodIShapeWood = "";
            aWoodIShapeLVL = "";
        }else if(design.getAMaterial().equalsIgnoreCase("woodIShapeWood")){
            aSteel = "";
            aStd = "";
            aLvl = "";
            aFlistd = "";
            aFlilvl = "";
            aPsl = "";
            aWoodIShape = "";
            aWoodIShapeWood = "selected";
            aWoodIShapeLVL = "";
        }else if(design.getAMaterial().equalsIgnoreCase("woodIShapeLVL")){
            aSteel = "";
            aStd = "";
            aLvl = "";
            aFlistd = "";
            aFlilvl = "";
            aPsl = "";
            aWoodIShape = "";
            aWoodIShapeWood = "";
            aWoodIShapeLVL = "selected";
        }
    }

    public String getOne(){ return one; }
    public String getTwo(){ return two; }
    public String getThree(){ return three; }
    public String getFour(){ return four; }
    public String getFive(){ return five; }

    public void setNumberValues(){
        if (design.getNumber().equalsIgnoreCase("1")) {
            one = "selected";
            two = "";
            three = "";
            four = "";
            five = "";
        }else if (design.getNumber().equalsIgnoreCase("2")) {
            one = "";
            two = "selected";
            three = "";
            four = "";
            five = "";
        }else if (design.getNumber().equalsIgnoreCase("3")) {
            one = "";
            two = "";
            three = "selected";
            four = "";
            five = "";
        }else if (design.getNumber().equalsIgnoreCase("4")) {
            one = "";
            two = "";
            three = "";
            four = "selected";
            five = "";
        }else if (design.getNumber().equalsIgnoreCase("5")) {
            one = "";
            two = "";
            three = "";
            four = "";
            five = "selected";
        }
    }

    public String getAOne(){ return aOne; }
    public String getATwo(){ return aTwo; }
    public String getAThree(){ return aThree; }
    public String getAFour(){ return aFour; }
    public String getAFive(){ return aFive; }

    public void setANumberValues(){
        if (design.getANumber().equalsIgnoreCase("1")) {
            aOne = "selected";
            aTwo = "";
            aThree = "";
            aFour = "";
            aFive = "";
        }else if (design.getANumber().equalsIgnoreCase("2")) {
            aOne = "";
            aTwo = "selected";
            aThree = "";
            aFour = "";
            aFive = "";
        }else if (design.getANumber().equalsIgnoreCase("3")) {
            aOne = "";
            aTwo = "";
            aThree = "selected";
            aFour = "";
            aFive = "";
        }else if (design.getANumber().equalsIgnoreCase("4")) {
            aOne = "";
            aTwo = "";
            aThree = "";
            aFour = "selected";
            aFive = "";
        }else if (design.getANumber().equalsIgnoreCase("5")) {
            aOne = "";
            aTwo = "";
            aThree = "";
            aFour = "";
            aFive = "selected";
        }
    }
    
    public String getAsphalt(){ return asphalt; }
    public String getBuildUp(){ return builtUp; }
    public String getMetalStandingSeam(){ return metalStandingSeam; }
    public String getSlate(){ return slate; }
    public String getWood(){ return wood;}
    public String getTile(){ return tile;}

    public void setMaterBValues(){
        if (design.getMaterb().equals("BuiltUp")){
            builtUp = "selected";
            asphalt = "";
            slate = "";
            wood = "";
            tile = "";
            metalStandingSeam = "";
        }else if (design.getMaterb().equals("MetalStandingSeam")){
            metalStandingSeam = "selected";
            tile = "";
            asphalt = "";
            slate = "";
            wood = "";
            builtUp = "";
        }else if (design.getMaterb().equals("Slate")){
            slate = "selected";
            asphalt = "";
            builtUp = "";
            wood = "";
            tile = "";
            metalStandingSeam = "";
        }else if (design.getMaterb().equals("Wood")){
            wood = "selected";
            asphalt = "";
            slate = "";
            builtUp = "";
            tile = "";
            metalStandingSeam = "";
        }else if (design.getMaterb().equals("Tile")){
            tile = "selected";
            asphalt = "";
            slate = "";
            wood = "";
            builtUp = "";
            metalStandingSeam = "";
        }else {
            asphalt = "selected";
            builtUp = "";
            slate = "";
            wood = "";
            tile = "";
            metalStandingSeam = "";
        }
    }
}
