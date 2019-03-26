package harrier.income.com.report;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

public class SectorContriToIndexForm extends ActionForm{
	Logger Logging = Logger.getLogger(SectorContriToIndexForm.class);	
	public void reset(){
			
		}
		Collection vectorIndexlist=null;
		ArrayList sectorContri=null;
		private String graph=" ";
		private String buttonValue=null;
		private String index="3252";
		Connection connection=null;
		Connection connection1=null;
		Connect con=ConnectInit.getConnect();
		private String from="";
		private String to="",from_button,to_button;
		private String b1="";
		
		private Vector sectorContriData=null;
		/**
		 * @return Returns the sectorContriData.
		 */
		public Vector getSectorContriData() {
			return sectorContriData;
		}
		/**
		 * @param sectorContriData The sectorContriData to set.
		 */
		public void setSectorContriData(Vector sectorContriData) {
			this.sectorContriData = sectorContriData;
		}
		/**
		 * @return Returns the index.
		 */
		public String getIndex() {
			return index;
		}
		/**
		 * @param index The index to set.
		 */
		public void setIndex(String index) {
			this.index = index;
		}
		public Collection getVectorIndexlist() {
			Vector indexCollection=new Vector();
			
			connection1=null;
			ResultSet rs1=null;
			String query;
			
			try{
				if(connection1==null)
					connection1=con.getdbConnection();
					
						query = ConnectInit.queries.getProperty("index_list");
					
					try {
						Statement stmt = connection1.createStatement();
						rs1 = stmt.executeQuery(query);
						while (rs1.next()) {
							indexCollection.add(new LabelValueBean(rs1.getString(2),rs1.getString(1)));
							
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
					    		if(connection1!=null)
					    			connection1.close();
					    		}catch(Exception ee){
					    			try{
				    					if(connection1!=null)
				    						connection1.close();
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
		/**
		 * @return Returns the b1.
		 */
		public String getB1() {
			return b1;
		}
		/**
		 * @param b1 The b1 to set.
		 */
		public void setB1(String b1) {
			this.b1 = b1;
		}
		/**
		 * @return Returns the from.
		 */
		public String getFrom() {
			return from;
		}
		/**
		 * @param from The from to set.
		 */
		public void setFrom(String from) {
			this.from = from;
		}
		/**
		 * @return Returns the to.
		 */
		public String getTo() {
			return to;
		}
		/**
		 * @param to The to to set.
		 */
		public void setTo(String to) {
			this.to = to;
		}
		
		public String getFrom_button() {
			return from_button;
		}

		public void setFrom_button(String from_button) {
			this.from_button = (String)from_button;
		}
		
		public String getTo_button() {
			return to_button;
		}

		public void setTo_button(String to_button) {
			this.to_button = to_button;
		}

		public void setSectorContri() {
			this.sectorContri = sectorContri;
		}
		
		public ArrayList getSectorContri()
		{
		    //indexname= ComposeIndex.getIndexName(iname);   
		 	//indexSectContri= indexname;  
			 sectorContri=new ArrayList();
			 Vector sectorContriData1=new Vector();
			 SectorWeightage sectorWeightage1;
			 index=	getIndex();
			 from =	getFrom();
			 to   =	getTo();
			try{
				//SectContri1=new Vector();
				//SectContri2=new Vector();
				String fdate=from.trim();
				String tdate=to.trim();
	            // app.Connect con = new app.Connect();
	            
	            	 connection=con.getdbConnection();
					//con.getConnection();
	            
					//PreparedStatement pst = connection.prepareStatement(con.queries.getProperty("select_sector_contri_to_change_in_index"));
					PreparedStatement pst;
					
					pst = connection.prepareStatement(ConnectInit.queries.getProperty("select_sector_contri_to_change_in_index1"));
		    		/* 
					pst.setString(1,index);
					pst.setString(2,index);
					pst.setString(3,tdate);
					pst.setString(4,index);
					pst.setString(5,index);
					pst.setString(6,fdate);
					pst.setString(7,index);
					pst.setString(8,tdate);
					pst.setString(9,index);
					pst.setString(10,fdate);
					*/
					
					ResultSet rs = pst.executeQuery(); 
					while(rs.next())
		                {
		                	    String sector=rs.getString(1);
		                	    sectorContriData1.add(sector);
		                	    //SectContri1.add(d);
		                        String sectWtg=rs.getString(2);

		                        if(sectWtg!=null)
		                        	if( sectWtg.equals(""))
		                        		sectWtg=null;
		                        if(sectWtg==null)
		                        {
		                        	//SectContri2.add("0.00");
		                        	//DatasetFactory1.field4.add("0.00");
		                        	Logging.debug("closing value is null "+sectWtg);
		                        }else{
		                        	//SectContri2.add(year1);
		                        	//DatasetFactory1.field4.add(year1);
		                        	Logging.debug("closing value is "+sectWtg);
		                        }	
		                        sectorContriData1.add(sectWtg);
		                        sectorWeightage1 = new SectorWeightage(sector,sectWtg);
		                        sectorContri.add(sectorWeightage1);
		                        
		                }
					setSectorContriData(sectorContriData1);
					
							              
				}catch(SQLException e){
					Logging.debug(e+"");
	/*				
				JFrame frame=new JFrame();
				JOptionPane.showMessageDialog(frame,"Unable To Connect DataBase","ERROR!",JOptionPane.ERROR_MESSAGE);
				System.exit(0);
		*/			
				}catch(Exception e){
					Logging.debug(e+"");
				}
				finally{
			    	try{
			    		if(connection!=null)
			    			connection.close();
			    		}
			    		catch(Exception ee)
			    		{
			    			Logging.error(" Error : Unable to close Connection "+ee.getMessage());
			    		}
				}
				Logging.debug("abc");
				return sectorContri;
			}
		
		

}
