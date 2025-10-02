package streams;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import streams.datasource.generate.ListOfModels;
import streams.datasource.model.Student;


/*
 * 
 * 		Avoid autoboxing/unboxing (Integer ↔ int).

		Provide extra numeric methods (sum(), average(), range(), etc.).

		More memory and CPU efficient for number-heavy processing.
 * 
 */
public class PrimitiveStreams {
	
	public static void intStream() {
		
		int[] arr =  new int[] {1, 2, 3, 4, 5, 6, 7};
		
		IntStream.of(arr)
			.forEach(System.out::println);
		
		int totalSum = IntStream.of(arr).sum();
		System.out.println("sum: " + totalSum);
		
		OptionalInt totalReduce = IntStream.of(arr).reduce(Integer::sum);
		System.out.println("reduce: " + (totalReduce.isPresent() ? totalReduce.getAsInt() : 0) );
		
		int totalReduceWithInit = IntStream.of(arr).reduce(0, Integer::sum);
		System.out.println("reduce with init val: " + totalReduceWithInit );
		
		OptionalInt minVal = IntStream.of(arr).min();
		System.out.println("min: " + (minVal.isPresent() ? minVal.getAsInt() : "") ) ;
		
		OptionalInt maxVal = IntStream.of(arr).max();
		System.out.println("max: " + (maxVal.isPresent() ? maxVal.getAsInt() : "") ) ;
			
	}
	
	public static void longStream() {
		System.out.println("\nlong range: ");
		LongStream
			.range(1L,10L)
        	.forEach(num -> System.out.print(num + ", "));
		
	}
	
	public static void boxed() {
		System.out.println("\nprimitive to object(boxed): ");
		
		LongStream
			.range(1L,10L)
			.boxed()
			.forEach(val -> System.out.print(val.hashCode() + ", "));
		
	}
	
	public static void unboxed() {
		
			System.out.println("\nobj to primitive (unbbox): ");
				
			Stream.of(1,2,3)
				.mapToInt(val -> val)
				.forEach(val -> System.out.print(val + ", "));
	}
	
	
	public static void doubleStream() {
		System.out.println("\ndouble random: ");
		DoubleStream.generate(Math::random)
        .limit(10)
        .forEach(num -> System.out.print(num + ", "));
		
		
		OptionalDouble avg= DoubleStream
				.generate(Math::random)
				.limit(10)
				.average();
		System.out.println("\naverage: "+ avg);
		
	}
	
	
	public static void range() {
		int sum = IntStream.rangeClosed(1, 5).sum();
		System.out.println(sum);
	}
	
	
	public static void main(String[] args) {
		intStream(); //intergerStream
		range(); //creates a eleme in range
		boxed();
		unboxed();
		longStream();
		doubleStream();
	}
}
