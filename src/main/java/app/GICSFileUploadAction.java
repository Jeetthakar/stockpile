/*
 * Created on Apr 26, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package app;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 * @author ashishi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GICSFileUploadAction  extends Action {
	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	Logger Logging = Logger.getLogger(GICSFileUploadAction.class);  
	  public ActionForward execute(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception{
			Logging.debug("inside execute");
			
			GICSFileUploadForm Idf=(GICSFileUploadForm) form;
			GICSReadFile pstk=new GICSReadFile();
			String hv=Idf.getHiddenVar();
			Logging.debug(hv);
			if(hv.equals("show")){
				
				String str=null;
				FormFile myFile =Idf.getTheFile();		    
				String fileName    = myFile.getFileName();
				Idf.setFileName(fileName);				
				try{       
					java.net.URL imgURL = GICSFileUploadAction.class.getResource("GICSFileUploadAction.class");
					Logging.debug("imgURL : " + imgURL);
					String resourcepth = imgURL.toString();
					Logging.debug("resourceurl : " + resourcepth);
					/*resourcepth = resourcepth.substring(resourcepth.lastIndexOf(":")-1);
					 Logging.debug("resourceurl : " + resourcepth);*/
					resourcepth = resourcepth.substring(6, (resourcepth.lastIndexOf("/WEB-INF/")+8));
					Logging.debug("resourceurl : " + resourcepth);
			 		resourcepth = resourcepth + "/classes/CoolMenus";
			 		Logging.debug("resourceurl is : " + resourcepth);
			 		resourcepth = resourcepth.replaceAll("%20", " ");
			 		Logging.debug("resourceurl is : " + resourcepth);
			        InputStream in =myFile.getInputStream();
			        BufferedInputStream input = new BufferedInputStream(in);
			        FileOutputStream file = new FileOutputStream(new File(resourcepth, fileName));
			        GICSReadFile.setStr_fileName(fileName);
			        Logging.debug("filename="+fileName);
			        String filetype=Idf.getFileType();
			        Logging.debug("FileType="+filetype);
			        pstk.setFile_type(filetype);
			        //PopFileDialog.setFiletype(filetype);
			        int read;
					byte[] buffer = new byte[4096];
					while ( (read=input.read(buffer))!=-1)
					{
						file.write(buffer, 0, read);
					}
					file.close();
					input.close();
					StringBuffer buffer12=pstk.displaydat(fileName,Idf);
					Idf.setDisp_buffer(buffer12);
		    }catch(Exception e) {
		    	Logging.error(" Error :"+e.getMessage());
		    }
		    String url="/pages/GICSFileDialog.jsp?Exchange="+Idf.getD1()+"&classification="+Idf.getId();
		    return new ActionForward(url);
		} 
			if(hv.equals("save")){
				try{
					Logging.debug("save button clicked");
					StringBuffer buffer12=pstk.storeStock(Idf.getD1(),request);
					Idf.setDisp_buffer(buffer12);
				}catch(Exception e){
					Logging.debug(e.getMessage());
				}
			}
			
		    return mapping.findForward("success");
	}
}
