package Week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj10775 {
	static int[] parent;
	static int numGates, numPlanes, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		numGates = Integer.parseInt(br.readLine());
		numPlanes = Integer.parseInt(br.readLine());
		parent = new int[numGates + 1];
		
		for(int i=1; i<=numGates; i++) parent[i] = i;
		
		for(int i=0; i<numPlanes; i++) {
			int wantGateIdx = Integer.parseInt(br.readLine());
			
			int findParkingIdx = find(wantGateIdx);
			if(findParkingIdx == 0) break;
			parent[findParkingIdx] = findParkingIdx - 1;
			ans++;
		}
		System.out.println(ans);
	}
	public static int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}

}
