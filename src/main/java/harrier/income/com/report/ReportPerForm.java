package harrier.income.com.report;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.validator.ValidatorForm;

import app.AcessControl;
import app.Connect;
import app.ListTypeClass1;

import com.harrier.initializeation.ConnectInit;

/**
 * @author Ashish
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReportPerForm extends ValidatorForm{
	Logger Logging = Logger.getLogger(ReportPerForm.class);
	private String selectReport =null ,selectprefrence=null,to=null,
		selectStock=null,filter="0",exe=null,difference="0",date="day",hiddenVar="no",check=null,hiddenVar1="no",
		exchange_name=null,index_name=null,stock_name1=null,update=null,hiddenVar2="no";
	private String index=null; 
	
	private Collection stockCollection=null,
	filterCollection=null,exeCollection=null,diffCollection=null;
	private Collection indexCollection = null;
	private Collection prefrencecollection = null;
	ResultSet rst;
	private ArrayList tableData=null;
	private String radioButton;
	public String resetButton=null;
	public String updateButton=null;
	public String hideFilter="notHide";
	private ArrayList indlist=new ArrayList();
//	app.Connect con=new app.Connect();
	Connect con = ConnectInit.getConnect();
	private Collection indCollection =	null;
	Connection con2;
	private Collection stockListOnIndexSelection =	null;
	private String viewButton=null;
	private String[] selectedStocks=null;
	private String hidVar=null;
	private String temp1=null; 
	private String[] indices=null;
	private String indicesHidVar=null;
	private String stockOfIndex=null;
	private Collection stockListOnExchangeSelection=null;
	private String stockOfExchange=null;
	private String resetFilterHidVar=null;
	private String resetSomeObj=null;
	private Connection connection=null;
	public void reset(ActionMapping mapping,HttpServletRequest request){
		this.selectedStocks=new String[0];
		this.indices=new String[0];
		this.hidVar=null;
		
		/*// 14:Traded Volume,15:Stock Dividend
		if(this.selectReport!=null && (this.selectReport.equals("14")||this.selectReport.equals("15"))){
			if(this.filter!=null){
				this.filter=null;
				
			}
		}
	*/	
			
		
		//this.viewButton=null;
		//selectedStocks=null;

	}
	
	public void resetOnResetButton(){
		this.setSelectprefrence("0");
		this.setSelectReport("0");
		this.setDiffCollection(null);
		//myForm.setFilter("notSelected"); //commented by Vikram
		this.setResetButton(null);
		this.setViewButton(null);
		this.setIndex(null);
		this.setDifference(null);
		this.setStockOfIndex(null);
		this.setFilter(null);
		this.setStockOfExchange(null);
		this.setExe(null);
	}
	public void resetOnFilterOptions(){
		this.setFilter(null);
		this.setIndex(null);
		this.setExe(null);
		this.setStockOfExchange(null);
		this.setStockOfIndex(null);
	}
	
	public String getViewButton() {
		return viewButton;
	}

	public void setViewButton(String viewButton) {
		this.viewButton = viewButton;
	}

		/**
		 * @return Returns the prefrencecollection.
		 */
		public Collection getPrefrencecollection() {
			Vector roles1 = new Vector();
			roles1.add(new LabelValueBean("Not Selected","0"));
			Connection connection = null;
			PreparedStatement stmt = null;
			ResultSet rst = null;
			Connect c = ConnectInit.getConnect();
			//dbconnect();
			try {
				if (connection == null)
					connection = c.getdbConnection();
				stmt = connection.prepareStatement(ConnectInit.queries.getProperty("select_prefrence_collection"));
				rst = stmt.executeQuery();

				while (rst.next()) {
					
					roles1.add(new LabelValueBean(rst.getString(2), rst.getString(1)));
				}
				rst.close();
				stmt.close();
			} catch (Exception e) {
				Logging.error(" Error : " + e.getMessage());
			} finally {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ee) {
					Logging.error(" Error : Unable to close connection "+ ee.getMessage());
				}
			}
			prefrencecollection = roles1;
			return prefrencecollection;
		}
		
		public void setPrefrencecollection(Collection prefrencecollection) {
			this.prefrencecollection=prefrencecollection;
		}
		
		
		/**
		 * @return Returns the indexCollection.
		 */
		public Collection getIndexCollection() {
			Vector roles = new Vector(10);
			
			
			// reports will be displayed acording to preference selection
	/*		if(this.selectprefrence==null){
				roles.add(new LabelValueBean("Not Selected","0"));
			}else if(this.selectprefrence.equals("1")){
				roles.add(new LabelValueBean("Not Selected","0"));
				roles.add(new LabelValueBean("Index Divisor","1"));
				roles.add(new LabelValueBean("Index Composition","2"));
				roles.add(new LabelValueBean("Index Wise PE,PB","3"));
				roles.add(new LabelValueBean("Index In Differrent Currency","6"));// added by Vikram
			}else if(this.selectprefrence.equals("2")){			
				roles.add(new LabelValueBean("Not Selected","0"));
				roles.add(new LabelValueBean("Stock Details","4"));
				roles.add(new LabelValueBean("Capital Change","5"));
			}else if(this.selectprefrence.equals("3")){
				roles.add(new LabelValueBean("Not Selected","0"));
				roles.add(new LabelValueBean("Company Weightage","7"));
				roles.add(new LabelValueBean("Industry Weightage","8"));
				roles.add(new LabelValueBean("Stock Contribution To Change In Index","9"));
			}else if(this.selectprefrence.equals("4")){
				roles.add(new LabelValueBean("Not Selected","0"));
				roles.add(new LabelValueBean("Index Comparison","10"));
				roles.add(new LabelValueBean("Index Compare OHLC","11"));
				roles.add(new LabelValueBean("Index Returns And Volatility","12"));
				roles.add(new LabelValueBean("Index Correlation","13"));
			}else if(this.selectprefrence.equals("5")){
				roles.add(new LabelValueBean("Not Selected","0"));
				roles.add(new LabelValueBean("Traded Volume","14"));
				roles.add(new LabelValueBean("Stock Dividend","15"));
			}
			indexCollection = roles;
			
			*/
			
			roles.add(new LabelValueBean("Not Selected","0"));
			
			roles.add(new LabelValueBean("Index Divisor","1"));
			roles.add(new LabelValueBean("Index Composition","2"));
			roles.add(new LabelValueBean("Index Wise PE,PB","3"));
			roles.add(new LabelValueBean("Index In Differrent Currency","6"));// added by Vikram
			
			roles.add(new LabelValueBean("Stock Details","4"));
			roles.add(new LabelValueBean("Capital Change","5"));
		
			roles.add(new LabelValueBean("Company Weightage","7"));
			roles.add(new LabelValueBean("Industry Weightage","8"));
			roles.add(new LabelValueBean("Stock Contribution To Change In Index","9"));
			
			roles.add(new LabelValueBean("Index Comparison","10"));
			roles.add(new LabelValueBean("Index Compare OHLC","11"));
			roles.add(new LabelValueBean("Index Returns And Volatility","12"));
			roles.add(new LabelValueBean("Index Correlation","13"));
			
			roles.add(new LabelValueBean("Traded Volume","14"));
			roles.add(new LabelValueBean("Stock Dividend","15"));
			roles.add(new LabelValueBean("Index Movement","16"));
			
			indexCollection = roles;

			
			
			
			
			
			return indexCollection;
		}

		/**
		 * @return Returns the FilterCollection.
		 */
		public Collection getFilterCollection() {
			Vector roles = new Vector(10);
			//roles.add(new LabelValueBean("Not Selected","notSelected"));
			roles.add(new LabelValueBean("Not Selected","0"));
			roles.add(new LabelValueBean("Index Wise","1"));
			roles.add(new LabelValueBean("Exchange Wise","2"));
			//roles.add(new LabelValueBean("Select Index","2"));
			filterCollection = roles;
			return filterCollection;
		}
		
		/**
		   * 
		   * @return Returns the ExeCollection.
		   */
		public Collection getExeCollection() {
				Vector vec = new Vector();
				if(selectReport.trim().equals("14")||selectReport.trim().equals("15")){
					vec.add(new LabelValueBean("Not Selected",""));
				}
				Logging.debug("--before-exeCollection---"+exeCollection);			
				Connection connection = null;
				PreparedStatement stmt = null;
				try {
					if(connection == null)
						connection = con.getdbConnection();
					if ((selectReport != null && selectReport.equals("1")) || (selectReport != null && selectReport.equals("2")) 
							|| (selectReport != null && selectReport.equals("3")) || (selectReport != null && selectReport.equals("4")) || (selectReport != null && selectReport.equals("16"))) {
						stmt = connection.prepareStatement(ConnectInit.queries.getProperty("index_list_without_child"));
						//query = c.queries.getProperty("index_list");
					} else {
						stmt = connection.prepareStatement(ConnectInit.queries.getProperty("stock_exchange_online_list"));
						//query = c.queries.getProperty("index_list_without_child");
					}
					
					ResultSet rst = stmt.executeQuery();
					while(rst.next()){
						vec.add(new LabelValueBean(rst.getString(2),rst.getString(1)));
						//IndexNameHash.put(rst.getString(1),rst.getString(2));
					}
					
					rst.close();
					stmt.close();
					exeCollection = vec;
					Logging.debug("--after-exeCollection---"+exeCollection);
					return exeCollection;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					Logging.debug(e);
				}	finally{
					try{
						if(connection != null)
							connection.close();
					}catch (Exception ee) {
						try{
							if(connection!=null)
								connection.close();
						}catch(Exception ex){
							Logging.error(" Error : Unable to close Connection "+ex.getMessage());
						}
						Logging.error(" Error : Unable to close Connection "+ee.getMessage());
					}
				}
				
				return exeCollection;
				}
		
	public ActionErrors validate(ActionMapping mapping,HttpServletRequest request)
		 {
				String newvalue = this.getHiddenVar(); 	
		 		
				Connection connection = null;
	    		if (newvalue != null && newvalue.equals("yes")) {
	    			
	    			ActionErrors errors = new ActionErrors();
	    			boolean flag=true,flag1=true;
	    			try{
	    				if(selectedStocks.length!=0 && this.selectReport.equals("4"))
	    				{
	    					int check = checkData();
	    					if(check == 1)
		    				{	
		    					this.setHidVar("no");
		    					errors.add(null,
										new ActionError("errors.duplicateEntry"));
		    					return errors;
		    				}
		    				else
		    				{	
		    					this.setTemp1("yes");
		    					//return null;	
		    				}
	    				}
	    				else if(!this.selectReport.equals("4"))
	    				{
	    					if(indices.length!=0 && (selectReport.equals("10")||selectReport.equals("11")||selectReport.equals("12")||selectReport.equals("13")))// this condition is for report 10,11,12,13 to validate the indices field
	    					{
	    						int check = checkData();
		    					if(check == 1)
			    				{	
			    					this.setHidVar("no");
			    					errors.add(null,new ActionError("errors.duplicateEntry"));
			    					return errors;
			    				}
	    					}else if(!(selectReport.equals("10")||selectReport.equals("11")||selectReport.equals("12")||selectReport.equals("13")))
	    					{
	    						int check = checkData();
		    					if(check == 1)
			    				{	
			    					this.setHidVar("no");
			    					errors.add(null,new ActionError("errors.duplicateEntry"));
			    					return errors;
			    				}
	    					}
	    					
	    				}
	    				
	    			}catch(Exception e){
	    				errors.add(null,new ActionError("Error.message.msg"));
	    				//Logging.debug("Error in Validation ");			
	    			}	
	    		}	
	    		
	    		//Logging.debug("---length---->>>>"+selectedStocks.length);
	    		//Logging.debug("---view ---->>>>"+viewButton);
	    		//Logging.debug("---hidVar ---->>>>"+hidVar);
	    		if(this.viewButton!=null){
	    			if(!hidVar.equals("yes"))
	    			{
		    			if(selectedStocks.length==0){
		    				ActionErrors ers = new ActionErrors();	
		    				ers.add(null,new ActionError("required.stock"));
		    				this.setTemp1("no");
		    				this.setHidVar(null);
		    				return ers;
		    			}
	    			}
	    			
	    		}
	    		Logging.debug("-length-indices----->"+indices.length);
	    		Logging.debug("-indicesHidVar----->"+indicesHidVar);
	    		if(this.selectReport.trim().equals("10")||
	    				this.selectReport.trim().equals("11")||
	    				this.selectReport.trim().equals("12")||
	    				this.selectReport.trim().equals("13"))
	    		{	
	    			if(!indicesHidVar.trim().equals("no"))
	    			{
		    			if(indices.length==0){
		    				ActionErrors ers = new ActionErrors();	
		    				ers.add(null,new ActionError("required.indices"));
		    				this.setHiddenVar("no");	
		    				return ers;
		    			}
	    			}
	    			
	    		}
	    		
	    		
	    		
	    		
	    		
	    	/*	//validation on update button for duplicate values
	    		String updateButton = this.getUpdateButton(); 	
		 		
				if (updateButton != null && updateButton.equals("Update")) {
	    			
	    			ActionErrors errors = new ActionErrors();
	    			boolean flag=true,flag1=true;
	    			try{
	    				int check = checkData();
	    				if(check == 1)
	    				{
	    					errors.add(null,
									new ActionError("errors.duplicateEntry"));
	    					return errors;
	    				}else
	    				{
	    					return null;	
	    				}
	    			}catch(Exception e){
	    				errors.add(null,new ActionError("Error.message.msg"));
	    				Logging.debug("Error in Validation ");			
	    		    }	
	    			
	    		}	
	    	*/
	    		
	    		return null;		
		 }
		
	public int checkData()
	{
		ResultSet rs1;
		Connection connection = null;
		String reportname=null;
		int ans=0;
		
		if(selectReport.equals("1"))
		{		reportname = "Index Divisor";
		}else if(selectReport.equals("2")){
				reportname = "Index Composition";
		}else if(selectReport.equals("3")){
				reportname = "Index Wise PE,PB";
		}else if(selectReport.equals("4")){
				reportname = "Stock Details";
		}else if(selectReport.equals("5")){
				reportname = "Capital Change";
		}else if(selectReport.equals("6")){
			   reportname = "Index In Different Currency";
		}else if(selectReport.equals("7")){
			   reportname = "Company Weightage";
		}else if(selectReport.equals("8")){
			   reportname = "Industry Weightage";
		}else if(selectReport.equals("9")){
			   reportname = "Stock Contribution To Change In Index";
		}else if(selectReport.equals("10")){
			   reportname = "Index Comparison";
		}else if(selectReport.equals("11")){
			   reportname = "Index Compare OHLC";
		}else if(selectReport.equals("12")){
			   reportname = "Index Returns And Volatility";
		}else if(selectReport.equals("13")){
			   reportname = "Index Correlation";
		}else if(selectReport.equals("14")){
			   reportname = "Traded Volume";
		}else if(selectReport.equals("15")){
			   reportname = "Stock Dividend";
		}
		else if(selectReport.equals("16")){
			   reportname = "Index Movement";
		}
		if(connection==null)
			connection=con.getdbConnection();
			
		try{
					
				/**
				 * Check For The Duplicate Data Entry 
				 * */
	/*		// this condition is for checking the duplicate entry for Index... reports 
			if((selectReport != null && selectReport.equals("1")) || (selectReport != null && selectReport.equals("2")) || (selectReport != null && selectReport.equals("3"))){
				//this code if block is added by Vikram
				String query=con.queries.getProperty("Check_for_duplicat_entry");
				PreparedStatement stmt = connection.prepareStatement(query);
				stmt.setString(1,selectprefrence);
				stmt.setString(2,reportname);
				stmt.setString(3,this.index);
				rst = stmt.executeQuery();
				while(rst.next()){
					 ans=Integer.parseInt(rst.getString(1));
				}
				rst.close();
				stmt.close();
			}
			//this condition is for checking the duplicate entry for Stock Details reports 
			if(selectReport != null && selectReport.equals("4")) //4:Stock Details
			{
				String stocks="";
				for(int i=0;i<this.selectedStocks.length;i++){
					if(i==0){
						stocks=stocks+selectedStocks[i];
					}
					else{
						stocks=stocks+","+selectedStocks[i];
					}
				}
				Logging.debug("---stocks->>>>>>"+stocks);
				
				String query=con.queries.getProperty("check_duplicate_for_stock_detail");
				PreparedStatement stmt = connection.prepareStatement(query);
				
				stmt.setString(1,selectprefrence);
				stmt.setString(2,reportname);
				stmt.setString(3,this.index);
				stmt.setString(4,stocks);
				rst = stmt.executeQuery();
				
				while(rst.next()){
					 ans=Integer.parseInt(rst.getString(1));
				}
				rst.close();
				stmt.close();
			}
			 if(selectReport != null && selectReport.equals("5")) //5: Capital Change
			 {
				 String query=con.queries.getProperty("check_duplicate_for_capital_change");
				PreparedStatement stmt = connection.prepareStatement(query);
				stmt.setString(1,selectprefrence);
				stmt.setString(2,reportname);
				stmt.setString(3,this.exe);
				rst = stmt.executeQuery();
				while(rst.next()){
					 ans=Integer.parseInt(rst.getString(1));
				}
				rst.close();
				stmt.close();
			 }
		*/
			PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("Check_for_duplicat_entry"));
			stmt.setString(1,selectprefrence);
			stmt.setString(2,reportname);
			//stmt.setString(3,this.index);
			rst = stmt.executeQuery();
			while(rst.next()){
				 ans=Integer.parseInt(rst.getString(1));
			}
			rst.close();
			stmt.close();
		
		} catch (SQLException e1) {	
				Logging.error("Error  :  "+e1);
		}
		finally{
				try{
					if(connection != null)
						connection.close();
				}
				catch (Exception ee) {
					try{
						if(connection!=null)
							connection.close();
					}catch(Exception ex){
						Logging.error(" Error : Unable to close Connection "+ex.getMessage());
					}
					Logging.error(" Error : Unable to close Connection "+ee.getMessage());
				}
		}
	return ans;
	}
	
	/**
	 * @return Returns the selectStock.
	 */
	public String getSelectStock() {
		
		return selectStock;
	}
	/**
	 * @param selectStock The selectStock to set.
	 */
	public void setSelectStock(String selectStock) {
		this.selectStock = selectStock;
	}	

	/**
	 * Database Connectivity
	 * */
	/*public void dbconnect(){
		
		try {if(app.Connect.con==null){
			con.getConnection();
			}
		} catch (Exception e) {	Logging.debug(e);} 
		
	}*/

	
	public String getSelectReport() {
		return selectReport;
	}

	public void setSelectReport(String selectReport) {
		this.selectReport = selectReport;
	}

	/**
	 * @return Returns the toDate.
	 */
	public String getTo() {
		return to;
	}
	/**
	 * @param to The toDate to set.
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * @return Returns the stockCollection.
	 */
	public Collection getStockCollection() {
		//dbconnect();
		Vector vec=new Vector();
		try {
			//Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
			if(connection==null)
			{
				connection=con.getdbConnection();
			 }
		//vec.add(new LabelValueBean("Not Selected","0"));
		if(filter!=null && filter.trim().equals("0")){
			try {
				PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("stockDetailFromDate_stock_list_index"));
				stmt.setString(1,exe);
				ResultSet rst = stmt.executeQuery();
				while(rst.next()){
					vec.add(new LabelValueBean(rst.getString(1),rst.getString(2)));
				}
				rst.close();
				stmt.close();
				stockCollection = vec;
				return stockCollection;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
				Logging.debug(e);
			}	
			
		}
		else if(filter!=null && filter.trim().equals("1")){
			try {
				PreparedStatement stmt =connection.prepareStatement(ConnectInit.queries.getProperty("stockDetailFromDate_stock_list_exchange"));
				stmt.setString(1,exe);
				ResultSet rst = stmt.executeQuery();
				while(rst.next()){
					vec.add(new LabelValueBean(rst.getString(2),rst.getString(1)));
				}
				rst.close();
				stmt.close();
				stockCollection = vec;
				return stockCollection;
			} catch (SQLException e) {
			//	e.printStackTrace();
				Logging.debug(e);
			}	
			
		}
//		Close the Dynamic Connection : Manoj Adekar
		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} 

//		Close the Dynamic Connection : Manoj Adekar
		finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
			}
		}

		stockCollection=vec;
		return stockCollection;
	}
	/**
	 * @param stockCollection The stockCollection to set.
	 */
	public void setStockCollection(Collection stockCollection) {
		this.stockCollection = stockCollection;
	}
	
	
	  
	
	/**
	 * @return Returns the diffCollection.
	 */
	public Collection getDiffCollection() {
		
		Vector vec=new Vector();
		//vec.add(new LabelValueBean("Not Selected","0"));
		try
		{
		if(date.equals("day"))
		{
			for(int i=1;i<=31;i++)
			{
				vec.add(new LabelValueBean(""+i,""+i));
				
			}
			diffCollection=vec;
		}
		
		if(date.equals("month"))
		{
			for(int i=1;i<=12;i++) 
			{
				vec.add(new LabelValueBean(""+i,""+i));
			}
			diffCollection=vec;
		}
		
		if(date.equals("year"))
		{
			for(int i=1;i<=50;i++) 
			{
				vec.add(new LabelValueBean(""+i,""+i));
			}
			diffCollection=vec;
		}
		}
		catch(Exception e){
			
		}
		
		return diffCollection;
	}
	/**
	 * @param diffCollection The diffCollection to set.
	 */
	public void setDiffCollection(Collection diffCollection) {
		this.diffCollection = diffCollection;
	}
	/**
	 * @return Returns the date.
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date The date to set.
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	
	/**
	 * @return Returns the difference.
	 */
	public String getDifference() {
		return difference;
	}
	/**
	 * @param difference The difference to set.
	 */
	public void setDifference(String difference) {
		this.difference = difference;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getExe() {
		return exe;
	}

	public void setExe(String exe) {
		this.exe = exe;
	}
	
	public  String updatetable()
	{
		String flag = "false";
		Connection connection = null;
		PreparedStatement pst1 = null;
		int days = this.datenum();
		String reportname = this.reportname();
		try {
			if(connection == null)
				connection = con.getdbConnection();
			pst1 = connection.prepareStatement(ConnectInit.queries.getProperty("update_prefrential_parameter"));
			//6:Index in Different Currency
			//7:Company Weightage
			//8:Industry Weightage
			//9:Stock Contribution To Change In Index
			if((selectReport != null && selectReport.equals("1") && index != null) ||
					(selectReport != null && selectReport.equals("2") && index != null) || 
					(selectReport != null && selectReport.equals("3") && index != null)||
					(selectReport != null && selectReport.equals("6") && index != null)||
					(selectReport != null && selectReport.equals("7") && index != null)||
					(selectReport != null && selectReport.equals("8") && index != null)||
					(selectReport != null && selectReport.equals("9") && index != null)||
					(selectReport != null && selectReport.equals("16") && index != null))
			{
				pst1.setString(1, null);
				pst1.setString(2, index); // changed by Vikram
				pst1.setString(3, null);
				pst1.setString(4, null);
				pst1.setString(5, null);
				pst1.setInt(6, days);
				pst1.setString(7, selectprefrence);
				pst1.setString(8, reportname);
				pst1.executeUpdate();
				flag="true";
			}else if(selectReport != null && selectReport.equals("4") && index != null && selectedStocks != null) 
			{
				pst1.setString(1, null);
				pst1.setString(2, index);
				pst1.setString(3, null);
				
				String stocks="";
				for(int i=0;i<this.selectedStocks.length;i++){
					if(i==0){
						stocks=stocks+selectedStocks[i];
					}
					else{
						stocks=stocks+","+selectedStocks[i];
					}
				}
				Logging.debug("---stocks->>>>>>"+stocks);
				
				pst1.setString(4, stocks);
				pst1.setString(5, null);
				pst1.setInt(6, days);
				pst1.setString(7, selectprefrence);
				pst1.setString(8, reportname);
				pst1.executeUpdate();
				flag="true";
			}
			
			else if(selectReport != null && selectReport.equals("5") && exe != null)
			{
				pst1.setString(1, exe);
				pst1.setString(2, null);
				pst1.setString(3, null);
				pst1.setString(4, null);
				pst1.setString(5, null);
				pst1.setInt(6, days);
				pst1.setString(7, selectprefrence);
				pst1.setString(8, reportname);
				pst1.executeUpdate();
				flag="true";
			}else if((selectReport != null && selectReport.equals("10") && indices != null) ||
					(selectReport != null && selectReport.equals("11") && indices != null) || 
					(selectReport != null && selectReport.equals("12") && indices != null)||
					(selectReport != null && selectReport.equals("13") && indices != null))
			{
				pst1.setString(1, null);
				
				
				String selectedIndices="";
				for(int i=0;i<this.indices.length;i++){
					if(i==0){
						selectedIndices=selectedIndices+indices[i];
					}
					else{
						selectedIndices=selectedIndices+","+indices[i];
					}
				}
				
				
				pst1.setString(2, selectedIndices); 
				pst1.setString(3, null);
				pst1.setString(4, null);
				pst1.setString(5, null);
				pst1.setInt(6, days);
				pst1.setString(7, selectprefrence);
				pst1.setString(8, reportname);
				pst1.executeUpdate();
				flag="true";
			}else if((selectReport != null && selectReport.trim().equals("14"))||(selectReport != null && selectReport.trim().equals("15")))
			{							
				if(this.filter!=null && this.filter.trim().equals("1")) // filter=1:index wise
				{
					pst1.setString(1, null);
					pst1.setString(2, this.index); // changed by Vikram
					pst1.setString(3, null);
					pst1.setString(4, this.stockOfIndex);
					pst1.setString(5, null);
					pst1.setInt(6, days);
					pst1.setString(7, selectprefrence);
					pst1.setString(8, reportname);
					pst1.executeUpdate();
					flag="true";
				}else if(this.filter!=null && this.filter.trim().equals("2")) // filter=2:Exchange wise
				{
					pst1.setString(1, exe);
					pst1.setString(2, null); // changed by Vikram
					pst1.setString(3, null);
					pst1.setString(4, this.stockOfExchange);
					pst1.setString(5, null);
					pst1.setInt(6, days);
					pst1.setString(7, selectprefrence);
					pst1.setString(8, reportname);
					pst1.executeUpdate();
					flag="true";
					
				}
			}
			
			pst1.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Logging.debug(e);
		}	finally{
			try{
				if(connection != null)
					connection.close();
		   	}catch (Exception ee) {
				try{
					if(connection!=null)
						connection.close();
				}catch(Exception ex){
					Logging.error(" Error : Unable to close Connection "+ex.getMessage());
				}
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}//rnd of try
		return flag;
	}// end of update()

	public String inserdata()
	{
		String flag = "false";
		Connection connection = null;
		PreparedStatement pst1 = null;
		String reportname = this.reportname();
		int days = this.datenum();
		try {
			if(connection == null)
				connection = con.getdbConnection();
		
			pst1 = connection.prepareStatement(ConnectInit.queries.getProperty("Insert_Prfrential_data"));
			
			//1,2,3,6: for Index.. report 
			//7:Company Weightage
			//8:Industry Weightage
			//9:Stock Contribution To Change In Index
			if((selectReport != null && selectReport.equals("1"))||
					(selectReport != null && selectReport.equals("2")) || 
					(selectReport != null && selectReport.equals("3"))||
					(selectReport.trim() != null && selectReport.trim().equals("6"))||
					(selectReport != null && selectReport.equals("7"))||
					(selectReport != null && selectReport.equals("8"))||
					(selectReport != null && selectReport.equals("9"))||
					(selectReport != null && selectReport.equals("16"))
					) 
			{
				pst1.setString(1, selectprefrence);
				pst1.setString(2, reportname);
				pst1.setString(3, null);
				pst1.setString(4, index); //index instead of exe :changed by Vikram
				pst1.setString(5, null);
				pst1.setString(6, null);
				pst1.setString(7, null);
				pst1.setInt(8, days);
				pst1.executeUpdate();
				flag="true";
			}else if(selectReport != null && selectReport.equals("4")) //4:Stock Details
			{
				pst1.setString(1, selectprefrence);
				pst1.setString(2, reportname);
				pst1.setString(3, null);
				pst1.setString(4, index);//index instead of exe :changed by Vikram
				pst1.setString(5, null);
				String stocks="";
				for(int i=0;i<this.selectedStocks.length;i++){
					if(i==0){
						stocks=stocks+selectedStocks[i];
					}
					else{
						stocks=stocks+","+selectedStocks[i];
					}
				}
				Logging.debug("---stocks->>>>>>"+stocks);
				
				pst1.setString(6, stocks);
				pst1.setString(7, null);
				pst1.setInt(8, days);
				pst1.executeUpdate();
				flag="true";
			}else if(selectReport != null && selectReport.equals("5")) //5: Capital Change
			{
				pst1.setString(1, selectprefrence);
				pst1.setString(2, reportname);
				pst1.setString(3, exe);
				pst1.setString(4, null);
				pst1.setString(5, null);
				pst1.setString(6, null);
				pst1.setString(7, null);
				pst1.setInt(8, days);
				pst1.executeUpdate();
				flag="true";
			}else if((selectReport != null && selectReport.trim().equals("10"))||
					(selectReport != null && selectReport.trim().equals("11")) || 
					(selectReport != null && selectReport.trim().equals("12"))||
					(selectReport.trim() != null && selectReport.trim().equals("13"))) 
			{
				pst1.setString(1, selectprefrence);
				pst1.setString(2, reportname);
				pst1.setString(3, null);
				
				
				String selectedIndices="";
				for(int i=0;i<this.indices.length;i++){
					if(i==0){
						selectedIndices=selectedIndices+indices[i];
					}
					else{
						selectedIndices=selectedIndices+","+indices[i];
					}
				}
				Logging.debug("---selectedIndices->>>>>>"+selectedIndices);
				pst1.setString(4, selectedIndices); 
				pst1.setString(5, null);
				pst1.setString(6, null);
				pst1.setString(7, null);
				pst1.setInt(8, days);
				pst1.executeUpdate();
				flag="true";
			}
				// 14: Traded Volume,15:Stock Dividend
			else if((selectReport != null && selectReport.trim().equals("14"))||(selectReport != null && selectReport.trim().equals("15")))
			{							
				if(this.filter!=null && this.filter.trim().equals("1")) // filter=1:index wise
				{
					pst1.setString(1, this.selectprefrence);
					pst1.setString(2, reportname);
					pst1.setString(3, null);
					pst1.setString(4, this.index);
					pst1.setString(5, null);
					pst1.setString(6, this.stockOfIndex);
					pst1.setString(7, null);
					pst1.setInt(8, days);
					pst1.executeUpdate();
					flag="true";
				}else if(this.filter!=null && this.filter.trim().equals("2")) // filter=2:Exchange wise
				{
					pst1.setString(1, this.selectprefrence);
					pst1.setString(2, reportname);
					pst1.setString(3, this.exe);
					pst1.setString(4, null);
					pst1.setString(5, null);
					pst1.setString(6,this.stockOfExchange);
					pst1.setString(7, null);
					pst1.setInt(8, days);
					pst1.executeUpdate();
					flag="true";
				}
			}
				
					
			
			pst1.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Logging.debug(e);
		}	finally{
			try{
				if(connection != null)
					connection.close();
		   	}catch (Exception ee) {
				try{
					if(connection!=null)
						connection.close();
				}catch(Exception ex){
					Logging.error(" Error : Unable to close Connection "+ex.getMessage());
				}
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}//end of try
		return flag;
	}
	
	public String reportname()
	{
		
		String reportname1= null;
			
		if(selectReport.equals("1"))
		{
			reportname1 = "Index Divisor";
		}else if(selectReport.equals("2")){
			reportname1 = "Index Composition";
		}else if(selectReport.equals("3")){
			reportname1 = "Index Wise PE,PB";
		}else if(selectReport.equals("4")){
			reportname1 = "Stock Details";
		}else if(selectReport.equals("5")){
			reportname1 = "Capital Change";
		}else if(selectReport.equals("6")){
			reportname1 = "Index In Different Currency";
		}else if(selectReport.equals("7")){
			reportname1 = "Company Weightage";
		}else if(selectReport.equals("8")){
			reportname1 = "Industry Weightage";
		}else if(selectReport.equals("9")){
			reportname1 = "Stock Contribution To Change In Index";
		}else if(selectReport.equals("10")){
			reportname1 = "Index Comparison";
		}else if(selectReport.equals("11")){
			reportname1 = "Index Compare OHLC";
		}else if(selectReport.equals("12")){
			reportname1 = "Index Returns And Volatility";
		}else if(selectReport.equals("13")){
			reportname1 = "Index Correlation";
		}else if(selectReport.equals("14")){
			reportname1 = "Traded Volume";
		}else if(selectReport.equals("15")){
			reportname1 = "Stock Dividend";
		}else if(selectReport.equals("16")){
			reportname1 = "Index Movement";
		}
		return reportname1;
	}
	
	public int datenum()
	{
		int days= 0;
		if(difference.equals("0")) 
		{
		return days;
		} else
		{
		int daysnumbes = Integer.parseInt(difference);
		
		if(date.equals("day"))
		{
		days = daysnumbes;
		}
		if(date.equals("month"))
		{
			
		days = daysnumbes*30;	
		}
		if(date.equals("year"))
		{
		days = daysnumbes*365;	
		}
		return days;
		}
		
	}
	public ArrayList getTableData() {
		Logging.debug("inside the gettabledata");
		Connect con=ConnectInit.getConnect();
		Connection connection=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		ArrayList temp=new ArrayList();
		String prefrenceid = selectprefrence;
		String reportname = reportname();
		try{
			if(connection==null)
				connection=con.getdbConnection();
				try {
					
					
					String query = ConnectInit.queries.getProperty("select_from_preferencedetail");
					pst = connection.prepareStatement(query);
					pst.setString(1,prefrenceid);
					pst.setString(2,reportname);
					rs = pst.executeQuery();
					int i=0;
					while(rs.next())
					{
						String index_id1=null;//this is very imp lokesh
						 String filePath1=rs.getString("report_name");
						 String exchange_id=rs.getString("exchange_id");
						 if(exchange_id!=null)
						 {
							 setExchange_name(exchange_id);
						 	 index_id1=getExchange_name();
							 
						 }
						 else{   

							index_id1=rs.getString("index_id1");
							setIndex_name(index_id1);
							index_id1=getIndex_name();
						 }
						 int fdate1=rs.getInt("days_difference");
						 Date tdate1=new Date();
						 String index_id2=rs.getString("index_id2");
						 String stock_id1=rs.getString("stock_id1");
						 if(stock_id1!=null)
						 {
						 	setStock_name(stock_id1);
						 }
						 String stock_idd1=getStock_name();
						 ReportPreDetail icd= new ReportPreDetail(filePath1,index_id1,fdate1,tdate1,index_id2,stock_idd1);
						 temp.add(icd);
					}
					
					
					
				} catch (Exception e) {
					// TODO: handle exception
					Logging.error(" Error : "+e.getMessage());
										}
				    }catch(Exception e){
				    	Logging.error(" Error : "+e.getMessage());
				     }
				    finally{
				    	try{
				    		if(connection!=null)
				    			connection.close();
				    		}catch(Exception ee){
				    			try{
			    					if(connection!=null)
			    						connection.close();
			    				}catch(Exception ex){
						    Logging.error(" Error : Unable to close Connection "+ex.getMessage());
		    					 }
					 	   Logging.error(" Error : Unable to close Connection "+ee.getMessage());
				    		}
				    }
				    
				    tableData=temp;
				    Logging.debug("sizeeeeeeee of tabledata"+tableData.size());
				    return tableData;

	}
	public void setTableData(ArrayList tableData) {
		this.tableData = tableData;
	}


	public String getSelectprefrence() {
		return selectprefrence;
	}

	public void setSelectprefrence(String selectprefrence) {
		this.selectprefrence = selectprefrence;
	}

	public String getHiddenVar() {
		return hiddenVar;
	}

	public void setHiddenVar(String hiddenVar) {
		this.hiddenVar = hiddenVar;
	}



	public String getCheck() {
		return check;
	}



	public void setCheck(String check) {
		this.check = check;
	}



	public String getHiddenVar1() {
		return hiddenVar1;
	}



	public void setHiddenVar1(String hiddenVar1) {
		this.hiddenVar1 = hiddenVar1;
	}
	
	public String getIndex_name() {
		return index_name;
	}

	public void setIndex_name(String index_id) {
		Connect con=ConnectInit.getConnect();
		Connection connection=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		String indname=null;
		String local_id=index_id;
		try{
			if(connection==null)
				connection=con.getdbConnection();
				try {
					
					
					String query = ConnectInit.queries.getProperty("query_copy_indexmaster");
					//select * from preferencedetail where preference_id\=?
					Logging.debug("Query   = "+query);
					pst = connection.prepareStatement(query);
					try{
									
						Integer.parseInt(local_id);
						pst.setString(1,local_id);
						rs = pst.executeQuery();
						while(rs.next())
						{
							indname =rs.getString(2);
						}
											
					}
					catch(Exception e)
					{
						Logging.error(" Error : "+e.getMessage());
						
						StringTokenizer st = new StringTokenizer(local_id,",");
						String indexNameList="";
						int i=0;
						
						while(st.hasMoreTokens())
						{
							String idx=st.nextToken();
							pst.setString(1,idx);
							rs = pst.executeQuery();
							if(rs!=null){
								while(rs.next())
								{  
									if(i==0)
									{
										indexNameList=indexNameList+rs.getString(2);
										i++;
									}else
									{
										indexNameList=indexNameList+","+rs.getString(2);
									}
																						 
								}
								
							}
							
						}
						indname=indexNameList;
					}
										
				} catch (Exception e) {
					// TODO: handle exception
					Logging.error(" Error : "+e.getMessage());
										}
				    }catch(Exception e){
				    	Logging.error(" Error : "+e.getMessage());
				     }
				    finally{
				    	try{
				    		if(connection!=null)
				    			connection.close();
				    		}catch(Exception ee){
				    			try{
			    					if(connection!=null)
			    						connection.close();
			    				}catch(Exception ex){
						    Logging.error(" Error : Unable to close Connection "+ex.getMessage());
		    					 }
					 	   Logging.error(" Error : Unable to close Connection "+ee.getMessage());
				    		}
				    }
				    this.index_name = indname;
	}
	
	public String getStock_name() {
		return stock_name1;
	}

	public void setStock_name(String stock_id) {
		Connect con=ConnectInit.getConnect();
		Connection connection=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		String local_id=stock_id;
		try{
			if(connection==null)
				connection=con.getdbConnection();
				try {
										
					String query = ConnectInit.queries.getProperty("select_stock_name_from_stock_master");
					//select * from preferencedetail where preference_id\=?
					Logging.debug("Query   = "+query);
					pst = connection.prepareStatement(query);
					
					try{
						Integer.parseInt(local_id);
						pst.setString(1,local_id);
						rs = pst.executeQuery();
						int i=0;
						while(rs.next())
						{
							stock_name1=rs.getString("stock_name");
													 
						}
					}
					catch(Exception e)
					{
						Logging.debug("problem--->"+e);
						StringTokenizer st = new StringTokenizer(local_id,",");
						String stockNameList="";
						int i=0;
						while(st.hasMoreTokens())
						{
							String stock=st.nextToken();
							pst.setString(1,stock);
							rs = pst.executeQuery();
							if(rs!=null){
								while(rs.next())
								{  
									if(i==0){
										stockNameList=stockNameList+rs.getString("stock_name");
										i++;
									}else{
										stockNameList=stockNameList+","+rs.getString("stock_name");
									}
																						 
								}
								
							}
							
						}
						stock_name1=stockNameList;
						
					}
						
				} catch (Exception e) {
					// TODO: handle exception
					Logging.error(" Error : "+e.getMessage());
										}
				    }catch(Exception e){
				    	Logging.error(" Error : "+e.getMessage());
				     }
				    finally{
				    	try{
				    		if(connection!=null)
				    			connection.close();
				    		}catch(Exception ee){
				    			try{
			    					if(connection!=null)
			    						connection.close();
			    				}catch(Exception ex){
						    Logging.error(" Error : Unable to close Connection "+ex.getMessage());
		    					 }
					 	   Logging.error(" Error : Unable to close Connection "+ee.getMessage());
				    		}
				    }
				    
				   // Logging.getDebug("sizeeeeeeee of tabledata"+tableData.size());
				    this.stock_name1 = stock_name1;
	}

	
	public String getExchange_name() {
		return exchange_name;
	}

	public void setExchange_name(String exchange_id) {
		Connect con=ConnectInit.getConnect();
		Connection connection=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		String local_id=exchange_id;
		try{
			if(connection==null)
				connection=con.getdbConnection();
				try {
					
					
					String query = ConnectInit.queries.getProperty("select_stock_ex_name_from_stockmaster");
					//select * from preferencedetail where preference_id\=?
					Logging.debug("Query   = "+query);
					pst = connection.prepareStatement(query);
					pst.setString(1,local_id);
					
					rs = pst.executeQuery();
					int i=0;
					while(rs.next())
					{
						exchange_name=rs.getString("stock_ex_name");
												 
					}
										
				} catch (Exception e) {
					// TODO: handle exception
					Logging.error(" Error : "+e.getMessage());
										}
				    }catch(Exception e){
				    	Logging.error(" Error : "+e.getMessage());
				     }
				    finally{
				    	try{
				    		if(connection!=null)
				    			connection.close();
				    		}catch(Exception ee){
				    			try{
			    					if(connection!=null)
			    						connection.close();
			    				}catch(Exception ex){
						    Logging.error(" Error : Unable to close Connection "+ex.getMessage());
		    					 }
					 	   Logging.error(" Error : Unable to close Connection "+ee.getMessage());
				    		}
				    }
				    
				   // Logging.getDebug("sizeeeeeeee of tabledata"+tableData.size());
				    this.exchange_name = exchange_name;
	}



	public String getHiddenVar2() {
		return hiddenVar2;
	}



	public void setHiddenVar2(String hiddenVar2) {
		this.hiddenVar2 = hiddenVar2;
	}



	public String getUpdate() {
		return update;
	}



	public void setUpdate(String update) {
		this.update = update;
	}



	public String getRadioButton() {
		return radioButton;
	}



	public void setRadioButton(String radioButton) {
		this.radioButton = radioButton;
	}



	public String getResetButton() {
		return resetButton;
	}



	public void setResetButton(String resetButton) {
		this.resetButton = resetButton;
		//Logging.debug("----resetButton: "+resetButton);
	}

	public String getUpdateButton() {
		return updateButton;
	}

	public void setUpdateButton(String updateButton) {
		this.updateButton = updateButton;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getHideFilter() {
		return hideFilter;
	}

	public void setHideFilter(String hideFilter) {
		this.hideFilter = hideFilter;
	}
	
	
	public Collection getIndCollection() {
	//	AcessControl asc=new AcessControl();
		AcessControl asc = ConnectInit.getAcessControl();
		String SelIndex=asc.getLangValues("indexUpdate.selectIndex");
		//Logging.debug("----->SelIndex--->>"+SelIndex);
		Logging.debug(" Inside getIndCollection(): select Index ="+SelIndex);
		Vector roles = new Vector();
		try{
			Connect c = ConnectInit.getConnect();
		
			//Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
			if(con2==null){
				con2 = c.getdbConnection();
			}
			if(index!=null)
				if((index.equals(""))|(index.equals(SelIndex)))
					index=null;
				
			String query =ConnectInit.queries.getProperty("select_parent_index");
			ResultSet rs=ListTypeClass1.resultCorporate(con2,query);
			if(!selectReport.equals("10")){
					if(!selectReport.equals("11")){
						if(!selectReport.equals("12")){
							if(!selectReport.equals("13"))
							{
								roles.add(new LabelValueBean("Not Selected",""));//Here not selected is hard coded.becoz SelIndex was null,check for its actual value and avoid hard coding
							}
						}
					}
					
			}
			if(index==null)
			{
				while(rs.next())
				{
					String count=rs.getString("index_id");
					roles.add(new LabelValueBean(rs.getString("index_name"),count));
				}
			}
			else
			{
				while(rs.next())
				{
					String count=rs.getString("index_id");
					if(index.equals(count))
					{
						setIndex(index);
						roles.add(new LabelValueBean(rs.getString("index_name"),count));
					}
					else
						roles.add(new LabelValueBean(rs.getString("index_name"),count));
				}
			}
		}catch(Exception e){}
//		Close the Dynamic Connection : Manoj Adekar
		finally {
				try {
					if (con2 != null)
						con2.close();
				} catch (Exception ee) {
					Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
				}
			}
		indCollection = roles;   	
		return indCollection;
	}
	/**
	 * @param indCollection The indCollection to set.
	 */
	public void setIndCollection(Collection indCollection) {
		this.indCollection = indCollection;
	}
	
	public Collection getStockListOnIndexSelection(){
		//Logging.getDebug(" -----------Inside getStockListOnIndexSelection()--------------");
		Logging.debug(" -----------Inside getStockListOnIndexSelection()--------------");
		
		//dbconnect();
		try {
			//Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
			if(connection==null)
			{
				connection=con.getdbConnection();
			 }
		Vector vec=null;
		ResultSet rst=null;
		
		//vec.add(new LabelValueBean("Not Selected","0")); 
		
			try {
				String query=ConnectInit.queries.getProperty("stockDetailFromDate_stock_list_index");
				PreparedStatement stmt = connection.prepareStatement(query);
				vec=new Vector();
				if(this.index!=null ||! this.index.equals("")){
					stmt.setInt(1,Integer.parseInt(this.index));
					rst = stmt.executeQuery();
				}
				if(rst!=null){
					
					while(rst.next()){
						vec.add(new LabelValueBean(rst.getString(1),rst.getString(2)));
					}
				}
				else{
					Logging.debug("------>>ResultSet is null");
					vec=null;
				}
				rst.close();
				stmt.close();
				stockListOnIndexSelection = vec;
				
			}catch (SQLException sqle) {
				Logging.debug(sqle);
				//sqle.printStackTrace();
				return null;
			}	
			catch (Exception e) {
				//e.printStackTrace();
				Logging.debug(e);
				return null;
			}
//			Close the Dynamic Connection : Manoj Adekar
		} catch (Exception e) {
			Logging.debug("Error :" + e);
		} 

//		Close the Dynamic Connection : Manoj Adekar
		finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
			}
		}

			
		return stockListOnIndexSelection;
	}

	public String[] getSelectedStocks() {
		return selectedStocks;
	}

	public void setSelectedStocks(String[] selectedStocks) {
		this.selectedStocks = selectedStocks;
	}

	public String getHidVar() {
		return hidVar;
	}

	public void setHidVar(String hidVar) {
		this.hidVar = hidVar;
	}

	public String getTemp1() {
		return temp1;
	}

	public void setTemp1(String temp1) {
		this.temp1 = temp1;
	}

	public String[] getIndices() {
		return indices;
	}

	public void setIndices(String[] indices) {
		this.indices = indices;
	}

	public String getIndicesHidVar() {
		return indicesHidVar;
	}

	public void setIndicesHidVar(String indicesHidVar) {
		this.indicesHidVar = indicesHidVar;
	}

	public String getStockOfIndex() {
		
		
		//
		return stockOfIndex;
	}

	public void setStockOfIndex(String stockOfIndex) {
		this.stockOfIndex = stockOfIndex;
	}
	public String getStockOfExchange() {
		return stockOfExchange;
	}

	public void setStockOfExchange(String stockOfExchange) {
		this.stockOfExchange = stockOfExchange;
	}

	public Collection getStockListOnExchangeSelection() {
		Vector roles1 = new Vector();
		roles1.add(new LabelValueBean("Not Selected","0"));
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		Connect c = ConnectInit.getConnect();
		//dbconnect();
		try {
			if (connection == null)
				connection = c.getdbConnection();

			String query= ConnectInit.queries.getProperty("select_stocks_where_exchange_id_is_given");    
				
				
			stmt = connection.prepareStatement(query);
			Logging.debug("--------------exchange--->"+exe);
			if(!exe.equals("") && exe!=null){
				
				stmt.setInt(1,Integer.parseInt(exe));
				rst = stmt.executeQuery();
			}
			
			

			while (rst.next()) {
				
				roles1.add(new LabelValueBean(rst.getString(2), rst.getString(1)));
			}
			rst.close();
			stmt.close();
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close connection "+ ee.getMessage());
			}
		}
		stockListOnExchangeSelection = roles1;
		
		
		
		
		
		
		
		return stockListOnExchangeSelection;
	}

	public void setStockListOnExchangeSelection(
			Collection stockListOnExchangeSelection) {
		this.stockListOnExchangeSelection = stockListOnExchangeSelection;
	}

	public String getResetFilterHidVar() {
		return resetFilterHidVar;
	}

	public void setResetFilterHidVar(String resetFilterHidVar) {
		this.resetFilterHidVar = resetFilterHidVar;
	}

	public String getResetSomeObj() {
		return resetSomeObj;
	}

	public void setResetSomeObj(String resetSomeObj) {
		this.resetSomeObj = resetSomeObj;
	}
	
	
	
}
