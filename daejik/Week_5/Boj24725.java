import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj24725 {
	static char[][] board;
	static boolean[][] visited;
	static int N, M, ans;
	static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new char[N][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			board[i] = br.readLine().toCharArray();
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(board[i][j] == 'E' || board[i][j] == 'I') {
					visited[i][j] = true;
					findMBTI(i, j, 1, -1);
					visited[i][j] = false;
				}
			}
		}
		System.out.println(ans);
	}
	public static void findMBTI(int r, int c, int depth, int dir) {
		
		if(depth == 4) {
			ans++;
			return;
		}
		
		if(dir == -1) {
			// 아직 이동 방향이 정해지지 않았음
			for(int d = 0; d < 8; d++) {
				int nr = r + dr[d], nc = c+dc[d];
				
				if(canNext(nr, nc)) {
					if(board[nr][nc] == 'N' || board[nr][nc] == 'S') {
						visited[nr][nc] = true;
						findMBTI(nr, nc, depth + 1, d);
						visited[nr][nc] = false;						
					}					
				}
			}
		}
		else {
			int nr = r + dr[dir], nc = c + dc[dir];
			
			if(canNext(nr, nc) && visited[nr][nc] == false) {
				
				if(depth == 2) {
					if(board[nr][nc] == 'F' || board[nr][nc] == 'T') {
						visited[nr][nc] = true;
						findMBTI(nr, nc, depth + 1, dir);
						visited[nr][nc] = false;
					}
				}
				else if(depth == 3) {
					if(board[nr][nc] == 'P' || board[nr][nc] == 'J') {
						visited[nr][nc] = true;
						findMBTI(nr, nc, depth + 1, dir);
						visited[nr][nc] = false;
					}
				}			
			}
		}
	}
	public static boolean canNext(int r, int c) {
		if(r<0 || c<0 || r>=N || c>=M) return false;
		return true;
	}
}
