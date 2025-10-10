package streams;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import datasource.generate.ListOfModels;
import datasource.model.Customer;
import datasource.model.Item;
import datasource.model.Order;
import datasource.model.Student;

public class CollectorsStream {

	record Age_Attendance_Key(int age, int attendance) {};
	record Items_Price(int totalItems, Double totalPrice) {};
	record Student_TotalFee_PaidFee(double termFee, double paidFee) {}

	public static void basicCollectors() {
		System.out.println("\n\n- - - - - - - - - - - - - - - - - - - - Basic collectors- - - - - - - - - - - - - - - - - - - - ");
		
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
		System.out.println("\n\n- - - - - - - - - - - - - - - - - - - - Grouping (First Arg for key)- - - - - - - - - - - - - - - - - - - - ");
		
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
		System.out.println("\n\n- - - - - - - - - - - - - - - - - - - - Grouping (Second Arg for return)- - - - - - - - - - - - - - - - - - - - ");
		
		Map<Integer, Set<Student>> groupBySetResult1 = ListOfModels.getStudents().stream()
				.collect(
						Collectors.groupingBy(
								Student::getAttendance,
								Collectors.toSet() // by default List
								)
							);
		System.out.println("\tGroupBy return type Custom collector List 1" + groupBySetResult1);
		
		Map<Integer, List<Student>> groupByCustomCollectorResult1 = ListOfModels.getStudents().stream()
				.collect(
						Collectors.groupingBy(
								Student::getAttendance,
								Collector.of(ArrayList::new,
										List::add ,//should be list not arraylist
										(left, right) -> {left.addAll(right);
											return left;
										})));
		
		System.out.println("\\tGroupBy return type Custom collector List 2" + groupByCustomCollectorResult1);
		
		Map<Integer, List<Student>> groupByCustomCollectorResult2 = ListOfModels.getStudents().stream()
				.collect(
						Collectors.groupingBy(
								Student::getAttendance,
								Collector.of(() -> new ArrayList<Student>(),
										(list, ele) -> list.add(ele),
										(left, right) -> {left.addAll(right);
											return left;
										})));
		
		System.out.println("\tGroupBy return type Set" + groupByCustomCollectorResult2);
		
		
		Map<Integer, Map<Integer, List<Student>>> groupBysecondaryGroupBy = ListOfModels.getStudents().stream()
				.collect(Collectors.groupingBy(
						Student::getAttendance,
						Collectors.groupingBy(Student::getAge)
						));
		System.out.println("\tGroupBy secondary groupBY" + groupBysecondaryGroupBy);
		
		
		Map<Integer, Double> groupByAndAverageReturn = ListOfModels.getStudents().stream()
				.collect(Collectors.groupingBy(
						Student::getAge,
						Collectors.averagingDouble(Student::getAttendance)
						));
		System.out.println("\tGroupBy Average return" + groupByAndAverageReturn);
		
		Map<Integer, Double> groupByAndSummingReturn = ListOfModels.getStudents().stream()
				.collect(Collectors.groupingBy(
						Student::getAge,
						Collectors.summingDouble(Student::getTermFee)
						));
		System.out.println("\tGroupBy Summing return" + groupByAndSummingReturn);
		
		Map<Integer, Optional<Student>> groupByAndMaxReturn = ListOfModels.getStudents().stream()
				.collect(
						Collectors.groupingBy(
						Student::getAge,
						Collectors.maxBy((currStudent, otherStudent) -> Double.compare(currStudent.getAttendance(), otherStudent.getAttendance()))
						));
		System.out.println("\tGroupBy Max return" + groupByAndMaxReturn);
		
		Map<Integer, Optional<Student>> groupByAndMinReturn = ListOfModels.getStudents().stream()
				.collect(
						Collectors.groupingBy(
						Student::getAge,
						Collectors.minBy((currStudent, otherStudent) -> Double.compare(currStudent.getAttendance(), otherStudent.getAttendance()))
								));
		System.out.println("\tGroupBy Max return" + groupByAndMinReturn);
		
		Map<Integer, Long> groupByAndCountReturn = ListOfModels.getStudents().stream()
				.collect(
						Collectors.groupingBy(
						Student::getAge,
						Collectors.counting()
								));
		System.out.println("\tGroupBy Count return" + groupByAndCountReturn);
		
		Map<Integer, DoubleSummaryStatistics> groupByAndSummaryReturn = ListOfModels.getStudents().stream()
				.collect(
						Collectors.groupingBy(
						Student::getAge,
						Collectors.summarizingDouble(Student::getTermFee)
								));
		System.out.println("\tGroupBy Summary return" + groupByAndSummaryReturn);
		

		Map<LocalDate, Map<String, Items_Price>> itemsCountAndCostByDate =
		    ListOfModels.genrateCustomersData().stream()
		        .flatMap(cust -> cust.getOrders().stream())
		        .collect(Collectors.groupingBy(
		            Order::getDate,
		            Collectors.collectingAndThen(
		                Collectors.toList(),
		                ordersList -> ordersList.stream()
		                    .flatMap(order -> order.getItems().stream())
		                    .collect(Collectors.groupingBy(
		                        Item::getName,
		                        Collectors.reducing(
		                            new Items_Price(0, 0.0),
		                            item -> new Items_Price(1, item.getPrice()),
		                            (a, b) -> new Items_Price(
		                                a.totalItems() + b.totalItems(),
		                                a.totalPrice() + b.totalPrice()
		                            )
		                        )
		                    ))
		            )
		        ));
		
		System.out.println("\tGroupBy date return items count and cost " + itemsCountAndCostByDate);
		
		
		Map<Integer, Set<Double>> groupByAndMappingReturn = ListOfModels.getStudents().stream()
				.collect(Collectors.groupingBy(Student::getAge,
						Collectors.mapping(Student::getPaidFee, Collectors.toSet()))
						);
		System.out.println("\tGroupBy and Mapping return" + groupByAndMappingReturn);
		
		Map<String, List<Object>> groupByAndFlatMappingReturn = ListOfModels.genrateCustomersData().stream()
				.collect(Collectors.groupingBy(Customer::getName,
						Collectors.flatMapping(c -> c.getOrders().stream(), Collectors.toList()))
						);
		System.out.println("\tGroupBy and Flat Mapping return" + groupByAndFlatMappingReturn);
		
		Map<Integer, List<Object>> groupByAndFilteringReturn = ListOfModels.getStudents().stream()
				.collect(Collectors.groupingBy(Student::getAttendance,
						Collectors.filtering(s -> s.getAge()>20, 
								Collectors.toList())
								));
		System.out.println("\tGroupBy and filtering result" + groupByAndFilteringReturn);
		
		Map<Integer, List<Object>> groupByAndFilteringAndMappingReturn = ListOfModels.getStudents().stream()
				.collect(Collectors.groupingBy(Student::getAttendance,
						Collectors.filtering(s -> s.getAge()>20, 
								Collectors.mapping(s -> s.getAge(), Collectors.toList())
								)));
		
		System.out.println("\tGroupBy and filtering + Mapping result" + groupByAndFilteringAndMappingReturn);
		
		Map<Integer, String> groupByAndMappingJoiningReturn = ListOfModels.getStudents().stream()
				.collect(Collectors.groupingBy(Student::getAge,
						Collectors.mapping(Student::getName, Collectors.joining(", ", "[", "]"))));
				
		System.out.println("\tGroupBy Mapping and Joining return" + groupByAndMappingJoiningReturn);

		
		Map<Integer, Student_TotalFee_PaidFee> groupByAndMappingReturn2 = ListOfModels.getStudents().stream()
				.collect(Collectors.groupingBy(Student::getAge,
						Collectors.reducing( new Student_TotalFee_PaidFee(0.0, 0.0),
								s -> new Student_TotalFee_PaidFee(s.getTermFee(), s.getPaidFee()),
								(s1, s2) -> new Student_TotalFee_PaidFee(s1.termFee() + s2.termFee(), s1.paidFee() + s2.paidFee())
								)));
		
		System.out.println("\tGroupBy Mapping and reducing multiple fields return" + groupByAndMappingReturn2);
		

	}
	
	
	public static void threeArgsConstructor_MapType() {
		System.out.println("- - - - - - - - - - - - - - - - - - - - Grouby 3args - - - - - - - - - - - - - - - - - - - - ");
		
		Map<Integer, List<String>> treeMapRes = ListOfModels.getStudents().stream()
				.collect(Collectors.groupingBy(Student::getAttendance,
						() -> new TreeMap<>(),
						Collectors.mapping(Student::getName, Collectors.toList())));
			System.out.println("\tGrouby by 3args TreeMap(Ascending) instead of Map: " + treeMapRes);
		
		Map<Integer, List<String>> treeMapDescRes = ListOfModels.getStudents().stream()
			.collect(Collectors.groupingBy(Student::getAttendance,
					() -> new TreeMap<>(Collections.reverseOrder()),
					Collectors.mapping(Student::getName, Collectors.toList())));
		System.out.println("\tGrouby by 3args TreeMap(Descending) instead of Map: " + treeMapDescRes);
		
		Map<Integer, List<String>> linkedHashMapRes = ListOfModels.getStudents().stream()
				.collect(Collectors.groupingBy(Student::getAttendance,
						LinkedHashMap::new,
						Collectors.mapping(Student::getName, Collectors.toList())));
			System.out.println("\tGrouby by 3args LinkedHashMap instead of Map: " + linkedHashMapRes);
			
			Map<Integer, List<String>> concurrentHashMapRes = ListOfModels.getStudents().stream()
					.collect(Collectors.groupingBy(Student::getAttendance,
							ConcurrentHashMap::new,
							Collectors.mapping(Student::getName, Collectors.toList())));
				System.out.println("\tGrouby by 3args ConcurrentHashMap instead of Map: " + concurrentHashMapRes);
			
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
		threeArgsConstructor_MapType();
		
	}
	}
