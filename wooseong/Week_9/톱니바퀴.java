package 스터디.Week_9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 톱니바퀴 {
    static int[] rot;
    static int[][] gear;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        gear = new int[4][8];

        for (int i = 0; i < 4; i++) {
            String str = br.readLine();
            gear[i] = Arrays.stream(str.split("")).mapToInt(Integer::parseInt).toArray();
        }
        int n = Integer.parseInt(br.readLine());
        int[][] gndir = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            gndir[i][0] = Integer.parseInt(st.nextToken());
            gndir[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            rot = new int[4];
            visited = new boolean[4];
            int gn = gndir[i][0] - 1;
            int dir = gndir[i][1];
            rot[gn] = dir;
            dfs(gn);
            rotate();
        }

        int answer = 0;

        for (int i = 0; i < 4; i++) {
            if (gear[i][0] == 1) {
                answer += Math.pow(2, i);
            }
        }
        System.out.println(answer);
    }

    static void dfs(int gn) {
        if (gn < 0 || gn >= 4) {return;}

        if (rot[gn] == 0 || visited[gn]) {return;}

        visited[gn] = true;

        if (gn > 0 && gear[gn - 1][2] != gear[gn][6]) {
            rot[gn - 1] = rot[gn] * -1;
        }
        if (gn < 3 && gear[gn + 1][6] != gear[gn][2]) {
            rot[gn + 1] = rot[gn] * -1;
        }
        dfs(gn - 1);
        dfs(gn + 1);
    }

    //배열에서 뒤로미루는게 어려웠음
    static void rotate() {
        for (int i = 0; i < 4; i++) {
            if (rot[i] == 1) {
                int temp = gear[i][7];
                for (int j = 7; j > 0; j--) {
                    gear[i][j] = gear[i][j-1];
                }
                gear[i][0] = temp;
            }
            if (rot[i] == -1) {
                int temp = gear[i][0];
                for (int j = 1; j < 8; j++) {
                    gear[i][j-1] = gear[i][j];
                }
                gear[i][7] = temp;
            }

        }
    }

}
