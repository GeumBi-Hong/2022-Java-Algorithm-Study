import java.io.*;
import java.util.*;

public class BOJ_19538 {
    static final int SZ = 200_010;
    static int N, M;
    static int[] degree, time, count;
    static ArrayList<Integer>[] adj;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        degree = new int[N + 1];
        time = new int[N + 1];
        count = new int[N + 1];
        for (int u = 1; u <= N; u++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v;
            while ((v = Integer.parseInt(st.nextToken())) != 0) {
                adj[u].add(v);
                degree[u]++;
            }
        }
        M = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Queue<Integer> queue = new LinkedList<>();
        Arrays.fill(time, -1);
        for (int i = 1, in; i <= M; i++) {
            in = Integer.parseInt(st.nextToken());
            time[in] = 0;
            queue.add(in);
        }
        while(!queue.isEmpty()) {
            int curr = queue.poll();
            for (int next : adj[curr]) {
                if (time[next] != -1) continue;
                int half = degree[next] / 2;
                if ((degree[next] & 1) == 1) half++;
                if (++count[next] >= half) {
                    queue.add(next);
                    time[next] = time[curr] + 1;
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            bw.write(time[i] + " ");
        }
        bw.flush();
        bw.close();
    }
}