/*
 * Created on Sep 7, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.harrier.initializeation.ConnectInit;


/**
 * @author ashishi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PatAction extends Action {
	Logger Logging = Logger.getLogger(PatAction.class);
	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		double pat1=0.00;
		double pat2=0.00;
		double pat3=0.00;
		double pat4=0.00;
		double pat5=0.00;
		double pat6=0.00;
		double pat7=0.00;
		double pat8=0.00;
		double num1=0.00;
		double num2=0.00;
		double num3=0.00;
		double num4=0.00;
		double num5=0.00;
		double num6=0.00;
		double num7=0.00;
		double num8=0.00;
		double totNum=0.00;
		double totPat=0.00;
		double totPat1=0.00;
		double mcv=0.00;
		double pe=0.00;
		double adj_net_worth=0.0;
		double nworth=0.0;
		double pb=0.0;
		String xDate1=null;
		
		String date1=null;
		Connect con = ConnectInit.getConnect();
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		int stock_id = 0;
		int exchange_id = 0;
		PatForm myForm = (PatForm) form;
		String stockID = myForm.getSelectStock();
		stock_id = Integer.parseInt(stockID);
		String exchangeid = myForm.getSelectExchange();
		exchange_id = Integer.parseInt(exchangeid);
		HttpSession sess=request.getSession();
		String hidenvari=myForm.getHiddenVar1();
		String resetval=myForm.getResetval();
		String viewButton=myForm.getCompute();
		String vanish=myForm.getVanish();
		String reset=myForm.getResetButton();
		String mvcdate=myForm.getMvcdate();
		String indicator = myForm.getSelectfinance();
		if(vanish !=null && vanish.equals("yes")){
			myForm.getSelectStock();
			myForm.setCheck("no");
			return new ActionForward("/pages/Pat.jsp");
		}
		if(viewButton !=null && viewButton.equals("yes")){
			myForm.getTableData();
			myForm.setCheck("no");
		}
		if(resetval !=null && resetval.equals("yes")){
			myForm.reset1();
		}
		
		if(hidenvari !=null && hidenvari.equals("yes") && indicator.equals("1")){
						//Index Composition
						
						if(myForm.is1())
						{
							pat1=Double.parseDouble(sess.getAttribute("pat1").toString());
							num1=Double.parseDouble(sess.getAttribute("num1").toString());
							xDate1=sess.getAttribute("xDate1").toString();
						}
						
						if(myForm.is2())
						{
							pat2=Double.parseDouble(sess.getAttribute("pat2").toString());
							num2=Double.parseDouble(sess.getAttribute("num2").toString());
							if(xDate1==null)
							xDate1=sess.getAttribute("xDate2").toString();
				        }
						
						if(myForm.is3())
						{
							pat3=Double.parseDouble(sess.getAttribute("pat3").toString());
							num3=Double.parseDouble(sess.getAttribute("num3").toString());
							if(xDate1==null)
							xDate1=sess.getAttribute("xDate3").toString();
						}
						if(myForm.is4())
						{
							pat4=Double.parseDouble(sess.getAttribute("pat4").toString());
							num4=Double.parseDouble(sess.getAttribute("num4").toString());
							if(xDate1==null)
							xDate1=sess.getAttribute("xDate4").toString();
						}
						if(myForm.is5())
						{
							pat5=Double.parseDouble(sess.getAttribute("pat5").toString());
							num5=Double.parseDouble(sess.getAttribute("num5").toString());
							if(xDate1==null)
							xDate1=sess.getAttribute("xDate5").toString();
						}
						if(myForm.is6())
						{
							pat6=Double.parseDouble(sess.getAttribute("pat6").toString());
							num6=Double.parseDouble(sess.getAttribute("num6").toString());
							if(xDate1==null)
							xDate1=sess.getAttribute("xDate6").toString();
						}
						if(myForm.is7())
						{
							pat7=Double.parseDouble(sess.getAttribute("pat7").toString());
							num7=Double.parseDouble(sess.getAttribute("num7").toString());
							if(xDate1==null)
							xDate1=sess.getAttribute("xDate7").toString();
						}
						if(myForm.is8())
						{
							pat8=Double.parseDouble(sess.getAttribute("pat8").toString());
							num8=Double.parseDouble(sess.getAttribute("num8").toString());
							if(xDate1==null)
							xDate1=sess.getAttribute("xDate8").toString();
						}
						totNum=num1+num2+num3+num4+num5+num6+num7+num8;
						totPat=pat1+pat2+pat3+pat4+pat5+pat6+pat7+pat8;
						try {
		        			if (connection == null)
		        				connection = con.getdbConnection();
		        			String getmcv = ConnectInit.queries
		        			.getProperty("select_mcv_from_stock_master");
		        			pst = connection.prepareStatement(getmcv);
		        			pst.setInt(1, stock_id);
		        			pst.setString(2, mvcdate);
		        			result = pst.executeQuery();
		        			while (result.next()) {
		        				mcv = Double.parseDouble(result.getString(1));
		        			}
		        			if (result != null)
		        				result.close();
		        			if (pst != null)
		        				pst.close();
		        		}
		        		catch (Exception ex) {
		        			Logging.error("Error : " + ex.getMessage());
		        		} finally {
		        			try {
		        				if (result != null)
		        					result.close();
		        				if (pst != null)
		        					pst.close();
		        			} catch (SQLException ex) {
		        				if (connection != null) {
		        					connection.rollback();
		        					connection.close();
		        				}
		        				Logging.error("Error :  " + ex.getMessage());
		        			}
		        		}
		        		myForm.setMcv(""+mcv);
		        		myForm.setTotNum(""+totNum);
		        		myForm.setXDate1(""+xDate1);
		        		if(totNum==12){
						totPat1=totPat;	
						totPat=totPat*10000000;
						pe=mcv/totPat;
						}
		                if(totNum==6){
		                	totPat=((totPat*12)/6);
		                totPat1=totPat;
		                totPat=totPat*10000000;
		                pe=mcv/totPat;
		                }
		                if(totNum==9){
		                	totPat=((totPat*12)/9);
		                totPat1=totPat;
		                totPat=totPat*10000000;
		                pe=mcv/totPat;
		                }
		                if(totNum==15){
		                	totPat=((totPat*12)/15);
		                totPat1=totPat;
		                totPat=totPat*10000000;
		                pe=mcv/totPat;
		                }
		                if(totNum==18){
		                	totPat=((totPat*12)/18);
		                totPat1=totPat;
		                totPat=totPat*10000000;
		                pe=mcv/totPat;
		                }
		                if(totNum==21){
		                	totPat=((totPat*12)/21);
		                totPat1=totPat;
		                totPat=totPat*10000000;
		                pe=mcv/totPat;
		                }
		                if(totNum==3){
		                	totPat=((totPat*12)/3);
		                totPat1=totPat;
		                totPat=totPat*10000000;
		                pe=mcv/totPat;
		                }
		                if(totNum==24){
		                	totPat=((totPat*12)/24);
		                totPat1=totPat;
		                totPat=totPat*10000000;
		                pe=mcv/totPat;
		                }
		                myForm.setTotPat1(""+totPat1);
		                myForm.setPe(""+pe);
		                myForm.set1(false);
		                myForm.set2(false);
		                myForm.set3(false);
		                myForm.set4(false);
		                myForm.set5(false);
		                myForm.set6(false);
		                myForm.set7(false);
		                myForm.set8(false); 
		        		String stock=null;
		        		
		        		try {
		        			if (connection == null)
		        				connection = con.getdbConnection();
		        			String getcompanyname = ConnectInit.queries
		        			.getProperty("select_stock_name_from_stock_master");
		        			pst = connection.prepareStatement(getcompanyname);
		        			pst.setInt(1, stock_id);
		        			result = pst.executeQuery();
		        			while (result.next()) {
		        				stock = result.getString(1);
		        			}
		        			if (result != null)
		        				result.close();
		        			if (pst != null)
		        				pst.close();
		        		}
		        		catch (Exception ex) {
		        			Logging.error("Error : " + ex.getMessage());
		        		} finally {
		        			try {
		        				if (result != null)
		        					result.close();
		        				if (pst != null)
		        					pst.close();
		        			} catch (SQLException ex) {
		        				if (connection != null) {
		        					connection.rollback();
		        					connection.close();
		        				}
		        				Logging.error("Error :  " + ex.getMessage());
		        			}
		        		}
		                StringBuffer buffer = new StringBuffer();
		                buffer.append("<table align='center' border='1'>");
		                buffer.append("<tr>");
		                buffer.append("<td>");
		                buffer.append("<font size='3' face='Arial' color='Blue'>Financial Details for</font>");
		                buffer.append("</td>");
		                buffer.append("<td>");
		    			buffer.append("<font size='3' face='Arial' color='Blue'>"+stock+"</font>");
		    			buffer.append("</td>");	
		                buffer.append("</tr>");
		                buffer.append("<tr>");
		                buffer.append("<td>");
		    			buffer.append("<font size='3' face='Arial' color='Blue'>Total Number of months</font>");
		    			buffer.append("</td>");
		    			buffer.append("<td>");
		    			buffer.append("<font size='3' face='Arial' color='Blue'>"+totNum+"</font>");
		    			buffer.append("</td>");
		                buffer.append("</tr>");
		                buffer.append("<tr>");
		                buffer.append("<td>");
		    			buffer.append("<font size='3' face='Arial' color='Blue'>Latest xDate is</font>");
		    			buffer.append("</td>");
		    			buffer.append("<td>");
		    			buffer.append("<font size='3' face='Arial' color='Blue'>"+xDate1+"</font>");
		    			buffer.append("</td>");
		                buffer.append("</tr>");
		                buffer.append("<tr>");
		                buffer.append("<td>");
		    			buffer.append("<font size='3' face='Arial' color='Blue'>Market Cap value is</font>");
		    			buffer.append("</td>");
		    			buffer.append("<td>");
		    			buffer.append("<font size='3' face='Arial' color='Blue'>"+mcv+"</font>");
		    			buffer.append("</td>");
		                buffer.append("</tr>");
		                buffer.append("<tr>");
		                buffer.append("<td>");
		    			buffer.append("<font size='3' face='Arial' color='Blue'>Total PAT is</font>");
		    			buffer.append("</td>");
		    			buffer.append("<td>");
		    			buffer.append("<font size='3' face='Arial' color='Blue'>"+totPat1+"</font>");
		    			buffer.append("</td>");
		    			buffer.append("</tr>");
		    			buffer.append("<tr>");
		                buffer.append("<td>");
		    			buffer.append("<font size='3' face='Arial' color='Blue'>The Calculated P/E is</font>");
		    			buffer.append("</td>");
		    			buffer.append("<td>");
		    			buffer.append("<font size='3' face='Arial' color='Blue'>"+pe+"</font>");
		    			buffer.append("</td>");
		    			buffer.append("</tr>");
		    			 buffer.append("</table>");
		    			PatForm.setDisp_buffer(buffer);
		    			myForm.setCheck("yes");
		}
		
		if(hidenvari !=null && hidenvari.equals("yes") && indicator.equals("2")){
			try {
    			if (connection == null)
    				connection = con.getdbConnection();
    			String getmcv = ConnectInit.queries
    			.getProperty("select_mcv_from_stock_master");
    			pst = connection.prepareStatement(getmcv);
    			pst.setInt(1, stock_id);
    			pst.setString(2, mvcdate);
    			result = pst.executeQuery();
    			while (result.next()) {
    				mcv = Double.parseDouble(result.getString(1));
    			}
    			myForm.setMcv(""+mcv);
    			if (result != null)
    				result.close();
    			if (pst != null)
    				pst.close();
    			String pbvalue = ConnectInit.queries
    			.getProperty("pb_value_get_calculated");
    			pst = connection.prepareStatement(pbvalue);
    			pst.setInt(1, stock_id);
    			pst.setInt(2,exchange_id);
    			result=pst.executeQuery();				
				while(result.next()){
					date1= result.getString(2); 
					adj_net_worth=result.getDouble(5);
					break;
				}
				nworth=(adj_net_worth*10000000);
				if(nworth>0 && mcv!=0){			//if(nworth!=0 && mcv!=0){									
					pb=mcv/nworth;
					myForm.setDate1(""+date1);
					myForm.setPb(""+pb);
				}
			}
    		catch (Exception ex) {
    			Logging.error("Error : " + ex.getMessage());
    		} finally {
    			try {
    				if (result != null)
    					result.close();
    				if (pst != null)
    					pst.close();
    			} catch (SQLException ex) {
    				if (connection != null) {
    					connection.rollback();
    					connection.close();
    				}
    				Logging.error("Error :  " + ex.getMessage());
    			}
    		}
    		myForm.setCheck("yes");
		}
		if(reset !=null && reset.equals("Reset"))
		{
			myForm.set1(false);
            myForm.set2(false);
            myForm.set3(false);
            myForm.set4(false);
            myForm.set5(false);
            myForm.set6(false);
            myForm.set7(false);
            myForm.set8(false);
		}
	
		return mapping.findForward("success");

	}

}