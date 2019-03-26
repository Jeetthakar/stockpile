/*
 * Created on Sep 22, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 

package harrier.income.com.report;
//import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardLegend;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.servlet.ServletUtilities;
//import org.jfree.chart.renderer.LineAndShapeRenderer;
import org.jfree.data.CategoryDataset;
import org.jfree.data.DatasetUtilities;
import org.jfree.data.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import app.Logging;
import org.jfree.chart.demo.servlet.ComposeIndex;

*//**
 * A simple demonstration application showing how to create a line chart using data from a
 * {@link CategoryDataset}.
 *//*
public class AreaChart  {

    *//**
     * Creates a new demo.
     *
     * @param title  the frame title.
     *//*
	public static String series1,series2;
	public static Vector value1,value2;
	public static String generateChart(Date hitDate,HttpSession session, PrintWriter pw,String iname)
	{
		Logging.getDebug("in AreaChart generateChart");
		String filename = null;
    	try
		{
	        final CategoryDataset dataset = createDataset(iname);
	        if (value1.size() == 0) {
				System.out.println("No data has been found");
				throw new NoDataException();
			}

	        final JFreeChart chart = createChart(dataset);
	        //final ChartPanel chartPanel = new ChartPanel(chart);
	        //chartPanel.setPreferredSize(new Dimension(500, 270));
	        //setContentPane(chartPanel);
	        chart.setBackgroundPaint(java.awt.Color.white);
			System.out.println("before save as  generatePieChart");
			ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			filename = ServletUtilities.saveChartAsPNG(chart, 700, 500, info, session);
//			Write the image map to the PrintWriter
			ChartUtilities.writeImageMap(pw, filename, info);
			pw.flush();
		} catch (NoDataException e) {
			System.out.println(e.toString());
			filename = "public_nodata_500x300.png";
		}  catch (Exception e) {
			System.out.println("Exception - " + e.toString());
			e.printStackTrace(System.out);
			filename = "public_error_500x300.png";
		}
		System.out.println("end of generatePieChart");
		return filename;
    }

    *//**
     * Creates a sample dataset.
     * 
     * @return The dataset.
     *//*
    private static CategoryDataset createDataset(String iname) {
        
       
        // column keys...
        final String type1 = "Open";
        final String type2 = "High";
        final String type3 = "Low";
        final String type4 = "Close";
       
        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(Double.parseDouble((String)value1.get(0)), series1, type1);
        dataset.addValue(Double.parseDouble((String)value1.get(1)), series1, type2);
        dataset.addValue(Double.parseDouble((String)value1.get(2)), series1, type3);
        dataset.addValue(Double.parseDouble((String)value1.get(3)), series1, type4);
       
        dataset.addValue(Double.parseDouble((String)value2.get(0)), series2, type1);
        dataset.addValue(Double.parseDouble((String)value2.get(1)), series2, type2);
        dataset.addValue(Double.parseDouble((String)value2.get(2)), series2, type3);
        dataset.addValue(Double.parseDouble((String)value2.get(3)), series2, type4);
        
    	 // create a dataset...    	
    	String indexname= ComposeIndex.getIndexName(iname); 
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	for(int i=(((GraphMethods.field4).size())-1);i>=0;i--){
    		String st="MonthConstants."+(String)(GraphMethods.field2).get(i);
            dataset.addValue(Double.parseDouble((String)(GraphMethods.field4).get(i)), indexname,new Day(Integer.parseInt((String)(GraphMethods.field3).get(i)), Integer.parseInt((String)(GraphMethods.field2).get(i)), Integer.parseInt((String)(GraphMethods.field1).get(i))));
    	}
        final double[] data = new double[] {
            {1.0, 4.0, 3.0, 5.0, 5.0, 7.0, 7.0, 8.0},
            {5.0, 7.0, 6.0, 8.0, 4.0, 4.0, 2.0, 1.0},
            {4.0, 3.0, 2.0, 3.0, 6.0, 3.0, 4.0, 3.0}
        };

        final CategoryDataset dataset = DatasetUtilities.createCategoryDataset(
            "Series ", "Type ", data
        );

    	return dataset;
                
    }
    
    *//**
     * Creates a sample chart.
     * 
     * @param dataset  a dataset.
     * 
     * @return The chart.
     *//*
    private static JFreeChart  createChart(final CategoryDataset dataset) {
        
        // create the chart...
        final JFreeChart chart = ChartFactory.createAreaChart(
            "",       // chart title
            "Type",                    // domain axis label
            "Value",                   // range axis label
            dataset,                   // data
            PlotOrientation.VERTICAL,  // orientation
            true,                      // include legend
            true,                      // tooltips
            false                      // urls
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        final StandardLegend legend = (StandardLegend) chart.getLegend();
        legend.setDisplaySeriesShapes(true);
        legend.setShapeScaleX(1.5);
        legend.setShapeScaleY(1.5);
        legend.setDisplaySeriesLines(true);

        chart.setBackgroundPaint(Color.white);

        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.white);

        // customise the range axis...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(true);
        return chart;
    }
    public static void Readdata(Vector vd1,Vector vd2,String s1,String s2)
    {
    	 System.out.println("inside read method");
    	value1=new Vector();
    	value2=new Vector();
    	series1=ComposeIndex.getIndexName(s1);
    	series2=ComposeIndex.getIndexName(s2);
    	  for(int j=0;j<vd1.size();j++)
          {
             String str=(String)vd1.get(j);
             System.out.println(str);
             value1.add(str);
          }
    	  for(int j=0;j<vd2.size();j++)
          {
             String str=(String)vd2.get(j);
             System.out.println(str);
             value2.add(str);
          }
    	 
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
			String filename = AreaChart.generateChart(sdf.parse("01-Aug-2002"),null,  pw,null);
//			String filename = WebHitChart.generateXYChart("service", null, pw);
			System.out.println("filename - " + filename);

		} catch (Exception e) {
			System.out.println("Exception - " + e.toString());
			e.printStackTrace();
		}
		//return;
	
	}

}

*/