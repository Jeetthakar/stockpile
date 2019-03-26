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

import app.Connect;

import com.harrier.initializeation.ConnectInit;


/**
 * @author neha
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RemoveReportPerForm extends ActionForm{
	Logger Logging = Logger.getLogger(RemoveReportPerForm.class);
	private String selectprefrence=null;
	private String remove=null;
	private String delete=null;
	private String selectReport[]=new String[0];
	private Collection reportCollection = null;
	private Collection prefrencecollection = null;
	/**
	 * @return Returns the remove.
	 */
	public String getRemove() {
		return remove;
	}
	/**
	 * @param remove The remove to set.
	 */
	public void setRemove(String remove) {
		this.remove = remove;
	}
	
	
	/**RESEST ALL FORM FIELDS**/
	  public void reset(ActionMapping mapping,HttpServletRequest request){
	  
	  	reportCollection = null;
	  	prefrencecollection = null;
	  	selectprefrence=null;
	  	remove=null;
	  	delete=null;
	  	selectReport=new String[0];
	  }
	  public ActionErrors validate(ActionMapping mapping,HttpServletRequest request)
		{
			ActionErrors errors = new ActionErrors();
			Connection connection = null;
			PreparedStatement stmt = null;
			ResultSet rst = null;
			Connect c = ConnectInit.getConnect();
			String pid=getSelectprefrence();
			
			int count=0;
			try{
				if(getRemove()!=null && getRemove().equals("Remove")){
					
					if(pid!=null && !pid.equals("0")){
						
						try {
							if (connection == null)
								connection = c.getdbConnection();
							stmt = connection.prepareStatement(ConnectInit.queries.getProperty("select_*_from_preferencedetail1"));
							stmt.setString(1,pid);
							rst = stmt.executeQuery();
				          
							while (rst.next()) {
								
								count++;
								
							}
							rst.close();
							stmt.close();
						} catch (Exception e) {
							Logging.error(" Error : " + e.getMessage());
						} 
					
						if(count!=0)
						{
							errors.add(null,new ActionError("Error.message.p_test"));
							return errors;
						}
						
					}
				}
				if(getDelete()!=null && getDelete().equals("Delete")){
					
			       if(pid!=null && !pid.equals("0")){
			       	  if(selectReport.length==0){
			       	     errors.add(null,new ActionError("Error.message.r_test"));
						 return errors;
			       	  }
			       }
				}
				
			}catch(Exception e){
				Logging.error(" Error : Unable to close Connection "+e.getMessage());
				Logging.debug("Error in Validation ");			
			  }	
			finally {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ee) {
					Logging.error(" Error : Unable to close connection "+ ee.getMessage());
				}
			}
			return null;
		}	
	/**
	 * @return Returns the prefrencecollection.
	 */
	public Collection getPrefrencecollection() {
		Vector roles1 = new Vector();
		roles1.add(new LabelValueBean("Not Selected","0"));
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		Connect c = ConnectInit.getConnect();
		//dbconnect();
		try {
			if (connection == null)
				connection = c.getdbConnection();
			stmt = connection.prepareStatement(ConnectInit.queries.getProperty("select_prefrence_collection"));
			rst = stmt.executeQuery();

			while (rst.next()) {
				
				roles1.add(new LabelValueBean(rst.getString(2), rst.getString(1)));
			}
			rst.close();
			stmt.close();
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close connection "+ ee.getMessage());
			}
		}
		prefrencecollection = roles1;
		return prefrencecollection;
		
	}
	/**
	 * @param prefrencecollection The prefrencecollection to set.
	 */
	public void setPrefrencecollection(Collection prefrencecollection) {
		this.prefrencecollection = prefrencecollection;
	}
	/**
	 * @return Returns the reportCollection.
	 */
	public Collection getReportCollection() {
		Vector r1 = new Vector();
		//r1.add(new LabelValueBean("Not Selected","0"));
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		Connect c = ConnectInit.getConnect();
		String pid=getSelectprefrence();
		
		try {
			if (connection == null)
				connection = c.getdbConnection();
			stmt = connection.prepareStatement(ConnectInit.queries.getProperty("select_*_from_preferencedetail1"));
			stmt.setString(1,pid);
			rst = stmt.executeQuery();
           
			while (rst.next()) {
				
				r1.add(new LabelValueBean(rst.getString(2),rst.getString(2)));
				
			}
			rst.close();
			stmt.close();
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close connection "+ ee.getMessage());
			}
		}
		reportCollection = r1;
		return reportCollection ;
	}
	/**
	 * @param reportCollection The reportCollection to set.
	 */
	public void setReportCollection(Collection reportCollection) {
		this.reportCollection = reportCollection;
	}
	/**
	 * @return Returns the selectprefrence.
	 */
	public String getSelectprefrence() {
		return selectprefrence;
	}
	/**
	 * @param selectprefrence The selectprefrence to set.
	 */
	public void setSelectprefrence(String selectprefrence) {
		this.selectprefrence = selectprefrence;
	}
	
	/**
	 * @return Returns the selectReport.
	 */
	public String[] getSelectReport() {
		return selectReport;
	}
	/**
	 * @param selectReport The selectReport to set.
	 */
	public void setSelectReport(String[] selectReport) {
		this.selectReport = selectReport;
	}
	/**
	 * function to delete the preference
	 */
	public void deletepreference(){
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
	    Connect c = ConnectInit.getConnect();
		String pid1=getSelectprefrence();
		int counter=0;
		try{
			if(getRemove()!=null && getRemove().equals("Remove")){
				
				if(pid1!=null && !pid1.equals("0")){
					
					try {
						if (connection == null)
							connection = c.getdbConnection();
						stmt = connection.prepareStatement(ConnectInit.queries.getProperty("select_*_from_preferencedetail1"));
						stmt.setString(1,pid1);
						rst = stmt.executeQuery();
			          
						while (rst.next()) {
							
							counter++;
							
						}
						rst.close();
						stmt.close();
					} catch (Exception e) {
						Logging.error(" Error : " + e.getMessage());
					} 
					if(counter==0){
						try {
							stmt = connection.prepareStatement(ConnectInit.queries.getProperty("delete_from_prefrence"));
							stmt.setString(1,pid1);
							rst = stmt.executeQuery();
				          	connection.setAutoCommit(true);
				          	rst.close();
				          	stmt.close();
						} catch (Exception e) {
							Logging.error(" Error : " + e.getMessage());
							Logging.debug("error"+e.getMessage());
						} 
					}
					
				}
			}
		
			
			
		}catch(Exception e){
			Logging.error(" Error : Unable to close Connection "+e.getMessage());
			Logging.debug("Error in Validation ");			
		  }	
		finally  {
			try {
				if (connection != null)
					connection.close();
			    } catch (Exception ee) {
				  Logging.error(" Error : Unable to close connection "+ ee.getMessage());
			        }
		        }
	}
	/**
	 * function to delete the reports
	 */
	public void deletereport()
	{      Connection connection = null;
	       PreparedStatement pst = null;
	       Connect c = ConnectInit.getConnect();
	       String pid=getSelectprefrence();
	       int uid = Integer.parseInt(pid);
		   String str[]=null;
		  
				try {
					if(getDelete()!=null && getDelete().equals("Delete")){
						
				   if(pid!=null && !pid.equals("0")){
				   	try{
					   if(connection==null)
						connection=c.getdbConnection();
					str=getSelectReport();
					
					for(int k=0;k<str.length;k++)
					{
						Logging.debug("sting"+str[k]);
						
					}
					if(str!=null){
						  for(int j=0;j<str.length;j++)
						  {
							String s=str[j];
							pst = connection.prepareStatement(ConnectInit.queries.getProperty("delete_report"));
							pst.setInt(1,uid);
							pst.setString(2,s);
							pst.executeUpdate();
							connection.setAutoCommit(true);
						
						  }
					  }	
					       
					         pst.close();
				   	}  catch (Exception e) {
						Logging.error(" Error : " + e.getMessage());
					       } 
						}	 
					  }	
					    
				       } catch (Exception e) {
					
					      Logging.error(" Error : "+e.getMessage());
					      Logging.debug(e);
					      }
		 
				
				finally  {
					try {
						if (connection != null)
							connection.close();
					    } catch (Exception ee) {
						  Logging.error(" Error : Unable to close connection "+ ee.getMessage());
					        }
				        }
				
	}
	
	/**
	 * @return Returns the delete.
	 */
	public String getDelete() {
		return delete;
	}
	/**
	 * @param delete The delete to set.
	 */
	public void setDelete(String delete) {
		this.delete = delete;
	}
}
