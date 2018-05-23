package task;

import java.util.*;

public class ex_6 {

	public static void main(String[] args) 
	{
		
		 	Registry company1 = new Company("First", "Dabrowskiego 20", new fullNumber("48", "111-111-111"));
	        Registry company2 = new Company("Second", "GdziestamwLodzi", new fullNumber("48", "222 222 222"));
	        Registry company3 = new Company("Third", "Hendaleko", new fullNumber("48", "333444555"));

	        Registry person1 = new Person("Antoni", "Urbaniak", "Dluga 125", new fullNumber("48", "322-223-344"));
	        Registry person2 = new Person("Agnieszka", "Jadczak", "Prusa 34", new fullNumber("48", "111-443-112"));
	        Registry person3 = new Person("Justyna", "Franczak", "Konstantynowska 44", new fullNumber("48", "096-212-545"));
	        

	        TreeMap<fullNumber, Registry> phoneBook = new TreeMap<fullNumber, Registry>(); //<K, V> K - the type of keys maintained by this map; V - the type of mapped values;
	        //populating tree map
	        phoneBook.put(company1.getPhoneNumber(), company1);
	        phoneBook.put(company2.getPhoneNumber(), company2);
	        phoneBook.put(company3.getPhoneNumber(), company3);
	        phoneBook.put(person1.getPhoneNumber(), person1);
	        phoneBook.put(person2.getPhoneNumber(), person2);
	        phoneBook.put(person3.getPhoneNumber(), person3);
	        
	        //get a set of entries
	        Set set = phoneBook.entrySet();
	        
	        //get an iterator
	        Iterator i = set.iterator();
	        
	        //display
	        while(i.hasNext()) 
	        {
	            Map.Entry<fullNumber, Registry> map = (Map.Entry<fullNumber, Registry>)i.next();
	            map.getValue().description();
	        }
	    
	}

}



