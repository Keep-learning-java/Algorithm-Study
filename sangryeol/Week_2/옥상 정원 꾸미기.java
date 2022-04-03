import java.io.*;
import java.util.*;

public class BOJ_6198 {
    public static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] heights = new int[N];
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(br.readLine());
        }
        Stack<Integer> stk = new Stack<>();
        long answer = 0;
        for (int i = 0; i < N; i++) {
            int height = heights[i];
            while (!stk.isEmpty() && stk.peek() <= height) stk.pop();
            stk.push(height);
            answer += 0L + stk.size() - 1;
        }
        System.out.println(answer);
    }
}
