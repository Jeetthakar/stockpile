/*
 * Created on Mar 19, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.masters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import app.Connect;

import com.harrier.initializeation.ConnectInit;

/**
 * @author naresh
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class IndexTypeAction extends Action {
	Logger Logging = Logger.getLogger(IndexTypeAction.class);
	ResultSet rs1, seqno;

	Connection con = null;

	Connect connect = ConnectInit.getConnect();

	PreparedStatement pseq, psub, checkcon;

	public static final String FORWARD_start = "success";

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		IndexType typ = (IndexType) form;
		String name = typ.getName();
		String desc = typ.getDesc();
		String update = typ.getUpdate();
		String new1 = typ.getNew1();
		//int ids = typ.getId_check(); //Commented on 08-08-06
		int ids = IndexType.id_val;
		String type = typ.getType_code();
		String index_segment=typ.getSelectSegment();
		/**
		 * check if user wants to update data. If yes then call update funtion
		 * else check if user wants to add new element. after update or add
		 * reset bean variables.
		 */
		if (update != null) {
			typ = (IndexType) form;
			name = typ.getName();
			desc = typ.getDesc();
			ids = IndexType.id_val;
			updateData(ids, name, desc,index_segment,type);
			typ.setUpdate(null);
			//typ.setIdentifier_desc(null);
			typ.setIdname(0);
			IndexType.id_val = 0; //Changed on 08-08-06
			IndexType.setCon_flag("U");
			return (new ActionForward(
					"/pages/masters/roleAdded.jsp?flag=IndexType&value=update"));
		} else if (new1 != null) {
			submitData(name, desc, type,index_segment);
			typ.setNew1(null);
			typ.setIdname(0);
			typ.setIdentifier_desc(null);
			IndexType.id_val = 0;  //Changed on 08-08-06
			IndexType.setCon_flag("N");
			return (new ActionForward(
					"/pages/masters/roleAdded.jsp?flag=IndexType&value=insert"));
		}
		return mapping.findForward(FORWARD_start);
	}

	/**
	 * Ones data is validated, record is updated or added as per the action
	 * selected by the user. Appropriate messges are displayed to the user for
	 * the action.
	 *  
	 */
	public void submitData(String stname, String detail, String typ_code,String index_segment) {
		int check = checkData(stname);
		if (check != 1) {
			int curr_seq = 0;
			int r_value = 0;
			con = null;
			try {
				if (con == null)
					con = connect.getdbConnection();
				try {
					String seqQuery = "select nextval('index_type_id')";
					pseq = con.prepareStatement(seqQuery);
					seqno = pseq.executeQuery();
					while (seqno.next()) {
						curr_seq = Integer.parseInt(seqno.getString(1));
						Logging.debug("Present Seq No. " + curr_seq);
					}
					psub = con.prepareStatement(ConnectInit.queries
							.getProperty("insert_index_type"));
					psub.setInt(1, curr_seq);
					psub.setString(2, stname);
					psub.setString(3, detail);
					psub.setString(4, typ_code);
					psub.setString(5,index_segment);
					psub.executeUpdate();
					r_value = 1;
					seqno.close();
					pseq.close();
				} catch (Exception et) {
					Logging.error("Error :" + et);
				}
			} finally {
				try {
					if (con != null)
						con.close();
				} catch (Exception ee) {
					Logging.error(" Error : Unable to close Connection "
							+ ee.getMessage());
				}
			}
		}
	}

	/**
	 * function to check duplicate entries in the table. funtion is called only
	 * when new data is inserted.
	 */
	public int checkData(String name) {
		int ans = 0;
		String nm1 = null;
		con = null;
		if (con == null)
			con = connect.getdbConnection();
		try {
			checkcon = con.prepareStatement(ConnectInit.queries
					.getProperty("check_index_type"));
			checkcon.setString(1, name);
			rs1 = checkcon.executeQuery();
			while (rs1.next()) {
				nm1 = rs1.getString(1);
			}
			if (nm1 != null) {
				if (nm1.equalsIgnoreCase(name)) {
					ans = 1;
				}
			}
			rs1.close();
			checkcon.close();
			return ans;
		} catch (Exception e) {
			Logging.error("Error check() :" + e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
		return ans;
	}

	public void updateData(int id, String stname, String detail,String index_segment,String type) {
		int id_int = id;
		PreparedStatement pseq, psub;
		ResultSet seqno;
		int curr_seq = 0;
		int r_value = 0;
		con = null;
		try {
			if (con == null)
				con = connect.getdbConnection();
			try {
				psub = con.prepareStatement(ConnectInit.queries
						.getProperty("update_index_type"));
				psub.setString(1, stname);
				psub.setString(2, detail);
				psub.setString(3,type);
				psub.setString(4,index_segment);
				psub.setInt(5, id_int);
				psub.executeUpdate();
				psub.close();
			/*	update_index_type=update index_type set index_type_name \= ?,
						description \= ? 
				UPDATE index_type  SET  index_type_name\=?, description\=?, index_type_code\=?, 
								       segment\=? where index_type_id \= ? */
			} catch (Exception et) {
				Logging.error("Error :" + et);
			}
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "
						+ ee.getMessage());
			}
		}
	}
}