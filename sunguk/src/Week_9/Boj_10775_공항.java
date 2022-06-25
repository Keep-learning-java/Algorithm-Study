package Week_9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class Boj_10775_공항 {

    TreeSet<Integer> possibleGates = new TreeSet<>();

    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int numberOfGates = Integer.parseInt(br.readLine());
            int numberOfAirplane = Integer.parseInt(br.readLine());
            for (int i = 0; i < numberOfGates; i++) {
                possibleGates.add(i + 1);
            }
            int answer = 0;
            while (numberOfAirplane-- > 0) {
                int airplaneDockableMaxGateNumber = Integer.parseInt(br.readLine());
                Integer possibleMaxGateNumber = possibleGates.floor(airplaneDockableMaxGateNumber);
                if (possibleMaxGateNumber != null) {
                    possibleGates.remove(possibleMaxGateNumber);
                    answer++;
                } else {
                    break;
                }
            }
            System.out.println(answer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}