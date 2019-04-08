/*
 * Created on Apr 20, 2006
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
import org.apache.struts.validator.ValidatorForm;

import app.Connect;

import com.harrier.initializeation.ConnectInit;
/**
 * @author naresh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TimeZones extends ValidatorForm
{Logger Logging = Logger.getLogger(TimeZones.class);
	String time_d,desc,new1,update,list,idname,B1,radioChk,id;
	Vector ex_actions;
	private Collection selectTimeZoneCollection=null;
	public String selectTimeZone=null,selectCountry=null,selectContinent=null,defaultVal=null;
	Connect con =ConnectInit.getConnect();
	Connection connection=null;
	Logger  log ;
	int time_id;
	String descrip;
	static String conf_flag;
	static String tid;
	String identifier_name="",identifier_desc="";
	public Collection getSelectTimeZoneCollection() {
		Vector vec = new Vector();
		vec.add(new LabelValueBean("Not Selected","0"));
		connection=null;
		try{
			if(connection==null)
				connection=con.getdbConnection();
			try {
				PreparedStatement stmt =connection.prepareStatement(ConnectInit.queries.getProperty("time_zone_list"));
				ResultSet rst = stmt.executeQuery();
				while(rst.next()){
					vec.add(new LabelValueBean(rst.getString(2),rst.getString(1)));
				}
				rst.close();
				stmt.close();
				selectTimeZoneCollection = vec;
				return selectTimeZoneCollection;
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
		return selectTimeZoneCollection;
	}
	/**
	 * @param selectCollection The selectCollection to set.
	 */
	
	public void setSelectTimeZoneCollection(Collection selectTimeZoneCollection) {
		this.selectTimeZoneCollection = selectTimeZoneCollection;
	}
	
	public String getSelectTimeZone() {
		connection=null;
		try{
			if(connection==null)
				connection=con.getdbConnection();
			if(defaultVal!=null && defaultVal.equals("yes")){
			try {
				PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("select_system_config"));
				ResultSet rst = stmt.executeQuery();
				while(rst.next()){
					selectTimeZone=rst.getString(19);
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
		return selectTimeZone;
	}
	/**
	 * @param select The select to set.
	 */
	public void setSelectTimeZone(String selectTimeZone) {
		Logging.debug("Inside setSelectIndex selectIndex = "+ selectTimeZone);
		this.selectTimeZone = selectTimeZone;
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
		TimeZones.conf_flag = conf_flag;
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
	 * @return Returns the tid.
	 */
	public static String getTid() {
		return tid;
	}
	/**
	 * @param tid The tid to set.
	 */
	public static void setTid(String tid) {
		TimeZones.tid = tid;
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
		get_name_desc(idname);
	}
	
	/**
	 * @return Returns the identifier_desc.
	 */
	public String getIdentifier_desc() {
		return identifier_desc;
	}
	/**
	 * @param identifier_desc The identifier_desc to set.
	 */
	public void setIdentifier_desc(String identifier_desc) {
		this.identifier_desc = identifier_desc;
	}
	/**
	 * @return Returns the identifier_name.
	 */
	public String getIdentifier_name() {
		return identifier_name;
	}
	/**
	 * @param identifier_name The identifier_name to set.
	 */
	public void setIdentifier_name(String identifier_name) {
		this.identifier_name = identifier_name;
	}
	/**
	 * @return Returns the list.
	 */
	public String getList()
	{
		return list;
	}
	/**
	 * @param list The list to set.
	 */
	public void setList(String list) 
	{
		this.list = list;
	}
	/**
	 * @return Returns the ex_actions.
	 */
	public Vector getEx_actions() {
		TimeZones.setConf_flag(null);
		return ex_actions;
	}
	
	/**
	 * @return Returns the radioChk.
	 */
	public String getRadioChk() {
		return radioChk;
	}
	/**
	 * @param radioChk The radioChk to set.
	 */
	public void setRadioChk(String radioChk) {
		this.radioChk = radioChk;
		
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
			{
				connection=con.getdbConnection();
			}
			try
			{
				pst = connection.prepareStatement(con.queries.getProperty("time_zone_list"));
				Logging.getDebug("query :"+pst); 
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
			this.desc=desc.trim();
		}
		else
			this.desc = desc;
		
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
	 * @return Returns the time_d.
	 */
	public String getTime_d() {
		return time_d;
	}
	/**
	 * @param time_d The time_d to set.
	 */
	public void setTime_d(String time_d) {
		this.time_d = time_d;
				
	}
	/**
	 * @return Returns the update.
	 */
	public String getUpdate() {
		return update;
	}
	
	/**
	 * @return Returns the b1.
	 */
	public String getB1() {
		return B1;
	}
	/**
	 * @param b1 The b1 to set.
	 */
	public void setB1(String b1) {
		B1 = b1;
	}
	/**
	 * @param update The update to set.
	 */
	public void setUpdate(String update) {
		this.update = update;
		
	}
	public void get_name_desc(String id)
	{
		Logging.debug("idname     "+id);
		tid = id;
		PreparedStatement pst;
		ResultSet rst;
		int idt_code = Integer.parseInt(id);
		connection=null;
		try
		{
			if(connection==null)
			{
				connection=con.getdbConnection();
			}
			try
			{
				pst = connection.prepareStatement(ConnectInit.queries.getProperty("sel_time_desc1"));
				pst.setFloat(1,idt_code);
				rst = pst.executeQuery();
				while(rst.next())
				{
					identifier_name = rst.getString(1);
					setIdentifier_name(identifier_name);
					getIdentifier_name();
					setTime_d(identifier_name);
					getTime_d();
					identifier_desc = rst.getString(2);
					setIdentifier_desc(identifier_desc);
					getIdentifier_desc();
					setDesc(identifier_desc);
					getDesc();
				}
				rst.close();
				pst.close();
				list=id;
			}catch (Exception e)
			{
				Logging.error("Error :"+e);
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
		ActionErrors errors = new ActionErrors();
		boolean flag=true,flag1=true;
		connection=null;
		try
		{
			if(connection==null)
				connection=con.getdbConnection();
			String newvalue = getList();
			if(update==null && new1==null){
				return null;
			}
			else{
				try
				{
					PreparedStatement checkcon = connection.prepareStatement(ConnectInit.queries.getProperty("sel_time_desc"));
					checkcon.setString(1,desc);
					ResultSet rs1 = checkcon.executeQuery();
					while(rs1.next())
					{
						time_id = rs1.getInt(1);
						String descrip1=rs1.getString(2);
						if(descrip1!=null){
							descrip=descrip1;
						}
						break;
					}
					if(new1!=null && new1.equals("N")){
						if(time_id!=0){
							errors.add("DuplicateEntry",new ActionError("Error.message.DuplicateEntry"));		
						}
						setUpdate(null);
						setNew1(null);
						setId(null);
					}
					else if(update!=null && update.equals("U")){
						String id_val = null;
						String ii=getId();
						id_val = getId();
						if(id_val.trim().equals(""))
						{
							setUpdate(null);
							setNew1(null);
							setId(null);
							errors.add("DuplicateEntry",new ActionError("Error.message.selectfromlist"));			
						}
						else if(desc.trim().equalsIgnoreCase(descrip)){
							if(time_id!=0 && time_id!=Integer.parseInt(ii)){
								errors.add("DuplicateEntry",new ActionError("Error.message.DuplicateEntry"));	
							}
						}
						setUpdate(null);
						setNew1(null);
						setId(null);
					}
					rs1.close();
					checkcon.close();
				}catch(Exception e)
				{
					Logging.error("Error check() :"+e);
				}
			}
			return errors;
		}
		finally{
			try{if(connection!=null)
				connection.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
	}
}






