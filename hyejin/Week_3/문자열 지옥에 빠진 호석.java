import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_20166 {

    static int n, m, k;
    static char[][] map;
    static HashMap<String, Integer> hashMap;

    // 상 하 좌 우 대각선 상 하 좌 우
    static int[] dy = { -1, 1, 0, 0, -1, -1, 1, 1 };
    static int[] dx = { 0, 0, -1, 1, -1, 1, -1, 1 };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer stk = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());
        map = new char[n][m];
        hashMap = new HashMap<>();

        // 맵 생성
        for(int i=0; i<n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for(int i=0; i<k; i++) {
            String destination = br.readLine();
            if(hashMap.containsKey(destination)) {
                System.out.println(hashMap.get(destination));
                continue;
            }
            hashMap.put(destination, 0);

            // 모든 칸에서 다 시작해봄
            for(int y=0; y<n; y++) {
                for(int x=0; x<m; x++) {
                    // 시간 줄여보기 위해 첫글자부터 틀린 건 스킵
                    if(destination.charAt(0) != map[y][x])
                        continue;
                    dfs(destination, Character.toString(map[y][x]), y, x);
                }
            }

            bw.write(hashMap.get(destination) + "\n");
            bw.flush();

        }
        bw.close();
    }

    public static void dfs(String destination, String now, int y, int x) {
        // 만들고자 하는 문자열이 만들어졌음
        if(hashMap.containsKey(now)) {
            hashMap.put(now, hashMap.get(now)+1);
            return;
        }
        // 문자열 길이가 목표문자열의 길이를 넘음 -> 이미 글렀어
        if(now.length() > destination.length()) {
            return;
        }

        // 이동해봅시다..
        for(int i=0; i<dy.length; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            // 좌표 예외처리
            if(ny < 0)
                ny = n-1;
            else if(ny >= n)
                ny = 0;

            if(nx < 0)
                nx = m-1;
            else if(nx >= m)
                nx = 0;

            // 다음 글자로 이동해도 되는지 판단해보자
            if(destination.charAt(now.length()) == map[ny][nx])
                dfs(destination, now+map[ny][nx], ny, nx);
        }
    }
}
