package harrier.com.menu;
import java.util.ArrayList; 

public class Menu {
	
	private String Menu_Id;
	private String Menu_Name;
	private String Menu_Role;
	private String Menu_Url;
	private ArrayList SubMenuList;
	
	
	public String getMenu_Id() {
		return Menu_Id;
	}
	public void setMenu_Id(String menu_Id) {
		Menu_Id = menu_Id;
	}
	public String getMenu_Name() {
		return Menu_Name;
	}
	public void setMenu_Name(String menu_Name) {
		Menu_Name = menu_Name;
	}
	public String getMenu_Role() {
		return Menu_Role;
	}
	public void setMenu_Role(String menu_Role) {
		Menu_Role = menu_Role;
	}
	public ArrayList getSubMenuList() {
		return SubMenuList;
	}
	public void setSubMenuList(ArrayList subMenuList) {
		SubMenuList = subMenuList;
	}
	public String getMenu_Url() {
		return Menu_Url;
	}
	public void setMenu_Url(String menu_Url) {
		Menu_Url = menu_Url;
	}
	
	
	

}
