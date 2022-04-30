import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_6118 {

    static int n, m;
    static ArrayList<Integer>[] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        map = new ArrayList[n+1];
        for(int i=1; i<=n; i++) {
            map[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());

            map[a].add(b);
            map[b].add(a);
        }

        int[] dist = bfs(1);

        // find answer
        int maxDistance = -1;
        int minIndex = Integer.MAX_VALUE;
        int count = 0;
        for(int i=2; i<=n; i++) {
            int d = dist[i];

            if(d > maxDistance) {
                count = 1;
                maxDistance = d;
                minIndex = i;
            }
            else if(d == maxDistance) {
                count++;
                if(i < minIndex) {
                    minIndex = i;
                }
            }
        }

        System.out.println(minIndex + " " + maxDistance + " " + count);
    }

    public static int[] bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        int[] dist = new int[n+1]; // start-index 까지의 거리
        Arrays.fill(dist, -1);

        q.offer(start);
        dist[start] = 0;

        while (!q.isEmpty()) {
            int here = q.poll();

            // 다음 헛간으로 이동
            for(int next : map[here]) {
                // 방문한 적 없음
                if(dist[next] == -1) {
                    q.offer(next);
                    dist[next] = dist[here] + 1;
                }
            }

        }

        return dist;
    }
}
