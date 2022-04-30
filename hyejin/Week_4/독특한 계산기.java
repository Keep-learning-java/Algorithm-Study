import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_19591 {

    static Deque<Long> number;
    static Deque<Character> oper;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        number = new LinkedList<>();
        oper = new LinkedList<>();

        // input
        StringBuilder sb = new StringBuilder(input);
        for(int i=0; i<input.length(); i++) {
            char c = input.charAt(i);

            if(c == '+' || c == '-' || c == '*' || c == '/') {
                // 처음 음수일 경우
                if(i == 0) {
                    continue;
                }
                oper.add(c);
                sb.setCharAt(i, ' ');
            }
        }
        StringTokenizer stk = new StringTokenizer(sb.toString());

        while(stk.hasMoreTokens()) {
            number.add(Long.parseLong(stk.nextToken()));
        }

        while(!oper.isEmpty()) {
            // firstN1 firstN2 lastN2 lastN1
            long firstN1 = number.pollFirst();
            long firstN2 = number.getFirst();
            long lastN1, lastN2;
            if(number.size() == 1) {
                lastN1 = firstN2;
                lastN2 = firstN1;
            }
            else {
                lastN1 = number.pollLast();
                lastN2 = number.getLast();
            }

            // 1. 부호 두개 꺼내서 우선순위 판단
            char firstOper = oper.getFirst();
            char lastOper = oper.getLast();

            // 앞
            if((firstOper == '*' || firstOper == '/')
                    && (lastOper == '+' || lastOper == '-')) {
                long result = calculate(firstN1, firstN2, firstOper);

                selectFirst(result, lastN1, lastN2);
            }
            // 뒤
            else if((firstOper == '+' || firstOper == '-')
                    && (lastOper == '*' || lastOper == '/')) {
                long result = calculate(lastN2, lastN1, lastOper);

                selectLast(result, firstN1, firstN2);
            }
            // 우선순위 같음
            else {
                long firstResult = calculate(firstN1, firstN2, firstOper);
                long lastResult = calculate(lastN2, lastN1, lastOper);

                if(firstResult >= lastResult) {
                    selectFirst(firstResult, lastN1, lastN2);
                }
                else {
                    selectLast(lastResult, firstN1, firstN2);
                }
            }
        }
        System.out.println(number.poll());
    }

    public static void selectFirst(long result, long n1, long n2) {
        number.removeFirst();
        number.offerFirst(result);
        number.offerLast(n1);
        oper.removeFirst();
    }

    public static void selectLast(long result, long n1, long n2) {
        number.removeLast();
        number.offerLast(result);
        number.offerFirst(n1);
        oper.removeLast();
    }

    public static long calculate(long n1, long n2, char operator) {
        if(operator == '+') {
            return n1 + n2;
        }
        else if(operator == '-') {
            return n1 - n2;
        }
        else if(operator == '*') {
            return n1 * n2;
        }
        else if(operator == '/') {
            return n1 / n2;
        }

        return 0;
    }
}
