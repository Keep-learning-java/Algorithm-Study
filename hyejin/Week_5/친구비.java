import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_16562 {

    static int n, m, k;
    static int[] price = new int[10001];
    static int[] parent = new int[10001];
    static boolean[] visited = new boolean[10001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());

        // init
        Arrays.fill(visited, false);
        for(int i=1; i<=n; i++) {
            parent[i] = i;
        }

        // price input
        stk = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) {
            price[i] = Integer.parseInt(stk.nextToken());
        }

        // 합집합 만들기
        for(int i=0; i<m; i++) {
            stk = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(stk.nextToken());
            int w = Integer.parseInt(stk.nextToken());

            union(v, w);
        }

        // 1번부터 친구가 되어본다
        int sum = 0;
        for(int i=1; i<=n; i++) {
            int root = find(i);

            // 친구가 아닌 경우
            if(!visited[root]) {
                sum += price[root];
                visited[root] = true;
            }
        }

        System.out.println(k < sum ? "Oh no" : sum);
    }

    // number 노드의 부모 노드를 찾아 반환
    public static int find(int number) {
        // 기저: 찾음
        if(number == parent[number]) {
            return number;
        }

        // 재귀적으로 부모 찾아 ㄱㄱ
        return parent[number] = find(parent[number]);
    }

    // n1과 n2의 합집합을 만듭니다..
    public static void union(int n1, int n2) {
        // 각각 부모를 찾음
        n1 = find(n1);
        n2 = find(n2);

        // 부모가 같지 않음
        if(n1 != n2) {
            // 부모를 같게 만들어 줄건데
            // 비용이 더 작은 것이 부모가 되도록.
            if(price[n1] < price[n2]) {
                parent[n2] = parent[n1];
            }
            else {
                parent[n1] = parent[n2];
            }
        }
    }
}
