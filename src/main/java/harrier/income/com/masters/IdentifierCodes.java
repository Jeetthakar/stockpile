/*
 * Created on Mar 6, 2006
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
public class IdentifierCodes extends org.apache.struts.action.ActionForm
{
	Logger Logging = Logger.getLogger(IdentifierCodes.class);
	Connect con =ConnectInit.getConnect();
	Connection connection=null;
	private String selectIdCode=null,defaultVal=null,selectStock=null;
	private Collection selectIdCodeCollection=null,stockCollection=null;
	PreparedStatement psub,pseq,checkcon;
	ResultSet rs1,seqno;
	Vector ex_actions;
	public String cname,desc,new1,update,d1,idname,cname_check;
	public static String identifier_name ,identifier_desc ,name;
	static String con_flag;
	static int iden_id;
	public Collection getSelectIdCodeCollection() {
		Vector vec = new Vector();
		vec.add(new LabelValueBean("Not Selected","0"));
		connection=null;
		try{
			if(connection==null)
				connection=con.getdbConnection();
			try {
				PreparedStatement stmt =connection.prepareStatement(ConnectInit.queries.getProperty("sel_identifier_list"));
				ResultSet rst = stmt.executeQuery();
				while(rst.next()){
					vec.add(new LabelValueBean(rst.getString(2),rst.getString(1)));
				}
				rst.close();
				stmt.close();
				selectIdCodeCollection = vec;
				return selectIdCodeCollection;
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
		return selectIdCodeCollection;
	}
	/**
	 * @param selectCollection The selectCollection to set.
	 */
	
	public void setSelectIdCodeCollection(Collection selectIdCodeCollection) {
		this.selectIdCodeCollection = selectIdCodeCollection;
	}
	public String getSelectIdCode() {
		connection=null;
		try{
			if(connection==null)
				connection=con.getdbConnection();
			if(defaultVal!=null && defaultVal.equals("yes")){
			try {
					PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("select_system_config"));
					ResultSet rst = stmt.executeQuery();
					while(rst.next()){
						 selectIdCode=rst.getString(19);
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
		return selectIdCode;
	}
	/**
	 * @param select The select to set.
	 */
	public void setSelectIdCode(String selectIdCode) {
		Logging.debug("Inside setSelectIndex selectIndex = "+ selectIdCode);
		this.selectIdCode = selectIdCode;
	}
	
	/*public String getIden_id() {
		return iden_id;
	}
	public void setIden_id(String iden_id) {
		if(iden_id!=null){
			this.iden_id = iden_id.trim();
		}
		else
		this.iden_id = iden_id;
	}*/
	public int getIden_id() {
		return iden_id;
	}
	public void setIden_id(int iden_id) {
		if(iden_id!=0){
			this.iden_id = iden_id;
		}
		else
			this.iden_id = iden_id;
	}
	public static String getCon_flag() {
		return con_flag;
	}
	public static void setCon_flag(String con_flag) {
		IdentifierCodes.con_flag = con_flag;
	}
	public String getCname_check() {
		return cname_check;
	}
	public void setCname_check(String cname_check) {
		if(cname_check!=null){
			this.cname_check = cname_check.trim();
		}
		else
			this.cname_check = cname_check;
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
		if(identifier_desc!=null){
			this.identifier_desc = identifier_desc.trim();
		}
		else
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
		if(identifier_name!=null){
			this.identifier_name = identifier_name.trim();
		}
		else
			this.identifier_name = identifier_name;
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
		if(idname!=null){
			this.idname = idname.trim();
			get_name_desc(idname);
		}
		else
			this.idname = idname;
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
	 * @return Returns the d1.
	 */
	public String getD1() {
		return d1;
	}
	/**
	 * @param d1 The d1 to set.
	 */
	public void setD1(String d1) {
		this.d1 = d1;
	}
	/**
	 * @return Returns the cname.
	 */
	public String getCname() {
		return cname;
	}
	/**
	 * @param cname The cname to set.
	 */
	public void setCname(String cname) {
		if(cname!=null){
			this.cname = cname.trim();
		}
		else
			this.cname = cname;
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
			this.desc = desc;
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
		setCon_flag(null);
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
		if(connection==null)
			connection=con.getdbConnection();
		try
		{
			pst = connection.prepareStatement(con.queries.getProperty("sel_identifier_list"));
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
	public void get_name_desc(String id)
	{
		setCon_flag(null);
		PreparedStatement pst;
		ResultSet rst;
		name = id;
		int idt_code = Integer.parseInt(id);
		connection=null;
		if(connection==null)
			connection=con.getdbConnection();
		try
		{
			pst = connection.prepareStatement(ConnectInit.queries.getProperty("sel_identifier_desc"));
			pst.setInt(1,idt_code);
			rst = pst.executeQuery();
			while(rst.next())
			{
				identifier_name = rst.getString(1);
				identifier_desc = rst.getString(2);
				setIdentifier_name(identifier_name);
				setCname(identifier_name);
				setCname_check(identifier_name);
				setName(identifier_name);
				setDesc(identifier_desc);
				setIdentifier_desc(identifier_desc);
				iden_id= rst.getInt(3);
				setIden_id(iden_id);
			}
			rst.close();
			pst.close();
		}catch (Exception e)
		{
			Logging.error("Error :"+e);
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
		IdentifierCodesAction act = new IdentifierCodesAction();
		String newvalue = getNew1();
		String updatevalue = getUpdate();
		if(newvalue != null)
		{
			ActionErrors errors = new ActionErrors();
			boolean flag=true,flag1=true;
			try{
				int check = act.checkData(cname);
				if(check == 1)
				{
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
				try
				{
					int id_val = IdentifierCodes.iden_id;
					Logging.debug("id is ........: "+id_val);
					if(id_val == 0) 
							//|| id_val.trim().length()==0)
					{
						setUpdate(null);
						errors.add("DuplicateEntry",new ActionError("Error.message.selectfromlist"));			
					}
					if(!getCname().equalsIgnoreCase(getCname_check()))
					{
					try{
						int check = act.checkData(getCname().trim());
						if(check == 1)
						{
							errors.add("DuplicateEntry",new ActionError("Error.message.DuplicateEntry"));			
						}
						}catch(Exception e){
						errors.add(null,new ActionError("Error.message.msg"));
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
/*	public int checkData(String name)
	{
		PreparedStatement checkcon;
		ResultSet rs1;
		int ans=0;
		String nm1 = null;
		connection=null;
		if(connection==null)
			connection=con.getdbConnection();
		try
		{
			checkcon = connection.prepareStatement(con.queries.getProperty("check_identifier_codes"));
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
		finally{
			try{if(connection!=null)
				connection.close();
			}catch(Exception ee){
				Logging.getError(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
		return ans;
	}*/
}
