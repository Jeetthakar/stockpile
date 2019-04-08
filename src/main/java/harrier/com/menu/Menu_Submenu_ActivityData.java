package harrier.com.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.harrier.initializeation.ConnectInit;

import app.Connect;
import app.LogonAction;

public class Menu_Submenu_ActivityData {			
	
	Connect c = ConnectInit.getConnect();
	Connection con = null;
//	Properties queries;
	String Query;
	Logger log;
	
	public ArrayList getMenuCollection(String role_Id) throws Exception {
		ArrayList menuList = new ArrayList();		
		PreparedStatement pst = null;
		ResultSet rst = null;
		log = Logger.getLogger(LogonAction.class.getName());
		
		try{				
			//StringBuffer menuBuffer = new StringBuffer();
		//	c = ConnectInit.getConnect();
		//	queries = c.queries;	
			Query =ConnectInit.queries.getProperty("menu_query");
			
			con = c.getdbConnection();
			//menuBuffer.append("select menu_id,menu_name,haslink,url,menu.activity_id from menu_master menu,roles_activities rolact where menu.activity_id = rolact.activity_id and role_id\=?  order by menu_id asc");
			
			pst = con.prepareStatement(Query);
			pst.setInt(1,Integer.parseInt(role_Id));
			//getInfo(" sb : "+Query);
			rst = pst.executeQuery();
			Menu menu = null;
			
			while (rst!= null && rst.next())
			{		
				
				menu  = new Menu();		
				menu.setMenu_Id(rst.getString("menu_id"));
				menu.setMenu_Name(rst.getString("menu_name"));
				menu.setMenu_Role(role_Id);
				
				// if menu has link 
				if(rst.getBoolean("haslink")==true){										
					menu.setMenu_Url(rst.getString("url"));					
					menu.setSubMenuList(null);
				}
				else{					
					menu.setMenu_Url(null);
					menu.setSubMenuList(getSubMenuCollection(rst.getString("menu_id"),role_Id));										
					
				}				
				menuList.add(menu);
			}
		}
		catch(Exception e){
			log.debug(" Exception while retrieving menus for roleId "+role_Id+e);
		}
		
		finally{			
			if(rst!=null)
				rst.close();
			if(pst!=null)
				pst.close();
			if(con!=null)
				con.close();
			
		}
	
		return menuList;
	}
	
	public ArrayList getSubMenuCollection(String menu_Id, String role_Id) throws Exception {
		
		ArrayList subMenuList = new ArrayList();
		Connection con1 = null;
		PreparedStatement pst1 = null;
		ResultSet rst1 = null;
		//Connect c ;
		
		try{
			//StringBuffer subMenuBuffer = new StringBuffer();
			//c = new Connect();
	//		queries = c.queries;
			Query = ConnectInit.queries.getProperty("submenu_query");			
			con1 = c.getdbConnection();
			
			/*subMenuBuffer.append("select submenu_id,submenu_name,menu_id,haslink,url from submenu_master submenu,roles_activities rolact where submenu.activity_id = rolact.activity_id and role_id = '"+role_Id+"'"+ 
									" and menu_id ='"+menu_Id+"' order by submenu_order asc");*/
			//subMenuBuffer.append("select submenu_id,submenu_name,menu_id,haslink,url from submenu_master submenu,roles_activities rolact where submenu.activity_id = rolact.activity_id and role_id=?"+ 
			//		" and menu_id=? order by submenu_order asc");
			pst1 = con.prepareStatement(Query);			
			pst1.setInt(1,Integer.parseInt(role_Id));
			pst1.setString(2,menu_Id);
			//getInfo(" sb : "+Query);
			rst1 = pst1.executeQuery();
						
			SubMenu submenu  = null;
			
			while (rst1!= null && rst1.next())
			{				
				submenu = new SubMenu();
				submenu.setMenu_Id(rst1.getString("menu_id"));
				submenu.setSubMenu_Id(rst1.getString("submenu_id"));
				submenu.setSubMenu_Name(rst1.getString("submenu_name"));
				
				// if submenu has link 
				if(rst1.getBoolean("haslink")==true){
					
					submenu.setSubMenu_Url(rst1.getString("url"));					
					submenu.setActivityList(null);
				}
				else{					
					submenu.setSubMenu_Url(null);
					submenu.setActivityList(getActivityCollection(menu_Id,rst1.getString("submenu_id"),role_Id));									
					
				}	
				subMenuList.add(submenu);
			}					
		}
		catch(Exception ex){
			log.debug(" Exception while retrieving submenus for menuId "+menu_Id+ex);
		}
		finally{			
			if(rst1!=null)
				rst1.close();
			if(pst1!=null)
				pst1.close();
			if(con1!=null)
				con1.close();			
		}		
		return subMenuList;
	}
	
	public ArrayList getActivityCollection(String menu_Id, String subMenu_Id,String role_Id) throws Exception {		
		ArrayList ActivityList = new ArrayList();
		Connection con2 = null;
		PreparedStatement pst2 = null;
		ResultSet rst2 = null;
		//Connect c ;
		
		try{
			//StringBuffer ActivityBuffer = new StringBuffer();
			//c = new Connect();
		//	queries = c.queries;			
			Query = ConnectInit.queries.getProperty("activity_query");
			con2 = c.getdbConnection();
			//ActivityBuffer.append("select act_map.act_menu_submenu_id,activity_name,menu_id,submenu_id,url,act_menu_submenu_order from activity_menu_submenu_mapping as act_map,roles_activities rolact, activities"+ 
				//					" where act_map.act_menu_submenu_id = rolact.activity_id"+ 
				//					" and rolact.activity_id = activities.activity_id"+
				//					" and role_id ='"+role_Id+"'"+
				//					" and menu_id ='"+menu_Id+"' and submenu_id = '"+subMenu_Id+"' order by act_menu_submenu_order asc");
			
			pst2 = con.prepareStatement(Query);
			pst2.setInt(1,Integer.parseInt(role_Id));
			pst2.setString(2,menu_Id);
			pst2.setString(3,subMenu_Id);
			//getInfo(" sb : "+Query);
			rst2 = pst2.executeQuery();					
			
			Activity activity = null; 
			
			while (rst2!= null && rst2.next())
			{				
				activity = new Activity();
				activity.setActivity_Id(rst2.getString("act_menu_submenu_id"));
				activity.setActivity_Name(rst2.getString("activity_name"));
				//activity.setActivity_Url(rst.getString("url"));
				
				// if submenu has link												
				activity.setActivity_Url(rst2.getString("url"));																	
				ActivityList.add(activity);
			}					
		}
		catch(Exception ex){
			log.debug(" Exception while retrieving activities for menuId "+menu_Id+ "  Sub menu id :"+subMenu_Id+ex);
		}
		finally{			
			if(rst2!=null)
				rst2.close();
			if(pst2!=null)
				pst2.close();
			if(con2!=null)
				con2.close();
			
		}
		return ActivityList;
	}
	
	/*public static void main (String args[]) throws Exception {
		Menu menu;
		SubMenu submenu;
		Activity activity;
		Menu_Submenu_ActivityData menu_sub_act = new Menu_Submenu_ActivityData();
		ArrayList menusubact = menu_sub_act.getMenuCollection("1");
		for (int i = 0;i< menusubact.size();i++){	
			menu = (Menu)menusubact.get(i);			 
			Logging.debug(menu.getMenu_Id() +"  "+ menu.getMenu_Name()+"  "+ menu.getMenu_Url());					
			
			for (int j=0; j<menu.getSubMenuList().size();j++){
				submenu = (SubMenu)menu.getSubMenuList().get(j);
				Logging.debug(submenu.getSubMenu_Id() +"  "+ submenu.getSubMenu_Name()+"  "+ submenu.getSubMenu_Url());
				
				for(int k = 0;k<submenu.getActivityList().size();k++){			
					
					activity =(Activity)submenu.getActivityList().get(k);
					Logging.debug(activity.getActivity_Id() +"  "+ activity.getActivity_Name()+"  "+ activity.getActivity_Url());
				}								
			}				
		}
	}*/
}

