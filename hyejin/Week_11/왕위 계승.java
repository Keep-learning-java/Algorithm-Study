package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_5021 {

    static int N, M;
    static HashMap<String, Integer> indegrees = new HashMap<>();
    static HashMap<String, Double> lineage = new HashMap<>();
    static HashMap<String, ArrayList<String>> graph = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        // 건국한 사람
        String root = br.readLine();
        indegrees.put(root, 0);

        for(int i=0; i<N; i++) {
            stk = new StringTokenizer(br.readLine());
            String child = stk.nextToken();
            String parent1 = stk.nextToken();
            String parent2 = stk.nextToken();

            // input indegree
            int value1 = indegrees.getOrDefault(parent1, 0);
            int value2 = indegrees.getOrDefault(parent2, 0);
            int value3 = indegrees.getOrDefault(child, 0);

            indegrees.put(parent1, value1);
            indegrees.put(parent2, value2);
            indegrees.put(child, value3+2);

            // input lineage
            lineage.put(child, 0.0);
            lineage.put(parent1, 0.0);
            lineage.put(parent2, 0.0);

            // input 부모자식 관계
            ArrayList<String> childrenOfParent1 = graph.getOrDefault(parent1, new ArrayList<>());
            ArrayList<String> childrenOfParent2 = graph.getOrDefault(parent2, new ArrayList<>());
            childrenOfParent1.add(child);
            childrenOfParent2.add(child);

            graph.put(parent1, childrenOfParent1);
            graph.put(parent2, childrenOfParent2);
        }
        lineage.put(root, 1.0);

        ArrayList<String> candidates = new ArrayList<>();
        for(int i=0; i<M; i++) {
            candidates.add(br.readLine());
        }
        topologicalSort(candidates);
    }

    public static void topologicalSort(ArrayList<String> candidates) {
        Queue<String> queue = new LinkedList<>();

        // indegree 0인 사람을 Queue에 담는다
        Set<String> keySet = indegrees.keySet();
        for (String name : keySet) {
            if(indegrees.get(name) == 0) {
                queue.offer(name);
            }
        }

        while (!queue.isEmpty()) {
            // 큐에서 사람 하나 꺼냄
            String now = queue.poll();

            // 그 사람의 자식을 꺼내서 인디그리 감소시킴
            for(String child : graph.getOrDefault(now, new ArrayList<>())) {
                int indegree = indegrees.get(child);
                indegree--;
                indegrees.put(child, indegree);

                double blood = lineage.get(child);
                blood += lineage.get(now);
                lineage.put(child, blood);

                // 0되면 큐에 다시 넣음
                if(indegree == 0) {
                    queue.offer(child);

                    double lineageValue = lineage.get(child);
                    lineageValue /= 2;
                    lineage.put(child, lineageValue);
                }
            }
        }

        double blood = 0;
        String selected = null;
        for (String candidate : candidates) {
            double currentBloodValue = lineage.getOrDefault(candidate, 0.0);
            if(blood < currentBloodValue) {
                blood = currentBloodValue;
                selected = candidate;
            }
        }

        System.out.println(selected);
    }
}
