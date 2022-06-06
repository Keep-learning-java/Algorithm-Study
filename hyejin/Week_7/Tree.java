import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13244 {

    static int t, n, m;
    static int[] parent = new int[1001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        for(int test=0; test<t; test++) {
            n = Integer.parseInt(br.readLine()); // # of nodes
            m = Integer.parseInt(br.readLine()); // # of relations

            boolean isTree = true;

            for(int i=1; i<=n; i++)
                parent[i] = i;

            for(int i=0; i<m; i++) {
                StringTokenizer stk = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(stk.nextToken());
                int b = Integer.parseInt(stk.nextToken());

                // 같은 부모 -> 싸이클 존재
                if(sameParent(a, b))
                    isTree = false;
                else
                    union(a, b);
            }

            // parent 값 2개 이상이면 그래프
            for(int i=1; i<=n; i++) {
                if(find(parent[i]) != 1) {
                    isTree = false;
                }
            }

            System.out.println(isTree ? "tree" : "graph");
        }
    }

    // 부모 찾기
    public static int find(int node) {
        // 기저 : 찾음
        if(node == parent[node])
            return node;

        // 재귀
        return parent[node] = find(parent[node]);
    }

    // 합집합 만들기
    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        // 두개 부모 같지 않다면 같은 부모로 만들어줍니다
        if(x != y) {
            if(x < y) {
                parent[y] = x;
            }
            else {
                parent[x] = y;
            }
        }
    }

    // 같은 부모? -> 트리 X
    public static boolean sameParent(int x, int y) {
        x = find(x);
        y = find(y);

        return x == y ? true : false;
    }
}
