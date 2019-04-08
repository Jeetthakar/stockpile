package harrier.income.com.report;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

import app.Connect;

import com.harrier.initializeation.ConnectInit;

public class SectorwiseForm extends ActionForm{
	Logger Logging = Logger.getLogger(SectorwiseForm.class);
	public void reset(){
		
	}
	Collection vectorIndexlist=null;
	private String graph=" ";
	private String buttonValue=null;
	
	private String[] d2={"3252","3259","0"};
	Connection connection=null;
	Connect con=ConnectInit.getConnect();
	
	/**
	 * @return Returns the d2.
	 */
	public String[] getD2() {
		return d2;
	}
	/**
	 * @param d2 The d2 to set.
	 */
	public void setD2(String[] d2) {
		this.d2 = d2;
	}
	

	public Collection getVectorIndexlist() {
		Vector indexCollection=new Vector();
		
		connection=null;
		ResultSet rs=null;
		String query;
		
		try{
			if(connection==null)
				connection=con.getdbConnection();
				
					query = ConnectInit.queries.getProperty("index_list");
				
				try {
					Statement stmt = connection.createStatement();
					rs = stmt.executeQuery(query);
					while (rs.next()) {
						indexCollection.add(new LabelValueBean(rs.getString(2),rs.getString(1)));
						
					}

					vectorIndexlist=indexCollection;
				} catch (Exception e) {
					// TODO: handle exception
					Logging.error(" Error : "+e.getMessage());
										}
				    }catch(Exception e){
				    	Logging.error(" Error : "+e.getMessage());
				     }
				    finally{
				    	try{
				    		if(connection!=null)
				    			connection.close();
				    		}catch(Exception ee){
				    			try{
			    					if(connection!=null)
			    						connection.close();
			    				}catch(Exception ex){
						    Logging.error(" Error : Unable to close Connection "+ex.getMessage());
		    					 }
					 	   Logging.error(" Error : Unable to close Connection "+ee.getMessage());
				    		}
			    }
				
				    return vectorIndexlist;

	}
	public void setVectorIndexlist(Vector vectorIndexlist) {
				this.vectorIndexlist=vectorIndexlist;		
	}
	public ActionErrors validate(ActionMapping mapping,HttpServletRequest request){
		ActionErrors errors = new ActionErrors();
		
		return errors;
	}
	/**
	 * @return Returns the graph.
	 */
	public String getGraph() {
		return graph;
	}
	/**
	 * @param graph The graph to set.
	 */
	public void setGraph(String graph) {
		this.graph = graph;
	}
	/**
	 * @return Returns the buttonValue.
	 */
	public String getButtonValue() {
		return buttonValue;
	}
	/**
	 * @param buttonValue The buttonValue to set.
	 */
	public void setButtonValue(String buttonValue) {
		this.buttonValue = buttonValue;
	}
	
	

}
