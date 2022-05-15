import java.io.*;
import java.util.*;

public class BOJ_24725 {
    static int N, M;
    static char[][] MBTI = {
            {'E', 'I'},
            {'N', 'S'},
            {'F', 'T'},
            {'P', 'J'}
    };
    static char[][] alphabets;
    static int[] roff = {1, -1, 0, 0, 1, 1, -1, -1};
    static int[] coff = {0, 0, -1, 1, 1, -1, 1, -1};
    static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }
    static int dfs(int depth, int r, int c, int d, String word) {
        if (depth == 4) {
            boolean mbtiFlag = true;
            for (int i = 0; i < 4; i++) {
                char alphabet = word.charAt(i);
                boolean findFlag = false;
                for (int j = 0; j < 2; j++) {
                    findFlag |= (alphabet == MBTI[i][j]);
                }
                mbtiFlag &= findFlag;
                if (!mbtiFlag) return 0;
            }
            return 1;
        }
        int nr = r + roff[d], nc = c + coff[d];
        if (!isIn(r, c)) return 0;
        return dfs(depth + 1, nr, nc, d, word + alphabets[r][c]);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        alphabets = new char[N + 1][M + 1];
        for (int i = 0; i < N; i++) {
            char[] rows = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                alphabets[i][j] = rows[j];
            }
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int d = 0; d < 8; d++) {
                    ans += dfs(0, i, j, d, "");
                }
            }
        }
        System.out.println(ans);
    }
}
