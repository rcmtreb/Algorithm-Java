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
            int repeats = 1; // you can increase this for more visible timing results

            // create copies to ensure fair timing
            int[] bubbleCopy = Arrays.copyOf(testArrays[i], testArrays[i].length);
            int[] selectionCopy = Arrays.copyOf(testArrays[i], testArrays[i].length);

            // ==== BUBBLE SORT ====
            long start = System.nanoTime();
            for (int r = 0; r < repeats; r++) {
                bubbleSort(bubbleCopy);
            }
            double bubbleTime = (System.nanoTime() - start) / 1_000_000.0; // ms

            // ==== SELECTION SORT ====
            start = System.nanoTime();
            for (int r = 0; r < repeats; r++) {
                selectionSort(selectionCopy);
            }
            double selectionTime = (System.nanoTime() - start) / 1_000_000.0; // ms

            // placeholders for other sorts
            double quickTime = 0.000;
            double mergeTime = 0.000;
            double heapTime = 0.000;
            double insertionTime = 0.000;
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

    // ===== BUBBLE SORT =====
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            // Optimization: stop if already sorted
            if (!swapped) break;
        }
    }

    // ===== SELECTION SORT =====
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }
}
