import java.io.*;
import java.util.*;

class Options {
    ArrayList<String> options;
    ArrayList<String> outputs;
    boolean[] isUsedAlphabet;
    Options() {
        options = new ArrayList<>();
        outputs = new ArrayList<>();
        isUsedAlphabet = new boolean[60];
    }
    void addOption(String option) {
        options.add(option);
    }
    char getFirstAlphabet(String option) {
        return option.charAt(0);
    }
    boolean isSmall(char c) {
        return c >= 'a' && c <= 'z';
    }
    int getAlphabetIndex(char c) {
        if (isSmall(c)) return c - 'a';
        return c - 'A';
    }
    boolean processFirstNotUsed(String[] splitOption) {
        boolean flag = false;
        StringBuilder tOption = new StringBuilder();
        for (String option : splitOption) {
            char first = getFirstAlphabet(option);
            int idx = getAlphabetIndex(first);
            if (!flag && !isUsedAlphabet[idx]) {
                isUsedAlphabet[idx] = true;
                String suffix = option.substring(1);
                tOption.append("[" + first + "]" + suffix + " ");
                flag = true;
            }
            else tOption.append(option + " ");
        }
        if (flag) outputs.add(tOption.toString().trim());
        return flag;
    }
    void processNotUsed(String originOption) {
        boolean flag = false;
        StringBuilder tOption = new StringBuilder();
        for (char c : originOption.toCharArray()) {
            if (c == ' ') {
                tOption.append(c);
                continue;
            }
            int idx = getAlphabetIndex(c);
            if (!flag && !isUsedAlphabet[idx]) {
                isUsedAlphabet[idx] = true;
                tOption.append("[" + c + "]");
                flag = true;
            }
            else tOption.append(c);
        }
        outputs.add(tOption.toString());
    }
    void process() {
        for (String option : options) {
            String[] splitOption = option.split(" ");
            boolean ret = processFirstNotUsed(splitOption);
            if (ret) continue;
            processNotUsed(option);
        }
    }
    void print() {
        for (String output : outputs) {
            System.out.println(output);
        }
    }
}

public class BOJ_1283 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Options options = new Options();
        for (int i = 1; i <= N; i++) {
            String option = br.readLine();
            options.addOption(option);
        }
        options.process();
        options.print();
    }
}