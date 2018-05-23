package task;

public class Company extends Registry 
{
	private String cname;
    private String cadress;
    private fullNumber cphoneNr;

    public Company(String cname, String cadress, fullNumber cphoneNr) 
    {
        this.cname = cname;
        this.cadress = cadress;
        this.cphoneNr = cphoneNr;
    }

    public String getCompanyName() 
    {
        return cname;
    }

    public String getAddress() 
    {
        return cadress;
    }
    
    @Override
    public fullNumber getPhoneNumber() 
    {
        return cphoneNr;
    }

    @Override
    public void description() 
    {
        System.out.println("Company name: " + getCompanyName());
        System.out.println("Address of company: " + getAddress());
        System.out.println("Phone number: +" + getPhoneNumber().partCode(null) + " " + getPhoneNumber().partNumber() + "\n");
    }

}
