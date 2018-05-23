package task;

public class  Person extends Registry 
{
	 private String name;
	 private String surname;
	 private String adress;
	 private fullNumber phoneNr;

	 public Person(String name, String surname, String adress, fullNumber phoneNr)
	 {
		 this.name = name;
		 this.surname = surname;
		 this.adress = adress;
		 this.phoneNr = phoneNr;
	 }

	 public String getName() 
	 {
		 return name;
	 }

	 public String getSurname() 
	 {
		 return surname;
	 }

	 public String getAddress() 
	 {
		 return adress;
	 }

	 @Override
	 public fullNumber getPhoneNumber() 
	 {
		 return phoneNr;
	 }

	 @Override
	 public void description() 
	 {
		 System.out.println("Full name: " + getName() + " " + getSurname());
		 System.out.println("Adress: " + getAddress());
		 System.out.println("Phone number: +" + getPhoneNumber().partCode(null) + " " + getPhoneNumber().partNumber() + "\n");	
	 }
}