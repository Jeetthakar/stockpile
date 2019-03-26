/*
 * Created on Sep 9, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package harrier.income.com.securities;


/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CUser {
	private String s_userName;
	private String s_password;
	private String s_firstName;
	private String s_middleName;
	private String s_lastName;
	private String s_compName;
	private String s_branch;
	private String s_designation;
	private String s_location;
	private String s_phoneNumber;
	private String s_mobileNumber;
	private String s_email;
	private CRole role;
	
	/**
	 * 
	 */
	public CUser() {
		// TODO Auto-generated constructor stub
		
	}
		
	/**
	 * @return Returns the s_branch.
	 */
	public String getS_branch() {
		return s_branch;
	}
	/**
	 * @param s_branch The s_branch to set.
	 */
	public void setS_branch(String s_branch) {
		this.s_branch = s_branch;
	}
	/**
	 * @return Returns the s_compName.
	 */
	public String getS_compName() {
		return s_compName;
	}
	/**
	 * @param name The s_compName to set.
	 */
	public void setS_compName(String name) {
		s_compName = name;
	}
	/**
	 * @return Returns the s_designation.
	 */
	public String getS_designation() {
		return s_designation;
	}
	/**
	 * @param s_designation The s_designation to set.
	 */
	public void setS_designation(String s_designation) {
		this.s_designation = s_designation;
	}
	/**
	 * @return Returns the s_email.
	 */
	public String getS_email() {
		return s_email;
	}
	/**
	 * @param s_email The s_email to set.
	 */
	public void setS_email(String s_email) {
		this.s_email = s_email;
	}
	/**
	 * @return Returns the s_firstName.
	 */
	public String getS_firstName() {
		return s_firstName;
	}
	/**
	 * @param name The s_firstName to set.
	 */
	public void setS_firstName(String name) {
		s_firstName = name;
	}
	/**
	 * @return Returns the s_lastName.
	 */
	public String getS_lastName() {
		return s_lastName;
	}
	/**
	 * @param name The s_lastName to set.
	 */
	public void setS_lastName(String name) {
		s_lastName = name;
	}
	/**
	 * @return Returns the s_location.
	 */
	public String getS_location() {
		return s_location;
	}
	/**
	 * @param s_location The s_location to set.
	 */
	public void setS_location(String s_location) {
		this.s_location = s_location;
	}
	/**
	 * @return Returns the s_middleName.
	 */
	public String getS_middleName() {
		return s_middleName;
	}
	/**
	 * @param name The s_middleName to set.
	 */
	public void setS_middleName(String name) {
		s_middleName = name;
	}
	/**
	 * @return Returns the s_mobileNumber.
	 */
	public String getS_mobileNumber() {
		return s_mobileNumber;
	}
	/**
	 * @param number The s_mobileNumber to set.
	 */
	public void setS_mobileNumber(String number) {
		s_mobileNumber = number;
	}
	/**
	 * @return Returns the s_password.
	 */
	public String getS_password() {
		return s_password;
	}
	/**
	 * @param s_password The s_password to set.
	 */
	public void setS_password(String s_password) {
		this.s_password = s_password;
	}
	/**
	 * @return Returns the s_phoneNumber.
	 */
	public String getS_phoneNumber() {
		return s_phoneNumber;
	}
	/**
	 * @param number The s_phoneNumber to set.
	 */
	public void setS_phoneNumber(String number) {
		s_phoneNumber = number;
	}
	/**
	 * @return Returns the s_userName.
	 */
	public String getS_userName() {
		return s_userName;
	}
	/**
	 * @param name The s_userName to set.
	 */
	public void setS_userName(String name) {
		s_userName = name;
	}
	
	/**
	 * 
	 * @author Administrator
	 *
	 * getUserDetail gives the user details
	 * @return CUser
	 */
	public CUser getUserDetail(String userName){
		CUser user = new CUser();
		return  user;
	}
	/**
	 * @return Returns the role.
	 */
	public CRole getRole() {
		return role;
	}
	/**
	 * @param role The role to set.
	 */
	public void setRole(CRole role) {
		this.role = role;
	}
}
