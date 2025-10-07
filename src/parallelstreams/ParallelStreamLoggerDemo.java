package parallelstreams;

import java.util.List;

import datasource.generate.ListOfModels;
import datasource.model.Student;


public class ParallelStreamLoggerDemo {
    public static void main(String[] args) {

        List<Student> students = ListOfModels.getStudents();

        // Run parallel stream with logging
        students.parallelStream()
            .peek(s -> StreamLogger.log("peek", s))
            .map(s -> {
                StreamLogger.log("map", s);
                return s;
            })
            .filter(s -> {
                StreamLogger.log("filter", s);
                return s.getAge() >= 20;
            })
//            .sorted((s1,s2) -> {
//            	StreamLogger.log("sortS1", s1);
//            	StreamLogger.log("sortS2", s2);
//            	
//            	return Integer.compare(s1.getAttendance(), s2.getAttendance());
//            })
            .sequential()
            .forEach(s -> StreamLogger.log("foreach", s));

        System.out.println("\n=== Raw Logs (unordered) ===");
        StreamLogger.dumpLogs();

        System.out.println("\n=== Grouped by Thread ===");
        StreamLogger.dumpLogsGroupedByThread();
    }
}
