/*
 * Created on Dec 28, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.masters;
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
 * @author Naresh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AddCurrency extends ActionForm 
{Logger Logging = Logger.getLogger(AddCurrency.class);
	private String from=null,clear=null,to=null,defaultVal=null,check=null,
	selectCurrency=null;
	private Collection selectCurrencyCollection=null;
	String tval,tvol;
	String name_add,list_add,code_add,new1_add,update_add;
	Vector ex_actions_add; 
	Connect con =ConnectInit.getConnect();
	Connection connection=null;
	String identifier_name_add ,identifier_desc_add ,name_add_check;
	static int ids;
	int id_add_check,idname_add;
	static String con_flag;
	static int identifier_id_add;
	public Collection getSelectCurrencyCollection() {
		Vector vec = new Vector();
		vec.add(new LabelValueBean("Not Selected","0"));
		connection=null;
		try{
			if(connection==null)
				connection=con.getdbConnection();
			try {
				PreparedStatement stmt =connection.prepareStatement(ConnectInit.queries.getProperty("addcurrency_list"));
				ResultSet rst = stmt.executeQuery();
				while(rst.next()){
				vec.add(new LabelValueBean(rst.getString(2),rst.getString(1)));
				}
				rst.close();
				stmt.close();
				selectCurrencyCollection = vec;
				return selectCurrencyCollection;
			} catch (SQLException e) {
			//	e.printStackTrace();
				Logging.debug(e);
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
		return selectCurrencyCollection;
	}
	/**
	 * @param selectCollection The selectCollection to set.
	 */

	public void setSelectCurrencyCollection(Collection selectCurrencyCollection) {
		this.selectCurrencyCollection = selectCurrencyCollection;
	}
	public String getSelectCurrency() {
		connection=null;
		try{
			if(connection==null)
				connection=con.getdbConnection();
				if(defaultVal!=null && defaultVal.equals("yes")){
					try {
						PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("select_system_config"));
						ResultSet rst = stmt.executeQuery();
						while(rst.next()){
							selectCurrency=rst.getString(19);
						}
						rst.close();
						stmt.close();
								
					} catch (SQLException e) {
						Logging.error("Error  :"+e.getMessage());
				//		e.printStackTrace();
			   }
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
		return selectCurrency;
	}
	/**
	 * @param select The select to set.
	 */
	public void setSelectCurrency(String selectCurrency) {
		Logging.debug("Inside setSelectIndex selectIndex = "+ selectCurrency);
		this.selectCurrency = selectCurrency;
	}
	public int getId_add_check() {
		return id_add_check;
	}
	public void setId_add_check(int id_add_check) {
		this.id_add_check = id_add_check;
	}
	public String getIdentifier_desc_add() {
		return identifier_desc_add;
	}
	public void setIdentifier_desc_add(String identifier_desc_add) {
		this.identifier_desc_add = identifier_desc_add;
	}
	public String getIdentifier_name_add() {
		return identifier_name_add;
	}
	public void setIdentifier_name_add(String identifier_name_add) {
		if(identifier_name_add!=null){
			this.identifier_name_add = identifier_name_add.trim();
		}
		else
			this.identifier_name_add = identifier_name_add;
	}
	public int getIds() {
		return ids;
	}
	public void setIds(int ids) {
		AddCurrency.ids = ids;
	}
	public String getName_add_check() {
		return name_add_check;
	}
	public void setName_add_check(String name_add_check) {
		this.name_add_check = name_add_check;
	}
	/**
	 * @return Returns the con_flag.
	 */
	public static String getCon_flag() {
		return con_flag;
	}
	/**
	 * @param con_flag The con_flag to set.
	 */
	public static void setCon_flag(String con_flag) {
		AddCurrency.con_flag = con_flag;
	}
	/**
	 * @return Returns the name_add.
	 */
	public String getName_add() {
		return name_add;
	}
	/**
	 * @param name_add The name_add to set.
	 */
	public void setName_add(String name_add) {
		if(name_add!=null ){
			this.name_add = name_add.trim();
		}
		else
			this.name_add = name_add;
	}
	
	
	/**
	 * @return Returns the idname.
	 */
	public int getIdname_add() {
		return idname_add;
	}
	/**
	 * @param idname The idname to set.
	 */
	public void setIdname_add(int idname_add) {
		this.idname_add = idname_add;
			get_name_desc_add(idname_add);
	}
	
	
	/**
	 * @return Returns the identifier_id.
	 */
	public static int getIdentifier_id_add() {
		return identifier_id_add;
	}
	/**
	 * @param identifier_id The identifier_id to set.
	 */
	public static void setIdentifier_id_add(int identifier_id_add) {
		AddCurrency.identifier_id_add = identifier_id_add;
	}
	/**
	 * @return Returns the new1.
	 */
	public String getNew1_add() {
		return new1_add;
	}
	/**
	 * @param new1 The new1 to set.
	 */
	public void setNew1_add(String new1_add) {
		this.new1_add = new1_add;
	}
	/**
	 * @return Returns the update.
	 */
	public String getUpdate_add() {
		return update_add;
	}
	/**
	 * @param update The update to set.
	 */
	public void setUpdate_add(String update_add) {
		this.update_add = update_add;
	}
	/**
	 * @return Returns the ex_actions.
	 */
	public Vector getEx_actions_add() {
		setCon_flag(null);
		return ex_actions_add;
	}
	/**
	 * @param ex_actions The ex_actions to set.
	 */
	/*public void setEx_actions_add() 
	{
		ResultSet rset;
		ex_actions_add = new Vector();
		rset = get_action_List_add();
		int i = 0;
		try 
		{
			while (rset.next())
			{				
				ex_actions_add.add(i, rset.getString(1));
				i++;
				ex_actions_add.add(i,(String)rset.getString(2));
				i++;
		    }   
		} catch (Exception sqlexp)
		  {
			  Logging.getError("error......"+sqlexp);
		  }
	}*/
	/*public ResultSet get_action_List_add()
	{
		PreparedStatement pst;
		ResultSet rst;
		try  
		{
			pst = Connect.con.prepareStatement(con.queries.getProperty("addcurrency_list"));
			rst = pst.executeQuery();
			rst.close();
			pst.close();
			return rst;
		}catch (Exception e)
		{
			Logging.getError("Error :"+e);
		}
		return null;
	}*/
	/**
	 * @return Returns the code.
	 */
	public String getCode_add() {
		return code_add;
	}
	/**
	 * @param code The code to set.
	 */
	public void setCode_add(String code_add) {
		if(code_add!=null){
			this.code_add = code_add.trim();
		}
		else
			this.code_add = code_add;
	}
	/**
	 * @return Returns the list.
	 */
	public String getList_add() {
		return list_add;
	}
	/**
	 * @param list The list to set.
	 */
	public void setList_add() {
		
	}
	
	public void get_name_desc_add(int id) 
	{
		ids = id;
		PreparedStatement pst;
		ResultSet rst;
		name_add = ""+id;
		int idt_code = id;
		connection=null;
		try{
			if(connection==null)
				connection=con.getdbConnection();	
		try
		{
			pst = connection.prepareStatement(ConnectInit.queries.getProperty("get_addcurrency_desc"));
			pst.setInt(1,idt_code);
			rst = pst.executeQuery();
			while(rst.next())
			{
				identifier_id_add = rst.getInt(1);
				setIdentifier_id_add(identifier_id_add);
				setIds(identifier_id_add);
				setId_add_check(identifier_id_add);
				identifier_name_add = rst.getString(2);
				setIdentifier_name_add(identifier_name_add);
				setSelectCurrency(identifier_name_add);
				setName_add(identifier_name_add);
				setName_add_check(identifier_name_add);
				identifier_desc_add = rst.getString(3);
				setIdentifier_desc_add(identifier_desc_add);
				setCode_add(identifier_desc_add);
			}
			rst.close();
			pst.close();
		    Logging.debug("idname     "+identifier_name_add);
		}catch (Exception e)
		{
			Logging.error("Error :"+e);
		}
		}catch(Exception e){
			Logging.error(" Error : "+e.getMessage());
		}
		finally{
			try{
				if(connection!=null)
					connection.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
	}
	public ActionErrors validate(ActionMapping mapping,HttpServletRequest request)
	 {
		String newvalue = getNew1_add();
		String updatevalue = getUpdate_add();
		if(newvalue != null)
		{
			ActionErrors errors = new ActionErrors();
			try
			{
				int check = checkData(name_add);
				if(check == 1)
				{
					setNew1_add(null);
					setId_add_check(0);
					setUpdate_add(null);
					errors.add("DuplicateEntry",new ActionError("Error.message.DuplicateEntry"));			
				}
			}catch(Exception e)
			{
				errors.add(null,new ActionError("Error.message.msg"));
				Logging.error("Error in Validation ");			
			}	
		return errors;
		}
		if(updatevalue != null)
		{
			Logging.debug("Error in Validation U..... "+updatevalue);
			ActionErrors errors = new ActionErrors();
			boolean flag=true,flag1=true;
			CountriesAction act = new CountriesAction();
			try{
				//int idname_t = getIds();
				int idname_t = identifier_id_add;
				if(idname_t == 0)
				{
					setUpdate_add(null);
					setIds(0);
					errors.add("DuplicateEntry",new ActionError("Error.message.selectfromlist"));			
				}
				if(!getName_add().equalsIgnoreCase(getName_add_check()))
				{
				try{
					int check = checkData(getName_add().trim());
					if(check == 1)
					{
						errors.add("DuplicateEntry",new ActionError("Error.message.DuplicateEntry"));
						setNew1_add(null);
						setUpdate_add(null);
						setIds(0);
					}
				}catch(Exception e){}
				}
			}catch(Exception e){
				errors.add(null,new ActionError("Error.message.msg"));
			 }		
			return errors;
		}
		return null;
	 }
	
	public int checkData(String name)
	{
		PreparedStatement checkcon;
		ResultSet rs1;
		int ans=0;
		String nm1 = null;
		connection=null;
		try{
			if(connection==null)
				connection=con.getdbConnection();	
		try
		{
			checkcon =connection.prepareStatement(ConnectInit.queries.getProperty("check_addcurrency"));
			checkcon.setString(1,name);
			rs1 = checkcon.executeQuery();
			while(rs1.next()){
				nm1 = rs1.getString(1);
		    }
		if(nm1 != null && nm1.trim().length() != 0){
			if(nm1.equalsIgnoreCase(name))
			{
				ans = 1;
			}
			checkcon.close();
			rs1.close();
		}	
		}catch(Exception e)
		{
			Logging.error("Error check() :"+e);
		}
		}catch(Exception e){
			Logging.error(" Error : "+e.getMessage());
		}
		finally{
			try{
				if(connection!=null)
					connection.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
		return ans;
	}
}
