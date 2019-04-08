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
public class countries extends org.apache.struts.action.ActionForm
{
	Logger Logging = Logger.getLogger(countries.class);
	Vector ex_actions,cont_list,curr_list;
	String identifier_name ,name_check;
	Connect con =ConnectInit.getConnect();
	Connection connection=null;
	private Collection selectCurrencyCollection=null;
	private Collection selectCountryCollection=null;
	private Collection selectContinentCollection=null;
	String name1,shname,list,Continent,cont_list_name,curr_list_name;
	String update,new1,identifier_desc;
	static String con_flag;
	int idname,currency,continent;
	static int cont_list_value,curr_list_value,idname_check;
	static int id_flag;
	static int country_id;
	public String selectCurrency=null,selectCountry=null,selectContinent=null,defaultVal=null;
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
				Logging.error(" Error : "+e.getMessage());
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
					Logging.error(" Error : "+e.getMessage());
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
		return selectCurrency;
	}
	/**
	 * @param select The select to set.
	 */
	public void setSelectCurrency(String selectCurrency) {
		Logging.debug("Inside setSelectIndex selectIndex = "+ selectCurrency);
		this.selectCurrency = selectCurrency;
	}
	public Collection getSelectCountryCollection() {
		Vector vec = new Vector();
		vec.add(new LabelValueBean("Not Selected","0"));
		connection=null;
		try{
			if(connection==null)
				connection=con.getdbConnection();
			try {
				PreparedStatement stmt =connection.prepareStatement(ConnectInit.queries.getProperty("get_country_list"));
				ResultSet rst = stmt.executeQuery();
				while(rst.next()){
				vec.add(new LabelValueBean(rst.getString(2),rst.getString(1)));
				}
				rst.close();
				stmt.close();
				selectCountryCollection = vec;
				return selectCountryCollection;
			} catch (SQLException e) {
				Logging.error(" Error : "+e.getMessage());
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
		return selectCountryCollection;
	}
	/**
	 * @param selectCollection The selectCollection to set.
	 */
	
	public void setSelectCountryCollection(Collection selectCountryCollection) {
		this.selectCountryCollection = selectCountryCollection;
	}
	public String getSelectCountry() {
		connection=null;
		try{
			if(connection==null)
				connection=con.getdbConnection();
			if(defaultVal!=null && defaultVal.equals("yes")){
			try {
					PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("select_system_config"));
					ResultSet rst = stmt.executeQuery();
					while(rst.next()){
						 selectCountry=rst.getString(19);
					}
					rst.close();
					stmt.close();
				} catch (SQLException e) {
					Logging.error(" Error : "+e.getMessage());
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
		return selectCountry;
	}
	/**
	 * @param select The select to set.
	 */
	public void setSelectCountry(String selectCountry) {
		Logging.debug("Inside setSelectCountry selectCountry = "+ selectCountry);
		this.selectCountry = selectCountry;
	}
	
	
	public Collection getSelectContinentCollection() {
		Vector vec = new Vector();
		vec.add(new LabelValueBean("Not Selected","0"));
		connection=null;
		try{
			if(connection==null)
				connection=con.getdbConnection();
			try {
				PreparedStatement stmt =connection.prepareStatement(ConnectInit.queries.getProperty("get_continents_list"));
				ResultSet rst = stmt.executeQuery();
				while(rst.next()){
				vec.add(new LabelValueBean(rst.getString(2),rst.getString(1)));
				}
				rst.close();
				stmt.close();
				selectContinentCollection = vec;
				return selectContinentCollection;
			} catch (SQLException e) {
				Logging.error(" Error : "+e.getMessage());
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
		return selectContinentCollection;
	}
	/**
	 * @param selectCollection The selectCollection to set.
	 */
	
	public void setSelectContinentCollection(Collection selectContinentCollection) {
		this.selectContinentCollection = selectContinentCollection;
	}
	public String getSelectContinent() {
		connection=null;
		try{
			if(connection==null)
				connection=con.getdbConnection();
			if(defaultVal!=null && defaultVal.equals("yes")){
			try {
					PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("select_system_config"));
					ResultSet rst = stmt.executeQuery();
					while(rst.next()){
						 selectContinent=rst.getString(19);
					}
					rst.close();
					stmt.close();
				} catch (SQLException e) {
					Logging.error(" Error : "+e.getMessage());
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
		return selectContinent;
	}
	/**
	 * @param select The select to set.
	 */
	public void setSelectContinent(String selectContinent) {
		Logging.debug("Inside setSelectIndex selectIndex = "+ selectContinent);
		this.selectContinent = selectContinent;
	}
	public int getIdname_check() {
		return idname_check;
	}
	public void setIdname_check(int idname_check) {
		countries.idname_check = idname_check;
	}
	public String getName_check() {
		return name_check;
	}
	public void setName_check(String name_check) {
		this.name_check = name_check;
	}
	public int getCountry_id() {
		return country_id;
	}
	public void setCountry_id(int country_id) {
		countries.country_id = country_id;
	}
	public String getIdentifier_desc() {
		return identifier_desc;
	}
	public void setIdentifier_desc(String identifier_desc) {
		if(identifier_desc!=null){
			this.identifier_desc = identifier_desc.trim();
		}
		else
			this.identifier_desc = identifier_desc;
	}
	public String getIdentifier_name() {
		return identifier_name;
	}
	public void setIdentifier_name(String identifier_name) {
		this.identifier_name = identifier_name;
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
		countries.con_flag = con_flag;
	}
	/**
	 * @return Returns the id_flag.
	 */
	public static int getId_flag() {
		return id_flag;
	}
	/**
	 * @param id_flag The id_flag to set.
	 */
	public static void setId_flag(int id_flag) {
		countries.id_flag = id_flag;
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
	 * @return Returns the idname.
	 */
	public int getIdname() {
		return idname;
	}
	/** 
	 * @param idname The idname to set.
	 */
	public void setIdname(int idname) {
		Logging.debug("set idname>....:"+idname);
		setId_flag(idname);
		this.idname = idname;
		get_name_desc(idname);
	}
	/**
	 * @return Returns the cont_list_value.
	 */
	public int getCont_list_value() {
		return cont_list_value;
	}
	/**
	 * @param cont_list_value The cont_list_value to set.
	 */
	public void setCont_list_value(int cont_list_value) {
		countries.cont_list_value = cont_list_value;
	}
	/**
	 * @return Returns the curr_list_value.
	 */ 
	public int getCurr_list_value() {
		return curr_list_value;
	}
	/**
	 * @param curr_list_value The curr_list_value to set.
	 */
	public void setCurr_list_value(int curr_list_value) {
		countries.curr_list_value = curr_list_value;
	}
	/**
	 * @return Returns the continent.
	 */
	public int getContinent() {
		return continent;
	}
	/**
	 * @param continent The continent to set.
	 */
	public void setContinent(int continent) {
		this.continent = continent;
	}
	/**
	 * @return Returns the currency.
	 */
	public int getCurrency() {
		return currency;
	}
	/**
	 * @param currency The currency to set.
	 */
	public void setCurrency(int currency) {
		this.currency = currency;
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
	public String getName1() {
		return name1;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName1(String name1) {
		if(name1!=null){
			this.name1 = name1.trim();
		}
		else
			this.name1 = name1;
	}
	/**
	 * @return Returns the shname.
	 */
	public String getShname() {
		return shname;
	}
	/**
	 * @param shname The shname to set.
	 */
	public void setShname(String shname) {
		if(shname!=null){
			this.shname = shname.trim();
		}
		else
			this.shname = shname;
	}
	
	/**
	 * @return Returns the ex_actions.
	 */
	public Vector getEx_actions() {
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
		setCon_flag(null);
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
	
	/**public ResultSet get_action_List()
	{
		PreparedStatement pst;
		ResultSet rst;
		connection=null;
		if(connection==null)
		{	
			connection=con.getdbConnection();
		}
		try
		{  
			pst = connection.prepareStatement(con.queries.getProperty("get_country_list"));
			rst = pst.executeQuery();
			rst.close();
			pst.close();
			return rst;
		}catch (Exception e)
		{
			Logging.getError("Error in get_list:"+e);
			return null;
		}
		finally{
			try{if(connection!=null)
				connection.close();
			}catch(Exception ee){
				Logging.getError(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
	}**/
	
	public void get_name_desc(int id)
	{
		Logging.debug("idname     "+id);
		PreparedStatement pst;
		ResultSet rst;
		country_id = id;
		int idt_code = id; 
		connection=null;
		if(connection==null)
		{	
			connection=con.getdbConnection();
		}
		try  
		{
			pst = connection.prepareStatement(ConnectInit.queries.getProperty("get_country_desc"));
			pst.setInt(1,idt_code);
			rst = pst.executeQuery();
			while(rst.next())
			{
				identifier_name = rst.getString(1);
				setIdentifier_name(identifier_name);
				setName1(identifier_name);
				setName_check(identifier_name);
				identifier_desc = rst.getString(2);
				setIdentifier_desc(identifier_desc);
				setShname(identifier_desc);
				cont_list_value = rst.getInt(3);
				setCont_list_value(cont_list_value);
				cont_list_name=""+cont_list_value;
				setContinent(cont_list_value);
				setSelectContinent(cont_list_name);
				curr_list_value = rst.getInt(4);
				setCurr_list_value(curr_list_value);
				curr_list_name=""+curr_list_value;
				setCurrency(curr_list_value);
				setSelectCurrency(curr_list_name);
				idname_check=rst.getInt(5);
				setIdname_check(idname_check);
				setCountry_id(idname_check);
			}
			rst.close();
			pst.close();
		}catch (Exception e)
		{
			Logging.error("Error get_desc:"+e);
		}
		finally{
			try{if(connection!=null)
				connection.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
	}
	
	public Vector getCont_list() {
		
		return cont_list;
	}
	/**
	 * @param cont_list The cont_list to set.
	 */
/**	public void setCont_list() 
	{
		ResultSet rst;
		app.Connect con=new app.Connect();
		connection=null;
		if(connection==null)
		{	
			connection=con.getdbConnection();
		}
		cont_list = new Vector();
		rst = getCont_List();
		int i = 0;
		try 
		{
			while (rst.next())
			{				
				cont_list.add(i, rst.getString(1));
				i++;
				cont_list.add(i,(String)rst.getString(2));
				i++;
		    }
			rst.close();
		} catch (SQLException sqlexp)
		  {
			  Logging.getError(sqlexp);
		  }
		finally{
			try{if(connection!=null)
				connection.close();
			}catch(Exception ee){
				Logging.getError(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
	}**/
	
/**	public ResultSet getCont_List()
	{
		PreparedStatement pst;
		ResultSet rset;
		try
		{
			pst = connection.prepareStatement(con.queries.getProperty("get_continents_list"));
			rset = pst.executeQuery();
			rset.close();
			pst.close();
			return rset;
		}catch (Exception e)
		{
			Logging.getError("Error getCont_list:"+e);
		}
		return null;
	}**/
	
	public Vector getCurr_list() {
		return curr_list;
	}
	/**
	 * @param curr_list The curr_list to set.
	 */
	/**public void setCurr_list() 
	{
		ResultSet rst;
		app.Connect con=new app.Connect();
		if(Connect.con==null)
		{
			con.getConnection();
		}
		curr_list = new Vector();
		rst = getCourrencyList();
		int i = 0;
		try 
		{
			while (rst.next())
			{				
				curr_list.add(i, rst.getString(1));
				i++;
				curr_list.add(i,(String)rst.getString(2));
				i++;
		    }
			rst.close();
		} catch (SQLException sqlexp)
		  {
			  Logging.getError(sqlexp);
		  }
		finally{
			try{if(connection!=null)
				connection.close();
			}catch(Exception ee){
				Logging.getError(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
	}**/
	
	/**public ResultSet getCourrencyList()
	{
		PreparedStatement pst;
		ResultSet rset;
		try
		{
			pst = Connect.con.prepareStatement(con.queries.getProperty("addcurrency_list"));
			rset = pst.executeQuery();
			rset.close();
			pst.close();
			return rset;
		}catch (Exception e)
		{
			Logging.getError("Error get_Curr_list:"+e);
		}
		return null;
	}**/
	
	public ActionErrors validate(ActionMapping mapping,HttpServletRequest request)
	 {
		String newvalue = getNew1();
		String updatevalue = getUpdate();
		/**
		 * check if user wants to update data.
		 * If yes then call update funtion else check if user wants to add new element.
		 * after update or add reset bean variables.
		 */
		if(newvalue != null)
		{
			
			ActionErrors errors = new ActionErrors();
			boolean flag=true,flag1=true;
			CountriesAction act = new CountriesAction();
			
			if(selectContinent == null || selectContinent.trim().equals("")||selectContinent.trim().equals("0") )//|| s_indexName.charAt(0)==' ')
			{
				errors.add("selectContinent",new ActionError("Error.message.Continent"));			
				setNew1(null); 
			}
			if(selectCurrency == null || selectCurrency.trim().equals("")||selectCurrency.trim().equals("0") )//|| s_indexName.charAt(0)==' ')
			{
				errors.add("selectCurrency",new ActionError("Error.message.s_stockCurrency"));			
				setNew1(null);
			}
			try{
				int check = act.checkData(name1);
				if(check == 1)
				{
					setNew1(null);
					setIdname(0);
					errors.add("DuplicateEntry",new ActionError("Error.message.DuplicateEntry"));			
				}
			}catch(Exception e){
				errors.add(null,new ActionError("Error.message.msg"));
			}		
			return errors;
		}
		/**
		 * check if user wants to update data.
		 * If yes then call update funtion else check if user wants to add new element.
		 * after update or add reset bean variables.
		 */
		if(updatevalue != null)
		{
			
			ActionErrors errors = new ActionErrors();
			boolean flag=true,flag1=true;
			CountriesAction act = new CountriesAction();
			
			if(selectContinent == null || selectContinent.trim().equals("")||selectContinent.trim().equals("0") )//|| s_indexName.charAt(0)==' ')
			{
				errors.add("selectContinent",new ActionError("Error.message.Continent"));			
				setNew1(null); 
				setUpdate(null);
			}
			if(selectCurrency == null || selectCurrency.trim().equals("")||selectCurrency.trim().equals("0") )//|| s_indexName.charAt(0)==' ')
			{
				errors.add("selectCurrency",new ActionError("Error.message.s_stockCurrency"));			
				setNew1(null);
				setUpdate(null);
			}
			
			try{
				int idname_t =CountriesAction.ids;
				if(idname_t == 0)
				{
					setUpdate(null);
					setIdname_check(0);
					errors.add("DuplicateEntry",new ActionError("Error.message.selectfromlist"));			
				}
				if(!getName1().equalsIgnoreCase(getName_check()))
				{
				try{
					int check = act.checkData(name1);
							//getName1().trim());
					if(check == 1)
					{
						errors.add("DuplicateEntry",new ActionError("Error.message.DuplicateEntry"));
						setNew1(null);
						setUpdate(null);
						setIdname_check(0);
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
	/**
	 * function to check duplicate entries in the table.
	 * funtion is called only when new data is inserted.
	 */
	/*public int checkData(String name)
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
			checkcon =connection.prepareStatement(con.queries.getProperty("check_country"));
			checkcon.setString(1,name);
			rs1 = checkcon.executeQuery();
			while(rs1.next())
			{
				nm1 = rs1.getString(1);
		}
			if(nm1.equalsIgnoreCase(name))
			{
				ans = 1;
			}
			rs1.close();
			checkcon.close();
		}catch(Exception e)
		{
			Logging.getError("Error check() :"+e);
		}
		}catch(Exception e){
			Logging.getError(" Error : "+e.getMessage());
		}
		finally{
			try{
				if(connection!=null)
					connection.close();
			}catch(Exception ee){
				Logging.getError(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
		return ans;
	}*/
}

