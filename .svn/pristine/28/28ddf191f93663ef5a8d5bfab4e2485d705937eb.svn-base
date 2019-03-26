/*
 * Created on Jan 8, 2006
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
public class CorporateActionMaster extends ActionForm
{Logger Logging = Logger.getLogger(CorporateActionMaster.class);
	String name,shname,event_index,event_stock,update,new1,desc,list,divisor,price;
	String name_h,type_h,shname_h,desc_h,id,divisor_h,price_h;
	Vector ex_actions;
	private Collection selectCACollection=null;
	public String selectCA=null,selectCountry=null,selectContinent=null,defaultVal=null;
	static Vector action_list;
	Connect con =ConnectInit.getConnect();
	Connection connection=null;
	ResultSet rset;
	public Collection getSelectCACollection() {
		Vector vec = new Vector();
		vec.add(new LabelValueBean("Not Selected","0"));
		connection=null;
		try{
			if(connection==null)
				connection=con.getdbConnection();
			try {
				PreparedStatement stmt =connection.prepareStatement(ConnectInit.queries.getProperty("corp_action_master_list"));
				ResultSet rst = stmt.executeQuery();
				while(rst.next()){
					vec.add(new LabelValueBean(rst.getString(2),rst.getString(1)));
				}
				rst.close();
				stmt.close();
				selectCACollection = vec;
				return selectCACollection;
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
		return selectCACollection;
	}
	/**
	 * @param selectCollection The selectCollection to set.
	 */
	
	public void setSelectCACollection(Collection selectCACollection) {
		this.selectCACollection = selectCACollection;
	}
	public String getSelectCA() {
		connection=null;
		try{
			if(connection==null)
				connection=con.getdbConnection();
			if(defaultVal!=null && defaultVal.equals("yes")){
				try {
					PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("select_system_config"));
					ResultSet rst = stmt.executeQuery();
					while(rst.next()){
						 selectCA=rst.getString(19);
					}
					rst.close();
					stmt.close();
				 } catch (SQLException e) {
				 	Logging.error("Error  :"+e.getMessage());
				// 	e.printStackTrace();
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
		return selectCA;
	}
	/**
	 * @param select The select to set.
	 */
	public void setSelectCA(String selectCA) {
		Logging.debug("Inside setSelectIndex selectIndex = "+ selectCA);
		this.selectCA = selectCA;
	}
	/**
	 * @return Returns the divisor_h.
	 */
	public String getDivisor_h() {
		return divisor_h;
	}
	/**
	 * @param divisor_h The divisor_h to set.
	 */
	public void setDivisor_h(String divisor_h) {
		this.divisor_h = divisor_h;
	}
	/**
	 * @return Returns the price_h.
	 */
	public String getPrice_h() {
		return price_h;
	}
	/**
	 * @param price_h The price_h to set.
	 */
	public void setPrice_h(String price_h) {
		this.price_h = price_h;
	}
	/**
	 * @return Returns the divisor.
	 */
	public String getDivisor() {
		return divisor;
	}
	/**
	 * @param divisor The divisor to set.
	 */
	public void setDivisor(String divisor) {
		this.divisor = divisor;
	}
	/**
	 * @return Returns the price.
	 */
	public String getPrice() {
		return price;
	}
	/**
	 * @param price The price to set.
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return Returns the desc_h.
	 */
	public String getDesc_h() {
		return desc_h;
	}
	/**
	 * @param desc_h The desc_h to set.
	 */
	public void setDesc_h(String desc_h) {
		this.desc_h = desc_h;
	}
	/**
	 * @return Returns the name_h.
	 */
	public String getName_h() {
		return name_h;
	}
	/**
	 * @param name_h The name_h to set.
	 */
	public void setName_h(String name_h) {
		this.name_h = name_h;
	}
	/**
	 * @return Returns the shname_h.
	 */
	public String getShname_h() {
		return shname_h;
	}
	/**
	 * @param shname_h The shname_h to set.
	 */
	public void setShname_h(String shname_h) {
		this.shname_h = shname_h;
	}
	/**
	 * @return Returns the type_h.
	 */
	public String getType_h() {
		return type_h;
	}
	/**
	 * @param type_h The type_h to set.
	 */
	public void setType_h(String type_h) {
		this.type_h = type_h;
	}
	/**
	 * @return Returns the desc.
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @param desc The desc to set.
	 */
	public void setDesc(String desc) {
		if(desc!=null){
			this.desc = desc.trim();
		}
		else
		this.desc = desc;
	}
	/**
	 * @return Returns the event_index.
	 */
	public String getEvent_index() {
		return event_index;
	}
	/**
	 * @param event_index The event_index to set.
	 */
	public void setEvent_index(String event_index) {
		this.event_index = event_index;
	}
	/**
	 * @return Returns the event_stock.
	 */
	public String getEvent_stock() {
		return event_stock;
	}
	/**
	 * @param event_stock The event_stock to set.
	 */
	public void setEvent_stock(String event_stock) {
		this.event_stock = event_stock;
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
	 * @return Returns the desc.
	 */
	
	/**
	 * @return Returns the ex_actions.
	 */
	public Vector getEx_actions() {
		return ex_actions;
	}
	/**
	 * @param ex_actions The ex_actions to set.
	 */
	public void setEx_actions() 
	{
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
			  Logging.error(sqlexp+"");
		  }
	}
	
	public ResultSet get_action_List()
	{
		PreparedStatement pst;
		ResultSet rst;
		connection=null;
		try
		{
			if(connection==null)
			{
				connection=con.getdbConnection();
			}
			try
			{
				pst = connection.prepareStatement(ConnectInit.queries.getProperty("corp_action_master_list"));
				rst = pst.executeQuery();
				rst.close();
				pst.close();
				return rst;
			}catch (Exception e)
			{
				Logging.error("Error :"+e);
			}
			return null;
		}
		finally{
			try{if(connection!=null)
				connection.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
	}
	
	public void get_data(String id)
	{ 
		PreparedStatement checkcon;
		ResultSet rs1;
		String nm1=null; 
		int id_int = Integer.parseInt(id);
		connection=null;
		try
		{
			if(connection==null)
			{
				connection=con.getdbConnection();
			}
			try
			{
				checkcon = connection.prepareStatement(ConnectInit.queries.getProperty("corp_action_master_list_desc"));
				checkcon.setInt(1,id_int);
				Logging.debug("query is ......."+checkcon);
				rs1 = checkcon.executeQuery();
				while(rs1.next())
				{
					int id_cop  = Integer.parseInt(rs1.getString(1));
					String name1 = rs1.getString(2);
					String sh_name = rs1.getString(3);
					String desc = rs1.getString(4);
					String act_type = rs1.getString(5);
					String div = rs1.getString(6);
					String pri = rs1.getString(7);
					setDivisor_h(div);
					setPrice_h(pri);
					setDesc_h(desc);
					setName_h(name1);
					setShname_h(sh_name);
					setType_h(act_type);
				}
				rs1.close();
				checkcon.close();
			}catch (Exception e){
				Logging.error("Error list_desc() :"+e);
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
	/**
	 * Check for duplicate values before inserting new records.
	 * @param name_chk
	 * @return
	 */
			
	public int checkDupl(String name_chk)
	{
		int ans=0;
		String nm1 = null;
		PreparedStatement chk;
		ResultSet chk_rs;
		connection=null;
		try
		{
			if(connection==null)
			{
				connection=con.getdbConnection();
			}
			try
			{
				chk = connection.prepareStatement(ConnectInit.queries.getProperty("chk_corp_action_master"));
				chk.setString(1,name_chk);
				chk_rs = chk.executeQuery();
				while(chk_rs.next())
				{
					nm1 = chk_rs.getString(1);
				}
				if(nm1.equalsIgnoreCase(name_chk))
				{
					ans = 1;
				}
				chk_rs.close();
				chk.close();
			}catch(Exception e)
			{
				Logging.error("Error check() :"+e);
			}
			return ans;
		}
		finally{
			try{if(connection!=null)
				connection.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
	}
	
	public int get_id(String name_id)
	{
		int id = 0;
		PreparedStatement chkid;
		ResultSet chk_id_rs;
		connection=null;
		try
		{
			if(connection==null)
			{
				connection=con.getdbConnection();
			}
			try
			{
				chkid = connection.prepareStatement(ConnectInit.queries.getProperty("sel_id_corp_action_master"));
				chkid.setString(1,name_id);
				chk_id_rs = chkid.executeQuery();
				while(chk_id_rs.next())
				{
					id = chk_id_rs.getInt(1);
				}
				chk_id_rs.close();
				chkid.close();
			}catch(Exception e)
			{
				Logging.error("Error check() :"+e);
			}
			return id;
		}
		finally{
			try{if(connection!=null)
				connection.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
	}
	/**
	 * check for duplicate value and for record id if the record
	 * has not been selected from the list.
	 */
	public ActionErrors validate(ActionMapping mapping,HttpServletRequest request)
	 {
		String newvalue = getNew1();
		String updatevalue = getUpdate();
		Logging.debug("Inside validate.......... ");
		if(newvalue != null)
		{
			String name_d = getName();
			ActionErrors errors = new ActionErrors();
			boolean flag=true,flag1=true;
			try{
				int check = checkDupl(name_d);
				if(check == 1)
				{
					errors.add("DuplicateEntry",new ActionError("Error.message.DuplicateEntry"));
					setNew1(null);
				}
			}catch(Exception e){
				errors.add(null,new ActionError("Error.message.msg"));
				Logging.debug("Error in Validation ");			
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
				String idname_t = getId();
				if(idname_t == null)
				{
					setUpdate(null);
					setId(null);
					errors.add("DuplicateEntry",new ActionError("Error.message.selectfromlist"));			
				}
			}catch(Exception e){
				errors.add(null,new ActionError("Error.message.msg"));
				Logging.debug("Error in Validation ");			
			}		
			return errors;
		}
		return null;
	 }
}
