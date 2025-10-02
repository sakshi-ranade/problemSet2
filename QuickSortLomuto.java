import java.util.Arrays;
import java.util.Random;

public class QuickSortLomuto {

  // Global counters
  private static long swaps;
  private static long comparisons;

  // ===== HELPER FUNCTIONS =====

  // Swap two numbers
  private static void swap(int[] array, int a, int b){
    int temp = array[b];
    array[b] = array[a];
    array[a] = temp;
    swaps++;
  }

  // Print array
  private static void printArray(int[] array){
    for(int num : array){
      System.out.print(num + " ");
    }
    System.out.println();
  }

  // Reset counters
  private static void resetCounters(){
    swaps = 0;
    comparisons = 0;
  }

  // Format runtime before printing
  private static String runTimeFormatting(long runTime){
    if(runTime < 1000){
      return runTime + " nanoseconds";
    }
    else if (runTime < 1000000){
      return (runTime/ 1000) + " microseconds";
    } else if (runTime < 1000000000) {
      return (runTime / 1000000) + " milliseconds";
    }
    else {
      return (runTime / 1000000000) + " seconds";
    }
  }

  // Generate random array
  private static int[] generateIntegerArray(int size){
    int upperbound = 100000;
    Random rand = new Random();
    int[] bigArray = new int[size];
    for(int i = 0; i < size; i++){
      bigArray[i] = rand.nextInt(upperbound) + 1;
    }
    return bigArray;
  }

  // ===== QUICK SORT (Lomuto Partition) =====

  private static int lomutoPartition(int[] arr, int p, int r){
    int pivot = arr[r];
    int i = p - 1;

    for(int j = p; j < r; j++){
      comparisons++;  // each comparison
      if(arr[j] <= pivot){
        i++;
        swap(arr, i, j);
      }
    }
    swap(arr, i + 1, r); // final pivot swap
    return i + 1;
  }

  private static void quickSort(int[] arr, int p, int r){
    if(p < r){
      int pi = lomutoPartition(arr, p, r);
      quickSort(arr, p, pi - 1);
      quickSort(arr, pi + 1, r);
    }
  }

  // ===== MAIN =====
  public static void main(String[] args) {
    // Define datasets
    int[][] datasets = {
        {8, 4, 7, 1, 9, 3, 2, 6, 5},
        {1, 3, 6, 9, 10, 11, 12, 13, 14},
        {18, 16, 14, 12, 10, 8, 6, 4, 2},
        {2, 5, 3, 2, 5, 5, 2 ,6, 3}
    };

    // Sort the datasets
    for(int i = 0; i < datasets.length; i++){
      int[] array = Arrays.copyOf(datasets[i], datasets[i].length);
      resetCounters();

      System.out.println("\n =============== DATASET " + (i+1) + "=================");
      System.out.println("Array: ");
      printArray(array);

      long startTime = System.nanoTime();
      quickSort(array, 0, array.length - 1);
      long endTime = System.nanoTime();

      System.out.println("Sorted Array: ");
      printArray(array);

      System.out.println("Comparisons: "+ comparisons);
      System.out.println("Swaps: "+ swaps);
      System.out.println("Running Time: "+ runTimeFormatting(endTime - startTime));
    }

    // Big dataset test
    int[] dataset5 = generateIntegerArray(100000);
    resetCounters();
    System.out.println("\n ================= DATASET 5 ===================");

    long startTime = System.nanoTime();
    quickSort(dataset5, 0, dataset5.length - 1);
    long endTime = System.nanoTime();

    System.out.println("Comparisons: "+ comparisons);
    System.out.println("Swaps: "+ swaps);
    System.out.println("Running Time: "+ runTimeFormatting(endTime - startTime));
  }
}
