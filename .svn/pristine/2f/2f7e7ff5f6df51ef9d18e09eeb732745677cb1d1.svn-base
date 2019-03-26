/**
 * 
 */
package harrier.income.com.report;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * @author lokesh
 *
 */
public class ReportPreDetail {
	private String filePaths1=null,index_name1=null,tdate=null,
	 fdate=null,list1=null,list2=null;
		
	ReportPreDetail(String filePath1,String index_id11,int fdate1,Date tdate1,String index_id2,String stock_id1){
		super();
		this.filePaths1=filePath1;
		setIndex_name1(index_id11);
		this.index_name1=getIndex_name1();
		//this.index_name1=index_id11;
		setFdate(fdate1);
		this.fdate=getFdate();
		setTdate(tdate1);
		this.tdate=getTdate();
		this.list1=index_id2;
		this.list2=stock_id1;
	}
	public String getFilePaths1() {
		return filePaths1;
	}

	public void setFilePaths1(String filePaths1) {
		this.filePaths1 = filePaths1;
	}

	public String getIndex_name1() {
		return index_name1;
	}

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

	public String getList1() {
		return list1;
	}
	public void setList1(String llist1) {
		this.list1 = llist1;
	}
	public String getList2() {
		return list2;
	}
	public void setList2(String llist2) {
		this.list2 = llist2;
	}
	
}
