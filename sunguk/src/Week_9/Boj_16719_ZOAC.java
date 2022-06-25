package Week_9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;


/**
 * https://www.acmicpc.net/problem/16719
 */
public class Boj_16719_ZOAC {

    ArrayList<PriorityCharacter> characters = new ArrayList<>();
    StringBuilder answer = new StringBuilder(); // 최종 답 출력을 위한 StringBuilder
    StringBuilder lastOutputValue = new StringBuilder(); // 최근 값을 저장하는 StringBuilder
    boolean[] isChecked;
    ArrayList<PriorityCharacter> outputArray = new ArrayList(); // 새로운 값이 들어오면 원본순서로 정렬하기 위한 ArrayList

    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String inputString = br.readLine();
            for (int i = 0; i < inputString.length(); i++) {
                characters.add(new PriorityCharacter(inputString.charAt(i), i));
            }
            isChecked = new boolean[inputString.length()];
            findLowestAsciiFromCurrentPosition(0);
            if (answer.length() > 0) {
                System.out.println(answer.delete(answer.length() - 1, answer.length()).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void findLowestAsciiFromCurrentPosition(int index) {
        PriorityQueue<PriorityCharacter> pqFromIndex = new PriorityQueue<>((o1, o2) -> {
            return o1.getAsciiCodeValue() - o2.getAsciiCodeValue();
        });
        for (int i = index; i < characters.size(); i++) {
            pqFromIndex.offer(characters.get(i));
        }
        while (!pqFromIndex.isEmpty()) {
            PriorityCharacter lowestAscii = pqFromIndex.poll();
            int originalPosition = lowestAscii.getOriginalPriority();
            if (isChecked[originalPosition] == false) {
                isChecked[originalPosition] = true;
                storeCharacter(originalPosition);
                findLowestAsciiFromCurrentPosition(originalPosition + 1);
            }
        }
    }

    private void storeCharacter(int index) {
        outputArray.add(characters.get(index));
        outputArray.sort((o1, o2) -> {
            return o1.getOriginalPriority() - o2.getOriginalPriority();
        });
        lastOutputValue.delete(0, lastOutputValue.length());
        for (PriorityCharacter priorityCharacter : outputArray) {
            lastOutputValue.append((char) priorityCharacter.getAsciiCodeValue());
        }
        answer.append(lastOutputValue).append("\n");
    }
}

class PriorityCharacter {

    private final int asciiCodeValue;
    private final int originalPriority;

    public PriorityCharacter(int asciiCodeValue, int originalPriority) {
        this.asciiCodeValue = asciiCodeValue;
        this.originalPriority = originalPriority;
    }

    public int getOriginalPriority() {
        return originalPriority;
    }

    public int getAsciiCodeValue() {
        return asciiCodeValue;
    }
}