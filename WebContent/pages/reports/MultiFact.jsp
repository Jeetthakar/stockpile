
<!-- Multi purpose Jsp mainly used  for Export to Xml for batch reports at a stroke ----- created by neha ---- 13th june 2007 -->
<%@ page import="java.util.*,java.io.*"%>
<%@ page  import="harrier.income.com.report.*"%>
<%@ page language="java" import="app.*"%>
<%@page import="org.jfree.chart.demo.servlet.ComposeIndex"%>
<%@page import="com.harrier.initializeation.ConnectInit"%><%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setHeader("Expires","0");
%>

 <jsp:useBean id="batchReportBeanN" scope="session" class="harrier.income.com.report.BatchReportFormN"/>
<%
harrier.income.com.report.BatchReportFormN abc=(harrier.income.com.report.BatchReportFormN)session.getAttribute("batchReportBeanN");
 //harrier.income.com.report.AddFactData data=new  harrier.income.com.report.AddFactData();
 org.jfree.chart.demo.servlet.CalculateCorrelation cc=new org.jfree.chart.demo.servlet.CalculateCorrelation();
 //org.jfree.chart.demo.servlet.ComposeIndex ci=new org.jfree.chart.demo.servlet.ComposeIndex();
  ComposeIndex ci = ConnectInit.getComposeIndex();
  org.jfree.chart.demo.servlet.IndexCompareOHLC comp=new org.jfree.chart.demo.servlet.IndexCompareOHLC();
	    session.setAttribute("ci2Composition",abc.getVw());
	 	session.setAttribute("ci2divisor",abc.getVExcel());
	 	session.setAttribute("ci2pepb",abc.getVExcel20());
        session.setAttribute("ci2Cwv",abc.getCompanyWeightageVector());
          session.setAttribute("ci2SD",abc.getStkDividentVec());
          session.setAttribute("ci2Cap",abc.getCapitalChangeVec());
          session.setAttribute("ci2Tr",abc.getTrdVolVec());
          session.setAttribute("ci2Scc",abc.getVi());
          session.setAttribute("ci2Iww",abc.getViw());
          session.setAttribute("ci2IRV",abc.getVExcel());
    
		ArrayList list=new ArrayList();
		if(abc.is1()) // index composition 
        {
			     abc.getTabledata3();
	             String var=abc.get31();
	             String type="1";
	             String iename=abc.getIndex_name11();
	             String from=null;
	             String to=null;
	             String idxname=null;
	            AddFactData  data1=new AddFactData(var,type,iename,from,to,idxname);          
				list.add(data1);
					
        }
		 if(abc.is4()){  // indexpepb
			
			abc.getIndex_arraylist();
	    	String var=abc.get34();
	    	String type="20";
	    	String iename=abc.getIndex_name11();
	    	String from=abc.get14();
			String to=abc.get24();
			String idxname=null;
			 AddFactData  data2=new AddFactData(var,type,iename,from,to,idxname);          
				list.add(data2);
	    	
	    	
			
		}
		 if(abc.is2()) // index divisor
        {
		abc.getTableData2();
    	String var=abc.get32();
	    String type="19";
		String iename=abc.getIndex_name11();
		String from=abc.get12();
		String to=abc.get22();
		String idxname=null;
		AddFactData  data3=new AddFactData(var,type,iename,from,to,idxname);          
		list.add(data3);
        }	
		
		
		
		 if(abc.is5()){  // stock details report
			abc.getTableDatas();
			String stk_name6= (String)abc.getStock_name();//stock name
	  		String ind_name6=(String)abc.getIndex_name11();// index name
	  		String[] var6= abc.get45();	//stockid
	  		String var=var6[1];
	  		String to = abc.get25();
	  		String from = abc.get15();
	  		String type="6";
	  		String 	iename = abc.getIndex_name11();
	  		String idxname=null;
	  		 AddFactData  data5=new AddFactData(var,type,iename,from,to,idxname);          
			list.add(data5);
	  	
		}
		
		if(abc.is7()){  //CompanyWiseWeightage
		
		        abc.getCompanyWeightage();
            	String var=abc.get37();
            	String iename=abc.getIndex_name11();
            	String type="2";
	           	String from=null;
	            String to=null;
	            String idxname=null;
	            AddFactData  data4=new AddFactData(var,type,iename,from,to,idxname);          
				list.add(data4);		
            			
		}
		if(abc.is105()){
		         abc.getTableDataSD();
		        String indExch22= "";
	  	        String var=abc.get61();
	  	        String to = abc.get125();
	  	        String from = abc.get115();
	  	        String iename=null;
	            String type="22"; 
	
	           	if(var=="1"){
	  	  	indExch22 = abc.get155();
	  		iename=abc.getExchange_name();
	    	} else{
	  		indExch22 = abc.get135();
	  		iename  = abc.getIndex_name11();
	  	    }
	  	     String idxname=null;
	  	     AddFactData  data6=new AddFactData(var,type,iename,from,to,idxname);          
				list.add(data6);
	    }
	    if(abc.is6()){
	   	        abc.getTableDatac();
	  		 String type="16"; 
	  		 String stk_name="A.C.C.";
	  		 String var=abc.get56();
	  		 String iename =abc.getExchange_name();
	  		 String to = abc.get26();
	  		 String from = abc.get16();
	  		 String idxname=null;
	  	     AddFactData  data7=new AddFactData(var,type,iename,from,to,idxname);          
				list.add(data7);
	   
	   
	   
	    }
	     if(abc.is104()){
	     abc.getTableDataTr();
		String indExch= "";
	  	String var=abc.get62();
	  	String to = abc.get124();
	  	String from = abc.get114();
	  	String iename=null;
	  	String type="25"; 
	  	if(var=="1"){
	  		indExch = abc.get154();
	  		iename=abc.getExchange_name();
	  	  }
		  else{
			indExch = abc.get134();
			iename=abc.getIndex_name11();
		   }
		 String idxname=null;
		   AddFactData  data8=new AddFactData(var,type,iename,from,to,idxname);          
				list.add(data8);
	   
	     }
	     if(abc.is9()){
	            abc.getStockcotriIndexchange();
             	
	  			String var=abc.get39();
	  			String iename=abc.getIndex_name11();
	  			String to = abc.get29();
	  			String from = abc.get19();
	  			String type="5";
	  			String idxname=null;
		        AddFactData  data9=new AddFactData(var,type,iename,from,to,idxname);          
				list.add(data9);
	  			
	    }
	      if(abc.is8()){  //Industry Wise weightage
	            abc.getIndweighttable();
	  		    String var=abc.get38();
	  		    String iename=abc.getIndex_name11();
	  		    String from=null;
	  		    String to=null;
	  		    String type="3";
	  			String idxname=null;
		        AddFactData  data10=new AddFactData(var,type,iename,from,to,idxname);          
				list.add(data10);
	  	
	    }
	    if(abc.is103()){        //Index Return and volatility
	                     String var=null;
	      				 String from = abc.get113();
						 String to = abc.get123();
						 String type="14";
						 String[] indexList = abc.get73();
						  session.setAttribute("idxList",indexList);
						 String iename=null;
						 String idxname=null;
		        AddFactData  data11=new AddFactData(var,type,iename,from,to,idxname);          
				list.add(data11);
	    }
	    
	            if(abc.is102()){         // Index Correlation
	           String from = abc.get112();
         	   String to =abc.get122();
        	   String[] var1=abc.get74();
			     Object arr1 = null;
			    
         	   session.setAttribute("arr1",var1);
                String url ="";
         	if(var1 != null)
			    {
				   for(int i=0;i < var1.length;i++)
				    {
						url = url + "&D1=" + var1[i];
				     }
			  }	
			
			  
			  %>     
		
		<%	
		       String type="15";
			    Vector vid=cc.getId_name(var1); 	
			    Object ci1 = null;
	            String var=null;
         		session.setAttribute("url",url);
				session.setAttribute("ci1",new Vector(vid));
				Vector cor=cc.getCalculatedCorrelation(from,to);
           		Object ci2 = null;
				session.setAttribute("ci2",new Vector(cor));
				String iename=null;
				String idxname=null;
				
				AddFactData  data12=new AddFactData(var,type,iename,from,to,idxname);          
				list.add(data12);
	      }
	   if(abc.is101()){   // Index compare OHLC 
	         String type="10";
	         String from =abc.get111();
        	 String to =abc.get121();
			 String[] var1=abc.get72();
			 String url1 = "";
			  if(var1 != null)
			{
				for(int i=0;i < var1.length;i++){
						
					url1 = url1 + "&D1=" + var1[i];
				}
				
			}
			%>
			<%	
				comp.setVector_compareOHLC1(var1,from,to);
						Vector v3=comp.getVector_compareOHLC();	
	        if((v3.size())!=0){
	                Vector v11=comp.ArrangeVectorCompareOHLC(v3); 
      				Object ci2h = null;
					session.setAttribute("ci2h",new Vector(v11));
      				Vector vid=comp.getVector_vid();
	                Object vec_ind = null;
					session.setAttribute("vec_ind",new Vector(vid));
	        
	        }
	        String var=null;
	           String iename=null;
				String idxname=null;
	            AddFactData  data13=new AddFactData(var,type,iename,from,to,idxname);          
				list.add(data13);
	   
	   }
	   
	     	session.setAttribute("getdata",list);
%>