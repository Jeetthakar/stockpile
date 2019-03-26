/*
 * Created on Dec 10, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.jfree.chart.demo.servlet;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import org.apache.log4j.Logger;

import app.Connect;

import com.harrier.initializeation.ConnectInit;
 /**
 * @author naresh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class QueryBuilder {
	Logger Logging = Logger.getLogger(QueryBuilder.class);
	public Vector tab_list;
	public Vector setTab_list() {
		Connect c = ConnectInit.getConnect();
		Connection con = null;
		con = c.getdbConnection();
		ResultSet rst = null;
		PreparedStatement pst = null;
		try 
		{
			tab_list = new Vector();
			//****commented=*******
			//pst = con.prepareStatement(c.queries.getProperty("get_all_tablenames"));
			//Modified by Ganesh on 10/2/07 ,Also in query.properities file to get required data from the database
			pst = con.prepareStatement(ConnectInit.queries.getProperty("get_all_table_names"));
			rst = pst.executeQuery();
			int i = 0;
				while (rst.next())
				{				
					tab_list.add(i, rst.getString(1));
					i++;
			    }   
		} catch (Exception sqlexp) 
		  {
			  Logging.debug("error.....:."+sqlexp);
		  }
		finally{
			try{
				if(rst != null)
				{
					rst.close();
				}
				if(pst != null)
				{
					pst.close();
				}
				con.close();
			}catch(Exception e){Logging.debug("error in closing.....:."+e);}
		}
		return tab_list;
	}
	
	public Vector setcol_list(String [] add_tables) {
		    Vector col_list = null;
		    Logging.debug("Inside setcol ...."+add_tables.length);
			Connect c = ConnectInit.getConnect();
			Connection con = null;
			con = c.getdbConnection();
			ResultSet rst = null;
			PreparedStatement pst = null;
			try 
			{
				col_list = new Vector();
				//Logging.getDebug("error...2..:."+add_tables);
				int len = add_tables.length;
				String temp_tab = "";
				for(int j = 0; j< len; j++)
				{
					if(j == 0)
						temp_tab = "'"+ temp_tab + add_tables[j] + "'";
					else
						temp_tab = temp_tab + "," + "'" + add_tables[j] + "'";
				}
				//String query = c.queries.getProperty("get_sel_columns");
				//Modified by Ganesh on 10/2/07 ,Also in query.properities file to get required data from the database
				String query = ConnectInit.queries.getProperty("get_column_name_for_selected_tables");
				query = query.replaceAll("XYZ",temp_tab);
				pst = con.prepareStatement(query);
				Logging.debug("set col...2..:."+query);
				rst = pst.executeQuery();
				int i = 0;
					while (rst.next())
					{				
						col_list.add(i, rst.getString(1));
						i++;
				    }   
			} catch (Exception sqlexp) 
			  {
				  Logging.error("error...2..:."+sqlexp);
			  }
			finally{
				try{
					if(rst != null)
					{
						rst.close();
					}
					if(pst != null)
					{
						pst.close();
					}
					con.close();
				}catch(Exception e){Logging.error("error in closing.....:."+e);}
			}
		 return col_list;
	}

	public String retquery(String fpath) {
		String query = "";
		String str = "";
		BufferedReader br = null;
		try{
			String path=Connect.getCoolMenuspath();
			String str_dirName = path+"CoolMenus/";
		    str_dirName = str_dirName + fpath;
		    Logging.debug("Inside java file PATH is "+fpath);
			FileReader file = new FileReader(fpath);		
			 br = new BufferedReader(file);
			 while((str = br.readLine()) != null){
			 	query += str;
			 }
			 query.replace('ÿ',' ');
			Logging.debug("Inside java file  "+query);
		}catch(Exception f){Logging.error("Inside java file retquery() "+f); return "Check File";}
		finally{
			try{
				br.close();
			}catch(Exception f){Logging.error("Inside retquery() finally: "+f); }
		}
		return query;
	}
}
