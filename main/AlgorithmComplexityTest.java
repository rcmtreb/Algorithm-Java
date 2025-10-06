import java.util.Random;
import java.util.Arrays;

public class AlgorithmComplexityTest {
    public static void main(String[] args) {
        // ===== INPUT SIZES =====
        int[] sizes = {1000, 5000, 10000, 50000, 100000};
        int[][] testArrays = new int[sizes.length][];
        Random rand = new Random();

        // ===== GENERATE RANDOM ARRAYS =====
        for (int i = 0; i < sizes.length; i++) {
            testArrays[i] = new int[sizes[i]];
            for (int j = 0; j < sizes[i]; j++) {
                testArrays[i][j] = rand.nextInt(1_000_000); // random integers up to 999,999
            }
        }

        // ===== SEARCHING ALGORITHMS =====
        System.out.println("==========================================================================");
        System.out.println("                    SEARCHING ALGORITHMS TIME TEST");
        System.out.println("==========================================================================");
        System.out.printf("| %-10s | %-15s | %-15s | %-15s |%n",
                "Array Size", "Key (random)", "Linear (ms)", "Binary (ms)");
        System.out.println("--------------------------------------------------------------------------");

        for (int i = 0; i < testArrays.length; i++) {
            int[] arr = testArrays[i];
            int[] sorted = Arrays.copyOf(arr, arr.length);
            Arrays.sort(sorted);
            int key = arr[rand.nextInt(arr.length)];
            int repeats = 1000; // repeat search for visible timing

            // ==== LINEAR SEARCH ====
            long start = System.nanoTime();
            for (int r = 0; r < repeats; r++) {
                linearSearch(arr, key);
            }
            double linearTime = (System.nanoTime() - start) / 1_000_000.0; // ms

            // ==== BINARY SEARCH ====
            start = System.nanoTime();
            for (int r = 0; r < repeats; r++) {
                binarySearch(sorted, key);
            }
            double binaryTime = (System.nanoTime() - start) / 1_000_000.0; // ms

            // ==== PRINT RESULTS ====
            System.out.printf("| %-10d | %-15d | %-15.3f | %-15.3f |%n",
                    sizes[i], key, linearTime, binaryTime);
        }

        System.out.println("--------------------------------------------------------------------------\n");

        // ===== SORTING ALGORITHMS =====
        System.out.println("===========================================================================================");
        System.out.println("                        SORTING ALGORITHMS TIME TEST");
        System.out.println("===========================================================================================");
        System.out.printf("| %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s |%n",
                "Array Size", "Quick", "Merge", "Heap", "Bubble", "Insertion", "Selection", "Shell");
        System.out.println("-------------------------------------------------------------------------------------------");

        for (int i = 0; i < testArrays.length; i++) {
            int size = sizes[i];
            int[] quickCopy = Arrays.copyOf(testArrays[i], testArrays[i].length);

            // gaya lang nung sa searching algo sa taas
            /**palitan nyo ung inyo 
            ===== FROM =====
            double quickTime = 0.000; 
            ===== TO =====
            long start = System.nanoTime();
            for (int r = 0; r < repeats; r++) {
                quickTime(kung ano ung parameters nyo);
            }
            **/
            double quickTime = 0.000;
            double mergeTime = 0.000;
            double heapTime = 0.000;
            double bubbleTime = 0.000;
            double insertionTime = 0.000;
            double selectionTime = 0.000;
            double shellTime = 0.000;

            // ==== PRINT SORTING TABLE ROW ====
            System.out.printf("| %-10d | %-10.3f | %-10.3f | %-10.3f | %-10.3f | %-10.3f | %-10.3f | %-10.3f |%n",
                    size, quickTime, mergeTime, heapTime, bubbleTime, insertionTime, selectionTime, shellTime);
        }

        System.out.println("-------------------------------------------------------------------------------------------\n");
    }

    // ===== LINEAR SEARCH =====
    public static int linearSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == key) return i;
        return -1;
    }

    // ===== BINARY SEARCH =====
    public static int binarySearch(int[] arr, int key) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == key)
                return mid;
            else if (arr[mid] < key)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }

}
