
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_24513 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] town = new int[N][M];
        int[] count = new int[3];
        int[][] infectedTime = new int[N][M]; // 감염된 시간

        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                town[i][j] = Integer.parseInt(st.nextToken());
                if (town[i][j] == 1 || town[i][j] == 2) {
                    q.add(new int[] { i, j });
                    infectedTime[i][j] = 0;
                }
            }
        }

        q.add(new int[]{-1,-1}); // 이게빠지면 time+1
        int time=1;

        int[][] direction = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        // bfs
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int row = temp[0];
            int col = temp[1];
            if (row == -1 ){
                if(q.isEmpty()) break;
                time+=1;
                q.add(new int[]{-1,-1});
                continue;
            }
            count[town[row][col] - 1] += 1;
            if (town[row][col] == 3) continue;
            for (int i = 0; i < 4; i++) {
                int nrow = row + direction[i][0];
                int ncol = col + direction[i][1];
                if (nrow < 0 || nrow >= N || ncol < 0 || ncol >= M
                    || town[nrow][ncol] == -1 || town[nrow][ncol] == 3) continue;
                else if (town[nrow][ncol] == 0) {
                    town[nrow][ncol] = town[row][col];
                    q.add(new int[] { nrow, ncol });
                    infectedTime[nrow][ncol]=time;
                } else if (town[nrow][ncol] != town[row][col] && infectedTime[nrow][ncol] == time) {
                    infectedTime[nrow][ncol] = -1;
                    town[nrow][ncol] = 3;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            System.out.print(count[i] + " ");
        }
    }
}
