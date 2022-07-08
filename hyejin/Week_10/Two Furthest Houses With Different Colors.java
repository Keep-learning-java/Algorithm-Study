package leetcode;

public class LeetCode_2078 {

    public static int solution(int[] colors) {
        int max = 0;

        for(int std=0; std<colors.length; std++) {
            int target = std+1;
            while(target < colors.length) {
                // 색 같으면
                if(colors[std] == colors[target]) {
                    target++;
                }

                // 색 다르면 거리 갱신
                else {
                    max = Math.max(max, Math.abs(target-std));
                    target++;
                }
            }
        }

        return max;
    }
}
