import java.io.*;
import java.util.*;

public class BOJ_13244 {
    static int tc, N, M;
    static int[] parent;
    static void init(int N) {
        parent = new int[N + 1];
    }
    static int find(int n) {
        if (parent[n] <= 0) return n;
        return parent[n] = find(parent[n]);
    }
    static boolean union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) return false;
        parent[u] = v;
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());
        for (int i = 0; i < tc; i++) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
            init(N);
            boolean isTree = true;
            for (int j = 0, u, v; j < M; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());
                isTree &= union(u, v);
            }
            int root = find(1);
            for (int j = 2; j <= N; j++) {
                isTree &= (root == find(j));
            }
            System.out.println(isTree ? "tree" : "graph");
        }
    }
}