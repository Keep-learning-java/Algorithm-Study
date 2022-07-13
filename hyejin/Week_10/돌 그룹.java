package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12886 {

    static int a, b, c, result;
    static boolean[][] visited;
    static int sum = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        a = Integer.parseInt(stk.nextToken());
        b = Integer.parseInt(stk.nextToken());
        c = Integer.parseInt(stk.nextToken());

        sum = a+b+c;

        if((a+b+c) % 3 != 0) {
            System.out.println(0);
            return;
        }

        visited = new boolean[sum + 1][sum + 1];
        result = 0;
        solution(a, b, c);
        System.out.println(result);
    }

    public static void solution(int a, int b, int c) {
        if(a == b && b == c) {
            result = 1;
            return;
        }

        dfs(a, b);
        dfs(b, c);
        dfs(c, a);
    }

    public static void dfs(int n1, int n2) {
        int min = Math.min(n1, n2);
        int max = Math.max(n1, n2);

        max -= min;
        min *= 2;

        // 방문한 적 있는지
        if(!visited[min][max] || !visited[max][min]) {
            visited[min][max] = true;
            visited[max][min] = true;

            solution(min, max, sum-(min+max));
        }
    }
}
