package streams;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import datasource.generate.ListOfModels;
import datasource.model.Customer;
import datasource.model.Item;
import datasource.model.Order;
import datasource.model.Student;

public class CollectorsStream {

	record Age_Attendance_Key(int age, int attendance) {};
	
	public static void basicCollectors() {
		System.out.println("- - - - - - - - - - - - - - - - - - - - Basic collectors- - - - - - - - - - - - - - - - - - - - ");
		
		List<String> toListResult = ListOfModels.genrateCustomersData().stream()
				.map(customer -> customer.getName())
				.collect(java.util.stream.Collectors.toList());
		System.out.println("\tTo List(Customer names): " + toListResult);
		
		Set<String> toSetResult = ListOfModels.genrateCustomersData().stream()
				.map(customer -> customer.getName())
				.collect(java.util.stream.Collectors.toSet());
		System.out.println("\tTo Set(Customer names): " + toSetResult);
		
		Map<String, Integer> toMapResult = ListOfModels.genrateCustomersData().stream()
				.flatMap(customer -> customer.getOrders().stream())
				.flatMap(order -> order.getItems().stream())
				.collect(Collectors.toMap(
						item -> item.getName(), // ===> Item::getName  => key
						item -> 1, // => value
						(oldVal, newVal) -> oldVal+newVal // ===> Integer::Sum => merger
						));
				
		System.out.println("\tTo Map(Customer order items name count): " + toMapResult);
		
		
		/*
		 * ✅ Java 9+ — List.of(...) returns an immutable list.
		 * 			List<String> names = List.of("A", "B", "C");
		 * 
		 * ✅ Java 8 and below — via Collections.unmodifiableList()
		 * 			List<String> list = new ArrayList<>();
		 * 			List<String> immutableList = Collections.unmodifiableList(list);
		 */
		List<String> unmodifiableListResult = ListOfModels.genrateCustomersData().stream()
				.map(customer -> customer.getName())
				.collect(java.util.stream.Collectors.toUnmodifiableList());
		//unmodifiableListResult.add("eew"); //doesn't work throws exception
		System.out.println("\tTo List(Customer names): " + unmodifiableListResult);
		
		Set<String> unmodifiableSetResult = ListOfModels.genrateCustomersData().stream()
				.map(customer -> customer.getName())
				.collect(java.util.stream.Collectors.toUnmodifiableSet());
		System.out.println("\tTo Set(Customer names): " + unmodifiableSetResult);

	}
	
	public static void groupBy_KeysMapping() {
		System.out.println("- - - - - - - - - - - - - - - - - - - - Grouping- - - - - - - - - - - - - - - - - - - - ");
		
		Map<LocalDate, List<Order>> groupByResult1 = ListOfModels.genrateCustomersData().stream()
				.flatMap(customer -> customer.getOrders().stream())
				.collect(Collectors.groupingBy(Order::getDate));
		System.out.println("\tGroupBy single column/field ->  key(date)" + groupByResult1);
		
		
		Map<Age_Attendance_Key, List<Student>> groupByResult2 = ListOfModels.getStudents().stream()
				.collect(Collectors.groupingBy(
						(student) -> new Age_Attendance_Key(student.getAge(), student.getAttendance())
						));
		System.out.println("\tGroupBy multiple columns/feilds/record/tuble -> key(age,attendance)" + groupByResult2);
	}

	
	public static void groupBy_ValuesMapping() {
		System.out.println("- - - - - - - - - - - - - - - - - - - - Grouping- - - - - - - - - - - - - - - - - - - - ");
		
		Map<Integer, Set<Student>> groupBySetResult1 = ListOfModels.getStudents().stream()
				.collect(
						Collectors.groupingBy(
								Student::getAttendance,
								Collectors.toSet() // by default List
								)
							);
		System.out.println("\tGroupBy 2nd Arg return type Set" + groupBySetResult1);
		
		
		Map<Age_Attendance_Key, List<Student>> groupByResult2 = ListOfModels.getStudents().stream()
				.collect(Collectors.groupingBy(
						(student) -> new Age_Attendance_Key(student.getAge(), student.getAttendance())
						));
		System.out.println("\tGroupBy multiple columns/feilds/record/tuble -> key(age,attendance)" + groupByResult2);
	}
	
	public static void partitionBy() {
		System.out.println("- - - - - - - - - - - - - - - - - - - - partitioning- - - - - - - - - - - - - - - - - - - - ");
		
		List<String> toListResult = ListOfModels.genrateCustomersData().stream()
				.map(customer -> customer.getName())
				.collect(java.util.stream.Collectors.toList());
		System.out.println("\tTo List(Customer names): " + toListResult);
	}
	
	
	public static void main(String[] args) {
		basicCollectors();
		groupBy_KeysMapping();
		groupBy_ValuesMapping();
		partitionBy();
		
	}
}
