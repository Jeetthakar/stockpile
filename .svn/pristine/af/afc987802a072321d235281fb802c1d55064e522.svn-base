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
public class TradedVolumeForm extends ActionForm{
	Logger Logging = Logger.getLogger(TradedVolumeForm.class);
	private String clear=null,traded_volume=null,from=null, to=null,checkShwIndices=null,
		compute=null,selectIndExch=null,filter="0", indExchName=null;
	
	private Collection selectIndExchCollection=null;
	private Vector trdVolVec =new Vector();
	
	
	private ArrayList tableData=null;
//	app.Connect con=new app.Connect();
	Connect con = ConnectInit.getConnect();
	private ResultSet rst;
	private Hashtable IndexNameHash=new Hashtable();

	
	 /**RESEST ALL FORM FIELDS**/
	  public void reset(ActionMapping mapping,HttpServletRequest request){
	  	selectIndExchCollection=null;selectIndExch=null;
	  	clear=null;traded_volume=null;
	  	filter="0";from =null; to =null;
	  	checkShwIndices=null;
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
	 * @return Returns the selectIndExch.
	 */
	public String getSelectIndExch() {
		/*Connection connection=null;
		try {
			if(connection==null)
				connection=con.getdbConnection();
			if(filter!=null && filter.equals("2")){
				PreparedStatement stmt = Connect.con.prepareStatement(con.queries.getProperty("select_system_config"));
				ResultSet rst = stmt.executeQuery();
				while(rst.next()){
					selectIndExch=rst.getString(25);
				}
				rst.close();
				stmt.close();
			}
			else if(filter!=null && filter.equals("1")){
				PreparedStatement stmt = Connect.con.prepareStatement(con.queries.getProperty("select_system_config"));
				ResultSet rst = stmt.executeQuery();
				while(rst.next()){
					selectIndExch=rst.getString(18);
				}
				rst.close();
				stmt.close();
			}
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
		}*/
		return selectIndExch;
	}
	/**
	 * @param selectIndExch The selectIndExch to set.
	 */
	public void setSelectIndExch(String selectIndExch) {
		this.selectIndExch = selectIndExch;
	}
	/**
	 * @return Returns the selectIndExchCollection.
	 */
	public Collection getSelectIndExchCollection() {
		Vector vec = new Vector();
		PreparedStatement stmt=null;
		//dbconnect();
		Connection connection=null;
		try{
			if(connection==null)
				connection=con.getdbConnection();
			vec.add(new LabelValueBean("Not Selected","0"));
			if(filter==null || filter.trim().length()==0 || filter.equals("0")){
				
				selectIndExchCollection = vec;
				return selectIndExchCollection;
			}
			else if(filter.equals("2")){
				try {
					if (checkShwIndices != null && checkShwIndices.equals("on")) {
						stmt = connection.prepareStatement(ConnectInit.queries.getProperty("index_list"));
						//query = c.queries.getProperty("index_list");
					} else {
						stmt = connection.prepareStatement(ConnectInit.queries.getProperty("index_list_without_child"));
						//query = c.queries.getProperty("index_list_without_child");
					}
					ResultSet rst = stmt.executeQuery();
					Logging.debug(" After Excecuting query..");
					while(rst.next()){
						vec.add(new LabelValueBean(rst.getString(2),rst.getString(1)));
						IndexNameHash.put(rst.getString(1),rst.getString(2));
					}
					
					rst.close();
					stmt.close();
					selectIndExchCollection = vec;
					return selectIndExchCollection;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					Logging.error(" Error : "+e.getMessage());
					//e.printStackTrace();
				}	
			}
			else if(filter.equals("1")){
				try {
					stmt = connection.prepareStatement(ConnectInit.queries.getProperty("stock_exchange_online_list"));
					ResultSet rst = stmt.executeQuery();
					while(rst.next()){
						vec.add(new LabelValueBean(rst.getString(2),rst.getString(1)));
						IndexNameHash.put(rst.getString(1),rst.getString(2));
					}
					rst.close();
					stmt.close();
					selectIndExchCollection = vec;
					return selectIndExchCollection;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					Logging.error(" Error : "+e.getMessage());
					//e.printStackTrace();
				}	
				
			}
			selectIndExchCollection = vec;
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
		return selectIndExchCollection;
	}
	/**
	 * @param selectIndExchCollection The selectIndExchCollection to set.
	 */
	public void setSelectIndExchCollection(Collection selectIndExchCollection) {
		this.selectIndExchCollection = selectIndExchCollection;
	}
	/**
	 * @return Returns the tableData.
	 */
	public ArrayList getTableData() {
		Logging.debug(" Inside getTableData");
//		org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		//app.Logging.getDebug("Inside setVector_traded_volume exchange_id and indexid is "+exch_id+" , "+ind_id);

		String stk_id=null, stk_name=null, trd_vol=null;
		Connection connection=null;
		StockDetails stkDetails;
		ArrayList tempData= new ArrayList();
		Vector vec=new Vector();
		int i=0;
		
		try {
			if(connection == null)
				connection = con.getdbConnection();
			if(traded_volume.length()< 1){
				traded_volume="0";
			}
			if(filter.equals("1")){  // Exchange wise
				Logging.debug(" Inside filter = 1(Exchange )");
				rst = con.tradedVolumeResult("traded_volume_list_exchange_wise",selectIndExch,traded_volume,from,to);
			}else {
				Logging.debug("Inside filter = 2 (Index) ");
				rst = con.tradedVolumeResult("traded_volume_list_index_wise",selectIndExch,traded_volume,from,to);
			}
			
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
			tableData = tempData;
			trdVolVec=vec;
			Logging.debug("size of arraylist "+tempData.size());
			return tableData;
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
	 * @return Returns the traded_volume.
	 */
	public String getTraded_volume() {
		if(traded_volume== "0")
			traded_volume="";
		
		return traded_volume;
	}
	/**
	 * @param traded_volume The traded_volume to set.
	 */
	public void setTraded_volume(String traded_volume) {
		this.traded_volume = traded_volume;
	}
	
	/**
	 * @return Returns the indExchName.
	 */
	public String getIndExchName() {
		Logging.debug(" inside getindExchName");
		try{
			String id=getSelectIndExch();
			Enumeration e;
			String str;
			String iname="";
			e=IndexNameHash.keys();
			while(e.hasMoreElements()){
				str=(String)e.nextElement();
				iname=(String)IndexNameHash.get(str);
				if(str.equals(id)){
					Logging.debug(" found !!!!");
					indExchName=iname;
					break;
				}
			}
			Logging.debug(" indExchName = "+ indExchName);
		}catch(Exception e){
			Logging.debug(" Error "+ e.toString());
		}
		return indExchName;
	}
	/**
	 * @param indExchName The indExchName to set.
	 */
	public void setIndExchName(String indExchName) {
		this.indExchName = indExchName;
	}
	
	
	/**
	 * @return Returns the checkShwIndices.
	 */
	public String getCheckShwIndices() {
		return checkShwIndices;
	}
	/**
	 * @param checkShwIndices The checkShwIndices to set.
	 */
	public void setCheckShwIndices(String checkShwIndices) {
		this.checkShwIndices = checkShwIndices;
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
}	  