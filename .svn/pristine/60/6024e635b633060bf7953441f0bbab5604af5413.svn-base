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

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;

import com.harrier.initializeation.ConnectInit;
/**  
 * @author Ashish
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GICSFileUploadForm extends ActionForm {
	Logger Logging = Logger.getLogger(GICSFileUploadForm.class);
	private String d1=null,id=null,str_fileName=null;
	private String histDate=null;
	private String b1=null;
	private Vector exchangeCollection=null,vector_classlist=null;
	private String fileType=null;
	private Vector fileTypeCollections=new Vector();
	private String hiddenVar=null;
	private FormFile theFile=null;
	private String fileName=" ";
	private String b2=null;
	private String b2var=null;
	private String indexname=null;
	private static StringBuffer disp_buffer=new StringBuffer();
	Object obj;
	Hashtable htable=new Hashtable();
	
	
	
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
	
	public Vector getExchangeCollection() {
		Vector temp2=new Vector();
		Connect con=ConnectInit.getConnect();
		Connection connection=null;
		ResultSet rs=null;
		String query;

		//query=stock_exchange_online_list
		
		try{
			if(connection==null)
				connection=con.getdbConnection();
					query = ConnectInit.queries.getProperty("stock_exchange_online_list");
				 temp2 = new Vector();
				 temp2.add(new LabelValueBean("Not Sellected","0"));
				try {
					Statement stmt = connection.createStatement();
					rs = stmt.executeQuery(query);
					while (rs.next()) {
						temp2.add(new LabelValueBean(rs.getString(2),rs.getString(1)));
					}
					exchangeCollection = temp2;
					
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
	 * @param vector_classlist The vector_classlist to set.
	 */
	public void setVector_classlist(Vector vector_classlist) {
		this.vector_classlist = vector_classlist;
				}
	
	/**
	 * @return Returns the vector_classlist.
	 */
	public Vector getVector_classlist() {
		
		Vector temp1=new Vector();
		Connect con=ConnectInit.getConnect();
		Connection connection=null;
		ResultSet rs=null;
		String query;
		try{
			if(connection==null)
				connection=con.getdbConnection();
					query = ConnectInit.queries.getProperty("icm_select_*_from_industry_classification_master");
				 temp1 = new Vector();
				 temp1.add(new LabelValueBean("Not Sellected","0"));
				try {
					Statement stmt = connection.createStatement();
					rs = stmt.executeQuery(query);
					while (rs.next()) {
						temp1.add(new LabelValueBean(rs.getString(2),rs.getString(1)));
					}
					vector_classlist = temp1;
				}
		/*app.Logging.getDebug("Inside setVector_classlist");
		app.Connect con=new app.Connect();
		Connection connection=null;
		try {
			if(connection==null)
				connection=con.getdbConnection();
			connection.rollback();
			connection.setAutoCommit(true);
			app.Logging.getDebug("Inside setVector_classlist after connection");
			vector_classlist = new Vector();
			rst = con.getClientList(connection,"icm_select_*_from_industry_classification_master");
			app.Logging.getDebug("In setVector_classlist "+rst);
			 
			int i = 0;
			obj = new Object[1][2];
			while (rst.next()) {
				vector_classlist.add(i, rst.getString(1));
				i++;
				vector_classlist.add(i, rst.getString(2));
				i++;
			}
			if(rst!=null)
				rst.close();
		}*/
				catch (Exception e) {
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
				
		return vector_classlist;
	}
	/**
	 * @return Returns the d1.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param d1 The d1 to set.
	 */
	public void setId(String id) {
		this.id = id;
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
	public String getHistDate() {
		return histDate;
	}
	/**
	 * @param histDate The histDate to set.
	 */
	public void setHistDate(String histDate) {
		this.histDate = histDate;
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
		fileTypeCollections.add(new LabelValueBean("Import Gics file",new Integer(i).toString()));
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

	/**
	 * @param str_fileName The str_fileName to set.
	 */
	public void setStr_fileName(String str_fileName) {
		Logging.debug("File is set"+str_fileName);
		str_fileName=null;
		this.str_fileName = str_fileName;
				
	}
	
	/**
	 *method to check extension(like csv) of file and returns true 
	 *
	 */
	private boolean checkExt(){
		Logging.debug("Inside check ext");
		String temp ="";		 
		Logging.debug("file is null");
		temp = str_fileName.substring(str_fileName.lastIndexOf(".")+1);
		Logging.debug(temp);
		if(temp.equalsIgnoreCase("csv"))
			return true;
		else return false;
		
	}
	/*public ActionErrors validate(ActionMapping mapping,HttpServletRequest request)
	 {
		ActionErrors errors = new ActionErrors();
		String temp ="";		 
		Logging.getDebug("file is null");
		temp = str_fileName.substring(str_fileName.lastIndexOf(".")+1);
		if(!temp.equalsIgnoreCase("csv"))
			errors.add("DuplicateEntry",new ActionError("Error.message.DuplicateEntry"));
	 return errors;
	 }*/
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
		GICSFileUploadForm.disp_buffer = disp_buffer;
	}
}
