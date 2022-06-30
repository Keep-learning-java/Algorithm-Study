package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_16929 {

    static int N, M;
    static boolean findCycle = false;
    static char[][] map;
    static boolean[][] visited;
    static int[] dy = { -1, 1, 0, 0 }; // 상하좌우
    static int[] dx = { 0, 0, -1, 1 };
    static class Dot {
        int y;
        int x;
        Dot(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];

        for(int i=0; i<N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for(int y=0; y<N; y++) {
            for(int x=0; x<M; x++) {
                Dot start = new Dot(y, x);
                ArrayList<Dot> list = new ArrayList<>();
                list.add(start);
                dfs(start, list);
            }
        }

        System.out.println(findCycle ? "Yes" : "No");
    }

    public static void dfs(Dot now, ArrayList<Dot> list) {
        visited[now.y][now.x] = true;
        // 기저 : 사이클 찾음
        if(findCycle)
            return;

        // 기저 : 사이클 형성 조건
        int size = list.size();
        if(size >= 4 && size % 2 == 0) {
            if(isAdjacent(list.get(0), list.get(size-1))) {
                findCycle = true;
                return;
            }
        }

        // 상하좌우 탐색
        for(int i=0; i<dy.length; i++) {
            int ny = now.y + dy[i];
            int nx = now.x + dx[i];

            if(!checkRange(ny, nx))
                continue;

            if(!visited[ny][nx] && map[now.y][now.x] == map[ny][nx]) {
                list.add(new Dot(ny, nx));
                dfs(new Dot(ny, nx), list);
                list.remove(list.size()-1);
            }

        }
        visited[now.y][now.x] = false;

    }

    public static boolean checkRange(int ny, int nx) {
        if(ny < 0 || ny >= N || nx < 0 || nx >= M)
            return false;
        return true;
    }

    public static boolean isAdjacent(Dot d1, Dot d2) {
        // 좌우로 인접
        if((Math.abs(d1.x - d2.x) == 1) && (d1.y - d2.y == 0))
            return true;
            // 상하로 인접
        else if((Math.abs(d1.y - d2.y) == 1) && (d1.x - d2.x == 0))
            return true;

        return false;
    }
}
