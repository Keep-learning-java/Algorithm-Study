import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_22252 {

    static class Gorilla {
        String name;
        int n;
        PriorityQueue<Integer> queue;

        Gorilla(String name, int n) {
            this.name = name;
            this.n = n;
            queue = new PriorityQueue<>(Collections.reverseOrder());
        }
    }

    static HashMap<String, Gorilla> hashMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int q = Integer.parseInt(br.readLine());
        StringTokenizer stk;
        long result = 0;

        for(int query=0; query<q; query++) {
            stk = new StringTokenizer(br.readLine());

            int op = Integer.parseInt(stk.nextToken());

            // 고릴라 정보 주어짐
            if(op == 1) {
                String name = stk.nextToken();
                int n = Integer.parseInt(stk.nextToken());

                // 이미 저장했던 고릴라?
                if(hashMap.containsKey(name)) {
                    Gorilla g = hashMap.get(name);
                    for(int i=0; i<n; i++)
                        g.queue.offer(Integer.parseInt(stk.nextToken()));
                }
                // 처음 나온 고릴라
                else {
                    Gorilla g = new Gorilla(name, n);
                    for(int i=0; i<n; i++)
                        g.queue.offer(Integer.parseInt(stk.nextToken()));

                    hashMap.put(name, g);
                }
            }
            // 고릴라와 거래
            else if(op == 2) {
                String name = stk.nextToken();

                // 그런 고릴라는 없단다..
                if(!hashMap.containsKey(name))
                    continue;

                Gorilla g = hashMap.get(name);

                int n = Integer.parseInt(stk.nextToken());

                if(n > g.queue.size())
                    n = g.queue.size();

                for(int i=0; i<n; i++)
                    result += g.queue.poll();
            }
        }
        System.out.println(result);
    }
}
