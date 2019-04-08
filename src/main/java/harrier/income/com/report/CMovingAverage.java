/*

package harrier.income.com.report;

//import candelchart.DatasetFactory1;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.servlet.http.HttpSession;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.StandardPieItemLabelGenerator;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.XYItemRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.DefaultPieDataset;
import org.jfree.data.MovingAverage;
import org.jfree.data.XYDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import app.Connect;
import app.Logging;

*//**
 * A time series chart.
 *//*
public class CMovingAverage {
	

    *//**
     * A demonstration application showing how to create a simple time series chart.
     *
     * @param title  the frame title.
     *//*
   
	public static String generatePieChart(Date hitDate,HttpSession session, PrintWriter pw,String maveragespan,String view)
    	{
       		String filename = null;
       		Logging.getDebug(" in generatePieChart view is "+view);
	    	try
			{
		        XYDataset dataset = createDataset(maveragespan,view);
		       	JFreeChart chart = createChart(dataset);
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
    	
    	public static String generatePieChart(HttpSession session, PrintWriter pw)
    	{
       		String filename = null;
       		String path = null;
	    	try
			{
		        XYDataset dataset = createDataset();
		       	JFreeChart chart = createChart(dataset);
				Logging.getDebug("After chart");
				chart.setBackgroundPaint(java.awt.Color.white);
				Logging.getDebug("before save as  generatePieChart");
				ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
				filename = ServletUtilities.saveChartAsPNG(chart, 700, 500, info, session);
	//			Write the image map to the PrintWriter
				//ChartUtilities.writeImageMap(pw, filename, info);
				Logging.getDebug("in ......."+filename);
				path=Connect.resourceurl;
				path=path+"/CoolMenus/"+filename;
				Logging.getDebug("in ......2."+path);
				ChartUtilities.saveChartAsPNG(new java.io.File(path),chart, 700, 500);
				pw.flush();
	    	}  catch (Exception e) {
				Logging.getError("Exception - " + e.toString());
				e.printStackTrace(System.out);				
			}
			Logging.getDebug("end of generatePieChart");
			return filename;
    	}
    	*//**
         * Creates a sample dataset.
         * 
         * @return a sample dataset.
         *//*
        private static XYDataset createDataset() {        
            final TimeSeries eur = GraphMethods.createFirstTimeSeries1();
           //final TimeSeries jpy = DatasetFactory1.createJPYTimeSeries1();
            final TimeSeriesCollection dataset;
            if(eur!=null)
           {
            final TimeSeries mav = MovingAverage.createMovingAverage(
                eur, "moving average", 30, 0
            );
            dataset = new TimeSeriesCollection();
            dataset.addSeries(eur);
            //dataset.addSeries(jpy);
            dataset.addSeries(mav);
            return dataset;
           }else
           {
           	 	return dataset=null;
           }
        }
    

    	 *//**
         * Creates a sample dataset.
         * 
         * @return a sample dataset.
         *//*
        private static XYDataset createDataset(String mavgspan,String view) {
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
    *//**
     * Creates a chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return a chart.
     *//*
    private static JFreeChart createChart( XYDataset dataset) {
    	 JFreeChart chart;
    	if(dataset!=null)
   	  {
	        chart = ChartFactory.createTimeSeriesChart(
	            "", 
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
   	  }else
   	  {
   	  		DefaultPieDataset data = new DefaultPieDataset();
   	  		PiePlot plot = new PiePlot(data);
			plot.setNoDataMessageFont(new Font("SansSerif", Font.PLAIN, 30));
			plot.setNoDataMessage("No data available");
		    chart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
   	  }
        return chart;
    }
    
    *//**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     *//*
    public static void main(java.lang.String[] args)
    {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.UK);
			PrintWriter pw = new PrintWriter(System.out);
			//String filename = WebHitChart.generateBarChart(sdf.parse("01-Aug-2002"), null, pw);
			String filename = CMovingAverage.generatePieChart(sdf.parse("01-Aug-2002"),null,  pw,"30","n");
//			String filename = WebHitChart.generateXYChart("service", null, pw);
			Logging.getDebug("filename - " + filename);

		} catch (Exception e) {
			Logging.getError("Exception - " + e.toString());
			e.printStackTrace();
		}
		//return;
	
	}
    
  }
*/