package harrier.income.com.report;

import java.awt.Color;
import java.awt.Font;
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
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
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
import org.jfree.ui.RectangleInsets;
import org.jfree.util.Rotation;

import uk.ltd.getahead.dwr.ExecutionContext;
import app.Connect;

import com.harrier.initializeation.ConnectInit;


public class AjaxGraphMethods {
	static Logger Logging = Logger.getLogger(AjaxGraphMethods.class);
	public static Vector field1,field2,field3,field4;
	public static Vector move1,move2,move3,move4;
	public static Vector move14,move24,move34,move44;
	public static Vector move11,move21,move31,move41;
	public static Vector move12,move22,move32,move42;
	static String indexnameC1,indexnameC2,indexnameC3;
	public static Vector Iw1,Iw2,Iw3;
	public static String fileIndWtg=null;
	
	static Connect con1 = ConnectInit.getConnect();
	HttpSession session = ExecutionContext.get().getSession();	
	PrintWriter pw = new PrintWriter(System.out);		
	Vector Retrived = (Vector)session.getAttribute("StoredVector");

	//pw = res.getWriter();
	public static String filename=null;
	public static String graphURL=null;
	static String indexname,indexname1;
	
	  public String getGraphChartIndexCompare(String[] var, String fdate, String tdate){
	   		
		  String filename =" ";
	   		try
			{
	   		
	   		String index1=null;
	   		
	   		String[] indMulti =var;
	   		String index2	  =null;
	   		try{
	   		 index1	  =indMulti[0];
	   		 index2	  =indMulti[1];
	   		}catch(Exception e){
	   			Logging.error("Exception - " + e.toString());
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
				Logging.error("Exception - " + e.toString());
				e.printStackTrace(System.out);
			}
			
			
			graphURL ="/Stockpile/servlet/DisplayChart?filename=" + filename; 
			return graphURL;
		}
	   
	    
	    

		public static void Readdata1(String iname,String fd,String td)
		{
			Connection connection=null;
			indexname= ComposeIndex.getIndexName(iname);   
		 	indexnameC1= indexname;      
			try{
				move11=new Vector();
				move21=new Vector();
				move31=new Vector();
				move41=new Vector();
	        //        Connect con = new Connect();
				Connect con = ConnectInit.getConnect();
//	              Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
	                if(connection==null)
	                {
	                	connection=con.getdbConnection();
	                }
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
/*				JFrame frame=new JFrame();
				//JOptionPane.showMessageDialog(frame,"Unable To Connect DataBase","ERROR!",JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(frame,"Unable To Connect DataBase","ERROR!",JOptionPane.ERROR_MESSAGE);
				System.exit(0);
*/					
				}catch(Exception e){
					Logging.debug(e+"");
				}
//				Close the Dynamic Connection : Manoj Adekar
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
		
		public static void Readdata2(String iname,String fd,String td)
		{
			Connection connection=null;
			indexname= ComposeIndex.getIndexName(iname);   
			indexnameC2= indexname;         
			try{
				move12=new Vector();
				move22=new Vector();
				move32=new Vector();
				move42=new Vector();
	           //     Connect con = new Connect();
				Connect con = ConnectInit.getConnect();
//	              Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
	                if(connection==null)
	                {
	                	connection=con.getdbConnection();
	                }
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
	/*			JFrame frame=new JFrame();
				JOptionPane.showMessageDialog(frame,"Unable To Connect DataBase","ERROR!",JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			*/		
				}catch(Exception e){
					Logging.debug(e+"");
				}
//				Close the Dynamic Connection : Manoj Adekar
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
	        xyplot.setAxisOffset(new RectangleInsets( 5D, 5D, 5D, 5D));
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
	        //NumberAxis numberaxis2 = new NumberAxis("Range Axis 4");
	        //numberaxis2.setLabelPaint(Color.green);
	        //numberaxis2.setTickLabelPaint(Color.green);
	        //xyplot.setRangeAxis(3, numberaxis2);
	        //XYDataset xydataset3 = createDataset4();
	        //xyplot.setDataset(3, xydataset3);
	        //xyplot.mapDatasetToRangeAxis(3, 3);
	        //StandardXYItemRenderer standardxyitemrenderer2 = new StandardXYItemRenderer();
	        //standardxyitemrenderer2.setSeriesPaint(0, Color.green);
	        //xyplot.setRenderer(3, standardxyitemrenderer2);
	        return jfreechart;
	    }
	    private static XYDataset createDataset4()
	    {
	    	
	    	
	        TimeSeries timeseries = new TimeSeries("", org.jfree.data.time.Day.class);
	        
	        /*for(int i=(((move41).size())-1);i>=0;i--){
	            
	            int d3=	Integer.parseInt((String)(move31).get(i));
	            int d2=	Integer.parseInt((String)(move21).get(i));	
	            int d1=	Integer.parseInt((String)(move11).get(i));
	            double d4=	(Double.parseDouble((String)(move41).get(i)))-1000D;
	            timeseries.add(new Day(d3, d2,d1),d4);	
	            //timeseries.add(new Day(Integer.parseInt((String)(move3).get(i)), Integer.parseInt((String)(move2).get(i)), Integer.parseInt((String)(move1).get(i))),(Double)(move4).get(i));
	        }
	              */
	        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
	        timeseriescollection.addSeries(timeseries);
	      
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
		 
		 public void ReaddataCompose()
		 {
			
			 try{
					field1=new Vector();
					field2=new Vector();
					
					//Vector a = (Vector)session.getAttribute("ci2");
					Vector a = Retrived;
					Iterator iter=a.iterator(); 
					 int i=0;
					 
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
		                    iter.next();
		                   
		                    String year1=(String)iter.next();
		                    field2.add(year1);
		                    //iter.next();
		                    iter.next();
			           }
			           Logging.debug("value of field "+field2); 
				}catch(Exception e){
					Logging.error("Exception - " + e.getMessage());
				}
				Logging.debug("abc");
				
		}
		 public void ReadCompWtg()
		 {
			
			 try{
					field1=new Vector();
					field2=new Vector();
					
					//Vector a = (Vector)session.getAttribute("ci2");
					Vector a = Retrived;
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
			           Logging.debug("value of field "+field2); 
				}catch(Exception e){
					Logging.error("Exception - " + e.getMessage());
				}
				Logging.debug("abc");
				
		}
		 public String getGraphChartCompany1(String index,String date,String num)
		 {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy",Locale.UK);
			Date dDate = null;
			String selReport=num;
			if(num.equals("1")){
				ReaddataCompose();
			}
			if(num.equals("2")){
				ReadCompWtg();
			}
			//getTabledata(index);
			String filename = null;
			try {
					dDate = sdf.parse("01-Aug-2002");
					
					Logging.debug("Inside generatePieChart");
					DefaultPieDataset data = new DefaultPieDataset();
					for(int i=0;i<field1.size();i++)
					{
						double s1=Double.parseDouble((String)field2.get(i));
						Logging.debug("Double.parseDouble"+s1);
						data.setValue(((String)field1.get(i)),s1);
					   
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
					Logging.error("Exception - " + e.getMessage());
					//e.printStackTrace(System.out);
				
			}
			Logging.debug("end of getGraphChartCompany");
			graphURL = "/Stockpile/servlet/DisplayChart?filename="+filename;
			return graphURL;
			
		}
		 
//			for industry weightage
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
		    public void ReaddataIndweight()
			{
				try{
						Iw1=new Vector();
						Iw2=new Vector();
						Iw3=new Vector();
						//String index=(request.getSession()).getAttribute("varIndexId").toString();
						//Vector a =(Vector)getIndweighttable(index);
						//Vector a = (Vector)((request.getSession()).getAttribute("colected_vector_iw"));
						Vector a =Retrived;
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
				           //Logging.getDebug(""+i);
			                for(int j=0;j<Iw1.size();j++)
			                {
			                   String str=(String)Iw1.get(j);
			                   Logging.debug(str);
			                }
			                for(int j=0;j<Iw2.size();j++)
			                {
			                   String str=(String)Iw2.get(j);
			                   Logging.debug(str);
			                }
						
					}catch(Exception e){
						Logging.error("Exception - " + e.getMessage());
					}
					Logging.debug("abc");
					
			}
		    public String getGraphChartIndustry(String index)
		    {
		    	    	
		    	try {
		    		ReaddataIndweight();
		       	 	CategoryDataset categorydataset = createDatasetInd();
		            JFreeChart jfreechart = createChartInd(categorydataset);
		            
		         	ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			        String path = null;
			        fileIndWtg = ServletUtilities.saveChartAsPNG(jfreechart, 500, 270,info, session);
				
					ChartUtilities.writeImageMap(pw, fileIndWtg, info,true);
					pw.flush();
		    	}  catch (Exception e) {
					Logging.error("Exception - " + e.getMessage());
					//e.printStackTrace(System.out);
				
		    	}
				Logging.debug("end of getGraphChartCompany");
				//return fileIndWtg;
				graphURL = "/Stockpile/servlet/DisplayChart?filename="+fileIndWtg;
				return graphURL;
		    }
		    //for Index Divisor
		    public String getGraphChart1(String index, String fromdate, String todate, String chartType,String report )
		    {
			  
			  /* HttpSession varPageSession = varRquest.getSession();
			   
			  
			   
			   String from =(varPageSession.getAttribute("from")).toString();
			   String to = (varPageSession.getAttribute("to")).toString();
			   
			   String varIndexId =(varPageSession.getAttribute("varIndexId")).toString();
			   */
			   //Logging.getDebug(" *** varIndexId"+varIndexId);
		    	
		    	//commented on 08-09-07
				// String from =(String)(session.getAttribute("from"));
				 //String to = (String)(session.getAttribute("to"));
				// String varIndexId =(String)(session.getAttribute("varIndexId"));
				
			   this.DisplayChart(fromdate,todate,index,chartType,report);   
			   return graphURL;
		    }
		    public String DisplayChart(String f_from,String f_to,String f_varIndexId,String chartType, String report){
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy",Locale.UK);
				 String var=f_varIndexId;
			     filename=null;graphURL=null;
			    
			     String fromdate = f_from;
			     String toDate  =f_to;
			    Date dDate = null;
				try {
					dDate = sdf.parse("01-Aug-2002");
				} catch (Exception e) {
					Logging.debug(" Error : "+e.getMessage());
				}
				String rep1=null;
				 StringBuffer buffer =new StringBuffer();
				  try {	
					  		if(report.equals("maverage")){
					  			Readdata(var, fromdate, toDate);
					  			rep1="Moving";
					  		}if(report.equals("inddivisor")){
								DivisorReaddata(var, fromdate, toDate);	
								rep1="Divisor";
							}
							//filename = generateChart(dDate, request.getSession(), pw,var,"b");
							filename = movingindex(rep1,chartType);
						
						Logging.debug("filename is"+filename);		    
						//buffer.append("<img src='"+ graphURL +"' width='700' height='500' border='0' usemap='#"+filename+"' >");			
							  
			      	}catch(Exception e) {Logging.debug("Error : "+e.getMessage()); }                
			      	//graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + filename; 
			      	graphURL ="/Stockpile/servlet/DisplayChart?filename="+filename; 
			      	return graphURL;
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
		//				org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
					AdjustDecimal ad = ConnectInit.getAdjustDecimal();
					//        Connect con = new Connect();
						Connect con = ConnectInit.getConnect();
						//Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		                if(connection==null)
		                {
		                	connection=con.getdbConnection();
		                }
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
	/*				JFrame frame=new JFrame();
					JOptionPane.showMessageDialog(frame,"Unable To Connect DataBase","ERROR!",JOptionPane.ERROR_MESSAGE);
					System.exit(0);
		*/				
					}catch(Exception e){
						Logging.debug(e+"");
					}
//					Close the Dynamic Connection : Manoj Adekar
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
		    public static void Readdata(String iname,String fd,String td)
			{
		    	Connection connection=null;
		    	indexname= ComposeIndex.getIndexName(iname);   
		    	
		    	       
				try{
					move1=new Vector();
					move2=new Vector();
					move3=new Vector();
					move4=new Vector();
		      //          Connect con = new Connect();
					Connect con = ConnectInit.getConnect();
					//Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		                if(connection==null)
		                {
		                	connection=con.getdbConnection();
		                }
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
//					Close the Dynamic Connection : Manoj Adekar
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
		   
		    public String movingindex(String report1,String varChart){
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
					Logging.error("Exception - " + e.toString());
					e.printStackTrace(System.out);
					 
				}
				return filename;
			}
		    private static XYDataset createDataset(String s1)
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
		        TimeSeries timeseries1 = MovingAverage.createMovingAverage(timeseries, "15 day "+s1+" average", 30, 15);
		        timeseriescollection.addSeries(timeseries);
		        timeseriescollection.addSeries(timeseries1);
		        timeseriescollection.setXPosition(TimePeriodAnchor.MIDDLE);
		        return timeseriescollection;
		        		        
		    }
		    private static JFreeChart createChart(XYDataset xydataset)
		    {
				
		        
		        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("", "", "Value", xydataset, true, true, false);
		        XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		        XYItemRenderer xyitemrenderer = xyplot.getRenderer();
		        StandardXYToolTipGenerator standardxytooltipgenerator = new StandardXYToolTipGenerator("{0}: ({1}, {2})", new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00"));
		        xyitemrenderer.setBaseToolTipGenerator(standardxytooltipgenerator);
		        return jfreechart;
		        
		    }

		   

		    
		    
		    private static JFreeChart createChartArea(XYDataset xydataset)
		    {
		        JFreeChart jfreechart = ChartFactory.createXYAreaChart("", "Time", "Value", xydataset, PlotOrientation.VERTICAL, true, true, false);
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
//		  Stock High Lows
		    
		    private static JFreeChart createChartOHLC(OHLCDataset ohlcdataset)
		    {
		        JFreeChart jfreechart = ChartFactory.createCandlestickChart("Candlestick Demo 1", "Time", "Value", ohlcdataset, true);
		        XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		        NumberAxis numberaxis = (NumberAxis)xyplot.getRangeAxis();
		        numberaxis.setAutoRangeIncludesZero(false);
		        numberaxis.setUpperMargin(0.0D);
		        numberaxis.setLowerMargin(0.0D);
		        return jfreechart;
		    }
		    static ArrayList year,month,day,hour,minute,high,low,open,close,volume;
		    
		    public void candlestickReaddata()
			{
		    	Vector v= Retrived;   
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
						 		
						 		 String op=(String)iter.next();
					             if(op==null) {        		op="0";            	}
					             
					             open.add(op);
					             String cl=(String)iter.next();
					             if(cl==null) {				cl="0";				}
					             
					             close.add(cl);
					             String l=(String)iter.next();
						         if(l==null) {      		l="0";          	}
						         
						         low.add(l);
					             String h=(String)iter.next();
					             if(h==null) {		  		h="0";		       	}
					              
					             high.add(h);
					             String vol=(String)iter.next();
					             if(vol==null)   {			vol="0";          	}
					             
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
						Logging.error("ERROR : "+e.getMessage());
					}
					Logging.debug("abc");
					
			}
		    
		    public String stockDetails()
			{
		    
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy",Locale.UK);
				String filename = null;
				Date dDate = null;
				try {
					dDate = sdf.parse("01-Aug-2002");
				} catch (Exception e) {
					Logging.debug(" Error : "+e.getMessage());
				}
		    	try
				{
		    		candlestickReaddata();
			        final OHLCDataset dataset = createHighLowDataset1();
			        final JFreeChart chart = createChartOHLC(dataset);
			        chart.setBackgroundPaint(java.awt.Color.white);
					
					ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
					filename = ServletUtilities.saveChartAsPNG(chart, 500, 270, info, session);
					ChartUtilities.writeImageMap(pw, filename, info,true);
					pw.flush();
				}  catch (Exception e) {
					Logging.error("Exception - " + e.toString());
					e.printStackTrace(System.out);
					}
				Logging.info("end of generatePieChart");
				//return filename;
				graphURL ="/Stockpile/servlet/DisplayChart?filename="+filename; 
		      	return graphURL;
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
			
}
