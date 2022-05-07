package ca.jrvs.practice.codingChallenge;

/**
 * Ticket: https://www.notion.so/jarvisdev/Merge-Sorted-Array-a0e13d7588c642e0a8036695fc3aa676
 */

public class MergeSortedArray {
    public void mergeArray(int[] arr1, int m, int[] arr2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;

        while(i >= 0 && j >= 0){
            if(arr1[i] > arr2[j]){
                arr1[k--] = arr1[i--];
            }
            else{
                arr1[k--] = arr2[j--];
            }
        }
        while(j >= 0){
            arr1[k--] = arr2[j--];
        }
    }
}
