import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17390 {

    static int n, q;
    static int[] arr, subtotals;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stk.nextToken());
        q = Integer.parseInt(stk.nextToken());
        arr = new int[n+1];
        subtotals = new int[n+1];

        // 수열 input
        stk = new StringTokenizer(br.readLine());
        arr[0] = 0;
        for(int i=1; i<=n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        // 비내림차 정렬
        Arrays.sort(arr);

        // 부분합 배열 구하기
        subtotals[0] = 0;
        subtotals[1] = arr[1];
        for(int i=2; i<=n; i++) {
            subtotals[i] = subtotals[i-1] + arr[i];
        }

        for(int i=0; i<q; i++) {
            stk = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(stk.nextToken());
            int r = Integer.parseInt(stk.nextToken());


            int sum = subtotals[r] - subtotals[l-1];

            System.out.println(sum);
        }
    }

}
