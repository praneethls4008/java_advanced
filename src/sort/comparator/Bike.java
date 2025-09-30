package sort.comparator;

public class Bike{

    private String type;  // sports, cruiser, commuter
    private int cc;
    private String brand;
    private double price;

    public Bike(String brand, String type, int cc, double price) {
        this.brand = brand;
        this.type = type;
        this.cc = cc;
        this.price = price;
    }
    
    

    public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public int getCc() {
		return cc;
	}



	public void setCc(int cc) {
		this.cc = cc;
	}



	public String getBrand() {
		return brand;
	}



	public void setBrand(String brand) {
		this.brand = brand;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	@Override
    public String toString() {
        return "Bike{" +
                "brand='" + brand + '\'' +
                ", type='" + type + '\'' +
                ", cc=" + cc +
                ", price=" + price +
                '}';
    }
}
