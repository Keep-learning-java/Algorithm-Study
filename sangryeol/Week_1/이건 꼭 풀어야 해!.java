import java.io.*;
import java.util.*;

public class BOJ_17390 {
    static int N, Q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        ArrayList<Integer> arrayList = new ArrayList<>();
        int[] pSum = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int in = Integer.parseInt(st.nextToken());
            arrayList.add(in);
        }
        Collections.sort(arrayList);
        for (int i = 1; i <= N; i++) {
            pSum[i] = pSum[i-1] + arrayList.get(i-1);
        }
        for (int i = 1, l, r; i <= Q; i++) {
            st = new StringTokenizer(br.readLine());
            l = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            bw.write(pSum[r] - pSum[l - 1] + "\n");
        }
        bw.flush();
        bw.close();
    }
}
