import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_3691 {
    static class Part implements Comparable<Part>{
        String type;
        String name;
        long price;
        long quality;

        Part(String type, String name, long price, long quality) {
            this.type = type;
            this.name = name;
            this.price = price;
            this.quality = quality;
        }

        @Override
        public int compareTo(Part o) {
            return Long.compare(this.price, o.price);
//            return this.price - o.price;
        }
    }
    static class PartOrderByQuality implements Comparable<PartOrderByQuality>{
        String type;
        String name;
        long price;
        long quality;

        PartOrderByQuality(String type, String name, long price, long quality) {
            this.type = type;
            this.name = name;
            this.price = price;
            this.quality = quality;
        }

        @Override
        public int compareTo(PartOrderByQuality o) {
            return Long.compare(this.quality, o.quality);
//            return this.quality - o.quality;
        }
    }

    static int n, b;
    static HashMap<String, PriorityQueue<Part>> map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for(int test=0; test<t; test++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            n = Integer.parseInt(stk.nextToken());
            b = Integer.parseInt(stk.nextToken());
            map = new HashMap<>();

            for(int a=0; a<n; a++) {
                stk = new StringTokenizer(br.readLine());
                String type = stk.nextToken();
                String name = stk.nextToken();
                long price = Long.parseLong(stk.nextToken());
                long quality = Long.parseLong(stk.nextToken());

                Part part = new Part(type, name, price, quality);
                PriorityQueue parts = map.getOrDefault(type, new PriorityQueue<Part>());
                parts.offer(part);
                map.put(type, parts);
            }

        }
        solution();
    }

    public static void solution() {
        // 성능 순으로 저장하고 있을 큐
        PriorityQueue<PartOrderByQuality> cq = new PriorityQueue<>();

        // 제일 싼것들로 조립해본다
        long sum = 0;
        for(String type : map.keySet()) {
            Part p = map.get(type).poll();
            sum += p.price;
            cq.offer(new PartOrderByQuality(p.type, p.name, p.price, p.quality));
        }

        // 제일 퀄리티 낮은 부품을 업그레이드
        long result = 0;
        while(true) {
            PartOrderByQuality lowest = cq.poll();

            boolean changeable = false;
            Part next = null;

            while(true) {

                // 업그레이드 할 물건이 없으면 ㅂㅂ
                if(map.get(lowest.type).isEmpty())
                    break;

                next = map.get(lowest.type).poll();

                // 더 비싼거 꺼내봤는데 퀄리티가 더 낮으면 선택 X
                if(next.quality <= lowest.quality)
                    continue;

                // 예산 안에서 바꿀 수 없음
                if(sum - lowest.price + next.price > b)
                    break;

                // 위 경우가 아니면 바꿀 수 있는 것임
                changeable = true;
                break;
            }
            if(changeable) {
                cq.offer(new PartOrderByQuality(next.type, next.name, next.price, next.quality));
                sum = sum - lowest.price + next.price;
            }
            else {
                result = lowest.quality;
                break;
            }

        }
        System.out.println(result);
    }
}
