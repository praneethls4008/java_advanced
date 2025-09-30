package sort.comparable;

public class Bike extends Vehicle{

    private String type;  // sports, cruiser, commuter
    private int cc;
    

    public Bike(String brand, String type, int cc, double price) {
        this.brand = brand;
        this.type = type;
        this.cc = cc;
        this.price = price;
    }
    
 // Override: compare by cc, then price
    @Override
    public int compareTo(Vehicle other) {
    	if (other instanceof Bike) {
            Bike o = (Bike) other;
            int ccCmp = Integer.compare(this.cc, o.cc);
            if (ccCmp != 0) return ccCmp;
            return Double.compare(this.price, o.price);
        }
        return super.compareTo(other); // fallback to price
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
