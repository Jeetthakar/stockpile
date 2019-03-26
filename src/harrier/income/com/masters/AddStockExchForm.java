/*
 * Created on Jan 18, 2006
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
import java.util.StringTokenizer;
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
public class AddStockExchForm extends ActionForm{
	Logger Logging = Logger.getLogger(AddStockExchForm.class);
	public String stk_ex_code,tm_zone,cnt_name,stk_name,iden_code_name,btn_add,defaultVal=null,from,exch_list,flag_err,
	selectExchange=null,selectCountry=null,selectTimeZone=null,selectIdCode=null,start_time,stop_time,start_t,stop_t;
	private Collection selectExchangeCollection=null;
	private Collection selectCountryCollection=null;
	private Collection selectTimeZoneCollection=null;
	private Collection selectIdCodeCollection=null,stockCollection=null;
	Vector con_list,time_list,exch_list_vector,ex_actions;
	Connect con =ConnectInit.getConnect();
	Connection connection=null;
	String exch_name,cont_id,time_id,new1,update,ex_identifier_code,stk_name_check;
	static String con_flag; 
	int start[];
	int stop[]	;
	int count,count1,exch_id,iden_code;
	public void reset(ActionMapping mapping,HttpServletRequest request){
		exch_name=null;start_time=null;stop_time=null;start_t=null;stop_t=null;
		
		stk_ex_code=null;tm_zone=null;stk_name=null;iden_code_name=null;
		time_id=null;ex_identifier_code=null;
		selectExchange=null;selectCountry=null;selectTimeZone=null;selectIdCode=null;
   	}
	public Collection getSelectExchangeCollection() {
		Vector vec = new Vector();
		vec.add(new LabelValueBean("Not Selected","0"));
		connection=null;
		PreparedStatement stmt=null;
		try{
			if(connection==null)
				connection=con.getdbConnection();
			try {
				
				stmt =connection.prepareStatement(ConnectInit.queries.getProperty("stock_exch_list"));
				ResultSet rst = stmt.executeQuery();
				while(rst.next()){
					vec.add(new LabelValueBean(rst.getString(2),rst.getString(1)));
				}
				rst.close();
				stmt.close();
				selectExchangeCollection = vec;
				return selectExchangeCollection;
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

		return selectExchangeCollection;
	}
	/**
	 * @param selectCollection The selectCollection to set.
	 */
	
	public void setSelectExchangeCollection(Collection selectExchangeCollection) {
		this.selectExchangeCollection = selectExchangeCollection;
	}
	public String getSelectExchange() {
		connection=null;
		try{
			if(connection==null)
				connection=con.getdbConnection();
			
		if(defaultVal!=null && defaultVal.equals("yes")){
			
			try {
					PreparedStatement stmt = connection.prepareStatement(ConnectInit.queries.getProperty("select_system_config"));
					ResultSet rst = stmt.executeQuery();
					while(rst.next()){
						 selectExchange=rst.getString(19);
					}
					rst.close();
					stmt.close();
			} catch (SQLException e) {
				Logging.error(" Error : "+e.getMessage());
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
		return selectExchange;
	}
	/**
	 * @param select The select to set.
	 */
	public void setSelectExchange(String selectExchange) {
		Logging.debug("Inside setSelectExchange selectExchange = "+ selectExchange);
		this.selectExchange = selectExchange;
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
				//e.printStackTrace();
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
	public Collection getSelectTimeZoneCollection() {
		Vector vec = new Vector();
		vec.add(new LabelValueBean("Not Selected","0"));
		connection=null;
		try{
			if(connection==null)
				connection=con.getdbConnection();
			try {
				PreparedStatement stmt =connection.prepareStatement(ConnectInit.queries.getProperty("time_diff_list"));
				ResultSet rst = stmt.executeQuery();
				while(rst.next()){
					vec.add(new LabelValueBean(rst.getString(2),rst.getString(1)));
				}
				rst.close();
				stmt.close();
				selectTimeZoneCollection = vec;
				return selectTimeZoneCollection;
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
		return selectTimeZone;
	}
	/**
	 * @param select The select to set.
	 */
	public void setSelectTimeZone(String selectTimeZone) {
		Logging.debug("Inside setSelectTimeZone selectTimeZone = "+ selectTimeZone);
		this.selectTimeZone = selectTimeZone;
	}
	
	
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
		return selectIdCode;
	}
	/**
	 * @param select The select to set.
	 */
	public void setSelectIdCode(String selectIdCode) {
		Logging.debug("Inside setSelectIdCode selectIdCode = "+ selectIdCode);
		this.selectIdCode = selectIdCode;
	}
	 public String getStk_name_check() {
		return stk_name_check;
	}
	public void setStk_name_check(String stk_name_check) {
		if(stk_name_check!=null){
			this.stk_name_check = stk_name_check.trim();
		}
		else
		this.stk_name_check = stk_name_check;
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
		AddStockExchForm.con_flag = con_flag;
	}
	/**
	 * @return Returns the flag_err.
	 */
	public String getFlag_err() {
		return flag_err;
	}
	/**
	 * @param flag_err The flag_err to set.
	 */
	public void setFlag_err(String flag_err) {
		this.flag_err = flag_err;
	}
	/**
	 * @return Returns the start_t.
	 */
	public String getStart_t() {
		return start_t;
	}
	/**
	 * @param start_t The start_t to set.
	 */
	public void setStart_t(String start_t) {
		this.start_t = start_t;
	}
	/**
	 * @return Returns the stop_t.
	 */
	public String getStop_t() {
		return stop_t;
	}
	/**
	 * @param stop_t The stop_t to set.
	 */
	public void setStop_t(String stop_t) {
		this.stop_t = stop_t;
	}
	/**
	 * @return Returns the start_time.
	 */
	public String getStart_time() {
		return start_time;
	}
	/**
	 * @param start_time The start_time to set.
	 */
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	/**
	 * @return Returns the stop_time.
	 */
	public String getStop_time() {
		return stop_time;
	}
	/**
	 * @param stop_time The stop_time to set.
	 */
	public void setStop_time(String stop_time) {
		this.stop_time = stop_time;
	}
	/**
	 * @return Returns the exch_list.
	 */
	public String getExch_list() {
		return exch_list;
	}
	/**
	 * @param exch_list The exch_list to set.
	 */
	public void setExch_list(String exch_list) {
		this.exch_list = exch_list;
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
	 * @return Returns the cont_id.
	 */
	public String getCont_id() {
		return cont_id;
	}
	/**
	 * @param cont_id The cont_id to set.
	 */
	public void setCont_id(String cont_id) {
		this.cont_id = cont_id;
	}
	/**
	 * @return Returns the exch_id.
	 */
	public int getExch_id() {
		return exch_id;
	}
	/**
	 * @param exch_id The exch_id to set.
	 */
	public void setExch_id(int exch_id) {
		this.exch_id = exch_id;
		get_name_desc(exch_id);
	}
	/**
	 * @return Returns the exch_name.
	 */
	public String getExch_name() {
		return exch_name;
	}
	/**
	 * @param exch_name The exch_name to set.
	 */
	public void setExch_name(String exch_name) {
		if(exch_name!=null){
			this.exch_name = exch_name.trim();
		}
		else
			this.exch_name = exch_name;
	}
	/**
	 * @return Returns the iden_code.
	 */
	public int getIden_code() {
		return iden_code;
	}
	/**
	 * @param iden_code The iden_code to set.
	 */
	public void setIden_code(int iden_code) {
		this.iden_code = iden_code;
	}
	/**
	 * @return Returns the time_id.
	 */
	public String getTime_id() {
		return time_id;
	}
	/**
	 * @param time_id The time_id to set.
	 */
	public void setTime_id(String time_id) {
		this.time_id = time_id;
	}
	/**
	 * @return Returns the cnt_name.
	 */
	public String getCnt_name() {
		return cnt_name;
	}
	/**
	 * @param cnt_name The cnt_name to set.
	 */
	public void setCnt_name(String cnt_name) {
		this.cnt_name = cnt_name;
	}
	
	/**
	 * @return Returns the stk_ex_code.
	 */
	public String getStk_ex_code() {
		return stk_ex_code;
	}
	/**
	 * @param stk_ex_code The stk_ex_code to set.
	 */
	public void setStk_ex_code(String stk_ex_code) {
		if(stk_ex_code!=null){
			this.stk_ex_code = stk_ex_code.trim();
		}
		else
			this.stk_ex_code = stk_ex_code;
	}
	/**
	 * @return Returns the stk_name.
	 */
	public String getStk_name() {
		return stk_name;
	}
	/**
	 * @param stk_name The stk_name to set.
	 */
	public void setStk_name(String stk_name) {
		if(stk_name!=null){
			this.stk_name = stk_name.trim();
		}
		else
			this.stk_name = stk_name;
	}
	/**
	 * @return Returns the tm_zone.
	 */
	public String getTm_zone() {
		return tm_zone;
	}
	/**
	 * @param tm_zone The tm_zone to set.
	 */
	public void setTm_zone(String tm_zone) {
		this.tm_zone = tm_zone;
	}
	/**
	 * @return Returns the ex_identifier_code.
	 */
	public String getEx_identifier_code() {
		return ex_identifier_code;
	}
	/**
	 * @param ex_identifier_code The ex_identifier_code to set.
	 */
	public void setEx_identifier_code(String ex_identifier_code) {
		Logging.debug("inside set"+ex_identifier_code);
		if(ex_identifier_code!=null){
			this.ex_identifier_code = ex_identifier_code.trim();
		}
		else
			this.ex_identifier_code = ex_identifier_code;
	}
	/**
	 * @return Returns the btn_add.
	 */
	public String getBtn_add() {
		return btn_add;
	}
	/**
	 * @param btn_add The btn_add to set.
	 */
	public void setBtn_add(String btn_add) {
		this.btn_add = btn_add;
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
	/**
	 * @return Returns the con_list.
	 */
	public Vector getCon_list() {
		setCon_list();
		return con_list;
	}
	/**
	 * @param con_list The con_list to set.
	 */
	
	public void setCon_list() 
	{
		ResultSet rset;
		con_list = new Vector();
		rset = get_con_List();
		int i = 0;
		try  
		{
			while (rset.next())
			{				
				con_list.add(i, rset.getString(1));
				i++;
				con_list.add(i,(String)rset.getString(2));
				i++;
		    }
			rset.close();
		} catch (Exception sqlexp)
		  {
			  Logging.error(sqlexp+"");
		}
	}
	
	public ResultSet get_con_List()
	{
		PreparedStatement pst;
		ResultSet rst;
		if(connection == null)
		{
			connection=con.getdbConnection();
		}
		try
		{
			pst = connection.prepareStatement(ConnectInit.queries.getProperty("get_country_list"));
			rst = pst.executeQuery();
			rst.close();
			pst.close();
			return rst;
		}catch (Exception e)
		{
			Logging.error("Error in get_list:"+e);
			return null;
		}
	}
	
	/**
	 * @return Returns the time_list.
	 */
	public Vector getTime_list()
	{
		setTime_list();
		return time_list;
	}
	/**
	 * @param time_list The time_list to set.
	 */
	public void setTime_list() 
	{
		ResultSet rset;
		time_list = new Vector();
		rset = get_time_List();
		int i = 0;
		try  
		{
			while (rset.next())
			{	
				time_list.add(i, rset.getString(1));
				i++;
				time_list.add(i, rset.getString(2));
				i++;
		    }
			rset.close();
		} catch (Exception sqlexp)
		  {
			  Logging.error("in settime  :"+sqlexp);
		}
	}
	
	public ResultSet get_time_List()
	{
		PreparedStatement pst;
		ResultSet rst;
		if(connection == null)
		{
			connection=con.getdbConnection();
		}
		try
		{
			pst = connection.prepareStatement(ConnectInit.queries.getProperty("time_diff_list"));
			rst = pst.executeQuery();
			rst.close();
			pst.close();
			return rst;
		}catch (Exception e)
		{
			Logging.error("Error in get_time_list:"+e);
			return null;
		}
	}
	

	/**
	 * @return Returns the exch_list.
	 */
	
	/**public Vector getExch_list_vector() 
	{
		AddStockExchForm.setCon_flag(null);
		setExch_list_vector();
		return exch_list_vector;
	}**/
	/**
	 * @param exch_list The exch_list to set.
	 */
	/**public void setExch_list_vector() 
	{
		ResultSet rset;
		exch_list_vector = new Vector();
		rset = get_exch_List();
		int i = 0;
		try  
		{
			while (rset.next())
			{	
				exch_list_vector.add(i, rset.getString(1));
				i++;
				exch_list_vector.add(i, rset.getString(2));
				i++;
		    }
			rset.close();
		} catch (Exception sqlexp)
		  {
			  Logging.getError("in setexch  :"+sqlexp);
		}
	}**/
	/**public ResultSet get_exch_List()
	{
		PreparedStatement pst;
		ResultSet rst;
		if(connection == null)
		{
			connection=con.getdbConnection();
		}
		try
		{
			pst = connection.prepareStatement(con.queries.getProperty("stock_exch_list"));
			rst = pst.executeQuery();
			rst.close();
			pst.close();
			return rst;
		}catch (Exception e)
		{
			Logging.getError("Error in get_time_list:"+e);
			return null;
		}
	}**/
	
	public void get_name_desc(int id)
	{
		PreparedStatement pst;
		ResultSet rst;
		int exchid_int = id;
		Connection connection=null;
		try
		{
			if(connection == null)
			{
				connection=con.getdbConnection();
			}
			try  
			{
				pst = connection.prepareStatement(ConnectInit.queries.getProperty("get_stk_exchange_desc"));
				pst.setInt(1,exchid_int);
				rst = pst.executeQuery();
				while(rst.next())
				{
					exch_id = rst.getInt(1);
					exch_name = rst.getString(2);
					if(exch_name == null)
						exch_name = "";
					setExch_name(exch_name);
					setStk_name(exch_name);
					setStk_name_check(exch_name);
					cont_id = rst.getString(3);
					setCont_id(cont_id);
					setCnt_name(cont_id);
					setSelectCountry(cont_id);
					time_id = rst.getString(4);
					setTm_zone(time_id);
					setTime_id(time_id);
					setSelectTimeZone(time_id);
					stk_ex_code = rst.getString(5);
					if(stk_ex_code == null)
						stk_ex_code = "";
					setStk_ex_code(stk_ex_code);
					start_t = rst.getString(6);
					if(start_t == null)
						start_t = "";
					setStart_t(start_t);
					setStart_time(start_t);
					stop_t = rst.getString(7);
					if(stop_t == null)
						stop_t = "";
					setStop_t(stop_t);
					setStop_time(stop_t);
					iden_code = rst.getInt(8);
					if(iden_code == 0)
						iden_code = 0;
					setIden_code(iden_code);
					
				}
				PreparedStatement id_check = connection.prepareStatement(ConnectInit.queries.getProperty("check_identifier_name_from_id"));
				id_check.setInt(1,iden_code);
				ResultSet id_res = id_check.executeQuery();
				while(id_res.next())    //-- If identifier code exist in database.
				{
					iden_code = id_res.getInt(1);
					ex_identifier_code = id_res.getString(2);
					setEx_identifier_code(ex_identifier_code);
				}
				id_res.close();
				rst.close();
				pst.close();
			}catch (Exception e)
			{
				Logging.error("Error get_desc:"+e); 
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
	
	public String startTime(String m_start_time,String n_stop_time){
		/**Validation for start time should be less than stop time
    	 *OR .......  Validation For Time Format
    	 ** */
			//Breaking n_stop_time
    		StringTokenizer token1=new StringTokenizer(n_stop_time,":");
    		while(token1.hasMoreElements()){
    			String t1=token1.nextToken();
    			stop[count1]=Integer.parseInt(t1);
    			count1++;
    		}
    		//Breaking m_start_time
    		StringTokenizer token=new StringTokenizer(m_start_time,":");
    		while(token.hasMoreElements()){
    			String t=token.nextToken();
    			start[count]=Integer.parseInt(t);
    			count++;
    		}
    		String isValid="t";
    		if(start[0]>stop[0]){
				isValid = "f";
			}
			else if(start[0]==stop[0]){
				if(start[1]>stop[1]){
					isValid = "f";
				}
				else if(start[1]==stop[1]){
						if(start[2]>stop[2] || start[2]==stop[2]){
							isValid = "f";
						}
						else{
							isValid="t";
						}
				}
				else{
					isValid="t";
				}
			}
			else{
				isValid="t";
			}
		return isValid;
	}
	private boolean ValidateTime(String time) {
		if (time == null || time.trim().equals("")) {
			return false;
		}
		try {
			String[] arr = time.split(":");
			String[] temp = { "23", "59", "59" };
			if (arr.length < 3 || arr.length > 3)
				return false;
			for (int i = 0; i < arr.length; i++) {
				Logging.debug(arr[i]);
				try {
					if (Integer.parseInt(arr[i]) > Integer.parseInt(temp[i])
							|| (Integer.parseInt(arr[i]) < 0))
						return false;
				} catch (Exception e) {
					return false;
				}
			}
			Logging.debug("Returned true");
			return true;
		} catch (Exception e) {
			Logging.error("Returned false");
			return false;
		}
	}
	/**
	 * Duplicate validation is done by considering the stock name and country name both.
	 * Stock name can be same but stock name and country name cant be same at a time for existing records.
	 */
	public ActionErrors validate(ActionMapping mapping,HttpServletRequest request)
	 {
		start=  new int[3];
		stop= new int[3];
		count=0;count1=0;
		String newvalue = getNew1();
		String flag = getFlag_err();
		String updatevalue = getUpdate();
		String m_start_time=start_time;
		String n_stop_time=stop_time;
		ActionErrors errors = new ActionErrors();
		String isValid="t";
		
		
		
		if(newvalue != null)
		{
			errors.clear();
			if(selectCountry == null || selectCountry.trim().equals("")||  selectCountry.trim().equals("0"))//|| s_indexName.charAt(0)==' ')
			{
				errors.add("selectCountry",new ActionError("Error.message.Country"));			
				setNew1(null);
			}
			if(selectTimeZone == null || selectTimeZone.trim().equals("") || selectTimeZone.trim().equals("0"))//|| s_indexName.charAt(0)==' ')
			{
				errors.add("selectTimeZone",new ActionError("Error.message.TimeZone"));			
				setNew1(null);
			}
			if(stk_ex_code == null || stk_ex_code.trim().equals("") || stk_ex_code.trim().equals("0"))//|| s_indexName.charAt(0)==' ')
			{
				errors.add("stk_ex_code",new ActionError("Error.message.StockExCode"));			
				setNew1(null);
			}
			if(ex_identifier_code == null || ex_identifier_code.trim().equals("") || ex_identifier_code.trim().equals("0"))//|| s_indexName.charAt(0)==' ')
			{
				errors.add("ex_identifier_code",new ActionError("Error.message.ExcIndCode"));			
				setNew1(null);
			}
			
			try{
				int check = checkData(stk_name,cnt_name,"new");
				int check_code = checkData_code(cnt_name,"new",stk_ex_code);
				if(check == 1)
				{
					errors.add("DuplicateEntry",new ActionError("Error.message.DuplicateEntry"));	
					setNew1(null);
				}
				if(check_code > 0)
				{
					errors.add("DuplicateEntry",new ActionError("Error.message.SameExchangeCode"));	
					setNew1(null);
				}
			}catch(Exception e)
			{
				errors.add(null,new ActionError("Error.message.msg"));
				Logging.error("Error in Validation ");			
			}	
			if (!ValidateTime(m_start_time)) {
				errors.add(null, new ActionError("Error.message.StartTime"));
				setNew1(null);
			}
			if (!ValidateTime(n_stop_time)) {
				errors.add(null, new ActionError("Error.message.StoptTime"));
				setNew1(null);
			}
			isValid=startTime(m_start_time,n_stop_time);
			if(isValid.equals("f")){
				errors.add(null,new ActionError("errors.startStopPercentage"));
				setNew1(null);
			}
			return errors;
		}
		if(updatevalue != null)
		{
			//Logging.getDebug("Error in Validation U..... "+updatevalue);
			errors.clear();
			if(selectCountry == null || selectCountry.trim().equals("")||  selectCountry.trim().equals("0"))//|| s_indexName.charAt(0)==' ')
			{
				errors.add("selectCountry",new ActionError("Error.message.Country"));			
				setNew1(null);
				setUpdate(null);
			}
			if(selectTimeZone == null || selectTimeZone.trim().equals("") || selectTimeZone.trim().equals("0"))//|| s_indexName.charAt(0)==' ')
			{
				errors.add("selectTimeZone",new ActionError("Error.message.TimeZone"));			
				setNew1(null);
				setUpdate(null);
			}
			if(stk_ex_code == null || stk_ex_code.trim().equals("") || stk_ex_code.trim().equals("0"))//|| s_indexName.charAt(0)==' ')
			{
				errors.add("stk_ex_code",new ActionError("Error.message.StockExCode"));			
				setNew1(null);
				setUpdate(null);
			}
			if(ex_identifier_code == null || ex_identifier_code.trim().equals("") || ex_identifier_code.trim().equals("0"))//|| s_indexName.charAt(0)==' ')
			{
				errors.add("ex_identifier_code",new ActionError("Error.message.ExcIndCode"));			
				setNew1(null);
				setUpdate(null);
			}
			try{
				int idname_t = getExch_id();
				//if(idname_t == null)
				if(idname_t == 0)
				{
					setUpdate(null);
					setExch_id(0);
					errors.add("DuplicateEntry",new ActionError("Error.message.selectfromlist"));			
				}
				if(!getStk_name().equalsIgnoreCase(getStk_name_check()))
				{
					try{
						int cont_id_int = Integer.parseInt(getCnt_name());
						int check = checkData(stk_name.trim(),cnt_name.trim(),"update");
						int check_code = checkData_code(cnt_name,"update",stk_ex_code);
						if(check!=0 && check != cont_id_int)
						{
							errors.add("DuplicateEntry",new ActionError("Error.message.DuplicateEntry"));	
							setNew1(null);
							setUpdate(null);
							//setExch_id(null);
							setExch_id(0);
						}
						if(check_code > 0 && check_code != cont_id_int)
						{
							errors.add("DuplicateEntry",new ActionError("Error.message.SameExchangeCode"));	
							setNew1(null);
							setUpdate(null);
							setExch_id(0);
						}
					}catch(Exception e)
					{
						errors.add(null,new ActionError("Error.message.msg"));
					}
				}
			}catch(Exception e){
				errors.add(null,new ActionError("Error.message.msg"));
			}	
			if (!ValidateTime(m_start_time)) {
				errors.add(null, new ActionError("Error.message.StartTime"));
				setNew1(null);
				setUpdate(null);
				setExch_id(0);
			}
			if (!ValidateTime(n_stop_time)) {
				errors.add(null, new ActionError("Error.message.StoptTime"));
				setNew1(null);
				setUpdate(null);
				setExch_id(0);
			}
			isValid=startTime(m_start_time,n_stop_time);
			if(isValid.equals("f")){
				errors.add(null,new ActionError("errors.startStopPercentage"));
				setNew1(null);
				setUpdate(null);
				setExch_id(0);
			}
			return errors;
		}
		errors.clear();
		return null;
	 }
	/*
	 * function to check duplicate entries in the table.
	 * funtion is called only when new data is inserted.
	 */
	public int checkData(String name,String cont_id,String str)
	{
		PreparedStatement checkcon;
		ResultSet rs1;
		int ans=0;
		int cont_id_int=0;
		if(getCnt_name()!=null)
		 cont_id_int = Integer.parseInt(getCnt_name());
		String nm1 = "";
		int nm2=0;
		connection=null;
		try
		{
			if(connection == null)
			{
				connection=con.getdbConnection();
			}
			try
			{
				checkcon = connection.prepareStatement(ConnectInit.queries.getProperty("check_stk_exchange"));
				checkcon.setString(1,name);
				//checkcon.setInt(2,cont_id_int);
				rs1 = checkcon.executeQuery();
				while(rs1.next())
				{
					nm1 = rs1.getString(1);
					nm2	= rs1.getInt(2);	
				}
			if(nm1 != null && nm1.trim().length() != 0){	
				if(nm1.equalsIgnoreCase(name))
				{
					ans = 1;
				}
			  }
			if(str.equals("update")){
					ans=nm2;
				}
				rs1.close();
				checkcon.close();
			}catch(Exception e)
			{
				Logging.error("Error check() :"+e);
			}
			return ans;
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
	public int checkData_code(String cont_id,String operation,String code)
	{
		PreparedStatement checkcon;
		ResultSet rs1;
		int ans=0;
		
		int cont_id_int = Integer.parseInt(getSelectCountry());
		String nm1 = null;
		int nm2=0;
		connection=null;
		try
		{
			if(connection == null)
			{
				connection=con.getdbConnection();
			}
			try
			{
				checkcon = connection.prepareStatement(ConnectInit.queries.getProperty("check_stk_exchange_duplicate_code"));
				checkcon.setString(1,code);
				rs1 = checkcon.executeQuery();
				while(rs1.next())
				{
					ans = Integer.parseInt(rs1.getString(1));
				}
				rs1.close();
				checkcon.close();
			}catch(Exception e)
			{
				Logging.error("Error check() :"+e);
			}
			return ans;
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
	/**
	 * @return Returns the ex_actions.
	 */
	public Vector getEx_actions() {
		return ex_actions;
	}
	/*
	 * Function to populate the list .
	 */
	public void setEx_actions() 
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
			if(connection == null)
			{
				connection=con.getdbConnection();
			}
			try
			{
				pst = connection.prepareStatement(ConnectInit.queries.getProperty("sel_identifier_list"));
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
			try{
				if(connection!=null)
					connection.close();
			}catch(Exception ee){
				Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			}
		}
	}
}
