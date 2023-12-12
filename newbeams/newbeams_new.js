var roofSelected = false;
var designTimberStr2 = "&nbsp;&nbsp;<a href=\"javaScript:openWindow('roof_image.html','Roof',375,375);\">View Image</a>";
function projectSelected(){
        DesignForm.submit();
}
function popUpWindow(url,name){
    settings=
        "toolbar=no,location=no,directories=no,"+
        "status=no,menubar=no,scrollbars=no,"+
        "resizable=no,width=450,height=160,left=100,top=100";
    
    MyNewWindow=window.open(url,name,settings);
	MyNewWindow.focus();
 
}
function openWindow(url,name,pWidth,pHeight){
    settings=
        "toolbar=no,location=no,directories=no,"+
        "status=no,menubar=no,scrollbars=no,"+
        "resizable=no,width="+pWidth+",height="+pHeight+",left=50,top=50";
    
    MyNewWindow=window.open(url,name,settings); 
	MyNewWindow.focus();
}
function setEnable(){
    if (this.document.DesignForm.loadType[0].checked == true) {
        this.document.DesignForm.load.disabled = 0;
        this.document.DesignForm.trifeet.disabled = -1;
        this.document.DesignForm.triinch.disabled = -1;
        this.document.DesignForm.storey.disabled = -1;
        this.document.DesignForm.trifeet.value = "";
        this.document.DesignForm.triinch.value = "0";
        this.document.DesignForm.storey.value = "";
        this.document.DesignForm.roofIncluded.disabled = -1;
        this.document.DesignForm.roofPitch.disabled = -1;
        this.document.DesignForm.materb.disabled = -1;
    }else if (this.document.DesignForm.loadType[1].checked == true) {
        this.document.DesignForm.load.disabled = -1;
        this.document.DesignForm.load.value = "";
        this.document.DesignForm.trifeet.disabled = 0;
        this.document.DesignForm.triinch.disabled = 0;
        this.document.DesignForm.storey.disabled = 0;
        this.document.DesignForm.roofIncluded.disabled = 0;
        this.document.DesignForm.roofPitch.disabled = 0;
        this.document.DesignForm.materb.disabled = 0;
    }else {
        this.document.DesignForm.load.disabled = -1;
        this.document.DesignForm.trifeet.disabled = -1;
        this.document.DesignForm.triinch.disabled = -1;
        this.document.DesignForm.storey.disabled = -1;
        this.document.DesignForm.roofIncluded.disabled = -1;
        this.document.DesignForm.roofPitch.disabled = -1;
        this.document.DesignForm.materb.disabled = -1;
    }
}
function setEnableTimber(){

        var code="&nbsp;&nbsp;"
Linking="<a href=\"javaScript:openWindow('roof_image.html','Roof',375,375);\">View Image</a>"
var designTimberStr = code+Linking
    if (this.document.DesignForm.floorType[3].checked == true) {
        this.document.DesignForm.roofIncluded.disabled = 0;
        this.document.DesignForm.roofPitch.disabled = 0;
        this.document.DesignForm.materb.disabled = 0;
        roofSelected = true;
        alert(designTimberStr);
        document.all.linker.innerHTML=designTimberStr;
        //writeLink();
    }else {
        this.document.DesignForm.roofIncluded.disabled = -1;
        this.document.DesignForm.roofPitch.disabled = -1;
        this.document.DesignForm.materb.disabled = -1;
        roofSelected = false;
        document.all.linker.innerHTML="";
        //writeLink();
    }
}

