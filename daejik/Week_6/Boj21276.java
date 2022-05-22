import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Boj21276 {
	static int N, M;
	static int arr[];
	static ArrayList<ArrayList<String>> ans = new ArrayList<>();
	static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
	static ArrayList<String> ancestorList = new ArrayList<String>();
	static HashMap<String, Integer> getIdxMap = new HashMap<String, Integer>();
	static HashMap<Integer, String> getNameMap = new HashMap<>();
	static Queue<Integer> q = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		// ans: 자손들이 담긴 최종 리스트
		// list: 조상 - 자손 관계가 담긴 리스트
		for(int i=0; i<N+1; i++) {
			ans.add(new ArrayList<String>());
			list.add(new ArrayList<Integer>());
		}
		
		// N개의 이름 입력 받아 이름순으로 정렬 -> map에 넣기
		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayList<String> inputName = new ArrayList<String>();
		for(int i=0; i<N; i++) {
			inputName.add(st.nextToken());
		}
		Collections.sort(inputName);
		
		for(int i=0; i<inputName.size(); i++) {
			getIdxMap.put(inputName.get(i), i);
			getNameMap.put(i, inputName.get(i));
		}
		
		// M개의 자손 - 조상 입력 받기
		M = Integer.parseInt(br.readLine());
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			String child = st.nextToken();
			String parent = st.nextToken();
			Integer childIdx = getIdxMap.get(child);
			Integer parentIdx = getIdxMap.get(parent);
			
			arr[childIdx]++; // 진입차수 증가
			list.get(parentIdx).add(childIdx);
		}
		
		// (1) 가장 조상 개수 체크하면서 큐에 넣기
		for(int i=0; i<N; i++) {
			if(arr[i] == 0) {
				ancestorList.add(getNameMap.get(i));
				q.add(i);
			}
		}
		
		// (2) 위상정렬 탐색
		// 진입 차수 감소 -> 진입 차수가 0이 되면, 다음 자손 찾으러 출발
		// 진입 차수가 0이다 = nowIdx 자손이 nextIdx이다.
		while(!q.isEmpty()) {
			int nowIdx = q.poll();
			
			for(int nextIdx : list.get(nowIdx)) {
				if(--arr[nextIdx] == 0) {
					q.add(nextIdx);
					ans.get(nowIdx).add(getNameMap.get(nextIdx));
				}
			}
		}
		
		// (3) 정답 출력
		System.out.println(ancestorList.size());
		Collections.sort(ancestorList);
		for(String ancestorName : ancestorList) {
			System.out.print(ancestorName + " ");
		}
		System.out.println();
		for(int i=0; i<N; i++) {
			String Name = inputName.get(i);
			
			System.out.print(Name + " " + ans.get(i).size());
			Collections.sort(ans.get(i));
			for(String ansName : ans.get(i)) {
				System.out.print(" " + ansName);
			}
			System.out.println();
		}
	}
}
