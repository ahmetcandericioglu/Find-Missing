import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


// Author: Ahmetcan Dericioglu

public class AhmetcanDericioglu_Q1_code {

    static void swap(int[] arr, int f, int s){
        int temp = arr[f];
        arr[f] = arr[s];
        arr[s] = temp;
    }

    static int partition(int arr[], int left, int right) {
            int pivotLoc = (left+right)/2;

            int i = left;
            int j = right;

            while (i <= j){
                if (arr[i] <= arr[pivotLoc])
                    i++;
                else if (arr[j] >= arr[pivotLoc])
                    j--;
                else {
                    swap(arr,i,j);
                    i++;
                    j--;
                }
            }
            if (pivotLoc < j)
            {
                swap(arr, j, pivotLoc);
                pivotLoc = j;
            }
            else if (pivotLoc > i){
                swap(arr, i, pivotLoc);
                pivotLoc = i;
            }
            return pivotLoc;
    }

    static int findMissing(int arr[], int left, int right) {

        if(left>right){
            if (right < 0)         // if right is lower than 0 missing number is 1
                return 1;
            else
                return arr[right]+1;    // if right is greater than 0
        }
        int pivotIndex = partition(arr, left, right);
        int pivot = arr[pivotIndex];
        if (pivot == pivotIndex + 2) {
            return findMissing(arr, left, pivotIndex - 1);
        }
        else
            return findMissing(arr, pivotIndex + 1, right);
        
    }

    static void shuffleArray(int[] ar) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    public static void main(String[] args) {

        // you can use this to try 10k, 20k, 30k, ... arrays

        /*
        int[] array = new int[10000];
        int rand = (int)(Math.random() * 10000) + 1;
        for (int i = 1; i<10001; i++){
            array[i-1] = i;
        }
        shuffleArray(array);
        System.out.println(array[rand]);
        array[rand] = 10001;
        */

        int[] array = {12,7,10,9,5,4,11,1,6,8,2};

        Instant start = Instant.now();
        findMissing(array, 0 , array.length-1);
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);


        System.out.println("Time taken: "+ timeElapsed.toNanos() +" nanoseconds");
        System.out.println(findMissing(array, 0, array.length-1) + " is the missing number in your array");
    }
}
