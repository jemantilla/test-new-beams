package com.newbeams.application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import com.newbeams.bean.Agreement;
import com.newbeams.bean.LeadForm;
import com.newbeams.bean.WorkOrder;
import com.newbeams.business.GenericService;
import com.newbeams.utility.DateUtility;

public class OrderSQLHandler extends GenericService{
    private static final String INSERT_LEADFORM = "Insert into leadform (ID, Call_Date, Intake_By, For_Which_Company, Caller_First_Name, "+
        "Caller_Last_Name, Caller_Company_Name, Phone, Facsimile, Mobile, Email, House, Street, "+
        "City, State, Zip, Tax_Id, Need,  Service, Scope, Property_Size, Shape, Former_Survey, From_Whom, "+
        "Survey_Date, Attention) "+
        "Values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";   
    
    private static final String UPDATE_LEADFORM = "Update LeadForm set Intake_By=?, For_Which_Company=?, Caller_First_Name=?, " +
    "Caller_Last_Name=?, Caller_Company_Name=?, Phone=?, Facsimile=?, Mobile=?, Email=?, House=?, Street=?, "+
    "City=?, State=?, Zip=?, Tax_Id=?, Need=?,  Service=?, Scope=?, Property_Size=?, Shape=?, Former_Survey=?, "+
    "From_Whom=?, Survey_Date=?, Attention=? Where ID=?";   
    
    private static final String UPDATE_LEADFORM_SCOPEOFSERVICE = "Update LeadForm set Scope = ? " + 
    	"Where ID = ?";
    
    private static final String INSERT_WORKORDER = "Insert into workorder (ID, Responded_By, Response_Date,  "+
    "Estimated_Service_Fee, Retainer,Date_Received,Amount_Type, Card_Number, Expiration_Date, Billing_House_Number, Billing_Street, "+
    "Billing_City, Billing_State, Billing_Zip, Client_Proceed_Date, Other_Promises, Fee_Accepted_Flag) "+ 
    "Values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    private static final String UPDATE_WORKORDER = "Update workorder set Responded_By=?, Response_Date=?,  "+
        "Estimated_Service_Fee=?, Retainer=?,Date_Received=?,Amount_Type=?, Card_Number=?, Expiration_Date=?, Billing_House_Number=?, Billing_Street=?, "+
        "Billing_City=?, Billing_State=?, Billing_Zip=?, Client_Proceed_Date=?, Other_Promises=?, Fee_Accepted_Flag=? "+ 
        " Where ID=?";
    
    private static final String UPDATE_WORKORDER_RETAINER = "Update WorkOrder set Retainer = ? " +
    	"Where ID = ?";

    private static final String INSERT_AGREEMENT = "Insert into agreement (ID, Agreement_Date, Project_No, Project_Name, "+
    	"Fee_Arrangement, Special_Conditions) Values (?,?,?,?,?,?)";
    
    private static final String PENDING_LEADFORMS = "Select ID, Call_Date,Intake_By, For_Which_Company, Caller_First_Name, "+
        "Caller_Last_Name, Caller_Company_Name, Phone, Facsimile, Mobile, Email, House, Street, "+
        "City, State, Zip, Tax_Id, Need, Service, Scope, Property_Size, Shape, Former_Survey, From_Whom, "+
        "Survey_Date, Attention from leadForm Where ID not in (select ID from WorkOrder) order by Call_Date desc";
    
    private static final String PENDING_WORKORDERS = "Select L.ID, L.Call_Date,L.Intake_By, L.For_Which_Company, L.Caller_First_Name, "+
    "L.Caller_Last_Name, L.Caller_Company_Name, L.Phone, L.Facsimile, L.Mobile, L.Email, L.House, L.Street, "+
    "L.City, L.State, L.Zip, L.Tax_Id, L.Need, L.Service, L.Scope, L.Property_Size, L.Shape, L.Former_Survey, L.From_Whom, "+
    "L.Survey_Date, L.Attention,  W.Responded_By, W.Response_Date,W.Estimated_Service_Fee, W.Retainer,W.Date_Received,W.Amount_Type,"+
    "W.Card_Number, W.Expiration_Date, W.Billing_House_Number, W.Billing_Street, "+
    "W.Billing_City, W.Billing_State, W.Billing_Zip, W.Client_Proceed_Date, W.Other_Promises ,W.Fee_Accepted_Flag "+ 
    "from workOrder W, leadForm L Where W.ID=L.ID and L.ID not in (select ID from agreement) and "+
    "W.Fee_Accepted_Flag != 'declined' ";
    
    private static final String PENDING_WORKORDERS_IDS = "select L.ID from workOrder W, leadForm L "+ 
    "Where W.ID=L.ID and L.ID not in (select ID from agreement)	and "+
    "W.Fee_Accepted_Flag != 'declined' order by L.Call_Date desc";
    
    private static final String DECLINED_WORKORDERS = "Select L.ID, L.Call_Date,L.Intake_By, L.For_Which_Company, L.Caller_First_Name, "+
    "L.Caller_Last_Name, L.Caller_Company_Name, L.Phone, L.Facsimile, L.Mobile, L.Email, L.House, L.Street, "+
    "L.City, L.State, L.Zip, L.Tax_Id, L.Need,  L.Service, L.Scope, L.Property_Size, L.Shape, L.Former_Survey, L.From_Whom, "+
    "L.Survey_Date, L.Attention, W.Responded_By, W.Response_Date,W.Estimated_Service_Fee, W.Retainer,W.Date_Received,W.Amount_Type,"+
    "W.Card_Number, W.Expiration_Date, W.Billing_House_Number, W.Billing_Street, "+
    "W.Billing_City, W.Billing_State, W.Billing_Zip, W.Client_Proceed_Date, W.Other_Promises ,W.Fee_Accepted_Flag "+ 
    "from workOrder W, leadForm L Where W.ID=L.ID and L.ID not in (select ID from agreement) and  W.Fee_Accepted_Flag = 'Declined'";
    
    private static final String DECLINED_WORKORDERS_IDS = "select L.ID from workOrder W, leadForm L "+ 
	"Where W.ID=L.ID and L.ID not in (select ID from agreement)	and W.Fee_Accepted_Flag = 'Declined' order by L.Call_Date desc";  
    
    private static final String LIST_AGREEMENTS = "Select L.ID, A.Agreement_Date, A.Project_No, A.Project_Name "+
	"from agreement A, leadForm L  Where  A.ID=L.ID order by Agreement_Date desc";

    private static final String SELECT_AGREEMENT_BY_ID = "Select L.ID, L.Call_Date,L.Intake_By, L.For_Which_Company, "+
	"L.Caller_First_Name, L.Caller_Last_Name, L.Caller_Company_Name, L.Phone, L.Facsimile, L.Mobile, L.Email, "+
	"L.House, L.Street, L.City, L.State, L.Zip, L.Tax_Id, L.Need,  L.Service, L.Scope, L.Property_Size, L.Shape, "+
	"L.Former_Survey, L.From_Whom, L.Survey_Date, L.Attention, W.Responded_By, W.Response_Date, "+
	"W.Estimated_Service_Fee, W.Retainer,W.Date_Received,W.Amount_Type,W.Card_Number, W.Expiration_Date, "+
	"W.Billing_House_Number, W.Billing_Street, W.Billing_City, W.Billing_State, W.Billing_Zip, W.Client_Proceed_Date, "+
	"W.Other_Promises ,W.Fee_Accepted_Flag, A.Agreement_Date, A.Project_No, A.Project_Name, A.Fee_Arrangement, "+
	"A.Special_Conditions from agreement A, workOrder W, leadForm L  Where  W.ID=? and L.ID=? and A.ID=?";
    
    private static final String DELETE_PENDING_LEADFORM = "Delete from leadForm Where ID=?";  
    
    private static final String DELETE_PENDING_WORKORDER = "Delete from workorder Where ID=?";
    
    private static final String RESTORE_DECLINED_WORKORDER = "Update workorder set Fee_Accepted_flag = '' Where ID=?";
    
    private static final String RESTORE_AGREEMENTS= "Delete  from agreement Where ID=?";
    	
    private ResultSet rs = null;
    private ResultSet rs1 = null;
    private ResultSet rs2 = null;

    ConnectionPool pool = ConnectionPool.getInstance();
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement ptmt = null;

    private long getLastID() throws SQLException{
        long lastID = 1;
        try{
            conn = pool.getConnection();
            stmt = conn.createStatement();
            String _qry ="Select Max(ID) From LeadForm";
            rs = stmt.executeQuery(_qry);
            if (rs.next()) {
                lastID = rs.getLong(1);
            }
        }catch (SQLException e){
            throw  e;
        }finally{
            try{
                rs.close();
                stmt.close();
                pool.setConnection(conn);
            }catch (SQLException e1){
                throw e1;
            }
        }
        return lastID;
    }

    private long getLastID1() throws SQLException{
       long lastID = 1;
       try{
           conn = pool.getConnection();
           stmt = conn.createStatement();
           String _qry ="Select Max(ID) From WOrkOrder";
           rs1 = stmt.executeQuery(_qry);
           if (rs1.next()) {
               lastID = rs1.getLong(1);
           }
       }catch (SQLException e){
           throw  e;
       }finally{
           try{
               rs1.close();
               stmt.close();
               pool.setConnection(conn);
           }catch (SQLException e1){
               throw e1;
           }
       }
       return lastID;
   }

    private long getLastID2() throws SQLException{
       long lastID = 1;
       try{
           conn = pool.getConnection();
           stmt = conn.createStatement();
           String _qry ="Select Max(ID) From Agreement";
           rs2 = stmt.executeQuery(_qry);
           if (rs2.next()) {
               lastID = rs2.getLong(1);
           }
       }catch (SQLException e){
           throw  e;
       }finally{
           try{
               rs2.close();
               stmt.close();
               pool.setConnection(conn);
           }catch (SQLException e1){
               throw e1;
           }
       }
       return lastID;
   }



    public int insertLeadForm(Agreement pAgreement) throws SQLException{
    	LeadForm pLeadForm = pAgreement.getWorkOrder().getLeadForm();
        // do insert into database
        int inserted = 0;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ptmt = null;

        try{
            long nextID = getLastID() + 1;
            System.out.println("nextID :" + nextID);
            conn = pool.getConnection();
            ptmt = conn.prepareStatement(INSERT_LEADFORM);
            ptmt.setLong(1, nextID);
            System.out.println("Date:"+pLeadForm.getCallDate());
            ptmt.setString(2, DateUtility.format(pLeadForm.getCallDate()));
            ptmt.setString(3, pLeadForm.getIntakeBy());
            ptmt.setString(4, pLeadForm.getForWhichCompany());
            ptmt.setString(5, pLeadForm.getCallerFirstName());
            ptmt.setString(6, pLeadForm.getCallerLastName());
            ptmt.setString(7, pLeadForm.getCallerCompanyName());
       //     ptmt.setString(6, pLeadForm.getPropertyAddress());
            ptmt.setString(8, pLeadForm.getPhone());
            ptmt.setString(9, pLeadForm.getFacsimile());
            ptmt.setString(10, pLeadForm.getMobile());
            ptmt.setString(11, pLeadForm.getEmail());
            ptmt.setString(12, pLeadForm.getHouse());
            ptmt.setString(13, pLeadForm.getStreet());
            ptmt.setString(14, pLeadForm.getCity());
            ptmt.setString(15, pLeadForm.getState());
            ptmt.setString(16, pLeadForm.getZip());
            ptmt.setString(17, pLeadForm.getTaxId());
       //     ptmt.setString(17, pLeadForm.getNotes());
            ptmt.setString(18, pLeadForm.getNeed());
            ptmt.setString(19, pLeadForm.getService());
            ptmt.setString(20, pLeadForm.getScope());
            ptmt.setString(21, pLeadForm.getPropertySize());
            ptmt.setString(22, pLeadForm.getShape());
            ptmt.setString(23, pLeadForm.getFormerSurvey());
            ptmt.setString(24, pLeadForm.getFromWhom());
            ptmt.setString(25, pLeadForm.getSurveyDate());
            ptmt.setString(26, pLeadForm.getAttention());
            
            inserted = ptmt.executeUpdate();    
            setLogDebug(nextID + " entered in to leadForm table");
            conn.commit();
        }catch (SQLException e){
            try{
                conn.rollback();
            }catch(SQLException e1){
                if (loggingError) {
                    setLogError("during rollback from insertProject");
                    e1.printStackTrace();
                }
            }
            throw  e;
        }finally{
            try{
                ptmt.close();
                pool.setConnection(conn);
            }catch (SQLException e1){
                throw e1;
            }
        }
        return inserted;
    }

    public Vector retrivePendingLeadForms(){
    	Vector pendingLeadForms = new Vector();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ptmt = null;

        try{
            conn = pool.getConnection();
            ptmt = conn.prepareStatement(PENDING_LEADFORMS);
            rs = ptmt.executeQuery();
            while (rs.next()) {
            	LeadForm leadForm = new LeadForm();
            	leadForm.setID(rs.getInt(1));
            	leadForm.setCallDate(rs.getDate(2));
            	leadForm.setIntakeBy(rs.getString(3));
            	leadForm.setForWhichCompany(rs.getString(4));
            	leadForm.setCallerFirstName(rs.getString(5));
            	leadForm.setCallerLastName(rs.getString(6));
            	leadForm.setCallerCompanyName(rs.getString(7));
            	leadForm.setPhone(rs.getString(8));
            	leadForm.setFacsimile(rs.getString(9));
            	leadForm.setMobile(rs.getString(10));
            	leadForm.setEmail(rs.getString(11));
            	leadForm.setHouse(rs.getString(12));
            	leadForm.setStreet(rs.getString(13));
            	leadForm.setCity(rs.getString(14));
            	leadForm.setState(rs.getString(15));
            	leadForm.setZip(rs.getString(16));
            	leadForm.setTaxId(rs.getString(17));
            	leadForm.setNeed(rs.getString(18));
            	leadForm.setService(rs.getString(19));
            	leadForm.setScope(rs.getString(20));
            	leadForm.setPropertySize(rs.getString(21));
            	leadForm.setShape(rs.getString(22));
            	leadForm.setFormerSurvey(rs.getString(23));
            	leadForm.setFromWhom(rs.getString(24));
            	leadForm.setSurveyDate(rs.getString(25));
            	leadForm.setAttention(rs.getString(26));
            	
            	pendingLeadForms.add(leadForm);
            }
            conn.commit();
        }catch (SQLException e){
        	e.printStackTrace();
            try{
                conn.rollback();
            }catch(SQLException e1){
                if (loggingError) {
                    setLogError("during rollback from retrivePendingLeadForms");
                }
                e1.printStackTrace();
            }
        }finally{
            try{
                ptmt.close();
                pool.setConnection(conn);
            }catch (SQLException e){
            	e.printStackTrace();
            }
        }
        return pendingLeadForms;
    }
    
    public int updateLeadForm(Agreement pAgreement) throws SQLException {
    	LeadForm pLeadForm = pAgreement.getWorkOrder().getLeadForm();
    	
        int inserted = 0;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
       // PreparedStatement ptmt1 = null;
        PreparedStatement ptmt = null;
        

        try{
            conn = pool.getConnection();
            ptmt = conn.prepareStatement(UPDATE_LEADFORM);
            
            ptmt.setString(1,pLeadForm.getIntakeBy());
            System.out.println("Intake By:"+pLeadForm.getIntakeBy());
            ptmt.setString(2,pLeadForm.getForWhichCompany());
            System.out.println("for which company:"+pLeadForm.getForWhichCompany());
            ptmt.setString(3,pLeadForm.getCallerFirstName());
            ptmt.setString(4,pLeadForm.getCallerLastName());
            ptmt.setString(5,pLeadForm.getCallerCompanyName());
            ptmt.setString(6,pLeadForm.getPhone());
            ptmt.setString(7,pLeadForm.getFacsimile());
            ptmt.setString(8,pLeadForm.getMobile());
            ptmt.setString(9,pLeadForm.getEmail());
            ptmt.setString(10,pLeadForm.getHouse());
            ptmt.setString(11,pLeadForm.getStreet());
            ptmt.setString(12,pLeadForm.getCity());
            ptmt.setString(13,pLeadForm.getState());
            ptmt.setString(14,pLeadForm.getZip());
            ptmt.setString(15,pLeadForm.getTaxId());
            ptmt.setString(16,pLeadForm.getNeed());
            ptmt.setString(17,pLeadForm.getService());
            ptmt.setString(18,pLeadForm.getScope());
            ptmt.setString(19,pLeadForm.getPropertySize());
            ptmt.setString(20,pLeadForm.getShape());
            ptmt.setString(21,pLeadForm.getFormerSurvey());
            ptmt.setString(22,pLeadForm.getFromWhom());
            ptmt.setString(23,pLeadForm.getSurveyDate());
            ptmt.setString(24,pLeadForm.getAttention());
            
            ptmt.setLong(25,pLeadForm.getID());
            System.out.println(ptmt.toString());
            inserted = ptmt.executeUpdate();
            setLogDebug(pLeadForm.getID() + " updated in leadform table");
            conn.commit();
        }catch (SQLException e){
            try{
                conn.rollback();
            }catch(SQLException e1){
                if (loggingError) {
                    setLogError("during rollback from insertProject");
                    e1.printStackTrace();
                }
            }
            throw  e;
        }finally{
            try{
                ptmt.close();
                pool.setConnection(conn);
            }catch (SQLException e1){
                throw e1;
            }
        }
        return inserted;
    	
    }
    
    public void deleteLeadForm(long pID) throws SQLException {
    	
    	ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ptmt = null;
        PreparedStatement ptmt1 = null;

        try{
            conn = pool.getConnection();
            ptmt = conn.prepareStatement(DELETE_PENDING_LEADFORM);
            ptmt.setLong(1, pID);
            System.out.println("ID"+pID);
            
            ptmt1 = conn.prepareStatement(DELETE_PENDING_WORKORDER);
            ptmt1.setLong(1, pID); 
            ptmt.execute();
            ptmt1.execute();           
            setLogDebug("ID =" + pID + "is deleted from leadform, workOrder table");
            conn.commit();
        }catch (SQLException e){
            try{
                conn.rollback();
            }catch(SQLException e1){
                if (loggingError) {
                    setLogError("during rollback from deleteProject");
                    e1.printStackTrace();
                }
            }
            throw  e;
        }finally{
            try{
                ptmt.close();
                ptmt1.close();
                pool.setConnection(conn);
            }catch (SQLException e1){
                throw e1;
            }
        }
      
    }    


    
    public int insertWorkOrder(Agreement pAgreement) throws SQLException{
    	WorkOrder pWorkOrder = pAgreement.getWorkOrder();
    	LeadForm pLeadForm = pWorkOrder.getLeadForm();	
    	
    	        
        int inserted = 0;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ptmt = null;
        PreparedStatement ptmt1 = null;
    	
    	
        try{
            conn = pool.getConnection();
            ptmt = conn.prepareStatement(INSERT_WORKORDER);
            ptmt1 = conn.prepareStatement(UPDATE_LEADFORM);
            
            ptmt1.setString(1,pLeadForm.getIntakeBy());
            ptmt1.setString(2,pLeadForm.getForWhichCompany());
            ptmt1.setString(3,pLeadForm.getCallerFirstName());
            System.out.println("first name is:"+pLeadForm.getCallerFirstName());
            ptmt1.setString(4,pLeadForm.getCallerLastName());
            ptmt1.setString(5,pLeadForm.getCallerCompanyName());
            ptmt1.setString(6,pLeadForm.getPhone());
            ptmt1.setString(7,pLeadForm.getFacsimile());
            ptmt1.setString(8,pLeadForm.getMobile());
            ptmt1.setString(9,pLeadForm.getEmail());
            ptmt1.setString(10,pLeadForm.getHouse());
            ptmt1.setString(11,pLeadForm.getStreet());
            ptmt1.setString(12,pLeadForm.getCity());
            ptmt1.setString(13,pLeadForm.getState());
            ptmt1.setString(14,pLeadForm.getZip());
            ptmt1.setString(15,pLeadForm.getTaxId());
            ptmt1.setString(16,pLeadForm.getNeed());
            ptmt1.setString(17,pLeadForm.getService());
            ptmt1.setString(18,pLeadForm.getScope());
            ptmt1.setString(19,pLeadForm.getPropertySize());
            ptmt1.setString(20,pLeadForm.getShape());
            ptmt1.setString(21,pLeadForm.getFormerSurvey());
            ptmt1.setString(22,pLeadForm.getFromWhom());
            ptmt1.setString(23,pLeadForm.getSurveyDate());
            ptmt1.setString(24,pLeadForm.getAttention());
            
            ptmt1.setLong(25,pLeadForm.getID());
                        
            inserted = ptmt1.executeUpdate();            
            System.out.println("ID taken from leadform is:"+pLeadForm.getID());
                                           
            ptmt.setLong(1, pLeadForm.getID());
            System.out.println("ID inserted into workorder is:"+pLeadForm.getID());
            ptmt.setString(2, pWorkOrder.getRespondedBy());
            System.out.println("service fee"+pWorkOrder.getRespondedBy());
            ptmt.setString(3, pWorkOrder.getResponseDate());
            ptmt.setString(4, pWorkOrder.getEstimatedServiceFee());
            System.out.println("service fee"+pWorkOrder.getEstimatedServiceFee());
            ptmt.setString(5, pWorkOrder.getRetainer());
            ptmt.setString(6, pWorkOrder.getDateReceived());
            ptmt.setString(7, pWorkOrder.getAmountType());
     //       ptmt.setString(6, pWorkOrder.getPayment());
            ptmt.setString(8, pWorkOrder.getCardNumber());
            ptmt.setString(9, pWorkOrder.getExpirationDate());
            ptmt.setString(10, pWorkOrder.getBillingHouseNumber());
            ptmt.setString(11, pWorkOrder.getBillingStreet());
            ptmt.setString(12, pWorkOrder.getBillingCity());
            ptmt.setString(13, pWorkOrder.getBillingState());
            ptmt.setString(14, pWorkOrder.getBillingZip());
            ptmt.setString(15, pWorkOrder.getClientProceedDate());
            ptmt.setString(16, pWorkOrder.getOtherPromises());
            ptmt.setString(17, pWorkOrder.getFeeAcceptedFlag());
            
            inserted = ptmt.executeUpdate();
            
            setLogDebug(pLeadForm.getID() + " entered in to workorder table");
            conn.commit();
        }catch (SQLException e){
            try{
                conn.rollback();
            }catch(SQLException e1){
                if (loggingError) {
                    setLogError("during rollback from insertProject");
                    e1.printStackTrace();
                }
            }
            throw  e;
        }finally{
            try{
                ptmt.close();
                ptmt1.close();
                pool.setConnection(conn);
            }catch (SQLException e1){
                throw e1;
            }
        }
        return inserted;
    }    
    
    public int updateWorkOrder(Agreement pAgreement) throws SQLException{
    	WorkOrder pWorkOrder = pAgreement.getWorkOrder();
    	LeadForm pLeadForm = pWorkOrder.getLeadForm();
    	
        // do insert into database
        int inserted = 0;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ptmt = null;
        PreparedStatement ptmt1 = null;

        try{
            //long nextID = getLastID1() + 1;
          //  System.out.println("nextID :" + nextID);
            conn = pool.getConnection();
            ptmt = conn.prepareStatement(UPDATE_WORKORDER);
            ptmt1 = conn.prepareStatement(UPDATE_LEADFORM);
            
            ptmt1.setString(1,pLeadForm.getIntakeBy());
            ptmt1.setString(2,pLeadForm.getForWhichCompany());
            ptmt1.setString(3,pLeadForm.getCallerFirstName());
            ptmt1.setString(4,pLeadForm.getCallerLastName());
            ptmt1.setString(5,pLeadForm.getCallerCompanyName());
            ptmt1.setString(6,pLeadForm.getPhone());
            ptmt1.setString(7,pLeadForm.getFacsimile());
            ptmt1.setString(8,pLeadForm.getMobile());
            ptmt1.setString(9,pLeadForm.getEmail());
            ptmt1.setString(10,pLeadForm.getHouse());
            ptmt1.setString(11,pLeadForm.getStreet());
            ptmt1.setString(12,pLeadForm.getCity());
            ptmt1.setString(13,pLeadForm.getState());
            ptmt1.setString(14,pLeadForm.getZip());
            ptmt1.setString(15,pLeadForm.getTaxId());
            ptmt1.setString(16,pLeadForm.getNeed());
            ptmt1.setString(17,pLeadForm.getService());
            ptmt1.setString(18,pLeadForm.getScope());
            ptmt1.setString(19,pLeadForm.getPropertySize());
            ptmt1.setString(20,pLeadForm.getShape());
            ptmt1.setString(21,pLeadForm.getFormerSurvey());
            ptmt1.setString(22,pLeadForm.getFromWhom());
            ptmt1.setString(23,pLeadForm.getSurveyDate());
            ptmt1.setString(24,pLeadForm.getAttention());
            
            ptmt1.setLong(25,pLeadForm.getID());
            
            inserted = ptmt1.executeUpdate();
            
            ptmt.setString(1, pWorkOrder.getRespondedBy());
            ptmt.setString(2, pWorkOrder.getResponseDate());
            ptmt.setString(3, pWorkOrder.getEstimatedServiceFee());
            System.out.println("updated value of service fee is:"+pWorkOrder.getEstimatedServiceFee());
            ptmt.setString(4, pWorkOrder.getRetainer());
            
            ptmt.setString(5, pWorkOrder.getDateReceived());
            ptmt.setString(6, pWorkOrder.getAmountType());
     //       ptmt.setString(6, pWorkOrder.getPayment());
            ptmt.setString(7, pWorkOrder.getCardNumber());
            ptmt.setString(8, pWorkOrder.getExpirationDate());
            ptmt.setString(9, pWorkOrder.getBillingHouseNumber());
            ptmt.setString(10, pWorkOrder.getBillingStreet());
            ptmt.setString(11, pWorkOrder.getBillingCity());
            ptmt.setString(12, pWorkOrder.getBillingState());
            ptmt.setString(13, pWorkOrder.getBillingZip());
            ptmt.setString(14, pWorkOrder.getClientProceedDate());
            ptmt.setString(15, pWorkOrder.getOtherPromises());
            ptmt.setString(16, pWorkOrder.getFeeAcceptedFlag());
            ptmt.setLong(17, pLeadForm.getID());
            inserted = ptmt.executeUpdate();
            
            setLogDebug(pLeadForm.getID() + " entered in to workorder table");
            conn.commit();
        }catch (SQLException e){
            try{
                conn.rollback();
            }catch(SQLException e1){
                if (loggingError) {
                    setLogError("during rollback from insertProject");
                    e1.printStackTrace();
                }
            }
            throw  e;
        }finally{
            try{
                ptmt.close();
                ptmt1.close();
                pool.setConnection(conn);
            }catch (SQLException e1){
                throw e1;
            }
        }
        return inserted;

    }
    
    public Vector retrivePendingWorkOrders(){
    	Vector pendingWorkOrders = new Vector();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
        ResultSet rs = null, rs1 = null;
        PreparedStatement ptmt = null, ptmt1 = null;

        try{
            conn = pool.getConnection();

            
            Hashtable pendingWorkOrdersTable = new Hashtable();
            ptmt = conn.prepareStatement(PENDING_WORKORDERS);
            rs = ptmt.executeQuery();
            while (rs.next()) {
            	WorkOrder workOrder = new WorkOrder();
            	LeadForm leadForm = new LeadForm();
            	workOrder.setLeadForm(leadForm);
            	
            	leadForm.setID(rs.getInt(1));
            	leadForm.setCallDate(rs.getDate(2));
            	leadForm.setIntakeBy(rs.getString(3));
            	leadForm.setForWhichCompany(rs.getString(4));
            	leadForm.setCallerFirstName(rs.getString(5));
            	leadForm.setCallerLastName(rs.getString(6));
            	leadForm.setCallerCompanyName(rs.getString(7));
            	leadForm.setPhone(rs.getString(8));
            	leadForm.setFacsimile(rs.getString(9));
            	leadForm.setMobile(rs.getString(10));
            	leadForm.setEmail(rs.getString(11));
            	leadForm.setHouse(rs.getString(12));
            	leadForm.setStreet(rs.getString(13));
            	leadForm.setCity(rs.getString(14));
            	leadForm.setState(rs.getString(15));
            	leadForm.setZip(rs.getString(16));
            	leadForm.setTaxId(rs.getString(17));
            	leadForm.setNeed(rs.getString(18));
            	leadForm.setService(rs.getString(19));
            	leadForm.setScope(rs.getString(20));
            	leadForm.setPropertySize(rs.getString(21));
            	leadForm.setShape(rs.getString(22));
            	leadForm.setFormerSurvey(rs.getString(23));
            	leadForm.setFromWhom(rs.getString(24));
            	leadForm.setSurveyDate(rs.getString(25));
            	leadForm.setAttention(rs.getString(26));
            	
            	
            	workOrder.setRespondedBy(rs.getString(27));
            	workOrder.setResponseDate(rs.getString(28));
            	workOrder.setEstimatedServiceFee(rs.getString(29));
            	workOrder.setRetainer(rs.getString(30));
            	workOrder.setDateReceived(rs.getString(31));
            	workOrder.setAmountType(rs.getString(32));
            	workOrder.setCardNumber(rs.getString(33));
            	workOrder.setExpirationDate(rs.getString(34));
            	workOrder.setBillingHouseNumber(rs.getString(35));
            	workOrder.setBillingStreet(rs.getString(36));
            	workOrder.setBillingCity(rs.getString(37));
            	workOrder.setBillingState(rs.getString(38));
            	workOrder.setBillingZip(rs.getString(39));
            	workOrder.setClientProceedDate(rs.getString(40));
            	workOrder.setOtherPromises(rs.getString(41));
            	workOrder.setFeeAcceptedFlag(rs.getString(42));
            	
            	pendingWorkOrdersTable.put(new Integer(leadForm.getID()), workOrder);
            }
            
            ptmt1 = conn.prepareStatement(PENDING_WORKORDERS_IDS);
            rs1 = ptmt1.executeQuery();
            while (rs1.next()){
            	int id = rs1.getInt(1);
            	WorkOrder workOrder = (WorkOrder) pendingWorkOrdersTable.get(new Integer(id));
            	pendingWorkOrders.add(workOrder);
            }
            
            conn.commit();
        }catch (SQLException e){
        	e.printStackTrace();
            try{
                conn.rollback();
            }catch(SQLException e1){
                if (loggingError) {
                    setLogError("during rollback from retrivePendingWorkOrders");
                }
                e1.printStackTrace();
            }
        }finally{
            try{
                ptmt.close();
                pool.setConnection(conn);
            }catch (SQLException e){
            	e.printStackTrace();
            }
        }
        return pendingWorkOrders;
    } 
    
    public Vector retriveDeclinedWorkOrders(){
    	Vector declinedWorkOrders = new Vector();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
        ResultSet rs = null, rs1 = null;
        PreparedStatement ptmt = null, ptmt1 = null;


        try{
        	conn = pool.getConnection();
            Hashtable declinedWorkOrdersTable = new Hashtable();
            ptmt = conn.prepareStatement(DECLINED_WORKORDERS);
            rs = ptmt.executeQuery();
            while (rs.next()) {
            	WorkOrder workOrder = new WorkOrder();
            	LeadForm leadForm = new LeadForm();
            	workOrder.setLeadForm(leadForm);
            	
            	leadForm.setID(rs.getInt(1));
            	leadForm.setCallDate(rs.getDate(2));
            	leadForm.setIntakeBy(rs.getString(3));
            	leadForm.setForWhichCompany(rs.getString(4));
            	leadForm.setCallerFirstName(rs.getString(5));
            	leadForm.setCallerLastName(rs.getString(6));
            	leadForm.setCallerCompanyName(rs.getString(7));
            	leadForm.setPhone(rs.getString(8));
            	leadForm.setFacsimile(rs.getString(9));
            	leadForm.setMobile(rs.getString(10));
            	leadForm.setEmail(rs.getString(11));
            	leadForm.setHouse(rs.getString(12));
            	leadForm.setStreet(rs.getString(13));
            	leadForm.setCity(rs.getString(14));
            	leadForm.setState(rs.getString(15));
            	leadForm.setZip(rs.getString(16));
            	leadForm.setTaxId(rs.getString(17));
            	leadForm.setNeed(rs.getString(18));
            	leadForm.setService(rs.getString(19));
            	leadForm.setScope(rs.getString(20));
            	leadForm.setPropertySize(rs.getString(21));
            	leadForm.setShape(rs.getString(22));
            	leadForm.setFormerSurvey(rs.getString(23));
            	leadForm.setFromWhom(rs.getString(24));
            	leadForm.setSurveyDate(rs.getString(25));
            	leadForm.setAttention(rs.getString(26));
            	
            	
            	workOrder.setRespondedBy(rs.getString(27));
            	workOrder.setResponseDate(rs.getString(28));
            	workOrder.setEstimatedServiceFee(rs.getString(29));
            	workOrder.setRetainer(rs.getString(30));
            	workOrder.setDateReceived(rs.getString(31));
            	workOrder.setAmountType(rs.getString(32));
            	workOrder.setCardNumber(rs.getString(33));
            	workOrder.setExpirationDate(rs.getString(34));
            	workOrder.setBillingHouseNumber(rs.getString(35));
            	workOrder.setBillingStreet(rs.getString(36));
            	workOrder.setBillingCity(rs.getString(37));
            	workOrder.setBillingState(rs.getString(38));
            	workOrder.setBillingZip(rs.getString(39));
            	workOrder.setClientProceedDate(rs.getString(40));
            	workOrder.setOtherPromises(rs.getString(41));
            	workOrder.setFeeAcceptedFlag(rs.getString(42));
            	
            	declinedWorkOrdersTable.put(new Integer(leadForm.getID()), workOrder);
            }
            
            ptmt1 = conn.prepareStatement(DECLINED_WORKORDERS_IDS);
            rs1 = ptmt1.executeQuery();
            while (rs1.next()){
            	int id = rs1.getInt(1);
            	WorkOrder workOrder = (WorkOrder) declinedWorkOrdersTable.get(new Integer(id));
            	declinedWorkOrders.add(workOrder);
            }
            
            conn.commit();
        }catch (SQLException e){
        	e.printStackTrace();
            try{
                conn.rollback();
            }catch(SQLException e1){
                if (loggingError) {
                    setLogError("during rollback from retrivePendingWorkOrders");
                }
                e1.printStackTrace();
            }
        }finally{
            try{
                ptmt.close();
                pool.setConnection(conn);
            }catch (SQLException e){
            	e.printStackTrace();
            }
        }
        return declinedWorkOrders;
    } 
    
            
    public void deleteWorkOrder(long pID) throws SQLException {
    	
    	ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ptmt = null;
        PreparedStatement ptmt1 = null;

        try{
            conn = pool.getConnection();
            ptmt = conn.prepareStatement(DELETE_PENDING_LEADFORM);
            ptmt.setLong(1, pID);
            System.out.println("ID"+pID);
            
            ptmt1 = conn.prepareStatement(DELETE_PENDING_WORKORDER);
            ptmt1.setLong(1, pID); 
            ptmt.execute();
            ptmt1.execute();           
            setLogDebug("ID =" + pID + "is deleted from leadform, workOrder table");
            conn.commit();
        }catch (SQLException e){
            try{
                conn.rollback();
            }catch(SQLException e1){
                if (loggingError) {
                    setLogError("during rollback from deleteProject");
                    e1.printStackTrace();
                }
            }
            throw  e;
        }finally{
            try{
                ptmt.close();
                ptmt1.close();
                pool.setConnection(conn);
            }catch (SQLException e1){
                throw e1;
            }
        }
      
    } 
    
    public void restoreDeclinedWorkOrder(long pID) throws SQLException {
    	
    	ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ptmt = null;

        try{
            conn = pool.getConnection(); 
            
            ptmt = conn.prepareStatement(RESTORE_DECLINED_WORKORDER);
            ptmt.setLong(1, pID);            
            ptmt.execute();
            setLogDebug("ID =" + pID + "is restored from leadform, workOrder table");
            conn.commit();
        }catch (SQLException e){
            try{
                conn.rollback();
            }catch(SQLException e1){
                if (loggingError) {
                    setLogError("during rollback from deleteProject");
                    e1.printStackTrace();
                }
            }
            throw  e;
        }finally{
            try{
                ptmt.close();
                pool.setConnection(conn);
            }catch (SQLException e1){
                throw e1;
            }
        }
      
    }   



    public int insertAgreement(Agreement pAgreement) throws SQLException{
    	WorkOrder pWorkOrder = pAgreement.getWorkOrder();
    	LeadForm pLeadForm = pWorkOrder.getLeadForm();
    	
         // do insert into database
         int inserted = 0;
         ConnectionPool pool = ConnectionPool.getInstance();
         Connection conn = null;
         Statement stmt = null;
         PreparedStatement ptmt = null, ptmt1 = null, ptmt2 = null;

         try{
             long nextID = getLastID2() + 1;
             System.out.println("nextID :" + nextID);
             conn = pool.getConnection();
             
             ptmt = conn.prepareStatement(INSERT_AGREEMENT);
             ptmt.setLong(1,  pLeadForm.getID());
             ptmt.setString(2, DateUtility.format(pAgreement.getAgreementDate()));
             ptmt.setString(3, pAgreement.getProjectNo());
             ptmt.setString(4, pAgreement.getProjectName());
             ptmt.setString(5,pAgreement.getFeeArrangement());
             ptmt.setString(6, pAgreement.getSpecialConditions());
             inserted = ptmt.executeUpdate();
             
             ptmt1 = conn.prepareStatement(UPDATE_WORKORDER_RETAINER);
             ptmt1.setString(1, pWorkOrder.getRetainer());
             ptmt1.setLong(2, pLeadForm.getID());
             inserted = ptmt1.executeUpdate();
             
             ptmt2 = conn.prepareStatement(UPDATE_LEADFORM_SCOPEOFSERVICE);
             ptmt2.setString(1, pLeadForm.getScope());
             ptmt2.setLong(2, pLeadForm.getID());
             inserted = ptmt2.executeUpdate();
                
             setLogDebug(nextID + " entered in to Agreement table");
             conn.commit();
         }catch (SQLException e){
             try{
                 conn.rollback();
             }catch(SQLException e1){
                 if (loggingError) {
                     setLogError("during rollback from insertAgreement");
                     e1.printStackTrace();
                 }
             }
             throw  e;
         }finally{
             try{
                 ptmt.close();
                 pool.setConnection(conn);
             }catch (SQLException e1){
                 throw e1;
             }
         }
         return inserted;
     }
    public Vector retriveListOfAgreements(){
    	Vector listOfAgreements = new Vector();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ptmt = null;

        try{
            conn = pool.getConnection();
            ptmt = conn.prepareStatement(LIST_AGREEMENTS);
            rs = ptmt.executeQuery();
            while (rs.next()) {
            	Agreement agreement = new Agreement();
            	WorkOrder workOrder = new WorkOrder();
            	LeadForm leadForm = new LeadForm();
            	workOrder.setLeadForm(leadForm);
            	agreement.setWorkOrder(workOrder);
            	
            	leadForm.setID(rs.getInt(1));
            	
            	agreement.setAgreementDate(rs.getDate(2));
            	agreement.setProjectNo(rs.getString(3));
            	agreement.setProjectName(rs.getString(4));
            	
            	listOfAgreements.add(agreement);
            }
            
            conn.commit();
        }catch (SQLException e){
        	e.printStackTrace();
            try{
                conn.rollback();
            }catch(SQLException e1){
                if (loggingError) {
                    setLogError("during rollback from listOfAgreements");
                }
                e1.printStackTrace();
            }
        }finally{
            try{
                ptmt.close();
                pool.setConnection(conn);
            }catch (SQLException e){
            	e.printStackTrace();
            }
        }
        return listOfAgreements;
        }
         
    
    public void restoreAgreements(long pID) throws SQLException {
    	
    	ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ptmt = null;

        try{
            conn = pool.getConnection(); 
            
            ptmt = conn.prepareStatement(RESTORE_AGREEMENTS);
            ptmt.setLong(1, pID);            
            ptmt.execute();
            setLogDebug("ID =" + pID + "is restored from Agreement table");
            conn.commit();
        }catch (SQLException e){
            try{
                conn.rollback();
            }catch(SQLException e1){
                if (loggingError) {
                    setLogError("during rollback from deleteProject");
                    e1.printStackTrace();
                }
            }
            throw  e;
        }finally{
            try{
                ptmt.close();
                pool.setConnection(conn);
            }catch (SQLException e1){
                throw e1;
            }
        }
      
    }
    public Agreement getAgreementByID(int pID){
        Agreement agreement = new Agreement();
       	WorkOrder workOrder = new WorkOrder();
      	LeadForm leadForm = new LeadForm();
       	workOrder.setLeadForm(leadForm);
       	agreement.setWorkOrder(workOrder);

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ptmt = null;

        try{
            conn = pool.getConnection();
            ptmt = conn.prepareStatement(SELECT_AGREEMENT_BY_ID);
            ptmt.setInt(1, pID);
            ptmt.setInt(2, pID);
            ptmt.setInt(3, pID);
            rs = ptmt.executeQuery();
            if (rs.next()) {
            	
            	leadForm.setID(rs.getInt(1));
            	leadForm.setCallDate(rs.getDate(2));
            	leadForm.setIntakeBy(rs.getString(3));
            	leadForm.setForWhichCompany(rs.getString(4));
            	leadForm.setCallerFirstName(rs.getString(5));
            	leadForm.setCallerLastName(rs.getString(6));
            	leadForm.setCallerCompanyName(rs.getString(7));
            	leadForm.setPhone(rs.getString(8));
            	leadForm.setFacsimile(rs.getString(9));
            	leadForm.setMobile(rs.getString(10));
            	leadForm.setEmail(rs.getString(11));
            	leadForm.setHouse(rs.getString(12));
            	leadForm.setStreet(rs.getString(13));
            	leadForm.setCity(rs.getString(14));
            	leadForm.setState(rs.getString(15));
            	leadForm.setZip(rs.getString(16));
            	leadForm.setTaxId(rs.getString(17));
            	leadForm.setNeed(rs.getString(18));            	
            	leadForm.setService(rs.getString(19));
            	leadForm.setScope(rs.getString(20));
            	leadForm.setPropertySize(rs.getString(21));
            	leadForm.setShape(rs.getString(22));
            	leadForm.setFormerSurvey(rs.getString(23));
            	leadForm.setFromWhom(rs.getString(24));
            	leadForm.setSurveyDate(rs.getString(25));
            	leadForm.setAttention(rs.getString(26));
            	       	
            	
            	workOrder.setRespondedBy(rs.getString(27));
            	workOrder.setResponseDate(rs.getString(28));
            	workOrder.setEstimatedServiceFee(rs.getString(29));
            	workOrder.setRetainer(rs.getString(30));
            	workOrder.setDateReceived(rs.getString(31));
            	workOrder.setAmountType(rs.getString(32));
            	workOrder.setCardNumber(rs.getString(33));
            	workOrder.setExpirationDate(rs.getString(34));
            	workOrder.setBillingHouseNumber(rs.getString(35));
            	workOrder.setBillingStreet(rs.getString(36));
            	workOrder.setBillingCity(rs.getString(37));
            	workOrder.setBillingState(rs.getString(38));
            	workOrder.setBillingZip(rs.getString(39));
            	workOrder.setClientProceedDate(rs.getString(40));
            	workOrder.setOtherPromises(rs.getString(41));
            	workOrder.setFeeAcceptedFlag(rs.getString(42));
            	
            	agreement.setAgreementDate(rs.getDate(43));
            	agreement.setProjectNo(rs.getString(44));
            	agreement.setProjectName(rs.getString(45));
            	agreement.setFeeArrangement(rs.getString(46));
            	agreement.setSpecialConditions(rs.getString(47));
            }
            
            conn.commit();
        }catch (SQLException e){
        	e.printStackTrace();
            try{
                conn.rollback();
            }catch(SQLException e1){
                if (loggingError) {
                    setLogError("during rollback from listOfAgreements");
                }
                e1.printStackTrace();
            }
        }finally{
            try{
                ptmt.close();
                pool.setConnection(conn);
            }catch (SQLException e){
            	e.printStackTrace();
            }
        }
        return agreement;
    }
}