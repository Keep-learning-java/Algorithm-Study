import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_24725 {

    static int n, m;
    static char[][] map = new char[201][201];

    // ↑, ↓, ←, →, ↗, ↙, ↖, ↘
    static int[] dy = { -1, 1, 0, 0, -1, 1, -1, 1 };
    static int[] dx = { 0, 0, -1, 1, 1, -1, -1, 1 };

    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        for(int i=0; i<n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // start
        for(int y=0; y<n; y++) {
            for(int x=0; x<m; x++) {
                search(y, x);
            }
        }

        System.out.println(count);
    }

    public static void search(int y, int x) {
        // 8가지 방향 다 해보자..
        // ↑, ↓, ←, →, ↗, ↙, ↖, ↘
        for(int dir=0; dir<dy.length; dir++) {
            List<Character> list = new ArrayList<>();

            for(int i=0; i<4; i++) {
                int ny = y + (dy[dir]*i);
                int nx = x + (dx[dir]*i);

                // range check
                if(ny < 0 || ny >= n || nx < 0 || nx >= m) {
                    break;
                }

                list.add(map[ny][nx]);
            }

            if(list.size() == 4 && isMbti(list)) {
                count++;
            }
            list.clear();
        }
    }

    public static boolean isMbti(List<Character> mbti) {
        // E I
        if(mbti.get(0) != 'E' && mbti.get(0) != 'I') {
            return false;
        }

        // N S
        if(mbti.get(1) != 'N' && mbti.get(1) != 'S') {
            return false;
        }

        // F T
        if(mbti.get(2) != 'F' && mbti.get(2) != 'T') {
            return false;
        }

        // J P
        if(mbti.get(3) != 'J' && mbti.get(3) != 'P') {
            return false;
        }

        return true;
    }
}
