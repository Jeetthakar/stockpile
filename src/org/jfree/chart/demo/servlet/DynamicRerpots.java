/*
 * Created on Dec 9, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.jfree.chart.demo.servlet;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import app.Connect;

import com.harrier.initializeation.ConnectInit;
/**
 * @author naresh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DynamicRerpots 
{
	Logger Logging = Logger.getLogger(DynamicRerpots.class);
	Connection con = null;
	Connect c = ConnectInit.getConnect();
	public ArrayList getData(String sqlquery)
	{
		ArrayList sqldata	=	new ArrayList();
		ResultSet rst = null;
		PreparedStatement pst = null;
		con = c.getdbConnection();
		Logging.debug("getdata in SQL  : "+sqlquery);
		try{
			pst = con.prepareStatement(sqlquery);
			rst = pst.executeQuery();
			ResultSetMetaData rsmd = rst.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			while(rst.next())
			{ 
				for(int i = 1; i <= numberOfColumns; i++)
				{
					if(rst.getString(i) != null)
						sqldata.add(rst.getString(i));
					else
						sqldata.add("-");
				}
			}
		}catch(Exception e){
			Logging.error("Error in SQL  : "+e);
		}
		finally{
			try{
				if(rst != null)
					rst.close();
				if(pst != null)
					pst.close();
					con.close();
			}catch(Exception e){
				Logging.error("Error in SQL  : "+e);
			}
		}
		return sqldata;
	}
	
	public String getError(String sqlquery)
	{
		String errormsg = "";
		ResultSet rst = null;
		PreparedStatement pst = null;
		con = c.getdbConnection();
		Logging.debug("in SQL  : "+sqlquery);
		try{
			pst = con.prepareStatement(sqlquery);
			rst = pst.executeQuery();
		}catch(Exception e){
			Logging.error("Error in SQL  : "+e);
			errormsg = e.toString();
		}
		finally{
			try{
			if(rst != null)
				rst.close();
			if(pst != null)
				pst.close();
				con.close();
			}catch(Exception e){
				Logging.error("Error in SQL  : "+e);
			}
		}
		return errormsg;
	}
	public int columncount(String sqlquery){
		int countcol = 0;
		ResultSet rst = null;
		PreparedStatement pst = null;
		con = c.getdbConnection();
		Logging.debug("in SQL  : "+sqlquery);
		try{
			pst = con.prepareStatement(sqlquery);
			rst = pst.executeQuery();
			ResultSetMetaData rsmd = rst.getMetaData();
			countcol = rsmd.getColumnCount();
		}catch(Exception e){
			Logging.error("Error in SQL  : "+e);
		}
		finally{
			try{
				if(rst != null)
					rst.close();
				if(pst != null)
					pst.close();
					con.close();
			}catch(Exception e){
				Logging.error("Error in SQL  : "+e);
			}
		}
		return countcol;
	}
	
	public ArrayList getColumns(String sqlquery)
	{
		ArrayList sqlcolumns	=	new ArrayList();
		ResultSet rst = null;
		PreparedStatement pst = null;
		con = c.getdbConnection();
		try{
			pst = con.prepareStatement(sqlquery);
			rst = pst.executeQuery();
			ResultSetMetaData rsmd = rst.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			for(int j = 1; j <= numberOfColumns; j++)
			{
				sqlcolumns.add(rsmd.getColumnName(j));
			} 
		}catch(Exception e){
			Logging.error("Error in SQL  : "+e);
		}
		finally{
			try{
			if(rst != null)
				rst.close();
			if(pst != null)
				pst.close();
				con.close();
			}catch(Exception e){
				Logging.error("Error in SQL  : "+e);
			}
		}
		return sqlcolumns;
	}
	
	public void makeCSV(String sqlquery)
	{
		{
			Logging.debug("at action."+sqlquery);
			Connection con = null;
			Connect c = ConnectInit.getConnect();
			ResultSet rst = null;
			PreparedStatement pst = null;
			String temp_store = "";
			//Get the file type if given else use .CSV
			String pathf = Connect.getCoolMenuspath();
			pathf = pathf + "CoolMenus/" + "excel.csv";
			File newfile = new File(pathf);
			FileWriter outfile = null;
			int parse = 0;
			try{
				outfile = new FileWriter(newfile);
				con = c.getdbConnection();
				pst = con.prepareStatement(sqlquery);
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
		}catch(Exception ew){
			Logging.error("Error in DynaAction 2: "+ew);
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
		}
	}
	
	public void makeQuery(String sqlquery)
	{
			String pathf_2 = Connect.getCoolMenuspath();
			pathf_2 = pathf_2 + "CoolMenus/" + "query.txt";
			Logging.debug("query in saving : "+sqlquery);
			File newfile2 = new File(pathf_2);
			FileWriter outfile2 = null;
			try{
				outfile2 = new FileWriter(newfile2);
				outfile2.write(sqlquery);
			}catch(Exception ef){}
			finally{
				try{
					outfile2.close();
				}catch(Exception ew){
					Logging.error("Error in DynaAction 2: "+ew);
				}
			}
		}
}
