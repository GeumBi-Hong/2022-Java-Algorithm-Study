class 합승택시요금 {
    final int INF = (int)1e8;
    public int solution(int n, int s, int a, int b, int[][] edges) {
        int[][] dist = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) dist[i][j] = INF;
                else dist[i][j] = 0;
            }
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], fare = edge[2];
            dist[u][v] = dist[v][u] = fare;
        }
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        int answer = INF;
        for (int i = 1; i <= n; i++) {
            answer = Math.min(answer, dist[s][i] + dist[i][a] + dist[i][b]);
        }
        return answer;
    }
}