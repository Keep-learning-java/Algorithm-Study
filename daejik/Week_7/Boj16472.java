package Week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj16472 {
	static int N;
	static int recogNum, ans;
	static int check[] = new int['z'-'a'+1];
	static String input;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		input = br.readLine();
	
		int startIdx = 0, endIdx = 0;
		int len = 1, recogNum = 1;
		check[input.charAt(0) - 'a']++;

		
		while(endIdx < input.length()) {
			// 최대 길이 갱신
			if(len > ans) ans = len;
			
			int nextIdx = endIdx + 1;
			if(nextIdx >= input.length()) break;
            
            // 다음 알파벳 체크
			char nextAlpha = input.charAt(nextIdx);
            
			// 이미 인식한 적이 있는 알파벳임
			if(check[nextAlpha - 'a'] > 0) {
				len++;
				endIdx++;
				check[nextAlpha- 'a']++;
			}
			// 인식한 적이 없는 알파벳임
			else {
				// 하나 더 인식할 수 있음
				if(recogNum < N) {
					recogNum++;
					check[nextAlpha - 'a']++;
					endIdx++;
					len++;
				}
				// 인식 불가
				// startIdx 한칸 움직이면서, 인식했던 알파벳 체크 카운트 감소
				else {
					char startAlpha = input.charAt(startIdx);
					check[startAlpha - 'a']--;
					if(check[startAlpha - 'a'] == 0) recogNum--;
					startIdx++;
					len--;
				}
			}
		}
		System.out.println(ans);
	}

}
