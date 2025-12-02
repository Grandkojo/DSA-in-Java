import java.util.Arrays;

public class MoveZeroesInPlace {
    
    //[0, 1, 0, 20, 15, 3, 0, 4, 0]
    public static int[] moveZeroesInPlace(int[] nums)
    {
        int nz = 0;

        for (int i = 0; i < nums.length-1; i++)
        {
            if (nums[i] != 0)
            {
                nums[nz] = nums[i];
                nums[i] = 0;
                nz++;
            }
        }

        return nums;
    }

    public static void main(String[] args) {
        int nums[] = new int[]{0, 1, 0, 20, 0, 0, 0, 12, 0, 15, 3, 0, 4, 0};
        System.out.println(Arrays.toString(moveZeroesInPlace(nums)));
    }
}
