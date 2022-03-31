import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_9081 {

    static int t;
    static char[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        for(int test=0; test<t; test++) {
            String input = br.readLine();
            arr = input.toCharArray();

            // 모든 경우의 수를 다 만들고 찾으면 시간초과
            // 뒤에서부터 체크
            int index = arr.length-1;
            for(int i=arr.length-1; i>0; i--) {
                // 내 앞의 글자가 나보다 작아진 경우의 인덱스를 저장
                if(arr[i] > arr[i-1]) {
                    index = i-1;
                    break;
                }
            }

            // 다시 뒤에서부터 체크
            for(int i=arr.length-1; i>index; i--) {
                // arr[index]보다 큰 문자 찾기
                if(arr[i] > arr[index]) {
                    // 두 문자 위치 바꿔주기
                    swap(i, index);
                    break;
                }
            }

            // arr[index] 뒷부분은 정렬해주세요
            Arrays.sort(arr, index+1, arr.length);

            System.out.println(arr);
        }
    }

    public static void swap(int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
