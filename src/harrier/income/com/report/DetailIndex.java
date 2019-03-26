package harrier.income.com.report;

import org.apache.log4j.Logger;

public class DetailIndex
{
	Logger Logging = Logger.getLogger(DetailIndex.class);
	private String indexname;
	private String indexid;
	private String status;
	private String current;
	private String indexopen;
	private String indexhigh;
	private String indexlow;
	private String indexclosing;
	private String tmcv;
	private String divisor;
	private String currency;
	private String indexdate;
	private String indexclsv;
	private String indextime;
	private String vachange;
	public DetailIndex(String indexname,String indexid,String current,String status,String indexopen,String indexhigh,String indexlow,String indexclosing,String vachange,String tmcv,String divisor,String currency,String indexdate,String indexclsv,String indextime)
	{
		this.indexid=indexid;
		this.indexname=indexname;
		this.currency=currency;
		this.current=current;
		this.indexopen=indexopen;
		this.indexhigh=indexhigh;
		this.indexlow=indexlow;
		this.indexclosing=indexclosing;
		this.vachange=vachange;
		this.tmcv=tmcv;
		this.status=status;
		this.divisor=divisor;
		this.indexdate=indexdate;
		this.indexclsv=indexclsv;
		this.indextime=indextime;
	}
	/**
	 * @return Returns the currency.
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * @param currency The currency to set.
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
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
	 * @return Returns the divisor.
	 */
	public String getDivisor() {
		return divisor;
	}
	/**
	 * @param divisor The divisor to set.
	 */
	public void setDivisor(String divisor) {
		this.divisor = divisor;
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
	 * @return Returns the indexclsv.
	 */
	public String getIndexclsv() {
		return indexclsv;
	}
	/**
	 * @param indexclsv The indexclsv to set.
	 */
	public void setIndexclsv(String indexclsv) {
		this.indexclsv = indexclsv;
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
	 * @return Returns the indexhigh.
	 */
	public String getIndexhigh() {
		return indexhigh;
	}
	/**
	 * @param indexhigh The indexhigh to set.
	 */
	public void setIndexhigh(String indexhigh) {
		this.indexhigh = indexhigh;
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
	 * @return Returns the indexlow.
	 */
	public String getIndexlow() {
		return indexlow;
	}
	/**
	 * @param indexlow The indexlow to set.
	 */
	public void setIndexlow(String indexlow) {
		this.indexlow = indexlow;
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
	 * @return Returns the indexopen.
	 */
	public String getIndexopen() {
		return indexopen;
	}
	/**
	 * @param indexopen The indexopen to set.
	 */
	public void setIndexopen(String indexopen) {
		this.indexopen = indexopen;
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
	 * @return Returns the tmcv.
	 */
	public String getTmcv() {
		return tmcv;
	}
	/**
	 * @param tmcv The tmcv to set.
	 */
	public void setTmcv(String tmcv) {
		this.tmcv = tmcv;
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
}
	