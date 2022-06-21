import java.util.*;
import java.io.*;

public class BOJ_22252 {
    static int Q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Q = Integer.parseInt(br.readLine());
        HashMap<String, PriorityQueue<Integer>> information = new HashMap<>();
        long ans = 0;
        for (int i = 1, q, k, c; i <= Q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            q = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            k = Integer.parseInt(st.nextToken());
            PriorityQueue<Integer> pq = information.getOrDefault(name, null);
            if (q == 1) {
                if (pq == null) pq = new PriorityQueue<>(Comparator.reverseOrder());
                for (int j = 0; j < k; j++) {
                    int value = Integer.parseInt(st.nextToken());
                    pq.add(value);
                }
                information.put(name, pq);
            }
            else {
                if (pq == null) continue;
                int b = Math.min(k, pq.size());
                for (int j = 0; j < b; j++) {
                    ans += pq.poll();
                }
            }
        }
        System.out.println(ans);
    }
}
