package harrier.income.com.report;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class ReportPerNameForm extends ActionForm{
	Logger Logging = Logger.getLogger(ReportPerNameForm.class);
	private String prefer_name=null,selectprename=null,prefername_check=null,additem=null,update=null;
	private Collection prenameCollection=null;
	int id;
	String name=null;
	Connect con =ConnectInit.getConnect();
	Connection connection=null;
	Connect connect = ConnectInit.getConnect();
	
	/**RESEST ALL FORM FIELDS**/
	  public void reset(ActionMapping mapping,HttpServletRequest request){
	  	prenameCollection=null;
	  	prefer_name=null;
	  	selectprename=null;
	  }
	 	 
	/**
	 * @return Returns the prefer_name.
	 */
	public String getPrefer_name() {
		return prefer_name;
	}
	/**
	 * @param prefer_name The prefer_name to set.
	 */
	public void setPrefer_name(String prefer_name) {
		this.prefer_name = prefer_name;
	}
	/**
	 * @return Returns the prenameCollection.
	 */
	public Collection getPrenameCollection() {
		Vector vec = new Vector();
		vec.add(new LabelValueBean("Not Selected","0"));
		connection=null;
		String prefer_id = null;
		String prefername = null;
		
		try{
			if(connection==null)
				connection=con.getdbConnection();
			try {
				PreparedStatement stmt =connection.prepareStatement(ConnectInit.queries.getProperty("get_preference_list"));
				ResultSet rst = stmt.executeQuery();
				while(rst.next()){
					prefer_id = rst.getString(1);
					prefername = rst.getString(2);
					if(prefername.equals("") || prefername.length() == 0)
				{
					vec.add(new LabelValueBean("--",prefer_id));
				}
				else
				{
					
					vec.add(new LabelValueBean(prefername,prefer_id ));
				}
				}
				rst.close();
				stmt.close();
				prenameCollection = vec;
				return prenameCollection;
				
			}catch (SQLException e) {
				Logging.error("Error  :"+e.getMessage());
				Logging.error("ID_Check "+prefer_id+" Name_Check "+ prefername);
			//	e.printStackTrace();
			}	
		} catch(Exception e){
			Logging.error(" Error : "+e.getMessage());
		}
		finally{
			try{if(connection!=null)
				connection.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
		
		return prenameCollection;
	}
	/**
	 * @param prenameCollection The prenameCollection to set.
	 */
	public void setPrenameCollection(Collection prenameCollection) {
		this.prenameCollection = prenameCollection;
	}
	
	
	/**
	 * @return Returns the selectprename.
	 */
	public String getSelectprename() {
		return selectprename;
	}
	/**
	 * @param selectprename The selectprename to set.
	 */
	public void setSelectprename(String selectprename) {
		this.selectprename = selectprename;
	}
	
	
	/**
	 * @return Returns the id.
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(int id) {
		this.id = id;
		get_name_id(id);
	}
	
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		if(name!=null){
			this.name = name.trim();
			setPrefer_name(name);
			getPrefer_name();
		}
		else
			this.name = name;
	}
	public String getPrefername_check() {
		return prefername_check;
	}
	public void setPrefername_check(String prefername_check) {
		this.prefername_check = prefername_check;
	}
	
	public void get_name_id(int id)
	{
		PreparedStatement pst,pst1;
		ResultSet rst,rst1;    
		int id_int = id;
		connection=null;
		try
		{
			if(connection==null)
				connection=con.getdbConnection();
			try 
			{        
				pst = connection.prepareStatement(ConnectInit.queries.getProperty("get_prefer_name_id"));
				pst.setInt(1,id_int);
				rst = pst.executeQuery();
				while(rst.next())
				{  
					name = rst.getString(1);
					setName(name);
					setPrefer_name(name);
					//setCmp_name_check(name);
				}
				rst.close();
				pst.close();
			}catch (Exception e)
			{
				Logging.error("Error......... :"+e);
			}
		}
		finally{
			try{if(connection!=null)
				connection.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
	}
	
	public ActionErrors validate(ActionMapping mapping,HttpServletRequest request)
	 {
		String addvalue = getAdditem();
		String updatevalue = getUpdate();
		
		Logging.debug("Inside validate.......... ");
		if(addvalue!=null)
		{
			ActionErrors errors = new ActionErrors();
			boolean flag=true,flag1=true;
			try{
				int check = checkData(prefer_name);
				if(check == 1)
				{
					errors.add("DuplicateEntry",new ActionError("Error.message.DuplicateEntry"));
					setAdditem(null);
				}
			}catch(Exception e){
			     errors.add(null,new ActionError("Error.message.msg"));
		      }	
		     return errors;
		}
		 if(updatevalue!=null)
		{
			//Logging.getDebug("Error in Validation U..... "+updatevalue);
			ActionErrors errors = new ActionErrors();
			boolean flag=true,flag1=true;
			//CountriesAction act = new CountriesAction();
			try{
				int idname_t = getId();
				if(idname_t == 0)
				{
					setUpdate(null);
					setId(0);
					errors.add("DuplicateEntry",new ActionError("Error.message.selectfromlist"));			
				}
				if(!getPrefer_name().equalsIgnoreCase(getPrefername_check()))
				{
				try{
					Logging.debug("Preference Name "+prefer_name);
					int check = checkData(prefer_name.trim());
					if(check == 1)
					{
						errors.add("DuplicateEntry",new ActionError("Error.message.DuplicateEntry"));
						setAdditem(null);
						setUpdate(null);
						setId(0);
					}
				}catch(Exception e){}
				}
			}
			catch(Exception e){
				      errors.add(null,new ActionError("Error.message.msg"));
			    }		
			return errors;
		}
		return null;
	 }
	
	public int checkData(String name_check)
	{
		PreparedStatement checkcon;
		ResultSet rs1;
		int ans=0;
		String nm1 = null;
		connection=null;
		try
		{
			if(connection==null)
				connection=con.getdbConnection();
			try
			{
				checkcon = connection.prepareStatement(ConnectInit.queries.getProperty("check_preference_name"));
				checkcon.setString(1,name_check);
				rs1 = checkcon.executeQuery();
				while(rs1.next())
				{
					nm1 = rs1.getString(1);
					
				}
				rs1.close();
				checkcon.close();
				if(nm1 != null && nm1.trim().length() != 0 && nm1.trim() != "Not Selected")
				{		
				if(nm1.equalsIgnoreCase(name_check))
				{
					ans = 1;
				}
				}
				rs1.close();
				checkcon.close();
			}catch(Exception e)
			{
				Logging.debug("Error check() :"+e);
			}
		}
		finally{
			try{if(connection!=null)
				connection.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
		return ans;
	}	
	
	/**
	 * @return Returns the additem.
	 */
	public String getAdditem() {
		return additem;
	}
	/**
	 * @param additem The additem to set.
	 */
	public void setAdditem(String additem) {
		this.additem = additem;
	}
	/**
	 * @return Returns the update.
	 */
	public String getUpdate() {
		return update;
	}
	/**
	 * @param update The update to set.
	 */
	public void setUpdate(String update) {
		this.update = update;
	}
}