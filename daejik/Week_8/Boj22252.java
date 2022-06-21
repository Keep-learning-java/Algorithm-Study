package Week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Boj22252 {
	private static HashMap<String, String> hashMap = new HashMap<>();
	private static ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
	private static int Q, gorilCnt;
	private static long ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Q = Integer.parseInt(br.readLine());
		
		while(Q-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			
			String nameValue = hashMap.get(name);
			int arrIdx = 0;
			
			// name - arr인덱스
			if(nameValue == null) {
				arr.add(new ArrayList<Integer>());
				arrIdx = gorilCnt;
				hashMap.put(name, String.valueOf(arrIdx));
				gorilCnt++;
			}
			else {
				arrIdx = Integer.parseInt(nameValue);
			}
			
			// 고릴라 정보 취득
			if(type == 1) {
				inputData(st, arrIdx);
			}
			// 고릴라에서 정보 빼오기
			else {
				ans += (long)getData(st, arrIdx);
			}
		}
		System.out.println(ans);
	}
	public static void inputData(StringTokenizer st, int idx) {
		int inputNum = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<inputNum; i++) {
			arr.get(idx).add(Integer.parseInt(st.nextToken()));
		}
	}
	public static int getData(StringTokenizer st, int idx) {
		int ret = 0;
		int getNum = Integer.parseInt(st.nextToken());
		
		Collections.sort(arr.get(idx), Collections.reverseOrder());
		
		while(getNum-- > 0 && !arr.get(idx).isEmpty()) {
			ret += (int)arr.get(idx).get(0);
			arr.get(idx).remove(0);
		}
		return ret;
	}

}
