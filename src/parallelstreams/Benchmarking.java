package parallelstreams;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;


public class Benchmarking {

	 // Generate a list of random integers
    private static List<Integer> generateData(int size) {
        Random rand = new Random();
        return IntStream.range(0, size)
                        .map(i -> rand.nextInt(1000)) // values [0..999]
                        .boxed()
                        .toList();
    }

    // Sequential vs Parallel benchmark
    public static long[] sequentialVsParallel(List<Integer> data) {
        long[] res = new long[2];

        // Sequential
        long startSeq = System.nanoTime();
        long seqSum = data.stream()
                          .mapToLong(n -> {
                              // Random CPU-heavy work (modular multiplication loop)
                              long r = n;
                              for (int i = 1; i < 100; i++) {
                                  r = (r * i) % 1234567;
                              }
                              return r;
                          })
                          .sum();
        long seqTime = System.nanoTime() - startSeq;

        // Parallel
        long startPar = System.nanoTime();
        long parSum = data.parallelStream()
                          .mapToLong(n -> {
                              long r = n;
                              for (int i = 1; i < 100; i++) {
                                  r = (r * i) % 1234567;
                              }
                              return r;
                          })
                          .sum();
        long parTime = System.nanoTime() - startPar;

        // Validate correctness
        if (seqSum != parSum) {
            System.out.println("⚠️ Warning: Results differ! Seq=" + seqSum + ", Par=" + parSum);
        }

        res[0] = seqTime;
        res[1] = parTime;
        return res;
    }

    // Run multiple times & compute statistics
    public static void cacheMultipleRuns(List<Integer> data, int runs) {
        long seqTotal = 0, parTotal = 0;
        long seqMax = Long.MIN_VALUE, parMax = Long.MIN_VALUE;
        long seqMin = Long.MAX_VALUE, parMin = Long.MAX_VALUE;

        for (int i = 0; i < runs; i++) {
            long[] result = sequentialVsParallel(data);

            seqTotal += result[0];
            parTotal += result[1];

            seqMax = Math.max(seqMax, result[0]);
            seqMin = Math.min(seqMin, result[0]);

            parMax = Math.max(parMax, result[1]);
            parMin = Math.min(parMin, result[1]);
        }

        System.out.println("\n--- Benchmark Results (" + runs + " runs) ---");
        System.out.println("Average (Sequential): " + (seqTotal / runs) / 1_000_000.0 + " ms");
        System.out.println("Average (Parallel)  : " + (parTotal / runs) / 1_000_000.0 + " ms");

        System.out.println("\nMax (Sequential): " + seqMax / 1_000_000.0 + " ms");
        System.out.println("Max (Parallel)  : " + parMax / 1_000_000.0 + " ms");

        System.out.println("\nMin (Sequential): " + seqMin / 1_000_000.0 + " ms");
        System.out.println("Min (Parallel)  : " + parMin / 1_000_000.0 + " ms");
    }

    public static void main(String[] args) {
        int dataSize = 1_000_000; // 1 million elements
        int runs = 20;            // run benchmark 20 times

        System.out.println("Generating dataset of size " + dataSize + "...");
        List<Integer> data = generateData(dataSize);

        cacheMultipleRuns(data, runs);
    }
}
