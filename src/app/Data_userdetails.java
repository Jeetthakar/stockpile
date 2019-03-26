/*
 * Created on Jun 17, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package app;


/**
 * @author W
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Data_userdetails {
	public  String userid,password,firstname,middlename,lastname,dob,address1;
	public String address2,city,country,designation,sequrityque,answer,zipcode,phone,mobile,branch,email,clientname;
	public  char gender;
	
	/* public String validate(Data_userdetails d,ActionErrors e)
		{
			boolean b;
			ActionErrors errors = new ActionErrors();
			userid=d.userid;
			password=d.password;
			firstname=d.firstname;
			middlename=d.middlename;
			lastname=d.lastname;
			dob=d.dob;
			address1=d.address1;
			address2=d.address2;
			city=d.city;
			language=d.language;
			country=d.country;
			designation=d.designation;
			sequrityque=d.sequrityque;
			answer=d.answer;
			gender=d.gender;
			zipcode=d.zipcode;
			phone=d.phone;
			mobile=d.mobile;
			
			if(userid.length()<6 )
			{
				errors.add("id",
								new ActionError("error.id.less"));

				str=str.concat("User Id should be of atleast 6 characters #");
			}


		if(userid.length()>10 )
		{
			str=str.concat("User Id should be of atmost 10 characters#");
		}
		if(userid.equalsIgnoreCase(password))
		{
			str=str.concat("User Id and Pasword Should be different #");
		}
		if(password.length()<6 )
		{
			str=str.concat("Password should be of atleast 6 characters#");
		}
		if(password.length()>10 )
		{
			str=str.concat("Password  should be of atmost 10 characters#");
		}
		if(checkwhitespace(userid))
		{
			str=str.concat("User Id should contain at least one White space character#");
		}
		if(checkwhitespace(password))
		{
			str=str.concat("Password should contain at least One White space character#");
		}
		if(Character.isLetterOrDigit(userid.charAt(0)) )
		{}
		else
		{
			str=str.concat("User Id should beginwith alpha numeric characters#");
		}
		if(Character.isLetterOrDigit(password.charAt(0)))
		{}
		else
		{
			str=str.concat("Password should beginwith alpha numeric characters#");
		}
		if(sequrityque.trim().equals(""))
		{
					str=str.concat("Please Select a A Proper Sequrity Question#");
		}
		if(answer.trim().equals(""))
		{
			str=str.concat("Answer field should not be Empty#");
		}
		if(dob==null ||dob.trim().equals(""))
		{
			str=str.concat("Please Fill a Valid Entry from the Date of Birth field #");
		}
		
		if(firstname.trim().equals(""))
		{
			str=str.concat("First Name field should not be blank#");
		}
		if(invalid(firstname))
		{
			str=str.concat("First Name field seams to be invalid#");
		}
		if(middlename.trim().equals(""))
		{
			str=str.concat("Middle Name field should not be blank#");
		}
		if(invalid(middlename))
		{
			str=str.concat("Middle Name field seams to be invalid#");
		}
		if(lastname.trim().equals(""))
		{
			str=str.concat("Last Name field should not be blank#");
		}
		if(invalid(lastname))
		{
			str=str.concat("Last Name field seams to be invalid#");
		}
		if(language.trim().equals(""))
		{
			str=str.concat("Please Fill a Valid Entry in the language field#");
		}
		if(address1.trim().equals(""))
		{
			str=str.concat("Address1 field should not be blank#");
		}
		if(city.trim().equals(""))
		{
			str=str.concat("Please Fill a Valid Entry in the City field #");
		}
		if(zipcode.trim().equals(""))
		{
			str=str.concat("Zipcode field should not be blank#");
		}
		if(invalidint(zipcode))
		{
					str=str.concat("Zipcode  seams to be invalid#");
		}
		if(invalidint(phone))
		{
							str=str.concat("Phone Number  seams to be invalid#");
		}
		if(invalidint(mobile))
		{
			str=str.concat("mobile Number  seams to be invalid#");
		}
		if(country.trim().equals(""))
		{
			str=str.concat("Please Fill a Valid Entry from the Country field #");
			}
		
			return str;
		}
		
		
		public  boolean checkwhitespace(String local)
		{
			int length=local.length();
			boolean b=true;char ch;
			char [] charr=new char[length];
			charr=local.toCharArray();
			for(int i=0;i<length;i++)
			{
				ch=charr[i];
				if(ch=='@'||ch=='_'||ch=='*'||ch=='#'||ch=='$'||ch=='%'||ch=='^'||ch=='~'||ch=='&'||ch=='?')
				{
					b=false; break;
				}
			}
			return b;
		}
		
		
		
		
		public  boolean invalid(String local)
			{
				int length=local.length();
				boolean b=false;
				char [] charr=new char[length+1];
				charr=local.toCharArray();
				for(int i=0;i<length;i++)
				{
					if(Character.isDigit(charr[i]))
					 {b=true;
						break;
					 }
		
				}
				return b;
		}
		
		public  boolean invalidint(String local)
				{
					int length=local.length();
					boolean b=false;
					char [] charr=new char[length+1];
					charr=local.toCharArray();
					for(int i=0;i<length;i++)
					{
						if(Character.isLetter(charr[i]))
						 {b=true;
							break;
						 }
		
					}
					return b;
		}
*/
}
