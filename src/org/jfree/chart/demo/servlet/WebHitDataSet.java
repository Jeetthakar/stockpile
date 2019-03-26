/*
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * ---------------------------
 * WebHitDataSet.java
 * ---------------------------
 * (C) Copyright 2002-2004, by Richard Atkinson.
 *
 * Original Author:  Richard Atkinson;
 */
package org.jfree.chart.demo.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Vector;

import org.apache.log4j.Logger;


public class WebHitDataSet {
	static Logger Logging = Logger.getLogger(WebHitDataSet.class);
	protected ArrayList data = new ArrayList();
	protected static  Vector field1,field2;
        
    public  WebHitDataSet() throws ParseException 
	{   
    	
       Readdata();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy",Locale.UK);
        Logging.debug("1");
        Logging.debug(field1.size());
		for(int i=0;i<field1.size();i++)
		{
			 Logging.debug(i);
			 Logging.debug((String)field1.get(i));
				data.add(new WebHit(sdf.parse("01-Aug-2002"), (String)field1.get(i), (Long.parseLong((String)field2.get(i)))));
		}
    }
	public ArrayList getDataByHitDate(String filterSection) {
		ArrayList results = new ArrayList();
		HashMap dateMap = new HashMap();
		Iterator iter = this.data.listIterator();
		int currentPosition = 0;
		while (iter.hasNext()) {
			WebHit webHit = (WebHit)iter.next();
			if (filterSection == null ? true : filterSection.equals(webHit.getSection())) {
				Integer position = (Integer)dateMap.get(webHit.getHitDate());
				if (position == null) {
					results.add(webHit);
					dateMap.put(webHit.getHitDate(), new Integer(currentPosition));
					currentPosition++;
				} else {
					WebHit previousWebHit = (WebHit)results.get(position.intValue());
					previousWebHit.setHitCount(previousWebHit.getHitCount() + webHit.getHitCount());
				}
			}

		}
		return results;
	}

	public ArrayList getDataBySection(Date filterHitDate) {
		ArrayList results = new ArrayList();
		Logging.debug("inside getdatay section");
		HashMap sectionMap = new HashMap();
		Iterator iter = this.data.listIterator();
		int currentPosition = 0;
		while (iter.hasNext()) {
			WebHit webHit = (WebHit)iter.next();
			if (filterHitDate == null ? true : filterHitDate.equals(webHit.getHitDate())) {
				Integer position = (Integer)sectionMap.get(webHit.getSection());
				if (position == null) {
					results.add(webHit);
					sectionMap.put(webHit.getSection(), new Integer(currentPosition));
					currentPosition++;
				} else {
					WebHit previousWebHit = (WebHit)results.get(position.intValue());
					previousWebHit.setHitCount(previousWebHit.getHitCount() + webHit.getHitCount());
				}
			}
		}
		return results;
	}

    public ArrayList getSections() {
      ArrayList list = new ArrayList();
    for(int i=0;i<field1.size();i++)
    {
            list.add((String)field1.get(i));
    }
     return list;
    }

	public static void main(java.lang.String[] args) {
		try {
                    
                        
			WebHitDataSet whDataSet = new WebHitDataSet();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy",Locale.UK);
			ArrayList results = whDataSet.getDataBySection(sdf.parse("01-Aug-2002"));
			Iterator iter = results.listIterator();
			while (iter.hasNext()) {
				WebHit wh = (WebHit)iter.next();
				Logging.debug(wh.getSection() + " - " + wh.getHitCount());
			}
			Logging.debug("Finished.");

		} catch (Exception e) {
			//e.printStackTrace();
			Logging.debug(e);
		}
	}

	public static ArrayList getDateList() {
		ArrayList dateList = new ArrayList();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy",Locale.UK);
		try {
			dateList.add(sdf.parse("28-Aug-2002"));
			dateList.add(sdf.parse("27-Aug-2002"));
			dateList.add(sdf.parse("26-Aug-2002"));
			dateList.add(sdf.parse("23-Aug-2002"));
			dateList.add(sdf.parse("22-Aug-2002"));
			dateList.add(sdf.parse("21-Aug-2002"));
			dateList.add(sdf.parse("20-Aug-2002"));
			dateList.add(sdf.parse("19-Aug-2002"));
			dateList.add(sdf.parse("16-Aug-2002"));
			dateList.add(sdf.parse("15-Aug-2002"));
			dateList.add(sdf.parse("14-Aug-2002"));
			dateList.add(sdf.parse("13-Aug-2002"));
			dateList.add(sdf.parse("12-Aug-2002"));
			dateList.add(sdf.parse("09-Aug-2002"));
			dateList.add(sdf.parse("08-Aug-2002"));
			dateList.add(sdf.parse("07-Aug-2002"));
			dateList.add(sdf.parse("06-Aug-2002"));
			dateList.add(sdf.parse("05-Aug-2002"));
			dateList.add(sdf.parse("02-Aug-2002"));
			dateList.add(sdf.parse("01-Aug-2002"));
		} catch (ParseException e) {
			// ignore
		}
		return dateList;
	}

	public  ArrayList getSectionList() {
		ArrayList sectionList = new ArrayList();
		for(int i=0;i<field1.size();i++)
		{
			sectionList.add((String)field1.get(i));
		}
		return sectionList;
	}
	public static void Readdata()
	{
        try{
			        	field1=new Vector();
			            field2=new Vector();
                        Class.forName("org.postgresql.Driver").newInstance();
                        String url="jdbc:postgresql://192.168.0.11:5432/sample";
                        Connection con=DriverManager.getConnection(url,"sunil","sunil");
                          Statement st1=con.createStatement();
                ResultSet rs1=st1.executeQuery("select category from jfreechart.piedata");
                while(rs1.next())
                {
                           // String year=rs1.getString("stock_name");
                        //field1.add(year);
                        String year2=rs1.getString("category");
                        field1.add(year2);
                }
                for(int i=0;i<field1.size();i++)
                {
                   String str=(String)field1.get(i);
                   Logging.debug(str);
                }
               Statement st2=con.createStatement();
                ResultSet rs2=st2.executeQuery("select value from jfreechart.piedata");
                while(rs2.next())
                {
                            String year=rs2.getString("value");
                        field2.add(year);
                }
                for(int i=0;i<field2.size();i++)
                {
                   String str=(String)field2.get(i);
                   Logging.debug(str);
                }

                }catch(SQLException e){
                        Logging.debug(e);
  /*             JFrame frame=new JFrame();
                JOptionPane.showMessageDialog(frame,"Unable To Connect DataBase","ERROR!",JOptionPane.ERROR_MESSAGE);
                System.exit(0);
*/
                }catch(Exception e){
                        Logging.debug(e);
                }
                Logging.debug((field1.size()));
                Logging.debug("abc");

        }

}