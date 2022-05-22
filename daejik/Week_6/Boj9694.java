import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;


public class Boj9694 {
	static int T, N, M;
	static int visited[] = new int[21];
	static ArrayList<Integer> ans = new ArrayList<Integer>();
	static ArrayList<Integer> tmp = new ArrayList<Integer>();
	//static ArrayList<Integer>
	static ArrayList<ArrayList<Pair>> graph = new ArrayList<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		// graph 생성
		for(int i=1; i<=21; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int t=1; t<=T; t++) {
			// 초기화
			ans.clear();
			tmp.clear();
			Arrays.fill(visited, Integer.MAX_VALUE);
			
			// N, M 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			// graph 초기화
			for(int i=0; i<=M; i++) {
				graph.get(i).clear();
			}
			
			// 관계 입력
			for(int i=0, x, y, z; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				z = Integer.parseInt(st.nextToken());
				
				graph.get(x).add(new Pair(y, z));
				graph.get(y).add(new Pair(x, z));
			}
			
			// dfs 탐색 시작
			tmp.add(0);
			visited[0] = 0;
			dfs(0);
			
			System.out.print("Case #"+t+":");
			
			if(ans.isEmpty()) {
				System.out.println(" -1");
			}
			else {
				for(int now : ans) {
					System.out.print(" "+now);
				}
				System.out.println();				
			}
		}
	}
	private static void dfs(int nowIdx) {
		if(nowIdx == M - 1) {
			ans.clear();
			ans.addAll(tmp);
			return;
		}
		
		for(Pair next : graph.get(nowIdx)) {
			int nextIdx = next.nextNode;
			int val = next.val;
			
			if(val + visited[nowIdx] < visited[nextIdx]) {
				visited[nextIdx] = val + visited[nowIdx];
				tmp.add(nextIdx);
				dfs(nextIdx);
				tmp.remove(tmp.size() - 1);
			}
		}
	}
	private static class Pair{
		int nextNode, val;
		
		Pair(int nextNode, int val){
			this.nextNode = nextNode;
			this.val = val;
		}
	}
}
