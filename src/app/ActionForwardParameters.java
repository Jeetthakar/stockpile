/*
 * Created on Oct 23, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;

/**
 * @author W
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import org.apache.struts.action.ActionForward;

/**
 * Simple utility class to add parameters to an ActionForward 
 * from within an action. This action is useful when you from 
 * within an action need to pass parameters to the jsp page or another
action 
 * you are forwarding to.
 */
public class ActionForwardParameters {

    /**
    *  Encupsulates parameters for ActionForward.
    */

    private Map params = new HashMap();

    /**
     * add all the parameters and values from the hashtable to the
actionforward
     * @param parametersValues a hastable with key value pairs
     * @return ActionForwardParameters object
     */
    public ActionForwardParameters add(Hashtable parametersValues) {
        for(Iterator i =
parametersValues.keySet().iterator();i.hasNext();){
            String key = (String) i.next();
            params.put(key,(String) parametersValues.get(key));
        }
        
        return this;
    }

    /**
    * Add parameters to provided ActionForward
    * @param forward ActionForward to add parameters to
    * @return ActionForward with added parameters to URL
    */
    public ActionForward forward(ActionForward forward) {
        StringBuffer path = new StringBuffer(forward.getPath());
        Iterator iter = params.entrySet().iterator();
        if (iter.hasNext()) {
            //add first parameter, if avaliable
            Map.Entry entry = (Map.Entry) iter.next();
            path.append("?" + entry.getKey() + "=" + entry.getValue());
            //add other parameters     
            while (iter.hasNext()) {
                entry = (Map.Entry) iter.next();
                path.append("&" + entry.getKey() + "=" +
entry.getValue());
            }
        }

        return new ActionForward(path.toString());
    }

  public String getNewPath(String path)
   {
  	 StringBuffer buff = new StringBuffer(path);
  	Iterator iter = params.entrySet().iterator();
    if (iter.hasNext()) {
        
        Map.Entry entry = (Map.Entry) iter.next();
        buff.append("?" + entry.getKey() + "=" + entry.getValue());
        while (iter.hasNext()) {
            entry = (Map.Entry) iter.next();
            buff.append("&" + entry.getKey() + "=" +entry.getValue());
        }
    }  
    return buff.toString();  
    
   }
}

