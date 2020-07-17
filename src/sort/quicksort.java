package sort;

import java.util.Arrays;

public class quicksort {
    private static void sort(int[] arr) {
        int lo = 0;
        int hi = arr.length - 1;
        sort(arr, lo, hi);
    }

    private static void sort(int[] arr, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int partition = partition(arr, lo, hi);
        System.out.println(partition);
        sort(arr, lo, partition - 1);
        sort(arr, partition + 1, hi);
    }

    private static int partition(int[] arr, int lo, int hi) {
        int key = arr[lo];
        int left = lo;
        int right = hi + 1;
        while (true) {
            while (key < arr[--right]) {
                if (right == lo) {
                    break;
                }
            }
            while (key > arr[++left]) {
                if (left == hi) {
                    break;
                }
            }
            if (left >= right) {
                break;
            } else {
                int tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;
            }
        }
        int tmp=arr[lo];
        arr[lo]=arr[right];
        arr[right]=tmp;
        System.out.println(Arrays.toString(arr));
        return right;
    }

    public static void main(String[] args) {
        int[] arr={2,4,7,1,3,8,5,6};
        sort(arr);
//        System.out.println(Arrays.toString(arr));
    }
}
