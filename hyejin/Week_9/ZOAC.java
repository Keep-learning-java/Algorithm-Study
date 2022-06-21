import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_16719 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        char[] arr = input.toCharArray();


        boolean[] visited = new boolean[101];
        dfs(arr, visited, 0, arr.length);
    }

    public static void dfs(char[] arr, boolean[] visited, int start, int end) {
        // 가장 작은 문자 찾기
        char min = Character.MAX_VALUE;
        int minIndex = -1;
        for(int i=start; i<end; i++) {
            // 작고 방문한 적 없는 문자를 찾아내세요..
            if(min > arr[i] && !visited[i]) {
                min = arr[i];
                minIndex = i;
            }
        }

        // 못찾으면 SKIP
        if(minIndex == -1)
            return;

        // 찾아냈음..
        visited[minIndex] = true;
        // 현재까지 찾은 것 출력
        for(int i=0; i<arr.length; i++) {
            if(visited[i])
                System.out.print(arr[i]);
        }
        System.out.println();

        dfs(arr, visited, minIndex, end);
        dfs(arr, visited, start, minIndex);
    }
}
