/*
 * Created on 9th June, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */ 
package harrier.income.com.report; 

import java.awt.Color;
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

import java.io.*;
import java.util.Hashtable;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException; 
import java.io.OutputStreamWriter;

import org.apache.log4j.Logger;
import org.jfree.chart.demo.servlet.AdjustDecimal;
import org.jfree.chart.demo.servlet.ComposeIndex;

import com.harrier.initializeation.ConnectInit;
import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

import sysconfig.model.indexMovement;
import sysconfig.model.stockDetailFromDate;
/**
 * @author neha
 * 
 *  Export to xml for batch reports at a stroke
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MakeXmlAll{
	Logger Logging = Logger.getLogger(MakeXmlAll.class);
	/**
	 * create_file is used to create the xml from the report.
	 * It uses the vector passed by the report page to create xml 
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
	static int intRep=0;
	String pathf=null;
	StringBuffer sb;
	StringBuffer sb1;
	 BufferedWriter bufferedWriter;
	
	 public MakeXmlAll(){
	try{ 
	 sb= new StringBuffer();
	 sb1= new StringBuffer();
	sb.append("<?xml version='1.0' encoding='UTF-8'?>");
	sb.append("<Batch_Report>"); 
	 pathf = Connect.getCoolMenuspath();
	 pathf = pathf + "CoolMenus/" + "BatchXml.xml"; 
	 bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathf)));
	}
	catch(Exception e)
	{
		
	}
	 }
	
		
	public void create_file2(Vector var_vec,int switch_code,Vector ai,String fdate, String tdate)
	{ 
		Vector vec=ai;
		Logging.debug("vec for ohlc"+vec);
	//	org.jfree.chart.demo.servlet.ComposeIndex ci=new org.jfree.chart.demo.servlet.ComposeIndex();
		ComposeIndex ci=ConnectInit.getComposeIndex();
		try
		{  
			
			switch(switch_code)
			{
				case 10:
					
					sb.append("<Report10>");
					sb.append("<Report-name>"+"IndexCompareOHLC"+"</Report-name>");
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
					
					for(int k= 0; k < var.length; k++)
					{
						 int varint10 = Integer.parseInt(var[k]);
						 String head = ci.get_index_name(varint10);
						 sb.append("<Index>");
						 sb.append("<index-name>"+"<![CDATA["+head+"]]>"+"</index-name>");
						 String id10 = null;
					
					if(ai.size()!= 0)
					{
						Iterator it = ai.iterator();
						while(it.hasNext())
						{
							for(int j = 0;j < (var.length * 5); j++)
							{
								if(j != 0 && j%5 == 0)
								{
									j = j + 1;
								}
								if(it.hasNext())
								{	
									id10=(String)it.next();
									try{
										if(j ==0){	
									          String date =id10;
									          sb.append("<date>"+date+"</date>");
									          sb1.append(date);
									          int rr=date.trim().length();
									           int jj=90;
									           int kk=jj-rr;
									         for(int g=0;g<kk;g++){sb1.append(" ");}	
									        }
										 else if(j ==1 && k==0){	
									          String open =id10;
									          sb.append("<open>"+open+"</open>");
									          sb1.append(open);
									          int rr=open.trim().length();
									           int jj=20;
									           int kk=jj-rr;
									         for(int g=0;g<kk;g++){sb1.append(" ");}	
									        }
									   else  if(j==2 && k==0){
									         String high = id10;
									
										     sb.append("<high>"+high+"</high>");
										     sb1.append(high);
										     int rr=high.trim().length();
										     int jj=20;
										     int kk=jj-rr;
										     for(int g=0;g<kk;g++){sb1.append(" ");}
									        }
									   else if(j==3 && k==0){
										     String low = id10;
											 sb.append("<low>"+low+"</low>");
										     sb1.append(low);
										     int rr=low.trim().length();
										     int jj=20;
										     int kk=jj-rr;
										     for(int g=0;g<kk;g++){sb1.append(" ");}
										   
									       }
									   else if(j ==4 && k==0){
										   String  close=id10;
									    	 sb.append("<close>"+close+"</close>");
									    	 sb1.append(close);
									    	 
									        }
									   else if(j==5 && k==1){
									        String open =id10;
								            sb.append("<open>"+open+"</open>");
								            sb1.append(open);
								             int rr=open.trim().length();
								             int jj=20;
								             int kk=jj-rr;
								            for(int g=0;g<kk;g++){sb1.append(" ");}	
									   }
									   else if(j==6 && k==1){
									   	  String high = id10;
										  sb.append("<high>"+high+"</high>");
										  sb1.append(high);
										  int rr=high.trim().length();
										  int jj=20;
										  int kk=jj-rr;
										   for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									   else if(j==7 && k==1){
									   	  String low = id10;
										  sb.append("<low>"+low+"</low>");
									      sb1.append(low);
									      int rr=low.trim().length();
									      int jj=20;
									      int kk=jj-rr;
									      for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									  else if(j==8 && k==1){
									  	  String  close=id10;
								    	  sb.append("<close>"+close+"</close>");
								    	  sb1.append(close);
									   }
									  
									}
									catch(Exception e){
										Logging.error(" Error : "+e.getMessage());
									       }
								
								}
							}
						
						}
				}//end of if
					sb.append("</Index>");
					}	
					
					sb.append("</Report10>");	
			break;
				case 15:
					
					sb.append("<Report11>");
					sb.append("<Report-name>"+"IndexCorrelation"+"</Report-name>");
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
					int counter=(var15.length/2);
					for(int k= 0,t1=0; k < counter ; k++,t1++)
					{
						
						Logging.debug("temp1"+t1);
						String head = var15[k];
						Logging.debug("head1"+head);
						sb.append("<Correlation>");
						 sb.append("<index-name>"+"<![CDATA["+head+"]]>"+"</index-name>");
					
					String id15,index = null;
					
					if(ai.size()!= 0)
					{
						Iterator it = ai.iterator();
						while(it.hasNext())
						{    
							
							for(int t = 0; t < (counter+1); t++)
							{
								
								
								if(it.hasNext())
								{
								id15=(String)it.next();
								try{
									if(t ==0){	
								           index =id15;
								          if(!( index.equalsIgnoreCase(head)))
								          {
								           sb.append("<index-correlated>"+"<![CDATA["+index+"]]>"+"</index-correlated>");
								           sb1.append(index);
								            int rr=index.trim().length();
								            int jj=90;
								            int kk=jj-rr;
								            for(int g=0;g<kk;g++){sb1.append(" ");}
								          }
								        }
									  if( t==(t1+1) && k==t1 ){	
									  	 if(!( index.equalsIgnoreCase(head))) 
									  	 { 	
								          String value =id15;
								          sb.append("<value>"+value+"</value>");
								          sb1.append(value);
									  	  }
								       }
								   
								  }
								   catch(Exception e){
									Logging.error(" Error : "+e.getMessage());
								       }
								}
								
							}
							
						}
				    }
					sb.append("</Correlation>");
					}
					sb.append("</Report11>");	
			break;
			}
	
		}catch(Exception e)
		{
				Logging.debug("Error prevented the file from being created."+e);
		}
		

	}//end of func
	
	
	
	public void create_file1(String[] arr,int switch_code,Vector vi,String fdate, String tdate)
	{ 
		
		Vector vec=vi;
		Logging.debug("Vector new====>"+vec);
//		org.jfree.chart.demo.servlet.ComposeIndex ci=new org.jfree.chart.demo.servlet.ComposeIndex();
		ComposeIndex ci=ConnectInit.getComposeIndex();	
		try
		{      
			
			sb.append("<Report14>");
			sb.append("<Report-name>"+"Index Return"+"</Report-name>");
			
			
			
			String id14=null;
			
			if(vi.size()!= 0)
			{
			    Iterator it = vi.iterator();
				while(it.hasNext())
				{
					
					for(int j = 0;j < 3; j++)
					{
						id14=(String)it.next();
						double dob=0.0;
						try{
							if(j ==0){	
						          String indexname =id14;
						           sb.append("<index-name>"+"<![CDATA["+indexname+"]]>"+"</index-name>");
									sb1.append(indexname);
									int rr=indexname.trim().length();
									int jj=90;
									int kk=jj-rr;
									for(int g=0;g<kk;g++){sb1.append(" ");}
						        }
						   if(j==1){
						         String preturn = id14;
						
							     sb.append("<Periodic-Returns>"+preturn+"</Periodic-Returns>");
							     sb1.append(preturn);
							     int rr=preturn.trim().length();
							     int jj=20;
							     int kk=jj-rr;
							     for(int g=0;g<kk;g++){sb1.append(" ");}
						        }
						   if(j==2){
							     String vreturn = id14;
								 sb.append("<Volatility-of-Returns>"+vreturn+"</Volatility-of-Returns>");
							     sb1.append(vreturn);
							   
						       }
							
						}
						catch(Exception e){
							 Logging.error(" Error : "+e.getMessage());
						}
					}					
					
				}
			}
			
			sb.append("</Report14>");

		}catch(Exception e)
		{
				Logging.debug("Error prevented the file from being created."+e);
		}
		
	}//end of func
	
	
	public void create_file(String var,int switch_code,String iename, Vector ai,String fdate, String tdate, String var1)
	{ 
		
		Vector vec=ai;
		
//		org.jfree.chart.demo.servlet.ComposeIndex ci=new org.jfree.chart.demo.servlet.ComposeIndex();
		ComposeIndex ci=ConnectInit.getComposeIndex();	
		
		
		try
		{  
			
			
			
			 int var_inte = Integer.parseInt(var);
        		var1 = ci.get_index_name(var_inte);
				
			 switch(switch_code)
				{
				case 1: 
					sb.append("<Report1>");  
					sb.append("<Report_name>"+"Index Composition "+"</Report_name>");
					sb.append("<Index_name>"+"<![CDATA["+iename+"]]>"+"</Index_name>");
				
				String id = null;
				
			
				if(ai.size()!= 0)
				{
					Iterator it = ai.iterator();
					while(it.hasNext())
					{
						sb.append("<stock>");
						it.next();
						for(int j = 0;j<12;j++)
						{
							if(it.hasNext())
							{	
								id=(String)it.next();
								//double dob=0.0;
								try{
									
									
									
										
									if(j==0){
										String stockname=id;
										sb.append("<stock_name>"+"<![CDATA["+stockname+"]]>"+"</stock_name>");
										sb1.append(stockname);
										int rr=stockname.trim().length();
										int jj=90;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==1){
										String tis=id;
									//	tis=AdjustDecimal.ArrangeAsNumeric(tis);
										sb.append("<tis>"+tis+"</tis>");
										sb1.append(tis);
										int rr=tis.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==2){
										String iwf=id;
										//iwf=ad.indexcompose(iwf);
										//iwf=AdjustDecimal.ArrangeAsNumeric(iwf);
										sb.append("<iwf>"+iwf+"</iwf>");
										sb1.append(iwf);
										int rr=iwf.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==3){
										String market=id;
										sb.append("<market>"+market+"</market>");
										sb1.append(market);
										int rr=market.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									}
									else if(j==4){
										String ltp=id;
										//adjusted=ad.indexcompose(adjusted);
										//adjusted=AdjustDecimal.ArrangeAsNumeric(adjusted);
										sb.append("<ltp>"+ltp+"</ltp>");
										sb1.append(ltp);
										int rr=ltp.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									  }
									else if(j==5){
										String last =id;
										//last=ad.indexcompose(last);
									//	last=AdjustDecimal.ArrangeAsNumeric(last);
										sb.append("<last>"+last+"</last>");
										sb1.append(last);
										int rr=last.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									  }
									else if(j==6){
										String currency=id;
										sb.append("<currency>"+currency+"</currency>");
										sb1.append(currency);
										int rr=currency.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==7){
										String curr_exch_convIratecomp=id;
										sb.append("<curr_exch>"+curr_exch_convIratecomp+"</curr_exch>");
										sb1.append(curr_exch_convIratecomp);
										int rr=curr_exch_convIratecomp.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==8){
										String mcv=id;
										//mcv=ad.indexcompose(mcv);
										//mcv=AdjustDecimal.ArrangeAsNumeric(mcv);
										sb.append("<mcv>"+mcv+"</mcv>");
										sb1.append(mcv);
										int rr=mcv.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									  }
									else if(j==9){
										String adjusted=id;
										sb.append("<adjusted>"+adjusted+"</adjusted>");
										sb1.append(adjusted);
										int rr=adjusted.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
																
									}
									
									
									
								    else if(j==10){
									   	String strweightage1=id;
									    sb.append("<weightage>"+strweightage1+"</weightage>");
										 sb1.append(strweightage1);
										  int rr=strweightage1.trim().length();
										  int jj=20;
										  int kk=jj-rr;
											for(int g=0;g<kk;g++){sb1.append(" ");}
								    }
								    
								    else if(j==11){
								    	String  date=id;
								    	 sb.append("<date>"+date+"</date>");
								    	 sb1.append(date);
										
										 
									  }
									
									
									
									
								} catch(Exception e){
									
									Logging.error(" Error : "+e.getMessage());
								    }
								
							}
						}
						//count = count + 1;
						sb.append("</stock>");	
					}
					
				}// end of if
				
				sb.append("</Report1>");
				 Logging.debug("nehabuffffer11 ====>"+sb.toString());
				 break;
                
				 
				case 2: 
					sb.append("<Report2>");    
					sb.append("<Report_name>"+"Company Wise Weightage "+"</Report_name>");
					sb.append("<Index_name>"+"<![CDATA["+iename+"]]>"+"</Index_name>");
					
				id=null;
				if(ai.size()!= 0)
				{
					Iterator it = ai.iterator();
					while(it.hasNext())
					{
				
						sb.append("<stock>");
						for(int j = 0;j<3;j++)
						{
							if(it.hasNext())
							{	
								id=(String)it.next();
								//double dob=0.0;
								try{
									
									
									
										
									if(j==0){
										String company=id;
										sb.append("<company>"+"<![CDATA["+company+"]]>"+"</company>");
										sb1.append(company);
										int rr=company.trim().length();
										int jj=90;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==1){
										String marketcap=id;
									//	tis=AdjustDecimal.ArrangeAsNumeric(tis);
										sb.append("<market_cap>"+marketcap+"</market_cap>");
										sb1.append(marketcap);
										int rr=marketcap.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									
								    else if(j==2){
									   	String strweightage1=id;
									    sb.append("<weightage>"+strweightage1+"</weightage>");
										 sb1.append(strweightage1);
										  int rr=strweightage1.trim().length();
										  int jj=20;
										  int kk=jj-rr;
											for(int g=0;g<kk;g++){sb1.append(" ");}
								    }
								    
								   
									
								} catch(Exception e){
									
									Logging.error(" Error : "+e.getMessage());
								    }
								
							}
						}
						//count = count + 1;
						sb.append("</stock>");	
					}
					
				}// end of if
				
				sb.append("</Report2>");
				 break; 
				 
				case 3: 
					sb.append("<Report3>");    
					sb.append("<Report_name>"+"Industry Wise Weightage"+"</Report_name>");
					sb.append("<Index_name>"+"<![CDATA["+iename+"]]>"+"</Index_name>");
					
				
				id=null;
				//Logging.debug("size of vector "+ai.size());
				if(ai.size()!= 0)
				{
					Iterator it = ai.iterator();
					while(it.hasNext())
					{
				
						sb.append("<stock>");
						for(int j = 0;j<3;j++)
						{
							if(it.hasNext())
							{	
								id=(String)it.next();
								//double dob=0.0;
								try{
									
									
									
										
									if(j==0){
										String industryname=id;
										sb.append("<industry_name>"+"<![CDATA["+industryname+"]]>"+"</industry_name>");
										sb1.append(industryname);
										int rr=industryname.trim().length();
										int jj=90;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==1){
										String marketcap=id;
									//	tis=AdjustDecimal.ArrangeAsNumeric(tis);
										sb.append("<market_cap>"+marketcap+"</market_cap>");
										sb1.append(marketcap);
										int rr=marketcap.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									
								    else if(j==2){
									   	String strweightage1=id;
									    sb.append("<weightage>"+strweightage1+"</weightage>");
										 sb1.append(strweightage1);
										  int rr=strweightage1.trim().length();
										  int jj=20;
										  int kk=jj-rr;
											for(int g=0;g<kk;g++){sb1.append(" ");}
								    }
								    
								   
									
								} catch(Exception e){
									
									Logging.error(" Error : "+e.getMessage());
								    }
								
							}
						}
						//count = count + 1;
						sb.append("</stock>");	
					}
					
				}// end of if
				
				sb.append("</Report3>");
				 break; 
				 
				case 5: 
					sb.append("<Report5>");  
					sb.append("<Report_name>"+"Stock Contribution"+"</Report_name>");
					sb.append("<Index_name>"+"<![CDATA["+iename+"]]>"+"</Index_name>");
					
				
				
				
				//String id = null;
				id=null;
				//Logging.debug("size of vector "+ai.size());
				if(ai.size()!= 0)
				{
					Iterator it = ai.iterator();
					while(it.hasNext())
					{
				
						sb.append("<stock>");
						for(int j = 0;j<4;j++)
						{
							if(it.hasNext())
							{	
								id=(String)it.next();
								//double dob=0.0;
								try{
									
									
									
										
									if(j==0){
										String stockname=id;
										sb.append("<stock_name>"+"<![CDATA["+stockname+"]]>"+"</stock_name>");
										sb1.append(stockname);
										int rr=stockname.trim().length();
										int jj=90;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==1){
										String indexmarket=id;
									//	tis=AdjustDecimal.ArrangeAsNumeric(tis);
										sb.append("<index_market>"+indexmarket+"</index_market>");
										sb1.append(indexmarket);
										int rr=indexmarket.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==2){
										String stockmarket=id;
										//iwf=ad.indexcompose(iwf);
										//iwf=AdjustDecimal.ArrangeAsNumeric(iwf);
										sb.append("<stock_market>"+stockmarket+"</stock_market>");
										sb1.append(stockmarket);
										int rr=stockmarket.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									
								    else if(j==3){
									   	String strweightage1=id;
									    sb.append("<weightage>"+strweightage1+"</weightage>");
										 sb1.append(strweightage1);
										  int rr=strweightage1.trim().length();
										  int jj=20;
										  int kk=jj-rr;
											for(int g=0;g<kk;g++){sb1.append(" ");}
								    }
								    
								    
									
									
									
									
								} catch(Exception e){
									
									Logging.error(" Error : "+e.getMessage());
								    }
								
							}
						}
						//count = count + 1;
						sb.append("</stock>");	
					}
					
				}// end of if
				
				sb.append("</Report5>");
				 break;
				case 19: 
					sb.append("<Report19>");   
					sb.append("<Report_name>"+"Index Divisor "+"</Report_name>");
					sb.append("<Index_name19>"+"<![CDATA["+iename+"]]>"+"</Index_name19>");
					
					
					
					
					
					
					String id19 = null;
					
					if(ai.size()!= 0)
					{
						Iterator it = ai.iterator();
						while(it.hasNext())
						{
							sb.append("<stock19>");
							for(int j=0;j<4;j++)
							{
								if(it.hasNext())
								{	
									id19=(String)it.next();
									//double dob=0.0;
						    try{
										
							   if(j ==0){	
							          String todate =id19;
							          sb.append("<trading_date19>"+todate+"</trading_date19>");
							          sb1.append(todate);
							          int rr=todate.trim().length();
							           int jj=90;
							           int kk=jj-rr;
							         for(int g=0;g<kk;g++){sb1.append(" ");}	
							        }
							   if(j==1){
							         String close = id19;
							
								     sb.append("<close19>"+close+"</close19>");
								     sb1.append(close);
								     int rr=close.trim().length();
								     int jj=20;
								     int kk=jj-rr;
								     for(int g=0;g<kk;g++){sb1.append(" ");}
							        }
							   if(j==2){
								     String mcap = id19;
									 sb.append("<mcap19>"+mcap+"</mcap19>");
								     sb1.append(mcap);
								     int rr=mcap.trim().length();
								     int jj=20;
								     int kk=jj-rr;
								     for(int g=0;g<kk;g++){sb1.append(" ");}
								   
							       }
							   if(j ==3){
								   String  divisor=id19;
							    	 sb.append("<divisor19>"+divisor+"</divisor19>");
							    	 sb1.append(divisor);
							        }
						} catch(Exception e){
							
							    Logging.error(" Error : "+e.getMessage());
						        }
						
					}//end of  it.has if	
							
							}// end of for	
							sb.append("</stock19>");	
						}
						
					}
					sb.append("</Report19>");
					 Logging.debug("nehabuffffer12 ====>"+sb.toString());
					break;
				case 20:
					sb.append("<Report20>");  
					sb.append("<Report_name>"+"Index Wise Pe/Pb "+"</Report_name>");
					sb.append("<Index_name>"+"<![CDATA["+iename+"]]>"+"</Index_name>");
					
					        
					     
							
							int index_id=Integer.parseInt(var);
							
						
								
							  String id20 = null;
							
								
								if(ai.size()!= 0)
								{
									Iterator it = ai.iterator();
									while(it.hasNext())
									{
								
										sb.append("<stock>");
										for(int j =0;j<9;j++)
										{
											if(it.hasNext())
											{	
												id20=(String)it.next();
												
												try{
													if(j==0){
														String t1date=id20;
														sb.append("<trading_date>"+t1date+"</trading_date>");
														sb1.append(t1date);
														int rr=t1date.trim().length();
														int jj=90;
														int kk=jj-rr;
														for(int g=0;g<kk;g++){sb1.append(" ");}
													   }
													else if(j==1){
														String close=id20;
													
														sb.append("<close>"+close+"</close>");
														sb1.append(close);
														int rr=close.trim().length();
														int jj=20;
														int kk=jj-rr;
														for(int g=0;g<kk;g++){sb1.append(" ");}
													   }
													else if(j==2){
														String pchange=id20;
														
														sb.append("<percent_change>"+pchange+"</percent_change>");
														sb1.append(pchange);
														int rr=pchange.trim().length();
														int jj=20;
														int kk=jj-rr;
														for(int g=0;g<kk;g++){sb1.append(" ");}
													   }
													else if(j==3){
														String mcap=id20;
														sb.append("<market_cap>"+mcap+"</market_cap>");
														sb1.append(mcap);
														int rr=mcap.trim().length();
														int jj=20;
														int kk=jj-rr;
														for(int g=0;g<kk;g++){sb1.append(" ");}
													}
													else if(j==4){
														String shtr=id20;
														sb.append("<share_traded>"+shtr+"</share_traded>");
														sb1.append(shtr);
														int rr=shtr.trim().length();
														int jj=20;
														int kk=jj-rr;
														for(int g=0;g<kk;g++){sb1.append(" ");}
													  }
													else if(j==5){
														String trnovr =id20;
														sb.append("<turnover>"+trnovr+"</turnover>");
														sb1.append(trnovr);
														int rr=trnovr.trim().length();
														int jj=20;
														int kk=jj-rr;
														for(int g=0;g<kk;g++){sb1.append(" ");}
													  }
													else if(j==6){
														String perat=id20;
														sb.append("<PE_Ratio>"+perat+"</PE_Ratio>");
														sb1.append(perat);
														int rr=perat.trim().length();
														int jj=20;
														int kk=jj-rr;
														for(int g=0;g<kk;g++){sb1.append(" ");}
													   }
													else if(j==7){
														String pbrat=id20;
														sb.append("<pb_ratio>"+pbrat+"</pb_ratio>");
														sb1.append(pbrat);
														int rr=pbrat.trim().length();
														int jj=20;
														int kk=jj-rr;
														for(int g=0;g<kk;g++){sb1.append(" ");}
													   }
													else if(j==8){
														String dividend=id20;
														sb.append("<dividend>"+dividend+"</dividend>");
														sb1.append(dividend);
														
													  }
													
												} catch(Exception e){
													 Logging.error(" Error : "+e.getMessage());
												     }
											}
										}//for
										sb.append("</stock>");
									}
										
								}
								
									sb.append("</Report20>"); 
								
								break;
						
				case 22:
					sb.append("<Report22>");   
					sb.append("<Report_name>"+"Stock Dividend "+"</Report_name>");
					sb.append("<Index_name>"+"<![CDATA["+iename+"]]>"+"</Index_name>");
					
				       
						
						int index_id22=Integer.parseInt(var);
						
					
							
						  String id22 = null;
						
							
							if(ai.size()!= 0)
							{
								Iterator it = ai.iterator();
								while(it.hasNext())
								{
									sb.append("<stock>");
									it.next();
									for(int j =0;j<6;j++)
									{
										if(it.hasNext())
										{	
											id22=(String)it.next();
											
											try{
												if(j==0){
													String stockname=id22;
													sb.append("<stock_name>"+"<![CDATA["+stockname+"]]>"+"</stock_name>");
													sb1.append(stockname);
													int rr=stockname.trim().length();
													int jj=90;
													int kk=jj-rr;
													for(int g=0;g<kk;g++){sb1.append(" ");}
												   }
												if(j==1){
													String facevalue=id22;
													sb.append("<face_value>"+facevalue+"</face_value>");
													sb1.append(facevalue);
													int rr=facevalue.trim().length();
													int jj=20;
													int kk=jj-rr;
													for(int g=0;g<kk;g++){sb1.append(" ");}
												   }
												else if(j==2){
													String tis=id22;
												//	tis=AdjustDecimal.ArrangeAsNumeric(tis);
													sb.append("<tis>"+tis+"</tis>");
													sb1.append(tis);
													int rr=tis.trim().length();
													int jj=20;
													int kk=jj-rr;
													for(int g=0;g<kk;g++){sb1.append(" ");}
												   }
												
												else if(j==3){
													String mcv=id22;
													//mcv=ad.indexcompose(mcv);
													//mcv=AdjustDecimal.ArrangeAsNumeric(mcv);
													sb.append("<mcv>"+mcv+"</mcv>");
													sb1.append(mcv);
													int rr=mcv.trim().length();
													int jj=20;
													int kk=jj-rr;
													for(int g=0;g<kk;g++){sb1.append(" ");}
												  }
												else if(j==4){
													String dividend=id22;
													sb.append("<dividend>"+dividend+"</dividend>");
													sb1.append(dividend);
													
												  }
												
											    else if(j==5){
											    	String  date=id22;
											    	 sb.append("<applied_date>"+date+"</applied_date>");
											    	 sb1.append(date);
													
													 
												  }
												
													
												  
												
											} catch(Exception e){
												 Logging.error(" Error : "+e.getMessage());
											     }
										}
									}//for
									sb.append("</stock>");
								}
									
							}
							sb.append("</Report22>");
							break;
				case 16: 
					sb.append("<Report16>");  
					sb.append("<Report_name>"+"Capital Change To Universe "+"</Report_name>");
					sb.append("<Index_name>"+"<![CDATA["+iename+"]]>"+"</Index_name>");
					
				
				
				
				String id16 = null;
				
				//Logging.debug("size of vector "+ai.size());
				if(ai.size()!= 0)
				{
					Iterator it = ai.iterator();
					while(it.hasNext())
					{
						sb.append("<stock>");
						it.next();
						for(int j = 0;j<7;j++)
						{
							if(it.hasNext())
							{	
								id16=(String)it.next();
								//double dob=0.0;
								try{
									
									
									
										
									if(j==0){
										String stockname=id16;
										
										sb.append("<stock_name>"+"<![CDATA["+stockname+"]]>"+"</stock_name>");
										sb1.append(stockname);
										int rr=stockname.trim().length();
										int jj=90;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==1){
										String facevalue=id16;
									//	tis=AdjustDecimal.ArrangeAsNumeric(tis);
										sb.append("<face_value>"+facevalue+"</face_value>");
										sb1.append(facevalue);
										int rr=facevalue.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==2){
										String tis=id16;
									//	tis=AdjustDecimal.ArrangeAsNumeric(tis);
										sb.append("<tis>"+tis+"</tis>");
										sb1.append(tis);
										int rr=tis.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==3){
										String mcv=id16;
										//mcv=ad.indexcompose(mcv);
										//mcv=AdjustDecimal.ArrangeAsNumeric(mcv);
										sb.append("<mcv>"+mcv+"</mcv>");
										sb1.append(mcv);
										int rr=mcv.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									  }
									else if(j==4){
										String iwf=id16;
										//iwf=ad.indexcompose(iwf);
										//iwf=AdjustDecimal.ArrangeAsNumeric(iwf);
										sb.append("<iwf>"+iwf+"</iwf>");
										sb1.append(iwf);
										int rr=iwf.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==5){
										String corpaction=id16;
										sb.append("<corp_action>"+corpaction+"</corp_action>");
										sb1.append(corpaction);
										int rr=corpaction.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									}
									
								    else if(j==6){
								    	String  applied_date=id16;
								    	 sb.append("<applied_date>"+applied_date+"</applied_date>");
								    	 sb1.append(applied_date);
										
										 
									  }
									
									
									
									
								} catch(Exception e){
									
									Logging.error(" Error : "+e.getMessage());
								    }
								
							}
						}
						//count = count + 1;
						sb.append("</stock>");	
					}
					
				}// end of if
				
				sb.append("</Report16>");
				 break;
				 
			case 6: 
					
				sb.append("<Report6>"); 
				sb.append("<Report_name>"+"Stock Details"+"</Report_name>");
				sb.append("<Index_name>"+"<![CDATA["+iename+"]]>"+"</Index_name>");
				
			
				
				String id6 = null;
				
				if(ai.size()!= 0)
				{
					Iterator it = ai.iterator();
					while(it.hasNext())
					{
						sb.append("<stock>");
						//it.next();
						for(int j = 0;j<10;j++)
						{
							if(it.hasNext())
							{	
								id6=(String)it.next();
								//double dob=0.0;
								try{
										
									if(j==0){
										String stockname=id6;
										
										sb.append("<stock_name>"+"<![CDATA["+stockname+"]]>"+"</stock_name>");
										sb1.append(stockname);
										int rr=stockname.trim().length();
										int jj=90;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==1){
										String openvalue=id6;
									//	tis=AdjustDecimal.ArrangeAsNumeric(tis);
										sb.append("<open_value>"+openvalue+"</open_value>");
										sb1.append(openvalue);
										int rr=openvalue.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==2){
										String closevalue=id6;
									//	tis=AdjustDecimal.ArrangeAsNumeric(tis);
										sb.append("<close_value>"+closevalue+"</close_value>");
										sb1.append(closevalue);
										int rr=closevalue.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==3){
										String lowvalue=id6;
									//	tis=AdjustDecimal.ArrangeAsNumeric(tis);
										sb.append("<low_value>"+lowvalue+"</low_value>");
										sb1.append(lowvalue);
										int rr=lowvalue.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==4){
										String highvalue=id6;
									//	tis=AdjustDecimal.ArrangeAsNumeric(tis);
										sb.append("<high_value>"+highvalue+"</high_value>");
										sb1.append(highvalue);
										int rr=highvalue.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==5){
										String tradedvalue=id6;
									//	tis=AdjustDecimal.ArrangeAsNumeric(tis);
										sb.append("<traded_value>"+tradedvalue+"</traded_value>");
										sb1.append(tradedvalue);
										int rr=tradedvalue.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==6){
										String tradedvol=id6;
									}
									else if(j==7){
										String mcv=id6;
										//mcv=ad.indexcompose(mcv);
										//mcv=AdjustDecimal.ArrangeAsNumeric(mcv);
										sb.append("<mcv>"+mcv+"</mcv>");
										sb1.append(mcv);
										int rr=mcv.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									  }
									else if(j==8){
										String nooftrades=id6;
										//iwf=ad.indexcompose(iwf);
										//iwf=AdjustDecimal.ArrangeAsNumeric(iwf);
										sb.append("<no_of_trades>"+nooftrades+"</no_of_trades>");
										sb1.append(nooftrades);
										int rr=nooftrades.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									
									
								    else if(j==9){
								    	String  date=id6;
								    	 sb.append("<date>"+date+"</date>");
								    	 sb1.append(date);
										
										 
									  }
									
									
									
									
								} catch(Exception e){
									
									Logging.error(" Error : "+e.getMessage());
								    }
								
							}
						}
						//count = count + 1;
						sb.append("</stock>");
					}
					
				}// end of if
				
				sb.append("</Report6>");
				 break;	
				 
			case 25:
				sb.append("<Report25>");  
				sb.append("<Report_name>"+"Traded Volume"+"</Report_name>");
				sb.append("<Index_name>"+"<![CDATA["+iename+"]]>"+"</Index_name>");
				
			        
			      
					
					//int index_id=Integer.parseInt(var);
					
				
						
					  String id25 = null;
					
						
						if(ai.size()!= 0)
						{
							Iterator it = ai.iterator();
							while(it.hasNext())
							{
								sb.append("<stock>");
								it.next();
								for(int j =0;j<2;j++)
								{
									if(it.hasNext())
									{	
										id25=(String)it.next();
										
										try{
											if(j==0){
												String stockname=id25;
												sb.append("<stock_name>"+"<![CDATA["+stockname+"]]>"+"</stock_name>");
												sb1.append(stockname);
												int rr=stockname.trim().length();
												int jj=90;
												int kk=jj-rr;
												for(int g=0;g<kk;g++){sb1.append(" ");}
											   }
											else if(j==1){
												String trd_vol=id25;
											
												sb.append("<traded_volume>"+trd_vol+"</traded_volume>");
												sb1.append(trd_vol);
												int rr=trd_vol.trim().length();
												int jj=20;
												int kk=jj-rr;
												for(int g=0;g<kk;g++){sb1.append(" ");}
											   }
											
											
											
											
										} catch(Exception e){
											 Logging.error(" Error : "+e.getMessage());
										}
									}
								}//for
								sb.append("</stock>");
							}
								
						}
						sb.append("</Report25>");
						break;
				
				}//end of switch	
								
		}catch(Exception e)
		{
			//Logging.debug("Error prevented the file from being created."+e);
		}
	
	}
	
	public void buffclose(){
		try{
			sb.append("</Batch_Report>"); 
			 bufferedWriter.write(sb.toString());
			 Logging.debug("nehabuffffer ====>"+sb.toString());
			bufferedWriter.flush();
		bufferedWriter.close();
		}catch(Exception e){
			
		}
	  }
}

			