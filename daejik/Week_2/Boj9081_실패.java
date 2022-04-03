import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj9081 {

	public static String arr;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			arr = br.readLine();
			
			int i = arr.length() - 1;
			int j = i;
			//(1) [i-1] < [i]인 i 찾기
			while(i > 0 && (arr.charAt(i - 1) >= arr.charAt(i))) i--;
			//(예외) 현재 상태가 마지막 단어임
			if(i <= 0) {
				bw.write(arr + "\n");
				continue;
			}
						
			//(2) [i-1] < j인 j 찾기
			while(arr.charAt(i - 1) > arr.charAt(j)) j--;

			//(3) [i-1] , [j] swap
			arr = swap(arr, i - 1, j);
			
			//(4) i ~ N-1 swap
			j = arr.length() - 1;
			while(i < j) {
				arr = swap(arr, i, j);
				i++;
				j--;
			}
			
			bw.write(arr + "\n");
		}
		bw.close();
	}
	public static String swap(String data, int i, int j) {
		char[] ret = data.toCharArray();
		char temp = ret[i];
		ret[i] = ret[j];
		ret[j] = temp;
		
		return new String(ret);
		
	}
}
