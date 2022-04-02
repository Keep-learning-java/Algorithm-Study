import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3079 {

    static int n, m;
    static long [] time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        time = new long[n];

        long maxTime = time[0];
        for(int i=0; i<n; i++) {
            time[i] = Integer.parseInt(br.readLine());
            if(time[i] > maxTime) {
                maxTime = time[i];
            }
        }

        System.out.println(binarySearch(maxTime));
    }

    public static long binarySearch(long maxTime) {
        // max 값 설정
        // 시간 가장 오래걸리는 심사대 시간 * 사람 수
        long right = maxTime * m;
        long left = 1;
        long result = right;

        while(right >= left) {
            long mid = (left+right) / 2;
            long sum = 0;

            // mid초동안 각 심사대에서 심사받을 수 있는 사람 수의 최대를 모두 더해준다
            for(int i=0; i<n; i++) {
                // ex) 10초동안 2초걸리는 심사대에서 심사받을 수 있는 사람은 최대 5명
                sum += mid / time[i];
            }

            // m명 다 심사받을 수 있으면
            if(sum >= m) {
                // 시간을 더 줄여볼까?
                result = mid;
                right = mid-1;
            }
            // 시간 모자라면
            else {
                left = mid+1;
            }
        }

        return result;
    }
}
