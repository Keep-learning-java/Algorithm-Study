import java.io.*;
import java.util.*;

public class BOJ_22251 {
    static int N, K, P, X;
    static int[] sevenSegment = new int[] {
        0b1110111, // 0
        0b0100100, // 1
        0b1011110, // 2
        0b1101110, // 3
        0b0101101, // 4
        0b1101011, // 5
        0b1111011, // 6
        0b0100110, // 7
        0b1111111, // 8
        0b1101111  // 9
    };
    static int[][] count = new int[10][10];
    static void init() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                count[i][j] = Integer.bitCount(sevenSegment[i] ^ sevenSegment[j]);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        init();
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            int from = X, to = i;
            int cnt = 0;
            for (int j = 0; j < K; j++) {
                cnt += count[from % 10][to % 10];
                from /= 10;
                to /= 10;
            }
            if (cnt > 0 && cnt <= P) ans++;
        }
        System.out.println(ans);
    }
}
