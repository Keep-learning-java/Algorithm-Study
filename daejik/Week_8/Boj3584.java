package Week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj3584 {
	public static Queue<Integer> q = new LinkedList<Integer>();
	public static int T, N;
	public static int[] visited = new int[100001];
	public static int[] parent = new int[100001];
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			Arrays.fill(visited, 0);
			Arrays.fill(parent, 0);
			while(!q.isEmpty()) q.poll();
			
			N = Integer.parseInt(br.readLine());
			
			for(int n=1; n<N; n++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				
				parent[B] = A;
			}
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			q.add(node1);
			q.add(node2);
			visited[node1] = 1;
			visited[node1] = 1;
			
			
			while(true) {
				int nowNode = q.poll();
				int parentNode = parent[nowNode];
				
				if(visited[parentNode] >= 1) {
					System.out.println(parentNode);
					break;
				}
				else if(visited[parentNode] == 0){
					visited[parentNode]++;
					q.add(parentNode);
				}
			}
		}
	}
}
