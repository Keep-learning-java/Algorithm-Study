import java.util.*;
import java.io.*;

public class BOJ_9081 {
    public static String getNextWord(String word) {
        int u = -1, v = 0;
        char[] tWord = word.toCharArray();
        int idx = word.length() - 1;
        while (idx > 0 && tWord[idx - 1] >= tWord[idx]) idx--;
        u = idx - 1;
        if (u == -1) return word;
        idx = word.length() - 1;
        while (idx >= u + 1 && tWord[u] >= tWord[idx]) idx--;
        v = idx;
        char t = tWord[u];
        tWord[u] = tWord[v];
        tWord[v] = t;
        Arrays.sort(tWord, u + 1, word.length());
        return new String(tWord);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String word = br.readLine();
            System.out.println(getNextWord(word));
        }
    }
}
