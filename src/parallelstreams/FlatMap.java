package parallelstreams;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import datasource.generate.ListOfModels;
import datasource.model.Book;
import datasource.model.Customer;
import datasource.model.Item;
import datasource.model.Student;

public class FlatMap {

	public static void flatMapBasic() {
		List<Book> finalList = ListOfModels.getLibraries()
				.stream()
				.flatMap(library -> library.getBooks().stream())
				.collect(Collectors.toList());
			
			System.out.println(finalList);
			
			List<String> inpList = new ArrayList<>();
			inpList.add("hello java");
			inpList.add("hello python");
			inpList.add("hello c");
			inpList.add("hello js");
			
			List<String> outLstMap = inpList.stream()
				.map(str -> Arrays.asList(str.split(" ")))
				.collect(ArrayList::new, ArrayList::addAll, (l, r)-> l.addAll(r));
			
			List<String> outLstFlatMap = inpList.stream()
					.flatMap(str -> Arrays.asList(str.split(" ")).stream())
					.collect(Collectors.toList());
			
			System.out.println("map: " + outLstMap);
			System.out.println("flatmap: " + outLstFlatMap);
	}
	
	public static void flatMapRealLifeEcommerceExp() {
		
		/*Concept	map()	flatMap()
			*	Output type	Stream<List<Order>>	Stream<Order>
			*	Nesting	Nested (requires flattening later)	Flat (ready-to-use)
			*	Readability	Harder to reason about	Natural, expressive
			*	Filtering capability	Must go inside substreams	Direct on orders or items
			*	Real-world usage	Rarely ideal for hierarchies	Industry-standard for multi-level data
		*/
		
		 // Each Customer contains nested data: Orders -> Items
	    List<Customer> customers = ListOfModels.genrateCustomersData();

	    // Step 2️⃣ : Build a flat stream pipeline
	    List<Item> recentItems = customers.stream().parallel()

	        // 1️⃣ Flatten Customer -> Orders
	        // getOrders() returns List<Order> for each Customer.
	        // Using flatMap ensures all orders from all customers
	        // are merged into one continuous Stream<Order>.
	        .flatMap(customer -> customer.getOrders().stream())

	        // 2️⃣ Filter only recent orders (last 7 days)
	        // Filtering happens now on the flat Stream<Order>,
	        // directly accessible because of previous flatMap.
	        .filter(order -> order.getDate().isAfter(LocalDate.now().minusDays(7)))

	        // 3️⃣ Flatten Orders -> Items
	        // Each Order contains List<Item>.
	        // Again, flatMap merges all those item lists into a single Stream<Item>.
	        .flatMap(order -> order.getItems().stream())

	        // 4️⃣ Collect all items into a final list
	        .collect(Collectors.toList());

	    // Step 3️⃣ : Print results
	    System.out.println("\n🛒 Customers → Orders → Filter Recent → Items");
	    System.out.println("--------------------------------------------------");
	    System.out.println("Items purchased in last 7 days:");
	    recentItems.forEach(System.out::println);
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		flatMapRealLifeEcommerceExp();
	}
	
}
