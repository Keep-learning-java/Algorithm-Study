import java.util.*;
import java.io.*;

public class BOJ_2644 {
    public static int N, M;
    public static int start, end;
    public static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    public static int bfs() {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N];
        queue.add(start);
        visited[start] = true;
        for (int d = 0; !queue.isEmpty(); d++) {
            int qSize = queue.size();
            for (int s = 0; s < qSize; s++) {
                int curr = queue.poll();
                if (curr == end) return d;
                for (int next : adj.get(curr)) {
                    if (visited[next]) continue;
                    queue.add(next);
                    visited[next] = true;
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<Integer>());
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        start--; end--;
        M = Integer.parseInt(br.readLine());
        for (int i = 0, u, v; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            u--; v--;
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        System.out.println(bfs());
    }
}
