/*
 * Created on Mar 2, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.masters;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import app.CapturedIndexCollection;
import app.Connect;

import com.harrier.initializeation.ConnectInit;
/**
 * @author naresh
 * 
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CapturedIndexAction extends Action{
	static Connect con1 = ConnectInit.getConnect();
	static Logger log;
	static{
		log =Logger.getLogger(CapturedIndexAction.class);
		PropertyConfigurator.configure(con1.resourceurl+"/resources/log4j.properties");
	}

	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)
		{
		   ActionForward fr = null;
		   CapturedIndexForm capturedindexForm=(CapturedIndexForm) form;
		   String[] id1=null;
		   String but_pressed=capturedindexForm.getB1();		  
		   if(but_pressed.equals("Submit"))
		   {
			String check_flag=capturedindexForm.getCheck_flag();
			String indexid=capturedindexForm.getIndexID();
		   	id1=request.getParameterValues("indexID");
		   	if(id1==null || id1.length==0){
		   		return fr= new ActionForward("/pages/masters/AddCapturedIndex.jsp");
		   	}
			CapturedIndexCollection.addStocksInSourceTable("Submit",id1,request);
		    CapturedIndexCollection.insertIndexValues(form);
		    capturedindexForm.setMessagess("m");
		    
		    	return fr= new ActionForward("/pages/masters/AddCapturedIndex.jsp?check_flag="+check_flag);
		   }
		   if(but_pressed.equals("Reset"))
		   {
		   	log.debug("button pressed is reset");	
		    String button="Reset";			
			return fr= new ActionForward("/pages/masters/AddCapturedIndex.jsp?opt=Reset");
		   }	
		   
		   return mapping.getInputForward();
		}
}
