import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15681 {

    static int n, r, q;
    static ArrayList<ArrayList<Integer>> node, tree;
    static int[] dp; // 자신의 서브트리의 노드 개수를 기록
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stk.nextToken());
        r = Integer.parseInt(stk.nextToken());
        q = Integer.parseInt(stk.nextToken());

        node = new ArrayList<>();
        tree = new ArrayList<>();
        dp = new int[n+1];
        parent = new int[n+1];

        for(int i=0; i<n+1; i++) {
            node.add(new ArrayList<>());
            tree.add(new ArrayList<>());
        }

        for(int i=1; i<n; i++) {
            stk = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(stk.nextToken());
            int v = Integer.parseInt(stk.nextToken());

            // 두 노드를 잇는 간선
            node.get(u).add(v);
            node.get(v).add(u);
        }

        // 트리 만들기
        // r이 root이므로 부모는 없어서 -1
        makeTree(r, -1);

        // 개수 세기
        countSubtreeNodes(r);

        for(int i=0; i<q; i++) {
            int u = Integer.parseInt(br.readLine());
            bw.write(dp[u] + "\n");
            bw.flush();
        }
        bw.close();
    }

    // 트리 생성
    public static void makeTree(int curNode, int p) {
        // 현재 노드의 자식들을 하나씩
        for(int node : node.get(curNode)) {
            if(node != p) {
                // add Node to currentNode's child
                tree.get(curNode).add(node);
                // set Node's parent to currentNode
                parent[node] = curNode;
                // 재귀
                makeTree(node, curNode);
            }
        }
    }

    public static int countSubtreeNodes(int curNode) {
        // 저장된 값 있으면 사용
        if(dp[curNode] != 0) {
            return dp[curNode];
        }

        // 자기자신을 루트로 하는 서브트리이므로 1
        dp[curNode] = 1;

        for(int node : tree.get(curNode)) {
            // 재귀적으로 서브트리 노드 개수를 가져와서 다 더해주면 됨
            countSubtreeNodes(node);
            dp[curNode] += countSubtreeNodes(node);
        }

        return dp[curNode];
    }
}
