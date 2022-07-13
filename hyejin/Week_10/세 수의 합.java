import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class BOJ_2295 {

    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        HashSet<Integer> set = new HashSet<>();
        for(int i=0; i<N; i++) {
            set.add(Integer.parseInt(br.readLine()));
        }
        HashSet<Integer> abSum = getABSum(set);
        HashSet<Integer> dSet = getDSet(set, abSum);

        int result = 0;
        for(int d : dSet) {
            result = Math.max(result, d);
        }

        System.out.println(result);
    }

    public static HashSet<Integer> getABSum(HashSet<Integer> set) {
        // a+b 모든 경우의 수
        HashSet<Integer> ret = new HashSet<>();

        for(int a : set) {
            for(int b : set) {
                ret.add(a + b);
            }
        }

        return ret;
    }

    public static HashSet<Integer> getDSet(HashSet<Integer> set, HashSet<Integer> abSum) {
        // d-c == a+b 인 모든 d를 저장
        HashSet<Integer> dSet = new HashSet<>();

        for(int d : set) {
            for(int c : set) {
                if(abSum.contains(d-c)) {
                    dSet.add(d);
                }
            }
        }

        return dSet;
    }
}
