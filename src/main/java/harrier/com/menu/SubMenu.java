package harrier.com.menu;

import java.util.ArrayList;

public class SubMenu {

	private String Menu_Id;
	private String SubMenu_Id;
	private String SubMenu_Name;
	private String SubMenu_Url;
	private ArrayList ActivityList;
	
	public ArrayList getActivityList() {
		return ActivityList;
	}
	public void setActivityList(ArrayList activityList) {
		ActivityList = activityList;
	}
	public String getMenu_Id() {
		return Menu_Id;
	}
	public void setMenu_Id(String menu_Id) {
		Menu_Id = menu_Id;
	}
	public String getSubMenu_Id() {
		return SubMenu_Id;
	}
	public void setSubMenu_Id(String subMenu_Id) {
		SubMenu_Id = subMenu_Id;
	}
	public String getSubMenu_Name() {
		return SubMenu_Name;
	}
	public void setSubMenu_Name(String subMenu_Name) {
		SubMenu_Name = subMenu_Name;
	}
	public String getSubMenu_Url() {
		return SubMenu_Url;
	}
	public void setSubMenu_Url(String subMenu_Url) {
		SubMenu_Url = subMenu_Url;
	}
	
}
