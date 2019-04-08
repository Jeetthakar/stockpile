/*
 * Created on Sept 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.report;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.demo.servlet.AdjustDecimal;
import org.jfree.chart.demo.servlet.ComposeIndex;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategorySeriesLabelGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.MovingAverage;
import org.jfree.data.time.TimePeriodAnchor;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.DefaultHighLowDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.date.DateUtilities;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.StandardGradientPaintTransformer;
import org.jfree.util.Rotation;

import app.Connect;

import com.harrier.initializeation.ConnectInit;



/**
 * @author lokesh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GraphMethodsPf {
	
	static Logger Logging = Logger.getLogger(GraphMethodsPf.class);
	public static Vector field1,field2,field3,field4;
	public static Vector move1,move2,move3,move4;
	public static Vector move14,move24,move34,move44;
	public static Vector move11,move21,move31,move41;
	public static Vector move12,move22,move32,move42;
	public static Vector Iw1,Iw2,Iw3;
	public static Vector sect1,sect2,sect3;
	public static Vector sect11,sect21,sect31;
	public static Vector sect12,sect22,sect32;
	static ArrayList field5,field6,field7,field8;
	
	public static Vector SectContri1,SectContri2;
	static String indexSectContri;
	
	//static Connect con1 = ConnectInit.getConnect();
//	static Connect con = ConnectInit.getConnect();
	//static Connection connection=null;
	public static String filename=null;
	public static String filenameSect=null;
	public static String fileIndWtg=null;
	public static String graphURL=null;
	static String indexname,indexname1;
	static String indexnameC1,indexnameC2;
	static String indexnameC3="-";
	static String[] indMultiSect =null;
	static String[] indexNameSect = null;
	static String[] sector1 =null;
	static String[] sector11 =null;
	static String[] sector12 =null;
	static int indLength=0;
	static double maxInd=0.0D;
	static IndexComposeReportMethod Icr = new IndexComposeReportMethod();
	private static ResultSet rst;
	ArrayList tabledata=null;
	static Vector vw=null;
	
	public static String generatePieChart(Date hitDate,HttpSession session, PrintWriter pw,int no)
	{
		//Readdata();
		PiePlot plot;
		String filename = null;
		try {
			//  Retrieve list of WebHits
			Logging.debug("Inside generatePieChart");
			//  Create and populate a PieDataSet
			DefaultPieDataset data = new DefaultPieDataset();
				for(int i=0;i<field1.size();i++)
				{
					data.setValue(((String)field1.get(i)),Double.parseDouble((String)field2.get(i)));
				   
				}
			    //  Create the chart object
					plot= new PiePlot(data);
				//plot.setInsets(new Insets(0, 5, 5, 5));
				Logging.debug(plot+"");
				//plot.setURLGenerator(new StandardPieURLGenerator("xy_chart.jsp","section"));
	            //plot.setToolTipGenerator(new StandardPieItemLabelGenerator());
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
			/*JFreeChart chart=ChartFactory.createPieChart("Sample Pie Chart",
					data,
					true, // legend?
					true, // tooltips?
					false); // URLs?*/
			//app.Logging.getDebug("After chart");
			chart.setBackgroundPaint(java.awt.Color.white);
		
			ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			String path = null;
			if(no==2)
			{
				filename = ServletUtilities.saveChartAsPNG(chart, 700, 500, info, session);
				/*
				path=Connect.resourceurl;
				path=path+"/CoolMenus/"+filename;
				ChartUtilities.saveChartAsPNG(new java.io.File(path),chart, 700, 500);
				*/
			}
			if(no==3)
			{
				filename = ServletUtilities.saveChartAsPNG(chart, 500, 400, info, session);
				/*
				path=Connect.resourceurl;
				path=path+"/CoolMenus/"+filename;
				ChartUtilities.saveChartAsPNG(new java.io.File(path),chart, 500, 400);
				*/
			}
			if(no==1)
			{
				filename = ServletUtilities.saveChartAsPNG(chart, 700, 800, info, session);
				/*
				path=Connect.resourceurl;
				path=path+"/CoolMenus/"+filename;
				ChartUtilities.saveChartAsPNG(new java.io.File(path),chart, 700, 800);
				*/
			}
//			  Write the image map to the PrintWriter
				//ChartUtilities.writeImageMap(pw, filename, info);
				pw.flush();
		}  catch (Exception e) {
			Logging.error("Exception - P1" + e.getMessage());
			//e.printStackTrace(System.out);
			
		}
		Logging.debug("end of generatePieChart");
		return filename;
	}
	
	public static void CompanyReaddata(Vector a)
	{
		try{
				field1=new Vector();
				field2=new Vector();
				
				Iterator iter=a.iterator(); 
				 int i=0;
				 Logging.debug("size is "+a.size());
		           while(iter.hasNext())
		           {  
			           	String year=(String)iter.next();
	                    field1.add(year);
	                    iter.next();
	                    String year1=(String)iter.next();
	                    field2.add(year1);
	                   // iter.next();
		           }
		           //Logging.getDebug(""+i);
	                for(int j=0;j<field1.size();j++)
	                {
	                   String str=(String)field1.get(j);
	                   Logging.debug(str);
	                }
	                for(int j=0;j<field2.size();j++)
	                {
	                   String str=(String)field2.get(j);
	                   Logging.debug(str);
	                }
				/*for(int i=0;i<a.size();i++)
				{
					Logging.getDebug((String)a.get(i));
				}*/
			}catch(Exception e){
				Logging.error("Exception - P2" + e.getMessage());
			}
			Logging.debug("abc");
			
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
                   Logging.debug(str);
                }
                for(int j=0;j<field2.size();j++)
                {
                   String str=(String)field2.get(j);
                   Logging.debug(str);
                }
		}catch(Exception e){
			Logging.error("Exception - P3" + e.getMessage());
		}
		
		//old code
			String filename = null;
			try {
					PiePlot plot;
					//  Retrieve list of WebHits
					Logging.debug("Inside generatePieChart");
					//  Create and populate a PieDataSet
					DefaultPieDataset data = new DefaultPieDataset();
					for(int i=0;i<field1.size();i++)
					{
						data.setValue(((String)field1.get(i)),Double.parseDouble((String)field2.get(i)));
					   
					}
				    //  Create the chart object
					plot= new PiePlot(data);
					//plot.setInsets(new Insets(0, 5, 5, 5));
					Logging.debug(plot+"");
		           // plot.setToolTipGenerator(new StandardPieItemLabelGenerator());
		            plot.setLabelGenerator(null);
		            plot.setNoDataMessageFont(new Font("SansSerif", Font.PLAIN, 30));
			        plot.setNoDataMessage("No data available");
			        JFreeChart chart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
			        ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			        String path = null;
			        filename = ServletUtilities.saveChartAsPNG(chart, 700, 500, info, session);
				//  Write the image map to the PrintWriter
					//ChartUtilities.writeImageMap(pw, filename, info);
					pw.flush();
			}  catch (Exception e) {
					Logging.error("Exception - P4" + e.getMessage());
					//e.printStackTrace(System.out);
				
			}
			Logging.debug("end of getGraphChartCompany");
			return filename;
    }
	
	public static void Readdata(String iname,String fd,String td)
	{
	 indexname= ComposeIndex.getIndexName(iname);   
        Connection connection=null;    
    //    Connect con = new Connect();
        Connect con = ConnectInit.getConnect();
		try{
			move1=new Vector();
			move2=new Vector();
			move3=new Vector();
			move4=new Vector();
              
//              Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
                if(connection==null)
                {
                	connection=con.getdbConnection();
                }
			//	PreparedStatement pst = connection.prepareStatement(con.queries.getProperty("moving_index_value"));
                PreparedStatement pst = connection.prepareStatement(ConnectInit.queries.getProperty("moving_index_value"));
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
	                	    move1.add(year);
	                	    move2.add(month);
	                	    move3.add(day);
	                        String year1=rs.getString("index_closing_value");

	                        if(year1!=null)
	                        	if( year1.equals(""))
	                        		year1=null;
	                        if(year1==null)
	                        {
	                        	move4.add("0.00");
	                        	//DatasetFactory1.field4.add("0.00");
	                        	Logging.debug("closing value is null "+year1);
	                        }else{
	                        	move4.add(year1);
	                        	//DatasetFactory1.field4.add(year1);
	                        	Logging.debug("closing value is "+year1);
	                        }	                        
	                }
	              
	              
			}catch(SQLException e){
				Logging.debug(e+"");
/*			JFrame frame=new JFrame();
			JOptionPane.showMessageDialog(frame,"Unable To Connect DataBase","ERROR!",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
*/				
			}catch(Exception e){
				Logging.debug(e+"");
			}
//			Close the Dynamic Connection : Manoj Adekar
			finally {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ee) {
					Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
				}
			}
			Logging.debug("abc");
			
	}
	
	public static void Readdata1(String iname,String fd,String td)
	{
		Connect con = ConnectInit.getConnect();
		Connection connection=null;
	 indexname= ComposeIndex.getIndexName(iname);   
	 	indexnameC1= indexname;  
	 	move11=new Vector();
		move21=new Vector();
		move31=new Vector();
		move41=new Vector();
		try{
//			Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()  
			if(connection==null)
					connection=con.getdbConnection();
	

		//		PreparedStatement pst = connection.prepareStatement(con.queries.getProperty("moving_index_value"));
			PreparedStatement pst = connection.prepareStatement(ConnectInit.queries.getProperty("moving_index_value"));				
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
	                	    move11.add(year);
	                	    move21.add(month);
	                	    move31.add(day);
	                        String year1=rs.getString("index_closing_value");

	                        if(year1!=null)
	                        	if( year1.equals(""))
	                        		year1=null;
	                        if(year1==null)
	                        {
	                        	move41.add("0.00");
	                        	//DatasetFactory1.field4.add("0.00");
	                        	Logging.debug("closing value is null "+year1);
	                        }else{
	                        	move41.add(year1);
	                        	//DatasetFactory1.field4.add(year1);
	                        	Logging.debug("closing value is "+year1);
	                        }	                        
	                }
	              
	              
			}catch(SQLException e){
				Logging.debug(e+"");
/*			JFrame frame=new JFrame();
			JOptionPane.showMessageDialog(frame,"Unable To Connect DataBase","ERROR!",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
	*/			
			}catch(Exception e){
				Logging.debug(e+"");
			}
			finally{
		    	try{
		    		if(connection!=null)
		    			connection.close();
		    		}
		    		catch(Exception ee)
		    		{
		    			Logging.error(" Error : Unable to close Connection "+ee.getMessage());
		    		}
			}
			Logging.debug("abc");
			
	}
	
	public static void Readdata2(String iname,String fd,String td)
	{
	 indexname= ComposeIndex.getIndexName(iname);   
	 indexnameC2= indexname;         
	Connection connection=null;
	 try{
			move12=new Vector();
			move22=new Vector();
			move32=new Vector();
			move42=new Vector();
				
       //         Connect con = new Connect();
			Connect con = ConnectInit.getConnect();
                if(connection==null)
					connection=con.getdbConnection();
			
			//	PreparedStatement pst = connection.prepareStatement(con.queries.getProperty("moving_index_value"));
                PreparedStatement pst = connection.prepareStatement(ConnectInit.queries.getProperty("moving_index_value"));
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
	                	    move12.add(year);
	                	    move22.add(month);
	                	    move32.add(day);
	                        String year1=rs.getString("index_closing_value");

	                        if(year1!=null)
	                        	if( year1.equals(""))
	                        		year1=null;
	                        if(year1==null)
	                        {
	                        	move42.add("0.00");
	                        	//DatasetFactory1.field4.add("0.00");
	                        	Logging.debug("closing value is null "+year1);
	                        }else{
	                        	move42.add(year1);
	                        	//DatasetFactory1.field4.add(year1);
	                        	Logging.debug("closing value is "+year1);
	                        }	                        
	                }
	              
	              
			}catch(SQLException e){
				Logging.debug(e+"");
	/*		JFrame frame=new JFrame();
			JOptionPane.showMessageDialog(frame,"Unable To Connect DataBase","ERROR!",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		*/		
			}catch(Exception e){
				Logging.debug(e+"");
			}
			finally{
		    	try{
		    		if(connection!=null)
		    			connection.close();
		    		}
		    		catch(Exception ee)
		    		{
		    			Logging.error(" Error : Unable to close Connection "+ee.getMessage());
		    		}
			}
			Logging.debug("abc");
			
	}
	
	public static void Readdata3(String iname,String fd,String td)
	{
		Connection connection=null;
		indexname= ComposeIndex.getIndexName(iname);   
		indexnameC3= indexname;         
		try{
			move14=new Vector();
			move24=new Vector();
			move34=new Vector();
			move44=new Vector();
//			Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		//	Connect con = new Connect();
			Connect con = ConnectInit.getConnect();
        	if(connection==null)
					connection=con.getdbConnection();
			
		//		PreparedStatement pst = connection.prepareStatement(con.queries.getProperty("moving_index_value"));
        	PreparedStatement pst = connection.prepareStatement(ConnectInit.queries.getProperty("moving_index_value"));
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
	                	    move14.add(year);
	                	    move24.add(month);
	                	    move34.add(day);
	                        String year1=rs.getString("index_closing_value");

	                        if(year1!=null)
	                        	if( year1.equals(""))
	                        		year1=null;
	                        if(year1==null)
	                        {
	                        	move44.add("0.00");
	                        	//DatasetFactory1.field4.add("0.00");
	                        	Logging.debug("closing value is null "+year1);
	                        }else{
	                        	move44.add(year1);
	                        	//DatasetFactory1.field4.add(year1);
	                        	Logging.debug("closing value is "+year1);
	                        }	                        
	                }
	              
	              
			}catch(SQLException e){
				Logging.debug(e+"");
/*			JFrame frame=new JFrame();
			JOptionPane.showMessageDialog(frame,"Unable To Connect DataBase","ERROR!",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
	*/			
			}catch(Exception e){
				Logging.debug(e+"");
			}
			finally{
		    	try{
		    		if(connection!=null)
		    			connection.close();
		    		}
		    		catch(Exception ee)
		    		{
		    			Logging.error(" Error : Unable to close Connection "+ee.getMessage());
		    		}
			}
			Logging.debug("abc");
			
	}
	
	
	public static void DivisorReaddata(String iname,String fd,String td)
	{
    	Connection connection=null; 
		indexname= ComposeIndex.getIndexName(iname);     
             Logging.debug("inside divisor readdata");
             Logging.debug(iname+" "+fd+" "+td);
               
		try{
			move1=new Vector();
			move2=new Vector();
			move3=new Vector();
			move4=new Vector();
		//org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
			AdjustDecimal ad = ConnectInit.getAdjustDecimal();
			//     Connect con = new Connect();
				Connect con = ConnectInit.getConnect();
//              Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
                if(connection==null)
                {
                	connection=con.getdbConnection();
                }
			//	PreparedStatement pst = connection.prepareStatement(con.queries.getProperty("index_divisor_date_wise"));
				PreparedStatement pst = connection.prepareStatement(ConnectInit.queries.getProperty("index_divisor_date_wise"));
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
	                	    move1.add(year);
	                	    move2.add(month);
	                	    move3.add(day);
	                        String year1=rs.getString(2);
	                        year1=ad.indexcompose(year1);
	                        move4.add(year1);
	                }  
				 for(int i=0;i<move1.size();i++)
			        {
			           String str=(String)move3.get(i)+"  "+(String)move2.get(i)+"  "+(String)move1.get(i);
			           Logging.debug(str);
			        }
			       
			        for(int i=0;i<move4.size();i++)
			        {
			           String str=(String)move4.get(i);
			           Logging.debug(str);
			        }  
			       // Logging.getDebug(field3.size());
			}catch(SQLException e){
				Logging.debug(e+"");
/*			JFrame frame=new JFrame();
			JOptionPane.showMessageDialog(frame,"Unable To Connect DataBase","ERROR!",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
	*/			
			}catch(Exception e){
				Logging.debug(e+"");
			}
//			Close the Dynamic Connection : Manoj Adekar
			finally {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception ee) {
					Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
				}
			}
			Logging.debug("abc");
			
	}
	
	public static String getFilename() {
		return filename;
	}
	public static String getFilenameSect() {
		return filenameSect;
	}
	public static void setFilename(String filename) {
		GraphMethodsPf.filename = filename;
	}

	public static String getGraphURL() {
		return graphURL;
	}

	public static void setGraphURL(String graphURL) {
		GraphMethodsPf.graphURL = graphURL;
	}

	public String getGraphChartCompany1(HttpSession session, PrintWriter pw )
    {	//	Logging.debug("no error till now");
	  		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy",Locale.UK);
			Date dDate = null;
			String filename = null;
			try {
					dDate = sdf.parse("01-Aug-2002");
					
					Logging.debug("Inside generatePieChart");
					DefaultPieDataset data = new DefaultPieDataset();
					for(int i=0;i<field1.size();i++)
					{
								data.setValue(((String)field1.get(i)),Double.parseDouble((String)field2.get(i)));
					   
					}
				   	
			        final JFreeChart chart = ChartFactory.createPieChart3D(
			                "",  // chart title
			                data,                // data
			               false,                   // include legend
			                true,
			                true
			            );

			            final PiePlot3D plot = (PiePlot3D) chart.getPlot();
			            plot.setToolTipGenerator(new StandardPieToolTipGenerator());
			            //plot.setToolTipGenerator(new StandardPieItemLabelGenerator());
			           
			            plot.setLabelFont(new Font("SansSerif",1,7));
			            plot.setStartAngle(290);
			            plot.setDirection(Rotation.CLOCKWISE);
			            plot.setForegroundAlpha(1.0f);
			            plot.setNoDataMessage("No data to display");
			            
			        ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			        String path = null;
			        filename = ServletUtilities.saveChartAsPNG(chart, 500, 270,info, session);
				//  Write the image map to the PrintWriter
					ChartUtilities.writeImageMap(pw, filename, info,true);
					pw.flush();
			}  catch (Exception e) {
					Logging.error("Exception - P5" + e.getMessage());
					//e.printStackTrace(System.out);
				
			}
			Logging.debug("end of getGraphChartCompany");
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
				 Logging.debug("size is "+a.size());
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
		          // Logging.getDebug(""+i);
	                for(int j=0;j<field1.size();j++)
	                {
	                   String str=(String)field1.get(j);
	                   Logging.debug(str);
	                }
	                for(int j=0;j<field2.size();j++)
	                {
	                   String str=(String)field2.get(j);
	                   Logging.debug(str);
	                }
				/*for(int i=0;i<a.size();i++)
				{
					Logging.getDebug((String)a.get(i));
				}*/
			}catch(Exception e){
				Logging.error("Exception - P6" + e.getMessage());
			}
			Logging.debug("abc");
			
	}

	public static void ReaddataCompose(HttpServletRequest request )
	{
		
		
		
		Logging.debug("dfyfgyh");
		try{
				field1=new Vector();
				field2=new Vector();
				
				
				Vector a = (Vector)((request.getSession()).getAttribute("colected_vector"));
				
				//Vector a = (Vector)getVw();
				Iterator iter=a.iterator(); 
				 int i=0;
				 Logging.debug("vector size is "+a.size());
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
		           Logging.debug("value of field "+field2); 
			}catch(Exception e){
				Logging.error("Exception - P7" + e.getMessage());
			}
			Logging.debug("abc");
			
	}
	
	
	
	
	static Vector vi;
	public static Vector getIndweighttable(String index) {
		
		vi=new Vector();
		Connection connection=null;
		double total1=0.00,total2=0.00;
		double strweightage=0.0,market=0.00;
		String index12 = index;
		String index1,index2,tno1=null;
		
//		Connect con=new Connect();
		Connect con = ConnectInit.getConnect();	
		ArrayList tempdata=new ArrayList();
		IndexWise indexwise;
		try {
			if (connection==null)
				connection=con.getdbConnection();
		
		rst = Icr.indweightResult(connection,"industry_wise_weightage", index12);
		int i = 0;
		Logging.debug("setVector_indweighttable of Industry wise weightage");
		try {

			while (rst.next()) {

				vi.add(i,rst.getString(1));
				vi.add(i,rst.getString(2));
				vi.add(i,rst.getString(3));
				
			}
			rst.close();
		} catch (SQLException sqlexp) {
			Logging.error("Error E1: "+sqlexp.getMessage());
		}
		}catch(Exception e){
			Logging.error(" Error E2: "+e.getMessage());
		}
		 finally{
			try{
				if(connection!=null)
					connection.close();
			}catch(Exception ee){
				try{
					if(connection!=null)
						connection.close();
				}catch(Exception ex){
					Logging.error(" Error : Unable to close connection "+ex.getMessage());
				}
				Logging.error(" Error : Unable to close connection "+ee.getMessage());
			}
		}
		
		 return vi;
	}
	
	public static void ReaddataIndweight(HttpServletRequest request)
	{
		try{
				Iw1=new Vector();
				Iw2=new Vector();
				Iw3=new Vector();
				//String index=(request.getSession()).getAttribute("varIndexId").toString();
				//Vector a =(Vector)getIndweighttable(index);
				Vector a = (Vector)((request.getSession()).getAttribute("colected_vector_iw"));
				Iterator iter=a.iterator(); 
				 int i=0;
				 Logging.debug("size is "+a.size());
		           while(iter.hasNext())
		           {  
			           	String year=(String)iter.next();
			           	Iw1.add(year);
	                    String year2=(String)iter.next();
	                    Iw3.add(year2);
	                    String year1=(String)iter.next();
	                    Iw2.add(year1);
	                    
		           }
		          
			}catch(Exception e){
				Logging.error("Exception - P8" + e.getMessage());
			}
			Logging.debug("abc");
			
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
	
	
	
	public void getGraphChart1(HttpSession session, PrintWriter Prw,String report )
    {
	  
		 String varChart =(String)(session.getAttribute("varChart"));
		 String from =(String)(session.getAttribute("from"));
		 String to = (String)(session.getAttribute("to"));
		 String varIndexId =(String)(session.getAttribute("varIndexId"));
		 this.DisplayChart(session,Prw,from,to,varIndexId,report,varChart);   
	
    }
	
	public static void DisplayChart(HttpSession session,PrintWriter pw,String f_from,String f_to,String f_varIndexId,String report,String varChart){
		//SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy",Locale.UK);
		 String var=f_varIndexId;
	     filename=null;graphURL=null;
	    
	     String fromdate = f_from;
	     String toDate  =f_to;
	     /*
	    Date dDate = null;
		try {
			dDate = sdf.parse("01-Aug-2002");
		} catch (Exception e) {
			Logging.getDebug(" Error : "+e.getMessage());
		}
		*/
		String rep1=null;
		
		  try {	
			  		if(report.equals("maverage")){
			  			Readdata(var, fromdate, toDate);
			  			rep1="Moving";
			  			
			  		}if(report.equals("inddivisor")){
						DivisorReaddata(var, fromdate, toDate);	
						rep1="Divisor";
					}
					//filename = generateChart(dDate, request.getSession(), pw,var,"b");
					filename = movingindex(session, pw,rep1,varChart);
				  
	      	}catch(Exception e) {Logging.debug("Error : E3"+e.getMessage()); }                
	       
	} 	
	
	
	public static String movingindex(HttpSession session, PrintWriter pw,String report1,String varChart){
		String filename = null;
		try{
			JFreeChart jfreechart = null;
			if(varChart.equals("1"))
  			{	
				XYDataset xydataset = createDataset(report1);
			    jfreechart = createChart(xydataset);
  				
  			}
  			if(varChart.equals("2"))
  			{	
  				
  				IntervalXYDataset intervalxydataset=createDatasetBar(report1);
  				jfreechart = createChartBar(intervalxydataset);
  				
  			}
  			if(varChart.equals("3"))
  			{	
  				XYDataset xydataset = createDatasetArea(report1);
  				jfreechart =createChartArea(xydataset);
  			}
  		
	    jfreechart.setBackgroundPaint(java.awt.Color.white);
		Logging.debug("before save as  generatePieChart");
		ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
		filename = ServletUtilities.saveChartAsPNG(jfreechart, 500, 270, info, session);
	//		Write the image map to the PrintWriter
		ChartUtilities.writeImageMap(pw, filename, info,true);
		pw.flush();
		}  catch (Exception e) {
			Logging.error("Exception P8- " + e.toString());
			e.printStackTrace(System.out);
			 
		}
		return filename;
	}
	
	private static XYDataset createDataset(String s1)
    {
        TimeSeries timeseriesMov = new TimeSeries("Portfolio "+s1);
        for(int i=(((move4).size())-1);i>=0;i--){
        
        int d3=	Integer.parseInt((String)(move3).get(i));
        int d2=	Integer.parseInt((String)(move2).get(i));	
        int d1=	Integer.parseInt((String)(move1).get(i));
        double d4=	Double.parseDouble((String)(move4).get(i));
        timeseriesMov.add(new Day(d3, d2,d1),d4);	
        //timeseries.add(new Day(Integer.parseInt((String)(move3).get(i)), Integer.parseInt((String)(move2).get(i)), Integer.parseInt((String)(move1).get(i))),(Double)(move4).get(i));
        }
        TimeZone timezone = TimeZone.getTimeZone("Pacific/Auckland");
        TimeSeriesCollection timeseriescolMov = new TimeSeriesCollection(timezone);
        TimeSeries timeseries1 = MovingAverage.createMovingAverage(timeseriesMov, "15 day "+s1+" average", 30, 15);
        timeseriescolMov.addSeries(timeseriesMov);
        timeseriescolMov.addSeries(timeseries1);
        timeseriescolMov.setXPosition(TimePeriodAnchor.MIDDLE);
        return timeseriescolMov;
        
        
    }
	private static XYDataset createDatasetArea(String s1)
    {
        TimeSeries timeseries = new TimeSeries("Portfolio "+s1);
        for(int i=(((move4).size())-1);i>=0;i--){
        
        int d3=	Integer.parseInt((String)(move3).get(i));
        int d2=	Integer.parseInt((String)(move2).get(i));	
        int d1=	Integer.parseInt((String)(move1).get(i));
        double d4=	Double.parseDouble((String)(move4).get(i));
        timeseries.add(new Day(d3, d2,d1),d4);	
        //timeseries.add(new Day(Integer.parseInt((String)(move3).get(i)), Integer.parseInt((String)(move2).get(i)), Integer.parseInt((String)(move1).get(i))),(Double)(move4).get(i));
        }
        TimeZone timezone = TimeZone.getTimeZone("Pacific/Auckland");
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection(timezone);
        //TimeSeries timeseries1 = MovingAverage.createMovingAverage(timeseries, "15 day "+s1+" average", 30, 15);
        timeseriescollection.addSeries(timeseries);
        //timeseriescollection.addSeries(timeseries1);
        timeseriescollection.setXPosition(TimePeriodAnchor.MIDDLE);
        return timeseriescollection;
        
        
    }
	
	private static XYDataset createDataset1()
    {
		TimeSeries timeseries = new TimeSeries(indexnameC2, org.jfree.data.time.Day.class);
        for(int i=(((move42).size())-1);i>=0;i--){
        
        int d3=	Integer.parseInt((String)(move32).get(i));
        int d2=	Integer.parseInt((String)(move22).get(i));	
        int d1=	Integer.parseInt((String)(move12).get(i));
        double d4=	Double.parseDouble((String)(move42).get(i));
        timeseries.add(new Day(d3, d2,d1),d4);	
        //timeseries.add(new Day(Integer.parseInt((String)(move3).get(i)), Integer.parseInt((String)(move2).get(i)), Integer.parseInt((String)(move1).get(i))),(Double)(move4).get(i));
        }
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
        timeseriescollection.addSeries(timeseries);
        return timeseriescollection;
    }
	
	private static XYDataset createDatasetCmp()
    {
		TimeSeries timeseriesCmp = new TimeSeries(indexnameC2, org.jfree.data.time.Day.class);
        for(int i=(((move42).size())-1);i>=0;i--){
        
        int d3=	Integer.parseInt((String)(move32).get(i));
        int d2=	Integer.parseInt((String)(move22).get(i));	
        int d1=	Integer.parseInt((String)(move12).get(i));
        double d4=	Double.parseDouble((String)(move42).get(i));
        timeseriesCmp.add(new Day(d3, d2,d1),d4);	
        //timeseries.add(new Day(Integer.parseInt((String)(move3).get(i)), Integer.parseInt((String)(move2).get(i)), Integer.parseInt((String)(move1).get(i))),(Double)(move4).get(i));
        }
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
        timeseriescollection.addSeries(timeseriesCmp);
        return timeseriescollection;
    }
	 private static XYDataset createDataset2()
	    {
	        TimeSeries timeseries = new TimeSeries(indexnameC1, org.jfree.data.time.Day.class);
	        
	        for(int i=(((move41).size())-1);i>=0;i--){
	            
	            int d3=	Integer.parseInt((String)(move31).get(i));
	            int d2=	Integer.parseInt((String)(move21).get(i));	
	            int d1=	Integer.parseInt((String)(move11).get(i));
	            double d4=	Double.parseDouble((String)(move41).get(i));
	            timeseries.add(new Day(d3, d2,d1),d4);	
	            //timeseries.add(new Day(Integer.parseInt((String)(move3).get(i)), Integer.parseInt((String)(move2).get(i)), Integer.parseInt((String)(move1).get(i))),(Double)(move4).get(i));
	        }
	              
	        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
	        timeseriescollection.addSeries(timeseries);
	      
	        return timeseriescollection;
	    }

	 private static XYDataset createDataset3()
	    {
	      
	    	  
		 TimeSeries timeseries = new TimeSeries(indexnameC3, org.jfree.data.time.Day.class);
		        
		        for(int i=(((move44).size())-1);i>=0;i--){
		            
		            int d3=	Integer.parseInt((String)(move34).get(i));
		            int d2=	Integer.parseInt((String)(move24).get(i));	
		            int d1=	Integer.parseInt((String)(move14).get(i));
		            double d4=	Double.parseDouble((String)(move44).get(i));
		            timeseries.add(new Day(d3, d2,d1),d4);	
		            //timeseries.add(new Day(Integer.parseInt((String)(move3).get(i)), Integer.parseInt((String)(move2).get(i)), Integer.parseInt((String)(move1).get(i))),(Double)(move4).get(i));
		        }
		              
		        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		        timeseriescollection.addSeries(timeseries);
		      
		        return timeseriescollection;
	    	  
	      
	    }

	
	
	
	private static JFreeChart createChart(XYDataset xydataset)
    {
		
        
        JFreeChart jfreechartMov = ChartFactory.createTimeSeriesChart("", "", "Value", xydataset, true, true, false);
        XYPlot xyplot = (XYPlot)jfreechartMov.getPlot();
        XYItemRenderer xyitemrenderer = xyplot.getRenderer();
        StandardXYToolTipGenerator standardxytooltipgenerator = new StandardXYToolTipGenerator("{0}: ({1}, {2})", new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00"));
        xyitemrenderer.setBaseToolTipGenerator(standardxytooltipgenerator);
        return jfreechartMov;
        
    }
	
	//for industry weightage
	private static CategoryDataset createDatasetInd()
    {
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
		String s = "Weightage In %";
        for(int i=0;i<Iw1.size();i++)
		{
        	//int d1=	Integer.parseInt((String)(Iw1).get(i));
            double d4=	Double.parseDouble((String)(Iw2).get(i));
					//data.setValue(((String)field1.get(i)),Double.parseDouble((String)field2.get(i)));
        	defaultcategorydataset.addValue(d4, s, (String)(Iw1).get(i));
		}
        
       
        return defaultcategorydataset;
    }

    private static JFreeChart createChartInd(CategoryDataset categorydataset)
    {
    	JFreeChart jfreechart = ChartFactory.createBarChart("", "Industry", "", categorydataset, PlotOrientation.HORIZONTAL, true, true, false);
        jfreechart.addSubtitle(new TextTitle("", new Font("Dialog", 2, 10)));
        jfreechart.setBackgroundPaint(Color.white);
        CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
        categoryplot.setBackgroundPaint(Color.lightGray);
        categoryplot.setRangeGridlinePaint(Color.white);
        categoryplot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        BarRenderer barrenderer = (BarRenderer)categoryplot.getRenderer();
        barrenderer.setBaseItemLabelsVisible(true);
        barrenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        CategoryAxis categoryaxis = categoryplot.getDomainAxis();
        categoryaxis.setCategoryMargin(0.0D);
        categoryaxis.setUpperMargin(0.02D);
        categoryaxis.setLowerMargin(0.02D);
        NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        numberaxis.setUpperMargin(0.10000000000000001D);
        return jfreechart;
    }
	
    static class CustomBarRenderer3D extends BarRenderer3D
    {

        public Paint getItemPaint(int i, int j)
        {
            CategoryDataset categorydataset = getPlot().getDataset();
            double d = categorydataset.getValue(i, j).doubleValue();
            if(d >= 0.69999999999999996D)
                return Color.green;
            else
                return Color.red;
        }

        public CustomBarRenderer3D()
        {
        }
    }
	
    public String getGraphChartIndustry(HttpSession session, PrintWriter pw )
    {
    	    	
    	try {
       	 	CategoryDataset categorydataset = createDatasetInd();
            JFreeChart jfreechart = createChartInd(categorydataset);
            
         	ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
	        String path = null;
	        fileIndWtg = ServletUtilities.saveChartAsPNG(jfreechart, 500, 270,info, session);
		
			ChartUtilities.writeImageMap(pw, fileIndWtg, info,true);
			pw.flush();
    	}  catch (Exception e) {
			Logging.error("Exception P9- " + e.getMessage());
			//e.printStackTrace(System.out);
		
    	}
		Logging.debug("end of getGraphChartCompany");
		return fileIndWtg;
    }
	
	
    private static XYDataset createDataset(String[] var1,String fdate,String tdate) {
    	TimeSeriesCollection dataset;
        dataset = new TimeSeriesCollection();
    	
        for(int i=0;i<var1.length;i++){
       		final TimeSeries jpy = createFirstTimeSeries11(var1[i],fdate,tdate);
       	 if((jpy!=null))
         {
         	Logging.debug(" jpy is "+jpy);	
       	 	dataset.addSeries(jpy);
         }else{
         		return dataset=null;
         }
       }
       return dataset;
    }
    
    
    public String getGraphChartIndexCompare(HttpSession session, PrintWriter pw){
   		String filename =" ";
   		try
		{
   		  	   
   		String index3=null;
   		String index1     =session.getAttribute("varIndexId").toString();
   		String[] indMulti =(String[])session.getAttribute("indMulti");
   		String index2	  =null;
   		try{
   		 index2	  =indMulti[0];
   		 index3	  =indMulti[1];
   		
   		}catch(Exception e){
   			Logging.error("Exception P10- " + e.toString());
   		}
   		
		String fdate= session.getAttribute("from").toString();
		String tdate= session.getAttribute("to").toString();
		
		Readdata2(index2, fdate, tdate);
		Readdata1(index1, fdate, tdate);
		Readdata3(index3, fdate, tdate);
		JFreeChart jfreechartICmp = createChart22();
    		
		jfreechartICmp.setBackgroundPaint(java.awt.Color.white);
		ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
		filename = ServletUtilities.saveChartAsPNG(jfreechartICmp, 500, 270, info, session);
		ChartUtilities.writeImageMap(pw, filename, info,true);
		pw.flush();
		
    	}  catch (Exception e) {
			Logging.error("Exception P11- " + e.toString());
			e.printStackTrace(System.out);
		}
		
		return filename;
	}
    
    public String getGraphChartIndexCompare01(HttpSession session, PrintWriter pw){
   		String filename =" ";
   		try
		{
   		  	
   			String index3=null;
   			String index1=null;
   			String fdate =null;
   			String tdate =null;
   			String[] indMulti =(String[])session.getAttribute("indexids");
   			String index2	  =null;
   			int leng=indMulti.length;
   			try{
   				index1	  =indMulti[0];
   				index2	  =indMulti[1];
   				
   				
   				fdate= session.getAttribute("sfdate").toString();
   				tdate= session.getAttribute("stdate").toString();
   				
		 
   			}catch(Exception e){
   			Logging.error("Exception P12- " + e.toString());
   		}
   		if(leng > 2){
   				index3	  =indMulti[2];
   				Readdata3(index3, fdate, tdate);
		}
		Readdata2(index2, fdate, tdate);
		Readdata1(index1, fdate, tdate);		
		JFreeChart jfreechart1 = createChartSCMP();    	
    	jfreechart1.setBackgroundPaint(java.awt.Color.white);
		ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
		filename = ServletUtilities.saveChartAsPNG(jfreechart1, 500, 270, info, session);
		ChartUtilities.writeImageMap(pw, filename, info,true);
		pw.flush();
		
    	}  catch (Exception e) {
			Logging.error("Exception P13- " + e.toString());
			e.printStackTrace(System.out);
		}
		
		return filename;
	}
    private static JFreeChart createChart22()
    {
    	
    	XYDataset xydatasetCmp = createDatasetCmp();
        JFreeChart jchartCmp = ChartFactory.createTimeSeriesChart("", "", "Primary Range Axis", xydatasetCmp, true, true, false);
        jchartCmp.setBackgroundPaint(Color.white);
       // jfreechart.addSubtitle(new TextTitle("Four datasets and four range axes."));
        XYPlot xyplot = (XYPlot)jchartCmp.getPlot();
        xyplot.setOrientation(PlotOrientation.VERTICAL);
        xyplot.setBackgroundPaint(Color.lightGray);
        xyplot.setDomainGridlinePaint(Color.white);
        xyplot.setRangeGridlinePaint(Color.white);
        xyplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));
        xyplot.getRangeAxis().setFixedDimension(15D);
        XYItemRenderer xyitemrenderer = xyplot.getRenderer();
        xyitemrenderer.setSeriesPaint(0, Color.black);
        NumberAxis numberaxis = new NumberAxis("Range Axis 2");
        numberaxis.setFixedDimension(10D);
        numberaxis.setAutoRangeIncludesZero(false);
        numberaxis.setLabelPaint(Color.red);
        numberaxis.setTickLabelPaint(Color.red);
        xyplot.setRangeAxis(1, numberaxis);
        xyplot.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_LEFT);
        XYDataset xydataset1 = createDataset2();
        xyplot.setDataset(1, xydataset1);
        xyplot.mapDatasetToRangeAxis(1, 1);
        StandardXYItemRenderer standardxyitemrenderer = new StandardXYItemRenderer();
        standardxyitemrenderer.setSeriesPaint(0, Color.red);
        xyplot.setRenderer(1, standardxyitemrenderer);
        NumberAxis numberaxis1 = new NumberAxis("Range Axis 3");
        numberaxis1.setLabelPaint(Color.blue);
        numberaxis1.setTickLabelPaint(Color.blue);
        xyplot.setRangeAxis(2, numberaxis1);
       XYDataset xydataset2 = createDataset3();
        xyplot.setDataset(2, xydataset2);
        xyplot.mapDatasetToRangeAxis(2, 2);
        StandardXYItemRenderer standardxyitemrenderer1 = new StandardXYItemRenderer();
        standardxyitemrenderer1.setSeriesPaint(0, Color.blue);
        xyplot.setRenderer(2, standardxyitemrenderer1);
        //NumberAxis numberaxis2 = new NumberAxis("Range Axis 4");
        //numberaxis2.setLabelPaint(Color.green);
        //numberaxis2.setTickLabelPaint(Color.green);
        //xyplot.setRangeAxis(3, numberaxis2);
        //XYDataset xydataset3 = createDataset4();
        //xyplot.setDataset(3, xydataset3);
        //xyplot.mapDatasetToRangeAxis(3, 3);
        StandardXYItemRenderer standardxyitemrenderer2 = new StandardXYItemRenderer();
        //standardxyitemrenderer2.setSeriesPaint(0, Color.green);
        xyplot.setRenderer(3, standardxyitemrenderer2);
        return jchartCmp;
    }
    
    private static JFreeChart createChartSCMP()
    {
    	
    	XYDataset xydataset = createDataset1();
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("", "", "Primary Range Axis", xydataset, true, true, false);
        jfreechart.setBackgroundPaint(Color.white);
       // jfreechart.addSubtitle(new TextTitle("Four datasets and four range axes."));
        XYPlot xyplot = (XYPlot)jfreechart.getPlot();
        xyplot.setOrientation(PlotOrientation.VERTICAL);
        xyplot.setBackgroundPaint(Color.lightGray);
        xyplot.setDomainGridlinePaint(Color.white);
        xyplot.setRangeGridlinePaint(Color.white);
        xyplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));
        xyplot.getRangeAxis().setFixedDimension(15D);
        XYItemRenderer xyitemrenderer = xyplot.getRenderer();
        xyitemrenderer.setSeriesPaint(0, Color.black);
        NumberAxis numberaxis = new NumberAxis("Range Axis 2");
        numberaxis.setFixedDimension(10D);
        numberaxis.setAutoRangeIncludesZero(false);
        numberaxis.setLabelPaint(Color.red);
        numberaxis.setTickLabelPaint(Color.red);
        xyplot.setRangeAxis(1, numberaxis);
        xyplot.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_LEFT);
        XYDataset xydataset1 = createDataset2();
        xyplot.setDataset(1, xydataset1);
        xyplot.mapDatasetToRangeAxis(1, 1);
        StandardXYItemRenderer standardxyitemrenderer = new StandardXYItemRenderer();
        standardxyitemrenderer.setSeriesPaint(0, Color.red);
        xyplot.setRenderer(1, standardxyitemrenderer);
       //NumberAxis numberaxis1 = new NumberAxis("Range Axis 3");
       //numberaxis1.setLabelPaint(Color.blue);
       //numberaxis1.setTickLabelPaint(Color.blue);
        //xyplot.setRangeAxis(2, numberaxis1);
       //XYDataset xydataset2 = createDataset3();
       //xyplot.setDataset(2, xydataset2);
        //xyplot.mapDatasetToRangeAxis(2, 2);
      //StandardXYItemRenderer standardxyitemrenderer1 = new StandardXYItemRenderer();
      //standardxyitemrenderer1.setSeriesPaint(0, Color.blue);
      //xyplot.setRenderer(2, standardxyitemrenderer1);
        
        //NumberAxis numberaxis2 = new NumberAxis("Range Axis 4");
        //numberaxis2.setLabelPaint(Color.green);
        //numberaxis2.setTickLabelPaint(Color.green);
        //xyplot.setRangeAxis(3, numberaxis2);
        //XYDataset xydataset3 = createDataset4();
        //xyplot.setDataset(3, xydataset3);
        //xyplot.mapDatasetToRangeAxis(3, 3);
        //StandardXYItemRenderer standardxyitemrenderer2 = new StandardXYItemRenderer();
        //standardxyitemrenderer2.setSeriesPaint(0, Color.green);
       // xyplot.setRenderer(3, standardxyitemrenderer2);
        return jfreechart;
    }
    
    private static XYDataset createDataset4()
    {
    		
        TimeSeries timeseries = new TimeSeries("", org.jfree.data.time.Day.class);
        
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
        timeseriescollection.addSeries(timeseries);
        return timeseriescollection;
              
    }
    public String getGraphChartSectorwise(HttpSession session, PrintWriter pw){
   		//String filenameSect =" ";
   		try
		{
   		  	
   		String index1	="";
   		String index2	="";
   		String index3	="";
   		indMultiSect =(String[])session.getAttribute("indexes");
   		indLength=indMultiSect.length;
   		indexNameSect=new String[indLength];
   		try{
   		 index1	  =indMultiSect[0];
   		 index2	  =indMultiSect[1];
   		 ReadSectordata1(index1);
   		 ReadSectordata2(index2);
   		 if(indLength > 2){
   			index3	  =indMultiSect[2];
   			ReadSectordata3(index3);
   		 }
   		}catch(Exception e){
   			Logging.error("Exception P14- " + e.toString());
   		}
   		
   		try{
   			
      		 indexNameSect[0]	  =ComposeIndex.getIndexName(index1); //indMultiSect[0];
      		 indexNameSect[1]	  =ComposeIndex.getIndexName(index2); //indMultiSect[1];
      		 if(indLength > 2)
      			indexNameSect[2]	  =ComposeIndex.getIndexName(index3);
      		
      	}catch(Exception e){
      			Logging.error("Exception P15- " + e.toString());
      	}
   		CategoryDataset CDSet=createDatasetSect();
   		
		JFreeChart jfreechart1 = createChart(CDSet);
		jfreechart1.setBackgroundPaint(java.awt.Color.white);
    	ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
		filenameSect = ServletUtilities.saveChartAsPNG(jfreechart1, 500, 270, info, session);
		ChartUtilities.writeImageMap(pw, filenameSect, info,true);
		pw.flush();
    	}  catch (Exception e) {
			Logging.error("Exception P16- " + e.toString());
			e.printStackTrace(System.out);
		}
		
		return filenameSect;
	}
    
    
    public String getGraphSectorPortfolio(HttpSession session, PrintWriter pw){
//    	Logging.debug("3 : inside getGraphSectorPortfolio() ");
    	String index3=null;
    	String index1	=session.getAttribute("varIndexId").toString();
   		String[] indMultiSect =(String[])session.getAttribute("indMulti");
   		String index2	  =null;
   		try{
  		Logging.debug("3 : inside try 1");
   		 index2	  =indMultiSect[0];
   		 index3	  =indMultiSect[1];
   		}catch(Exception e){
   			Logging.error("Exception P17- " + e.toString());
   		}
    	
    	
   		try
		{
   		Logging.debug("3 : inside try 2");
   		indLength=indMultiSect.length+1;
   		indexNameSect=new String[indLength];
   		try{
   		 Logging.debug("3 : inside try 2-1");
   		 index2	  =indMultiSect[0];
   		 ReadSectordata1(index1);
   		 ReadSectordata2(index2);
   		 if(indLength > 2){
   			index3	  =indMultiSect[1];
   			ReadSectordata3(index3);
   		 }
   		}catch(Exception e){
   			Logging.error("Exception P18- " + e.toString());
   		}
   		
   		try{
  			Logging.debug("3 : inside try 3");
      		 indexNameSect[0]	  =ComposeIndex.getIndexName(index1); //indMultiSect[0];
      		 indexNameSect[1]	  =ComposeIndex.getIndexName(index2); //indMultiSect[1];
      		 if(indLength > 2)
      		 {
      			 indexNameSect[2]	  =ComposeIndex.getIndexName(index3);
      		 }
      		
      	}catch(Exception e){
      			Logging.error("Exception P19- " + e.toString());
      	}
      	Logging.debug("returned from compose index...");
   		CategoryDataset CDSet=createDatasetSect();
   		Logging.debug("after creating data sector");
		JFreeChart jfreechart1 = createChart(CDSet);
		jfreechart1.setBackgroundPaint(java.awt.Color.white);
    	ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
		filenameSect = ServletUtilities.saveChartAsPNG(jfreechart1, 500, 270, info, session);
		ChartUtilities.writeImageMap(pw, filenameSect, info,true);
		pw.flush();
    	}  catch (Exception e) {
			Logging.error("Exception P20- " + e.toString());
		//	e.printStackTrace(System.out);
		}
//		Logging.debug("3 : process over...file created"+filenameSect);
		return filenameSect;
	}
   
    
    public static void ReadSectordata1(String indexid1)
	{
    	Logging.debug("inside ReadSectordata1");
	  Connection connection=null;
		try{
			sect1=new Vector();
			sect2=new Vector();
			sect3=new Vector();
         //       Connect con = new Connect();
			Connect con = ConnectInit.getConnect();
			Logging.debug("1 : "+con);
//              Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
                if(connection==null)
                {
                	Logging.debug("inside if for connection...");
                	connection=con.getdbConnection();
                }
              Logging.debug("connection 1:"+connection);
                /*
				PreparedStatement pst = Connect.con.prepareStatement(con.queries.getProperty("select_sectorwise_index"));
				pst.setString(1,indexid1);
				pst.setString(2,indexid1);
				pst.setString(3,indexid1);
				*/
             //   PreparedStatement pst = connection.prepareStatement(con.queries.getProperty("industry_wise_weightage"));
              PreparedStatement pst = connection.prepareStatement(ConnectInit.queries.getProperty("industry_wise_weightage"));
              
               Logging.debug("got the properties...1");
               Logging.debug("Prepared stmt 1:"+pst); 
               pst.setString(1,indexid1);
		//		pst.setString(2,indexid1);
		//		pst.setString(3,indexid1);
				Logging.debug("Query goign to execute...1");
				ResultSet rs = pst.executeQuery(); 
				Logging.debug("Query executed...1");
				while(rs.next())
	            {				
					sect1.add(rs.getString(1));
					//sect2.add(rs.getString(2));
					sect3.add(rs.getString(3));	   
	            }
				sector1=new String[sect1.size()];
	            for(int i=0;i<sect1.size();i++)
	            {
	            	Logging.debug("for taking time...");
	            	sector1[i]=(String)sect1.get(i);
	            }
	              
		}catch(SQLException e){
				Logging.debug(e+"");
		/*	JFrame frame=new JFrame();
			JOptionPane.showMessageDialog(frame,"Unable To Connect DataBase","ERROR!",JOptionPane.ERROR_MESSAGE);
			System.exit(0);*/
				
		}catch(Exception e){
				Logging.debug(e+"");
		}
//		Close the Dynamic Connection : Manoj Adekar
		finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
			}
		}
		Logging.debug("abc");
		Logging.debug("coming out of ReadSectordata1");
	}
    public static void ReadSectordata2(String indexid2)
	{
   	Logging.debug("inside ReadSectordata2");
    	Connection connection=null;
		try{
			sect11=new Vector();
			sect21=new Vector();
			sect31=new Vector();
     //           Connect con = new Connect();
			Connect con = ConnectInit.getConnect();
		Logging.debug("2 : "+con);
//              Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
                if(connection==null)
                {
                	connection=con.getdbConnection();
                }
			//	PreparedStatement pst = connection.prepareStatement(con.queries.getProperty("industry_wise_weightage"));
				PreparedStatement pst = connection.prepareStatement(ConnectInit.queries.getProperty("industry_wise_weightage"));
				Logging.debug("Prepared stmt 2:"+pst);
				pst.setString(1,indexid2);
		//		pst.setString(2,indexid2);
		//		pst.setString(3,indexid2);
				Logging.debug("Query goign to execute...2");
				ResultSet rs = pst.executeQuery(); 
				Logging.debug("Query executed...2");
				while(rs.next())
	            {
					Logging.debug("while 2");
					sect11.add(rs.getString(1));
					//sect21.add(rs.getString(2));
					sect31.add(rs.getString(3));	   
	            }
				sector11=new String[sect11.size()];
	            for(int i=0;i<sect11.size();i++)
	            {
	            	Logging.debug("for 2");
	            	sector11[i]=(String)sect11.get(i);
	            }
	              
	              
		}catch(SQLException e){
				Logging.debug(e+"");
		/*	JFrame frame=new JFrame();
			JOptionPane.showMessageDialog(frame,"Unable To Connect DataBase","ERROR!",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		*/		
		}catch(Exception e){
				Logging.debug(e+"");
		}
//		Close the Dynamic Connection : Manoj Adekar
		finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
			}
		}
		Logging.debug("abc");
		Logging.debug("coming out of ReadSectordata2");
	}
    public static void ReadSectordata3(String indexid3)
	{
  	Logging.debug("inside ReadSectordata3");
	  Connection connection=null;
		try{
			sect12=new Vector();
			sect22=new Vector();
			sect32=new Vector();
    //            Connect con = new Connect();
			Connect con = ConnectInit.getConnect();
			Logging.debug("3 : "+con);
//          Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
                if(connection==null)
                {
                	connection=con.getdbConnection();
                }
			//	PreparedStatement pst = connection.prepareStatement(con.queries.getProperty("industry_wise_weightage"));
                PreparedStatement pst = connection.prepareStatement(ConnectInit.queries.getProperty("industry_wise_weightage"));
               Logging.debug("Prepared stmt 3:"+pst);
                pst.setString(1,indexid3);
		//		pst.setString(2,indexid3);
		//		pst.setString(3,indexid3);
				Logging.debug("Query goign to execute...3");
				ResultSet rs = pst.executeQuery(); 
				Logging.debug("Query executed...3");
				while(rs.next())
	            {
					Logging.debug("while 3");
					sect12.add(rs.getString(1));
					//sect21.add(rs.getString(2));
					sect32.add(rs.getString(3));	   
	            }
				sector12=new String[sect12.size()];
	            for(int i=0;i<sect12.size();i++)
	            {
	            	Logging.debug("for 3");
	            	sector12[i]=(String)sect12.get(i);
	            }  
	              
		}catch(SQLException e){
				Logging.debug(e+"");
	/*		JFrame frame=new JFrame();
			JOptionPane.showMessageDialog(frame,"Unable To Connect DataBase","ERROR!",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
			*/	
		}catch(Exception e){
				Logging.debug(e+"");
		}
//		Close the Dynamic Connection : Manoj Adekar
		finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
			}
		}
		Logging.debug("abc");
		Logging.debug("coming out of ReadSectordata3");
	}
    private static JFreeChart createChartIndWtg()
    {
    	
    	XYDataset xydataset = createDataset1();
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("", "", "Primary Range Axis", xydataset, true, true, false);
        jfreechart.setBackgroundPaint(Color.white);
       // jfreechart.addSubtitle(new TextTitle("Four datasets and four range axes."));
        XYPlot xyplot = (XYPlot)jfreechart.getPlot();
        xyplot.setOrientation(PlotOrientation.VERTICAL);
        xyplot.setBackgroundPaint(Color.lightGray);
        xyplot.setDomainGridlinePaint(Color.white);
        xyplot.setRangeGridlinePaint(Color.white);
        xyplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));
        xyplot.getRangeAxis().setFixedDimension(15D);
        XYItemRenderer xyitemrenderer = xyplot.getRenderer();
        xyitemrenderer.setSeriesPaint(0, Color.black);
        NumberAxis numberaxis = new NumberAxis("Range Axis 2");
        numberaxis.setFixedDimension(10D);
        numberaxis.setAutoRangeIncludesZero(false);
        numberaxis.setLabelPaint(Color.red);
        numberaxis.setTickLabelPaint(Color.red);
        xyplot.setRangeAxis(1, numberaxis);
        xyplot.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_LEFT);
        XYDataset xydataset1 = createDataset2();
        xyplot.setDataset(1, xydataset1);
        xyplot.mapDatasetToRangeAxis(1, 1);
        StandardXYItemRenderer standardxyitemrenderer = new StandardXYItemRenderer();
        standardxyitemrenderer.setSeriesPaint(0, Color.red);
        xyplot.setRenderer(1, standardxyitemrenderer);
        NumberAxis numberaxis1 = new NumberAxis("Range Axis 3");
        numberaxis1.setLabelPaint(Color.blue);
        numberaxis1.setTickLabelPaint(Color.blue);
        xyplot.setRangeAxis(2, numberaxis1);
       XYDataset xydataset2 = createDataset4();
        xyplot.setDataset(2, xydataset2);
        xyplot.mapDatasetToRangeAxis(2, 2);
        StandardXYItemRenderer standardxyitemrenderer1 = new StandardXYItemRenderer();
        standardxyitemrenderer1.setSeriesPaint(0, Color.blue);
        xyplot.setRenderer(2, standardxyitemrenderer1);
        NumberAxis numberaxis2 = new NumberAxis("Range Axis 4");
        numberaxis2.setLabelPaint(Color.green);
        numberaxis2.setTickLabelPaint(Color.green);
        xyplot.setRangeAxis(3, numberaxis2);
        XYDataset xydataset3 = createDataset4();
        xyplot.setDataset(3, xydataset3);
        xyplot.mapDatasetToRangeAxis(3, 3);
        StandardXYItemRenderer standardxyitemrenderer2 = new StandardXYItemRenderer();
        standardxyitemrenderer2.setSeriesPaint(0, Color.green);
        xyplot.setRenderer(3, standardxyitemrenderer2);
        return jfreechart;
    }
    
    private static CategoryDataset createDatasetSect()
    {
    	int leng=0;
    	Comparable[] colkeys=null;
    	int indLen=indexNameSect.length;
    	
    	int leng1=sect1.size();
       	int leng2=sect11.size();
       	int leng3=0;
       	if(indLength > 2)
    	  leng3=sect12.size();
    	
       	
       	if(indLength > 2){
    	if(leng1 < leng2){
    		if(leng2 < leng3){
    			leng=leng3;
    			colkeys=sector12;
    		}else{
    			leng=leng2;
    			colkeys=sector11;
    		}
    		
    	}else{
    		if(leng1 < leng3){
    			leng=leng3;
    			colkeys=sector12;
    		}else{
    			leng=leng1;
    			colkeys=sector1;
    		}
    		
    	}
       	}else{
       		if(leng1 < leng2){
       			leng=leng2;
       			colkeys=sector11;
       		}else{
       			leng=leng1;
       			colkeys=sector1;
       		}
       		
       	}
       	
    	double ad[][]=new double [indLen][leng];
    	
    	for(int i=0;i<indLen;i++){
    		
    		for(int j=0;j<leng;j++){
    			 
    			if(i==0){
    				try{
    					ad[i][j]=Double.parseDouble((String)(sect3).get(j));
    					if(ad[i][j] > maxInd){	maxInd=ad[i][j]; }
    				
    				}catch(Exception e){
    					ad[i][j]=0.0D;
    				}
    			}else if(i==1){
    				try{
    					ad[i][j]=Double.parseDouble((String)(sect31).get(j));
    					if(ad[i][j] > maxInd){	maxInd=ad[i][j]; }
    				}catch(Exception e){
    					ad[i][j]=0.0D;
    				}
    			}else{
    				if(indLength > 2){
    					try{
    						ad[i][j]=Double.parseDouble((String)(sect32).get(j));
    						if(ad[i][j] > maxInd){	maxInd=ad[i][j]; }
    					}catch(Exception e){
    						ad[i][j]=0.0D;
    					}
    				}
    			}
    		}
    	}
    	
    	Comparable[] rowkeys= indexNameSect;
    	
    	//Comparable[] colkeys=sector1;
   	Logging.debug("Database Utils returning :"+DatasetUtilities.createCategoryDataset(rowkeys,colkeys, ad));
        return DatasetUtilities.createCategoryDataset(rowkeys,colkeys, ad);
    }

    private static JFreeChart createChart(CategoryDataset categorydataset)
    {	
    	Logging.debug("inside createChart() of GraphMethodPf");
        JFreeChart jfreechart = ChartFactory.createBarChart("Industrywise Compare", "", "Weightage(%)", categorydataset, PlotOrientation.HORIZONTAL, true, true, false);
        jfreechart.setBackgroundPaint(Color.white);
        CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
        categoryplot.setBackgroundPaint(Color.lightGray);
        categoryplot.setRangeGridlinePaint(Color.white);
        categoryplot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
        if(maxInd <1){maxInd=100D;}
        numberaxis.setRange(0.0D, maxInd);
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer barrenderer = (BarRenderer)categoryplot.getRenderer();
        barrenderer.setDrawBarOutline(false);
        barrenderer.setLegendItemToolTipGenerator(new StandardCategorySeriesLabelGenerator("Tooltip: {0}"));
        return jfreechart;
    }

    
    
    private static JFreeChart createChartArea(XYDataset xydataset)
    {
        JFreeChart jfreechart = ChartFactory.createXYAreaChart("XY Area Chart Demo 2", "Time", "Value", xydataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot xyplot = (XYPlot)jfreechart.getPlot();
        DateAxis dateaxis = new DateAxis("Time");
        dateaxis.setLowerMargin(0.0D);
        dateaxis.setUpperMargin(0.0D);
        xyplot.setDomainAxis(dateaxis);
        xyplot.setForegroundAlpha(0.5F);
        XYItemRenderer xyitemrenderer = xyplot.getRenderer();
        xyitemrenderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator("{0}: ({1}, {2})", new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("#,##0.00")));
        return jfreechart;
    }
    
    
    private static JFreeChart createChartBar(IntervalXYDataset intervalxydataset)
    {
        JFreeChart jfreechart = ChartFactory.createXYBarChart("", "", true, "", intervalxydataset, PlotOrientation.VERTICAL, true, false, false);
        jfreechart.addSubtitle(new TextTitle("", new Font("Dialog", 2, 10)));
        jfreechart.setBackgroundPaint(Color.white);
        XYPlot xyplot = (XYPlot)jfreechart.getPlot();
        XYItemRenderer xyitemrenderer = xyplot.getRenderer();
        StandardXYToolTipGenerator standardxytooltipgenerator = new StandardXYToolTipGenerator("{1} = {2}", new SimpleDateFormat("yyyy"), new DecimalFormat("0"));
        xyitemrenderer.setBaseToolTipGenerator(standardxytooltipgenerator);
        xyplot.setBackgroundPaint(Color.lightGray);
        xyplot.setRangeGridlinePaint(Color.white);
        DateAxis dateaxis = (DateAxis)xyplot.getDomainAxis();
        dateaxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
        dateaxis.setLowerMargin(0.01D);
        dateaxis.setUpperMargin(0.01D);
        return jfreechart;
    }
   
    private static IntervalXYDataset createDatasetBar(String s1)
    {
        TimeSeries timeseries = new TimeSeries("Portfolio "+s1);
        for(int i=(((move4).size())-1);i>=0;i--){
        
        int d3=	Integer.parseInt((String)(move3).get(i));
        int d2=	Integer.parseInt((String)(move2).get(i));	
        int d1=	Integer.parseInt((String)(move1).get(i));
        double d4=	Double.parseDouble((String)(move4).get(i));
        timeseries.add(new Day(d3, d2,d1),d4);	
        //timeseries.add(new Day(Integer.parseInt((String)(move3).get(i)), Integer.parseInt((String)(move2).get(i)), Integer.parseInt((String)(move1).get(i))),(Double)(move4).get(i));
        }
        TimeZone timezone = TimeZone.getTimeZone("Pacific/Auckland");
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection(timezone);
        //TimeSeries timeseries1 = MovingAverage.createMovingAverage(timeseries, "15 day "+s1+" average", 30, 15);
        timeseriescollection.addSeries(timeseries);
        //timeseriescollection.addSeries(timeseries1);
        timeseriescollection.setXPosition(TimePeriodAnchor.MIDDLE);
        return timeseriescollection;
                
    }
    //Stock High Lows
    
    private static JFreeChart createChartOHLC(OHLCDataset ohlcdataset)
    {
        JFreeChart jfreechart = ChartFactory.createCandlestickChart("Stock Details", "Time", "Value", ohlcdataset, true);
        XYPlot xyplot = (XYPlot)jfreechart.getPlot();
        NumberAxis numberaxis = (NumberAxis)xyplot.getRangeAxis();
        numberaxis.setAutoRangeIncludesZero(false);
        numberaxis.setUpperMargin(0.0D);
        numberaxis.setLowerMargin(0.0D);
        return jfreechart;
    }
    static ArrayList year,month,day,hour,minute,high,low,open,close,volume;
    
    public static void candlestickReaddata(Vector v)
	{
            
		try{
			 year=new ArrayList();
			 month=new ArrayList();
			 day=new ArrayList();
			
			 high=new ArrayList();
			 low=new ArrayList();
			 open=new ArrayList();
			 close=new ArrayList();
			 volume=new ArrayList();
			 Iterator iter=v.iterator(); 
               int i=0;
				 while(iter.hasNext())
			        {
				 		
						
	                    iter.next();
				 		//Logging.getDebug("1");
				 		 String op=(String)iter.next();
			              
			               if(op==null)
			               {
			               		op="0";
			               	}
			               open.add(op);
			               String cl=(String)iter.next();
			               
			               if(cl==null)
			               {
			               		cl="0";
			               	}
			               close.add(cl);
			              
			                String l=(String)iter.next();
				               
				               if(l==null)
				               {
				               		l="0";
				               	}
				               low.add(l);
			              
			               String h=(String)iter.next();
			              
			               if(h==null)
			               {
			               		h="0";
			               	}
			              
			               high.add(h);
			               String vol=(String)iter.next();
			              
			               if(vol==null)
			               {
			               		vol="0";
			               	}
			               volume.add(vol);
			               String mcv=(String)iter.next();
			               String trdvalue=(String)iter.next();
			               String ntrades=(String)iter.next();
			               String d=(String)iter.next();
			        	    
			        	    String y=d.substring(6,10);
			        	    String m=d.substring(3,5);
			        	    String dy=d.substring(0,2);
			                year.add(y);
			                month.add(m);
			                day.add(dy);
			              
			        }	              	               				
			}catch(Exception e){
				Logging.error("ERROR E4: "+e.getMessage());
			}
			Logging.debug("abc");
			
	}
    
    public static String generateChart(HttpSession session, PrintWriter pw)
	{
    
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy",Locale.UK);
		String filename = null;
		Date dDate = null;
		try {
			dDate = sdf.parse("01-Aug-2002");
		} catch (Exception e) {
			Logging.debug(" Error E5: "+e.getMessage());
		}
    	try
		{
	        final OHLCDataset dataset = createHighLowDataset1();
	        final JFreeChart chart = createChartOHLC(dataset);
	        chart.setBackgroundPaint(java.awt.Color.white);
			
			ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			filename = ServletUtilities.saveChartAsPNG(chart, 500, 270, info, session);
			ChartUtilities.writeImageMap(pw, filename, info,true);
			pw.flush();
		}  catch (Exception e) {
			Logging.error("Exception P21- " + e.toString());
			e.printStackTrace(System.out);
			}
		Logging.info("end of generatePieChart");
		return filename;
	}
    
 public static OHLCDataset createHighLowDataset1() {
    	
    	
       final Date[] date = new Date[year.size()];
       final double[] high1 = new double[year.size()];
       final double[] low1 = new double[year.size()];
       final double[] open1 = new double[year.size()];
       final double[] close1 = new double[year.size()];
       final double[] volume1 = new double[year.size()];
       Logging.debug("Inside high low dataset4");
       if(year.size()!=0)
       {
       for(int i=0;i<high.size();i++)
        {
        	
        	date[i]  = DateUtilities.createDate(Integer.parseInt((String)year.get(i)), Integer.parseInt((String)month.get(i)),Integer.parseInt((String)day.get(i)));//, Integer.parseInt((String)hours.get(i)), Integer.parseInt((String)minutes.get(i)));
        	high1[i]=Double.parseDouble((String)high.get(i));
        	low1[i]=Double.parseDouble((String)low.get(i));
        	open1[i]=Double.parseDouble((String)open.get(i));
        	close1[i]=Double.parseDouble((String)close.get(i));
        	volume1[i]=Double.parseDouble((String)volume.get(i));
        	Logging.debug("Inside high low dataset6");
        }
        Logging.debug("Inside high low dataset7");
        return new DefaultHighLowDataset("Series 1", date, high1, low1, open1, close1, volume1);
       }else
       {
       	DefaultHighLowDataset data= new DefaultHighLowDataset("Series 1", date, high1, low1, open1, close1, volume1);
       	return data=null;
       }
    }
 
 public static TimeSeries createFirstTimeSeries11(String iname1,String fd,String td) {
 	
     Connection connection=null;
	 indexname1=ComposeIndex.getIndexName(iname1);         
	try{
		Logging.debug("Inside try index name is "+indexname1);	
		field5=new ArrayList();
			field6=new ArrayList();
			field7=new ArrayList();
			field8=new ArrayList();
			field5.clear();
			field6.clear();field7.clear();field8.clear();
//			Connect con = new Connect();
			Connect con = ConnectInit.getConnect();
//			Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
			if(connection==null)
			{
			connection= con.getdbConnection();
			}
	//		PreparedStatement pst = connection.prepareStatement(con.queries.getProperty("moving_index_value"));
			PreparedStatement pst = connection.prepareStatement(ConnectInit.queries.getProperty("moving_index_value"));
			pst.setString(1,iname1);
			pst.setString(2,fd);
			pst.setString(3,td);				
			ResultSet rs = pst.executeQuery(); 
			while(rs.next())
	            {
	            	    String d=rs.getString("index_value_date");
	            	  // Logging.getDebug(d);
	            	    String year=d.substring(6,10);
	            	    String month=d.substring(3,5);
	            	    String day=d.substring(0,2);
	                    field5.add(year);
	                    field6.add(month);
	                    field7.add(day);
	                    String year1=rs.getString("index_closing_value");
	                    if(year1==null || year1.equals(""))
                        {
                        	field8.add("0.00");
                        	//Logging.getDebug("closing value is null "+year1);
                        }else{
                        	field8.add(year1);
                        	//Logging.getDebug("closing value is "+year1);
                        }
	            }
	          
	            for(int i=0;i<field5.size();i++)
	            {
	               String str=(String)field7.get(i)+(String)field6.get(i)+(String)field5.get(i);
	              
	            }
	           
	            for(int i=0;i<field6.size();i++)
	            {
	               String str=(String)field6.get(i);
	               Logging.debug(str);
	            }
	          
	           
			
		}catch(SQLException e){
			Logging.debug(e+"");
/*		JFrame frame=new JFrame();
		JOptionPane.showMessageDialog(frame,"Unable To Connect DataBase","ERROR!",JOptionPane.ERROR_MESSAGE);
		System.exit(0);
			*/
		}catch(Exception e){
			Logging.debug(e+"");
		}
//		Close the Dynamic Connection : Manoj Adekar
		finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception ee) {
				Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
			}
		}
		
		Logging.debug("abc");
        TimeSeries t1 = new TimeSeries(indexname1);
       // Logging.getDebug("field8.size()"+field8.size());
       // Logging.getDebug("field7.size()"+field7.size());
       // Logging.getDebug("field6.size()"+field6.size());
        //Logging.getDebug("field5.size()"+field5.size());
        if(field8.size()==0)
        {
        	return t1=null;
        }
        if(field6.size()!=0)
        {
	       try {
		        	for(int i=0;i<field5.size();i++)
		            {
		               String st="MonthConstants."+(String)field6.get(i);
		               //Logging.getDebug("st is "+st);
		                t1.add(new Day(Integer.parseInt((String)field7.get(i)), Integer.parseInt((String)field6.get(i)), Integer.parseInt((String)field5.get(i))), new Double((String)field8.get(i)));
		              
		            }	       	
	       }
	       catch (Exception e) {
	           System.err.println("Error E5: "+e.getMessage());
	       }
	       return t1;
        }else{
        	return t1=null;
        }
   }
 	
 
 	public static void ReadSectorContri(String iname,String fd,String td,Vector SectorContriData1)
	{
 		
	    indexname= ComposeIndex.getIndexName(iname);   
	 	indexSectContri= indexname;      
		try{
			SectContri1=new Vector();
			SectContri2=new Vector();
			String fdate=fd.trim();
			String tdate=td.trim();
			
			int i=0;
			while(i < SectorContriData1.size()){
				SectContri1.add(SectorContriData1.get(i));
				i++;
				SectContri2.add(SectorContriData1.get(i));
				i++;
			}
			/*
             Connect con = new Connect();
             if(con==null)
             {
				con.getConnection();
             }
             //String query1="select cls.class_name,sum(to_number((((s1.mcv-s2.mcv)/(i1.adjusted_tmcv-i2.adjusted_tmcv))*100),'9999999999999999999999999999999999999999d99')) as weightage from (select adjusted_tmcv,index_id from index_value_daily where index_id = ? and to_date(index_value_date,'dd-mm-yyyy')= (select max(to_date(index_value_date,'dd-mm-yyyy')) from index_value_daily ivd where index_id = ? and to_date(index_value_date,'dd-mm-yyyy') <= to_date(?,'dd-mm-yyyy') )) i1 , (select adjusted_tmcv,index_id from index_value_daily where index_id = ? and to_date(index_value_date,'dd-mm-yyyy')= (select max(to_date(index_value_date,'dd-mm-yyyy')) from index_value_daily ivd where index_id = ? and to_date(index_value_date,'dd-mm-yyyy') >= to_date(?,'dd-mm-yyyy') )) i2 , (select spd.mcv,spd.stock_id,ic.index_id,spd.stock_price_date from stock_price_daily spd, index_composition ic where ic.index_id = ? and to_date(spd.stock_price_date,'dd-mm-yyyy')= (select max(to_date(stock_price_date,'dd-mm-yyyy')) from stock_price_daily where to_date(stock_price_date,'dd-mm-yyyy') <= to_date(?,'dd-mm-yyyy')) and ic.stock_id=spd.stock_id) s1, (select spd.mcv,spd.stock_id,ic.index_id,spd.stock_price_date from stock_price_daily spd, index_composition ic where ic.index_id = ? and to_date(spd.stock_price_date,'dd-mm-yyyy')= (select max(to_date(stock_price_date,'dd-mm-yyyy')) from stock_price_daily where to_date(stock_price_date,'dd-mm-yyyy') >= to_date(?,'dd-mm-yyyy')) and ic.stock_id=spd.stock_id) s2 , stock_master sm ,class_company cc, class cls,company c where sm.company_id=cc.company_id and cc.class_id = cls.class_id and c.company_id=cc.company_id and cls.level_id=1 and (i1.index_id=i2.index_id) and (i1.index_id=s1.index_id) and (s1.index_id=s2.index_id) and (i1.adjusted_tmcv-i2.adjusted_tmcv<>0) and (s1.stock_id=s2.stock_id) and (s1.mcv-s2.mcv<>0) and sm.stock_id=s1.stock_id group by cls.class_name";
				PreparedStatement pst = Connect.con.prepareStatement(con.queries.getProperty("select_sector_contri_to_change_in_index"));
             //PreparedStatement pst = Connect.con.prepareStatement(con.queries.getProperty(query1));
				
				pst.setString(1,iname);
				pst.setString(2,iname);
				pst.setString(3,tdate);
				pst.setString(4,iname);
				pst.setString(5,iname);
				pst.setString(6,fdate);
				pst.setString(7,iname);
				pst.setString(8,tdate);
				pst.setString(9,iname);
				pst.setString(10,fdate);
				
				ResultSet rs = pst.executeQuery(); 
				while(rs.next())
	                {
	                	    String d=rs.getString(1);
	                	    
	                	    SectContri1.add(d);
	                        String year1=rs.getString(2);

	                        if(year1!=null)
	                        	if( year1.equals(""))
	                        		year1=null;
	                        if(year1==null)
	                        {
	                        	SectContri2.add("0.00");
	                        	//DatasetFactory1.field4.add("0.00");
	                        	Logging.getDebug("closing value is null "+year1);
	                        }else{
	                        	SectContri2.add(year1);
	                        	//DatasetFactory1.field4.add(year1);
	                        	Logging.getDebug("closing value is "+year1);
	                        }	                        
	                }
	              
				Connect.con.close();
				
				 
			}catch(SQLException e){
				Logging.getDebug(e);
			JFrame frame=new JFrame();
			JOptionPane.showMessageDialog(frame,"Unable To Connect DataBase","ERROR!",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
			*/
			
			} catch(Exception e){
				Logging.debug(e+"");
			}
			Logging.debug("abc");
			
				
 		}
 	 public String getGraphChartSectorContri(HttpSession session, PrintWriter pw){
    		//String filenameSect =" ";
 		
    	try
 		{
    		
    		String index1	="";
    		try{
    			index1 =(String)(session.getAttribute("index"));
    		}catch(Exception e){
    			Logging.error("not retrive data from session"+e);
    		}
    		String from =(String)(session.getAttribute("from"));
    		String to = (String)(session.getAttribute("to"));
    		Vector SectorContriData = (Vector)(session.getAttribute("SectorContriData"));
    		
    		//index1 ="3252";
    		//String from ="01-10-07";
    		//String to = "10-10-07";
    		
    		/*try{
    			
    			index1	  =ComposeIndex.getIndexName(index1); //indMultiSect[0];
       		
    		}catch(Exception e){
       			Logging.getError("Exception - " + e.toString());
    		}*/
    		
    		ReadSectorContri(index1,from,to,SectorContriData);
    		CategoryDataset categorydatasetSectContri = createDatasetSectContri();
    		JFreeChart jfreechartSContri = createChartSectContri(categorydatasetSectContri);
    		//CategoryDataset CDSet=createDatasetSect();
    		
 		/*
 		 	JFreeChart jfreechart1 = createChart(CDSet);
 		 	jfreechart1.setBackgroundPaint(java.awt.Color.white);
 		 	ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
 		 	filenameSect = ServletUtilities.saveChartAsPNG(jfreechart1, 500, 270, info, session);
 		*/
    		jfreechartSContri.setBackgroundPaint(java.awt.Color.white);
         	ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
     		filename = ServletUtilities.saveChartAsPNG(jfreechartSContri, 500, 270, info, session);
    		
     		ChartUtilities.writeImageMap(pw, filename, info,true);
     		pw.flush();
     	}  catch (Exception e) {
 			Logging.error("Exception P23- " + e.toString());
 			e.printStackTrace(System.out);
 		}
 		
 		return filename;
 	}
 	
 	static class CustomCylinderRenderer extends CylinderRenderer
    {

        public Paint getItemPaint(int i, int j)
        {
            return colors[j % colors.length];
        }

        private Paint colors[];

        public CustomCylinderRenderer(Paint apaint[])
        {
            colors = apaint;
        }
    }


    private static CategoryDataset createDatasetSectContri()
    {
        DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
        for(int i=0;i< SectContri1.size();i++){
        	String sContri1=((String)(SectContri1).get(i));//(Double)SectContri1.get(i);
        	double sContri2=Double.parseDouble((String)(SectContri2).get(i));
        	defaultcategorydataset.addValue(sContri2, "S1", sContri1);
        
        }
       
        return defaultcategorydataset;
    }

    private static JFreeChart createChartSectContri(CategoryDataset categorydataset)
    {
        JFreeChart jfreechart = ChartFactory.createBarChart3D("", "Sectors", "Weightage", categorydataset, PlotOrientation.HORIZONTAL, false, true, false);
        CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
        Paint apaint[] = createPaint();
        CustomCylinderRenderer customcylinderrenderer = new CustomCylinderRenderer(apaint);
        customcylinderrenderer.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_VERTICAL));
        customcylinderrenderer.setBaseOutlinePaint(Color.gray);
        customcylinderrenderer.setBaseOutlineStroke(new BasicStroke(0.1F));
        customcylinderrenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
        categoryplot.setRenderer(customcylinderrenderer);
        return jfreechart;
    }

    private static Paint[] createPaint()
    {
        Paint apaint[] = new Paint[SectContri1.size()];
        for(int i=0;i<SectContri1.size();i++){
        	        
	        float red=(float)Math.random();
	        float green=(float)Math.random();
	        float blue=(float)Math.random();
	        //Color.getHSBColor(red,green,blue);
            apaint[i] = new GradientPaint(0.0F, 0.0F, Color.white, 0.0F, 0.0F, Color.getHSBColor(red,green,blue));
        }
       
        return apaint;
    }

   /* public static JPanel createDemoPanel()
    {
        JFreeChart jfreechart = createChart(createDatasetSectContri());
        return new ChartPanel(jfreechart);
    }*/

    
	
}

