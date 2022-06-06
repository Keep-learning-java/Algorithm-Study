import java.io.*;
import java.util.*;

public class BOJ_16472 {
    static int N;
    static int[] alphabet = new int[26];
    static int getIdx(char c) {
        return c - 'a';
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        char[] meow = br.readLine().toCharArray();
        int bound = meow.length;
        int s = 0, e = 0, idx;
        int cnt = 0, ans = 0;
        while (e < bound) {
            idx = getIdx(meow[e++]);
            if (++alphabet[idx] == 1) cnt++;
            while (cnt > N) {
                idx = getIdx(meow[s++]);
                if (--alphabet[idx] == 0) cnt--;
            }
            ans = Math.max(ans, e - s);
        }
        System.out.println(ans);
    }
}