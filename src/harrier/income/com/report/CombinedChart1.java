/*
 * Created on Sep 24, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 
package harrier.income.com.report;

*//**
 * @author sunil
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 *//*
import java.awt.Font;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.Legend;
import org.jfree.chart.StandardLegend;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.renderer.BarRenderer;
import org.jfree.chart.renderer.AreaRenderer;
import org.jfree.chart.renderer.LineAndShapeRenderer;
import org.jfree.data.CategoryDataset;
import org.jfree.data.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import javax.servlet.http.HttpSession;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.entity.StandardEntityCollection;

import app.Logging;
import org.jfree.chart.demo.servlet.ComposeIndex;

*//**
 * A demo for the {@link CombinedDomainCategoryPlot} class.
 *//*
public class CombinedChart1  {

	public static String series1,series2;
	public static Vector value1,value2;
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
		ChartUtilities.writeImageMap(pw, filename, info);
		pw.flush();
		}  catch (Exception e) {
			app.Logging.getError("Exception - " + e.toString());
			e.printStackTrace(System.out);
			 
		}
		app.Logging.getDebug("end of generatePieChart");
		return filename;
	}

    *//**
     * Creates a dataset.
     *
     * @return A dataset.
     *//*
    private static CategoryDataset createDataset1(String iname) {
        
       
    	String indexname= ComposeIndex.getIndexName(iname); 
       
        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i=(((DatasetFactory1.field4).size())-1);i>=0;i--){
    		String st="MonthConstants."+(String)(DatasetFactory1.field2).get(i);
            dataset.addValue(Double.parseDouble((String)(DatasetFactory1.field4).get(i)), indexname,new Day(Integer.parseInt((String)(DatasetFactory1.field3).get(i)), Integer.parseInt((String)(DatasetFactory1.field2).get(i)), Integer.parseInt((String)(DatasetFactory1.field1).get(i))));
    	}
        return dataset;
                
    }
    private static CategoryDataset createDataset2(String iname) {
        
       

    	String indexname= ComposeIndex.getIndexName(iname); 
       
        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i=(((DatasetFactory1.field4).size())-1);i>=0;i--){
    		String st="MonthConstants."+(String)(DatasetFactory1.field2).get(i);
            dataset.addValue(Double.parseDouble((String)(DatasetFactory1.field4).get(i)), indexname,new Day(Integer.parseInt((String)(DatasetFactory1.field3).get(i)), Integer.parseInt((String)(DatasetFactory1.field2).get(i)), Integer.parseInt((String)(DatasetFactory1.field1).get(i))));
    	}
        return dataset;        
                
    }
    public static CategoryDataset createDataset1() {

        final DefaultCategoryDataset result = new DefaultCategoryDataset();

        // row keys...
        final String series1 = "First";
        final String series2 = "Second";

        // column keys...
        final String type1 = "Type 1";
        final String type2 = "Type 2";
        final String type3 = "Type 3";
        final String type4 = "Type 4";
        final String type5 = "Type 5";
        final String type6 = "Type 6";
        final String type7 = "Type 7";
        final String type8 = "Type 8";

        result.addValue(1.0, series1, type1);
        result.addValue(4.0, series1, type2);
        result.addValue(3.0, series1, type3);
        result.addValue(5.0, series1, type4);
        result.addValue(5.0, series1, type5);
        result.addValue(7.0, series1, type6);
        result.addValue(7.0, series1, type7);
        result.addValue(8.0, series1, type8);

        result.addValue(5.0, series2, type1);
        result.addValue(7.0, series2, type2);
        result.addValue(6.0, series2, type3);
        result.addValue(8.0, series2, type4);
        result.addValue(4.0, series2, type5);
        result.addValue(4.0, series2, type6);
        result.addValue(2.0, series2, type7);
        result.addValue(1.0, series2, type8);

        return result;

    }

    *//**
     * Creates a dataset.
     *
     * @return A dataset.
    *//* 
    public static CategoryDataset createDataset2() {

        final DefaultCategoryDataset result = new DefaultCategoryDataset();

        // row keys...
        final String series1 = "Third";
        final String series2 = "Fourth";

        // column keys...
        final String type1 = "Type 1";
        final String type2 = "Type 2";
        final String type3 = "Type 3";
        final String type4 = "Type 4";
        final String type5 = "Type 5";
        final String type6 = "Type 6";
        final String type7 = "Type 7";
        final String type8 = "Type 8";

        result.addValue(11.0, series1, type1);
        result.addValue(14.0, series1, type2);
        result.addValue(13.0, series1, type3);
        result.addValue(15.0, series1, type4);
        result.addValue(15.0, series1, type5);
        result.addValue(17.0, series1, type6);
        result.addValue(17.0, series1, type7);
        result.addValue(18.0, series1, type8);

        result.addValue(15.0, series2, type1);
        result.addValue(17.0, series2, type2);
        result.addValue(16.0, series2, type3);
        result.addValue(18.0, series2, type4);
        result.addValue(14.0, series2, type5);
        result.addValue(14.0, series2, type6);
        result.addValue(12.0, series2, type7);
        result.addValue(11.0, series2, type8);

        return result;

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
    
    *//**
     * Creates a chart.
     *
     * @return A chart.
     *//*
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
    public static void Readdata(Vector vd1,Vector vd2,String s1,String s2)
    {
    	 app.Logging.getDebug("inside read method");
    	value1=new Vector();
    	value2=new Vector();
    	series1=ComposeIndex.getIndexName(s1);
    	series2=ComposeIndex.getIndexName(s2);
    	  for(int j=0;j<vd1.size();j++)
          {
             String str=(String)vd1.get(j);
             app.Logging.getDebug(str);
             value1.add(str);
          }
    	  for(int j=0;j<vd2.size();j++)
          {
             String str=(String)vd2.get(j);
             app.Logging.getDebug(str);
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
			//String filename = CombinedChart.generateBarChart(sdf.parse("01-Aug-2002"), null, pw);
			String filename = CombinedChart1.generateChart(sdf.parse("01-Aug-2002"),null,  pw,null,"l");
//			String filename = WebHitChart.generateXYChart("service", null, pw);
			app.Logging.getDebug("filename - " + filename);

		} catch (Exception e) {
			app.Logging.getError("Exception - " + e.toString());
			e.printStackTrace();
		}
		//return;
	
	}

}
*/