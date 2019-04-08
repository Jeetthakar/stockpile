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
import org.jfree.chart.demo.servlet.AdjustDecimal;

import app.Connect;

import com.harrier.initializeation.ConnectInit;

/**
 * @author abhijit b.
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class stockListForm extends ActionForm{
	Logger Logging = Logger.getLogger(stockListForm.class);
	private String clear=null,defaultVal=null,stockName=null,ExchName=null,
		compute=null,selectExchange=null,filter=null,excelFormat=null;
	//private char ='\0';
	private Collection selectExchgCollection=null;
	private Vector stklistVec=new Vector();
	private Vector stklistVec1=new Vector();
	private Vector stklistVec2=new Vector();
	private Hashtable ExchNameHash=new Hashtable();
	
	private ArrayList tableData=null;
	private ArrayList tableData1=null;
	private ArrayList tableData2=null;
//	app.Connect con=new app.Connect();
	Connect con = ConnectInit.getConnect();
	private Connection connect=null;
	private ResultSet rst;
	
	 /**RESEST ALL FORM FIELDS**/
	  public void reset(ActionMapping mapping,HttpServletRequest request){
	  	selectExchgCollection=null;selectExchange=null;
	  	clear=null;defaultVal=null;
	  	//filter=null;
	  }
	  
	  /**
	   * VALIDATE FORM DATA
	  * **/
	  public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request){
	  		Logging.debug(" Inside validate....");
	  		ActionErrors errors = new ActionErrors();
	  		return errors;
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
	 * @return Returns the selectExchange.
	 */
	public String getSelectExchange() {
		String stkName=getStockName();
		getExchName();
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
				ExchNameHash.put(rst.getString(1),rst.getString(2));
			}
				
			rst.close();
			stmt.close();
			selectExchgCollection = vec;
			//return selectExchgCollection;
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
		
		return selectExchgCollection;
	}
	/**
	 * @param selectExchgCollection The selectExchgCollection to set.
	 */
	public void setSelectExchgCollection(Collection selectExchgCollection) {
		this.selectExchgCollection = selectExchgCollection;
	}
	/**
	 * @return Returns the tableData.
	 */
	public ArrayList getTableData() {
		
		String stkId=null,stkName=null, faceVal=null, tis=null, mCap=null, 
			close = null, date=null;
		double mCapVal=0.0;
		
		PreparedStatement stmt =null;
		Connection connection = null;
		
//		org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
		org.jfree.chart.demo.servlet.FieldSort sort=new org.jfree.chart.demo.servlet.FieldSort();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();	
		Vector vec=new Vector();
		ArrayList tempData= new ArrayList();
		Vector tempVector=new Vector();
		
		StockDetails stkDetails;
		/*if(Connect.con==null){
		con.getConnection();
		}*/
		
		Connect con = ConnectInit.getConnect();
//		Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		if(connect==null)
		{
			connect = con.getdbConnection();
		}
		
		int i =0;
		rst = con.returnResult("get_stock_list_for_report",selectExchange);
		try {
			while (rst.next()) {
				if (rst.getString(1) == null) {							// stk ID
					stkId = "0";
				} else {
					stkId= rst.getString(1).toString();
				}
				vec.add(i,stkId);
				i++;
					
				if (rst.getString(2) == null) {							// stk Name
					stkName = "--";
				} else {
					stkName = rst.getString(2).toString();
				}
				vec.add(i,stkName);
				i++;
				
				if (rst.getString(3) == null) {							// tis
					tis="0";
				} else {
					tis = rst.getString(3).toString();
					tis= ad.indexcompose(tis);
				}
				vec.add(i,tis);
				i++;
				
				if (rst.getString(4) == null) {							// close
					close= "0";
				} else {
					close=rst.getString(4).toString();
					close=ad.indexcompose(close);
				}
				vec.add(i,close);
				i++;
				 
				if ((rst.getString(3) != null) && (rst.getString(4) != null)) {		// Calculate mCap
					mCapVal=((double)Double.parseDouble(rst.getString(3))*(double)Double.parseDouble(rst.getString(4)));
				}else{
					mCapVal=0.00;
				}	
				
				/*
				if (mCapVal == 0.00) {
					mCap = "0.00";
				} else {
					String mcvalue=Double.toString(mCapVal);
					mCap=ad.indexcompose(mcvalue);// changed by neha
				} */
				
				if(rst.getString(7)== null){
					mCap="0.00";
					
				}
				else{
					mCap=rst.getString(7);
					mCap=ad.indexcompose(mCap);
				}
				vec.add(i,mCap);
				i++;
				
				if (rst.getString(5) == null) {						// face Val
					faceVal= "0";
				} else {
					faceVal= rst.getString(5).toString();
					faceVal=ad.indexcompose(faceVal);
				}
				vec.add(i,faceVal);
				i++;
				
				if (rst.getString(6) == null) {						// date
					date= "0";
				} else {
					date= rst.getString(6).toString();
				}
				vec.add(i,date);
				i++;
				
				if(filter == null){
					stkDetails = new StockDetails(stkId, stkName,tis,close, mCap, faceVal,   date);
					tempData.add(stkDetails);
					
				}else {
					String fletter=filter.substring(0,1);
					String wordAlpha = stkName.substring(0,1);
					if(fletter.equals(wordAlpha))
					{
						stkDetails = new StockDetails(stkId, stkName,tis,close, mCap, faceVal,   date);
						tempData.add(stkDetails);
					}
				}
			}
			Logging.debug("No of cols = "+ tempData.size());
			rst.close();
			tableData = tempData;
			stklistVec=vec;
			
			//Closing Dynamic Connection : Manoj Adekar
			con.closeDynaCon();
			
			return tableData;
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :"+sqlexp.getMessage());
		} finally{
		try{
			if(connect!=null)
				connect.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}	
		return tableData;
	}
	/**
	 * @param tableData The tableData to set.
	 */
	public void setTableData(ArrayList tableData) {
		this.tableData = tableData;
	}
	
	public ArrayList getTableData1() {
		
		String stkId=null,stkName=null, faceVal=null, tis=null, mCap=null, 
			close = null, date=null;
		double mCapVal=0.0;
		
		PreparedStatement stmt =null;
		Connection connection = null;
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();	
//		org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
		org.jfree.chart.demo.servlet.FieldSort sort=new org.jfree.chart.demo.servlet.FieldSort();
		
		Vector vec=new Vector();
		ArrayList tempData= new ArrayList();
		Vector tempVector=new Vector();
		
		StockDetails stkDetails;
//		Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		Connect con = ConnectInit.getConnect();
		if(connect==null){
			connect= con.getdbConnection();
		}
		
		int i =0;
		rst = con.returnResult("get_stock_list_for_report",selectExchange);
		try {
			while (rst.next()) {
				if (rst.getString(1) == null) {							// stk ID
					stkId = "0";
				} else {
					stkId= rst.getString(1).toString();
				}
				vec.add(i,stkId);
				i++;
					
				if (rst.getString(2) == null) {							// stk Name
					stkName = "--";
				} else {
					stkName = rst.getString(2).toString();
				}
				vec.add(i,stkName);
				i++;
				
				if (rst.getString(3) == null) {							// tis
					tis="0";
				} else {
					tis = rst.getString(3).toString();
					tis= ad.indexcompose(tis);
				}
				vec.add(i,tis);
				i++;
				if(filter == null){
					stkDetails = new StockDetails(stkId, stkName,tis,close, mCap, faceVal,   date);
					tempData.add(stkDetails);
					
				}else {
					String fletter=filter.substring(0,1);
					String wordAlpha = stkName.substring(0,1);
					if(fletter.equals(wordAlpha))
					{
						stkDetails = new StockDetails(stkId, stkName,tis,close, mCap, faceVal,   date);
						tempData.add(stkDetails);
					}
				}
			}
			Logging.debug("No of cols = "+ tempData.size());
			rst.close();
			tableData1 = tempData;
			stklistVec1=vec;

			//Closing Dynamic Connection : Manoj Adekar
			con.closeDynaCon();
			
			return tableData1;
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :"+sqlexp.getMessage());
		} finally{
		try{
			if(connection!=null)
				connection.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}	
		return tableData1;
	}
	/**
	 * @param tableData The tableData to set.
	 */
	public void setTableData1(ArrayList tableData1) {
		this.tableData1 = tableData1;
	}
	
	
	public ArrayList getTableData2() {
		
		String stkId=null,stkName=null, faceVal=null, tis=null, mCap=null, 
			close = null, date=null;
		double mCapVal=0.0;
		
		PreparedStatement stmt =null;
		Connection connection = null;
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();	
//		org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
		org.jfree.chart.demo.servlet.FieldSort sort=new org.jfree.chart.demo.servlet.FieldSort();
		
		Vector vec=new Vector();
		ArrayList tempData= new ArrayList();
		Vector tempVector=new Vector();
		
		StockDetails stkDetails;
		/*if(Connect.con==null){
			con.getConnection();
		}*/
		Connect c = ConnectInit.getConnect();
//		Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		if(connect==null)
		{
			connect = con.getdbConnection();
		}
		int i =0;
		rst = con.returnResult("get_stock_list_for_report",selectExchange);
		try {
			while (rst.next()) {
				if (rst.getString(4) == null) {							// close
					close= "0";
				} else {
					close=rst.getString(4).toString();
					close=ad.indexcompose(close);
				}
				vec.add(i,close);
				i++;
				
				if ((rst.getString(3) != null) && (rst.getString(4) != null)) {		// Calculate mCap
					mCapVal=((double)Double.parseDouble(rst.getString(3))*(double)Double.parseDouble(rst.getString(4)));
				}else{
					mCapVal=0.00;
				}/*	 commented by neha 15thfeb,2007
				if (mCapVal == 0.00) {
					mCap = "0.00";
				} else {
					mCap=ad.indexcompose(mCap);
				}*/
				if(rst.getString(7)== null){
					mCap="0.00";
					
				}
				else{
					mCap=rst.getString(7);
					mCap=ad.indexcompose(mCap);
				}
				vec.add(i,mCap);
				i++;
				
				if (rst.getString(5) == null) {						// face Val
					faceVal= "0";
				} else {
					faceVal= rst.getString(5).toString();
					faceVal=ad.indexcompose(faceVal);
				}
				vec.add(i,faceVal);
				i++;
				
				if (rst.getString(6) == null) {						// date
					date= "0";
				} else {
					date= rst.getString(6).toString();
				}
				vec.add(i,date);
				i++;
				
				if(filter == null){
					stkDetails = new StockDetails(stkId, stkName,tis,close, mCap, faceVal,   date);
					tempData.add(stkDetails);
					
				}else {
					String fletter=filter.substring(0,1);
					String wordAlpha = stkName.substring(0,1);
					if(fletter.equals(wordAlpha))
					{
						stkDetails = new StockDetails(stkId, stkName,tis,close, mCap, faceVal,   date);
						tempData.add(stkDetails);
					}
				}
			}
			Logging.debug("No of cols = "+ tempData.size());
			rst.close();
			tableData2 = tempData;
			stklistVec2=vec;
//			Closing Dynamic Connection : Manoj Adekar
			con.closeDynaCon();
			
			return tableData2;
		} catch (SQLException sqlexp) {
			Logging.error("SQL Error :"+sqlexp.getMessage());
		} finally{
		try{
			if(connect!=null)
				connect.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}	
		return tableData2;
	}
	/**
	 * @param tableData The tableData to set.
	 */
	public void setTableData2(ArrayList tableData2) {
		this.tableData2 = tableData2;
	}	
			
	/**
	 * @return Returns the letter.
	 */
	public String getFilter() {
		return filter;
	} 
	/**
	 * @param letter The letter to set.
	 */
	public void setFilter(String filter) {
		this.filter = filter;
	}
	/**
	 * @return Returns the stockName.
	 */
	public String getStockName() {
		Connection connection = null;
		try{
			if(connection ==null)
				connection = con.getdbConnection();
			
			rst = con.returnResult("stock_name_for_id",selectExchange);
			
			if (rst.next()) {							// stock Name
				stockName = rst.getString(1);
			} else {
				stockName= "--";
			}
			Logging.debug(" Stock Name "+ stockName);
			
		} catch (Exception e) {
			Logging.error("Error : "+e.getMessage());
			//e.printStackTrace();
		}	finally{
			try{
				if(connection != null)
					connection.close();
			}catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
		Logging.debug(" StockName = "+ stockName);
		return stockName;
	}
	/**
	 * @param stockName The stockName to set.
	 */
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	/**
	 * @return Returns the excelFormat.
	 */
	public String getExcelFormat() {
		return excelFormat;
	}
	/**
	 * @param excelFormat The excelFormat to set.
	 */
	public void setExcelFormat(String excelFormat) {
		this.excelFormat = excelFormat;
	}

	/**
	 * @return Returns the stklistVec.
	 */
	public Vector getStklistVec() {
		return stklistVec;
	}

	/**
	 * @param stklistVec The stklistVec to set.
	 */
	public void setStklistVec(Vector stklistVec) {
		this.stklistVec = stklistVec;
	}

	public Vector getStklistVec1() {
		return stklistVec1;
	}

	/**
	 * @param stklistVec The stklistVec to set.
	 */
	public void setStklistVec1(Vector stklistVec1) {
		this.stklistVec1 = stklistVec1;
	}
	
	public Vector getStklistVec2() {
		return stklistVec2;
	}

	/**
	 * @param stklistVec The stklistVec to set.
	 */
	public void setStklistVec2(Vector stklistVec2) {
		this.stklistVec2 = stklistVec2;
	}
	
	/**
	 * @return Returns the exchName.
	 */
	public String getExchName() {
		Logging.debug(" inside getExchName");
		try{
			String id=selectExchange;
			Enumeration e;
			String str;
			String iname="";
			e=ExchNameHash.keys();
			while(e.hasMoreElements()){
				str=(String)e.nextElement();
				iname=(String)ExchNameHash.get(str);
				if(str.equals(id)){
					Logging.debug(" found !!!!");
					ExchName=iname;
					break;
				}
			}
			Logging.debug(" ExchName = "+ ExchName);
		}catch(Exception e){
			Logging.debug(" Error "+ e.toString());
		}
		return ExchName;
	}

	/**
	 * @param exchName The exchName to set.
	 */
	public void setExchName(String exchName) {
		ExchName = exchName;
	}
}	  
		
	  