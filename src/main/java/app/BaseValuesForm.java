/*
 * Created on Mar 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author Vivek
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BaseValuesForm extends ActionForm {
    
    StringBuffer tableString=new StringBuffer("empty");
    NewIndexForm newIndexForm;
    String fromComposition;

    /**
     * @return Returns the fromComposition.
     */
    public String getFromComposition() {
        return fromComposition;
    }
    /**
     * @param fromComposition The fromComposition to set.
     */
    public void setFromComposition(String fromComposition) {
        this.fromComposition = fromComposition;
    }
    /**
     * @return Returns the newIndexForm.
     */
    public NewIndexForm getNewIndexForm() {
        return newIndexForm;
    }
    /**
     * @param newIndexForm The newIndexForm to set.
     */
    public void setNewIndexForm(NewIndexForm newIndexForm) {
        this.newIndexForm = newIndexForm;
    }
    /**
     * @return Returns the tableString.
     */
    public StringBuffer getTableString() {
        return tableString;
    }
    /**
     * @param tableString The tableString to set.
     */
    public void setTableString(StringBuffer tableString) {
    //   blankbaseValueTable();
    }
    
    public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request) {
       // System.out.println("Form submitted");
        ActionErrors actionErrors=new ActionErrors();
        if(request.getParameter("B4")!=null && request.getParameter("B4").trim().equals("<< Back")){
            return actionErrors; 
        }
        
        this.tableString=new FillTables().DesignBaseValuesTable(newIndexForm,request,actionErrors);
      //  System.out.println("Form submitted1 : "+tableString.length());
        return actionErrors;
    }
    
    public void blankbaseValueTable(){
        
    }
}
