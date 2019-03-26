/*
 * Created on Jan 25, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;
import org.apache.struts.action.ActionForm;

/**
 * @author pranoti
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class stocklist {
	String id;
	String sname;
	String action;
	String apply;
	String amount;
	String ratio_shr;
	String shr_offer;
	String percent;
	String no_share;
	String stkexname;
	String status;
	String applied;
	String iname;
	String basedate;
	String value;
	String desc;
	

	
	public stocklist(String id,String action,String sname,String apply,String amount,String ratio_shr,String shr_offer,String percent,String no_share)
	{
		this.id=id;
		this.sname=sname;
		this.action=action;
		this.apply=apply;
		this.amount=amount;
		this.ratio_shr=ratio_shr;
		this.shr_offer=shr_offer;
		this.percent=percent;
		this.no_share=no_share;
	}

	public stocklist(String id,String action,String stkexname,String sname,String apply,String applied,String amount,String percent,String ratio_shr,String shr_offer,String no_share,String status)
	{
		this.id=id;
		this.action=action;
		this.stkexname=stkexname;
		this.sname=sname;		
		this.apply=apply;
		this.applied=applied;
		this.amount=amount;
		this.percent=percent;
		this.ratio_shr=ratio_shr;
		this.shr_offer=shr_offer;		
		this.no_share=no_share;
		this.status=status;
	}	
	public stocklist(String id,String action,String iname,String stkexname,String sname,String apply,String applied,String basedate,String value,String status)
	{
		this.id=id;
		this.action=action;
		this.iname=iname;
		this.stkexname=stkexname;
		this.sname=sname;		
		this.apply=apply;
		this.applied=applied;
		this.basedate=basedate;
		this.value=value;
		this.status=status;		
	}
	
	public stocklist(String id,String stkexname,String sname,String apply,String amount,String percent,String ratio_shr,String shr_offer,String desc,String value,String status)
	{
		this.id=id;	
		this.stkexname=stkexname;
		this.sname=sname;		
		this.apply=apply;		
		this.amount=amount;
		this.percent=percent;
		this.ratio_shr=ratio_shr;
		this.shr_offer=shr_offer;		
		this.desc=desc;
		this.value=value;
		this.status=status;
	}
	
	public stocklist(String id,String iname,String sname,String action,String apply,String value,String status)
	{
		this.id=id;
		this.iname=iname;
		this.sname=sname;
		this.action=action;
		this.apply=apply;		
		this.value=value;
		this.status=status;
	}	
	
	public stocklist(String id,String action,String stkexname,String sname,String iname,String apply,String applied,String amount,String percent,String ratio_shr,String shr_offer,String value,String status)
	{
		this.id=id;
		this.action=action;
		this.stkexname=stkexname;
		this.sname=sname;
		this.iname=iname;
		this.apply=apply;
		this.applied=applied;
		this.amount=amount;
		this.percent=percent;
		this.ratio_shr=ratio_shr;
		this.shr_offer=shr_offer;		
		this.value=value;
		this.status=status;
	}	
	/**
	 * @return Returns the amount.
	 */
	public String getAmount() {
		return amount;
	}
	/**
	 * @param amount The amount to set.
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	/**
	 * @return Returns the no_share.
	 */
	public String getNo_share() {
		return no_share;
	}
	/**
	 * @param no_share The no_share to set.
	 */
	public void setNo_share(String no_share) {
		this.no_share = no_share;
	}
	/**
	 * @return Returns the percent.
	 */
	public String getPercent() {
		return percent;
	}
	/**
	 * @param percent The percent to set.
	 */
	public void setPercent(String percent) {
		this.percent = percent;
	}
	/**
	 * @return Returns the ratio_shr.
	 */
	public String getRatio_shr() {
		return ratio_shr;
	}
	/**
	 * @param ratio_shr The ratio_shr to set.
	 */
	public void setRatio_shr(String ratio_shr) {
		this.ratio_shr = ratio_shr;
	}
	/**
	 * @return Returns the shr_offer.
	 */
	public String getShr_offer() {
		return shr_offer;
	}
	/**
	 * @param shr_offer The shr_offer to set.
	 */
	public void setShr_offer(String shr_offer) {
		this.shr_offer = shr_offer;
	}	
	
/**
 * @return Returns the sname.
 */
public String getSname() {
	return sname;
}
/**
 * @param sname The sname to set.
 */
public void setSname(String sname) {
	this.sname = sname;
}
	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @return Returns the apply.
	 */
	public String getApply() {
		return apply;
	}
	/**
	 * @param apply The apply to set.
	 */
	public void setApply(String apply) {
		this.apply = apply;
	}
	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
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
	/**
	 * @return Returns the stkexname.
	 */
	public String getStkexname() {
		return stkexname;
	}
	/**
	 * @param stkexname The stkexname to set.
	 */
	public void setStkexname(String stkexname) {
		this.stkexname = stkexname;
	}
	/**
	 * @return Returns the applied.
	 */
	public String getApplied() {
		return applied;
	}
	/**
	 * @param applied The applied to set.
	 */
	public void setApplied(String applied) {
		this.applied = applied;
	}
	/**
	 * @return Returns the iname.
	 */
	public String getIname() {
		return iname;
	}
	/**
	 * @param iname The iname to set.
	 */
	public void setIname(String iname) {
		this.iname = iname;
	}
	/**
	 * @return Returns the basedate.
	 */
	public String getBasedate() {
		return basedate;
	}
	/**
	 * @param basedate The basedate to set.
	 */
	public void setBasedate(String basedate) {
		this.basedate = basedate;
	}
	/**
	 * @return Returns the value.
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value The value to set.
	 */
	public void setValue(String value) {
		this.value = value;
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
		this.desc = desc;
	}	
}
