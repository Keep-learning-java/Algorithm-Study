import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_9612 {

    static int n;
    static Map<String, Integer> words;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stk.nextToken());
        words = new HashMap<>();
        String maxKey = "";
        int maxValue = 1;

        // input
        for(int i=0; i<n; i++) {
            String word = br.readLine();

            // 처음 입력된 단어
            if(!words.containsKey(word)) {
                words.put(word, 1);
            }
            // 입력된 적 있음
            else {
                int count = words.get(word);
                words.replace(word, ++count);

                // max 갱신
                if(maxValue < count) {
                    maxValue = count;
                    maxKey = word;
                }
                // max값 같으면 사전순
                else if(maxValue == count) {
                    // word가 더 뒤에 글자임
                    if(word.compareTo(maxKey) > 0) {
                        maxKey = word;
                    }
                }
            }
        }

        System.out.println(maxKey + " " + maxValue);
    }
}