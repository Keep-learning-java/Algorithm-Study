import java.io.*;
import java.util.*;

public class BOJ_15681 {
    static int N, Q, R;
    static final int SZ = 100_010;
    static int[] dp = new int[SZ];
    static ArrayList<Integer>[] adj = new ArrayList[SZ];
    public static int f(int curr, int parent) {
        if (dp[curr] != -1) return dp[curr];
        dp[curr] = 1;
        for (int next : adj[curr]) {
            if (next == parent) continue;
            dp[curr] += f(next, curr);
        }
        return dp[curr];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1, u, v; i <= N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }
        Arrays.fill(dp, -1);
        f(R, 0);
        for (int i = 1, q; i <= Q; i++) {
            q = Integer.parseInt(br.readLine());
            System.out.println(dp[q]);
        }
    }
}
