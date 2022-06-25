import java.io.*;
import java.util.*;

public class BOJ_10775 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TreeSet<Integer> gates = new TreeSet<>();
        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());
        for (int g = 1; g <= G; g++) {
            gates.add(g);
        }
        int ans = 0;
        for (int i = 0; i < P; i++) {
            int want = Integer.parseInt(br.readLine());
            Integer found = gates.floor(want);
            if (found == null) break;
            gates.remove(found);
            ans++;
        }
        System.out.println(ans);
    }
}
