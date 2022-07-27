package Week_12;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Leet_797_All_Paths_From_Source_to_Target {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int targetNodeNumber = graph.length - 1;
        Queue<List<Integer>> queue = new LinkedList<>();
        for (int i = 0; i < graph[0].length; i++) {
            List<Integer> newDirections = new ArrayList<>();
            newDirections.add(0);
            newDirections.add(graph[0][i]);
            queue.add(newDirections);
        }
        List<List<Integer>> answerList = new ArrayList<>();
        while (!queue.isEmpty()) {
            List<Integer> list = queue.poll();
            if (list.get(list.size() - 1) == targetNodeNumber) {
                answerList.add(list);
            } else {
                int lastNode = list.get(list.size() - 1);
                for (int i = 0; i < graph[lastNode].length; i++) {
                    List<Integer> updateDirectionList = new ArrayList<>(list);
                    updateDirectionList.add(graph[lastNode][i]);
                    queue.add(updateDirectionList);
                }
            }
        }
        return answerList;
    }
}
