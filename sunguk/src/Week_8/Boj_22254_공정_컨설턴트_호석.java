package Week_8;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @link https://www.acmicpc.net/problem/22254
 */
public class Boj_22254_공정_컨설턴트_호석 {

    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line, " ");
            int orderCount = Integer.parseInt(st.nextToken());
            int leftTime = Integer.parseInt(st.nextToken());

            List<Order> orders = new ArrayList(orderCount);
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < orderCount; i++) {
                orders.add(new Order(Integer.parseInt(st.nextToken())));
            }
            int finalAnswer = orderCount;

            int minFactoryCount = 1;
            int maxFactoryCount = orderCount;
            int mid = (minFactoryCount + maxFactoryCount) / 2;

            PriorityQueue<Integer> pq = new PriorityQueue<>(orderCount);
            while (minFactoryCount <= maxFactoryCount) {
                boolean isPossible = true;
                pq.clear();
                for (int i = 0; i < mid; i++) {
                    pq.offer(0);
                }
                for (Order order : orders) {
                    Integer factoryCapacity = pq.poll();
                    if (factoryCapacity + order.getTime() > leftTime) {
                        isPossible = false;
                        break;
                    }
                    pq.offer(factoryCapacity + order.getTime());
                }
                if (isPossible) {
                    finalAnswer = mid;
                    maxFactoryCount = mid - 1;
                } else {
                    minFactoryCount = mid + 1;
                }
                mid = (minFactoryCount + maxFactoryCount) / 2;
            }
            System.out.println(finalAnswer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Order {

    private final int time;

    public Order(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }
}