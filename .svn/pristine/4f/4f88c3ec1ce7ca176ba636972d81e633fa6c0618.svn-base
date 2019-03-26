/*
 * Created on Jun 30, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.harrier.initializeation.ConnectInit;
;
/**
 * @author naresh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DynamicTableAction extends Action 
{Logger Logging = Logger.getLogger(DynamicTableAction.class);
	public static final String FORWARD_start1 = "success";
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		Logging.debug("at action.");
		DynamicTableForm dyn = (DynamicTableForm) form;
		Connection con = null;
		Connect c = ConnectInit.getConnect();
		ResultSet rst = null;
		PreparedStatement pst = null;
		String sqlString = 
			dyn.getSqltext();
		String parse_flag = null;
		//ArrayList arrl = new ArrayList();
		String temp_store = "";
		//Get the file type if given else use .CSV
		String pathf = Connect.getCoolMenuspath();
		pathf = pathf + "CoolMenus/" + "excel.csv";
		File newfile = new File(pathf);
		FileWriter outfile = new FileWriter(newfile);
		int parse = 0;
		try{
			con = c.getdbConnection();
			pst = con.prepareStatement(sqlString);
			rst = pst.executeQuery();
			ResultSetMetaData rsmd = rst.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			for(int j = 1; j <= numberOfColumns; j++)
			{
				if(j != 1) 
					temp_store += ",";
				temp_store += rsmd.getColumnName(j);
				
			} 
			temp_store += "\n"; 
			outfile.write(temp_store);
			temp_store = "";
			Logging.debug("Column count is : "+numberOfColumns);
			
			while(rst.next())
			{ 
				for(int i = 1; i <= numberOfColumns; i++)
				{
					if(rst.getString(i) != null)
						temp_store += rst.getString(i);
					else
						temp_store += "-";
					if(i%numberOfColumns != 0)
						temp_store += ",";
					
				}				
				outfile.write(temp_store);
				temp_store = "\n"; 
			}
			dyn.setParse_error(1);			
		}catch(Exception ew){
			dyn.setParse_error(2);
			Logging.error("Error in DynaAction 1: "+ew);
			parse_flag = ew.getMessage();			
			dyn.setSqlerror(ew.getMessage());
		}
		finally{
			try{
				if(rst != null)
					rst.close();
				if(pst != null)
					pst.close();
				con.close();
				outfile.close();
			}catch(Exception ew){
				Logging.error("Error in DynaAction 2: "+ew);
			}
		}
		
		return new ActionForward("/pages/DynaReport.jsp");
		
		
		//return mapping.getInputForward();	
			
		//return mapping.findForward(FORWARD_start1);
	}

}
