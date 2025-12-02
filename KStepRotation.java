import java.util.Arrays;

public class KStepRotation {

    public static int[] EfficientKStepRotationTempArray(int[] nums, int k)
    {
        int arrLength =  nums.length;

        //normalize steps
        k = k % arrLength;
        int[] tempArray = new int[arrLength];

        //loop through according the number of steps
        for (int i = 0; i < k; i++)
        {
            //calculate new index 
            int newIndex = (i + k) % arrLength;

            //insert value in the new array
            tempArray[newIndex] = nums[i];
        }
    
        return tempArray;

    }

    public static void EfficientKStepRotationInPlace(int[] nums, int k)
    {
        int arrLength = nums.length;

        k = k % arrLength;
        int[] rotated = new int[arrLength];
        
        for (int i = 0; i < k; i++)
        {
            rotated[(i + k) % arrLength] = nums[i];
        }

        System.arraycopy(rotated, k, nums, arrLength, arrLength);
    }

    public static void main(String[] args) {
        
        int[] nums = new int[]{2,3,4};

        System.out.println(Arrays.toString(EfficientKStepRotationTempArray(nums, 2)));
    }
}