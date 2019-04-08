/*
 * Created on Mar 3, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app; 
 
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import java.util.*;
/**
 * @author rahul
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EmailReportForm extends ActionForm
{
	Logger Logging = Logger.getLogger(EmailReportForm.class);
	String to,cc,desc,attachment,subject,rname,varid,username,att,cas,switch_type;
	String to_date,from,var1,file;
	Vector vec;
	
	/**
	 * @return Returns the file.
	 */
	public String getFile() {
		return file;
	}
	/**
	 * @param file The file to set.
	 */
	public void setFile(String file) {
		this.file = file;
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
	 * @return Returns the to_date.
	 */
	public String getTo_date() {
		return to_date;
	}
	/**
	 * @param to_date The to_date to set.
	 */
	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}
	/**
	 * @return Returns the var1.
	 */
	public String getVar1() {
		return var1;
	}
	/**
	 * @param var1 The var1 to set.
	 */
	public void setVar1(String var1) {
		this.var1 = var1;
	}
	/**
	 * @return Returns the vec.
	 */
	public Vector getVec() {
		return vec;
	}
	/**
	 * @param vec The vec to set.
	 */
	public void setVec(Vector vec) {
		Logging.debug("size of vector : "+vec.size());
		this.vec = vec;
	}
	/**
	 * @return Returns the switch_type.
	 */
	public String getSwitch_type() {
		return switch_type;
	}
	/**
	 * @param switch_type The switch_type to set.
	 */
	public void setSwitch_type(String switch_type) {
		this.switch_type = switch_type;
	}
	/**
	 * @return Returns the cas.
	 */
	public String getCas() {
		return cas;
	}
	/**
	 * @param cas The cas to set.
	 */
	public void setCas(String cas) {
		this.cas = cas;
	}
	/**
	 * @return Returns the att.
	 */
	public String getAtt() {
		return att;
	}
	/**
	 * @param att The att to set.
	 */
	public void setAtt(String att) {
		this.att = att;
	}
	/**
	 * @return Returns the username.
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username The username to set.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return Returns the attachment.
	 */
	public String getAttachment() {
		return attachment;
	}
	/**
	 * @param attachment The attachment to set.
	 */
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	/**
	 * @return Returns the varid.
	 */
	public String getVarid() {
		return varid;
	}
	/**
	 * @param varid The varid to set.
	 */
	public void setVarid(String varid) {
		this.varid = varid;
	}
	/**
	 * @return Returns the cc.
	 */
	public String getCc() {
		return cc;
	}
	/**
	 * @param cc The cc to set.
	 */
	public void setCc(String cc) {
		this.cc = cc;
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
	/**
	 * @return Returns the rname.
	 */
	public String getRname() {
		return rname;
	}
	/**
	 * @param rname The rname to set.
	 */
	public void setRname(String rname) {
		this.rname = rname;
	}
	/**
	 * @return Returns the subject.
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject The subject to set.
	 */
	public void setSubject(String subject) {
		this.subject = subject;
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
	
	
}
