import java.io.*;
import java.util.*;

class Component {
    int price, quality;
    Component(int price, int quality) {
        this.price = price;
        this.quality = quality;
    }
}

public class BOJ_3691 {
    static int N, B;
    static final int INF = (int)1e9 + 10;
    static HashMap<String, ArrayList<Component>> componentMap;
    static boolean isPossible(int want) {
        int sum = 0;
        for (ArrayList<Component> components : componentMap.values()) {
            int price = INF;
            for (Component component : components) {
                if (component.quality >= want) {
                    price = Math.min(price, component.price);
                }
            }
            sum += price;
            if (sum > B) return false;
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            componentMap = new HashMap<>();
            for (int i = 0; i < N; i++) {
                String type;
                int price, quality;
                st = new StringTokenizer(br.readLine());
                type = st.nextToken(); st.nextToken();
                price = Integer.parseInt(st.nextToken());
                quality = Integer.parseInt(st.nextToken());
                componentMap.putIfAbsent(type, new ArrayList<>());
                ArrayList<Component> components = componentMap.get(type);
                components.add(new Component(price, quality));
            }
            int lo = 0, hi = INF;
            while (lo + 1 < hi) {
                int mid = (lo + hi) / 2;
                if (isPossible(mid)) lo = mid;
                else hi = mid;
            }
            System.out.println(lo);
        }
    }
}
