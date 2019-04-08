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
public class IndexType extends org.apache.struts.action.ActionForm
{ 
	Logger Logging = Logger.getLogger(IndexType.class);
	Connect con =ConnectInit.getConnect();
	Connection connection=null;
	String name,desc,new1,update,list_ind,type_code,type;
	Vector ex_actions;
	private Collection selectIndexTypeCollection=null;
	public String selectIndexType=null,selectCountry=null,selectContinent=null,defaultVal=null;
	String identifier_name ,identifier_desc,name_check;
	static String con_flag;
	int id_check,idname,segmentid;
	static int id_val;
	String selectSegment=null;
	
	public Collection getSelectIndexTypeCollection() {
		Vector vec = new Vector();
		vec.add(new LabelValueBean("Not Selected","0"));
		connection=null;
		try{
			if(connection==null)
				connection=con.getdbConnection();
			try {
			    PreparedStatement stmt =connection.prepareStatement(ConnectInit.queries.getProperty("index_type_list_new"));
				ResultSet rst = stmt.executeQuery();
				while(rst.next()){
					vec.add(new LabelValueBean(rst.getString(2),rst.getString(1)));
				}
				rst.close();
				stmt.close();
				selectIndexTypeCollection = vec;
				return selectIndexTypeCollection;
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
		return selectIndexTypeCollection;
	}
	public void reset(ActionMapping mapping,HttpServletRequest request){
	  	
   		name				=	null;
   		desc=null;
   		type_code=null;
   	}
	public void setSelectIndexTypeCollection(Collection selectIndexTypeCollection) {
		this.selectIndexTypeCollection = selectIndexTypeCollection;
	}
	public String getSelectIndexType() {
		connection=null;
		try{
			if(connection==null)
				connection=con.getdbConnection();
				if(defaultVal!=null && defaultVal.equals("yes")){
					try {
						PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("select_system_config"));
						ResultSet rst = stmt.executeQuery();
						while(rst.next()){
							selectIndexType=rst.getString(19);
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
		return selectIndexType;
	}
	/**
	 * @param select The select to set.
	 */
	public void setSelectIndexType(String selectIndexType) {
		Logging.debug("Inside setSelectIndex selectIndex = "+ selectIndexType);
		this.selectIndexType = selectIndexType;
	}
	
	public int getId_check() {
		return id_check;
	}
	public void setId_check(int id_check) {
		Logging.debug("in setid .......:"+id_check);
		this.id_check = id_check;
	}
	public String getName_check() {
		return name_check;
	}
	public void setName_check(String name_check) {
		if(name_check!=null){
			this.name_check=name_check.trim();
		}
		else
			this.name_check = name_check;
	}
	public static String getCon_flag() {
		return con_flag;
	}
	public static void setCon_flag(String con_flag) {
		IndexType.con_flag = con_flag;
	}
	public String getIdentifier_name() {
		return identifier_name;
	}
	public void setIdentifier_name(String identifier_name) {
		this.identifier_name = identifier_name;
	}
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return Returns the type_code.
	 */
	public String getType_code() {
		return type_code;
	}
	/**
	 * @param type_code The type_code to set.
	 */
	public void setType_code(String type_code) {
		if(type_code!=null){
			this.type_code = type_code.trim();
		}
		else
			this.type_code = type_code;
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
	 * @return Returns the list.
	 */
	public String getList_ind() {
		return list_ind;
	}
	/**
	 * @param list The list to set.
	 */
	public void setList_ind(String list_ind) {
		this.list_ind = list_ind;
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
	
	public Vector getEx_actions() 
	{
		setCon_flag(null);
		return ex_actions;
	}
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
			pst = connection.prepareStatement(con.queries.getProperty("index_type_list"));
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
	public void get_name_desc(int id)
	{
		String temp_str=null;
		PreparedStatement pst;
		ResultSet rst;
		//name = ""+id; //Commented on 02/09/06
		id_check = id;
		int idt_code = id;
		connection=null;
		if(connection==null)
			connection=con.getdbConnection();
		try
		{
			pst = connection.prepareStatement(ConnectInit.queries.getProperty("index_type_desc"));
			pst.setInt(1,idt_code);
			rst = pst.executeQuery();
			while(rst.next())
			{
				identifier_name = rst.getString(1);
				setIdentifier_name(identifier_name);
				setName(identifier_name);
				setName_check(identifier_name);
				if(rst.getString(2)!= null)
				{
					identifier_desc = rst.getString(2);
					setIdentifier_desc(identifier_desc);
					setDesc(identifier_desc);
				}
				else
				{
					identifier_desc = "";
				}
				
				type = rst.getString(3);
				setType_code(type);
				
				id_val= rst.getInt(4);
				setId_check(id_val);
				
				selectSegment=rst.getString(5);
				setSelectSegment(selectSegment);
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
		String indexName=getName();
		IndexTypeAction act = new IndexTypeAction();
		String newvalue = getNew1();
		String updatevalue = getUpdate();
		if(newvalue != null)
		{
		ActionErrors errors = new ActionErrors();
		boolean flag=true,flag1=true;
		try{
			int check = act.checkData(indexName);
			if(check == 1)
			{
				setNew1(null);
				setUpdate(null);
				errors.add("DuplicateEntry",new ActionError("Error.message.DuplicateEntry"));			
			}
		}catch(Exception e){
			errors.add(null,new ActionError("Error.message.msg"));
		}	
		return errors;
		}
		if(updatevalue != null)
		{
			boolean flag=true,flag1=true;
			ActionErrors errors = new ActionErrors();
			try
			{
				//String id_val = null;
				id_val = IndexType.id_val;
				if(id_val == 0) 
						//||id_val..trim().length()==0)
				{
					setUpdate(null);
					setNew1(null);
					errors.add("DuplicateEntry",new ActionError("Error.message.selectfromlist"));			
				}
				if(!indexName.equalsIgnoreCase(getName_check()))
				{
				try{
					int check = act.checkData(indexName);
					if(check == 1)
					{
						setNew1(null);
						setUpdate(null);
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
	public String getSelectSegment() {
		return selectSegment;
	}
	public void setSelectSegment(String selectSegment) {
		this.selectSegment = selectSegment;
	}
	public int getSegmentid() {
		return segmentid;
	}
	public void setSegmentid(int segmentid) {
		this.segmentid = segmentid;
	}
}

