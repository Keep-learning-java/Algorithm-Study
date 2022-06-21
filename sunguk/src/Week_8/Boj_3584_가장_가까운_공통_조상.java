package Week_8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/3584
 */
class Boj_3584_가장_가까운_공통_조상 {

    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int caseCount = Integer.parseInt(br.readLine());
            while (caseCount-- > 0) {
                Map<Integer, Node> nodeMap = new HashMap<>();
                int nodeCount = Integer.parseInt(br.readLine());
                while (nodeCount-- > 1) { // N-1 번 노드 정보를 읽음
                    String line = br.readLine();
                    StringTokenizer st = new StringTokenizer(line, " ");
                    int parentNum = Integer.parseInt(st.nextToken());
                    int childNum = Integer.parseInt(st.nextToken());

                    Node parentNode = nodeMap.getOrDefault(parentNum, new Node(parentNum));
                    Node childNode = nodeMap.getOrDefault(childNum, new Node(childNum));

                    parentNode.addChild(childNode);
                    childNode.setParent(parentNode);
                    nodeMap.put(parentNum, parentNode);
                    nodeMap.put(childNum, childNode);

                }
                String line = br.readLine();
                StringTokenizer st = new StringTokenizer(line, " ");
                int firstNodeNum = Integer.parseInt(st.nextToken());
                int secondNodeNum = Integer.parseInt(st.nextToken());

                Node firstNode = nodeMap.get(firstNodeNum);
                Set<Node> parentsSet = getParentsSet(firstNode);
                Node currentNode = nodeMap.get(secondNodeNum);
                while (!parentsSet.contains(currentNode)) {
                    currentNode = currentNode.getParent();
                    if (currentNode.isRoot()) {
                        break;
                    }
                }
                System.out.println(currentNode.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Set<Node> getParentsSet(Node node) {
        Node currentNode = node;
        Set<Node> parentsSet = new HashSet<>();
        parentsSet.add(currentNode);
        while (!currentNode.isRoot()) {
            parentsSet.add(currentNode.getParent());
            currentNode = currentNode.getParent();
        }
        return parentsSet;
    }


}

class Node {
    public int getId() {
        return id;
    }

    public Node getParent() {
        return parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    private final int id;
    private Node parent;
    private final List<Node> children = new ArrayList<>();

    public Node(int id) {
        this.id = id;
    }

    void addChild(Node node) {
        if (node != null) {
            children.add(node);
        }
    }

    void setParent(Node node) {
        if (node != null) {
            parent = node;
        }
    }

    boolean isRoot() {
        return parent == null;
    }
}
