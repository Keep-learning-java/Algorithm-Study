import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


public class Boj1283 {
	static int N;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Set<String> s = new HashSet<>();
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// ���ʿ������� ���������� �ܾ� ���
			ArrayList<String> arr = new ArrayList<>();
			while(st.hasMoreTokens()) {
				arr.add(st.nextToken());
			}
			
			String ans = null;
			
			// ��� 1
			for(String now : arr) {
				String firstStr = String.valueOf(now.charAt(0)).toLowerCase();
				
				if(s.contains(firstStr) == false) {
					ans = makeAns(arr, now, 0);
					s.add(firstStr);
					break;					
				}
			}
			
			if(ans != null) {
				System.out.println(ans);
				continue;
			}
			
			// ��� 2
			for(String now : arr) {
				for(int j=1; j<now.length(); j++) {
					String checkStr = String.valueOf(now.charAt(j)).toLowerCase();
					
					if(s.contains(checkStr) == false) {
						ans = makeAns(arr, now, j);
						s.add(checkStr);
						break;					
					}					
				}
				if(ans != null)
					break;
			}
			
			if(ans != null) {
				System.out.println(ans);
				continue;
			}
			
			// ��� 3
			System.out.println(makeAns(arr, null, 0));
		}
	}
	
	public static String makeAns(ArrayList<String> arr, String now, int idx) {
		// now ���ڿ��� idx��°�� ����Ű
		String ans = null;
		
		for(String str : arr) {
			if(str == now) {
				// idx��°�� [] ó�� ����� �Ѵ�.
				// (0 ~ idx - 1) + [idx] + (idx + 1 ~)
				str = (str.substring(0, idx)).concat("[" + str.substring(idx, idx + 1) + "]").concat(str.substring(idx + 1));
			}
			
			if(ans == null) {
				ans = str;
			}
			else {
				ans = ans + " " + str;
			}
		}
		
		return ans;
	}
}
