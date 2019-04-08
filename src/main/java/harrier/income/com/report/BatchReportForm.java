package harrier.income.com.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;
import org.jfree.chart.demo.servlet.AdjustDecimal;

import app.Connect;

import com.harrier.initializeation.ConnectInit;

/**
 * @author neha.
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BatchReportForm extends ActionForm{
	Logger Logging = Logger.getLogger(BatchReportForm.class);
	private String selectUser=null,filePath=null,fileVariable=null,executeButton=null,editButton=null,
	index_name=null,exchange_name=null,stock_name1=null,id=null,defaultVal=null,compute=null,radioButton=null;
	private String tod=null,fromd=null,vanish=null,fin=null,view=null,checkradio=null;
	boolean checkdate=false;
	private String selectReport[]=new String[0];
	
	String indexName=null;
	private String resetButton=null;
	private String resetButHV=null;
	
	String index12=null,index2=null,index3=null;
	private Collection selectUserCollection=null;
	private Collection selectReportCollection=null;
	
	
	private ArrayList tableData=null;
	private ArrayList tabledata3=null;
	private ArrayList tableData2=null;
	private ArrayList tableDatnew=null;
	private String tradingDate;
	private String close;
	private String mCap;
	private String divisor;
	private String pe,to;
	private String pb,from;
	private String divYield;
	private Boolean check1;
	private Vector vw;
	private String selectIndex=null;
	public indexMove im1;
	double total=0.00;
	int diff=0;
//	AdjustDecimal ad = new AdjustDecimal();
	IndexComposeReportMethod Icr = new IndexComposeReportMethod();
//	Connect con=new Connect();
	Connect con = ConnectInit.getConnect();
	private ResultSet rst;
	Vector vExcel=new Vector();	
	ArrayList index_arraylist=new ArrayList();

	
	private String index_name2=null;
	
	private String dataCount=null;
	private Vector vTraded= new Vector();
	
	private String  frdate = null, todate = null; 
				
	private String currencyParam=null;
	
	private ArrayList tableData3 = null;
	
	private ArrayList tableDatac    = null;
	private Vector capitalChangeVec = null;
	private ArrayList tableDatas    = null;
	
	Collection selectExchgCollection = null;
	private String stockName 		 = null, selectExchange = null;
	private Hashtable IndexNameHash  = new Hashtable();
	
	private int itype=0;
	
	
	/* variables for companywise reports*/
	private String val=null;
	double totalMcap		=0;
	double totalWt			=0;
	private ArrayList companyWeightage=new ArrayList();
	private Vector CompanyWeightageVector=new Vector();
	String tval,tvol,graph,computetotalreturns, b_showChild, query,index,lastclosingvalue,cdate=null,hist_Date,CompareDate;
	private Vector vi;
	ArrayList indweighttable=null;
	
	
	/* variables for stock Contri reports*/
		
	String tdate,fdate;
	//ArrayList indweighttable=null;
	ArrayList stockcotriIndexchange=null;
	Vector vector_remStockid=null;
	
	
//variable for index compare report
	
	private String[] d2=null;
	
	//variables for returns and volatility
	private String[] indexList;
	PreparedStatement pst;
	private Vector vector_index_rv1;
	private ArrayList final_Vector=null;
	
	
//	 stock dividend report 
	private String filter="0",selectIndExch=null; //,from=null,to=null;
	private ArrayList tableDataSD=null;
	private Vector stkDividentVec = new Vector();
	
	
	//traded volume
	private Vector trdVolVec =new Vector();
	private String traded_volume=null;
	private ArrayList tableDataTr=null;
		
	
	
	Connection connection=null;   
    ResultSet rs = null;
	public returnVol rv;
	Vector ohlcParam=new Vector();
	Vector corelParam=new Vector();
	Vector comParam=new Vector();
	
	
	
	
   /**
	* @param excel The vExcel to set.
	*/
	public void setVExcel(Vector excel) {
		vExcel = excel;
	}
	
	/**RESEST ALL FORM FIELDS**/
	public void reset(ActionMapping mapping,HttpServletRequest request){
		  
	  	  
	  	selectReport=new String[0];
	  	
	  	filePath=null;
	  	fileVariable=null;
	  	executeButton=null;
	  	index_name=null;
	  	stock_name1=null;
	  	checkdate=false;
	  	radioButton=null;

	}
	  
   /**
	* VALIDATE FORM DATA
	**/
	public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request){
	  		Logging.debug(" Inside validate....");
	  		ActionErrors errors = new ActionErrors();
	  		return errors;
	}  
	  
	 /**
	* @return Returns the vExcel.
	*/
	public Vector getVExcel() {
		return vExcel;
	}
	/**
	 * @return Returns the checkdate.
	 */
	public boolean isCheckdate() {
		return checkdate;
	}
	/**
	 * @param checkdate The checkdate to set.
	 */
	public void setCheckdate(boolean checkdate) {
		this.checkdate = checkdate;
	}
   /**
	* @return Returns the filePath.
	*/  	  
	public String getFilePath() {
		return filePath;
	}
	
   /**
	* @param filePaths The filePath to set.
	*/
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

   /**
	* @return Returns the fileVariable.
	*/  
	public String getFileVariable() {
		return fileVariable;
	}

   /**
	* @param fileVariable The fileVariable to set.
	*/
	public void setFileVariable(String fileVariable) {
		this.fileVariable = fileVariable;
	}

   /**
	* @return Returns the tableData.
	*/  
	public ArrayList getTableData() {
		Logging.debug("inside the gettabledata");
		Connect con=ConnectInit.getConnect();
		Connection connection=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		ArrayList temp=new ArrayList();
		String local_id=getSelectUser();
		try{
			if(connection==null)
				connection=con.getdbConnection();
				try {
					
					
					String query = ConnectInit.queries.getProperty("select_*_from_preferencedetail1");
					//select * from preferencedetail where preference_id\=?
					Logging.debug("Query   = "+query);
					pst = connection.prepareStatement(query);
					pst.setString(1,local_id);
					rs = pst.executeQuery();
					
					while(rs.next())
					{
						
						String index_id1=null;//this is very imp lokesh
						 filePath=rs.getString("report_name");
						 String exchange_id=rs.getString("exchange_id");
						 if(exchange_id!=null)
						 {
							 setExchange_name(exchange_id);
						 	 index_id1=getExchange_name();
							 
						 }
						 else{   

							index_id1=rs.getString("index_id1");
							id=index_id1;
							setId(id);
							id=getId();
							setIndex_name2(id);
							index_id1=getIndex_name2();
							//index_id1=getIndex_name();
						 }
						
						 int fdate1  	   = 	rs.getInt("days_difference");
						 Date tdate1	   =	new Date();
						 String index_id2  =	rs.getString("index_id2");
						 String stock_id1  =	rs.getString("stock_id1");
						 // String stock_idd1=getStock_name(stock_id1);
						 setStock_name(stock_id1);
						 String stock_idd1 =	getStock_name();
						 BatchReportDetails icd= new BatchReportDetails(filePath,index_id1,fdate1,tdate1,index_id2,stock_idd1,id);
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


	/**
	 * @return Returns the tableDatnew.
	 */
	
	// tabledata for selected reports
	public ArrayList getTableDatnew() {
		String str[]=null;
		Connect con=ConnectInit.getConnect();
		Connection connection=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		ArrayList temp=new ArrayList();
		
		
		
		try{
			if(connection==null)
				connection=con.getdbConnection();
				try {
					str=getSelectReport();
					Logging.debug("str---------arr"+str);
					if(str!=null){
						for(int j=0;j<str.length;j++)
						{
							String s=str[j];
							Logging.debug("report name===========> " +s); 
							Logging.debug("str length===========> " +str.length); 								
							String query = ConnectInit.queries.getProperty("get_rep_detail");
													
							pst = connection.prepareStatement(query);
							pst.setString(1,s);
							
							rs = pst.executeQuery();
							int i=0;
							while(rs.next())
							{									
								String index_id1=null;//this is very imp lokesh
								 filePath=rs.getString("report_name");
								 String exchange_id=rs.getString("exchange_id");
								 Logging.debug("filepath=====>"+filePath);
								 if(exchange_id!=null)
								 {
									 setExchange_name(exchange_id);
								 	 index_id1=getExchange_name();
									 
								 }
								 else{   
	
									index_id1=rs.getString("index_id1");
									Logging.debug("indexid=="+index_id1);
									id=index_id1;
									setId(id);
									id=getId();
									 setIndex_name2(id);
									index_id1=getIndex_name2();
									
									//index_id1=getIndex_name();
									//Logging.debug("indexidnameeeeeeeeeee=="+getIndex_name());
								 }
								 int fdate1  	   = 	rs.getInt("days_difference");
								 Date tdate1	   =	new Date();
								 Logging.debug("here we go???");
								 String index_id2=rs.getString("index_id2");
								 String stock_id1=rs.getString("stock_id1");
								 setStock_name(stock_id1);
								 String stock_idd1=getStock_name();
								 Logging.debug("here we go");
								 BatchReportDetails icd= new BatchReportDetails(filePath,index_id1,fdate1,tdate1,index_id2,stock_idd1,id);
								 temp.add(icd);
							}// end of while
							
						}// end of for
					}// end of if	
					
				} catch (Exception e) {
					// TODO: handle exception
					Logging.error(" Error : "+e.getMessage());
					Logging.debug(e);
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
				    
				    tableDatnew=temp;
				    Logging.debug("sizeeeeeeee of tabledata"+tableData.size());
				   
				    

		return tableDatnew;
	}
	
	public void setTableDatnew(ArrayList tableDatnew) {
		this.tableDatnew = tableDatnew;
	} 


	/**
	 * @return Returns the select.
	 */
	public String getSelectUser() {
		return selectUser;
	}
	
	/**
	 * @param selectUser The selectUser to set.
	 */
	public void setSelectUser(String selectUser) {
		this.selectUser = selectUser;
	}
	
	/**
	 * @return Returns the executeButton.
	 */
	public String getExecuteButton() {
		 return executeButton;
	}
	
	/**
	 * @param executeButton The executeButton to set.
	 */
	public void setExecuteButton(String sButton) {
		this.executeButton 	= 	sButton;
	}
	
	/**
	 * @return Returns the selectUserCollection.
	 */
	public Collection getSelectUserCollection() {
		Vector vec = new Vector();
		
		Connection connection = null;
		vec.add(new LabelValueBean("Not Selected","0"));
		PreparedStatement stmt = null;
		try {
			if(connection == null){
				connection = con.getdbConnection();
			}
			stmt 		  = connection.prepareStatement(ConnectInit.queries.getProperty("select_*_from_report_prefrence"));		
			ResultSet rst = stmt.executeQuery();
			while(rst.next()){
				vec.add(new LabelValueBean(rst.getString(2),rst.getString(1)));
			}	
			rst.close();
			stmt.close();
			selectUserCollection = vec;
			//return selectExchgCollection;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
			Logging.debug(e);
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception ee) {
				try {
					if (connection!=null){
						connection.close();
					}
				} catch(Exception ex) {
					Logging.error(" Error : Unable to close Connection "+ex.getMessage());
				}
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
		Logging.debug("sizeeeeeeee of usercollection"+vec.size());
		selectUserCollection = vec;
		return selectUserCollection;
	}
	
   /**
	* @param selectUserCollection The selectUserCollection to set.
	*/
	public void setSelectUserCollection(Collection selectUserCollection) {
		this.selectUserCollection = selectUserCollection;
	}

	/**
	 * @return Returns the index_name.
	 */

	public String getIndex_name() {
		Connect con=ConnectInit.getConnect();
		Connection connection=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		String local_id=getId();
		try{
			if(connection==null)
				connection=con.getdbConnection();
				try {
					
					
					String query = ConnectInit.queries.getProperty("query_copy_indexmaster");
					//select * from preferencedetail where preference_id\=?
					Logging.debug("Query   = "+query);
					pst = connection.prepareStatement(query);
					pst.setString(1,local_id);
					
					rs = pst.executeQuery();
					int i=0;
					while(rs.next())
					{
						index_name=rs.getString("index_name");
												 
					}
					Logging.debug("indexname"+index_name);
										
				} catch (Exception e) {
					// TODO: handle exception
					Logging.error(" Error : "+e.getMessage());
				}
		 } catch(Exception e){
				    	Logging.error(" Error : "+e.getMessage());
		 } finally {
				    	try {
				    		if(connection!=null)
				    			connection.close();
				    	} catch(Exception ee){
				    			try {
			    					if(connection!=null)
			    						connection.close();
			    				} catch(Exception ex){
			    					Logging.error(" Error : Unable to close Connection "+ex.getMessage());
		    					}
			    				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
				       }
		}
		Logging.debug("sizeeeeeeee of tabledata"+tableData.size());
				
		return index_name;
	}

	public void setIndex_name(String index_name) {
		
		this.index_name=index_name;
							
	}


   /**
	* @return Returns the check1.
	*/
	public Boolean getCheck1() {
		return check1;
	}

   /**
	* @param check1 The check1 to set.
	*/
	public void setCheck1(Boolean check1) {
		this.check1 = check1;
	}
	
	/**
	* @return Returns the exchange_name.
	*/
	public String getExchange_name() {
		return exchange_name;
	}

   /**
	* @param exchange_name The exchange_name to set.
	*/
	public void setExchange_name(String exchange_id) {
		Connect con			  = ConnectInit.getConnect();
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs 		  = null;
		String local_id  	  = exchange_id;
		try{
				if(connection == null) {
					connection = con.getdbConnection();
				}
				try {
					
					String query = ConnectInit.queries.getProperty("select_stock_ex_name_from_stockmaster");
					//select * from preferencedetail where preference_id\=?
					Logging.debug("Query   = "+query);
					pst = connection.prepareStatement(query);
					pst.setString(1,local_id);
					rs = pst.executeQuery();
					
					while(rs.next())
					{
						exchange_name = rs.getString("stock_ex_name");						 
					}
										
				} catch (Exception e) {
					// TODO: handle exception
					Logging.error(" Error : "+e.getMessage());
				}
		} catch(Exception e) {
				    Logging.error(" Error : "+e.getMessage());
		} finally {
				    try {
				    		if (connection != null) {
				    			connection.close();
				    		}
				    } catch (Exception ee) {
				    			try {
			    					if (connection != null){
			    						connection.close();
			    					}
			    				} catch(Exception ex) {
			    					Logging.error(" Error : Unable to close Connection "+ex.getMessage());
		    					}
			    				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
				    }
		}
		Logging.debug("sizeeeeeeee of tabledata"+tableData.size());
		this.exchange_name = exchange_name;
	}

   /**
	* @return Returns the stock_name1.
	*/
	public String getStock_name() {
		return stock_name1;
	}
	
   /**
	* @param stock_name The stock_name to set.
	*/
	/*
	public void setStock_name(String stock_id) {
		Connect con 		  = ConnectInit.getConnect();
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs 		  = null;
		String local_id 	  = stock_id;
		try{
			if(connection == null)
				connection=con.getdbConnection();
				try {
					
					String query = con.queries.getProperty("select_stock_name_from_stock_master");
					//select * from preferencedetail where preference_id\=?
					Logging.getDebug("Query   = "+query);
					pst = connection.prepareStatement(query);
					pst.setString(1,local_id);
					rs = pst.executeQuery();
					stock_name1 = null;
					while(rs.next())
					{
						stock_name1 = rs.getString("stock_name");						 
					}
										
				} catch (Exception e) {
					// TODO: handle exception
					Logging.getError(" Error : "+e.getMessage());
				}
		} catch (Exception e) {
				    Logging.getError(" Error : "+e.getMessage());
		} finally {
				    try {
				    		if(connection != null){
				    			connection.close();
				    		}
				    } catch(Exception ee) {
				    			try {
			    					if(connection!=null) {
			    						connection.close();
			    					}
			    				} catch(Exception ex) {
			    						Logging.getError(" Error : Unable to close Connection "+ex.getMessage());
		    					}
			    				Logging.getError(" Error : Unable to close Connection "+ee.getMessage());
				    }
		 }
		 Logging.getDebug("sizeeeeeeee of tabledata"+tableData.size());
		 this.stock_name1 = stock_name1;
	}

	*/
	public void setStock_name(String stock_id) {
		Connect con=ConnectInit.getConnect();
		Connection connection=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		String local_id=stock_id;
		stock_name1=null;
		try{
		if(connection==null)
		connection=con.getdbConnection();
		try {

		String query = ConnectInit.queries.getProperty("select_stock_name_from_stock_master");
		//select * from preferencedetail where preference_id\=?
		Logging.debug("Query = "+query);
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
//		 TODO: handle exception
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

//		 Logging.getDebug("sizeeeeeeee of tabledata"+tableData.size());
		this.stock_name1 = stock_name1;
		}


		
   /**
	* @return Returns the id.
	*/
	public String getId() {
		return id;
	}
	
	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
	
   /**
	* @return Returns tableData3.
	* tableData3 for Index Composition report
	*/

	
	public ArrayList getTabledata3() {
		
		
		String local_id=getSelectUser();
		String stockid=null,stockname=null,currency=null,tis=null, iwf=null, adjusted=null, mcv=null, stockprice=null, market=null, last=null,curr_exch_convIratecomp1=null,strweightage1=null;
		
		Connection connection=null;
		Connect con=ConnectInit.getConnect();
		double total1=0.00;
		vw=new Vector();
		double tmcv=0.0;
		ArrayList tempdata=new ArrayList();
		String index_id,date;
//		org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		// to retrive index id form database
		try{
			 if(connection==null)
				connection=con.getdbConnection();
			try {
				PreparedStatement stmt =connection.prepareStatement(ConnectInit.queries.getProperty("get_indexcomp_id"));
				stmt.setString(1,local_id);
				stmt.setString(2,"Index Composition");
				ResultSet rst = stmt.executeQuery();
				while(rst.next()){
					 index12=rst.getString("index_id1");
				}	
				setIndex(index12);
				setIndex_name2(index12);
					Logging.debug("index1------>"+index12);
				rst.close();
				stmt.close();
				
				
			}catch (SQLException e) {
				Logging.error("Error  :"+e.getMessage());
				
			//	e.printStackTrace();
			}	
		} catch(Exception e){
			Logging.error(" Error : "+e.getMessage());
		}
		
		
		try{
		
		Logging.debug("index12------>"+index12);
		ResultSet tmcvrst = Icr.stiockweightageLatestResult(connection,"get_tmcv_for_composition",index12);
		Logging.debug("get tmcv of Compose Index");
		try 
		{
			while (tmcvrst.next()) 
			{
				index_id=tmcvrst.getString(1);
				tmcv=tmcvrst.getDouble(2);
				Logging.debug("tmcv is "+tmcv);
				date=tmcvrst.getString(3);
				
			}
			
		}
		
		catch (SQLException sqlexp) 
		{
			Logging.error("SQL Error :"+sqlexp.getMessage());
		}
		
		String curr_exch_convIratecomp=null,strmcv=null;
		double weightage=0.0,mcve=0.0;
		rst = Icr.indexComposeResult(connection,"get_composition_for_compose_report",index12);
		int i = 0;
		 
		
		 tabledata3=new ArrayList();
		 IndexCompose12 indexcompose1;
		Logging.debug("setVector_tabledata of Compose Index");
		try {
			while (rst.next()) {
				
				if (rst.getString(1) == null) {
					stockid= "0";
					} else {
						stockid=rst.getString(1);
						vw.add(i,rst.getString(1));
					}
				i++;
				if (rst.getString(2) == null) {
					stockname="0";
				} else {
					stockname=rst.getString(2);
					vw.add(i,rst.getString(2));
				}
				i++;
				if (rst.getString(4) == null) {
					tis="0";
					} else {
						tis=rst.getString(4);
						tis=AdjustDecimal.ArrangeAsNumeric(tis);
						vw.add(i,rst.getString(4));
					}
				i++;
				if (rst.getString(5) == null) {
					iwf="0";
				} else {
					iwf=rst.getString(5);
					iwf=ad.indexcompose(iwf);
					iwf=AdjustDecimal.ArrangeAsNumeric(iwf);
					vw.add(i,rst.getString(5));
				}
				i++;
				if (rst.getString(9) == null) {
					market= "0";
				} else {
					market=rst.getString(9);
					vw.add(i,rst.getString(9));
				}
				i++;
				if (rst.getString(6) == null) {
					adjusted= "0";
				} else {
					adjusted=rst.getString(6);
					adjusted=ad.indexcompose(adjusted);
					adjusted=AdjustDecimal.ArrangeAsNumeric(adjusted);
					vw.add(i,rst.getString(6));
				}
				i++;
				if (rst.getString(10) == null) {
					last= "0";
				} else {
					last=rst.getString(10);
					last=ad.indexcompose(last);
					last=AdjustDecimal.ArrangeAsNumeric(last);
					vw.add(i,rst.getString(10));
				}
				i++;
				if(rst.getString(3) == null) {
					currency= "0";
				} else {
					currency=rst.getString(3);
					vw.add(i,rst.getString(3));
				}
				
				i++;
				curr_exch_convIratecomp=getCurrancyExchRate(index12,stockid);
				//Logging.getDebug("curr_exch_convIrate is "+curr_exch_convIrate);
				curr_exch_convIratecomp=ad.indexcompose4digit(curr_exch_convIratecomp);
				if(curr_exch_convIratecomp==null){
					curr_exch_convIratecomp="0";
					vw.add(i,curr_exch_convIratecomp);
				}else{
					curr_exch_convIratecomp1=curr_exch_convIratecomp;
					vw.add(i,curr_exch_convIratecomp);
				}
			
				i++;
				if (rst.getString(7) == null) {
					mcv= "0";
				} else {
					mcv=rst.getString(7);
					mcv=ad.indexcompose(mcv);
					mcv=AdjustDecimal.ArrangeAsNumeric(mcv);
					vw.add(i,rst.getString(7));
				}
				i++;
				if (rst.getString(7) == null) {
					mcv= "0";
				} else {
					mcv=rst.getString(7);
					mcv=ad.indexcompose(mcv);
					mcv=AdjustDecimal.ArrangeAsNumeric(mcv);
					vw.add(i,rst.getString(7));
				}
				i++;
				strmcv=rst.getString(7);
				 mcve=Double.parseDouble(strmcv);
				 if(tmcv!=0.0){
				 	weightage=(mcve/tmcv)*100.00;
				 }
				 total1=total1+weightage;
				 String strweightage=new Double(weightage).toString();
				 strweightage=ad.shareholdingpatt(strweightage);
				 strweightage=ad.indexcompose4digit(strweightage);
				 strweightage=AdjustDecimal.ArrangeAsNumeric(strweightage);
				 strweightage1=strweightage;
				 vw.add(i,strweightage1);
				 //weightage 
				/*if (rst.getString(8) == null) {
					vector_tabledata.add(i, "0");
				} else {
					vector_tabledata.add(i, rst.getString(8));
				}*/
			
				 i++;
				if (rst.getString(8) == null) {
					stockprice= "-0";
				} else {
					stockprice= rst.getString(8);
					vw.add(i,rst.getString(8));
				}
				i++;
				indexcompose1 = new IndexCompose12(stockid,stockname,currency,tis,iwf,adjusted,mcv,stockprice,market,last,curr_exch_convIratecomp1,strweightage1);
				tempdata.add(indexcompose1);
				}
			rst.close();
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :"+sqlexp.getMessage());
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
					Logging.error(" Error : Unable to close connection "+ex.getMessage());
				}
				Logging.error(" Error : Unable to close connection "+ee.getMessage());
			}
		}
		tabledata3=tempdata;
		 Logging.debug(tabledata3);
		setTotal(total1);
		setVw(vw);
		 return tabledata3;
	  	}
	
		/**
		 * @return Returns the Tabledata3 .
		 */
	  	
		
	  	public void setTabledata3(ArrayList tabledata3) 
	  	{
		this.tabledata3 =tabledata3;
	  	}
	  	public String getIndex() {
	  		return index;
	  	}
	  	/**
	  	 * @param index
	  	 * The index to set.
	  	 */
	  	public void setIndex(String index) {
	  		Logging.debug("setIndex index " + index);
	  		if (index != null)
	  			this.index = index;
	  	 }
	  	/**
	  	 * @return Returns the indexName.
	  	 */
	  	public String getIndexName() {


		try{
			String local_d1=index;
			Logging.debug("INDEX id="+local_d1);
			Enumeration e;
			String str;
			String iname="",ival="";
			e=IndexNameHash.keys();
			while(e.hasMoreElements()){
				str=(String)e.nextElement();
				iname=(String)IndexNameHash.get(str);
				if(str.equals(local_d1)){
					Logging.debug("Yes it is matched ");
					indexName=iname;
					break;
				}
				Logging.debug("INDEX value from getIndexName  = "+str);
				Logging.debug("INDEX NAME from getIndexName  = "+iname);
			}
			
			
			
		}catch(Exception e){
			
		}
		return indexName ;
	}
	  	/**
	  	 * @param indexName The indexName to set.
	  	 */
	  	public void setIndexName(String indexName) {
		this.indexName = indexName;
	  	}
	  	
   /**
	* @return Returns currancyExchRate.
	*/
	public String getCurrancyExchRate(String index12,String stockid) {
	  		String cexch_rate = null;
	  		String stk_currency_id = null, ind_currency_id = null;
	 // 		Connect con = new Connect();
	  		Connection connection = null;
	  		if(connection == null) {
	  			connection = con.getdbConnection();
	  		}
	  		try {
	  			//Logging.getDebug("inside getCurrancyExchRate");
	  			ResultSet rstexc = Icr.indwtResult(connection,"get_index_and_stock_currency_id", stockid,index12);
				Logging.debug("rst is "+rstexc);

	  			while (rstexc.next()) {
	  				if (rstexc.getString(1) == null) {
	  					stk_currency_id="0";
	  				} else {
	  					stk_currency_id=(String)rstexc.getString(1);
	  				}
	  				if (rstexc.getString(2) == null) {
	  					ind_currency_id="0";
	  				} else {
					ind_currency_id=(String)rstexc.getString(2);
	  				}				
	  			}
	  			//Logging.getDebug("stk_currency_id is "+stk_currency_id+" ind_currency_id is "+ind_currency_id);
	  			if(stk_currency_id.equals(ind_currency_id)) {
	  				cexch_rate="1.00";
	  			} else {
	  				/*ResultSet rst11 = con.indwtResult("get_currency_exchange_rate", ind_currency_id,stk_currency_id);
	  				 while (rst11.next()) {
	  				 	if (rst.getString(1) == null) {
	  				 		cexch_rate="0";
	  				 	}else{
	  				 		cexch_rate=(String)rst11.getString(1);
	  				 	}					
	  				 }*/
				
	  				String temp=Icr.getIndexCurrancyExchRate(connection,stk_currency_id,ind_currency_id);
	  				double exch=0.0; 
	  				if(temp != null) {
	  					exch=new Double(temp).doubleValue();
	  				} else {
	  					temp=Icr.getIndexCurrancyExchRate(connection,ind_currency_id,stk_currency_id);
	  					if(temp==null) {
	  						exch=1.0;
	  					} else {
	  						exch=1/new Double(temp).doubleValue();
	  					}
	  				}
	  				cexch_rate=new Double(exch).toString();
	  				Logging.debug("currency exchange rate is "+cexch_rate);
	  			}
	  			
	  		} catch (SQLException sqlexp) {
	  			Logging.error("SQL Error :"+sqlexp.getMessage());
	  		} finally {
	  			try {
	  				if(connection!=null) {
	  					connection.close();
	  				}
	  			} catch(Exception ee) {
	  				try {
	  					if(connection!=null) {
	  						connection.close();
	  					}
	  				} catch(Exception ex) {
	  					Logging.error(" Error : "+ex.getMessage());
	  				}
	  				Logging.error(" Error : "+ee.getMessage());
	  			}
	  		}
	  		return cexch_rate;
	 }

	/**
	 * @return Returns the total.
	 */
	public double getTotal() {
		return total;
	}
	
	/**
	 * @param total The total to set.
	 */
	public void setTotal(double total) {
		this.total = total;
	}
	
	/**
	 * @return Returns the vw.
	 */
	public Vector getVw() {
		return vw;
	}
	/**
	 * @param vw The vw to set.
	 */
	public void setVw(Vector vw) {
		this.vw = vw;
	}
	
	/**
	 * @return Returns the compute.
	 */
	public String getCompute() {
		return compute;
	}
	
	/**
	 * @param compute The compute to set.
	 */
	public void setCompute(String compute) {
		this.compute = compute;
	}
	
	/**
	 * @return Returns the defaultVal.
	 */
	public String getDefaultVal() {
		return defaultVal;
	}
	
   /**
	* @param defaultVal The defaultVal to set.
	*/
	public void setDefaultVal(String defaultVal) {
		this.defaultVal = defaultVal;
	}
	
   /**
	* @return Returns the index_arraylist.
	* ArrayList for IndexWise  PE/PB
	*/
	public ArrayList getIndex_arraylist() {
		String local_id=getSelectUser();
		
		ArrayList vdata=new ArrayList();
		Connection connection=null;
		Connect con=ConnectInit.getConnect();
		// To retrive index id form database
		String td=null,close=null,change=null,mcap=null,shtr=null,trnovr=null,perat=null,pbrat=null,dividend=null;
		
		try{
			 if(connection==null)
				connection=con.getdbConnection();
			try {
				PreparedStatement stmt =connection.prepareStatement(ConnectInit.queries.getProperty("get_indexpepb_id"));
				stmt.setString(1,local_id);
				stmt.setString(2,"Index Wise PE,PB");
				ResultSet rst = stmt.executeQuery();
				while(rst.next()){
					 index12=rst.getString("index_id1");
					 diff=rst.getInt("days_difference");
				}
				index=index12;
				setIndex(index);
				setIndex_name2(index);
				if(isCheckdate()== true) // select date from calendar
				{
					to=getTod();
					from=getFromd();
				}
				else                       // date generated from days difference from database
				{	
					
				 Date curr=new Date();      
			     SimpleDateFormat ft=new SimpleDateFormat("dd-MM-yyyy");
			      to=ft.format(curr);
			     long newd=diff*24*60*60*1000L;
			     java.util.Date s4= new java.text.SimpleDateFormat("dd-MM-yyyy").parse(to);
			     long t2=s4.getTime();
			     long t1=t2-newd; 
			     Date s1=new Date(t1);
			     SimpleDateFormat ft1=new SimpleDateFormat("dd-MM-yyyy");
			     from=ft1.format(s1);
			     
			     /*
					Date curr = new Date(); 
					setFrdate(diff);
					from=getFrdate();
					setTodate(curr);
					to=getTodate();
				*/
			    }
			     Logging.debug("todate"+to);
			     Logging.debug("fromdate"+from);
			     Logging.debug("diff"+diff);
				 //Logging.debug("index1--new---->"+index12);
					
				rst.close();
				stmt.close();
				
				
			}catch (SQLException e) {
				Logging.error("Error  :"+e.getMessage());
				
			//	e.printStackTrace();
			}	
		} catch(Exception e){
			Logging.error(" Error : "+e.getMessage());
		}
		try {
		//		org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal(); 
			AdjustDecimal ad = ConnectInit.getAdjustDecimal();	
				
				vdata = new ArrayList();
				String local_d1=index12;
				String local_from=from;// write code for from date
				String local_to=to;
				Logging.debug("todate1"+to);
			     Logging.debug("fromdate1"+from);
				double lastclose=getlastclosing(local_d1,local_from);
				Logging.debug(local_d1+"  "+local_from+" "+"  "+local_to);
				PreparedStatement pst = Connect.con.prepareStatement(ConnectInit.queries.getProperty("indexwise_pe_pb_ratio"));
				pst.setString(1,local_d1);
				pst.setString(2,local_from);
				pst.setString(3,local_to);
				pst.setString(4,local_d1);
				pst.setString(5,local_from);
				pst.setString(6,local_to);
				pst.setString(7,local_d1);
				pst.setString(8,local_from);
				pst.setString(9,local_to);
				pst.setString(10,local_from);
				pst.setString(11,local_to);
				pst.setString(12,local_d1);
				ResultSet rst = pst.executeQuery(); 
				Logging.debug("setVector_vdata");
				int i=0;
				vExcel.clear();
				while(rst.next())
				{
					if (rst.getString(1) == null) {
						td="---";
					} else {
						td=rst.getString(1);
					}
					vExcel.add(i,td);
					i++;
					if (rst.getString(2) == null) {
						close="0";
					} else {
						String strclose=(String)rst.getString(2);
						strclose=ad.indexcompose(strclose);
						strclose=AdjustDecimal.ArrangeAsNumeric(strclose);
						close=strclose;
					}
					vExcel.add(i,close);
					i++;
					double pchange=0.0;
					if(lastclose!=0.0){
						pchange=(((double)rst.getDouble(2))-lastclose)/lastclose;
					}else{
						pchange=0.0;
					}
					String strpchange=new Double(pchange).toString();
					strpchange=ad.indexcompose(strpchange);
					strpchange=AdjustDecimal.ArrangeAsNumeric(strpchange);
					change=strpchange;
					vExcel.add(i,strpchange);
					i++;
					
					lastclose=(double)rst.getDouble(2);
					
					if (rst.getString(3) == null) {
						mcap="0";
					} else {
						double mcv=(double)rst.getDouble(3);
						mcv=mcv/1000000.0;
						String strmcv=new Double(mcv).toString();
						strmcv=ad.shareholdingpatt(strmcv);
						strmcv=ad.indexcompose(strmcv);
						strmcv=AdjustDecimal.ArrangeAsNumeric(strmcv);
						mcap=strmcv;
					}
					vExcel.add(i,mcap);
					i++;
					if (rst.getString(4) == null) {
						shtr="0";
					} else {						
						shtr=rst.getString(4);
					}
					vExcel.add(i,shtr);
					i++;
					if (rst.getString(5) == null) {
						trnovr="0";
					} else {
						double trv=(double)rst.getDouble(5);
						trv=trv/1000000.0;
						String turnover=new Double(trv).toString();
						turnover=ad.indexcompose(turnover);
						turnover=AdjustDecimal.ArrangeAsNumeric(turnover);
						trnovr=turnover;
					}
					vExcel.add(i,trnovr);
					i++;
					if (rst.getString(6) == null) {
						perat="0";
					} else {
						double tmcv=(double)rst.getDouble(3);
						double dperatio=(double)rst.getDouble(6);
						Logging.debug("pe ratio is "+dperatio+" tmcv "+tmcv);
						dperatio=tmcv/dperatio;
						Logging.debug("pe ratio is "+dperatio+" actaual "+(double)rst.getDouble(6));
						String peratio=new Double(dperatio).toString();
						peratio=ad.shareholdingpatt(peratio);
						peratio=ad.indexcompose(peratio);
						peratio=AdjustDecimal.ArrangeAsNumeric(peratio);
						perat=peratio;
					}
					vExcel.add(i,perat);
					i++;
					if (rst.getString(7) == null) {
						pbrat="0";
					} else {
						double tmcv=(double)rst.getDouble(3);
						double dpbratio=(double)rst.getDouble(7);
						Logging.debug("pb ratio is "+dpbratio+" tmcv "+tmcv);
						dpbratio=tmcv/dpbratio;
						Logging.debug("pb ratio is "+dpbratio+" actual "+(double)rst.getDouble(7));
						String pbratio=new Double(dpbratio).toString();
						pbratio=ad.shareholdingpatt(pbratio);
						pbratio=ad.indexcompose(pbratio);
						pbratio=AdjustDecimal.ArrangeAsNumeric(pbratio);
						pbrat=pbratio;
					}
					vExcel.add(i,pbrat);
					i++;
					if (rst.getString(8) == null) {
						dividend="0";
					} else {
						double tmcv=(double)rst.getDouble(3);
						double divvalue=(double)rst.getDouble(8);
						divvalue=tmcv/divvalue;
						Logging.debug("div value is "+divvalue);
						String div=new Double(divvalue).toString();
						div=ad.indexcompose(div);
						div=AdjustDecimal.ArrangeAsNumeric(div);
						dividend=div;
					}
					vExcel.add(i,dividend);
				    i++;
					IndexPePbDetails ipepb=new IndexPePbDetails(td,close,change,mcap,shtr,trnovr,perat,pbrat,dividend);
					vdata.add(ipepb);
				}	//end of while
				Logging.debug("vector size "+vdata.size());			
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :"+sqlexp.getMessage());
		}

		setVExcel(vExcel);
		index_arraylist=vdata;
		return index_arraylist;
	}
	
	public double getlastclosing(String id,String fdate)
	{
		double lastclose=0.0;
		try
		{
	//		Connect con=new Connect();
			if(Connect.con==null)
			{
				con.getConnection();
			}
			PreparedStatement pst = Connect.con.prepareStatement(ConnectInit.queries.getProperty("get_index_closing_max_value"));
			pst.setString(1,id);
			pst.setString(2,id);
			pst.setString(3,fdate);
			ResultSet rst = pst.executeQuery(); 
			int i = 0;
			while(rst.next())
			{
				if(rst.getString(1)==null){
					lastclose=0.0;
				}else{
					lastclose=rst.getDouble(1);
				}
			}
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :"+sqlexp.getMessage());
		}
		return lastclose;
	}
	/**
	 * @param index_arraylist The index_arraylist to set.
	 */
	public void setIndex_arraylist(ArrayList index_arraylist) {
		this.index_arraylist = index_arraylist;
	}
	

	
	
   /**
	* @return Returns tabledata2.
	* Tabledata2 for Index Divisor report
	*/  
	public ArrayList getTableData2() {
		ArrayList Pp 	=	new ArrayList();
		Vector Table_data_vector = new Vector();
		harrier.income.com.report.AdjustDecimal ad=new harrier.income.com.report.AdjustDecimal();
		Connection connection=null;
//		Connect con=new Connect();
		
		Logging.debug("Connecting using con "+Connect.con);
		
	     // to retrive index id from database
		try{
			 if(connection==null)
				connection=con.getdbConnection();
			try {
				PreparedStatement stmt =connection.prepareStatement(ConnectInit.queries.getProperty("get_indexdiv_id"));
				stmt.setInt(1,1);
				stmt.setString(2,"Index Divisor");
				ResultSet rst = stmt.executeQuery();
				while(rst.next()){
					selectIndex=rst.getString("index_id1");
					 diff=rst.getInt("days_difference");
				}
				if(isCheckdate()== true)   // select date from calendar
				{
					to=getTod();
					from=getFromd();
				}
				else                        // date generated from days difference from database
				{	
					
					Date curr = new Date(); 
					setFrdate(diff);
					from=getFrdate();
					setTodate(curr);
					to=getTodate();
			    }
				setIndex(selectIndex);
				setIndex_name2(selectIndex); 
			     
				rst.close();
				stmt.close();
				
				
			}catch(Exception e){
				Logging.error(" Error : "+e.getMessage());
		     }
		}
		catch(Exception e){
			Logging.error(" Error : "+e.getMessage());
		}
		// tabledata for index divisor
	try
	{
		PreparedStatement pst=null;
		if(from!=null && to!=null){
				
					String query=ConnectInit.queries.getProperty("index_divisor_date_wise1");
					if(connection==null){
						connection=con.getdbConnection();
					}
					if(con==null){
						con=new Connect();
					}
					pst = connection.prepareStatement(query);
					pst.setString(1,selectIndex);
					pst.setString(2,from);
					pst.setString(3,to);
					
					int ii=0;
					
					ResultSet rst = pst.executeQuery(); 
					vExcel.clear();
						while(rst.next()){
							if (rst.getString(1) == null) {
								tradingDate="--";
								
							} else {
								tradingDate=rst.getString(1);
								
							}
							vExcel.add(ii,tradingDate);
							ii++;
							
							if (rst.getString(2) == null) {
								close="0";
								
							} else {
								String strclose=(String)rst.getString(2);
								close=ad.indexcompose(strclose);
								
							}
							vExcel.add(ii,close);
							ii++;
							
							if (rst.getString(3) == null) {
								mCap="0";
								
							} else {
								double mcv=(double)rst.getDouble(3);
								mcv=mcv/1000000.0;
								String strmcv=new Double(mcv).toString();
								Logging.debug("Strmcv Value is "+strmcv);
								strmcv=ad.shareholdingpatt(strmcv);
								mCap=ad.indexcompose(strmcv);
								
							}
							vExcel.add(ii,mCap);
							ii++;
							
							if (rst.getString(4) == null) {
								divisor="0";
								
							} else {
								double mcv=(double)rst.getDouble(4);
								String strmcv=new Double(mcv).toString();
								Logging.debug("Strmcv1 Value is  "+strmcv);
								strmcv=ad.shareholdingpatt(strmcv);
								divisor=ad.indexcompose(strmcv);
								
							}
							vExcel.add(ii,divisor);
							ii++;
									
							im1=new indexMove(tradingDate,close,mCap,divisor, pe, pb,divYield);
							
							Pp.add(im1);
							
							//setVar_Table_data_vector(Table_data_vector);
							Table_data_vector.add(Pp);
							
							//Logging.debug("VEXCEL Vector "+vExcel);
						}
						
						rst.close();
						pst.close();
						
				}//end of if
	}
		catch (Exception e) {
					Logging.debug("e----------------message");
				//	e.printStackTrace();
					Logging.debug(e);
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
							Logging.error(" Error : Unable to close connection "+ex.getMessage());
						}
						Logging.error(" Error : Unable to close connection "+ee.getMessage());
					   }
				   }

		setVExcel(vExcel);
		tableData2=Pp;
		return tableData2;
	}

	/**
	 * @param tableDate The tableData2 to set.
	 */
	public void setTableData2(ArrayList tableData2) {
		this.tableData2 = tableData2;
	}
	
	
	
	/**
	 * @return Returns the from.
	 */
	public String getFromd() {
		return fromd;
	}
	/**
	 * @param from The from to set.
	 */
	public void setFromd(String fromd) {
		this.fromd = fromd;
	}
	/**
	 * @return Returns the pb.
	 */
	public String getPb() {
		return pb;
	}
	/**
	 * @param pb The pb to set.
	 */
	public void setPb(String pb) {
		this.pb = pb;
	}
	/**
	 * @return Returns the to.
	 */
	public String getTod() {
		return tod;
	}
	/**
	 * @param to The to to set.
	 */
	public void setTod(String tod) {
		this.tod = tod;
	}
	/**
	 * @return Returns the radioButton.
	 */
	public String getRadioButton() {
		return radioButton;
	}
	/**
	 * @param radioButton The radioButton to set.
	 */
	public void setRadioButton(String radioButton) {
		this.radioButton = radioButton;
	}
	
	/**
	 * @return Returns the selectReportCollection.
	 */
	public Collection getSelectReportCollection() {
		Vector vec = new Vector();
		String local_id=getSelectUser();
		Connection connection = null;
		//vec.add(new LabelValueBean("Not Selected","0"));
		PreparedStatement stmt = null;
		try {
			if(connection == null)
				connection = con.getdbConnection();
			stmt = connection.prepareStatement(ConnectInit.queries.getProperty("select_*_from_preferencedetail1"));
			stmt.setString(1,local_id);			
			ResultSet rst = stmt.executeQuery();
			while(rst.next()){
				vec.add(new LabelValueBean(rst.getString(2),rst.getString(2)));
				
			}
				
			rst.close();
			stmt.close();
			
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
		
		Logging.debug("sizeeeeeeee of usercollection"+vec.size());
		selectReportCollection = vec;
		
		return selectReportCollection;
	}
	/**
	 * @param selectReportCollection The selectReportCollection to set.
	 */
	public void setSelectReportCollection(Collection selectReportCollection) {
		this.selectReportCollection = selectReportCollection;
	}
	
	/**
	 * @return Returns the selectReport.
	 */
	public String[] getSelectReport() {
		return selectReport;
	}
	/**
	 * @param selectReport The selectReport to set.
	 */
	public void setSelectReport(String[] selectReport) {
		this.selectReport = selectReport;
	}
	/**
	 * @return Returns the vanish.
	 */
	public String getVanish() {
		return vanish;
	}
	/**
	 * @param vanish The vanish to set.
	 */
	public void setVanish(String vanish) {
		this.vanish = vanish;
	}
	/**
	 * @return Returns the fin.
	 */
	public String getFin() {
		return fin;
	}
	/**
	 * @param fin The fin to set.
	 */
	public void setFin(String fin) {
		this.fin = fin;
	}
	
	/**
	 * @return Returns the view.
	 */
	public String getView() {
		return view;
	}
	/**
	 * @param view The view to set.
	 */
	public void setView(String view) {
		this.view = view;
	}
	/**
	 * @return Returns the checkradio.
	 */
	public String getCheckradio() {
		return checkradio;
	}
	/**
	 * @param checkradio The checkradio to set.
	 */
	public void setCheckradio(String checkradio) {
		this.checkradio = checkradio;
	}
	

	/* From Tabledata For Stock Details Report */
	/**
	 * @return Returns the tableDatas.
	 * TableData For Stock Detail Report
	 */
	public ArrayList getTableDatas() {
	
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String local_id = "2";
		String stockidd = null;
		String exchange_id = null;
		int days_diff=0;
		
		try{
			if(connection == null)
				connection = con.getdbConnection();
				try {
					
					String query = ConnectInit.queries.getProperty("select_*_from_preferencedetail12");
					//Logging.getDebug("Query   = "+query);
					pst = connection.prepareStatement(query);
					pst.setString(1,local_id);
					pst.setString(2,"Stock Details");
					rs = pst.executeQuery();
					
					while(rs.next())
					{
						
						stockidd = null;
						exchange_id = rs.getString("exchange_id");
						days_diff = rs.getInt("days_difference");
						//Logging.getDebug("days_diff"+days_diff);
						stockidd = rs.getString("stock_id1");
						
					}
					
					if(isCheckdate()== true)   // select date from calendar
					{
						to=getTod();
						from=getFromd();
					}
					else                        // date generated from days difference from database
					{
					
						Date curr = new Date(); 
						setFrdate(days_diff);
						from=getFrdate();
						setTodate(curr);
						to=getTodate();
					}
										
				     String stkName = null, openVal = null, closeVal = null, lowVal = null, highVal = null,
				     trdVal = null, trdVol = null, noTrades = null, mcv = null, pDate = null;
				     //Connect con1=ConnectInit.getConnect();
	//			     Connect con = new Connect();
				     //PreparedStatement stmt =null;
				     Vector vec = new Vector();
				     ArrayList tempDatas = new ArrayList();
				     StockDetails stkDetails;
			
				     if(Connect.con == null)
				     {
				    	 con.getConnection();
				     }
				     Logging.debug("stockidd+fromdate+todate"+stockidd+from+to);
				     //fromdate="1-1-2006";
				     setIndex_name1(stockidd);
				     String [] stockid3=getIndex_name1();
				     int i =0;
				     for(int j=0;j<stockid3.length;j++)
				     {
				    	 
				     String stockid4=stockid3[j];
				     rst = con.highlowResult("stock_price_daily_between_date",stockid4,from,to);
				     //int i =0;
				     try {
			
				
				    	 while (rst.next()) {
					
				    		 if (rst.getString(2) == null) {						// stock name
				    			 stkName = "--";
				    		 } else {
				    			 stkName= rst.getString(2).toString();
				    		 }
				    		 vec.add(i, stkName);
				    		 i++;
					
				    		 if (rst.getString(3) == null) {						// open val
				    			 openVal ="0";
				    		 } else {
				    			 openVal = rst.getString(3).toString();
				    		 }
				    		 vec.add(i,openVal);
				    		 i++;
					
				    		 if (rst.getString(4) == null) {						// close val
				    			 closeVal = "0";
				    		 } else {
				    			 closeVal=rst.getString(4).toString();
				    		 }
				    		 vec.add(i,closeVal);
				    		 i++;
					
				    		 if (rst.getString(5) == null) {						// low val
				    			 lowVal ="0";
				    		 } else {
				    			 lowVal = rst.getString(5).toString();					
				    		 }
				    		 vec.add(i,lowVal);
				    		 i++;
					
				    		 if (rst.getString(6) == null) {						// high Val
				    			 highVal = "0";					
				    		 } else {
				    			 highVal = rst.getString(6).toString();
				    		 }
				    		 vec.add(i,highVal);
				    		 i++;
					
				    		 if (rst.getString(7) == null) {						// Traded Volume
				    			 trdVol = "0";
				    		 } else {
				    			 trdVol = rst.getString(7).toString();
				    		 }
				    		 vec.add(i,trdVol);
				    		 i++;
					
				    		 if (rst.getString(10) == null) {					// Traded Value
				    			 trdVal = "--";
				    		 } else {
				    			 trdVal = rst.getString(10).toString();
				    		 }
				    		 vec.add(i,trdVal);
				    		 i++;
					
				    		 if (rst.getString(9) == null) {						// MCap mcv
				    			 mcv ="--";
				    		 } else {
				    			 //Logging.getDebug("*************************************** MCap = "+  rst.getDouble(9));
				    			 //double mCapVal = (double)rst.getDouble(9);					
				    			 //mcv = new Double(mCapVal).toString();//    rst.getString(9);
				    			 mcv = rst.getString(9).toString();
				    		 }
				    		 vec.add(i,mcv);
				    		 i++;
						
				    		 if (rst.getString(11) == null) {					// No of trades
				    			 noTrades = "0";
				    		 } else {
				    			 noTrades = rst.getString(11).toString();
				    		 }			
				    		 vec.add(i,noTrades);
				    		 i++;
				
				    		 if (rst.getString(8) == null) {						// Price date
				    			 pDate = "--";
				    		 } else {
				    			 pDate = rst.getString(8).toString();
				    		 }
				    		 vec.add(i,pDate);
				    		 i++;
				    		 stkDetails = new StockDetails(stkName, openVal, closeVal, lowVal, highVal, 
				    				 trdVal, pDate, mcv, trdVol, noTrades );
					
				    		 tempDatas.add(stkDetails);			
				    	 }	
				    	 rst.close();	
				
				    }catch (SQLException sqlexp) {
				    	 Logging.error("SQL Error : "+sqlexp.getMessage());
				    }catch (Exception exp) {
				    	 Logging.error(" Error : "+exp.getMessage());
				    }  
					
				  }
				    Logging.debug(" tempData = "+ tempDatas.size());
				    tableDatas = tempDatas;
				    //setVector_highlowtable(vector_highlowtable);	
					
									
			} catch (Exception e) {
					// TODO: handle exception
					Logging.error(" Error : "+e.getMessage());
			}
			}catch(Exception e){
					    	Logging.error(" Error : "+e.getMessage());
			}
			finally{
					 try{
					 	if(connection!=null) {
					    	connection.close();
					 	}
					} catch(Exception ee){}
			}

		return tableDatas;
	}

	
   /**
	* @param tableDatas The tableDatas to set.
	*/
	public void setTableDatas(ArrayList tableDatas) {
		this.tableDatas = tableDatas;
	}
	/* Upto Tabledata For Stock Details Report */
	
	/* From Tabledata For Capital Change Report */
   /**
	* @return Returns the tableDatac.
	* Tabledata For Capital Change Report
	*/
	public ArrayList getTableDatac() {
		//Connect con= ConnectInit.getConnect();
		//PreparedStatement stmt = null;
		Connection connection = null;
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		//ArrayList temp = new ArrayList();
		String local_id = getSelectUser();
		
		String id1 = null;
		String exchange_id = null;
		int days_diff = 0;
		
		
			try{
				if(connection == null) {
				connection = con.getdbConnection();
				}
				try {
					
						String query = ConnectInit.queries.getProperty("select_*_from_preferencedetail12");
						//Logging.getDebug("Query   = "+query);
						pst = connection.prepareStatement(query);
						pst.setString(1,local_id);
						pst.setString(2,"Capital Change");
						rs = pst.executeQuery();
				
						while(rs.next())
						{
							id1 = null;
							
							exchange_id = rs.getString("exchange_id");
							days_diff = rs.getInt("days_difference");
							Logging.debug("days_diff" +days_diff);
							//Logging.getDebug("id1" +id1);
						
							if(exchange_id!=null){
								id1 = exchange_id;
								Logging.debug("id1" +id1);
							}  
						}
						
						if(isCheckdate()== true)   // select date from calendar
						{
							to=getTod();
							from=getFromd();
						}
						else                        // date generated from days difference from database
						{
						
						Date curr = new Date(); 
						setFrdate(days_diff);
						from=getFrdate();
						setTodate(curr);
						to=getTodate();
						}						
						String stkId = null, stkName = null, faceVal=null, tis = null, mCap = null, iwf = null,
						CAName = null, date = null;
						
		//				org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
						AdjustDecimal ad = ConnectInit.getAdjustDecimal();
						Vector vec = new Vector();
						ArrayList tempData = new ArrayList();
						
						StockDetails stkDetails;
												 
						 if(Connect.con==null){
							con.getConnection();
						}
					
						Logging.debug("id1from to"+id1+from+to);
						rst = con.highlowResultmktStatus("capital_change_to_universe",id1,from,to);
						Logging.debug("set vector_capitalchangetouniv of capital change to universe");
						int i =0;
						try {
							while (rst.next()) {
								Logging.debug(" Inside while: i="+i);

								if (rst.getString(1) == null) {						// stk_id
									stkId= "--";
									
								} else {
									stkId= rst.getString(1).toString();
								}
								vec.add(i, stkId);
								i++;
								
								if (rst.getString(2) == null) {						//Stock Name
									stkName= "--";
								} else {
									stkName= rst.getString(2).toString();
								}
								vec.add(i, stkName);
								i++;
								
								if (rst.getString(3) == null) {						// Face value
									faceVal= "--";
								} else {
									String fcVal = (String) rst.getString(3);            		
						        	fcVal = ad.indexcompose(fcVal);
						        	faceVal = fcVal;
									
								}
								vec.add(i, faceVal);
								i++;
								
								if (rst.getString(4) == null) {						// TIS
									tis= "0.00";
								} else {
									tis = rst.getString(4).toString();
								}
								vec.add(i, tis);
								i++;
							
								if (rst.getString(5) == null) {						// mCap
									mCap= "0";
								} else {
									String mVal = (String) rst.getString(5);            		
						        	mVal = ad.indexcompose(mVal);
						        	mCap = mVal;
									
								}
								vec.add(i, mCap);
								i++;
								
								if (rst.getString(6) == null) {						// iwf
									iwf = "--";
								} else {
									iwf = rst.getString(6).toString();
								}
								vec.add(i, iwf);
								i++;
								
								if (rst.getString(8) == null) {						// CA name
									CAName = "--";
								} else {
									CAName = rst.getString(8).toString();
								}
								vec.add(i, CAName);
								i++;
								
								if (rst.getString(7) == null) {						// date
									date = "--";
								} else {
									date = rst.getString(7).toString();
								}
								vec.add(i, date);
								i++;
								Logging.debug("stk ID "+ stkId + " stkName = "+ stkName+ " faceVal = " + faceVal+
											"\n tis= "+tis+ " mCap= "+mCap+" iwf= "+iwf+ "CAName= "+CAName+" date= "+date); 
								stkDetails = new StockDetails(stkId, stkName, faceVal, tis, mCap, iwf, CAName, date);
								tempData.add(stkDetails);
								
							}
							Logging.debug("No of cols = "+ tempData.size());
							rst.close();
							tableDatac = tempData;
							capitalChangeVec = vec;
						} catch (SQLException sqlexp) {
								Logging.error("Error : "+sqlexp.getMessage());
						}	
					
						
				 } catch (Exception e) {
						// TODO: handle exception
						Logging.error(" Error : "+e.getMessage());
				 }
			} catch(Exception e) {
					    	Logging.error(" Error : "+e.getMessage());
					    	
			}
			finally {
					    	try {
					    		if(connection != null) {
					    			connection.close();
					    		}
					    	} catch(Exception ee) {
					    			
				    		}
			}
		
		return tableDatac;
	}
	
   /**
	* @param tableDatac The tableDatac to set.
	*/
	public void setTableDatac(ArrayList tableDatac) {
		this.tableDatac = tableDatac;
	}
	
	/* Upto Tabledata For Capital Change Report */
	
   /**
	* @return Returns the stockName.
	*/
	
	public String getStock_name(String stock_id) {
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
//		select * from preferencedetail where preference_id\=?
		Logging.debug("Query = "+query);
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
//		 TODO: handle exception
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

//		 Logging.getDebug("sizeeeeeeee of tabledata"+tableData.size());
		this.stock_name1 = stock_name1;
		return stock_name1;
	
	}
	
	public String getStockName() {
		Logging.debug(" inside getindExchName");
		try{
			String id = getSelectExchange();
			Enumeration e;
			String str;
			String iname="";//,ival="";
			e = IndexNameHash.keys();
			while(e.hasMoreElements())	{
				str = (String)e.nextElement();
				iname=(String)IndexNameHash.get(str);
				if(str.equals(id)) {
					Logging.debug(" found !!!!");
					stockName = iname;
					break;
				}
			}
			Logging.debug(" indExchName = "+ stockName);
		} catch(Exception e) {
			Logging.debug(" Error "+ e.toString());
		}
		return stockName;
	}
	
	
   /**
	* @param stockName The stockName to set.
	*/
	
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	
	
   /**
	* @return Returns the selectExchange.
	*/
	public String getSelectExchange() {
		return selectExchange;
	}
	
   /**
	* @param selectExchange The selectExchange to set.
	*/
	public void setSelectExchange(String selectExchange) {
		this.selectExchange = selectExchange;
	}
	
   /**
	* @return Returns the selectExchgCollection.
	*/
	public Collection getSelectExchgCollection() {
		Vector vec = new Vector();
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			if(connection == null){
				connection = con.getdbConnection();
			}
			stmt = connection.prepareStatement(ConnectInit.queries.getProperty("stock_exchange_list"));
			ResultSet rst = stmt.executeQuery();
			
			while(rst.next()){
				vec.add(new LabelValueBean(rst.getString(2),rst.getString(1)));
				IndexNameHash.put(rst.getString(1),rst.getString(2));
			}
				
			rst.close();
			stmt.close();
			selectExchgCollection = vec;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
			Logging.debug(e);
		}	finally {
			try {
				if(connection != null){
					connection.close();
				}
			} catch (Exception ee) {
				try {
					if(connection    != null) {
						connection.close();
					}
				} catch(Exception ex) {
					Logging.error(" Error : Unable to close Connection "+ex.getMessage());
				}
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
					
		return selectExchgCollection;
	}
	
	/**
	 * @param selectExchgCollection The selectExchgCollection to set.
	 */
	public void setSelectExchgCollection(Collection selectExchgCollection) {
		this.selectExchgCollection = selectExchgCollection;
	}

	
	/**
	    * @return Returns the fdate.
		*/
		public String getFrdate() {
			return frdate;
		}
		

	   /**
		* @param frdate The frdate to set.
		*/
		public void setFrdate(int fdate11) {
			
			long newd=fdate11*24*60*60*1000L;
		    java.util.Date s4 = new Date();
		    long t2           = s4.getTime();
		    long t1           = t2-newd; 
		    Date s1           = new Date(t1);
		    SimpleDateFormat ft1 = new SimpleDateFormat("dd-MM-yyyy");
		    frdate             = ft1.format(s1);
		    
		}
		
	   /**
		* @return Returns the todate.
		*/
		public String getTodate() {
			return todate;
		}
		
	   /**
		* @param todate The todate to set.
		*/
		public void setTodate(Date tdate11) {
			Date curr = new Date();      
		    SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		    todate = ft.format(curr);   
		}
		
	
		/*   From for companyWeightage Report*/
		
		/**
		 * @return Returns the companyWeightage.
		 */
		public ArrayList getCompanyWeightage() {
			totalMcap=0;
			totalWt=0;
			Connect con=ConnectInit.getConnect();
			Connection connection=null;
			PreparedStatement pst=null;
			ResultSet rs=null;
			String company=null,mcap=null,weightage=null;
			ArrayList temp=new ArrayList();
			
			
			String local_id = getSelectUser();
			String stockidd = null;
			String exchange_id = null;
			int days_diff=0;
			try{
				if(connection==null)
					connection=con.getdbConnection();
					try {
						
						
									
									String query = ConnectInit.queries.getProperty("select_*_from_preferencedetail12");
									//Logging.getDebug("Query   = "+query);
									pst = connection.prepareStatement(query);
									pst.setString(1,local_id);
									pst.setString(2,"Company Weightage");
									rs = pst.executeQuery();
									
									while(rs.next())
									{
										
										stockidd = null;
										exchange_id = rs.getString("exchange_id");
										exchange_id = rs.getString("index_id1"); //important
										days_diff = rs.getInt("days_difference");
										//Logging.getDebug("days_diff"+days_diff);
										//stockidd = rs.getString("stock_id1");
										
									}
									
									if(isCheckdate()== true)   // select date from calendar
									{
										to=getTod();
										from=getFromd();
									}
									else                        // date generated from days difference from database
									{
									Date curr = new Date(); 
									setFrdate(days_diff);
									from=getFrdate();
									//setTodate(curr);
									//to=getTodate();
									
									}
						
						
						
						//String local_date="10-01-2007";
						//String local_d1="3252";
						String local_date="" ;
						String local_d1="";
						//String local_date=getDate();
						//String local_d1=getD1();
						 local_date=from;
						 local_d1=exchange_id;
						//String query = con.queries.getProperty("company_wise_weightage");
						query = ConnectInit.queries.getProperty("company_wise_weightage");
						Logging.debug("Query   = "+query);
						pst = connection.prepareStatement(query);
						pst.setString(1, local_d1);
						pst.setString(2, local_date);			
						rs = pst.executeQuery();
						int i=0;
						CompanyWeightageVector.clear();
						while (rs.next()) {
							if (rs.getString(1) == null) {
								company="--";
								CompanyWeightageVector.add(i, "--");
								
							} else {
								company=rs.getString(1);
								CompanyWeightageVector.add(i, rs.getString(1));
								
							}
							i++;
							if (rs.getString(2) == null) {
								mcap="0";
								CompanyWeightageVector.add(i, "0");
								
							} else {
								mcap=rs.getString(2);
								CompanyWeightageVector.add(i, rs.getString(2));
								double NumericMcap=Double.parseDouble(mcap);
								totalMcap=totalMcap+NumericMcap;
							}
							i++;
							if (rs.getString(3) == null) {
								weightage="0";
								CompanyWeightageVector.add(i, "0");
							} else {
								weightage=rs.getString(3);
								double NumericWeightage=Double.parseDouble(weightage);
								totalWt=totalWt+NumericWeightage;
								CompanyWeightageVector.add(i, rs.getString(3));
							}
							i++;
							CompanyWiseWeightageDetails cww=new CompanyWiseWeightageDetails(company,mcap,weightage);
							temp.add(cww);
						}//end of while 
						
					} catch (Exception e) {
						// TODO: handle exception
						Logging.error(" Error : "+e.getMessage());
					}
			} catch(Exception e) {
					Logging.error(" Error : "+e.getMessage());
		    } finally {
					    	try {
					    		if(connection != null)
					    			connection.close();
					    	} catch(Exception ee) {
					    			try {
				    					if(connection != null)
				    						connection.close();
				    				} catch(Exception ex) {
				    					Logging.error(" Error : Unable to close Connection "+ex.getMessage());
			    					}
				    				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
					    	}
			}
			Logging.debug("Table Vector"+CompanyWeightageVector);
			setCompanyWeightageVector(CompanyWeightageVector);
			companyWeightage=temp;
			return companyWeightage;
			
		}
		
		/**
		 * @param companyWeightage The companyWeightage to set.
		 */
		public void setCompanyWeightage(ArrayList companyWeightage) {
			this.companyWeightage = companyWeightage;
		}
		

		/**
		 * @return Returns the companyWeightageVector.
		 */
		public Vector getCompanyWeightageVector() {
			return CompanyWeightageVector;
		}
		/**
		 * @param companyWeightageVector The companyWeightageVector to set.
		 */
		public void setCompanyWeightageVector(Vector companyWeightageVector) {
			this.CompanyWeightageVector = companyWeightageVector;
		}

		/*   Upto for companyWeightage Report*/
	
		/*  From   changes for Industry wise report   */
		/**
		 * @return Returns the IndwiseWeightageVector.
		 */

		public ArrayList getIndweighttable() {
			String industryname=null,marketcap=null,weightage=null,Strmvc=null,mar=null;
			vi=new Vector();
			Connection connection=null;
			double total1=0.00,total2=0.00;
			double strweightage=0.0,market=0.00;
			//String index12 = index;
			//String index12 = "3252";
			AdjustDecimal ad = ConnectInit.getAdjustDecimal();
			
			String index1,index2,tno1=null;
			index1=index2=index;
//			Connect con=new Connect();
			indweighttable = new ArrayList();
			ArrayList tempdata=new ArrayList();
			IndexWise indexwise;
			
			String local_id = getSelectUser();
			String index_id = null;
			
			
			try {
				if(connection == null){
					connection=con.getdbConnection();
				}	
				
				String query = ConnectInit.queries.getProperty("select_*_from_preferencedetail12");
				//Logging.getDebug("Query   = "+query);
				pst = connection.prepareStatement(query);
				pst.setString(1,local_id);
				pst.setString(2,"Industry Weightage");
				rs = pst.executeQuery();
				
				while(rs.next())
				{
					
					
					index_id = rs.getString("index_id1"); //important index id
				
				}
				
				rst = Icr.indweightResult(connection,"industry_wise_weightage", index_id);
				int i = 0;
				Logging.debug("setVector_indweighttable of Industry wise weightage");
				try {

					while (rst.next()) {
						
						if (rst.getString(1) == null) {
							industryname= "0";
						} else {
							industryname=rst.getString(1);
							vi.add(i,rst.getString(1));
						}
						i++;
						if (rst.getString(2) == null) {
							marketcap= "0";
						} else {
							marketcap=rst.getString(2);
							marketcap=ad.indexcompose(marketcap);
							marketcap=org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric(marketcap);
							vi.add(i,rst.getString(2));
						}
						i++;
						mar=rst.getString(2);
						mar=ad.indexcompose(mar);
						market=Double.parseDouble(mar);
						total2=total2+market;
						tno1=ad.shareholdingpatt(total2);
						tno1=org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric(tno1);
						if (rst.getString(3) == null) {
							weightage= "0";
						} else {
							weightage=rst.getString(3);
							weightage=ad.indexcompose(weightage);
							vi.add(i,rst.getString(3));
						}
						i++;
						Strmvc=rst.getString(3);
						strweightage=Double.parseDouble(Strmvc);
						total1=total1+strweightage;
						indexwise = new IndexWise(industryname,marketcap,weightage);
						tempdata.add(indexwise);
					}
					rst.close();
				} catch (SQLException sqlexp) {
					Logging.error("Error : "+sqlexp.getMessage());
				}
			} catch(Exception e) {
				Logging.error(" Error : "+e.getMessage());
			} finally {
				try {
					if(connection!=null)
						connection.close();
				} catch(Exception ee) {
					try {
						if(connection != null)
							connection.close();
					} catch(Exception ex){
						Logging.error(" Error : Unable to close connection "+ex.getMessage());
					}
					Logging.error(" Error : Unable to close connection "+ee.getMessage());
				}
			}
			indweighttable=tempdata;
			 Logging.debug(indweighttable);
			 setTotal(total1);
			 setVal(tno1);
			 setVi(vi);
			 return indweighttable;
		}
		/**
		 * @param vector_indtable The vector_indtable to set.
		 */
		public void setIndweighttable(ArrayList indweighttable) {
			this.indweighttable =indweighttable;
		}

		

		/**
		 * @return Returns the val.
		 */
		public String getVal() {
			return val;
		}

		/**
		 * @param val The val to set.
		 */
		public void setVal(String val) {
			this.val = val;
		}

		/**
		 * @return Returns the vi.
		 */
		public Vector getVi() {
			return vi;
		}

		/**
		 * @param vi The vi to set.
		 */
		public void setVi(Vector vi) {
			this.vi = vi;
		}
		
		/*   Upto  for Stock Contribution to Change Report*/

		/*   From for Stock Contribution to Change Report*/
		
		/**
		 * @return Returns the StockcotriIndexchange.
		 */

		public ArrayList getStockcotriIndexchange() {
			
			Logging.debug("Inside Vector_stockcotriIndexchange");
			
			//String index1=index;
			//String fodate=from;
			//String todate=to;
			String index1="";
			String fodate="";
			String todate="";
			
			String local_id = getSelectUser();
			String id1 = null;
			String exchange_id = null;
			int days_diff = 0;
			
			
			vi=new Vector();
			String stockname=null,indexmarket=null,stockmarket=null,weightage=null;
			stockcotriIndexchange = new ArrayList();
			ArrayList tempdata=new ArrayList();
			StockContri stockcontri;
			try {
				Vector date=new Vector();
	//			Connect con=new Connect();
				connection=con.getdbConnection();
					if(Connect.con==null)
					{
						con.getConnection();
					}
					
													
									String query = ConnectInit.queries.getProperty("select_*_from_preferencedetail12");
									//Logging.getDebug("Query   = "+query);
									pst = connection.prepareStatement(query);
									pst.setString(1,local_id);
									pst.setString(2,"Stock Contribution To Change In Index");
									rs = pst.executeQuery();
							
									while(rs.next())
									{
										id1 = null;
										
										index1 = rs.getString("index_id1");
										days_diff = rs.getInt("days_difference");
										Logging.debug("days_diff" +days_diff);
										//Logging.getDebug("id1" +id1);
									
										if(exchange_id!=null){
											id1 = exchange_id;
											Logging.debug("id1" +id1);
										}  
									}
									
									if(isCheckdate()== true)   // select date from calendar
									{
										todate=getTod();
										fodate=getFromd();
									}
									else                        // date generated from days difference from database
									{
									Date curr = new Date(); 
									setFrdate(days_diff);
									fodate=getFrdate();
									setTodate(curr);
									todate=getTodate();
					
									}
					
					rst = con.StockcontriIndexResult("stock_contribution_to_change_in_index",index1,fodate,todate);
					int i = 0,q=0;
					Logging.debug("setVector_stockcotriIndexchange");	
					try {
					while(rst.next())
					{
						Logging.debug("inside first while end "+rst);
					
						try{
							//Logging.getDebug("get tring "+rst.getString(1));
							if(rst.getString(1) == null) {
								stockname= "0";
								Logging.debug("after get");
							} else {
								stockname=(rst.getString(1).trim());
								vi.add(i,rst.getString(1));
							}
						}catch(Exception e){
							Logging.debug("Error while returning resultset"+e.getMessage());
						}
						
						i++;
						if (rst.getString(2) == null) {
							indexmarket= "0";
						} else {
							 
								String str=rst.getString(2);
								String str2=str.substring(str.indexOf(46),(str.indexOf(46)+3));
								String str1=str.substring(0,str.indexOf(46))+str2;
								indexmarket= str1;
								vi.add(i,indexmarket);
								//Logging.getDebug((String)rst.getString(2));
						}
						i++;
						
						if (rst.getString(3) == null) {
							stockmarket= "0";
						} else {
							String str=rst.getString(3);
							String str2=str.substring(str.indexOf(46),(str.indexOf(46)+3));
							String str1=str.substring(0,str.indexOf(46))+str2;
							stockmarket= str1;
							vi.add(i,stockmarket);
							//Logging.getDebug((String)rst.getString(3));
						}
						i++;
						if (rst.getString(4) == null) {
							weightage= "0";
						} else {
							weightage=rst.getString(4);
							vi.add(i,rst.getString(4));
							//Logging.getDebug((String)rst.getString(4));
						}
						i++;
						date.add(q,rst.getString(5));
						
						q++;
						
						//Logging.getDebug((String)rst.getString(5));
						date.add(q,rst.getString(6));
						
						//Logging.getDebug((String)rst.getString(6));
						q++;		
						
						stockcontri = new StockContri(stockname,indexmarket,stockmarket,weightage);
						tempdata.add(stockcontri);
						
					}
				} catch (SQLException sqlexp) {
						Logging.error("SQL Error :"+sqlexp.getMessage());
				}
					
				Logging.debug(""+stockcotriIndexchange.size());
				
				Logging.debug("After first while end");
				vector_remStockid = new Vector();
				if(date.size()>1){
				 tdate=(String)date.get(0);
				 fdate=(String)date.get(1);
				}
				Logging.debug("in bean"+index+"  "+fdate+" "+"  "+tdate);
				ResultSet rst1 = new Connect().StockcontriSidlResult("stock_contribution_stock_id_left",index1,fdate,tdate);
				int j=0;
				while(rst1.next())
				{
					vector_remStockid.add(j, rst1.getString(1));
				}
				if(vector_remStockid.size()!=0)
				{
					for(int k=0;k<(vector_remStockid.size());k++)
					{
						String s_id=(String)vector_remStockid.get(k);
						ResultSet rst2 = new Connect().stockcontriIndResult("stock_contribution_to_change_in_index_individual",index1,s_id,todate,fodate);
						Logging.debug("setVector_stockcotriIndexchange");
						
							while (rst2.next()) {
								i++;
								if(rst.getString(1)==null) {
									stockname= "0";
									
								} else {
									stockname=(rst.getString(1).trim());
									
								}

								if (rst.getString(2) == null) {
									indexmarket= "0";
								} else {
									 
										String str=rst.getString(2);
										String str2=str.substring(str.indexOf(46),(str.indexOf(46)+3));
										String str1=str.substring(0,str.indexOf(46))+str2;
										indexmarket= str1;
										//Logging.getDebug((String)rst.getString(2));
								}

								if (rst.getString(3) == null) {
									stockmarket= "0";
								} else {
									String str=rst.getString(3);
									String str2=str.substring(str.indexOf(46),(str.indexOf(46)+3));
									String str1=str.substring(0,str.indexOf(46))+str2;
									stockmarket= str1;
									//Logging.getDebug((String)rst.getString(3));
								}
								if (rst.getString(4) == null) {
									weightage= "0";
								} else {
									weightage=rst.getString(4);
									//Logging.getDebug((String)rst.getString(4));
								}
							//	stockcontri = new StockContri(stockname,indexmarket,stockmarket,weightage);
								Logging.debug("-----------------------------------"+stockname);
								
							}
							rst.close();
							
						}
					}
				
					
			} catch (SQLException sqlexp) {
				Logging.error("SQL Error :"+sqlexp.getMessage());
			}
			stockcotriIndexchange=tempdata;
			Logging.debug("SQL Error before return :"+stockcotriIndexchange);
			setVi(vi);
			
			return stockcotriIndexchange;
		}
		/**
		 * @param vector_stockcotriIndexchange
		 *            The vector_stockcotriIndexchange to set.
		 */
		public void setStockcotriIndexchange(ArrayList stockcotriIndexchange) {
			this.stockcotriIndexchange= stockcotriIndexchange;
		}
	
		/*   Upto for Stock Contribution to Change Report*/
		
		
		
		/* From index compare report */
		
		
		/**
		 * @return Returns the d2.
		 */
		public String[] getD2() {
			return d2;
		}

		/**
		 * @param d2 The d2 to set.
		 */
		public void setD2(String[] d2) {
			this.d2 = d2;
		}

		/**
		 * @return Returns the from.
		 */
		public String getFrom() {
			return from;
		}

		/**
		 * @param from The from to set.
		 */
		public void setFrom(String from) {
			this.from = from;
		}

		/**
		 * @return Returns the to.
		 */
		public String getTo() {
			return to;
		}

		/**
		 * @param to The to to set.
		 */
		public void setTo(String to) {
			this.to = to;
		}
		
		/* Upto index compare report */

		/*  From Index returns and volatility */
		
		

		/**
		 * @return Returns the vector_index_rv.
		 */
		
		public ArrayList getFinal_Vector() {	
			
			ArrayList arr=new ArrayList();
			
			try{
				connection=con.getdbConnection();
			String index1=null,
				local_id=getSelectUser();
			int days_diff=0;
			
   			String query = ConnectInit.queries.getProperty("select_*_from_preferencedetail12");
			pst = connection.prepareStatement(query);
			pst.setString(1,local_id);
			pst.setString(2,"Index Returns And Volatility");
			rs = pst.executeQuery();
	
			while(rs.next())
			{
										
				index1 = rs.getString("index_id1");
				days_diff = rs.getInt("days_difference");
				Logging.debug("days_diff" +days_diff);
				
				
			}
			
			if(isCheckdate()== true)   // select date from calendar
			{
				to=getTod();
				from=getFromd();
			}
			else                        // date generated from days difference from database
			{
			Date curr = new Date(); 
			setFrdate(days_diff);
			from=getFrdate();
			setTodate(curr);
			to=getTodate();
			
			}
		   	
			setIndex_name1(index1);
			indexList=getIndex_name1();
			//int len=indexList.length;
			}catch(Exception e)	{
				try{
					if (connection!=null)
						connection.close();
				}
				catch(Exception ex) {}
			}
			
			
			Logging.debug("Inside vector_indexList_rv");
			
			if(indexList!=null) 
			   	if(from!=null && to!=null) {   
			   		try{
				
			   				//if(connection==null)					
			   				connection=con.getdbConnection();				
			   				harrier.income.com.report.AdjustDecimal ad=new harrier.income.com.report.AdjustDecimal();
			   				final_Vector = new ArrayList();		
			   				int j = 0;	
			   				//Logging.getDebug(indexList.length+"  "+from+" "+"  "+to);
			   				// Logging.getDebug("IndexList length is "+indexList.length);
			   				
			   				for(int k=0;k<indexList.length;k++)
					    	{
			   					//vector_index_rv1.clear();
			   					vector_index_rv1 = new Vector();
							
			   					Logging.debug("Value of K  "+indexList[k]);
							
			   					String Query =ConnectInit.queries.getProperty("indexwise_returns_and_volatility");
			   					try {
			   							pst = connection.prepareStatement(Query);
			   							pst.setString(1, indexList[k]);
			   							pst.setString(2, from);
			   							pst.setString(3, to);
			   							rst = pst.executeQuery();
			   							vExcel.clear();
						
						
										Logging.debug("setVector_index_rv1");
										
										int i=0;
										double tmcv=0.00;
										Logging.debug("Resultset ="+rst);
						
										while(rst.next())
										{
											if (rst.getString(1) == null) {
												vector_index_rv1.add(i, "0");
												//vExcel.add(i, "0");
											} 
											else {
													vector_index_rv1.add(i, rst.getString(1));
													//vExcel.add(i, rst.getString(1));
													}
							
											i++;
		
											if (rst.getString(2) == null) {
												vector_index_rv1.add(i, "--");
												//vExcel.add(i, "--");
											} 
											else {
												vector_index_rv1.add(i, rst.getString(2));
												//vExcel.add(i, rst.getString(2));
											}
											i++;
		
											if (rst.getString(3) == null) {
												vector_index_rv1.add(i, "0");
												//vExcel.add(i, "0");
											} 
											else {
												vector_index_rv1.add(i, rst.getString(3));
												//vExcel.add(i, rst.getString(3));
												tmcv=tmcv+(double)Double.parseDouble(rst.getString(3));
											}
											i++;					
										}					
						
						
						
						Logging.debug("vector size "+vector_index_rv1.size());
						
						int m=0;String str1=null,str2=null;
						
						if(vector_index_rv1.size()!=0)
						{
							str1=(String)vector_index_rv1.get(m);
							m++;j++;
							
							str2=(String)vector_index_rv1.get(m);
							m++;j++;
							
							Logging.debug("before getMonthlyReturns");
							
							double mr=getMonthlyReturns(vector_index_rv1);
							
							String mrstr=new Double(mr).toString();
							
							mrstr=ad.indexcompose(mrstr);
							
						//	vector_index_rv.add(j,mrstr);
							m++;j++;
							double volret=getAvgDailyVolatility(vector_index_rv1,tmcv);
							
							String volretstr=new Double(volret).toString();
							volretstr=ad.indexcompose(volretstr);
						//	vector_index_rv.add(j,volretstr);
							j++;	
							
							final_Vector.add(new returnVol(str1,str2,mrstr,volretstr));
							int tmp=0;
							
							vExcel.add(tmp,str2);
							tmp++;
							vExcel.add(tmp,mrstr);
							tmp++;
							vExcel.add(tmp,volretstr);
							tmp++;
							
							Logging.debug("vector size 1 "+final_Vector.size());
							Logging.debug("vExcel vector size>>> "+vExcel.size());
						}
						
						}
						catch(SQLException ex){}
					}
			} finally{
				try{
					if(connection!=null)
						connection.close();
				}catch(SQLException ee){
					try{
						if(connection!=null)
							connection.close();
					}catch(Exception ex){
						Logging.error(" Error : Unable to close connection "+ex.getMessage());
					}
					Logging.error(" Error : Unable to close connection "+ee.getMessage());
				}
			}
			}
			Logging.debug("Value of Final_Vector "+final_Vector);
			Logging.debug("Value of vExel>>>>> "+vExcel);
			setVExcel(vExcel);
			return final_Vector;
		}
		
		
		/**
		 * @param final_Vector
		 *            The final_Vector to set.
		 */
		public void setFinal_Vector(ArrayList final_Vector) {
			this.final_Vector=final_Vector;
		}
		

		public double getAvgDailyVolatility(Vector v,double indexmean)
		{
			
			try{
							
			
				Logging.debug("Inside getAvgDailyVolatility()");
				double sum_volatility=0.0,vratio=0.0,sum_indexvolatility=0.0,sum_indexvalue=0.0;
				Vector vol=new Vector();
				int i=0,m=0;
				indexmean=indexmean/(v.size()/3);
				Logging.debug("indexmean "+indexmean);				
				i=0;
				
				while(i<v.size())
				{
					i++;i++;
					double indval1=(double)Double.parseDouble((String)v.get(i));
					Logging.debug("indval1 "+indval1+" indexmean "+indexmean);
					sum_indexvalue=(indval1-indexmean);
					vol.add(new Double(sum_indexvalue).toString());
					Logging.debug("sum_indexvalue "+sum_indexvalue+" sum_indexvolatility "+sum_indexvolatility);
					sum_indexvolatility=sum_indexvolatility+sum_indexvalue;
					Logging.debug(" sum_indexvolatility "+sum_indexvolatility);
					i++;
				}
				
				i=0;
				
				while(i<vol.size())
				{
					double mult1=((double)Double.parseDouble((String)vol.get(i))-sum_indexvolatility);
					Logging.debug(" mult1 "+mult1);
					double mult=(((double)Double.parseDouble((String)vol.get(i))-sum_indexvolatility)*((double)Double.parseDouble((String)vol.get(i))-sum_indexvolatility));
					Logging.debug(" sum_volatility "+sum_volatility+" mult "+mult);
					sum_volatility=sum_volatility+mult;
					i++;
				}
			 vratio= 1.00/(((vol.size())));
			 Logging.debug(" sum_volatility "+sum_volatility);
			 double avgdailyvol=(Math.sqrt((vratio*sum_volatility)));
			 Logging.debug("vector size "+v.size()+" avgdailyvol is "+avgdailyvol);
			return avgdailyvol;
			}
			
			finally{
				try{
					
				}
				catch(Exception e)
				{
					
				}
			}
			
		}
		public double getMonthlyReturns(Vector v)
		{
				Logging.debug("Inside getMonthlyReturns()");
				double mreturn=0.0,lmr=0.0,fmr=0.0;
				int l=v.size();
				Logging.debug("Inside getMonthlyReturns()"+l);
			if(v.size()!=0){
				lmr=(double)Double.parseDouble((String)v.get(l-1));
				fmr=(double)Double.parseDouble((String)v.get(2));
			}
			Logging.debug(" l size "+l+" lmr "+lmr+" fmr"+fmr);
			if(fmr!=0.00){
					mreturn=((lmr-fmr)/fmr);
			}else{
					mreturn=0.00;
			}
			Logging.debug("mreturn "+mreturn);
			return mreturn;
		}
		
		
		
		/*  Upto Index returns and volatility */
		
		/**
		 * @return Returns the indexName.
		 */
		/*	
		public String getIndexName() {
			return indexName;
		}
		*/
		/**
		 * @param indexName The indexName to set.
		 */
		/*
		public void setIndexName(String indexName) {
			this.indexName = indexName;
		}
		
		*/
		/*  From for stock dividend report */
		

		/**
		 * @return Returns the tableDataSD.
		 */
		public ArrayList getTableDataSD() {
			Logging.debug(" Inside getTableDataSD");
			String stk_id=null, stk_name=null, face=null, tis = null, mcv=null, amt=null, date=null;
			AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		//	org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
			
			Connection connection=null;
			StockDetails stkDetails;
			ArrayList tempData= new ArrayList();
			Vector vec = new Vector();
			int i=0;
			
			try {
				if(connection == null)
					connection = con.getdbConnection();
				
					filter="";
					from="";
					to="";
					selectIndExch="";
												
					PreparedStatement pst = null;
					ResultSet rs = null;
					//ArrayList temp = new ArrayList();
					String local_id = getSelectUser();
					
					String id1 = null;
					int days_diff = 0;
					
											
								
					String query = ConnectInit.queries.getProperty("select_*_from_preferencedetail12");
					//Logging.getDebug("Query   = "+query);
					pst = connection.prepareStatement(query);
					pst.setString(1,local_id);
					pst.setString(2,"Stock Dividend");
					rs = pst.executeQuery();
					
					while(rs.next())
					{
						id1 = null;
						//exchange_id = rs.getString("exchange_id");
						id1 = rs.getString("index_id1");
						days_diff = rs.getInt("days_difference");
						Logging.debug("days_diff" +days_diff);
						//Logging.getDebug("id1" +id1);
								
					}
					if(isCheckdate()== true)   // select date from calendar
					{
						to=getTod();
						from=getFromd();
					}
					else                        // date generated from days difference from database
					{ 					
					Date curr = new Date(); 
					setFrdate(days_diff);
					from=getFrdate();
					setTodate(curr);
					to=getTodate();
					}
				
				/*
				Logging.getDebug("tabledatasd"+filter+from+to+selectIndExch);
				if(filter.equals("1")){  // Exchange wise
					Logging.getDebug(" Inside filter = 1(Exchange )");
					rst = con.changeInStockDetailResult("stock_divident_exchange_wise",selectIndExch,from,to);
					
				}else {
					Logging.getDebug("Inside filter = 2 (Index) ");
					rst = con.changeInStockDetailResult("stock_divident_index_wise",selectIndExch,from,to);
										
				}
				*/
				String Query = ConnectInit.queries.getProperty("stock_divident_index_wise");
				
				pst = connection.prepareStatement(Query);
				pst.setString(1, id1);
				pst.setString(2, from);
				pst.setString(3, to);
				rst = pst.executeQuery();
				
				
				
				Logging.debug("rst in traded volume is "+rst);
				
				while (rst.next()) {

					if (rst.getString(1) == null) {							// stock id
						stk_id = "0";
						//vector_stockDivident.add(i, "0");
					} else {
						stk_id = rst.getString(1).toString();
						//vector_stockDivident.add(i, rst.getString(1));
					}
					vec.add(i,stk_id);
					i++;

					if (rst.getString(2) == null) {							// stock name
						stk_name = "--";
						//vector_stockDivident.add(i, "--");
					} else {
						stk_name = rst.getString(2).toString();
						//vector_stockDivident.add(i, rst.getString(2));
					}
					vec.add(i,stk_name);
					i++;

					if (rst.getString(3) == null) {							// face Val
						face = "0";
						//vector_stockDivident.add(i, "0");
					} else {
						face = rst.getString(3).toString();
						//vector_stockDivident.add(i, rst.getString(3));
					}
					vec.add(i,face);
					i++;
					
					if (rst.getString(4) == null) {							// tis
						tis = "0";
						//vector_stockDivident.add(i, "0");
					} else {
						tis = rst.getString(4).toString();
						//vector_stockDivident.add(i, rst.getString(4));
					}
					vec.add(i,tis);
					i++;

					if (rst.getString(5) == null) {							// mcv
						mcv = "0.00";
						//vector_stockDivident.add(i, "0.00");
					} else {
						String ad1=(String) rst.getString(5);            		
		        		ad1=ad.indexcompose(ad1);
		        		mcv = ad1;
						//vector_stockDivident.add(i, ad1);
					}
					vec.add(i,mcv);
					i++;
					if (rst.getString(6) == null) {							// amount
						amt = "0.00";
						//vector_stockDivident.add(i, "0.00");
					} else {
						amt = rst.getString(6).toString();
						//vector_stockDivident.add(i, rst.getString(6));
					}
					vec.add(i,amt);
					i++;	
					if (rst.getString(7) == null) {							// date
						date = "--";
						//vector_stockDivident.add(i, "--");
					} else {
						date = rst.getString(7).toString();
						//vector_stockDivident.add(i, rst.getString(7));
					}
					vec.add(i,date);
					i++;
					
					stkDetails = new StockDetails(stk_id, stk_name,face, tis, mcv, amt, date, 21);
					tempData.add(stkDetails);
					
				}
				tableDataSD = tempData;
				stkDividentVec= vec;
				Logging.debug("size of arraylist "+tempData.size());
				return tableDataSD;
			} catch (Exception sqlexp) {
				Logging.error("Error : "+sqlexp.getMessage());
			}finally{
				try {
					if(connection!=null)
						connection.close();
				} catch(Exception ee){
					try {
						if(connection!=null)
							connection.close();
					} catch(Exception ex){
						Logging.error(" Error : Unable to close Connection "+ex.getMessage());
					}
					Logging.error(" Error : Unable to close Connection "+ee.getMessage());
				}
			}
			Logging.debug("tabledatasd"+tableDataSD);
			return tableDataSD;
		}
		/**
		 * @param tableDataSD The tableDataSD to set.
		 */
		public void setTableDataSD(ArrayList tableDataSD) {
			this.tableDataSD = tableDataSD;
		}
		
		/**
		 * @return Returns the stkDividentVec.
		 */
		public Vector getStkDividentVec() {
			return stkDividentVec;
		}

		/**
		 * @param stkDividentVec The stkDividentVec to set.
		 */
		public void setStkDividentVec(Vector stkDividentVec) {
			this.stkDividentVec = stkDividentVec;
		}
		
		/*  Upto for stock dividend report */
		
		/* From changes for Traded Volume */
		
		/**
		 * @return Returns the tableDataTr.
		 */
		public ArrayList getTableDataTr() {
			Logging.debug(" Inside getTableDataTr");
	//		org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
			AdjustDecimal ad = ConnectInit.getAdjustDecimal();
			//Logging.getDebug("Inside setVector_traded_volume exchange_id and indexid is "+exch_id+" , "+ind_id);

			String stk_id=null, stk_name=null, trd_vol=null;
			Connection connection=null;
			StockDetails stkDetails;
			ArrayList tempData= new ArrayList();
			Vector vec=new Vector();
			int i=0;
			
			//setFilter("2");	
			//setTraded_volume("1000");
			
			try {
				if(connection == null)
					connection = con.getdbConnection();
				
					filter="2";
					from="";
					to="";
					selectIndExch="";
												
					PreparedStatement pst = null;
					ResultSet rs = null;
					//ArrayList temp = new ArrayList();
					String local_id = getSelectUser();
					
					String id1 = null;
					//String stockid1 = null;
					int days_diff = 0;
					
					
					
					String query = ConnectInit.queries.getProperty("select_*_from_preferencedetail12");
					//Logging.getDebug("Query   = "+query);
					pst = connection.prepareStatement(query);
					pst.setString(1,local_id);
					pst.setString(2,"Traded Volume");
					rs = pst.executeQuery();
					while(rs.next())
					{
						id1 = null;
						//exchange_id = rs.getString("exchange_id");
						id1 = rs.getString("index_id1");
						days_diff = rs.getInt("days_difference");
						Logging.debug("days_diff" +days_diff);
						//stockid1 = rs.getString("stock_id1");
						//Logging.getDebug("id1" +id1);
					}
					if(isCheckdate()== true)   // select date from calendar
					{
						to=getTod();
						from=getFromd();
					}
					else                        // date generated from days difference from database
					{
						Date curr = new Date(); 
						setFrdate(days_diff);
						from=getFrdate();
						setTodate(curr);
						to=getTodate();
				
					}
					//Vector vTraded=new Vector();
					
					vTraded.add(0,id1);
					vTraded.add(1,from);
					vTraded.add(2,to);
					
					if(filter.equals("1")){  // Exchange wise
						Logging.debug(" Inside filter = 1(Exchange )");
						rst = con.tradedVolumeResult("traded_volume_list_exchange_wise",selectIndExch,traded_volume,from,to);
					} else {
						Logging.debug("Inside filter = 2 (Index) ");
						//rst = con.tradedVolumeResult("traded_volume_list_index_wise",selectIndExch,traded_volume,from,to);
					
					}
				
				
					String Query = ConnectInit.queries.getProperty("traded_volume_list_index_wise");
					Logging.debug(Query);
					pst = connection.prepareStatement(Query);
					pst.setString(1, id1);
					pst.setString(2, "1");
					pst.setString(3, from);
					pst.setString(4, to);
					rst = pst.executeQuery();	
					
					Logging.debug("rst in traded volume is "+rst);
					
					while(rst.next()) {
				
						if (rst.getString(1) == null) {				// stock id
							stk_id= "0";
						} else {
							stk_id= rst.getString(1).toString();
						}
						vec.add(i,stk_id);
						i++;
								
						if (rst.getString(2) == null) {				// stock name
							stk_name= "--";
						} else {
							stk_name= rst.getString(2).toString();
						}
						vec.add(i,stk_name);
						i++;
							
						if (rst.getString(3) == null) {				// traded vol
							trd_vol= "0.00";
						} else {
							String ad1=(String) rst.getString(3);            		
				        	ad1=ad.indexcompose(ad1);
				        	trd_vol= ad1;
						}
						vec.add(i,trd_vol);
						i++;
						
						stkDetails = new StockDetails(stk_id, stk_name,trd_vol);
						tempData.add(stkDetails);	
						
					}
					
				
				tableDataTr = tempData;
				trdVolVec=vec;
				Logging.debug("size of arraylist "+tempData.size());
				return tableDataTr;
			} catch (Exception sqlexp) {
				Logging.error("Error : "+sqlexp.getMessage());
			}finally{
				try{
					if(connection!=null)
						connection.close();
				}catch(Exception ee){
					
					Logging.error(" Error : Unable to close Connection "+ee.getMessage());
				}
			}
		return tableDataTr;
		}
		/**
		 * @param tableData The tableDataTr to set.
		 */
		public void setTableDataTr(ArrayList tableDataTr) {
			this.tableDataTr = tableDataTr;
		}
		
		/**
		 * @return Returns the trdVolVec.
		 */
		public Vector getTrdVolVec() {
			return trdVolVec;
		}

		/**
		 * @param trdVolVec The trdVolVec to set.
		 */
		public void setTrdVolVec(Vector trdVolVec) {
			this.trdVolVec = trdVolVec;
		}
		/**
		 * @return Returns the vTraded.
		 */
		public Vector getVTraded() {
			return vTraded;
		}
		/**
		 * @param vTraded The vTraded to set.
		 */
		public void setVTradede(Vector vTraded) {
			this.vTraded = vTraded;
		}

		
		/**
		 * @return Returns the traded_volume.
		 */
		public String getTraded_volume() {
			return traded_volume;
		}
		/**
		 * @param traded_volume The traded_volume to set.
		 */
		public void setTraded_volume(String traded_volume) {
			this.traded_volume = traded_volume;
		}

		/**
		 * @return Returns the filter.
		 */
		public String getFilter() {
			return filter;
		}

		/**
		 * @param filter The filter to set.
		 */
		public void setFilter(String filter) {
			this.filter = filter;
		}
		
		/* Upto changes for Traded Volume */
		
		//for separate ids and convert it into String array
		
		public void setIndex_name1(String index_id){
			

			StringTokenizer st = new StringTokenizer(index_id,",");
			String indexList1 []=new String[5];
			int i=0;

			while(st.hasMoreTokens())
			{
			indexList1[i]=st.nextToken();
			i++;

			}
			
			indexList=indexList1;
		}
		public String [] getIndex_name1(){
			return indexList;
		}

		
		public String getIndex_name2() {
			return index_name2;
		}

		//		for separate ids and generate names for  it 
		public void setIndex_name2(String index_id) {
			Connect con1=ConnectInit.getConnect();
			Connection connection1=null;
			PreparedStatement pst1=null;
			ResultSet rs1=null;
			String indname2=null;
			String local_id=index_id;
			try{
				if(connection1==null)
					connection1=con1.getdbConnection();
					try {
						
						
						String query = ConnectInit.queries.getProperty("query_copy_indexmaster");
						//select * from preferencedetail where preference_id\=?
						Logging.debug("Query   = "+query);
						pst1 = connection1.prepareStatement(query);
						try{
										
							Integer.parseInt(local_id);
							pst1.setString(1,local_id);
							rs1 = pst1.executeQuery();
							while(rs1.next())
							{
								indname2 =rs1.getString(2);
							}
												
						}
						catch(Exception e)
						{
														
							StringTokenizer st = new StringTokenizer(local_id,",");
							String indexNameList="";
							int i=0;
							
							while(st.hasMoreTokens())
							{
								String idx=st.nextToken();
								pst1.setString(1,idx);
								rs1 = pst1.executeQuery();
								if(rs1!=null){
									while(rs1.next())
									{  
										if(i==0)
										{
											indexNameList=indexNameList+rs1.getString(2);
											i++;
										}else
										{
											indexNameList=indexNameList+","+rs1.getString(2);
										}
																							 
									}
									
								}
								
							}
							indname2=indexNameList;
						}
											
					} catch (Exception e) {
						// TODO: handle exception
						Logging.error(" Error : "+e.getMessage());
											}
				    } catch(Exception e){
					    	Logging.error(" Error : "+e.getMessage());
					} finally {
					    	try{
					    		if(connection1!=null)
					    			connection1.close();
					    	} catch(Exception ee){
					    			try {
				    					if(connection1!=null)
				    						connection1.close();
				    				} catch(Exception ex){
				    					Logging.error(" Error : Unable to close Connection "+ex.getMessage());
			    					}
				    				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
					    	}
					}
					this.index_name2 = indname2;
		}
		
		
		//code for indexcompare OHLC
		
		public Vector getOhlcParam() {
	//		String index_id1=null,index_id2=null,index_id3=null;
			String fromdate="";
			String toDate="";
			Vector vec =new Vector();
			
			Connection connection = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
			//ArrayList temp = new ArrayList();
			String local_id = getSelectUser();
			
			String id1 = null;
	//		String exchange_id = null;
			int days_diff = 0;
					
				try{
					if(connection == null) {
					connection = con.getdbConnection();
					}
					try {
						
							String query = ConnectInit.queries.getProperty("select_*_from_preferencedetail12");
							//Logging.getDebug("Query   = "+query);
							pst = connection.prepareStatement(query);
							pst.setString(1,local_id);
							pst.setString(2,"Index Compare OHLC");
							rs = pst.executeQuery();
					
							while(rs.next())
							{
								id1 = null;
								
								id1 = rs.getString("index_id1");
								days_diff = rs.getInt("days_difference");
								Logging.debug("days_diff" +days_diff);
								//Logging.getDebug("id1" +id1);
							
								
							}
							if(isCheckdate()== true)   // select date from calendar
							{
								toDate=getTod();
								fromdate=getFromd();
							}
							else                        // date generated from days difference from database
							{
							Date curr = new Date(); 
							setFrdate(days_diff);
							fromdate=getFrdate();
							setTodate(curr);
							toDate=getTodate();
							}
							setIndex_name1(id1);					
							String[] s1=getIndex_name1();
							vec.add(0,fromdate);
							vec.add(1,toDate);
							vec.add(2,s1);
							
							
					 } catch (SQLException sqlexp) {
									Logging.error("Error : "+sqlexp.getMessage());
					 } catch (Exception e) {
							// TODO: handle exception
							Logging.error(" Error : "+e.getMessage());
					 }
				} catch(Exception e) {
						    	Logging.error(" Error : "+e.getMessage());
						    	
				}
				finally {
						    	try {
						    		if(connection != null) {
						    			connection.close();
						    		}
						    	} catch(Exception ee) {
						    			
					    		}
				
			   }
			   this.ohlcParam= vec;
			
			   return ohlcParam;
		}
		
		public void setOhlcParam() {
			
		}
		
// from code for index corelation
		
		public Vector getCorelParam() {
	//		String index_id1=null,index_id2=null,index_id3=null;
			String fromdate="";
			String toDate="";
			Vector vect =new Vector();
			
			Connection connection = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
			//ArrayList temp = new ArrayList();
			String local_id = getSelectUser();
			
			String id1 = null;
	//		String exchange_id = null;
			int days_diff = 0;
					
				try{
					if(connection == null) {
					connection = con.getdbConnection();
					}
					try {
						
							String query = ConnectInit.queries.getProperty("select_*_from_preferencedetail12");
							//Logging.getDebug("Query   = "+query);
							pst = connection.prepareStatement(query);
							pst.setString(1,local_id);
							pst.setString(2,"Index Correlation");
							rs = pst.executeQuery();
					
							while(rs.next())
							{
								id1 = null;
								
								id1 = rs.getString("index_id1");
								days_diff = rs.getInt("days_difference");
								Logging.debug("days_diff" +days_diff);
								//Logging.getDebug("id1" +id1);
							
								
							}
							if(isCheckdate()== true)   // select date from calendar
							{
								toDate=getTod();
								fromdate=getFromd();
							}
							else                        // date generated from days difference from database
							{
							Date curr = new Date(); 
							setFrdate(days_diff);
							fromdate=getFrdate();
							setTodate(curr);
							toDate=getTodate();
							}
							setIndex_name1(id1);					
							String[] s1=getIndex_name1();
							vect.add(0,fromdate);
							vect.add(1,toDate);
							vect.add(2,s1);
														 
							
					 } catch (SQLException sqlexp) {
									Logging.error("Error : "+sqlexp.getMessage());
					 } catch (Exception e) {
							// TODO: handle exception
							Logging.error(" Error : "+e.getMessage());
					 }
				} catch(Exception e) {
						    	Logging.error(" Error : "+e.getMessage());
						    	
				}
				finally {
						    	try {
						    		if(connection != null) {
						    			connection.close();
						    		}
						    	} catch(Exception ee) {
						    			
					    		}
				
			   }			
			 this.corelParam= vect;
			
			 return corelParam;
		}
		
		public void setCorelParam() {
			
		}
		
//Upto code for index corelation
		
//	 code for index compare	
		public Vector getComParam() {
			//String index_id1=null,index_id2=null,index_id3=null;
			String fromdate="";
			String toDate="";
			Vector vect =new Vector();
			
			Connection connection = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
			//ArrayList temp = new ArrayList();
			String local_id = getSelectUser();
			
			String id1 = null;
		//	String exchange_id = null;
			int days_diff = 0;
					
				try{
					if(connection == null) {
					connection = con.getdbConnection();
					}
					try {
						
							String query = ConnectInit.queries.getProperty("select_*_from_preferencedetail12");
							//Logging.getDebug("Query   = "+query);
							pst = connection.prepareStatement(query);
							pst.setString(1,local_id);
							pst.setString(2,"Index Comparison");
							rs = pst.executeQuery();
					
							while(rs.next())
							{
								id1 = null;
								
								id1 = rs.getString("index_id1");
								days_diff = rs.getInt("days_difference");
								Logging.debug("days_diff" +days_diff);
								//Logging.getDebug("id1" +id1);
							
								
							}
							if(isCheckdate()== true)   // select date from calendar
							{
								toDate=getTod();
								fromdate=getFromd();
							}
							else                        // date generated from days difference from database
							{
							Date curr = new Date(); 
							setFrdate(days_diff);
							fromdate=getFrdate();
							setTodate(curr);
							toDate=getTodate();
							}
							setIndex_name1(id1);					
							String[] s1=getIndex_name1();
							vect.add(0,fromdate);
							vect.add(1,toDate);
							vect.add(2,s1[0]);
							vect.add(3,s1[1]);
							vect.add(4,s1[2]);
							vect.add(5,s1[3]);
							
							 
							
					 } catch (SQLException sqlexp) {
									Logging.error("Error : "+sqlexp.getMessage());
					 } catch (Exception e) {
							// TODO: handle exception
							Logging.error(" Error : "+e.getMessage());
					 }
				} catch(Exception e) {
						    	Logging.error(" Error : "+e.getMessage());
						    	
				} finally {
						    	try {
						    		if(connection != null) {
						    			connection.close();
						    		}
						    	} catch(Exception ee) {
						    			
					    		}
			   }
			   this.comParam= vect;
			
			   return comParam;
		}
		
		/**
		 * @return Returns the dataCount.
		 */
		public String getDataCount() {
			return dataCount;
		}
		/**
		 * @param dataCount The dataCount to set.
		 */
		public void setDataCount(String dataCount) {
			this.dataCount = dataCount;
		}
		/**
		 * @return Returns the resetButton.
		 */
		public String getResetButton() {
			return resetButton;
		}
		/**
		 * @param resetButton The resetButton to set.
		 */
		public void setResetButton(String resetButton) {
			this.resetButton = resetButton;
		}
		/**
		 * @return Returns the resetButHV.
		 */
		public String getResetButHV() {
			return resetButHV;
		}
		/**
		 * @param resetButHV The resetButHV to set.
		 */
		public void setResetButHV(String resetButHV) {
			this.resetButHV = resetButHV;
		}
		
		public String getCurrencyParam() {
		
							
			Connection connection = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
		
			String local_id = getSelectUser();
			String id1 = null;
		
					
				try{
					if(connection == null) {
					connection = con.getdbConnection();
					}
					try {
						
							String query = ConnectInit.queries.getProperty("select_*_from_preferencedetail12");
							//Logging.getDebug("Query   = "+query);
							pst = connection.prepareStatement(query);
							pst.setString(1,local_id);
							pst.setString(2,"Index In Different Currency");
							rs = pst.executeQuery();
					
							while(rs.next())
							{
								id1 = null;
								id1 = rs.getString("index_id1");
																
							}
													
														
					 } catch (SQLException sqlexp) {
									Logging.error("Error : "+sqlexp.getMessage());
					 } catch (Exception e) {
							// TODO: handle exception
							Logging.error(" Error : "+e.getMessage());
					 }
				} catch(Exception e) {
					
						    	Logging.error(" Error : "+e.getMessage());						    	
				} finally {
						    	try {
						    		if(connection != null) {
						    			connection.close();
						    		}
						    	} catch(Exception ee) {
						    			
					    		}
				
			   }
					
			   currencyParam= id1;
			
			   return currencyParam;
		}

		/**
		 * @return Returns the itype.
		 */
		public int getItype() {
			return itype;
		}

		/**
		 * @param itype The itype to set.
		 */
		public void setItype(int itype) {
			this.itype = itype;
		}
		
}
	
	