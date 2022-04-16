package 스터디.Week_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class 마법사상어와비바리기 {
    static int n, m, r, c;
    static int[][] grid, disi;
    static Queue<int[]> cloudQ;
    static ArrayList<int[]> movedCloud;
    static boolean[][] cloudCheck;
    static int[][] direction =
            { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };

    public static void main(String[] args) throws IOException {
        init();

        for (int i = 0; i < m; i++) {
            moveCloud(i);
            waterCopy();
            creatCloud();
            movedCloud.clear();
        }
        int answer = 0;

        for (int i = 0; i < n; i++) {
            answer += IntStream.of(grid[i]).sum();
        }
        System.out.println(answer);
    }

    private static void creatCloud() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cloudCheck[i][j] || grid[i][j] < 2) {continue;}
                int[] dot = { i, j };
                grid[i][j] -= 2;
                cloudQ.add(dot);
            }
        }
    }

    private static void waterCopy() {
        for (int[] dot : movedCloud) {
            int waterCnt = 0;
            for (int j = 1; j <8; j+=2) {
                int nr = dot[0] + direction[j][0];
                int nc = dot[1] + direction[j][1];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n || grid[nr][nc]==0) {continue;}
                waterCnt++;
            }
            grid[dot[0]][dot[1]] += waterCnt;
        }
    }

    private static void moveCloud(int i) {

        int dirx = direction[disi[i][0]][0] * disi[i][1];
        int diry = direction[disi[i][0]][1] * disi[i][1];
        cloudCheck = new boolean[n][n];

        while (!cloudQ.isEmpty()) {
            int[] now = cloudQ.poll();
            int nr = now[0] + dirx;
            int nc = now[1] + diry;

            if (nr >= 0) {nr %= n;} else {nr = (n + nr % n) % n;}
            if (nc >= 0) {nc %= n;} else {nc = (n + nc % n) % n;}

            grid[nr][nc] += 1;
            movedCloud.add(new int[] { nr, nc });
            cloudCheck[nr][nc] =true;
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        disi = new int[m][2];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            disi[i][0] = Integer.parseInt(st.nextToken()) - 1;
            disi[i][1] = Integer.parseInt(st.nextToken());
        }
        movedCloud = new ArrayList<>();


        cloudQ = new LinkedList<>();
        cloudQ.add(new int[] { n - 1, 0 });
        cloudQ.add(new int[] { n - 2, 0 });
        cloudQ.add(new int[] { n - 1, 1 });
        cloudQ.add(new int[] { n - 2, 1 });

    }
}
