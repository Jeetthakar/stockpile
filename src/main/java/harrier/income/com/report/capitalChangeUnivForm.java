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
public class capitalChangeUnivForm extends ActionForm{
	Logger Logging = Logger.getLogger(capitalChangeUnivForm.class);
	private String from=null,go=null,clear=null,to=null,defaultVal=null,
		compute=null,selectExchange=null,stockName=null;
	private Collection selectExchgCollection=null;
	private Vector capitalChangeVec=new Vector();
	
	private Hashtable IndexNameHash=new Hashtable();
	
	private ArrayList tableData=null;
//	app.Connect con=new app.Connect();
	private ResultSet rst;
	
	 /**RESEST ALL FORM FIELDS**/
	  public void reset(ActionMapping mapping,HttpServletRequest request){
	  	selectExchgCollection=null;selectExchange=null;
	  	from=null;go=null;to=null;
	  	clear=null;defaultVal=null;
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
	 * @return Returns the go.
	 */
	public String getGo() {
		return go;
	}
	/**
	 * @param go The go to set.
	 */
	public void setGo(String go) {
		this.go = go;
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
		Connect con = ConnectInit.getConnect();
		//vec.add(new LabelValueBean("Not Selected","0"));
		PreparedStatement stmt = null;
		try {
			if(connection == null)
				connection = con.getdbConnection();
			stmt = connection.prepareStatement(ConnectInit.queries.getProperty("stock_exchange_online_list"));
							
			ResultSet rst = stmt.executeQuery();
			while(rst.next()){
				vec.add(new LabelValueBean(rst.getString(2),rst.getString(1)));
				IndexNameHash.put(rst.getString(1),rst.getString(2));
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
		String stkId=null,stkName=null, faceVal=null, tis=null, mCap=null, iwf=null,
		CAName=null, date=null;
		
		PreparedStatement stmt =null;
		Connection connection = null;
		
//		org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		Vector vec=new Vector();
		ArrayList tempData= new ArrayList();
		
		StockDetails stkDetails;
		
		
		/*if(Connect.con==null){
			con.getConnection();
		}*/
		//Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		Connect con = ConnectInit.getConnect();
		if(connection==null)
		{
			connection = con.getdbConnection();
		}
		
		rst = con.highlowResultmktStatus("capital_change_to_universe",selectExchange,from,to);
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
					String fcVal=(String) rst.getString(3);            		
		        	fcVal=ad.indexcompose(fcVal);
		        	faceVal= fcVal;
					
				}
				vec.add(i, faceVal);
				i++;
				
				if (rst.getString(4) == null) {						// TIS
					tis= "0.00";
				} else {
					tis=rst.getString(4).toString();
				}
				vec.add(i, tis);
				i++;
			
				if (rst.getString(5) == null) {						// mCap
					mCap= "0";
				} else {
					String mVal=(String) rst.getString(5);            		
		        	mVal=ad.indexcompose(mVal);
		        	mCap= mVal;
					
				}
				vec.add(i, mCap);
				i++;
				
				if (rst.getString(6) == null) {						// iwf
					iwf= "--";
				} else {
					iwf= rst.getString(6).toString();
				}
				vec.add(i, iwf);
				i++;
				
				if (rst.getString(8) == null) {						// CA name
					CAName= "--";
				} else {
					CAName= rst.getString(8).toString();
				}
				vec.add(i, CAName);
				i++;
				
				if (rst.getString(7) == null) {						// date
					date= "--";
				} else {
					date= rst.getString(7).toString();
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
			tableData = tempData;
			capitalChangeVec=vec;
			
			//Close the Dynamic connection : Manoj Adekar
			con.closeDynaCon();
			return tableData;
			
			
			
		} catch (SQLException sqlexp) {
			Logging.error("Error : "+sqlexp.getMessage());
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
		
		return tableData;
	}
	/**
	 * @param tableData The tableData to set.
	 */
	public void setTableData(ArrayList tableData) {
		this.tableData = tableData;
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
	/**
	 * @return Returns the stockName.
	 */
	public String getStockName() {
		Logging.debug(" inside getindExchName");
		try{
			String id=getSelectExchange();
			Enumeration e;
			String str;
			String iname="",ival="";
			e=IndexNameHash.keys();
			while(e.hasMoreElements()){
				str=(String)e.nextElement();
				iname=(String)IndexNameHash.get(str);
				if(str.equals(id)){
					Logging.debug(" found !!!!");
					stockName=iname;
					break;
				}
			}
			Logging.debug(" indExchName = "+ stockName);
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
	 * @return Returns the capitalChangeVec.
	 */
	public Vector getCapitalChangeVec() {
		return capitalChangeVec;
	}

	/**
	 * @param capitalChangeVec The capitalChangeVec to set.
	 */
	public void setCapitalChangeVec(Vector capitalChangeVec) {
		this.capitalChangeVec = capitalChangeVec;
	}
}	  