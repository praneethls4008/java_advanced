package generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SUV{
	
}

class HATCHBACK{
	
}

class XUV{
	
}

class Alto800{
	
}

class Safari{
	
}

class Breeza{
	
}





public class GenericsExamples {
	//<T which should extend Comparable
	//Comparable< any subclass of T> consumer (can add T or SubOfT, can only return Object)
	//return type T
	//Any subclass of T producer (cannot add T or SubOfT, can return T or SubOfT)
	public static <T extends Comparable<? super T>> T max(List<? extends T> list) {
	    T max = list.get(0);
	    for (T element : list) {
	        if (element.compareTo(max) > 0) {
	            max = element;
	        }
	    }
	    return max;
	}
	
	public static void anySubclassOfTProducer() {
		
		List<Integer> ints = List.of(3, 5, 1);
	    System.out.println(max(ints));  // 5
	    
	    
		/*
		 * any(?) extends(subclass) of T(superclass)
		 * Object -> Number -> Integer
		 * we cannot write but read
		 */
		List<? extends Number> list = Arrays.asList(1, 2, 3); // List<Integer> is OK

		// ❌ Cannot insert
		// list.add(10); // Compile error
		// list.add(new Number(){}); // Compile error
		// list.add(null); // ✅ Only null is allowed

		// ✅ Safe to read
		Number n = list.get(0); // Works
		// Integer i = list.get(0); // ❌ Not guaranteed
		
	

	}
	
	public static void anySubclassOfTConsumer() {
		/*
		 * any(?) super(subclass) of T(superclass)
		 * Object -> Number -> Integer
		 * we can add Double because, Double is subclass of Number
		 */

		List<? super Number> list = new ArrayList<>();
		list.add(23);
		list.add(23.3);
		list.add(23L);
		System.out.println(list.get(0).getClass());
		System.out.println(list.get(1).getClass());
		System.out.println(list.get(2).getClass());
		
		
		/*
		 * if List<? super Integer> list = new ArrayList<>();
		 * we cant add Double to it because Double is not subclass of Integer
		 */
		
		List<? super Integer> list2 = new ArrayList<>();
		list2.add(23);
		
		//below throws exception
		//list2.add(23.3);
		
		System.out.println(list2.get(0).getClass());
	}
	
	public static void typeErasure() {
		/* What is type erasure?

				Generics exist only at compile time.

				At runtime, all generic info is erased.
				👉 Both are just ArrayList at runtime.
		*/
		
				List<String> a = new ArrayList<>();
				List<Integer> b = new ArrayList<>();
				System.out.println(a.getClass() == b.getClass()); // true


				
	}
	
	
	public static void main(String[] args) {
		anySubclassOfTConsumer();
		anySubclassOfTProducer();
		typeErasure();
	}
}
