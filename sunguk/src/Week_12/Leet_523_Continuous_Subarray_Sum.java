package Week_12;

import java.util.Arrays;

public class Leet_523_Continuous_Subarray_Sum {
    public boolean checkSubarraySum(int[] nums, int k) {
        int[] sumNums = Arrays.copyOf(nums, nums.length);
        int i = 1;
        int startIndex = 1;
        while (i < nums.length) {
            sumNums[i - startIndex] += nums[i];
            if (sumNums[i - startIndex] % k == 0) return true;
            i++;
            if (i >= nums.length) {
                startIndex++;
                i = startIndex;
            }
        }
        return false;
    }
}
