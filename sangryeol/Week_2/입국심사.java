import java.io.*;
import java.util.*;

public class BOJ_3079 {
    static long N, M;
    static ArrayList<Long> times = new ArrayList<>();
    static boolean f(long param) {
        long cnt = 0;
        for (long time : times) {
            cnt += param / time;
        }
        return cnt >= M;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            long time = Integer.parseInt(br.readLine());
            times.add(time);
        }
        Collections.sort(times);
        int last = times.size() - 1;
        long lo = 0, hi = times.get(last) * M;
        while (lo + 1 < hi) {
            long mid = (lo + hi) / 2;
            if (f(mid)) hi = mid;
            else lo = mid;
        }
        System.out.println(hi);
    }
}
