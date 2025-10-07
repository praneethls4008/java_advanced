package streams;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import datasource.generate.ListOfModels;
import datasource.model.Student;

/*
 * 
 * Terminal opertaion is stream fucntion which does not return a new stream, but ends  current stream
 * 
 * streams are lazy, but terminal i=operations are eager
 */
public class TerminalOperations {

	
	public static void allMatch() {
		boolean allMatched = ListOfModels.getStudents()
			.stream()
			.allMatch(student -> student.getAge()>1);
		System.out.println("All elements in list are grater than 1" + allMatched);
	}
	
	public static void anyMatch() {
		boolean anyMatched = ListOfModels.getStudents()
				.stream()
				.anyMatch(student -> student.getAge()>1);
			System.out.println("Any elements in list are grater than 1" + anyMatched);
	}
	
	public static void count() {
		long count = ListOfModels.getStudents()
				.stream()
				.filter(student -> student.getAttendance()>75)
				.count();
			System.out.println("No of Student with attendence greater than 75: " + count);
	}
	
	public static void collect() {
		 List<String> studentList = ListOfModels.getStudents()
				.stream()
				.filter(student -> student.getAttendance()>75)
				.map(student -> student.getName())
				.collect(Collectors.toList());
		 
		 System.out.println("Students attendence greater than 75 " + studentList);
	}
	
	public static void findAny() {
		 Optional<Student> student = ListOfModels.getStudents()
				.stream()
				.findAny();
		 
		 System.out.println("finay any one in list " + (student.isPresent() ? student.get() : "") );
	}
	
	public static void forEach() {
		System.out.println("\nnames of students who's age greater than 24:\n\t");
		ListOfModels.getStudents()
		.stream()
		.filter(student -> student.getAge()>=25)
		.forEach(student -> System.out.print(student.getName() + ", "));
	}
	
	
	public static void maxEle() {
		Optional<Student> student = ListOfModels.getStudents()
		.stream()
		.max((s1, s2) -> Double.compare(s1.getTermFee(), s2.getTermFee()));
		String msg = "none";
		if(student.isPresent()) {
			msg = student.get().getName() + "[" + student.get().getTermFee() + "]";
		}
		System.out.println("\nmax tutuion fee: " + msg);
	}
	
	
	public static void minEle() {
		Optional<Student> student = ListOfModels.getStudents()
				.stream()
				.min((s1, s2) -> Double.compare(s1.getTermFee(), s2.getTermFee()));
				String msg = "none";
				if(student.isPresent()) {
					msg = student.get().getName() + "[" + student.get().getTermFee() + "]";
				}
				System.out.println("\nmin tutuion fee: " + msg);
	}
	
	
	public static void maxElement() {
		
	}
	
	
	public static void main(String[] args) {
		allMatch(); // returns boolean if all elements are greater than predicate
		anyMatch(); // returns boolean if any element are greater than predicate
		count(); //return total no of elements in current stream
		collect();//return all elements in current stream to collection
		findAny();// returns any one from stream or null
		forEach(); // iterated over each element in stream
		maxEle(); //max element in stream optional<T>
		minEle(); //min element in stream optional<T>
	}
}
