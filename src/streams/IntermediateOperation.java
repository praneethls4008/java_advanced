package streams;

import java.util.Collections;

import streams.datasource.generate.ListOfModels;

/*
 * 
 * Intermediate opertaion is stream fucntion which does return a new stream
 * 
 * Streams are lazy
 * intermediate operations are lazy, then will only process when terminal operation is performed
 * if not terminal operation performed intermediate functions wont run
 */
public class IntermediateOperation {
	
	public static void filter() {
		System.out.print("\nnames of students who's age greater than {24}:\n\t");
		ListOfModels.getStudents()
		.stream()
		.filter(student -> student.getAge()>=25)
		.forEach(student -> System.out.print(student.getName() + ", "));
	}
	
	public static void distinct() {
		System.out.print("\ndistinct ages greater than {20}:\n\t");
		ListOfModels.getStudents()
		.stream()
		.filter(student -> student.getAge()>20)
		.map(student -> student.getAge())
		.distinct()
		.forEach(student -> System.out.print(student + ", "));
	}
	
	public static void limit() {
		System.out.print("\n limit to 2 students with age greater than {23}:\n\t");
		ListOfModels.getStudents()
			.stream()
			.filter(student -> student.getAge()>23)
			.limit(2)
			.forEach(student -> System.out.print(student.getName() + ", "));
	}
	
	public static void map() {
		System.out.print("\n list student name with no due fee:\n\t");
		ListOfModels.getStudents()
			.stream()
			.filter(student -> student.getTermFee()-student.getPaidFee() == 0)
			.map(student -> student.getName())
			.forEach(studentName -> System.out.print(studentName + ", "));
	}
	
	public static void sorted() {
		System.out.print("\n Ascending sort students based on attendance:\n\t");
		ListOfModels.getStudents()
			.stream()
			.sorted((s1, s2) ->  s1.getAttendance() - s2.getAttendance())
			.forEach(student -> System.out.print(student.getName() + "[" + student.getAttendance() + "], "));
	
		
		System.out.print("\n Descending sort students based on attendance:\n\t");
		ListOfModels.getStudents()
			.stream()
			.sorted((s1, s2) -> s2.getAttendance() - s1.getAttendance())
			.forEach(student -> System.out.print(student.getName() + "[" + student.getAttendance() + "], "));
	
		System.out.print("\n Ascending sort Sort students based on name:\n\t");
		ListOfModels.getStudents()
			.stream()
			.sorted((s1, s2) ->  s1.getName().compareTo(s2.getName()) )
			.forEach(student -> System.out.print(student.getName() + "[" + student.getAttendance() + "], "));
	
		
		System.out.print("\n Descending sort Sort students based on name:\n\t");
		ListOfModels.getStudents()
			.stream()
			.sorted((s1, s2) ->  s2.getName().compareTo(s1.getName()) )
			.forEach(student -> System.out.print(student.getName() + "[" + student.getAttendance() + "], "));
	
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		filter(); // returns new stream after filtering
		distinct(); // returns new stream with distinct values
		limit();//limits stream to max size and returns as new stream
		map();//takes streams and returns new streams functional interface
		sorted();
	}

}
