package Week_9;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/3691
 */
public class Boj_3691_컴퓨터_조립 {

    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int caseCount = Integer.parseInt(br.readLine());

            while (caseCount-- > 0) {
                String line = br.readLine();
                StringTokenizer st = new StringTokenizer(line, " ");
                Map<String, PriorityQueue> partsMap = new HashMap<>(); // key: type, value: parts of type
                int numberOfParts = Integer.parseInt(st.nextToken());
                int budget = Integer.parseInt(st.nextToken());

                while (numberOfParts-- > 0) {
                    st = new StringTokenizer(br.readLine(), " ");
                    String type = st.nextToken();
                    st.nextToken();

                    int costOfThePart = Integer.parseInt(st.nextToken());
                    int qualityOfThePart = Integer.parseInt(st.nextToken());
                    PriorityQueue<Part> costAscendingPriorityQueue = partsMap.getOrDefault(type, new PriorityQueue<Part>(Comparator.comparingInt(Part::getCost)));
                    costAscendingPriorityQueue.offer(new Part(type, costOfThePart, qualityOfThePart));

                    partsMap.put(type, costAscendingPriorityQueue);
                }

                long totalCost = 0L;
                PriorityQueue<Part> qualityAscendingPriorityQueue = new PriorityQueue<>((o1, o2) -> o1.getQuality() - o2.getQuality());
                for (PriorityQueue<Part> pq : partsMap.values()) { // fill with all types of parts
                    Part cheapPartOfType = pq.poll();
                    totalCost += cheapPartOfType.getCost();
                    qualityAscendingPriorityQueue.offer(cheapPartOfType);
                }

                List<Integer> collectedQualities = new ArrayList<>(); // it's for figuring it out what the lowest quality of the collected parts is
                while (!qualityAscendingPriorityQueue.isEmpty()) {
                    Part currentLowestQualityPart = qualityAscendingPriorityQueue.poll();
                    String type = currentLowestQualityPart.getType();
                    if (partsMap.get(type).isEmpty()) { // empty means there is any part of this type
                        collectedQualities.add(currentLowestQualityPart.getQuality()); // no option to choose
                        continue;
                    }

                    Part nextCheapPartOfTheType = (Part) partsMap.get(type).poll(); // ready to search better part of this type
                    if (nextCheapPartOfTheType.getQuality() <= currentLowestQualityPart.getQuality()) {
                        qualityAscendingPriorityQueue.offer(currentLowestQualityPart);
                        continue;
                    }

                    int costOfNextPart = nextCheapPartOfTheType.getCost();
                    int costOfCurrentPart = currentLowestQualityPart.getCost();
                    if (totalCost - costOfCurrentPart + costOfNextPart <= budget) {
                        totalCost = totalCost - costOfCurrentPart + costOfNextPart;
                        qualityAscendingPriorityQueue.offer(nextCheapPartOfTheType); // keep next part
                    } else {
                        qualityAscendingPriorityQueue.offer(currentLowestQualityPart); // keep current part
                    }
                }
                collectedQualities.sort(Comparator.naturalOrder());
                System.out.println(collectedQualities.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class Part {
    private String type;
    private int cost;
    private int quality;

    Part(String type, int cost, int quality) {
        this.type = type;
        this.cost = cost;
        this.quality = quality;
    }

    public String getType() {
        return type;
    }

    public int getCost() {
        return cost;
    }

    public int getQuality() {
        return quality;
    }
}