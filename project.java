import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.time.Duration;
import java.time.Instant;

public class SortingComparison {
    public static void main(String[] args) {
        final int size = 50000; // Size of the arry is 50000
        Random random = new Random(); // this code is to generate the number(array)
        int[] array = new int[size]; // declaring an array of 50000 size
        List<Integer> myList = new ArrayList<>(); // changing array to the list

        // to fill the array with random integers
        for (int a = 0; a < size; a++) {
            array[a] = random.nextInt();
        }
        // this is to fill the list using the same array values
        for (int value : array) {
            myList.add(value);
        }

        for (int t = 0; t < 10; t++) {
            System.out.println("Time Stamp: " + (t + 1) + "\n");

            // calculating Time for Bubble Sort
            int[] bubbleArray = array.clone(); // cloning the array for independent sorting
            Instant startTimeForBubble = Instant.now();//here 
            bubbleSort(bubbleArray);
            Instant stopTimeForBubble = Instant.now();
            long durationBubble = Duration.between(startTimeForBubble, stopTimeForBubble).toNanos();
            System.out.println("Time for Bubble Sort is: " + durationBubble + " Nano's");

            //calculating Time for Insertion Sort
            int[] insertionArray = array.clone(); // cloning the array for independent sorting
            Instant startTimeForInsertion = Instant.now();
            insertionSort(insertionArray);
            Instant stopTimeForInsertion = Instant.now();
            long durationInsertion = Duration.between(startTimeForInsertion, stopTimeForInsertion).toNanos();
            System.out.println("Time for Insertion Sort is: " + durationInsertion + " Nano's");

            // Time Merge Sort
            List<Integer> mergeList = new ArrayList<>(myList); // Clonig the list for independent sorting
            Instant startTimeForMerge = Instant.now();
            mergeList = mergeSort(mergeList);
            Instant stopTimeForMerge = Instant.now();
            long durationMerge = Duration.between(startTimeForMerge, stopTimeForMerge).toNanos();
            System.out.println("Time for Merge Sort is: " + durationMerge + " Nano's");

            System.out.println();
        }
    }

    // This is Bubble Sort algo using from book
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // This is insertion sort algo
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int j = 1; j < n; j++) {
            int key = arr[j];
            int i = j - 1;
            while (i >= 0 && arr[i] > key) {
                arr[i + 1] = arr[i];
                i = i - 1;
            }
            arr[i + 1] = key;
        }
    }

    // mergesort 
    public static List<Integer> mergeSort(List<Integer> L) {
        if (L.size() <= 1) {
            return L;
        }

        int mid = L.size() / 2;
        List<Integer> L1 = new ArrayList<>(L.subList(0, mid));
        List<Integer> L2 = new ArrayList<>(L.subList(mid, L.size()));

        L1 = mergeSort(L1);
        L2 = mergeSort(L2);

        return merge(L1, L2);
    }

    // this function is to merge two sorted list
    public static List<Integer> merge(List<Integer> L1, List<Integer> L2) {
        List<Integer> L = new ArrayList<>();

        while (!L1.isEmpty() && !L2.isEmpty()) {
            if (L1.get(0) < L2.get(0)) {
                L.add(L1.remove(0));
            } else {
                L.add(L2.remove(0));
            }
        }

        // remained elements
        L.addAll(L1);
        L.addAll(L2);

        return L;
    }
}
