/*getCompanyWeightageVector
 * Created on Feb 25, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

import app.Connect;

import com.harrier.initializeation.ConnectInit;
/**
 * @author sonali
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CompanyWiseWeightageForm extends ActionForm {
	Logger Logging = Logger.getLogger(CompanyWiseWeightageForm.class);
private String d1=null;
private String check1=null;
private String check2=null;
private String date=null;
private String b1=null;
private Vector vector_indexlist=null;
private ArrayList companyWeightage=new ArrayList();
private String chk="no";
private String graph=" ";
private String filename=" ";
private Vector CompanyWeightageVector=new Vector();
String indexName=null;
double totalMcap=0;
double totalWt=0;
private String download="off";
private String hiddenVar=null;
private String userid1="";

Hashtable IndexNameHash=new Hashtable();

public ActionErrors validate(ActionMapping mapping,HttpServletRequest request){
		ActionErrors errors = new ActionErrors();
		ActionError err=null;
			if(getDate()==null){
				  errors.add("name",new ActionMessage("error.name.required"));
			      
			}
				return errors;
	}
/**
 * @return Returns the totalMcap.
 */
public double getTotalMcap() {
	return totalMcap;
}
/**
 * @param totalMcap The totalMcap to set.
 */
public void setTotalMcap(double totalMcap) {
	this.totalMcap = totalMcap;
}

/**
 * @return Returns the graph.
 */
public String getGraph() {
	Logging.debug("in getter"+graph);
	return graph;
}
/**
 * @param graph The graph to set.
 */
public void setGraph(String graph) {
	this.graph = graph;
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
 * @return Returns the check1.
 */
public String getCheck1() {
	return check1;
}
/**
 * @param check1 The check1 to set.
 */
public void setCheck1(String check1) {
	this.check1 = check1;
}
/**
 * @return Returns the check2.
 */
public String getCheck2() {
	return check2;
}
/**
 * @param check2 The check2 to set.
 */
public void setCheck2(String check2) {
	this.check2 = check2;
}
/**
 * @return Returns the date.
 */
public String getDate() {
	return date;
}
/**
 * @param date The date to set.
 */
public void setDate(String date) {
	this.date = date;
}
/**
 * @return Returns the vector_indexlist.
 */
public Vector getVector_indexlist() {
	totalMcap=0;
	totalWt=0;

	Vector indexCollection=new Vector();
	Connect con=ConnectInit.getConnect();
	Connection connection=null;
	ResultSet rs=null;
	String query;
	
	try{
		if(connection==null)
			connection=con.getdbConnection();
			String chk =getCheck1();
			if(chk!=null ){
				query = ConnectInit.queries.getProperty("index_list");
			}else{
				query = ConnectInit.queries.getProperty("index_list_without_child");
			}
			Vector vector_indexlist = new Vector();
			try {
				
				PreparedStatement pst = connection.prepareStatement(query);
				pst.setString(1,userid1);
				rs = pst.executeQuery();
				//Statement stmt = connection.createStatement();
				//rs = stmt.executeQuery(query);
				
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
	try{
		if(connection==null)
			connection=con.getdbConnection();
			try {
				
				String local_date=getDate();
				String local_d1=getD1();
				String query = ConnectInit.queries.getProperty("company_wise_weightage");
				Logging.debug("Query   = "+query);
				pst = connection.prepareStatement(query);
				pst.setString(1, local_d1);
				//pst.setString(2, local_date);			
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
 * @return Returns the chk.
 */
public String getChk() {
	return chk;
}
/**
 * @param chk The chk to set.
 */
public void setChk(String chk) {
	this.chk = chk;
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
/**
 * @return Returns the filename.
 */
public String getFilename() {
	return filename;
}
/**
 * @param filename The filename to set.
 */
public void setFilename(String filename) {
	this.filename = filename;
}
/**
 * @return Returns the indexName.
 */
public String getIndexName() {
	try{
		String local_d1=getD1();
		Logging.debug("INDEX id="+local_d1);
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
 * @return Returns the download.
 */
public String getDownload() {
	return download;
}
/**
 * @param download The download to set.
 */
public void setDownload(String download) {
	this.download = download;
}
public void reset(ActionMapping arg0, HttpServletRequest arg1) {
	this.check1=null;
	this.check2=null;
	this.date=null;
	this.b1=null;
	this.d1=null;
	this.download=null;
	this.chk=null;
	this.graph=null;
	this.filename=null;
	this.indexName=null;
	this.totalMcap=0;
	this.totalWt=0;
	this.hiddenVar=null;
}

/**
 * @return Returns the totalWt.
 */
public double getTotalWt() {
	return totalWt;
}
/**
 * @param totalWt The totalWt to set.
 */
public void setTotalWt(double totalWt) {
	this.totalWt = totalWt;
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
public String getUserid1() {
	return userid1;
}
public void setUserid1(String userid1) {
	this.userid1 = userid1;
}
}
