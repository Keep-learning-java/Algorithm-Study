package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_22251 {

    static int n, k, p, x;
    static final int[][] LED = { // 위에서부터, ON 1 OFF 0
            { 1, 1, 1, 0, 1, 1, 1 }, // 0
            { 0, 0, 1, 0, 0, 1, 0 }, // 1
            { 1, 0, 1, 1, 1, 0, 1 }, // 2
            { 1, 0, 1, 1, 0, 1, 1 }, // 3
            { 0, 1, 1, 1, 0, 1, 0 }, // 4
            { 1, 1, 0, 1, 0, 1, 1 }, // 5
            { 1, 1, 0, 1, 1, 1, 1 }, // 6
            { 1, 0, 1, 0, 0, 1, 0 }, // 7
            { 1, 1, 1, 1, 1, 1, 1 }, // 8
            { 1, 1, 1, 1, 0, 1, 1 } // 9
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());
        p = Integer.parseInt(stk.nextToken());
        x = Integer.parseInt(stk.nextToken());

        System.out.println(onoff(numberToLed(x)));
    }

    public static int onoff(int[] xLed) {
        int result = 0;

        // 1층 ~ n층 탐색
        for(int target=1; target<=n; target++) {
            if(x == target)
                continue;

            int[] targetLed = numberToLed(target);
            // x를 target으로 제한된 횟수 안에 바꿀 수 있는가?
            if(changeable(targetLed, xLed)) {
                result++;
            }
        }


        return result;
    }

    public static boolean changeable(int[] targetLed, int[] xLed) {
        // k자리수 검사
        int count = 0;
        for(int a=0; a<k; a++) {
            int num = xLed[a];
            // LED 하나씩
            for(int b=0; b<LED[0].length; b++) {
                // 몇개의 led가 다른지?
                if(LED[num][b] != LED[targetLed[a]][b]) {
                    count++;
                }
            }
        }

        if(count > p) {
            return false;
        }
        return true;
    }


    // 숫자를 led 자리수로 : 501 -> 0501
    public static int[] numberToLed(int number) {
        int[] result = new int[k];
        int index = k-1;
        while(number > 0) {
            result[index--] = number%10;
            number /= 10;
        }
        return result;
    }

}
