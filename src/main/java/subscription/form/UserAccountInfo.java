package subscription.form;

public class UserAccountInfo {
private String subname;
private String subid;
private String subdate;
private String validfor;
private String vailidity;
private String Charges;

public String getCharges() {
	return Charges;
}
public void setCharges(String charges) {
	Charges = charges;
}
public String getSubdate() {
	return subdate;
}
public void setSubdate(String subdate) {
	this.subdate = subdate;
}
public String getSubid() {
	return subid;
}
public void setSubid(String subid) {
	this.subid = subid;
}
public String getSubname() {
	return subname;
}
public void setSubname(String subname) {
	this.subname = subname;
}
public String getValidfor() {
	return validfor;
}
public void setValidfor(String validfor) {
	this.validfor = validfor;
}
public String getVailidity() {
	return vailidity;
}
public void setVailidity(String vailidity) {
	this.vailidity = vailidity;
}
	
}
