package com.newbeams.business;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.newbeams.application.OrderSQLHandler;
import com.newbeams.bean.WorkOrder;
import com.newbeams.bean.LeadForm;
import com.newbeams.bean.Agreement;
import java.util.Vector;

public class OrderFormHandler extends GenericFormHandler{

    OrderSQLHandler mOrderSQLHandler = new OrderSQLHandler();
    /*LeadForm mLeadForm = new LeadForm();
    WorkOrder mWorkOrder = new WorkOrder();*/
    Agreement mAgreement = new Agreement();
    
    Vector mPendingLeadForms = new Vector();
    Vector mPendingWorkOrders = new Vector();
    Vector mDeclinedWorkOrders = new Vector();
    Vector mListOfAgreements = new Vector();
    
    /*public void setLeadForm(LeadForm pLeadForm) { mLeadForm = pLeadForm; }
    public LeadForm getLeadForm() { return mLeadForm; }

    public void setWorkOrder(WorkOrder pWorkOrder) { mWorkOrder = pWorkOrder; }
    public WorkOrder getWorkOrder() { return mWorkOrder; }*/

    public void setAgreement (Agreement pAgreement) { mAgreement = pAgreement; }
    public Agreement getAgreement() { return mAgreement; }
    
    public void retrivePendingLeadForms() { mPendingLeadForms = mOrderSQLHandler.retrivePendingLeadForms(); }
    public Vector getPendingLeadForms() { return mPendingLeadForms; }  
    
    public void retrivePendingWorkOrders() { mPendingWorkOrders = mOrderSQLHandler.retrivePendingWorkOrders(); }
    public Vector getPendingWorkOrders() { return mPendingWorkOrders; }
    
    public void retriveDeclinedWorkOrders() { mDeclinedWorkOrders = mOrderSQLHandler.retriveDeclinedWorkOrders(); }
    public Vector getDeclinedWorkOrders() { return mDeclinedWorkOrders; }
    
    public void retriveListOfAgreements() { mListOfAgreements = mOrderSQLHandler.retriveListOfAgreements(); }
    public Vector getListOfAgreements() { return mListOfAgreements; }

    public Agreement getAgreementByID(int pID) {
        return mOrderSQLHandler.getAgreementByID(pID);
    }

    private boolean setLeadFormParameter(HttpServletRequest pReq, LeadForm pLeadForm){
    	if (pLeadForm == null) {
    		System.out.println("leadform is null");
    	}
    	try{
    		System.out.println("Intake By:"+pLeadForm.getIntakeBy());
    	pLeadForm.setIntakeBy(pReq.getParameter("IntakeBy"));
    	System.out.println("Intake By:"+pLeadForm.getIntakeBy());
    	System.out.println("for which company:"+pLeadForm.getForWhichCompany());
    	pLeadForm.setForWhichCompany(pReq.getParameter("ForWhichCompany"));
    	System.out.println("for which company:"+pLeadForm.getForWhichCompany());
    	pLeadForm.setCallerFirstName(pReq.getParameter("CallerFirstName"));
    	pLeadForm.setCallerLastName(pReq.getParameter("CallerLastName"));
    	pLeadForm.setCallerCompanyName(pReq.getParameter("CallerCompanyName"));
    	pLeadForm.setPhone(pReq.getParameter("Phone"));
    	pLeadForm.setFacsimile(pReq.getParameter("Facsimile"));
    	pLeadForm.setMobile(pReq.getParameter("Mobile"));
    	pLeadForm.setEmail(pReq.getParameter("Email"));
      //  mLeadForm.setPropertyAddress(pReq.getParameter("PropertyAddress"));
    	pLeadForm.setHouse(pReq.getParameter("House"));
    	pLeadForm.setStreet(pReq.getParameter("Street"));
    	pLeadForm.setCity(pReq.getParameter("City"));
    	pLeadForm.setState(pReq.getParameter("State"));
    	pLeadForm.setZip(pReq.getParameter("Zip"));
    	pLeadForm.setTaxId(pReq.getParameter("TaxId"));
     //   mLeadForm.setNotes(pReq.getParameter("Notes"));
    	pLeadForm.setNeed(pReq.getParameter("Need"));
    	pLeadForm.setService(pReq.getParameter("Service"));
    	pLeadForm.setScope(pReq.getParameter("Scope"));
    	System.out.println("start"+pLeadForm.getScope()+ "-End");
    	pLeadForm.setPropertySize(pReq.getParameter("PropertySize"));
    	pLeadForm.setShape(pReq.getParameter("Shape"));
    	pLeadForm.setFormerSurvey(pReq.getParameter("FormerSurvey"));
    	pLeadForm.setFromWhom(pReq.getParameter("FromWhom"));
    	pLeadForm.setSurveyDate(pReq.getParameter("SurveyDate"));
    	pLeadForm.setAttention(pReq.getParameter("Attention"));
    	
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return true;
    }

    public boolean handleLeadForm(HttpServletRequest pReq, HttpServletResponse pRes){
        if (setLeadFormParameter(pReq, mAgreement.getWorkOrder().getLeadForm())) {
            try{
                mOrderSQLHandler.insertLeadForm(mAgreement);
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
        }else {
            return false;
        }
    }

    public boolean handleUpdateLeadForm(HttpServletRequest pReq, HttpServletResponse pRes){
        if (setLeadFormParameter(pReq, mAgreement.getWorkOrder().getLeadForm())) {
            try{
                mOrderSQLHandler.updateLeadForm(mAgreement);
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
        }else {
            return false;
        }
    }
    
    public boolean handledeleteLeadForm(HttpServletRequest pReq, HttpServletResponse pRes){
            try{
            	long id = Long.parseLong(pReq.getParameter("ID"));
                mOrderSQLHandler.deleteLeadForm(id);
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
    }

    private boolean setWorkOrderParameter(HttpServletRequest pReq, WorkOrder pWorkOrder){
    	pWorkOrder.setRespondedBy(pReq.getParameter("RespondedBy"));
    	System.out.println("Responded BY:"+pWorkOrder.getRespondedBy());
    	pWorkOrder.setResponseDate(pReq.getParameter("ResponseDate"));
    	System.out.println("Response date:"+pWorkOrder.getResponseDate());
       // mWorkOrder.setQuote(pReq.getParameter("Quote"));
    	pWorkOrder.setEstimatedServiceFee(pReq.getParameter("EstimatedServiceFee"));
    	System.out.println("service fee"+pWorkOrder.getEstimatedServiceFee());
    	pWorkOrder.setRetainer(pReq.getParameter("Retainer"));
    	pWorkOrder.setDateReceived(pReq.getParameter("DateReceived"));
    	pWorkOrder.setAmountType(pReq.getParameter("AmountType"));       
     //   mWorkOrder.setPayment(pReq.getParameter("Payment"));
    	pWorkOrder.setCardNumber(pReq.getParameter("CardNumber"));
    	pWorkOrder.setExpirationDate(pReq.getParameter("ExpirationDate"));
    	pWorkOrder.setBillingHouseNumber(pReq.getParameter("BillingHouseNumber"));
    	pWorkOrder.setBillingStreet(pReq.getParameter("BillingStreet"));
    	pWorkOrder.setBillingCity(pReq.getParameter("BillingCity"));
    	pWorkOrder.setBillingState(pReq.getParameter("BillingState"));
    	pWorkOrder.setBillingZip(pReq.getParameter("BillingZip"));
    	pWorkOrder.setClientProceedDate(pReq.getParameter("ClientProceedDate"));
    	pWorkOrder.setOtherPromises(pReq.getParameter("OtherPromises"));
    	pWorkOrder.setFeeAcceptedFlag(pReq.getParameter("FeeAcceptedFlag"));
        return true;
    }

    public boolean handleWorkOrder(HttpServletRequest pReq, HttpServletResponse pRes){
        if ( (setWorkOrderParameter(pReq, mAgreement.getWorkOrder())) &&
        		(setLeadFormParameter(pReq, mAgreement.getWorkOrder().getLeadForm()))) {
            try{
                mOrderSQLHandler.insertWorkOrder(mAgreement);
                }catch(Exception e){
                e.printStackTrace();
                return false;                
                } 
            return true;
        }else {
            return false;
        }
    }
    
    public boolean handleUpdateWorkOrder(HttpServletRequest pReq, HttpServletResponse pRes){
        if ( (setWorkOrderParameter(pReq, mAgreement.getWorkOrder())) &&
        		(setLeadFormParameter(pReq, mAgreement.getWorkOrder().getLeadForm()))) {
            try{
                mOrderSQLHandler.updateWorkOrder(mAgreement);
                }catch(Exception e){
                e.printStackTrace();
                return false;                
                } 
            return true;
        }else {
            return false;
        }
    }
    
    public boolean handledeleteWorkOrder(HttpServletRequest pReq, HttpServletResponse pRes){
        try{
        	long id = Long.parseLong(pReq.getParameter("ID"));
            mOrderSQLHandler.deleteWorkOrder(id);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    
    public boolean handleRestoreDeclinedWorkOrder(HttpServletRequest pReq, HttpServletResponse pRes){
        try{
        	long id = Long.parseLong(pReq.getParameter("ID"));
            mOrderSQLHandler.restoreDeclinedWorkOrder(id);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    private boolean setAgreementParameter(HttpServletRequest pReq, Agreement pAgreement){
    	WorkOrder pWorkOrder = pAgreement.getWorkOrder();
    	LeadForm pLeadForm = pWorkOrder.getLeadForm();
    	
    	pAgreement.setProjectNo(pReq.getParameter("ProjectNo"));
    	pAgreement.setProjectName(pReq.getParameter("ProjectName"));
        pAgreement.setSpecialConditions(pReq.getParameter("SpecialConditions"));
        pAgreement.setFeeArrangement(pReq.getParameter("FeeArrangement"));
        
        pWorkOrder.setRetainer(pReq.getParameter("Retainer"));
        
        pLeadForm.setScope(pReq.getParameter("ScopeOfServices"));
        return true;
    }

    public boolean handleAgreement(HttpServletRequest pReq, HttpServletResponse pRes){
        if (setAgreementParameter(pReq, mAgreement)) {
            try{
                mOrderSQLHandler.insertAgreement(mAgreement);
                }catch(Exception e){
                e.printStackTrace();
                return false;                
                } 
            return true;
        }else {
            return false;
        }
    }
    public boolean handleRestoreAgreements(HttpServletRequest pReq, HttpServletResponse pRes){
        try{
        	long id = Long.parseLong(pReq.getParameter("ID"));
            mOrderSQLHandler.restoreAgreements(id);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}