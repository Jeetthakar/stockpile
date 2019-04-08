/*
 * Created on Apr 26, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;

import com.harrier.initializeation.ConnectInit;
/**  
 * @author sonali
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DatFileUploadForm extends ActionForm {
	Logger Logging = Logger.getLogger(DatFileUploadForm.class);
	private String d1=null;
	private String date=null;
	private String b1=null;
	private Vector exchangeCollection=null;
	private String fileType=null;
	private Vector fileTypeCollections=new Vector();
	private String hiddenVar=null;
	private FormFile theFile=null;
	private String fileName=" ";
	private String b2=null;
	private String b2var=null;
	private String indexname=null;
	Hashtable htable=new Hashtable();
	private static StringBuffer disp_buffer=new StringBuffer();
	private static StringBuffer save_buffer=new StringBuffer();
	
	/**RESEST ALL FORM FIELDS**/
	  public void reset(ActionMapping mapping,HttpServletRequest request){
	  	b2var=null;
	  	hiddenVar=null;
	  	date=null;
	  	d1=null;
	  }
	 
	
	
	
	/**
	 * @return Returns the b2var.
	 */
	public String getB2var() {
		return b2var;
	}
	/**
	 * @param b2var The b2var to set.
	 */
	public void setB2var(String b2var) {
		this.b2var = b2var;
	}
	/**
	 * @return Returns the b2.
	 */
	public String getB2() {
		return b2;
	}
	/**
	 * @param b2 The b2 to set.
	 */
	public void setB2(String b2) {
		this.b2 = b2;
	}
	/**
	 * @return Returns the fileName.
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName The fileName to set.
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return Returns the save_buffer.
	 */
	public static StringBuffer getSave_buffer() {
		return save_buffer;
	}
	/**
	 * @param save_buffer The disp_buffer to set.
	 */
	public static void setSave_buffer(StringBuffer save_buffer) {
		DatFileUploadForm.save_buffer = save_buffer;
	}
	/**
	 * @return Returns the disp_buffer.
	 */
	public static StringBuffer getDisp_buffer() {
		return disp_buffer;
	}
	/**
	 * @param disp_buffer The disp_buffer to set.
	 */
	public static void setDisp_buffer(StringBuffer disp_buffer) {
		DatFileUploadForm.disp_buffer = disp_buffer;
	}
	
	public Vector getExchangeCollection() {
		Vector temp=new Vector();
		Connect con=ConnectInit.getConnect();
		Connection connection=null;
		ResultSet rs=null;
		String query;

		//query=stock_exchange_list
		try{
			if(connection==null)
				connection=con.getdbConnection();
					query = ConnectInit.queries.getProperty("stock_exchange_online_list");
				 temp = new Vector();
				 temp.add(new LabelValueBean("Not Selected","0"));
				try {
					Statement stmt = connection.createStatement();
					rs = stmt.executeQuery(query);
					while (rs.next()) {
						temp.add(new LabelValueBean(rs.getString(2),rs.getString(1)));
					}
					exchangeCollection = temp;
					
				} catch (Exception e) {
					// TODO: handle exception
					Logging.error(" Error : "+e.getMessage());
										}
				    }catch(Exception e){
				    	Logging.error(" Error : "+e.getMessage());
				     }
				    finally{
				    	try{
				    		if(connection!=null)
				    			connection.close();
				    		}catch(Exception ee){				    			
				    			Logging.error(" Error : Unable to close Connection "+ee.getMessage());
				    		}
			    }

		return exchangeCollection;
	}

	/**
	 * @return Returns the b1.
	 */
	public String getB1() {
		return b1;
	}
	/**
	 * @param b1 The b1 to set.
	 */
	public void setB1(String b1) {
		this.b1 = b1;
	}
	/**
	 * @return Returns the d1.
	 */
	public String getD1() {
		return d1;
	}
	/**
	 * @param d1 The d1 to set.
	 */
	public void setD1(String d1) {
		this.d1 = d1;
	}
	/**
	 * @return Returns the histDate.
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param histDate The histDate to set.
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @param exchangeCollection The exchangeCollection to set.
	 */
	public void setExchangeCollection(Vector exchangeCollection) {
		this.exchangeCollection = exchangeCollection;
	}
	
	/**
	 * @return Returns the fileType.
	 */
	public String getFileType() {
		return fileType;
	}
	/**
	 * @param fileType The fileType to set.
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	/**
	 * @return Returns the fileTypeCollections.
	 */
	public Vector getFileTypeCollections() {
		fileTypeCollections=new Vector();
		int i=1;
		//fileTypeCollections.add(new Integer(i));
		fileTypeCollections.add(new LabelValueBean("Open_High_Low_Close_File",new Integer(i).toString()));
		return fileTypeCollections;
	}
	/**
	 * @param fileTypeCollections The fileTypeCollections to set.
	 */
	public void setFileTypeCollections(Vector fileTypeCollections) {
		this.fileTypeCollections = fileTypeCollections;
	}
	/**
	 * @return Returns the hiddenVar.
	 */
	public String getHiddenVar() {
		return hiddenVar;
	}
	/**
	 * @param hiddenVar The hiddenVar to set.
	 */
	public void setHiddenVar(String hiddenVar) {
		this.hiddenVar = hiddenVar;
	}
	/**
	 * @return Returns the theFile.
	 */
	public FormFile getTheFile() {
		return theFile;
	}
	/**
	 * @param theFile The theFile to set.
	 */
	public void setTheFile(FormFile theFile) {
		this.theFile = theFile;
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
	 * @return Returns the htable.
	 */
	public Hashtable getHtable() {
		
		return htable;
	}
	/**
	 * @param htable The htable to set.
	 */
	public void setHtable(Hashtable htable) {
		this.htable = htable;
	}
}
