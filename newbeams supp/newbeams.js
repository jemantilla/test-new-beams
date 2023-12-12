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
function openWindow(url,name,pWidth,pHeight,pLeft,pTop){
    settings=
        "toolbar=no,location=no,directories=no,"+
        "status=no,menubar=no,scrollbars=no,"+
        "resizable=no,width="+pWidth+",height="+pHeight+",left="+pLeft+",top="+pTop;
    
    MyNewWindow=window.open(url,name,settings); 
	MyNewWindow.focus();
}
function openWindowWithScroll(url,name,pWidth,pHeight,pScroll){
    settings=
        "toolbar=no,location=no,directories=no,"+
        "status=no,menubar=no,scrollbars="+pScroll
        ",resizable=no,width="+pWidth+",height="+pHeight+",left=50,top=50";
    
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
        this.document.DesignForm.storey.value = "1";
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
function setCheckExist(pMater, pNumber){
    setValues(pMater, pNumber);
    setEnable();
}
var doRefresh = false;
function setValues(pMater, pNumber){
    if ((pMater != document.DesignForm.mater.value) && (pMater != null)) {
        alert("diff Mater" + pMater + "-local:"+document.DesignForm.mater.value);
        document.DesignForm.mater.value = pMater;
        doRefresh = true;
    }
    if (pNumber != document.DesignForm.number.value) {
        alert("diff pNumber");
        document.DesignForm.number.value = pNumber;
        doRefresh = true;
    }
    if (doRefresh) {
        RefreshPage();
    }else{
        //alert("same Number" + pNumber + "-local:"+document.DesignForm.number.value);
    }
}
function RefreshPage(){
    alert("doRefresh:" + doRefresh);
    if (doRefresh) {
        doRefresh = false;
        alert(" in doRefresh");
        document.writeln("<META HTTP-EQUIV=Refresh CONTENT=5;URL=check_exist.jsp>");
    }
}
function setEnableTimber(pMater, pNumber){
    if ((pMater != document.DesignForm.mater.value) && (pMater != null)) {
        //alert("diff Mater" + pMater + "-local:"+document.DesignForm.mater.value);
        //document.writeln("<META HTTP-EQUIV=\"Refresh\" CONTENT=\"15\">");
        document.DesignForm.mater.value = pMater;
    }
    if ((pNumber != document.DesignForm.number.value) && (pNumber != null)) {
        //document.writeln("<META HTTP-EQUIV=\"Refresh\" CONTENT=\"15\">");
        //alert("diff pNumber");
        document.DesignForm.number.value = pNumber;
    }
    //alert(document.referrer);
    //alert(document.DesignForm.mater.value + document.DesignForm.number.value);
    //alert(top.method);
    //alert(history.length);
}
var saveBeam = false;
function saveBeamClicked(){
    saveBeam = true;
}
function unLoad(backURL){
    //alert(backURL);
    if (saveBeam) {
        //alert("button clicked");
        saveBeam = false;
        this.form.submit();
    }else{
        location = backURL;
        //parent.location = backURL;
        //alert(backURL);
    }
}

