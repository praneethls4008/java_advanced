package sort.comparable;

public class Car extends Vehicle{
    
    private String model;
    private int year;
    

    public Car(String brand, String model, int year, double price) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
    }
    
 // Override: compare by year, then price
    @Override
    public int compareTo(Vehicle other) {
    	if (other instanceof Car) {
            Car o = (Car) other;
            int yearCmp = Integer.compare(this.year, o.year);
            if (yearCmp != 0) return yearCmp;
            return Double.compare(this.price, o.price);
        }
        return super.compareTo(other); // fallback to price
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", price=" + price +
                '}';
    }
}
