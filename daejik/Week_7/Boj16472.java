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
			// �ִ� ���� ����
			if(len > ans) ans = len;
			
			int nextIdx = endIdx + 1;
			if(nextIdx >= input.length()) break;
            
            // ���� ���ĺ� üũ
			char nextAlpha = input.charAt(nextIdx);
            
			// �̹� �ν��� ���� �ִ� ���ĺ���
			if(check[nextAlpha - 'a'] > 0) {
				len++;
				endIdx++;
				check[nextAlpha- 'a']++;
			}
			// �ν��� ���� ���� ���ĺ���
			else {
				// �ϳ� �� �ν��� �� ����
				if(recogNum < N) {
					recogNum++;
					check[nextAlpha - 'a']++;
					endIdx++;
					len++;
				}
				// �ν� �Ұ�
				// startIdx ��ĭ �����̸鼭, �ν��ߴ� ���ĺ� üũ ī��Ʈ ����
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
