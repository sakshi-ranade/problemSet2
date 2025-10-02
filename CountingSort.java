import java.util.Arrays;
import java.util.Random;

public class CountingSort {
    //======= HELPER FUNCTIONS ======

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
    private static int[] generateIntegerArray(int size,boolean initVal){
        Random rand = new Random();
        int[] bigArray = new int[size];
        for(int i = 0; i < size; i++){
            if(!initVal)
                bigArray[i] = rand.nextInt(size) + 1;
            else
                bigArray[i] = 0;
        }
        return bigArray;
    }
    //  Returns the maximum value of a given array
    public static int getMaxValue(int[] A) {
        int max = A[0];
        for (int i = 1; i < A.length; i++) {
            if (A[i] > max) {
                max = A[i];
            }
        }
        return max;
    }
    // ======== COUNTING SORT FUNCTION ========
    private static void countingSort(int[] A, int[] B, int k) {
        // Initializing the counting array with size of maximum value + 1 because of 0th index
        int[] C = generateIntegerArray(k+1, true);
        if(A.length <= 100){
            System.out.println("Counting Array after initialize : "+Arrays.toString(C));
        }
        // Counting the number of occurrence from the main array A
        for (int j = 0; j < A.length; j++) {
            C[A[j]]++;
        }
        if(A.length <= 100){
            System.out.println("Counting Array : "+Arrays.toString(C));
        }
        // Calculate the prefix sum at every index to achieve stability
        for (int i = 1; i < C.length; i++) {
            C[i] += C[i-1];
        }
        if(A.length <= 100){
            System.out.println("Cumulative Array : "+Arrays.toString(C));
        }
        // Traverse and update the array B with new sorted positions of the elements from Array A
        for (int j = A.length-1; j >= 0; j--) {
            B[C[A[j]]-1] = A[j];
            C[A[j]]--;
        }
    }
    public static void main(String[] args) {
        // Sample Test cases of datasets
        int[][] datasets = {
                {8, 4, 7, 1, 9, 3, 2, 6, 5},
                {1, 3, 6, 9, 10, 11, 12, 13, 14},
                {18, 16, 14, 12, 10, 8, 6, 4, 2},
                {2, 5, 3, 2, 5, 5, 2 ,6, 3}
        };

        // Testing the above datasets runtime
        for(int i = 0; i < datasets.length; i++){
            int[] array = Arrays.copyOf(datasets[i], datasets[i].length);

            System.out.println("\n =============== DATASET " + (i+1) + "=================");
            System.out.println("Array: " + Arrays.toString(array));
            int[] sortedArray = new int[array.length];
            int maxValue = getMaxValue(array);
            long startTime = System.nanoTime();
            countingSort(array, sortedArray, maxValue);
            long endTime = System.nanoTime();
            System.out.println("Sorted Array "+Arrays.toString(sortedArray));
            System.out.println("Running Time: "+ runTimeFormatting(endTime - startTime));
        }


        // Big dataset of random integers
        int[] dataset5 = generateIntegerArray(100000,false);
        int[] sortedDataset = generateIntegerArray(100000, true);
        int maxValueOfSet = getMaxValue(dataset5);
        System.out.println("\n ================= DATASET 5 ===================");
        long startTime = System.nanoTime();
        countingSort(dataset5,sortedDataset,maxValueOfSet);
        long endTime = System.nanoTime();
        System.out.println("Running Time: "+ runTimeFormatting(endTime - startTime));

    }
}
