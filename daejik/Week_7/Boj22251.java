package Week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj22251 {
	static boolean led[][] = 
		{
				{true, true, true, true, true, true, false},		// 0
				{false, true, true, false, false, false, false},	// 1
				{true, true, false, true, true, false, true},		// 2
				{true, true, true, true, false, false, true},		// 3
				{false, true, true, false, false, true, true},		// 4
				{true, false, true, true, false, true, true},		// 5
				{true, false, true, true, true, true, true},		// 6
				{true, true, true, false, false, false, false},		// 7
				{true, true, true, true, true, true, true},			// 8
				{true, true, true, true, false, true, true}			// 9
		};
	static int[] nowFloor;
	static int convCnt[][] = new int[10][10];
	static boolean check[] = new boolean[1000000];
	static int N, K, P, X, ans;

	public static void init() {
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				if(i==j) continue;
				// i -> j 로 바꾸는데 필요한 toggle 횟수
				int cnt = 0;
				for(int k=0; k<7; k++) {
					if(led[i][k] != led[j][k]) cnt++;
				}
				convCnt[i][j] = cnt;
			}
		}
	}
	public static void main(String[] args) throws IOException {
		init();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		nowFloor = intToArr(X);
		check[X] = true;
		for(int i=1; i<=N; i++) {
			if(i == X) continue;
			
			int[] arr = intToArr(i);
			if(canConvert(arr) == true) ans++;
		}
	
		System.out.println(ans);
	}
	public static boolean canConvert(int[] arr) {
		int toggleCnt = 0;
		
		for(int idx=0; idx<K; idx++) {
			int nowNum = nowFloor[idx];
			int toNum = arr[idx];
			
			toggleCnt += convCnt[nowNum][toNum];
		}
		
		if(toggleCnt <= P && toggleCnt >= 1) return true;
		return false;
	}
	public static int[] intToArr(int n) {
		int[] arr = new int[K];
		for(int idx = K - 1; idx >= 0 && n > 0; idx--) {
			arr[idx] = n % 10;
			n /= 10;
		}
		return arr;
	}

}
