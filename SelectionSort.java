import java.util.Arrays;
import java.util.Random;

public class SelectionSort {

  //Global variables
  private static long swaps;
  private static long comparisons;

  //======= HELPER FUNCTIONS ======
  // Swaps two numbers
  private static void swap(int[] array, int a, int b){
    int temp = array[b]; //uses temp variable to swap two elements in the array
    array[b] = array[a];
    array[a] = temp;
    swaps++;
  }

  // Print array function
  private static void printArray(int[] array){
    int size = array.length;
    for(int i = 0; i < size; i++){
      System.out.print(array[i] + " "); //prints all the elements in the array
    }
    System.out.println( ); //new line after printing the array
  }

  // Function to reset counters before calling for each dataset
  private static void resetCounters(){
    swaps = 0;
    comparisons = 0;
  }

  // Format run time before printing
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

  // Random integers array generator
  private static int[] generateIntegerArray(int size){
    int upperbound = 100000;
    Random rand = new Random();
    int[] bigArray = new int[size];
    for(int i = 0; i < size; i++){
      bigArray[i] = rand.nextInt(upperbound) + 1;
    }
    return bigArray;
  }


  // ======== SELECTION SORT FUNCTION ========

  private static void selectionSort(int[] array){
    int size = array.length;
    for(int i = 0; i < size - 1; i++){
      int minIndex = i; //initialize i as minIndex and compare with the rest of the remaining unsorted elements
      for(int j = i+1; j < size; j++){
        comparisons++; //increment comparisons
            if(array[j] < array[minIndex]){
              minIndex = j; //update minIndex if current element < ith element
            }
      }
      if(minIndex != i) //prevents additional swaps when minIndex == i
        swap(array, i, minIndex); //swap once after scanning through the unsorted elements
    }
  }

  public static void main(String[] args) {
    //defining first 4 datasets
    int[][] datasets = {
        {8, 4, 7, 1, 9, 3, 2, 6, 5},
        {1, 3, 6, 9, 10, 11, 12, 13, 14},
        {18, 16, 14, 12, 10, 8, 6, 4, 2},
        {2, 5, 3, 2, 5, 5, 2 ,6, 3}
    };

    // sorting above datasets
    for(int i = 0; i < datasets.length; i++){
      int[] array = Arrays.copyOf(datasets[i], datasets[i].length);
      resetCounters();

      System.out.println("\n =============== DATASET " + (i+1) + "=================");
      System.out.println("Array: ");
      printArray(array);

      long startTime = System.nanoTime();
      selectionSort(array);
      long endTime = System.nanoTime();

      System.out.println("Sorted Array ");
      printArray(array);

      System.out.println("Comparisons: "+ comparisons);
      System.out.println("Swaps: "+ swaps);
      System.out.println("Running Time: "+ runTimeFormatting(endTime - startTime));
    }

    // Big dataset of random integers
    int[] dataset5 = generateIntegerArray(100000);
    resetCounters();
    System.out.println("\n ================= DATASET 5 ===================");
    //System.out.println("Array: ");
    //printArray(array);

    long startTime = System.nanoTime();
    selectionSort(dataset5);
    long endTime = System.nanoTime();

    //System.out.println("Sorted Array ");
    //printArray(array);

    System.out.println("Comparisons: "+ comparisons);
    System.out.println("Swaps: "+ swaps);
    System.out.println("Running Time: "+ runTimeFormatting(endTime - startTime));

  }
}



