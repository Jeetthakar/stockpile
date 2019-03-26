

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

import app.Connect;

import com.harrier.initializeation.ConnectInit;

/**
 * @author naresh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MarketGainerLoosersForm extends ActionForm{
	Logger Logging = Logger.getLogger(MarketGainerLoosersForm.class);	
	private String from=null,go=null,clear=null,to=null,defaultVal=null,checkShwIndices=null,checkChart=null,
		selectIndex=null,selectCriteria=null,text=null,compute=null,selectExchange=null,
		stockName=null, indexName=null;
	
	private Hashtable IndexNameHash=new Hashtable();
	private Hashtable StockNameHash=new Hashtable();
	
	private Collection selectIndexCollection=null,selectCriteriaCollection=null,stockCollection=null,selectExchgCollection=null;
	private Vector vecTop10Details=new Vector();
	private ArrayList tableData=null;
	
	String tval,tvol;
//	stockDetails sdf;
	private ResultSet rst;
	Connect con = ConnectInit.getConnect();
	StockDetails stkDetails;
	//StockDetails stockDet = new StockDetails();
//	app.Connect con=new app.Connect();
public 	MarketGainerLoosersForm() {
	selectIndexCollection=null;stockCollection=null;
  	from=null;go=null;to=null;
  	clear=null;defaultVal=null;
  	checkShwIndices=null;
  	checkChart=null;
}
	 /**RESEST ALL FORM FIELDS**/
	  public void reset(ActionMapping mapping,HttpServletRequest request){
	  	selectIndexCollection=null;
	  	stockCollection=null;
	  	from=null;
	  	go=null;
	  	to=null;
	  	clear=null;
	  	defaultVal=null;
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
			//dbconnect();
			Connection connection = null;
			vec.add(new LabelValueBean("Not Selected","0"));
			PreparedStatement stmt = null;
			try {
				if(connection == null)
					connection = con.getdbConnection();
				if (checkShwIndices != null && checkShwIndices.equals("on")) {
					stmt = connection.prepareStatement(ConnectInit.queries.getProperty("index_list"));
					//query = c.queries.getProperty("index_list");
				} else {
					stmt = connection.prepareStatement(ConnectInit.queries.getProperty("index_list_without_child"));
					//query = c.queries.getProperty("index_list_without_child");
				}
				
				ResultSet rst = stmt.executeQuery();
				while(rst.next()){
					vec.add(new LabelValueBean(rst.getString(2),rst.getString(1)));
					IndexNameHash.put(rst.getString(1),rst.getString(2));
				}
				
				rst.close();
				stmt.close();
				selectIndexCollection = vec;
				return selectIndexCollection;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
				Logging.debug(e);
			}	finally{
				try{
					if(connection != null)
						connection.close();
				}catch (Exception ee) {
					
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
	 * @return
	 */
	public ArrayList getTableData() {
		String stockName=null,rank=null,tradedVol=null;
		//Connect con1=ConnectInit.getConnect();
//		app.Connect con=new app.Connect();
		Connect con = ConnectInit.getConnect();
		PreparedStatement stmt =null;
		Connection connection = null;
		//StockDetails[] sd1 ;
		
		Vector vec=new Vector();
		ArrayList tempData= new ArrayList();
		StockDetails stkDetails;
		
		try {
			if(connection == null)
				connection = con.getdbConnection();
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
			
			edge: while (rst.next()&&h<10) {
				
					if (rst.getString(1) != null) {
						String pqr=rst.getString(1).trim();
						
					for(int g=0;g<10;g++)
					{
						if(pqr.equals(stored[g]))
								
							continue edge;
						else
						{
							stored[h]=rst.getString(1);
							h++;
							break;
						}
					}
				}
				rank=str[k];
				vec.add(i,str[k]);
				i++;
				k++;
				if (rst.getString(2) == null) {						// stock name
					stockName = "--";
				} else {
					stockName= rst.getString(2).toString();
				}
				vec.add(i, stockName);
				i++;
				
				if (rst.getString(3) == null) {						// cam Name
					tradedVol ="--";
				} else {
					tradedVol = rst.getString(3).toString();
				}
				vec.add(i,tradedVol);
				i++;
				
				
				
				
					stkDetails = new StockDetails(rank,stockName,tradedVol);
				/*sd1= new StockDetails(stkName, openVal, closeVal, lowVal, highVal, 
						trdVal, pDate, mcv, trdVol, noTrades );
				*/
				tempData.add(stkDetails);			
			}	
			rst.close();
			Logging.debug(" tempData = "+ tempData.size());
			tableData = tempData;
			vecTop10Details = vec;
			Logging.debug(" vector = " + vecTop10Details.toString());
			return tableData;
		
		}catch (Exception exp) {
			Logging.error(" Error : "+exp.getMessage());
		}
	}finally{
		try{
			if(connection!=null)
				connection.close();
		}catch(Exception ee){
			
			Logging.error(" Error : Unable to close Connection "+ee.getMessage());
		}
	}
		
		return tableData;
	}
	
	/**
	 * @param tableDate The tableDate to set.
	 */
	public void setTableData(ArrayList tableData) {
		this.tableData = tableData;
	}
	
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
	 * @return Returns the vector_highlowtable.
	 */
	public Vector getVecTop10Details() {
		Logging.debug(" Inside getvecStockDetails");
		
		return vecTop10Details;
	}
	/**
	 * @param vector_highlowtable The vector_highlowtable to set.
	 */
	public void setVecTop10Details(Vector vecTop10Details) {
		Logging.debug(" inside setvecStockDetails");
		this.vecTop10Details = vecTop10Details;
		
	}
	public ResultSet Top10Details(String index2,String sid,String fdate,String toDate) {
		try {
			PreparedStatement pst;
			
			ResultSet rst;
			Logging.debug("inside Top 10 result");
			Logging.debug(fdate+"  "+sid+"  ");
			String Query =ConnectInit.queries.getProperty(index2);
			Logging.debug(Query);
			 pst = app.Connect.con.prepareStatement(Query);
			 pst.setString(1, sid);
			 pst.setString(2, fdate);
			pst.setString(3,toDate);	
			rst = pst.executeQuery();
			Logging.debug("1");
		} catch (SQLException e) {
			Logging.error("Error : " + e.getMessage());
		}
		return rst;
		
	}
}
