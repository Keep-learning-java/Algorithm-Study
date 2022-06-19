import java.util.*;
import java.io.*;

class Node implements Comparable<Node> {
    int u, v, w;
    Node(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }
    @Override
    public int compareTo(Node o) {
        return w - o.w;
    }
}

public class BOJ_21924 {
    static int N, M;
    static int[] parent;

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
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ArrayList<Node> graphs = new ArrayList<>();
        parent = new int[N + 1];
        long ans = 0;
        for (int i = 1, u, v, w; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            graphs.add(new Node(u, v, w));
            ans += w;
        }
        Collections.sort(graphs);
        int cnt = 0;
        for (int i = 0; i < M && cnt < N - 1; i++) {
            Node node = graphs.get(i);
            if (union(node.u, node.v)) {
                ans -= node.w;
                cnt++;
            }
        }
        System.out.println(cnt != N - 1 ? -1 : ans);
    }
}
