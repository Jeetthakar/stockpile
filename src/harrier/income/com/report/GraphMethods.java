/*
 * Created on Feb 27, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 
package harrier.income.com.report;

import java.awt.Font;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import java.text.DecimalFormat;

import java.util.Date;
import java.util.Locale;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
//import org.jfree.chart.demo.servlet.AreaChart;
//import org.jfree.chart.demo.servlet.CMovingAverage;
//import org.jfree.chart.demo.servlet.CombinedChart1;
import org.jfree.chart.demo.servlet.ComposeIndex;
import org.jfree.chart.demo.servlet.DatasetFactory1;

import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.StandardPieItemLabelGenerator;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.renderer.XYItemRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.DefaultPieDataset;
import org.jfree.data.MovingAverage;
import org.jfree.data.XYDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import org.jfree.chart.Legend;
import org.jfree.chart.StandardLegend;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;

import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;

import org.jfree.chart.renderer.BarRenderer;
import org.jfree.chart.renderer.LineAndShapeRenderer;

import org.jfree.data.CategoryDataset;
import org.jfree.data.DefaultCategoryDataset;

import org.jfree.data.time.Day;
import harrier.income.com.report.CMovingAverage;

import app.Connect;
import app.Logging;

*//**
 * @author sonali
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 *//*
public class GraphMethods {
	
	public static Vector field1,field2,field3,field4;
	static Connect con1 = ConnectInit.getConnect();
	public static String filename=null;
	public static String graphURL=null;
	static String indexname,indexname1;
	public static String generatePieChart(Date hitDate,HttpSession session, PrintWriter pw,int no)
	{
		//Readdata();
		PiePlot plot;
		String filename = null;
		try {
			//  Retrieve list of WebHits
			app.Logging.getDebug("Inside generatePieChart");
			//  Create and populate a PieDataSet
			DefaultPieDataset data = new DefaultPieDataset();
				for(int i=0;i<field1.size();i++)
				{
					data.setValue(((String)field1.get(i)),Double.parseDouble((String)field2.get(i)));
				   
				}
			    //  Create the chart object
					plot= new PiePlot(data);
				//plot.setInsets(new Insets(0, 5, 5, 5));
				app.Logging.getDebug(plot);
				//plot.setURLGenerator(new StandardPieURLGenerator("xy_chart.jsp","section"));
	            plot.setToolTipGenerator(new StandardPieItemLabelGenerator());
	            plot.setLabelGenerator(null);
	            plot.setNoDataMessageFont(new Font("SansSerif", Font.PLAIN, 30));
		        plot.setNoDataMessage("No data available");
		           // plot.setCircular(true);
		           // plot.setInteriorGap(0.0);			
			            //plot.setExplodePercent(1, 0.30);
            //plot.setLabelLinkPaint(Color.red);
            //plot.setLabelGap(0.02);
			//app.Logging.getDebug("before chart");			
			JFreeChart chart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
			JFreeChart chart=ChartFactory.createPieChart("Sample Pie Chart",
					data,
					true, // legend?
					true, // tooltips?
					false); // URLs?
			//app.Logging.getDebug("After chart");
			chart.setBackgroundPaint(java.awt.Color.white);
			//app.Logging.getDebug("before save as  generatePieChart");
			//  Write the chart image to the temporary directory
			ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			String path = null;
			if(no==2)
			{
				filename = ServletUtilities.saveChartAsPNG(chart, 700, 500, info, session);
				
				path=Connect.resourceurl;
				path=path+"/CoolMenus/"+filename;
				ChartUtilities.saveChartAsPNG(new java.io.File(path),chart, 700, 500);
				
			}
			if(no==3)
			{
				filename = ServletUtilities.saveChartAsPNG(chart, 500, 400, info, session);
				
				path=Connect.resourceurl;
				path=path+"/CoolMenus/"+filename;
				ChartUtilities.saveChartAsPNG(new java.io.File(path),chart, 500, 400);
				
			}
			if(no==1)
			{
				filename = ServletUtilities.saveChartAsPNG(chart, 700, 800, info, session);
				
				path=Connect.resourceurl;
				path=path+"/CoolMenus/"+filename;
				ChartUtilities.saveChartAsPNG(new java.io.File(path),chart, 700, 800);
				
			}
//			  Write the image map to the PrintWriter
				ChartUtilities.writeImageMap(pw, filename, info);
				pw.flush();
		}  catch (Exception e) {
			app.Logging.getError("Exception - " + e.getMessage());
			//e.printStackTrace(System.out);
			
		}
		app.Logging.getDebug("end of generatePieChart");
		return filename;
	}
	   
	public static String generatePieChart(Date hitDate,HttpSession session, PrintWriter pw,String[] var1,String fdate,String tdate)
	{
   		String filename = null;
    	try
		{
	        final XYDataset dataset = createDataset(var1,fdate,tdate);
	       	JFreeChart chart = createChart(dataset);
			app.Logging.getDebug("After chart");
			chart.setBackgroundPaint(java.awt.Color.white);
			app.Logging.getDebug("before save as  generatePieChart");
			ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			filename = ServletUtilities.saveChartAsPNG(chart, 700, 500, info, session);
//			Write the image map to the PrintWriter
			ChartUtilities.writeImageMap(pw, filename, info);
			pw.flush();
    	}  catch (Exception e) {
			app.Logging.getError("Exception - " + e.toString());
			e.printStackTrace(System.out);
		}
		app.Logging.getInfo("end of generatePieChart");
		return filename;
	}

    private static XYDataset createDataset(String[] var1,String fdate,String tdate) {
    	TimeSeriesCollection dataset;
        dataset = new TimeSeriesCollection();
    	final TimeSeries eur = DatasetFactory1.createFirstTimeSeries11(var1[0],fdate,tdate);         
       if((eur!=null))
       {
       		dataset.addSeries(eur);
       	}
       app.Logging.getDebug("var1[0] is "+var1[0]+" dataset is "+dataset);
       for(int i=0;i<var1.length;i++){
       		final TimeSeries jpy = DatasetFactory1.createFirstTimeSeries11(var1[i],fdate,tdate);
       	 if((jpy!=null))
         {
         	app.Logging.getDebug(" jpy is "+jpy);	
       	 	dataset.addSeries(jpy);
         }else{
         		return dataset=null;
         }
       }
       return dataset;
    }
    private static JFreeChart createChart(final XYDataset dataset) {
    	final JFreeChart chart;
    	if(dataset!=null)
    	{
	    	chart = ChartFactory.createTimeSeriesChart(
	            "Index Compare", 
	            "Date", 
	            "Value",
	            dataset, 
	            true, 
	            true, 
	            false
	        );
	        final XYItemRenderer renderer = chart.getXYPlot().getRenderer();
	        final StandardXYToolTipGenerator g = new StandardXYToolTipGenerator(
	            StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
	            new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00")
	        );
	        renderer.setToolTipGenerator(g);
    	}
    	else
	 	{
 	  		DefaultPieDataset data = new DefaultPieDataset();
 	  		PiePlot plot = new PiePlot(data);
			plot.setNoDataMessageFont(new Font("SansSerif", Font.PLAIN, 30));
			plot.setNoDataMessage("No data available");
		    chart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
	 	 }
        return chart;
    }
	public static void CompanyReaddata(Vector a)
	{
		try{
				field1=new Vector();
				field2=new Vector();
				
				Iterator iter=a.iterator(); 
				 int i=0;
				 app.Logging.getDebug("size is "+a.size());
		           while(iter.hasNext())
		           {  
			           	String year=(String)iter.next();
	                    field1.add(year);
	                    iter.next();
	                    String year1=(String)iter.next();
	                    field2.add(year1);
	                   // iter.next();
		           }
		           //app.Logging.getDebug(""+i);
	                for(int j=0;j<field1.size();j++)
	                {
	                   String str=(String)field1.get(j);
	                   app.Logging.getDebug(str);
	                }
	                for(int j=0;j<field2.size();j++)
	                {
	                   String str=(String)field2.get(j);
	                   app.Logging.getDebug(str);
	                }
				for(int i=0;i<a.size();i++)
				{
					app.Logging.getDebug((String)a.get(i));
				}
			}catch(Exception e){
				app.Logging.getError("Exception - " + e.getMessage());
			}
			app.Logging.getDebug("abc");
			
	}
  
	public void getGraphChart(HttpServletRequest varRquest, PrintWriter Prw )
    {
	   GraphMethods objGM = new GraphMethods();
	   HttpSession varPageSession = varRquest.getSession();
	   String mavg = ((varPageSession.getAttribute("varCheckAvg")).toString());
	   String span = (varPageSession.getAttribute("varSpan")).toString();
	   String chartType = (varPageSession.getAttribute("varChart")).toString();
	   String from = (varPageSession.getAttribute("from")).toString();
	   String to = (varPageSession.getAttribute("to")).toString();
	   String varIndexId = (varPageSession.getAttribute("varIndexId")).toString();
	   objGM.DisplayChart("maverage",varRquest,mavg,Prw,span,chartType,from,to,varIndexId);   
	
    }
	public String getGraphChartIndexCompare(HttpSession session, PrintWriter pw, HttpServletRequest request){
   		String filename =" ";
   		HttpSession sess=request.getSession();
   		String[] var1=null;
   		var1[0]=(String)sess.getAttribute("index_id1");
   		var1[1]=(String)sess.getAttribute("index_id2");
  		String fdate=(String)sess.getAttribute("fdate");
   		String tdate=(String)sess.getAttribute("tdate");
   		
   		System.out.println("************************************************");
   		System.out.println("indexid1="+var1[0]);
  		System.out.println("indexid2="+var1[1]);
   		System.out.println("FromDate="+fdate);
   		System.out.println("ToDate="+tdate);
   		System.out.println("************************************************");
    	
   		try
		{
	        final XYDataset dataset = createDataset(var1,fdate,tdate);
	       	JFreeChart chart = createChart(dataset);
    		app.Logging.getDebug("After chart");
			chart.setBackgroundPaint(java.awt.Color.white);
			app.Logging.getDebug("before save as  generatePieChart");
			ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			filename = ServletUtilities.saveChartAsPNG(chart, 700, 500, info, session);
//			Write the image map to the PrintWriter
			ChartUtilities.writeImageMap(pw, filename, info);
			pw.flush();
    	}  catch (Exception e) {
			app.Logging.getError("Exception - " + e.toString());
			e.printStackTrace(System.out);
		}
		app.Logging.getInfo("end of generatePieChart");
		return filename;

		
	}

	public String getGraphChartCompany(HttpSession session, PrintWriter pw )
    {
		//code from companyreddata
		
    	Vector a=(Vector)session.getAttribute("ci2");
    	
		try{
			field1=new Vector();
			field2=new Vector();
			Iterator iter=a.iterator(); 
			 int i=0;
			    while(iter.hasNext())
	           {  
		           	String year=(String)iter.next();
                    field1.add(year);
                    iter.next();
                    String year1=(String)iter.next();
                    field2.add(year1);
                }
	            for(int j=0;j<field1.size();j++)
                {
                   String str=(String)field1.get(j);
                   app.Logging.getDebug(str);
                }
                for(int j=0;j<field2.size();j++)
                {
                   String str=(String)field2.get(j);
                   app.Logging.getDebug(str);
                }
		}catch(Exception e){
			app.Logging.getError("Exception - " + e.getMessage());
		}
		
		//old code
			String filename = null;
			try {
					PiePlot plot;
					//  Retrieve list of WebHits
					app.Logging.getDebug("Inside generatePieChart");
					//  Create and populate a PieDataSet
					DefaultPieDataset data = new DefaultPieDataset();
					for(int i=0;i<field1.size();i++)
					{
						data.setValue(((String)field1.get(i)),Double.parseDouble((String)field2.get(i)));
					   
					}
				    //  Create the chart object
					plot= new PiePlot(data);
					//plot.setInsets(new Insets(0, 5, 5, 5));
					app.Logging.getDebug(plot);
		            plot.setToolTipGenerator(new StandardPieItemLabelGenerator());
		            plot.setLabelGenerator(null);
		            plot.setNoDataMessageFont(new Font("SansSerif", Font.PLAIN, 30));
			        plot.setNoDataMessage("No data available");
			        JFreeChart chart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
			        ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			        String path = null;
			        filename = ServletUtilities.saveChartAsPNG(chart, 700, 500, info, session);
				//  Write the image map to the PrintWriter
					ChartUtilities.writeImageMap(pw, filename, info);
					pw.flush();
			}  catch (Exception e) {
					app.Logging.getError("Exception - " + e.getMessage());
					//e.printStackTrace(System.out);
				
			}
			app.Logging.getDebug("end of getGraphChartCompany");
			return filename;
    }
	
	public String getGraphChartIndexCompare(HttpSession session, PrintWriter pw){
   		String filename =" ";
   		try
		{
   		System.out.println("Inside Graphmethods ");	
   		String[] var={"",""};
   		var=(String[])session.getAttribute("indexids");
		String fdate=(String)session.getAttribute("sfdate");
		String tdate=(String)session.getAttribute("stdate");
	        final XYDataset dataset = createDataset(var,fdate,tdate);
	       	JFreeChart chart = createChart(dataset);
    		app.Logging.getDebug("After chart");
			chart.setBackgroundPaint(java.awt.Color.white);
			app.Logging.getDebug("before save as  generatePieChart");
			ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			app.Logging.getDebug(" After Chart Rendering ");
			filename = ServletUtilities.saveChartAsPNG(chart, 700, 500, info, session);
//			Write the image map to the PrintWriter
			app.Logging.getDebug(" After save Chart as ");
			ChartUtilities.writeImageMap(pw, filename, info);
			app.Logging.getDebug(" After writeImage Map");
			pw.flush();
    	}  catch (Exception e) {
			app.Logging.getError("Exception - " + e.toString());
			e.printStackTrace(System.out);
		}
		app.Logging.getInfo("end of generatePieChart");
		return filename;
	}
	


	public static void DisplayChart(String report,HttpServletRequest request,String mavg,PrintWriter pw,String fspan, String fChartType,String f_from,String f_to,String f_varIndexId){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy",Locale.UK);
		 String var=f_varIndexId;
	     filename=null;graphURL=null;
	     if(fChartType==null)
	    	 fChartType="4";
	     String fromdate = f_from;
	     String toDate  =f_to;
	    Date dDate = null;
		try {
			dDate = sdf.parse("01-Aug-2002");
		} catch (Exception e) {
			Logging.getDebug(" Error : "+e.getMessage());
		}
		 StringBuffer buffer =new StringBuffer();
		  try {			  		
		  	System.out.println("var is"+var);	
		  	
			if((fChartType.equals("2"))){
				//Logging.getDebug("in bar chart");
				if(report.equals("maverage")){
					Readdata(var, fromdate, toDate);
					filename = generateChart(dDate, request.getSession(), pw,var,"b");
				}
				if(report.equals("inddivisor")){
					DivisorReaddata(var, fromdate, toDate);	
					filename =generateChart(dDate, request.getSession(), pw,var,"b");
				}
				graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + filename;
				System.out.println("graphURL is"+graphURL);
				System.out.println("filename is"+filename);		    
				buffer.append("<img src='"+ graphURL +"' width='700' height='500' border='0' usemap='#"+filename+"' >");			
			}
			
			if((fChartType.equals("1"))){
				//Logging.getDebug("in line chart");
				if(report.equals("maverage")){
					Readdata(var, fromdate, toDate);
				}
				if(report.equals("inddivisor")){
					DivisorReaddata(var, fromdate, toDate);	
				}
				filename =generateChart(dDate, request.getSession(),pw,var,"l");
				graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + filename;
				System.out.println("graphURL is"+graphURL);
				System.out.println("filename is"+filename);			    
				buffer.append("<img src='"+ graphURL +"' width='700' height='500' border='0' usemap='#"+filename+"' >");				
			}
			
			 if((fChartType.equals("4"))){
			 	Logging.getDebug("in moving average chart");
			 	if(report.equals("maverage")){
					Readdata(var, fromdate, toDate);
				}
				if(report.equals("inddivisor")){
					DivisorReaddata(var, fromdate, toDate);	
				}
				filename = generatePieChart1(dDate, request.getSession(), pw,fspan,mavg);
				graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + filename;
				System.out.println("graphURL is"+graphURL);
				System.out.println("filename is"+filename);		    
				buffer.append("<img src='"+ graphURL +"' width='700' height='500' border='0' usemap='#"+filename+"' >");
		   }
			 
		   if((fChartType.equals("3"))){
		   		//Logging.getDebug("in area chart");
		   	if(report.equals("maverage")){
				Readdata(var, fromdate, toDate);
			}
			if(report.equals("inddivisor")){
				DivisorReaddata(var, fromdate, toDate);	
			}
		   		//Logging.getDebug("before filename");
				filename =AreaChart.generateChart(dDate, request.getSession(), pw,var);
				//Logging.getDebug("after filename"+filename);
				graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + filename;
				System.out.println("graphURL is"+graphURL);
				System.out.println("filename is"+filename);						
				buffer.append("<img src='"+ graphURL +"' width='700' height='500' border='0' usemap='#"+filename+"' >");
			} 		
	      	}catch(Exception e) {System.out.println("Error : "+e.getMessage()); }                
	       
	      	//	System.out.println(buffer.toString());
	      
	      //	return buffer;
	} 	

	public static void Readdata(String iname,String fd,String td)
	{
	 indexname= ComposeIndex.getIndexName(iname);   
               
		try{
				field1=new Vector();
				field2=new Vector();
				field3=new Vector();
				field4=new Vector();
                app.Connect con = new app.Connect();
                if(Connect.con==null)
                {
				con.getConnection();
                }
				PreparedStatement pst = Connect.con.prepareStatement(con.queries.getProperty("moving_index_value"));
				pst.setString(1,iname);
				pst.setString(2,fd);
				pst.setString(3,td);
				
				ResultSet rs = pst.executeQuery(); 
				while(rs.next())
	                {
	                	    String d=rs.getString("index_value_date");
	                	    String year=d.substring(6,10);
	                	    String month=d.substring(3,5);
	                	    String day=d.substring(0,2);
	                        field1.add(year);
	                        field2.add(month);
	                        field3.add(day);
	                        String year1=rs.getString("index_closing_value");

	                        if(year1!=null)
	                        	if( year1.equals(""))
	                        		year1=null;
	                        if(year1==null)
	                        {
	                        	field4.add("0.00");
	                        	//DatasetFactory1.field4.add("0.00");
	                        	app.Logging.getDebug("closing value is null "+year1);
	                        }else{
	                        	field4.add(year1);
	                        	//DatasetFactory1.field4.add(year1);
	                        	app.Logging.getDebug("closing value is "+year1);
	                        }	                        
	                }
	              
	              
			}catch(SQLException e){
				app.Logging.getDebug(e);
			JFrame frame=new JFrame();
			JOptionPane.showMessageDialog(frame,"Unable To Connect DataBase","ERROR!",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
				
			}catch(Exception e){
				app.Logging.getDebug(e);
			}
			app.Logging.getDebug("abc");
			
	}
	
	public static void DivisorReaddata(String iname,String fd,String td)
	{
    	 indexname= ComposeIndex.getIndexName(iname);     
             app.Logging.getDebug("inside divisor readdata");
             app.Logging.getDebug(iname+" "+fd+" "+td);
               
		try{
				field1=new Vector();
				field2=new Vector();
				field3=new Vector();
				field4=new Vector();
				org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
                app.Connect con = new app.Connect();
                if(Connect.con==null)
                {
                	con.getConnection();
                }
				PreparedStatement pst = Connect.con.prepareStatement(con.queries.getProperty("index_divisor_date_wise"));
				pst.setString(1,iname);
				pst.setString(2,fd);
				pst.setString(3,td);				
				ResultSet rs = pst.executeQuery(); 
				while(rs.next())
	                {
	                	    String d=rs.getString(1);
	                	    String year=d.substring(6,10);
	                	    String month=d.substring(3,5);
	                	    String day=d.substring(0,2);
	                        field1.add(year);
	                        field2.add(month);
	                        field3.add(day);
	                        String year1=rs.getString(2);
	                        year1=ad.indexcompose(year1);
	                        field4.add(year1);
	                }  
				 for(int i=0;i<field1.size();i++)
			        {
			           String str=(String)field3.get(i)+"  "+(String)field2.get(i)+"  "+(String)field1.get(i);
			           app.Logging.getDebug(str);
			        }
			       
			        for(int i=0;i<field4.size();i++)
			        {
			           String str=(String)field4.get(i);
			           app.Logging.getDebug(str);
			        }  
			       // app.Logging.getDebug(field3.size());
			}catch(SQLException e){
				app.Logging.getDebug(e);
			JFrame frame=new JFrame();
			JOptionPane.showMessageDialog(frame,"Unable To Connect DataBase","ERROR!",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
				
			}catch(Exception e){
				app.Logging.getDebug(e);
			}
			app.Logging.getDebug("abc");
			
	}
	
	public static String getFilename() {
		return filename;
	}

	public static void setFilename(String filename) {
		GraphMethods.filename = filename;
	}

	public static String getGraphURL() {
		return graphURL;
	}

	public static void setGraphURL(String graphURL) {
		GraphMethods.graphURL = graphURL;
	}

	public String getGraphChartCompany1(HttpSession session, PrintWriter pw )
    {
	  		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy",Locale.UK);
			Date dDate = null;
			String filename = null;
			try {
					dDate = sdf.parse("01-Aug-2002");
					PiePlot plot;
			
					//  Retrieve list of WebHits
					app.Logging.getDebug("Inside generatePieChart");
					//  Create and populate a PieDataSet
					DefaultPieDataset data = new DefaultPieDataset();
					for(int i=0;i<field1.size();i++)
					{
								data.setValue(((String)field1.get(i)),Double.parseDouble((String)field2.get(i)));
					   
					}
				    //  Create the chart object
					plot= new PiePlot(data);
					//plot.setInsets(new Insets(0, 5, 5, 5));
					app.Logging.getDebug(plot);
		            plot.setToolTipGenerator(new StandardPieItemLabelGenerator());
		            plot.setLabelGenerator(null);
		            plot.setNoDataMessageFont(new Font("SansSerif", Font.PLAIN, 30));
			        plot.setNoDataMessage("No data available");
			        JFreeChart chart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
			        ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			        String path = null;
			        filename = ServletUtilities.saveChartAsPNG(chart, 700, 500, info, session);
				//  Write the image map to the PrintWriter
					ChartUtilities.writeImageMap(pw, filename, info);
					pw.flush();
			}  catch (Exception e) {
					app.Logging.getError("Exception - " + e.getMessage());
					//e.printStackTrace(System.out);
				
			}
			app.Logging.getDebug("end of getGraphChartCompany");
			return filename;
    }
	
	public static void StockcontriReaddata(HttpServletRequest request)
	{
		try{
				field1=new Vector();
				field2=new Vector();
				Vector a = (Vector)((request.getSession()).getAttribute("colected_vector"));
				Iterator iter=a.iterator(); 
				 int i=0;
				 app.Logging.getDebug("size is "+a.size());
		           while(iter.hasNext())
		           {  
			           	String year=(String)iter.next();
	                    field1.add(year);
	                    iter.next();
	                    iter.next();
	                    String year1=(String)iter.next();
	                    field2.add(year1);
	                   // iter.next();
		           }
		          // app.Logging.getDebug(""+i);
	                for(int j=0;j<field1.size();j++)
	                {
	                   String str=(String)field1.get(j);
	                   app.Logging.getDebug(str);
	                }
	                for(int j=0;j<field2.size();j++)
	                {
	                   String str=(String)field2.get(j);
	                   app.Logging.getDebug(str);
	                }
				for(int i=0;i<a.size();i++)
				{
					app.Logging.getDebug((String)a.get(i));
				}
			}catch(Exception e){
				app.Logging.getError("Exception - " + e.getMessage());
			}
			app.Logging.getDebug("abc");
			
	}

	public static void ReaddataCompose(HttpServletRequest request )
	{
		
		app.Logging.getDebug("dfyfgyh");
		try{
				field1=new Vector();
				field2=new Vector();				
				Vector a = (Vector)((request.getSession()).getAttribute("colected_vector"));
				Iterator iter=a.iterator(); 
				 int i=0;
				 app.Logging.getDebug("vector size is "+a.size());
		           while(iter.hasNext())
		           {  
		           		iter.next();
		           		String year=(String)iter.next();
	                    field1.add(year);
	                    iter.next();
	                    iter.next();
	                    iter.next();
	                    iter.next();
	                    iter.next();
	                    iter.next();
	                    iter.next();
	                    iter.next();
	                    String year1=(String)iter.next();
	                    field2.add(year1);
	                    iter.next();
	                    iter.next();
		           }
		           app.Logging.getDebug("value of field "+field2); 
			}catch(Exception e){
				app.Logging.getError("Exception - " + e.getMessage());
			}
			app.Logging.getDebug("abc");
			
	}
	
	public static void ReaddataIndweight(HttpServletRequest request)
	{
		try{
				field1=new Vector();
				field2=new Vector();
				field3=new Vector();
				Vector a = (Vector)((request.getSession()).getAttribute("colected_vector"));
				Iterator iter=a.iterator(); 
				 int i=0;
				 app.Logging.getDebug("size is "+a.size());
		           while(iter.hasNext())
		           {  
			           	String year=(String)iter.next();
	                    field1.add(year);
	                    String year2=(String)iter.next();
	                    field3.add(year2);
	                    String year1=(String)iter.next();
	                    field2.add(year1);
	                    
		           }
		           //app.Logging.getDebug(""+i);
	                for(int j=0;j<field1.size();j++)
	                {
	                   String str=(String)field1.get(j);
	                   app.Logging.getDebug(str);
	                }
	                for(int j=0;j<field2.size();j++)
	                {
	                   String str=(String)field2.get(j);
	                   app.Logging.getDebug(str);
	                }
				
			}catch(Exception e){
				app.Logging.getError("Exception - " + e.getMessage());
			}
			app.Logging.getDebug("abc");
			
	}
	
	public void getGraphChart1(HttpServletRequest varRquest, PrintWriter Prw )
    {
	   GraphMethods objGM = new GraphMethods();
	   HttpSession varPageSession = varRquest.getSession();
	   String mavg=null;
	   System.out.print("varCheckmavg value>>>>> "+varPageSession.getAttribute("varCheckAvg"));
	   if((varPageSession.getAttribute("varCheckAvg"))!=null)		   
		   mavg = ((varPageSession.getAttribute("varCheckAvg")).toString());
	   else
		   mavg="unchecked";
	   Logging.getDebug(" *** mavg = " + mavg);
	   String span = (varPageSession.getAttribute("varSpan")).toString();
	   String chartType = (varPageSession.getAttribute("varChart")).toString();
	   Logging.getDebug(" *** chartType  "+chartType);
	   String from = (varPageSession.getAttribute("from")).toString();
	   String to = (varPageSession.getAttribute("to")).toString();
	   
	   String varIndexId = (varPageSession.getAttribute("varIndexId")).toString();
	   Logging.getDebug(" *** varIndexId"+varIndexId);
	   String chartTp=(varPageSession.getAttribute("chartType")).toString();
	   Logging.getDebug(" *** chartTp"+chartTp);
	   Logging.getDebug("span====="+span);
	   objGM.DisplayChart(chartTp,varRquest,mavg,Prw,span,chartType,from,to,varIndexId);   
	
    }
	
	public static String generateChart(Date hitDate,HttpSession session, PrintWriter pw,String iname,String vchart)
	{
   		String filename = null;
    	try
		{
        final JFreeChart chart = createChart(iname,vchart);
        app.Logging.getDebug("After chart");
		chart.setBackgroundPaint(java.awt.Color.white);
		app.Logging.getDebug("before save as  generatePieChart");
		ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
		filename = ServletUtilities.saveChartAsPNG(chart, 700, 500, info, session);
//			Write the image map to the PrintWriter
		ChartUtilities.writeImageMap(pw, filename, info,true);
		pw.flush();
		}  catch (Exception e) {
			app.Logging.getError("Exception - " + e.toString());
			e.printStackTrace(System.out);
			 
		}
		app.Logging.getDebug("end of generatePieChart");
		return filename;
	}
	 
	
	private static JFreeChart createChart(String iname,String vchart) {
   	 final CategoryAxis domainAxis = new CategoryAxis("Date");
   	 Logging.getDebug("before setting axis line visible false ");
   	 domainAxis.setAxisLineVisible(false);    	
        final CombinedDomainCategoryPlot plot = new CombinedDomainCategoryPlot(domainAxis);
   	
        if(vchart.equals("b")){
	        final CategoryDataset dataset1 = createDataset1(iname);       
	        final NumberAxis rangeAxis1 = new NumberAxis("Value");
	        rangeAxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	        //final LineAndShapeRenderer renderer1 = new LineAndShapeRenderer();
	        final BarRenderer renderer1 = new BarRenderer();
	        renderer1.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
	        final CategoryPlot subplot1 = new CategoryPlot(dataset1, null, rangeAxis1, renderer1);
//	      skip some labels if they overlap...
	      //  CategoryAxis domainAxis1 = (CategoryAxis) plot.getDomainAxis();
	      // Logging.getDebug("before setting axis line visible false ");
	       // domainAxis1.setAxisLineVisible(false);//.setSkipCategoryLabelsToFit(true);
	        subplot1.setDomainGridlinesVisible(true);
	        subplot1.setNoDataMessageFont(new Font("SansSerif", Font.PLAIN, 30));
	        subplot1.setNoDataMessage("No data available");
	        plot.add(subplot1, 1);
   	}
   	if(vchart.equals("l")){
	        final CategoryDataset dataset2 = createDataset2(iname);
	        final NumberAxis rangeAxis2 = new NumberAxis("Value");
	        rangeAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	        final LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
	        renderer2.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
	        final CategoryPlot subplot2 = new CategoryPlot(dataset2, null, rangeAxis2, renderer2);
	        subplot2.setDomainGridlinesVisible(true);
	        subplot2.setNoDataMessageFont(new Font("SansSerif", Font.PLAIN, 30));
	        subplot2.setNoDataMessage("No data available");
	        plot.add(subplot2, 1);
      }
       
       
       final JFreeChart result = new JFreeChart(
           " ",
           new Font("SansSerif", Font.BOLD, 12),
           plot,
           true
       );
       final StandardLegend legend = (StandardLegend) result.getLegend();
       legend.setDisplaySeriesShapes(true);
       legend.setShapeScaleX(1.5);
       legend.setShapeScaleY(1.5);
       legend.setDisplaySeriesLines(true);
       result.getLegend().setAnchor(Legend.SOUTH);
       return result;

   }
	 
	private static CategoryDataset createDataset1(String iname) {
	        
	       
	    	String indexname= ComposeIndex.getIndexName(iname); 
	       
	        // create the dataset...
	        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	        for(int i=(((field4).size())-1);i>=0;i--){
	    		String st="MonthConstants."+(String)(field2).get(i);
	            dataset.addValue(Double.parseDouble((String)(field4).get(i)), indexname,new Day(Integer.parseInt((String)(field3).get(i)), Integer.parseInt((String)(field2).get(i)), Integer.parseInt((String)(field1).get(i))));
	    	}
	        return dataset;
	                
	    }
	 
	private static CategoryDataset createDataset2(String iname) {
	        
	       

	    	String indexname= ComposeIndex.getIndexName(iname); 
	       
	        // create the dataset...
	        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	        for(int i=(((field4).size())-1);i>=0;i--){
	    		String st="MonthConstants."+(String)(field2).get(i);
	            dataset.addValue(Double.parseDouble((String)(field4).get(i)), indexname,new Day(Integer.parseInt((String)(field3).get(i)), Integer.parseInt((String)(field2).get(i)), Integer.parseInt((String)(field1).get(i))));
	    	}
	        return dataset;        
	                
	    }
	
	public static void assign_name(String index_name) {
    	indexname1=index_name;
    }
 
	public static TimeSeries createFirstTimeSeries1() {
        
        TimeSeries t2 = new TimeSeries(indexname);       
       if(field3.size()!=0)
       {
	   try {
               for(int i=0;i<field4.size();i++)
                {
                   String st="MonthConstants."+(String)field2.get(i);
                    t2.add(new Day(Integer.parseInt((String)field3.get(i)), Integer.parseInt((String)field2.get(i)), Integer.parseInt((String)field1.get(i))), new Double((String)field4.get(i)));
                  
                }
        }catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return t2;
       }else
       {
       		return t2=null;
       }
    }
	
	*//**
     * Creates a sample dataset.
     * 
     * @return a sample dataset.
     *//*
    private static XYDataset createDataset1(String mavgspan,String view) {
    	Logging.getDebug(" view is "+view);
    	int Avg_span=(int)Integer.parseInt(mavgspan);
       // final TimeSeries eur = DatasetFactory1.createFirstTimeSeries1();
    	final TimeSeries eur = GraphMethods.createFirstTimeSeries1();
       //final TimeSeries jpy = DatasetFactory1.createJPYTimeSeries1();
        final TimeSeriesCollection dataset;
        if(eur!=null )
       {
        final TimeSeries mav = MovingAverage.createMovingAverage(
            eur, "moving average", Avg_span, 0
        );
        dataset = new TimeSeriesCollection();
        dataset.addSeries(eur);
        //dataset.addSeries(jpy);
        if(view!=null && view.equals("checked")){
        	dataset.addSeries(mav);
        }
        return dataset;
       }else
       {
       	 	return dataset=null;
       }
    }
    
    public static String generatePieChart1(Date hitDate,HttpSession session, PrintWriter pw,String maveragespan,String view)
	{
   		String filename = null;
   		Logging.getDebug(" in generatePieChart view is "+view);
    	try
		{
	        XYDataset dataset = createDataset1(maveragespan,view);
	       	JFreeChart chart = createChart1(dataset);
			Logging.getDebug("After chart");
			chart.setBackgroundPaint(java.awt.Color.white);
			Logging.getDebug("before save as  generatePieChart");
			ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			filename = ServletUtilities.saveChartAsPNG(chart, 700, 500, info, session);
//			Write the image map to the PrintWriter
			ChartUtilities.writeImageMap(pw, filename, info);
			pw.flush();
    	}  catch (Exception e) {
			app.Logging.getError("Exception - " + e.toString());
			e.printStackTrace(System.out);				
		}
		Logging.getDebug("end of generatePieChart");
		return filename;
	}
    
    private static JFreeChart createChart1(final XYDataset dataset) {
    	final JFreeChart chart;
    	if(dataset!=null)
    	{
	    	chart = ChartFactory.createTimeSeriesChart(
	            "Divisor Movement", 
	            "Date", 
	            "Value",
	            dataset, 
	            true, 
	            true, 
	            false
	        );
	        final XYItemRenderer renderer = chart.getXYPlot().getRenderer();
	        final StandardXYToolTipGenerator g = new StandardXYToolTipGenerator(
	            StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
	            new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00")
	        );
	        renderer.setToolTipGenerator(g);
    	}
    	else
	 	{
 	  		DefaultPieDataset data = new DefaultPieDataset();
 	  		PiePlot plot = new PiePlot(data);
			plot.setNoDataMessageFont(new Font("SansSerif", Font.PLAIN, 30));
			plot.setNoDataMessage("No data available");
		    chart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
	 	 }
        return chart;
    }
}



*/