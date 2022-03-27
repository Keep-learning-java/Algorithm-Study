import java.io.*;
import java.util.*;

public class BOJ_1654 {
    static int K, N;
    static int[] lines;
    public static long f() {
        long lo = 0, hi = 1L << 31;
        while (lo + 1 < hi) {
            long mid = (lo + hi) / 2;
            int count = 0;
            for (int i = 0; i < K; i++) {
                count += lines[i] / mid;
            }
            if (count >= N) lo = mid;
            else hi = mid;
        }
        return lo;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        lines = new int[N];
        for (int i = 0; i < K; i++) {
            lines[i] = Integer.parseInt(br.readLine());
        }
        System.out.println(f());
    }
}
