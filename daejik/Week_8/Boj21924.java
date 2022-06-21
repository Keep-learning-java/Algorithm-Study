package Week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj21924 {
	private static int[] parent;
	private static boolean[] visitedNode;
	private static ArrayList<ArrayList<Pair>> graph;
	private static ArrayList<Struct> vector;
	private static Queue<Integer> q;
	private static int N, M;
	private static long totalCost, ans;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N + 1];
		visitedNode = new boolean[N + 1];
		vector = new ArrayList<>();
		q = new LinkedList<Integer>();
		graph = new ArrayList<>();
		
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<Pair>());
		}
		
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			totalCost += cost;
			graph.get(a).add(new Pair(b, cost));
			graph.get(b).add(new Pair(a, cost));
			vector.add(new Struct(a, b, cost));
		}
		// 연결안된 빌딩이 있는지 체크
		bfs();
		
		for(int i=1; i<=N; i++) {
			parent[i] = i;
			if(!visitedNode[i]) {
				System.out.println(-1);
				return;
			}
		}
		
		// vector 오름차순 정렬
		Collections.sort(vector);
		
		for(Struct now : vector) {
			union(now.a, now.b, now.cost);
		}
		System.out.println(totalCost - ans);
	}
	public static void bfs() {
		q.add(1);
		visitedNode[1] = true;
		
		while(!q.isEmpty()) {
			
			int now = (int) q.poll();
			
			for(int i=0; i<graph.get(now).size(); i++) {
				int nextNode = graph.get(now).get(i).node;
				
				if(!visitedNode[nextNode]) {	
					visitedNode[nextNode] = true;
					q.add(nextNode);
				}
			}
		}
	}
	public static void union(int node1, int node2, int cost) {
		int aParent = find(node1);
		int bParent = find(node2);
		
		if(aParent != bParent) {
			ans += (long)cost;
			parent[bParent] = aParent;
		}
	}
	public static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
}
class Pair {
	int node, edge;
	
	Pair(int node, int edge){
		this.node = node;
		this.edge = edge;
	}
}
class Struct implements Comparable<Struct>{
	int a, b, cost;
	
	Struct(int a, int b, int cost){
		this.a = a;
		this.b = b;
		this.cost = cost;
	}

	@Override
	public int compareTo(Struct o) {
		if(o.cost > cost) return -1;
		else if(o.cost < cost) return 1;
		else return 0;
	}
	
	@Override
	public String toString() {
		return "(" + this.a + "," + this.b + "):"+ this.cost;
	}
}
