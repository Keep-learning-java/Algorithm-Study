package Week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj16929 {
	static char[][] map = new char[50][50];
	static boolean[][] visited = new boolean[50][50];
	static int dr[]= {1, 0, -1, 0}, dc[]= {0, 1, 0, -1};
	static int numRow, numCol;
	static boolean flag;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		numRow = Integer.parseInt(st.nextToken());
		numCol = Integer.parseInt(st.nextToken());
		
		for(int row = 0; row < numRow; row++) {
			map[row] = br.readLine().toCharArray();
		}
		
		for(int row = 0; row < numRow; row++) {
			for(int col = 0; col < numCol; col++) {
				
				visited[row][col] = true;
				dfs(row, col, row, col, 1);
				visited[row][col] = false;
				
				if(flag == true) {
					System.out.println("Yes");
					return;
				}
			}
		}
		System.out.println("No");
	}
	public static void dfs(int nowR, int nowC, int originR, int originC, int cnt) {
		if(flag == true) return;

		for(int direction=0; direction<4; direction++) {	
			int nextR = nowR + dr[direction];
			int nextC = nowC + dc[direction];
			
			if(!canNext(nextR, nextC)) continue;
			if(visited[nextR][nextC]) {
				if(nextR == originR && nextC == originC && cnt >= 3) {
					flag = true;
					return;
				}
			} else {
				if(map[nextR][nextC] == map[originR][originC]) {
					visited[nextR][nextC] = true;
					dfs(nextR, nextC, originR, originC, cnt + 1);
					visited[nextR][nextC] = false;
				}
			}
		}
	}
	public static boolean canNext(int r, int c) {
		if(r<0 || c<0 || r>=numRow || c>=numCol) return false;
		return true;
	}
}
