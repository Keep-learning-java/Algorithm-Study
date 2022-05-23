import java.io.*;
import java.util.*;

public class BOJ_21276 {
    static int N, M;
    static TreeMap<String, Integer> map = new TreeMap<>();
    static String[] names;
    static ArrayList<Integer>[] adj;
    static ArrayList<Integer>[] tree;
    static int[] indegrees;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        indegrees = new int[N + 1];
        adj = new ArrayList[N + 1];
        tree = new ArrayList[N + 1];
        names = new String[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            String name = st.nextToken();
            map.put(name, i);
            names[i] = name;
            adj[i] = new ArrayList<>();
            tree[i] = new ArrayList<>();
        }
        M = Integer.parseInt(br.readLine());
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int child = map.get(st.nextToken());
            int parent = map.get(st.nextToken());
            adj[parent].add(child);
            indegrees[child]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<String> roots = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (indegrees[i] == 0) {
                queue.add(i);
                roots.add(names[i]);
            }
        }
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int next : adj[curr]) {
                if (--indegrees[next] == 0) {
                    queue.add(next);
                    tree[curr].add(next);
                }
            }
        }
        System.out.println(roots.size());
        Collections.sort(roots);
        for (String name : roots) {
            System.out.print(name + " ");
        }
        System.out.println();
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            StringBuilder output = new StringBuilder(e.getKey());
            ArrayList<Integer> childs = tree[e.getValue()];
            output.append(" " + childs.size() + " ");
            ArrayList<String> tNames = new ArrayList<>();
            for (int child : childs) {
                tNames.add(names[child]);
            }
            Collections.sort(tNames);
            for (String name : tNames) {
                output.append(name + " ");;
            }
            System.out.println(output.toString());
        }
    }
}
