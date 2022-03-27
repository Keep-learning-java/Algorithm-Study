import java.io.*;
import java.util.*;

public class BOJ_9612 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> hashMap = new HashMap<>();
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String in = st.nextToken();
            if (hashMap.containsKey(in)) {
                int value = hashMap.get(in);
                hashMap.put(in, value + 1);
            }
            else {
                hashMap.put(in, 1);
            }
        }
        List<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(hashMap.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue() != o2.getValue()) return o2.getValue().compareTo(o1.getValue());
                return o2.getKey().compareTo(o1.getKey());
            }
        });
        System.out.println(entryList.get(0).getKey() + " " + entryList.get(0).getValue());
    }
}
