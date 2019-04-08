

package harrier.income.com.report;


import java.awt.Font;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.DefaultHighLowDataset;

import app.BackTestCompute;

/**
 * A demo showing a candlestick chart.
 *
 */
public class Candlestick {

	static Logger Logging = Logger.getLogger(Candlestick.class);
	JFreeChart chart;
  
	public static String generateChart(Date hitDate,HttpSession session, PrintWriter pw)
	{
    //super(title);
		String filename = null;
    	try
		{
	        final DefaultHighLowDataset dataset = DatasetFactory1.createHighLowDataset1();
	       Logging.debug("Inside generate chart");
	        JFreeChart chart = ChartFactory.createCandlestickChart(
	                "Stock Details",
	                "Date", 
	                "Price (Rs. per share)",
	                dataset, 
	                false
	            );
	       chart = createChart(dataset);
	       Logging.debug("Inside generate chart1");
	       // chart.getXYPlot().setOrientation(PlotOrientation.VERTICAL);
	       // final ChartPanel chartPanel = new ChartPanel(chart);
	        //chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
	        //setContentPane(chartPanel);
	        chart.setBackgroundPaint(java.awt.Color.white);
			System.out.println("before save as  generatePieChart");
			ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			filename = ServletUtilities.saveChartAsPNG(chart, 700, 500, info, session);
//			Write the image map to the PrintWriter
			ChartUtilities.writeImageMap(pw, filename, info, false);
			pw.flush();
		}  catch (Exception e) {
			Logging.debug("Exception - " + e.toString());
			e.printStackTrace(System.out);
			}
		Logging.debug("end of generatePieChart");
		return filename;
	}
	
	
	/**
     *  a candlestick chart for Stock Details report
     *
     * @param title  the frame title.
     */
	public static String generateChart(HttpSession session, PrintWriter pw)
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
	        final DefaultHighLowDataset dataset = DatasetFactory1.createHighLowDataset1();
	       Logging.debug("Inside generate chart");
	        JFreeChart chart = ChartFactory.createCandlestickChart(
	                "Stock Details",
	                "Date", 
	                "Price (Rs. per share)",
	                dataset, 
	                false
	            );
	         chart = createChart(dataset);
	       Logging.debug("Inside generate chart1");
	       // chart.getXYPlot().setOrientation(PlotOrientation.VERTICAL);
	       // final ChartPanel chartPanel = new ChartPanel(chart);
	        //chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
	        //setContentPane(chartPanel);
	        chart.setBackgroundPaint(java.awt.Color.white);
			System.out.println("before save as  generatePieChart");
			ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			filename = ServletUtilities.saveChartAsPNG(chart, 700, 500, info, session);
//			Write the image map to the PrintWriter
			ChartUtilities.writeImageMap(pw, filename, info, false);
			pw.flush();
		}  catch (Exception e) {
			Logging.debug("Exception - " + e.toString());
			e.printStackTrace(System.out);
			}
		Logging.debug("end of generatePieChart");
		return filename;
	}
	
	

    /**
     * Creates a chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return The dataset.
     */
    private static JFreeChart createChart(final DefaultHighLowDataset dataset) {
    	 Logging.debug("Inside create chart");
    	  JFreeChart chart;
    	  if(dataset!=null)
    	  {
    	  	chart = ChartFactory.createCandlestickChart(
		            "Stock Details",
		            "Date", 
		            "Price (Rs. per share)",
		            dataset, 
		            true
		        );
		       Logging.debug("Inside jfree chart");
    	   
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
    
    // ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/guide.html                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    * 
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(java.lang.String[] args)
    {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.UK);
			PrintWriter pw = new PrintWriter(System.out);
			//String filename = WebHitChart.generateBarChart(sdf.parse("01-Aug-2002"), null, pw);
			String filename = Candlestick.generateChart(sdf.parse("01-Aug-2002"),null,  pw);
//			String filename = WebHitChart.generateXYChart("service", null, pw);
			Logging.debug("filename - " + filename);

		} catch (Exception e) {
			Logging.debug("Exception - " + e.toString());
			e.printStackTrace();
		}
		//return;
	
	}
}
