/*
 * Created on Mar 1, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */ 
package app;  
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.jfree.chart.demo.servlet.AdjustDecimal;
import org.jfree.chart.demo.servlet.ComposeIndex;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import sysconfig.model.indexMovement;
import sysconfig.model.stockDetailFromDate;

import com.harrier.initializeation.ConnectInit;
/**
 * @author rahul
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MakeExcel 
{
	Logger Logging = Logger.getLogger(MakeExcel.class);
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
	 * @param var1
	 */
	
	WritableFont arial10font = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE  ,Colour.BLUE);
	WritableCellFormat arial10format = new WritableCellFormat (arial10font); 
	int jCount;
	public void create_file(String var,int switch_code,Vector ai,String fdate, String tdate, String var1)
	{ 
		
		Vector vec;
//		org.jfree.chart.demo.servlet.ComposeIndex ci=new org.jfree.chart.demo.servlet.ComposeIndex();
		ComposeIndex ci=ConnectInit.getComposeIndex();
		try
		{  
			String pathf = Connect.getCoolMenuspath();
			/**
			 * The excel sheet headings are created according to the type of report.  
			 */
			
			switch(switch_code)
			{
				case 1: 
					int val;
					try {
						val=Integer.parseInt(var);
					} catch (RuntimeException e1) {
						// TODO Auto-generated catch block
					//	e1.printStackTrace();
						Logging.debug(e1);
						val=0;
					}
					pathf = pathf + "CoolMenus/" + "IndexCompositionReport.xls";
					WritableWorkbook workbook = Workbook.createWorkbook(new File(pathf));
					WritableSheet sheet = workbook.createSheet("Stockpile", 0);
					Label label = new Label(0, 0, "Index Composition Report ", arial10format); 
					sheet.addCell(label);
					label = new Label(0, 3, "Index Name :"+ci.get_index_name(val), arial10format); 
					sheet.addCell(label);
					label = new Label(0, 5, "Stock name ", arial10format); 
					sheet.addCell(label);
					label = new Label(1, 5, "Total shares ", arial10format); 
					sheet.addCell(label);
					label = new Label(2, 5, "IWF ", arial10format); 
					sheet.addCell(label);
					label = new Label(3, 5, "Market lot ", arial10format); 
					sheet.addCell(label);
					label = new Label(4, 5, "Price (LTP) ", arial10format); 
					sheet.addCell(label);
					label = new Label(5, 5, "Price (Last) ", arial10format); 
					sheet.addCell(label);
					label = new Label(6, 5, "Currency ", arial10format); 
					sheet.addCell(label);
					label = new Label(7, 5, "Currency Exch. rate ", arial10format); 
					sheet.addCell(label);
					label = new Label(8, 5, "Market cap. ", arial10format); 
					sheet.addCell(label);
					label = new Label(9, 5, "Adjusted market cap. ", arial10format); 
					sheet.addCell(label);
					label = new Label(10, 5, "Weightage ", arial10format); 
					sheet.addCell(label);
					label = new Label(11, 5, "Date ", arial10format); 
					sheet.addCell(label);
					
					String id = null;
					int count = 6;
					Label label1;
					if(ai.size()!= 0)
					{
						Iterator it = ai.iterator();
						while(it.hasNext())
						{
					
							it.next();
							for(int j = 0;j<12;j++)
							{
								if(it.hasNext())
								{	
									id=(String)it.next();
									label1 = new Label(j,count,id);
									sheet.addCell(label1);
								}
							}
							count = count + 1;
						}
					}
					workbook.write(); 
					workbook.close();
					break;
					case 2:
						pathf = pathf + "CoolMenus/" + "CompanyWiseWeightage.xls";
						WritableWorkbook workbook2 = Workbook.createWorkbook(new File(pathf));
						WritableSheet sheet2 = workbook2.createSheet("Stockpile", 0);
						Label label2 = new Label(0, 0, "Company Wise Weightage Report ", arial10format); 
						sheet2.addCell(label2);
						label2 = new Label(0, 1, "Company name ", arial10format); 
						sheet2.addCell(label2);
						label2 = new Label(1, 1, "Market Cap. ", arial10format); 
						sheet2.addCell(label2);
						label2 = new Label(2, 1, "Weightage(%) ", arial10format); 
						sheet2.addCell(label2);
						String id1 = null;
						int count1 = 3;
						org.jfree.chart.demo.servlet.FieldSort sort2=new org.jfree.chart.demo.servlet.FieldSort();         
					    int diri=0,dir1i=0,dir2i=0;int fieldnoi = 0;
					   if(fieldnoi==0)
				        {
				        	diri=sort2.getcount();
				          	ai=sort2.SetOrderSort(ai,0,3);
				        }
						  if(fieldnoi==1)
				          {
				          	dir1i=sort2.getcount1();
				          	ai=sort2.SetOrderSortNo(ai,1,3);
				          }
				          if(fieldnoi==2)
				          {
				          	dir2i=sort2.getcount2();
				          	ai=sort2.SetOrderSortNo(ai,2,3);
				          }    
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
										label2 = new Label(j1,count1,id1);
										sheet2.addCell(label2);
									}
								}
								count1 = count1 + 1;
							}
						}
						workbook2.write(); 
						workbook2.close();
						break;
					case 3:
						pathf = pathf + "CoolMenus/" + "IndWiseWeightage.xls";
						WritableWorkbook workbook3 = Workbook.createWorkbook(new File(pathf));
						WritableSheet sheet3 = workbook3.createSheet("Stockpile", 0);
						String label_heading = "Industry Wise Weightage Report for index : ";
						int var_int = Integer.parseInt(var);
		           		label_heading = label_heading +" " + ci.get_index_name(var_int);
						Label label3 = new Label(0, 0, label_heading , arial10format); 
						sheet3.addCell(label3);
						label3 = new Label(0, 1, "Industry name ", arial10format); 
						sheet3.addCell(label3);
						label3 = new Label(1, 1, "Market Cap. ", arial10format); 
						sheet3.addCell(label3);
						label3 = new Label(2, 1, "Weightage(%) ", arial10format); 
						sheet3.addCell(label3);
						String id3 = null;
						int count3 = 3;
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
										label3 = new Label(j3,count3,id3);
										sheet3.addCell(label3);
									}
								}
								count3 = count3 + 1;
							}
						}
						workbook3.write(); 
						workbook3.close();
						break;
					case 4: 
						pathf = pathf + "CoolMenus/" + "IndexList.xls";
						WritableWorkbook workbook4 = Workbook.createWorkbook(new File(pathf));
						WritableSheet sheet4 = workbook4.createSheet("Stockpile", 0);
						Label label4 = new Label(0, 0, "Index List Report ", arial10format); 
						sheet4.addCell(label4);
						label4 = new Label(0, 1, "Index name ", arial10format); 
						sheet4.addCell(label4);
						label4 = new Label(1, 1, "Value ", arial10format); 
						sheet4.addCell(label4);
						label4 = new Label(2, 1, "Open ", arial10format); 
						sheet4.addCell(label4);
						label4 = new Label(3, 1, "High ", arial10format); 
						sheet4.addCell(label4);
						label4 = new Label(4, 1, "Low ", arial10format); 
						sheet4.addCell(label4);
						label4 = new Label(5, 1, "Last closing ", arial10format); 
						sheet4.addCell(label4);
						label4 = new Label(6, 1, "(%) Change ", arial10format); 
						sheet4.addCell(label4);
						label4 = new Label(7, 1, "Market cap", arial10format); 
						sheet4.addCell(label4);
						label4 = new Label(8, 1, "Divisor ", arial10format); 
						sheet4.addCell(label4);
						label4 = new Label(9, 1, "Currency ", arial10format); 
						sheet4.addCell(label4);
						label4 = new Label(10, 1, "Date ", arial10format); 
						sheet4.addCell(label4);
						
						String id4 = null;
						int count4 = 3;
						if(ai.size()!= 0)
						{
							Iterator it4 = ai.iterator();
							while(it4.hasNext()) 
							{
								it4.next();
								for(int j4 = 0;j4<11;j4++)
								{
									if(it4.hasNext())
									{	
										id4=(String)it4.next();
										if(!id4.toString().trim().equals("down"))
										{	if(!id4.toString().trim().equals("up"))
											{
												label4 = new Label(j4,count4,id4);
												sheet4.addCell(label4);
											}
										}
										if(id4.toString().trim().equals("down"))
										{
											j4 = j4 -1;
										}
										if(id4.toString().trim().equals("up"))
										{
											j4 = j4 -1;
										}
									}
								}
								count4 = count4 + 1;
							}
						}
						workbook4.write(); 
						workbook4.close();
						break;
					case 5:
						pathf = pathf + "CoolMenus/" + "StockContribution.xls";
						WritableWorkbook workbook5 = Workbook.createWorkbook(new File(pathf));
						WritableSheet sheet5 = workbook5.createSheet("Stockpile", 0);
						String label_heading5 = "Stock Contribution to change in index report for index : ";
						int var_int5 = Integer.parseInt(var);
		           		label_heading5 = label_heading5 +" " + ci.get_index_name(var_int5);
						Label label5 = new Label(0, 0, label_heading5 , arial10format); 
						sheet5.addCell(label5);
						String fdate5 = "From date : " + fdate;
						String tdate5 = "To date : " + tdate;
						label5 = new Label(0, 2, fdate5, arial10format); 
						sheet5.addCell(label5);
						label5 = new Label(3, 2, tdate5, arial10format); 
						sheet5.addCell(label5);
						label5 = new Label(0, 3, "Stock name ", arial10format); 
						sheet5.addCell(label5);
						label5 = new Label(1, 3, "Index mkt. cap. difference ", arial10format); 
						sheet5.addCell(label5);
						label5 = new Label(2, 3, "Stock mkt. cap. difference ", arial10format); 
						sheet5.addCell(label5);
						label5 = new Label(3, 3, "Weightage", arial10format); 
						sheet5.addCell(label5);
						String id5 = null;
						int count5 = 4;
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
										label5 = new Label(j5,count5,id5);
										sheet5.addCell(label5);
									}
								}
								count5 = count5 + 1;
							}
						}
						workbook5.write(); 
						workbook5.close();
						break;
					
					case 8:
						pathf = pathf + "CoolMenus/" + "LatestIndexDivisor.xls";
						WritableWorkbook workbook7 = Workbook.createWorkbook(new File(pathf));
						WritableSheet sheet7 = workbook7.createSheet("Stockpile", 0);
						String label_heading7 = "Latest Index Divisor. ";
						Label label7 = new Label(0, 0, label_heading7 , arial10format); 
						sheet7.addCell(label7);
						label7 = new Label(0, 2, "Index Name", arial10format); 
						sheet7.addCell(label7);
						label7 = new Label(1, 2, "Divisor ", arial10format); 
						sheet7.addCell(label7);
				//		org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
						AdjustDecimal ad = ConnectInit.getAdjustDecimal();
						String id7 = null;
						int count7 = 3;
						if(ai.size()!= 0)
						{
						    Iterator it7 = ai.iterator();
							while(it7.hasNext())
							{
								it7.next(); 
								for(int j7 = 0;j7<2;j7++)
								{
									if(it7.hasNext())
									{	
										
										id7=(String)it7.next();
										if(j7%2 != 0)
										{
											id7 = ad.twodigitdeci(id7);
										}
										label7 = new Label(j7,count7,id7);
										sheet7.addCell(label7);
									}
								}
								count7 = count7 + 1;
							}
						}
						workbook7.write(); 
						workbook7.close();
						break;
					case 12:
						pathf = pathf + "CoolMenus/" + "IndexPerformance.xls";
						WritableWorkbook workbook12 = Workbook.createWorkbook(new File(pathf));
						WritableSheet sheet12 = workbook12.createSheet("Stockpile", 0);
						String label_heading12 = "Index Performance for various Indices. ";
						Label label12 = new Label(0, 0, label_heading12 , arial10format); 
						sheet12.addCell(label12);
						String heading12 = "For Date : " + fdate;
						label12 = new Label(0, 1, heading12 ); 
						sheet12.addCell(label12);
						label12 = new Label(0, 2, "Index Name", arial10format); 
						sheet12.addCell(label12);
						label12 = new Label(1, 2, "1 Month(In %) ", arial10format); 
						sheet12.addCell(label12);
						label12 = new Label(2, 2, "3 Month(In %) ", arial10format); 
						sheet12.addCell(label12);
						label12 = new Label(3, 2, "6 Month(In %) ", arial10format); 
						sheet12.addCell(label12);
						label12 = new Label(4, 2, "1 Year(In %) ", arial10format); 
						sheet12.addCell(label12);
						String id12 = null;
						int count12 = 3;
						if(ai.size()!= 0)
						{
						    Iterator it12 = ai.iterator();
							while(it12.hasNext())
							{
								it12.next(); 
								for(int j12 = 0; j12 < 5; j12++)
								{
									if(it12.hasNext())
									{	
										id12=(String)it12.next();
										label12 = new Label(j12,count12,id12);
										sheet12.addCell(label12);
									}
								}
								count12 = count12 + 1;
							}
						}
						workbook12.write(); 
						workbook12.close();
						break;
					case 16:
						pathf = pathf + "CoolMenus/" + "CapitalChangeToUniverse.xls";
						WritableWorkbook workbook16 = Workbook.createWorkbook(new File(pathf));
						WritableSheet sheet16 = workbook16.createSheet("Stockpile", 0);
						String label_heading16 = "Stock Exchange name : ";
						//int var_int16 = Integer.parseInt(var);
		           		//label_heading16 = label_heading16 +" " + ci.get_index_name(var_int16);
						Label label16 = new Label(0, 0, label_heading16 , arial10format); 
						sheet16.addCell(label16);
						String fdate16 = "From date : " + fdate;
						String tdate16 = "To date : " + tdate;
						label16 = new Label(0, 2, fdate16, arial10format); 
						sheet16.addCell(label16);
						label16 = new Label(3, 2, tdate16, arial10format); 
						sheet16.addCell(label16);
						label16 = new Label(0, 3, "Stock name ", arial10format); 
						sheet16.addCell(label16);
						label16 = new Label(1, 3, "Face value", arial10format); 
						sheet16.addCell(label16);
						label16 = new Label(2, 3, "Total issuable shares", arial10format); 
						sheet16.addCell(label16);
						label16 = new Label(3, 3, "Mkt. Cap value", arial10format); 
						sheet16.addCell(label16);
						label16 = new Label(4, 3, "Investible weight factor", arial10format); 
						sheet16.addCell(label16);
						label16 = new Label(5, 3, "Corporate action", arial10format); 
						sheet16.addCell(label16);
						label16 = new Label(6, 3, "Applied date", arial10format); 
						sheet16.addCell(label16);
						String id16 = null;
						int count16 = 4;
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
										label16 = new Label(j16,count16,id16);
										sheet16.addCell(label16);
									}
								}
								count16 = count16 + 1;
							}
						}
						workbook16.write(); 
						workbook16.close();
						break;
					case 13:
						pathf = pathf + "CoolMenus/" + "StockPerformanceReport.xls";
						WritableWorkbook workbook13 = Workbook.createWorkbook(new File(pathf));
						WritableSheet sheet13 = workbook13.createSheet("Stockpile", 0);
						String label_heading13 = "Index name : ";
						int var_int13 = Integer.parseInt(var);
		           		label_heading13 = label_heading13 +" " + ci.get_index_name(var_int13);
						Label label13 = new Label(0, 0, label_heading13 , arial10format); 
						sheet13.addCell(label13);
						String fdate13 = "From date : " + fdate;
						String tdate13 = "To date : " + tdate;
						label13 = new Label(0, 2, fdate13, arial10format); 
						sheet13.addCell(label13);
						label13 = new Label(3, 2, tdate13, arial10format); 
						sheet13.addCell(label13);
						label13 = new Label(0, 3, "Stock Name ", arial10format); 
						sheet13.addCell(label13);
						label13 = new Label(1, 3, "Issued Capitals(In millions) ", arial10format); 
						sheet13.addCell(label13);
						label13 = new Label(2, 3, "Mkt. cap.(In millions)", arial10format); 
						sheet13.addCell(label13);
						label13 = new Label(3, 3, "Weightage", arial10format); 
						sheet13.addCell(label13);
						label13 = new Label(4, 3, "Beta", arial10format); 
						sheet13.addCell(label13);
						label13 = new Label(5, 3, "R2", arial10format); 
						sheet13.addCell(label13);
						label13 = new Label(6, 3, "Average Daily Volatality(%)", arial10format); 
						sheet13.addCell(label13);
						label13 = new Label(7, 3, "Periodic Returns", arial10format); 
						sheet13.addCell(label13);
						String id13 = null;
						int count13 = 4;
						if(ai.size()!= 0)
						{
						    Iterator it13 = ai.iterator();
							while(it13.hasNext())
							{
								it13.next();
								for(int j13 = 0;j13<8;j13++)
								{
									if(it13.hasNext())
									{	
										id13=(String)it13.next();
										label13 = new Label(j13,count13,id13);
										sheet13.addCell(label13);
									}
								}
								count13 = count13 + 1;
							}
						}
						workbook13.write(); 
						workbook13.close();
						break;
					case 17:
						String ex_name = null;
						try{
							PreparedStatement pstdata;
							ResultSet rs;
							Connection con=null; 
							Connect connect = ConnectInit.getConnect();
							pstdata = Connect.con.prepareStatement(ConnectInit.queries.getProperty("get_stock_exchange_name"));
							int var_i = Integer.parseInt(var);
							pstdata.setInt(1,var_i);
							rs = pstdata.executeQuery();
							while(rs.next())
						    {
						    	ex_name = rs.getString(1);
						    }
						}catch(Exception e){Logging.debug("Error in query ."+e);}
						pathf = pathf + "CoolMenus/" + "InactiveStockList.xls";
						WritableWorkbook workbook17 = Workbook.createWorkbook(new File(pathf));
						WritableSheet sheet17 = workbook17.createSheet("Stockpile", 0);
						String label_heading17 = "Stock List Of Inactive Stocks  . " ;
						Label label17 = new Label(0, 0, label_heading17, arial10format );
						sheet17.addCell(label17);
						String heading17 = "Exchange name : " + ex_name;
						label17 = new Label(0, 1, heading17 , arial10format);
						sheet17.addCell(label17); 
						label17 = new Label(0, 3, "Stock Name", arial10format); 
						sheet17.addCell(label17);
						label17 = new Label(1, 3, "Outstanding Shares ", arial10format); 
						sheet17.addCell(label17);
						label17 = new Label(2, 3, "Price (LTP) ", arial10format); 
						sheet17.addCell(label17);
						label17 = new Label(3, 3, "Mkt. Cap. ", arial10format); 
						sheet17.addCell(label17);
						label17 = new Label(4, 3, "Face Value ", arial10format); 
						sheet17.addCell(label17);
						label17 = new Label(5, 3, "Date ", arial10format); 
						sheet17.addCell(label17);
						String id17 = null;
						int count17 = 4;
						if(ai.size()!= 0)
						{
						    Iterator it17 = ai.iterator();
							while(it17.hasNext())
							{
								it17.next(); 
								for(int j17 = 0; j17 < 6; j17++)
								{
									if(it17.hasNext())
									{	
										id17=(String)it17.next();
										label17 = new Label(j17,count17,id17);
										sheet17.addCell(label17);
									}
								}
								count17 = count17 + 1;
							}
						}
						workbook17.write(); 
						workbook17.close();
						break;
					case 18: 
						pathf = pathf + "CoolMenus/" + "ShareHoldingPattern.xls";
						WritableWorkbook workbook18 = Workbook.createWorkbook(new File(pathf));
						WritableSheet sheet18 = workbook18.createSheet("Stockpile", 0);
						org.jfree.chart.demo.servlet.AdjustDecimal ab = new org.jfree.chart.demo.servlet.AdjustDecimal();
						Label label18 = new Label(0, 0, "Share Holding Pattern Report ", arial10format); 
						sheet18.addCell(label18);
						
						label18 = new Label(1, 4, "Category", arial10format); 
						sheet18.addCell(label18);
						label18 = new Label(2, 4, "Sub Category", arial10format); 
						sheet18.addCell(label18);
						label18 = new Label(3, 4, "No Of Securities Held ", arial10format); 
						sheet18.addCell(label18);
						label18 = new Label(4, 4, "% Holding", arial10format); 
						sheet18.addCell(label18);
						label18 = new Label(1, 5, "Promotor's Holding", arial10format); 
						sheet18.addCell(label18);
						label18 = new Label(2, 6, "Indian Promoters", arial10format); 
						sheet18.addCell(label18);
						label18 = new Label(2, 7, "Foreign Promoters", arial10format); 
						sheet18.addCell(label18);
						label18 = new Label(2, 8, "Persons Acting in Concert ", arial10format); 
						sheet18.addCell(label18);
						label18 = new Label(2, 9, "Sub Total ", arial10format); 
						sheet18.addCell(label18);
						label18 = new Label(1, 11, "Institutional Invstors ", arial10format); 
						sheet18.addCell(label18);
						label18 = new Label(2, 12, "Mutual Funds and UTI", arial10format); 
						sheet18.addCell(label18);
						label18 = new Label(2, 13, "Banks, FIs, Insurance Co.s, Central / State Govt. / Non-Govt.Institutions", arial10format); 
						sheet18.addCell(label18);
						label18 = new Label(2, 14, "FIIs", arial10format); 
						sheet18.addCell(label18);
						label18 = new Label(2, 15, "Sub Total ", arial10format); 
						sheet18.addCell(label18);
						label18 = new Label(1, 17, "Sub Total ", arial10format); 
						sheet18.addCell(label18);
						label18 = new Label(2, 18, "Private Corporate Bodies", arial10format); 
						sheet18.addCell(label18);
						label18 = new Label(2, 19, "Indian Public", arial10format); 
						sheet18.addCell(label18);
						label18 = new Label(2, 20, "NRI / OCBs", arial10format); 
						sheet18.addCell(label18);
						label18 = new Label(2, 21, "Any Other", arial10format); 
						sheet18.addCell(label18);
						label18 = new Label(2, 22, "Sub Total ", arial10format); 
						sheet18.addCell(label18);
						label18 = new Label(2, 23, "Grand Total ", arial10format); 
						sheet18.addCell(label18);
						
						float gtotal1 = 0;
						float gtotal2 = 0;
						String id18 = null;
						int count18 = 6;
						float sub_total1 = 0;
						float sub_total2 = 0;
						Iterator it18 = ai.iterator();
						if(ai.size()!= 0)
						{
							
							for(int u = 0; u < 3; u++)
							{
								if(it18.hasNext())
								{
								it18.next();
								it18.next();
								for(int j18 = 3;j18<5;j18++)
								{
									if(it18.hasNext())
									{	
										id18=(String)it18.next();
										float id_int18 = Float.parseFloat(id18);
										if(j18%2 == 0)
										{
											sub_total1 = sub_total1 + id_int18; 
										}
										else 
										{
											sub_total2 = sub_total2 + id_int18;
										}
										label18 = new Label(j18,count18,id18);
										sheet18.addCell(label18);
									}
								}
								count18 = count18 + 1;
								}
							}
							String sub_st2 = ab.shareholdingpatt(sub_total2);
							sub_st2 = ab.twodigitdeci(sub_st2);
							label18 = new Label(3,9,sub_st2);
							sheet18.addCell(label18);
							String sub_st1 = ab.shareholdingpatt(sub_total1);
							sub_st1 = ab.twodigitdeci(sub_st1);
							label18 = new Label(4,9,sub_st1);
							sheet18.addCell(label18);
							
						}
						//--------------//
						gtotal1 = gtotal1 + sub_total1;
						gtotal2 = gtotal2 + sub_total2;
						
						sub_total1 = 0;
						sub_total2 = 0;
						if(ai.size()!= 0)
						{
							count18 = 12;
							for(int u = 0; u < 3; u++)
							{
								if(it18.hasNext())
								{
								it18.next();
								it18.next();
								for(int j18 = 3;j18<5;j18++)
								{
									if(it18.hasNext())
									{	
										id18=(String)it18.next();
										float id_int18 = Float.parseFloat(id18);
										if(j18%2 == 0)
										{
											sub_total1 = sub_total1 + id_int18; 
										}
										else 
										{
											sub_total2 = sub_total2 + id_int18;
										}
										label18 = new Label(j18,count18,id18);
										sheet18.addCell(label18);
									}
								}
								count18 = count18 + 1;
								}
							}
							String sub_st2 = ab.shareholdingpatt(sub_total2);
							sub_st2 = ab.twodigitdeci(sub_st2);
							label18 = new Label(3,15,sub_st2);
							sheet18.addCell(label18);
							String sub_st1 = ab.shareholdingpatt(sub_total1);
							sub_st1 = ab.twodigitdeci(sub_st1);
							label18 = new Label(4,15,sub_st1);
							sheet18.addCell(label18);
							
						}
						//------------------------//
						gtotal1 = gtotal1 + sub_total1;
						gtotal2 = gtotal2 + sub_total2;
						sub_total1 = 0;
						sub_total2 = 0;
						
						try{
						if(ai.size()!= 0)
						{
							count18 = 18;
							for(int u = 0; u < 4; u++)
							{
								try{
									it18.next();
									it18.next();
									}catch(Exception t){}
								if(it18.hasNext())
								{
								
								for(int j18 = 3;j18<5;j18++)
								{
									
									if(it18.hasNext())
									{	
										id18=(String)it18.next();
										float id_int18 = Float.parseFloat(id18);
										if(j18%2 == 0)
										{
											sub_total1 = sub_total1 + id_int18; 
										}
										else 
										{
											sub_total2 = sub_total2 + id_int18;
										}
										label18 = new Label(j18,count18,id18);
										sheet18.addCell(label18);
									}
								}
								count18 = count18 + 1;
								}
							}
							gtotal1 = gtotal1 + sub_total1;
							gtotal2 = gtotal2 + sub_total2;
							Logging.debug("wwwww...."+gtotal1);
							String sub_st2 = ab.shareholdingpatt(sub_total2);
							sub_st2 = ab.twodigitdeci(sub_st2);
							label18 = new Label(3,22,sub_st2);
							sheet18.addCell(label18);
							
							String sub_st1 = new Float(sub_total1).toString();
							sub_st1 = ab.twodigitdeci(sub_st1);
							label18 = new Label(4,22,sub_st1);
							sheet18.addCell(label18);
							
							
							String sub_st2g = ab.shareholdingpatt(gtotal2);
							sub_st2g = ab.twodigitdeci(sub_st2g);
							label18 = new Label(3,23,sub_st2g);
							sheet18.addCell(label18);
							
							String sub_st1g = new Float(gtotal1).toString();
							String sub_stgg = ab.twodigitdeci(sub_st1g);
							label18 = new Label(4,23,sub_stgg);
							sheet18.addCell(label18);
								 
						}
						}catch(Exception r){}
						workbook18.write(); 
						workbook18.close();
						break;
					case 19: 
						
						pathf = pathf + "CoolMenus/" + "IndexDivisor.xls";
						WritableWorkbook workbook19 = Workbook.createWorkbook(new File(pathf));
						WritableSheet sheet19 = workbook19.createSheet("Stockpile", 0);
						Label label19 = new Label(0, 0, "Index Divisor Report ", arial10format); 
						sheet19.addCell(label19);
						label19 = new Label(0,2, "Index Name : "+ci.getIndexName(var), arial10format); 
						sheet19.addCell(label19);
						label19 = new Label(0, 3, "From Date   :"+fdate+"       To Date   : "+tdate, arial10format); 
						sheet19.addCell(label19);
						label19 = new Label(0, 6, "Trading Date ", arial10format); 
						sheet19.addCell(label19);
						label19 = new Label(1, 6, "Close ", arial10format); 
						sheet19.addCell(label19);
						label19 = new Label(2, 6, "MKt. Cap ", arial10format); 
						sheet19.addCell(label19);
						label19 = new Label(3, 6, "Divisor ", arial10format); 
						sheet19.addCell(label19);
						
						id = null;
						count = 7;
						if(ai.size()!= 0)
						{
							Iterator it = ai.iterator();
							while(it.hasNext())
							{
								indexMovement im=(indexMovement)it.next();
										label19 = new Label(0,count,im.getTradingDate());
										sheet19.addCell(label19);
										label19 = new Label(1,count,im.getClose());
										sheet19.addCell(label19);
										label19 = new Label(2,count,im.getMCap());
										sheet19.addCell(label19);
										label19 = new Label(3,count,im.getDivisor());
										sheet19.addCell(label19);
								
								count = count + 1;
							}
						}
						workbook19.write(); 
						workbook19.close();
						break;
					case 20: 
						pathf = pathf + "CoolMenus/" + "IndexPe_Pb.xls";
						WritableWorkbook workbook20 = Workbook.createWorkbook(new File(pathf));
						WritableSheet sheet20 = workbook20.createSheet("Stockpile", 0);
						Label label20 = new Label(0, 0, "Index PE/PB Report ", arial10format); 
						sheet20.addCell(label20);
						label20 = new Label(0, 1, "Trading Date ", arial10format); 
						sheet20.addCell(label20);
						label20 = new Label(1, 1, "Close ", arial10format); 
						sheet20.addCell(label20);
						label20 = new Label(2, 1, "%Change ", arial10format); 
						sheet20.addCell(label20);
						label20 = new Label(3, 1, "Market cap.  ", arial10format); 
						sheet20.addCell(label20);
						label20 = new Label(4, 1, "Share Traded ", arial10format); 
						sheet20.addCell(label20);
						label20 = new Label(5, 1, "Turn Over ", arial10format); 
						sheet20.addCell(label20);
						label20 = new Label(6, 1, "P/E Ratio ", arial10format); 
						sheet20.addCell(label20);
						label20 = new Label(7, 1, "P/B Ratio ", arial10format); 
						sheet20.addCell(label20);
						label20 = new Label(8, 1, "Divident Yield ", arial10format); 
						sheet20.addCell(label20);
						
						id = null;
						count = 3;
						
						if(ai.size()!= 0)
						{
							Iterator it = ai.iterator();
							while(it.hasNext())
							{
						
								//it.next();
								for(int j = 0;j<9;j++)
								{
									if(it.hasNext())
									{	
										id=(String)it.next();
										label20 = new Label(j,count,id);
										sheet20.addCell(label20);
									}
								}
								count = count + 1;
							}
						}
						workbook20.write(); 
						workbook20.close();
						break;
						
					case 21: 
						pathf = pathf + "CoolMenus/" + "StockList.xls";
						workbook = Workbook.createWorkbook(new File(pathf));
						sheet = workbook.createSheet("Stockpile", 0);
						label = new Label(0, 0, "StockList ", arial10format); 
						sheet.addCell(label);
						label = new Label(0, 1, "Stock name ", arial10format); 
						sheet.addCell(label);
						label = new Label(1, 1, "Outstanding Shares ", arial10format); 
						sheet.addCell(label);
						label = new Label(2, 1, "Price(LTP) ", arial10format); 
						sheet.addCell(label);
						label = new Label(3, 1, "Market Cap ", arial10format); 
						sheet.addCell(label);
						label = new Label(4, 1, "Face Value ", arial10format); 
						sheet.addCell(label);
						label = new Label(5, 1, "Date ", arial10format); 
						sheet.addCell(label);
						
						
						id = null;
						count = 3;
						
						if(ai.size()!= 0)
						{
							Iterator it = ai.iterator();
							while(it.hasNext())
							{
						
								it.next();
								for(int j = 0;j<6;j++)
								{
									if(it.hasNext())
									{	
										id=(String)it.next();
										label1 = new Label(j,count,id);
										sheet.addCell(label1);
									}
								}
								count = count + 1;
							}
						}
						workbook.write(); 
						workbook.close();
						break;
						
					case 24: 
						
						pathf = pathf + "CoolMenus/" + "IndexMovement.xls";
						workbook19 = Workbook.createWorkbook(new File(pathf));
						sheet19 = workbook19.createSheet("Stockpile", 0);
						label19 = new Label(0, 0, "Index Movement ", arial10format); 
						sheet19.addCell(label19);
						label19 = new Label(0,2, "Index Name : "+ci.getIndexName(var), arial10format); 
						sheet19.addCell(label19);
						label19 = new Label(0, 3, "From Date   :"+fdate+"       To Date   : "+tdate, arial10format); 
						sheet19.addCell(label19);
						label19 = new Label(0, 6, "Trading Date ", arial10format); 
						sheet19.addCell(label19);
						label19 = new Label(1, 6, "Close ", arial10format); 
						sheet19.addCell(label19);
						label19 = new Label(2, 6, "MKt. Cap ", arial10format); 
						sheet19.addCell(label19);
						label19 = new Label(3, 6, "Divisor ", arial10format); 
						sheet19.addCell(label19);
						
						id = null;
						count = 7;
						if(ai.size()!= 0)
						{
							Iterator it = ai.iterator();
							while(it.hasNext())
							{
								indexMovement im=(indexMovement)it.next();
										label19 = new Label(0,count,im.getTradingDate());
										sheet19.addCell(label19);
										label19 = new Label(1,count,im.getClose());
										sheet19.addCell(label19);
										label19 = new Label(2,count,im.getMCap());
										sheet19.addCell(label19);
										label19 = new Label(3,count,im.getDivisor());
										sheet19.addCell(label19);
								
								count = count + 1;
							}
						}
						workbook19.write(); 
						workbook19.close();
						break;
			}
			
		}catch(Exception e)
		{
			Logging.debug("Error prevented the file from being created."+e);
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
		Vector vec;
	//	org.jfree.chart.demo.servlet.ComposeIndex ci=new org.jfree.chart.demo.servlet.ComposeIndex();
		ComposeIndex ci=ConnectInit.getComposeIndex();
		try
		{  
			String pathf = Connect.getCoolMenuspath();
			switch(switch_code)
			{
				case 10:
					pathf = pathf + "CoolMenus/" + "IndexCompareOHLC.xls";
					WritableWorkbook workbook10 = Workbook.createWorkbook(new File(pathf));
					WritableSheet sheet10 = workbook10.createSheet("Stockpile", 0);
					String label_heading10 = "Index Comparison Report. ";
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
					int var_int10 = var.length;
					int disp = 1;
					int col_head = 1;
					for(int j= 0; j < var.length; j++)
					{
						int varint10 = Integer.parseInt(var[j]);
						String heading10 = ci.get_index_name(varint10);
						Label label10 = new Label(disp, 5, heading10 , arial10format); 
						sheet10.addCell(label10);
						disp = disp + 5;
						//display column headings
						label10 = new Label(col_head, 6, "Open ", arial10format); 
						sheet10.addCell(label10);
						col_head++;
						label10 = new Label(col_head, 6, "High ", arial10format); 
						sheet10.addCell(label10);
						col_head++;
						label10 = new Label(col_head, 6, "Low", arial10format); 
						sheet10.addCell(label10);
						col_head++; 
						label10 = new Label(col_head, 6, "Close", arial10format); 
						sheet10.addCell(label10);
						col_head = col_head + 2;
					}
					Label label10 = new Label(0, 1, label_heading10 , arial10format); 
					sheet10.addCell(label10);
			
					String fdate10 = "From date : " + fdate;
					String tdate10 = "To date : " + tdate;
					label10 = new Label(0, 2, fdate10, arial10format); 
					sheet10.addCell(label10);
					label10 = new Label(1, 2, tdate10, arial10format); 
					sheet10.addCell(label10);
					label10 = new Label(0, 6, "Date ", arial10format); 
					sheet10.addCell(label10);
			
					String id10 = null;
					int count10 = 7;
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
									label10 = new Label(j10,count10,id10);
									sheet10.addCell(label10);
								}
							}
							count10 = count10 + 1;
						}
				}
					workbook10.write(); 
					workbook10.close();
			break;
				case 15:
					pathf = pathf + "CoolMenus/" + "IndexCorrelation.xls";
					WritableWorkbook workbook15 = Workbook.createWorkbook(new File(pathf));
					WritableSheet sheet15 = workbook15.createSheet("Stockpile", 0);
					String label_heading15 = "Index Correlation Report. ";
					Label label15 = new Label(0, 0, label_heading15, arial10format); 
					sheet15.addCell(label15);
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
					int var_int15 = var15.length;
					int disp15 = 1;
					int col_head15 = 1;
					String fdate15 = "From date : " + fdate;
					String tdate15 = "To date : " + tdate;
					label15 = new Label(0, 2, fdate15, arial10format); 
					sheet15.addCell(label15);
					label15 = new Label(1, 2, tdate15, arial10format); 
					sheet15.addCell(label15);
					for(int j15= 0; j15 < var15.length; j15++)
					{
						String heading15 = var15[j15];
						label15 = new Label(disp15, 5, heading15 ); 
						sheet15.addCell(label15);
						disp15 = disp15 + 1;
					}
					int d15 = 6;
					for(int j15= 0; j15 < var15.length; j15++)
					{
						mean_count = mean_count + 1;
						String heading15 = var15[j15];
						label15 = new Label(0, d15, heading15 ); 
						sheet15.addCell(label15);
						d15 = d15 + 1;
					}
					mean_count = mean_count / 2;
					String id15 = null;
					int count15 = 6;
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
								label15 = new Label(col,count15,id15);
								sheet15.addCell(label15);
								col = col +1;
							}
							count15 = count15 + 1;
							col = 1;
						}
				    }
					workbook15.write(); 
					workbook15.close();
			break;
			}
	
		}catch(Exception e)
		{
				Logging.debug("Error prevented the file from being created."+e);
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
		Vector vec;
//		org.jfree.chart.demo.servlet.ComposeIndex ci=new org.jfree.chart.demo.servlet.ComposeIndex();
		ComposeIndex ci=ConnectInit.getComposeIndex();
		try
		{      
			String pathf = Connect.getCoolMenuspath();
			pathf = pathf + "CoolMenus/" + "IndexReturn.xls";
			WritableWorkbook workbook14 = Workbook.createWorkbook(new File(pathf));
			WritableSheet sheet14 = workbook14.createSheet("Stockpile", 0);
			String label_heading14 = "Index Returns Volatality Report. ";
			
			int var_int14 = arr.length;
			Label label14 = new Label(0, 0, label_heading14 , arial10format);
			sheet14.addCell(label14);
			label14 = new Label(0, 4, "Index Name ", arial10format); 
			sheet14.addCell(label14);
			label14 = new Label(1, 4, "Periodic Returns ", arial10format); 
			sheet14.addCell(label14);
			label14 = new Label(2, 4, "Volatality of return", arial10format); 
			sheet14.addCell(label14);
			
			String fdate14 = "From date : " + fdate;
			String tdate14 = "To date : " + tdate;
			label14 = new Label(0, 2, fdate14, arial10format); 
			sheet14.addCell(label14);
			label14 = new Label(1, 2, tdate14, arial10format); 
			sheet14.addCell(label14);
			Logging.debug("Index Returns Volatality Report. "+vi.size());
			String id14 = null;
			int count14 = 5;
			if(vi.size()!= 0)
			{
			    Iterator it14 = vi.iterator();
				while(it14.hasNext())
				{
					it14.next();
					for(int j14 = 0;j14 < 3; j14++)
					{
						if(it14.hasNext())
						{	
							id14=(String)it14.next();
							label14 = new Label(j14,count14,id14);
							sheet14.addCell(label14);
						}
					}
					count14 = count14 + 1;
				}
			}
			workbook14.write(); 
			workbook14.close();
			

		}catch(Exception e)
		{
				Logging.debug("Error prevented the file from being created."+e);
		}

	}
	
	
	/**
	 * by Manoj(stock_detail,)
	 * Overloaded funtion for accepting Arraylist .
	 * 
	 * @param switch_code
	 * @param vi
	 * @param fdate
	 * @param tdate
	 */
	public void create_file_stock_detail(String var,int switch_code,Vector ai,String fdate, String tdate1, String var1,String tdate,String param1,String param2,String param3 )
	{ 
		Vector vec;
//		org.jfree.chart.demo.servlet.ComposeIndex ci=new org.jfree.chart.demo.servlet.ComposeIndex();
		ComposeIndex ci=ConnectInit.getComposeIndex();
		try
		{  
			String pathf = Connect.getCoolMenuspath();
			
			switch(switch_code)
			{
			case 6:
				
				pathf = pathf + "CoolMenus/" + "StockHighLow.xls";
				WritableWorkbook workbook6 = Workbook.createWorkbook(new File(pathf));
				WritableSheet sheet6 = workbook6.createSheet("Stockpile", 0);
				String label_heading6 = "Stock details report for index : ";
				int var_int6 = Integer.parseInt(var);
           		label_heading6 = label_heading6 +" " + ci.get_index_name(var_int6);
				Label label6 = new Label(0, 0, label_heading6, arial10format); 
				sheet6.addCell(label6);
				
				String label_heading6s = "Stock name : ";
				int var_int6s = Integer.parseInt(var1);
           		label_heading6s = label_heading6s +" " + ci.get_stock_name(var_int6s);
				label6 = new Label(0, 1, label_heading6s ); 
				sheet6.addCell(label6);
				
				String fdate6 = "From date : " + fdate;
				String tdate6 = "To date : " + tdate1;
				label6 = new Label(0, 2, fdate6, arial10format); 
				sheet6.addCell(label6);
				label6 = new Label(1, 2, tdate6, arial10format); 
				sheet6.addCell(label6);
				label6 = new Label(0, 4, "Stock name ", arial10format); 
				sheet6.addCell(label6);
				label6 = new Label(1, 4, "Opening value ", arial10format); 
				sheet6.addCell(label6);
				label6 = new Label(2, 4, "Closing value ", arial10format); 
				sheet6.addCell(label6);
				label6 = new Label(3, 4, "Lowest value", arial10format); 
				sheet6.addCell(label6);
				label6 = new Label(4, 4, "Highest value", arial10format); 
				sheet6.addCell(label6);
				label6 = new Label(5, 4, "Traded volume ", arial10format); 
				sheet6.addCell(label6);
				label6 = new Label(6, 4, "Traded value ", arial10format); 
				sheet6.addCell(label6);
				label6 = new Label(7, 4, "Mkt. Cap ", arial10format); 
				sheet6.addCell(label6);
				Logging.debug("tdate: "+tdate);
				if(tdate.equals("true")){
					label6 = new Label(8, 4, "No. Of Trades", arial10format); 
					sheet6.addCell(label6);
					label6 = new Label(9, 4, "Date", arial10format); 
					sheet6.addCell(label6);
				}
				else{
					label6 = new Label(8, 4, "Date", arial10format); 
					sheet6.addCell(label6);
				}
				String id6 = null;
				int count6 = 5;
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
										label6 = new Label(8,count6,id6);	
										sheet6.addCell(label6);
										break;
									}
									else{
										label6 = new Label(j6,count6,id6);	
										sheet6.addCell(label6);
									}
									
								}
							}
							count6 = count6 + 1;
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
									label6 = new Label(j6,count6,id6);
									sheet6.addCell(label6);
								}
							}
							count6 = count6 + 1;
						}
					}
				}
				
				
				workbook6.write(); 
				workbook6.close();
				break;
				
			case 22:
				pathf = pathf + "CoolMenus/" + "StockDivident.xls";
				WritableWorkbook workbook5 = Workbook.createWorkbook(new File(pathf));
				WritableSheet sheet5 = workbook5.createSheet("Stockpile", 0);
				String label_heading5 = "Stock Divident";
				String label_heading="";
				Label label5 = new Label(0, 0, label_heading5 , arial10format); 
				sheet5.addCell(label5);
				if(var1.equals("1")){
	           		label_heading = "Exchange:" +" " + ci.getExchangeName(var1);
				}
				else{
					int var_int5 = Integer.parseInt(var1);
	           		label_heading = "Index:" +" " + ci.get_index_name(var_int5);
				}
				
				label5 = new Label(0, 2, label_heading , arial10format); 
				sheet5.addCell(label5);
				String fdate5 = "From date : " + fdate;
				String tdate5 = "To date : " + tdate1;
				label5 = new Label(0, 3, fdate5, arial10format); 
				sheet5.addCell(label5);
				label5 = new Label(3, 3, tdate5, arial10format); 
				sheet5.addCell(label5);
				label5 = new Label(0, 5, "Stock name ", arial10format); 
				sheet5.addCell(label5);
				label5 = new Label(1, 5, "Face Value ", arial10format); 
				sheet5.addCell(label5);
				label5 = new Label(2, 5, "Total Issued Share", arial10format); 
				sheet5.addCell(label5);
				label5 = new Label(3, 5, "Market Cap", arial10format); 
				sheet5.addCell(label5);
				label5 = new Label(4, 5, "Divident", arial10format); 
				sheet5.addCell(label5);
				label5 = new Label(5, 5, "Applied Date", arial10format); 
				sheet5.addCell(label5);
				String id5 = null;
				int count5 = 6;
				if(ai.size()!= 0)
				{
				    Iterator it5 = ai.iterator();
					while(it5.hasNext())
					{
						for(int j5 = 0;j5<7;j5++)
						{
							if(it5.hasNext())
							{	
								id5=(String)it5.next();
								if(j5==0){
									continue;
								}
								label5 = new Label(j5-1,count5,id5);
								sheet5.addCell(label5);
							}
						}
						count5 = count5 + 1;
					}
				}
				workbook5.write(); 
				workbook5.close();
				break;
				
			case 23: 
				//null,type,v2,fDate,index,filter,stock,null,null,null
				//String var,int switch_code,Vector ai,String fdate, String tdate1, String var1,String tdate,String param1,String param2,String param3
				pathf = pathf + "CoolMenus/" + "StockDetail.xls";
				WritableWorkbook workbook19 = Workbook.createWorkbook(new File(pathf));
				WritableSheet sheet19 = workbook19.createSheet("Stockpile", 0);
				Label label19 = new Label(0, 0, "Stock Detail From Date ", arial10format); 
				sheet19.addCell(label19);
				if(var1.equals("Index Wise")){	
					label19 = new Label(0, 3, "Index: "+tdate1, arial10format); 
					sheet19.addCell(label19);
				}else{
					label19 = new Label(0, 3, "Stock: "+tdate1, arial10format); 
					sheet19.addCell(label19);
				}
				label19 = new Label(0, 4, "Stock:"+tdate, arial10format); 
				sheet19.addCell(label19);
				label19 = new Label(0, 5, "From Date: "+fdate, arial10format); 
				sheet19.addCell(label19);
	                  			
				label19 = new Label(0, 7, "Symbol ", arial10format); 
				sheet19.addCell(label19);
				label19 = new Label(1, 7, "Price Date ", arial10format); 
				sheet19.addCell(label19);
				label19 = new Label(2, 7, "Price ", arial10format); 
				sheet19.addCell(label19);
				label19 = new Label(3, 7, "Price(USD) ", arial10format); 
				sheet19.addCell(label19);
				label19 = new Label(4, 7, "Tis ", arial10format); 
				sheet19.addCell(label19);
				label19 = new Label(5, 7, "MCV ", arial10format); 
				sheet19.addCell(label19);
				label19 = new Label(6, 7, "MCV(USD) ", arial10format); 
				sheet19.addCell(label19);
				label19 = new Label(7, 7, "Traded Volume ", arial10format); 
				sheet19.addCell(label19);
				label19 = new Label(8, 7, " Traded Value ", arial10format); 
				sheet19.addCell(label19);
				label19 = new Label(9, 7, " Series ", arial10format); 
				sheet19.addCell(label19);
				label19 = new Label(10, 7, "C.A	 ", arial10format); 
				sheet19.addCell(label19);
				
				String id = null;
				int count = 8;
				if(ai.size()!= 0)
				{
					Iterator it = ai.iterator();
					while(it.hasNext())
					{
						stockDetailFromDate im=(stockDetailFromDate)it.next();
								label19 = new Label(0,count,im.getIc());
								sheet19.addCell(label19);
								label19 = new Label(1,count,im.getPriceDate());
								sheet19.addCell(label19);
								label19 = new Label(2,count,im.getPusd());
								sheet19.addCell(label19);
								label19 = new Label(3,count,im.getPrice());
								sheet19.addCell(label19);
								label19 = new Label(4,count,im.getTis());
								sheet19.addCell(label19);
								label19 = new Label(5,count,im.getMusd());
								sheet19.addCell(label19);
								label19 = new Label(6,count,im.getMcv());
								sheet19.addCell(label19);
								label19 = new Label(7,count,im.getTradedVolume());
								sheet19.addCell(label19);
								label19 = new Label(8,count,im.getTradedValue());
								sheet19.addCell(label19);
								label19 = new Label(9,count,im.getSeries());
								sheet19.addCell(label19);
								label19 = new Label(10,count,im.getIsCA());
								sheet19.addCell(label19);
								
						
						count = count + 1;
					}
				}
				workbook19.write(); 
				workbook19.close();
				break;
			}

		}catch(Exception e)
		{
				Logging.debug("Error prevented the file from being created."+e);
		}

	}
	
}
