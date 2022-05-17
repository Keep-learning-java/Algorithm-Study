import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_21276 {

    static int n, m;
    static HashMap<String, Integer> map = new HashMap<>(); // 이름, 노드번호
    static String[] people;
    static int[] indegree = new int[1000];
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        people = br.readLine().split(" ");

        for(int i=0; i<n; i++) {
            map.put(people[i], i);
        }

        graph = new ArrayList[1000];
        for(int i=0; i<n; i++) {
            graph[i] = new ArrayList<>();
        }

        m = Integer.parseInt(br.readLine());
        for(int i=0; i<m ;i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            String x = stk.nextToken();
            String y = stk.nextToken();
            int nx = map.get(x);
            int ny = map.get(y);

            // y는 x의 조상 : y -> x
            graph[ny].add(nx);

            // indegree 늘려줌
            indegree[nx]++;
        }

        topologicalSort();
    }

    public static void topologicalSort() {
        Queue<Integer> q = new LinkedList<>();
        ArrayList<Integer>[] children = new ArrayList[n];
        int rootCount = 0; // 가문의 시조 몇명인지
        boolean[] root = new boolean[n]; // 시조인지 표시
        // init
        for(int i=0; i<n; i++) {
            children[i] = new ArrayList<>();
        }
        for(int i=0; i<n; i++) {
            if(indegree[i] == 0) {
                rootCount++;
                root[i] = true;
            }
        }

        // indegree가 0인 노드를 Queue에 담아준다
        for(int i=0; i<n; i++) {
            if(indegree[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            // 큐에서 값을 하나 꺼내고
            int node = q.poll();

            // node가 가리키고 있던 값들의 indegree를 1 감소 시킴
            // (사용한 노드와 간선은 지우는 것임)
            for(int child : graph[node]) {
                indegree[child]--;

                // indegree가 0이 되었다면 큐에 삽입
                if(indegree[child] == 0) {
                    q.offer(child);
                    // node의 자식으로도 추가해줍니다
                    children[node].add(child);
                }
            }
        }

        // print

        // 가문의 개수
        System.out.println(rootCount);

        // 각 가문의 시조의 이름 사전순
        ArrayList<String> rootName = new ArrayList<>();
        for(int i=0; i<n; i++) {
            // 시조이면
            if(root[i]) {
                rootName.add(people[i]);
            }
        }
        Collections.sort(rootName);
        for(String name : rootName) {
            System.out.print(name + " ");
        }
        System.out.println();

        // 이름의 사전 순대로
        String[] peopleCopy = people.clone(); // peopleCopy = people 하면 colyReference 되어버림
        Arrays.sort(peopleCopy);

        // 이름, 자식 수, 사전순 자식이름
        for(String name : peopleCopy) {
            int number = map.get(name);
            System.out.print(name + " " + children[number].size() + " ");

            ArrayList<String> childrenNameList = new ArrayList<>();
            for(int childrenNumber : children[number]) {
                childrenNameList.add(people[childrenNumber]);
            }

            Collections.sort(childrenNameList);
            for(String childrenName : childrenNameList) {
                System.out.print(childrenName + " ");
            }
            System.out.println();
        }

    }
}
