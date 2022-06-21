import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_21924 {

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int dis;

        Edge(int from, int to, int dis) {
            this.from = from;
            this.to = to;
            this.dis = dis;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dis - o.dis;
        }
    }

    static int n, m;
    static ArrayList<Edge>[] graph = new ArrayList[100001];
    static ArrayList<Edge> list = new ArrayList<>();
    static int[] parent = new int[100001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        // init
        for(int i=1; i<=n; i++) {
            parent[i] = i;
        }
        for(int i=1; i<=n; i++) {
            graph[i] = new ArrayList<>();
        }

        long sum = 0;
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            int c = Integer.parseInt(stk.nextToken());

            graph[a].add(new Edge(a, b, c));
            graph[b].add(new Edge(b, a, c));
            sum += c;

            list.add(new Edge(a, b, c));
        }

        long mstSum = 0;

        // 거리가 짧은 간선부터 연결
        Collections.sort(list);

        for(int i=0; i<m; i++) {
            Edge edge = list.get(i);
            // 부모가 같지 않을 때 (다른 컴포넌트에 속할 때)만 union
            if(find(edge.from) != find(edge.to)) {
                mstSum += edge.dis;
                union(find(edge.from), find(edge.to));
            }
        }

        // 컴포넌트 2개 이상인지?
        int value = find(parent[1]);
        for(int i=2; i<=n; i++) {
            if(find(parent[i]) != value) {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(sum - mstSum);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a < b)
            parent[b] = a;
        else
            parent[a] = b;
    }

    public static int find(int x) {
        if(x == parent[x])
            return x;

        parent[x] = find(parent[x]);
        return parent[x];
    }
}
