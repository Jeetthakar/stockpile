package harrier.com.menu;
import java.util.ArrayList;

public class Activity {

	private String Activity_Name;
	private String Activity_Url;
	private String Activity_Id;
	private String Menu_Id;
	private String SubMenu_Id;
	private ArrayList activityList; 
	
	public String getActivity_Id() {
		return Activity_Id;
	}
	public void setActivity_Id(String activity_Id) {
		Activity_Id = activity_Id;
	}
	public String getActivity_Name() {
		return Activity_Name;
	}
	public void setActivity_Name(String activity_Name) {
		Activity_Name = activity_Name;
	}
	public String getActivity_Url() {
		return Activity_Url;
	}
	public void setActivity_Url(String activity_Url) {
		Activity_Url = activity_Url;
	}
	public ArrayList getActivityList() {
		return activityList;
	}
	public void setActivityList(ArrayList activityList) {
		this.activityList = activityList;
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
	
}
