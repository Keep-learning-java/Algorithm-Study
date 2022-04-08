import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ_21939 {

    static class Problem implements Comparable<Problem> {
        int p;  // 문제 번호
        int l;  // 난이도

        Problem(int p, int l) {
            this.p = p;
            this.l = l;
        }

        @Override
        public int compareTo(Problem o) {
            // 난이도 -> 문제 번호 정렬
            if(this.l == o.l) {
                return this.p - o.p;
            }
            else {
                return this.l - o.l;
            }
        }
    }

    static int n, m;
    static TreeSet<Problem> set;
    static HashMap<Integer, Integer> map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        set = new TreeSet<>();
        map = new HashMap<>();

        n = Integer.parseInt(br.readLine());

        for(int i=0; i<n; i++) {
            stk = new StringTokenizer(br.readLine());

            int p = Integer.parseInt(stk.nextToken());
            int l = Integer.parseInt(stk.nextToken());

            set.add(new Problem(p, l));
            map.put(p, l);
        }

        m = Integer.parseInt(br.readLine());

        for(int i=0; i<m; i++) {
            stk = new StringTokenizer(br.readLine());
            String oper = stk.nextToken();

            // recommend
            if(oper.equals("recommend")) {
                int x = Integer.parseInt(stk.nextToken());
                // 가장 어려운 문제
                if(x == 1) {
                    System.out.println(set.last().p);
                }
                // 가장 쉬운 문제
                else if(x == -1) {
                    System.out.println(set.first().p);
                }
            }
            // add
            else if(oper.equals("add")) {
                int p = Integer.parseInt(stk.nextToken());
                int l = Integer.parseInt(stk.nextToken());

                set.add(new Problem(p, l));
                map.put(p, l);
            }
            // solved
            else if(oper.equals("solved")) {
                int p = Integer.parseInt(stk.nextToken());

                set.remove(new Problem(p, map.get(p)));
                map.remove(p);
            }
        }
    }
}
