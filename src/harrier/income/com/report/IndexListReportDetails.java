/**
 * @author Manoj Adekar
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;

import org.apache.log4j.Logger;

public class IndexListReportDetails {
	Logger Logging = Logger.getLogger(IndexListReportDetails.class);
	private String indexname;
	private String indexid;
	private String status;
	private String current;
	private String indexclosing;
	private String indexdate;
	private String indextime;
	private String vachange;
	private String minmax;
	private String oneweek;
	private String onemonth;
	private String check1;
	private String status1W;
	private String status1M;
	private String fwh;
	private String fwl;
	

	

	public IndexListReportDetails(String indexname,String indexid,String current,String status,String minmax,String oneweek,String onemonth,String indexclosing,String vachange,String indexdate,String check1,String status1W,String status1M,String fwh,String fwl)
	{
		this.indexid=indexid;
		this.indexname=indexname;
		this.current=current;
		this.indexclosing=indexclosing;
		this.vachange=vachange;
		this.status=status;
		this.indexdate=indexdate;
		this.minmax=minmax;
		this.onemonth=onemonth;
		this.oneweek=oneweek;
		this.check1=check1;
		this.status1W=status1W;
		this.status1M=status1M;
		this.fwh=fwh;
		this.fwl=fwl;
		
	}
	
	public String getFwh() {
		return fwh;
	}

	public void setFwh(String fwh) {
		this.fwh = fwh;
	}

	public String getFwl() {
		return fwl;
	}

	public void setFwl(String fwl) {
		this.fwl = fwl;
	}
	/**
	 * @return Returns the current.
	 */
	public String getCurrent() {
		return current;
	}
	/**
	 * @param current The current to set.
	 */
	public void setCurrent(String current) {
		this.current = current;
	}
	
	/**
	 * @return Returns the indexclosing.
	 */
	public String getIndexclosing() {
		return indexclosing;
	}
	/**
	 * @param indexclosing The indexclosing to set.
	 */
	public void setIndexclosing(String indexclosing) {
		this.indexclosing = indexclosing;
	}
	
	
	/**
	 * @return Returns the indexdate.
	 */
	public String getIndexdate() {
		return indexdate;
	}
	/**
	 * @param indexdate The indexdate to set.
	 */
	public void setIndexdate(String indexdate) {
		this.indexdate = indexdate;
	}
	
	/**
	 * @return Returns the indexid.
	 */
	public String getIndexid() {
		Logging.debug(" ******** value oof indexid "+ indexid);
		return indexid;
	}
	/**
	 * @param indexid The indexid to set.
	 */
	public void setIndexid(String indexid) {
		this.indexid = indexid;
	}

	/**
	 * @return Returns the indexname.
	 */
	public String getIndexname() {
		return indexname;
	}
	/**
	 * @param indexname The indexname to set.
	 */
	public void setIndexname(String indexname) {
		this.indexname = indexname;
	}
	
	/**
	 * @return Returns the indextime.
	 */
	public String getIndextime() {
		return indextime;
	}
	/**
	 * @param indextime The indextime to set.
	 */
	public void setIndextime(String indextime) {
		this.indextime = indextime;
	}
	
	/**
	 * @return Returns the vachange.
	 */
	public String getVachange() {
		return vachange;
	}
	/**
	 * @param vachange The vachange to set.
	 */
	public void setVachange(String vachange) {
		this.vachange = vachange;
	}
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public String getMinmax() {
		return minmax;
	}

	public void setMinmax(String minmax) {
		this.minmax = minmax;
	}

	public String getOnemonth() {
		return onemonth;
	}

	public void setOnemonth(String onemonth) {
		this.onemonth = onemonth;
	}

	public String getOneweek() {
		return oneweek;
	}

	public void setOneweek(String oneweek) {
		this.oneweek = oneweek;
	}

	public String getCheck1() {
		return check1;
	}

	public void setCheck1(String check1) {
		this.check1 = check1;
	}

	public String getStatus1M() {
		return status1M;
	}

	public void setStatus1M(String status1M) {
		this.status1M = status1M;
	}

	public String getStatus1W() {
		return status1W;
	}

	public void setStatus1W(String status1W) {
		this.status1W = status1W;
	}

}
	