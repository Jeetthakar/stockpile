/*
 * Created on Feb 21, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */ 
package harrier.income.com.report;  
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.jfree.chart.demo.servlet.AdjustDecimal;
import org.jfree.chart.demo.servlet.ComposeIndex;

import app.Connect;

import com.harrier.initializeation.ConnectInit;
/**
 * @author neha
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MakeXmlNew{
	Logger Logging = Logger.getLogger(MakeXmlNew.class);
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
	
	
	static int intRep=0;
	String pathf=null;
	
	
	public void create_file(String var,int switch_code,String iename, Vector ai,String fdate, String tdate, String var1)
	{ 
		
		Vector vec=ai;
		Logging.debug("Vector====>"+vec);
		Logging.debug("iename====>"+iename);
	//	org.jfree.chart.demo.servlet.ComposeIndex ci=new org.jfree.chart.demo.servlet.ComposeIndex();
		ComposeIndex ci=ConnectInit.getComposeIndex();
		
		StringBuffer sb= new StringBuffer();
		StringBuffer sb1= new StringBuffer();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		
		try
		{  
			 pathf = Connect.getCoolMenuspath();
			
			 switch(switch_code)
				{
				case 1: 
					sb.append("<Index-Report>");
					sb.append("<Report-name>"+"IndexComposition"+"</Report-name>");
					sb.append("<Index-id>"+iename+"</Index-id>");
					
					
				pathf = pathf + "CoolMenus/" + "IndexCompositionReport.xml";
				
				
				
				String id = null;
				
				Logging.debug("size of vector "+ai.size());
				if(ai.size()!= 0)
				{
					Iterator it = ai.iterator();
					while(it.hasNext())
					{
				
						it.next();
						sb.append("<stock>");
						for(int j = 0;j<12;j++)
						{
							if(it.hasNext())
							{	
								id=(String)it.next();
							
								try{
									
									
									
										
									if(j==0){
										String stockname=id;
										
										
										sb.append("<stock-name>"+"<![CDATA["+stockname+"]]>"+"</stock-name>");

										sb1.append(stockname);
										int rr=stockname.trim().length();
										int jj=90;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==1){
										String tis=id;
									
										sb.append("<tis>"+tis+"</tis>");
										sb1.append(tis);
										int rr=tis.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==2){
										String iwf=id;
										
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
										
										sb.append("<ltp>"+ltp+"</ltp>");
										sb1.append(ltp);
										int rr=ltp.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									  }
									else if(j==5){
										String last =id;
									
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
						sb.append("</stock>");
					}
					
				}// end of if
				
				sb.append("</Index-Report>");
				 break;
				case 8:
					pathf = pathf + "CoolMenus/" + "LatestIndexDivisor.xml";
					sb.append("<Index-Report>");
					sb.append("<Report-name>"+"LatestIndexDivisor"+"</Report-name>");
					String id8 = null;
					
					if(ai.size()!= 0)
					{
					    Iterator it = ai.iterator();
						while(it.hasNext())
						{
							it.next(); 
							sb.append("<Index>");
							for(int j = 0;j<2;j++)
							{
								if(it.hasNext())
								{	
									
									id8=(String)it.next();
									
									try{
										if(j==0){
											String indexname=id8;
											sb.append("<index-name>"+"<![CDATA["+indexname+"]]>"+"</index-name>");
											sb1.append(indexname);
											int rr=indexname.trim().length();
											int jj=90;
											int kk=jj-rr;
											for(int g=0;g<kk;g++){sb1.append(" ");}
										   }
										else if(j ==1){
											   String  divisor=id8;
										    	 sb.append("<divisor>"+divisor+"</divisor>");
										    	 sb1.append(divisor);
										        }
										
									    }   catch(Exception e){
									    	  Logging.error(" Error : "+e.getMessage());
									           }
									
								}
							}
							sb.append("</Index>");
						}
					}
					sb.append("</Index-Report>");
					break;
				case 19: 
					pathf = pathf + "CoolMenus/" + "IndexDivisor.xml";
					sb.append("<Index-Report>");
					sb.append("<Report-name>"+" IndexDivisor"+"</Report-name>");
					sb.append("<Index-id>"+iename+"</Index-id>");
					
					String id19 = null;
					
					if(ai.size()!= 0)
					{
						Iterator it = ai.iterator();
						while(it.hasNext())
						{
							for(int j=0;j<4;j++)
							{
								if(it.hasNext())
								{	
									id19=(String)it.next();
									
						    try{
										
							   if(j ==0){	
							          String todate =id19;
							          sb.append("<trading-date>"+todate+"</trading-date>");
							          sb1.append(todate);
							          int rr=todate.trim().length();
							           int jj=90;
							           int kk=jj-rr;
							         for(int g=0;g<kk;g++){sb1.append(" ");}	
							        }
							   if(j==1){
							         String close = id19;
							
								     sb.append("<close>"+close+"</close>");
								     sb1.append(close);
								     int rr=close.trim().length();
								     int jj=20;
								     int kk=jj-rr;
								     for(int g=0;g<kk;g++){sb1.append(" ");}
							        }
							   if(j==2){
								     String mcap = id19;
									 sb.append("<mcap>"+mcap+"</mcap>");
								     sb1.append(mcap);
								     int rr=mcap.trim().length();
								     int jj=20;
								     int kk=jj-rr;
								     for(int g=0;g<kk;g++){sb1.append(" ");}
								   
							       }
							   if(j ==3){
								   String  divisor=id19;
							    	 sb.append("<divisor>"+divisor+"</divisor>");
							    	 sb1.append(divisor);
							        }
						} catch(Exception e){
							
							    Logging.error(" Error : "+e.getMessage());
						        }
						
					}//end of  it.has if	
							
							}// end of for	
								
						}
						
					}
					 
					
					 sb.append("</Index-Report>");
					 
					 break;
					
						
				case 20:
					       pathf = pathf + "CoolMenus/" + "IndexPe_Pb.xml";
					       sb.append("<Index-Report>");
					       sb.append("<Report-name>"+"IndexPe_Pb"+"</Report-name>");
						  
							int index_id=Integer.parseInt(var);
									
								
							  String id20 = null;
							
								
								if(ai.size()!= 0)
								{
									Iterator it = ai.iterator();
									while(it.hasNext())
									{
								
										 
										for(int j =0;j<9;j++)
										{
											if(it.hasNext())
											{	
												id20=(String)it.next();
												
												try{
													if(j==0){
														String t1date=id20;
														sb.append("<trading-date>"+t1date+"</trading-date>");
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
														
														sb.append("<percent-change>"+pchange+"</percent-change>");
														sb1.append(pchange);
														int rr=pchange.trim().length();
														int jj=20;
														int kk=jj-rr;
														for(int g=0;g<kk;g++){sb1.append(" ");}
													   }
													else if(j==3){
														String mcap=id20;
														sb.append("<market-cap>"+mcap+"</market-cap>");
														sb1.append(mcap);
														int rr=mcap.trim().length();
														int jj=20;
														int kk=jj-rr;
														for(int g=0;g<kk;g++){sb1.append(" ");}
													}
													else if(j==4){
														String shtr=id20;
														sb.append("<share-traded>"+shtr+"</share-traded>");
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
														sb.append("<PE-Ratio>"+perat+"</PE-Ratio>");
														sb1.append(perat);
														int rr=perat.trim().length();
														int jj=20;
														int kk=jj-rr;
														for(int g=0;g<kk;g++){sb1.append(" ");}
													   }
													else if(j==7){
														String pbrat=id20;
														sb.append("<pb-ratio>"+pbrat+"</pb-ratio>");
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
										}
										
									}
									
								}
								sb.append("</Index-Report>");
								break;
						
				case 22:
					    pathf = pathf + "CoolMenus/" + "StockDivident.xml";
					    sb.append("<Stock-Report>");
						sb.append("<Report-name>"+"StockDivident "+"</Report-name>");
                    	
				
				        
				      
						
						int index_id22=Integer.parseInt(var);
						
					
							
						  String id22 = null;
						
							
							if(ai.size()!= 0)
							{
								Iterator it = ai.iterator();
								while(it.hasNext())
								{
							
									it.next();
									sb.append("<stock>");
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
													tis=AdjustDecimal.ArrangeAsNumeric(tis);
													sb.append("<tis>"+tis+"</tis>");
													sb1.append(tis);
													int rr=tis.trim().length();
													int jj=20;
													int kk=jj-rr;
													for(int g=0;g<kk;g++){sb1.append(" ");}
												   }
												
												else if(j==3){
													String mcv=id22;
													
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
									sb.append("<stock>");
								}
								
							}
							 sb.append("</Stock-Report>");
							break;
				case 16: 
					sb.append("<Stock-Report>");
					sb.append("<Report-name>"+" CapitalChangeToUniverse"+"</Report-name>");
					sb.append("<Exchange-id>"+iename+"</Exchange-id>");
					
					
				pathf = pathf + "CoolMenus/" + "CapitalChangeToUniverse.xml";
						
				String id16 = null;
				
				Logging.debug("size of vector "+ai.size());
				if(ai.size()!= 0)
				{
					Iterator it = ai.iterator();
					while(it.hasNext())
					{
				
						it.next();
						sb.append("<stock>");
						for(int j = 0;j<7;j++)
						{
							if(it.hasNext())
							{	
								id16=(String)it.next();
								
								try{
									
																		
									if(j==0){
										String stockname=id16;
										sb.append("<stock-name>"+"<![CDATA["+stockname+"]]>"+"</stock-name>");

										sb1.append(stockname);
										int rr=stockname.trim().length();
										int jj=90;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==1){
										String facevalue=id16;
									    sb.append("<face-value>"+facevalue+"</face-value>");
										sb1.append(facevalue);
										int rr=facevalue.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==2){
										String tis=id16;
									    sb.append("<tis>"+tis+"</tis>");
										sb1.append(tis);
										int rr=tis.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==3){
										String mcv=id16;
										sb.append("<mcv>"+mcv+"</mcv>");
										sb1.append(mcv);
										int rr=mcv.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									  }
									else if(j==4){
										String iwf=id16;
										sb.append("<iwf>"+iwf+"</iwf>");
										sb1.append(iwf);
										int rr=iwf.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==5){
										String corpaction=id16;
										sb.append("<corp-action>"+corpaction+"</corp-action>");
										sb1.append(corpaction);
										int rr=corpaction.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									}
									
								    else if(j==6){
								    	String  applied_date=id16;
								    	 sb.append("<applied-date>"+applied_date+"</applied-date>");
								    	 sb1.append(applied_date);
										
										 
									  }
									
									
									
									
								} catch(Exception e){
									
									Logging.error(" Error : "+e.getMessage());
								    }
								
							}
						}
						sb.append("</stock>");
					}
						
				}
				
				sb.append("</Stock-Report>");
				
				 break;
				case 2: 
					sb.append("<Weightage_report>");      
					sb.append("<Index_id>"+iename+"</Index_id>");
					
					pathf = pathf + "CoolMenus/" + "CompanyWiseWeightage.xml";
				
				id=null;
				Logging.debug("size of vector "+ai.size());
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
						
						sb.append("</stock>");	
					}
					
				}// end of if
				
				  sb.append("</Weightage_report>");
				 break; 
				 
			case 3: 
				    pathf = pathf + "CoolMenus/" + "IndWiseWeightage.xml";
					sb.append("<Weightage_report>");      
					sb.append("<Index_id>"+iename+"</Index_id>");
					
				id=null;
				Logging.debug("size of vector "+ai.size());
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
						
						sb.append("</stock>");	
					}
					
				}// end of if
				
				sb.append("</Weightage_report>");
				 break; 
				 
			case 4 :
				pathf = pathf + "CoolMenus/" + "IndexList.xml";
				sb.append("<Index-Report>");
				sb.append("<Report-name>"+"IndexList"+"</Report-name>");
				String id4 = null;
				
				if(ai.size()!= 0)
				{
					Iterator it = ai.iterator();
					while(it.hasNext()) 
					{
						it.next();
						sb.append("<index>");
						for(int j=0;j<11;j++)
						{
							if(it.hasNext())
							{	
								id4=(String)it.next();
								
								try{
									if(j==0){
										String indexname=id4;
										sb.append("<index-name>"+"<![CDATA["+indexname+"]]>"+"</index-name>");
										
										
										sb1.append(indexname);
										int rr=indexname.trim().length();
										int jj=90;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									 else if(j==1){
										String value=id4;
									    sb.append("<value>"+value+"</value>");
										sb1.append(value);
										int rr=value.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									 else if(j==2){
										String open=id4;
									    sb.append("<open>"+open+"</open>");
										sb1.append(open);
										int rr=open.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==3){
										String high=id4;
										sb.append("<high>"+high+"</high>");
										sb1.append(high);
										int rr=high.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									  }
									else if(j==4){
										String low=id4;
										sb.append("<low>"+low+"</low>");
										sb1.append(low);
										int rr=low.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==5){
										String lastclose=id4;
										sb.append("<last-close>"+lastclose+"</last-close>");
										sb1.append(lastclose);
										int rr=lastclose.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									}
									else if(j==6){
										String per_change=id4;
										sb.append("<per-change>"+per_change+"</per-change>");
										sb1.append(per_change);
										int rr=per_change.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									 }
									else if(j==7){
										String market_cap=id4;
										sb.append("<market-cap>"+market_cap+"</market-cap>");
										sb1.append(market_cap);
										int rr=market_cap.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									 }
									else if(j==8){
										String divisor=id4;
										sb.append("<divisor>"+divisor+"</divisor>");
										sb1.append(divisor);
										int rr=divisor.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									 }
									else if(j==9){
										String currency=id4;
										sb.append("<currency>"+currency+"</currency>");
										sb1.append(currency);
										int rr=currency.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									 }
									 else if(j==10){
								    	String  date=id4;
								    	 sb.append("<date>"+date+"</date>");
								    	 sb1.append(date);
																			 
									  }
									
								   }   catch(Exception e){
								   	       Logging.error(" Error : "+e.getMessage());
								               }
			
								
							}
						}
						sb.append("</index>");
					}
				}
				
				sb.append("</Index-Report>");
				
				break;
			case 5: 
				pathf = pathf + "CoolMenus/" + "StockContribution.xml";
				sb.append("<Weightage_report>");      
				sb.append("<Index_id>"+iename+"</Index_id>");
				
			id=null;
			Logging.debug("size of vector "+ai.size());
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
								
									sb.append("<index_market>"+indexmarket+"</index_market>");
									sb1.append(indexmarket);
									int rr=indexmarket.trim().length();
									int jj=20;
									int kk=jj-rr;
									for(int g=0;g<kk;g++){sb1.append(" ");}
								   }
								else if(j==2){
									String stockmarket=id;
									
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
					
					sb.append("</stock>");	
				}
				
			}
			
			    sb.append("</Weightage_report>"); 
			 break;
			case 6: 
				pathf = pathf + "CoolMenus/" + "StockDetails.xml";
				sb.append("<Stock-Report>");
				sb.append("<Report-name>"+"Stock Details"+"</Report-name>");
				sb.append("<Index-name>"+iename+"</Index-name>");
				
			
				
				String id6 = null;
				Logging.debug("size of vector "+ai.size());
				if(ai.size()!= 0)
				{
					Iterator it = ai.iterator();
					while(it.hasNext())
					{
				
						sb.append("<stock>");
						for(int j = 0;j<10;j++)
						{
							if(it.hasNext())
							{	
								id6=(String)it.next();
								
								try{
									
									
									
										
									if(j==0){
										String stockname=id6;
										
										sb.append("<stock-name>"+"<![CDATA["+stockname+"]]>"+"</stock-name>");
										sb1.append(stockname);
										int rr=stockname.trim().length();
										int jj=90;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==1){
										String openvalue=id6;
									
										sb.append("<open-value>"+openvalue+"</open-value>");
										sb1.append(openvalue);
										int rr=openvalue.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==2){
										String closevalue=id6;
									
										sb.append("<close-value>"+closevalue+"</close-value>");
										sb1.append(closevalue);
										int rr=closevalue.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==3){
										String lowvalue=id6;
								
										sb.append("<low-value>"+lowvalue+"</low-value>");
										sb1.append(lowvalue);
										int rr=lowvalue.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==4){
										String highvalue=id6;
									
										sb.append("<high-value>"+highvalue+"</high-value>");
										sb1.append(highvalue);
										int rr=highvalue.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==5){
										String tradedvalue=id6;
									
										sb.append("<traded-value>"+tradedvalue+"</traded-value>");
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
										
										sb.append("<mcv>"+mcv+"</mcv>");
										sb1.append(mcv);
										int rr=mcv.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									  }
									else if(j==8){
										String nooftrades=id6;
										
										
										sb.append("<no-of-trades>"+nooftrades+"</no-of-trades>");
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
						sb.append("</stock>");	
					}
					
				}
				
				sb.append("</Stock-Report>");
				 break;
			case 12:
				pathf = pathf + "CoolMenus/" + "IndexPerformance.xml";
				sb.append("<Index-Report>");
				sb.append("<Report-name>"+"IndexPerformance"+"</Report-name>");
				
				String id12 = null;
				
				if(ai.size()!= 0)
				{
				    Iterator it = ai.iterator();
					while(it.hasNext())
					{
						it.next(); 
						sb.append("<Index>");
						for(int j = 0; j < 5; j++)
						{
							if(it.hasNext())
							{	
								id12=(String)it.next();
								
								try{
									if(j==0){
										String indexname=id12;
										sb.append("<index-name>"+"<![CDATA["+indexname+"]]>"+"</index-name>");
										sb1.append(indexname);
										int rr=indexname.trim().length();
										int jj=90;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									 else if(j==1){
										String m1=id12;
									    sb.append("<First-month>"+m1+"</First-month>");
										sb1.append(m1);
										int rr=m1.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									 else if(j==2){
										String m3=id12;
									    sb.append("<Third-month>"+m3+"</Third-month>");
										sb1.append(m3);
										int rr=m3.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==3){
										String m6=id12;
										sb.append("<Sixth-month>"+m6+"</Sixth-month>");
										sb1.append(m6);
										int rr=m6.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									  }
									else if(j==4){
										String m12=id12;
										sb.append("<Year>"+m12+"</Year>");
										sb1.append(m12);
										
									   }
									
							 	   }     catch(Exception e){
									Logging.error(" Error : "+e.getMessage());
								      }
							}
						}
						sb.append("</Index>");	
					}
				}
				sb.append("</Index-Report>");
				break;
				
			case 17:
				pathf = pathf + "CoolMenus/" + "InactiveStockList.xml";
				sb.append("<Stock-Report>");
				sb.append("<Report-name>"+"InactiveStockList"+"</Report-name>");
				//sb.append("<Exchange-name>"+iename+"</Exchange-name>");
				
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
				
				
				String id17 = null;
				
				if(ai.size()!= 0)
				{
				    Iterator it = ai.iterator();
					while(it.hasNext())
					{
						it.next(); 
						sb.append("<stock>");
						for(int j = 0; j < 4; j++)
						{
							if(it.hasNext())
							{	
								id17=(String)it.next();
								
								try{
									if(j==0){
										String stockname=id17;
										sb.append("<stock_name>"+"<![CDATA["+stockname+"]]>"+"</stock_name>");
										sb1.append(stockname);
										int rr=stockname.trim().length();
										int jj=90;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==1){
										String outshares=id17;
									
										sb.append("<outstanding_shares>"+outshares+"</outstanding_shares>");
										sb1.append(outshares);
										int rr=outshares.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==2){
										String face_value=id17;
										
										sb.append("<face_value>"+face_value+"</face_value>");
										sb1.append(face_value);
										int rr=face_value.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									
								    else if(j==3){
									   	String date=id17;
									    sb.append("<date>"+date+"</date>");
										 sb1.append(date);
										  
								    }
								  
								 }   catch(Exception e){

									       Logging.error(" Error : "+e.getMessage());
								         }
							}
						}
						sb.append("</stock>");
					}
				}
				sb.append("</Stock-Report>");
				break;
			case 21: 
				
				pathf = pathf + "CoolMenus/" + "StockList.xml";
				sb.append("<Stock-Report>");
				sb.append("<Report-name>"+"StockList"+"</Report-name>");
				sb.append("<Exchange-id>"+iename+"</Exchange-id>");
				
				
				String id21 = null;
				
				if(ai.size()!= 0)
				{
					Iterator it = ai.iterator();
					while(it.hasNext()) {
						it.next();
						sb.append("<stock>");
						for(int j = 0;j<6;j++)
						{
							if(it.hasNext())
							{	
								id21=(String)it.next();
								
								try{
									if(j==0){
										String stockname=id21;
										
										sb.append("<stock-name>"+"<![CDATA["+stockname+"]]>"+"</stock-name>");
										sb1.append(stockname);
										int rr=stockname.trim().length();
										int jj=90;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==1){
										String tis=id21;
									
										sb.append("<tis>"+tis+"</tis>");
										sb1.append(tis);
										int rr=tis.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==2){
										String closevalue=id21;
									
										sb.append("<close-value>"+closevalue+"</close-value>");
										sb1.append(closevalue);
										int rr=closevalue.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==3){
										String mcap=id21;
								
										sb.append("<mcap>"+mcap+"</mcap>");
										sb1.append(mcap);
										int rr=mcap.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==4){
										String face_value=id21;
									
										sb.append("<face_value>"+face_value+"</face_value>");
										sb1.append(face_value);
										int rr=face_value.trim().length();
										int jj=20;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==5){
										String date=id21;
									
										sb.append("<date>"+date+"</date>");
										sb1.append(date);
										
									   }
									
									
								}
								catch(Exception e){
									Logging.error(" Error : "+e.getMessage());
								}
								
							}
						}
						sb.append("</stock>");
					}
				}
				sb.append("</Stock-Report>");
				break;
			case 24: 
				
				pathf = pathf + "CoolMenus/" + "IndexMovement.xml";
				sb.append("<Index-Report>");
				sb.append("<Report-name>"+"Index Movement"+"</Report-name>");
				sb.append("<Index-id>"+iename+"</Index-id>");
				String id24 = null;
				
				if(ai.size()!= 0)
				{
					Iterator it = ai.iterator();
					while(it.hasNext())
					{
						
						for(int j = 0;j<4;j++)
						{
							if(it.hasNext())
							{	
								id24=(String)it.next();
								
								try{
									 if(j ==0){	
								          String todate =id24;
								          sb.append("<trading-date>"+todate+"</trading-date>");
								          sb1.append(todate);
								          int rr=todate.trim().length();
								           int jj=90;
								           int kk=jj-rr;
								         for(int g=0;g<kk;g++){sb1.append(" ");}	
								        }
								   if(j==1){
								         String close = id24;
								
									     sb.append("<close>"+close+"</close>");
									     sb1.append(close);
									     int rr=close.trim().length();
									     int jj=20;
									     int kk=jj-rr;
									     for(int g=0;g<kk;g++){sb1.append(" ");}
								        }
								   if(j==2){
									     String mcap = id24;
										 sb.append("<mcap>"+mcap+"</mcap>");
									     sb1.append(mcap);
									     int rr=mcap.trim().length();
									     int jj=20;
									     int kk=jj-rr;
									     for(int g=0;g<kk;g++){sb1.append(" ");}
									   
								       }
								   if(j ==3){
									   String  divisor=id24;
								    	 sb.append("<divisor>"+divisor+"</divisor>");
								    	 sb1.append(divisor);
								        }
									
								}
								catch(Exception e){
									Logging.error(" Error : "+e.getMessage());
								       }
								
							}
						}
						
						
					}
				}
				sb.append("</Index-Report>");
				break;
			
			case 25:
				pathf = pathf + "CoolMenus/" + "TradedVolume.xml";
				sb.append("<Index_report>");      
				sb.append("<Report-name>"+"TradedVolume"+"</Report-name>");
				
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
												
											   }
											
											
											
											
										} catch(Exception e){
											 Logging.error(" Error : "+e.getMessage());
										}
									}
								}//for
								sb.append("</stock>");
							}
								
						}
						sb.append("</Index_report>");
						break;
			case 26 :
				pathf = pathf + "CoolMenus/" + "PortfolioBasket.xml";
				sb.append("<Basket-Report>");
				sb.append("<Basket-name>"+"Portfolio Basket Calculator"+"</Basket-name>");
				String id26 = null;
				
				if(ai.size()!= 0)
				{
					Iterator it = ai.iterator();
					while(it.hasNext()) 
					{
						
						sb.append("<basket>");
						//it.next();
						for(int j=0;j<5;j++)
						{
							if(it.hasNext())
							{	
								id26=(String)it.next();
								
								try{
									if(j==0){
										String srno=id26;
										sb.append("<Sr-No>"+"<![CDATA["+srno+"]]>"+"</Sr-No>");
										sb1.append(srno);
										int rr=srno.trim().length();
										int jj=10;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									 else if(j==1){
										String symbolN=id26;
									    sb.append("<Symbol-Name>"+symbolN+"</Symbol-Name>");
										sb1.append(symbolN);
										int rr=symbolN.trim().length();
										int jj=30;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									 else if(j==2){
										String pricep=id26;
									    sb.append("<Last-TradedPrice>"+pricep+"</Last-TradedPrice>");
										sb1.append(pricep);
										int rr=pricep.trim().length();
										int jj=30;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==3){
										String mktval=id26;
										sb.append("<Mkt-CapValue>"+mktval+"</Mkt-CapValue>");
										sb1.append(mktval);
										int rr=mktval.trim().length();
										int jj=50;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									  }
									else if(j==4){
										String sharesCal=id26;
										sb.append("<Shares-Calculated>"+sharesCal+"</Shares-Calculated>");
										sb1.append(sharesCal);
										
									   }
																	
								   }   catch(Exception e){
								   	       Logging.error(" Error : "+e.getMessage());
								               }
			
								
							}
						}
						sb.append("</basket>");
					}
				}
				
				sb.append("</Basket-Report>");
				
				break;
				
			case 27 :
				pathf = pathf + "CoolMenus/" + "IndexCalculatorRpt.xml";
				sb.append("<IndexCal-Report>");
				sb.append("<IndexCal-name>"+"Index Calculator"+"</IndexCal-name>");
				String id27 = null;
				
				if(ai.size()!= 0)
				{
					Iterator it = ai.iterator();
					while(it.hasNext()) 
					{
						
						sb.append("<IndexCal>");
						//it.next();
						for(int j=0;j<4;j++)
						{
							if(it.hasNext())
							{	
								id27=(String)it.next();
								
								try{
									if(j==0){
										String srno=id27;
										sb.append("<Sr-No>"+"<![CDATA["+srno+"]]>"+"</Sr-No>");
										sb1.append(srno);
										int rr=srno.trim().length();
										int jj=10;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									 else if(j==1){
										String symbolN=id27;
									    sb.append("<Symbol-Name>"+symbolN+"</Symbol-Name>");
										sb1.append(symbolN);
										int rr=symbolN.trim().length();
										int jj=30;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									 else if(j==2){
										String pricep=id27;
									    sb.append("<Last-TradedPrice>"+pricep+"</Last-TradedPrice>");
										sb1.append(pricep);
										int rr=pricep.trim().length();
										int jj=30;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									   }
									else if(j==3){
										String myPrice=id27;
										sb.append("<My-Price>"+myPrice+"</My-Price>");
										sb1.append(myPrice);
										int rr=myPrice.trim().length();
										int jj=50;
										int kk=jj-rr;
										for(int g=0;g<kk;g++){sb1.append(" ");}
									  }
																										
								   }   catch(Exception e){
								   	       Logging.error(" Error : "+e.getMessage());
								               }
			
								
							}
						}
						sb.append("</IndexCal>");
					}
				}
				
				sb.append("</IndexCal-Report>");
				
				break;
				
				
				}//end of switch	
								
		}catch(Exception e)
		{
			Logging.debug("Error prevented the file from being created."+e);
		}
		
		try{
		  
		
		  
				BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathf)));
				
		        bufferedWriter.write(sb.toString());
				bufferedWriter.flush();
				bufferedWriter.close();
		        Logging.debug("buffffer ====>"+sb.toString());
		         Logging.error("nh"+sb.toString());
		 } catch(Exception e){
			Logging.error("nnnnnnn"+e);
			}
		
	}
	/**
	 * function for accepting Sting[].
	 * 
	 * @param arr
	 * @param switch_code
	 * @param vi
	 * @param fdate
	 * @param tdate
	 */
	public void create_file1(String[] arr,int switch_code,Vector vi,String fdate, String tdate)
	{ 
		
		Vector vec=vi;
		Logging.debug("Vector new====>"+vec);
	//	org.jfree.chart.demo.servlet.ComposeIndex ci=new org.jfree.chart.demo.servlet.ComposeIndex();
		StringBuffer sb= new StringBuffer();
		StringBuffer sb1= new StringBuffer();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		try
		{      
			 pathf = Connect.getCoolMenuspath();
			pathf = pathf + "CoolMenus/" + "IndexReturn.xml";
			sb.append("<Index-Report>");
			sb.append("<Report-name>"+"IndexReturn"+"</Report-name>");
			
			
			
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
			
			sb.append("</Index-Report>");

		}catch(Exception e)
		{
				Logging.debug("Error prevented the file from being created."+e);
		}
		try{
			  
			
			  
					BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathf)));
					
			        bufferedWriter.write(sb.toString());
					bufferedWriter.flush();
					bufferedWriter.close();
			        Logging.debug("buffffernew ====>"+sb.toString());
			         Logging.error("nnnnn"+sb.toString());
			 } catch(Exception e){
				Logging.error("nnnnnnn"+e);
				}
	}//end of func
	
	/**
	 * Overloaded funtion create_file for vector.
	 * 
	 * @param var_vec
	 * @param switch_code
	 * @param ai
	 * @param fdate
	 * @param tdate
	 */
	public void create_file2(Vector var_vec,int switch_code,Vector ai,String fdate, String tdate)
	{ 
		Vector vec=ai;
		Logging.debug("vec for ohlc"+vec);
	//	org.jfree.chart.demo.servlet.ComposeIndex ci=new org.jfree.chart.demo.servlet.ComposeIndex();
		ComposeIndex ci=ConnectInit.getComposeIndex();
		StringBuffer sb= new StringBuffer();
		StringBuffer sb1= new StringBuffer();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		try
		{  
			 pathf = Connect.getCoolMenuspath();
			switch(switch_code)
			{
				case 10:
					pathf = pathf + "CoolMenus/" + "IndexCompareOHLC.xml";
					sb.append("<Index-Report>");
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
					
					sb.append("</Index-Report>");	
			break;
				case 15:
					pathf = pathf + "CoolMenus/" + "IndexCorrelation.xml";
					sb.append("<Index-Report>");
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
					sb.append("</Index-Report>");	
			break;
			}
	
		}catch(Exception e)
		{
				Logging.debug("Error prevented the file from being created."+e);
		}
		try{
			  
			
			  
					BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathf)));
					
			        bufferedWriter.write(sb.toString());
					bufferedWriter.flush();
					bufferedWriter.close();
			        Logging.debug("buffffernew ====>"+sb.toString());
			         Logging.error("nnnnn"+sb.toString());
			 } catch(Exception e){
				Logging.error("nnnnnnn"+e);
				}

	}//end of func
	
		
}

			