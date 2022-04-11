import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2302 {

    static int n, m;
    static int[] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        cache = new int[n+1];

        cache[0] = 1;
        cache[1] = 1;
        cache[2] = 2;

        // 점화식 cache[i] = cache[i-1] + cache[i-2]
        for(int i=3; i<=n; i++) {
            cache[i] = cache[i-1] + cache[i-2];
        }

        int result = 1;
        int before = 0;
        for(int i=0; i<m; i++) {
            int vip = Integer.parseInt(br.readLine());
            // vip 좌석으로 구분되는 좌석들의 경우의 수를 곱해주면 됨
            result *= cache[vip-before-1];
            before = vip;
        }
        result *= cache[n-before];

        System.out.println(result);
    }
}
