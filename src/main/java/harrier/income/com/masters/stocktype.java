/*
 * Created on Mar 8, 2006
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
public class stocktype extends org.apache.struts.action.ActionForm
{
	Logger Logging = Logger.getLogger(stocktype.class);
	Connect con =ConnectInit.getConnect();
	Connection connection=null;
	private Collection selectStockTypeCollection=null;
	public String selectStockType=null,selectCountry=null,selectContinent=null,defaultVal=null;
	String name,update,list,new1;
	String type_name,from;
	String name_check;
	static String conf_flag;
	int ids,idname;
	static int id_check;
	public Collection getSelectStockTypeCollection() {
		Vector vec = new Vector();
		vec.add(new LabelValueBean("Not Selected","0"));
		connection=null;
		try{
			if(connection==null)
				connection=con.getdbConnection();
			try {
				PreparedStatement stmt =connection.prepareStatement(ConnectInit.queries.getProperty("get_stock_type_list"));
				ResultSet rst = stmt.executeQuery();
				while(rst.next()){
					vec.add(new LabelValueBean(rst.getString(2),rst.getString(1)));
				}
				rst.close();
				stmt.close();
				selectStockTypeCollection = vec;
				return selectStockTypeCollection;
			} catch (SQLException e) {
				Logging.error("Error  :"+e.getMessage());
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
		return selectStockTypeCollection;
	}
	/**
	 * @param selectCollection The selectCollection to set.
	 */
	
	public void setSelectStockTypeCollection(Collection selectStockTypeCollection) {
		this.selectStockTypeCollection = selectStockTypeCollection;
	}
	public String getSelectStockType() {
		connection=null;
		try{
			if(connection==null)
				connection=con.getdbConnection();
			if(defaultVal!=null && defaultVal.equals("yes")){
				try {
					PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("select_system_config"));
					ResultSet rst = stmt.executeQuery();
					while(rst.next()){
						 selectStockType=rst.getString(19);
					}
					rst.close();
					stmt.close();
				} catch (SQLException e) {
					Logging.error("Error  :"+e.getMessage());
				//	e.printStackTrace();
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
		return selectStockType;
	}
	/**
	 * @param select The select to set.
	 */
	public void setSelectStockType(String selectStockType) {
		Logging.debug("Inside setSelectIndex selectIndex = "+ selectStockType);
		this.selectStockType = selectStockType;
	}
	/**
	 * @return Returns the conf_flag.
	 */
	public static String getConf_flag() {
		return conf_flag;
	}
	/**
	 * @param conf_flag The conf_flag to set.
	 */
	public static void setConf_flag(String conf_flag) {
		stocktype.conf_flag = conf_flag;
	}
	public int getId_check() {
		return id_check;
	}
	public void setId_check(int id_check) {
		this.id_check = id_check;
	}
	public String getName_check() {
		return name_check;
	}
	public void setName_check(String name_check) {
		this.name_check = name_check;
	}
	public int getIds() {
		return ids;
	}
	public void setIds(int ids) {
		this.ids = ids;
		
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
	 * @return Returns the idname.
	 */
	public int getIdname() {
		return idname;
	}
	/**
	 * @param idname The idname to set.
	 */
	public void setIdname(int idname) {
		this.idname = idname;
		get_name_desc(idname);
	}
	public String getType_name()
	{
		if(type_name==null)
			type_name = "";
		return type_name;
	}
	/**
	 * @param type_name The type_name to set.
	 */
	public void setType_name(String type_name) {
		
		this.type_name = type_name;
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
		this.name = name;
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
	Vector ex_actions;
	
	/**
	 * @return Returns the ex_actions.
	 */
	public Vector getEx_actions() {
		stocktype.setConf_flag(null);
		return ex_actions;
	}
	/**
	 * @param ex_actions The ex_actions to set.
	 */
	/*public void setEx_actions() 
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

	}*/
	/*public ResultSet get_action_List()
	{
		PreparedStatement pst;
		ResultSet rst;
		connection=null;
		try
		{
			if(connection==null)
				connection=con.getdbConnection();
			try
			{
				pst = connection.prepareStatement(con.queries.getProperty("get_stock_type_list"));
				rst = pst.executeQuery();
				rst.close();
				pst.close();
				return rst;
			}catch (Exception e)
			{
				Logging.getError("Error :"+e);
			}
		}
		finally{
			try{if(connection!=null)
				connection.close();
			}catch(Exception ee){
				Logging.getError(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
		return null;
	}*/
	
	public void get_name_desc(int id)
	{
		PreparedStatement pst;
		ResultSet rst;
		ids = id;
		int idt_code = id;
		connection=null;
		try
		{
			if(connection==null)
				connection=con.getdbConnection();
			try
			{
				String Query1 ="select stock_type_name "+
								"from stock_type " +
								"where stock_type_id = "+idt_code+"";
				pst = connection.prepareStatement(ConnectInit.queries.getProperty("get_stock_type_desc"));
				pst.setInt(1,idt_code);
				rst = pst.executeQuery();
				while(rst.next())
				{
					type_name = rst.getString(1);
					setName(type_name);
					setType_name(type_name);
					setName_check(type_name);
					id_check= rst.getInt(2);
					setId_check(id_check);
				}
				rst.close();
				pst.close();
		    }catch (Exception e)
			{
				Logging.error("Error :"+e);
			}
		 }finally{
		 	try{if(connection!=null)
		 		connection.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		 }
	}
	/**
	 * Data is validated for duplicated rating code names and 
	 * if user has tried to update a record without selecting it
	 * from the list.
	 */
	public ActionErrors validate(ActionMapping mapping,HttpServletRequest request)
	 {
		String newvalue = getNew1();
		StockTypeAction act = new StockTypeAction();
		String updatevalue = getUpdate();
		if(newvalue != null)
		{
			ActionErrors errors = new ActionErrors();
			boolean flag=true,flag1=true;
			try
			{
				int check = act.checkData(name,"N");
				if(check == 1)
				{
					errors.add("DuplicateEntry",new ActionError("Error.message.DuplicateEntry"));			
				}
			}catch(Exception e)
			{
				errors.add(null,new ActionError("Error.message.msg"));
				Logging.error("Error in Validation "+e.getMessage());			
			}	
		return errors;
		}
		if(updatevalue != null)
		{
			ActionErrors errors = new ActionErrors();
			try
			{
				int id_val = 0;
				id_val = stocktype.id_check;
				if(id_val==0)
				{
					setUpdate(null);
					setNew1(null);
					errors.add("DuplicateEntry",new ActionError("Error.message.selectfromlist"));			
				}
				if(!getName().equalsIgnoreCase(getName_check()))
				{
				try{
					int check = act.checkData(getName(),"U");
					if(check != 0 && check!=getId_check())
					{
						setNew1(null);
						setUpdate(null);
						errors.add("DuplicateEntry",new ActionError("Error.message.DuplicateEntry"));			
					}
				}catch(Exception e){}
				}
			}catch(Exception e)
			{
				errors.add(null,new ActionError("Error.message.msg"));
			}	
		return errors;
		}
		return null;
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
}
