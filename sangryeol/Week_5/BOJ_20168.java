import java.io.*;
import java.util.*;

public class BOJ_20168 {
    static final int INF = (int)1e9;
    static int N, M, C, start, end;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int[][] dist = new int[N+1][N+1];
        int[][] edge = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) edge[i][j] = 0;
                else edge[i][j] = INF;
            }
        }
        for (int i = 1, u, v, w; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            edge[u][v] = edge[v][u] = w;
        }
        for (int c = 1; c <= 1000; c++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (edge[i][j] <= c) dist[i][j] = edge[i][j];
                    else dist[i][j] = INF;
                }
            }
            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= N; j++) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
            if (dist[start][end] <= C) {
                System.out.println(c);
                return;
            }
        }
        System.out.println(-1);
    }
}