package streams.problems;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import streams.datasource.generate.ListOfModels;
import streams.datasource.model.Student;

public class Filter {
	
	public static void problem1() {
		System.out.print("\n 2 student with age greater than 23");
		ListOfModels.getStudents()
			.stream()
			.filter(student -> student.getAge()>23)
			.limit(2)
			.forEach(student -> System.out.print(student.getName() + ", "));
	}
	
	
	
	public static void main(String[] args) {
		problem1();
	}
}
