import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_6198 {
    static int n;
    static long[] height;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        height = new long[n];

        // input
        for(int i=0; i<n; i++) {
            height[i] = Long.parseLong(br.readLine());
        }
        long result = 0;
        for(int i=0; i<n; i++) {
            result += solution(i);
        }
        System.out.println(result);
    }

    // index번째 건물 관리인이 옥상정원 확인할 수 있는 총 수 반환
    public static long solution(int index) {
        int count = 0;
        for(int i=index+1; i<n; i++) {
            // 오른쪽이 나보다 높거나 같으면 끝
            if(height[index] <= height[i]) {
                return count;
            }
            // 낮으면 더해줌
            else {
                count++;
            }
        }
        return count;
    }
}
