/*
 * Created on Mar 4, 2006
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
 * @author naresh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AddCompanyForm extends ActionForm
{Logger Logging = Logger.getLogger(AddCompanyForm.class);
	private String go=null,clear=null,to=null,defaultVal=null,check=null,checkChart=null,
	selectCompany=null,selectStock=null,text=null;
	private Collection selectCompanyCollection=null,stockCollection=null;
	private Vector vector_highlowtable;
	String tval,tvol;
	private ResultSet rst=null;
	String name_add,list_add,code_add,new1_add,update_add,idname_add;
	Vector ex_actions_add; 
	Connect con =ConnectInit.getConnect();
	Connection connection=null;
	String identifier_name_add ,identifier_desc_add ,ids,id_add_check,name_add_check;
	static int identifier_id_add = 0;
	String cmp_name,add,from,name,class_id,new1,update,company_list,cmp_name_check; 
	String[]class_list;
	int id;
	Vector ex_actions;
	public Vector class_list_vector;
	Connect connect = ConnectInit.getConnect();
	static String con_flag;
	public void reset(ActionMapping mapping,HttpServletRequest request){
	  	
   		cmp_name				=	null;
   	}
	public Collection getSelectCompanyCollection() {
		Vector vec = new Vector();
		vec.add(new LabelValueBean("Not Selected","0"));
		connection=null;
		String comp_id = null;
		String comp_name = null;
		
		try{
			if(connection==null)
				connection=con.getdbConnection();
			try {
				PreparedStatement stmt =connection.prepareStatement(ConnectInit.queries.getProperty("get_company_list"));
				ResultSet rst = stmt.executeQuery();
				while(rst.next()){
					comp_id = rst.getString(1);
					comp_name = rst.getString(2);
					if(comp_name.equals("") || comp_name.length() == 0)
				{
					vec.add(new LabelValueBean("--",comp_id));
				}
				else
				{
					/*id_check = rst.getString(1); 
					name_check = rst.getString(2);*/
					vec.add(new LabelValueBean(comp_name,comp_id ));
				}
				}
				rst.close();
				stmt.close();
				selectCompanyCollection = vec;
				return selectCompanyCollection;
			}catch (SQLException e) {
				Logging.error("Error  :"+e.getMessage());
				Logging.error("ID_Check "+comp_id+" Name_Check "+ comp_name);
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
		return selectCompanyCollection;
	}
	/**
	 * @param selectCollection The selectCollection to set.
	 */
	public void setSelectCompanyCollection(Collection selectCompanyCollection) {
		this.selectCompanyCollection = selectCompanyCollection;
	}
	public String getSelectCompany() {
		connection=null;
		try{
			if(connection==null)
				connection=con.getdbConnection();
				if(defaultVal!=null && defaultVal.equals("yes")){
					try {
						PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("select_system_config"));
						ResultSet rst = stmt.executeQuery();
						while(rst.next()){
							selectCompany=rst.getString(20);
						}
						rst.close();
						stmt.close();
					} catch (SQLException e) {
					//	e.printStackTrace();
						Logging.error(" Error : "+e.getMessage());
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
		return selectCompany;
	}
	/**
	 * @param select The select to set.
	 */
	public void setSelectCompany(String selectCompany) {
		Logging.debug("Inside setSelectIndex selectIndex = "+ selectCompany);
		this.selectCompany = selectCompany;
	}
	public String getCmp_name_check() {
		return cmp_name_check;
	}
	public void setCmp_name_check(String cmp_name_check) {
		this.cmp_name_check = cmp_name_check;
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
		AddCompanyForm.con_flag = con_flag;
	}
	/**
	 * @return Returns the class_list.
	 */
	public String[] getClass_list() {
		return class_list;
	}
	/**
	 * @param class_list The class_list to set.
	 */
	public void setClass_list(String[] class_list) {
		this.class_list = class_list;
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
	 * @return Returns the company_list.
	 */
	public String getCompany_list() {
		return company_list;
	}
	/**
	 * @param company_list The company_list to set.
	 */
	public void setCompany_list(String company_list) {
		this.company_list = company_list;
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
	/**
	 * @return Returns the class_id.
	 */
	public String getClass_id() {
		return class_id;
	}
	/**
	 * @param class_id The class_id to set.
	 */
	public void setClass_id(String class_id) {
		this.class_id = class_id;
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
			setCmp_name(name);
			getCmp_name();
		}
		else
			this.name = name;
	}
	/**
	 * @return Returns the class_list_vector.
	 */
	public Vector getClass_list_vector() 
	{
		return class_list_vector;
	}
	/**
	 * @param class_list_vector The class_list_vector to set.
	 */
	/*public void setClass_list_vector() 
	{
		ResultSet rst;
		class_list_vector = new Vector();
		rst = getList();
		int i = 0;
		try 
		{
			while (rst.next())
			{				
				class_list_vector.add(i, rst.getString(1));
				i++;
				class_list_vector.add(i,(String)rst.getString(2));
				i++;
		    }
			rst.close();
		} catch (SQLException sqlexp)
		  {
			  Logging.getDebug(sqlexp);
		  }
	}*/
	
	/*public ResultSet getList()
	{
		PreparedStatement pst;
		ResultSet rset;
		try
		{
			pst = connection.prepareStatement(connect.queries.getProperty("industry_classification_list"));
			rset = pst.executeQuery();
			rset.close();
			pst.close();
			return rset;
		}catch (Exception e)
		{
			Logging.getDebug("Error :"+e);
		
		return null;
		}
	}*/
	/**
	 * @return Returns the ex_actions.
	 */
	public Vector getEx_actions() {
		setCon_flag(null);
		return ex_actions;
	}
	/**
	 * @param ex_actions The ex_actions to set.
	 */
	
	/**
	 * @return Returns the cmp_name.
	 */
	public String getCmp_name() {
		return cmp_name;
	}
	/**
	 * @param cmp_name The cmp_name to set.
	 */
	public void setCmp_name(String cmp_name) {
		if(cmp_name!=null){
			this.cmp_name = cmp_name.trim();
		}
		else
		this.cmp_name = cmp_name;
	}
	 
	/**
	 * @return Returns the add.
	 */
	public String getAdd() {
		return add;
	}
	/**
	 * @param add The add to set.
	 */
	public void setAdd(String add) {
		this.add = add;
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
	
	
/**	public void setEx_actions() 
	{
		ResultSet rset;
		ex_actions = new Vector();
		rset = get_action_List();
		int i = 0;
		try 
		{
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
			  Logging.getError(sqlexp);
		  }
	}**/
	
/**	public ResultSet get_action_List()
	{
		PreparedStatement pst;
		ResultSet rst;
		if(connection==null)
		{
			connection=con.getdbConnection();
		}
		try
		{
			pst = connection.prepareStatement(connect.queries.getProperty("get_company_list"));
			rst = pst.executeQuery();
			rst.close();
			pst.close();
			return rst;
		}catch (Exception e)
		{
			Logging.getError("Error :"+e);
		} 
		finally{
			try{if(connection!=null)
				connection.close();
			}catch(Exception ee){
				Logging.getError(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
		return null;
	}**/
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
				pst = connection.prepareStatement(ConnectInit.queries.getProperty("get_company_name_id"));
				pst.setInt(1,id_int);
				rst = pst.executeQuery();
				while(rst.next())
				{  
					name = rst.getString(1);
					setName(name);
					setCmp_name(name);
					setCmp_name_check(name);
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
		String newvalue = getNew1();
		String updatevalue = getUpdate();
		Logging.debug("Inside validate.......... ");
		if(newvalue != null)
		{
			ActionErrors errors = new ActionErrors();
			boolean flag=true,flag1=true;
			try{
				int check = checkData(cmp_name);
				if(check == 1)
				{
					errors.add("DuplicateEntry",new ActionError("Error.message.DuplicateEntry"));
					setNew1(null);
				}
			}catch(Exception e){
			errors.add(null,new ActionError("Error.message.msg"));
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
				int idname_t = getId();
				if(idname_t == 0)
				{
					setUpdate(null);
					setId(0);
					errors.add("DuplicateEntry",new ActionError("Error.message.selectfromlist"));			
				}
				if(!getCmp_name().equalsIgnoreCase(getCmp_name_check()))
				{
				try{
					Logging.debug("Comp Name "+cmp_name);
					int check = checkData(cmp_name.trim());
					if(check == 1)
					{
						errors.add("DuplicateEntry",new ActionError("Error.message.DuplicateEntry"));
						setNew1(null);
						setUpdate(null);
						setId(0);
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
				checkcon = connection.prepareStatement(ConnectInit.queries.getProperty("check_company_name"));
				checkcon.setString(1,name_check);
				rs1 = checkcon.executeQuery();
				while(rs1.next())
				{
					nm1 = rs1.getString(1);
				}
				rs1.close();
				checkcon.close();
				if(nm1 != null && nm1.trim().length() != 0)
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
}
