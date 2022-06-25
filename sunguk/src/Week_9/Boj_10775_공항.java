package Week_9;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj_10775_공항 {

    int[] highestNumberGates;
    boolean[] isChecked;

    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int numberOfGates = Integer.parseInt(br.readLine());
            int numberOfAirplane = Integer.parseInt(br.readLine());
            highestNumberGates = new int[numberOfGates + 1];
            isChecked = new boolean[numberOfGates + 1];
            for (int i = 0; i < highestNumberGates.length; i++) {
                highestNumberGates[i] = i;
            }
            int answer = 0;
            while (numberOfAirplane-- > 0) {
                int airplaneDockableMaxGateNumber = Integer.parseInt(br.readLine());
                int highestGateNumber = findHighestNumberGate(airplaneDockableMaxGateNumber);
                if (0 < highestGateNumber && highestGateNumber <= airplaneDockableMaxGateNumber) {
                    isChecked[highestGateNumber] = true;
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

    private int findHighestNumberGate(int gateNumber) {
        if (gateNumber == highestNumberGates[gateNumber] && !isChecked[gateNumber]) {
            return gateNumber;
        } else {
            return highestNumberGates[gateNumber] = findHighestNumberGate(gateNumber - 1);
        }
    }

}
