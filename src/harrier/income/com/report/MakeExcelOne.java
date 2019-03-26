/*
 * Created on Jun 8, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */ 
package harrier.income.com.report;  
import java.io.File; 
import java.util.*;

import jxl.*; 
import jxl.format.UnderlineStyle;
import jxl.write.*;

import java.sql.Connection;
import java.sql.PreparedStatement; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.jfree.chart.demo.servlet.ComposeIndex;

import com.harrier.initializeation.ConnectInit;

import app.*;

import sysconfig.model.indexMovement;
import sysconfig.model.stockDetailFromDate;
/**
 * @author lokesh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MakeExcelOne 
{
	Logger Logging = Logger.getLogger(MakeExcelOne.class);
	/**
	 * create_file is used to create the excel sheet from the report.
	 * It uses the vector passed by the report page to create a excel sheet.
	 * 
	 * 
	 * @param var
	 * @param switch_code
	 * @param ai
	 * @param fdate
	 * @param tdate
	 * @param indexName
	 */
	WritableFont arial10font = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE  ,Colour.BLUE);
	WritableCellFormat arial10format = new WritableCellFormat (arial10font);
	int jCount;
	WritableSheet sheet;
	WritableWorkbook workbook;
	String pathf ;
	Label label;
	Label label1;
	Label label19;
	int xValue=0;
	int yValue=0;
	 /**
	* default Constructor.
	*/
	
	public MakeExcelOne(){
		try
		{
			 pathf = Connect.getCoolMenuspath();
			 pathf = pathf + "CoolMenus/" + "Allinone.xls";
			 workbook = Workbook.createWorkbook(new File(pathf));
			 sheet = workbook.createSheet("Stockpile", 0);
		}catch(Exception e)
		{
			Logging.error("Error prevented the file from being created 1."+e);
		}
	}
	
	
	

	public void create_file(String indexId,int switch_code, Vector ai, String fdate, String tdate, String indexName)
	{
				 
		
		try
		{
			
			switch(switch_code){
			case 1:

				
			yValue++;	
       		
			label = new Label(xValue, yValue, "Index Composition Report ", arial10format);
			sheet.addCell(label);
			yValue++;
			yValue++;
						
			label = new Label(xValue, yValue, "Index Name :"+indexName, arial10format);
			sheet.addCell(label);
			yValue++;
			
			
			
			label = new Label(xValue, yValue, "Stock name ", arial10format);
			sheet.addCell(label);
			xValue++;
			label = new Label(xValue, yValue, "Total shares ", arial10format);
			sheet.addCell(label);
			xValue++;
			label = new Label(xValue, yValue, "IWF ", arial10format);
			sheet.addCell(label);
			xValue++;
			label = new Label(xValue, yValue, "Market lot ", arial10format);
			sheet.addCell(label);
			xValue++;
			label = new Label(xValue, yValue, "Price (LTP) ", arial10format);
			sheet.addCell(label);
			xValue++;
			label = new Label(xValue, yValue, "Price (Last) ", arial10format);
			sheet.addCell(label);
			xValue++;
			label = new Label(xValue, yValue, "Currency ", arial10format);
			sheet.addCell(label);
			xValue++;
			label = new Label(xValue, yValue, "Currency Exch. rate ", arial10format);
			sheet.addCell(label);
			xValue++;
			label = new Label(xValue, yValue, "Market cap. ", arial10format);
			sheet.addCell(label);
			xValue++;
			label = new Label(xValue, yValue, "Adjusted market cap. ", arial10format);
			sheet.addCell(label);
			xValue++;
			label = new Label(xValue, yValue, "Weightage ", arial10format);
			sheet.addCell(label);
			xValue++;
			label = new Label(xValue, yValue, "Date ", arial10format);
			sheet.addCell(label);
			yValue++;
			String id = null;
			
			if(ai.size()!= 0)
			{
				Iterator it = ai.iterator();
				while(it.hasNext())
				{

					it.next();
					//yValue++;
					for(int j = 0;j<12;j++)
					{
						if(it.hasNext())
						{
							id=(String)it.next();
							double dob=0.0;
							try{
								dob = Double.parseDouble(id);
								
								jxl.write.Number num= new jxl.write.Number(j, yValue,dob);
								sheet.setColumnView(j,18);
								sheet.addCell(num);
							} catch(Exception e){
								label1 = new Label(j,yValue,id);
								sheet.setColumnView(j,18);
								sheet.addCell(label1);
								
																	
							}

						}
					}
					yValue = yValue + 1;
				}
			}
			xValue=0;
			
			break;
			
			case 6:
				
				Logging.debug(" Inside case 6");
				yValue++;
				String label_heading6 = "Stock details report for index : ";
				//int var_int6 = Integer.parseInt(index);
           		label_heading6 = label_heading6 +" " + indexName;
				label = new Label(0, yValue, label_heading6, arial10format); 
				sheet.addCell(label);
				yValue++;
				
				label_heading6 = "Stock name : ";
				//int var_int6 = Integer.parseInt(var1);
           		label_heading6 = label_heading6 +" " +indexId;// for stockname
				label = new Label(0, yValue, label_heading6 ); 
				sheet.addCell(label);
				yValue++;
				
				String fdate6 = "From date : " + fdate;
				String tdate6 = "To date : " + tdate;
				label = new Label(0, yValue, fdate6, arial10format); 
				sheet.addCell(label);
				label = new Label(1, yValue, tdate6, arial10format); 
				sheet.addCell(label);
				yValue++;
				label = new Label(0, yValue, "Stock name ", arial10format); 
				sheet.addCell(label);
				label = new Label(1, yValue, "Opening value ", arial10format); 
				sheet.addCell(label);
				label = new Label(2, yValue, "Closing value ", arial10format); 
				sheet.addCell(label);
				label = new Label(3, yValue, "Lowest value", arial10format); 
				sheet.addCell(label);
				label = new Label(4, yValue, "Highest value", arial10format); 
				sheet.addCell(label);
				label = new Label(5, yValue, "Traded volume ", arial10format); 
				sheet.addCell(label);
				label = new Label(6, yValue, "Traded value ", arial10format); 
				sheet.addCell(label);
				label = new Label(7, yValue, "Mkt. Cap ", arial10format); 
				sheet.addCell(label);
				Logging.debug("tdate: "+tdate);
				if(tdate.equals("true")){
					label = new Label(8, yValue, "No. Of Trades", arial10format); 
					sheet.addCell(label);
					label = new Label(9, yValue, "Date", arial10format); 
					sheet.addCell(label);
				}
				else{
					label = new Label(8, yValue, "Date", arial10format); 
					sheet.addCell(label);
				}
				yValue++;
				String id6 = null;
				//int count6 = 5;
				if(tdate!=null && tdate.equals("false")){
					if(ai.size()!= 0)
					{
					    Iterator it6 = ai.iterator();
						while(it6.hasNext())
						{
							for(int j6 = 0;j6<10;j6++)
							{
								
								if(it6.hasNext())
								{	
									String next=(String)it6.next();
									id6=next;
									if((j6==8)){
										continue;
									}
									if((j6==9)){
										label = new Label(8,yValue,id6);	
										sheet.addCell(label);
										break;
									}
									else{
										label = new Label(j6,yValue,id6);	
										sheet.addCell(label);
									}
									
								}
							}
							yValue = yValue + 1;
						}
					}
				}
				else{
					if(ai.size()!= 0)
					{
					    Iterator it6 = ai.iterator();
						while(it6.hasNext())
						{
							for(int j6 = 0;j6<10;j6++)
							{
								
								if(it6.hasNext())
								{	
									String next=(String)it6.next();
									id6=next;
									label = new Label(j6,yValue,id6);
									sheet.addCell(label);
								}
							}
							yValue = yValue + 1;
						}
					}
				}
								
				break;
				
				
			case 22:
				// excel case for stock divident
				yValue++;
				
				String label_heading22 = "Stock Divident";
				String label_heading="";
				label = new Label(0, yValue, label_heading22 , arial10format); 
				sheet.addCell(label);
				yValue++;
				
				label_heading = "Index:" +" " + indexName;
				if(indexId.equals("1")){
	           		label_heading = "Exchange:" +" " + indexName;
				}
						
				
				label = new Label(0, yValue, label_heading , arial10format); 
				sheet.addCell(label);
				yValue++;
				
				String fdate22 = "From date : " + fdate;
				String tdate22 = "To date : " + tdate;
				
				label = new Label(0, yValue, fdate22, arial10format); 
				sheet.addCell(label);
				label = new Label(3, yValue, tdate22, arial10format); 
				sheet.addCell(label);
				yValue++;yValue++;
				
				label = new Label(0,yValue, "Stock name ", arial10format); 
				sheet.addCell(label);
				label = new Label(1, yValue, "Face Value ", arial10format); 
				sheet.addCell(label);
				label = new Label(2, yValue, "Total Issued Share", arial10format); 
				sheet.addCell(label);
				label = new Label(3, yValue, "Market Cap", arial10format); 
				sheet.addCell(label);
				label = new Label(4, yValue, "Divident", arial10format); 
				sheet.addCell(label);
				label = new Label(5, yValue, "Applied Date", arial10format); 
				sheet.addCell(label);
				String id22 = null;
				//int count22 = 6;
				yValue++;
				if(ai.size()!= 0)
				{
				    Iterator it22 = ai.iterator();
					while(it22.hasNext())
					{
						for(int j22 = 0;j22<7;j22++)
						{
							if(it22.hasNext())
							{	
								id22=(String)it22.next();
								double dob=0.0;
								if(j22==0){
									continue;
								}
								try{
									dob=Double.parseDouble(id22);
									jxl.write.Number num=new jxl.write.Number(j22-1,yValue,dob); 	
									sheet.setColumnView(j22-1,18);
									sheet.addCell(num);
								}catch(Exception e){
									label = new Label(j22-1,yValue,id22);
									sheet.setColumnView(j22-1,18);
									sheet.addCell(label);
								}
							}
						}
						yValue = yValue + 1;
					}
				}
				
				break;
				
			case 2:
				
				yValue++;
				label = new Label(0, yValue, "Company Wise Weightage Report ", arial10format); 
				sheet.addCell(label);
				yValue++;yValue++;
				
				label = new Label(xValue, yValue, "Index Name :"+indexName, arial10format);
				sheet.addCell(label);
				yValue++;
				
				label = new Label(xValue, yValue, "Applied Date :"+fdate, arial10format);
				sheet.addCell(label);
				yValue++;
				
				label = new Label(0, yValue, "Company name ", arial10format); 
				sheet.addCell(label);
				
				label = new Label(1, yValue, "Market Cap. ", arial10format); 
				sheet.addCell(label);
				
				label = new Label(2, yValue, "Weightage(%) ", arial10format); 
				sheet.addCell(label);
				String id1 = null;
				//int count1 = 3;
				yValue++;
				yValue++;
				org.jfree.chart.demo.servlet.FieldSort sort2=new org.jfree.chart.demo.servlet.FieldSort();         
			    int diri=0,dir1i=0,dir2i=0;int fieldnoi = 0;
				if(ai.size()!= 0)
				{
					Iterator it1 = ai.iterator();
					while(it1.hasNext())
					{
						for(int j1 = 0;j1<3;j1++)
						{
							if(it1.hasNext())
							{	
								id1=(String)it1.next();
								double dob=0.0;
								try{
									dob = Double.parseDouble(id1);
									jxl.write.Number num= new jxl.write.Number(j1, yValue,dob);
									sheet.setColumnView(j1,18);
									sheet.addCell(num);											
								}catch(Exception e){
									label = new Label(j1,yValue,id1);
									sheet.setColumnView(j1,18);
									sheet.addCell(label);
								}
									
							}
						}
						yValue++; 
					}
				}
				
				break;

			case 3:
				
				yValue++;
				label_heading = "Industry Wise Weightage Report : ";
				label = new Label(0, yValue, label_heading , arial10format); 
				sheet.addCell(label);
				yValue++;
				
           		label_heading = "Index Name : " +  indexName;
           		label = new Label(0, yValue, label_heading , arial10format); 
				sheet.addCell(label);
				yValue++;
				
				label = new Label(0, yValue, "Industry name ", arial10format); 
				sheet.addCell(label);
				xValue++;
				label = new Label(1, yValue, "Market Cap. ", arial10format); 
				sheet.addCell(label);
				xValue++;
				label = new Label(2, yValue, "Weightage(%) ", arial10format); 
				sheet.addCell(label);
				String id3 = null;
				
				yValue++;yValue++;
				if(ai.size()!= 0)
				{
				    Iterator it3 = ai.iterator();
					while(it3.hasNext())
					{
						for(int j3 = 0;j3<3;j3++)
						{
							if(it3.hasNext())
							{	
								id3=(String)it3.next();
								double dob=0.0;
								try{
									dob = Double.parseDouble(id3);
									jxl.write.Number num1= new jxl.write.Number(j3, yValue,dob);
									sheet.setColumnView(j3,18);
									sheet.addCell(num1);
								} catch(Exception e){
									label = new Label(j3,yValue,id3);
									sheet.setColumnView(j3,18);
									sheet.addCell(label);
								}
							}
						}
						yValue = yValue + 1;
					}
				}
				
				break;
					
			case 5:
				
				yValue++;
				String label_heading5 = "Stock Contribution to change in index report for index : ";
			
           		label_heading5 = label_heading5 +" " + indexName;
           		label = new Label(xValue, yValue, label_heading5 , arial10format); 
				sheet.addCell(label);
				yValue++;
				
				String fdate5 = "From date : " + fdate;
				String tdate5 = "To date : " + tdate;
				
				label = new Label(0, yValue, fdate5, arial10format); 
				sheet.addCell(label);
				label = new Label(3, yValue, tdate5, arial10format); 
				sheet.addCell(label);
				yValue++;
				
				label = new Label(0, yValue, "Stock name ", arial10format); 
				sheet.addCell(label);
				label = new Label(1, yValue, "Index mkt. cap. difference ", arial10format); 
				sheet.addCell(label);
				label = new Label(2, yValue, "Stock mkt. cap. difference ", arial10format); 
				sheet.addCell(label);
				label = new Label(3, yValue, "Weightage", arial10format); 
				sheet.addCell(label);
				String id5 = null;
				
				yValue++;
				if(ai.size()!= 0)
				{
				    Iterator it5 = ai.iterator();
					while(it5.hasNext())
					{
						for(int j5 = 0;j5<4;j5++)
						{
							if(it5.hasNext())
							{	
								id5=(String)it5.next();
								double dob=0.0;
								try{
									dob = Double.parseDouble(id5);
									jxl.write.Number num= new jxl.write.Number(j5, yValue,dob);
									sheet.setColumnView(j5,25);
									sheet.addCell(num);
								} catch(Exception e){
									label = new Label(j5,yValue,id5);
									sheet.setColumnView(j5,25);
									sheet.addCell(label);
								}
								
							}
						}
						yValue = yValue + 1;
					}
				}
				
				break;

				
					
				case 16:
					
					// case for Capital Change report
					yValue++;
					Label label16 = new Label(0, yValue, "Capital Change To Universe Report", arial10format); 
					sheet.addCell(label16);
					yValue++; yValue++;
					String label_heading16 = "Stock Exchange name : ";
					label_heading16 = label_heading16 +" " + indexName;
										
				    label16 = new Label(0, yValue, label_heading16 , arial10format); 
					sheet.addCell(label16);
					yValue++;
					
					String fdate16 = "From date : " + fdate;
					String tdate16 = "To date : " + tdate;
					label16 = new Label(0, yValue, fdate16, arial10format); 
					sheet.addCell(label16);
					label16 = new Label(3, yValue, tdate16, arial10format); 
					sheet.addCell(label16);
					yValue++;
					
					label16 = new Label(0, yValue, "Stock name ", arial10format); 
					sheet.addCell(label16);
					label16 = new Label(1, yValue, "Face value", arial10format); 
					sheet.addCell(label16);
					label16 = new Label(2, yValue, "Total issuable shares", arial10format); 
					sheet.addCell(label16);
					label16 = new Label(3, yValue, "Mkt. Cap value", arial10format); 
					sheet.addCell(label16);
					label16 = new Label(4, yValue, "Investible weight factor", arial10format); 
					sheet.addCell(label16);
					label16 = new Label(5, yValue, "Corporate action", arial10format); 
					sheet.addCell(label16);
					label16 = new Label(6, yValue, "Applied date", arial10format); 
					sheet.addCell(label16);
					String id16 = null;
					//int count16 = 4;
					yValue++;
					if(ai.size()!= 0)
					{
					    Iterator it16 = ai.iterator();
						while(it16.hasNext())
						{
							it16.next();
							for(int j16 = 0;j16<7;j16++)
							{
								if(it16.hasNext())
								{	
									id16=(String)it16.next();
									double dob=0.0;
									try{
										dob = Double.parseDouble(id16);
										jxl.write.Number num= new jxl.write.Number(j16, yValue,dob);
										sheet.setColumnView(j16,18);
										sheet.addCell(num);
									} catch(Exception e){
										label16 = new Label(j16,yValue,id16);
										sheet.setColumnView(j16,18);
										sheet.addCell(label16);
									}
									
								}
							}
							yValue = yValue + 1;
						}
					}
									
					break;
				

				case 19: 
					String indexName19 = indexName;
					String fdate19 = fdate;
					String tdate19 = tdate;
					yValue++;
					
					label = new Label(xValue, yValue, "Index Divisor Report ", arial10format); 
					sheet.addCell(label);
					yValue++;
					
					label = new Label(xValue,yValue, "Index Name : "+indexName19, arial10format); 
					sheet.addCell(label);
					yValue++;
					label = new Label(xValue, yValue, "From Date   :"+fdate19+"       To Date   : "+tdate19, arial10format); 
					sheet.addCell(label);
					yValue++;
					
					label = new Label(xValue, yValue, "Trading Date ", arial10format); 
					sheet.addCell(label);
					xValue++;
					label = new Label(xValue, yValue, "Close ", arial10format); 
					sheet.addCell(label);
					xValue++;
					label = new Label(xValue, yValue, "MKt. Cap ", arial10format); 
					sheet.addCell(label);
					xValue++;
					label = new Label(xValue, yValue, "Divisor ", arial10format); 
					sheet.addCell(label);
					
					id = null;
					//int count19 = 7;
					if(ai.size()!= 0)
					{
						Iterator it = ai.iterator();
						yValue++;
						while(it.hasNext())
						{
								
									label = new Label(0,yValue,(String)it.next());
									sheet.addCell(label);
									label = new Label(1,yValue,(String)it.next());
									sheet.addCell(label);
									label = new Label(2,yValue,(String)it.next());
									sheet.addCell(label);
									label = new Label(3,yValue,(String)it.next());
									sheet.addCell(label);
							
									yValue = yValue + 1;
						}
					}
					xValue=0;
					break;	
					

				case 20: 
					
					
					yValue++;
					
					label = new Label(0, yValue, "Index PE/PB Report ", arial10format); 
					sheet.addCell(label);
					yValue++;
				
					label =new Label(0, yValue, "Index: "+indexName, arial10format);
					sheet.addCell(label);
					yValue++;
					
						label = new Label(0, yValue, "From Date "+fdate, arial10format);
						sheet.addCell(label);
						
						label = new Label(1, yValue, "       To Date "+tdate, arial10format);
						sheet.addCell(label);
						yValue++;
						//table headings 
						label = new Label(0, yValue, "Trading Date ", arial10format); 
						sheet.addCell(label);
						label = new Label(1, yValue, "Close ", arial10format); 
						sheet.addCell(label);
						label = new Label(2, yValue, "%Change ", arial10format); 
						sheet.addCell(label);
						label = new Label(3, yValue, "Market cap.  ", arial10format); 
						sheet.addCell(label);
						label = new Label(4, yValue, "Share Traded ", arial10format); 
						sheet.addCell(label);
						label = new Label(5, yValue, "Turn Over ", arial10format); 
						sheet.addCell(label);
						label = new Label(6, yValue, "P/E Ratio ", arial10format); 
						sheet.addCell(label);
						label = new Label(7, yValue, "P/B Ratio ", arial10format); 
						sheet.addCell(label);
						label = new Label(8, yValue, "Divident Yield ", arial10format); 
						sheet.addCell(label);
						
						id = null;
						
						yValue++;
						if(ai.size()!= 0)
						{
							Iterator it = ai.iterator();
							while(it.hasNext())
							{
						
								
								for(int j = 0;j<9;j++)
								{
									if(it.hasNext())
									{	
										id=(String)it.next();
										double dob=0.0;
										try{
											dob = Double.parseDouble(id);
											jxl.write.Number num2= new jxl.write.Number(j, yValue,dob);
											sheet.setColumnView(j,18);
											sheet.addCell(num2);
										} catch(Exception e){
											label = new Label(j,yValue,id);
											sheet.setColumnView(j,18);
											sheet.addCell(label);
										}
									}
								}
								yValue = yValue + 1;
							}
						}
						
						xValue=0;
						break;
						
						
						
				/*		

				case 24: 
					// Moving Index
					System.out.println("in case 24 of makeexcel");
					yValue++;
					label19 = new Label(0, yValue, "Index Movement Report ", arial10format); 
					sheet.addCell(label19);
					yValue = yValue+2;
					label19 = new Label(0,yValue, "Index Name : "+indexName, arial10format); 
					sheet.addCell(label19);
					yValue++;
					label19 = new Label(0, yValue, "From Date   :"+fdate+"       To Date   : "+tdate, arial10format); 
					sheet.addCell(label19);
					yValue = yValue+3;
					label19 = new Label(0, yValue, "Trading Date ", arial10format); 
					sheet.addCell(label19);
					label19 = new Label(1, yValue, "Close ", arial10format); 
					sheet.addCell(label19);
					label19 = new Label(2, yValue, "MKt. Cap ", arial10format); 
					sheet.addCell(label19);
					label19 = new Label(3, yValue, "Divisor ", arial10format); 
					sheet.addCell(label19);
					
					id = null;
					//count = 7;
					yValue++;
					if(ai.size()!= 0)
					{
						Iterator it24 = ai.iterator();
						while(it24.hasNext())
						{
							
							for(int j24 = 0;j24<4;j24++)
							{
								if(it24.hasNext())
								{	
									id=(String)it24.next();
									double dob=0.0;
									try{
										dob=Double.parseDouble(id);
										jxl.write.Number num=new jxl.write.Number(j24,yValue,dob); 	
										sheet.setColumnView(j24,18);
										sheet.addCell(num); 
									}
									catch(Exception e){
										label19 = new Label(j24,yValue,id);
										sheet.setColumnView(j24,18);
									   sheet.addCell(label19);
									}
									
								}
							}
							yValue = yValue + 1;
							
						}
					}
					
					break;
				*/
				case 25: 
					// Excel case for Traded Volume
					yValue++;				
					Label label25 = new Label(0, 0, "Traded Volume Index/Exchange Wise ", arial10format); 
					sheet.addCell(label25);
					yValue++;
					Logging.debug(" filter= "+ indexId);
					String label_heading25=" Stock Exchange :";
					String indExchName= indexName;
					
					if(indexId.equals("2")){
						label_heading25=" Index name : ";
						
					}
					Logging.debug(" Exchange or Index Name is : "+ indExchName);
					label_heading25 = label_heading25 + indExchName;
					label25=new Label(0,yValue,label_heading25,arial10format);
					sheet.addCell(label25);
					yValue++;
					label25 = new Label(0, yValue, "From Date   :"+fdate+"       To Date   : "+tdate, arial10format); 
					sheet.addCell(label25);
					yValue++;
					label25 = new Label(0, yValue, "Stock Name ", arial10format); 
					sheet.addCell(label25);
					label25 = new Label(1, yValue, "Traded Volume ", arial10format); 
					sheet.addCell(label25);
					
					String id25 = null;
					
					yValue++;
					if(ai.size()!= 0)
					{
						Iterator it25 = ai.iterator();
						while(it25.hasNext())
						{
							it25.next();
							for(int j25 = 0;j25<2;j25++)
							{
								if(it25.hasNext())
								{	
									id25=(String)it25.next();
									double dob=0.0;
									try{
										dob=Double.parseDouble(id25);
										jxl.write.Number num=new jxl.write.Number(j25,yValue,dob); 	
										sheet.setColumnView(j25,18);
										sheet.addCell(num); 
									}
									catch(Exception e){
										label25 = new Label(j25,yValue,id25);
										sheet.setColumnView(j25,18);
									    sheet.addCell(label25);
									}
									
								}
							}
							yValue = yValue + 1;
						}
					}
					
					break;

			}
			
		}catch(Exception e)
		{
			Logging.error("Error prevented the file from being created."+e);
		}
		
	}
	
	/**
	 * Overloaded funtion create_file for vector.
	 * 
	 * @param var_vec
	 * @param switch_code
	 * @param ai
	 * @param fdate
	 * @param tdate
	 */
	public void create_file(Vector var_vec,int switch_code,Vector ai,String fdate, String tdate)
	{ 
		ComposeIndex ci=ConnectInit.getComposeIndex();
	//	org.jfree.chart.demo.servlet.ComposeIndex ci=new org.jfree.chart.demo.servlet.ComposeIndex();
		try
		{  
			
			switch(switch_code)
			{
				case 10:
					yValue++;
					String label_heading10 = "Index Compare OHLC Report. ";
					label = new Label(0, yValue, label_heading10 , arial10format); 
					sheet.addCell(label);
					
					String fdate10 = "From date : " + fdate;
					String tdate10 = "To date : " + tdate;
					yValue=yValue+1;
					label = new Label(0, 2, fdate10, arial10format); 
					sheet.addCell(label);
					label = new Label(1, 2, tdate10, arial10format); 
					sheet.addCell(label);
					yValue=yValue+1;
					
					label = new Label(0, yValue, "Date ", arial10format); 
					sheet.addCell(label);
					
					String[] var = new String[var_vec.size()]; 
					if(var_vec.size()!= 0)
					{
				
						Iterator itv = var_vec.iterator();
						int var_v = 0;
						while(itv.hasNext())
						{
							String m = (String)itv.next();
							var[var_v] = m;
							var_v++;
						}
					}
					
					int disp = 1;
					int col_head = 1;
					for(int j= 0; j < var.length; j++)
					{
						int varint10 = Integer.parseInt(var[j]);
						String heading10 = ci.get_index_name(varint10);
						label = new Label(disp, yValue, heading10 , arial10format); 
						sheet.addCell(label);
						yValue++;
						disp = disp + 5;
						//display column headings
						label = new Label(col_head, yValue, "Open ", arial10format); 
						sheet.addCell(label);
						col_head++;
						label = new Label(col_head, yValue, "High ", arial10format); 
						sheet.addCell(label);
						col_head++;
						label = new Label(col_head, yValue, "Low", arial10format); 
						sheet.addCell(label);
						col_head++; 
						label = new Label(col_head, yValue, "Close", arial10format); 
						sheet.addCell(label);
						col_head = col_head + 2;
					}
					
					
					
			
					yValue++;
					String id10 = null;
					//int count10 = 7;
					if(ai.size()!= 0)
					{
						Iterator it10 = ai.iterator();
						while(it10.hasNext())
						{
							for(int j10 = 0;j10 < (var.length * 5); j10++)
							{
								if(j10 != 0 && j10%5 == 0)
								{
									j10 = j10 + 1;
								}
								if(it10.hasNext())
								{	
									id10=(String)it10.next();
									label = new Label(j10,yValue,id10);
									sheet.addCell(label);
								}
							}
							yValue = yValue + 1;
						}
				}
					
			break;
			case 15:
					
				    yValue++;
					String label_heading15 = "Index Correlation Report. ";
					label = new Label(0, yValue, label_heading15, arial10format); 
					sheet.addCell(label);
					yValue++;
					
					String fdate15 = "From date : " + fdate;
					String tdate15 = "To date : " + tdate;
					label = new Label(0,yValue, fdate15, arial10format); 
					sheet.addCell(label);
					label = new Label(1, yValue, tdate15, arial10format); 
					sheet.addCell(label);
					yValue++;yValue++;
					
					String[] var15 = new String[var_vec.size()]; 
					int mean_count = 0;
					if(var_vec.size()!= 0)
					{
				
						Iterator itv15 = var_vec.iterator();
						int var_v15 = 0;
						while(itv15.hasNext())
						{
							itv15.next();
							String m = (String)itv15.next();
							var15[var_v15] = m;
							var_v15++;
						}
					}
					
					int disp15 = 1;
					
					
					
					for(int j15= 0; j15 < var15.length; j15++)
					{
						String heading15 = var15[j15];
						label = new Label(disp15, yValue, heading15 ); 
						sheet.addCell(label);
						disp15 = disp15 + 1;
					}
					int count15 = yValue;
					
					for(int j15= 0; j15 < var15.length; j15++)
					{
						mean_count = mean_count + 1;
						String heading15 = var15[j15];
						label = new Label(0, yValue, heading15 ); 
						sheet.addCell(label);
						yValue = yValue + 1;
					}
					mean_count = mean_count / 2;
					String id15 = null;
					//int count15 = 6;
					//yValue=yValue+1;
					int col = 1; 
					if(ai.size()!= 0)
					{
						Iterator it15 = ai.iterator();
						while(it15.hasNext())
						{
							it15.next();
							for(int t = 0; t < mean_count; t++)
							{
								id15=(String)it15.next();
								label = new Label(col,count15,id15);
								sheet.addCell(label);
								col = col +1;
							}
							count15 = count15 + 1;
							col = 1;
						}
				    }
					yValue = count15+1;
			break;
			}
	
		}catch(Exception e)
		{
				Logging.error("Error prevented the file from being created."+e);
		}

	}
	
	/**
	 * Overloaded funtion for accepting Sting[].
	 * 
	 * @param arr
	 * @param switch_code
	 * @param vi
	 * @param fdate
	 * @param tdate
	 */
	public void create_file(String[] arr,int switch_code,Vector vi,String fdate, String tdate)
	{ 
		
		try
		{      
			yValue++;
			String label_heading14 = "Index Returns Volatality Report. ";
			label = new Label(0, yValue, label_heading14 , arial10format);
			sheet.addCell(label);
			yValue++;yValue++;
			
			String fdate14 = "From date : " + fdate;
			String tdate14 = "To date : " + tdate;
			label = new Label(0, yValue, fdate14, arial10format); 
			sheet.addCell(label);
			label = new Label(1, yValue, tdate14, arial10format); 
			sheet.addCell(label);
			yValue++;
			
			
			label = new Label(0, yValue, "Index Name ", arial10format); 
			sheet.addCell(label);
			label = new Label(1, yValue, "Periodic Returns ", arial10format); 
			sheet.addCell(label);
			label = new Label(2, yValue, "Volatality of return", arial10format); 
			sheet.addCell(label);
			yValue++;
			
			String id14 = null;
			
			if(vi.size()!= 0)
			{
			    Iterator it14 = vi.iterator();
				while(it14.hasNext())
				{
					
					for(int j14 = 0;j14 < 3; j14++)
					{
						id14=(String)it14.next();
						double dob=0.0;
						try{
							dob=Double.parseDouble(id14);
							jxl.write.Number num=new jxl.write.Number(j14,yValue,dob); 	
							sheet.setColumnView(j14,18);
							sheet.addCell(num); 
						}
						catch(Exception e){
							label = new Label(j14,yValue,id14);
							sheet.setColumnView(j14,18);
						    sheet.addCell(label);
						}
					}					
					yValue = yValue + 1;
				}
			}
						

		}catch(Exception e)
		{
				Logging.error("Error prevented the file from being created."+e);
		}

	}
	
	
	
	public void closeExcel(){
		try{
		workbook.write(); 
		workbook.close();
		}catch(Exception e)
		{
				Logging.error("Error prevented the file from being created 3."+e);
		}
				
	}

	
}
