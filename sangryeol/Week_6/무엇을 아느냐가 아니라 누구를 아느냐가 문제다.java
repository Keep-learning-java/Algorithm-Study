import java.io.*;
import java.util.*;

class Relationship implements Comparable<Relationship> {
    int to, cost;
    Relationship(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Relationship o) {
        return cost - o.cost;
    }
}

public class BOJ_9694 {
    static int TC, N, M;
    static ArrayList<Relationship>[] adj;
    static int[] parent;
    static final int INF = (int)1e9;
    static void dijk() {
        PriorityQueue<Relationship> pq = new PriorityQueue<>();
        int[] dist = new int[M];
        Arrays.fill(dist, INF);
        Arrays.fill(parent, -1);
        pq.add(new Relationship(0, 0));
        dist[0] = 0;
        while (!pq.isEmpty()) {
            Relationship curr = pq.poll();
            if (curr.cost > dist[curr.to]) continue;
            for (Relationship next : adj[curr.to]) {
                if (dist[next.to] > dist[curr.to] + next.cost) {
                    dist[next.to] = dist[curr.to] + next.cost;
                    parent[next.to] = curr.to;
                    pq.add(next);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TC = Integer.parseInt(br.readLine());
        for (int t = 1; t <= TC; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            adj = new ArrayList[M + 1];
            for (int i = 0; i <= M; i++) {
                adj[i] = new ArrayList<>();
            }
            parent = new int[M];
            for (int i = 0, u, v, w; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());
                w = Integer.parseInt(st.nextToken());
                adj[u].add(new Relationship(v, w));
                adj[v].add(new Relationship(u, w));
            }
            dijk();
            if (parent[M - 1] != -1) {
                int curr = M - 1;
                Stack<Integer> stk = new Stack<>();
                while (curr != -1) {
                    stk.add(curr);
                    curr = parent[curr];
                }
                System.out.print("Case #" + t + ": ");
                while (!stk.isEmpty()) {
                    System.out.print(stk.pop() + " ");
                }
                System.out.println();
            }
            else System.out.println("Case #" + t + ": -1");
        }
    }
}
