import java.util.Arrays;

public class MergeSort {

    public static void mergeSortOriginal(int[] array) {
        int arrLength = array.length;
        int midIndex = arrLength / 2;
        int[] leftHalf = new int[midIndex];
        int[] rightHalf = new int[arrLength - midIndex];

        if (arrLength < 2) {
            return;
        }
        // left half of array
        for (int i = 0; i < midIndex; i++) {
            leftHalf[i] = array[i];
        }

        // right half of array
        for (int j = midIndex; j < arrLength; j++) {
            rightHalf[j - midIndex] = array[j];
        }

        // mergesort sub arrays
        mergeSortOriginal(leftHalf);
        mergeSortOriginal(rightHalf);

        // merge them all together
        mergeOriginal(array, leftHalf, rightHalf);

    }

    public static void mergeOriginal(int[] array, int[] leftHalf, int[] rightHalf) {
        int leftSize = leftHalf.length;
        int rightSize = rightHalf.length;
        // initialize counters for three arrays
        int i = 0, j = 0, k = 0;

        while (i < leftSize && j < rightSize) {
            if (leftHalf[i] <= rightHalf[j]) {
                array[k] = leftHalf[i];
                i++;
            } else {
                array[k] = rightHalf[j];
                j++;
            }
            k++;
        }

        while (i < leftSize) {
            array[k] = leftHalf[i];
            i++;
            k++;
        }

        while (j < rightSize) {
            array[k] = rightHalf[j];
            j++;
            k++;
        }
    }

    public static void mergeSort(int[] array, int[] index, int[] counts) {
        if (array.length < 2)
            return;

        int mid = array.length / 2;

        int[] left = new int[mid];
        int[] right = new int[array.length - mid];

        int[] leftIndex = new int[mid];
        int[] rightIndex = new int[array.length - mid];

        for (int i = 0; i < mid; i++) {
            left[i] = array[i];
            leftIndex[i] = index[i];
        }

        for (int i = mid; i < array.length; i++) {
            right[i - mid] = array[i];
            rightIndex[i - mid] = index[i];
        }

        mergeSort(left, leftIndex, counts);
        mergeSort(right, rightIndex, counts);

        merge(array, index, left, right, leftIndex, rightIndex, counts);
    }

    public static void merge(
        int[] array,
        int[] index,
        int[] left,
        int[] right,
        int[] leftIndex,
        int[] rightIndex,
        int[] counts) {

    int i = 0, j = 0, k = 0;

    while (i < left.length && j < right.length) {
        if (left[i] <= right[j]) {
            array[k] = left[i];
            index[k] = leftIndex[i];
            i++;
        } else {
            // right[j] is smaller and to the right of left[i]
            counts[leftIndex[i]] += (right.length - j);

            array[k] = right[j];        
            index[k] = rightIndex[j];
            j++;                      
        }
        k++;
    }

    while (i < left.length) {
        array[k] = left[i];
        index[k] = leftIndex[i];
        i++;
        k++;
    }

    while (j < right.length) {
        array[k] = right[j];
        index[k] = rightIndex[j];
        j++;
        k++;
    }
}

    public static void countInversions(int[] array, int[] counts) {

    }
    // public static void main(String[] args) {
    // int[] nums = new int[]{5, 2, 6, 1};
    // int[] counts = new int[nums.length];

    // System.out.println(Arrays.toString(nums));
    // mergeSort(nums, counts);
    // // countInversions(nums, counts);
    // System.out.println(Arrays.toString(nums));

    // }

    public static void main(String[] args) {
        int[] nums = { 5, 2, 6, 1 };
        int[] counts = new int[nums.length];

        int[] index = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            index[i] = i;
        }

        mergeSort(nums, index, counts);

        System.out.println(Arrays.toString(counts)); // [2, 1, 1, 0]
    }

}
