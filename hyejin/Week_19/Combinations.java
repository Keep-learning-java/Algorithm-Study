class Solution {
    public ListListInteger combine(int n, int k) {
        ListListInteger ret = new ArrayListListInteger();
		boolean[] check = new boolean[n + 1];

		dfs(ret, new ArrayList(), n, k, 1, 1);

		return ret;
    }
    
    public void dfs(ListListInteger ret, ListInteger inner, int n, int k, int depth, int cur) {
		 기저  k개 다 찼음
		if (depth  k) {
			ret.add(new ArrayListInteger(inner));
			return;
		}

		 cur보다 큰 숫자들 백트래킹
		for (int i = cur; i = n; i++) {
			inner.add(i);
			dfs(ret, inner, n, k, depth + 1, i + 1);
			inner.remove(inner.size() - 1);
		}

	}
}