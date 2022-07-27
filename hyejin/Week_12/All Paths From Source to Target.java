class Solution {
    int index = 0;
    boolean[] visited = new boolean[15];

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        ArrayList<List<Integer>> result = new ArrayList<>();

        dfs(graph, 0, new ArrayList<>(), result);

        return result;
    }

    public void dfs(int[][] graph, int cur, ArrayList<Integer> history, ArrayList<List<Integer>> result) {

        // 기저 : 도착
        if(cur == graph.length-1) {
            List<Integer> path = (ArrayList<Integer>) history.clone();
            path.add(0, 0);
            result.add(path);
            return;
        }

        for(int i=0; i<graph[cur].length; i++) {
            int next = graph[cur][i];

            if(!visited[next]) {
                visited[next] = true;
                history.add(next);
                dfs(graph, next, history, result);
                history.remove(history.size()-1);
                visited[next] = false;
            }
        }
    }
}