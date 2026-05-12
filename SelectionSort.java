
public class SelectionSort {

    // Function to perform Selection Sort
    static void selectionSort(int arr[]) {

        int n = arr.length;

        // Traverse through entire array
        for (int i = 0; i < n - 1; i++) {

            // Assume current index has minimum element
            int minIndex = i;

            // Find the minimum element in remaining array
            for (int j = i + 1; j < n; j++) {

                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap the minimum element with current element
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    // Function to print array
    static void printArray(int arr[]) {

        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    public static void main(String[] args) {

        int arr[] = {64, 25, 12, 22, 11};

        System.out.println("Original Array:");
        printArray(arr);

        // Call selection sort
        selectionSort(arr);

        System.out.println("\nSorted Array:");
        printArray(arr);
    }
}