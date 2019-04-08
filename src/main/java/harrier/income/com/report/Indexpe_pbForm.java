/*
 * Created on Feb 24, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
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
public class Indexpe_pbForm extends ActionForm {
	Logger Logging = Logger.getLogger(Indexpe_pbForm.class);
private String d1=null;
private String roleId_pepb="";

//index list
private String check=null,userid1;					// check box
private String from=null;					//from date
private String to=null;						//to date
private String b1=null;						//submit button
private Vector vector_indexlist=null;		//list of indices
ArrayList index_arraylist=new ArrayList();	//table data
private String hiddenVar=null;				//changes with submit
Vector vExcel=new Vector();					//vector for creating excel file
private String indexName=null;				//to print indexname in printerfriendly
Hashtable IndexNameHash=new Hashtable();	//to fill indexids and names to get the selected index name
private Connection connection=null;

public void reset(ActionMapping arg0, HttpServletRequest arg1) {
	this.check=null;
	this.to=null;
	this.from=null;
	this.d1=null;
	this.b1=null;
}

/**
 * @return Returns the vector_indexlist.
 */
public Collection getVector_indexlist() {
	Vector indexCollection=new Vector();
	Connect con=ConnectInit.getConnect();
	Connection connection=null;
	ResultSet rs=null;
	String query;
	IndexComposeReportMethod Icr = new IndexComposeReportMethod();
	try{
		if(connection==null)
			connection=con.getdbConnection();
			if(getCheck()!=null && getCheck().trim().equals("on")){
				query = ConnectInit.queries.getProperty("index_list");
			}else{
				query = ConnectInit.queries.getProperty("index_list_without_child");
			}
			Vector vector_indexlist = new Vector();
			try {
			/*	Statement stmt = connection.createStatement();
				rs = stmt.executeQuery(query);
				while (rs.next()) {
					vector_indexlist.add(new LabelValueBean(rs.getString(2),rs.getString(1)));
					IndexNameHash.put(rs.getString(1),rs.getString(2));

				}*/
				
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,userid1);
					rs = pst.executeQuery();
					//AcessControl asc=new AcessControl();
					//String NotSelected=asc.getLangValues("Masters.NotSelected");
					//Logging.getDebug(" Inside getIndexcollection(): Not Selected ="+NotSelected);
					
					vector_indexlist.add(new LabelValueBean("Not Selected","0"));
					while (rs.next()) {
						
						vector_indexlist.add(new LabelValueBean(rs.getString(2),rs.getString(1)));
						IndexNameHash.put(rs.getString(1),rs.getString(2));
						
					}
					
//					Change by Manoj adekar
					int role_id2=Integer.parseInt(roleId_pepb);
					if(role_id2!=1)
					{
						ResultSet rbs = Icr.benchindecolection(connection,"index_list_without_child_bench");
						while (rbs.next()) {
							
							vector_indexlist.add(new LabelValueBean(rbs.getString(2), rbs.getString(1)));
							IndexNameHash.put(rs.getString(1),rs.getString(2));
							
						} 
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
 * @return Returns the check.
 */
public String getCheck() {
	return check;
}
/**
 * @param check The check to set.
 */
public void setCheck(String check) {
	this.check = check;
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
/**
 * @return Returns the index_arraylist.
 */
public ArrayList getIndex_arraylist() {
	ArrayList vdata=new ArrayList();
	String td=null,close=null,change=null,mcap=null,shtr=null,trnovr=null,perat=null,pbrat=null,dividend=null;
	try {
	//		org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal(); 
		AdjustDecimal ad = ConnectInit.getAdjustDecimal();
		//		app.Connect con=new app.Connect();
			Connect con = ConnectInit.getConnect();
			//Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection
			if(connection==null)
			{
				connection=con.getdbConnection();
			}
			
			vdata = new ArrayList();
			String local_d1=getD1();
			String local_from=getFrom() ;
			String local_to=getTo() ;
			double lastclose=getlastclosing(local_d1,local_from);
			Logging.debug(local_d1+"  "+local_from+" "+"  "+local_to);
			PreparedStatement pst = connection.prepareStatement(ConnectInit.queries.getProperty("indexwise_pe_pb_ratio"));
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

//	Close the Dynamic Connection : Manoj Adekar
	finally {
		try {
			if (connection != null)
				connection.close();
		} catch (Exception ee) {
			Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
		}
	}
	setVExcel(vExcel);
	index_arraylist=vdata;
	return index_arraylist;
}
public double getlastclosing(String id,String fdate)
{		Connection connection=null;
	double lastclose=0.0;
	try
	{
		Connect con = ConnectInit.getConnect();
//		Connect con=new app.Connect();
		if(connection==null)
		{
			connection=con.getdbConnection();
		}
		PreparedStatement pst = connection.prepareStatement(ConnectInit.queries.getProperty("get_index_closing_max_value"));
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
	finally {
		try {
			if (connection != null)
				connection.close();
		} catch (Exception ee) {
			Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
		}
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
 * @return Returns the vExcel.
 */
public Vector getVExcel() {
	return vExcel;
}
/**
 * @param excel The vExcel to set.
 */
public void setVExcel(Vector excel) {
	vExcel = excel;
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
	return indexName ;
}
/**
 * @param indexName The indexName to set.
 */
public void setIndexName(String indexName) {
	this.indexName = indexName;
}

public String getUserid1() {
	return userid1;
}

public void setUserid1(String userid1) {
	this.userid1 = userid1;
}

public String getRoleId_pepb() {
	return roleId_pepb;
}

public void setRoleId_pepb(String roleId_pepb) {
	this.roleId_pepb = roleId_pepb;
}


}
