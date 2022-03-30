import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1717 {

    static int n, m;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        // init
        parent = new int[n+1];
        for(int i=0; i<=n; i++) {
            parent[i] = i;
        }

        // go
        for(int i=0; i<m; i++) {
            stk = new StringTokenizer(br.readLine());
            int oper = Integer.parseInt(stk.nextToken());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());

            // 합집합 만들기
            if(oper == 0) {
                union(x, y);
            }
            // 판별
            else if(oper == 1) {
                if(isSameParent(x, y)) {
                    System.out.println("YES");
                }
                else {
                    System.out.println("NO");
                }
            }
        }
    }

    // x와 y가 포함된 집합을 합침
    public static void union(int x, int y) {
        // 각각 부모를 찾음
        x = find(x);
        y = find(y);

        // 부모가 같지 않음
        if(x != y) {
            // 부모를 같게 만들어 줘야 함
            // 작은 수가 부모가 되도록
            if(x < y) {
                parent[y] = x;
            }
            else {
                parent[x] = y;
            }
        }
    }

    // x가 어떤 집합에 포함되어 있는지 -> 루트 반환
    public static int find(int x) {
        // 루트이면 반환
        if(x == parent[x]) {
            return x;
        }
        // 부모를 찾아나갑니다
        else {
            return parent[x] = find(parent[x]);
        }
    }

    public static boolean isSameParent(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) {
            return true;
        }
        else {
            return false;
        }
    }
}
