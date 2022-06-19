import java.io.*;
import java.util.*;

public class BOJ_22254 {
    static int N, X;
    static int[] times;
    static boolean isPossible(int m) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int bound = Math.min(N, m);
        for (int i = 1; i <= bound; i++) {
            pq.add(times[i]);
        }
        for (int i = bound + 1; i <= N; i++) {
            int time = pq.poll();
            if (time + times[i] > X) return false;
            pq.add(time + times[i]);
        }
        return pq.poll() <= X;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        times = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            times[i] = Integer.parseInt(st.nextToken());
        }
        int lo = 0, hi = 100010;
        while (lo + 1 < hi) {
            int mid = (lo + hi) / 2;
            if (isPossible(mid)) hi = mid;
            else lo = mid;
        }
        System.out.println(hi);
    }
}
