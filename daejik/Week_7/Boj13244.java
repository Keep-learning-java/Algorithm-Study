package Week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Pair{
	int a, b;
	
	Pair(int a, int b) {
		this.a = a;
		this.b = b;
	}
}
public class Boj13244 {
	private static int T, N, M;
	private static int parent[] = new int[1001];
	private static final String tree = "tree";
	private static final String graph = "graph";
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			Arrays.fill(parent, 0);
			for(int i=1; i<=N; i++) parent[i] = i;
			boolean treeFlag = false;
			
			while(M-- > 0) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				treeFlag = union(a, b);
				
				if(treeFlag == false) break;
			}
			
			if(treeFlag == false) {
				System.out.println(graph);
				continue;
			}

			int parentNum = find(1);
			
			for(int i=2; i<=N; i++) {
				if(parentNum != find(i)) {
					treeFlag = false;
					break;
				}
			}
			
			if(treeFlag == true) {
				System.out.println(tree);
			}
			else {
				System.out.println(graph);
			}
		}
	}
	public static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
				
		if(a == b) {
			return false;
		}
		else {
			parent[a] = b;
			return true;
		}
	}
	public static int find(int n) {
		if(parent[n] == n) return n;
		return parent[n] = find(parent[n]);
	}
}
