/*
 * Created on Mar 3, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

import app.Connect;

import com.harrier.initializeation.ConnectInit;

/**
 * @author sonali
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InactiveStockListForm extends ActionForm {
	Logger Logging = Logger.getLogger(InactiveStockListForm.class);
	private String d1=null;							//indexid
	private String b1=null;							//submit button
	private Vector vector_indexlist=null;			//list of indices 
	private String hiddenVar=null;					//value chagnes with submit
	private ArrayList tableData=new ArrayList();	//table data
	private Vector vExcel=new Vector();				//vector required for download excel link
	private String indexName=null;					//to display indexname in printerfriendly link
	Hashtable IndexNameHash=new Hashtable();		//to get the indexname from indexid of selected index

	
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		this.b1=null;
		this.d1=null;
		this.hiddenVar=null;
	}
	
	public ArrayList getTableData() {
 		Connect con=ConnectInit.getConnect();
		Connection connection=null;
		PreparedStatement pst;
		ResultSet rs=null;
		String query;
		ArrayList temp=new ArrayList();
		
		//temporary variables to pass to the constructor of InactiveStockDetails
		String stockId,stockName,outstandingShares,faceValue,date;
		String index_id=getD1();
		try{
			query = ConnectInit.queries.getProperty("get_stock_list_for_inactive_stock");
			if(connection==null)
				connection=con.getdbConnection();
				try {
					pst = connection.prepareStatement(query);
					pst.setString(1, index_id);
					rs = pst.executeQuery();
					int i=0;
					vExcel.clear();
					while (rs.next()) {
						if (rs.getString(1) == null) {
							stockId="0";
						} else {
							stockId=rs.getString(1);
						}
						vExcel.add(i,stockId);
						i++;		
						if (rs.getString(2) == null) {
							stockName="--";
						} else {
							stockName=rs.getString(2);
						}
						vExcel.add(i,stockName);
						i++;

						if (rs.getString(3) == null) {
							outstandingShares="0";
						} else {
							outstandingShares=rs.getString(3);	
						}
						vExcel.add(i,outstandingShares);
						i++;
						if (rs.getString(4) == null) {
							faceValue="0";
						} else {
							String price=(String)rs.getString(4);
							faceValue=price;
						}
						vExcel.add(i,faceValue);
						i++;				

						if (rs.getString(5) == null) {
							date="0";
						} else {
							date=rs.getString(5);
							
						}
						vExcel.add(i,date );
						i++;				
	
						InactiveStockDetails isd=new InactiveStockDetails(stockId,stockName,outstandingShares,faceValue,date);
						temp.add(isd);
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
				    setVExcel(vExcel);
				    tableData=temp;
				    return tableData;
	}
	/**
	 * @param tableData The tableData to set.
	 */
	public void setTableData(ArrayList tableData) {
		this.tableData = tableData;
	}
	/**
	 * @return Returns the vector_indexlist.
	 */
	public Vector getVector_indexlist() {
		Vector indexCollection=new Vector();
		Connect con=ConnectInit.getConnect();
		Connection connection=null;
		ResultSet rs=null;
		String query;
		
		try{
			if(connection==null)
				connection=con.getdbConnection();
				query = ConnectInit.queries.getProperty("stock_exchange_online_list");
				Vector vector_indexlist = new Vector();
				try {
					Statement stmt = connection.createStatement();
					rs = stmt.executeQuery(query);
					while (rs.next()) {
						vector_indexlist.add(new LabelValueBean(rs.getString(2),rs.getString(1)));
						IndexNameHash.put(rs.getString(1),rs.getString(2));
					}
					indexCollection = vector_indexlist;
					
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
				return indexCollection;
		

		
	}
	/**
	 * @param vector_indexlist The vector_indexlist to set.
	 */
	public void setVector_indexlist(Vector vector_indexlist) {
		this.vector_indexlist = vector_indexlist;
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
	public void setB1(String b1) {
		this.b1 = b1;
	}
	/**
	 * @return Returns the d1.
	 */
	public String getD1() {
		return d1;
	}
	/**
	 * @param d1 The d1 to set.
	 */
	public void setD1(String d1) {
		this.d1 = d1;
	}
	/**
	 * @return Returns the hiddenVar.
	 */
	public String getHiddenVar() {
		return hiddenVar;
	}
	/**
	 * @param hiddenVar The hiddenVar to set.
	 */
	public void setHiddenVar(String hiddenVar) {
		this.hiddenVar = hiddenVar;
	}
	/**
	 * @return Returns the tableData.
	 */
	/**
	 * @return Returns the vExcel.
	 */
	public Vector getVExcel() {
		return vExcel;
	}
	/**
	 * @param excel The vExcel to set.
	 */
	public void setVExcel(Vector excel) {
		this.vExcel = excel;
	}
	/**
	 * @return Returns the indexName.
	 */
	public String getIndexName() {
		try{
			String local_d1=getD1();
			Enumeration e;
			String str;
			String iname="",ival="";
			e=IndexNameHash.keys();
			while(e.hasMoreElements()){
				str=(String)e.nextElement();
				iname=(String)IndexNameHash.get(str);
				if(str.equals(local_d1)){
					indexName=iname;
					break;
				}
			}
		}catch(Exception e){
			Logging.error("Error :"+e.getMessage());
		}

		return indexName;
	}
	/**
	 * @param indexName The indexName to set.
	 */
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
}
