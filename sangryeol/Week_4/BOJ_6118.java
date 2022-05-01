import java.io.*;
import java.util.*;

class Answer {
    int number, dist, count;
    Answer(int n, int d, int c) {
        number = n;
        dist = d;
        count = c;
    }
}

public class BOJ_6118 {
    static int N, M;
    static ArrayList<Integer>[] adj;
    static Answer bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, -1);
        queue.add(start);
        dist[start] = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int next : adj[curr]) {
                if (dist[next] != -1) continue;
                dist[next] = dist[curr] + 1;
                queue.add(next);
            }
        }
        Answer answer = new Answer(0, 0, 0);
        for (int i = 1; i <= N; i++) {
            int d = dist[i];
            if (d > answer.dist) {
                answer.dist = d;
                answer.count = 1;
                answer.number = i;
            }
            else if (d == answer.dist) {
                answer.count++;
            }
        }
        return answer;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1, u, v; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }
        Answer answer = bfs(1);
        System.out.println(answer.number + " " + answer.dist + " " + answer.count);
    }
}