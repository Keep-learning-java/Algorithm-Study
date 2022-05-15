import java.io.*;
import java.util.*;

public class BOJ_16562 {
    static int N, M, K;
    static int[] costs, parents;
    public static int find(int n) {
        if (parents[n] < 0) return n;
        return parents[n] = find(parents[n]);
    }
    public static void union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) return;
        if (costs[u] > costs[v]) parents[u] = v;
        else parents[v] = u;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        costs = new int[N + 1];
        parents = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        Arrays.fill(parents, -1);
        for (int i = 1; i <= N; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1, u, v; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            union(u, v);
        }
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            if (parents[i] != -1) continue;
            ans += costs[i];
            K -= costs[i];
        }
        if (K >= 0) System.out.println(ans);
        else System.out.println("Oh no");
    }
}
