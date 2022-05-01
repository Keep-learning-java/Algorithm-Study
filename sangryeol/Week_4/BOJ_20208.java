import java.io.*;
import java.util.*;

public class BOJ_20208 {
    static int N, M, H, sr, sc, mintCount, ans;
    static int[][] maze;
    static int[] indexs;
    static ArrayList<Integer> mintChocos = new ArrayList<>();
    static boolean isPossible(int count) {
        int row = sr, col = sc, destRow = 0, destCol = 0;
        int health = M;
        for (int i = 0; i < count; i++) {
            destRow = mintChocos.get(indexs[i]) / N;
            destCol = mintChocos.get(indexs[i]) % N;
            int dist = Math.abs(row - destRow) + Math.abs(col - destCol);
            if (dist > health) return false;
            row = destRow; col = destCol;
            health -= dist; health += H;
        }
        int returnDist = Math.abs(destRow - sr) + Math.abs(destCol - sc);
        if (returnDist > health) return false;
        return true;
    }
    static void permutation(int pos, int selected) {
        if (pos > mintCount) return;
        if (isPossible(pos)) ans = Math.max(ans, pos);
        for (int i = 0; i < mintCount; i++) {
            if ((selected & 1 << i) != 0) continue;
            indexs[pos] = i;
            permutation(pos + 1, selected | (1 << i));
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        mintCount = 0;
        maze = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                maze[i][j] = Integer.parseInt(st.nextToken());
                if (maze[i][j] == 1) {
                    sr = i; sc = j;
                }
                if (maze[i][j] == 2) {
                    mintCount++;
                    mintChocos.add(i * N + j);
                }
            }
        }
        indexs = new int[mintCount];
        permutation(0, 0);
        System.out.println(ans);
    }
}
