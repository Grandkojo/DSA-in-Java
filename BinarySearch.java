import java.util.Arrays;

public class BinarySearch {

    public static int binarySearch(int[] array, int target){
        int mid = array.length/2;
        int low = 0;
        int high = array.length;

        for (int i = low; i < high; i++){
            if (array[i] == target){
                return i;
            }
            
            if (array[mid] < target)
                low = mid + 1;
            // else if (array[mid] > target) 
            high = mid + 1;
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < 100; i++){
            array[i] = i+1;
        }

        System.out.println(Arrays.toString(array));
        System.out.println(binarySearch(array, 27));
    }
}
