package harrier.income.com.report;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

import app.AcessControl;
import app.Connect;

import com.harrier.initializeation.ConnectInit;
/**
* @author sonali
*
* TODO To change the template for this generated type comment go to
* Window - Preferences - Java - Code Style - Code Templates
*/
public class IndexCompareForm extends ActionForm {
	Logger Logging = Logger.getLogger(IndexCompareForm.class);
	private String[] d2=null;
	private String check=null;
	private String from=" ",roleId_com;
	private String to=" ",frombut,tobut;
	private String b1=null;
	String userid1;
	//private Vector vector_indexlist=null;
	private Collection vector_indexlist=null;
	PreparedStatement pst;
	ResultSet rs,rs1;
	Connection connection=null;
	private String graph=" ";
	private String buttonValue=null;
	IndexComposeReportMethod Icr = new IndexComposeReportMethod();
	
	/*============================Following public void reset() method is Added By Ganesh:23/1/2007 
	 * so that if IndexComapreS1.jsp page is loaded through menu then it should be reset.
	 * Else if page is loaded through compare button present on the same page then page should not be reset
	 * ==================================================================================================
	 * */
	/*====================START CODE=====================================*/
	public void reset(){
		this.check=null;
		this.from="";
		this.to="";
		this.buttonValue =null;
		this.connection=null;
		graph=" ";
		d2=null;
	}
	/*======================END CODE ADDED==============================*/
	
	
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		// TODO Auto-generated method stub
		
		this.check=null;
		this.from="";
		this.to="";
		this.buttonValue =null;
		this.connection=null;
		graph=" ";
		d2=null;
		
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
	 * @return Returns the d2.
	 */
	public String[] getD2() {
		return d2;
	}
	/**
	 * @param d2 The d2 to set.
	 */
	public void setD2(String[] d2) {
		this.d2 = d2;
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
	 * @return Returns the vector_indexlist.
	 */

	public Collection getVector_indexlist() {
		Vector indexCollection=new Vector();
		
		Connect con=ConnectInit.getConnect();
		connection=null;
		ResultSet rs=null;
		String query;
		vector_indexlist=null;
		try{
			if(connection==null)
				connection=con.getdbConnection();
				String chk =getCheck();
				if(chk!=null ){
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
					
					indexCollection.add(new LabelValueBean("Not Selected","0"));
					while (rs.next()) {
						indexCollection.add(new LabelValueBean(rs.getString(2),rs.getString(1)));
					}
//					Change by Manoj adekar
					int role_id2=Integer.parseInt(roleId_com);
					if(role_id2!=1)
					{

						ResultSet rbs = Icr.benchindecolection(connection,"index_list_without_child_bench");
						while (rbs.next()) {
							indexCollection.add(new LabelValueBean(rbs.getString(2),rbs.getString(1)));
						}
					}
					
					vector_indexlist=indexCollection;
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
				//return indexCollection;
				    return vector_indexlist;

	}//end of getVector_indexlist()
	/**
	 * @param vector_indexlist The vector_indexlist to set.
	 */
	public void setVector_indexlist(Vector vector_indexlist) {
				this.vector_indexlist=vector_indexlist;		
	}
	
	public ActionErrors validate(ActionMapping mapping,HttpServletRequest request){
		ActionErrors errors = new ActionErrors();
		ActionError err=null;
		
		
			if(d2==null){
				errors.add("select index",err);
			}
			if(from==null){
				
			}
			if(to==null){
				
			}
				return errors;
	}
	
	/**
	 * @return Returns the graph.
	 */
	public String getGraph() {
		return graph;
	}
	/**
	 * @param graph The graph to set.
	 */
	public void setGraph(String graph) {
		this.graph = graph;
	}
	/**
	 * @return Returns the buttonValue.
	 */
	public String getButtonValue() {
		return buttonValue;
	}
	/**
	 * @param buttonValue The buttonValue to set.
	 */
	public void setButtonValue(String buttonValue) {
		this.buttonValue = buttonValue;
	}
	public String getUserid1() {
		return userid1;
	}
	public void setUserid1(String userid1) {
		Logging.debug("value at page"+userid1);
		this.userid1 = userid1;
	}
	public String getRoleId_com() {
		return roleId_com;
	}
	public void setRoleId_com(String roleId_com) {
		this.roleId_com = roleId_com;
	}
	public String getFrombut() {
		return frombut;
	}
	public void setFrombut(String frombut) {
		this.frombut = frombut;
	}
	public String getTobut() {
		return tobut;
	}
	public void setTobut(String tobut) {
		this.tobut = tobut;
	}
	

	/**
	 * @return Returns the comapareButtonClick.
	 */

	
}

