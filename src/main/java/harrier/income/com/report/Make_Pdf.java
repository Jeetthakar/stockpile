/*
 * Created on Feb 13, 2007
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

import app.*;
import java.awt.Color;
import java.io.*;
import java.util.Hashtable;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException; 
import java.io.OutputStreamWriter;

import com.harrier.initializeation.ConnectInit;
import com.lowagie.text.Image;


import org.apache.log4j.Logger;
import org.jfree.chart.demo.servlet.AdjustDecimal;
import org.jfree.chart.demo.servlet.ComposeIndex;

import sysconfig.model.indexMovement;
import sysconfig.model.stockDetailFromDate;


import com.lowagie.text.*;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfWriter;

/**
 * @author kapil
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Make_Pdf{
	Logger Logging = Logger.getLogger(Make_Pdf.class);
	/**
	 * create_file is used to create the pdf from the report.
	 * It uses the vector passed by the report page to create pdf 
	 * 
	 * 
	 * @param var
	 * @param switch_code
	 * @param ai
	 * @param fdate
	 * @param tdate
	 * @param var1
	 */
	
	int jCount;
	boolean flag=true;
	static int intRep=0;
	String pathf=null;
	String path=null;
	Image jpg1=null;
	int l=0;


	//int Rep++;

	public void create_file(String var,int switch_code,String iename, Vector ai,String fdate, String tdate, String var1)
	{ 
		// new cide for pdf

		StringBuffer sb1= new StringBuffer();
		try
		{  
			 pathf = Connect.getCoolMenuspath();
	       	 l=pathf.length();
			 path=pathf.substring(0,(l-16));
			 path=path+"pages/stockpile_banner.jpg";
			 pathf = Connect.getCoolMenuspath();
		
			 switch(switch_code)
				{
			 
				case 4: 
									
					pathf = pathf + "CoolMenus/" + "IndexList.pdf";
					Logging.debug("path of pdf file"+pathf);
					Rectangle pageSize4 = new Rectangle(0,0,1500,900);
				    Document document4 = new Document(pageSize4,0,0,0,0);
					document4.setMargins(0,0,0,0);

				    PdfWriter.getInstance(document4, new FileOutputStream(pathf));
				    document4.open();
				    
				    try{		
						   jpg1 = Image.getInstance(path);
						   jpg1.setAlignment(1);
			        	   jpg1.setWidthPercentage(300);
			        	   jpg1.setDeflated(true);
			        	   jpg1.setDpi(100,100);
			        	   document4.add(jpg1);
					} catch(Exception e){
					        Logging.debug("Error not able to display image"+e);	
					}

				    Table t24 = new Table(1);
				    t24.setBackgroundColor(Color.YELLOW);
			        t24.setCellsFitPage(true);
				    t24.setTableFitsPage(true);
				    t24.setPadding(0);
				    String head4= new String("Index List");
				    com.lowagie.text.Cell c24 = new com.lowagie.text.Cell(head4);
				    c24.setHorizontalAlignment(1);
				    c24.setHeader(true);
			    	t24.addCell(c24);
			    	
					document4.add(t24);
					
			  	Table t = new Table(26);
			  	//t.setBorder(220, 255, 100);
			  	t.setPadding(10);
			  	t.setSpacing(1);
			  	t.setBorderWidth(1);
			  	t.setBorderColor(Color.GRAY);
			  	t.setBackgroundColor(Color.LIGHT_GRAY);
			  	String s = new String();
			  	com.lowagie.text.Cell c4= new com.lowagie.text.Cell(s);
				
			  	c24.setHeader(true);
				
				
				String id = null;
				
				Logging.debug("size of vector "+ai.size());
				if(ai.size()!= 0)
				{
					Iterator it = ai.iterator();
					while(it.hasNext())
					{
				
						it.next();
						for(int j = 0;j<11;j++)
						{
							if(it.hasNext())
							{	
								id=(String)it.next();
								//double dob=0.0;
								try{
									if(j==0){
										if(flag){
											//String s24[] = {"Index name","Value","Open ", "High ","Low ","Last closing ","(%) Change ","Market cap","Divisor ", "Currency ","Date "};
											//for(int i = 0;i<s24.length;i++){
										    //com.lowagie.text.Cell c24 = new com.lowagie.text.Cell(s24[i]);
											c4 = new com.lowagie.text.Cell("Index name");
											c4.setColspan(3);
											c4.setHorizontalAlignment(1);
											t.addCell(c4);
											c4 = new com.lowagie.text.Cell("Value");
											c4.setColspan(2);
											c4.setHorizontalAlignment(1);
											t.addCell(c4);
											c4 = new com.lowagie.text.Cell("Open ");
											c4.setColspan(2);
											c4.setHorizontalAlignment(1);
											t.addCell(c4);
											c4 = new com.lowagie.text.Cell("High ");
											c4.setColspan(2);
											c4.setHorizontalAlignment(1);
											t.addCell(c4);
											c4 = new com.lowagie.text.Cell("Low ");
											c4.setColspan(2);
											c4.setHorizontalAlignment(1);
											t.addCell(c4);
											c4 = new com.lowagie.text.Cell("Last closing ");
											c4.setColspan(2);
											c4.setHorizontalAlignment(1);
											t.addCell(c4);
											c4 = new com.lowagie.text.Cell("(%) Change ");
											c4.setColspan(2);
											c4.setHorizontalAlignment(1);
											t.addCell(c4);
											c4 = new com.lowagie.text.Cell("Market cap");
											c4.setColspan(3);
											c4.setHorizontalAlignment(1);
											t.addCell(c4);
											c4 = new com.lowagie.text.Cell("Divisor ");
											c4.setColspan(3);
											c4.setHorizontalAlignment(1);
											t.addCell(c4);
											c4 = new com.lowagie.text.Cell("Currency ");
											c4.setColspan(2);
											c4.setHorizontalAlignment(1);
											t.addCell(c4);
											c4 = new com.lowagie.text.Cell("Date");
											c4.setColspan(3);
											c4.setHorizontalAlignment(1);
											t.addCell(c4);
											//}
										flag=false;
										}
										String index_name=id;
										sb1.append(index_name);
										s = index_name;
										c4 = new com.lowagie.text.Cell(s);
										c4.setColspan(3);
										t.addCell(c4);
									   					
									   }
									else if(j==1){
										String value=id;
										sb1.append(value);
										s = value;
										c4 = new com.lowagie.text.Cell(s);
										c4.setColspan(2);
										c4.setHorizontalAlignment(1);
										t.addCell(c4);
									   					
									   }
									else if(j==2){
										String open=id;
										//iwf=ad.indexcompose(iwf);
										//iwf=AdjustDecimal.ArrangeAsNumeric(iwf);
										
										sb1.append(open);
										s = open;
										c4 = new com.lowagie.text.Cell(s);
										c4.setColspan(2);
										c4.setHorizontalAlignment(1);
										t.addCell(c4);
									   					
									   }
									else if(j==3){
										String high=id;
										sb1.append(high);
										s = high;
										c4 = new com.lowagie.text.Cell(s);
										c4.setColspan(2);
										c4.setHorizontalAlignment(1);
										t.addCell(c4);
									   					
									}
									else if(j==4){
										String low=id;
										sb1.append(low);
										s = low;
										c4 = new com.lowagie.text.Cell(s);
										c4.setColspan(2);
										c4.setHorizontalAlignment(1);
										t.addCell(c4);
									   					
									  }
									else if(j==5){
										String last_closing =id;
										sb1.append(last_closing);
										s = last_closing;
										c4 = new com.lowagie.text.Cell(s);
										c4.setColspan(2);
										c4.setHorizontalAlignment(1);
										t.addCell(c4);
									   					
									  }
									else if(j==6){
										String change=id;
										
										sb1.append(change);
										s = change;
										c4 = new com.lowagie.text.Cell(s);
										c4.setColspan(2);
										c4.setHorizontalAlignment(1);
										t.addCell(c4);
									   					
									   }
									else if(j==7){
										String market_cap=id;
										
										sb1.append(market_cap);
										s = market_cap;
										c4 = new com.lowagie.text.Cell(s);
										c4.setColspan(3);
										c4.setHorizontalAlignment(1);
										t.addCell(c4);
									   					
									   }
									else if(j==8){
										String divisor=id;
										
										sb1.append(divisor);
										s = divisor;
										c4 = new com.lowagie.text.Cell(s);
										c4.setColspan(3);
										c4.setHorizontalAlignment(1);
										t.addCell(c4);
									   					
									  }
									else if(j==9){
										String currency=id;
										
										sb1.append(currency);
										s = currency;
										c4 = new com.lowagie.text.Cell(s);
										c4.setColspan(2);
										c4.setHorizontalAlignment(1);
										t.addCell(c4);
									   					
																
									}
									
									
									
								    else if(j==10){
									   	String date=id;
									    
										 sb1.append(date);
										 s = date;
										 c4 = new com.lowagie.text.Cell(s);
										 c4.setColspan(3);
										 c4.setHorizontalAlignment(1);
										 t.addCell(c4);
										   					
								    }
								    
								    
									
								} catch(Exception e){
									
									Logging.error(" Error : "+e.getMessage());
								}
							
								
								
							}
						}
						//count = count + 1;
					}
						
				}// end of if
				
				document4.add(t);
				document4.close();
				 break;
				
				 //Code by Manoj Adekar
				case 26: 
					
					pathf = pathf + "CoolMenus/" + "PortfolioBasket.pdf";
					Logging.debug("path of pdf file"+pathf);
					Rectangle pageSize26 = new Rectangle(0,0,1500,900);
				    Document document26 = new Document(pageSize26,0,0,0,0);
					document26.setMargins(0,0,0,0);

				    PdfWriter.getInstance(document26, new FileOutputStream(pathf));
				    document26.open();
				    
				    try{		
						   jpg1 = Image.getInstance(path);
						   jpg1.setAlignment(1);
			        	   jpg1.setWidthPercentage(300);
			        	   jpg1.setDeflated(true);
			        	   jpg1.setDpi(100,100);
			        	   document26.add(jpg1);
					} catch(Exception e){
					        Logging.debug("Error not able to display image"+e);	
					}

				    Table t26 = new Table(1);
				    t26.setBackgroundColor(Color.YELLOW);
			        t26.setCellsFitPage(true);
				    t26.setTableFitsPage(true);
				    t26.setPadding(0);
				    String head26= new String("Portfolio Basket Calculator");
				    com.lowagie.text.Cell cp = new com.lowagie.text.Cell(head26);
				    cp.setHorizontalAlignment(1);
				    cp.setHeader(true);
			    	t26.addCell(cp);
			    	
					document26.add(t26);
					
			  	Table tp = new Table(10);
			  	//t.setBorder(220, 255, 100);
			  	tp.setPadding(10);
			  	tp.setSpacing(1);
			  	tp.setBorderWidth(1);
			  	tp.setBorderColor(Color.GRAY);
			  	tp.setBackgroundColor(Color.LIGHT_GRAY);
			  	String sp = new String();
			  	com.lowagie.text.Cell c26= new com.lowagie.text.Cell(sp);
				
			  	c26.setHeader(true);
				String idp = null;
				
				Logging.debug("size of vector "+ai.size());
				if(ai.size()!= 0)
				{
					Iterator it = ai.iterator();
					while(it.hasNext())
					{
						//it.next();
						for(int j = 0;j<5;j++)
						{
							if(it.hasNext())
							{	
								idp=(String)it.next();
								//double dob=0.0;
								try{
									if(j==0){
										if(flag){
											c26 = new com.lowagie.text.Cell("Sr No");
											c26.setColspan(2);
											c26.setHorizontalAlignment(1);
											tp.addCell(c26);
											c26 = new com.lowagie.text.Cell("Symbol Name");
											c26.setColspan(2);
											c26.setHorizontalAlignment(1);
											tp.addCell(c26);
											c26 = new com.lowagie.text.Cell("Last Traded Price");
											c26.setColspan(2);
											c26.setHorizontalAlignment(1);
											tp.addCell(c26);
											c26 = new com.lowagie.text.Cell("Mkt. Cap. Value");
											c26.setColspan(2);
											c26.setHorizontalAlignment(1);
											tp.addCell(c26);
											c26 = new com.lowagie.text.Cell("Shares Calculated");
											c26.setColspan(2);
											c26.setHorizontalAlignment(1);
											tp.addCell(c26);
											//}
										flag=false;
										}
										String srNo=idp;
										sb1.append(srNo);
										sp = srNo;
										c26 = new com.lowagie.text.Cell(sp);
										c26.setColspan(2);
										tp.addCell(c26);
									   					
									   }
									else if(j==1){
										String symbolN=idp;
										sb1.append(symbolN);
										sp = symbolN;
										c26 = new com.lowagie.text.Cell(sp);
										c26.setColspan(2);
										c26.setHorizontalAlignment(1);
										tp.addCell(c26);
									   					
									   }
									else if(j==2){
										String price=idp;
										//iwf=ad.indexcompose(iwf);
										//iwf=AdjustDecimal.ArrangeAsNumeric(iwf);
										
										sb1.append(price);
										sp = price;
										c26 = new com.lowagie.text.Cell(sp);
										c26.setColspan(2);
										c26.setHorizontalAlignment(1);
										tp.addCell(c26);
									   					
									   }
									else if(j==3){
										String mktValue=idp;
										sb1.append(mktValue);
										sp = mktValue;
										c26 = new com.lowagie.text.Cell(sp);
										c26.setColspan(2);
										c26.setHorizontalAlignment(1);
										tp.addCell(c26);
									   					
									}
									else if(j==4){
										String shares=idp;
										sb1.append(shares);
										sp = shares;
										c26 = new com.lowagie.text.Cell(sp);
										c26.setColspan(2);
										c26.setHorizontalAlignment(1);
										tp.addCell(c26);
									   					
									  }
									
								} catch(Exception e){
									Logging.error(" Error : "+e.getMessage());
								}
							}
						}
						//count = count + 1;
					}
						
				}// end of if
				
				document26.add(tp);
				document26.close();
				 break;
				
				 //Code by Manoj Adekar
				case 27: 
					
					pathf = pathf + "CoolMenus/" + "IndexCalculatorRpt.pdf";
					Logging.debug("path of pdf file"+pathf);
					Rectangle pageSize27 = new Rectangle(0,0,1500,900);
				    Document document27 = new Document(pageSize27,0,0,0,0);
					document27.setMargins(0,0,0,0);

				    PdfWriter.getInstance(document27, new FileOutputStream(pathf));
				    document27.open();
				    
				    try{		
						   jpg1 = Image.getInstance(path);
						   jpg1.setAlignment(1);
			        	   jpg1.setWidthPercentage(300);
			        	   jpg1.setDeflated(true);
			        	   jpg1.setDpi(100,100);
			        	   document27.add(jpg1);
					} catch(Exception e){
					        Logging.debug("Error not able to display image"+e);	
					}

				    Table t27 = new Table(1);
				    t27.setBackgroundColor(Color.YELLOW);
			        t27.setCellsFitPage(true);
				    t27.setTableFitsPage(true);
				    t27.setPadding(0);
				    String head27= new String("Index Calculator");
				    com.lowagie.text.Cell ci = new com.lowagie.text.Cell(head27);
				    ci.setHorizontalAlignment(1);
				    ci.setHeader(true);
			    	t27.addCell(ci);
			    	
					document27.add(t27);
					
			  	Table ti = new Table(8);//For Number of Columns
			  	//t.setBorder(220, 255, 100);
			  	ti.setPadding(10);
			  	ti.setSpacing(1);
			  	ti.setBorderWidth(1);
			  	ti.setBorderColor(Color.GRAY);
			  	ti.setBackgroundColor(Color.LIGHT_GRAY);
			  	String si = new String();
			  	com.lowagie.text.Cell c27= new com.lowagie.text.Cell(si);
				
			  	c27.setHeader(true);
				String idi = null;
				
				Logging.debug("size of vector "+ai.size());
				if(ai.size()!= 0)
				{
					Iterator it = ai.iterator();
					while(it.hasNext())
					{
						//it.next();
						for(int j = 0;j<4;j++)
						{
							if(it.hasNext())
							{	
								idi=(String)it.next();
								//double dob=0.0;
								try{
									if(j==0){
										if(flag){
											c27 = new com.lowagie.text.Cell("Sr No");
											c27.setColspan(2);
											c27.setHorizontalAlignment(1);
											ti.addCell(c27);
											c27 = new com.lowagie.text.Cell("Symbol Name");
											c27.setColspan(2);
											c27.setHorizontalAlignment(1);
											ti.addCell(c27);
											c27 = new com.lowagie.text.Cell("Last Traded Price");
											c27.setColspan(2);
											c27.setHorizontalAlignment(1);
											ti.addCell(c27);
											c27 = new com.lowagie.text.Cell("My Price");
											c27.setColspan(2);
											c27.setHorizontalAlignment(1);
											ti.addCell(c27);
											
											//}
										flag=false;
										}
										String srNo=idi;
										sb1.append(srNo);
										si = srNo;
										c27 = new com.lowagie.text.Cell(si);
										c27.setColspan(2);
										ti.addCell(c27);
									   					
									   }
									else if(j==1){
										String symbolN=idi;
										sb1.append(symbolN);
										si = symbolN;
										c27 = new com.lowagie.text.Cell(si);
										c27.setColspan(2);
										c27.setHorizontalAlignment(1);
										ti.addCell(c27);
									   					
									   }
									else if(j==2){
										String price=idi;
										//iwf=ad.indexcompose(iwf);
										//iwf=AdjustDecimal.ArrangeAsNumeric(iwf);
										
										sb1.append(price);
										si = price;
										c27 = new com.lowagie.text.Cell(si);
										c27.setColspan(2);
										c27.setHorizontalAlignment(1);
										ti.addCell(c27);
									   					
									   }
									else if(j==3){
										String myPrice=idi;
										sb1.append(myPrice);
										si = myPrice;
										c27 = new com.lowagie.text.Cell(si);
										c27.setColspan(2);
										c27.setHorizontalAlignment(1);
										ti.addCell(c27);
									   					
									}									
								} catch(Exception e){
									Logging.error(" Error : "+e.getMessage());
								}
							}
						}
						//count = count + 1;
					}
						
				}// end of if
				
				document27.add(ti);
				document27.close();
				 break;

				 
				case 12: 
					
					
					pathf = pathf + "CoolMenus/" + "IndexPerformance.pdf";
					Logging.debug("path of pdf file"+pathf);
					//Rectangle pageSize12 = new Rectangle(0,0,2382,3369);
					Rectangle pageSize12= new Rectangle(0,0,1300,900);
				    //Document document12 = new Document(pageSize12);	
					Document document12 = new Document(pageSize12,0,0,0,0);
					document12.setMargins(0,0,0,0);
				   	
				    PdfWriter.getInstance(document12, new FileOutputStream(pathf));
				    document12.open();
				    try{		
						   jpg1 = Image.getInstance(path);
						   jpg1.setAlignment(1);
			        	   jpg1.setWidthPercentage(300);
			        	   jpg1.setDeflated(true);
			        	   jpg1.setDpi(100,100);
			        	   document12.add(jpg1);
					} catch(Exception e){
					        Logging.debug("Error not able to display image"+e);	
					}
				    Table t212 = new Table(1);
				    t212.setCellsFitPage(true);
				    t212.setTableFitsPage(true);
				    t212.setPadding(0);
				    String head12= new String("Index Performance");
				    com.lowagie.text.Cell c212 = new com.lowagie.text.Cell(head12);
				    c212.setHorizontalAlignment(1);
				    c212.setHeader(true);
				    t212.addCell(c212);
			    		    
					document12.add(t212);
					
			  	Table t12 = new Table(7);
			  	
			  	t12.setPadding(10);
			  	t12.setSpacing(1);
			  	t12.setBorderWidth(1);
			  	t12.setBorderColor(Color.GRAY);
			  	t12.setBackgroundColor(Color.LIGHT_GRAY);
			  	String s12 = new String();
			  	com.lowagie.text.Cell c112= new com.lowagie.text.Cell(s12);
				
				c112.setHeader(true);
				String id12 = null;
				
				Logging.debug("size of vector "+ai.size());
				if(ai.size()!= 0)
				{
					Iterator it = ai.iterator();
					while(it.hasNext())
					{
				
						it.next();
						for(int j = 0;j<5;j++)
						{
							if(it.hasNext())
							{	
								id12=(String)it.next();
								//double dob=0.0;
								try{
									if(j==0){
										//code to copy
										if(flag){
											
											c112 = new com.lowagie.text.Cell("Index name");
											c112.setColspan(2);
											c112.setHorizontalAlignment(1);
											t12.addCell(c112);	
											c112 = new com.lowagie.text.Cell("1 Month(In %)");
											c112.setColspan(1);
											c112.setHorizontalAlignment(1);
											t12.addCell(c112);
											c112 = new com.lowagie.text.Cell("3 Month(In %)");
											c112.setColspan(1);
											c112.setHorizontalAlignment(1);
											t12.addCell(c112);
											c112 = new com.lowagie.text.Cell("6 Month(In %)");
											c112.setColspan(1);
											c112.setHorizontalAlignment(1);
											t12.addCell(c112);
											c112 = new com.lowagie.text.Cell("1 Year(In %) ");
											c112.setColspan(2);
											c112.setHorizontalAlignment(1);
											t12.addCell(c112);
										flag=false;
										}
										
										String index_name=id12;
										sb1.append(index_name);
										s12 = index_name;
										c112 = new com.lowagie.text.Cell(s12);
										c112.setColspan(2);
										c112.setHorizontalAlignment(1);
										t12.addCell(c112);
									   					
									   }
									else if(j==1){
										String one_month=id12;
										sb1.append(one_month);
										s12 = one_month;
										c112 = new com.lowagie.text.Cell(s12);
										c112.setColspan(1);
										c112.setHorizontalAlignment(1);
										t12.addCell(c112);
									   					
									   }
									
											
									
									else if(j==2){
										String three_month=id12;
										sb1.append(three_month);
										s12 = three_month;
										c112 = new com.lowagie.text.Cell(s12);
										c112.setColspan(1);
										c112.setHorizontalAlignment(1);
										t12.addCell(c112);
									   					
									  }
									else if(j==3){
										String six_month=id12;
										sb1.append(six_month);
										s12 = six_month;
										c112 = new com.lowagie.text.Cell(s12);
										c112.setColspan(1);
										c112.setHorizontalAlignment(1);
										t12.addCell(c112);
									   					
									}
									else if(j==4){
										String one_yr=id12;
										sb1.append(one_yr);
										s12 = one_yr;
										c112 = new com.lowagie.text.Cell(s12);
										c112.setColspan(2);
										c112.setHorizontalAlignment(1);
										t12.addCell(c112);
									   					
									}
									
																
								} catch(Exception e){
									
									Logging.error(" Error : "+e.getMessage());
								}
							
								
								
							}
						}
						//count = count + 1;
					}
						
				}// end of if
				
				document12.add(t12);
				document12.close();
				 break;
				 
				case 8: 
					
					
					pathf = pathf + "CoolMenus/" + "LatestIndexDivisor.pdf";
					Logging.debug("path of pdf file"+pathf);
					Rectangle pageSize8= new Rectangle(0,0,1300,900);
				  	Document document8 = new Document(pageSize8,0,0,0,0);
					document8.setMargins(0,0,0,0);
				   	
				    PdfWriter.getInstance(document8, new FileOutputStream(pathf));
				    document8.open();
				    try{		
						   jpg1 = Image.getInstance(path);
						   jpg1.setAlignment(1);
			        	   jpg1.setWidthPercentage(300);
			        	   jpg1.setDeflated(true);
			        	   jpg1.setDpi(100,100);
			        	   document8.add(jpg1);
					} catch(Exception e){
					        Logging.debug("Error not able to display image"+e);	
					}
				    Table t28 = new Table(1);
				    t28.setBackgroundColor(Color.YELLOW);
			        t28.setCellsFitPage(true);
				    t28.setTableFitsPage(true);
				    t28.setPadding(0);
				    String head8= new String("Latest IndexDivisor");
				    com.lowagie.text.Cell c28 = new com.lowagie.text.Cell(head8);
				    c28.setHorizontalAlignment(1);
				    c28.setHeader(true);
				    t28.addCell(c28);
			    	
					document8.add(t28);
					
			  	Table t8 = new Table(2,1);
			  	t8.setPadding(10);
			  	t8.setSpacing(1);
			  	t8.setBorderWidth(1);
			  	t8.setBorderColor(Color.GRAY);
			  	t8.setBackgroundColor(Color.LIGHT_GRAY);
			  	String s8 = new String();
			  	com.lowagie.text.Cell c18= new com.lowagie.text.Cell(s8);
				
			  	c18.setHeader(true);
							
				String id8 = null;
				
				Logging.debug("size of vector "+ai.size());
				if(ai.size()!= 0)
				{
					Iterator it = ai.iterator();
					while(it.hasNext())
					{
				
						it.next();
						for(int j = 0;j<2;j++)
						{
							if(it.hasNext())
							{	
								id8=(String)it.next();
								//double dob=0.0;
								try{
									if(j==0){
										if(flag){
											c18 = new com.lowagie.text.Cell("Index name");
											c18.setHorizontalAlignment(1);
											t8.addCell(c18);
											c18 = new com.lowagie.text.Cell("Divisor");
											c18.setHorizontalAlignment(1);
											t8.addCell(c18);
										flag=false;
										}
										String index_name=id8;
										
										sb1.append(index_name);
										s8 = index_name;
										c18 = new com.lowagie.text.Cell(s8);
										//c18.setHorizontalAlignment(1);
										t8.addCell(c18);
									   					
									   }
									else if(j==1){
										String divisor=id8;
										sb1.append(divisor);
										s8 = divisor;
										c18 = new com.lowagie.text.Cell(s8);
										c18.setHorizontalAlignment(1);
										t8.addCell(c18);
									   					
									   }
								} catch(Exception e){
									
									Logging.error(" Error : "+e.getMessage());
								}
							
								
								
							}
						}
						//count = count + 1;
					}
						
				}// end of if
				
				document8.add(t8);
				document8.close();
				 break;
				 
	case 24: 
		
		
		pathf = pathf + "CoolMenus/" + "IndexMovement.pdf";
		Logging.debug("path of pdf file"+pathf);
		Rectangle pageSize24= new Rectangle(0,0,1300,900);
		Document document24 = new Document(pageSize24,0,0,0,0);
		document24.setMargins(0,0,0,0);
	   	
	    PdfWriter.getInstance(document24, new FileOutputStream(pathf));
	    document24.open();
	    try{		
			   jpg1 = Image.getInstance(path);
			   jpg1.setAlignment(1);
     	       jpg1.setWidthPercentage(300);
     	       jpg1.setDeflated(true);
     	       jpg1.setDpi(100,100);
     	        document24.add(jpg1);
		} catch(Exception e){
		        Logging.debug("Error not able to display image"+e);	
		}
	    Table t224 = new Table(1);
	    t224.setBackgroundColor(Color.YELLOW);
        t224.setCellsFitPage(true);
	    t224.setTableFitsPage(true);
	    t224.setPadding(0);
	    String head24= new String("Index Movement");
	    com.lowagie.text.Cell c224 = new com.lowagie.text.Cell(head24);
	    c224.setHorizontalAlignment(1);
	    c224.setHeader(true);
	    t224.addCell(c224);
    	
		document24.add(t224);
		
  	Table t024 = new Table(4,1);
  	t024.setBackgroundColor(Color.LIGHT_GRAY);
  	t024.setPadding(10);
  	t024.setSpacing(1);
  	t024.setBorderWidth(1);
  	t024.setBorderColor(Color.GRAY);
  	String s024 = new String();
  	com.lowagie.text.Cell c124= new com.lowagie.text.Cell(s024);
	
  	c124.setHeader(true);
				
	String id24 = null;
	
	Logging.debug("size of vector "+ai.size());
	if(ai.size()!= 0)
	{
		Iterator it = ai.iterator();
		while(it.hasNext())
		{
	
			//it.next();
			for(int j = 0;j<4;j++)
			{
				if(it.hasNext())
				{	
					id24=(String)it.next();
					//double dob=0.0;
					try{
						if(j==0){
							if(flag){
								String s224[] = {"Trading Date","Close","MKt.Cap(in millions)","Divisor"};
								for(int i = 0;i<s224.length;i++){
							    	//com.lowagie.text.Cell c212 = new com.lowagie.text.Cell(s212[i]);
									c124 = new com.lowagie.text.Cell(s224[i]);
									c124.setHorizontalAlignment(1);
									t024.addCell(c124);
								}
								
							flag=false;
							}
							String trading_date = id24;
							sb1.append(trading_date);
							s024 = trading_date;
							c124 = new com.lowagie.text.Cell(s024);
							t024.addCell(c124);
						   					
						   }
						else if(j==1){
							String close=id24;
							sb1.append(close);
							s024 = close;
							c124 = new com.lowagie.text.Cell(s024);
							c124.setHorizontalAlignment(1);
							t024.addCell(c124);
						   					
						   }
						else if(j==2){
							String mkt_cap=id24;
							sb1.append(mkt_cap);
							s024 = mkt_cap;
							c124 = new com.lowagie.text.Cell(s024);
							c124.setHorizontalAlignment(1);
							t024.addCell(c124);
						   					
						   }
						else if(j==3){
							String divisor=id24;
							sb1.append(divisor);
							s024 = divisor;
							c124 = new com.lowagie.text.Cell(s024);
							c124.setHorizontalAlignment(1);
							t024.addCell(c124);
						   					
						   }
					} catch(Exception e){
						
						Logging.error(" Error : "+e.getMessage());
					}
				
					
					
				}
			}
			//count = count + 1;
		}
			
	}// end of if
	
	document24.add(t024);
	document24.close();
	 break;
	 
	    case 14: 
		
		
		pathf = pathf + "CoolMenus/" + "IndexReturn.pdf";
		Logging.debug("path of pdf file"+pathf);
		//Rectangle pageSize14 = new Rectangle(0,0,2382,3369);
	    //Document document14 = new Document(pageSize14);
		Rectangle pageSize14= new Rectangle(0,0,1300,900);
		Document document14 = new Document(pageSize14,0,0,0,0);
		document14.setMargins(0,0,0,0);
	   	
	    PdfWriter.getInstance(document14, new FileOutputStream(pathf));
	    document14.open();
	    try{		
			   jpg1 = Image.getInstance(path);
			   jpg1.setAlignment(1);
  	           jpg1.setWidthPercentage(300);
  	           jpg1.setDeflated(true);
  	           jpg1.setDpi(100,100);
  	           document14.add(jpg1);
		} catch(Exception e){
		        Logging.debug("Error not able to display image"+e);	
		}
	    Table t214 = new Table(1);
	    t214.setBackgroundColor(Color.YELLOW);
        t214.setCellsFitPage(true);
	    t214.setTableFitsPage(true);
	    t214.setPadding(0);
	    String head14= new String("Index Return");
	    com.lowagie.text.Cell c214 = new com.lowagie.text.Cell(head14);
	    c214.setHorizontalAlignment(1);
	    c214.setHeader(true);
	    t214.addCell(c214);
		document14.add(t214);
		
  	Table t14 = new Table(3,1);
  	//t.setBorder(220, 255, 140);
  	t14.setBackgroundColor(Color.LIGHT_GRAY);
  	t14.setPadding(10);
  	t14.setSpacing(1);
  	t14.setBorderWidth(1);
  	t14.setBorderColor(Color.GRAY);
  	String s14 = new String();
  	com.lowagie.text.Cell c114= new com.lowagie.text.Cell(s14);
	
  	c114.setHeader(true);
	String id14 = null;
	
	Logging.debug("size of vector "+ai.size());
	if(ai.size()!= 0)
	{
		Iterator it = ai.iterator();
		while(it.hasNext())
		{
	
			//it.next();
			for(int j = 0;j<3;j++)
			{
				if(it.hasNext())
				{	
					id14=(String)it.next();
					//double dob=0.0;
					try{
						if(j==0){
							if(flag){
								String s214[] = {"Index Name","Periodic Returns","Volatility Of Returns"};
								for(int i = 0;i<s214.length;i++){
							    	//com.lowagie.text.Cell c212 = new com.lowagie.text.Cell(s212[i]);
									c114 = new com.lowagie.text.Cell(s214[i]);
									c114.setHorizontalAlignment(1);
									t14.addCell(c114);
								}
								
							flag=false;
							}
							
							String index_name=id14;
							
							sb1.append(index_name);
							s14 = index_name;
							c114 = new com.lowagie.text.Cell(s14);
							t14.addCell(c114);
						   					
						   }
						else if(j==1){
							String periodic_returns=id14;
							sb1.append(periodic_returns);
							s14 = periodic_returns;
							c114 = new com.lowagie.text.Cell(s14);
							c114.setHorizontalAlignment(1);
							t14.addCell(c114);
						   					
						   }
						else if(j==2){
							String volatility_of_returns=id14;
							sb1.append(volatility_of_returns);
							s14 = volatility_of_returns;
							c114 = new com.lowagie.text.Cell(s14);
							c114.setHorizontalAlignment(1);
							t14.addCell(c114);
						   					
						   }
						
					} catch(Exception e){
						
						Logging.error(" Error : "+e.getMessage());
					}
				
					
					
				}
			}
			//count = count + 1;
		}
			
	}// end of if
	
	document14.add(t14);
	document14.close();
	 break;
	 
	 //for stock list
	 //for Stock List 
	    case 21: 
			
			
			pathf = pathf + "CoolMenus/" + "StockList.pdf";
			Logging.debug("path of pdf file"+pathf);
			Rectangle pageSize21= new Rectangle(0,0,1300,900);
			Document document21 = new Document(pageSize21,0,0,0,0);
			document21.setMargins(0,0,0,0);
		    PdfWriter.getInstance(document21, new FileOutputStream(pathf));
		    document21.open();
		    try{		
				   jpg1 = Image.getInstance(path);
				   jpg1.setAlignment(1);
	  	           jpg1.setWidthPercentage(300);
	  	           jpg1.setDeflated(true);
	  	           jpg1.setDpi(100,100);
	  	         document21.add(jpg1);
			} catch(Exception e){
			        Logging.debug("Error not able to display image"+e);	
			}
		    Table t221 = new Table(1);
		    t221.setBackgroundColor(Color.YELLOW);
	        t221.setCellsFitPage(true);
		    t221.setTableFitsPage(true);
		    t221.setPadding(0);
		    String head21= new String("Stock List");
		    com.lowagie.text.Cell c221 = new com.lowagie.text.Cell(head21);
		    c221.setHorizontalAlignment(1);
		    c221.setHeader(true);
		    t221.addCell(c221);
			document21.add(t221);
			
	  	Table t21 = new Table(6,1);
	  	t21.setBackgroundColor(Color.LIGHT_GRAY);
	  	t21.setPadding(10);
	  	t21.setSpacing(1);
	  	t21.setBorderWidth(1);
	  	t21.setBorderColor(Color.GRAY);
	  	String s21 = new String();
	  	com.lowagie.text.Cell c121= new com.lowagie.text.Cell(s21);
		
	  	c121.setHeader(true);
					
		String id21 = null;
		
		Logging.debug("size of vector "+ai.size());
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
						id21=(String)it.next();
						//double dob=0.0;
						try{
							if(j==0){
								if(flag){
									String s221[] = {"Stock name","Outstanding Shares","Price(LTP)","Market Cap", "Face Value","Date"};
									for(int i = 0;i<s221.length;i++){
								    	//com.lowagie.text.Cell c212 = new com.lowagie.text.Cell(s212[i]);
										c121 = new com.lowagie.text.Cell(s221[i]);
										c121.setHorizontalAlignment(1);
										t21.addCell(c121);
									}
									
								flag=false;
								}
								String stock_name=id21;
								sb1.append(stock_name);
								s21 = stock_name;
								c121 = new com.lowagie.text.Cell(s21);
								t21.addCell(c121);
							   					
							   }
							else if(j==1){
								String outsta_share=id21;
								sb1.append(outsta_share);
								s21 = outsta_share;
								c121 = new com.lowagie.text.Cell(s21);
								c121.setHorizontalAlignment(1);
								t21.addCell(c121);
							   					
							   }
							else if(j==2){
								String price_ltp=id21;
								sb1.append(price_ltp);
								s21 = price_ltp;
								c121 = new com.lowagie.text.Cell(s21);
								c121.setHorizontalAlignment(1);
								t21.addCell(c121);
							   					
							   }
							else if(j==3){
								String mkt_cap=id21;
								sb1.append(mkt_cap);
								s21 = mkt_cap;
								c121 = new com.lowagie.text.Cell(s21);
								c121.setHorizontalAlignment(1);
								t21.addCell(c121);
							   					
							   }
							else if(j==4){
								String face_value=id21;
								sb1.append(face_value);
								s21 = face_value;
								c121 = new com.lowagie.text.Cell(s21);
								c121.setHorizontalAlignment(1);
								t21.addCell(c121);
							   					
							   }
							else if(j==5){
								String date=id21;
								sb1.append(date);
								s21 = date;
								c121 = new com.lowagie.text.Cell(s21);
								c121.setHorizontalAlignment(1);
								t21.addCell(c121);
							   					
							   }
						} catch(Exception e){
							
							Logging.error(" Error : "+e.getMessage());
						}
					
						
						
					}
				}
				//count = count + 1;
			}
				
		}// end of if
		
		document21.add(t21);
		document21.close();
		 break;
		case 17: 
			
			
			pathf = pathf + "CoolMenus/" + "InactiveStock.pdf";
			Logging.debug("path of pdf file"+pathf);
			Rectangle pageSize17= new Rectangle(0,0,1300,900);
			Document document17 = new Document(pageSize17,0,0,0,0);
			document17.setMargins(0,0,0,0);
		   	
		    PdfWriter.getInstance(document17, new FileOutputStream(pathf));
		    document17.open();
		    try{		
				   jpg1 = Image.getInstance(path);
				   jpg1.setAlignment(1);
	  	           jpg1.setWidthPercentage(300);
	  	           jpg1.setDeflated(true);
	  	           jpg1.setDpi(100,100);
	  	           document17.add(jpg1);
			} catch(Exception e){
			        Logging.debug("Error not able to display image"+e);	
			}
		    Table t217 = new Table(1);
		    t217.setBackgroundColor(Color.YELLOW);
	        t217.setCellsFitPage(true);
		    t217.setTableFitsPage(true);
		    t217.setPadding(0);
		    String head17= new String("Inactive Stock");
		    com.lowagie.text.Cell c217 = new com.lowagie.text.Cell(head17);
		    c217.setHorizontalAlignment(1);
		    c217.setHeader(true);
		    t217.addCell(c217);
			document17.add(t217);
			
	  	Table t17 = new Table(4,1);
	  	//t.setBorder(220, 255, 100);
	  	t17.setPadding(10);
	  	t17.setSpacing(1);
	  	t17.setBorderWidth(1);
	  	t17.setBorderColor(Color.GRAY);
	  	t17.setBackgroundColor(Color.LIGHT_GRAY);
	  	String s17 = new String();
	  	com.lowagie.text.Cell c117= new com.lowagie.text.Cell(s17);
		
	  	c117.setHeader(true);
		String id17 = null;
		
		Logging.debug("size of vector "+ai.size());
		if(ai.size()!= 0)
		{
			Iterator it = ai.iterator();
			while(it.hasNext())
			{
		
				it.next();
				for(int j = 0;j<4;j++)
				{
					if(it.hasNext())
					{	
						id17=(String)it.next();
						//double dob=0.0;
						try{
							if(j==0){
								if(flag){
									String s217[] = {"Stock Name","Outstanding Shares","Face Value","Date"};
									for(int i = 0;i<s217.length;i++){
								    	//com.lowagie.text.Cell c212 = new com.lowagie.text.Cell(s212[i]);
										c117 = new com.lowagie.text.Cell(s217[i]);
										c117.setHorizontalAlignment(1);
										t17.addCell(c117);
									}
									
								flag=false;
								}
								String stock_name=id17;
								
								sb1.append(stock_name);
								s17 = stock_name;
								c117 = new com.lowagie.text.Cell(s17);
								t17.addCell(c117);
							   					
							   }
							else if(j==1){
								String outst_share=id17;
								sb1.append(outst_share);
								s17 = outst_share;
								c117 = new com.lowagie.text.Cell(s17);
								c117.setHorizontalAlignment(1);
								t17.addCell(c117);
							   					
							   }
							
							
							
							
							else if(j==2){
								String face_value=id17;
								sb1.append(face_value);
								s17 = face_value;
								c117 = new com.lowagie.text.Cell(s17);
								c117.setHorizontalAlignment(1);
								t17.addCell(c117);
							   					
							  }
							else if(j==3){
								String date=id17;
								sb1.append(date);
								s17 = date;
								c117 = new com.lowagie.text.Cell(s17);
								c117.setHorizontalAlignment(1);
								t17.addCell(c117);
							   					
							}
							
						} catch(Exception e){
							
							Logging.error(" Error : "+e.getMessage());
						}
					
						
						
					}
				}
				//count = count + 1;
			}
				
		}// end of if
		
		document17.add(t17);
		document17.close();
		 break;
			 
		case 1: 
				
			pathf = pathf + "CoolMenus/" + "IndexCompositionReport.pdf";
			//Logging.debug("path of pdf file"+pathf);
			Rectangle pageSize = new Rectangle(0,0,1600,900);
		    Document document = new Document(pageSize);	
		    //document.setMargins(0,0,0,0);
		    PdfWriter.getInstance(document, new FileOutputStream(pathf));
		    //PdfWriter.getInstance(document, new FileOutputStream("C:\\task1\\filename.pdf"));
		    document.open();
		    Table t2= new Table(1);
		    
		    try{		
				   jpg1 = Image.getInstance(path);
				   jpg1.setAlignment(1);
	  	           jpg1.setWidthPercentage(300);
	  	           jpg1.setDeflated(true);
	  	           jpg1.setDpi(100,100);
	  	           document.add(jpg1);
			} catch(Exception e){
			        Logging.debug("Error not able to display image"+e);	
			}
		   		
			
		  t2.setCellsFitPage(true);
		  t2.setBackgroundColor(Color.yellow);
		  t2.setTableFitsPage(true);
		  t2.setPadding(0);    
		  String head= new String("Index Composition Report");
		  com.lowagie.text.Cell c2 = new com.lowagie.text.Cell(head);
		  c2.setHorizontalAlignment(1);
		  c2.setHeader(true);
		  t2.addCell(c2);
		  document.add(t2);
		    
	  	Table tcom = new Table(17);
	  	tcom.setPadding(10);
	  	tcom.setSpacing(1);
	  	
	  	tcom.setBackgroundColor(Color.LIGHT_GRAY);
	  	tcom.setBorderWidth(1);
	  	tcom.setBorderColor(Color.LIGHT_GRAY);
	  	String scom = new String();
	  	
	  	com.lowagie.text.Cell c1=new com.lowagie.text.Cell(scom);
		
		String idcom = null;
		
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
						//double dob=0.0;
						try{
							if(j==0){
								if(flag){
									c1 = new com.lowagie.text.Cell("STOCK NAME");
									//c1.setHorizontalAlignment(1);
									c1.setColspan(2);
									tcom.addCell(c1);
									c1 = new com.lowagie.text.Cell("TOTAL SHARES");
									c1.setHorizontalAlignment(1);
									c1.setColspan(2);
									tcom.addCell(c1);
									c1 = new com.lowagie.text.Cell("IWF");
									c1.setHorizontalAlignment(1);
									tcom.addCell(c1);
									c1 = new com.lowagie.text.Cell("Mkt. Lot");
									c1.setHorizontalAlignment(1);
									tcom.addCell(c1);
									c1 = new com.lowagie.text.Cell("Price (LTP)");
									c1.setHorizontalAlignment(1);
									tcom.addCell(c1);
									c1 = new com.lowagie.text.Cell("Price (Last)");
									c1.setHorizontalAlignment(1);
									tcom.addCell(c1);
									c1 = new com.lowagie.text.Cell("Curr. Exch. Rate");
									c1.setHorizontalAlignment(1);
									tcom.addCell(c1);
									c1 = new com.lowagie.text.Cell("Curr ");
									c1.setHorizontalAlignment(1);
									tcom.addCell(c1);
									c1 = new com.lowagie.text.Cell("Market cap");
									c1.setHorizontalAlignment(1);
									c1.setColspan(2);
									tcom.addCell(c1);
									
									c1 = new com.lowagie.text.Cell("Adj. Market cap");
									c1.setHorizontalAlignment(1);
									c1.setColspan(2);
									tcom.addCell(c1);
									c1 = new com.lowagie.text.Cell("Weightage");
									c1.setHorizontalAlignment(1);
									tcom.addCell(c1);
									c1 = new com.lowagie.text.Cell("Date");
									c1.setHorizontalAlignment(1);
									c1.setColspan(2);
									tcom.addCell(c1);
									flag=false;
								}
								String stockname=id;
								
								sb1.append(stockname);
								s = stockname;
								c1 = new com.lowagie.text.Cell(s);
								c1.setColspan(2);
								tcom.addCell(c1);
							   					
							   }
							else if(j==1){
								String tis=id;
								sb1.append(tis);
								s = tis;
								c1 = new com.lowagie.text.Cell(s);
								c1.setHorizontalAlignment(2);
								c1.setColspan(2);
								tcom.addCell(c1);
							   					
							   }
							else if(j==2){
								String iwf=id;
								sb1.append(iwf);
								s = iwf;
								c1 = new com.lowagie.text.Cell(s);
								c1.setHorizontalAlignment(2);
								
								tcom.addCell(c1);
							   					
							   }
							else if(j==3){
								String market=id;
								sb1.append(market);
								s = market;
								c1 = new com.lowagie.text.Cell(s);
								c1.setHorizontalAlignment(2);
								tcom.addCell(c1);
							   					
							}
							else if(j==4){
								String ltp=id;
								sb1.append(ltp);
								s = ltp;
								c1 = new com.lowagie.text.Cell(s);
								c1.setHorizontalAlignment(2);
								tcom.addCell(c1);
							   					
							  }
							else if(j==5){
								String last =id;
								//last=ad.indexcompose(last);
							//	last=AdjustDecimal.ArrangeAsNumeric(last);
								
								sb1.append(last);
								s = last;
								c1 = new com.lowagie.text.Cell(s);
								c1.setHorizontalAlignment(2);
								tcom.addCell(c1);
							   					
							  }
							else if(j==6){
								String currency=id;
								
								sb1.append(currency);
								s = currency;
								c1 = new com.lowagie.text.Cell(s);
								c1.setHorizontalAlignment(2);
								tcom.addCell(c1);
							   					
							   }
							else if(j==7){
								String curr_exch_convIratecomp=id;
								
								sb1.append(curr_exch_convIratecomp);
								s = curr_exch_convIratecomp;
								c1 = new com.lowagie.text.Cell(s);
								c1.setHorizontalAlignment(2);
								tcom.addCell(c1);
							   					
							   }
							else if(j==8){
								String mcv=id;
								
								sb1.append(mcv);
								s = mcv;
								c1 = new com.lowagie.text.Cell(s);
								c1.setHorizontalAlignment(2);
								c1.setColspan(2);
								tcom.addCell(c1);
							   					
							  }
							else if(j==9){
								String adjusted=id;
								
								sb1.append(adjusted);
								s = adjusted;
								c1 = new com.lowagie.text.Cell(s);
								c1.setHorizontalAlignment(2);
								c1.setColspan(2);
								tcom.addCell(c1);
							   					
														
							}
							
						    else if(j==10){
							   	String strweightage1=id;
							    
								 sb1.append(strweightage1);
								 s = strweightage1;
								 c1 = new com.lowagie.text.Cell(s);
								 c1.setHorizontalAlignment(2);
								 tcom.addCell(c1);
								   					
						    }
						    
						    else if(j==11){
						    	String  date=id;
						    	s = date;
						    	sb1.append(date);
						    	c1 = new com.lowagie.text.Cell(s);
						    	c1.setColspan(2);
								tcom.addCell(c1);
								 
							}
							
						} catch(Exception e){
							
							Logging.error(" Error : "+e.getMessage());
						}
					
						
						
					}
				}
				//count = count + 1;
			}
				
		}// end of if

		
		document.add(tcom);
		document.close();
		 break;
		
		case 19: 
			
			
			pathf = pathf + "CoolMenus/" + "IndexDivisor.pdf";
			//Logging.debug("path of pdf file"+pathf);
			Rectangle pageSize19 = new Rectangle(0,0,1300,900);
		    Document document19 = new Document(pageSize19);	
		   	PdfWriter.getInstance(document19, new FileOutputStream(pathf));
		    document19.open();
		    try{		
				   jpg1 = Image.getInstance(path);
				   jpg1.setAlignment(1);
	  	           jpg1.setWidthPercentage(300);
	  	           jpg1.setDeflated(true);
	  	           jpg1.setDpi(100,100);
	  	           document19.add(jpg1);
			} catch(Exception e){
			        Logging.debug("Error not able to display image"+e);	
			}
		    Table t219= new Table(1);
		      
		   
		      t219.setCellsFitPage(true);
		      t219.setBackgroundColor(Color.yellow);
		      t219.setTableFitsPage(true);
		      t219.setPadding(0);
		      String head19= new String("Index Divisor Report");
		      com.lowagie.text.Cell c219 = new com.lowagie.text.Cell(head19);
		      c219.setHorizontalAlignment(1);
		      c219.setHeader(true);
		      t219.addCell(c219);
		      document19.add(t219);
		 
		  	Table t19 = new Table(4);
		  	t19.setPadding(10);
		  	t19.setSpacing(1);
		  	
		  	//t19.setBackgroundColor(Color.LIGHT_GRAY);
		  	t19.setBorderWidth(1);
		  	//t19.setBorderColor(Color.WHITE);
		  	t19.setBorderColor(Color.LIGHT_GRAY);
		  	t19.setBackgroundColor(Color.LIGHT_GRAY);
		  	String s19 = new String();
		  	
	  	com.lowagie.text.Cell c119= new com.lowagie.text.Cell(s19);
		c119.setHeader(true);
		String id19 = null;
		
		//Logging.debug("size of vector "+ai.size());
		if(ai.size()!= 0)
		{
			Iterator it = ai.iterator();
			while(it.hasNext())
			{
		
				//it.next();
				for(int j = 0;j<4;j++)
				{
					if(it.hasNext())
					{	
						id19=(String)it.next();
						//double dob=0.0;
						try{
							if(j==0){
								
								
								
								
									if(flag){
										c119 = new com.lowagie.text.Cell("TRADING DATE");
										c119.setHorizontalAlignment(1);
										t19.addCell(c119);
										c119 = new com.lowagie.text.Cell("CLOSE VALUE");
										c119.setHorizontalAlignment(1);
										t19.addCell(c119);
										c119 = new com.lowagie.text.Cell("MARKET CAP");
										c119.setHorizontalAlignment(1);
										t19.addCell(c119);
										c119 = new com.lowagie.text.Cell("DIVISOR");
										c119.setHorizontalAlignment(1);
										t19.addCell(c119);
										
										flag=false;
									}
								
								
								String trading_date=id19;
								sb1.append(trading_date);
								s19 = trading_date;
								c119 = new com.lowagie.text.Cell(s19);
								t19.addCell(c119);
							   					
							   }
							else if(j==1){
								String close=id19;
								sb1.append(close);
								s19 = close;
								c119 = new com.lowagie.text.Cell(s19);
								
								c119.setHorizontalAlignment(1);
								t19.addCell(c119);
							   					
							   }
							
							else if(j==2){
								String market=id19;
								sb1.append(market);
								s19 = market;
								c119 = new com.lowagie.text.Cell(s19);
								
								c119.setHorizontalAlignment(1);
								t19.addCell(c119);
							   					
							}
							
							
							else if(j==3){
								String divisor=id19;
								sb1.append(divisor);
								s19 = divisor;
								c119 = new com.lowagie.text.Cell(s19);
								
								c119.setHorizontalAlignment(1);
								t19.addCell(c119);
							   					
							  }
							
							
							
						} catch(Exception e){
							
							Logging.error(" Error : "+e.getMessage());
						}
					
						
						
					}
				}
				//count = count + 1;
			}
				
		}// end of if

		
		document19.add(t19);
		document19.close();
		 break;
		 // kapil code start
		 //for capital change
		case 16: 
			
			
			pathf = pathf + "CoolMenus/" + "CapitalChangeToUniverse.pdf";
			//Logging.debug("path of pdf file"+pathf);
			Rectangle pageSize16 = new Rectangle(0,0,1300,900);
		    Document document16 = new Document(pageSize16);	
		   	
		    PdfWriter.getInstance(document16, new FileOutputStream(pathf));
		    document16.open();
		    try{		
				   jpg1 = Image.getInstance(path);
				   jpg1.setAlignment(1);
	  	           jpg1.setWidthPercentage(300);
	  	           jpg1.setDeflated(true);
	  	           jpg1.setDpi(100,100);
	  	           document16.add(jpg1);
			} catch(Exception e){
			        Logging.debug("Error not able to display image"+e);	
			}
		    Table t216= new Table(1);
		      //t2.setConvert2pdfptable(true);
		      t216.setCellsFitPage(true);
		      t216.setBackgroundColor(Color.yellow);
		      t216.setTableFitsPage(true);
		      t216.setPadding(0);
		      String head16= new String("Capital Change To Universe Report");
		     
		      com.lowagie.text.Cell c216 = new com.lowagie.text.Cell(head16);
		      c216.setHorizontalAlignment(1);
		      c216.setHeader(true);
		      t216.addCell(c216);
		      document16.add(t216);
		 
	  	Table t16 = new Table(7);
	  	t16.setPadding(10);
	  	t16.setSpacing(1);
	  	
	  	t16.setBackgroundColor(Color.LIGHT_GRAY);
	  	t16.setBorderWidth(1);
	  	t16.setBorderColor(Color.LIGHT_GRAY);
	  	
	  	
	  	String s16 = new String();
	  	com.lowagie.text.Cell c116= new com.lowagie.text.Cell(s16);
		c116.setHeader(true);
		String id16 = null;
		if(ai.size()!= 0)
		{
			Iterator it = ai.iterator();
			while(it.hasNext())
			{
		
				it.next();
				for(int j = 0;j<7;j++)
				{
					if(it.hasNext())
					{	
						id16=(String)it.next();
						//double dob=0.0;
						try{
							if(j==0){
								
								if(flag){
									c116 = new com.lowagie.text.Cell("STOCK NAME");
									//c116.setHorizontalAlignment(1);
									t16.addCell(c116);
									c116 = new com.lowagie.text.Cell("FACE VALUE");
									c116.setHorizontalAlignment(1);
									t16.addCell(c116);
									c116 = new com.lowagie.text.Cell("TOTAL ISSUED SHARES");
									c116.setHorizontalAlignment(1);
									t16.addCell(c116);
									
									c116 = new com.lowagie.text.Cell("MKT CAP VALUE");
									c116.setHorizontalAlignment(1);
									t16.addCell(c116);
									c116 = new com.lowagie.text.Cell("IWF");
									c116.setHorizontalAlignment(1);
									t16.addCell(c116);
									c116 = new com.lowagie.text.Cell("CORPORATE ACTION");
									c116.setHorizontalAlignment(1);
									t16.addCell(c116);
									c116 = new com.lowagie.text.Cell("APPLIED DATE");
									c116.setHorizontalAlignment(1);
									t16.addCell(c116);
									
									flag=false;
								}
								String stock_name=id16;
								sb1.append(stock_name);
								s16 = stock_name;
								c116 = new com.lowagie.text.Cell(s16);
								t16.addCell(c116);
							   					
							   }
							else if(j==1){
								String face_value=id16;
								sb1.append(face_value);
								s16 = face_value;
								c116 = new com.lowagie.text.Cell(s16);
								c116.setHorizontalAlignment(1);
								t16.addCell(c116);
							   					
							   }
							
							else if(j==2){
								String total_issuable_shares=id16;
								sb1.append(total_issuable_shares);
								s16 = total_issuable_shares;
								c116 = new com.lowagie.text.Cell(s16);
								c116.setHorizontalAlignment(1);
								t16.addCell(c116);
							   					
							}
							
							
							else if(j==3){
								String mkt_Cap=id16;
								//mcv=ad.indexcompose(mcv);
								//mcv=AdjustDecimal.ArrangeAsNumeric(mcv);
								
								sb1.append(mkt_Cap);
								s16 = mkt_Cap;
								c116 = new com.lowagie.text.Cell(s16);
								c116.setHorizontalAlignment(1);
								t16.addCell(c116);
							   					
							  }
							  else if(j==4){
								String iwf=id16;
								
								sb1.append(iwf);
								s16 = iwf;
								c116 = new com.lowagie.text.Cell(s16);
								c116.setHorizontalAlignment(1);
								t16.addCell(c116);
							   					
							  }
							  else if(j==5){
								String corporate_action=id16;
								
								sb1.append(corporate_action);
								s16 = corporate_action;
								c116 = new com.lowagie.text.Cell(s16);
								c116.setHorizontalAlignment(1);
								t16.addCell(c116);
							   					
							  }
							
							else if(j==6){
								String app_date=id16;
								
								sb1.append(app_date);
								s16 = app_date;
								c116 = new com.lowagie.text.Cell(s16);
								c116.setHorizontalAlignment(1);
								t16.addCell(c116);
							   					
							  }
							
						} catch(Exception e){
							
							Logging.error(" Error : "+e.getMessage());
						}
					
														
					}
				}
				//count = count + 1;
			}
				
		}// end of if

		
		document16.add(t16);
		document16.close();
		 break;
		//for company wise weightage 
		case 2: 
			
			
			pathf = pathf + "CoolMenus/" + "CompanyWiseWeightage.pdf";
			//Logging.debug("path of pdf file"+pathf);
			Rectangle pageSize2 = new Rectangle(0,0,1300,900);
		    Document document2 = new Document(pageSize2);	
		   	
		    PdfWriter.getInstance(document2, new FileOutputStream(pathf));
		    document2.open();
		    try{		
				   jpg1 = Image.getInstance(path);
				   jpg1.setAlignment(1);
	  	           jpg1.setWidthPercentage(300);
	  	           jpg1.setDeflated(true);
	  	           jpg1.setDpi(100,100);
	  	           document2.add(jpg1);
			} catch(Exception e){
			        Logging.debug("Error not able to display image"+e);	
			}
		      Table t22= new Table(1);
		      t22.setCellsFitPage(true);
		      t22.setBackgroundColor(Color.yellow);
		      t22.setTableFitsPage(true);
		      t22.setPadding(0);
		      String head2= new String("Company Weightage Report");
		     
		      com.lowagie.text.Cell c22 = new com.lowagie.text.Cell(head2);
		      c22.setHorizontalAlignment(1);
		      c22.setHeader(true);
		      t22.addCell(c22);
		      document2.add(t22);
		 
		  	Table t02 = new Table(3);
		   	t02.setPadding(10);
		  	t02.setSpacing(1);
		  	
		  	t02.setBackgroundColor(Color.LIGHT_GRAY);
		  	t02.setBorderWidth(1);
		  	//t02.setBorderColor(Color.WHITE);
		  	t02.setBorderColor(Color.LIGHT_GRAY);
		  	String s02 = new String();
		  	com.lowagie.text.Cell c12= new com.lowagie.text.Cell(s02);
		
		String id2 = null;
	
		//Logging.debug("size of vector "+ai.size());
		if(ai.size()!= 0)
		{
			Iterator it = ai.iterator();
			while(it.hasNext())
			{
		
				//it.next();
				for(int j = 0;j<3;j++)
				{
					if(it.hasNext())
					{	
						id2=(String)it.next();
						//double dob=0.0;
						try{
							if(j==0){
								
								if(flag){
									c12 = new com.lowagie.text.Cell("COMPANY NAME");
									//c12.setHorizontalAlignment(1);
									t02.addCell(c12);
									c12 = new com.lowagie.text.Cell("MARKET CAP");
									c12.setHorizontalAlignment(1);
									t02.addCell(c12);
									c12 = new com.lowagie.text.Cell("WEIGHTAGE");
									c12.setHorizontalAlignment(1);
									t02.addCell(c12);
									flag=false;
								}
																		
								String company_Name=id2;
								sb1.append(company_Name);
								s02 = company_Name;
								c12 = new com.lowagie.text.Cell(s02);
								//c12.setHorizontalAlignment(1);
								t02.addCell(c12);
							   					
							   }
							else if(j==1){
								String mark_cap=id2;
								sb1.append(mark_cap);
								s02 = mark_cap;
								c12 = new com.lowagie.text.Cell(s02);
								c12.setHorizontalAlignment(1);
								t02.addCell(c12);
							   					
							   }
							else if(j==2){
								String weightage=id2;
								//iwf=ad.indexcompose(iwf);
								//iwf=AdjustDecimal.ArrangeAsNumeric(iwf);
								
								sb1.append(weightage);
								s02 = weightage;
								c12 = new com.lowagie.text.Cell(s02);
								c12.setHorizontalAlignment(1);
								t02.addCell(c12);
							   					
							   }
												
												
						} catch(Exception e){
							
							Logging.error(" Error : "+e.getMessage());
						}
					
						
					}
				}
				//count = count + 1;
			}
				
		}// end of if
		
		document2.add(t02);
		document2.close();
		 break;
//		for Industry Wise Weightage  
		case 3: 
			
			
			pathf = pathf + "CoolMenus/" + "IndWiseWeightage.pdf";
			//Logging.debug("path of pdf file"+pathf);
			Rectangle pageSize3 = new Rectangle(0,0,1300,900);
		    Document document3 = new Document(pageSize3);	
		   PdfWriter.getInstance(document3, new FileOutputStream(pathf));
		    document3.open();
		    try{		
				   jpg1 = Image.getInstance(path);
				   jpg1.setAlignment(1);
	  	           jpg1.setWidthPercentage(300);
	  	           jpg1.setDeflated(true);
	  	           jpg1.setDpi(100,100);
	  	           document3.add(jpg1);
			} catch(Exception e){
			        Logging.debug("Error not able to display image"+e);	
			}
		
		    Table t23= new Table(1);
		     t23.setCellsFitPage(true);
		      t23.setBackgroundColor(Color.yellow);
		      t23.setTableFitsPage(true);
		      t23.setPadding(0);
		      String head3= new String("Industry Weightage Report");
		     
		      com.lowagie.text.Cell c23 = new com.lowagie.text.Cell(head3);
		      c23.setHorizontalAlignment(1);
		      c23.setHeader(true);
		      t23.addCell(c23);
		      document3.add(t23);
		 
		  	Table t3 = new Table(3);
		   	t3.setPadding(10);
		  	t3.setSpacing(1);
		  	
		  	t3.setBackgroundColor(Color.LIGHT_GRAY);
		  	t3.setBorderWidth(1);
		  	t3.setBorderColor(Color.LIGHT_GRAY);
		  	String s3 = new String();
		  	com.lowagie.text.Cell c13= new com.lowagie.text.Cell(s3);
		   
		String id3 = null;
		//Logging.debug("size of vector "+ai.size());
		if(ai.size()!= 0)
		{
			Iterator it = ai.iterator();
			while(it.hasNext())
			{
		
				//it.next();
				for(int j = 0;j<3;j++)
				{
					if(it.hasNext())
					{	
						id3=(String)it.next();
						//double dob=0.0;
						try{
							if(j==0){
								
								if(flag){
									c13 = new com.lowagie.text.Cell("INDUSTRY NAME");
									//c13.setHorizontalAlignment(1);
									t3.addCell(c13);
									c13 = new com.lowagie.text.Cell("MARKET CAP");
									c13.setHorizontalAlignment(1);
									t3.addCell(c13);
									c13 = new com.lowagie.text.Cell("WEIGHTAGE");
									c13.setHorizontalAlignment(1);
									t3.addCell(c13);
									flag=false;
								}
								
								String company_Name=id3;
								sb1.append(company_Name);
								s3 = company_Name;
								c13 = new com.lowagie.text.Cell(s3);
								//c13.setHorizontalAlignment(1);
								t3.addCell(c13);
							   					
							   }
							else if(j==1){
								String mark_cap=id3;
								sb1.append(mark_cap);
								s3= mark_cap;
								c13 = new com.lowagie.text.Cell(s3);
								c13.setHorizontalAlignment(1);
								t3.addCell(c13);
							   					
							   }
							else if(j==2){
								String weightage=id3;
								
								sb1.append(weightage);
								s3 = weightage;
								c13 = new com.lowagie.text.Cell(s3);
								c13.setHorizontalAlignment(1);
								t3.addCell(c13);
							   					
							   }
								
							
						} catch(Exception e){
							
							Logging.error(" Error : "+e.getMessage());
						}
					
					}
				}
				//count = count + 1;
			}
				
		}// end of if
		
		document3.add(t3);
		document3.close();
		 break;
		 //for stock divident
		case 22: 
			
			
			pathf = pathf + "CoolMenus/" + "StockDivident.pdf";
			//Logging.debug("path of pdf file"+pathf);
			Rectangle pageSize22 = new Rectangle(0,0,1300,900);
		    Document document22 = new Document(pageSize22);	
		   	PdfWriter.getInstance(document22, new FileOutputStream(pathf));
		    document22.open();
		    try{		
				   jpg1 = Image.getInstance(path);
				   jpg1.setAlignment(1);
	  	           jpg1.setWidthPercentage(300);
	  	           jpg1.setDeflated(true);
	  	           jpg1.setDpi(100,100);
	  	         document22.add(jpg1);
			} catch(Exception e){
			        Logging.debug("Error not able to display image"+e);	
			}
		    
		    Table t222 = new Table(1);
		    t222.setCellsFitPage(true);
		      t222.setBackgroundColor(Color.yellow);
		      t222.setTableFitsPage(true);
		      t222.setPadding(0);
		      String head22= new String("Stock Divident Report");
		      com.lowagie.text.Cell c222 = new com.lowagie.text.Cell(head22);
		      c222.setHorizontalAlignment(1);
		      c222.setHeader(true);
		      t222.addCell(c222);
		      document22.add(t222);
		 
		  	Table t2sd = new Table(6);
		  	t2sd.setPadding(10);
		  	t2sd.setSpacing(1);
		  	
		  	t2sd.setBackgroundColor(Color.LIGHT_GRAY);
		  	t2sd.setBorderWidth(1);
		  //	t2sd.setBorderColor(Color.WHITE);
		  	t2sd.setBorderColor(Color.LIGHT_GRAY);
		  	String s2sd = new String();
		  	
	  	com.lowagie.text.Cell c122= new com.lowagie.text.Cell(s2sd);
		c122.setHeader(true);
		String id22 = null;
					
		//Logging.debug("size of vector "+ai.size());
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
						id22=(String)it.next();
						//double dob=0.0;
						try{
							if(j==0){
								
								
								if(flag){
									c122 = new com.lowagie.text.Cell("STOCK NAME");
									//c122.setHorizontalAlignment(1);
									t2sd.addCell(c122);
									c122 = new com.lowagie.text.Cell("FACE VALUE");
									c122.setHorizontalAlignment(1);
									t2sd.addCell(c122);
									c122 = new com.lowagie.text.Cell("TOTAL ISSUED SHARES");
									c122.setHorizontalAlignment(1);
									t2sd.addCell(c122);
									c122 = new com.lowagie.text.Cell("MARKET CAP");
									c122.setHorizontalAlignment(1);
									t2sd.addCell(c122);
									c122 = new com.lowagie.text.Cell("DIVIDENT");
									c122.setHorizontalAlignment(1);
									t2sd.addCell(c122);
									c122 = new com.lowagie.text.Cell("APPLIED DATE");
									c122.setHorizontalAlignment(1);
									t2sd.addCell(c122);
									
									flag=false;
								}
								String stock_name=id22;
								sb1.append(stock_name);
								s2sd = stock_name;
								c122 = new com.lowagie.text.Cell(s2sd);
								t2sd.addCell(c122);
							   					
							   }
							else if(j==1){
								String face_value=id22;
								sb1.append(face_value);
								s2sd = face_value;
								c122 = new com.lowagie.text.Cell(s2sd);
								c122.setHorizontalAlignment(1);
								t2sd.addCell(c122);
							   					
							   }
							else if(j==2){
								String total_issued_share=id22;
								sb1.append(total_issued_share);
								s2sd = total_issued_share;
								c122 = new com.lowagie.text.Cell(s2sd);
								c122.setHorizontalAlignment(1);
								t2sd.addCell(c122);
							   					
							   }
							else if(j==3){
								String mark_cap=id22;
								sb1.append(mark_cap);
								s2sd = mark_cap;
								c122 = new com.lowagie.text.Cell(s2sd);
								c122.setHorizontalAlignment(1);
								t2sd.addCell(c122);
							   					
							}
							else if(j==4){
								String divident =id22;
								
								sb1.append(divident);
								s2sd = divident;
								c122 = new com.lowagie.text.Cell(s2sd);
								c122.setHorizontalAlignment(1);
								t2sd.addCell(c122);
							   					
							  }
							else if(j==5){
								String app_date=id22;
								
								sb1.append(app_date);
								s2sd = app_date;
								c122 = new com.lowagie.text.Cell(s2sd);
								c122.setHorizontalAlignment(1);
								t2sd.addCell(c122);
							   					
							  }
							
																
						} catch(Exception e){
							
							Logging.error(" Error : "+e.getMessage());
						}
					
						
					}
				}
				//count = count + 1;
			}
				
		}// end of if

		
		document22.add(t2sd);
		document22.close();
		 break;
		 
		 //for Traded volume
		case 25: 
			
			
			pathf = pathf + "CoolMenus/" + "TradedVolume.pdf";
			//Logging.debug("path of pdf file"+pathf);
			Rectangle pageSize25 = new Rectangle(0,0,1300,900);
		    Document document25 = new Document(pageSize25);	
		   	
		    PdfWriter.getInstance(document25, new FileOutputStream(pathf));
		    document25.open();
		    try{		
				   jpg1 = Image.getInstance(path);
				   jpg1.setAlignment(1);
	  	           jpg1.setWidthPercentage(300);
	  	           jpg1.setDeflated(true);
	  	           jpg1.setDpi(100,100);
	  	           document25.add(jpg1);
			} catch(Exception e){
			        Logging.debug("Error not able to display image"+e);	
			}
		    Table t225= new Table(1);
		      
		    t225.setCellsFitPage(true);
		    t225.setBackgroundColor(Color.yellow);
		    t225.setTableFitsPage(true);
		    t225.setPadding(0);
		      String head25= new String("Traded Volume Report");
		      com.lowagie.text.Cell c225 = new com.lowagie.text.Cell(head25);
		      c225.setHorizontalAlignment(1);
		      c225.setHeader(true);
		      t225.addCell(c225);
		      document25.add(t225);
		 
		  	Table t25 = new Table(2);
		  	t25.setPadding(10);
		  	t25.setSpacing(1);
		  	
		  	t25.setBackgroundColor(Color.LIGHT_GRAY);
		  	t25.setBorderWidth(1);
		  	//t25.setBorderColor(Color.WHITE);
		  	t25.setBorderColor(Color.LIGHT_GRAY);
		  	String s25 = new String();
		  	
	  	com.lowagie.text.Cell c125= new com.lowagie.text.Cell(s25);
		c125.setHeader(true);
		 
		
		String id25 = null;
		
		//Logging.debug("size of vector "+ai.size());
		if(ai.size()!= 0)
		{
			Iterator it = ai.iterator();
			while(it.hasNext())
			{
		
				it.next();
				for(int j = 0;j<2;j++)
				{
					if(it.hasNext())
					{	
						id25=(String)it.next();
						//double dob=0.0;
						try{
							if(j==0){
								
								if(flag){
									c125 = new com.lowagie.text.Cell("STOCK NAME");
									//c125.setHorizontalAlignment(1);
									t25.addCell(c125);
									c125 = new com.lowagie.text.Cell("TRADED VOLUME");
									c125.setHorizontalAlignment(1);
									t25.addCell(c125);
									flag=false;
									
								}	
								String stock_name=id25;
								sb1.append(stock_name);
								s25 = stock_name;
								c125 = new com.lowagie.text.Cell(s25);
								
								t25.addCell(c125);
							   					
							   }
							else if(j==1){
								String traded_volume=id25;
								sb1.append(traded_volume);
								s25= traded_volume;
								c125 = new com.lowagie.text.Cell(s25);
								
								c125.setHorizontalAlignment(1);
								t25.addCell(c125);
							   					
							   }
							
						} catch(Exception e){
							
							Logging.error(" Error : "+e.getMessage());
						}
					
						
						
					}
				}
				//count = count + 1;
			}
				
		}// end of if

		
		document25.add(t25);
		document25.close();
		 break; 
		 
		 //for stock contribution
		case 5: 
			
			
			
			pathf = pathf + "CoolMenus/" + "StockContribution.pdf";
			//Logging.debug("path of pdf file"+pathf);
			Rectangle pageSize5 = new Rectangle(0,0,1300,900);
		    Document document5 = new Document(pageSize5);	
		   	
		    PdfWriter.getInstance(document5, new FileOutputStream(pathf));
		    document5.open();
		    try{		
				   jpg1 = Image.getInstance(path);
				   jpg1.setAlignment(1);
	  	           jpg1.setWidthPercentage(300);
	  	           jpg1.setDeflated(true);
	  	           jpg1.setDpi(100,100);
	  	           document5.add(jpg1);
			} catch(Exception e){
			        Logging.debug("Error not able to display image"+e);	
			}
		    Table t25sc= new Table(1);
		      
		    t25sc.setCellsFitPage(true);
		    t25sc.setBackgroundColor(Color.yellow);
		    t25sc.setTableFitsPage(true);
		    t25sc.setPadding(0);
		    String head5= new String("Stock Contribution Report");
		    com.lowagie.text.Cell c25 = new com.lowagie.text.Cell(head5);
		    c25.setHorizontalAlignment(1);
		    c25.setHeader(true);
		    t25sc.addCell(c25);
		    document5.add(t25sc);
		 	Table t5 = new Table(4);
		  	t5.setPadding(10);
		  	t5.setSpacing(1);
		  	
		    t5.setBackgroundColor(Color.LIGHT_GRAY);
		  	t5.setBorderWidth(1);
		  	//t5.setBorderColor(Color.WHITE);
		  	t5.setBorderColor(Color.LIGHT_GRAY);
		  	String s5 = new String();
		  	
	  	com.lowagie.text.Cell c15= new com.lowagie.text.Cell(s5);
		c15.setHeader(true);
		String id5 = null;
		
		//Logging.debug("size of vector "+ai.size());
		if(ai.size()!= 0)
		{
			Iterator it = ai.iterator();
			while(it.hasNext())
			{
		
				//it.next();
				for(int j = 0;j<4;j++)
				{
					if(it.hasNext())
					{	
						id5=(String)it.next();
						//double dob=0.0;
						try{
							if(j==0){
								
								if(flag){
									c15 = new com.lowagie.text.Cell("STOCK NAME");
									//c15.setHorizontalAlignment(1);
									t5.addCell(c15);
									c15 = new com.lowagie.text.Cell("Index mkt. cap. difference");
									c15.setHorizontalAlignment(1);
									t5.addCell(c15);
									c15 = new com.lowagie.text.Cell("Stock mkt. cap. difference");
									c15.setHorizontalAlignment(1);
									t5.addCell(c15);
									c15 = new com.lowagie.text.Cell("Weightage");
									c15.setHorizontalAlignment(1);
									t5.addCell(c15);
									flag=false;
									
								}	
								String stock_name=id5;
								
								sb1.append(stock_name);
								s5 = stock_name;
								c15 = new com.lowagie.text.Cell(s5);
								t5.addCell(c15);
							   	
								
							   }
							else if(j==1){
								String Index_mkt_cap_diff=id5;
								sb1.append(Index_mkt_cap_diff);
								s5 = Index_mkt_cap_diff;
								c15 = new com.lowagie.text.Cell(s5);
								c15.setHorizontalAlignment(1);
								t5.addCell(c15);
							   					
							   }
							
							else if(j==2){
								String Stock_mkt_cap_diff=id5;
								sb1.append(Stock_mkt_cap_diff);
								s5 = Stock_mkt_cap_diff;
								c15 = new com.lowagie.text.Cell(s5);
								c15.setHorizontalAlignment(1);
								t5.addCell(c15);
							   					
							}
							
							
							else if(j==3){
								String weightage=id5;
								sb1.append(weightage);
								s5 = weightage;
								c15 = new com.lowagie.text.Cell(s5);
								c15.setHorizontalAlignment(1);
								t5.addCell(c15);
							   					
							  }
							
							
							
						} catch(Exception e){
							
							Logging.error(" Error : "+e.getMessage());
						}
					
						
						
					}
				}
				//count = count + 1;
			}
				
		}// end of if

		
		document5.add(t5);
		document5.close();
		 break;
		 
		case 6: 
			
			
			pathf = pathf + "CoolMenus/" + "StockDetails.pdf";
			
			Rectangle pageSize6 = new Rectangle(0,0,1300,900);
		    Document document6 = new Document(pageSize6);	
		   	
		    PdfWriter.getInstance(document6, new FileOutputStream(pathf));
		    document6.open();
		    try{		
				   jpg1 = Image.getInstance(path);
				   jpg1.setAlignment(1);
	  	           jpg1.setWidthPercentage(300);
	  	           jpg1.setDeflated(true);
	  	           jpg1.setDpi(100,100);
	  	          document6.add(jpg1);
			} catch(Exception e){
			        Logging.debug("Error not able to display image"+e);	
			}
		    
		    Table tsd= new Table(1);
		      
		    tsd.setCellsFitPage(true);
		    tsd.setBackgroundColor(Color.yellow);
		    tsd.setTableFitsPage(true);
		    tsd.setPadding(0);
		    String head6= new String("Stock Details Report");
		    com.lowagie.text.Cell c6s = new com.lowagie.text.Cell(head6);
		    c6s.setHorizontalAlignment(1);
		    c6s.setHeader(true);
		    tsd.addCell(c6s);
		    document6.add(tsd);
		 
		  	Table t6 = new Table(9);
		  	t6.setPadding(10);
		  	t6.setSpacing(1);
		  	
		  	t6.setBackgroundColor(Color.LIGHT_GRAY);
		  	t6.setBorderWidth(1);
		    //	t6.setBorderColor(Color.WHITE);
		  	t6.setBorderColor(Color.LIGHT_GRAY);
		  	String s6 = new String();
		  	
	  	    com.lowagie.text.Cell c6= new com.lowagie.text.Cell(s6);
		    c6.setHeader(true);
		
			String id6 = null;
			
			//Logging.debug("size of vector "+ai.size());
			if(ai.size()!= 0)
			{
				Iterator it = ai.iterator();
				while(it.hasNext())
				{
			
					//it.next();
					for(int j = 0;j<10;j++)
					{
						if(it.hasNext())
						{	
							id6=(String)it.next();
							//double dob=0.0;
							try{
								
																		
									
								if(j==0){
									
									if(flag){
										c6 = new com.lowagie.text.Cell("STOCK NAME");
										//c6.setHorizontalAlignment(1);
										t6.addCell(c6);
										c6 = new com.lowagie.text.Cell("Open Value");
										c6.setHorizontalAlignment(1);
										t6.addCell(c6);
										c6 = new com.lowagie.text.Cell("Close Value");
										c6.setHorizontalAlignment(1);
										t6.addCell(c6);
										c6 = new com.lowagie.text.Cell("Low Value");
										c6.setHorizontalAlignment(1);
										t6.addCell(c6);
										c6 = new com.lowagie.text.Cell("High Value");
										c6.setHorizontalAlignment(1);
										t6.addCell(c6);
										c6 = new com.lowagie.text.Cell("Traded Value");
										c6.setHorizontalAlignment(1);
										t6.addCell(c6);
										c6 = new com.lowagie.text.Cell("Market cap.");
										c6.setHorizontalAlignment(1);
										t6.addCell(c6);
										c6 = new com.lowagie.text.Cell("No Of Trades");
										c6.setHorizontalAlignment(1);
										t6.addCell(c6);
										c6 = new com.lowagie.text.Cell("Date");
										c6.setHorizontalAlignment(1);
										t6.addCell(c6);
										
										
										flag=false;
									}
									String stockname=id6;
									sb1.append(stockname);
									s6 = stockname;
									c6 = new com.lowagie.text.Cell(s6);
									t6.addCell(c6);
								   }
								else if(j==1){
									String openvalue=id6;
									sb1.append(openvalue);
									s6 = openvalue;
									c6 = new com.lowagie.text.Cell(s6);
									c6.setHorizontalAlignment(1);
									t6.addCell(c6);
								   }
								else if(j==2){
									String closevalue=id6;
								//	tis=AdjustDecimal.ArrangeAsNumeric(tis);
									
									sb1.append(closevalue);
									s6 = closevalue;
									c6 = new com.lowagie.text.Cell(s6);
									c6.setHorizontalAlignment(1);
									t6.addCell(c6);
								   }
								else if(j==3){
									String lowvalue=id6;
								//	tis=AdjustDecimal.ArrangeAsNumeric(tis);
									
									sb1.append(lowvalue);
									s6 = lowvalue;
									c6 = new com.lowagie.text.Cell(s6);
									c6.setHorizontalAlignment(1);
									t6.addCell(c6);
								   }
								else if(j==4){
									String highvalue=id6;
								//	tis=AdjustDecimal.ArrangeAsNumeric(tis);
									
									sb1.append(highvalue);
									s6 = highvalue;
									c6 = new com.lowagie.text.Cell(s6);
									c6.setHorizontalAlignment(1);
									t6.addCell(c6);
								   }
								else if(j==5){
									String tradedvalue=id6;
								//	tis=AdjustDecimal.ArrangeAsNumeric(tis);
									sb1.append(tradedvalue);
									s6 = tradedvalue;
									c6 = new com.lowagie.text.Cell(s6);
									c6.setHorizontalAlignment(1);
									t6.addCell(c6);
								   }
								else if(j==6){
									String tradedvol=id6;
								}
								else if(j==7){
									String mcv=id6;
									//mcv=ad.indexcompose(mcv);
									//mcv=AdjustDecimal.ArrangeAsNumeric(mcv);
									sb1.append(mcv);
									s6 = mcv;
									c6 = new com.lowagie.text.Cell(s6);
									c6.setHorizontalAlignment(1);
									t6.addCell(c6);
								  }
								else if(j==8){
									String nooftrades=id6;
									sb1.append(nooftrades);
									s6 = nooftrades;
									c6 = new com.lowagie.text.Cell(s6);
									c6.setHorizontalAlignment(1);
									t6.addCell(c6);
								   }
								
								
							    else if(j==9){
							    	String  date=id6;
							    	
							    	 sb1.append(date);
							    	 s6 = date;
							    	 c6 = new com.lowagie.text.Cell(s6);
									 c6.setHorizontalAlignment(1);
									 t6.addCell(c6);
									 
								  }
								
								
								
								
							} catch(Exception e){
								
								Logging.error(" Error : "+e.getMessage());
							}
							
						}
					}
					//count = count + 1;
				}
			
			}// end of if

			document6.add(t6);
			document6.close();
			break;	
			 
			
			
			
//		kapil code enid 
		case 20: 
			
			
			pathf = pathf + "CoolMenus/" + "IndexPe_Pb.pdf";
			//Logging.debug("path of pdf file"+pathf);
			Rectangle pageSize20 = new Rectangle(0,0,1300,900);
		    Document document20 = new Document(pageSize20);	
		   	
		    PdfWriter.getInstance(document20, new FileOutputStream(pathf));
		    document20.open();
		    try{		
				   jpg1 = Image.getInstance(path);
				   jpg1.setAlignment(1);
	  	           jpg1.setWidthPercentage(300);
	  	           jpg1.setDeflated(true);
	  	           jpg1.setDpi(100,100);
	  	           document20.add(jpg1);
	  	           
		    } catch(Exception e){
			        Logging.debug("Error not able to display image"+e);	
			}
		    Table t220= new Table(1);
		    //t2.setConvert2pdfptable(true);
		    t220.setCellsFitPage(true);
		    t220.setBackgroundColor(Color.yellow);
		    t220.setTableFitsPage(true);
		    t220.setPadding(0);
		    String head20= new String("Index Pe Pb Report");
		    com.lowagie.text.Cell c220 = new com.lowagie.text.Cell(head20);
		    c220.setHorizontalAlignment(1);
		    //c2.setWidth("200");
		    c220.setHeader(true);
		    t220.addCell(c220);
		    document20.add(t220);
		    
	  	Table t20 = new Table(9);
	  	//t.setBorder(220, 255, 100);
	  	t20.setPadding(10);
	  	t20.setSpacing(1);
	  	
	    t20.setBackgroundColor(Color.LIGHT_GRAY);
	  	t20.setBorderWidth(1);
	  	//t20.setBorderColor(Color.WHITE);
	  	t20.setBorderColor(Color.LIGHT_GRAY);
	  	String s20 = new String();
	  	
	  	com.lowagie.text.Cell c120=new com.lowagie.text.Cell(s20);
		c120.setHeader(true);
		String id20 = null;
		//Logging.debug("size of vector "+ai.size());
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
						id20=(String)it.next();
						//double dob=0.0;
						try{
							if(j==0){
								if(flag){
									c120 = new com.lowagie.text.Cell("Trading Date");
									//c120.setHorizontalAlignment(1);
									t20.addCell(c120);
									c120 = new com.lowagie.text.Cell("Close");
									c120.setHorizontalAlignment(1);
									t20.addCell(c120);
									c120 = new com.lowagie.text.Cell("%Change");
									c120.setHorizontalAlignment(1);
									t20.addCell(c120);
									c120 = new com.lowagie.text.Cell("Market Cap.");
									c120.setHorizontalAlignment(1);
									t20.addCell(c120);
									c120 = new com.lowagie.text.Cell("Share Traded");
									c120.setHorizontalAlignment(1);
									t20.addCell(c120);
									c120 = new com.lowagie.text.Cell("Turn Over");
									c120.setHorizontalAlignment(1);
									t20.addCell(c120);
									c120 = new com.lowagie.text.Cell("P/E Ratio");
									c120.setHorizontalAlignment(1);
									t20.addCell(c120);
									c120 = new com.lowagie.text.Cell("P/B Ratio");
									c120.setHorizontalAlignment(1);
									t20.addCell(c120);
									c120 = new com.lowagie.text.Cell("Divident Yield");
									c120.setHorizontalAlignment(1);
									t20.addCell(c120);
									
									
									flag=false;
								}
								
								
								String trading_date=id20;
								sb1.append(trading_date);
								s20 = trading_date;
								c120 = new com.lowagie.text.Cell(s20);
								t20.addCell(c120);
							   					
							   }
							else if(j==1){
								String close=id20;
								sb1.append(close);
								s20 = close;
								c120 = new com.lowagie.text.Cell(s20);
								c120.setHorizontalAlignment(1);
								t20.addCell(c120);
							   					
							   }
							else if(j==2){
								String per_change=id20;
								//iwf=ad.indexcompose(iwf);
								//iwf=AdjustDecimal.ArrangeAsNumeric(iwf);
								
								sb1.append(per_change);
								s20 = per_change;
								c120 = new com.lowagie.text.Cell(s20);
								c120.setHorizontalAlignment(1);
								t20.addCell(c120);
							   					
							   }
							else if(j==3){
								String market=id20;
								sb1.append(market);
								s20 = market;
								c120 = new com.lowagie.text.Cell(s20);
								c120.setHorizontalAlignment(1);
								t20.addCell(c120);
							   					
							}
							else if(j==4){
								String shares_traded=id20;
								sb1.append(shares_traded);
								s20 = shares_traded;
								c120 = new com.lowagie.text.Cell(s20);
								c120.setHorizontalAlignment(1);
								t20.addCell(c120);
							   					
							  }
							else if(j==5){
								String turnover =id20;
								sb1.append(turnover);
								s20 = turnover;
								c120 = new com.lowagie.text.Cell(s20);
								c120.setHorizontalAlignment(1);
								t20.addCell(c120);
							   					
							  }
							else if(j==6){
								String pe_ratio=id20;
								sb1.append(pe_ratio);
								s20 = pe_ratio;
								c120 = new com.lowagie.text.Cell(s20);
								c120.setHorizontalAlignment(1);
								t20.addCell(c120);
							   					
							   }
							else if(j==7){
								String pb_ratio=id20;
								sb1.append(pb_ratio);
								s20 = pb_ratio;
								c120 = new com.lowagie.text.Cell(s20);
								c120.setHorizontalAlignment(1);
								t20.addCell(c120);
							   					
							   }
							else if(j==8){
								String dividend_yield=id20;
								sb1.append(dividend_yield);
								s20 = dividend_yield;
								c120 = new com.lowagie.text.Cell(s20);
								c120.setHorizontalAlignment(1);
								t20.addCell(c120);
							   					
							  }
							
															   
							
						} catch(Exception e){
							
							Logging.error(" Error : "+e.getMessage());
						}
					
						
					}
				}
				//count = count + 1;
			}
				
		}// end of if

		
		document20.add(t20);
		document20.close();
		 break;
		

				 
				}//end of switch	
			
				 
							
		}catch(Exception e)
		{
			Logging.debug("Error prevented the file from being created."+e);
		}finally{
			
		}
		
	}
	//**************************OHLC
	
	public void create_file( Vector vid,int switch_code,String iename, Vector ai,String fdate, String tdate)
	{ 
		StringBuffer sb1= new StringBuffer();
	//	org.jfree.chart.demo.servlet.ComposeIndex ci=new org.jfree.chart.demo.servlet.ComposeIndex();
		ComposeIndex ci=ConnectInit.getComposeIndex();
		try
		{  
			 pathf = Connect.getCoolMenuspath();
	       	 l=pathf.length();
			 path=pathf.substring(0,(l-16));
			 path=path+"pages/stockpile_banner.jpg";
			 
			 switch(switch_code)
				{
				case 10: 	
					
					pathf = pathf + "CoolMenus/" + "IndexCompareOHLC.pdf";
					Rectangle pageSize10 = new Rectangle(0,0,1300,900);
				    Document document10 = new Document(pageSize10);	
				   	PdfWriter.getInstance(document10, new FileOutputStream(pathf));
				    document10.open();
				    Table t210= new Table(1);
				    try{	
			    		
					   	jpg1 = Image.getInstance(path);
					   	jpg1.setAlignment(1);
		        		jpg1.setWidthPercentage(300);
		        		jpg1.setDeflated(true);
		        		jpg1.setDpi(100,100);
		        		document10.add(jpg1);
				
					   
				  } catch(Exception e){
					Logging.debug("Error not able to display image"+e);	
				  }
				  t210.setBackgroundColor(Color.YELLOW);
			      t210.setCellsFitPage(true);
			      t210.setTableFitsPage(true);
			      t210.setPadding(0);
			      String head10= new String("Index Compare OHLC Report");
			      com.lowagie.text.Cell c210 = new com.lowagie.text.Cell(head10);
			      c210.setHorizontalAlignment(1);
			      c210.setHeader(true);
			      t210.addCell(c210);
			      document10.add(t210);
			      Logging.debug("lokesh problem in Table t10 ");
			 
		  	Table t10 = new Table(4*vid.size()+1,1);
		  	t10.setBackgroundColor(Color.LIGHT_GRAY);
		  	t10.setPadding(10);
		  	t10.setSpacing(1);
		  	t10.setBorderWidth(1);
		  	t10.setBorderColor(Color.LIGHT_GRAY);
		  	String s10 = new String();
		  	com.lowagie.text.Cell c110= new com.lowagie.text.Cell(s10);
			c110.setHeader(true);
			String id10 = null;
		
			String[] var = new String[vid.size()]; 
			if(vid.size()!= 0)
			{
		
				Iterator itv = vid.iterator();
				
				int var_v = 0;
				int varint10=0;
				while(itv.hasNext())
				{
					String m = (String)itv.next();
					varint10 = Integer.parseInt(m);
					if(var_v==0){
												
						var[var_v] = ci.get_index_name(varint10);
						c110 = new com.lowagie.text.Cell(var[var_v]);
						c110.setColspan(5);
						c110.setHorizontalAlignment(1);
						t10.addCell(c110);
						var_v++;
					}else{
												
						var[var_v] = ci.get_index_name(varint10);
						c110 = new com.lowagie.text.Cell(var[var_v]);
						c110.setColspan(4);
						c110.setHorizontalAlignment(1);
						t10.addCell(c110);
						var_v++;
					}
				}
			}
			
			
			if(ai.size()!= 0)
			{
				Iterator it = ai.iterator();
				while(it.hasNext())
				{
					Logging.debug("lokesh problem in iterator ");
				   	
					//it.next();
					for(int j = 0;j<(4*vid.size())+1;j++)
					{
						if(it.hasNext())
						{	
							id10=(String)it.next();
							//double dob=0.0;
							try{
								
								
								if(j == 0){
									if(flag){
										c110 = new com.lowagie.text.Cell("Date");
										//c110.setHorizontalAlignment(1);
										t10.addCell(c110);
										for(int ij=0; ij < vid.size();ij++){
										
											c110 = new com.lowagie.text.Cell("Open");
											c110.setHorizontalAlignment(1);
											t10.addCell(c110);
											c110 = new com.lowagie.text.Cell("High");
											c110.setHorizontalAlignment(1);
											t10.addCell(c110);
											c110 = new com.lowagie.text.Cell("Low");
											c110.setHorizontalAlignment(1);
											t10.addCell(c110);
											c110 = new com.lowagie.text.Cell("Close");
											c110.setHorizontalAlignment(1);
											t10.addCell(c110);
										}
										flag=false;
									}	
								String stockname=id10;
								sb1.append(stockname);
								s10 = stockname;
								c110 = new com.lowagie.text.Cell(s10);
								t10.addCell(c110);
								Logging.debug("lokesh problem in  "+stockname);
							   				
							   }else if(j==1 || (j%4)==1){
								String tis=id10;
								sb1.append(tis);
								s10 = tis;
								c110 = new com.lowagie.text.Cell(s10);
								c110.setHorizontalAlignment(2);
								t10.addCell(c110);
							   					
							   } else if(j==2 || (j%4)==2){
								String iwf=id10;
								sb1.append(iwf);
								s10 = iwf;
								c110 = new com.lowagie.text.Cell(s10);
								c110.setHorizontalAlignment(2);
								t10.addCell(c110);
							   				
							   }else if(j==3 || (j%4)==3){
								String market=id10;
								sb1.append(market);
								s10 = market;
								c110 = new com.lowagie.text.Cell(s10);
								c110.setHorizontalAlignment(2);
								t10.addCell(c110);
							   					
							   }else if(j==4 || (j%4)==0){
								String ltp=id10;
								sb1.append(ltp);
								s10 = ltp;
								c110 = new com.lowagie.text.Cell(s10);
								c110.setHorizontalAlignment(2);
								t10.addCell(c110);
							   					
							  }
			
						
							} catch(Exception e)
							{
								Logging.debug("Error prevented the file from being created."+e);
							}
						}
					}
				}
			 }
			document10.add(t10);
			document10.close();
			break;
			}
		}catch(Exception e)
		{
			Logging.debug("Error prevented the file from being created."+e);
		}finally{

		}
		
	}

	
	//****************************************************************
//for index correlation
	
	public void create_file1(Vector var_vec,String var,int switch_code,String iename, Vector ai,String fdate, String tdate, String var1)
	{ 
		// new cide for pdf
		Vector vec;
	//	org.jfree.chart.demo.servlet.ComposeIndex ci=new org.jfree.chart.demo.servlet.ComposeIndex();
		StringBuffer sb1= new StringBuffer();
		try
		{  
			
			 pathf = Connect.getCoolMenuspath();
	       	 l=pathf.length();
			 path=pathf.substring(0,(l-16));
			 path=path+"pages/stockpile_banner.jpg";
			 switch(switch_code)
				{
			 // for index composition
				case 15: 
			pathf = pathf + "CoolMenus/" + "IndexCorrelation.pdf";
			Logging.debug("path of pdf file"+pathf);
			Rectangle pageSize15 = new Rectangle(0,0,1500,900);
		    Document document15 = new Document(pageSize15,0,0,0,0);
			document15.setMargins(0,0,0,0);
		    PdfWriter.getInstance(document15, new FileOutputStream(pathf));
		    document15.open();
		    try{		
				   jpg1 = Image.getInstance(path);
				   jpg1.setAlignment(1);
	  	           jpg1.setWidthPercentage(300);
	  	           jpg1.setDeflated(true);
	  	           jpg1.setDpi(100,100);
	  	           document15.add(jpg1);
	  	           
		    } catch(Exception e){
			        Logging.debug("Error not able to display image"+e);	
			}
		    Table t215 = new Table(1);
		    t215.setBackgroundColor(Color.YELLOW);
	        t215.setCellsFitPage(true);
		    t215.setTableFitsPage(true);
		    t215.setPadding(0);
		    String head15= new String("Index Correlation");
		    com.lowagie.text.Cell c215 = new com.lowagie.text.Cell(head15);
		    c215.setHorizontalAlignment(1);
		    c215.setHeader(true);
		    t215.addCell(c215);
			document15.add(t215);
			
			String[] temp = new String[var_vec.size()]; 
			
			int count = 0;
			if(var_vec.size()!= 0)
			{
				Iterator temp1 = var_vec.iterator();
				int var_v15 = 0;
				while(temp1.hasNext())
				{
					temp1.next();
					String m = (String)temp1.next();
					Logging.debug("vector string(kap) is"+m);
					temp[var_v15] = m;
					count++;
				}
				Logging.debug("count is = "+count);
			}
			
	  	Table t15 = new Table(count+1);
	  	t15.setBackgroundColor(Color.LIGHT_GRAY);
	  	t15.setPadding(10);
	  	t15.setSpacing(1);
	  	t15.setBorderWidth(1);
	  	t15.setBorderColor(Color.GRAY);
	  	String s15 = new String();
	  	com.lowagie.text.Cell c115= new com.lowagie.text.Cell(s15);
		c115.setHeader(true);
		
		//2n table
		Table t15a = new Table(count+1);
		t15a.setBackgroundColor(Color.LIGHT_GRAY);
	  	t15a.setPadding(10);
	  	t15a.setSpacing(1);
	  	t15a.setBorderWidth(1);
	  	t15a.setBorderColor(Color.GRAY);
	  	String s15a = new String();
	  	com.lowagie.text.Cell c115a= new com.lowagie.text.Cell(s15a);
		c115.setHeader(true);
		//end 2nd table
		
		//String id15 = null;
				
				Logging.debug("size of vector "+ai.size());
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
						Logging.debug("vector string(kap) is"+m);
						var15[var_v15] = m;
						var_v15++;
					}
				}
				int var_int15 = var15.length;
				int disp15 = 1;
				int col_head15 = 1;
				String fdate15 = "From date : " + fdate;
				String tdate15 = "To date : " + tdate;
				
				//for first cell 
				c115a = new com.lowagie.text.Cell(" ");
				c115a.setHorizontalAlignment(1);
				t15a.addCell(c115a);
			
				for(int j15= 0; j15 < var15.length; j15++)
				{
					String heading15a = var15[j15];
					Logging.debug("in first for loop"+heading15a);
					try{
					if(!(var15[j15].equals(null))){
					c115a = new com.lowagie.text.Cell(heading15a);
					c115a.setHorizontalAlignment(1);
					}
					t15a.addCell(c115a);
					}catch(NullPointerException ne){
						//ne.printStackTrace();
						Logging.debug(ne);
						}
					//else{break;}
					
				}
								
						String id15 = null;
						if(ai.size()!= 0)
						{							
							Iterator it15 = ai.iterator();
							while(it15.hasNext())
							{
								//it15.next();
									id15=(String)it15.next();
									Logging.debug("in 3rd for loop"+id15);
									c115 = new com.lowagie.text.Cell(id15);
									c115.setHorizontalAlignment(1);
									t15.addCell(c115);
							}
				
							
				}
				
				document15.add(t15a);		
				document15.add(t15);
				document15.close();
				 break;
				}//end of switch	
				
		
		}catch(Exception e)
		{
			Logging.debug("Error prevented the file from being created."+e);
		}finally{
			
		}// end of finaly
			
	
	}//end of method create file
	
}

			