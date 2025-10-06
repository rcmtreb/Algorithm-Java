import java.util.InputMismatchException;
import java.util.Scanner;

public class AlgorithmMenu {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice = -1;

        while (true) {
            System.out.println("=====================================");
            System.out.println("        SORTING ALGORITHMS LIST      ");
            System.out.println("=====================================");
            System.out.println("1. Quick Sort");
            System.out.println("2. Merge Sort");
            System.out.println("3. Heap Sort");
            System.out.println("4. Bubble Sort");
            System.out.println("5. Insertion Sort");
            System.out.println("6. Selection Sort");
            System.out.println("7. Shell Sort");
            System.out.println("0. Exit");
            System.out.println("=====================================");
            System.out.print("Enter your choice: ");

            try {
                choice = input.nextInt();

                if (choice == 0) {
                    System.out.println("\nExiting program... ");
                    break;
                } else if (choice >= 1 && choice <= 7) {
                    System.out.println("\nYou selected option " + choice + ".");
                    System.out.println("bolaga! Wala pa nganing implementation\n");
                } else {
                    System.out.println("\nInvalid choice! Please select a valid option.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input! Please enter a number.\n");
                input.nextLine();
            }
        }

        input.close();
    }
}
