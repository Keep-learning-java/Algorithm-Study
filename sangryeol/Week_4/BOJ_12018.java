import java.io.*;
import java.util.*;

public class BOJ_12018 {
    public static void main(String[] args) throws IOException {
        int N, M;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> scores = new PriorityQueue<>();
        for (int i = 1, P, L; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            P = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            ArrayList<Integer> mileages = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= P; j++) {
                int mileage = Integer.parseInt(st.nextToken());
                mileages.add(mileage);
            }
            Collections.sort(mileages, Collections.reverseOrder());
            if (mileages.size() >= L - 1) scores.add(mileages.get(L - 1));
            else scores.add(1);
        }
        int cnt = 0, sum = 0;
        while (!scores.isEmpty()) {
            int score = scores.poll();
            if (sum + score > M) break;
            sum += score;
            cnt++;
        }
        System.out.println(cnt);
    }
}
