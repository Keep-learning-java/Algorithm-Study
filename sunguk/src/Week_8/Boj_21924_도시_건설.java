package Week_8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_21924_도시_건설 {

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
                int firstBuildingNumber = Integer.parseInt(st.nextToken());
                int secondBuildingNumber = Integer.parseInt(st.nextToken());
                int price = Integer.parseInt(st.nextToken());
                totalPrice += price;
                Road road = new Road(firstBuildingNumber, secondBuildingNumber, price);
                roads.add(road);
            }

            roads.sort(Comparator.naturalOrder());

            Map<Integer, Building> buildingMap = new HashMap<>();
            int selectedRoadCounts = 0;
            for (Road road : roads) {
                if (selectedRoadCounts == buildingCount - 1) break;

                int smallerBuildingNumber = road.getSmallerBuildingNumber();
                int biggerBuildingNumber = road.getBiggerBuildingNumber();

                Building firstBuilding = buildingMap.getOrDefault(smallerBuildingNumber, new Building(smallerBuildingNumber));
                Building secondBuilding = buildingMap.getOrDefault(biggerBuildingNumber, new Building(biggerBuildingNumber));

                int firstBuildingParentId = getParentBuildingOf(firstBuilding).getId();
                int secondBuildingParentId = getParentBuildingOf(secondBuilding).getId();

                if (firstBuildingParentId != secondBuildingParentId) {
                    /**
                     * TODO: 22. 6. 19. building 번호가 낮은 것을 기준으로 부모로 설정하니 동기화시 문제가 됨
                     * 
                     * 7 6
                     * 1 2 1
                     * 3 4 1
                     * 5 6 1
                     * 1 7 2
                     * 7 3 3
                     * 5 7 3
                     *
                     *    7
                     *  / | \
                     * 1  3  5
                     * |  |  |
                     * 2  4  6
                     *
                     * 1, 3, 5 가 각각이 자신이 최상위 부모로 인지함. 동기화 필요
                      */

                    syncParent(firstBuilding, secondBuilding);
                    buildingMap.put(smallerBuildingNumber, firstBuilding);
                    buildingMap.put(biggerBuildingNumber, secondBuilding);
                    minPrice += road.getPrice();
                    selectedRoadCounts++;
                }
            }

            boolean hasFoundAnswer = true;
            List<Building> buildings = new ArrayList<>(buildingMap.values());
            for (int i = 1; i < buildings.size(); i++) {
                Building firstParent = updateParentBuildingOf(buildings.get(i - 1));
                Building secondParent = updateParentBuildingOf(buildings.get(i));
                if (firstParent.getId() != secondParent.getId()) {
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

    private Building updateParentBuildingOf(Building building) {
        if (building.hasParent()) {
            Building parentBuilding = getParentBuildingOf(building.getParentBuilding());
            building.setParentBuilding(parentBuilding);
            return parentBuilding;
        }
        return building.getParentBuilding();
    }


    private Building getParentBuildingOf(Building building) {
        if (building.hasParent()) {
            return getParentBuildingOf(building.getParentBuilding());
        }
        return building.getParentBuilding();
    }

    private void syncParent(Building firstBuilding, Building secondBuilding) {
        Building firstBuildingParent = getParentBuildingOf(firstBuilding);
        Building secondBuildingParent = getParentBuildingOf(secondBuilding);
        if (firstBuildingParent.getId() < secondBuildingParent.getId()) {
            firstBuilding.setParentBuilding(secondBuildingParent);
        } else {
            secondBuilding.setParentBuilding(firstBuildingParent);
        }
    }
}

class Road implements Comparable<Road> {
    private final int smallerBuildingNumber;
    private final int biggerBuildingNumber;
    private final int price;

    public int getSmallerBuildingNumber() {
        return smallerBuildingNumber;
    }

    public int getBiggerBuildingNumber() {
        return biggerBuildingNumber;
    }

    public int getPrice() {
        return price;
    }

    public Road(int firstBuildingNumber, int secondBuildingNumber, int price) {
        this.smallerBuildingNumber = Math.min(firstBuildingNumber, secondBuildingNumber);
        this.biggerBuildingNumber = Math.max(firstBuildingNumber, secondBuildingNumber);
        this.price = price;
    }

    @Override
    public int compareTo(Road o) { // ascending sort 오름차순
        return price - o.price;
    }
}


class Building {
    private final int id;
    private Building parentBuilding = this;

    public Building(int id) {
        this.id = id;
    }

    public Building getParentBuilding() {
        return parentBuilding;
    }

    public int getId() {
        return id;
    }

    boolean hasParent() {
        return parentBuilding.getId() != id;
    }

    public void setParentBuilding(Building building) {
        parentBuilding = building;
    }
}