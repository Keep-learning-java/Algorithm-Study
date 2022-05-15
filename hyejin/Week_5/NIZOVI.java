import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_11954 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        Stack<Character> stack = new Stack<>();
        for(int i=0; i<input.length(); i++) {
            char ch = input.charAt(i);

            if(ch == '{') {
                printTab(stack.size());
                stack.push(ch);
                // 앞이 닫는 괄호
                if(i-1 >= 0 && input.charAt(i-1) == '}') {
                    System.out.println();
                }
                System.out.println("{");
            }
            else if(ch == '}') {
                stack.pop();
                // 앞에 열린 괄호면
                if(input.charAt(i-1) != '{' && input.charAt(i-1) != '}') {
                    System.out.println();
                }

                // 앞이 닫힌 괄호면
                if(input.charAt(i-1) == '}') {
                    System.out.println();
                }
                printTab(stack.size());
                System.out.print("}");
            }
            else if(ch == ',') {
                // 앞이 여는 괄호 or 쉼표면 탭
                if(input.charAt(i-1) == '{' || input.charAt(i-1) == ',') {
                    printTab(stack.size());
                }

                System.out.println(',');
            }
            else {
                // 앞이 닫는 괄호인 경우
                if(input.charAt(i-1) == '}') {
                    System.out.println();
                }
                // 앞이 문자가 아닌 경우에 탭
                if(input.charAt(i-1) < 'a' || input.charAt(i-1) > 'z') {
                    printTab(stack.size());
                }

                System.out.print(ch);
            }
        }
    }

    public static void printTab(int count) {
        for(int i=0; i<count; i++) {
            System.out.print("  ");
        }
    }
}
