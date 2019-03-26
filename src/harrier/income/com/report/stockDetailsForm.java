package harrier.income.com.report;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

import app.AcessControl;
import app.Connect;

import com.harrier.initializeation.ConnectInit;

/**
 * @author naresh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class stockDetailsForm extends ActionForm{
	Logger Logging = Logger.getLogger(stockDetailsForm.class);
	private String from=null,go=null,clear=null,to=null,defaultVal=null,checkShwIndices=null,checkChart=null,
		selectIndex=null,selectCriteria=null,selectStock=null,text=null,compute=null,selectExchange=null,
		stockName=null, indexName=null,b1=null,userid1;
	
	private Hashtable IndexNameHash=new Hashtable();
	private Hashtable StockNameHash=new Hashtable();
	
	private Collection selectIndexCollection=null,selectCriteriaCollection=null,stockCollection=null,selectExchgCollection=null;
	private Vector vecStockDetails=new Vector();
	private ArrayList tableData=null;
	String tval,tvol,roleId_stk;
//	stockDetails sdf;
	private ResultSet rst;
	PreparedStatement pst;
	ResultSet rs,rs1;
	StockDetails stkDetails;
	IndexComposeReportMethod Icr = new IndexComposeReportMethod();
	//StockDetails stockDet = new StockDetails();
//	app.Connect con=new app.Connect();
	Connect con = ConnectInit.getConnect();
public 	stockDetailsForm() {
	selectIndexCollection=null;stockCollection=null;
  	from=null;go=null;to=null;
  	clear=null;defaultVal=null;
  	checkShwIndices=null;
  	checkChart=null;
}
	 /**RESEST ALL FORM FIELDS**/
	  public void reset(ActionMapping mapping,HttpServletRequest request){
	  	selectIndexCollection=null;stockCollection=null;
	  	from=null;go=null;to=null;
	  	clear=null;defaultVal=null;
	  	checkShwIndices=null;
	  	checkChart=null;
	  }
	  
	  /**
	   * VALIDATE FORM DATA
	  * **/
	  public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request)
	  {
	  	ActionErrors errors = new ActionErrors();
	  	return errors;
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
			//vec.add(new LabelValueBean("Not Selected","0"));
			PreparedStatement stmt = null;
			try {
				if(connection == null)
					connection = con.getdbConnection();
				stmt = connection.prepareStatement(ConnectInit.queries.getProperty("stock_exchange_online_list"));
								
				ResultSet rst = stmt.executeQuery();
				while(rst.next()){
					vec.add(new LabelValueBean(rst.getString(2),rst.getString(1)));
				}
				
				rst.close();
				stmt.close();
				selectExchgCollection = vec;
				//return selectExchgCollection;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
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
			
			return selectExchgCollection;
		}
		/**
		 * @param selectExchgCollection The selectExchgCollection to set.
		 */
		public void setSelectExchgCollection(Collection selectExchgCollection) {
			this.selectExchgCollection = selectExchgCollection;
		}
	
	  
	  /**
	   * @return Returns the selectCollection.
	   */
	public Collection getSelectIndexCollection() {
			Vector vec = new Vector();
			String query;
			Connection connection = null;
		//	vec.add(new LabelValueBean("Not Selected","0"));
			PreparedStatement stmt = null;
			try {
				if(connection == null)
					connection = con.getdbConnection();
				
				if(checkShwIndices != null && checkShwIndices.equals("on")){
					query = ConnectInit.queries.getProperty("index_list");
				}else{
					query = ConnectInit.queries.getProperty("index_list_without_child");
				}
				//Vector vector_indexlist = new Vector();
				
				try {
					
					pst = connection.prepareStatement(query);
					pst.setString(1,userid1);
					rs = pst.executeQuery();
			//		AcessControl asc=new AcessControl();
					AcessControl asc = ConnectInit.getAcessControl();
					String NotSelected=asc.getLangValues("Masters.NotSelected");
					Logging.debug(" Inside getIndexcollection(): Not Selected ="+NotSelected);
					
					vec.add(new LabelValueBean("Not Selected","0"));
					while (rs.next()) {
						vec.add(new LabelValueBean(rs.getString(2),rs.getString(1)));
					}
//					Change by Manoj adekar
					int role_id2=Integer.parseInt(roleId_stk);
					if(role_id2!=1)
					{

						ResultSet rbs = Icr.benchindecolection(connection,"index_list_without_child_bench");
						while (rbs.next()) {
							vec.add(new LabelValueBean(rbs.getString(2),rbs.getString(1)));
						}
					}
				selectIndexCollection = vec;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				Logging.debug(e);
			}	
			}catch(Exception e){
		    	Logging.error(" Error : "+e.getMessage());
		     }
			finally{
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
			
			return selectIndexCollection;
		}
		/**
		 * @param selectCollection The selectCollection to set.
		 */
		public void setSelectIndexCollection(Collection selectIndexCollection) {
			this.selectIndexCollection = selectIndexCollection;
		}
		/**
		 * @return text Returns the String Text
		 */
		public String getText(){
			return text;
		}
	  /**
	   * 
	   * @param text The text to set
	   */
	 public void setText(String text) {
	 	this.text=text;
	 }
		
	  
	/**
	 * @return Returns the clear.
	 */
	public String getClear() {
		return clear;
	}
	/**
	 * @param clear The clear to set.
	 */
	public void setClear(String clear) {
		this.clear = clear;
	}
	
	/**
	 * @return Returns the from.
	 */
	public String getFrom() {
		return from;
	}
	/**
	 * @param from The fromDate to set.
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	
	/**
	 * @return checkShwIndices Returns the checkShwIndices
	 */
	public String getCheckShwIndices() {
		//Logging.getDebug(" Inside getCheck : check = "+ check);
		return checkShwIndices;
	}
	/**
	 * 
	 * @param checkShwIndices The checkShwIndices to set
	 */
	public void setCheckShwIndices(String checkShwIndices){
		this.checkShwIndices=checkShwIndices;
	}
	
	/**
	 * 
	 * @return checkChart Returns the checkChart 
	 */
	public String getCheckChart() {
		return checkChart;
	}
	/**
	 * 
	 * @param checkChart The checkChart to set
	 */
	public void setCheckChart(String checkChart){
		this.checkChart = checkChart;
		ArrayList tempArrayList = new ArrayList();
		tempArrayList = getTableData();
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
	 * Database Connectivity
	 * */
/*	public void dbconnect(){
		
		try {if(app.Connect.con==null){
			con.getConnection();
			}
		} catch (Exception e) {	System.out.println(e);} 
		
	}*/
	public void reset_stkevent(){
		from=null;go=null;
	  	clear=null;to=null;
	  	
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
	 * @return Returns the select.
	 */
	public String getSelectIndex() {
		/*if(defaultVal!=null && defaultVal.equals("yes")){
			Connection connection=null;
			try{
				if(connection==null)
					connection=con.getdbConnection();
				PreparedStatement stmt = connection.prepareStatement(con.queries.getProperty("select_system_config"));
				ResultSet rst = stmt.executeQuery();
				while(rst.next()){
					 selectIndex=rst.getString(25);
				}
				rst.close();
				stmt.close();
								
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try{
					if(connection!=null)
						connection.close();
				}catch(Exception ee){
					try{
						if(connection!=null)
							connection.close();
					}catch(Exception ex){
						Logging.getError(" Error : Unable to close Connection "+ex.getMessage());
					}
					Logging.getError(" Error : Unable to close Connection "+ee.getMessage());
				}
			}
		}*/
		return selectIndex;
	}
	/**
	 * @param select The select to set.
	 */
	public void setSelectIndex(String selectIndex) {
		//Logging.getDebug("Inside setSelectIndex selectIndex = "+ selectIndex);
		this.selectIndex = selectIndex;
	}
	public String getSelectCriteria() {
		return selectCriteria;
	}
	
	public void setSelectCriteria(String selectCriteria) {
		//Logging.getDebug("Inside setSelectIndex selectIndex = "+ selectIndex);
		this.selectCriteria = selectCriteria;
	}
	public Collection getSelectCriteriaCollection() {
		
		Vector vec = new Vector();
		//dbconnect();
		try
		{
		//vec.add(1,"Traded Volume");
		vec.add(new LabelValueBean("Traded Volume","1"));
		//vec.add(2,"Traded Value");
		vec.add(new LabelValueBean("Traded Value","2"));
		//vec.add(3,"Market Price Hike %");
		vec.add(new LabelValueBean("Market Price Hike %","3"));
		}
	 catch (Exception e) {
		// TODO Auto-generated catch block
	//	e.printStackTrace();
		 Logging.debug(e);
	}
		Logging.debug("VECTOR ISSSSSSSSS"+vec);
	 	selectCriteriaCollection = vec;
		return selectCriteriaCollection;
		
	}
	/**
	 * @param selectCollection The selectCollection to set.
	 */
	public void setSelectCriteriaCollection(Collection selectCriteriaCollection) {
		this.selectCriteriaCollection = selectCriteriaCollection;
	}
	/**
	 * @return Returns the stockCollection.
	 */
	public Collection getStockCollection() {
		//dbconnect();
		Vector vec=new Vector();
		Connection connection=null;
			try {
				if(connection == null)
					connection = con.getdbConnection();
				PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("stockDetailFromDate_stock_list_index"));
				stmt.setString(1,selectIndex);
				ResultSet rst = stmt.executeQuery();
				while(rst.next()){
					vec.add(new LabelValueBean(rst.getString(1),rst.getString(2)));
					StockNameHash.put(rst.getString(2),rst.getString(1));
				}
				rst.close();
				stmt.close();
				stockCollection = vec;
			//	return stockCollection;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				Logging.debug(e);
			}finally{
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
		stockCollection=vec;
		return stockCollection;
	}
	/**
	 * @param stockCollection The stockCollection to set.
	 */
	public void setStockCollection(Collection stockCollection) {
		//Logging.getDebug(" Inside set Stock Collection...");
		this.stockCollection = stockCollection;
	}
	
	
	
	/**
	 * @return Returns the tableData.
	 */
	public ArrayList getTableData() {
		String stkName=null, openVal=null, closeVal=null, lowVal=null, highVal=null,
		trdVal=null, trdVol=null, noTrades = null, mcv =null, pDate=null;
		//Connect con1=ConnectInit.getConnect();
//		app.Connect con=new app.Connect();
		PreparedStatement stmt =null;
		Connection connection = null;
		//StockDetails[] sd1 ;
		
		Vector vec=new Vector();
		ArrayList tempData= new ArrayList();
		StockDetails stkDetails;
		
		//Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		if(con==null)
			{
				connection = con.getdbConnection();
			}
		
		rst = con.highlowResult("stock_price_daily_between_date", selectStock,from,to);
		int i =0;
		try {
		//	String query=con.queries.getProperty("total_return_index_old_data");
			//Logging.getDebug(" query = " + query);
			//stmt = Connect.con.prepareStatement(query);
			//stmt.setString(1,selectStock);
			//stmt.setString(2,from);
			//stmt.setString(3,to);
			//rst = stmt.executeQuery();
			
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
					//System.out.println("*************************************** MCap = "+  rst.getDouble(9));
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
				/*sd1= new StockDetails(stkName, openVal, closeVal, lowVal, highVal, 
						trdVal, pDate, mcv, trdVol, noTrades );
				*/
				tempData.add(stkDetails);			
			}	
			rst.close();	
			
			//Closing Dynamic Connection : Manoj Adekar
			con.closeDynaCon();
			
		}catch (SQLException sqlexp) {
			Logging.error("SQL Error : "+sqlexp.getMessage());
		}catch (Exception exp) {
			Logging.error(" Error : "+exp.getMessage());
		}
		Logging.debug(" tempData = "+ tempData.size());
		tableData = tempData;
		
		vecStockDetails = vec;
		Logging.debug(" vector = " + vecStockDetails.toString());
		
		//setVector_highlowtable(vector_highlowtable);
		return tableData;
	}
	
	/**
	 * @param tableDate The tableDate to set.
	 */
	public void setTableData(ArrayList tableData) {
		this.tableData = tableData;
	}
	public ArrayList getTableData1() {
		String stockName1=null,camName=null,corpActionType=null,appliedDate=null,status=null;
		//Connect con1=ConnectInit.getConnect();
//		app.Connect con=new app.Connect();
		PreparedStatement stmt =null;
		Connection connection = null;
		//StockDetails[] sd1 ;
		
		Vector vec=new Vector();
		ArrayList tempData1= new ArrayList();
		StockDetails stkDetails;
		
		
		
		if(connection==null)
			connection=con.getdbConnection();
		rst = con.corporateDetails("get_corpotate_details",selectIndex,from,to);
		int i =0;
		try {
		//	String query=con.queries.getProperty("total_return_index_old_data");
			//Logging.getDebug(" query = " + query);
			//stmt = Connect.con.prepareStatement(query);
			//stmt.setString(1,selectStock);
			//stmt.setString(2,from);
			//stmt.setString(3,to);
			//rst = stmt.executeQuery();
			
			while (rst.next()) {
				
				if (rst.getString(1) == null) {						// stock name
					stockName1 = "--";
				} else {
					stockName1= rst.getString(1).toString();
				}
				vec.add(i, stockName1);
				i++;
				
				if (rst.getString(2) == null) {						// cam Name
					camName ="--";
				} else {
					camName = rst.getString(2).toString();
				}
				vec.add(i,camName);
				i++;
				
				if (rst.getString(3) == null) {						// corporateaction type
					corpActionType = "--";
				} else {
					corpActionType=rst.getString(3).toString();
				}
				vec.add(i,corpActionType);
				i++;
				
				if (rst.getString(4) == null) {						// applied Date
					appliedDate ="0";
				} else {
					appliedDate = rst.getString(4).toString();					
				}
				vec.add(i,appliedDate);
				i++;
				
				if (rst.getString(5) == null) {						// status
					status = "0";					
				} else {
					status = rst.getString(5).toString();
				}
				vec.add(i,status);
				i++;
				
				
					stkDetails = new StockDetails(stockName1,camName,corpActionType,appliedDate,status );
				/*sd1= new StockDetails(stkName, openVal, closeVal, lowVal, highVal, 
						trdVal, pDate, mcv, trdVol, noTrades );
				*/
				tempData1.add(stkDetails);			
			}	
			rst.close();	
			
			//Closing Dynamic Connection : Manoj Adekar
			con.closeDynaCon();
			
		}catch (SQLException sqlexp) {
			Logging.error("SQL Error : "+sqlexp.getMessage());
		}catch (Exception exp) {
			Logging.error(" Error : "+exp.getMessage());
		}
		Logging.debug(" tempData = "+ tempData1.size());
		tableData = tempData1;
		
		vecStockDetails = vec;
		Logging.debug(" vector = " + vecStockDetails.toString());
		
		//setVector_highlowtable(vector_highlowtable);
		return tableData;
	}
	
	/**
	 * @param tableDate The tableDate to set.
	 */
	public void setTableData1(ArrayList tableData) {
		this.tableData = tableData;
	}
	/*public ArrayList getTableData2() {
		String stockName2=null,rank=null,tradedVolume=null;
		//Connect con1=ConnectInit.getConnect();
		app.Connect con=new app.Connect();
		PreparedStatement stmt =null;
		Connection connection = null;
		//StockDetails[] sd1 ;
		
		Vector vec=new Vector();
		ArrayList tempData1= new ArrayList();
		StockDetails stkDetails;
		
		if(Connect.con==null)
		{
			con.getConnection();
		}
		rst = con.Top10Details("get_tradedVolume_details",selectIndex,from,to);
		int i =0;
		int k=0;
		int h=0;
		String[] str={"1","2","3","4","5","6","7","8","9","10"};
		String[] stored=new String[10];
		try {
		//	String query=con.queries.getProperty("total_return_index_old_data");
			//Logging.getDebug(" query = " + query);
			//stmt = Connect.con.prepareStatement(query);
			//stmt.setString(1,selectStock);
			//stmt.setString(2,from);
			//stmt.setString(3,to);
			//rst = stmt.executeQuery();
			
			while (rst.next()&&h<10) {
				for(int g=0;g<10;g++)
					if (rst.getString(1) != null) {
					String pqr=rst.getString(1);
					if(pqr==stored[g])
						break;
					}
					else{
						
						stored[h]=rst.getString(1);
						h++;
				}
				vec.add(i,str[k]);
				i++;
				k++;
				if (rst.getString(2) == null) {						// stock name
					stockName2 = "--";
				} else {
					stockName2= rst.getString(2).toString();
				}
				vec.add(i, stockName2);
				i++;
				
				if (rst.getString(3) == null) {						// cam Name
					tradedVolume ="--";
				} else {
					tradedVolume = rst.getString(3).toString();
				}
				vec.add(i,tradedVolume);
				i++;
				
				
				
				
					stkDetails = new StockDetails(stockName2,tradedVolume);
				sd1= new StockDetails(stkName, openVal, closeVal, lowVal, highVal, 
						trdVal, pDate, mcv, trdVol, noTrades );
				
				tempData1.add(stkDetails);			
			}	
			rst.close();	
			
		}catch (SQLException sqlexp) {
			app.Logging.getError("SQL Error : "+sqlexp.getMessage());
		}catch (Exception exp) {
			app.Logging.getError(" Error : "+exp.getMessage());
		}
		Logging.getDebug(" tempData = "+ tempData1.size());
		tableData = tempData1;
		
		vecStockDetails = vec;
		Logging.getDebug(" vector = " + vecStockDetails.toString());
		
		//setVector_highlowtable(vector_highlowtable);
		return tableData;
	}
	
	*//**
	 * @param tableDate The tableDate to set.
	 *//*
	public void setTableData2(ArrayList tableData) {
		this.tableData = tableData;
	}
	*/
	/**
	 * @return Returns the indexName.
	 */
	public String getIndexName() {
		Logging.debug(" inside getIndexName");
		try{
			String id=getSelectIndex();
			Enumeration e;
			String str;
			String iname="",ival="";
			e=IndexNameHash.keys();
			while(e.hasMoreElements()){
				str=(String)e.nextElement();
				iname=(String)IndexNameHash.get(str);
				if(str.equals(id)){
					Logging.debug(" found !!!!");
					indexName=iname;
					break;
				}
			}
			Logging.debug(" indExchName = "+ indexName);
		}catch(Exception e){
			Logging.debug(" Error "+ e.toString());
		}
		return indexName;
	}
	/**
	 * @param indexName The indexName to set.
	 */
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	/**
	 * @return Returns the stockName.
	 */
	public String getStockName() {
		Logging.debug(" inside getStockName");
		try{
			String id=getSelectStock();
			Enumeration e;
			String str;
			String iname="",ival="";
			e=StockNameHash.keys();
			while(e.hasMoreElements()){
				str=(String)e.nextElement();
				iname=(String)StockNameHash.get(str);
				if(str.equals(id)){
					Logging.debug(" found !!!!");
					stockName=iname;
					break;
				}
			}
			Logging.debug(" Stock Name = "+ stockName);
		}catch(Exception e){
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
	 * @return Returns the vector_highlowtable.
	 */
	public Vector getVecStockDetails() {
		Logging.debug(" Inside getvecStockDetails");
		/*String stkName=null, openVal=null, closeVal=null, lowVal=null, highVal=null,
		trdVal=null, trdVol=null, noTrades = null, mcv =null, pDate=null;
		
		java.util.Iterator i3=vector_highlowtable.iterator();
		while(i3.hasNext())
        {   
           stkName= i3.next().toString();
           openVal= i3.next().toString();
		   closeVal= i3.next().toString();
		   lowVal= i3.next().toString();
		   highVal= i3.next().toString();
		   trdVal= i3.next().toString();
		   trdVol= i3.next().toString();
		   noTrades= i3.next().toString();
		   mcv= i3.next().toString();
		   pDate= i3.next().toString();
		   
		   app.Logging.getDebug("Vector : stkName="+stkName+" openVal="+openVal+" closeVal="+closeVal);
		   app.Logging.getDebug("  lowVal="+lowVal+" highVal="+highVal+" trdVal="+trdVal+" trdVol="+trdVol);
		   app.Logging.getDebug("  noTrades="+noTrades+" mcv="+mcv+" pDate="+pDate);
		   
        }*/
		return vecStockDetails;
	}
	/**
	 * @param vector_highlowtable The vector_highlowtable to set.
	 */
	public void setVecStockDetails(Vector vecStockDetails) {
		Logging.debug(" inside setvecStockDetails");
		this.vecStockDetails = vecStockDetails;
		
	}
	/**
	 * @return Returns the b1.
	 */
	public String getB1() {
		return b1;
	}
	/**
	 * @param b1 The b1 to set.
	 */
	public void setB1(String b2) {
		this.b1 = b2;
	}
	public String getUserid1() {
		return userid1;
	}
	public void setUserid1(String userid1) {
		this.userid1 = userid1;
	}
	public String getRoleId_stk() {
		return roleId_stk;
	}
	public void setRoleId_stk(String roleId_stk) {
		this.roleId_stk = roleId_stk;
	}
}
