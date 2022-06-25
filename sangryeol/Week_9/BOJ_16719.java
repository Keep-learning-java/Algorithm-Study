import java.io.*;
import java.util.*;

public class BOJ_16719 {
    static boolean[] selected;
    static ArrayList<String> strings = new ArrayList<>();
    static int getMinIndex(char[] input, int left, int right) {
        char minAlphabet = 'Z';
        int index = left;
        for (int i = left; i < right; i++) {
            if (minAlphabet > input[i]) {
                minAlphabet = input[i];
                index = i;
            }
        }
        return index;
    }
    static void splitString(char[] input, int left, int right) {
        if (left >= right) return;
        int index = getMinIndex(input, left, right);
        selected[index] = true;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length; i++) {
            if (selected[i]) {
                sb.append(input[i]);
            }
        }
        strings.add(sb.toString());
        splitString(input, index + 1, right);
        splitString(input, left, index);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();
        int inputLength = input.length;
        selected = new boolean[inputLength];
        splitString(input, 0, inputLength);
        for (String output : strings) {
            System.out.println(output);
        }
    }
}
