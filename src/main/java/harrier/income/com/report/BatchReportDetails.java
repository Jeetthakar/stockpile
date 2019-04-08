/**
 * 
 */
package harrier.income.com.report;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lokesh
 *
 */
public class BatchReportDetails {
	
	private String filePaths = null, index_name1 = null, tdate = null,
	 			fdate = null, list1 = null, list2 = null, inxid = null;
	
   /**
	* default Constructor.
	*/
	
   /**
	* parameterized Constructor.
	*/
	BatchReportDetails(String filePath, String index_id11, int fdate1, Date tdate1, String index_id2, String stock_id1, String id) {
		super();
		this.filePaths = filePath;
		setIndex_name1(index_id11);
		this.index_name1 = getIndex_name1();
		setFdate(fdate1);
		this.fdate = getFdate();
		setTdate(tdate1);
		this.tdate = getTdate();
		this.list1 = index_id2;
		this.list2 = stock_id1;
		setInxid(id);
		this.inxid = getInxid();
		
	}
	
   /**
	* @return Returns the filePaths.
	*/
	public String getFilePaths() {
		return filePaths;
	}

   /**
	* @param filePaths The filePaths to set.
	*/
	public void setFilePaths(String filePaths) {
		this.filePaths = filePaths;
	}

   /**
	* @return Returns the index_name1.
	*/
	public String getIndex_name1() {
		return index_name1;
	}

   /**
	* @param index_name1 The index_name1 to set.
	*/
	public void setIndex_name1(String fileVariables) {
		this.index_name1 = fileVariables;
	}
	
   /**
    * @return Returns the fdate.
	*/
	public String getFdate() {
		return fdate;
	}
	

   /**
	* @param fdate The fdate to set.
	*/
	public void setFdate(int fdate11) {
		
		long newd=fdate11*24*60*60*1000L;
	    java.util.Date s4 = new Date();
	    long t2           = s4.getTime();
	    long t1           = t2-newd; 
	    Date s1           = new Date(t1);
	    SimpleDateFormat ft1 = new SimpleDateFormat("dd-MM-yyyy");
	    fdate             = ft1.format(s1);
	    
	}
	
   /**
	* @return Returns the tdate.
	*/
	public String getTdate() {
		return tdate;
	}
	
   /**
	* @param tdate The tdate to set.
	*/
	public void setTdate(Date tdate11) {
		Date curr = new Date();      
	    SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
	    tdate = ft.format(curr);   
	}
	
   /**
	* @return Returns the list1.
	*/
	public String getList1() {
		return list1;
	}
	
   /**
	* @param list1 The list1 to set.
	*/
	public void setList1(String llist1) {
		this.list1 = llist1;
	}
	
   /**
	* @return Returns the List2.
	*/
	public String getList2() {
		return list2;
	}
	
   /**
	* @param list2 The List2 to set.
	*/
	public void setList2(String llist2) {
		this.list2 = llist2;
	}
	
   /**
	* @return Returns the inxid.
	*/
	public String getInxid() {
		return inxid;
	}
	
   /**
	* @param inxid The inxid to set.
	*/
	public void setInxid(String inxid) {
		this.inxid = inxid;
	}
}
