import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1654 {

    static int k, n;
    static int[] arr = new int[10000];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        k = Integer.parseInt(stk.nextToken());
        n = Integer.parseInt(stk.nextToken());

        long max = 0;
        for(int i=0; i<k; i++) {
            stk = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(stk.nextToken());
            if(max < arr[i]) {
                max = arr[i];
            }
        }

        long min = 1;
        long mid = 1;

        while(max >= min) {
            mid = (max + min) / 2;
            long count = 0;

            // 각 랜선을 자른 조각 개수 총 합
            for(int i=0; i<k; i++) {
                count += arr[i]/mid;
            }
            // 같거나 많음 -> 조건은 충족했으나 잘린 랜선의 길이가 더 길어도 조건을 충족할 수 있으므로
            // 좀 더 길게 잘라봅니다.
            if(count >= n) {
                min = mid + 1;
            }
            // 모자람
            else if(count < n) {
                max = mid - 1;
            }
        }

        System.out.println(max);
    }

}
