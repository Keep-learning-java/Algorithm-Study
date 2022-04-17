import java.io.*;
import java.util.*;

public class BOJ_2302 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        dp[0] = dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        int answer = 1, s = 1;
        for (int i = 1; i <= M; i++) {
            int e = Integer.parseInt(br.readLine());
            answer *= dp[e - s];
            s = e + 1;
        }
        answer *= dp[N + 1 - s];
        System.out.println(answer);
    }
}