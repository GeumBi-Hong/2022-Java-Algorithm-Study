import java.io.*;
import java.util.*;

public class BOJ_1949 {
    public static int N;
    public static int[] persons;
    public static int[][] dp;
    public static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    public static int f(int curr, int selected, int parent) {
        if (dp[curr][selected] != -1) return dp[curr][selected];
        dp[curr][selected] = 0;
        if (selected == 1) dp[curr][selected] = persons[curr];
        for (int next : adj.get(curr)) {
            if (next == parent) continue;
            int ret = f(next, 0, curr);
            if (selected == 0) ret = Math.max(ret, f(next, 1, curr));
            dp[curr][selected] += ret;
        }
        return dp[curr][selected];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        persons = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            persons[i] = Integer.parseInt(st.nextToken());
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0, u, v; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            u--; v--;
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        dp = new int[N][2];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println(Math.max(f(0, 0, -1), f(0, 1, -1)));
    }
}
