import java.io.*;
import java.util.*;
class Problem implements Comparable<Problem> {
    int p, l;
    Problem(int p, int l) {
        this.p = p;
        this.l = l;
    }
    @Override
    public int compareTo(Problem o) {
        if (this.l != o.l) return this.l - o.l;
        return this.p - o.p;
    }
}
public class BOJ_21939 {
    public static void main(String[] args) throws IOException {
        int N, M, P, L;
        int[] solved = new int[100010];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        PriorityQueue<Problem> hardPQ = new PriorityQueue<>();
        PriorityQueue<Problem> easyPQ = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            P = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            hardPQ.add(new Problem(-P, -L));
            easyPQ.add(new Problem(P, L));
            solved[P] = L;
        }
        M = Integer.parseInt(br.readLine());
        for (int i = 1; i <= M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if (command.equals("add")) {
                P = Integer.parseInt(st.nextToken());
                L = Integer.parseInt(st.nextToken());
                hardPQ.add(new Problem(-P, -L));
                easyPQ.add(new Problem(P, L));
                solved[P] = L;
            }
            else if (command.equals("recommend")) {
                int type = Integer.parseInt(st.nextToken());
                if (type == 1) {
                    while (!hardPQ.isEmpty() && solved[-hardPQ.peek().p] != -hardPQ.peek().l) hardPQ.poll();
                    System.out.println(-hardPQ.peek().p);
                }
                else {
                    while (!easyPQ.isEmpty() && solved[easyPQ.peek().p] != easyPQ.peek().l) easyPQ.poll();
                    System.out.println(easyPQ.peek().p);
                }
            }
            else if (command.equals("solved")) {
                P = Integer.parseInt(st.nextToken());
                solved[P] = 0;
            }
        }
    }
}