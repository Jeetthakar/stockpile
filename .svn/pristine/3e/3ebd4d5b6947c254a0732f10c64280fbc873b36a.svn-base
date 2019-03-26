/*
 * Created on Sep 16, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.compute;

import harrier.income.com.entities.CFormula;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.harrier.initializeation.ConnectInit;

/**
 * @author kena
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSplitReverse {

//	private Statement st_state,stm;	
	private PreparedStatement pst_preStat;
	private ResultSet rst,rs,rst1,rst2,rst3;
	static Connection con_connect;
//	private Properties p_queries,prop;
	private Logger log_logger;
//	private CFormula cFor=new CFormula();
	private double nDivisor;
	private double ltpOld,ltpNew,tmcvOld,tmcvNew,oldDivisor,newDivisor;
	long tisOld,tisNew;
	private double mcvOld,mcvNew,iwf,diffTmcv;
	public CSplitReverse() {
		//loads file required
		
//		p_queries = new Properties();
//		log_logger =
//			Logger.getLogger(CIndexCalculator.class.getName());
//		PropertyConfigurator.configure("resources/l4j3.properties");
//		try {
//			p_queries.load(new FileInputStream("resources/query.properties"));
//			log_logger.debug("File Loaded");
//		} catch (IOException e) {
//			log_logger.debug("unable to get file");
//			log_logger.error("error in file");
//			//System.exit(0);
//		}
	}
	//function to establish connection
	public void getConnection() {
		try {
			
			Properties rs = new Properties();
			rs.load(new FileInputStream("resources/database.properties"));
			String machine = rs.getProperty("machine");
			String port = rs.getProperty("port");
			String database = rs.getProperty("database");
			String user = rs.getProperty("user");
			String password = rs.getProperty("password");
			Class.forName("org.postgresql.Driver").newInstance();
			String url = "jdbc:postgresql://" + machine + ":" + port + "/"
			+ database;		
			con_connect = DriverManager.getConnection(url, user, password);
			log_logger.debug(url+user+password);
		} catch (SQLException e) {
			log_logger.debug("connection" + e);
			log_logger.debug("unable to connect database");
			//System.exit(0);
			
		} catch (Exception e) {
			log_logger.debug("connection1" + e);
		}
		
	}
	public double computeDivisor(long l_indexID,long l_stockID,long aforVal,long bhVal,String date){
		try {
			CFormula cFor = ConnectInit.getCFormula();
			pst_preStat= con_connect.prepareStatement(ConnectInit.queries.getProperty("corporate_action_parameters"));
			pst_preStat.setString(1,date);
			pst_preStat.setLong(2,l_indexID );
			pst_preStat.setLong(3,l_stockID );
			log_logger.debug("till query");
			rst =pst_preStat.executeQuery();//executes query to get data for index computation
			log_logger.debug("Executed");
			ltpOld=rst.getDouble("ltp");
			tisOld=rst.getLong("tis");
			tmcvOld=rst.getDouble("tmcv");
			oldDivisor=rst.getDouble("divisor");
			mcvOld=rst.getDouble("mcv");
			iwf=rst.getDouble("iwf");
			while(rst.next()){
			ltpNew=cFor.newLTPSplitRev(ltpOld,aforVal,bhVal);
			tisNew=cFor.newTISSplitRev(tisOld,aforVal,bhVal);
			mcvNew=cFor.calMarketCap(ltpNew,1,1,tisNew,iwf);//insert exchange code,marketlot
			tmcvNew=tmcvOld-mcvOld+mcvNew;
			diffTmcv=tmcvNew-tmcvOld;
			log_logger.debug("ltp new =" + ltpNew);
			log_logger.debug("tis new =" + tisNew);
			log_logger.debug("mcv new =" + mcvNew);
			log_logger.debug("tmcv new =" + tmcvNew);
			log_logger.debug("diffTmcv =" + diffTmcv);
			}
				
		}catch(SQLException e){
			log_logger.debug(e);
		}
		return nDivisor;
	}
	public static void main(String srgs[]) {
		long l_indexid=1,l_stockid=5;
		double index1;
		long aRatio=1,bRatio=2;
		
		CDividendStk cindexCal =new CDividendStk();
		cindexCal.getConnection();
	//	log_logger.debug("Called");
		//	cindexCal.returnResult(l_index);//insert index id
		index1=cindexCal.computeDivisor(l_indexid,l_stockid,aRatio,bRatio,"13-09-2004");
	//	log_logger.debug("index divisor is :" + index1);
		//log_logger.debug("Tmcv " +cindexCal.getTmcv());
	}
	
}

