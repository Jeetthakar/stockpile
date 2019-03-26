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
public class DatFileUploadAction  extends Action {
	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	Logger Logging = Logger.getLogger(DatFileUploadAction.class);   
	  public ActionForward execute(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception{
	  		Logging.debug("inside execute");
			DatFileUploadForm myForm = (DatFileUploadForm)form;
			String hv=myForm.getHiddenVar();
			String hv1=myForm.getB2var();
			Logging.debug(hv);
			PopFileDialogNewStockDat pstk=new PopFileDialogNewStockDat();
			if(hv.equals("yes")){				
				String str=null;
				FormFile myFile =myForm.getTheFile();		    
				String fileName = myFile.getFileName();
				myForm.setFileName(fileName);
				
				try{       
					java.net.URL imgURL = DatFileUploadAction.class.getResource("DatFileUploadAction.class");
					Logging.debug("imgURL : " + imgURL);
					String resourcepth = imgURL.toString();
					Logging.debug("resourceurl : " + resourcepth);
					/*resourcepth = resourcepth.substring(resourcepth.lastIndexOf(":")-1);
					 System.out.println("resourceurl : " + resourcepth);*/
					resourcepth = resourcepth.substring(6, (resourcepth.lastIndexOf("/WEB-INF/")+8));
					Logging.debug("resourceurl : " + resourcepth);
			 		resourcepth = resourcepth + "/classes/CoolMenus";
			 		Logging.debug("resourceurl is : " + resourcepth);
			 		resourcepth = resourcepth.replaceAll("%20", " ");
			 		Logging.debug("resourceurl is : " + resourcepth);
			        InputStream in =myFile.getInputStream();
			        BufferedInputStream input = new BufferedInputStream(in);
			        FileOutputStream file = new FileOutputStream(new File(resourcepth, fileName));
			     
			        Logging.debug("filename="+fileName);
			        String hist_date=myForm.getDate();
			        Logging.debug("date="+hist_date);
			        PopFileDialogNewStockDat.setHist_Date(hist_date);
			        String filetype=myForm.getFileType();
			        Logging.debug("FileType="+filetype);
			        pstk.setFile_type(filetype);
			       // PopFileDialog.setFiletype(filetype);
			        int read;
					byte[] buffer = new byte[4096];
					while ( (read=input.read(buffer))!=-1)
					{
						file.write(buffer, 0, read);
					}
					file.close();
					input.close();	

					StringBuffer buffer12=pstk.displaydat(fileName,myForm);
					DatFileUploadForm.setDisp_buffer(buffer12);

					
		    }catch(Exception e) {
		    	Logging.error(" Error :"+e.getMessage());
		    }

		   /* String url="../pages/DatFileUpload.jsp?Exchange="+myForm.getD1()+"&fType="+myForm.getFileType()+"&Date="+myForm.getDate();
		    return new ActionForward(url);*/

		    String url="/pages/DatFileUpload.jsp?Exchange="+myForm.getD1()+"&fType="+myForm.getFileType()+"&Date="+myForm.getDate();
		    return new ActionForward(response
                    .encodeURL(url));

		} 
			if(hv1.equals("yes")){
				try{
					Logging.debug("save button clicked");
					String str,iname;
					//PopFileDialogNewStockDat pfd = new PopFileDialogNewStockDat();
					String hist_date=myForm.getDate();
					Logging.debug("date="+hist_date);
			        StringBuffer buffer12=pstk.storeStock(request);
			        Logging.debug("*********************b12 "+buffer12);
			        myForm.setSave_buffer(buffer12);
				}catch(Exception e){
					Logging.error(e.getMessage());
				}
			}

		return (mapping.getInputForward());

	}
}
