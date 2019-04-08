package harrier.income.com.report;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import java.io.*;
import app.*;


/**
 * @author abhijit b.
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FileDownLoadForm extends ActionForm{
	Logger Logging = Logger.getLogger(FileDownLoadForm.class);
	String file_name=null, index=null, type=null, pathf=null,from=null, to=null, var1=null;
	
	Vector dataVec=new Vector();
	
	
	File file = new File (pathf);
	
//	app.Connect con=new app.Connect();
	 /**RESEST ALL FORM FIELDS**/
	  public void reset(ActionMapping mapping,HttpServletRequest request){
	  	file_name=null; index=null; type=null; pathf=null;
	  	from=null; to=null;var1=null;
	  	
	  }
	  
	  /**
	   * VALIDATE FORM DATA
	  * **/
	  public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request)
	  {
	  	ActionErrors errors = new ActionErrors();
	  	return errors;
	  }  
	  
	  
	  
	/**
	 * @return Returns the file_name.
	 */
	public String getFile_name() {
		return file_name;
	}
	/**
	 * @param file_name The file_name to set.
	 */
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	/**
	 * @return Returns the pathf.
	 */
	public String getPathf() {
		return pathf;
	}
	/**
	 * @param pathf The pathf to set.
	 */
	public void setPathf(String pathf) {
		this.pathf = pathf;
	}
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		MakeExcel obj = new MakeExcel();
				
		int typeVal = Integer.parseInt(type);
		
		switch(typeVal){
		
			case 1: 
				break;
			case 6:
				Logging.debug(" case 6");
				obj.create_file_for_Stock_details(index,type,dataVec,from,to);
				//response.setHeader ("Content-Disposition", "attachment; filename=\"StockHighLow.xls\"");
				break;
			default:
				break;
		}
		
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return Returns the index.
	 */
	public String getIndex() {
		return index;
	}
	/**
	 * @param index The index to set.
	 */
	public void setIndex(String index) {
		this.index = index;
	}
	/**
	 * @return Returns the dataVec.
	 */
	public Vector getDataVec() {
		return dataVec;
	}
	/**
	 * @param dataVec The dataVec to set.
	 */
	public void setDataVec(Vector dataVec) {
		this.dataVec = dataVec;
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
}	  
	