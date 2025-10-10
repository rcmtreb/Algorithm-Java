import java.util.Random;
import java.util.Arrays;

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

            // ==== QUICK SORT ====
            long start = System.nanoTime();
            quickSort(quickCopy, 0, quickCopy.length - 1);
            double quickTime = (System.nanoTime() - start) / 1_000_000.0;

            // ==== MERGE SORT ====
            start = System.nanoTime();
            mergeSort(mergeCopy, 0, mergeCopy.length - 1);
            double mergeTime = (System.nanoTime() - start) / 1_000_000.0;

            // ==== HEAP SORT ====
            start = System.nanoTime();
            heapSort(heapCopy);
            double heapTime = (System.nanoTime() - start) / 1_000_000.0;

            // ==== BUBBLE SORT ====
            start = System.nanoTime();
            bubbleSort(bubbleCopy);
            double bubbleTime = (System.nanoTime() - start) / 1_000_000.0;

            // ==== INSERTION SORT ====
            start = System.nanoTime();
            insertionSort(insertionCopy);
            double insertionTime = (System.nanoTime() - start) / 1_000_000.0;

            // ==== SELECTION SORT ====
            start = System.nanoTime();
            selectionSort(selectionCopy);
            double selectionTime = (System.nanoTime() - start) / 1_000_000.0;

            // ==== SHELL SORT ====
            start = System.nanoTime();
            shellSort(shellCopy);
            double shellTime = (System.nanoTime() - start) / 1_000_000.0;

            // ==== PRINT RESULTS ====
            System.out.printf("| %-10d | %-10.3f | %-10.3f | %-10.3f | %-10.3f | %-10.3f | %-10.3f | %-10.3f |%n",
                    size, quickTime, mergeTime, heapTime, bubbleTime,
                    insertionTime, selectionTime, shellTime);
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

    // ===== QUICK SORT =====
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // ===== MERGE SORT =====
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i) L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) arr[k++] = L[i++];
            else arr[k++] = R[j++];
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // ===== HEAP SORT =====
    public static void heapSort(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest])
            largest = left;

        if (right < n && arr[right] > arr[largest])
            largest = right;

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }

    // ===== BUBBLE SORT =====
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    // ===== INSERTION SORT =====
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // ===== SELECTION SORT =====
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx])
                    minIdx = j;
            }
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }

    // ===== SHELL SORT =====
    public static void shellSort(int[] arr) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
    }
}
