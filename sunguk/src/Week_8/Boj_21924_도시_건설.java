package Week_8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Boj_21924_도시_건설 {

    private int[] parent;

    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            int buildingCount = Integer.parseInt(st.nextToken()); // N, 3~10^5 3~100000
            int roadCount = Integer.parseInt(st.nextToken()); // M, 2~


            long totalPrice = 0;
            long minPrice = 0;
            List<Road> roads = new ArrayList<>();

            while (roadCount-- > 0) {
                st = new StringTokenizer(br.readLine());
                int leftNumber = Integer.parseInt(st.nextToken());
                int rightNumber = Integer.parseInt(st.nextToken());
                int price = Integer.parseInt(st.nextToken());
                totalPrice += price;
                Road road = new Road(leftNumber, rightNumber, price);
                roads.add(road);
            }

            roads.sort(Comparator.naturalOrder());

            parent = new int[buildingCount + 1];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }

            int selectedRoadCounts = 0;

            for (Road road : roads) {
                if (selectedRoadCounts == buildingCount - 1) break;

                int leftBuildingNumber = road.getLeftNumber();
                int rightBuildingNumber = road.getRightNumber();

                int firstBuildingParentId = findParent(leftBuildingNumber);
                int secondBuildingParentId = findParent(rightBuildingNumber);

                if (firstBuildingParentId != secondBuildingParentId) {
                    syncParentsAsSmallerNumber(leftBuildingNumber, rightBuildingNumber);
                    minPrice += road.getPrice();
                    selectedRoadCounts++;
                }
            }

            boolean hasFoundAnswer = true;
            for (int i = 1; i < buildingCount; i++) {
                int firstParent = findParent(i);
                int secondParent = findParent(i + 1);
                if (firstParent != secondParent) {
                    hasFoundAnswer = false;
                    break;
                }
            }

            if (hasFoundAnswer) {
                System.out.println(totalPrice - minPrice);
            } else {
                System.out.println(-1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    int findParent(int buildingNumber) {
        if (parent[buildingNumber] != buildingNumber) {
            parent[buildingNumber] = findParent(parent[buildingNumber]);
        }
        return parent[buildingNumber];
    }

    void syncParentsAsSmallerNumber(int firstBuildingNumber, int secondBuildingNumber) {
        int firstParent = findParent(firstBuildingNumber);
        int secondParent = findParent(secondBuildingNumber);
        if (firstParent < secondParent) {
            parent[secondParent] = firstParent;
        } else {
            parent[firstParent] = secondParent;
        }
    }
}

class Road implements Comparable<Road> {
    private final int leftNumber;
    private final int rightNumber;
    private final int price;

    public int getLeftNumber() {
        return leftNumber;
    }

    public int getRightNumber() {
        return rightNumber;
    }

    public int getPrice() {
        return price;
    }

    public Road(int leftBuildingNumber, int rightBuildingNumber, int price) {
        this.leftNumber = leftBuildingNumber;
        this.rightNumber = rightBuildingNumber;
        this.price = price;
    }

    @Override
    public int compareTo(Road o) { // ascending sort 오름차순
        return price - o.price;
    }
}