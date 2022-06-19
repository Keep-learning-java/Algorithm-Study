import java.io.*;
import java.util.*;

public class BOJ_3584 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] parent = new int[N + 1];
            boolean[] visited = new boolean[N + 1];
            for (int i = 1, u, v; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());
                parent[v] = u;
            }
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            while (u != 0) {
                visited[u] = true;
                u = parent[u];
            }
            while (!visited[v]) {
                v = parent[v];
            }
            System.out.println(v);
        }
    }
}
