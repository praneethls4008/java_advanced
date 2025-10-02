package streams;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

import streams.datasource.generate.ListOfModels;
import streams.datasource.model.Student;

public class GeneratorsConsumers {
	
	public static void unlimitedGenerator() {
		
		//supplier
		Supplier<String> supplier =  new Supplier<String>() {
			int i=0; 
			@Override
			public String get() {
				return "data "+ (++i);
			}
			
		};
		
		//consumer
		Consumer<String> consumer = new Consumer<String>() {

			@Override
			public void accept(String t) {
				System.out.println(t);
			}
			
		};
		
		//stream of data
		Stream<String> stringStream = Stream.generate(supplier);
		
		//consumed or processed
		stringStream.forEach(consumer);
		
	}
	
	public static void collectionStream() {
		List<Student> studentsList = ListOfModels.getStudents();
		
		//stream of student
		Stream<Student> studentsStream = studentsList.stream();
		
		//consumed
		studentsStream.forEach((student) -> System.out.println(student));
	}
	
	public static void main(String[] args) {
		
						/*  ulimited stream genration  */
		//unlimitedGenerator();
		
		
	collectionStream();
	
	

		
	}
}
