package task;

public  class fullNumber implements Comparable<fullNumber>
{
	private String code;
	private String number;
	
	public String partCode(Object newParam) //Object newParam so u can call .mtdCode(null)
	{
		 return code;
	}

	public String partNumber()
	{	
		 return number;
	}
	
	public fullNumber(String code, String number) 
	{
        this.code = code;
        this.number = number;
	}
	
	@Override
	public int compareTo(fullNumber nr) 
	{
		return number.compareTo(nr.number);
	}
	
	@Override
	public String toString() 
	{
		return "+" + code + " " + number;
	}
	

}