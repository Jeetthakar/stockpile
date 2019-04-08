
package harrier.income.com.report;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.jfree.chart.axis.SegmentedTimeline;
import org.jfree.chart.demo.servlet.ComposeIndex;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.WaferMapDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.FixedMillisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.DefaultHighLowDataset;
import org.jfree.data.xy.DefaultWindDataset;
import org.jfree.data.xy.WindDataset;
import org.jfree.date.DateUtilities;
import org.jfree.date.MonthConstants;

import com.harrier.initializeation.ConnectInit;

import app.BackTestCompute;
import app.Connect;


public abstract class DatasetFactory1 {
	static Logger Logging = Logger.getLogger(DatasetFactory1.class);
	
     static ArrayList field1,field2,field3,field4,field5,field6,field7,field8,year,month,day,hour,minute,high,low,open,close,volume;
     static Connection con;
    static String indexname,indexname1;
    static app.Connect con1 = new app.Connect();
    
    public static CategoryDataset createCategoryDataset() {

        final double[][] data = new double[][]
            {{10.0, 4.0, 15.0, 14.0},
             {-5.0, -7.0, 14.0, -3.0},
             {6.0, 17.0, -12.0, 7.0},
             {7.0, 15.0, 11.0, 0.0},
             {-8.0, -6.0, 10.0, -9.0},
             {9.0, 8.0, 0.0, 6.0},
             {-10.0, 9.0, 7.0, 7.0},
             {11.0, 13.0, 9.0, 9.0},
             {-3.0, 7.0, 11.0, -10.0}};

        return DatasetUtilities.createCategoryDataset("Series ", "Category ", data);

    }

   
    public static CategoryDataset createSingleCategoryDataset() {

        final Number[][] data = new Integer[][]
            {{new Integer(10)},
             {new Integer(-5)},
             {new Integer(6)},
             {new Integer(7)},
             {new Integer(-8)},
             {new Integer(9)},
             {new Integer(-10)},
             {new Integer(11)},
             {new Integer(-3)}};

        return DatasetUtilities.createCategoryDataset("Series ", "Category ", data);

    }

    
    public static CategoryDataset createSingleSeriesCategoryDataset() {

        final double[][] data = new double[][] {{10.0, -4.0, 15.0, 14.0}};

        return DatasetUtilities.createCategoryDataset("Series ", "Category ", data);

    }

  
    public static IntervalCategoryDataset createIntervalCategoryDataset() {

        return null;

    }

    
    //public static XYDataset createSampleXYDataset() {
        //return new SampleXYDataset();
    //}

   
    public static TimeSeriesCollection createTimeSeriesCollection1() {

        final TimeSeries t1 = new TimeSeries("Annual", "Year", "Value", Year.class);
        try {
            t1.add(new Year(1990), new Double(50.1));
            t1.add(new Year(1991), new Double(12.3));
            t1.add(new Year(1992), new Double(23.9));
            t1.add(new Year(1993), new Double(83.4));
            t1.add(new Year(1994), new Double(-34.7));
            t1.add(new Year(1995), new Double(76.5));
            t1.add(new Year(1996), new Double(10.0));
            t1.add(new Year(1997), new Double(-14.7));
            t1.add(new Year(1998), new Double(43.9));
            t1.add(new Year(1999), new Double(49.6));
            t1.add(new Year(2000), new Double(37.2));
            t1.add(new Year(2001), new Double(17.1));
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return new TimeSeriesCollection(t1);

    }

    
    public static TimeSeriesCollection createTimeSeriesCollection2() {

        final TimeSeriesCollection data = new TimeSeriesCollection();
        data.addSeries(createSecondTimeSeries1());
        return data;

    }

   
    public static TimeSeriesCollection createTimeSeriesCollection3() {

        final TimeSeriesCollection collection = new TimeSeriesCollection();
        collection.addSeries(createUSDTimeSeries());
        collection.addSeries(createEURTimeSeries());
        return collection;

    }

    
    public static TimeSeriesCollection createTimeSeriesCollection4() {

        final TimeSeries t4 = new TimeSeries("Test",
                                                 "Millisecond", "Value", FixedMillisecond.class);
        final Date now = new Date();
        try {
            t4.add(new FixedMillisecond(now.getTime() + 0), new Double(50.1));
            t4.add(new FixedMillisecond(now.getTime() + 1), new Double(12.3));
            t4.add(new FixedMillisecond(now.getTime() + 2), new Double(23.9));
            t4.add(new FixedMillisecond(now.getTime() + 3), new Double(83.4));
            t4.add(new FixedMillisecond(now.getTime() + 4), new Double(34.7));
            t4.add(new FixedMillisecond(now.getTime() + 5), new Double(76.5));
            t4.add(new FixedMillisecond(now.getTime() + 6), new Double(150.0));
            t4.add(new FixedMillisecond(now.getTime() + 7), new Double(414.7));
            t4.add(new FixedMillisecond(now.getTime() + 8), new Double(1500.9));
            t4.add(new FixedMillisecond(now.getTime() + 9), new Double(4530.6));
            t4.add(new FixedMillisecond(now.getTime() + 10), new Double(7337.2));
            t4.add(new FixedMillisecond(now.getTime() + 11), new Double(9117.1));
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return new TimeSeriesCollection(t4);

    }

   
    public static TimeSeries createUSDTimeSeries() {

        final TimeSeries t1 = new TimeSeries("USD/GBP");
        try {
            t1.add(new Day(2, MonthConstants.JANUARY, 2001), 1.4956);
            t1.add(new Day(3, MonthConstants.JANUARY, 2001), new Double(1.5047));
            t1.add(new Day(4, MonthConstants.JANUARY, 2001), new Double(1.4931));
            t1.add(new Day(5, MonthConstants.JANUARY, 2001), new Double(1.4955));
            t1.add(new Day(8, MonthConstants.JANUARY, 2001), new Double(1.4994));
            t1.add(new Day(9, MonthConstants.JANUARY, 2001), new Double(1.4911));
            t1.add(new Day(10, MonthConstants.JANUARY, 2001), new Double(1.4903));
            t1.add(new Day(11, MonthConstants.JANUARY, 2001), new Double(1.4947));
            t1.add(new Day(12, MonthConstants.JANUARY, 2001), new Double(1.4784));
            t1.add(new Day(15, MonthConstants.JANUARY, 2001), new Double(1.4787));
            t1.add(new Day(16, MonthConstants.JANUARY, 2001), new Double(1.4702));
            t1.add(new Day(17, MonthConstants.JANUARY, 2001), new Double(1.4729));
            t1.add(new Day(18, MonthConstants.JANUARY, 2001), new Double(1.4760));
            t1.add(new Day(19, MonthConstants.JANUARY, 2001), new Double(1.4685));
            t1.add(new Day(22, MonthConstants.JANUARY, 2001), new Double(1.4609));
            t1.add(new Day(23, MonthConstants.JANUARY, 2001), new Double(1.4709));
            t1.add(new Day(24, MonthConstants.JANUARY, 2001), new Double(1.4576));
            t1.add(new Day(25, MonthConstants.JANUARY, 2001), new Double(1.4589));
            t1.add(new Day(26, MonthConstants.JANUARY, 2001), new Double(1.4568));
            t1.add(new Day(29, MonthConstants.JANUARY, 2001), new Double(1.4566));
            t1.add(new Day(30, MonthConstants.JANUARY, 2001), new Double(1.4604));
            t1.add(new Day(31, MonthConstants.JANUARY, 2001), new Double(1.4616));
            t1.add(new Day(1, MonthConstants.FEBRUARY, 2001), new Double(1.4777));
            t1.add(new Day(2, MonthConstants.FEBRUARY, 2001), new Double(1.4687));
            t1.add(new Day(5, MonthConstants.FEBRUARY, 2001), new Double(1.4753));
            t1.add(new Day(6, MonthConstants.FEBRUARY, 2001), new Double(1.4605));
            t1.add(new Day(7, MonthConstants.FEBRUARY, 2001), new Double(1.4619));
            t1.add(new Day(8, MonthConstants.FEBRUARY, 2001), new Double(1.4453));
            t1.add(new Day(9, MonthConstants.FEBRUARY, 2001), new Double(1.4463));
            t1.add(new Day(12, MonthConstants.FEBRUARY, 2001), new Double(1.4521));
            t1.add(new Day(13, MonthConstants.FEBRUARY, 2001), new Double(1.4517));
            t1.add(new Day(14, MonthConstants.FEBRUARY, 2001), new Double(1.4601));
            t1.add(new Day(15, MonthConstants.FEBRUARY, 2001), new Double(1.4500));
            t1.add(new Day(16, MonthConstants.FEBRUARY, 2001), new Double(1.4517));
            t1.add(new Day(19, MonthConstants.FEBRUARY, 2001), new Double(1.4459));
            t1.add(new Day(20, MonthConstants.FEBRUARY, 2001), new Double(1.4449));
            t1.add(new Day(21, MonthConstants.FEBRUARY, 2001), new Double(1.4447));
            t1.add(new Day(22, MonthConstants.FEBRUARY, 2001), new Double(1.4465));
            t1.add(new Day(23, MonthConstants.FEBRUARY, 2001), new Double(1.4487));
            t1.add(new Day(26, MonthConstants.FEBRUARY, 2001), new Double(1.4417));
            t1.add(new Day(27, MonthConstants.FEBRUARY, 2001), new Double(1.4420));
            t1.add(new Day(28, MonthConstants.FEBRUARY, 2001), new Double(1.4421));
            t1.add(new Day(1, MonthConstants.MARCH, 2001), new Double(1.4547));
            t1.add(new Day(2, MonthConstants.MARCH, 2001), new Double(1.4741));
            t1.add(new Day(5, MonthConstants.MARCH, 2001), new Double(1.4686));
            t1.add(new Day(6, MonthConstants.MARCH, 2001), new Double(1.4667));
            t1.add(new Day(7, MonthConstants.MARCH, 2001), new Double(1.4618));
            t1.add(new Day(8, MonthConstants.MARCH, 2001), new Double(1.4685));
            t1.add(new Day(9, MonthConstants.MARCH, 2001), new Double(1.4677));
            t1.add(new Day(12, MonthConstants.MARCH, 2001), new Double(1.4660));
            t1.add(new Day(13, MonthConstants.MARCH, 2001), new Double(1.4526));
            t1.add(new Day(14, MonthConstants.MARCH, 2001), new Double(1.4483));
            t1.add(new Day(15, MonthConstants.MARCH, 2001), new Double(1.4441));
            t1.add(new Day(16, MonthConstants.MARCH, 2001), new Double(1.4303));
            t1.add(new Day(19, MonthConstants.MARCH, 2001), new Double(1.4259));
            t1.add(new Day(20, MonthConstants.MARCH, 2001), new Double(1.4283));
            t1.add(new Day(21, MonthConstants.MARCH, 2001), new Double(1.4293));
            t1.add(new Day(22, MonthConstants.MARCH, 2001), new Double(1.4192));
            t1.add(new Day(23, MonthConstants.MARCH, 2001), new Double(1.4293));
            t1.add(new Day(26, MonthConstants.MARCH, 2001), new Double(1.4334));
            t1.add(new Day(27, MonthConstants.MARCH, 2001), new Double(1.4371));
            t1.add(new Day(28, MonthConstants.MARCH, 2001), new Double(1.4347));
            t1.add(new Day(29, MonthConstants.MARCH, 2001), new Double(1.4362));
            t1.add(new Day(30, MonthConstants.MARCH, 2001), new Double(1.4217));
            t1.add(new Day(2, MonthConstants.APRIL, 2001), new Double(1.4205));
            t1.add(new Day(3, MonthConstants.APRIL, 2001), new Double(1.4270));
            t1.add(new Day(4, MonthConstants.APRIL, 2001), new Double(1.4333));
            t1.add(new Day(5, MonthConstants.APRIL, 2001), new Double(1.4287));
            t1.add(new Day(6, MonthConstants.APRIL, 2001), new Double(1.4395));
            t1.add(new Day(9, MonthConstants.APRIL, 2001), new Double(1.4494));
            t1.add(new Day(10, MonthConstants.APRIL, 2001), new Double(1.4385));
            t1.add(new Day(11, MonthConstants.APRIL, 2001), new Double(1.4348));
            t1.add(new Day(12, MonthConstants.APRIL, 2001), new Double(1.4402));
            t1.add(new Day(17, MonthConstants.APRIL, 2001), new Double(1.4314));
            t1.add(new Day(18, MonthConstants.APRIL, 2001), new Double(1.4197));
            t1.add(new Day(19, MonthConstants.APRIL, 2001), new Double(1.4365));
            t1.add(new Day(20, MonthConstants.APRIL, 2001), new Double(1.4416));
            t1.add(new Day(23, MonthConstants.APRIL, 2001), new Double(1.4396));
            t1.add(new Day(24, MonthConstants.APRIL, 2001), new Double(1.4360));
            t1.add(new Day(25, MonthConstants.APRIL, 2001), new Double(1.4397));
            t1.add(new Day(26, MonthConstants.APRIL, 2001), new Double(1.4402));
            t1.add(new Day(27, MonthConstants.APRIL, 2001), new Double(1.4366));
            t1.add(new Day(30, MonthConstants.APRIL, 2001), new Double(1.4309));
            t1.add(new Day(1, MonthConstants.MAY, 2001), new Double(1.4324));
            t1.add(new Day(2, MonthConstants.MAY, 2001), new Double(1.4336));
            t1.add(new Day(3, MonthConstants.MAY, 2001), new Double(1.4329));
            t1.add(new Day(4, MonthConstants.MAY, 2001), new Double(1.4375));
            t1.add(new Day(8, MonthConstants.MAY, 2001), new Double(1.4321));
            t1.add(new Day(9, MonthConstants.MAY, 2001), new Double(1.4219));
            t1.add(new Day(10, MonthConstants.MAY, 2001), new Double(1.4226));
            t1.add(new Day(11, MonthConstants.MAY, 2001), new Double(1.4199));
            t1.add(new Day(14, MonthConstants.MAY, 2001), new Double(1.4183));
            t1.add(new Day(15, MonthConstants.MAY, 2001), new Double(1.4218));
            t1.add(new Day(16, MonthConstants.MAY, 2001), new Double(1.4295));
            t1.add(new Day(17, MonthConstants.MAY, 2001), new Double(1.4296));
            t1.add(new Day(18, MonthConstants.MAY, 2001), new Double(1.4296));
            t1.add(new Day(21, MonthConstants.MAY, 2001), new Double(1.4366));
            t1.add(new Day(22, MonthConstants.MAY, 2001), new Double(1.4283));
            t1.add(new Day(23, MonthConstants.MAY, 2001), new Double(1.4244));
            t1.add(new Day(24, MonthConstants.MAY, 2001), new Double(1.4102));
            t1.add(new Day(25, MonthConstants.MAY, 2001), new Double(1.4205));
            t1.add(new Day(29, MonthConstants.MAY, 2001), new Double(1.4183));
            t1.add(new Day(30, MonthConstants.MAY, 2001), new Double(1.4230));
            t1.add(new Day(31, MonthConstants.MAY, 2001), new Double(1.4201));
            t1.add(new Day(1, MonthConstants.JUNE, 2001), new Double(1.4148));
            t1.add(new Day(4, MonthConstants.JUNE, 2001), new Double(1.4142));
            t1.add(new Day(5, MonthConstants.JUNE, 2001), new Double(1.4095));
            t1.add(new Day(6, MonthConstants.JUNE, 2001), new Double(1.3938));
            t1.add(new Day(7, MonthConstants.JUNE, 2001), new Double(1.3886));
            t1.add(new Day(8, MonthConstants.JUNE, 2001), new Double(1.3798));
            t1.add(new Day(11, MonthConstants.JUNE, 2001), new Double(1.3726));
            t1.add(new Day(12, MonthConstants.JUNE, 2001), new Double(1.3788));
            t1.add(new Day(13, MonthConstants.JUNE, 2001), new Double(1.3878));
            t1.add(new Day(14, MonthConstants.JUNE, 2001), new Double(1.4002));
            t1.add(new Day(15, MonthConstants.JUNE, 2001), new Double(1.4033));
            t1.add(new Day(18, MonthConstants.JUNE, 2001), new Double(1.4038));
            t1.add(new Day(19, MonthConstants.JUNE, 2001), new Double(1.4023));
            t1.add(new Day(20, MonthConstants.JUNE, 2001), new Double(1.3952));
            t1.add(new Day(21, MonthConstants.JUNE, 2001), new Double(1.4142));
            t1.add(new Day(22, MonthConstants.JUNE, 2001), new Double(1.4114));
            t1.add(new Day(25, MonthConstants.JUNE, 2001), new Double(1.4141));
            t1.add(new Day(26, MonthConstants.JUNE, 2001), new Double(1.4157));
            t1.add(new Day(27, MonthConstants.JUNE, 2001), new Double(1.4136));
            t1.add(new Day(28, MonthConstants.JUNE, 2001), new Double(1.4089));
            t1.add(new Day(29, MonthConstants.JUNE, 2001), new Double(1.4066));
            t1.add(new Day(2, MonthConstants.JULY, 2001), new Double(1.4154));
            t1.add(new Day(3, MonthConstants.JULY, 2001), new Double(1.4072));
            t1.add(new Day(4, MonthConstants.JULY, 2001), new Double(1.4064));
            t1.add(new Day(5, MonthConstants.JULY, 2001), new Double(1.3995));
            t1.add(new Day(6, MonthConstants.JULY, 2001), new Double(1.4070));
            t1.add(new Day(9, MonthConstants.JULY, 2001), new Double(1.4094));
            t1.add(new Day(10, MonthConstants.JULY, 2001), new Double(1.4113));
            t1.add(new Day(11, MonthConstants.JULY, 2001), new Double(1.4143));
            t1.add(new Day(12, MonthConstants.JULY, 2001), new Double(1.4061));
            t1.add(new Day(13, MonthConstants.JULY, 2001), new Double(1.4008));
            t1.add(new Day(16, MonthConstants.JULY, 2001), new Double(1.3999));
            t1.add(new Day(17, MonthConstants.JULY, 2001), new Double(1.4003));
            t1.add(new Day(18, MonthConstants.JULY, 2001), new Double(1.4155));
            t1.add(new Day(19, MonthConstants.JULY, 2001), new Double(1.4165));
            t1.add(new Day(20, MonthConstants.JULY, 2001), new Double(1.4282));
            t1.add(new Day(23, MonthConstants.JULY, 2001), new Double(1.4190));
            t1.add(new Day(24, MonthConstants.JULY, 2001), new Double(1.4200));
            t1.add(new Day(25, MonthConstants.JULY, 2001), new Double(1.4276));
            t1.add(new Day(26, MonthConstants.JULY, 2001), new Double(1.4275));
            t1.add(new Day(27, MonthConstants.JULY, 2001), new Double(1.4233));
            t1.add(new Day(30, MonthConstants.JULY, 2001), new Double(1.4246));
            t1.add(new Day(31, MonthConstants.JULY, 2001), new Double(1.4254));
            t1.add(new Day(1, MonthConstants.AUGUST, 2001), new Double(1.4319));
            t1.add(new Day(2, MonthConstants.AUGUST, 2001), new Double(1.4321));
            t1.add(new Day(3, MonthConstants.AUGUST, 2001), new Double(1.4293));
            t1.add(new Day(6, MonthConstants.AUGUST, 2001), new Double(1.4190));
            t1.add(new Day(7, MonthConstants.AUGUST, 2001), new Double(1.4176));
            t1.add(new Day(8, MonthConstants.AUGUST, 2001), new Double(1.4139));
            t1.add(new Day(9, MonthConstants.AUGUST, 2001), new Double(1.4214));
            t1.add(new Day(10, MonthConstants.AUGUST, 2001), new Double(1.4266));
            t1.add(new Day(11, MonthConstants.AUGUST, 2001), new Double(1.4220));
            t1.add(new Day(12, MonthConstants.AUGUST, 2001), new Double(1.4210));
            t1.add(new Day(15, MonthConstants.AUGUST, 2001), new Double(1.4383));
            t1.add(new Day(16, MonthConstants.AUGUST, 2001), new Double(1.4431));
            t1.add(new Day(17, MonthConstants.AUGUST, 2001), new Double(1.4445));
            t1.add(new Day(20, MonthConstants.AUGUST, 2001), new Double(1.4444));
            t1.add(new Day(21, MonthConstants.AUGUST, 2001), new Double(1.4483));
            t1.add(new Day(22, MonthConstants.AUGUST, 2001), new Double(1.4556));
            t1.add(new Day(23, MonthConstants.AUGUST, 2001), new Double(1.4468));
            t1.add(new Day(24, MonthConstants.AUGUST, 2001), new Double(1.4464));
            t1.add(new Day(28, MonthConstants.AUGUST, 2001), new Double(1.4483));
            t1.add(new Day(29, MonthConstants.AUGUST, 2001), new Double(1.4519));
            t1.add(new Day(30, MonthConstants.AUGUST, 2001), new Double(1.4494));
            t1.add(new Day(31, MonthConstants.AUGUST, 2001), new Double(1.4505));
            t1.add(new Day(3, MonthConstants.SEPTEMBER, 2001), new Double(1.4519));
            t1.add(new Day(4, MonthConstants.SEPTEMBER, 2001), new Double(1.4460));
            t1.add(new Day(5, MonthConstants.SEPTEMBER, 2001), new Double(1.4526));
            t1.add(new Day(6, MonthConstants.SEPTEMBER, 2001), new Double(1.4527));
            t1.add(new Day(7, MonthConstants.SEPTEMBER, 2001), new Double(1.4617));
            t1.add(new Day(10, MonthConstants.SEPTEMBER, 2001), new Double(1.4583));
            t1.add(new Day(11, MonthConstants.SEPTEMBER, 2001), new Double(1.4693));
            t1.add(new Day(12, MonthConstants.SEPTEMBER, 2001), new Double(1.4633));
            t1.add(new Day(13, MonthConstants.SEPTEMBER, 2001), new Double(1.4690));
            t1.add(new Day(14, MonthConstants.SEPTEMBER, 2001), new Double(1.4691));
            t1.add(new Day(17, MonthConstants.SEPTEMBER, 2001), new Double(1.4668));
            t1.add(new Day(18, MonthConstants.SEPTEMBER, 2001), new Double(1.4624));
            t1.add(new Day(19, MonthConstants.SEPTEMBER, 2001), new Double(1.4678));
            t1.add(new Day(20, MonthConstants.SEPTEMBER, 2001), new Double(1.4657));
            t1.add(new Day(21, MonthConstants.SEPTEMBER, 2001), new Double(1.4575));
            t1.add(new Day(24, MonthConstants.SEPTEMBER, 2001), new Double(1.4646));
            t1.add(new Day(25, MonthConstants.SEPTEMBER, 2001), new Double(1.4699));
            t1.add(new Day(26, MonthConstants.SEPTEMBER, 2001), new Double(1.4749));
            t1.add(new Day(27, MonthConstants.SEPTEMBER, 2001), new Double(1.4756));
            t1.add(new Day(28, MonthConstants.SEPTEMBER, 2001), new Double(1.4699));
            t1.add(new Day(1, MonthConstants.OCTOBER, 2001), new Double(1.4784));
            t1.add(new Day(2, MonthConstants.OCTOBER, 2001), new Double(1.4661));
            t1.add(new Day(3, MonthConstants.OCTOBER, 2001), new Double(1.4767));
            t1.add(new Day(4, MonthConstants.OCTOBER, 2001), new Double(1.4770));
            t1.add(new Day(5, MonthConstants.OCTOBER, 2001), new Double(1.4810));
            t1.add(new Day(8, MonthConstants.OCTOBER, 2001), new Double(1.4743));
            t1.add(new Day(9, MonthConstants.OCTOBER, 2001), new Double(1.4667));
            t1.add(new Day(10, MonthConstants.OCTOBER, 2001), new Double(1.4505));
            t1.add(new Day(11, MonthConstants.OCTOBER, 2001), new Double(1.4434));
            t1.add(new Day(12, MonthConstants.OCTOBER, 2001), new Double(1.4504));
            t1.add(new Day(15, MonthConstants.OCTOBER, 2001), new Double(1.4471));
            t1.add(new Day(16, MonthConstants.OCTOBER, 2001), new Double(1.4474));
            t1.add(new Day(17, MonthConstants.OCTOBER, 2001), new Double(1.4512));
            t1.add(new Day(18, MonthConstants.OCTOBER, 2001), new Double(1.4445));
            t1.add(new Day(19, MonthConstants.OCTOBER, 2001), new Double(1.4384));
            t1.add(new Day(22, MonthConstants.OCTOBER, 2001), new Double(1.4275));
            t1.add(new Day(23, MonthConstants.OCTOBER, 2001), new Double(1.4212));
            t1.add(new Day(24, MonthConstants.OCTOBER, 2001), new Double(1.4233));
            t1.add(new Day(25, MonthConstants.OCTOBER, 2001), new Double(1.4297));
            t1.add(new Day(26, MonthConstants.OCTOBER, 2001), new Double(1.4328));
            t1.add(new Day(29, MonthConstants.OCTOBER, 2001), new Double(1.4515));
            t1.add(new Day(30, MonthConstants.OCTOBER, 2001), new Double(1.4564));
            t1.add(new Day(31, MonthConstants.OCTOBER, 2001), new Double(1.4541));
            t1.add(new Day(1, MonthConstants.NOVEMBER, 2001), new Double(1.4624));
            t1.add(new Day(2, MonthConstants.NOVEMBER, 2001), new Double(1.4632));
            t1.add(new Day(5, MonthConstants.NOVEMBER, 2001), new Double(1.4570));
            t1.add(new Day(6, MonthConstants.NOVEMBER, 2001), new Double(1.4588));
            t1.add(new Day(7, MonthConstants.NOVEMBER, 2001), new Double(1.4646));
            t1.add(new Day(8, MonthConstants.NOVEMBER, 2001), new Double(1.4552));
            t1.add(new Day(9, MonthConstants.NOVEMBER, 2001), new Double(1.4579));
            t1.add(new Day(12, MonthConstants.NOVEMBER, 2001), new Double(1.4575));
            t1.add(new Day(13, MonthConstants.NOVEMBER, 2001), new Double(1.4429));
            t1.add(new Day(14, MonthConstants.NOVEMBER, 2001), new Double(1.4425));
            t1.add(new Day(15, MonthConstants.NOVEMBER, 2001), new Double(1.4318));
            t1.add(new Day(16, MonthConstants.NOVEMBER, 2001), new Double(1.4291));
            t1.add(new Day(19, MonthConstants.NOVEMBER, 2001), new Double(1.4140));
            t1.add(new Day(20, MonthConstants.NOVEMBER, 2001), new Double(1.4173));
            t1.add(new Day(21, MonthConstants.NOVEMBER, 2001), new Double(1.4132));
            t1.add(new Day(22, MonthConstants.NOVEMBER, 2001), new Double(1.4131));
            t1.add(new Day(23, MonthConstants.NOVEMBER, 2001), new Double(1.4083));
            t1.add(new Day(26, MonthConstants.NOVEMBER, 2001), new Double(1.4122));
            t1.add(new Day(27, MonthConstants.NOVEMBER, 2001), new Double(1.4136));
            t1.add(new Day(28, MonthConstants.NOVEMBER, 2001), new Double(1.4239));
            t1.add(new Day(29, MonthConstants.NOVEMBER, 2001), new Double(1.4225));
            t1.add(new Day(30, MonthConstants.NOVEMBER, 2001), new Double(1.4260));
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return t1;
    }

   
    public static TimeSeries createEURTimeSeries() {

        final TimeSeries t1 = new TimeSeries("EUR/GBP");
        try {
            t1.add(new Day(2, MonthConstants.JANUARY, 2001), new Double(1.5788));
            t1.add(new Day(3, MonthConstants.JANUARY, 2001), new Double(1.5913));
            t1.add(new Day(4, MonthConstants.JANUARY, 2001), new Double(1.5807));
            t1.add(new Day(5, MonthConstants.JANUARY, 2001), new Double(1.5711));
            t1.add(new Day(8, MonthConstants.JANUARY, 2001), new Double(1.5778));
            t1.add(new Day(9, MonthConstants.JANUARY, 2001), new Double(1.5851));
            t1.add(new Day(10, MonthConstants.JANUARY, 2001), new Double(1.5846));
            t1.add(new Day(11, MonthConstants.JANUARY, 2001), new Double(1.5727));
            t1.add(new Day(12, MonthConstants.JANUARY, 2001), new Double(1.5585));
            t1.add(new Day(15, MonthConstants.JANUARY, 2001), new Double(1.5694));
            t1.add(new Day(16, MonthConstants.JANUARY, 2001), new Double(1.5629));
            t1.add(new Day(17, MonthConstants.JANUARY, 2001), new Double(1.5831));
            t1.add(new Day(18, MonthConstants.JANUARY, 2001), new Double(1.5624));
            t1.add(new Day(19, MonthConstants.JANUARY, 2001), new Double(1.5694));
            t1.add(new Day(22, MonthConstants.JANUARY, 2001), new Double(1.5615));
            t1.add(new Day(23, MonthConstants.JANUARY, 2001), new Double(1.5656));
            t1.add(new Day(24, MonthConstants.JANUARY, 2001), new Double(1.5795));
            t1.add(new Day(25, MonthConstants.JANUARY, 2001), new Double(1.5852));
            t1.add(new Day(26, MonthConstants.JANUARY, 2001), new Double(1.5797));
            t1.add(new Day(29, MonthConstants.JANUARY, 2001), new Double(1.5862));
            t1.add(new Day(30, MonthConstants.JANUARY, 2001), new Double(1.5803));
            t1.add(new Day(31, MonthConstants.JANUARY, 2001), new Double(1.5714));
            t1.add(new Day(1, MonthConstants.FEBRUARY, 2001), new Double(1.5717));
            t1.add(new Day(2, MonthConstants.FEBRUARY, 2001), new Double(1.5735));
            t1.add(new Day(5, MonthConstants.FEBRUARY, 2001), new Double(1.5691));
            t1.add(new Day(6, MonthConstants.FEBRUARY, 2001), new Double(1.5676));
            t1.add(new Day(7, MonthConstants.FEBRUARY, 2001), new Double(1.5677));
            t1.add(new Day(8, MonthConstants.FEBRUARY, 2001), new Double(1.5737));
            t1.add(new Day(9, MonthConstants.FEBRUARY, 2001), new Double(1.5654));
            t1.add(new Day(12, MonthConstants.FEBRUARY, 2001), new Double(1.5621));
            t1.add(new Day(13, MonthConstants.FEBRUARY, 2001), new Double(1.5761));
            t1.add(new Day(14, MonthConstants.FEBRUARY, 2001), new Double(1.5898));
            t1.add(new Day(15, MonthConstants.FEBRUARY, 2001), new Double(1.6045));
            t1.add(new Day(16, MonthConstants.FEBRUARY, 2001), new Double(1.5852));
            t1.add(new Day(19, MonthConstants.FEBRUARY, 2001), new Double(1.5704));
            t1.add(new Day(20, MonthConstants.FEBRUARY, 2001), new Double(1.5892));
            t1.add(new Day(21, MonthConstants.FEBRUARY, 2001), new Double(1.5844));
            t1.add(new Day(22, MonthConstants.FEBRUARY, 2001), new Double(1.5934));
            t1.add(new Day(23, MonthConstants.FEBRUARY, 2001), new Double(1.5951));
            t1.add(new Day(26, MonthConstants.FEBRUARY, 2001), new Double(1.5848));
            t1.add(new Day(27, MonthConstants.FEBRUARY, 2001), new Double(1.5706));
            t1.add(new Day(28, MonthConstants.FEBRUARY, 2001), new Double(1.5680));
            t1.add(new Day(1, MonthConstants.MARCH, 2001), new Double(1.5645));
            t1.add(new Day(2, MonthConstants.MARCH, 2001), new Double(1.5754));
            t1.add(new Day(5, MonthConstants.MARCH, 2001), new Double(1.5808));
            t1.add(new Day(6, MonthConstants.MARCH, 2001), new Double(1.5766));
            t1.add(new Day(7, MonthConstants.MARCH, 2001), new Double(1.5756));
            t1.add(new Day(8, MonthConstants.MARCH, 2001), new Double(1.5760));
            t1.add(new Day(9, MonthConstants.MARCH, 2001), new Double(1.5748));
            t1.add(new Day(12, MonthConstants.MARCH, 2001), new Double(1.5779));
            t1.add(new Day(13, MonthConstants.MARCH, 2001), new Double(1.5837));
            t1.add(new Day(14, MonthConstants.MARCH, 2001), new Double(1.5886));
            t1.add(new Day(15, MonthConstants.MARCH, 2001), new Double(1.5931));
            t1.add(new Day(16, MonthConstants.MARCH, 2001), new Double(1.5945));
            t1.add(new Day(19, MonthConstants.MARCH, 2001), new Double(1.5880));
            t1.add(new Day(20, MonthConstants.MARCH, 2001), new Double(1.5817));
            t1.add(new Day(21, MonthConstants.MARCH, 2001), new Double(1.5927));
            t1.add(new Day(22, MonthConstants.MARCH, 2001), new Double(1.6065));
            t1.add(new Day(23, MonthConstants.MARCH, 2001), new Double(1.6006));
            t1.add(new Day(26, MonthConstants.MARCH, 2001), new Double(1.6007));
            t1.add(new Day(27, MonthConstants.MARCH, 2001), new Double(1.5989));
            t1.add(new Day(28, MonthConstants.MARCH, 2001), new Double(1.6135));
            t1.add(new Day(29, MonthConstants.MARCH, 2001), new Double(1.6282));
            t1.add(new Day(30, MonthConstants.MARCH, 2001), new Double(1.6090));
            t1.add(new Day(2, MonthConstants.APRIL, 2001), new Double(1.6107));
            t1.add(new Day(3, MonthConstants.APRIL, 2001), new Double(1.6093));
            t1.add(new Day(4, MonthConstants.APRIL, 2001), new Double(1.5880));
            t1.add(new Day(5, MonthConstants.APRIL, 2001), new Double(1.5931));
            t1.add(new Day(6, MonthConstants.APRIL, 2001), new Double(1.5968));
            t1.add(new Day(9, MonthConstants.APRIL, 2001), new Double(1.6072));
            t1.add(new Day(10, MonthConstants.APRIL, 2001), new Double(1.6167));
            t1.add(new Day(11, MonthConstants.APRIL, 2001), new Double(1.6214));
            t1.add(new Day(12, MonthConstants.APRIL, 2001), new Double(1.6120));
            t1.add(new Day(17, MonthConstants.APRIL, 2001), new Double(1.6229));
            t1.add(new Day(18, MonthConstants.APRIL, 2001), new Double(1.6298));
            t1.add(new Day(19, MonthConstants.APRIL, 2001), new Double(1.6159));
            t1.add(new Day(20, MonthConstants.APRIL, 2001), new Double(1.5996));
            t1.add(new Day(23, MonthConstants.APRIL, 2001), new Double(1.6042));
            t1.add(new Day(24, MonthConstants.APRIL, 2001), new Double(1.6061));
            t1.add(new Day(25, MonthConstants.APRIL, 2001), new Double(1.6045));
            t1.add(new Day(26, MonthConstants.APRIL, 2001), new Double(1.5970));
            t1.add(new Day(27, MonthConstants.APRIL, 2001), new Double(1.6095));
            t1.add(new Day(30, MonthConstants.APRIL, 2001), new Double(1.6141));
            t1.add(new Day(1, MonthConstants.MAY, 2001), new Double(1.6076));
            t1.add(new Day(2, MonthConstants.MAY, 2001), new Double(1.6077));
            t1.add(new Day(3, MonthConstants.MAY, 2001), new Double(1.6035));
            t1.add(new Day(4, MonthConstants.MAY, 2001), new Double(1.6060));
            t1.add(new Day(8, MonthConstants.MAY, 2001), new Double(1.6178));
            t1.add(new Day(9, MonthConstants.MAY, 2001), new Double(1.6083));
            t1.add(new Day(10, MonthConstants.MAY, 2001), new Double(1.6107));
            t1.add(new Day(11, MonthConstants.MAY, 2001), new Double(1.6209));
            t1.add(new Day(14, MonthConstants.MAY, 2001), new Double(1.6228));
            t1.add(new Day(15, MonthConstants.MAY, 2001), new Double(1.6184));
            t1.add(new Day(16, MonthConstants.MAY, 2001), new Double(1.6167));
            t1.add(new Day(17, MonthConstants.MAY, 2001), new Double(1.6223));
            t1.add(new Day(18, MonthConstants.MAY, 2001), new Double(1.6305));
            t1.add(new Day(21, MonthConstants.MAY, 2001), new Double(1.6420));
            t1.add(new Day(22, MonthConstants.MAY, 2001), new Double(1.6484));
            t1.add(new Day(23, MonthConstants.MAY, 2001), new Double(1.6547));
            t1.add(new Day(24, MonthConstants.MAY, 2001), new Double(1.6444));
            t1.add(new Day(25, MonthConstants.MAY, 2001), new Double(1.6577));
            t1.add(new Day(29, MonthConstants.MAY, 2001), new Double(1.6606));
            t1.add(new Day(30, MonthConstants.MAY, 2001), new Double(1.6604));
            t1.add(new Day(31, MonthConstants.MAY, 2001), new Double(1.6772));
            t1.add(new Day(1, MonthConstants.JUNE, 2001), new Double(1.6717));
            t1.add(new Day(4, MonthConstants.JUNE, 2001), new Double(1.6685));
            t1.add(new Day(5, MonthConstants.JUNE, 2001), new Double(1.6621));
            t1.add(new Day(6, MonthConstants.JUNE, 2001), new Double(1.6460));
            t1.add(new Day(7, MonthConstants.JUNE, 2001), new Double(1.6333));
            t1.add(new Day(8, MonthConstants.JUNE, 2001), new Double(1.6265));
            t1.add(new Day(11, MonthConstants.JUNE, 2001), new Double(1.6311));
            t1.add(new Day(12, MonthConstants.JUNE, 2001), new Double(1.6238));
            t1.add(new Day(13, MonthConstants.JUNE, 2001), new Double(1.6300));
            t1.add(new Day(14, MonthConstants.JUNE, 2001), new Double(1.6289));
            t1.add(new Day(15, MonthConstants.JUNE, 2001), new Double(1.6276));
            t1.add(new Day(18, MonthConstants.JUNE, 2001), new Double(1.6299));
            t1.add(new Day(19, MonthConstants.JUNE, 2001), new Double(1.6353));
            t1.add(new Day(20, MonthConstants.JUNE, 2001), new Double(1.6378));
            t1.add(new Day(21, MonthConstants.JUNE, 2001), new Double(1.6567));
            t1.add(new Day(22, MonthConstants.JUNE, 2001), new Double(1.6523));
            t1.add(new Day(25, MonthConstants.JUNE, 2001), new Double(1.6418));
            t1.add(new Day(26, MonthConstants.JUNE, 2001), new Double(1.6429));
            t1.add(new Day(27, MonthConstants.JUNE, 2001), new Double(1.6439));
            t1.add(new Day(28, MonthConstants.JUNE, 2001), new Double(1.6605));
            t1.add(new Day(29, MonthConstants.JUNE, 2001), new Double(1.6599));
            t1.add(new Day(2, MonthConstants.JULY, 2001), new Double(1.6727));
            t1.add(new Day(3, MonthConstants.JULY, 2001), new Double(1.6620));
            t1.add(new Day(4, MonthConstants.JULY, 2001), new Double(1.6628));
            t1.add(new Day(5, MonthConstants.JULY, 2001), new Double(1.6730));
            t1.add(new Day(6, MonthConstants.JULY, 2001), new Double(1.6649));
            t1.add(new Day(9, MonthConstants.JULY, 2001), new Double(1.6603));
            t1.add(new Day(10, MonthConstants.JULY, 2001), new Double(1.6489));
            t1.add(new Day(11, MonthConstants.JULY, 2001), new Double(1.6421));
            t1.add(new Day(12, MonthConstants.JULY, 2001), new Double(1.6498));
            t1.add(new Day(13, MonthConstants.JULY, 2001), new Double(1.6447));
            t1.add(new Day(16, MonthConstants.JULY, 2001), new Double(1.6373));
            t1.add(new Day(17, MonthConstants.JULY, 2001), new Double(1.6443));
            t1.add(new Day(18, MonthConstants.JULY, 2001), new Double(1.6246));
            t1.add(new Day(19, MonthConstants.JULY, 2001), new Double(1.6295));
            t1.add(new Day(20, MonthConstants.JULY, 2001), new Double(1.6362));
            t1.add(new Day(23, MonthConstants.JULY, 2001), new Double(1.6348));
            t1.add(new Day(24, MonthConstants.JULY, 2001), new Double(1.6242));
            t1.add(new Day(25, MonthConstants.JULY, 2001), new Double(1.6241));
            t1.add(new Day(26, MonthConstants.JULY, 2001), new Double(1.6281));
            t1.add(new Day(27, MonthConstants.JULY, 2001), new Double(1.6296));
            t1.add(new Day(30, MonthConstants.JULY, 2001), new Double(1.6279));
            t1.add(new Day(31, MonthConstants.JULY, 2001), new Double(1.6300));
            t1.add(new Day(1, MonthConstants.AUGUST, 2001), new Double(1.6290));
            t1.add(new Day(2, MonthConstants.AUGUST, 2001), new Double(1.6237));
            t1.add(new Day(3, MonthConstants.AUGUST, 2001), new Double(1.6138));
            t1.add(new Day(6, MonthConstants.AUGUST, 2001), new Double(1.6121));
            t1.add(new Day(7, MonthConstants.AUGUST, 2001), new Double(1.6170));
            t1.add(new Day(8, MonthConstants.AUGUST, 2001), new Double(1.6135));
            t1.add(new Day(9, MonthConstants.AUGUST, 2001), new Double(1.5996));
            t1.add(new Day(10, MonthConstants.AUGUST, 2001), new Double(1.5931));
            t1.add(new Day(13, MonthConstants.AUGUST, 2001), new Double(1.5828));
            t1.add(new Day(14, MonthConstants.AUGUST, 2001), new Double(1.5824));
            t1.add(new Day(15, MonthConstants.AUGUST, 2001), new Double(1.5783));
            t1.add(new Day(16, MonthConstants.AUGUST, 2001), new Double(1.5810));
            t1.add(new Day(17, MonthConstants.AUGUST, 2001), new Double(1.5761));
            t1.add(new Day(20, MonthConstants.AUGUST, 2001), new Double(1.5831));
            t1.add(new Day(21, MonthConstants.AUGUST, 2001), new Double(1.5870));
            t1.add(new Day(22, MonthConstants.AUGUST, 2001), new Double(1.5808));
            t1.add(new Day(23, MonthConstants.AUGUST, 2001), new Double(1.5845));
            t1.add(new Day(24, MonthConstants.AUGUST, 2001), new Double(1.5844));
            t1.add(new Day(28, MonthConstants.AUGUST, 2001), new Double(1.5924));
            t1.add(new Day(29, MonthConstants.AUGUST, 2001), new Double(1.5950));
            t1.add(new Day(30, MonthConstants.AUGUST, 2001), new Double(1.5941));
            t1.add(new Day(31, MonthConstants.AUGUST, 2001), new Double(1.5968));
            t1.add(new Day(3, MonthConstants.SEPTEMBER, 2001), new Double(1.6020));
            t1.add(new Day(4, MonthConstants.SEPTEMBER, 2001), new Double(1.6236));
            t1.add(new Day(5, MonthConstants.SEPTEMBER, 2001), new Double(1.6352));
            t1.add(new Day(6, MonthConstants.SEPTEMBER, 2001), new Double(1.6302));
            t1.add(new Day(7, MonthConstants.SEPTEMBER, 2001), new Double(1.6180));
            t1.add(new Day(10, MonthConstants.SEPTEMBER, 2001), new Double(1.6218));
            t1.add(new Day(11, MonthConstants.SEPTEMBER, 2001), new Double(1.6182));
            t1.add(new Day(12, MonthConstants.SEPTEMBER, 2001), new Double(1.6157));
            t1.add(new Day(13, MonthConstants.SEPTEMBER, 2001), new Double(1.6171));
            t1.add(new Day(14, MonthConstants.SEPTEMBER, 2001), new Double(1.5960));
            t1.add(new Day(17, MonthConstants.SEPTEMBER, 2001), new Double(1.5952));
            t1.add(new Day(18, MonthConstants.SEPTEMBER, 2001), new Double(1.5863));
            t1.add(new Day(19, MonthConstants.SEPTEMBER, 2001), new Double(1.5790));
            t1.add(new Day(20, MonthConstants.SEPTEMBER, 2001), new Double(1.5811));
            t1.add(new Day(21, MonthConstants.SEPTEMBER, 2001), new Double(1.5917));
            t1.add(new Day(24, MonthConstants.SEPTEMBER, 2001), new Double(1.6005));
            t1.add(new Day(25, MonthConstants.SEPTEMBER, 2001), new Double(1.5915));
            t1.add(new Day(26, MonthConstants.SEPTEMBER, 2001), new Double(1.6012));
            t1.add(new Day(27, MonthConstants.SEPTEMBER, 2001), new Double(1.6032));
            t1.add(new Day(28, MonthConstants.SEPTEMBER, 2001), new Double(1.6133));
            t1.add(new Day(1, MonthConstants.OCTOBER, 2001), new Double(1.6147));
            t1.add(new Day(2, MonthConstants.OCTOBER, 2001), new Double(1.6002));
            t1.add(new Day(3, MonthConstants.OCTOBER, 2001), new Double(1.6041));
            t1.add(new Day(4, MonthConstants.OCTOBER, 2001), new Double(1.6172));
            t1.add(new Day(5, MonthConstants.OCTOBER, 2001), new Double(1.6121));
            t1.add(new Day(8, MonthConstants.OCTOBER, 2001), new Double(1.6044));
            t1.add(new Day(9, MonthConstants.OCTOBER, 2001), new Double(1.5974));
            t1.add(new Day(10, MonthConstants.OCTOBER, 2001), new Double(1.5915));
            t1.add(new Day(11, MonthConstants.OCTOBER, 2001), new Double(1.6022));
            t1.add(new Day(12, MonthConstants.OCTOBER, 2001), new Double(1.6014));
            t1.add(new Day(15, MonthConstants.OCTOBER, 2001), new Double(1.5942));
            t1.add(new Day(16, MonthConstants.OCTOBER, 2001), new Double(1.5925));
            t1.add(new Day(17, MonthConstants.OCTOBER, 2001), new Double(1.6007));
            t1.add(new Day(18, MonthConstants.OCTOBER, 2001), new Double(1.6000));
            t1.add(new Day(19, MonthConstants.OCTOBER, 2001), new Double(1.6030));
            t1.add(new Day(22, MonthConstants.OCTOBER, 2001), new Double(1.6014));
            t1.add(new Day(23, MonthConstants.OCTOBER, 2001), new Double(1.5995));
            t1.add(new Day(24, MonthConstants.OCTOBER, 2001), new Double(1.5951));
            t1.add(new Day(25, MonthConstants.OCTOBER, 2001), new Double(1.5953));
            t1.add(new Day(26, MonthConstants.OCTOBER, 2001), new Double(1.6057));
            t1.add(new Day(29, MonthConstants.OCTOBER, 2001), new Double(1.6051));
            t1.add(new Day(30, MonthConstants.OCTOBER, 2001), new Double(1.6027));
            t1.add(new Day(31, MonthConstants.OCTOBER, 2001), new Double(1.6144));
            t1.add(new Day(1, MonthConstants.NOVEMBER, 2001), new Double(1.6139));
            t1.add(new Day(2, MonthConstants.NOVEMBER, 2001), new Double(1.6189));
            t1.add(new Day(5, MonthConstants.NOVEMBER, 2001), new Double(1.6248));
            t1.add(new Day(6, MonthConstants.NOVEMBER, 2001), new Double(1.6267));
            t1.add(new Day(7, MonthConstants.NOVEMBER, 2001), new Double(1.6281));
            t1.add(new Day(8, MonthConstants.NOVEMBER, 2001), new Double(1.6310));
            t1.add(new Day(9, MonthConstants.NOVEMBER, 2001), new Double(1.6313));
            t1.add(new Day(12, MonthConstants.NOVEMBER, 2001), new Double(1.6272));
            t1.add(new Day(13, MonthConstants.NOVEMBER, 2001), new Double(1.6361));
            t1.add(new Day(14, MonthConstants.NOVEMBER, 2001), new Double(1.6323));
            t1.add(new Day(15, MonthConstants.NOVEMBER, 2001), new Double(1.6252));
            t1.add(new Day(16, MonthConstants.NOVEMBER, 2001), new Double(1.6141));
            t1.add(new Day(19, MonthConstants.NOVEMBER, 2001), new Double(1.6086));
            t1.add(new Day(20, MonthConstants.NOVEMBER, 2001), new Double(1.6055));
            t1.add(new Day(21, MonthConstants.NOVEMBER, 2001), new Double(1.6132));
            t1.add(new Day(22, MonthConstants.NOVEMBER, 2001), new Double(1.6074));
            t1.add(new Day(23, MonthConstants.NOVEMBER, 2001), new Double(1.6065));
            t1.add(new Day(26, MonthConstants.NOVEMBER, 2001), new Double(1.6061));
            t1.add(new Day(27, MonthConstants.NOVEMBER, 2001), new Double(1.6039));
            t1.add(new Day(28, MonthConstants.NOVEMBER, 2001), new Double(1.6069));
            t1.add(new Day(29, MonthConstants.NOVEMBER, 2001), new Double(1.6044));
            t1.add(new Day(30, MonthConstants.NOVEMBER, 2001), new Double(1.5928));
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return t1;
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
     
    public static void Readdata(String iname,String fd,String td)
		{
    	 indexname= ComposeIndex.getIndexName(iname);   
                   
			try{
					field1=new ArrayList();
					field2=new ArrayList();
					field3=new ArrayList();
					field4=new ArrayList();
                    app.Connect con = new app.Connect();
                    if(Connect.con==null)
                    {
					con.getConnection();
                    }
					PreparedStatement pst = Connect.con.prepareStatement(ConnectInit.queries.getProperty("moving_index_value"));
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
		                        if(year1==null || year1.equals(""))
		                        {
		                        	field4.add("0.00");
		                        	Logging.debug("closing value is null "+year1);
		                        }else{
		                        	field4.add(year1);
		                        	Logging.debug("closing value is "+year1);
		                        }		                        
		                }
		              
		                for(int i=0;i<field1.size();i++)
	                    {
	                       String str=(String)field3.get(i)+(String)field2.get(i)+(String)field1.get(i);
	                      
	                    }
		               
		                for(int i=0;i<field4.size();i++)
	                    {
	                       String str=(String)field4.get(i);
	                       Logging.debug(str);
	                    }
				}catch(SQLException e){
					Logging.debug(e);
				JFrame frame=new JFrame();
				JOptionPane.showMessageDialog(frame,"Unable To Connect DataBase","ERROR!",JOptionPane.ERROR_MESSAGE);
				System.exit(0);
					
				}catch(Exception e){
					Logging.debug(e);
				}
				Logging.debug("abc");
				
		}
    public static void DivisorReaddata(String iname,String fd,String td)
	{
    	 indexname= ComposeIndex.getIndexName(iname);     
             Logging.debug("inside divisor readdata");
             Logging.debug(iname+" "+fd+" "+td);
               
		try{
				field1=new ArrayList();
				field2=new ArrayList();
				field3=new ArrayList();
				field4=new ArrayList();
				org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
                app.Connect con = new app.Connect();
                if(Connect.con==null)
                {
                	con.getConnection();
                }
				PreparedStatement pst = Connect.con.prepareStatement(ConnectInit.queries.getProperty("index_divisor_date_wise"));
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
			           Logging.debug(str);
			        }
			       
			        for(int i=0;i<field4.size();i++)
			        {
			           String str=(String)field4.get(i);
			           Logging.debug(str);
			        }  
			       // Logging.debug(field3.size());
			}catch(SQLException e){
				Logging.debug(e);
			JFrame frame=new JFrame();
			JOptionPane.showMessageDialog(frame,"Unable To Connect DataBase","ERROR!",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
				
			}catch(Exception e){
				Logging.debug(e);
			}
			Logging.debug("abc");
			
	}

    public static void Readdatacompare(String iname1,String fd,String td)
	{
    	Logging.debug("Inside readdata compare");
             indexname1=ComposeIndex.getIndexName(iname1);    
                
		try{
			Logging.debug("Inside try index name is "+indexname1);	
			field5=new ArrayList();
				field6=new ArrayList();
				field7=new ArrayList();
				field8=new ArrayList();
				app.Connect con = new app.Connect();
				if(Connect.con==null)
				{
				con.getConnection();
				}
				PreparedStatement pst = Connect.con.prepareStatement(ConnectInit.queries.getProperty("moving_index_value"));
				pst.setString(1,iname1);
				pst.setString(2,fd);
				pst.setString(3,td);				
				ResultSet rs = pst.executeQuery(); 
				while(rs.next())
	                {
	                	    String d=rs.getString("index_value_date");	                	   
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
	                        	//Logging.debug("closing value is null "+year1);
	                        }else{
	                        	field8.add(year1);
	                        	//Logging.debug("closing value is "+year1);
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
				Logging.debug(e);
			JFrame frame=new JFrame();
			JOptionPane.showMessageDialog(frame,"Unable To Connect DataBase","ERROR!",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
				
			}catch(Exception e){
				Logging.debug(e);
			}
			Logging.debug("abc");
			
	}

   
    public static TimeSeries createSecondTimeSeries1() {

         TimeSeries t1 = new TimeSeries(indexname1);
         if(field6.size()!=0)
         {
        try {
	        	for(int i=0;i<field5.size();i++)
	            {
	               String st="MonthConstants."+(String)field6.get(i);
	                t1.add(new Day(Integer.parseInt((String)field7.get(i)), Integer.parseInt((String)field6.get(i)), Integer.parseInt((String)field5.get(i))), new Double((String)field8.get(i)));
	               
	            }
        	
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return t1;
         }else{
         	return t1=null;
         }
    }
   
    public static TimeSeries createSecondTimeSeries11(String iname1,String fd,String td) {
    	
     indexname1=ComposeIndex.getIndexName(iname1);         
	try{
		Logging.debug("Inside try index name is "+indexname1);	
		field5=new ArrayList();
			field6=new ArrayList();
			field7=new ArrayList();
			field8=new ArrayList();
			app.Connect con = new app.Connect();
			if(Connect.con==null)
			{
			con.getConnection();
			}
			PreparedStatement pst = Connect.con.prepareStatement(ConnectInit.queries.getProperty("moving_index_value"));
			pst.setString(1,iname1);
			pst.setString(2,fd);
			pst.setString(3,td);				
			ResultSet rs = pst.executeQuery(); 
			while(rs.next())
	            {
	            	    String d=rs.getString("index_value_date");
	            	  // Logging.debug(d);
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
                        	//Logging.debug("closing value is null "+year1);
                        }else{
                        	field8.add(year1);
                        	//Logging.debug("closing value is "+year1);
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
			Logging.debug(e);
		JFrame frame=new JFrame();
		JOptionPane.showMessageDialog(frame,"Unable To Connect DataBase","ERROR!",JOptionPane.ERROR_MESSAGE);
		System.exit(0);
			
		}catch(Exception e){
			Logging.debug(e);
		}
		Logging.debug("abc");
        TimeSeries t1 = new TimeSeries(indexname1);
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
		                t1.add(new Day(Integer.parseInt((String)field7.get(i)), Integer.parseInt((String)field6.get(i)), Integer.parseInt((String)field5.get(i))), new Double((String)field8.get(i)));
		               
		            }
	       	
	       }
	       catch (Exception e) {
	           System.err.println(e.getMessage());
	       }
	       return t1;
        }else{
        	return t1=null;
        }
   }
    public static TimeSeries createFirstTimeSeries11(String iname1,String fd,String td) {
    	
     indexname1=ComposeIndex.getIndexName(iname1);         
	try{
		Logging.debug("Inside try index name is "+indexname1);	
		field5=new ArrayList();
			field6=new ArrayList();
			field7=new ArrayList();
			field8=new ArrayList();
			field5.clear();
			field6.clear();field7.clear();field8.clear();
			app.Connect con = new app.Connect();
			if(Connect.con==null)
			{
			con.getConnection();
			}
			PreparedStatement pst = Connect.con.prepareStatement(ConnectInit.queries.getProperty("moving_index_value"));
			pst.setString(1,iname1);
			pst.setString(2,fd);
			pst.setString(3,td);				
			ResultSet rs = pst.executeQuery(); 
			while(rs.next())
	            {
	            	    String d=rs.getString("index_value_date");
	            	  // Logging.debug(d);
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
                        	//Logging.debug("closing value is null "+year1);
                        }else{
                        	field8.add(year1);
                        	//Logging.debug("closing value is "+year1);
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
			Logging.debug(e);
		JFrame frame=new JFrame();
		JOptionPane.showMessageDialog(frame,"Unable To Connect DataBase","ERROR!",JOptionPane.ERROR_MESSAGE);
		System.exit(0);
			
		}catch(Exception e){
			Logging.debug(e);
		}
		Logging.debug("abc");
        TimeSeries t1 = new TimeSeries(indexname1);
       // Logging.debug("field8.size()"+field8.size());
       // Logging.debug("field7.size()"+field7.size());
       // Logging.debug("field6.size()"+field6.size());
        //Logging.debug("field5.size()"+field5.size());
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
		               //Logging.debug("st is "+st);
		                t1.add(new Day(Integer.parseInt((String)field7.get(i)), Integer.parseInt((String)field6.get(i)), Integer.parseInt((String)field5.get(i))), new Double((String)field8.get(i)));
		              
		            }	       	
	       }
	       catch (Exception e) {
	           System.err.println("Error : "+e.getMessage());
	       }
	       return t1;
        }else{
        	return t1=null;
        }
   }
    public static void candlestickReaddata(String iname,String fd,String td)
	{
             Logging.debug("Inside read method");
             //Logging.debug(iname+"  "+fd+"   "+td);
                
		try{
			 year=new ArrayList();
			 month=new ArrayList();
			 day=new ArrayList();
			// hours=new ArrayList();
			 //minutes=new ArrayList();
			 high=new ArrayList();
			 low=new ArrayList();
			 open=new ArrayList();
			 close=new ArrayList();
			 volume=new ArrayList();
                app.Connect con = new app.Connect();
                if(Connect.con==null)
                {
                	con.getConnection();
                }
				PreparedStatement pst = Connect.con.prepareStatement(ConnectInit.queries.getProperty("stock_price_daily_between_date"));
				
				pst.setString(1,fd);
				pst.setString(2,td);
				pst.setString(3,iname);
				Logging.debug("Inside read method");
				ResultSet rs1 = pst.executeQuery(); 
				 while(rs1.next())
			        {
				 		Logging.debug("1");
				 	String d=rs1.getString(5);
			        	    Logging.debug(d);
			        	    String y=d.substring(6,10);
			        	    String m=d.substring(3,5);
			        	    String dy=d.substring(0,2);
			                year.add(y);
			                month.add(m);
			                day.add(dy);
			                
			              // String t=rs1.getString(2);
			              // hours.add(t.substring(0,2));
			               //minutes.add(t.substring(3,5));
			               String h=rs1.getString(7);
			               Logging.debug(h);
			               if(h==null)
			               {
			               		h="0";
			               	}
			               //Logging.debug(h);
			               high.add(h);
			               String l=rs1.getString(6);
			               Logging.debug(l);
			               if(l==null)
			               {
			               		l="0";
			               	}
			               low.add(l);
			               String op=rs1.getString(3);
			               Logging.debug(op);
			               if(op==null)
			               {
			               		op="0";
			               	}
			               open.add(op);
			               String cl=rs1.getString(4);
			               Logging.debug(cl);
			               if(cl==null)
			               {
			               		cl="0";
			               	}
			               close.add(cl);
			               String v=rs1.getString(8);
			               Logging.debug(v);
			               if(v==null)
			               {
			               		v="0";
			               	}
			               volume.add(v);
			              
			        }         
				
			}catch(SQLException e){
				Logging.debug("ERROR : "+e.getMessage());
			JFrame frame=new JFrame();
			JOptionPane.showMessageDialog(frame,"Unable To Connect DataBase","ERROR!",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
				
			}catch(Exception e){
				Logging.debug("ERROR : "+e.getMessage());
			}
			Logging.debug("abc");
			
	}
    public static void candlestickReaddata(Vector v)
	{
             Logging.debug("Inside read method");
            
		try{
			 year=new ArrayList();
			 month=new ArrayList();
			 day=new ArrayList();
			// hours=new ArrayList();
			 //minutes=new ArrayList();
			 high=new ArrayList();
			 low=new ArrayList();
			 open=new ArrayList();
			 close=new ArrayList();
			 volume=new ArrayList();
			 Iterator iter=v.iterator(); 
               int i=0;
				 while(iter.hasNext())
			        {
				 		
						//String year=(String)iter.next();
	                    //field1.add(year);
	                   // iter.next();
	                    iter.next();
				 		//Logging.debug("1");
				 		 String op=(String)iter.next();
			               Logging.debug(op);
			               if(op==null)
			               {
			               		op="0";
			               	}
			               open.add(op);
			               String cl=(String)iter.next();
			               Logging.debug(cl);
			               if(cl==null)
			               {
			               		cl="0";
			               	}
			               close.add(cl);
			              
			                String l=(String)iter.next();
				               Logging.debug(l);
				               if(l==null)
				               {
				               		l="0";
				               	}
				               low.add(l);
			              // String t=rs1.getString(2);
			              // hours.add(t.substring(0,2));
			               //minutes.add(t.substring(3,5));
			               String h=(String)iter.next();
			               Logging.debug(h);
			               if(h==null)
			               {
			               		h="0";
			               	}
			               //Logging.debug(h);
			               high.add(h);
			               String vol=(String)iter.next();
			               Logging.debug(v);
			               if(vol==null)
			               {
			               		vol="0";
			               	}
			               volume.add(vol);
			               String mcv=(String)iter.next();
			               String trdvalue=(String)iter.next();
			               String ntrades=(String)iter.next();
			               String d=(String)iter.next();
			        	    Logging.debug(d);
			        	    String y=d.substring(6,10);
			        	    String m=d.substring(3,5);
			        	    String dy=d.substring(0,2);
			                year.add(y);
			                month.add(m);
			                day.add(dy);
			              
			        }	              	               				
			}catch(Exception e){
				Logging.debug("ERROR : "+e.getMessage());
			}
			Logging.debug("abc");
			
	}

   
    public static DefaultHighLowDataset createHighLowDataset1() {
    	
    	Logging.debug("Inside high low dataset");
       final Date[] date = new Date[year.size()];
       
       final double[] high1 = new double[year.size()];
       final double[] low1 = new double[year.size()];
       final double[] open1 = new double[year.size()];
       final double[] close1 = new double[year.size()];
       Logging.debug("Inside high low dataset2");
       final double[] volume1 = new double[year.size()];
       Logging.debug("Inside high low dataset4");
       if(year.size()!=0)
       {
       for(int i=0;i<high.size();i++)
        {
        	Logging.debug("Inside high low dataset5");
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
    public static void connect()
	{
                
                
		try{
				Logging.debug("Inside connect method");
               Class.forName("org.postgresql.Driver").newInstance();
				String url="jdbc:postgresql://192.168.0.11:5432/sample";
				con=DriverManager.getConnection(url,"sunil","sunil");
				   
				
			}catch(SQLException e){
				Logging.debug(e);
			JFrame frame=new JFrame();
			JOptionPane.showMessageDialog(frame,"Unable To Connect DataBase","ERROR!",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
				
			}catch(Exception e){
				Logging.debug("ERROR : "+e.getMessage());
			}
			
			
	}

  
    public static DefaultHighLowDataset createSegmentedHighLowDataset(
        final SegmentedTimeline timeline, final Date start) {

        // some open-high-low-close data
        final double[][] data =
               {{248.1999, 249.3999, 247.0499, 247.6999},
                {247.4999, 250.6499, 246.7999, 249.3999},
                {249.5999, 249.7499, 247.4999, 248.5999},
                {248.5999, 251.5499, 248.4999, 248.6499},
                {248.8499, 249.4499, 247.8499, 248.7999},
                {249.1999, 250.5499, 248.4999, 248.7999},
                {249.2999, 251.1499, 248.9499, 249.1499},
                {248.1499, 249.8999, 247.2999, 249.0499},
                {248.5999, 248.8999, 246.2999, 246.9499},
                {247.1999, 248.3999, 246.6499, 248.3499},
                {246.0999, 246.5999, 244.4999, 244.5999},
                {243.1999, 243.3999, 240.9499, 242.3499},
                {243.5999, 243.5999, 242.2499, 242.8999},
                {242.4999, 243.1499, 241.5999, 242.8499},
                {244.1999, 247.0499, 243.7499, 246.9999},
                {246.9499, 247.6499, 245.2999, 246.0499},
                {245.5999, 248.0999, 245.1999, 247.8999},
                {247.9499, 247.9499, 243.8499, 243.9499},
                {242.1999, 245.9499, 242.1999, 244.7499},
                {244.6499, 246.5999, 244.4999, 245.5999},
                {245.4499, 249.1999, 245.0999, 249.0999},
                {249.0999, 250.2999, 248.4499, 249.2499},
                {249.4999, 249.8499, 246.7499, 246.8499},
                {246.8499, 247.6499, 245.8999, 246.8499},
                {247.6999, 250.7999, 247.6999, 250.6999},
                {250.8999, 251.4499, 249.0999, 249.4999},
                {249.6499, 252.4999, 249.5999, 251.6499},
                {251.9499, 252.2999, 249.4999, 250.0499},
                {251.2499, 251.6999, 248.7999, 248.9499},
                {249.0999, 250.2499, 247.9499, 249.7499},
                {250.0499, 251.1499, 249.4499, 249.9499},
                {250.0499, 251.1499, 249.4499, 249.9499},
                {249.9999, 250.3499, 246.5999, 246.9499},
                {247.0999, 249.6999, 246.8999, 249.2999},
                {249.8999, 252.9499, 249.8499, 252.3999},
                {252.7999, 253.3499, 251.1999, 251.6999},
                {250.4999, 251.2999, 248.9499, 249.8999},
                {250.6999, 253.4499, 250.6999, 253.1999},
                {252.9999, 253.8999, 252.2999, 253.2499},
                {253.6999, 255.1999, 253.4999, 253.9499},
                {253.4499, 254.7999, 252.7999, 254.3499},
                {253.4499, 254.5999, 252.4999, 254.2999},
                {253.5999, 253.8999, 251.6999, 251.7999},
                {252.3499, 253.6999, 251.7999, 253.5499},
                {253.5499, 254.2499, 251.1999, 251.3499},
                {251.2499, 251.9499, 249.9999, 251.5999},
                {251.9499, 252.5999, 250.2499, 251.9999},
                {251.2499, 252.7499, 251.0999, 252.1999},
                {251.6499, 252.5499, 248.8499, 248.9499},
                {249.6499, 249.8999, 248.5499, 249.0999},
                {249.3499, 250.4499, 248.9499, 250.0999},
                {249.5499, 252.1499, 249.2999, 252.0499},
                {252.1499, 252.1499, 250.2499, 250.8499},
                {251.2499, 254.9499, 250.9999, 254.4499},
                {254.0999, 255.1999, 253.4499, 254.5999},
                {254.4999, 254.9499, 252.3999, 252.8999},
                {253.2999, 253.6499, 252.1499, 252.8999},
                {253.4999, 254.1499, 251.8999, 252.0499},
                {252.3499, 254.4499, 252.3499, 254.2999},
                {254.6499, 255.7499, 251.4499, 251.6499},
                {254.6499, 255.7499, 251.4499, 251.6499},
                {252.2499, 253.1499, 251.5999, 252.9499},
                {253.4499, 253.9499, 251.0999, 251.4999},
                {251.7499, 251.8499, 249.4499, 251.0999},
                {250.8499, 251.7999, 249.9499, 251.5499},
                {251.5499, 252.1499, 250.3499, 251.5999},
                {252.9999, 254.9499, 252.7999, 254.8499},
                {254.6999, 255.4499, 253.8999, 255.3499},
                {254.9999, 256.9500, 254.9999, 256.0999},
                {256.4500, 258.2499, 255.3499, 258.1499},
                {257.4500, 258.6499, 257.2499, 257.9500},
                {257.7499, 259.1499, 257.2000, 258.7999},
                {257.8999, 258.2000, 256.7499, 257.7000},
                {257.9500, 260.2999, 257.5999, 259.9500},
                {259.2499, 260.4500, 258.8499, 259.4999},
                {259.4500, 260.2499, 259.1499, 259.5499},
                {260.0499, 260.3499, 257.4999, 257.8999},
                {257.8999, 261.9999, 257.3999, 261.8999},
                {261.8999, 262.5499, 259.8499, 261.6499},
                {261.5499, 263.3499, 261.0999, 263.0499},
                {263.1499, 264.4500, 262.3499, 263.9999},
                {264.1499, 264.2999, 261.8499, 262.7999},
                {262.6499, 263.2499, 261.5499, 262.9500},
                {263.2999, 264.9500, 262.6499, 263.9500},
                {263.5999, 264.8499, 263.4500, 264.5999},
                {264.7499, 268.0999, 264.7499, 267.2499},
                {266.3499, 267.7499, 265.7000, 266.8499},
                {267.0999, 267.6499, 266.6499, 266.8499},
                {266.6499, 267.0499, 264.7499, 265.7499},
                {265.4500, 265.7499, 264.2499, 264.8999},
                {265.3499, 266.4500, 265.2999, 265.5999},
                {263.8499, 264.0499, 262.8499, 263.9999},
                {263.9500, 264.5499, 262.9500, 264.2999},
                {264.5999, 265.5499, 262.7499, 262.7999},
                {263.3999, 263.5499, 261.3999, 261.8999},
                {262.2000, 262.2000, 260.8499, 261.7000},
                {260.2499, 263.8499, 260.0999, 263.7000},
                {263.2999, 266.0999, 263.2999, 265.8999},
                {266.2000, 266.9999, 264.8499, 266.6499}};

            final int m = data.length;

            final Date[] date = new Date[m];
            final double[] high = new double[m];
            final double[] low = new double[m];
            final double[] open = new double[m];
            final double[] close = new double[m];
            final double[] volume = new double[m];

            final SegmentedTimeline.Segment segment = timeline.getSegment(start);
            for (int i = 0; i < m; i++) {
                while (!segment.inIncludeSegments()) {
                    segment.inc();
                }
                date[i] = segment.getDate();
                open[i] = data[i][0];
                high[i] = data[i][1];
                low[i] = data[i][2];
                close[i] = data[i][3];

                segment.inc();
            }

            return new DefaultHighLowDataset("Series 1", date, high, low, open, close, volume);

    }

   
    public static WindDataset createWindDataset1() {

        final int jan = 1;
        final Object[][][] data = new Object[][][] {{
            {DateUtilities.createDate(1999, jan, 3), new Double(0.0), new Double(10.0)},
            {DateUtilities.createDate(1999, jan, 4), new Double(1.0), new Double(8.5)},
            {DateUtilities.createDate(1999, jan, 5), new Double(2.0), new Double(10.0)},
            {DateUtilities.createDate(1999, jan, 6), new Double(3.0), new Double(10.0)},
            {DateUtilities.createDate(1999, jan, 7), new Double(4.0), new Double(7.0)},
            {DateUtilities.createDate(1999, jan, 8), new Double(5.0), new Double(10.0)},
            {DateUtilities.createDate(1999, jan, 9), new Double(6.0), new Double(8.0)},
            {DateUtilities.createDate(1999, jan, 10), new Double(7.0), new Double(11.0)},
            {DateUtilities.createDate(1999, jan, 11), new Double(8.0), new Double(10.0)},
            {DateUtilities.createDate(1999, jan, 12), new Double(9.0), new Double(11.0)},
            {DateUtilities.createDate(1999, jan, 13), new Double(10.0), new Double(3.0)},
            {DateUtilities.createDate(1999, jan, 14), new Double(11.0), new Double(9.0)},
            {DateUtilities.createDate(1999, jan, 15), new Double(12.0), new Double(11.0)},
            {DateUtilities.createDate(1999, jan, 16), new Double(0.0), new Double(0.0)} } };

        return new DefaultWindDataset(new String[] {"Wind!!"}, data);
    }

   
    public static WaferMapDataset createWaferMapDataset() {
        final WaferMapDataset data = new WaferMapDataset(30, 20);
        data.addValue(1, 5, 14); // (value, chipx, chipy)
        data.addValue(1, 5, 13);
        data.addValue(1, 5, 12);
        data.addValue(1, 5, 11);
        data.addValue(1, 5, 10);
        data.addValue(1, 5, 9);
        data.addValue(7, 5, 8);
        data.addValue(8, 5, 7);
        data.addValue(9, 5, 6);
        data.addValue(1, 6, 10);
        data.addValue(1, 7, 10);
        data.addValue(1, 8, 10);
        data.addValue(1, 9, 10);
        data.addValue(1, 10, 10);
        data.addValue(1, 11, 10);
        data.addValue(1, 11, 11);
        data.addValue(1, 11, 12);
        data.addValue(2, 11, 13);
        data.addValue(1, 11, 14);
        data.addValue(2, 11, 9);
        data.addValue(2, 11, 8);
        data.addValue(2, 11, 7);
        data.addValue(2, 11, 6);

        data.addValue(6, 16, 6);
        data.addValue(6, 17, 6);
        data.addValue(6, 18, 6);
        data.addValue(6, 19, 6);
        data.addValue(6, 20, 6);
        data.addValue(6, 21, 6);
        data.addValue(6, 22, 6);
        data.addValue(3, 19, 7);
        data.addValue(3, 19, 8);
        data.addValue(3, 19, 9);
        data.addValue(3, 19, 10);
        data.addValue(3, 19, 11);
        data.addValue(3, 19, 12);
        data.addValue(3, 19, 13);
        data.addValue(4, 19, 14);
        data.addValue(4, 18, 14);
        data.addValue(4, 17, 14);
        data.addValue(4, 16, 14);
        data.addValue(4, 20, 14);
        data.addValue(4, 21, 14);
        data.addValue(4, 22, 14);
        return data;
    } 

  
    public static WaferMapDataset createRandomWaferMapDataset(final int values) {
        final int xdim = 30;
        final int ydim = 20;
        final Random random = new Random();
        final WaferMapDataset data = new WaferMapDataset(xdim, ydim);
        for (int x = 1; x <= xdim; x++) {
            for (int y = 0; y < ydim; y++) {
                data.addValue(random.nextInt(values), x, y);
            }
        }
        return data;
    }

}
