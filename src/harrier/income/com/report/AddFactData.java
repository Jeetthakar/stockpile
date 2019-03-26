/*
 * Created on Jun 8, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;

/**
 * @author neha
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AddFactData {

	public String var=null;
	public String type=null;
	public String iename=null;
	public String from=null;
	public String to=null;
	public String idxname=null;
	public AddFactData()
	{
		
	}
	
	public AddFactData(String var,String type,String iename,String from,String to,String idxname){
		
		this.var=var;
		this.type=type;
		this.iename=iename;
		this.from=from;
		this.to=to;
		this.idxname=idxname;
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
	 * @return Returns the idxname.
	 */
	public String getIdxname() {
		return idxname;
	}
	/**
	 * @param idxname The idxname to set.
	 */
	public void setIdxname(String idxname) {
		this.idxname = idxname;
	}
	/**
	 * @return Returns the iename.
	 */
	public String getIename() {
		return iename;
	}
	/**
	 * @param iename The iename to set.
	 */
	public void setIename(String iename) {
		this.iename = iename;
	}
	/**
	 * @return Returns the to.
	 */
	public String getTo() {
		return to;
	}
	/**
	 * @param to The to to set.
	 */
	public void setTo(String to) {
		this.to = to;
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
	 * @return Returns the var.
	 */
	public String getVar() {
		return var;
	}
	/**
	 * @param var The var to set.
	 */
	public void setVar(String var) {
		this.var = var;
	}
}
