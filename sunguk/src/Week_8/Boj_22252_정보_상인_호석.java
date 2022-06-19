package Week_8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/22252
 */
public class Boj_22252_정보_상인_호석 {

    final int EARNING = 1;
    final int BUYING = 2;

    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int lineCounts = Integer.parseInt(br.readLine());
            Map<String, PriorityQueue<Integer>> gorillaInfoMap = new HashMap<>();
            long total = 0;
            while (lineCounts-- > 0) {
                String line = br.readLine();
                StringTokenizer st = new StringTokenizer(line, " ");
                int command = Integer.parseInt(st.nextToken());
                String gorillaName = st.nextToken();
                if (command == BUYING) {
                    int buyingCount = Integer.parseInt(st.nextToken());
                    PriorityQueue<Integer> items = gorillaInfoMap.get(gorillaName);
                    if (items == null) continue;
                    while (buyingCount-- > 0) {
                        if (items.isEmpty()) {
                            break;
                        } else {
                            total += items.poll();
                        }
                    }
                } else if (command == EARNING) {
                    PriorityQueue<Integer> items = gorillaInfoMap.getOrDefault(gorillaName, new PriorityQueue<Integer>(Comparator.reverseOrder()));
                    int itemCount = Integer.parseInt(st.nextToken());
                    while (itemCount-- > 0) {
                        items.add(Integer.parseInt(st.nextToken()));
                    }
                    gorillaInfoMap.put(gorillaName, items);
                }
            }
            System.out.println(total);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
