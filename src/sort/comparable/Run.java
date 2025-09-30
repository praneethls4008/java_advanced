package sort.comparable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * 
 * Comparable vs Comparator in Java
1. Comparable

Interface: Comparable<T>

Package: java.lang

Method to implement:

int compareTo(T other);


Purpose: Defines the natural ordering of objects.

Where defined: Inside the class itself.

Example:

class Car implements Comparable<Car> {
    private String brand;
    private double price;

    public Car(String brand, double price) {
        this.brand = brand;
        this.price = price;
    }

    public double getPrice() { return price; }

    @Override
    public int compareTo(Car other) {
        return Double.compare(this.price, other.price); // natural order by price
    }
}


Usage:

List<Car> cars = ...
Collections.sort(cars); // uses compareTo()

2. Comparator

Interface: Comparator<T>

Package: java.util

Method to implement:

int compare(T o1, T o2);


Purpose: Defines a custom ordering, independent of the class.

Where defined: As a separate class / anonymous class / lambda.

Example:

Comparator<Car> sortByBrand = new Comparator<>() {
    @Override
    public int compare(Car c1, Car c2) {
        return c1.getBrand().compareTo(c2.getBrand());
    }
};

Collections.sort(cars, sortByBrand);


Or with lambda:

cars.sort((c1, c2) -> c1.getBrand().compareTo(c2.getBrand()));

⚖️ Key Differences
Feature	Comparable	Comparator
Interface	Comparable<T>	Comparator<T>
Method	compareTo(T o)	compare(T o1, T o2)
Belongs to	The class itself (modifies it)	Separate object (external)
Natural vs Custom	Defines natural order	Defines custom order(s)
Flexibility	Only one ordering possible	Can have multiple orderings
Package	java.lang	java.util
Example Use	Collections.sort(list)	Collections.sort(list, comparator)
🚦 When to Use Which?
✅ Use Comparable when:

The class has a natural, default ordering (e.g., Integer, String, Date).

You expect sorting in only one way most of the time.

Example: Sorting students by roll number.

class Student implements Comparable<Student> {
    int rollNo;
    String name;
    public int compareTo(Student o) {
        return Integer.compare(this.rollNo, o.rollNo);
    }
}

✅ Use Comparator when:

You want multiple ways to sort.

You cannot modify the class (e.g., library classes).

Example: Sorting students by name, then marks, then age.
 */

public class Run {
	
	public static void sortList() {
		List<Car> cars = Arrays.asList(
                new Car("Toyota", "Corolla", 2020, 18000),
                new Car("Honda", "Civic", 2021, 22000),
                new Car("Ford", "Mustang", 2019, 35000),
                new Car("Tesla", "Model 3", 2022, 40000),
                new Car("BMW", "X5", 2021, 60000),
                new Car("Audi", "A4", 2020, 42000),
                new Car("Mercedes", "C-Class", 2019, 45000),
                new Car("Hyundai", "Elantra", 2021, 20000),
                new Car("Kia", "Seltos", 2022, 25000),
                new Car("Volkswagen", "Passat", 2020, 28000)
        );

        List<Bike> bikes = Arrays.asList(
                new Bike("Yamaha", "Sports", 600, 12000),
                new Bike("Honda", "Commuter", 150, 2000),
                new Bike("Royal Enfield", "Cruiser", 350, 2500),
                new Bike("Kawasaki", "Sports", 1000, 15000),
                new Bike("Ducati", "Sports", 1200, 20000),
                new Bike("Harley-Davidson", "Cruiser", 750, 18000),
                new Bike("Suzuki", "Sports", 250, 4000),
                new Bike("Bajaj", "Commuter", 125, 1500),
                new Bike("TVS", "Commuter", 110, 1200),
                new Bike("KTM", "Sports", 390, 5500)
        );
        
        
        Collections.sort(cars);
        System.out.println("Cars sorted by price:");
        cars.forEach(System.out::println);

        Collections.sort(bikes);
        System.out.println("\nBikes sorted by price:");
        bikes.forEach(System.out::println);

	}

	public static void main(String[] args) {
		sortList();
	}
}
