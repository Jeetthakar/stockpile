package subscription.form;

/**
 * @author Chandrashekhar M.  Dehankar 
 * @Date   November 5, 2008 11:32:22 AM 
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

import app.Connect;

import com.harrier.initializeation.ConnectInit;

public class subscriptionForm extends ActionForm {
	// This Program is Bean class to store the registration form information.
Logger Logging = Logger.getLogger(subscriptionForm.class);
	private String subscriprion_id;

	private String validity;

	private String stock;

	private String cost;

	private String subscription_name;

	private Collection subscriptionList;

	private int[] subscriprion_id_list;

	private String textduration;

	private boolean agree = true;

	public boolean isAgree() {
		return agree;
	}

	public void setAgree(boolean t) {
		this.agree = true;
	}

	public String getSubscription_name() {
		return subscription_name;
	}

	public void setSubscription_name(String subscription_name) {
		this.subscription_name = subscription_name;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getSubscriprion_id() {
		return subscriprion_id;
	}

	public void setSubscriprion_id(String subscriprion_id) {
		this.subscriprion_id = subscriprion_id;

	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public Collection getSubscriptionList() {
		/*This Function Pre-populate the Data On Jsp*/
		
		Collection col = null;
		Connect con = ConnectInit.getConnect();
		Connection conn = con.getdbConnection();
		try {
			
			Vector vec2 = new Vector(10);

			PreparedStatement pst;
			ResultSet rs = null;
			pst = conn.prepareStatement("select *  from subscription_master  where subscription_id!=1 order by subscription_id");
			rs = pst.executeQuery();

			while (rs.next()) {

				subscriptionForm t = new subscriptionForm();
				t.setSubscriprion_id(rs.getString(1));
				t.setValidity(rs.getString(2));
				t.setStock(rs.getString(3));
				t.setCost(rs.getString(4));
				t.setSubscription_name(rs.getString(5));
				t.setTextduration((rs.getString(6)));
				vec2.add(Integer.parseInt(rs.getString(1)) - 2, t);
				t = null;

			}

			col = vec2;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
			Logging.debug(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				Logging.debug(e);
			}
		}

		return col;
	}

	public void setSubscriptionList(Collection subscriptionList) {
		this.subscriptionList = subscriptionList;
	}

	public int[] getSubscriprion_id_list() {
		return subscriprion_id_list;
	}

	public void setSubscriprion_id_list(int[] subscriprion_id_list) {
		this.subscriprion_id_list = subscriprion_id_list;
	}

	public String getTextduration() {
		return textduration;
	}

	public void setTextduration(String textduration) {
		this.textduration = textduration;
	}

}
