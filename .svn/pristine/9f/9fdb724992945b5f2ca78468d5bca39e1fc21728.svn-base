/*
 * Created on Sep 11, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.fixedincome;

import harrier.income.com.compute.CIndexCalculator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

import app.Connect;
import app.IndexCalculatorCollection;
import app.NewIndexForm;

import com.harrier.initializeation.ConnectInit;


public class QueryClass {
	static Logger Logging = Logger.getLogger(QueryClass.class);
    private static Connection con11 = null;
 //   public Properties config;
    static Connect con1 = ConnectInit.getConnect();
    public static double[] fiftytwo_min_max=new double[2]; 
//	static CIndexCalculator ICalculator=new CIndexCalculator();

    /**
     * This method is used to establish 
     * connection with database
     */
    private static void getConnection() {
        if(con1.con!=null)
            con11=con1.con;
        else
        if (con11 == null)
            con11 = con1.getConnection();

    }
    private static void getConnectionForTransaction() {
        /*if(con1.conForTransaction!=null)
            con=con1.conForTransaction;
        else
        if (con == null)
            con = con1.getConnectionForTransaction();*/

    }
    
    public void fixedIncomeChangeSession(HttpServletRequest request){
        try{
            HttpSession session=request.getSession();
            Logging.debug("request.getparameter(basedate) "+request.getParameter("basedate"));
            //Logging.getDebug("request.getAttribute(basedate) "+request.getAttribute("basedate"));
            if(request.getParameter("new")!=null && request.getParameter("new").equals("yes")){
            	try{ 
                    Logging.debug("Removing computeIndex from session");
                    session.removeAttribute("FixedIncomeComputeIndexForm");
                    }catch(Exception e){}
            }
            if(request.getParameter("basedate")!=null){
                try{ 
                    Logging.debug("Removing computeIndex from session");
                    session.removeAttribute("FixedIncomeComputeIndexForm");
                    }catch(Exception e){}
            }else{
                Logging.debug("Removing newIndexForm &  indexComposition from session");
                try{ 
                    session.removeAttribute("FixedIncomeIndexCompositionForm");
                    }catch(Exception e){}
                    try{ 
                    session.removeAttribute("FixedIncomeDefineIndexForm");
                    }catch(Exception e){}
            }
            
        }catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static void getNextIndexId(String query, ActionForm form) {
        try {
            getConnection();
            FixedIncomeDefineIndexForm form1 = (FixedIncomeDefineIndexForm) form;
            PreparedStatement psmt = con11.prepareStatement(query);
            Statement stmt = con11.createStatement();
            ResultSet rs = stmt.executeQuery(ConnectInit.queries.getProperty("get_sequence_index_id"));
            rs.next();
            psmt.setInt(1, rs.getInt(1));
            form1.setI_indexID(String.valueOf(rs.getInt(1)));
        } catch (Exception e) {
            Logging.info("Error in generating next index id ");
        }
    }
    public ResultSet ReturnParentDetails(String s_parentIndex){
        getConnection();
        try{
       // Statement stmt1 = con11.createStatement();
        Logging.debug("Checking currency of parent ");
    	//ResultSet rs1 = stmt1.executeQuery("select * from index_master where parent_id='"+s_parentIndex+"'");
    	PreparedStatement psmt = con11.prepareStatement(ConnectInit.queries.getProperty("index_master_get_parent_detail"));
    	psmt.setString(1,s_parentIndex);
    	ResultSet rs1=psmt.executeQuery();
    	return rs1;
    	
        }catch (Exception e) {
            // TODO: handle exception
        }
    	return null;
    }
    /**
     * @param query   the query to insert a record into index master
     * @param form    the object of NewIndexForm containing all details of new index to be created
     * 
     */
    public static void fixedIncomeInsertIntoIndexMaster(String query, ActionForm form,String use_user,String user_id) throws Exception{

        try {
            Logging.info("Before connection  " + query);
            
            getConnectionForTransaction();
            Logging.info("Query is " + query);

            //	Connection con = getConnection();
            
            FixedIncomeDefineIndexForm form1 = (FixedIncomeDefineIndexForm) form;
            PreparedStatement psmt =form1.getCon().prepareStatement(query);
            Logging.info("After preparedstsmt : " + form1.getB_isIndexIsChildOrCustomized());

            String child = null;
            String customized = null;
            if (form1.getB_isIndexIsChildOrCustomized()==null){
                Logging.info("form1.getB_isIndexIsChildOrCustomized()==null " + form1.getB_isIndexIsChildOrCustomized());
                child = "n";
                customized = "n";
            }else if (form1.getB_isIndexIsChildOrCustomized() != null && form1.getB_isIndexIsChildOrCustomized().trim().equals("1")) {
                child = "y";
                customized = "n";
            } else {
                child = "n";
                customized = "y";
            }

            Logging.info("After form " + form1.getS_indexName());

            Statement stmt = form1.getCon().createStatement();
            ResultSet rs = stmt.executeQuery(ConnectInit.queries.getProperty("get_sequence_index_id"));
            rs.next();
            psmt.setInt(1, rs.getInt(1));
            Logging.debug("rs.getInt(1) in insertIntoIndexMaster : " + rs.getInt(1));
            form1.setI_indexID(String.valueOf(rs.getInt(1)));
            Logging.info("After form1.getI_indexID() in  " + form1.getI_indexID());
            if (form1.getS_alertPercent() == null || form1.getS_rejectionPercent() == null) {
                stmt = null;
                rs = null;
                stmt = form1.getCon().createStatement();
                rs = stmt.executeQuery(ConnectInit.queries.getProperty("insert_new_index"));//"select alert_percentage,rejection_percentage from information_schema.system_configuration");
            }

            psmt.setString(2, form1.getS_indexName().trim());
            Logging.info("After preparedstsmt2");
            psmt.setString(3, form1.getD_creationDate());
            Logging.info("After preparedstsmt3 ::: "+form1.getB_isActive());
            if(form1.getB_isActive()!=null && form1.getB_isActive().equals("on")){
                psmt.setString(4, "n"); 
            }else{
                psmt.setString(4, "y"); 
            }
            
            Logging.info("After preparedstsmt4");
            psmt.setString(5, form1.getD_baseDate());
            Logging.info(form1.getD_baseDate());
            psmt.setString(6, form1.getD_baseValue());
           
           
          //  String tinterval = form1.getI_timeInterval();
        //    Properties config = new Properties();
           
            psmt.setString(7,null);
          
            if (form1.getB_isCaptured() != null && form1.getB_isCaptured().trim().equals("on")) {
              if(form1.getS_capturedFrom().equals("0")){
                  psmt.setString(8, "n");
              }else{
                psmt.setString(8, "y");
              }
            } else {
                psmt.setString(8, "n");
            }
            Logging.info("After preparedstsmt8");
            psmt.setString(9, form1.getS_capturedFrom());
            Logging.info("After preparedstsmt9");
            psmt.setString(10, null);
            Logging.info("After preparedstsmt10");
            psmt.setString(11, null);
            Logging.info("After preparedstsmt11");
           
                psmt.setString(12, null);
         
            Logging.info("After preparedstsmt12");
            psmt.setString(13,null);//form1.getB_isNewIndexIsChild());  psmt.setString(13, child);
            Logging.info("After preparedstsmt13");
          /*  
            if (Integer.parseInt(form1.getS_parentIndex()) == 0) {
                psmt.setString(14, null);
            } else {
                psmt.setInt(14, Integer.parseInt(form1.getS_parentIndex()));
            }*/
            psmt.setInt(14, 0);
            Logging.info("After preparedstsmt14");
            if (form1.getS_indexType().equals("4")) {
                Logging.info("total returns index in insert ");                
                ResultSet rsfortotalreturns = null;
                PreparedStatement stmtfortotalreturns = con11.prepareStatement(ConnectInit.queries.getProperty("index_master_get_parent_detail"));
                stmtfortotalreturns.setString(1,form1.getS_parentIndex());
            	rsfortotalreturns=psmt.executeQuery();
                rsfortotalreturns.next();
                psmt.setInt(15, Integer.parseInt(rsfortotalreturns.getString("base_currency_id")));

            } else {
                psmt.setInt(15, Integer.parseInt(form1.getS_baseCurrency()));
            }
            Logging.info("After preparedstsmt15");
            psmt.setInt(16, Integer.parseInt(form1.getS_indexType()));
            Logging.info("check index type for total returns index" + form1.getS_indexType());
            Logging.info("After preparedstsmt16");
            String alert = null;

            rs.next();
            if (form1.getS_alertPercent() == null)
                alert = String.valueOf(rs.getFloat(1));
            else
                alert = form1.getS_alertPercent();
            psmt.setFloat(17, Float.parseFloat(alert));

            rs.next();
            if (form1.getS_rejectionPercent() == null)
                alert = String.valueOf(rs.getFloat(2));
            else
                alert = form1.getS_rejectionPercent();
            psmt.setFloat(18, Float.parseFloat(alert));

            Logging.info("After preparedstsmt18");

            psmt.setString(19, form1.getS_industryClassificationID());
            Logging.info("After preparedstsmt19");
            psmt.setString(20, form1.getS_type());
            Logging.info("After preparedstsmt20");
            if(form1.getB_computeSettlementValue()!=null && form1.getB_computeSettlementValue().trim().equals("on")){
            psmt.setString(21, "y");
            }else{
                psmt.setString(21, "n");   
            }
            Logging.info("After preparedstsmt21");
            if ((form1.getS_ISIN()).equals("")) {
                psmt.setString(22, null);
            } else {
                psmt.setString(22, form1.getS_ISIN());
            }
            Logging.info("After preparedstsmt22");
            psmt.setString(23, customized);//form1.getB_isIndexCustomizedVersion());
            Logging.info("After preparedstsmt23");
            try {
                Statement stclassficationid = form1.getCon().createStatement();
                rs = stclassficationid.executeQuery(ConnectInit.queries.getProperty("get_industry_classification_if_from_sys_conf"));
                rs.next();
                psmt.setInt(24, 0);
                psmt.setInt(25, rs.getInt(1));
            } catch (Exception e) {
                // TODO: handle exception
                Logging.info("Unable to insert industry classification detail" + e);
            }
            Logging.info("form1.getIs_testIndex()  " + form1.getIs_testIndex());
            if(form1.getIs_testIndex()!=null && form1.getIs_testIndex().trim().equals("on")){
            psmt.setString(26, "y");
            }else{
                psmt.setString(26, "n");  
            }
          /*  if(use_user!=null && use_user.equals("yes")){
            	 psmt.setString(27, user_id);  
            }*/
            
            /* do later
            if(form1.getName_list()!=null){
            	 psmt.setInt(27, Integer.parseInt(form1.getName_list()));
            } */
            psmt.setInt(27,0);
            
            /*psmt.setString(24,"0");
            Logging.getInfo("After preparedstsmt24");
            psmt.setString(25,form1.getS_industryClassificationID());
            Logging.getInfo("After preparedstsmt25");*/
            Logging.info("Inserting Record  " + psmt);
            psmt.executeUpdate();
            Logging.info("Record Inserted Successfully " + psmt);
            Logging.info("Record Inserted Successfully with con object as : "+form1.getCon());
            psmt.close();

        } catch (Exception e) {
            Logging.info("Error while creating prepared Statemwent" + e);
            throw e;
        }

    }

    public static void insertIntoIndexMaster1(String query, ActionForm form) {

        try {
            //	Logging.getInfo("Before connection  "+query);

            getConnection();
            Logging.info("Query is " + query);

            //	Connection con = getConnection();
            NewIndexForm form1 = (NewIndexForm) form;
            PreparedStatement psmt = form1.getDatabaseConnection().prepareStatement(query);
            //	Logging.getInfo("After preparedstsmt");
           
            String child = null;
            String customized = null;
            if (form1.getB_isIndexIsChildOrCustomized() == "1") {
                child = "y";
                customized = "n";
            } else {
                child = "n";
                customized = "y";
            }

            //   Logging.getInfo("After form "+form1.getS_indexName());

            Statement stmt = form1.getDatabaseConnection().createStatement();
            ResultSet rs = stmt.executeQuery(ConnectInit.queries.getProperty("get_max_index_id"));
            rs.next();
            psmt.setInt(1, rs.getInt(1));
            Logging.debug("rs.getInt(1) in insertIntoIndexMaster : " + rs.getInt(1));
            form1.setI_indexID(String.valueOf(rs.getInt(1)));
            if (form1.getS_alertPercent() == null || form1.getS_rejectionPercent() == null) {
                stmt = null;
                rs = null;
                stmt = form1.getDatabaseConnection().createStatement();
                rs = stmt.executeQuery(ConnectInit.queries.getProperty("insert_new_index"));//"select alert_percentage,rejection_percentage from information_schema.system_configuration");
            }

            psmt.setString(2, form1.getS_indexName().trim());
            //	Logging.getInfo("After preparedstsmt2");
            psmt.setString(3, form1.getD_creationDate());
            //	Logging.getInfo("After preparedstsmt3");
            psmt.setString(4, form1.getB_isActive());
            //	Logging.getInfo("After preparedstsmt4");
            psmt.setString(5, form1.getD_baseDate());
            Logging.info(form1.getD_baseDate());
            psmt.setString(6, form1.getD_baseValue());
            //psmt.setFloat(6,Float.parseFloat(form1.getD_baseValue()));
            //	Logging.getInfo(formatDate(form1.getD_baseValue()));
            Logging.info("in insert index Maser after query" + form1.getI_timeInterval());
            String tinterval = form1.getI_timeInterval();
     //       Properties config = new Properties();
            /*config.load(new FileInputStream("resources/System_config.properties"));
            if((tinterval==null)||(tinterval.equals("")))
            {
                tinterval=config.getProperty("time_interval");
            }*/
            //Logging.getInfo(tinterval);
            psmt.setString(7, tinterval);
            Logging.info("After preparedstsmt7" + form1.getI_timeInterval());
            if (form1.getB_isCaptured() == null) {
                psmt.setString(8, "n");
            } else {
                psmt.setString(8, form1.getB_isCaptured());
            }
            //	Logging.getInfo("After preparedstsmt8");
            psmt.setString(9, form1.getS_capturedFrom());
            //Logging.getInfo("After preparedstsmt9");
            psmt.setString(10, form1.getD_startTime());
            //	Logging.getInfo("After preparedstsmt10");
            psmt.setString(11, form1.getD_stopTime());
            //	Logging.getInfo("After preparedstsmt11");
            if ((form1.getS_reutersCode()).equals("")) {
                psmt.setString(12, null);
            } else {
                psmt.setString(12, form1.getS_reutersCode());
            }
            //	Logging.getInfo("After preparedstsmt12");
            psmt.setString(13, child);//form1.getB_isNewIndexIsChild());
            //	Logging.getInfo("After preparedstsmt13");
            if (Integer.parseInt(form1.getS_parentIndex()) == 0) {
                psmt.setString(14, null);
            } else {
                psmt.setInt(14, Integer.parseInt(form1.getS_parentIndex()));
            }
            //	Logging.getInfo("After preparedstsmt14");
            psmt.setInt(15, Integer.parseInt(form1.getS_baseCurrency()));
            //	Logging.getInfo("After preparedstsmt15");
            psmt.setInt(16, Integer.parseInt(form1.getS_indexType()));
            Logging.info("After preparedstsmt16");
            String alert = null;

            rs.next();
            if (form1.getS_alertPercent() == null)
                alert = String.valueOf(rs.getFloat(1));
            else
                alert = form1.getS_alertPercent();
            psmt.setFloat(17, Float.parseFloat(alert));

            rs.next();
            if (form1.getS_rejectionPercent() == null)
                alert = String.valueOf(rs.getFloat(2));
            else
                alert = form1.getS_rejectionPercent();
            psmt.setFloat(18, Float.parseFloat(alert));

            Logging.info("After preparedstsmt18");

            psmt.setString(19, form1.getS_industryClassificationID());
            Logging.info("After preparedstsmt19");
            psmt.setString(20, form1.getS_type());
            Logging.info("After preparedstsmt20");
            psmt.setString(21, form1.getB_computeSettlementValue());
            Logging.info("After preparedstsmt21");
            if ((form1.getS_ISIN()).equals("")) {
                psmt.setString(22, null);
            } else {
                psmt.setString(22, form1.getS_ISIN());
            }
            Logging.info("After preparedstsmt22");
            psmt.setString(23, customized);//form1.getB_isIndexCustomizedVersion());
            Logging.info("After preparedstsmt23");
            /*psmt.setString(24,"0");
            Logging.getInfo("After preparedstsmt24");
            psmt.setString(25,form1.getS_industryClassificationID());
            Logging.getInfo("After preparedstsmt25");*/

            //	psmt.executeUpdate();
            Logging.info("Record Inserted Successfully " + psmt);
            Logging.info("Record Inserted Successfully");


        } catch (Exception e) {
            Logging.info("Error while creating prepared Statemwent" + e.getMessage());
        }

    }
    
    
    /**
     * save the checked index as currency (currency which is checked by user)
     * save values for index in index_master,intra_day_indices,index_value_daily tables.
     * @param index_id
     * @param request
     */
    public static void SaveAsCurrencyIndex(String index_id,HttpServletRequest request) {
        try {
        	CIndexCalculator ICalculator = ConnectInit.getCIndexCalculator();
        	SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
    		Date dt = new Date();   		
    		String date=fr.format(dt).toString();
    		dt.getDate();//get system current date.
    		String time=dt.toString().split(" ")[3];//get system current time.
    		String curr_index_id="0";
    		long tableid=0;
    		String pindex_name=null,p_currid=null;
            getConnection();
            Logging.debug("index_id is "+index_id);
            PreparedStatement psmt1 = con11.prepareStatement(ConnectInit.queries.getProperty("get_indexname_currencyid_from_im"));//get base currency_id and index name for selected index.
            psmt1.setString(1, index_id);
            ResultSet indrs=psmt1.executeQuery();
            while(indrs.next()){
            	pindex_name=indrs.getString(1);
            	p_currid=indrs.getString(2);
            }
            Logging.debug(" pindex_name "+pindex_name+" p_currid is "+p_currid);
        	String[] crid=request.getParameterValues("currencyid");	//get list of currency_id for checked currencies.			   
            Logging.debug(" crid "+crid+" length is "+crid.length);
	   		for(int i=0;i<crid.length;i++){
	   			if(!(p_currid.equals(crid[i])) && !(crid[i].equals("0"))){
					Logging.debug(" values "+i+" is "+crid[i]);
					String val_indValue=request.getParameter("indValue:"+crid[i]);//get index value for currency checked.
					String val_tmcv=request.getParameter("tmcv:"+crid[i]);//get total mkt. cap. value for currency checked.
					String val_divisor=request.getParameter("divisor:"+crid[i]);//get divisor value for currency checked.
					Logging.debug(" val_indValue "+i+" is "+val_indValue);
					String curr_code=IndexCalculatorCollection.getCurrencyName(crid[i]);
					String indexName=pindex_name+":Curr:"+curr_code;//generate name of new currency index.
		            PreparedStatement psmt = con11.prepareStatement(ConnectInit.queries.getProperty("insert_in_index_master_as_currency"));//insert into index_master values for new currency index.
		            psmt.setString(2, indexName);	            
	                psmt.setString(3, date);
	                psmt.setString(4, val_indValue);
	                psmt.setInt(5, Integer.parseInt(index_id));
	                psmt.setString(6, crid[i]);
	                psmt.setString(7, "5");//set index_type as currency index.
	                psmt.setInt(8, Integer.parseInt(index_id));
		            ResultSet rs1 = con11.createStatement().executeQuery(ConnectInit.queries.getProperty("get_sequence_index_id"));//get next index_id from index_master table.
		            rs1.next();
		            curr_index_id=rs1.getString(1);
		            psmt.setString(1,curr_index_id );
		            Logging.debug("pstmt   : "+psmt);
		            psmt.executeUpdate();
		            
	//		          insert in intra_day_indices for currency index created.
		    		tableid =0;
		    		try {				
						Statement stm = Connect.con.createStatement();
						ResultSet rst = stm.executeQuery(ConnectInit.queries.getProperty("get_sequence_intra_day_indices_id"));//get next intra_day_indices_id from intra_day_indices table.
						Logging.debug(rst+"");
						rst.next();
						tableid = rst.getLong(1);	
						Logging.debug(""+tableid);
					} catch (SQLException e) {
						Logging.error("ERROR: "+e.getMessage());	
					}
								
					PreparedStatement pst_preStat = Connect.con.prepareStatement(ConnectInit.queries.getProperty("insert_into_intra_day_indices"));//insert values for new currency index in intra_day_indices table.
					Logging.debug("after firing query for insert");
					Logging.debug(""+curr_index_id);
					Logging.debug("   "+val_indValue);
					pst_preStat.setLong(1, tableid);
					pst_preStat.setString(5,curr_index_id);
					pst_preStat.setString(6, val_tmcv);
					pst_preStat.setString(2, val_indValue);
					pst_preStat.setString(3, date);			
					pst_preStat.setString(4, time);
					
					pst_preStat.executeUpdate();//execute query		   		
					Logging.debug("after execting insert_into_intra_day_indices");
	//	          insert in index_value_daily for currency index created.
		            /*try {
		    			Statement stm = Connect.con.createStatement();
		    			ResultSet rst = stm.executeQuery("select nextval('index_value_daily_id')");//get next index_value_daily_id from index_value_daily table.
		    			Logging.getDebug(rst);
		    			rst.next();
		    			tableid = rst.getLong(1);
		    			} catch (SQLException ae) {
		    				Logging.getError("ERROR: "+ae.getMessage());	
		    			}   */	 
					
					//code for 52 week low and high from 
					double index_close=Double.parseDouble(val_indValue);
                	fiftytwo_min_max =ICalculator.getFiftytwo_Week_HighLow(""+curr_index_id);
                	if(index_close > fiftytwo_min_max[0]){
                		fiftytwo_min_max[0]=index_close;
                	}
                	if(index_close < fiftytwo_min_max[1]){
                		fiftytwo_min_max[1]=index_close;
                	}
                	// code for 52 week low and high to
		    		Logging.debug("before insert query in index value daily");
		    		PreparedStatement pst_preStat1 = Connect.con.prepareStatement(ConnectInit.queries
		    				.getProperty("insert_into_index_value_daily"));//insert new currency index values in index_value_daily table.
		    		//pst_preStat1.setLong(1, tableid);				
		    		pst_preStat1.setDouble(1, Double.parseDouble(val_indValue));
		    		pst_preStat1.setDouble(2, Double.parseDouble(val_indValue));
		    		pst_preStat1.setDouble(3, Double.parseDouble(val_indValue));
		    		pst_preStat1.setDouble(4, Double.parseDouble(val_indValue));
		    		pst_preStat1.setString(5, (new Integer(curr_index_id).toString()));
		    		pst_preStat1.setString(6, date);
		    		pst_preStat1.setString(7, null);
		    		pst_preStat1.setDouble(8, Double.parseDouble(val_divisor));
		    		pst_preStat1.setDouble(9, Double.parseDouble(val_tmcv));
		    		pst_preStat1.setDouble(10,Double.parseDouble(val_tmcv));
		    		pst_preStat1.setDouble(11,Double.parseDouble(val_divisor));	
		    		pst_preStat1.setDouble(12,fiftytwo_min_max[0]);	
		    		pst_preStat1.setDouble(13,fiftytwo_min_max[1]);	
		    		
		    		pst_preStat1.executeUpdate();//execute query   
	   			}
	   		}
        } catch (Exception e) {
        	Logging.error("Error while inserting copy of parent " + e.getMessage());
        }
    }

    public static void createChildIndexIntoIndexMaster(String query, ActionForm form, String childIndexname) {

        try {
            //	Logging.getInfo("Before connection  "+query);

            getConnection();
            Logging.info("Query is " + query);

            //	Connection con = getConnection();
            NewIndexForm form1 = (NewIndexForm) form;
            PreparedStatement psmt = form1.getDatabaseConnection().prepareStatement(query);
            //	Logging.getInfo("After preparedstsmt");
           
            String child = null;
            String customized = null;
            if (form1.getB_isIndexIsChildOrCustomized() == "1") {
                child = "y";
                customized = "n";
            } else {
                child = "n";
                customized = "y";
            }

            //   Logging.getInfo("After form "+form1.getS_indexName());

            Statement stmt = form1.getDatabaseConnection().createStatement();
            ResultSet rs = stmt.executeQuery(ConnectInit.queries.getProperty("get_sequence_index_id"));
            rs.next();
            psmt.setInt(1, rs.getInt(1));
            form1.setI_indexID(String.valueOf(rs.getInt(1)));
            if (form1.getS_alertPercent() == null || form1.getS_rejectionPercent() == null) {
                stmt = null;
                rs = null;
                stmt = form1.getDatabaseConnection().createStatement();
                rs = stmt.executeQuery(ConnectInit.queries.getProperty("insert_new_index"));//"select alert_percentage,rejection_percentage from information_schema.system_configuration");
            }

            psmt.setString(2, form1.getS_indexName().trim() + "." + childIndexname);
            //	Logging.getInfo("After preparedstsmt2");
            psmt.setString(3, form1.getD_creationDate());
            //	Logging.getInfo("After preparedstsmt3");
            psmt.setString(4, form1.getB_isActive());
            //	Logging.getInfo("After preparedstsmt4");
            psmt.setString(5, form1.getD_baseDate());
            Logging.info(form1.getD_baseDate());
            psmt.setString(6, form1.getD_baseValue());
            //psmt.setFloat(6,Float.parseFloat(form1.getD_baseValue()));
            //	Logging.getInfo(formatDate(form1.getD_baseValue()));
            Logging.info("in insert index Maser after query" + form1.getI_timeInterval());
            String tinterval = form1.getI_timeInterval();
          //  Properties config = new Properties();
            /*config.load(new FileInputStream("resources/System_config.properties"));
            if((tinterval==null)||(tinterval.equals("")))
            {
                tinterval=config.getProperty("time_interval");
            }*/
            //Logging.getInfo(tinterval);
            psmt.setString(7, tinterval);
            Logging.info("After preparedstsmt7" + form1.getI_timeInterval());
            if (form1.getB_isCaptured() == null) {
                psmt.setString(8, "n");
            } else {
                psmt.setString(8, form1.getB_isCaptured());
            }
            //	Logging.getInfo("After preparedstsmt8");
            psmt.setString(9, form1.getS_capturedFrom());
            //Logging.getInfo("After preparedstsmt9");
            psmt.setString(10, form1.getD_startTime());
            //	Logging.getInfo("After preparedstsmt10");
            psmt.setString(11, form1.getD_stopTime());
            //	Logging.getInfo("After preparedstsmt11");
            if ((form1.getS_reutersCode()).equals("")) {
                psmt.setString(12, null);
            } else {
                psmt.setString(12, form1.getS_reutersCode());
            }
            //	Logging.getInfo("After preparedstsmt12");
            psmt.setString(13, child);//form1.getB_isNewIndexIsChild());
            //	Logging.getInfo("After preparedstsmt13");
            if (Integer.parseInt(form1.getS_parentIndex()) == 0) {
                psmt.setString(14, null);
            } else {
                psmt.setInt(14, Integer.parseInt(form1.getS_parentIndex()));
            }
            //	Logging.getInfo("After preparedstsmt14");
            psmt.setInt(15, Integer.parseInt(form1.getS_baseCurrency()));
            //	Logging.getInfo("After preparedstsmt15");
            psmt.setInt(16, Integer.parseInt(form1.getS_indexType()));
            Logging.info("After preparedstsmt16");
            String alert = null;

            rs.next();
            if (form1.getS_alertPercent() == null)
                alert = String.valueOf(rs.getFloat(1));
            else
                alert = form1.getS_alertPercent();
            psmt.setFloat(17, Float.parseFloat(alert));

            rs.next();
            if (form1.getS_rejectionPercent() == null)
                alert = String.valueOf(rs.getFloat(2));
            else
                alert = form1.getS_rejectionPercent();
            psmt.setFloat(18, Float.parseFloat(alert));

            Logging.info("After preparedstsmt18");

            psmt.setString(19, form1.getS_industryClassificationID());
            Logging.info("After preparedstsmt19");
            psmt.setString(20, form1.getS_type());
            Logging.info("After preparedstsmt20");
            psmt.setString(21, form1.getB_computeSettlementValue());
            Logging.info("After preparedstsmt21");
            if ((form1.getS_ISIN()).equals("")) {
                psmt.setString(22, null);
            } else {
                psmt.setString(22, form1.getS_ISIN());
            }
            Logging.info("After preparedstsmt22");
            psmt.setString(23, customized);//form1.getB_isIndexCustomizedVersion());
            Logging.info("After preparedstsmt23");
            /*psmt.setString(24,"0");
            Logging.getInfo("After preparedstsmt24");
            psmt.setString(25,form1.getS_industryClassificationID());
            Logging.getInfo("After preparedstsmt25");*/

            psmt.executeUpdate();
            Logging.info("Record Inserted Successfully " + psmt);
            Logging.info("Record Inserted Successfully");


        } catch (Exception e) {
            Logging.info("Error while creating prepared Statemwent" + e.getMessage());
        }

    }

    public static String formatDate() {
        SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
        Date dt = new Date();
        return fr.format(dt).toString();
    }

    public static void insertCompositionOfParent(String query, String indexID, String parentID,Connection dbConnection) {
        ResultSet rs1 = null;
        ResultSet rs = null;

        try {
            Logging.info("indexID is ---" + query);
            getConnectionForTransaction();
            PreparedStatement psmt = dbConnection.prepareStatement(query);
            psmt.setInt(1, Integer.parseInt(parentID));
            Logging.info("psmt is ---" + psmt);
            rs = psmt.executeQuery();
            psmt = null;
            psmt = dbConnection.prepareStatement(ConnectInit.queries.getProperty("insert_into_index_composition"));
            Logging.info("indexID is " + indexID);
            if (rs != null) {
                 while (rs.next()) {
			        psmt.setFloat(1, rs.getFloat(1));
			        psmt.setString(2, formatDate());
			        psmt.setFloat(3, Integer.parseInt(indexID));
			        psmt.setFloat(4, rs.getInt(2));
			        psmt.setDouble(5, rs.getDouble(3));
			       /* rs1 = dbConnection.createStatement().executeQuery((ConnectInit.getConnect()).queries.getProperty("get_sequence_index_stock_id"));
			        rs1.next();
			        psmt.setInt(1, rs1.getInt(1));*/
			        psmt.executeUpdate();              
                 }
			}
        } catch (Exception e) {
            Logging.info("QueryClass:Error while inserting composition of Parent " + e.getMessage());
        }
    }

    public static void fixedIncomeStoreCopyOfParent(String query, String parentIndex, String indexName, FixedIncomeDefineIndexForm form,String use_user,String user_id) {
        try {
            Logging.info("query   : "+query);
            getConnection();
            PreparedStatement psmt = form.getCon().prepareStatement(query);
            psmt.setString(2, indexName);
              Logging.info("form.getIs_testIndex()  aaa   : "+form.getIs_testIndex());
            if(form.getIs_testIndex()==null){
                 psmt.setString(3, "n");
            }else{
                 psmt.setString(3, "y");   
            }
            if(use_user!=null && use_user.equals("yes")){
            	psmt.setInt(4, Integer.parseInt(user_id));
            	psmt.setInt(5, Integer.parseInt(parentIndex));
            }else{
            	psmt.setInt(4, Integer.parseInt(parentIndex));
            }
            ResultSet rs1 = form.getCon().createStatement().executeQuery(ConnectInit.queries.getProperty("get_max_index_id"));
            rs1.next();
            form.setI_indexID(String.valueOf(rs1.getInt(1)));
            psmt.setInt(1, rs1.getInt(1));
            Logging.info("pstmt   : "+psmt);
            psmt.executeUpdate();

        } catch (Exception e) {
            Logging.info("Error while inserting copy of parent " + e.getMessage());
        }
    }
    
    public static void storeCopyOfParent1(String query, String parentIndex, String indexName, NewIndexForm form) {
        try {
            getConnection();
            PreparedStatement psmt = form.getDatabaseConnection().prepareStatement(query);
            psmt.setString(1, indexName);
            psmt.setInt(2, Integer.parseInt(parentIndex));
            /*ResultSet rs1 = con.createStatement().executeQuery("SELECT NEXTVAL('index_id')");
            rs1.next();
            form.setI_indexID(String.valueOf(rs1.getInt(1)));
            psmt.setInt(1, rs1.getInt(1));*/

            psmt.executeUpdate();

        } catch (Exception e) {
            Logging.info("Error while inserting copy of parent " + e.getMessage());
        }
    }

    public static int getIndexType(String indexid,String baseDate,Connection dbConnection) {
        if(baseDate==null){
            baseDate=QueryClass.formatDate();
        }
        int returnString = 6;
     //   if (con == null)
      //  dbConnection = con1.getConnectionForTransaction();
        	Logging.info("con : "+dbConnection+" : "+baseDate);
        try {
             PreparedStatement psmt = dbConnection.prepareStatement(ConnectInit.queries.getProperty("select_index_name"));
             psmt.setString(1, indexid);
             Logging.info("psmt : "+psmt);
            ResultSet indexdetails = psmt.executeQuery();
            if (indexdetails.next()) {
                 Logging.info("psmt a: "+indexdetails.getInt("index_type_id"));
                if (indexdetails.getInt("index_type_id") == 4) {
                    psmt = dbConnection.prepareStatement(ConnectInit.queries.getProperty("totalReturns_divisortoday"));
                    Logging.info("psmt b: "+psmt);
                    psmt.setString(1, baseDate);
                    psmt.setString(2, indexid);
                    Logging.info("psmt : "+psmt);
                    indexdetails = psmt.executeQuery();
                    if (indexdetails.next()) {
                        Logging.info("psmt1 : "+psmt);
                        boolean flag = false;
                        if (indexdetails.getString("adjusted_divisor") != null && !indexdetails.getString("adjusted_divisor").trim().equals("")) {
                            if (indexdetails.getString("index_closing_value") != null && !indexdetails.getString("index_closing_value").trim().equals("")) {
                                flag = true;

                                Logging.info("psmt2 : "+psmt);
                                psmt = dbConnection.prepareStatement(ConnectInit.queries.getProperty("totalReturns_divisortoday"));
                                psmt.setString(1, baseDate);
                                psmt.setString(2, indexid);
                                indexdetails = psmt.executeQuery();
                                if (indexdetails.next()) {
                                    flag = false;
                                    if (indexdetails.getString("adjusted_divisor") != null && !indexdetails.getString("adjusted_divisor").trim().equals("")) {
                                        if (indexdetails.getString("index_closing_value") != null && !indexdetails.getString("index_closing_value").trim().equals("")) {
                                            flag = true;
                                            
                                            //code to check cash dividend
                                            Logging.info("psmt3 : "+psmt);
                                            
                                            psmt = dbConnection.prepareStatement(ConnectInit.queries.getProperty("totalreturns_lastclosingdate"));
                                            psmt.setString(1, baseDate);
                                            psmt.setString(2, indexid);
                                            Logging.info("psmt4 : "+psmt);                                            
                                            indexdetails = psmt.executeQuery();
                                            Logging.debug("indexdetails.first() : "+indexdetails.first());
                                          //   indexdetails.next();
                                             String lastclosingdate=indexdetails.getString("index_value_date");
                                             Logging.info("lastclosingdate : "+lastclosingdate);   
                                            psmt = dbConnection.prepareStatement(ConnectInit.queries.getProperty("totalreturns_cashdividend"));
                                            psmt.setString(1, lastclosingdate);
                                            psmt.setString(2, baseDate);
                                            psmt.setString(3, indexid);
                                            indexdetails = psmt.executeQuery();
                                            Logging.info("psmt 3.1 : "+psmt);
                                            if (indexdetails.next()) {
                                                Logging.info("psmt4 : "+psmt);
                                                
                                                    Logging.info("psmt5 : "+psmt);
                                                    if (indexdetails.getString(1) != null) {
                                                        Logging.info("psmt6 : "+psmt);
                                                        returnString = 0; 
                                                    }else  returnString = 5;   
                                                
                                            }else{
                                                returnString = 5;
                                            }
                                            
                                        }else    returnString = 4;
                                    }else    returnString = 4;
                                } else {
                                    // for computing Total returns index todays Previous index value and divisor should be available
                                    returnString = 4;
                                }
                            } else {
                                // for computing Total returns index todays closing index value and divisor should be available
                                returnString = 3;
                            }

                        }else  returnString = 3;

                    } else {
                        //todays divisor not available
                        returnString = 2;
                    }
                }else returnString = 0;
            } else {
                //This index is not difined and composed
                returnString = 1;
            }

        } catch (Exception e) {
            // TODO: handle exception
            //some database problem
            returnString = 6;
          //  e.printStackTrace();
            Logging.debug(e);
        }
        Logging.debug("returnString : "+returnString);
        return returnString;
    }
    
    public  int getTotalReturnError(String indexid,String baseDate,Connection dbConnection) {
        if(baseDate==null){
            baseDate=QueryClass.formatDate();
        }
        int returnString = 6;
     //   if (con == null)
      //  dbConnection = con1.getConnectionForTransaction();
        	Logging.info("con : "+dbConnection+" : "+baseDate);
        try {
             PreparedStatement psmt = dbConnection.prepareStatement(ConnectInit.queries.getProperty("select_index_name"));
             psmt.setString(1, indexid);
             Logging.info("psmt : "+psmt);
            ResultSet indexdetails = psmt.executeQuery();
           
               
                    psmt = dbConnection.prepareStatement(ConnectInit.queries.getProperty("totalReturns_divisortoday1"));
                    Logging.info("psmt b: "+psmt);
                    psmt.setString(1, baseDate);
                    psmt.setString(2, indexid);
                    Logging.info("psmt : "+psmt);
                    indexdetails = psmt.executeQuery();
                    if (indexdetails.next()) {
                        Logging.info("psmt1 : "+psmt);
                        boolean flag = false;
                        if (indexdetails.getString("adjusted_divisor") != null && !indexdetails.getString("adjusted_divisor").trim().equals("")) {
                            if (indexdetails.getString("index_closing_value") != null && !indexdetails.getString("index_closing_value").trim().equals("")) {
                                flag = true;

                                Logging.info("psmt2 : "+psmt);
                                psmt = dbConnection.prepareStatement(ConnectInit.queries.getProperty("totalReturns_divisortoday1"));
                                psmt.setString(1, baseDate);
                                psmt.setString(2, indexid);
                                indexdetails = psmt.executeQuery();
                                if (indexdetails.next()) {
                                    flag = false;
                                    if (indexdetails.getString("adjusted_divisor") != null && !indexdetails.getString("adjusted_divisor").trim().equals("")) {
                                        if (indexdetails.getString("index_closing_value") != null && !indexdetails.getString("index_closing_value").trim().equals("")) {
                                            flag = true;
                                            
                                            //code to check cash dividend
                                            Logging.info("psmt3 : "+psmt);
                                            
                                            psmt = dbConnection.prepareStatement(ConnectInit.queries.getProperty("totalreturns_lastclosingdate1"));
                                            psmt.setString(1, baseDate);
                                            psmt.setString(2, indexid);
                                            Logging.info("psmt4 : "+psmt);                                            
                                            indexdetails = psmt.executeQuery();
                                            Logging.debug("indexdetails.first() : "+indexdetails.first());
                                          //   indexdetails.next();
                                             String lastclosingdate=indexdetails.getString("index_value_date");
                                             Logging.info("lastclosingdate : "+lastclosingdate);   
                                            psmt = dbConnection.prepareStatement(ConnectInit.queries.getProperty("totalreturns_cashdividend1"));
                                            psmt.setString(1, lastclosingdate);
                                            psmt.setString(2, baseDate);
                                            psmt.setString(3, indexid);
                                            indexdetails = psmt.executeQuery();
                                            Logging.info("psmt 3.1 : "+psmt);
                                            if (indexdetails.next()) {
                                                Logging.info("psmt4 : "+psmt);
                                                
                                                    Logging.info("psmt5 : "+psmt);
                                                    if (indexdetails.getString(1) != null) {
                                                        Logging.info("psmt6 : "+psmt);
                                                        returnString = 0; 
                                                    }else  returnString = 5;   
                                                
                                            }else{
                                                returnString = 5;
                                            }
                                            
                                        }else    returnString = 4;
                                    }else    returnString = 4;
                                } else {
                                    // for computing Total returns index todays Previous index value and divisor should be available
                                    returnString = 4;
                                }
                            } else {
                                // for computing Total returns index todays closing index value and divisor should be available
                                returnString = 3;
                            }

                        }else  returnString = 3;

                    } else {
                        //todays divisor not available
                        returnString = 2;
                    }
               
             

        } catch (Exception e) {
            // TODO: handle exception
            //some database problem
            returnString = 6;
        //    e.printStackTrace();
            Logging.debug(e);
        }
        Logging.debug("returnString : "+returnString);
        return returnString;
    }

/*public static Connection getConnection() {
	Connection con=null;
	try {
		
		Properties rs = new Properties();
		rs.load(new FileInputStream("resources/database.properties"));
		String machine = rs.getProperty("machine");
		String port = rs.getProperty("port");
		String database = rs.getProperty("database");
		String user = rs.getProperty("user");
		String password = rs.getProperty("password");
		Class.forName("org.postgresql.Driver").newInstance();
		String url = "jdbc:postgresql://" + machine + ":" + port + "/"
		+ database;		
		con= DriverManager.getConnection(url, user, password); 
		Logging.getInfo("Connected successfully");
		
	} catch (SQLException e) {
		Logging.getInfo("connection" + e);
		System.exit(0);
		
	} catch (Exception e) {
		Logging.getInfo("connection1" + e);
	}
	return con; 	

}*/

}

