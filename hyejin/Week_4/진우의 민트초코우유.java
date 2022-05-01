import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_20208 {

    static int n, m, h;
    static boolean[] visited = new boolean[10];
    static Point home;
    static ArrayList<Point> milk;
    static int result;

    static class Point {
        int y;
        int x;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        h = Integer.parseInt(stk.nextToken());
        milk = new ArrayList<>();

        for(int i=0; i<n; i++) {
            stk = new StringTokenizer(br.readLine());

            for(int j=0; j<n; j++) {
                int num = Integer.parseInt(stk.nextToken());

                if(num == 1)
                    home = new Point(i, j);
                else if(num == 2)
                    milk.add(new Point(i, j));
            }
        }

        // 처음 방문할 우유 정하기
        for(int i=0; i<milk.size(); i++) {
            Point p = milk.get(i);
            int distance = Math.abs(home.y - p.y) + Math.abs(home.x - p.x);

            if(distance <= m) {
                dfs(p, m-distance+h, 1, i);
            }
        }

        System.out.println(result);

    }

    public static void dfs(Point now, int curHealth, int count, int milkIndex) {
        visited[milkIndex] = true;

        // 다음 어느 우유로 갈지
        for(int i=0; i<milk.size(); i++) {
            // 방문 안해본 우유
            if(!visited[i]) {
                Point p = milk.get(i);
                int distance = Math.abs(now.y - p.y) + Math.abs(now.x - p.x);

                // 현재 체력으로 갈 수 있는 거리
                if(distance <= curHealth) {
                    dfs(p, curHealth-distance+h, count+1, i);
                }
            }

        }

        // 집에 돌아갈 수 있는지?
        int distance = Math.abs(now.y - home.y) + Math.abs(now.x - home.x);
        if(distance <= curHealth) {
            result = Math.max(result, count);
        }

        visited[milkIndex] = false;
    }
}
