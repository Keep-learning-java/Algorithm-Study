import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_12018 {

    static int n, m;
    static int[] p = new int[100];
    static int[] l = new int[100];
    static Integer[][] mileage = new Integer[100][100];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        for(int i=0; i<n; i++) {
            stk = new StringTokenizer(br.readLine());
            p[i] = Integer.parseInt(stk.nextToken());
            l[i] = Integer.parseInt(stk.nextToken());

            stk = new StringTokenizer(br.readLine());
            for(int j=0; j<p[i]; j++) {
                mileage[i][j] = Integer.parseInt(stk.nextToken());
            }

            Arrays.sort(mileage[i], 0, p[i], Collections.reverseOrder());
        }

        int[] myUsedMileage = new int[n]; // 내가 사용한 마일리지 저장
        for(int i=0; i<n; i++) {
            // 신청인원 < 정원이면 1점만 투자
            if(p[i] < l[i]) {
                myUsedMileage[i] = 1;
            }
            // 신청인원 >= 정원
            // 순위권에 든 사람 중 가장 적게 투자한 사람과 같은 마일리지
            else {
                myUsedMileage[i] = mileage[i][l[i]-1];
            }
        }

        // mum 을 다시 오름차순 정렬
        Arrays.sort(myUsedMileage);
        int sum = 0;
        int count = 0;
        for(int i=0; i<myUsedMileage.length; i++) {
            sum += myUsedMileage[i];
            if(sum > m) {
                break;
            }
            count++;
        }
        System.out.println(count);
    }
}
