/*
 * Created on Sep 5, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com;
import java.util.*;
/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CAuditTrail {
	private String s_corporateActionName;
	private String s_activityName;
	private String s_userName;
	private Date dt_activityDate;
	private Date dt_activityTime;
	
	/**
	 * 
	 */
	public CAuditTrail() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @return Returns the dt_activityDate.
	 */
	public Date getDt_activityDate() {
		return dt_activityDate;
	}
	/**
	 * @param dt_activityDate The dt_activityDate to set.
	 */
	public void setDt_activityDate(Date dt_activityDate) {
		this.dt_activityDate = dt_activityDate;
	}
	/**
	 * @return Returns the dt_activityTime.
	 */
	public Date getDt_activityTime() {
		return dt_activityTime;
	}
	/**
	 * @param dt_activityTime The dt_activityTime to set.
	 */
	public void setDt_activityTime(Date dt_activityTime) {
		this.dt_activityTime = dt_activityTime;
	}
	/**
	 * @return Returns the s_activityName.
	 */
	public String getS_activityName() {
		return s_activityName;
	}
	/**
	 * @param name The s_activityName to set.
	 */
	public void setS_activityName(String name) {
		s_activityName = name;
	}
	/**
	 * @return Returns the s_corporateActionName.
	 */
	public String getS_corporateActionName() {
		return s_corporateActionName;
	}
	/**
	 * @param actionName The s_corporateActionName to set.
	 */
	public void setS_corporateActionName(String actionName) {
		s_corporateActionName = actionName;
	}
	/**
	 * @return Returns the s_userName.
	 */
	public String getS_userName() {
		return s_userName;
	}
	/**
	 * @param name The s_userName to set.
	 */
	public void setS_userName(String name) {
		s_userName = name;
	}
}
