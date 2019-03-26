/*
 * Created on Aug 21, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package app;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author W
 * 
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ChangepassForm extends ActionForm {
	Logger Logging = Logger.getLogger(ChangepassForm.class);
	String loginName, oldPassword, newPassword, confirmNewPassword;

	/**
	 * @return
	 */
	public String getOldPassword() {
		return oldPassword;
	}

	/**
	 * @param string
	 */
	public void setOldPassword(String string) {
		oldPassword = string;
	}

	/**
	 * @return
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param string
	 */
	public void setNewPassword(String string) {
		newPassword = string;
	}

	/**
	 * @return
	 */
	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	/**
	 * @param string
	 */
	public void setConfirmNewPassword(String string) {
		confirmNewPassword = string;
	}

	/**
	 * @return
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param string
	 */
	public void setLoginName(String string) {
		loginName = string;
	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		if ((loginName == null) || (loginName.length() < 1))
			errors.add("username", new ActionError("error.username.required"));

		if ((oldPassword == null) || (oldPassword.length() < 1))
			errors.add("password", new ActionError("error.password.required"));
		if ((confirmNewPassword == null) || (confirmNewPassword.length() < 1)) {
			errors
					.add("password2", new ActionError(
							"error.password2.required"));
			Logging.debug("password2");
		}
		if ((newPassword == null) || (newPassword.length() < 1))
			errors
					.add("password1", new ActionError(
							"error.password1.required"));
		if ((confirmNewPassword != null) && (confirmNewPassword != null)) {
			if (!newPassword.trim().equals(confirmNewPassword)) {
				errors.add("password1", new ActionError("error.pw1.unequal"));
			}

		}

		return errors;

	}

	public boolean checkwhitespace(String local) {
		int length = local.length();
		boolean b = true;
		char ch;
		char[] charr = new char[length];
		charr = local.toCharArray();
		for (int i = 0; i < length; i++) {
			ch = charr[i];
			if (ch == '@' || ch == '_' || ch == '*' || ch == '#' || ch == '$'
					|| ch == '%' || ch == '^' || ch == '~' || ch == '&'
					|| ch == '?') {
				b = false;
				break;
			}
		}
		return b;
	}
}
