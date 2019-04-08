/*
 * Created on Jan 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.masters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import app.Connect;

import com.harrier.initializeation.ConnectInit;

/**
 * @author rahul
 *
 * Form class fro continents master.
 */
public class Continents extends ActionForm 
{Logger Logging = Logger.getLogger(Continents.class);
	String name,idname,list,new1,update;
	Vector ex_actions;
	String cont_name,idform1;
	//Connection con=null;
	Connect connect = ConnectInit.getConnect();
	String ids,name_check,id_check;
	static String con_flag;
	public static String getCon_flag() {
		return con_flag;
	}
	public static void setCon_flag(String con_flag) {
		Continents.con_flag = con_flag;
	}
	public String getId_check() {
		return id_check;
	}
	public void setId_check(String id_check) {
		this.id_check = id_check;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getName_check() {
		return name_check;
	}
	public void setName_check(String name_check) {
		this.name_check = name_check;
	}
	/**
	 * @return Returns the idform1.
	 */
	public String getIdform1() {
		return idform1;
	}
	/**
	 * @param idform1 The idform1 to set.
	 */
	public void setIdform1(String idform1) {
		this.idform1 = idform1;
	}
	
	/**
	 * @return Returns the cont_name.
	 */
	public String getCont_name() {
		return cont_name;
	}
	/**
	 * @param cont_name The cont_name to set.
	 */
	public void setCont_name(String cont_name) {
		this.cont_name = cont_name;
	}
	/**
	 * @return Returns the idname.
	 */
	public String getIdname() {
		return idname;
	}
	/**
	 * @param idname The idname to set.
	 */
	public void setIdname(String idname) {
		this.idname = idname;
	}
	/**
	 * @return Returns the list.
	 */
	public String getList() {
		return list;
	}
	/**
	 * @param list The list to set.
	 */
	public void setList(String list) {
		this.list = list;
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
		}
		else
		this.name = name;
	}
	/**
	 * @return Returns the new1.
	 */
	public String getNew1() {
		return new1;
	}
	/**
	 * @param new1 The new1 to set.
	 */
	public void setNew1(String new1) {
		this.new1 = new1;
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
	public Vector getEx_actions() {
		Continents.setCon_flag(null);
		return ex_actions;
	}
	public void setEx_actions() 
	{
		Connection connection = null;
		ResultSet rset;
		ex_actions = new Vector();
		
		int i = 0;
		try 
		{  if(connection == null){
			connection = connect.getdbConnection();
		     }
		    rset = get_action_List(connection);
			while (rset.next())
			{				
				ex_actions.add(i, rset.getString(1));
				i++;
				ex_actions.add(i,(String)rset.getString(2));
				i++;
		    }
			rset.close();
			
		} catch (Exception sqlexp)
		  {
			  Logging.error(sqlexp+"");
		  }finally{
			try{if(connection!=null)
				connection.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
	}
	
	public ResultSet get_action_List(Connection connection)
	{
		//Connection connection = null;
		PreparedStatement pst;
		ResultSet rst;
		
		try
		{
			pst = connection.prepareStatement(ConnectInit.queries.getProperty("get_continents_list"));
			rst = pst.executeQuery();
			return rst;
		}catch (Exception e)
		{
			Logging.error("Error :"+e);
		}
		return null;
	}
	public void get_name_desc(String id)
	{
		Connection connection = null;
		PreparedStatement pst;
		ResultSet rst;
		ids = id;
		int idt_code = Integer.parseInt(id);
		
		try
		{   
			if(connection == null){
			connection = connect.getdbConnection();
		    }
			pst = connection.prepareStatement(ConnectInit.queries.getProperty("get_continents_desc"));
			pst.setInt(1,idt_code);
			rst = pst.executeQuery();
			while(rst.next())
			{
				cont_name = rst.getString(1);
			}
			rst.close();
			pst.close();
	}catch (Exception e)
		{
		Logging.error("Error getdesc :"+e);
		}finally{
			try{if(connection!=null)
				connection.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
		
	}
	
	public ActionErrors validate(ActionMapping mapping,HttpServletRequest request)
	 {
		String newvalue = getNew1();
		String updatevalue = getUpdate();
		ContinentsAction act = new ContinentsAction();
		if(newvalue != null)
		{
		
		ActionErrors errors = new ActionErrors();
		boolean flag=true,flag1=true;
		
		try{
			int check = act.checkData(name);
			if(check == 1)
			{
				setNew1(null);
				errors.add("DuplicateEntry",new ActionError("Error.message.DuplicateEntry"));			
			}
		}catch(Exception e){
			errors.add(null,new ActionError("Error.message.msg"));
		}	
		return errors;
		} 
		if(updatevalue != null)
		{  
			ActionErrors errors = new ActionErrors();
			try{
				String idname_t = getId_check();
				if(idname_t.trim().equals(""))
				{
					setUpdate(null);
					setIdform1(null);
					errors.add("DuplicateEntry",new ActionError("Error.message.selectfromlist"));		
					return errors;
				}
				if(!getName().equalsIgnoreCase(getName_check()))
				{
				try{
					int check = act.checkData(getName().trim());
					if(check == 1)
					{
						setNew1(null);
						setUpdate(null);
						errors.add("DuplicateEntry",new ActionError("Error.message.DuplicateEntry"));			
					}
				}catch(Exception e){}
				}
			}catch(Exception e){
				errors.add(null,new ActionError("Error.message.msg"));
				Logging.error("Error in Validation ");			
			}		
			return errors;
		}
		return null;
	 }
}
