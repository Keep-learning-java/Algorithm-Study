import java.io.*;
import java.util.*;

public class BOJ_11954 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        LinkedList<String> outputs = new LinkedList<>();
        int brace = 0;
        StringBuilder word = new StringBuilder();
        for (char c : input.toCharArray()) {
            String output;
            if (c == '{') {
                output = " ".repeat(brace++ * 2) + "{";
                outputs.add(output);
            }
            else if (c == '}') {
                if (word.length() > 0) {
                    output = " ".repeat(brace * 2) + word;
                    word = new StringBuilder();
                    outputs.add(output);
                }
                output = " ".repeat(--brace * 2) + "}";
                outputs.add(output);
            }
            else if (c == ',') {
                if (word.length() == 0) {
                    output = outputs.pollLast();
                    output += ",";
                    outputs.addLast(output);
                }
                else {
                    output = " ".repeat(brace * 2) + word + ",";
                    word = new StringBuilder();
                    outputs.addLast(output);
                }
            }
            else word.append(c);
        }
        for (String output : outputs) {
            System.out.println(output);
        }
    }
}
