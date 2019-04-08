/*
 * Created on Sep 9, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com;
import java.util.*;
import harrier.income.com.entities.*;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CStockExchList {
	private Vector col_exchlist;
	
	/**
	 * Constructor will create the instance of the collection 
	 * object
	 */
	public CStockExchList() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @author Administrator
	 * @param exch will hold the details of the StockExchange
	 * 
	 * addExchange Function adds the StockExchange to the list 
	 */
	public boolean addExchange(CStockExchange exch){
		return true;
	}
	
	/**
	 * @author Administrator
	 * 
	 * removeExchange()method removes the Stock Exchange from the
	 * List of Stock Exchanges
	 */
	public boolean removeExchange(String exch){
		return true;
	}
	
	
	/**
	 * @return Returns the col_exchlist.
	 */
	public Vector getCol_exchlist() {
		return col_exchlist;
	}
	/**
	 * @param col_exchlist The col_exchlist to set.
	 */
	public void setCol_exchlist(Vector col_exchlist) {
		this.col_exchlist = col_exchlist;
	}
}
