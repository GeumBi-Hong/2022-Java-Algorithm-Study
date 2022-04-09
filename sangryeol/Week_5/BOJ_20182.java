import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int v, cost;

    Edge(int to, int cost) {
        this.v = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}

public class BOJ_20182 {
    static final int MAXSZ = 100_010;
    static final int INF = (int)1e9;
    static int N, M, C;
    static ArrayList<Edge>[] adj = new ArrayList[MAXSZ];
    public static int dijk(int start, int end) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[][] dist = new int[21][MAXSZ];
        for (int[] array : dist) {
            Arrays.fill(array, INF);
        }
        int ret = INF;
        for (int i = 1; i <= 20; i++) {
            dist[i][start] = 0;
            pq.add(new Edge(start, 0));
            while (!pq.isEmpty()) {
                Edge curr = pq.poll();
                if (curr.cost > dist[i][curr.v]) continue;
                for (Edge next : adj[curr.v]) {
                    if (i < next.cost) continue;
                    if (dist[i][next.v] > dist[i][curr.v] + next.cost) {
                        dist[i][next.v] = dist[i][curr.v] + next.cost;
                        pq.add(new Edge(next.v, dist[i][next.v]));
                    }
                }
            }
            if (dist[i][end] <= C) return i;
        }
        return -1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1, u, v, w; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            adj[u].add(new Edge(v, w));
            adj[v].add(new Edge(u, w));
        }
        System.out.println(dijk(start, end));
    }
}