/*
 * Created on Sep 21, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */


/**
 * @author W
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */


	/*
	 * Created on Sep 10, 2004
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
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;

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
	public class Index {
		static Logger Logging = Logger.getLogger(Index.class);
		private PreparedStatement pst_preStat, pst;
		
		private Statement stm;
		
		private ResultSet rst, rst1, rst2, rst3;
		
		private  app.Connect con_connect;
		
	//	private Properties p_queries, prop;
		
		private Logger log_logger;
		
//		private CFormula cFor = new CFormula();
		
		private double ltp = 0, iwf = 0, mcv = 0, exch = 1, tmcv = 0, divisor = 0,
		base_value = 0, indexVal = 0, flag = 0, fto_exch = 1;
		
		private Vector v = new Vector();
		
		private long ml = 0, tis = 0, stkid = 0, curridStk = 0, curridIndex = 0;
		//private Connect c=ConnectInit.getConnect();
		private long stkId = 0, currId = 0;
		public static double[] fiftytwo_min_max=new double[2]; 
//		static CIndexCalculator ICalculator=new CIndexCalculator();
		private Connection connection=null;
		public Index() {
//			Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
			con_connect = ConnectInit.getConnect();
			try{
				if(connection==null)
				{
					connection=con_connect.getdbConnection();
				}
			//loads file required
		//	p_queries = new Properties();
			log_logger = Logger.getLogger(CIndexCalculator.class.getName());
			PropertyConfigurator.configure("resources/l4j3.properties");
		/*	try {
				p_queries.load(new FileInputStream("resources/query.properties"));
				Logging.debug("File Loaded");
			} catch (IOException e) {
				Logging.debug("unable to get file");
				log_logger.error("error in file");
			//	System.exit(0);
			}*/
			} catch (Exception e) {
				log_logger.error("error in Connection"+e);
				//Logging.getDebug("Error :" + e);
			}
			
		}
		
		
		public ResultSet getExchCode() {
			try {
//				Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
				Connect con=ConnectInit.getConnect();
				Logging.debug("in exchange code query");
				if(connection==null)
				{
					connection=con.getdbConnection();
				}
				pst_preStat = connection.prepareStatement(ConnectInit.queries
						.getProperty("get_exchange_rate"));
				rst2 = pst_preStat.executeQuery();
				//executes query for getting exchange codes
				stkId = rst2.getLong("stock_currency_id");
				currId = rst2.getLong("base_currency_id");
				fto_exch = rst2.getDouble("intra_day_ex_rate_value");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				Logging.debug("Erro in getExchCode "+e1.getMessage());
			}
			return rst2;
		}
		
		public String getTime() {
			java.util.Date dt = new java.util.Date();
			dt.getDate();
			return dt.toString().split(" ")[3];
		}
		
		public double getValue(long l_indexID,long l_stkid,String date){
			double ltp1=0,adjustprice=0,closeprice=0;
			try{
				//select adjusted_price,spd.stock_closing_price from 
				//information_schema.stock_price_daily spd, information_schema.index_composition ic 
				//where  ic.index_id\=? and ic.stock_id\=spd.stock_id and spd.stock_id=? 
				//and to_date(stock_price_date,'dd-mm-yyyy') \= to_date(?,'dd-mm-yyyy') - 1
				
				//Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
				Connect con=ConnectInit.getConnect();
				if(connection==null)
				{
					connection=con.getdbConnection();
				}
			pst_preStat = connection.prepareStatement(ConnectInit.queries
					.getProperty("get_adjusted_stock_price"));
			pst_preStat.setLong(1, l_indexID);
			pst_preStat.setLong(2, l_stkid);
			pst_preStat.setString(3,date);
			rst2 = pst_preStat.executeQuery();
			rst2.next();
			adjustprice=rst2.getDouble("adjusted_price");
			closeprice=rst2.getDouble("stock_closing_price");
			if(adjustprice==0){
				ltp1= closeprice;
			}else
			{
				ltp1= adjustprice;
			}
			}catch (SQLException e) {
				Logging.debug(e.getMessage());
				log_logger.debug("index values" + e);
			}
			return ltp1;
		}
		
		//query to get values required in index computation
		public String computeIndex(String indexID, String settlement,String close) {
			CFormula cFor = ConnectInit.getCFormula();
			String time = getTime();
			CIndexCalculator ICalculator = ConnectInit.getCIndexCalculator();
			long l_indexID = Long.parseLong(indexID);
			double high, low;
			String date="";
			long id;
			try {
//				Change by Manoj Adekar for Dynamic connection using getdbConnection() instead of  getConnection()
				Connect con=ConnectInit.getConnect();
				if(connection==null)
				{
					connection=con.getdbConnection();
				}
				pst_preStat =connection.prepareStatement(ConnectInit.queries
						.getProperty("compute_index"));
				pst_preStat.setLong(1, l_indexID);
				//pst_preStat.setString(1, date);
				Logging.debug("Inside compute Index");
				rst = pst_preStat.executeQuery();//executes query to get data for
				// index computation
				//get all ltp values and stock id for the given date
				while (rst.next()) {
				//	Logging.debug("Row no." + rst.getRow());
					ltp = rst.getDouble("ltp");//get ltp
					iwf = rst.getDouble("iwf");//get iwf
					ml = rst.getLong("market_lot");//get market lot
					tis = (long) rst.getDouble("tis");//get tis
					stkid = rst.getLong("stock_id");//get stock id
					curridStk = rst.getLong("stock_currency_id");//get currencyId for stock
					curridIndex = rst.getLong("base_currency_id");
					date = rst.getString("price_date");
				//	if(ltp==0){
				//		ltp=getValue(l_indexID,stkid ,date);
				//	}
					Logging.debug("After getting all");
					if ((curridStk != curridIndex) && (flag == 0)) {
						rst2 = getExchCode();
						flag = 1;
					}
					if ((curridStk != curridIndex)) {
						while (rst2.next()) {
							if ((curridStk == stkId) && (curridIndex == currId)) {
								exch = fto_exch;
								break;
							}
						}
						rst2.beforeFirst();
						
					}
					base_value = rst.getDouble("base_value");//get base value for index
					mcv = cFor.calMarketCap(ltp, ml, exch, tis, iwf);//calculates
					v.addElement(new Double(mcv));//collects mcv in vector
				}
				tmcv = cFor.totalMarketCap(v);//calculate tmcv
				try {
					pst_preStat = Connect.con.prepareStatement(ConnectInit.queries
							.getProperty("select_daily_divisor"));
					pst_preStat.setLong(1, l_indexID);//set ? for index id
					//pst_preStat.setString(2, date);
					rst1 = pst_preStat.executeQuery();//execute query
					
					if (!rst1.next())//(rst1.getRow() == 0)//check
					{
						Logging.debug("Before divisor computed");
						divisor = cFor.divisor(tmcv, base_value);
						Logging.debug("After divisor computed");
						
					} else {
						divisor = rst1.getDouble("divisor");
						Logging.debug("divisor taken");
					}
				} catch (Exception e) {
					Logging.debug(e.getMessage()+" Error in while");
					log_logger.debug("index values" + e);
				}
				indexVal = cFor.index(tmcv, divisor);// index compute
				Logging.debug("value calculated = " + indexVal);
				//inserts value in intra day indices
				try {
					try {
						Logging.debug("Before craeteStatement in intraday"); 
						stm = Connect.con.createStatement();
						 rst = stm
						 .executeQuery("select nextval('intra_day_indices_id')");
						 Logging.debug("After craeteStatement in intraday "+rst);
						 rst.next();
						 
					} catch (SQLException e) {
						Logging.debug("error in Query "+e.getMessage());
						
					}
					id = rst.getLong(1);
					pst_preStat = Connect.con.prepareStatement(ConnectInit.queries
							.getProperty("insert_into_intra_day_indices"));
					Logging.debug("Before set value");
					pst_preStat.setLong(1, id);
					pst_preStat.setLong(5, l_indexID);
					pst_preStat.setDouble(6, tmcv);
					pst_preStat.setDouble(2, indexVal);
					pst_preStat.setString(3, date);
					
					pst_preStat.setString(4, time);
					
					pst_preStat.executeUpdate();//execute query
					Logging.debug("after set value");
				} catch (SQLException e) {
					Logging.debug("ERROR");
					log_logger.debug("index values" + e);
				}
				// select index_lowest_value,index_highest_value from index value daily
				
				try {
					pst_preStat = Connect.con.prepareStatement(ConnectInit.queries
							.getProperty("get_high_low_index_value"));
					pst_preStat.setLong(1, l_indexID);//set ? for index id
					pst_preStat.setString(2, date);
					rst2 = pst_preStat.executeQuery();//execute query
				} catch (Exception e) {
					Logging.debug("ERROR");
					log_logger.debug("index values" + e);
				}
				//if no enteries
				Logging.debug("Divisor is "+divisor);
				rst2.next();
				if (rst2.getRow() == 0) {
					try {
						try {
							stm = Connect.con.createStatement();
							rst = stm
							.executeQuery("select nextval('index_value_daily_id')");
							Logging.debug(rst);
							rst.next();
						} catch (SQLException e) {
							Logging.debug("error in Query "+e.getMessage());
					
						}
						id = rst.getLong(1);
						
						//code for 52 week low and high from 
	                 							
			        	fiftytwo_min_max =ICalculator.getFiftytwo_Week_HighLow(""+l_indexID);
			        	if(indexVal > fiftytwo_min_max[0]){
			        		fiftytwo_min_max[0]=indexVal;
			        	}
			        	if(indexVal < fiftytwo_min_max[1]){
			        		fiftytwo_min_max[1]=indexVal;
			        	}
			        	// code for 52 week low and high to
						pst_preStat = Connect.con.prepareStatement(ConnectInit.queries
								.getProperty("insert_into_index_value_daily"));
						pst_preStat.setLong(1, id);
						pst_preStat.setDouble(2, indexVal);
						pst_preStat.setDouble(3, indexVal);
						pst_preStat.setDouble(4, indexVal);
						pst_preStat.setDouble(5, indexVal);
						pst_preStat.setLong(6, l_indexID);
						pst_preStat.setString(7, date);
						pst_preStat.setString(8, settlement);
						pst_preStat.setDouble(9, divisor);
						pst_preStat.setDouble(10, tmcv);
						pst_preStat.setDouble(11,fiftytwo_min_max[0]);
						pst_preStat.setDouble(12,fiftytwo_min_max[1]);
						
						
						
						
						
						
					int count=	pst_preStat.executeUpdate();//execute query
						
					} catch (SQLException e) {
						Logging.debug("ERROR Inserting in index_value_daily "+e.getMessage());
						log_logger.debug("index values" + e);
					}
				} else {
					low = rst2.getDouble("index_lowest_value");
					high = rst2.getDouble("index_highest_value");
					if (indexVal > high) {
						//update_high_index_value
						
						pst_preStat = Connect.con.prepareStatement(ConnectInit.queries
								.getProperty("update_high_index_value"));
						pst_preStat.setDouble(1, indexVal);
						pst_preStat.setLong(2, l_indexID);
						pst_preStat.setString(3, date);
						pst_preStat.executeUpdate();
					}
					if (indexVal < low) {
						//update_low_index_value
						
						pst_preStat = Connect.con.prepareStatement(ConnectInit.queries
								.getProperty("update_low_index_value"));
						pst_preStat.setDouble(1, indexVal);
						pst_preStat.setLong(2, l_indexID);
						pst_preStat.setString(3, date);
						pst_preStat.executeUpdate();
					}
				}
				
			} catch (SQLException e) {
				log_logger.debug(e);
			}
			if(close=="yes")
			{
				//update information_schema.index_value_daily set 
				
				Logging.debug("in closing part");
				try{
					pst_preStat = Connect.con.prepareStatement(ConnectInit.queries
							.getProperty("update_index_value_daily_set_closing_value"));
					pst_preStat.setDouble(1, indexVal);
					pst_preStat.setDouble(2, tmcv);
					pst_preStat.setLong(3,l_indexID);
					pst_preStat.setString(4, date);
					pst_preStat.executeUpdate();
					
				}catch (SQLException e) {
					log_logger.debug(e);
				}
			}
			String str = Double.toString(indexVal);
			return str;
		}
		
		public static void main(String srgs[]) {
			long l_index = 1;
			String index1;
			long tm;
			
			Index cindexCal = new Index();		
			Logging.debug("Called");
			//	cindexCal.returnResult(l_index);//insert index id
			index1 = cindexCal.computeIndex("158",  "yes","yes");
			Logging.debug("index value is :" + index1);
			Logging.debug("Tmcv " + cindexCal.getTmcv());
			tm = (long) cindexCal.getTmcv();
			Logging.debug(tm);
			Logging.debug("Divisor " + cindexCal.getDivisor());
		}
		
		/**
		 * @return Returns the curridStk.
		 */
		public long getCurridStk() {
			return curridStk;
		}
		
		/**
		 * @param curridStk
		 *            The curridStk to set.
		 */
		public void setCurridStk(long curridStk) {
			this.curridStk = curridStk;
		}
		
		/**
		 * @return Returns the divisor.
		 */
		public double getDivisor() {
			return divisor;
		}
		
		/**
		 * @param divisor
		 *            The divisor to set.
		 */
		public void setDivisor(double divisor) {
			this.divisor = divisor;
		}
		
		/**
		 * @return Returns the exch.
		 */
		public double getExch() {
			return exch;
		}
		
		/**
		 * @param exch
		 *            The exch to set.
		 */
		public void setExch(double exch) {
			this.exch = exch;
		}
		
		/**
		 * @return Returns the indexVal.
		 */
		public double getIndexVal() {
			return indexVal;
		}
		
		/**
		 * @param indexVal
		 *            The indexVal to set.
		 */
		public void setIndexVal(double indexVal) {
			this.indexVal = indexVal;
		}
		
		/**
		 * @return Returns the iwf.
		 */
		public double getIwf() {
			return iwf;
		}
		
		/**
		 * @param iwf
		 *            The iwf to set.
		 */
		public void setIwf(double iwf) {
			this.iwf = iwf;
		}
		
		/**
		 * @return Returns the ltp.
		 */
		public double getLtp() {
			return ltp;
		}
		
		/**
		 * @param ltp
		 *            The ltp to set.
		 */
		public void setLtp(double ltp) {
			this.ltp = ltp;
		}
		
		/**
		 * @return Returns the mcv.
		 */
		public double getMcv() {
			return mcv;
		}
		
		/**
		 * @param mcv
		 *            The mcv to set.
		 */
		public void setMcv(double mcv) {
			this.mcv = mcv;
		}
		
		/**
		 * @return Returns the ml.
		 */
		public long getMl() {
			return ml;
		}
		
		/**
		 * @param ml
		 *            The ml to set.
		 */
		public void setMl(long ml) {
			this.ml = ml;
		}
		
		/**
		 * @return Returns the stkid.
		 */
		public long getStkid() {
			return stkid;
		}
		
		/**
		 * @param stkid
		 *            The stkid to set.
		 */
		public void setStkid(long stkid) {
			this.stkid = stkid;
		}
		
		/**
		 * @return Returns the stkId.
		 */
		public long getStkId() {
			return stkId;
		}
		
		/**
		 * @param stkId
		 *            The stkId to set.
		 */
		public void setStkId(long stkId) {
			this.stkId = stkId;
		}
		
		/**
		 * @return Returns the tis.
		 */
		public long getTis() {
			return tis;
		}
		
		/**
		 * @param tis
		 *            The tis to set.
		 */
		public void setTis(long tis) {
			this.tis = tis;
		}
		
		/**
		 * @return Returns the tmcv.
		 */
		public double getTmcv() {
			return tmcv;
		}
		
		/**
		 * @param tmcv
		 *            The tmcv to set.
		 */
		public void setTmcv(double tmcv) {
			this.tmcv = tmcv;
		}
		
		/**
		 * @return Returns the rst.
		 */
		public ResultSet getRst() {
			return rst;
		}
		
		/**
		 * @param rst
		 *            The rst to set.
		 */
		public void setRst(ResultSet rst) {
			this.rst = rst;
		}
	}



