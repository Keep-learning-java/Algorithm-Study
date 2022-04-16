import java.io.*;
import java.util.*;

public class BOJ_20166 {
    static int N, M, K;
    static char[][] maze;
    static HashMap<String, Integer> hashMap;
    static final int[] roff = {-1, 1, 0, 0, 1, 1, -1, -1};
    static final int[] coff = {0, 0, 1, -1, 1, -1, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        maze = new char[N][M];
        for (int i = 0; i < N; i++) {
            maze[i] = br.readLine().toCharArray();
        }
        hashMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dfs(i, j, "" + maze[i][j]);
            }
        }
        for (int i = 0; i < K; i++) {
            String destination = br.readLine();
            System.out.println(hashMap.getOrDefault(destination, 0));
        }
    }
    public static void dfs(int r, int c, String s) {
        if (s.length() > 5) return;
        int count = hashMap.getOrDefault(s, 0);
        hashMap.put(s, count + 1);
        for (int d = 0; d < 8; d++) {
            int nr = (N + r + roff[d]) % N;
            int nc = (M + c + coff[d]) % M;
            dfs(nr, nc, s + maze[nr][nc]);
        }
    }
}
