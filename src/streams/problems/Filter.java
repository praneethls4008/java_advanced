package streams.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import datasource.generate.ListOfModels;
import datasource.model.Student;

public class Filter {
	
	public static void problem1() {
		System.out.print("\n 2 student with age greater than 23");
		ListOfModels.getStudents()
			.stream()
			.filter(student -> student.getAge()>23)
			.limit(2)
			.forEach(student -> System.out.print(student.getName() + ", "));
	}
	
	public static void problem2() {
		record StudentFiltered(int id, String name, int age) {};
		
		System.out.print("\n groupby Student age");
		Map<Integer, List<StudentFiltered>> groupedBy = ListOfModels.getStudents()
			.stream()
			.map(student -> {
				return new StudentFiltered(student.getId(), student.getName(), student.getAge());
			})
			.collect(Collectors.groupingBy(StudentFiltered::age));
		System.out.print(groupedBy);
		
		
		
	}
	
	
	
	public static void main(String[] args) {
		problem1();
		problem2();
	}
}
