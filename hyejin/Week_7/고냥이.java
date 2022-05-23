package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_16472 {

    static int n;
    static int maxLength = 0;
    static int[] check = new int[26]; // 알파벳 몇번 나왔는지 기록
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        char[] arr = br.readLine().toCharArray();

        int start = 0;
        int end = 0;
        int count = 1; // 지금까지 나온 알파벳 종류 수
        check[arr[0]-'a'] = 1;

        while(true) {
            end++;

            // 종료 조건: 끝까지 봤음
            if(end == arr.length) {
                break;
            }

            int right = arr[end] - 'a';
            check[right]++;

            if(check[right] == 1) {
                count++;
            }

            // 알파벳 종류가 n보다 큰 상태
            while(count > n) {
                // start를 앞으로 한칸 옮길 것

                int left = arr[start] - 'a';
                check[left]--;

                // 이제 check[left] 알파벳은 문자열에 없음
                if(check[left] == 0) {
                    count--;
                }

                start++;
            }

            maxLength = Math.max(maxLength, end-start+1);
        }

        System.out.println(maxLength);
    }
}
