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
import datasource.model.Item;
import datasource.model.Order;
import datasource.model.Student;

//Import statements: We need these to use Streams, Collectors, and various data structures
import java.util.*;


//⚙️ This class demonstrates Java Stream Collectors in depth.
//Each section focuses on a type of Collector operation: toList, groupingBy, partitioningBy, etc.
public class CollectorsStream {

 // 🧩 Records used as simple immutable data carriers (Java 16+ feature)
 // Instead of creating full classes with getters, we can define quick "tuples"
 record Age_Attendance_Key(int age, int attendance) {};
 record Items_Price(int totalItems, Double totalPrice) {};
 record Student_TotalFee_PaidFee(double termFee, double paidFee) {}

 // ========================================================================
 // 🧠 SECTION 1: BASIC COLLECTORS (toList, toSet, toMap, toUnmodifiableList)
 // ========================================================================
 public static void basicCollectors() {
     System.out.println("\n\n- - - - - - - - - - - Basic collectors - - - - - - - - - -");

     // ✅ toList(): Collects stream elements into a List
     List<String> toListResult = ListOfModels.genrateCustomersData().stream()
             .map(customer -> customer.getName())
             .collect(Collectors.toList());
     System.out.println("\tTo List (Customer names): " + toListResult);

     // ✅ toSet(): Collects into a Set (no duplicates)
     Set<String> toSetResult = ListOfModels.genrateCustomersData().stream()
             .map(customer -> customer.getName())
             .collect(Collectors.toSet());
     System.out.println("\tTo Set (Customer names): " + toSetResult);

     // ✅ toMap(): Creates a Map from Stream elements
     // ⚙️ keyMapper -> key, valueMapper -> value, mergeFn -> handle duplicates
     Map<String, Integer> toMapResult = ListOfModels.genrateCustomersData().stream()
             .flatMap(customer -> customer.getOrders().stream())
             .flatMap(order -> order.getItems().stream())
             .collect(Collectors.toMap(
                     item -> item.getName(),          // Key mapper
                     item -> 1,                       // Value mapper
                     (oldVal, newVal) -> oldVal + newVal // Merge function for duplicates
             ));
     System.out.println("\tTo Map (Item name count): " + toMapResult);

     /*
      * ⚙️ Java 9+ introduced toUnmodifiableList() and toUnmodifiableSet()
      * They produce immutable (read-only) collections.
      */
     List<String> unmodifiableListResult = ListOfModels.genrateCustomersData().stream()
             .map(customer -> customer.getName())
             .collect(Collectors.toUnmodifiableList());
     System.out.println("\tUnmodifiable List: " + unmodifiableListResult);

     Set<String> unmodifiableSetResult = ListOfModels.genrateCustomersData().stream()
             .map(customer -> customer.getName())
             .collect(Collectors.toUnmodifiableSet());
     System.out.println("\tUnmodifiable Set: " + unmodifiableSetResult);
 }

 // ========================================================================
 // 🧠 SECTION 2: GROUPING BY — KEY MAPPING (1st argument controls grouping key)
 // ========================================================================
 public static void groupBy_KeysMapping() {
     System.out.println("\n\n- - - - - - - - - - GroupingBy Keys - - - - - - - - - -");

     // ✅ Single-field grouping
     // ⚙️ Group all orders by their order date
     Map<LocalDate, List<Order>> groupByResult1 = ListOfModels.genrateCustomersData().stream()
             .flatMap(customer -> customer.getOrders().stream())
             .collect(Collectors.groupingBy(Order::getDate));
     System.out.println("\tGroupBy (single field - date): " + groupByResult1);

     // ✅ Multi-field grouping using a record as key
     // ⚙️ Groups students by both age and attendance
     Map<Age_Attendance_Key, List<Student>> groupByResult2 = ListOfModels.getStudents().stream()
             .collect(Collectors.groupingBy(
                     student -> new Age_Attendance_Key(student.getAge(), student.getAttendance())
             ));
     System.out.println("\tGroupBy (multi-field record key): " + groupByResult2);
 }

 // ========================================================================
 // 🧠 SECTION 3: GROUPING BY — VALUE MAPPING (2nd argument controls collector)
 // ========================================================================
 public static void groupBy_ValuesMapping() {
     System.out.println("\n\n- - - - - - - - - - GroupingBy Values - - - - - - - - - -");

     // ✅ Collect grouped students into a Set instead of List
     Map<Integer, Set<Student>> groupBySetResult1 = ListOfModels.getStudents().stream()
             .collect(Collectors.groupingBy(Student::getAttendance, Collectors.toSet()));
     System.out.println("\tGroupBy (attendance -> set of students): " + groupBySetResult1);

     // ✅ Custom collector using Collector.of()
     // ⚙️ Demonstrates custom accumulation logic
     Map<Integer, List<Student>> customCollectorResult = ListOfModels.getStudents().stream()
             .collect(Collectors.groupingBy(
                     Student::getAttendance,
                     Collector.of(
                             ArrayList::new,
                             List::add,
                             (left, right) -> { left.addAll(right); return left; }
                     )
             ));
     System.out.println("\tGroupBy with custom collector: " + customCollectorResult);

     // ✅ Nested Grouping: group by attendance, then by age
     Map<Integer, Map<Integer, List<Student>>> groupBySecondary = ListOfModels.getStudents().stream()
             .collect(Collectors.groupingBy(
                     Student::getAttendance,
                     Collectors.groupingBy(Student::getAge)
             ));
     System.out.println("\tNested GroupBy (attendance -> age): " + groupBySecondary);

     // ✅ Aggregations
     Map<Integer, Double> averageAttendance = ListOfModels.getStudents().stream()
             .collect(Collectors.groupingBy(Student::getAge, Collectors.averagingDouble(Student::getAttendance)));
     System.out.println("\tGroupBy (average attendance by age): " + averageAttendance);

     Map<Integer, Double> sumFees = ListOfModels.getStudents().stream()
             .collect(Collectors.groupingBy(Student::getAge, Collectors.summingDouble(Student::getTermFee)));
     System.out.println("\tGroupBy (sum of term fees by age): " + sumFees);

     // ✅ Max, Min, Count, Summary
     Map<Integer, Optional<Student>> maxAttendance = ListOfModels.getStudents().stream()
             .collect(Collectors.groupingBy(
                     Student::getAge,
                     Collectors.maxBy(Comparator.comparingDouble(Student::getAttendance))
             ));
     System.out.println("\tGroupBy (max attendance per age): " + maxAttendance);

     Map<Integer, Long> countByAge = ListOfModels.getStudents().stream()
             .collect(Collectors.groupingBy(Student::getAge, Collectors.counting()));
     System.out.println("\tGroupBy (count by age): " + countByAge);

     Map<Integer, DoubleSummaryStatistics> summaryByAge = ListOfModels.getStudents().stream()
             .collect(Collectors.groupingBy(Student::getAge, Collectors.summarizingDouble(Student::getTermFee)));
     System.out.println("\tGroupBy (termFee summary by age): " + summaryByAge);

     // ✅ Advanced Example: Nested Grouping → Count + Price per Date
     Map<LocalDate, Map<String, Items_Price>> itemsCountAndCostByDate =
             ListOfModels.genrateCustomersData().stream()
                     .flatMap(cust -> cust.getOrders().stream())
                     .collect(Collectors.groupingBy(
                             Order::getDate,
                             Collectors.collectingAndThen(
                                     Collectors.toList(),
                                     orders -> orders.stream()
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
     System.out.println("\tNested GroupBy (date -> item count & cost): " + itemsCountAndCostByDate);

     // ✅ mapping(), filtering(), joining(), reducing()
     Map<Integer, Set<Double>> paidFeesByAge = ListOfModels.getStudents().stream()
             .collect(Collectors.groupingBy(Student::getAge,
                     Collectors.mapping(Student::getPaidFee, Collectors.toSet())));
     System.out.println("\tGroupBy and Mapping (paid fees): " + paidFeesByAge);

     Map<Integer, List<Object>> filteringResult = ListOfModels.getStudents().stream()
             .collect(Collectors.groupingBy(Student::getAttendance,
                     Collectors.filtering(s -> s.getAge() > 20, Collectors.toList())));
     System.out.println("\tGroupBy and Filtering: " + filteringResult);

     Map<Integer, String> joinNames = ListOfModels.getStudents().stream()
             .collect(Collectors.groupingBy(Student::getAge,
                     Collectors.mapping(Student::getName, Collectors.joining(", ", "[", "]"))));
     System.out.println("\tGroupBy and Joining names: " + joinNames);
 }

 // ========================================================================
 // 🧠 SECTION 4: groupingBy with 3 arguments (keyMapper, mapSupplier, downstream)
 // ========================================================================
 public static void threeArgsConstructor_MapType() {
     System.out.println("- - - - - GroupingBy with 3 args - - - - -");

     // ⚙️ TreeMap (ascending order)
     Map<Integer, List<String>> treeMapRes = ListOfModels.getStudents().stream()
             .collect(Collectors.groupingBy(Student::getAttendance,
                     TreeMap::new,
                     Collectors.mapping(Student::getName, Collectors.toList())));
     System.out.println("\tTreeMap (ascending): " + treeMapRes);

     // ⚙️ TreeMap (descending order)
     Map<Integer, List<String>> treeMapDesc = ListOfModels.getStudents().stream()
             .collect(Collectors.groupingBy(Student::getAttendance,
                     () -> new TreeMap<>(Collections.reverseOrder()),
                     Collectors.mapping(Student::getName, Collectors.toList())));
     System.out.println("\tTreeMap (descending): " + treeMapDesc);

     // ⚙️ LinkedHashMap (preserves insertion order)
     Map<Integer, List<String>> linkedHashMapRes = ListOfModels.getStudents().stream()
             .collect(Collectors.groupingBy(Student::getAttendance,
                     LinkedHashMap::new,
                     Collectors.mapping(Student::getName, Collectors.toList())));
     System.out.println("\tLinkedHashMap: " + linkedHashMapRes);

     // ⚙️ ConcurrentHashMap (thread-safe)
     Map<Integer, List<String>> concurrentRes = ListOfModels.getStudents().stream()
             .collect(Collectors.groupingBy(Student::getAttendance,
                     ConcurrentHashMap::new,
                     Collectors.mapping(Student::getName, Collectors.toList())));
     System.out.println("\tConcurrentHashMap: " + concurrentRes);
 }

 // ========================================================================
 // 🧠 SECTION 5: partitioningBy (split data into two groups: true/false)
 // ========================================================================
 public static void partitionBy() {
     System.out.println("- - - - - partitioningBy - - - - -");

     // ⚙️ Split students into two groups based on attendance
     Map<Boolean, List<Student>> result = ListOfModels.getStudents().stream()
             .collect(Collectors.partitioningBy(s -> s.getAttendance() > 65));
     System.out.println("\tPartition (Students): " + result);

     // ⚙️ Partition + Mapping: collect names instead of objects
     Map<Boolean, List<String>> result2 = ListOfModels.getStudents().stream()
             .collect(Collectors.partitioningBy(s -> s.getAttendance() > 65,
                     Collectors.mapping(Student::getName, Collectors.toList())));
     System.out.println("\tPartition (Student names): " + result2);
 }

 // ========================================================================
 // 🧠 SECTION 6: Parallel vs Sequential Performance Comparison
 // ========================================================================
 public static void parallelStreamGroubBy() {
     System.out.println("- - - - - parallel stream groupBy - - - - -");

     long start1 = System.currentTimeMillis();
     Map<Integer, Student_TotalFee_PaidFee> parallel = ListOfModels.getStudents().parallelStream()
             .collect(Collectors.groupingBy(
                     Student::getAge,
                     Collectors.reducing(new Student_TotalFee_PaidFee(0.0, 0.0),
                             s -> new Student_TotalFee_PaidFee(s.getTermFee(), s.getPaidFee()),
                             (s1, s2) -> new Student_TotalFee_PaidFee(
                                     s1.termFee() + s2.termFee(),
                                     s1.paidFee() + s2.paidFee())
                     )));
     long timeParallel = System.currentTimeMillis() - start1;

     long start2 = System.currentTimeMillis();
     Map<Integer, Student_TotalFee_PaidFee> concurrent = ListOfModels.getStudents().parallelStream()
             .collect(Collectors.groupingByConcurrent(
                     Student::getAge,
                     Collectors.reducing(new Student_TotalFee_PaidFee(0.0, 0.0),
                             s -> new Student_TotalFee_PaidFee(s.getTermFee(), s.getPaidFee()),
                             (s1, s2) -> new Student_TotalFee_PaidFee(
                                     s1.termFee() + s2.termFee(),
                                     s1.paidFee() + s2.paidFee())
                     )));
     long timeConcurrent = System.currentTimeMillis() - start2;

     long start3 = System.currentTimeMillis();
     Map<Integer, Student_TotalFee_PaidFee> sequential = ListOfModels.getStudents().stream()
             .collect(Collectors.groupingBy(
                     Student::getAge,
                     Collectors.reducing(new Student_TotalFee_PaidFee(0.0, 0.0),
                             s -> new Student_TotalFee_PaidFee(s.getTermFee(), s.getPaidFee()),
                             (s1, s2) -> new Student_TotalFee_PaidFee(
                                     s1.termFee() + s2.termFee(),
                                     s1.paidFee() + s2.paidFee())
                     )));
     long timeSequential = System.currentTimeMillis() - start3;

     System.out.printf("""
         Parallel Time (groupingBy): %d ms
         Parallel Time (groupingByConcurrent): %d ms
         Sequential Time: %d ms
         """, timeParallel, timeConcurrent, timeSequential);
 }

 // ========================================================================
 // MAIN — Calls all examples sequentially
 // ========================================================================
 public static void main(String[] args) {
     basicCollectors();
     groupBy_KeysMapping();
     groupBy_ValuesMapping();
     partitionBy();
     threeArgsConstructor_MapType();
     parallelStreamGroubBy();
 }
}
