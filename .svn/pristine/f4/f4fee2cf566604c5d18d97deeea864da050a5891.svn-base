/*
 * Created on jun 12, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.fixedincome;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.jfree.chart.demo.servlet.AdjustDecimal;

import app.Connect;
import app.NewIndexForm;

import com.harrier.initializeation.ConnectInit;
/**
 * @author neha
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class FillTables {
	Logger Logging = Logger.getLogger(FillTables.class);
    public String removeStocksFromSourceTable(String[] keys,String indexType,
            Hashtable sourceTable, Hashtable destinationTable) {
    	String flag="no";
        if (!(sourceTable.isEmpty()) && (keys != null) && (keys.length > 0)) {

            for (int i = 0; i < keys.length; i++) {

                try {
                	Object obj = sourceTable.get(keys[i]);
                	StockDetails abd=(StockDetails)obj;
                	double iwf=abd.getIwf();	
                	if((indexType!=null && indexType.equals("1") && iwf!=1.0)){
                		Logging.debug("indexType is "+indexType);
                		flag="yes";
                		continue;
                	}
                    if (!destinationTable.containsKey(keys[i])) {
                        destinationTable.put(keys[i], obj);
                        sourceTable.remove(keys[i]);
                        flag="no";
                    } else {
                        sourceTable.remove(keys[i]);
                    }
                } catch (Exception e) {
                    Logging.debug("Error in addStocks " + e.getMessage());
                }
            }

        }
        if(flag==null)
        	flag="no";
        return flag;
        // setTotalMCV();
    }

    public void addStocksInSourceTable(String[] keys, Hashtable sourceTable,
            Hashtable destinationTable) {

        if (!(destinationTable.isEmpty()) && (keys.length > 0)
                && (keys != null)) {
            for (int i = 0; i < keys.length; i++) {
                try {
                    Logging.debug(keys[i]);
                    Object obj = destinationTable.get(keys[i]);
                    if (!sourceTable.containsKey(keys[i])) {
                        sourceTable.put(keys[i], obj);
                        destinationTable.remove(keys[i]);
                    } else {
                        destinationTable.remove(keys[i]);
                    }
                } catch (Exception e) {
                }
            }
        }
        Logging.debug("destinationTable.size() : "
                + destinationTable.size());
        //setTotalMCV();
    }

    public StringBuffer fixedIncomeAddRowsInTable(Hashtable data,String letter) {
    	 Logging.debug("letter in addRowsInTable is "+letter);
        StringBuffer buffer = new StringBuffer();
        try {
            if (data.isEmpty()) {
                return null;
            }

            //change here if problem occurs after sorting

            Set set = data.entrySet();
            Map.Entry [] entries = (Map.Entry[]) set.toArray(new Map.Entry[set
                    .size()]);
            Arrays.sort(entries, new Comparator() {
                public int compare(Object o1, Object o2) {
                    StockDetails v11 = (StockDetails) ((Map.Entry) o1)
                            .getValue();
                    StockDetails v22 = (StockDetails) ((Map.Entry) o2)
                            .getValue();
                    Object v1 = v11.getStockName();
                    Object v2 = v22.getStockName();
                    return ((Comparable) v1).compareTo(v2);
                }
            });
            int ix = 0;

            for (int i = 0; i < entries.length; i++) {

                String id = (String) entries[i].getKey();
                StockDetails rs = (StockDetails) data.get(id);
                String stock_name=rs.getStockName();
                String char1=stock_name.substring(0,1);
                //Logging.getDebug("letter is "+letter+"  char1 is "+char1);
                if(letter==null || (char1!=null && letter.equals(char1))){
                buffer.append("<tr>");

                buffer
                        .append("<td width='7%' align=center valign= center height='5'>");
                buffer.append("<input type='checkbox' name='stockid' value="
                        + rs.getStockID() + ">");
                buffer.append("</td>");

                buffer
                        .append("<td width='24%' align='left' valign='center' height='5'>");
                buffer.append("<font face='Arial' size='1' color='blue'>");
                buffer.append(rs.getStockName());
                buffer.append("</font>");
                buffer.append("</td>");

                buffer
                        .append("<td width='9%' align='right' valign='center' height='5'>");
                buffer.append("<font face='Arial' size='1' color='blue'>");
                buffer.append(rs.getIwf());
                buffer.append("</font>");
                buffer.append("<p></p>");
                buffer.append("</td>");

                buffer
                        .append("<td width='9%' align='right' valign='center' height='5'>");
                buffer.append("<font face='Arial' size='1' color='blue'>");
                buffer.append(rs.getLtp());
                buffer.append("</font>");
                buffer.append("</td>");

                buffer
                        .append("<td width='8%' align='left' valign='center' height='5'>");
                buffer.append("<font face='Arial' size='1' color='blue'>");
                buffer.append(rs.getCurrency());
                buffer.append("</font>");
                buffer.append("</td>");

                buffer
                        .append("<td width='11%' align='right' valign='center' height='5'>");
                buffer.append("<font face='Arial' size='1' color='blue'>");
                buffer.append(rs.getTis());
                buffer.append("</font>");
                buffer.append("</td>");

                buffer
                        .append("<td width='11%' align='right' valign='center' height='5'>");
                buffer.append("<font face='Arial' size='1' color='blue'>");
                buffer.append(rs.getMarket_lot());
                buffer.append("</font>");
                buffer.append("</td>");

                buffer
                        .append("<td width='11%' align='right' valign='center' height='5'>");
                buffer.append("<font face='Arial' size='1' color='blue'>");
                buffer.append(rs.getDate());
                buffer.append("</font>");
                buffer.append("</td>");
                buffer.append("</tr>");
            }
            }

        } catch (Exception e) {
          //  e.printStackTrace();
            Logging.debug("Error in inserting rows in Table "
                    + e.getMessage());

        }

        return buffer;

    }

    public StringBuffer fixedIncomeAddRowsInSecondTable(Hashtable data, String indexType,
            HttpServletRequest request) {
    	/*instead of creating object of index composition to get parent index currency id
    	 * try to get the currency id from index composition form since this method is being 
    	 * is being called from the index composition page .
    	*/
    	//IndexCompositionForm icf=new IndexCompositionForm();//created object of indexcomposition form class 
    	//String indexCurrencyId=icf.getParentCurrencyId();//get the index currency id from object
        HttpSession httpSession=request.getSession();
        FixedIncomeDefineIndexForm indexForm=(FixedIncomeDefineIndexForm)httpSession.getAttribute("FixedIncomeDefineIndexForm");
    	Map.Entry [] entries1;
        StringBuffer buffer = new StringBuffer();
        boolean readOnly = true;
        double total = 0.0;
   //     org.jfree.chart.demo.servlet.AdjustDecimal ad = new org.jfree.chart.demo.servlet.AdjustDecimal();
        AdjustDecimal ad = ConnectInit.getAdjustDecimal();
        if (data.isEmpty())
            return null;

        if (indexType.equals("2")) {

            readOnly = false;
        }

        for (Enumeration e = data.keys(); e.hasMoreElements();) {
            String id = e.nextElement().toString();
            StockDetails rs1 = (StockDetails) data.get(id);
            total += rs1.getMktCapital1(2,indexForm.getS_baseCurrency(),rs1.getCurrencyId());
            rs1.setTotalMktCapital(total);
        }

        Set set = data.entrySet();
        Map.Entry [] entries = (Map.Entry[]) set.toArray(new Map.Entry[set
                .size()]);
        Arrays.sort(entries, new Comparator() {
            public int compare(Object o1, Object o2) {
                //	StockDetails rs1 = (StockDetails)data.get(id);
                StockDetails v11 = (StockDetails) ((Map.Entry) o1).getValue();
                StockDetails v22 = (StockDetails) ((Map.Entry) o2).getValue();
                Object v1 = v11.getStockName();
                Object v2 = v22.getStockName();
                return ((Comparable) v1).compareTo(v2);
            }
        });
        int ix = 0;

        entries1 = new Map.Entry[entries.length];
        int marcapiwf = 0;
        try {
            marcapiwf = Integer.parseInt(indexType);
        } catch (Exception e) {
            marcapiwf = 2;
            // TODO: handle exception
        }
        for (int i = 0; i < entries.length; i++) {
            //	Logging.getDebug("Inside while");
            entries1[i] = entries[i];
            String id = (String) entries[i].getKey();
            /*
             * for(Enumeration e = data.keys();e.hasMoreElements();) { String id =
             * e.nextElement().toString();
             */
            StockDetails rs = (StockDetails) data.get(id);

            buffer.append("<tr>");
            buffer.append("<td width='5%' align='center'>");
            buffer.append("<input type='checkbox' name='stockid1' value="
                    + rs.getStockID() + ">");
            buffer.append("</td>");

            buffer.append("<td width='15%' align='left'>");
            buffer.append("<font face='Arial' size='1' color='blue'>");
            buffer.append(rs.getStockName());
            buffer.append("</font>");
            buffer.append("</td>");

            buffer.append("<td width='7%' align='right'>");
            buffer.append("<font face='Arial' size='1' color='blue'>");
            String iwf = null;
            String mkt_cap = ad.shareholdingpatt(rs.getMktCapital1(marcapiwf,indexForm.getS_baseCurrency(),rs.getCurrencyId()));
            String adjmkt_cap = ad.shareholdingpatt(rs
                    .getAdjustedMarket(marcapiwf));
            adjmkt_cap=mkt_cap;
            Logging.debug("before setting iwf with index type : "
                    + indexType);
            if (request.getParameter("iwf" + rs.getStockID()) == null) {
                Logging.debug("1");
                iwf = String.valueOf(rs.getIwf());
                if (indexType.equals("1")) {
                    Logging.debug("2");
                    iwf = "1.0";
                }
            } else if (indexType.equals("1")) {
                Logging.debug("2");
                iwf = "1.0";
            } else {
                Logging.debug("3");
                iwf = request.getParameter("iwf" + rs.getStockID());
            }
            Logging.debug("after setting iwf : " + iwf);
            Logging.debug(readOnly + "4indexType : " + indexType);
            if (!readOnly) {
                Logging.debug(readOnly + "4indexType : " + indexType);
                buffer.append("<input type= text name ='iwf" + rs.getStockID()
                        + "'");
                buffer
                        .append(" size='11' align='right' onblur='if((isit(FixedIncomeIndexCompositionForm.iwf"
                                + rs.getStockID()
                                + ".value))==false){FixedIncomeIndexCompositionForm.iwf"
                                + rs.getStockID()
                                + ".value=\"1.0\" }else{var padjmcap=(FixedIncomeIndexCompositionForm.adjmcap"+rs.getStockID()+".value); var cal"+rs.getStockID()+ "=FixedIncomeIndexCompositionForm.iwf"+ rs.getStockID()+".value*"+adjmkt_cap+";"+
                                		" FixedIncomeIndexCompositionForm.adjmcap"+rs.getStockID()+".value=cal"+rs.getStockID()+ ";alert(FixedIncomeIndexCompositionForm.totalMCV.value);alert((FixedIncomeIndexCompositionForm.totalMCV.value - padjmcap));var tmcv"+ "=((FixedIncomeIndexCompositionForm.totalMCV.value - padjmcap) + ((FixedIncomeIndexCompositionForm.iwf"+ rs.getStockID()+".value)*"+adjmkt_cap+"));"+
                                		" FixedIncomeIndexCompositionForm.totalMCV"+".value=tmcv"+ ";} '  value='"
                                + iwf + "' >");
            } else 
                buffer.append(iwf);

            buffer.append("</font>");
            buffer.append("</td>");
          /*  buffer.append("<td width='7%' align='right'>");
            buffer.append("<font face='Arial' size='1' color='blue'>");
            Logging.getDebug("inclusion exclusion date 4indexType : " + indexType);
            buffer.append("<input type='text' readonly='true'  size='14' name='incls_date" + rs.getStockID()+" value='' >");
            buffer.append("<input onclick='c2.popup('incls_date" + rs.getStockID()+"');'  type='button' value='...'/>'");
            buffer.append("</font>");
            buffer.append("</td>");*/

            buffer.append("<td width='9%' align='right'>");
            buffer.append("<font face='Arial' size='1' color='blue'>");
            buffer.append(rs.getLtp());
            buffer.append("</font>");
            buffer.append("</td>");
            
            buffer.append("<td width='8%' align='right'>");
            buffer.append("<font face='Arial' size='1' color='blue'>");
            buffer.append(rs.getMarket_lot());
            buffer.append("</font>");
            buffer.append("</td>");


            buffer.append("<td width='8%' align='left'>");
            buffer.append("<font face='Arial' size='1' color='blue'>");
            buffer.append(rs.getCurrency());
            buffer.append("</font>");
            buffer.append("</td>");

            buffer.append("<td width='13%' align='right'>");
            buffer.append("<font face='Arial' size='1' color='blue'>");
            buffer.append(rs.getOutStanding());
            buffer.append("</font>");
            buffer.append("</td>");
            
            
            /*double mcap_calc=((double)Double.parseDouble(adjmkt_cap))*((double)Double.parseDouble(iwf));
            adjmkt_cap=new Double(mcap_calc).toString();
            Logging.getDebug("adjmkt_cap after calculating is "+adjmkt_cap);*/
           
            buffer.append("<td width='11%' align='right'>");
            buffer.append("<font face='Arial' size='1' color='blue'>");
            buffer.append(mkt_cap);
            buffer.append("</font>");
            buffer.append("</td>");
           
            String striwf=request.getParameter("iwf"+ rs.getStockID());
            Logging.debug("striwf is after changing value "+striwf);
            buffer.append("<td width='26%' align='right'>");
            buffer.append("<font face='Arial' size='1' color='blue'>");
            Logging.debug(readOnly + "4indexType : " + indexType);
            buffer.append("<input name ='adjmcap"+rs.getStockID()+"'");
            buffer.append(" size='30' align='right'  value='"+ adjmkt_cap +"'>");// onfocus=' func("+rs.getStockID()+","+request.getParameter("iwf" + rs.getStockID())+","+adjmkt_cap+");' >");                    
            buffer.append("</font>");
            buffer.append("</td>");

            String weight = null;
            try {
                weight = ad.shareholdingpatt(rs.getWightage());
            } catch (Exception e) {
                weight = new Double(rs.getWightage()).toString();
                // TODO: handle exception
            }
            buffer.append("<td width='10%' align='right'>");
            buffer.append("<font face='Arial' size='1' color='blue'>");
            buffer.append(weight);
            buffer.append("</font>");
            buffer.append("</td>");
            buffer.append("</tr>");
        }

        return buffer;
    }
//with inclusion exclusion date of stock.
    public StringBuffer fixedIncomeAddRowsInSecondTableInclsDate(Hashtable data, String indexType,
            HttpServletRequest request) {
    	/*instead of creating object of index composition to get parent index currency id
    	 * try to get the currency id from index composition form since this method is being 
    	 * is being called from the index composition page .
    	*/
    	//IndexCompositionForm icf=new IndexCompositionForm();//created object of indexcomposition form class 
    	//String indexCurrencyId=icf.getParentCurrencyId();//get the index currency id from object
        HttpSession httpSession=request.getSession();
        FixedIncomeDefineIndexForm indexForm=(FixedIncomeDefineIndexForm)httpSession.getAttribute("FixedIncomeDefineIndexForm");
    	Map.Entry [] entries1;
        StringBuffer buffer = new StringBuffer();
        boolean readOnly = true;
        double total = 0.0;
  //      org.jfree.chart.demo.servlet.AdjustDecimal ad = new org.jfree.chart.demo.servlet.AdjustDecimal();
        AdjustDecimal ad = ConnectInit.getAdjustDecimal();
        if (data.isEmpty())
            return null;

        if (indexType.equals("2")) {

            readOnly = false;
        }

        for (Enumeration e = data.keys(); e.hasMoreElements();) {
            String id = e.nextElement().toString();
            StockDetails rs1 = (StockDetails) data.get(id);
            total += rs1.getMktCapital1(2,indexForm.getS_baseCurrency(),rs1.getCurrencyId());
            rs1.setTotalMktCapital(total);
        }

        Set set = data.entrySet();
        Map.Entry [] entries = (Map.Entry[]) set.toArray(new Map.Entry[set
                .size()]);
        Arrays.sort(entries, new Comparator() {
            public int compare(Object o1, Object o2) {
                //	StockDetails rs1 = (StockDetails)data.get(id);
                StockDetails v11 = (StockDetails) ((Map.Entry) o1).getValue();
                StockDetails v22 = (StockDetails) ((Map.Entry) o2).getValue();
                Object v1 = v11.getStockName();
                Object v2 = v22.getStockName();
                return ((Comparable) v1).compareTo(v2);
            }
        });
        int ix = 0;

        entries1 = new Map.Entry[entries.length];
        int marcapiwf = 0;
        try {
            marcapiwf = Integer.parseInt(indexType);
        } catch (Exception e) {
            marcapiwf = 2;
            // TODO: handle exception
        }
        for (int i = 0; i < entries.length; i++) {
            //	Logging.getDebug("Inside while");
            entries1[i] = entries[i];
            String id = (String) entries[i].getKey();
            /*
             * for(Enumeration e = data.keys();e.hasMoreElements();) { String id =
             * e.nextElement().toString();
             */
            StockDetails rs = (StockDetails) data.get(id);

            buffer.append("<tr>");
            buffer.append("<td width='5%' align='center'>");
            buffer.append("<input type='checkbox' name='stockid1' value="
                    + rs.getStockID() + ">");
            buffer.append("</td>");

            buffer.append("<td width='15%' align='left'>");
            buffer.append("<font face='Arial' size='1' color='blue'>");
            buffer.append(rs.getStockName());
            buffer.append("</font>");
            buffer.append("</td>");

            buffer.append("<td width='7%' align='right'>");
            buffer.append("<font face='Arial' size='1' color='blue'>");
            String iwf = null;
            String mkt_cap = ad.shareholdingpatt(rs.getMktCapital1(marcapiwf,indexForm.getS_baseCurrency(),rs.getCurrencyId()));
            String adjmkt_cap = ad.shareholdingpatt(rs
                    .getAdjustedMarket(marcapiwf));
            adjmkt_cap=mkt_cap;
            Logging.debug("before setting iwf with index type : "
                    + indexType);
            if (request.getParameter("iwf" + rs.getStockID()) == null) {
                Logging.debug("1");
                iwf = String.valueOf(rs.getIwf());
                if (indexType.equals("1")) {
                    Logging.debug("2");
                    iwf = "1.0";
                }
            } else if (indexType.equals("1")) {
                Logging.debug("2");
                iwf = "1.0";
            } else {
                Logging.debug("3");
                iwf = request.getParameter("iwf" + rs.getStockID());
            }
            Logging.debug("after setting iwf : " + iwf);
            Logging.debug(readOnly + "4indexType : " + indexType);
            if (!readOnly) {
                Logging.debug(readOnly + "4indexType : " + indexType);
                buffer.append("<input type= text name ='iwf" + rs.getStockID()
                        + "'");
                buffer
                        .append(" size='11' align='right' onblur='if((isit(indexComposition.iwf"
                                + rs.getStockID()
                                + ".value))==false){indexComposition.iwf"
                                + rs.getStockID()
                                + ".value=\"1.0\" }else{var padjmcap=(indexComposition.adjmcap"+rs.getStockID()+".value); var cal"+rs.getStockID()+ "=indexComposition.iwf"+ rs.getStockID()+".value*"+adjmkt_cap+";"+
                                		" indexComposition.adjmcap"+rs.getStockID()+".value=cal"+rs.getStockID()+ ";alert(indexComposition.totalMCV.value);alert((indexComposition.totalMCV.value - padjmcap));var tmcv"+ "=((indexComposition.totalMCV.value - padjmcap) + ((indexComposition.iwf"+ rs.getStockID()+".value)*"+adjmkt_cap+"));"+
                                		" indexComposition.totalMCV"+".value=tmcv"+ ";} '  value='"
                                + iwf + "' >");
            } else 
                buffer.append(iwf);

            buffer.append("</font>");
            buffer.append("</td>");
            buffer.append("<td width='5%' align='right'>");
            buffer.append("<font face='Arial' size='1' color='blue'>");
            Logging.debug("exclusion date 4indexType : " + indexType);
            buffer.append("<input type='text' readonly='true'  size='14' name='incls_date" + rs.getStockID()+" value='' >");
            buffer.append("<input onclick='c2.popup('incls_date" + rs.getStockID()+"');'  type='button' value='...'/>'");
            buffer.append("</font>");
            buffer.append("</td>");
            
            buffer.append("<td width='5%' align='right'>");
            buffer.append("<font face='Arial' size='1' color='blue'>");
            Logging.debug("exclusion date 4indexType : " + indexType);
            buffer.append("<input type='text' readonly='true'  size='14' name='excls_date" + rs.getStockID()+" value='' >");
            buffer.append("<input onclick='c2.popup('excls_date" + rs.getStockID()+"');'  type='button' value='...'/>'");
            buffer.append("</font>");
            buffer.append("</td>");

            buffer.append("<td width='9%' align='right'>");
            buffer.append("<font face='Arial' size='1' color='blue'>");
            buffer.append(rs.getLtp());
            buffer.append("</font>");
            buffer.append("</td>");
            
            buffer.append("<td width='8%' align='right'>");
            buffer.append("<font face='Arial' size='1' color='blue'>");
            buffer.append(rs.getMarket_lot());
            buffer.append("</font>");
            buffer.append("</td>");


            buffer.append("<td width='8%' align='left'>");
            buffer.append("<font face='Arial' size='1' color='blue'>");
            buffer.append(rs.getCurrency());
            buffer.append("</font>");
            buffer.append("</td>");

            buffer.append("<td width='13%' align='right'>");
            buffer.append("<font face='Arial' size='1' color='blue'>");
            buffer.append(rs.getOutStanding());
            buffer.append("</font>");
            buffer.append("</td>");
            
            
            /*double mcap_calc=((double)Double.parseDouble(adjmkt_cap))*((double)Double.parseDouble(iwf));
            adjmkt_cap=new Double(mcap_calc).toString();
            Logging.getDebug("adjmkt_cap after calculating is "+adjmkt_cap);*/
           
            buffer.append("<td width='11%' align='right'>");
            buffer.append("<font face='Arial' size='1' color='blue'>");
            buffer.append(mkt_cap);
            buffer.append("</font>");
            buffer.append("</td>");
           
            String striwf=request.getParameter("iwf"+ rs.getStockID());
            Logging.debug("striwf is after changing value "+striwf);
            buffer.append("<td width='26%' align='right'>");
            buffer.append("<font face='Arial' size='1' color='blue'>");
            Logging.debug(readOnly + "4indexType : " + indexType);
            buffer.append("<input readOnly name ='adjmcap"+rs.getStockID()+"'");
            buffer.append(" size='30' align='right'  value='"+ adjmkt_cap +"'>");// onfocus=' func("+rs.getStockID()+","+request.getParameter("iwf" + rs.getStockID())+","+adjmkt_cap+");' >");                    
            buffer.append("</font>");
            buffer.append("</td>");

            String weight = null;
            try {
                weight = ad.shareholdingpatt(rs.getWightage());
            } catch (Exception e) {
                weight = new Double(rs.getWightage()).toString();
                // TODO: handle exception
            }
            buffer.append("<td width='10%' align='right'>");
            buffer.append("<font face='Arial' size='1' color='blue'>");
            buffer.append(weight);
            buffer.append("</font>");
            buffer.append("</td>");
            buffer.append("</tr>");
        }

        return buffer;
    }
    public ActionErrors validateIWF(Hashtable destinationTable,
            HttpServletRequest request, ActionErrors actionErrors) {
        if (!destinationTable.isEmpty()) {
            double individual_iwf = 0.0;
            Set set = destinationTable.entrySet();
            Map.Entry [] entries = (Map.Entry[]) set.toArray(new Map.Entry[set
                    .size()]);
            Arrays.sort(entries, new Comparator() {
                public int compare(Object o1, Object o2) {
                    StockDetails v11 = (StockDetails) ((Map.Entry) o1)
                            .getValue();
                    StockDetails v22 = (StockDetails) ((Map.Entry) o2)
                            .getValue();
                    Object v1 = v11.getStockName();
                    Object v2 = v22.getStockName();
                    return ((Comparable) v1).compareTo(v2);
                }
            });
            int ix = 0;
            for (int i = 0; i < entries.length; i++) {

                String idiwf = (String) entries[i].getKey();

                StockDetails rs = (StockDetails) destinationTable.get(idiwf);
                try {
                    //take care of request
                    individual_iwf = new Double(request.getParameter("iwf"
                            + rs.getStockID())).doubleValue();
                    if (individual_iwf < 0 || individual_iwf > 1) {
                        actionErrors.add(null,new ActionError("indexcompose.iwf.freefloat"));
                        return actionErrors;                       
                    }
                } catch (Exception ex) {
                    actionErrors.add(null,new ActionError("indexcompose.iwf.freefloat"));
                    return actionErrors; 
                }
                Logging.debug("individual_iwf :" + individual_iwf);

            }            
        }
        return actionErrors;
    }
    
    public StringBuffer fixedIncomeDesignBaseValuesTable(NewIndexForm form,HttpServletRequest request,ActionErrors actionErrors) {
        StringBuffer stringBuffer=new StringBuffer();
        Connect c=ConnectInit.getConnect();
        try{
         String query =ConnectInit.queries.getProperty("auto_generate_child_index");
         PreparedStatement preparedStatement=form.getDatabaseConnection().prepareStatement(query);
         Logging.debug("preparedStatement  "+preparedStatement);
         ResultSet  rst=preparedStatement.executeQuery();
        String childindexname="";
        int i=1;
        // String[] ee=request.getParameterValues("indexname");
        String iname=form.getS_indexName();
        String basevalue=form.getD_baseValue();
        while(rst.next()){ 
            childindexname=iname+"."+rst.getString("level_name")+"."+rst.getString("class_name");                 
        //    Logging.getDebug("While putting value is "+rst.getString("industry_classification_code"));
             stringBuffer.append("<tr><td width=\"5%\" align=\"right\" bgcolor=\"#FFFFFF\">&nbsp;") ;                 
             stringBuffer.append(i+"</td><td width=\"23%\" align=\"left\" bgcolor=\"#FFFFFF\"> &nbsp;&nbsp;"+childindexname );                  
              stringBuffer.append("</td>");
              stringBuffer.append("<td width=\"5%\" align=\"center\" bgcolor=\"#FFFFFF\"><input type=\"text\" name=\""+iname+rst.getString("industry_classification_code")+"\"  size=\"10\" align='left'");
             stringBuffer.append(" onblur='if((isit(baseValues."+iname+rst.getString("industry_classification_code")+".value))==false){baseValues."+iname+rst.getString("industry_classification_code")+ ".value="+basevalue+"} '");
            // Logging.getDebug(request.getParameter(iname+rst.getString("industry_classification_code")));
             if(request.getParameter(iname+rst.getString("industry_classification_code"))!=null  && !request.getParameter(iname+rst.getString("industry_classification_code")).trim().equals("")){
                  // Logging.getDebug("In if"); 
                   stringBuffer.append(" value="+request.getParameter(iname+rst.getString("industry_classification_code"))+">"); 
                   try{
                       double value=new Double(request.getParameter(iname+rst.getString("industry_classification_code"))).doubleValue();
                      // Logging.getDebug("While putting value is "+rst.getString("industry_classification_code"));
                       if(value<=0){
                           stringBuffer.append("<font color=\"#FF0000\">*</font>");
                           if(actionErrors.isEmpty())
                           actionErrors.add(null,new ActionError("indexcompose.invalidBaseValue"));
                       }else{
                           stringBuffer.append("&nbsp;");
                       }
                   }catch (Exception e) {
                     //  Logging.getDebug("In catch");
                       stringBuffer.append("<font color=\"#FF0000\">*</font>");
                       if(actionErrors.isEmpty())
                           actionErrors.add(null,new ActionError("indexcompose.invalidBaseValue1"));
                    
                    // TODO: handle exception
                }
                   stringBuffer.append("</td>");  
               }else{
                   stringBuffer.append(" value="+basevalue+"></td>"); 
               }
               stringBuffer.append("</tr>");
               i++ ;                 
         } 
        }catch (Exception e) {
          //  e.printStackTrace();
        	Logging.debug(e);
            // TODO: handle exception
        }
        return stringBuffer;
        
    }

}

