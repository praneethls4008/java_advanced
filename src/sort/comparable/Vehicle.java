package sort.comparable;

public class Vehicle implements Comparable<Vehicle>{
    String brand;
    double price;
    
	@Override
	public int compareTo(Vehicle vehicle2) {
		if(this.price > vehicle2.price) {
			return 1; //sort
		}else if(this.price < vehicle2.price){
			return -1;//dont sort
		}else {
			return 0;//dont sort or equal
		} 
	}
    
    
}
