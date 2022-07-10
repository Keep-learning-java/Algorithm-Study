package Week_10;

import java.util.*;

public class Leet_2078_Two_Furthest_Houses_With_Different_Colors {
    public int maxDistance(int[] colors) {
        Map<Integer, List<House>> houseMap = new HashMap<>();
        for (int i = 0; i < colors.length; i++) {
            List<House> houses = houseMap.getOrDefault(colors[i], new ArrayList());
            houses.add(new House(i, colors[i]));
            houseMap.put(colors[i], houses);
        }
        houseMap.forEach((integer, arrayList) -> {
            arrayList.sort(Comparator.comparingInt(o -> o.index));
        });
        Set<Integer> colorOfHouses = houseMap.keySet();
        int longestDistance = 0;
        for (Integer colorOfHouse : colorOfHouses) {
            for (Integer colorOfHouse2 : colorOfHouses) {
                if (!Objects.equals(colorOfHouse, colorOfHouse2)) {
                    List<House> list1 = houseMap.get(colorOfHouse);
                    List<House> list2 = houseMap.get(colorOfHouse2);
                    longestDistance = Math.max(longestDistance, Math.abs(list1.get(0).index - list2.get(0).index));
                    longestDistance = Math.max(longestDistance, Math.abs(list1.get(list1.size() - 1).index - list2.get(0).index));
                    longestDistance = Math.max(longestDistance, Math.abs(list1.get(list1.size() - 1).index - list2.get(list2.size() - 1).index));
                    longestDistance = Math.max(longestDistance, Math.abs(list1.get(0).index - list2.get(list2.size() - 1).index));
                }
            }
        }
        return longestDistance;

    }

    class House {
        int index;
        int color;

        public House(int index, int color) {
            this.index = index;
            this.color = color;
        }
    }
}
