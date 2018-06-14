package taskDatabase;

public class Record 
{
	private String type;
	private int serialNumber; //tylko 6 cyfr
	private String manufacturer;
	private int productionYear;
	private String mechanics;
	private double valuation;
	//private String description;
	
	/*<------------------CONSTRUSTORS------------------>*/
	public Record() {
		this.type = "Unknown";
		this.serialNumber = 0;
		this.manufacturer = "Unknown";
		this.productionYear = 0;
		this.mechanics= "Unknown";
		this.valuation = 0;
		//description;
	}
	public Record(String type, int serialNumber, String manufacturer, int productionYear, String mechanics,/* String description,*/ double valuation) 
	{
		this.setType(type);
		this.setSerialNumber(serialNumber);
		this.setManufacturer(manufacturer);
		this.setProductionYear(productionYear);
		this.setMechanics(mechanics);
		this.setValuation(valuation);
	}
	public Record(String string)
	{
		String[] parts = string.split("[ ]");
	
		this.type = parts[0];
		this.serialNumber = Integer.parseInt(parts[1]);
		this.manufacturer = parts[2];
		this.productionYear = Integer.parseInt(parts[3]);
		this.mechanics= parts[4];
		this.valuation = Double.parseDouble(parts[5]);
		//description
		
	}
	/*<------------------GETTERS AND SETTERS------------------>*/
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public int getProductionYear() {
		return productionYear;
	}
	public void setProductionYear(int productionYear) {
		this.productionYear = productionYear;
	}
	public String getMechanics() {
		return mechanics;
	}
	public void setMechanics(String mechanics) {
		this.mechanics = mechanics;
	}
	public double getValuation() {
		return valuation;
	}
	public void setValuation(double valuation) {
		this.valuation = valuation;
	}
	/*<------------------TOSTRING OVERRIDE------------------>*/
	@Override
	public String toString()
	{
		return this.getType()+" "+String.format("%06d", this.getSerialNumber())+" "+this.getManufacturer()+" "+this.getProductionYear()+" "+this.getMechanics()+" "+this.getValuation()+"$";	
	}
	
}
