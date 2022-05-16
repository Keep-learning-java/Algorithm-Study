import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_9694 {

    static final int MAX = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for(int test=1; test<=t; test++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(stk.nextToken());
            int m = Integer.parseInt(stk.nextToken());
            int[][] map = new int[m][m];
            int[] parent = new int[m]; // 경로 추적을 위함
            Arrays.fill(parent, -1);

            for(int[] row : map)
                Arrays.fill(row, MAX);

            // 관계 input
            for(int i=0; i<n; i++) {
                stk = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(stk.nextToken());
                int y = Integer.parseInt(stk.nextToken());
                int z = Integer.parseInt(stk.nextToken());

                map[x][y] = z;
                map[y][x] = z;
            }

            System.out.print("Case #" + test + ": ");
            Stack<Integer> result = dijkstra(m, map, parent);
            while(!result.isEmpty()) {
                System.out.print(result.pop() + " ");
            }
            System.out.println();
        }
    }

    public static Stack<Integer> dijkstra(int m, int[][] map, int[] parent) {
        Queue<Integer> q = new PriorityQueue<>();
        int[] dist = new int[m];
        Arrays.fill(dist, MAX);

        Stack<Integer> history = new Stack<>();

        q.offer(0);
        dist[0] = 0;

        while(!q.isEmpty()) {
            int here = q.poll();

            // 모든 정점 탐색 ㄱㄱ
            for(int i=0; i<m; i++) {
                // 연결되어 있고 && here를 거치는 것이 지금 알고있는 최단거리보다 짧으면 갱신
                if(map[here][i] != MAX && dist[i] > dist[here]+map[here][i]) {
                    dist[i] = dist[here]+map[here][i];
                    q.offer(i);

                    parent[i] = here;
                }
            }
        }

        if(parent[m-1] == -1) {
            history.push(-1);
        }
        else {
            int index = m-1;
            while(index != -1) {
                history.push(index);
                index = parent[index];
            }
        }

        return history;
    }
}
