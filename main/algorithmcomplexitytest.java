import java.util.Random;
import java.util.Arrays;

// ===== METRICS CLASS TO TRACK OPERATIONS =====
class SortMetrics {
    public long swaps = 0;
    public long comparisons = 0;
    public double time = 0;
    
    public void reset() {
        swaps = 0;
        comparisons = 0;
        time = 0;
    }
}

public class algorithmcomplexitytest {
    public static void main(String[] args) {
        // ===== INPUT SIZES =====
        int[] sizes = {1000, 5000, 10000, 50000, 100000};
        int[][] testArrays = new int[sizes.length][];
        Random rand = new Random();

        // ===== GENERATE RANDOM ARRAYS =====
        for (int i = 0; i < sizes.length; i++) {
            testArrays[i] = new int[sizes[i]];
            for (int j = 0; j < sizes[i]; j++) {
                testArrays[i][j] = rand.nextInt(1_000_000);
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
            int repeats = 1000;

            // ==== LINEAR SEARCH ====
            long start = System.nanoTime();
            for (int r = 0; r < repeats; r++) {
                linearSearch(arr, key);
            }
            double linearTime = (System.nanoTime() - start) / 1_000_000.0;

            // ==== BINARY SEARCH ====
            start = System.nanoTime();
            for (int r = 0; r < repeats; r++) {
                binarySearch(sorted, key);
            }
            double binaryTime = (System.nanoTime() - start) / 1_000_000.0;

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
            int[] arr = testArrays[i];

            // copies for each algorithm
            int[] quickCopy = Arrays.copyOf(arr, arr.length);
            int[] mergeCopy = Arrays.copyOf(arr, arr.length);
            int[] heapCopy = Arrays.copyOf(arr, arr.length);
            int[] bubbleCopy = Arrays.copyOf(arr, arr.length);
            int[] insertionCopy = Arrays.copyOf(arr, arr.length);
            int[] selectionCopy = Arrays.copyOf(arr, arr.length);
            int[] shellCopy = Arrays.copyOf(arr, arr.length);

            // Metrics for each algorithm
            SortMetrics quickMetrics = new SortMetrics();
            SortMetrics mergeMetrics = new SortMetrics();
            SortMetrics heapMetrics = new SortMetrics();
            SortMetrics bubbleMetrics = new SortMetrics();
            SortMetrics insertionMetrics = new SortMetrics();
            SortMetrics selectionMetrics = new SortMetrics();
            SortMetrics shellMetrics = new SortMetrics();

            // ==== QUICK SORT ====
            long start = System.nanoTime();
            quickSort(quickCopy, 0, quickCopy.length - 1, quickMetrics);
            quickMetrics.time = (System.nanoTime() - start) / 1_000_000.0;

            // ==== MERGE SORT ====
            start = System.nanoTime();
            mergeSort(mergeCopy, 0, mergeCopy.length - 1, mergeMetrics);
            mergeMetrics.time = (System.nanoTime() - start) / 1_000_000.0;

            // ==== HEAP SORT ====
            start = System.nanoTime();
            heapSort(heapCopy, heapMetrics);
            heapMetrics.time = (System.nanoTime() - start) / 1_000_000.0;

            // ==== BUBBLE SORT ====
            start = System.nanoTime();
            bubbleSort(bubbleCopy, bubbleMetrics);
            bubbleMetrics.time = (System.nanoTime() - start) / 1_000_000.0;

            // ==== INSERTION SORT ====
            start = System.nanoTime();
            insertionSort(insertionCopy, insertionMetrics);
            insertionMetrics.time = (System.nanoTime() - start) / 1_000_000.0;

            // ==== SELECTION SORT ====
            start = System.nanoTime();
            selectionSort(selectionCopy, selectionMetrics);
            selectionMetrics.time = (System.nanoTime() - start) / 1_000_000.0;

            // ==== SHELL SORT ====
            start = System.nanoTime();
            shellSort(shellCopy, shellMetrics);
            shellMetrics.time = (System.nanoTime() - start) / 1_000_000.0;

            // ==== PRINT RESULTS ====
            System.out.printf("| %-10d | %-10.3f | %-10.3f | %-10.3f | %-10.3f | %-10.3f | %-10.3f | %-10.3f |%n",
                    size, quickMetrics.time, mergeMetrics.time, heapMetrics.time, bubbleMetrics.time,
                    insertionMetrics.time, selectionMetrics.time, shellMetrics.time);
        }

        System.out.println("-------------------------------------------------------------------------------------------\n");

        // ===== DETAILED ANALYSIS: COMPARISONS & SWAPS PER ARRAY SIZE =====
        System.out.println("===========================================================================================");
        System.out.println("         DETAILED ANALYSIS: COMPARISONS AND SWAPS FOR EACH ARRAY SIZE");
        System.out.println("===========================================================================================\n");

        for (int i = 0; i < testArrays.length; i++) {
            int size = sizes[i];
            int[] arr = testArrays[i];

            System.out.println("─────────────────────────────────────────────────────────────────────────────");
            System.out.println("ARRAY SIZE: " + size + " elements");
            System.out.println("─────────────────────────────────────────────────────────────────────────────");

            // copies for each algorithm
            int[] quickCopy = Arrays.copyOf(arr, arr.length);
            int[] mergeCopy = Arrays.copyOf(arr, arr.length);
            int[] heapCopy = Arrays.copyOf(arr, arr.length);
            int[] bubbleCopy = Arrays.copyOf(arr, arr.length);
            int[] insertionCopy = Arrays.copyOf(arr, arr.length);
            int[] selectionCopy = Arrays.copyOf(arr, arr.length);
            int[] shellCopy = Arrays.copyOf(arr, arr.length);

            // Metrics for each algorithm
            SortMetrics quickMetrics = new SortMetrics();
            SortMetrics mergeMetrics = new SortMetrics();
            SortMetrics heapMetrics = new SortMetrics();
            SortMetrics bubbleMetrics = new SortMetrics();
            SortMetrics insertionMetrics = new SortMetrics();
            SortMetrics selectionMetrics = new SortMetrics();
            SortMetrics shellMetrics = new SortMetrics();

            // ==== QUICK SORT ====
            long start = System.nanoTime();
            quickSort(quickCopy, 0, quickCopy.length - 1, quickMetrics);
            quickMetrics.time = (System.nanoTime() - start) / 1_000_000.0;

            // ==== MERGE SORT ====
            start = System.nanoTime();
            mergeSort(mergeCopy, 0, mergeCopy.length - 1, mergeMetrics);
            mergeMetrics.time = (System.nanoTime() - start) / 1_000_000.0;

            // ==== HEAP SORT ====
            start = System.nanoTime();
            heapSort(heapCopy, heapMetrics);
            heapMetrics.time = (System.nanoTime() - start) / 1_000_000.0;

            // ==== BUBBLE SORT ====
            start = System.nanoTime();
            bubbleSort(bubbleCopy, bubbleMetrics);
            bubbleMetrics.time = (System.nanoTime() - start) / 1_000_000.0;

            // ==== INSERTION SORT ====
            start = System.nanoTime();
            insertionSort(insertionCopy, insertionMetrics);
            insertionMetrics.time = (System.nanoTime() - start) / 1_000_000.0;

            // ==== SELECTION SORT ====
            start = System.nanoTime();
            selectionSort(selectionCopy, selectionMetrics);
            selectionMetrics.time = (System.nanoTime() - start) / 1_000_000.0;

            // ==== SHELL SORT ====
            start = System.nanoTime();
            shellSort(shellCopy, shellMetrics);
            shellMetrics.time = (System.nanoTime() - start) / 1_000_000.0;

            // Print detailed metrics
            System.out.printf("%-15s | %-15s | %-15s | %-15s%n", "Algorithm", "Comparisons", "Swaps", "Time (ms)");
            System.out.println("─────────────────────────────────────────────────────────────────────────────");
            System.out.printf("%-15s | %15d | %15d | %15.3f%n", "Quick Sort", quickMetrics.comparisons, quickMetrics.swaps, quickMetrics.time);
            System.out.printf("%-15s | %15d | %15d | %15.3f%n", "Merge Sort", mergeMetrics.comparisons, mergeMetrics.swaps, mergeMetrics.time);
            System.out.printf("%-15s | %15d | %15d | %15.3f%n", "Heap Sort", heapMetrics.comparisons, heapMetrics.swaps, heapMetrics.time);
            System.out.printf("%-15s | %15d | %15d | %15.3f%n", "Bubble Sort", bubbleMetrics.comparisons, bubbleMetrics.swaps, bubbleMetrics.time);
            System.out.printf("%-15s | %15d | %15d | %15.3f%n", "Insertion Sort", insertionMetrics.comparisons, insertionMetrics.swaps, insertionMetrics.time);
            System.out.printf("%-15s | %15d | %15d | %15.3f%n", "Selection Sort", selectionMetrics.comparisons, selectionMetrics.swaps, selectionMetrics.time);
            System.out.printf("%-15s | %15d | %15d | %15.3f%n", "Shell Sort", shellMetrics.comparisons, shellMetrics.swaps, shellMetrics.time);
            System.out.println();
        }
        System.out.println("===========================================================================================\n");
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

    // ===== QUICK SORT =====
    public static void quickSort(int[] arr, int low, int high) {
        quickSort(arr, low, high, null);
    }

    private static void quickSort(int[] arr, int low, int high, SortMetrics metrics) {
        if (low < high) {
            int pi = partition(arr, low, high, metrics);
            quickSort(arr, low, pi - 1, metrics);
            quickSort(arr, pi + 1, high, metrics);
        }
    }

    private static int partition(int[] arr, int low, int high, SortMetrics metrics) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (metrics != null) metrics.comparisons++;
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                if (metrics != null) metrics.swaps++;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        if (metrics != null) metrics.swaps++;
        return i + 1;
    }

    // ===== MERGE SORT =====
    public static void mergeSort(int[] arr, int left, int right) {
        mergeSort(arr, left, right, null);
    }

    private static void mergeSort(int[] arr, int left, int right, SortMetrics metrics) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, metrics);
            mergeSort(arr, mid + 1, right, metrics);
            merge(arr, left, mid, right, metrics);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, SortMetrics metrics) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i) L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (metrics != null) metrics.comparisons++;
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
            if (metrics != null) metrics.swaps++;
        }
        while (i < n1) {
            arr[k++] = L[i++];
            if (metrics != null) metrics.swaps++;
        }
        while (j < n2) {
            arr[k++] = R[j++];
            if (metrics != null) metrics.swaps++;
        }
    }

    // ===== HEAP SORT =====
    public static void heapSort(int[] arr) {
        heapSort(arr, null);
    }

    private static void heapSort(int[] arr, SortMetrics metrics) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i, metrics);

        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            if (metrics != null) metrics.swaps++;

            heapify(arr, i, 0, metrics);
        }
    }

    private static void heapify(int[] arr, int n, int i, SortMetrics metrics) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (metrics != null) metrics.comparisons++;
        if (left < n && arr[left] > arr[largest])
            largest = left;

        if (metrics != null) metrics.comparisons++;
        if (right < n && arr[right] > arr[largest])
            largest = right;

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            if (metrics != null) metrics.swaps++;
            heapify(arr, n, largest, metrics);
        }
    }

    // ===== BUBBLE SORT =====
    public static void bubbleSort(int[] arr) {
        bubbleSort(arr, null);
    }

    private static void bubbleSort(int[] arr, SortMetrics metrics) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (metrics != null) metrics.comparisons++;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    if (metrics != null) metrics.swaps++;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    // ===== INSERTION SORT =====
    public static void insertionSort(int[] arr) {
        insertionSort(arr, null);
    }

    private static void insertionSort(int[] arr, SortMetrics metrics) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                if (metrics != null) metrics.comparisons++;
                arr[j + 1] = arr[j];
                if (metrics != null) metrics.swaps++;
                j--;
            }
            arr[j + 1] = key;
            if (metrics != null) metrics.swaps++;
        }
    }

    // ===== SELECTION SORT =====
    public static void selectionSort(int[] arr) {
        selectionSort(arr, null);
    }

    private static void selectionSort(int[] arr, SortMetrics metrics) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (metrics != null) metrics.comparisons++;
                if (arr[j] < arr[minIdx])
                    minIdx = j;
            }
            if (minIdx != i) {
                int temp = arr[minIdx];
                arr[minIdx] = arr[i];
                arr[i] = temp;
                if (metrics != null) metrics.swaps++;
            }
        }
    }

    // ===== SHELL SORT =====
    public static void shellSort(int[] arr) {
        shellSort(arr, null);
    }

    private static void shellSort(int[] arr, SortMetrics metrics) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    if (metrics != null) metrics.comparisons++;
                    arr[j] = arr[j - gap];
                    if (metrics != null) metrics.swaps++;
                }
                arr[j] = temp;
                if (metrics != null) metrics.swaps++;
            }
        }
    }
}