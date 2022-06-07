import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_3584 {

    static int n;
    static boolean[] isNotRoot;
    static ArrayList<Integer>[] adj;
    static int[] parent;
    static int[] depth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        int t = Integer.parseInt(br.readLine());

        for(int test=0; test<t; test++) {
            n = Integer.parseInt(br.readLine());
            isNotRoot = new boolean[n+1];
            adj = new ArrayList[n+1];
            for(int i=1; i<=n; i++) {
                adj[i] = new ArrayList<>();
            }
            parent = new int[n+1];
            depth = new int[n+1];

            for(int i=0; i<n-1; i++) {
                stk = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(stk.nextToken());
                int b = Integer.parseInt(stk.nextToken());

                adj[a].add(b);
                adj[b].add(a);

                isNotRoot[b] = true;
            }

            int root = findRoot(n, isNotRoot);
            dfs(0, root, -1);

            stk = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(stk.nextToken());
            int v = Integer.parseInt(stk.nextToken());

            System.out.println(lca(u, v));
        }
    }

    public static int lca(int u, int v) {
        // 두 노드를 같은 depth로 맞춰주기
        int du = depth[u];
        int dv = depth[v];

        // du가 더 깊음
        if(du > dv) {
            while(du > dv) {
                u = parent[u];
                du--;
            }
        }
        else if(du < dv) {
            while(du < dv) {
                v = parent[v];
                dv--;
            }
        }

        // 같은 depth 됨
        // 위로 올라가면서 공통 부모 찾기
        while(u != v) {
            u = parent[u];
            v = parent[v];
        }

        return u;
    }

    // depth, parent 저장
    public static void dfs(int d, int now, int p) {
        depth[now] = d;
        parent[now] = p;

        // 자식 타고 DFS
        for(int next : adj[now]) {
            if(next != p) {
                dfs(d+1, next, now);
            }
        }
    }

    public static int findRoot(int n, boolean[] isNotRoot) {
        // 부모가 하나도 없는 것이 root
        for(int i=1; i<=n; i++) {
            if(!isNotRoot[i])
                return i;
        }
        return 0;
    }
}
