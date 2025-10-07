package parallelstreams;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

import datasource.model.Student;

public class StreamLogger {
    private static final ConcurrentLinkedQueue<String> logs = new ConcurrentLinkedQueue<>();

    public static void log(String stage, Student student) {
        logs.add(String.format("[%s] ID: %d -> %s",
            stage, student.getId(), Thread.currentThread().getName()));
    }

    public static void dumpLogs() {
        logs.forEach(System.out::println);
    }

    public static void dumpLogsGroupedByThread() {
        logs.stream()
            .collect(Collectors.groupingBy(
                log -> log.substring(log.lastIndexOf("->") + 3)
            ))
            .forEach((thread, entries) -> {
                System.out.println("\n=== " + thread + " ===");
                entries.forEach(System.out::println);
            });
    }

    public static void clear() {
        logs.clear();
    }
}

