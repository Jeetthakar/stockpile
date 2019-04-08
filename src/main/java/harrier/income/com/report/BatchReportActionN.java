

package harrier.income.com.report;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import app.Connect;

import com.harrier.initializeation.ConnectInit;

/**
 * @author naresh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BatchReportActionN extends Action{
	Logger Logging = Logger.getLogger(BatchReportActionN.class);
	public ActionForward execute( ActionMapping mapping, ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response )
	throws IOException, ServletException {
		
		ActionForward fr;
		BatchReportFormN brf=(BatchReportFormN)form;
		String radval=brf.getRadioButton();
		String cradio=brf.getCheckradio();
		boolean flag1=false;
		boolean flag2=false;
		boolean flag3=false;
//		app.Connect con = new app.Connect();
		Connect con = ConnectInit.getConnect();
		String selectUser = brf.getSelectUser();
		String exeButton=brf.getExecuteButton();
		
		String bacButton=brf.getBackButton();
		String resButton=brf.getResetButton();
		
		if(exeButton !=null && exeButton.equals("Execute")){
				
					
						//Index Composition
						if(brf.is1())
						{
							brf.getTableData2();
						}
						//s.equals("Index Wise PE,PB"))
						if(brf.is3())
						{
							brf.getCurrencyParam();
				        }
						//s.equals("Index Divisor"))
						if(brf.is2())
						{
							try{
							ArrayList l1=brf.getTabledata3();
							}
							catch(Exception e){
								Logging.debug("Exception naresh");
						
							}
				        }
						//	s.equals("Index In Different Currency"))
						if(brf.is4())
						{
						brf.getIndex_arraylist();	
						}
						
			 
					//Stock Details
						if(brf.is5())
						{
							brf.getTableDatas();
						}
						//Capital Change
						if(brf.is6())
						{
							brf.getTableDatac();
				        }
						
				
					//s.equals("Company Weightage"))
							if(brf.is7())
							{
								brf.getCompanyWeightage();
							}
							//s.equals("Industry Weightage"))
							if(brf.is8())
							{
								brf.getIndweighttable();
					        }
							//s.equals("Stock Contribution To Change In Index"))
							if(brf.is9())
							{
								brf.getStockcotriIndexchange();
					        }
						if(brf.is100())
						{
							//index compare
							String fromdate="";
				 			String toDate="";
				 			String[] var1={"",""};
				 			String[] var2={"",""};
				 			String ids=null;
				 			String str2=null;
				 			String str1=null;
				 			Connection connection = null;
				 			PreparedStatement pst = null;
				 			ResultSet rs = null;
				 			try{
				 				//brf.getComParam();
						 			HttpSession sess=request.getSession();
						 			PrintWriter pw=response.getWriter();
						 			var1=brf.get71();
						 			fromdate=brf.get110();
						 			toDate=brf.get120();
								if((var1==null)&& selectUser.equals("6"))
						 			{
						 				try {
						 					if (connection == null)
						 						connection = con.getdbConnection();
						 					try {
						 						/*String query = con.queries.getProperty("Select_from_prefrenctial_detail");
						 						pst = connection.prepareStatement(query);
						 						pst.setString(1, selectUser);
						 						pst.setString(2, "Index Comparison");
						 						rs = pst.executeQuery();
						 						while (rs.next()) {
						 							ids = rs.getString(1);
						 						}
						 					*/
						 						str1=ids.substring(0,4);
						 						str2=ids.substring(5,ids.length());
						 						var2[0]=str1;
						 						var2[1]=str2;
						 						var1=var2;
						 						sess.setAttribute("indexids",var1);
									 			sess.removeAttribute("sfdate");
									 			sess.setAttribute("sfdate",fromdate);
									 			sess.removeAttribute("stdate");
									 			sess.setAttribute("stdate",toDate);
						 					} catch (Exception e) {
						 						// TODO: handle exception
						 						Logging.error(" Error : " + e.getMessage());
						 					}
						 				} catch (Exception e) {
						 					Logging.error(" Error : " + e.getMessage());
						 				} finally {
						 					try {
						 						if (connection != null)
						 							connection.close();
						 					} catch (Exception ee) {
						 						try {
						 							if (connection != null)
						 								connection.close();
						 						} catch (Exception ex) {
						 							Logging.error(" Error : Unable to close Connection "
						 									+ ex.getMessage());
						 						}
						 						Logging.error(" Error : Unable to close Connection "
						 								+ ee.getMessage());
						 					}
						 				}
						 			}else{
						 			sess.setAttribute("indexids",var1);
						 			sess.removeAttribute("sfdate");
						 			sess.setAttribute("sfdate",fromdate);
						 			sess.removeAttribute("stdate");
						 			sess.setAttribute("stdate",toDate);
						 			}
				 			}catch(Exception e){
				 				
				 			}
						}
		
						//Index Compare OHLC
						if(brf.is101())
						{
							brf.getOhlcParam();
				        }
						//Index Returns and Volatility
						if(brf.is103())
						{
							brf.getFinal_Vector();
				        }
						//Index Corelation
						if(brf.is102())
						{
							brf.getCorelParam();
				        }
							
						//Traded Volume
						if(brf.is104())
						{
							brf.getTableDataTr();
						}
						//Stock Dividend
						if(brf.is105())
						{
							brf.getTableDataSD();
				        }
						//Index Movement
						if(brf.is106())
						{
							brf.getIndexMovingTable();
							HttpSession sess=request.getSession();
							
							Vector collected_Table_Vector =brf.getVar_Table_data_vector();
							sess.removeAttribute("var_Table_Vector");
							sess.setAttribute("var_Table_Vector",collected_Table_Vector);
							
							String varSelectIndex =brf.get136();
							sess.removeAttribute("varIndexId");
							sess.setAttribute("varIndexId",varSelectIndex);
							
						
							sess.removeAttribute("chartType");
							sess.setAttribute("chartType","maverage");
							
							/**
							 * Setting variable name "filename"for excel file
							 */
							sess.removeAttribute("filename");
							sess.setAttribute("filename","IndexMovement.xls");
							/**
							 * Setting variable name "to" for Starting date
							 */
							String varSelectToDate =brf.get126();
							
							sess.removeAttribute("to");
							sess.setAttribute("to",varSelectToDate);
							/**
							 * Setting variable name "from" for ending date
							 */
							String varSelectFromDate =brf.get116();
							//Logging.debug("Datefrom "+varSelectFromDate);
							sess.removeAttribute("from");
							sess.setAttribute("from",varSelectFromDate);
							
							//String varCheckMAvg =brf.getCheck_moving_avg();
							//Logging.debug(varCheckMAvg);
							sess.removeAttribute("varCheckAvg");
							//sess.setAttribute("varCheckAvg",varCheckMAvg);
							sess.setAttribute("varCheckAvg","checked");
							
							//String varSpan =brf.getSelectSpan();
							//Logging.debug(varSpan);
							//sess.removeAttribute("varSpan");
							//sess.setAttribute("varSpan",varSpan);
							sess.setAttribute("varSpan","15");
							//String varChart =brf.getSelectChart();
							//Logging.debug(varChart);
							sess.removeAttribute("varChart");
							//sess.setAttribute("varChart",varChart);
							sess.setAttribute("varChart","1");
							
							Vector v2=brf.getVExcel106();
							sess.setAttribute("ci2",v2);
							
							
				        }
				brf.setExecuteButton(null);
				return fr= new ActionForward("/pages/reports/PreIndexSelectReportN.jsp?FromLogin=no");
			
		}
		
	    if(resButton !=null && resButton.equals("Reset")){
			//brf.setSelectUser("0");
			brf.set1(false);
			brf.set2(false);
			brf.set3(false);
			brf.set4(false);
			brf.set5(false);
			brf.set6(false);
			brf.set7(false);
			brf.set8(false);
			brf.set9(false);
			brf.set100(false);
			brf.set101(false);
			brf.set102(false);
			brf.set103(false);
			brf.set104(false);
			brf.set105(false);
			brf.set106(false);
			
		    brf.setResetButton(null);
			return fr= new ActionForward("/pages/reports/BatchReportsN.jsp");
			
		}
	if(bacButton !=null && bacButton.equals("Back")){
		//brf.setSelectUser("0");
		brf.set1(false);
		brf.set2(false);
		brf.set3(false);
		brf.set4(false);
		brf.set5(false);
		brf.set6(false);
		brf.set7(false);
		brf.set8(false);
		brf.set9(false);
		brf.set100(false);
		brf.set101(false);
		brf.set102(false);
		brf.set103(false);
		brf.set104(false);
		brf.set105(false);
		brf.set106(false);
		
	    brf.setBackButton(null);
		return fr= new ActionForward("/pages/reports/BatchReportsN.jsp");
		
	} else{
		
		 	brf.setExecuteButton(null);
			return fr= new ActionForward("/pages/reports/BatchReportsN.jsp");
	}
			
			
	}
}






