package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_1283 {

    static int n;
    static HashMap<Character, Integer> hashMap; // <단축키, index번째 단어>
    static ArrayList<String> words;
    static boolean[] check;
    static ShortCut[] shortCuts;

    static class ShortCut {
        String word;
        int index;

        ShortCut(String word, int index) {
            this.word = word;
            this.index = index;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        hashMap = new HashMap<>();
        words = new ArrayList<>();
        check = new boolean[n];
        shortCuts = new ShortCut[n];

        // input
        for(int i=0; i<n; i++) {
            String input = br.readLine();
            String lowerCase = input.toLowerCase();
            words.add(input);

            boolean flag = false; // 1번 순서로 단축키 지정했는지?

            // 1. 단어의 첫 글자가 단축키로 지정되었는가?
            StringTokenizer stk = new StringTokenizer(input);
            while(stk.hasMoreTokens()) {
                String temp = stk.nextToken();

                // ㄴㄴ
                if(!hashMap.containsKey(temp.toLowerCase().charAt(0))) {
                    // 단축키로 지정
                    hashMap.put(temp.toLowerCase().charAt(0), i);
                    shortCuts[i] = new ShortCut(input, input.indexOf(temp));
                    check[i] = true;
                    flag = true;
                    break;
                }
            }

            if(flag)
                continue;

            // 2. 왼쪽부터 탐색
            for(int j=0; j<input.length(); j++) {
                char ch = lowerCase.charAt(j);

                // 공백문자일시 스킵
                if(ch == ' ')
                    continue;

                // 단축키로 지정 안됐으면 -> 단축키 설정
                if(!hashMap.containsKey(ch)) {
                    shortCuts[i] = new ShortCut(input, j);
                    hashMap.put(ch, i);
                    check[i] = true; // 단축키를 가진 단어라고 표시
                    break;
                }

            }

        }
        // print
        for(int i=0; i<n; i++) {
            String word = words.get(i);
            StringBuilder sb = new StringBuilder(word);

            // 단축키 지정된 단어면
            if(check[i]) {
                ShortCut shortCut = shortCuts[i];
                sb.insert(shortCut.index, "[");
                sb.insert(shortCut.index+2, "]");
                System.out.println(sb);
            }
            // 안됐으면 그냥 출력
            else {
                System.out.println(word);
            }
        }

    }
}
