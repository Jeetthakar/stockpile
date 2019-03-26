/*
 * Created on Sep 9, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSystemConfigurator extends ActionForm{
	private double d_alertPercentage;
	private double d_rejectPercentage;
	private long l_compInterval;
	private long l_intraDayInterval;
	private long l_maxCompany;
	private long l_monitorRefresh;
	private long l_precisionValue;
	private long i_rateOfPriceFeed;
	private String s_custLogoPath;
	private String s_custName;
	private String s_nameLogoHorizon;
	private String s_nameLogoVertical;
	
	/**
	 * 
	 */
	public CSystemConfigurator() {
		// TODO Auto-generated constructor stub
		
	}
	
	/**
	 * @return Returns the d_alertPercentage.
	 */
	public double getD_alertPercentage() {
		return d_alertPercentage;
	}
	/**
	 * @param percentage The d_alertPercentage to set.
	 */
	public void setD_alertPercentage(double percentage) {
		d_alertPercentage = percentage;
	}
	/**
	 * @return Returns the d_rejectPercentage.
	 */
	public double getD_rejectPercentage() {
		return d_rejectPercentage;
	}
	/**
	 * @param percentage The d_rejectPercentage to set.
	 */
	public void setD_rejectPercentage(double percentage) {
		d_rejectPercentage = percentage;
	}
	/**
	 * @return Returns the i_rateOfPriceFeed.
	 */
	public long getI_rateOfPriceFeed() {
		return i_rateOfPriceFeed;
	}
	/**
	 * @param ofPriceFeed The i_rateOfPriceFeed to set.
	 */
	public void setI_rateOfPriceFeed(long ofPriceFeed) {
		i_rateOfPriceFeed = ofPriceFeed;
	}
	/**
	 * @return Returns the l_compInterval.
	 */
	public long getL_compInterval() {
		return l_compInterval;
	}
	/**
	 * @param interval The l_compInterval to set.
	 */
	public void setL_compInterval(long interval) {
		l_compInterval = interval;
	}
	/**
	 * @return Returns the l_intraDayInterval.
	 */
	public long getL_intraDayInterval() {
		return l_intraDayInterval;
	}
	/**
	 * @param dayInterval The l_intraDayInterval to set.
	 */
	public void setL_intraDayInterval(long dayInterval) {
		l_intraDayInterval = dayInterval;
	}
	/**
	 * @return Returns the l_maxCompany.
	 */
	public long getL_maxCompany() {
		return l_maxCompany;
	}
	/**
	 * @param company The l_maxCompany to set.
	 */
	public void setL_maxCompany(long company) {
		l_maxCompany = company;
	}
	/**
	 * @return Returns the l_monitorRefresh.
	 */
	public long getL_monitorRefresh() {
		return l_monitorRefresh;
	}
	/**
	 * @param refresh The l_monitorRefresh to set.
	 */
	public void setL_monitorRefresh(long refresh) {
		l_monitorRefresh = refresh;
	}
	/**
	 * @return Returns the l_precisionValue.
	 */
	public long getL_precisionValue() {
		return l_precisionValue;
	}
	/**
	 * @param value The l_precisionValue to set.
	 */
	public void setL_precisionValue(long value) {
		l_precisionValue = value;
	}
	/**
	 * @return Returns the s_custLogoPath.
	 */
	public String getS_custLogoPath() {
		return s_custLogoPath;
	}
	/**
	 * @param logoPath The s_custLogoPath to set.
	 */
	public void setS_custLogoPath(String logoPath) {
		s_custLogoPath = logoPath;
	}
	/**
	 * @return Returns the s_custName.
	 */
	public String getS_custName() {
		return s_custName;
	}
	/**
	 * @param name The s_custName to set.
	 */
	public void setS_custName(String name) {
		s_custName = name;
	}
	/**
	 * @return Returns the s_nameLogoHorizon.
	 */
	public String getS_nameLogoHorizon() {
		return s_nameLogoHorizon;
	}
	/**
	 * @param logoHorizon The s_nameLogoHorizon to set.
	 */
	public void setS_nameLogoHorizon(String logoHorizon) {
		s_nameLogoHorizon = logoHorizon;
	}
	/**
	 * @return Returns the s_nameLogoVertical.
	 */
	public String getS_nameLogoVertical() {
		return s_nameLogoVertical;
	}
	/**
	 * @param logoVertical The s_nameLogoVertical to set.
	 */
	public void setS_nameLogoVertical(String logoVertical) {
		s_nameLogoVertical = logoVertical;
	}
}
