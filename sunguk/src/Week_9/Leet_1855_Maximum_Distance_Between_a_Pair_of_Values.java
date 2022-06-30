package Week_9;

public class Leet_1855_Maximum_Distance_Between_a_Pair_of_Values {
    /**
     * find possible distance using binary search
     *
     * @return 0 if there is not valid pair
     */
    public int maxDistance(int[] nums1, int[] nums2) {
        int answer = 0;
        int min = 1; // searching for distance of 0 is useless
        int max = nums2.length;
        while (min <= max) {
            int mid = (max + min) / 2;
            boolean isPossible = false;
            for (int i = 0; i < nums1.length; i++) {
                if (i + mid < nums2.length) {
                    if (nums1[i] <= nums2[i + mid]) {
                        answer = mid;
                        isPossible = true;
                        break;
                    }
                }
            }
            if (isPossible) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return answer;
    }
}