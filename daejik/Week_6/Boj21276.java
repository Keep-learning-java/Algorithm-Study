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
		// ans: �ڼյ��� ��� ���� ����Ʈ
		// list: ���� - �ڼ� ���谡 ��� ����Ʈ
		for(int i=0; i<N+1; i++) {
			ans.add(new ArrayList<String>());
			list.add(new ArrayList<Integer>());
		}
		
		// N���� �̸� �Է� �޾� �̸������� ���� -> map�� �ֱ�
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
		
		// M���� �ڼ� - ���� �Է� �ޱ�
		M = Integer.parseInt(br.readLine());
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			String child = st.nextToken();
			String parent = st.nextToken();
			Integer childIdx = getIdxMap.get(child);
			Integer parentIdx = getIdxMap.get(parent);
			
			arr[childIdx]++; // �������� ����
			list.get(parentIdx).add(childIdx);
		}
		
		// (1) ���� ���� ���� üũ�ϸ鼭 ť�� �ֱ�
		for(int i=0; i<N; i++) {
			if(arr[i] == 0) {
				ancestorList.add(getNameMap.get(i));
				q.add(i);
			}
		}
		
		// (2) �������� Ž��
		// ���� ���� ���� -> ���� ������ 0�� �Ǹ�, ���� �ڼ� ã���� ���
		// ���� ������ 0�̴� = nowIdx �ڼ��� nextIdx�̴�.
		while(!q.isEmpty()) {
			int nowIdx = q.poll();
			
			for(int nextIdx : list.get(nowIdx)) {
				if(--arr[nextIdx] == 0) {
					q.add(nextIdx);
					ans.get(nowIdx).add(getNameMap.get(nextIdx));
				}
			}
		}
		
		// (3) ���� ���
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
