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
public class StockDividentForm extends ActionForm{
	Logger Logging = Logger.getLogger(StockDividentForm.class);
	private String clear=null,from=null, to=null,checkShwIndices=null,
	compute=null,selectIndExch=null,filter="0", indExchName=null;

private Collection selectIndExchCollection=null;
private Vector stkDividentVec = new Vector();


private ArrayList tableData=null;
//	app.Connect con=new app.Connect();
Connect con = ConnectInit.getConnect();
private ResultSet rst;
private Hashtable IndexNameHash=new Hashtable();


 /**RESEST ALL FORM FIELDS**/
  public void reset(ActionMapping mapping,HttpServletRequest request){
  	selectIndExchCollection=null;selectIndExch=null;
  	clear=null;
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
	 * @return Returns the selectIndExch.
	 */
	public String getSelectIndExch() {
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
					
				} else {
					stmt = connection.prepareStatement(ConnectInit.queries.getProperty("index_list_without_child"));
						
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
	String stk_id=null, stk_name=null, face=null, tis = null, mcv=null, amt=null, date=null;
	
//	org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
	AdjustDecimal ad = ConnectInit.getAdjustDecimal();
	Connection connection=null;
	StockDetails stkDetails;
	ArrayList tempData= new ArrayList();
	Vector vec = new Vector();
	int i=0;
	
	try {
		if(connection == null)
			connection = con.getdbConnection();
		
		if(filter.equals("1")){  // Exchange wise
			Logging.debug(" Inside filter = 1(Exchange )");
			rst = con.changeInStockDetailResult("stock_divident_exchange_wise",selectIndExch,from,to);
			
		}else {
			Logging.debug("Inside filter = 2 (Index) ");
			rst = con.changeInStockDetailResult("stock_divident_index_wise",selectIndExch,from,to);
		}
		
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
		tableData = tempData;
		stkDividentVec= vec;
		Logging.debug("size of arraylist "+tempData.size());
		return tableData;
	} catch (Exception sqlexp) {
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
}  