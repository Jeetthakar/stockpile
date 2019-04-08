/*
 * Created on Feb 25, 2006
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
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;
import org.jfree.chart.demo.servlet.AdjustDecimal;

import app.Connect;

import com.harrier.initializeation.ConnectInit;

/**
 * @author sonali
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IndexCompareOHLCForm extends ActionForm {
	Logger Logging = Logger.getLogger(IndexCompareOHLCForm.class);
	private String[] d1=null;
	private String check=null;
	private String from=null;
	private String to=null;
	private String b1=null;
	private Vector vector_indexlist=null;
	ArrayList indexohlc=new ArrayList();
	Vector vexcel=new Vector();
	
	Connection connection=null;
	Connect con=ConnectInit.getConnect();
	private String hiddenVar=null;
	
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		// TODO Auto-generated method stub
			this.connection=null;
			this.check=null;
			this.d1=null;
			this.from=null;
			this.to=null;
			this.b1=null;
			

	}

	public Vector getVector_indexlist() {
	
		Vector indexCollection=new Vector();
		Connect con=ConnectInit.getConnect();
		Connection connection=null;
		ResultSet rs=null;
		String query;

		try{
			
			if(connection==null)
				connection=con.getdbConnection();
				String chk =getCheck();
				if(chk!=null ){
					query = ConnectInit.queries.getProperty("index_list");
				}else{
					query = ConnectInit.queries.getProperty("index_list_without_child");
				}
				Vector vector_indexlist = new Vector();
				try {
					Statement stmt = connection.createStatement();
					rs = stmt.executeQuery(query);
					while (rs.next()) {
						
						vector_indexlist.add(new LabelValueBean(rs.getString(2),rs.getString(1)));
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
	public void setVector_indexlist(Vector vector_indexlist) {
		this.vector_indexlist = vector_indexlist;
	}
	
	public ArrayList getIndexohlc() {
		String dt=null,open=null,high=null,low=null,close=null;
		ArrayList temp=new ArrayList();
		String s1[]=getD1();
		String fromdate=getFrom();
		String toDate=getTo();
//		org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		ResultSet rs=null;
		PreparedStatement pst=null;
		String query;
		try{
			
			if(connection==null)
				connection=null;
			
				connection=con.getdbConnection();
				query = ConnectInit.queries.getProperty("get_index_daily_values_between_date_OHLC");
				Logging.debug("s1[0]="+s1[0]);
				Logging.debug("s1[0]="+s1[1]);
				Logging.debug("From date="+fromdate);
				Logging.debug("to date = "+toDate);
				Logging.debug("query="+query);
				pst = connection.prepareStatement(query);
				pst.setString(1,s1[0]);
				pst.setString(2,s1[1]);	
				pst.setString(3,s1[0]);	
				pst.setString(4,fromdate);
				pst.setString(5,toDate);
				
				
				rs = pst.executeQuery();
				Logging.debug(" AFter Exeeution........");
				Logging.debug(" RST count = "+ rs.getRow());
				int i=0;
				try {
					while (rs.next()) {
								if (rs.getString(2) == null) {
								dt="--";
								vexcel.add(i,"--");
						} else {
							dt=rs.getString(2);
							vexcel.add(i,rs.getString(2));
						}
						i++;
						if (rs.getString(3) == null) {
							open="0.00";
							vexcel.add(i,"0.00");
						} else {
							String ad1=(String)rs.getString(3);
							ad1=ad.indexcompose(ad1);
							open=ad1;
							vexcel.add(i,ad1);
						}
						i++;
						if (rs.getString(4) == null) {
							high="0.00";
							vexcel.add(i,"0.00");
						} else {
							String ad2=(String)rs.getString(4);
		            		ad2=ad.indexcompose(ad2);
		            		high=ad2;
		            		vexcel.add(i,ad2);
						}
						i++;
						if (rs.getString(5) == null) {
							low="0.00";
							vexcel.add(i,"0.00");
						} else {
							String ad3=(String)rs.getString(5);
		            		ad3=ad.indexcompose(ad3);
		            		low=ad3;
		            		vexcel.add(i,low);
						}
						i++;
						if (rs.getString(6) == null) {
							close="0.00";
							vexcel.add(i,"0.00");
						} else {
		            		String ad4=(String)rs.getString(6);
		            		ad4=ad.indexcompose(ad4);
		            		close=ad4;
		            		vexcel.add(i,ad4);
						}
						IndexOHLCDetails iod=new IndexOHLCDetails(dt,open,high,low,close);
						temp.add(iod);
						
			}   //end of while

					
				} catch (Exception e) {
					// TODO: handle exception
					Logging.error(" Error : "+e);
										}
				    }catch(Exception e){
				    	Logging.error(" Error : "+e);
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
					    Logging.error(" Error : Unable to close Connection "+ex);
		    					 }
					 	   Logging.error(" Error : Unable to close Connection "+ee);
				    		}
				    }
				    setVexcel(vexcel);
				    indexohlc=temp;		
		    		return indexohlc; 
	}
public void setIndexohlc(ArrayList indexohlc) {
		this.indexohlc = indexohlc;
}
public String[] getD1() {
	return d1;
}
public void setD1(String d1[]) {
	this.d1 = d1;
}
public String getFrom() {
	return from;
}
public void setFrom(String from) {
	this.from = from;
}
public String getTo() {
	return to;
}
public void setTo(String to) {
	this.to = to;
}
public String getB1() {
	return b1;
}
public void setB1(String b1) {
	this.b1 = b1;
}
public String getCheck() {
		return check;
}
public void setCheck(String check) {
	this.check = check;
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
	 * @return Returns the vexcel.
	 */
	public Vector getVexcel() {
		Logging.debug("Starting of getVexcel");
		return vexcel;
		
	}
	/**
	 * @param vexcel The vexcel to set.
	 */
	public void setVexcel(Vector vexcel) {
		Logging.debug("Starting of setvexcel");
		this.vexcel = vexcel;
		Logging.debug(" Vector ="+ vexcel.toString());
		
	}
}//end of class
