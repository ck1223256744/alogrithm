package sort;

import java.util.Arrays;

public class mergesort {
    private static void sort(int[] arr){
        int lo=0;
        int hi=arr.length-1;
        sort(arr,lo,hi);
    }
    private static void sort(int[] arr, int lo, int hi) {
        if (lo == hi) {
            return;
        }
        int mid = (lo + hi) / 2;
        sort(arr, lo, mid);
        sort(arr, mid + 1, hi);
        merge(arr, lo, hi);
    }

    private static void merge(int[] arr, int lo, int hi) {
        int mid = (lo + hi) / 2;
        int left_size = mid - lo + 1;
        int rigth_size = hi - mid;
        int[] left = new int[mid - lo + 1];
        int[] right = new int[hi - mid];
        for (int i = lo; i <= mid; i++) {
            left[i - lo] = arr[i];
        }
        for (int i = mid + 1; i <= hi; i++) {
            right[i - mid - 1] = arr[i];
        }

        int pleft = 0, pright = 0, i = lo;
        while (pleft < left_size && pright < rigth_size) {
            if (left[pleft] < right[pright]) {
                arr[i++] = left[pleft++];
            } else {
                arr[i++] = right[pright++];
            }
        }
        while (pleft < left_size) {
            arr[i++] = left[pleft++];
        }
        while (pright < rigth_size) {
            arr[i++] = right[pright++];
        }
    }

    public static void main(String[] args) {
        int[] arr = { 3, 6, 4, 7, 1, 5, 2, 8 };
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
