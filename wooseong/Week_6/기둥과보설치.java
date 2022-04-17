package 스터디.Week_6;

import java.util.ArrayList;
import java.util.Arrays;

public class 기둥과보설치 {
    public static void main(String[] args) {
        int n = 5;
        int[][] bf = {
                { 0, 0, 0, 1 }, { 2, 0, 0, 1 }, { 4, 0, 0, 1 }, { 0, 1, 1, 1 }, { 1, 1, 1, 1 }, { 2, 1, 1, 1 },
                { 3, 1, 1, 1 }, { 2, 0, 0, 0 }, { 1, 1, 1, 0 }, { 2, 2, 0, 1 }
        };
        int[][] x = new Solution().solution(n, bf);

        for (int i = 0; i < x.length; i++) {System.out.println(Arrays.toString(x[i]));}
    }
}

class Solution {
    boolean[][] beams, pillars;
    int n;

    public int[][] solution(int n, int[][] build_frame) {
        beams = new boolean[n + 3][n + 3];
        pillars = new boolean[n + 3][n + 3];
        this.n = n;

        for (int[] bf : build_frame) {
            int x = bf[0]+1;
            int y = bf[1]+1;
            int a = bf[2];
            int b = bf[3];

            if (b == 1) {
                if (a == 0 && pillarVerify(x, y)) {
                    pillars[x][y] = true;
                }
                if (a == 1 && beamVerify(x, y)) {
                    beams[x][y] = true;
                }
            }
            if (b == 0) {
                if (a == 0) {pillars[x][y] = false;}
                else if (a == 1) {beams[x][y] = false;}

                if (brutalForce(x, y)) {continue;}

                if (a == 0) {pillars[x][y] = true;}
                else if (a == 1) {beams[x][y] = true;}
            }

        }
        ArrayList<int[]> list = new ArrayList<>();

        for (int i = 1; i <= n + 1; i++) {
            for (int j = 1; j <= n + 1; j++) {
                if (pillars[i][j]) {list.add(new int[] { i-1, j-1, 0 });}
                if (beams[i][j]) {list.add(new int[] { i-1, j-1, 1 });}
            }
        }

        return list.toArray(new int[0][3]);

    }

    private boolean brutalForce(int x, int y) {
        for (int i = 1; i <= n+1; i++) {
            for (int j = 1; j <= n+1; j++) {
                if (pillars[i][j] && !pillarVerify(i, j)) {
                    return false;
                }
                if (beams[i][j] && !beamVerify(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean pillarVerify(int x, int y) {
        return y == 1 || pillars[x][y - 1] || beams[x][y] || beams[x - 1][y];
    }

    private boolean beamVerify(int x, int y) {
        return pillars[x][y - 1] || pillars[x + 1][y - 1] || (beams[x - 1][y] && beams[x + 1][y]);
    }
}



