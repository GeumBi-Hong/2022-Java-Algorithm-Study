
public class Solution {
    public static int solution(int[][] board, int[][] skill) {
        int answer = 0;

        int rowLength = board.length;
        int colLength = board[0].length;

        int[][] sumgraph = new int[rowLength + 1][colLength + 1];

        for (int[] sk : skill) {
            int type = sk[0];
            int r1 = sk[1];
            int c1 = sk[2];
            int r2 = sk[3];
            int c2 = sk[4];
            int degree = sk[5];

            if (type == 1) {
                degree = -degree;
            }
            sumgraph[r1][c1] += degree;
            sumgraph[r1][c2 + 1] += -degree;
            sumgraph[r2 + 1][c2 + 1] += degree;
            sumgraph[r2 + 1][c1] += -degree;
        }

        // 누적합
        // 세로합
        for (int j = 0; j < colLength + 1; j++) {
            for (int i = 1; i < rowLength + 1; i++) {
                sumgraph[i][j] += sumgraph[i - 1][j];
            }
        }
        // 가로합
        for (int i = 0; i < rowLength + 1; i++) {
            for (int j = 1; j < colLength + 1; j++) {
                sumgraph[i][j] += sumgraph[i][j - 1];
            }
        }

        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                if (board[i][j] + sumgraph[i][j] > 0) {answer++;}
                board[i][j] += sumgraph[i][j];
            }
        }

        return answer;
    }

}
