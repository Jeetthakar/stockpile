
/*
 * Created on Jul 23, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */ 
package harrier.income.com.report;  
 


import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;
import app.*;

import java.io.*;

import java.io.FileOutputStream;
import java.io.IOException; 
import java.io.OutputStreamWriter;
import java.util.Vector;
import java.util.Iterator;

import org.jfree.chart.demo.servlet.AdjustDecimal;
import org.jfree.chart.demo.servlet.ComposeIndex;

import sysconfig.model.indexMovement;
import sysconfig.model.stockDetailFromDate;

import com.harrier.initializeation.ConnectInit;
import com.lowagie.text.*;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

import com.lowagie.text.pdf.ColumnText;



/**
 * @author lokesh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MakePdfOne{
	Logger Logging = Logger.getLogger(MakePdfOne.class);
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
	//int Rep++;
	 Image jpg1=null;
	 int l=0;
	 String path=null;
	 Rectangle pageSize = new Rectangle(0,0,1600,900);
	 Document document = new Document(pageSize);	

	 	StringBuffer sb1= new StringBuffer();
		//org.jfree.chart.demo.servlet.ComposeIndex ci=new org.jfree.chart.demo.servlet.ComposeIndex();
		ComposeIndex ci=ConnectInit.getComposeIndex();
		 /**
		* default Constructor.
		*/
		
		public MakePdfOne(){
			
				 pathf = Connect.getCoolMenuspath();
				 l=pathf.length();
				 path=pathf.substring(0,(l-16));
				 path=path+"pages/stockpile_banner.jpg";
				 pathf = pathf + "CoolMenus/" + "Allinone.pdf";
				 try{
				 document.setMargins(0,0,0,0);
				 PdfWriter.getInstance(document, new FileOutputStream(pathf));
				 document.open();
				 }catch(Exception e)
				 {
					 Logging.error("Not able to initialize PdfWriter"+e);
				 }
				 
		}
		public void closePdf(){
			document.close();
		}
		public void addImage(){
			
		    try{	
		    		
		    		jpg1 = Image.getInstance(path);
				    jpg1.setAlignment(1);
	        		jpg1.setWidthPercentage(300);
	        		jpg1.setDeflated(true);
	        		jpg1.setDpi(100,100);
	        		document.add(jpg1);
			
				   
			} catch(Exception e){
			        Logging.error("Error not able to display image"+e);	
			}
		}
		
		public void create_file(Vector var_vec,int switch_code, Vector ai,String fdate, String tdate)
		{ 
			
			try
			{  
				 			
				 switch(switch_code)
					{
				 
					case 14: 
						
						
					    
						Table t214 = new Table(1);
						
						addImage();
						
					    t214.setCellsFitPage(true);
					    t214.setTableFitsPage(true);
					    t214.setBackgroundColor(Color.YELLOW);
					    t214.setPadding(0);
					    String head14= new String("Index Returns and Volatility ");
					    com.lowagie.text.Cell c214 = new com.lowagie.text.Cell(head14);
					    c214.setHorizontalAlignment(1);
					    c214.setHeader(true);
					    t214.addCell(c214);
						document.add(t214);
						
				  	Table t14 = new Table(3,1);
				  	
				  	t14.setPadding(10);
				  	t14.setSpacing(1);
				  	t14.setBorderWidth(1);
				  	t14.setBackgroundColor(Color.LIGHT_GRAY);
				  	t14.setBorderColor(Color.GRAY);
				  	String s14 = new String();
				  	com.lowagie.text.Cell c114= new com.lowagie.text.Cell(s14);
					
				  	c114.setHeader(true);
					String id14 = null;
					
					//Logging.getDebug("size of vector "+ai.size());
					if(ai.size()!= 0)
					{
						Iterator it = ai.iterator();
						while(it.hasNext())
						{
											
							for(int j = 0;j<3;j++)
							{
								if(it.hasNext())
								{	
									id14=(String)it.next();
									
									try{
										if(j==0){
											if(flag){
												String s214[] = {"Index Name","Periodic Returns","Volatility Of Returns"};
												for(int i = 0;i<s214.length;i++){
											    	
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
					
					document.add(t14);
					
					 break;
					 
				 
				 
					case 15: 
						
					
					    Table t215 = new Table(1);
					    addImage();
					    /*
					    try{	
				    									   
					    		jpg1 = Image.getInstance(path);
							    jpg1.setAlignment(1);
				        		jpg1.setWidthPercentage(300);
				        		jpg1.setDeflated(true);
				        		jpg1.setDpi(100,100);
				        		document.add(jpg1);
						
							   
						} catch(Exception e){
							Logging.getError("Error not able to display image"+e);	
						}*/
					    
					    t215.setBackgroundColor(Color.YELLOW);
				        t215.setCellsFitPage(true);
					    t215.setTableFitsPage(true);
					    t215.setPadding(0);
					    String head15= new String("Index Correlation");
					    com.lowagie.text.Cell c215 = new com.lowagie.text.Cell(head15);
					    c215.setHorizontalAlignment(1);
					    c215.setHeader(true);
					    t215.addCell(c215);
						document.add(t215);
						
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
								//Logging.getDebug("vector string(kap) is"+m);
								temp[var_v15] = m;
								count++;
							}
							//Logging.getDebug("count is = "+count);
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
					
					
					//Logging.getDebug("size of vector "+ai.size());
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
							//Logging.getDebug("vector string(kap) is"+m);
							var15[var_v15] = m;
							var_v15++;
						}
					}
					
					
					//for first cell 
					c115a = new com.lowagie.text.Cell(" ");
					c115a.setHorizontalAlignment(1);
					t15a.addCell(c115a);
				
					for(int j15= 0; j15 < var15.length; j15++)
					{
						String heading15a = var15[j15];
						//Logging.getDebug("in first for loop"+heading15a);
						try{
						if(!(var15[j15].equals(null))){
						c115a = new com.lowagie.text.Cell(heading15a);
						c115a.setHorizontalAlignment(1);
						}
						t15a.addCell(c115a);
						}catch(NullPointerException ne){
							//ne.printStackTrace();
							Logging.debug(ne);}
						//else{break;}
						
					}
									
					String id15 = null;
					if(ai.size()!= 0)
					{							
						Iterator it15 = ai.iterator();
						while(it15.hasNext())
						{
							
							id15=(String)it15.next();
							//Logging.getDebug("in 3rd for loop"+id15);
							c115 = new com.lowagie.text.Cell(id15);
							c115.setHorizontalAlignment(1);
							t15.addCell(c115);
						}
													
					}
					
					document.add(t15a);		
					document.add(t15);
					
					 break;
					 
					 
				case 10: 	
						
						
						Vector vid= var_vec;
					    Table t210= new Table(1);
					    addImage();
					    
					    /*
					    try{	
				    		
				    		jpg1 = Image.getInstance(path);
						    jpg1.setAlignment(1);
			        		jpg1.setWidthPercentage(300);
			        		jpg1.setDeflated(true);
			        		jpg1.setDpi(100,100);
			        		document.add(jpg1);
					
						   
					  } catch(Exception e){
						Logging.getError("Error not able to display image"+e);	
					  }*/
					    
					  t210.setBackgroundColor(Color.YELLOW);
				      t210.setCellsFitPage(true);
				      t210.setTableFitsPage(true);
				      t210.setPadding(0);
				      String head10= new String("Index Compare OHLC Report");
				      com.lowagie.text.Cell c210 = new com.lowagie.text.Cell(head10);
				      c210.setHorizontalAlignment(1);
				      c210.setHeader(true);
				      t210.addCell(c210);
				      document.add(t210);
				      
				 
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
							Logging.debug("problem in iterator ");
						   	
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
										Logging.debug("problem in  "+stockname);
									   				
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
										Logging.error("Error prevented the file from being created."+e);
									}
								}
							}
						}
					 }
					document.add(t10);
					
					
					break;
				
			 	
					}//end of switch	
					
			
			}catch(Exception e)
			{
				Logging.error("Error prevented the file from being created."+e);
			}finally{
				
			}// end of finally
				
		
		}//end of method create file 
	 
	
	public void create_file(String var,int switch_code, Vector ai,String fdate, String tdate,String iename)
	{ 
		
		
		StringBuffer sb1= new StringBuffer();
		try
		{  
			 
			 switch(switch_code)
				{
				case 1: 
					
				   
				    Table t2= new Table(1);
				    addImage();
				    
				    /*try{					    		
				    		jpg1 = Image.getInstance(path);
						    jpg1.setAlignment(1);
			        		jpg1.setWidthPercentage(300);
			        		jpg1.setDeflated(true);
			        		jpg1.setDpi(100,100);
			        		document.add(jpg1);
					
						   
					} catch(Exception e){
					        Logging.getError("Error not able to display image"+e);	
					}*/
				   	
				  t2.setCellsFitPage(true);
				  t2.setBackgroundColor(Color.yellow);
				  t2.setTableFitsPage(true);
				  t2.setPadding(0);    
				  String head= new String("Index Composition Report for Index: ");
				  head=head+iename;
				  com.lowagie.text.Cell c2 = new com.lowagie.text.Cell(head);
				  c2.setHorizontalAlignment(1);
				  
				  t2.addCell(c2);
				  document.add(t2);
				  Table t = new Table(17);
			  	  t.setPadding(10);
			  	
			  	t.setBackgroundColor(Color.LIGHT_GRAY);
			  	t.setBorderWidth(1);
			
			  	t.setBorderColor(Color.LIGHT_GRAY);
			  	String s = new String();
			  	
			  	com.lowagie.text.Cell c1=new com.lowagie.text.Cell(s);
				String id = null;
				
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
								
								try{
									if(j==0){
										if(flag){
											c1 = new com.lowagie.text.Cell("STOCK NAME");
											c1.setColspan(2);
											t.addCell(c1);
											c1 = new com.lowagie.text.Cell("TOTAL SHARES");
											c1.setHorizontalAlignment(1);
											c1.setColspan(2);
											t.addCell(c1);
											c1 = new com.lowagie.text.Cell("IWF");
											c1.setHorizontalAlignment(1);
											t.addCell(c1);
											c1 = new com.lowagie.text.Cell("Mkt. Lot");
											c1.setHorizontalAlignment(1);
											t.addCell(c1);
											c1 = new com.lowagie.text.Cell("Price (LTP)");
											c1.setHorizontalAlignment(1);
											t.addCell(c1);
											c1 = new com.lowagie.text.Cell("Price (Last)");
											c1.setHorizontalAlignment(1);
											t.addCell(c1);
											c1 = new com.lowagie.text.Cell("Curr. Exch. Rate");
											c1.setHorizontalAlignment(1);
											t.addCell(c1);
											c1 = new com.lowagie.text.Cell("Curr ");
											c1.setHorizontalAlignment(1);
											t.addCell(c1);
											c1 = new com.lowagie.text.Cell("Market cap");
											c1.setHorizontalAlignment(1);
											c1.setColspan(2);
											t.addCell(c1);
											
											c1 = new com.lowagie.text.Cell("Adj. Market cap");
											c1.setHorizontalAlignment(1);
											c1.setColspan(2);
											t.addCell(c1);
											c1 = new com.lowagie.text.Cell("Weightage");
											c1.setHorizontalAlignment(1);
											t.addCell(c1);
											c1 = new com.lowagie.text.Cell("Date");
											c1.setHorizontalAlignment(1);
											c1.setColspan(2);
											t.addCell(c1);
											flag=false;
										}
										String stockname=id;
										
										sb1.append(stockname);
										s = stockname;
										c1 = new com.lowagie.text.Cell(s);
										c1.setColspan(2);
										t.addCell(c1);
									   					
									   }
									else if(j==1){
										String tis=id;
										sb1.append(tis);
										s = tis;
										c1 = new com.lowagie.text.Cell(s);
										c1.setHorizontalAlignment(2);
										c1.setColspan(2);
										t.addCell(c1);
									   					
									   }
									else if(j==2){
										String iwf=id;
										sb1.append(iwf);
										s = iwf;
										c1 = new com.lowagie.text.Cell(s);
										c1.setHorizontalAlignment(2);
										
										t.addCell(c1);
									   					
									   }
									else if(j==3){
										String market=id;
										sb1.append(market);
										s = market;
										c1 = new com.lowagie.text.Cell(s);
										c1.setHorizontalAlignment(2);
										t.addCell(c1);
									   					
									}
									else if(j==4){
										String ltp=id;
										sb1.append(ltp);
										s = ltp;
										c1 = new com.lowagie.text.Cell(s);
										c1.setHorizontalAlignment(2);
										t.addCell(c1);
									   					
									  }
									else if(j==5){
										String last =id;
										sb1.append(last);
										s = last;
										c1 = new com.lowagie.text.Cell(s);
										c1.setHorizontalAlignment(2);
										t.addCell(c1);
									   					
									  }
									else if(j==6){
										String currency=id;
										sb1.append(currency);
										s = currency;
										c1 = new com.lowagie.text.Cell(s);
										c1.setHorizontalAlignment(2);
										t.addCell(c1);
									   					
									   }
									else if(j==7){
										String curr_exch_convIratecomp=id;
										sb1.append(curr_exch_convIratecomp);
										s = curr_exch_convIratecomp;
										c1 = new com.lowagie.text.Cell(s);
										c1.setHorizontalAlignment(2);
										t.addCell(c1);
									   					
									   }
									else if(j==8){
										String mcv=id;
										sb1.append(mcv);
										s = mcv;
										c1 = new com.lowagie.text.Cell(s);
										c1.setHorizontalAlignment(2);
										c1.setColspan(2);
										t.addCell(c1);
									   					
									  }
									else if(j==9){
										String adjusted=id;
										sb1.append(adjusted);
										s = adjusted;
										c1 = new com.lowagie.text.Cell(s);
										c1.setHorizontalAlignment(2);
										c1.setColspan(2);
										t.addCell(c1);
									   					
																
									}
									
								    else if(j==10){
									   	 String strweightage1=id;
									     sb1.append(strweightage1);
										 s = strweightage1;
										 c1 = new com.lowagie.text.Cell(s);
										 c1.setHorizontalAlignment(2);
										 t.addCell(c1);
										   					
								    }
								    
								    else if(j==11){
								    	String  date=id;
								    	s = date;
								    	sb1.append(date);
								    	c1 = new com.lowagie.text.Cell(s);
								    	c1.setColspan(2);
										t.addCell(c1);
										 
									}
									
								} catch(Exception e){
									
									Logging.error(" Error : "+e.getMessage());
								}
								
							}
						}
						//count = count + 1;
					}
						
				}// end of if
				
				document.add(t);
				
				 break;
				 
				case 19: 
					
					    Table t219= new Table(1);
					    addImage();
					    
					    /*try{		
							    jpg1 = Image.getInstance(path);
							    jpg1.setAlignment(1);
				        		jpg1.setWidthPercentage(300);
				        		jpg1.setDeflated(true);
				        		jpg1.setDpi(100,100);
				        		document.add(jpg1);
												   
						} catch(Exception e){
						        Logging.getError("Error not able to display image"+e);	
						}*/
					   
				      t219.setCellsFitPage(true);
				      t219.setBackgroundColor(Color.yellow);
				      t219.setTableFitsPage(true);
				      t219.setPadding(0);
				      String head19= new String("Index Divisor Report For Index:");
				      head19=head19+iename;
				      com.lowagie.text.Cell c219 = new com.lowagie.text.Cell(head19);
				      c219.setHorizontalAlignment(1);
				      c219.setHeader(true);
				      t219.addCell(c219);
				      
				      head19=new String("From Date= "+fdate+"    To Date= "+tdate);
				      c219 = new com.lowagie.text.Cell(head19);
				      c219.setHorizontalAlignment(1);
				      c219.setHeader(true);
				      t219.addCell(c219);
				      
				      document.add(t219);
				 
				  	Table t19 = new Table(4);
				  	t19.setPadding(10);
				  	t19.setSpacing(1);
				  	
				  	t19.setBackgroundColor(Color.LIGHT_GRAY);
				  	t19.setBorderWidth(1);
				  	
				  	t19.setBorderColor(Color.LIGHT_GRAY);
				  	String s19 = new String();
					  	
				  	com.lowagie.text.Cell c119= new com.lowagie.text.Cell(s19);
					c119.setHeader(true);
					String id19 = null;
					
					if(ai.size()!= 0)
					{
						Iterator it = ai.iterator();
						while(it.hasNext())
						{
					
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
					
					document.add(t19);
					
					 break;
					 
				case 6: 
						
					
				  	/*try{		
						    jpg1 = Image.getInstance(path);
						    jpg1.setAlignment(1);
			        		jpg1.setWidthPercentage(300);
			        		jpg1.setDeflated(true);
			        		jpg1.setDpi(100,100);
			        		document.add(jpg1);
											   
					} catch(Exception e){
					        Logging.getError("Error not able to display image"+e);	
					}*/
				  	
				    Table tsd= new Table(1);
				    addImage();  
				    tsd.setCellsFitPage(true);
				    tsd.setBackgroundColor(Color.yellow);
				    tsd.setTableFitsPage(true);
				    tsd.setPadding(0);
				    String head6= new String("Stock Details Report For Index:");
				    head6=head6+iename;
				    com.lowagie.text.Cell c6s = new com.lowagie.text.Cell(head6);
				    c6s.setHorizontalAlignment(1);
				    c6s.setHeader(true);
				    tsd.addCell(c6s);
				    
				    head6=new String("From Date= "+fdate+"    To Date= "+tdate);
				    c6s = new com.lowagie.text.Cell(head6);
				    c6s.setHorizontalAlignment(1);
				    c6s.setHeader(true);
				    tsd.addCell(c6s);
				    
				    document.add(tsd);
				 
				  	Table t6 = new Table(9);
				   	t6.setPadding(10);
				  	t6.setSpacing(1);
				  	
				  	t6.setBackgroundColor(Color.LIGHT_GRAY);
				  	t6.setBorderWidth(1);
				    t6.setBorderColor(Color.LIGHT_GRAY);
				  	String s6 = new String();
				  	
			  	    com.lowagie.text.Cell c6= new com.lowagie.text.Cell(s6);
				    c6.setHeader(true);
				
					String id6 = null;
					
					if(ai.size()!= 0)
					{
						Iterator it = ai.iterator();
						while(it.hasNext())
						{
					
							for(int j = 0;j<10;j++)
							{
								if(it.hasNext())
								{	
									id6=(String)it.next();
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
											sb1.append(closevalue);
											s6 = closevalue;
											c6 = new com.lowagie.text.Cell(s6);
											c6.setHorizontalAlignment(1);
											t6.addCell(c6);
										   }
										else if(j==3){
											String lowvalue=id6;
										
											sb1.append(lowvalue);
											s6 = lowvalue;
											c6 = new com.lowagie.text.Cell(s6);
											c6.setHorizontalAlignment(1);
											t6.addCell(c6);
										   }
										else if(j==4){
											String highvalue=id6;
											sb1.append(highvalue);
											s6 = highvalue;
											c6 = new com.lowagie.text.Cell(s6);
											c6.setHorizontalAlignment(1);
											t6.addCell(c6);
										   }
										else if(j==5){
											String tradedvalue=id6;
										
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
					document.add(t6);
					
					break;	
		
				case 20: 
									
									
				    Table t220= new Table(1);
				    addImage();
				   /* try{		
						    jpg1 = Image.getInstance(path);
						    jpg1.setAlignment(1);
			        		jpg1.setWidthPercentage(300);
			        		jpg1.setDeflated(true);
			        		jpg1.setDpi(100,100);
			        		document.add(jpg1);
					
						   
					} catch(Exception e){
					        Logging.getError("Error not able to display image"+e);	
					}*/
				    
				    t220.setCellsFitPage(true);
				    t220.setBackgroundColor(Color.yellow);
				    t220.setTableFitsPage(true);
				    t220.setPadding(0);
				    String head20= new String("Index Pe Pb Report For Index: ");
				    head20=head20+iename;
				    com.lowagie.text.Cell c220 = new com.lowagie.text.Cell(head20);
				    c220.setHorizontalAlignment(1);
				   
				    c220.setHeader(true);
				    t220.addCell(c220);
				    
				    head20=new String("From Date= "+fdate+"    To Date= "+tdate);
				    c220 = new com.lowagie.text.Cell(head20);
				    c220.setHorizontalAlignment(1);
				    c220.setHeader(true);
				    t220.addCell(c220);
				    
				    document.add(t220);
				    
			  	Table t20 = new Table(9);
			  	
			  	t20.setPadding(10);
			  	t20.setSpacing(1);
			  	
			    t20.setBackgroundColor(Color.LIGHT_GRAY);
			  	t20.setBorderWidth(1);
			  	
			  	t20.setBorderColor(Color.LIGHT_GRAY);
			  	String s20 = new String();
			  	
			  	com.lowagie.text.Cell c120=new com.lowagie.text.Cell(s20);
				c120.setHeader(true);
				String id20 = null;
				
				if(ai.size()!= 0)
				{
					Iterator it = ai.iterator();
					while(it.hasNext())
					{
				
						for(int j = 0;j<9;j++)
						{
							if(it.hasNext())
							{	
								id20=(String)it.next();
								
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
				
				document.add(t20);
				
				 break;
				 
				 
				//	for company wise weightage 
				case 2: 
					
					
				     Table t22= new Table(1);
				     addImage();
				    /* try{		
						    jpg1 = Image.getInstance(path);
						    jpg1.setAlignment(1);
			        		jpg1.setWidthPercentage(300);
			        		jpg1.setDeflated(true);
			        		jpg1.setDpi(100,100);
			        		document.add(jpg1);
											   
					} catch(Exception e){
					        Logging.getError("Error not able to display image"+e);	
					}*/
				     
				      t22.setCellsFitPage(true);
				      t22.setBackgroundColor(Color.yellow);
				      t22.setTableFitsPage(true);
				      t22.setPadding(0);
				      String head2= new String("Company Weightage Report For Index :");
				      head2=head2+iename;
				      com.lowagie.text.Cell c22 = new com.lowagie.text.Cell(head2);
				      c22.setHorizontalAlignment(1);
				      c22.setHeader(true);
				      t22.addCell(c22);
				      
				      head2=new String("For Date= "+fdate);
					  c22 = new com.lowagie.text.Cell(head2);
					  c22.setHorizontalAlignment(1);
					  c22.setHeader(true);
					  t22.addCell(c22);
				      
				      document.add(t22);
				 
				  	Table t02 = new Table(3);
				   	t02.setPadding(10);
				  	t02.setSpacing(1);
				  	
				  	t02.setBackgroundColor(Color.LIGHT_GRAY);
				  	t02.setBorderWidth(1);
				  	
				  	t02.setBorderColor(Color.LIGHT_GRAY);
				  	String s02 = new String();
				  	com.lowagie.text.Cell c12= new com.lowagie.text.Cell(s02);
				
				String id2 = null;
			
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
				
				document.add(t02);
				
				 break;
				 
				 //	for Industry Wise Weightage  
				case 3: 
					
					
					
				    Table t23= new Table(1);
				    addImage();
				    /*try{		
						    jpg1 = Image.getInstance(path);
						   
			        		jpg1.setAlignment(1);
			        		jpg1.setWidthPercentage(300);
			        		jpg1.setDeflated(true);
			        		jpg1.setDpi(100,100);
			        		document.add(jpg1);
					
						   
					} catch(Exception e){
					        Logging.getError("Error not able to display image"+e);	
					}
				    */
				     t23.setCellsFitPage(true);
				      t23.setBackgroundColor(Color.yellow);
				      t23.setTableFitsPage(true);
				      t23.setPadding(0);
				      String head3= new String("Industry Weightage Report For Index :");
				      head3=head3+iename;
				      com.lowagie.text.Cell c23 = new com.lowagie.text.Cell(head3);
				      c23.setHorizontalAlignment(1);
				      c23.setHeader(true);
				      t23.addCell(c23);
				      document.add(t23);
				 
				  	Table t3 = new Table(3);
				   	t3.setPadding(10);
				  	t3.setSpacing(1);
				  	
				  	t3.setBackgroundColor(Color.LIGHT_GRAY);
				  	t3.setBorderWidth(1);
				  	
				  	t3.setBorderColor(Color.LIGHT_GRAY);
				  	String s3 = new String();
				  	com.lowagie.text.Cell c13= new com.lowagie.text.Cell(s3);
				   
				String id3 = null;
				
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
				
				document.add(t3);
				
				 break;
				 
				 
				 //for stock divident
				case 22: 
									    
				    
				    Table t222 = new Table(1);
				    addImage();
				    /*try{		
						    jpg1 = Image.getInstance(path);
						   	jpg1.setAlignment(1);
			        		jpg1.setWidthPercentage(300);
			        		jpg1.setDeflated(true);
			        		jpg1.setDpi(100,100);
			        		document.add(jpg1);
					
						   
					} catch(Exception e){
					        Logging.getError("Error not able to display image"+e);	
					}
				    */
				      t222.setCellsFitPage(true);
				      t222.setBackgroundColor(Color.yellow);
				      t222.setTableFitsPage(true);
				      t222.setPadding(0);
				      String head22= new String("Stock Divident Report");
				      head22=head22+iename;
				      com.lowagie.text.Cell c222 = new com.lowagie.text.Cell(head22);
				      c222.setHorizontalAlignment(1);
				      c222.setHeader(true);
				      t222.addCell(c222);
				      
				      head22=new String("From Date= "+fdate+"    To Date= "+tdate);
					  c222 = new com.lowagie.text.Cell(head22);
					  c222.setHorizontalAlignment(1);
					  c222.setHeader(true);
					  t222.addCell(c222);
				      
				      document.add(t222);
				 
				  	Table t2sd = new Table(6);
				  	t2sd.setPadding(10);
				  	
				  	t2sd.setBackgroundColor(Color.LIGHT_GRAY);
				  	t2sd.setBorderWidth(1);
				    t2sd.setBorderColor(Color.LIGHT_GRAY);
				  	String s2sd = new String();
				  	
			  	com.lowagie.text.Cell c122= new com.lowagie.text.Cell(s2sd);
				c122.setHeader(true);
				String id22 = null;
				
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
				
				document.add(t2sd);
				
				 break;
				 
				 
				 //for capital change
				case 16: 
					
					
				    Table t216= new Table(1);
				    addImage();
				    /*try{		
						    jpg1 = Image.getInstance(path);
			        		jpg1.setAlignment(1);
			        		jpg1.setWidthPercentage(300);
			        		jpg1.setDeflated(true);
			        		jpg1.setDpi(100,100);
			        		document.add(jpg1);
					
						   
					} catch(Exception e){
					        Logging.getError("Error not able to display image"+e);	
					}*/
				     
				      t216.setCellsFitPage(true);
				      t216.setBackgroundColor(Color.yellow);
				      t216.setTableFitsPage(true);
				      t216.setPadding(0);
				      String head16= new String("Capital Change To Universe Report");
				      head16=head16+iename;
				      com.lowagie.text.Cell c216 = new com.lowagie.text.Cell(head16);
				      c216.setHorizontalAlignment(1);
				      c216.setHeader(true);
				      t216.addCell(c216);
				      
				      head16=new String("From Date= "+fdate+"    To Date= "+tdate);
				      c216 = new com.lowagie.text.Cell(head16);
				      c216.setHorizontalAlignment(1);
				      c216.setHeader(true);
					  t216.addCell(c216);
				      
				      document.add(t216);
				 
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
				
				document.add(t16);
				
				 break;
				
				 //for Traded volume
				case 25: 
					
									
				    Table t225= new Table(1);
				    addImage();
				    /*
				    try{		
						    jpg1 = Image.getInstance(path);
						    jpg1.setAlignment(1);
			        		jpg1.setWidthPercentage(300);
			        		jpg1.setDeflated(true);
			        		jpg1.setDpi(100,100);
			        		document.add(jpg1);
											   
					} catch(Exception e){
					        Logging.getError("Error not able to display image"+e);	
					}*/
				      
				    t225.setCellsFitPage(true);
				    t225.setBackgroundColor(Color.yellow);
				    t225.setTableFitsPage(true);
				    t225.setPadding(0);
				    String head25= new String("Traded Volume Report For Ind/Exch :");
				    head25=head25+iename;
				    com.lowagie.text.Cell c225 = new com.lowagie.text.Cell(head25);
				    c225.setHorizontalAlignment(1);
				    c225.setHeader(true);
				    t225.addCell(c225);
				    
				    head25=new String("From Date= "+fdate+"    To Date= "+tdate);
				    c225 = new com.lowagie.text.Cell(head25);
				    c225.setHorizontalAlignment(1);
				    c225.setHeader(true);
					t225.addCell(c225);
				    
				    document.add(t225);
				 
				  	Table t25 = new Table(2);
				  	t25.setPadding(10);
				  	t25.setSpacing(1);
				  	
				  	t25.setBackgroundColor(Color.LIGHT_GRAY);
				  	t25.setBorderWidth(1);
				  	
				  	t25.setBorderColor(Color.LIGHT_GRAY);
				  	String s25 = new String();
				  	
			  	com.lowagie.text.Cell c125= new com.lowagie.text.Cell(s25);
				c125.setHeader(true);
				 
				
				String id25 = null;
				
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
				
				document.add(t25);
				
				 break; 
				 
				 //for stock contribution
				case 5: 
										
				    
				    Table t25sc= new Table(1);
				    addImage();
				   /* try{		
						    jpg1 = Image.getInstance(path);
						    jpg1.setAlignment(1);
			        		jpg1.setWidthPercentage(300);
			        		jpg1.setDeflated(true);
			        		jpg1.setDpi(100,100);
			        		document.add(jpg1);
					
						   
					} catch(Exception e){
					        Logging.getError("Error not able to display image"+e);	
					}
				    */
				    t25sc.setCellsFitPage(true);
				    t25sc.setBackgroundColor(Color.yellow);
				    t25sc.setTableFitsPage(true);
				    t25sc.setPadding(0);
				    String head5= new String("Stock Contribution Report For Index :");
				    head5=head5+iename;
				    com.lowagie.text.Cell c25 = new com.lowagie.text.Cell(head5);
				    c25.setHorizontalAlignment(1);
				    c25.setHeader(true);
				    t25sc.addCell(c25);
				    
				    head5=new String("From Date= "+fdate+"    To Date= "+tdate);
				    c25 = new com.lowagie.text.Cell(head5);
				    c25.setHorizontalAlignment(1);
				    c25.setHeader(true);
				    t25sc.addCell(c25);
				    
				    document.add(t25sc);
				 	Table t5 = new Table(4);
				  	t5.setPadding(10);
				  	t5.setSpacing(1);
				  	
				    t5.setBackgroundColor(Color.LIGHT_GRAY);
				  	t5.setBorderWidth(1);
				  	
				  	t5.setBorderColor(Color.LIGHT_GRAY);
				  	String s5 = new String();
				  	
			  	com.lowagie.text.Cell c15= new com.lowagie.text.Cell(s5);
				c15.setHeader(true);
				String id5 = null;
				
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
				
				document.add(t5);
			
				 break;
				 
				
				}//end of switch	
			
				 
							
		}catch(Exception e)
		{
			Logging.error("Error prevented the file from being created."+e);
		}finally{
			
		}
		
		
		}
	
		
}

			