import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_23629 {

    static String[] numbers = {
            "ZERO", "ONE", "TWO", "THREE",
            "FOUR", "FIVE", "SIX", "SEVEN",
            "EIGHT", "NINE"
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        // 처음이 음수인 경우를..
        boolean startWithNegative = false;
        if(input.charAt(0) == '-') {
            startWithNegative = true;
            input = input.substring(1);
        }
        String[] split = input.split("\\+|-|x|/|=");
        char[] oper = new char[split.length-1]; // 연산자만 따로
        int index = 0;
        for(int i=0; i<input.length(); i++) {
            char ch = input.charAt(i);

            if(ch == '+' || ch == '-' || ch == 'x' || ch == '/') {
                oper[index++] = ch;
            }
        }

        int[] conversion = new int[split.length]; // 변환 후 숫자 저장

        for(int i=0; i<split.length; i++) {
            String piece = split[i];
            // 비어있는 경우, 숫자가 아닌 경우?
            if(piece.equals("")) {
                // 끝내버려
                System.out.println("Madness!");
                return;
            }
            // 열가지 숫자에 대해서 검사하셈.. 갖고있냐?
            boolean flag = false;
            for(int j=0; j<numbers.length; j++) {
                String number = numbers[j];

                if(piece.contains(number)) {
                    flag = true;
                    piece = piece.replaceAll(number, String.valueOf(j));
                }
            }
            if(!flag) {
                System.out.println("Madness!");
                return;
            }

            // 숫자로만 이루어지지 않았음..
            if(!piece.matches("[0-9]+")){
                System.out.println("Madness!");
                return;
            }
            int converted = Integer.parseInt(piece);
            if(startWithNegative) {
                converted = -converted;
                startWithNegative = false;
            }
            conversion[i] = converted;

        }

        print(conversion, oper);
        System.out.println(converse(calculate(conversion, oper)));
    }

    public static void print(int[] num, char[] oper) {
        for(int i=0; i<oper.length; i++) {
            System.out.print(num[i]);
            System.out.print(oper[i]);
        }
        System.out.println(num[num.length-1]+"=");
    }

    public static long calculate(int[] num, char[] oper) {
        long result = num[0];

        for(int i=0; i<oper.length; i++) {
            int n = num[i+1];
            char o = oper[i];

            if(o == '+') {
                result += n;
            }
            else if(o == '-') {
                result -= n;
            }
            else if(o == 'x') {
                result *= n;
            }
            // 나누기
            else {
                result /= n;
            }
        }

        return result;
    }

    public static String converse(long n) {
        String nString = String.valueOf(n);
        StringBuilder result = new StringBuilder();

        // 음수인 경우
        int index = 0;
        if(n < 0) {
            index = 1;
            result.append('-');
        }
        for(int i=index; i<nString.length(); i++) {
            String s = nString.substring(i, i+1);
            result.append(numbers[Integer.parseInt(s)]);
        }
        return result.toString();
    }
}