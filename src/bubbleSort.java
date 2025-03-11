import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class bubbleSort {
    public static int[] createRandomArray(int arrayLength) {
        Random random = new Random();
        int[] array = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = random.nextInt(101); // Random integers between 0 and 100
        }
        return array;
    }

    // Given an integer array and filename, write the array to the file, with each line containing one integer in the array.
    public static void writeArrayToFile(int[] array, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int num : array) {
                writer.write(String.valueOf(num));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // This is the reverse of writeArrayToFile; Given the filename, read the integers (one line per integer) to an array, and return the array
    public static int[] readFileToArray(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            return reader.lines().mapToInt(Integer::parseInt).toArray();
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
            return new int[0]; // Return an empty array in case of error
        }
    }

    // Given an integer array, sort it in-place, i.e., the order of the elements will be changed so that the final array is in sorted order.
    public static void bubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (array[i - 1] > array[i]) {
                    // Swap array[i - 1] and array[i]
                    int temp = array[i - 1];
                    array[i - 1] = array[i];
                    array[i] = temp;
                    swapped = true;
                }
            }
            n--; // Reduce the range of the next pass
        } while (swapped);
    }

    // The main function will handle a user's keyboard input specified in the next session
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the length of the random array: ");
        int length = scanner.nextInt();
        
        int[] randomArray = createRandomArray(length);
        
        System.out.print("Enter the filename to save the array: ");
        String filename = scanner.next();
        
        writeArrayToFile(randomArray, filename);
        System.out.println("Array written to " + filename);
        
        int[] readArray = readFileToArray(filename);
        System.out.println("Array read from file:");
        for (int num : readArray) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        bubbleSort(readArray);
        System.out.println("Sorted array:");
        for (int num : readArray) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        scanner.close();
}
}
