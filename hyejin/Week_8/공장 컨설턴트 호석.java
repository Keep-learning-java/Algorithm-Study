import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_22254 {

    static int n, x;
    static int[] time = new int[100001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stk.nextToken());
        x = Integer.parseInt(stk.nextToken());

        stk = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            time[i] = Integer.parseInt(stk.nextToken());
        }

        binarySearch();
    }

    public static void binarySearch() {
        int low = 1;
        int high = n;

        while(low <= high) {
            int mid = (low+high)/2;

            if(processable(mid)) {
                high = mid-1;
            }
            else {
                low = mid+1;
            }
        }

        System.out.println(low);
    }

    public static boolean processable(int mid) {
        PriorityQueue<Integer> q = new PriorityQueue<>();

        for(int i=0; i<mid; i++) {
            q.offer(0);
        }

        for(int i=0; i<n; i++) {
            int t = q.poll();
            if(t + time[i] > x) {
                return false;
            }
            q.offer(t + time[i]);
        }

        return true;
    }
}
