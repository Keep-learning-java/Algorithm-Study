class Solution {
    static int[] dx = { 1, 0, 0, -1 }; // d, l, r, u
	static int[] dy = { 0, -1, 1, 0 };
	static String[] letter = { "d", "l", "r", "u" };

	String answer = "";
	int n, m;
	int r, c;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
		this.n = n;
		this.m = m;
		this.r = r;
		this.c = c;

		// 목적지까지 남은 거리
		int diff = Math.abs(y - c) + Math.abs(x - r);

		if (diff % 2 != k % 2)
			return "impossible";
		if (diff > k)
			return "impossible";

		dfs(x, y, k, "", diff);

		if (answer.equals(""))
			answer = "impossible";

		return answer;
    }
    
	public boolean dfs(int x, int y, int k, String path, int diff) {
		// 목적지에 도착했고, 이동거리 k인 경우
		if (k == 0 && diff == 0) {
			answer = path;
			return true;
		}

		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];

			// 범위 체크
			if (ny <= 0 || ny > m || nx <= 0 || nx > n)
				continue;

			// 이동 가능 거리가 모자라면 SKIP
			if (diff > k)
				continue;
			
			// 목적지까지 남은 거리와, 남은 이동가능 거리의 홀짝이 맞아야 함
			if (diff % 2 == k % 2) {
				int nextDiff = Math.abs(r - nx) + Math.abs(c - ny);
				if (dfs(nx, ny, k - 1, path + letter[i], nextDiff)) {
					return true;
				}
			}

		}
		return false;
	}

}