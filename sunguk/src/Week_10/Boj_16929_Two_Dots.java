package Week_10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_16929_Two_Dots {


    int[] dirR = new int[]{0, 0, 1, -1};
    int[] dirC = new int[]{1, -1, 0, 0};
    int rows, columns;
    int[][] map;

    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line, " ");
            rows = Integer.parseInt(st.nextToken());
            columns = Integer.parseInt(st.nextToken());
            map = new int[rows][columns];
            for (int i = 0; i < rows; i++) {
                line = br.readLine();
                for (int j = 0; j < columns; j++) {
                    map[i][j] = line.charAt(j);
                }
            }
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    Dot startingPoint = new Dot(i, j);
                    if (hasCycleIfStartsFrom(startingPoint)) {
                        System.out.println("Yes");
                        return;
                    }
                }
            }

            System.out.println("No");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    boolean[][] isVisited;

    private boolean hasCycleIfStartsFrom(Dot startingPoint) {
        isVisited = new boolean[rows][columns];
        isVisited[startingPoint.r][startingPoint.c] = true;
        return moveToPossibleDotFrom(startingPoint, startingPoint, 1);
    }

    private boolean moveToPossibleDotFrom(Dot dot, Dot startingPoint, int dotCounts) {
        for (int i = 0; i < dirR.length; i++) {
            int rr = dot.r + dirR[i];
            int cc = dot.c + dirC[i];
            if (rr >= 0 && cc >= 0 && rr < rows && cc < columns) {
                if (rr == startingPoint.r && cc == startingPoint.c && dotCounts >= 4) {
                    return true;
                }
                if (!isVisited[rr][cc] && map[rr][cc] == map[startingPoint.r][startingPoint.c]) {
                    isVisited[rr][cc] = true;
                    if (moveToPossibleDotFrom(new Dot(rr, cc), startingPoint, dotCounts + 1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    class Dot {
        int r, c;

        public Dot(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}