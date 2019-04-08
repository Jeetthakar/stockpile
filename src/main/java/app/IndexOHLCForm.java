/*
 * Created on Sep 7, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;

import com.harrier.initializeation.ConnectInit;

/**
 * @author ashishi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IndexOHLCForm extends ActionForm {
	Logger Logging = Logger.getLogger(IndexOHLCForm.class);
	private String d1 = null;
	
	private Collection indexCollection = null;

	private String indName = null;

	private String b1 = null;

	private String fileType = null;

	private Vector fileTypeCollections = new Vector();

	private String hiddenVar = null;

	private FormFile theFile = null;
	
	private String fileName = " ";

	private String b2 = null;

	private String b2var = null;

	private static StringBuffer disp_buffer = new StringBuffer();

	private static StringBuffer save_buffer = new StringBuffer();

	/**RESEST ALL FORM FIELDS**/
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		indexCollection = null;
		indName=null;
		b2var = null;
		hiddenVar = null;
		d1 = null;
	}

	public void reset() {
		this.b2var = null;
		this.hiddenVar = null;
		this.d1 = null;
		this.indName=null;
		this.disp_buffer=null;
		this.save_buffer=null;
	}

	/**
	 * @return Returns the indexCollection.
	 */
	public Collection getIndexCollection() {
		Vector roles = new Vector(10);
		//roles.add(new LabelValueBean("Not Selected","0"));
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		Connect c = ConnectInit.getConnect();
		//dbconnect();
		try {
			if (connection == null)
				connection = c.getdbConnection();
			stmt = connection.prepareStatement(ConnectInit.queries
					.getProperty("index_list"));
			rst = stmt.executeQuery();

			while (rst.next()) {
				//int count=rst.getInt(1);
				roles
						.add(new LabelValueBean(rst.getString(2), rst
								.getString(1)));
			}
			rst.close();
			stmt.close();
		} catch (Exception e) {
			Logging.error(" Error : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close connection "
						+ ee.getMessage());
			}
		}
		indexCollection = roles;
		return indexCollection;
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
	 * @return Returns the indName.
	 */
	public String getIndName() {
		return indName;
	}

	/**
	 * @param indName The indName to set.
	 */
	public void setIndName(String indName) {
		this.indName = indName;
	}

	/**
	 * @return Returns the save_buffer.
	 */
	public StringBuffer getSave_buffer() {
		return save_buffer;
	}

	/**
	 * @param save_buffer The disp_buffer to set.
	 */
	public void setSave_buffer(StringBuffer save_buffer) {
		IndexOHLCForm.save_buffer = save_buffer;
	}

	/**
	 * @return Returns the disp_buffer.
	 */
	public StringBuffer getDisp_buffer() {
		return disp_buffer;
	}

	/**
	 * @param disp_buffer The disp_buffer to set.
	 */
	public void setDisp_buffer(StringBuffer disp_buffer) {
		IndexOHLCForm.disp_buffer = disp_buffer;
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
		fileTypeCollections = new Vector();
		int i = 1;
		//fileTypeCollections.add(new Integer(i));
		fileTypeCollections.add(new LabelValueBean("Open High Low Close File",
				new Integer(i).toString()));
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

}