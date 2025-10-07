package parallelstreams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import datasource.generate.ListOfModels;
import datasource.model.Student;

public class DataHandling {

	public static void forEachTypes() {
		System.out.println("\nforeach: ");
		ListOfModels.getStudents().stream().parallel().filter(studentObj -> studentObj.getAttendance() > 65)
				.map(Student::getId).forEach(studentId -> System.out.print(studentId + ", "));

		System.out.println("\nforeachOrdered: ");
		ListOfModels.getStudents().stream().parallel().filter(studentObj -> studentObj.getAttendance() > 65)
				.map(Student::getId).forEachOrdered(studentId -> System.out.print(studentId + ", "));
	}

	public static void arrayList_NotSafe() {
		ArrayList<Integer> arrayList = new ArrayList<>();
		System.out.println("\n- - - - - - - - -using ArrayList(Not Thread Safe)- - - - - - - - -");
		for (int i = 0; i < 50; i++) {
			arrayList.clear();
			int iterationEnd = 51;
			IntStream.range(0, iterationEnd).parallel().forEach(num -> {
				arrayList.add(num);
			});

			System.out.println(
					"\nlen of arraylist: " + arrayList.size() + ", elemnts lost: " + (iterationEnd - arrayList.size()));
		}
	}

	public static void arrayListSynced_Safe() {
		List<Integer> arrayListSynchronized = Collections.synchronizedList(new ArrayList<>());
		System.out.println(
				"\n- - - - - - - - -using Collections.synchronizedList(ArrayList)(Thread Safe)- - - - - - - - -");
		for (int i = 0; i < 50; i++) {
			arrayListSynchronized.clear();
			int iterationEnd = 51;
			IntStream.range(0, iterationEnd).parallel().forEach(num -> {
				arrayListSynchronized.add(num);
			});

			System.out.println("\nlen of arraylist(Sync): " + arrayListSynchronized.size() + ", elemnts lost: "
					+ (iterationEnd - arrayListSynchronized.size()));
		}
	}

	public static void vector_Safe() {
		Vector<Integer> vector = new Vector<>();
		System.out.println("\n- - - - - - - - -using vector(Thread Safe)- - - - - - - - -");
		for (int i = 0; i < 50; i++) {
			vector.clear();
			int iterationEnd = 51;
			IntStream.range(0, iterationEnd).parallel().forEach(num -> {
				vector.add(num);
			});

			System.out
					.println("\nlen of vector: " + vector.size() + ", elemnts lost: " + (iterationEnd - vector.size()));
		}
	}

	public static void streamCollectors_Safe() {
		List<Integer> arrayListCollect = new ArrayList<>();
		System.out.println("\n- - - - - - - - -using stream collectors (Thread Safe)- - - - - - - - - - ");

		/*
		 * 
		 * IntStream.range(0, iterationEnd)
			    .parallel()
			    .boxed()
			    .collect(Collectors.toList());
		 * 
		 * 
		 * box values as we are using primitive stream
		 */
		for (int i = 0; i < 50; i++) {
			int iterationEnd = 51;
			arrayListCollect = IntStream.range(0, iterationEnd).parallel().collect(ArrayList::new, ArrayList::add,
					(leftArr, rightArr) -> leftArr.addAll(rightArr));

			System.out.println("\nlen of stream collectors: " + arrayListCollect.size() + ", elemnts lost: "
					+ (iterationEnd - arrayListCollect.size()));
		}
	}

	public static void main(String[] args) {
		forEachTypes();//foreach is not synced foreachOrdered is synced)follows stream order)
		arrayList_NotSafe();//array list is not thread safe
		arrayListSynced_Safe();// wrap arraylist with Collections.sychronizeCollectionList to make it sync
		vector_Safe();//vector is thread safe
		streamCollectors_Safe();//for collector implement (containerType, accumulator, combiner)  ex: (ArrayList, ArrayList::New, (leftArr, rightArr)->leftArr.addAll(rightArr))
		
	}

}
