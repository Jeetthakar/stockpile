/*
 * Created on Mar 10, 2005
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
public class RatingCodes extends org.apache.struts.action.ActionForm
{
	Logger Logging = Logger.getLogger(RatingCodes.class);
	Connect con =ConnectInit.getConnect();
	Connection connection=null;
	PreparedStatement psub,pseq,checkcon;
	ResultSet rs1,seqno,rset;
	private Collection selectRatingCodesCollection=null;
	public String selectRatingCodes=null,selectCountry=null,selectContinent=null,defaultVal=null;
	String rat_name,rat_desc,chk1,chk2,new1,update,list;
	static String conf_flag;
	int rat_id_check,code_id,idname;
	static int rat_id;
	Vector ex_actions;
	String identifier_name,identifier_desc,rat_name_check;
	public Collection getSelectRatingCodesCollection() {
		Vector vec = new Vector();
		vec.add(new LabelValueBean("Not Selected","0"));
		connection=null;
		try{
			if(connection==null)
				connection=con.getdbConnection();
			try {
				PreparedStatement stmt =connection.prepareStatement(ConnectInit.queries.getProperty("get_rating_codes_list"));
				ResultSet rst = stmt.executeQuery();
				while(rst.next()){
					vec.add(new LabelValueBean(rst.getString(2),rst.getString(1)));
				}
				rst.close();
				stmt.close();
				selectRatingCodesCollection = vec;
				return selectRatingCodesCollection;
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

		return selectRatingCodesCollection;
	}
	/**
	 * @param selectCollection The selectCollection to set.
	 */
	
	public void setSelectRatingCodesCollection(Collection selectRatingCodesCollection) {
		this.selectRatingCodesCollection = selectRatingCodesCollection;
	}
	public String getSelectRatingCodes() {
		connection=null;
		try{
			if(connection==null)
				connection=con.getdbConnection();
			if(defaultVal!=null && defaultVal.equals("yes")){
			try {
					PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("select_system_config"));
					ResultSet rst = stmt.executeQuery();
					while(rst.next()){
						 selectRatingCodes=rst.getString(19);
					}
					rst.close();
					stmt.close();
			} catch (SQLException e) {
				Logging.error("Error  :"+e.getMessage());
				//e.printStackTrace();
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
		return selectRatingCodes;
	}
	/**
	 * @param select The select to set.
	 */
	public void setSelectRatingCodes(String selectRatingCodes) {
		Logging.debug("Inside setSelectIndex selectIndex = "+ selectRatingCodes);
		this.selectRatingCodes = selectRatingCodes;
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
		RatingCodes.conf_flag = conf_flag;
	}
	public String getRat_name_check() {
		return rat_name_check;
	}
	public void setRat_name_check(String rat_name_check) {
		this.rat_name_check = rat_name_check;
	}
	public int getCode_id() {
		return code_id;
	}
	public void setCode_id(int code_id) {
		this.code_id = code_id;
		
	}
	public String getIdentifier_desc() {
		if (identifier_desc == null)
		{
			identifier_desc= " ";
		}
		return identifier_desc;
	}
	public void setIdentifier_desc(String identifier_desc) {
		this.identifier_desc = identifier_desc;
	}
	public String getIdentifier_name() {
		return identifier_name;
	}
	public void setIdentifier_name(String identifier_name) {
		this.identifier_name = identifier_name;
	}
	public int getRat_id_check() {
		return rat_id_check;
	}
	public void setRat_id_check(int rat_id_check) {
		this.rat_id_check = rat_id_check;
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
	/*
	public static String getIdentifier_desc() {
		if (identifier_desc == null)
		{
			identifier_desc= " ";
		}
		return identifier_desc;
	}
	
	*/
	
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
	 * @return Returns the ex_actions.
	 */
	public Vector getEx_actions() {
		RatingCodes.setConf_flag(null);
		
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
		if(connection==null)
		{
			connection=con.getdbConnection();
		}
		try
		{
			pst = Connect.con.prepareStatement(con.queries.getProperty("get_rating_codes_list"));
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
	}*/
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
	 * @return Returns the chk2.
	 */
	public String getChk2() {
		return chk2;
	}
	/**
	 * @param chk2 The chk2 to set.
	 */
	public void setChk2(String chk2) {
		this.chk2 = chk2;
	}
	/**
	 * @return Returns the chk1.
	 */
	public String getChk1() {
		return chk1;
	}
	/**
	 * @param chk1 The chk1 to set.
	 */
	public void setChk1(String chk1) {
		this.chk1 = chk1;
	}
	
	/** 
	 * @return Returns the rat_desc.
	 */
	public String getRat_desc() {
		return rat_desc;
	}
	/**
	 * @param rat_desc The rat_desc to set.
	 */
	public void setRat_desc(String rat_desc) {
		this.rat_desc = rat_desc;
	}
	/**
	 * @return Returns the rat_id.
	 */
	public int getRat_id() {
		return rat_id;
	}
	/**
	 * @param rat_id The rat_id to set.
	 */
	public void setRat_id(int rat_id) {
		this.rat_id = rat_id;
	}
	/**
	 * @return Returns the rat_name.
	 */
	public String getRat_name() {
		return rat_name;
	}
	/**
	 * @param rat_name The rat_name to set.
	 */
	public void setRat_name(String rat_name)
	{
		
		this.rat_name = rat_name;
	}
	
	public void get_name_desc(int id)
	{
		code_id = id;
		PreparedStatement pst;
		ResultSet rst;
		int idt_code = id;
		connection=null;
		try
		{
		if(connection==null)
		{
			connection=con.getdbConnection();
		}
		try
		{
			pst = connection.prepareStatement(ConnectInit.queries.getProperty("get_rating_codes_desc"));
			pst.setInt(1,idt_code);
			rst = pst.executeQuery();
			while(rst.next())
			{
				identifier_name = rst.getString(1);
				setIdentifier_name(identifier_name);
				setRat_name(identifier_name);
				setRat_name_check(identifier_name);
				identifier_desc = rst.getString(2);
				setIdentifier_desc(identifier_desc);
				setRat_desc(identifier_desc);
				rat_id= rst.getInt(3);
				setRat_id(rat_id);
				setRat_id_check(rat_id);
			}
			rst.close();
			pst.close();
		}catch (Exception e)
		{
			Logging.error("Error :"+e); 
		}}
		finally{
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
		String updatevalue = getUpdate();
		RatCodesAction act = new RatCodesAction();
		if(newvalue != null)
		{
			ActionErrors errors = new ActionErrors();
			boolean flag=true,flag1=true;
			try
			{
				int check = act.checkData(rat_name,"new");
				if(check == 1)
				{
					errors.add("DuplicateEntry",new ActionError("Error.message.DuplicateEntry"));			
				}
			}catch(Exception e)
			{
				errors.add(null,new ActionError("Error.message.msg"));
			}	
		return errors;
		}
		if(updatevalue != null)
		{
			ActionErrors errors = new ActionErrors();
			try
			{
				int id_val=0;
				id_val = RatingCodes.rat_id;
				if(id_val==0)
				{
					setUpdate(null);
					setNew1(null);
					setRat_id_check(0);
					setIdname(0);
					errors.add("DuplicateEntry",new ActionError("Error.message.selectfromlist"));			
				}
				else if(!getRat_name().equalsIgnoreCase(getRat_name_check()))
				{
				try{
					int check = act.checkData(getRat_name().trim(),"update");
					if(check != 0 && check!=getRat_id_check())
					{
						setNew1(null);
						setUpdate(null);
						setRat_id_check(0);
						setIdname(0);
						errors.add("DuplicateEntry",new ActionError("Error.message.DuplicateEntry"));			
					}
				}catch(Exception e){
					Logging.error("Error  :"+e.getMessage());
				}
				}
			}catch(Exception e)
			{
				errors.add(null,new ActionError("Error.message.msg"));
			}	
		return errors;
		}
		
		return null;
	 }
}	
	