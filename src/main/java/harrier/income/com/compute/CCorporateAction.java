/*
 * Created on Sep 17, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.compute;

import harrier.income.com.entities.CFormula;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import app.Connect;

import com.harrier.initializeation.ConnectInit;


/**
 * @author kena
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class CCorporateAction {

	private PreparedStatement pst_preStat;

	private ResultSet rst;

	private static app.Connect con_connect;

//	private Properties p_queries, prop;
	 
	Logger log_logger;

//	private CFormula cFor = new CFormula();

	private double nDivisor;

	private double ltpOld, ltpNew, tmcvOld, tmcvNew, oldDivisor;

	private long tisOld, tisNew;

	private double mcvOld, mcvNew, iwf, diffTmcv;

	public CCorporateAction() {
		Connection con=null;
		con_connect = ConnectInit.getConnect();
		//con_connect.getConnection();
		
//		Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
		try{
			if(con==null)
			{
				con_connect.getdbConnection();
			}
			
		/*	p_queries = new Properties();
			log_logger = Logger.getLogger(CIndexCalculator.class.getName());
			PropertyConfigurator.configure("resources/l4j3.properties");
			try {
				p_queries.load(new FileInputStream("resources/query.properties"));
				log_logger.debug("File Loaded");
			} catch (IOException e) {
				log_logger.debug("unable to get file");
				log_logger.error("error in file");
				//System.exit(0);
			}	*/
		} catch (Exception e) {
			log_logger.debug("Error in connection");
			//Logging.getDebug("Error :" + e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ee) {
				log_logger.debug("Error in Close connection");
				//Logging.getError(" Error : Unable to close Connection "+ ee.getMessage());
			}
		}
	}
	//Returns ResultSet containing data for which corporate action is to be applied
	private ResultSet CorporateParameters(String date, long l_indexID,
			long l_stockID) {
		try {
			
			pst_preStat = Connect.con.prepareStatement(ConnectInit.queries
					.getProperty("corporate_action_parameters"));
			pst_preStat.setString(1, date);
			pst_preStat.setLong(2, l_indexID);
			pst_preStat.setLong(3, l_stockID);
			log_logger.debug("till query");
			rst = pst_preStat.executeQuery();//executes query to get data for index computation
			log_logger.debug("Executed");
		} catch (SQLException e) {
			log_logger.debug(e);
		}
		try {
			rst.next();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			log_logger.debug(e1.getMessage());
		}
		return rst;
	}

	//STOCK DIVIDEND
	public double dsor_stkDividend(long l_indexID, long l_stockID,
			long aforVal, long bhVal, String date) {
		try {
			CFormula cFor = ConnectInit.getCFormula();
			rst = CorporateParameters(date, l_indexID, l_stockID);
			ltpOld = rst.getDouble("ltp");
			log_logger.debug(ltpOld);
			tisOld = rst.getLong("tis");
			log_logger.debug(tisOld);
			tmcvOld = rst.getDouble("tmcv");
			log_logger.debug(tmcvOld);
			oldDivisor = rst.getDouble("divisor");
			log_logger.debug(oldDivisor);
			mcvOld = rst.getDouble("mcv");
			log_logger.debug(mcvOld);
			iwf = rst.getDouble("iwf");
			log_logger.debug(iwf);
			ltpNew = cFor.newLTPStkDivi(ltpOld, aforVal, bhVal);
			tisNew = cFor.newTISStkDivi(tisOld, aforVal, bhVal);
			//				insert exchange code,marketlot
			mcvNew = cFor.calMarketCap(ltpNew, 1, 1, tisNew, iwf);
			tmcvNew = tmcvOld - mcvOld + mcvNew;
			diffTmcv = tmcvNew - tmcvOld;
			nDivisor = cFor.newDivisorCorp(tmcvOld, diffTmcv, oldDivisor);
			log_logger.debug("ltp new =" + ltpNew);
			log_logger.debug("tis new =" + tisNew);
			log_logger.debug("mcv new =" + mcvNew);
			log_logger.debug("tmcv new =" + tmcvNew);
			log_logger.debug("diffTmcv =" + diffTmcv);
			log_logger.debug("divisor =" + nDivisor);

		} catch (SQLException e) {
			log_logger.debug(e);
		}
		return nDivisor;
	}

	//RIGHTS OFFERING
	public double dsor_RightsOff(long l_indexID, long l_stockID, long aforVal,
			long bhVal, String date, double price) {
		try {
			CFormula cFor = ConnectInit.getCFormula();
			rst = CorporateParameters(date, l_indexID, l_stockID);
			// index computation
			log_logger.debug("Executed");
			ltpOld = rst.getDouble("ltp");
			tisOld = rst.getLong("tis");
			tmcvOld = rst.getDouble("tmcv");
			oldDivisor = rst.getDouble("divisor");
			mcvOld = rst.getDouble("mcv");
			iwf = rst.getDouble("iwf");
			ltpNew = cFor.newLTPRightsOff(ltpOld, aforVal, bhVal, price);
			tisNew = cFor.newTISRightsOff(tisOld, aforVal, bhVal);
			//				insert exchange code,marketlot
			mcvNew = cFor.calMarketCap(ltpNew, 1, 1, tisNew, iwf);
			tmcvNew = tmcvOld - mcvOld + mcvNew;
			diffTmcv = tmcvNew - tmcvOld;
			cFor.newDivisorCorp(tmcvOld,diffTmcv,oldDivisor);
			log_logger.debug("ltp new =" + ltpNew);
			log_logger.debug("tis new =" + tisNew);
			log_logger.debug("mcv new =" + mcvNew);
			log_logger.debug("tmcv new =" + tmcvNew);
			log_logger.debug("diffTmcv =" + diffTmcv);

		} catch (SQLException e) {
			log_logger.debug(e);
		}
		return nDivisor;
	}

	//CSPLIT REVERSE
	public double dsor_SplitReverse(long l_indexID, long l_stockID,
			long aforVal, long bhVal, String date) {
		try {
			CFormula cFor = ConnectInit.getCFormula();
			rst = CorporateParameters(date, l_indexID, l_stockID);
			log_logger.debug("Executed");
			ltpOld = rst.getDouble("ltp");
			tisOld = rst.getLong("tis");
			tmcvOld = rst.getDouble("tmcv");
			oldDivisor = rst.getDouble("divisor");
			mcvOld = rst.getDouble("mcv");
			iwf = rst.getDouble("iwf");
			ltpNew = cFor.newLTPSplitRev(ltpOld, aforVal, bhVal);
			tisNew = cFor.newTISSplitRev(tisOld, aforVal, bhVal);
			//				insert exchange code,marketlot
			mcvNew = cFor.calMarketCap(ltpNew, 1, 1, tisNew, iwf);
			tmcvNew = tmcvOld - mcvOld + mcvNew;
			diffTmcv = tmcvNew - tmcvOld;
			cFor.newDivisorCorp(tmcvOld,diffTmcv,oldDivisor);
			log_logger.debug("ltp new =" + ltpNew);
			log_logger.debug("tis new =" + tisNew);
			log_logger.debug("mcv new =" + mcvNew);
			log_logger.debug("tmcv new =" + tmcvNew);
			log_logger.debug("diffTmcv =" + diffTmcv);

		} catch (SQLException e) {
			log_logger.debug(e);
		}
		return nDivisor;
	}

	public double dsor_Warrant(long l_indexID, long l_stockID, long aforVal,
			long bhVal, String date) {
		long noWar = 0;
		try {
			CFormula cFor = ConnectInit.getCFormula();
			rst = CorporateParameters(date, l_indexID, l_stockID);
			log_logger.debug("Executed");
			ltpOld = rst.getDouble("ltp");
			tisOld = rst.getLong("tis");
			tmcvOld = rst.getDouble("tmcv");
			oldDivisor = rst.getDouble("divisor");
			mcvOld = rst.getDouble("mcv");
			iwf = rst.getDouble("iwf");
			rst.next();
			//noWar = cFor.noOSharesFWar(aforVal, bhVal);
			tisNew = cFor.newTISWarrantCon(tisOld, noWar);
			//			insert exchange code,marketlot			
			mcvNew = cFor.calMarketCap(ltpOld, 1, 1, tisNew, iwf);
			tmcvNew = tmcvOld - mcvOld + mcvNew;
			diffTmcv = tmcvNew - tmcvOld;
			cFor.newDivisorCorp(tmcvOld,diffTmcv,oldDivisor);
			log_logger.debug("ltp new =" + ltpNew);
			log_logger.debug("tis new =" + tisNew);
			log_logger.debug("mcv new =" + mcvNew);
			log_logger.debug("tmcv new =" + tmcvNew);
			log_logger.debug("diffTmcv =" + diffTmcv);
		} catch (SQLException e) {
			log_logger.debug(e);
		}
		return nDivisor;
	}

	//	CASH DIVIDEND
	public String dsor_CashDividend(String l_indexID, String l_stockID,
			String date, String price) {
		long indexID = Long.parseLong(l_indexID);
		long stockID = Long.parseLong(l_stockID);
		double d_price=Double.parseDouble(price);
		try {
			CFormula cFor = ConnectInit.getCFormula();
			rst = CorporateParameters(date, indexID, stockID);
			log_logger.debug("Executed");
			ltpOld = rst.getDouble("ltp");
			tisOld = rst.getLong("tis");
			tmcvOld = rst.getDouble("tmcv");
			oldDivisor = rst.getDouble("divisor");
			mcvOld = rst.getDouble("mcv");
			iwf = rst.getDouble("iwf");
			ltpNew = cFor.newLTPCashDivi(ltpOld, d_price);
			//insert exchange code,marketlot
			mcvNew = cFor.calMarketCap(ltpNew, 1, 1, tisOld, iwf);
			tmcvNew = tmcvOld - mcvOld + mcvNew;
			diffTmcv = tmcvNew - tmcvOld;
			cFor.newDivisorCorp(tmcvOld,diffTmcv,oldDivisor);
			log_logger.debug("ltp new =" + ltpNew);
			log_logger.debug("mcv new =" + mcvNew);
			log_logger.debug("tmcv new =" + tmcvNew);
			log_logger.debug("diffTmcv =" + diffTmcv);
		} catch (SQLException e) {
			log_logger.debug(e);
		}
		String divisor =Double.toString(nDivisor);
		return divisor;
	}

	//REPURCHASE
	public double dsor_Repurchase(long l_indexID, long l_stockID, String date,
			long l_tenShares) {
		rst = CorporateParameters(date, l_indexID, l_stockID);
		try {
			CFormula cFor = ConnectInit.getCFormula();
			ltpOld = rst.getDouble("ltp");
			tisOld = rst.getLong("tis");
			tmcvOld = rst.getDouble("tmcv");
			oldDivisor = rst.getDouble("divisor");
			mcvOld = rst.getDouble("mcv");
			iwf = rst.getDouble("iwf");
			tisNew = cFor.newTISRepurchase(tisOld, l_tenShares);
			mcvNew = cFor.calMarketCap(ltpNew, 1, 1, tisOld, iwf);
			tmcvNew = tmcvOld - mcvOld + mcvNew;
			diffTmcv = tmcvNew - tmcvOld;
			cFor.newDivisorCorp(tmcvOld,diffTmcv,oldDivisor);
			log_logger.debug("ltp new =" + ltpNew);
			log_logger.debug("mcv new =" + mcvNew);
			log_logger.debug("tmcv new =" + tmcvNew);
			log_logger.debug("diffTmcv =" + diffTmcv);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			 log_logger.debug(e);
		}
		return nDivisor;
	}
/*	public static void main(String args[]){
		double divisor=0;
		CCorporateAction ac=new CCorporateAction();
		divisor=ac.dsor_stkDividend(1,5,1,2,"16-09-2004");
		log_logger.debug(divisor);
	}*/
}