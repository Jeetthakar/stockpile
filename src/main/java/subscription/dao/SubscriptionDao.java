package subscription.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;

import subscription.form.UserAccountInfo;
import app.Connect;

import com.harrier.initializeation.ConnectInit;

public class SubscriptionDao {
	Logger Logging = Logger.getLogger(SubscriptionDao.class);

	/*
	 * public synchronized String checkexistuserSubscription(String
	 * userid,String sub_id ) {
	 * 
	 * PreparedStatement pst=null; Connection con=null; String status="success";
	 * ResultSet rs=null;
	 * 
	 * try {
	 * 
	 * //ist we need to check wether sub is already exist
	 * 
	 * Connect c=ConnectInit.getConnect(); con=c.getdbConnection(); Properties
	 * queries=null; queries=c.queries; String Query =
	 * queries.getProperty("check_user_subscription"); pst =
	 * con.prepareStatement(Query); pst.setString(1,userid );
	 * pst.setString(2,sub_id ); rs=pst.executeQuery() ; int count=0;
	 * while(rs.next()) { count++;
	 * 
	 * } if(count==1) { status="exist"; }
	 * 
	 * pst.close() ;
	 * 
	 * 
	 * 
	 * } catch (SQLException e) { status="datafail"; System.out.print(
	 * "subscription.dao.SubscriptionDao.AdduserSubscription Fail due to.." +
	 * e);
	 * 
	 * } finally { try { con.close(); } catch (SQLException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } }
	 * 
	 * 
	 * return status;
	 * 
	 * 
	 * }
	 */
	//new method ccreated by samiksha with new parameter of transaction Id
	public synchronized String AdduserSubscription(String userid,
			String sub_id, Connection con,String transactionId) {

		PreparedStatement pst = null;

		String status = "success";
		// Connection con=null;

		if (status.equalsIgnoreCase("success")) {

			try {
				Connect c = ConnectInit.getConnect();
				if (con == null) {
					con = c.getdbConnection();
				}

				// ist we need to check wheather its a paid subscription
				// if its paid then disable the free subsciption
				//commented by samiksha
				// if (sub_id != "1") {
				if (!sub_id.equals("1")) {
					// update status of free subscription for this user
//					commented by samiksha
//					updatefreesubscription(userid, "1", con);
					updatefreesubscription(userid,sub_id, con);
				}

				// con.setAutoCommit(false);
				// Properties queries=null;
				// queries=ConnectInit.queries;
				String Query = ConnectInit.queries
						.getProperty("insert_user_subscription_new");
				pst = con.prepareStatement(Query);
				SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
				Date dt = new Date();

				pst.setString(1, userid);
				pst.setString(2, sub_id);
				pst.setString(3, fr.format(dt).toString());
				pst.setString(4, transactionId);
				pst.executeUpdate();

				// pst.execute();

				// if it is no a free subscription then change role of user to
				// subscribe user
				Integer i = new Integer(1);
				Integer i2 = new Integer(sub_id);

				if (i2.intValue() != i.intValue()) {
					Query = ConnectInit.queries.getProperty("update_user_role");
					pst = con.prepareStatement(Query);
					pst.setString(1, userid);
					pst.executeUpdate();
					pst.close();

				}

			} catch (SQLException e) {
				status = "datafail";
				Logging.debug("subscription.dao.SubscriptionDao.AdduserSubscription Fail due to.."
						+ e);

			}

		}

		return status;

	}
	public synchronized String AdduserSubscription(String userid,
			String sub_id, Connection con) {

		PreparedStatement pst = null;

		String status = "success";
		// Connection con=null;

		if (status.equalsIgnoreCase("success")) {

			try {
				Connect c = ConnectInit.getConnect();
				if (con == null) {
					con = c.getdbConnection();
				}

				// ist we need to check wheather its a paid subscription
				// if its paid then disable the free subsciption
				//commented by samiksha
				// if (sub_id != "1") {
				if (!sub_id.equals("1")) {
					// update status of free subscription for this user
//					commented by samiksha
//					updatefreesubscription(userid, "1", con);
					updatefreesubscription(userid,sub_id, con);
				}

				// con.setAutoCommit(false);
				// Properties queries=null;
				// queries=ConnectInit.queries;
				String Query = ConnectInit.queries
						.getProperty("insert_user_subscription");
				pst = con.prepareStatement(Query);
				SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
				Date dt = new Date();

				pst.setString(1, userid);
				pst.setString(2, sub_id);
				pst.setString(3, fr.format(dt).toString());
				pst.executeUpdate();

				// pst.execute();

				// if it is no a free subscription then change role of user to
				// subscribe user
				Integer i = new Integer(1);
				Integer i2 = new Integer(sub_id);

				if (i2.intValue() != i.intValue()) {
					Query = ConnectInit.queries.getProperty("update_user_role");
					pst = con.prepareStatement(Query);
					pst.setString(1, userid);
					pst.executeUpdate();
					pst.close();

				}

			} catch (SQLException e) {
				status = "datafail";
				Logging.debug("subscription.dao.SubscriptionDao.AdduserSubscription Fail due to.."
						+ e);

			}

		}

		return status;

	}

	public synchronized Collection UserAccountInfo(String userid) {

		Collection col = null;
		Connect con = ConnectInit.getConnect();
		Connection conn = con.getdbConnection();
		try {
			Vector vec = new Vector(15);

			PreparedStatement pst;
			ResultSet rs = null;
			// Properties queries=null;
			// queries=ConnectInit.queries;

			String Query = ConnectInit.queries.getProperty("user_account_info");
			pst = conn.prepareStatement(Query);
			pst.setString(1, userid);
			// select s.subscription_id,u.sub_date
			// ,duration,age(CURRENT_DATE,to_date(u.sub_date,'yyyy mm dd')) from
			// user_subscription u ,subscription_master s where
			// userid='cmdehankar@gmail.com' and
			// s.subscription_id=u.subscription_id
			rs = pst.executeQuery();
			while (rs.next()) {
				/*
				 * public class UserAccountInfo { private String subname;
				 * private String subid; private String subdate; private String
				 * validfor;
				 */
				UserAccountInfo t = new UserAccountInfo();

				Logging.debug(rs.getString(1) + "--" + rs.getString(2) + "--"
						+ rs.getString(3) + "--" + rs.getString(4));
				t.setSubid(rs.getString(1));
				t.setSubdate(rs.getString(2));
				t.setVailidity(rs.getString(3));
				t.setSubname(rs.getString(5));
				int pos = rs.getString(4).trim().indexOf(" ");
				Logging.debug("space at " + pos);
				String days = rs.getString(4).substring(0, pos);
				int over = Integer.parseInt(days);

				// int total=Integer.parseInt(rs.getString(4));
				int valid = Integer.parseInt(rs.getString(3).trim()) - over;
				Integer i = new Integer(valid);
				days = i.toString();

				t.setValidfor(days);

				// t.setSubdate(days);
				vec.add(t);

				t = null;

			}

			col = vec;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			Logging.debug(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				Logging.debug(e);
			}
		}

		return col;

	}

	/*
	 * select
	 * us.subscription_id,us.sub_date,sm.duration,sm.textduration,sm.charges
	 * ,sm.sub_name from user_subscription us,subscription_master sm where
	 * us.subscription_id=sm.subscription_id and us.user_id=61
	 */

	public String canceluserSubscription(String userid, String sub_name) {

		PreparedStatement pst = null;
		Connection con = null;
		String status = "success";
		ResultSet rs = null;

		if (status.equalsIgnoreCase("success")) {

			try {
				Connect c = ConnectInit.getConnect();
				con = c.getdbConnection();
				// Properties queries=null;
				// queries=ConnectInit.queries;
				String Query = ConnectInit.queries
						.getProperty("select_subscription_name");
				// String
				// Query="select c.user_id ,c.subscription_id from subscription_master a ,users b ,user_subscription c where sub_name=? and username=? and a.subscription_id=c.subscription_id and b.user_id=c.user_id";
				pst = con.prepareStatement(Query);
				con.setAutoCommit(false);

				pst.setString(1, sub_name.trim());
				pst.setString(2, userid.trim());
				rs = pst.executeQuery();
				String user_id = null;
				String sub_id = null;

				while (rs.next()) {
					user_id = rs.getString(1);
					sub_id = rs.getString(2);

				}

				if (sub_id == null) {
					status = "notexist";
				} else {

					Query = ConnectInit.queries
							.getProperty("cancel_user_subscription");
					// delete from user_subscription where user_id=? and
					// subscription_id=?
					pst = con.prepareStatement(Query);
					pst.setString(1, user_id);
					pst.setString(2, sub_id);
					pst.executeUpdate();

					Query = ConnectInit.queries
							.getProperty("count_user_subscription");
					pst = con.prepareStatement(Query);
					pst.setString(1, user_id);
					rs = pst.executeQuery();
					String count = null;
					while (rs.next()) {// if count =0 then we change user role
										// to registor user
						count = rs.getString(1);

					}

					if (count.equals("0")) { // we change user role to registor
												// user and role also
						Query = ConnectInit.queries
								.getProperty("update_subscription_free");
						pst = con.prepareStatement(Query);
						pst.setString(1, user_id);
						pst.executeUpdate();

						Query = ConnectInit.queries
								.getProperty("user_role_registor");
						pst = con.prepareStatement(Query);
						pst.setString(1, user_id);
						pst.executeUpdate();
						con.setAutoCommit(true);
						pst.close();

					} else {
						con.setAutoCommit(true);
						pst.close();

					}

				}

			} catch (SQLException e) {
				status = "datafail";
				Logging.debug("subscription.dao.SubscriptionDao.canceluserSubscription Fail due to.."
						+ e);

			} finally {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					Logging.debug(e);
				}
			}

		}

		return status;

	}

	public void updatefreesubscription(String user_id, String sub_id,
			Connection con) {

		PreparedStatement pst = null;
		String status = "success";
		// ResultSet rs=null;

		try {
			Connect c = ConnectInit.getConnect();
			// con=c.getdbConnection();
			// Properties queries=null;
			// queries=ConnectInit.queries;
			String Query = ConnectInit.queries
					.getProperty("update_subscription_status");
			pst = con.prepareStatement(Query);
			pst.setString(1, user_id);
			pst.setString(2, sub_id);
			pst.executeUpdate();
			// int count=0;
			pst.close();

		} catch (SQLException e) {
			status = "datafail";
			Logging.debug("subscription.dao.SubscriptionDao.updatefreesubscription Fail due to.."
					+ e);

		}

	}

}
